package io.github.zzzyyylllty.kangelquests.data.types

import io.github.zzzyyylllty.kangelquests.KAngelQuests.registeredObjectives
import io.github.zzzyyylllty.kangelquests.KAngelQuests.runningObjectives
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.tasks.completeTasks
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerFishEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync
import taboolib.platform.util.killer

object Player {



    @SubscribeEvent
    fun onPlayerFish(e: PlayerFishEvent) {
        if (registeredObjectives.contains(e))
            onPlayerFish1(e)
    }

    private fun onPlayerFish1(e: PlayerFishEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["ENTITY:ENTITYTYPE"] = e.caught?.type
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            metaList["HAND:EQUIPMENTSLOT"] = e.hand?.name
            completeTasks(p, ObjectiveType.PLAYER_FISH, 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerChat(e: PlayerChatEvent) {
        if (registeredObjectives.contains(e))
            onPlayerChat1(e)
    }

    private fun onPlayerChat1(e: PlayerChatEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["MESSAGE:STRING"] = e.message
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, ObjectiveType.PLAYER_CHAT, 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerSyncChat(e: PlayerChatEvent) {
        if (registeredObjectives.contains(e))
            onPlayerChat1(e)
    }

    private fun onPlayerSyncChat1(e: PlayerChatEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["MESSAGE:STRING"] = e.message
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, ObjectiveType.PLAYER_SYNCCHAT, 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerChangeWorld(e: PlayerChangedWorldEvent) {
        if (registeredObjectives.contains(e))
            onPlayerChangeWorld1(e)
    }

    private fun onPlayerChangeWorld1(e: PlayerChangedWorldEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.player
            metaList["FROM:STRING"] = e.from.name
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, ObjectiveType.PLAYER_CHANGEWORLD, 1, metaList)
        }
    }

    @SubscribeEvent
    fun onPlayerKillMob(e: EntityDeathEvent) {
        if (e.killer is Player && registeredObjectives.contains(e))
            onPlayerKillMob1(e)
    }

    private fun onPlayerKillMob1(e: EntityDeathEvent) {
        if (!runningObjectives.contains(e)) submitAsync {
            runningObjectives.add(e)
            val metaList = LinkedHashMap<String, Any?>()
            val p = e.killer as Player
            metaList["ENTITYTYPE"] = e.entityType
            metaList["PX:NUMBER"] = p.location.x
            metaList["PY:NUMBER"] = p.location.y
            metaList["PZ:NUMBER"] = p.location.z
            completeTasks(p, ObjectiveType.PLAYER_SYNCCHAT, 1, metaList)
        }
    }


}
