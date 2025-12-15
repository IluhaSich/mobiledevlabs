package com.example.mobiledevlabs.data

import android.content.Context
import com.example.mobiledevlabs.data.local.AppDatabase
import com.example.mobiledevlabs.data.local.CharacterEntity
import com.example.mobiledevlabs.data.model.Character
import com.example.mobiledevlabs.data.model.CharacterApi
import com.example.mobiledevlabs.data.model.toModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val client: HttpClient,
    context: Context
) {

    private val dao = AppDatabase.get(context).characterDao()


    fun observeCharacters(): Flow<List<Character>> =
        dao.observeAll().map { list -> list.map { it.toModel() } }

    suspend fun ensureCharactersLoaded(from: Int, to: Int) {
        if (dao.getAll().isNotEmpty()) return
        loadAndReplace(from, to)
    }

    suspend fun refresh(from: Int, to: Int) {
        loadAndReplace(from, to)
    }

    suspend fun loadMore(from: Int, to: Int) {
        val characters = loadFromApi(from, to)
        dao.insertAll(characters.map { it.toEntity() })
    }

    private suspend fun loadAndReplace(from: Int, to: Int) {
        val characters = loadFromApi(from, to)
        dao.clear()
        dao.insertAll(characters.map { it.toEntity() })
    }

    suspend fun loadFromApi(from: Int, to: Int): List<Character> =
        coroutineScope {
            (from..to).map { id ->
                async {
                    try {
                        val api: CharacterApi =
                            client.get("$BASE_URL/$id").body()
                        api.toModel()
                    } catch (e: Exception) {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
        }

    private fun Character.toEntity(): CharacterEntity =
        CharacterEntity(
            name = name,
            culture = culture,
            born = born,
            titles = titles.joinToString("|"),
            aliases = aliases.joinToString("|"),
            playedBy = playedBy.joinToString("|")
        )

    companion object {
        private const val BASE_URL =
            "https://www.anapioficeandfire.com/api/characters"
    }
}
