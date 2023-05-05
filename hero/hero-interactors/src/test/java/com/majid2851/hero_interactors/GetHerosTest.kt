package com.majid2851.hero_interactors

import com.majid2851.core.DataState
import com.majid2851.core.ProgressBarState
import com.majid2851.core.UIComponent
import com.majid2851.hero_datasource.cache.HeroDatabase
import com.majid2851.hero_datasource_test.cache.HeroCacheFake
import com.majid2851.hero_datasource_test.cache.HeroDatabaseFake
import com.majid2851.hero_datasource_test.network.HeroServiceFake
import com.majid2851.hero_datasource_test.network.HeroServiceResponseType
import com.majid2851.hero_datasource_test.network.data.HeroDataMalformed
import com.majid2851.hero_datasource_test.network.data.HeroDataValid
import com.majid2851.hero_datasource_test.network.data.HeroDataValid.NUM_HEROS
import com.majid2851.hero_datasource_test.serialzeHeroData
import com.majid2851.hero_domain.Hero
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetHerosTest
{
    private lateinit var getHeros: GetHeros

    @Test
    fun getHeros_success()
    {
        runBlocking {
            val heroDatabase= HeroDatabaseFake()
            val heroCache=HeroCacheFake(
                heroDatabase
            )
            val heroService=HeroServiceFake().build(
                type=HeroServiceResponseType.GoodData
            )

            getHeros=GetHeros(
                heroCache = heroCache,
                service = heroService
            )


            var cachedHeros=heroCache.selectAll()
            assert(cachedHeros.isEmpty())


            val emission=getHeros.excecute().toList()

            assert(emission[0]==DataState.Loading<List<Hero>>(ProgressBarState.Loading))


            assert(emission[1] is DataState.Data)

            assert((emission[1] as DataState.Data).data?.size ?: 0 == NUM_HEROS)

            cachedHeros=heroCache.selectAll()


            assert(
                cachedHeros.size== NUM_HEROS
            )

            assert(emission[2]==DataState.Loading<List<Hero>>(ProgressBarState.Idle))

        }

    }



    @Test
    fun getHeros_malformed()= runBlocking {
        val heroDatabase= HeroDatabaseFake()
        val heroCache=HeroCacheFake(
            heroDatabase
        )
        val heroService=HeroServiceFake().build(
            type=HeroServiceResponseType.MalformedData
        )

        getHeros=GetHeros(
            heroCache = heroCache,
            service = heroService
        )

        var cachedHeros=heroCache.selectAll()
        assert(cachedHeros.isEmpty())

        val heroData= serialzeHeroData(HeroDataValid.data)
        heroCache.insert(heroData)

        cachedHeros=heroCache.selectAll()
        assert(!cachedHeros.isEmpty())

        val emission=getHeros.excecute().toList()


        assert(emission[0]==DataState.Loading<List<Hero>>(ProgressBarState.Loading))

        assert(emission[1] is DataState.Response)

        println("------------------------------->"+
                ((emission[1] as DataState.Response).uiComponent as UIComponent.Dialog).title)
        assert(
            ((emission[1] as DataState.Response).uiComponent as UIComponent.Dialog).title
                =="Error"
        )







        assert(emission[2]==DataState.Loading<List<Hero>>(ProgressBarState.Idle))





    }

    @Test
    fun getHero_EmptyList()= runBlocking {
        val heroDatabase= HeroDatabaseFake()
        val heroCache=HeroCacheFake(
            heroDatabase
        )
        val heroService=HeroServiceFake().build(
            type=HeroServiceResponseType.EmptyList
        )

        getHeros=GetHeros(
            heroCache = heroCache,
            service = heroService
        )

        val emission=getHeros.excecute().toList()

        assert(emission[0]==DataState.Loading<List<Hero>>(ProgressBarState.Loading))


        assert(emission[1] is DataState.Data)

        assert((emission[1] as DataState.Data).data?.size ?: 0 == 0)


        assert(emission[2]==DataState.Loading<List<Hero>>(ProgressBarState.Idle))
    }



}