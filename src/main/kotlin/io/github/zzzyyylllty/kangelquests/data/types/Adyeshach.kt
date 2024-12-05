package io.github.zzzyyylllty.kangelquests.data.types

import ink.ptms.adyeshach.core.event.AdyeshachEntityInteractEvent
import io.github.zzzyyylllty.kangelquests.KAngelQuests
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Adyeshach")
object AdyeshachQuests {

    @SubscribeEvent
    fun onAdyeshachNPCInteract(e: AdyeshachEntityInteractEvent) {                         // 关注事件
        if (KAngelQuests.registeredObjectives.contains(ObjectiveType.ADYESHACH_INTERACT)) // 如果任务中存在此事件
            onAdyeshachNPCInteract1(e)                                                    // 执行判断
    }

    private fun onAdyeshachNPCInteract1(e: AdyeshachEntityInteractEvent) {

        // 发送任务
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
            completeTasks(p, ObjectiveType.ADYESHACH_INTERACT, 1, metaList)
        }
    }
}
