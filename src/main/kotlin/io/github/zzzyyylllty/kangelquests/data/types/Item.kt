package io.github.zzzyyylllty.kangelquests.data.types

import io.github.zzzyyylllty.kangelquests.KAngelQuests.registeredObjectives
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import org.bukkit.event.inventory.FurnaceExtractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

object Item {

    @SubscribeEvent
    fun onItemFurnace(e: FurnaceExtractEvent) {
        if (registeredObjectives.contains(e))
            onItemFurnace1(e)
    }

    private fun onItemFurnace1(e: FurnaceExtractEvent) {
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            val block = e.block ?: return@submitAsync
            metaList["BLOCK:MATERIAL"] = e.block.type
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["ITEMSTACK:MATERIAL"] = e.itemType
            metaList["EXP:NUMBER"] = e.expToDrop
            completeTasks(p, ObjectiveType.ITEM_FURNACE, e.itemAmount, metaList)

        }
    }
}
