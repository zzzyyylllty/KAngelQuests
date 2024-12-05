package io.github.zzzyyylllty.kangelquests.data.types

import io.github.zzzyyylllty.kangelquests.KAngelQuests
import io.github.zzzyyylllty.kangelquests.KAngelQuests.runningObjectives
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockIgniteEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerHarvestBlockEvent
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

object BlockObjectives {

    private var metaList = LinkedHashMap<String, Any?>()

    @SubscribeEvent
    fun onBlockHarvest(e: PlayerHarvestBlockEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onBlockHarvest1(e)
    }

    @SubscribeEvent
    fun onBlockBreak(e: BlockBreakEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onBlockBreak(e)
    }

    @SubscribeEvent
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onBlockPlace1(e)
    }

    @SubscribeEvent
    fun onBlockIgnite(e: BlockIgniteEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onBlockIgnite1(e)
    }

    @SubscribeEvent
    fun onBlockInteract(e: PlayerInteractEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onBlockInteract1(e)
    }

    private fun onBlockHarvest1(e: PlayerHarvestBlockEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val p = e.player
            metaList["BLOCK:BLOCK"] = e.harvestedBlock
            metaList["X:NUMBER"] = e.harvestedBlock.location.x
            metaList["Y:NUMBER"] = e.harvestedBlock.location.y
            metaList["Z:NUMBER"] = e.harvestedBlock.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand.name
            completeTasks(p, ObjectiveType.BLOCK_HARVEST, 1, metaList)
        }
    }

    private fun onBlockBreak1(e: BlockBreakEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val p = e.player
            metaList["BLOCK:BLOCK"] = e.block
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["EXP:INT"] = e.expToDrop
            completeTasks(p, ObjectiveType.BLOCK_BREAK, 1, metaList)
        }
    }

    private fun onBlockPlace1(e: BlockPlaceEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val p = e.player
            metaList["BLOCK:BLOCK"] = e.block
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand.name
            metaList["ITEMSTACK:ITEM"] = e.itemInHand
            completeTasks(p, ObjectiveType.BLOCK_PLACE, 1, metaList)
        }
    }

    private fun onBlockIgnite1(e: BlockIgniteEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val p = e.player
            metaList["BLOCK:BLOCK"] = e.block
            metaList["X:NUMBER"] = e.block.location.x
            metaList["Y:NUMBER"] = e.block.location.y
            metaList["Z:NUMBER"] = e.block.location.z
            metaList["PX:NUMBER"] = p?.location?.x
            metaList["PY:NUMBER"] = p?.location?.y
            metaList["PZ:NUMBER"] = p?.location?.z
            metaList["IGNITECAUSE:IGNITECAUSE"] = e.cause.name
            metaList["BLOCK:IGNITINGBLOCK"] = e.ignitingBlock
            completeTasks(p, ObjectiveType.BLOCK_IGNITE, 1, metaList)
        }
    }

    private fun onBlockInteract1(e: PlayerInteractEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            if (e.clickedBlock != null) {
                val p = e.player
                val block = e.clickedBlock ?: return@submitAsync
                metaList["BLOCK:BLOCK"] = block
                metaList["X:NUMBER"] = block.location.x
                metaList["Y:NUMBER"] = block.location.y
                metaList["Z:NUMBER"] = block.location.z
                metaList["PX:NUMBER"] = p.location.x
                metaList["PY:NUMBER"] = p.location.y
                metaList["PZ:NUMBER"] = p.location.z
                metaList["HAND:EQUIPMENTSLOT"] = e.hand?.name
                metaList["ITEMSTACK:ITEM"] = e.item
                metaList["ACTION:ACTION"] = e.action.name
                completeTasks(p, ObjectiveType.BLOCK_INTERACT, 1, metaList)
            }
        }
    }

}