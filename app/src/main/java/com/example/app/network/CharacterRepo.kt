package com.example.app.network

import RickAndMortyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepo(val api: RickAndMortyApi) {

    private var prev: String? = null
    private var next: String? = null

    fun getCharacters(notifier: CharacterRepo.CharacterRepoNotifier) {
        next?.takeIf { it.trim().isNotBlank() }?.let {
            // fetch next characters
            /** IMPORTANT: REMEMBER TO CALL ENQUEUE!!! */
            api.fetchNextCharacters(it).enqueue(getCallback(notifier))
        } ?: run {
            if(prev == null) {
                // initial load
                /** IMPORTANT: REMEMBER TO CALL ENQUEUE!!! */
                api.fetchCharacters().enqueue(getCallback(notifier))
            }
        }
    }

    private fun getCallback(notifier: CharacterRepoNotifier): Callback<RickAndMortyCharactersResponse> {
        return object : Callback<RickAndMortyCharactersResponse> {
            override fun onResponse(
                call: Call<RickAndMortyCharactersResponse>,
                response: Response<RickAndMortyCharactersResponse>
            ) {
                if(response.isSuccessful) {
                    prev = response.body()?.info?.prev
                    next = response.body()?.info?.next
                    notifier.handleSuccess(response.body()?.results)
                } else {
                    notifier.handleFailure()
                }
            }

            override fun onFailure(call: Call<RickAndMortyCharactersResponse>, t: Throwable) {
                notifier.handleFailure()
            }
        }
    }

    interface CharacterRepoNotifier {
        fun handleSuccess(response: List<RickAndMortyCharacter>?)
        fun handleFailure()
    }
}