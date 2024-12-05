package io.github.zzzyyylllty.kangelquests.tasks

import io.github.zzzyyylllty.kangelquests.KAngelQuests.config
import io.github.zzzyyylllty.kangelquests.KAngelQuests.questsMap
import io.github.zzzyyylllty.kangelquests.data.ObjectiveType
import org.bukkit.entity.Player
import taboolib.common.platform.function.submitAsync
import taboolib.common.platform.function.warning
import taboolib.platform.util.asLangText

fun completeTasks(p: Player?, objective: ObjectiveType, amount: Number, metaList: LinkedHashMap<String, Any?>) {

    if (p == null) return

    submitAsync {

        val data = questsMap[p]

        if (data == null) { // 如果未找到玩家数据
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

                if (task.goal.toDouble() >= task.progress.toDouble()) break
                // 如果子任务已完成，则结束当前任务判定

                // TODO 任务依赖

                // TODO

                for (rawObjective in task.task.taskObjectives)
                    for (meta in rawObjective.meta) {
                        if (metaList[meta.key] != meta.value) return@submitAsync
                        // META 要求

                        if (rawObjective.requirement != null && runKether(
                                listOf(rawObjective.requirement),
                                p
                            ).get() as Boolean
                        ) break
                        // Kether 代理判断


                    }
            }


        }
    }
}
