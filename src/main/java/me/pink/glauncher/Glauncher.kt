package me.pink.glauncher

import me.pink.glauncher.commands.GiveItemCommand
import me.pink.glauncher.listeners.CrossbowLaunch
import me.pink.glauncher.listeners.TNTExplosionHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


class Glauncher : JavaPlugin(), Listener {
    private var configurations: Configurations? = null

    override fun onEnable() {
        server.pluginManager.registerEvents(CrossbowLaunch(), this)
        server.pluginManager.registerEvents(TNTExplosionHandler(), this)

        getCommand("gl")?.setExecutor(GiveItemCommand())

        configurations = Configurations(this)
        configurations?.load()
        this.saveValueToConfig()

    }

    override fun onDisable() {
        configurations = null
    }

    fun getValueFromConfig(key: String): Int {
        return configurations?.get(ConfigurationType.CONFIG)?.getInt(key) ?: 0
    }

    private fun saveValueToConfig() {
        val config = configurations?.get(ConfigurationType.CONFIG)
        config?.set("obsidian-explosion-chance", 50)
        config?.set("explosion-power", 50)
        config?.set("tnt-fuse-ticks", 70)

        configurations?.save(ConfigurationType.CONFIG)
    }
}
