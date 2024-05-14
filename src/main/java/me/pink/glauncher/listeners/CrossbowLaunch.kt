package me.pink.glauncher.listeners

import me.pink.glauncher.GlauncherInstance
import me.pink.glauncher.handlers.spawnTNT
import me.pink.glauncher.utils.hasNBTKey
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent

class CrossbowLaunch : Listener {

    @EventHandler
    fun onBowShot(event: EntityShootBowEvent) {
        val bow = event.bow ?: return
        val arrowEntity = event.projectile
        val shooter = event.entity

        val plugin = GlauncherInstance.instance ?: return

        if (!hasNBTKey(bow, "GLauncher", "MLauncher")) {
            return
        }

        if (arrowEntity is Arrow) {
            val arrowItemStack = arrowEntity.itemStack


            if (hasNBTKey(bow, "GLauncher", "MLauncher") && !hasNBTKey(arrowItemStack, "GLauncher", "Arrow")) {
                return
            }

            if (shooter is Player) {
                spawnTNT(shooter, plugin.getValueFromConfig("tnt-speed").toDouble())
                arrowEntity.remove()
                return
            }
        }
    }
}