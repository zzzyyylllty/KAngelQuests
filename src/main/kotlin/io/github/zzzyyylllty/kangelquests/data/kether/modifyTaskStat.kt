package io.github.zzzyyylllty.kangelquests.data.kether

import io.github.zzzyyylllty.kangelquests.KAngelQuests.questsMap
import io.github.zzzyyylllty.kangelquests.data.QuestStat
import org.bukkit.entity.Player
import taboolib.common.util.isPlayer
import taboolib.module.kether.*


@KetherParser(["kquest quest"], shared = true)
fun parser1() = scriptParser {
    it.switch {
        case("active", "unlock", "accept") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer()) questsMap[sender as Player]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            }
        }
        case("lock") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer()) questsMap[sender as Player]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            }
        }
        case("complete") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer() && questsMap[sender as Player]?.quests?.get(q)?.questTasks != null) {
                    questsMap[sender]?.quests?.get(q)?.questStat = QuestStat.COMPLETED
                    for (questTask in questsMap[sender]?.quests?.get(q)?.questTasks!!) {
                        questsMap[sender]?.quests?.get(q)?.questTasks?.get(questTask.key)?.progress =
                            questTask.value.goal
                        // TODO 触发事件
                    }
                }
            }
        }
        case("slientcomplete") {
            val q = it.nextToken()
            actionNow {
                val sender = script().sender
                if (sender.isPlayer() && questsMap[sender as Player]?.quests?.get(q)?.questTasks != null) {
                    questsMap[sender]?.quests?.get(q)?.questStat = QuestStat.COMPLETED
                    for (questTask in questsMap[sender]?.quests?.get(q)?.questTasks!!) {
                        questsMap[sender]?.quests?.get(q)?.questTasks?.get(questTask.key)?.progress =
                            questTask.value.goal
                    }
                }
            }
        }
    }
}