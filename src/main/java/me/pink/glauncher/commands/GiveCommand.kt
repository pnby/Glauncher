package me.pink.glauncher.commands

import me.pink.glauncher.handlers.giveItemToPlayer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class GiveItemCommand : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Эта команда может быть выполнена только игроком.")
            return true
        }

        if (args.size != 2) {
            sender.sendMessage("Использование: /gl give <player>")
            return true
        }

        val material = Material.getMaterial(Material.CROSSBOW.toString())!!
        val customName = ""

        val receiver = Bukkit.getPlayer(args[1])!!


        val itemObj = giveItemToPlayer(receiver, material, customName, 1.0)
        sender.sendMessage("Выдан предмет с параметрами $itemObj")

        val argsAsString = args.joinToString(" ")
        sender.sendMessage(argsAsString)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String>? {
        if (alias.equals("gl", ignoreCase = true) && args.size == 2 && args[0].equals(
                "give",
                ignoreCase = true
            ) && sender is Player
        ) {
            val completions = mutableListOf<String>()

            Bukkit.getOnlinePlayers().forEach { player ->
                completions.add(player.name)
            }

            return completions
        }
        return null
    }
}