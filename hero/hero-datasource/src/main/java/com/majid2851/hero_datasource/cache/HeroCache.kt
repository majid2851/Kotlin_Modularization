package com.majid2851.hero_datasource.cache

import com.majid2851.hero_domain.Hero
import com.squareup.sqldelight.db.SqlDriver


interface HeroCache {

    suspend fun getHero(id: Int): Hero?

    suspend fun removeHero(id: Int)

    suspend fun selectAll(): List<Hero>

    suspend fun insert(hero: Hero)

    suspend fun insert(heros: List<Hero>)

    suspend fun searchByName(localizedName: String): List<Hero>

    suspend fun searchByAttr(primaryAttr: String): List<Hero>

    suspend fun searchByAttackType(attackType: String): List<Hero>

    // Can select multiple roles
    suspend fun searchByRole(
        carry: Boolean = false,
        escape: Boolean = false,
        nuker: Boolean = false,
        initiator: Boolean = false,
        durable: Boolean = false,
        disabler: Boolean = false,
        jungler: Boolean = false,
        support: Boolean = false,
        pusher: Boolean = false,
    ): List<Hero>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroCache {
            return HeroCacheImpl(HeroDatabase(sqlDriver))
        }
        val schema: SqlDriver.Schema = HeroDatabase.Schema

        val dbName: String = "heros.db"
    }

}