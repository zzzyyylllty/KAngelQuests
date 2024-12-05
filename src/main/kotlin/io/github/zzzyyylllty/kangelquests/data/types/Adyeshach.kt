package io.github.zzzyyylllty.kangelquests.data.types

import ink.ptms.adyeshach.core.event.AdyeshachEntityInteractEvent
import io.github.zzzyyylllty.kangelquests.KAngelQuests
import io.github.zzzyyylllty.kangelquests.KAngelQuests.runningObjectives
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Adyeshach")
object AdyeshachQuests {

    private var metaList = LinkedHashMap<String, Any?>()

    @SubscribeEvent
    fun onAdyeshachNPCInteract(e: AdyeshachEntityInteractEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onAdyeshachNPCInteract1(e)
    }

    fun onAdyeshachNPCInteract1(e: AdyeshachEntityInteractEvent) {
        if (!KAngelQuests.runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val p = e.player
            metaList["ID:STRING"] = e.entity.id
            metaList["VECTOR:VECTOR"] = e.vector
            metaList["ITEMSTACK:ITEM"] =
                if (e.isMainHand) e.player.inventory.itemInMainHand else e.player.inventory.itemInOffHand
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, ObjectiveType.ADYESHACH_INTERACT, 1, metaList)
        }
    }
}
