package me.pink.glauncher

import me.pink.glauncher.commands.CrossbowCommand
import me.pink.glauncher.listeners.CrossbowLaunch
import me.pink.glauncher.listeners.TNTExplosionHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

object GlauncherInstance {
    var instance: Glauncher? = null
}

class Glauncher : JavaPlugin(), Listener {
    private var configurations: Configurations? = null

    override fun onEnable() {
        server.pluginManager.registerEvents(CrossbowLaunch(), this)
        server.pluginManager.registerEvents(TNTExplosionHandler(), this)

        getCommand("gl")?.setExecutor(CrossbowCommand())

        configurations = Configurations(this)
        configurations?.load()
        saveConfigValues()

        GlauncherInstance.instance = this
    }

    override fun onDisable() {
        configurations = null
    }

    private fun saveConfigValues() {
        val config = configurations?.get(ConfigurationType.CONFIG)


        val hasObsidianChance = config?.contains("obsidian-explosion-chance") ?: false
        val hasFuseTicks = config?.contains("tnt-fuse-ticks") ?: false

        if (!hasObsidianChance) {
            config?.set("obsidian-explosion-chance", 50)
        }
        if (!hasFuseTicks) {
            config?.set("tnt-fuse-ticks", 70)
        }

        configurations?.save(ConfigurationType.CONFIG)
    }


    fun getValueFromConfig(key: String): Int {
        return configurations?.get(ConfigurationType.CONFIG)?.getInt(key) ?: 0
    }
}
