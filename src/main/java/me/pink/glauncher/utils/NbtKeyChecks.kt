package me.pink.glauncher.utils

import de.tr7zw.nbtapi.NBTItem
import org.bukkit.inventory.ItemStack

import de.tr7zw.nbtapi.NBTEntity
import org.bukkit.entity.Entity


fun hasNBTKey(item: ItemStack, key: String, value: String): Boolean {
    val nbtI = NBTItem(item)
    return nbtI.getString(key) == value
}


fun hasNBTKeyEntity(entity: Entity, key: String, value: String): Boolean {
    val nbtI = NBTEntity(entity)
    return nbtI.getString(key) == value
}
