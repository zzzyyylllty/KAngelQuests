package io.github.zzzyyylllty.kangelquests.data.types


import io.github.zzzyyylllty.kangelquests.KAngelQuests
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import io.lumine.mythic.bukkit.events.MythicMobInteractEvent
import org.bukkit.entity.Player
import taboolib.common.env.RuntimeDependency
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@RuntimeDependency("Mythicmobs")
object MythicMobs {

    private var metaList = LinkedHashMap<String, Any?>()
    @SubscribeEvent
    fun onMythicMobsMobKill(e: MythicMobDeathEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onMythicMobsMobKill1(e)
    }

    @SubscribeEvent
    fun onMythicMobsMobKill(e: MythicMobInteractEvent) {
        if (KAngelQuests.registeredObjectives.contains(e))
            onMythicMobsMobInteract1(e)
    }

    private fun onMythicMobsMobKill1(e: MythicMobDeathEvent) {
        if (!KAngelQuests.runningObjectives.contains(e)) submitAsync {
            KAngelQuests.runningObjectives.add(e)
            if (e.killer is Player) {
                val p = e.killer as Player
                metaList["NAME:STRING"] = e.mobType.internalName
                metaList["MOBLEVEL:NUMBER"] = e.mobLevel
                metaList["X:NUMBER"] = e.entity.location.x
                metaList["Y:NUMBER"] = e.entity.location.y
                metaList["Z:NUMBER"] = e.entity.location.z
                completeTasks(p, ObjectiveType.MYTHICMOBS_KILL, 1, metaList)
            }
        }
    }

    private fun onMythicMobsMobInteract1(e: MythicMobInteractEvent) {
        if (!KAngelQuests.runningObjectives.contains(e)) submitAsync {
            KAngelQuests.runningObjectives.add(e)
            val p = e.player
            metaList["NAME:STRING"] = e.activeMobType.internalName
            metaList["MOBLEVEL:NUMBER"] = e
            metaList["X:NUMBER"] = e.activeMob.location.x
            metaList["Y:NUMBER"] = e.activeMob.location.y
            metaList["Z:NUMBER"] = e.activeMob.location.z
            completeTasks(p, ObjectiveType.MYTHICMOBS_INTERACT, 1, metaList)
        }
    }
}
