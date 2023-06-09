plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies{
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDataSource))

    "implementation"(project(Modules.heroDomain))
    "implementation"(Kotlinx.coroutinesCore)


    "testImplementation"(project(Modules.heroDataSourceTest))
    "testImplementation"(Junit.junit4)
    "testImplementation"(Ktor.ktorClientMock)
    "testImplementation"(Ktor.clientSerialization)


}