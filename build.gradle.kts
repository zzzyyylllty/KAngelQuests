import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.18"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

taboolib {
    env {
        // 安装模块
        install(Basic)
        install(Bukkit)
        install(BukkitUtil)
        install(BukkitUI)
        install(BukkitHook)
        install(BukkitFakeOp)
        install(BukkitNMSEntityAI)
        install(BukkitNMSDataSerializer)
        install(BukkitNMSItemTag)
        install(BukkitNMSUtil)
        install(BukkitNMS)
        install(MinecraftChat)
        install(MinecraftEffect)
        install(CommandHelper)
        install(I18n)
        install(Metrics)
        install(Database)
        install(DatabasePlayer)
        install(DatabasePtc)
        install(DatabasePtcObject)
        install(Kether)

    }
    version { taboolib = "6.2.0-beta20" }
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "adyeshach"
        url = uri("https://repo.tabooproject.org/repository/releases/")
    }
    maven {
        name = "mythicmobs"
        url = uri("https://mvn.lumine.io/repository/maven-public/")
    }
    maven {
        name = "citizens-repo"
        url = uri("https://maven.citizensnpcs.co/repo")
    }

    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    mavenCentral()
}
dependencies {
    compileOnly("net.citizensnpcs:citizens-main:2.0.35-SNAPSHOT")
    implementation("me.clip:placeholderapi:2.11.5")
    compileOnly("ink.ptms.adyeshach:api:2.0.19")
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("io.lumine:Mythic-Dist:5.6.1")

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
