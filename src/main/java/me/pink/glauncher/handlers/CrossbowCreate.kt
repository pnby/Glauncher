package me.pink.glauncher.handlers

import de.tr7zw.nbtapi.NBTEntity
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.util.*


fun giveItemToPlayer(
    player: Player,
    material: Material,
    customName: String,
    damageModifier: Double,
    key: String = "GLauncher",
    value: String = "MLauncher"
): ItemStack {
    val item = createItemWithCustomNBT(material, customName, damageModifier, key, value)
    player.inventory.addItem(item)
    return item
}

fun createItemWithCustomNBT(
    material: Material,
    customName: String,
    damageModifier: Double,
    key: String = "Glauncher",
    value: String = "MLauncher"
): ItemStack {
    val item = ItemStack(material)
    val nbtI = NBTItem(item)

    nbtI.setString(key, value) // В отдельную функцию
    nbtI.applyNBT(item) // В отдельную функцию

    val meta = item.itemMeta ?: return item
    meta.setDisplayName(customName)

    val damageModifierKey = NamespacedKey.minecraft("damage-modifier")
    val damageModifierUUID = UUID.nameUUIDFromBytes("${damageModifierKey.namespace}:${damageModifierKey.key}".toByteArray())
    val modifier = AttributeModifier(
        damageModifierUUID,
        "Damage Modifier",
        damageModifier,
        AttributeModifier.Operation.ADD_NUMBER,
        EquipmentSlot.HAND
    )
    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier)

    item.itemMeta = meta
    return item
}