plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    id("voyager-jvm-module")
    id("maven-publish")
}

group = "cafe.adriel.voyager"
version = providers.gradleProperty("version").getOrElse("1.0.0")

android {
    namespace = "cafe.adriel.voyager.rxjava"
}

kotlin {
    sourceSets {
        commonJvmMain.dependencies {
            api(projects.voyagerCore)
            api(projects.voyagerScreenmodel)
            compileOnly(libs.rxjava)
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
                name.set("Voyager RxJava")
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
