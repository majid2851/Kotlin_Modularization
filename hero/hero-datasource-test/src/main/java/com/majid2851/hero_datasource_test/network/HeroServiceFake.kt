package com.majid2851.hero_datasource_test.network

import com.majid2851.hero_datasource.network.HeroService
import com.majid2851.hero_datasource.network.HeroServiceImpl
import com.majid2851.hero_datasource_test.network.data.HeroDataMalformed
import com.majid2851.hero_datasource_test.network.data.HeroDataValid
import com.majid2851.hero_datasource_test.network.data.HeroEmptyData
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort

class HeroServiceFake
{
    private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
    private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"
    fun build(
        type:HeroServiceResponseType
    ):HeroService {
        val client = HttpClient(MockEngine)
        {
            install(JsonFeature)
            {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            engine {
                addHandler { request ->
                    when (request.url.fullUrl) {
                        "https://api.opendota.com/api/heroStats" -> {
                            val responseHeaders = headersOf(
                                "Content-Type" to listOf("application/json", "charset=utf-8")
                            )
                            when (type) {
                                is HeroServiceResponseType.EmptyList -> {
                                    respond(
                                        HeroEmptyData.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }

                                is HeroServiceResponseType.MalformedData -> {
                                    respond(
                                        HeroDataMalformed.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }

                                is HeroServiceResponseType.GoodData -> {
                                    respond(
                                        HeroDataValid.data,
                                        status = HttpStatusCode.OK,
                                        headers = responseHeaders
                                    )
                                }

                                is HeroServiceResponseType.Http404 -> {
                                    respond(
                                        HeroEmptyData.data,
                                        status = HttpStatusCode.NotFound,
                                        headers = responseHeaders
                                    )
                                }
                            }
                        }

                        else -> error("Unhandled ${request.url.fullUrl}")
                    }
                }
            }
        }
        return HeroServiceImpl(client)
    }
}