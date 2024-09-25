plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "androidx.kylin.widget.photoview"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
}


// maven publish plugin configuration
afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "NexusOSS"
                url = uri(properties["MAVEN_URL"].toString())
                credentials {
                    username = properties["MAVEN_USER"].toString()
                    password = properties["MAVEN_PASSWD"].toString()
                }
            }
        }
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                groupId = android.namespace
                artifactId = "photoview-ktx"
                version = "1.0.0-alpha"
                pom {
                    name.set(android.namespace)
                    url.set("https://github.com/androidx-kylin/photoview-ktx")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("androidx-kylin")
                            name.set("rae")
                            email.set("raedev666@gmail.com")
                        }
                    }
                }
            }
        }
    }
}