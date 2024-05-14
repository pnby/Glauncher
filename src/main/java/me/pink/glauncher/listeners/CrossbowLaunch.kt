package me.pink.glauncher.listeners

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

        if (!hasNBTKey(bow, "GLauncher", "MLauncher")) {
            return
        }

        if (arrowEntity is Arrow) {
            val arrowItemStack = arrowEntity.itemStack


            if (hasNBTKey(bow, "GLauncher", "MLauncher") && !hasNBTKey(arrowItemStack, "GLauncher", "Arrow")) {
                event.isCancelled = true
                return
            }

            if (shooter is Player) {
                spawnTNT(shooter, 2.0)
                return
            }
        }
    }
}