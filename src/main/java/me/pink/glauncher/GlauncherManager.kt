package me.pink.glauncher

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.*

class Configurations(private val plugin: Plugin) {
    private val configurations: MutableMap<ConfigurationType, Pair<FileConfiguration, File>> = EnumMap(ConfigurationType::class.java)

    private fun generateDefaultFile(name: String): File {
        val file = File(plugin.dataFolder, name)

        if (!file.exists()) {
            plugin.saveResource(name, false)
        }

        return file
    }

    fun load() {
        for (configurationType in ConfigurationType.entries) {
            if (configurations.containsKey(configurationType)) {
                continue
            }

            val configurationFile = generateDefaultFile(configurationType.fileName)
            val configuration = YamlConfiguration.loadConfiguration(configurationFile)

            configurations[configurationType] = Pair(configuration, configurationFile)
        }
    }

    fun reload() {
        configurations.clear()
        load()
    }

    private fun getEntry(configurationType: ConfigurationType): Pair<FileConfiguration, File> {
        return configurations[configurationType]!!
    }

    fun get(configurationType: ConfigurationType): FileConfiguration {
        return getEntry(configurationType).first
    }

    fun save(configurationType: ConfigurationType) {
        val entry = getEntry(configurationType)
        entry.first.save(entry.second)
    }
}

enum class ConfigurationType(val fileName: String) {
    CONFIG("config.yml")
}
