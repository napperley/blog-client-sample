import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

group = "org.webscene"
version = "0.1-SNAPSHOT"

buildscript {
    var kotlinVer: String by extra

    kotlinVer = "1.1.60"

    repositories {
        mavenCentral()
    }
    
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVer))
    }
}

apply {
    plugin("kotlin2js")
}

val kotlinVer: String by extra

repositories {
    mavenCentral()
    maven {
        url = uri("libs")
    }
}

dependencies {
    @Suppress("LocalVariableName")
    val WEB_SCENE_CLIENT_VER = "0.1-SNAPSHOT"

    "compile"(kotlin("stdlib-js", kotlinVer))
    "compile"("org.webscene:webscene-client:$WEB_SCENE_CLIENT_VER")
}

val compileKotlin2Js by tasks.getting(Kotlin2JsCompile::class) {
    val fileName = "blog-client.js"

    kotlinOptions.outputFile = "${projectDir.absolutePath}/web/js/$fileName"
    kotlinOptions.sourceMap = true
    doFirst { File("${projectDir.absolutePath}/web/js").deleteRecursively() }
}
val build by tasks
val assembleWeb by tasks.creating(Copy::class) {
    dependsOn("classes")
    configurations["compile"].forEach { file ->
        from(zipTree(file.absolutePath)) {
            includeEmptyDirs = false
            include { fileTreeElement ->
                val path = fileTreeElement.path

                path.endsWith(".js") && path.startsWith("META-INF/resources/") || !path.startsWith("META_INF/")
            }
        }
    }
    from(compileKotlin2Js.destinationDir)
    into("${projectDir.absolutePath}/web/js")
}

task<Copy>("deployClient") {
    dependsOn(compileKotlin2Js, assembleWeb)
    from("${projectDir.absolutePath}/src/main/resources")
    into("${projectDir.absolutePath}/web")
}