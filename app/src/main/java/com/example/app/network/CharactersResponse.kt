package com.example.app.network

// link to API Documentation: https://rickandmortyapi.com/documentation#character

data class RickAndMortyCharactersResponse(val info: RickAndMortyInfo, val results: List<RickAndMortyCharacter>)

data class RickAndMortyCharacter(val name: String, val image: String? = null)

data class RickAndMortyInfo(val count: Int, val pages: Int, val next: String? = null, val prev: String? = null)