package com.example.mobiledevlabs.data

import com.example.mobiledevlabs.data.model.Character
import com.example.mobiledevlabs.data.model.CharacterApi
import com.example.mobiledevlabs.data.model.toModel
import com.example.mobiledevlabs.data.model.toModels
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class CharacterRepository(
    private val client: HttpClient
) {

    suspend fun getCharacters(from: Int = 1201, to: Int = 1250): List<Character> = coroutineScope {
        val deferred = (from..to).map { id ->
            async {
                try {
                    val characterApi: CharacterApi = client.get("$BASE_URL/$id").body()
                    characterApi.toModel()
                } catch (e: Exception) {
                    null
                }
            }
        }

        deferred.awaitAll().filterNotNull()
    }

    suspend fun getCharacterById(id: Int): Character? {
        return try {
            val response: CharacterApi = client.get("$BASE_URL/$id").body()
            response.toModel()
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val BASE_URL =
            "https://www.anapioficeandfire.com/api/characters"
    }
}
