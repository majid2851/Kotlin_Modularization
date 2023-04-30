object Hilt {
    const val hiltVersion = "2.44.1"
    const val hiltLifeCycleVersion="1.0.0-alpha03"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltLifeCycle="androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifeCycleVersion"
}

object HiltTest {
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
}