package me.pink.glauncher.listeners

import me.pink.glauncher.GlauncherInstance
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent

class TNTExplosionHandler : Listener {

    @EventHandler
    fun onTNTExplode(event: EntityExplodeEvent) {
        val plugin = GlauncherInstance.instance ?: return
        val loc = event.entity.location

        val x = loc.blockX
        val y = loc.blockY
        val z = loc.blockZ

        if (loc.block.type != Material.WATER) {
            for (i in x - 2..x + 2) {
                for (j in y - 1..y + 1) {
                    for (k in z - 2..z + 2) { // Взрыв происходит в трёхмерном кубе 5x5x5
                        val targetBlock = loc.world.getBlockAt(i, j, k)
                        if (targetBlock.type == Material.OBSIDIAN || targetBlock.type == Material.CRYING_OBSIDIAN) {
                            val chance = (Math.random() * 100).toInt()

                            if (chance < plugin.getValueFromConfig("obsidian-explosion-chance")) {
                                targetBlock.breakNaturally()
                                loc.world.playSound(loc, Sound.BLOCK_STONE_BREAK, 10f, 1.5f)
                            }
                        }
                    }
                }
            }
        }

    }
}