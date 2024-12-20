package io.github.zzzyyylllty.kangelquests.tasks.debugger

import io.github.zzzyyylllty.kangelquests.KAngelQuests
import io.github.zzzyyylllty.kangelquests.data.Quest
import io.github.zzzyyylllty.kangelquests.data.QuestStat
import io.github.zzzyyylllty.kangelquests.data.Task
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender

fun maniuallyCompleteTask(action: String, q: Quest, task: Task, slient: Boolean): Boolean {

}

fun maniuallyCompleteTask(action: String, q: String, p: ProxyCommandSender, slient: Boolean): Any {

    if (p !is Player) return "fail,Reason is Sender is not a Player"
    if (KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks == null) return "fail,Reason is Player's quest Task is Null"
    when (action) {
        "complete" -> {
            KAngelQuests.questsMap[p]?.quests?.get(q)?.questStat = QuestStat.COMPLETED
            for (questTask in KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks!!) {
                KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks?.get(questTask.key)?.progress =
                    questTask.value.goal
                if (!slient) {/* TODO 触发Agent */
                }
                return true
            }
        }

        "fail" -> {
            KAngelQuests.questsMap[p]?.quests?.get(q)?.questStat = QuestStat.COMPLETED
            for (questTask in KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks!!) {
                KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks?.get(questTask.key)?.progress = 0
                if (!slient) {/* TODO 触发Agent */
                }
                return true
            }
        }

        "accept" -> {
            KAngelQuests.questsMap[p]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            if (!slient) {/* TODO 触发Agent */
            }
            return true
        }

        "restart" -> {
            KAngelQuests.questsMap[p]?.quests?.get(q)?.questStat = QuestStat.ACTIVE
            for (questTask in KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks!!) {
                KAngelQuests.questsMap[p]?.quests?.get(q)?.questTasks?.get(questTask.key)?.progress = 0
                if (!slient) {/* TODO 触发Agent */
                }
                return true
            }
        }


    }

    // return@actionNow maniuallyCompleteTask("complete"...)
}
