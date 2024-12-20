package io.github.zzzyyylllty.kangelquests.data.kether

import io.github.zzzyyylllty.kangelquests.KAngelQuests.questsMap
import io.github.zzzyyylllty.kangelquests.data.QuestStat
import io.github.zzzyyylllty.tasks.debugger.maniuallyCompleteTask
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
        case("lock", "unaccept", "unactive", "inactive") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "lock",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender
                )
            }
        }
        case("complete", "done", "finish") {
            val q = it.nextToken()
            actionNow {
                if (script().sender !is Player) return@actionNow null else return@actionNow maniuallyCompleteTask(
                    "complete",
                    nextToken().split(".")[0],
                    nextToken().split(".")[1],
                    script().sender
                )
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