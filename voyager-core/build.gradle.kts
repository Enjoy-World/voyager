plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.atomicfu)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose.multiplatform)
    id("voyager-kmp-module")
    id("maven-publish")
}

group = "cafe.adriel.voyager"
version = providers.gradleProperty("version").getOrElse("1.0.0")

android {
    namespace = "cafe.adriel.voyager.core"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.runtimeSaveable)
            implementation(libs.coroutines.core)
        }
        commonJvmTest.dependencies {
            implementation(libs.junit.api)
            runtimeOnly(libs.junit.engine)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.androidx.lifecycle.savedState)
            implementation(libs.androidx.lifecycle.viewModelKtx)
            implementation(libs.androidx.lifecycle.viewModelCompose)
        }
    }
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
                name.set("Voyager Core")
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
