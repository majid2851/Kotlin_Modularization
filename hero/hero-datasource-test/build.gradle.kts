
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id(SqlDelight.plugin) version SqlDelight.version
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies{
    "implementation"(project(Modules.heroDataSource))
    "implementation"(project(Modules.heroDomain))
    "implementation"(Ktor.ktorClientMock)
    "implementation"(Ktor.clientSerialization)
}


