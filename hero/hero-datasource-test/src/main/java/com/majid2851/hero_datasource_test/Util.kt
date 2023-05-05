package com.majid2851.hero_datasource_test

import com.majid2851.hero_datasource.network.HeroDto
import com.majid2851.hero_datasource.network.toHero
import com.majid2851.hero_domain.Hero
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json= Json {
    ignoreUnknownKeys=true
}
@OptIn(ExperimentalSerializationApi::class)
fun serialzeHeroData(jsonData:String):List<Hero>{
    val heros:List<Hero> = json.decodeFromString<List<HeroDto>>(jsonData)
        .map {
            it.toHero()
        }
    return heros
}