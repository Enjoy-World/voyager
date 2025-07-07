plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    id("voyager-android-module")
    id("maven-publish")
}

group = "cafe.adriel.voyager"
version = providers.gradleProperty("version").getOrElse("1.0.0")

android {
    namespace = "cafe.adriel.voyager.hilt"
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.voyagerScreenmodel)
    api(projects.voyagerNavigator)

    implementation(libs.androidx.lifecycle.savedState)
    implementation(libs.androidx.lifecycle.viewModelKtx)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.junit.api)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Enjoy-World/voyager")
            credentials {
                username = providers.gradleProperty("gpr.user").getOrElse("")
                password = providers.gradleProperty("gpr.key").getOrElse("")
            }
        }
    }
    publications {
        withType<MavenPublication> {
            pom {
                name.set("Voyager Hilt")
                description.set("A pragmatic navigation library for Jetpack Compose")
                url.set("https://github.com/adrielcafe/voyager")
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    url.set("https://github.com/adrielcafe/voyager")
                    connection.set("scm:git:ssh://git@github.com/adrielcafe/voyager.git")
                    developerConnection.set("scm:git:ssh://git@github.com/adrielcafe/voyager.git")
                }
                developers {
                    developer {
                        id.set("adrielcafe")
                        name.set("Adriel Cafe")
                        url.set("https://github.com/adrielcafe/")
                    }
                }
            }
        }
    }
}
