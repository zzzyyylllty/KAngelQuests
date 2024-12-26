package io.github.zzzyyylllty.zaleplon.data.types

import io.github.zzzyyylllty.zaleplon.Zaleplon
import io.github.zzzyyylllty.zaleplon.functions.completeTasks
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Citizens")
object CitizensQuests {

    @SubscribeEvent                                                                       // =EventHandler+RegisterEvents
    fun onCitizensNPCInteract(e: C) {
        if (Zaleplon.registeredObjectives.contains("Citizens_INTERACT")) // If registered objects contains it
            onCitizensNPCInteract1(e)                                                    // Do
    }

    private fun onCitizensNPCInteract1(e: CitizensEntityInteractEvent) {

        // Send BukkitRunnable
        submitAsync {
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["ID:STRING"] = e.entity.id
            metaList["VECTOR:VECTOR"] = e.vector
            metaList["ITEMSTACK:MATERIAL"] =
                if (e.isMainHand) e.player.inventory.itemInMainHand else e.player.inventory.itemInOffHand
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, "Citizens_INTERACT", 1, metaList)
        }
    }
}
