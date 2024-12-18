package io.github.zzzyyylllty.kangelquests.tasks

import io.github.zzzyyylllty.kangelquests.KAngelQuests.config
import io.github.zzzyyylllty.kangelquests.KAngelQuests.questsMap
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import io.github.zzzyyylllty.kangelquests.data.QuestStat
import org.bukkit.entity.Player
import taboolib.common.platform.function.submitAsync
import taboolib.common.platform.function.warning
import taboolib.platform.util.asLangText

fun completeTasks(p: Player?, objective: ObjectiveType, amount: Number, metaList: LinkedHashMap<String, Any?>) {

    if (p == null) return

    submitAsync {

        val data = questsMap[p]

        if (data == null) { // If Player data not found
            when (config["debug.no-player-data"]) {
                "SKIP" -> warning(p.asLangText("DEBUG_NO_PLAYER_DATA_WARN", p.name))
                "KICK" -> run {
                    warning(p.asLangText("DEBUG_NO_PLAYER_DATA_WARN", p.name))
                    p.kickPlayer(p.asLangText("DEBUG_NO_PLAYER_DATA_WARN_KICK", p.name))
                }
            }
            return@submitAsync
        }

        for (rawquest in data.quests) {

            val questId = rawquest.key
            val quest = rawquest.value

            for (rawtask in quest.questTasks) {

                val taskKey = rawtask.key
                val task = rawtask.value

                // If it already completes
                if (task.goal.toDouble() >= task.progress.toDouble()) break

                val dependency = task.task.addon.dependency
                for (depend in dependency) {
                    var completedDepends = 0
                    for (q in depend.quests) {
                        if (questsMap[p] != null && questsMap[p]?.quests?.keys?.contains(q.key) == true) {
                            if (q.value == 0) if (questsMap[p]?.quests?.get(q.key)?.questStat == QuestStat.COMPLETED) completedDepends++
                            else {
                                val progress = questsMap[p]?.quests?.get(q.key)?.questTasks?.get(q.key)?.progress ?: 0
                                val goal = questsMap[p]?.quests?.get(q.key)?.questTasks?.get(q.key)?.goal ?: 0
                                if (goal.toInt() <= progress.toInt()) completedDepends++
                            }
                        }
                    }
                }

                // TODO

                for (rawObjective in task.task.taskObjectives)
                    for (meta in rawObjective.meta) {

                        // META
                        if (metaList[meta.key] != meta.value) return@submitAsync

                        // Kether Requirement
                        if (rawObjective.requirement != null && runKether(
                                listOf(rawObjective.requirement),
                                p
                            ).get() as Boolean? == false
                        ) break

                        // Kether run
                        if (rawObjective.run != null) runKether(listOf(rawObjective.run), p).get()
                    }
            }
        }
    }
}
