buildscript {
    repositories {
        repositories {
            maven {
                // GTNH ASM Fork
                name = "gtnh"
                url = uri("http://jenkins.usrv.eu:8081/nexus/content/groups/public/")
                isAllowInsecureProtocol = true
                mavenContent { includeGroup("org.ow2.asm") }
            }
            maven {
                name = "forge"
                url = uri("https://maven.minecraftforge.net")
                mavenContent {
                    includeGroup("net.minecraftforge")
                    includeGroup("net.minecraftforge.srg2source")
                    includeGroup("de.oceanlabs.mcp")
                    includeGroup("cpw.mods")
                }
            }
            maven {
                // because Srg2Source needs an eclipse dependency.
                name = "eclipse"
                url = uri("https://repo.eclipse.org/content/groups/eclipse/")
                mavenContent { includeGroup("org.eclipse.jdt") }
            }
            maven {
                name = "mojang"
                url = uri("https://libraries.minecraft.net/")
                mavenContent {
                    includeGroup("com.ibm.icu")
                    includeGroup("com.mojang")
                    includeGroup("com.paulscode")
                    includeGroup("org.lwjgl.lwjgl")
                    includeGroup("tv.twitch")
                    includeGroup("net.minecraft")
                }
            }
            mavenCentral() { mavenContent() { excludeGroup("org.ow2.asm") } }
        }
    }
}

repositories {
    maven {
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    mavenLocal()
}

dependencies {
    api("testmod:testmod:1.0:dev")
    // For manual testing of dependency code
    // api("curse.maven:nei-358228:4275622-dev-sources")
    // api("com.cloudbees:diff4j:1.1")
}

plugins {
    id("com.gtnewhorizons.retrofuturagradle")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
    withSourcesJar()
    withJavadocJar()
}

minecraft {
    mcVersion.set("1.7.10")
    applyMcDependencies.set(true)
    skipSlowTasks.set(true)
}

group = "testdepmod"
version = "1.0"

publishing {
    publications {
        create<MavenPublication>("testdepmod") {
            from(components["java"])
        }
    }
}
