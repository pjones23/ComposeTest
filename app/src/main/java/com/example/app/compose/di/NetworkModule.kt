package com.example.app.compose.di

import RickAndMortyApi
import RickAndMortyService
import com.example.app.network.CharacterRepo
import org.koin.dsl.module

val networkModule = module {

    single <RickAndMortyService> {
        RickAndMortyService()
    }
    factory <RickAndMortyApi> {
        get<RickAndMortyService>().api
    }
    factory<CharacterRepo> {
        CharacterRepo(get<RickAndMortyApi>())
    }
}