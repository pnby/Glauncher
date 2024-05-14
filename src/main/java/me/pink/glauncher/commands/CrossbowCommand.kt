package me.pink.glauncher.commands

import me.pink.glauncher.handlers.giveItemToPlayer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class CrossbowCommand : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Эта команда может быть выполнена только игроком.")
            return true
        }

        if (args.size != 3) {
            sender.sendMessage("Использование: /gl give <launcher/grenade> <player>")
            return true
        }


        if (args[1] == "launcher") {
            val material = Material.getMaterial(Material.CROSSBOW.toString())!!
            val customName = ""

            val receiver = Bukkit.getPlayer(args[2])!!


            val itemObj = giveItemToPlayer(receiver, material, customName, 1.0)
            sender.sendMessage("Выдан предмет с параметрами $itemObj")

            val argsAsString = args.joinToString(" ")
            sender.sendMessage(argsAsString)
            return true
        }


        if (args[1] == "grenade") {
            val material = Material.getMaterial(Material.ARROW.toString())!!
            val customName = ""

            val receiver = Bukkit.getPlayer(args[2])!!

            val itemObj = giveItemToPlayer(receiver, material, customName, 1.0, value="Arrow")
            sender.sendMessage("Выдан предмет с параметрами $itemObj")

            val argsAsString = args.joinToString(" ")
            sender.sendMessage(argsAsString)
            return true
        }

        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String>? {
        if (args.size == 1) {
            return listOf("give")
        } else if (args.size == 2) {
            return listOf("launcher", "grenade").filter { it.startsWith(args[1]) }
        } else if (args.size == 3 && args[1] == "grenade") {
            return Bukkit.getOnlinePlayers().map { it.name }.filter { it.startsWith(args[2]) }
        }
        return null
    }
}