package me.pink.glauncher.handlers

import me.pink.glauncher.Glauncher
import me.pink.glauncher.GlauncherInstance
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

fun spawnTNT(player: Player, speed: Double) {
    val plugin = GlauncherInstance.instance ?: return
    val playerLocation = player.eyeLocation
    val playerDirection = getPlayerDirection(player)
    val spawnLocation = playerLocation.add(playerDirection.normalize().multiply(2))


    val tnt = player.world.spawnEntity(spawnLocation, EntityType.PRIMED_TNT) as TNTPrimed
    tnt.velocity = playerDirection.normalize().multiply(speed)
    tnt.fuseTicks = plugin.getValueFromConfig("tnt-fuse-ticks")

}

fun getPlayerDirection(player: Player): Vector {
    val yaw = player.location.yaw
    val pitch = player.location.pitch

    val directionX = (-sin(Math.toRadians(yaw.toDouble())) * cos(Math.toRadians(pitch.toDouble())))
    val directionY = (-sin(Math.toRadians(pitch.toDouble())))
    val directionZ = (cos(Math.toRadians(yaw.toDouble())) * cos(Math.toRadians(pitch.toDouble())))

    return Vector(directionX, directionY, directionZ)
}

