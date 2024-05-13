package me.pink.glauncher.listeners


import me.pink.glauncher.handlers.spawnTNT
import me.pink.glauncher.utils.hasNBTKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent

class CrossbowLaunch : Listener {
    @EventHandler
    fun onProjectileLaunch(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        val shooter = projectile.shooter

        if (shooter is Player) {
            val crossbowItem = shooter.equipment.itemInMainHand

            if (hasNBTKey(crossbowItem, "Glauncher", "Mlauncher")) {
                projectile.remove()
                spawnTNT(shooter, 2.0)
            }
        }
    }
}