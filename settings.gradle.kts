pluginManagement {
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/google")
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/jcenter")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/google")
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public")
        maven(url = "https://maven.aliyun.com/nexus/content/repositories/jcenter")
        google()
        mavenCentral()
    }
}

rootProject.name = "Scanner"
include(":app")
 