package io.github.zzzyyylllty.tasks.debugger

import io.github.zzzyyylllty.kangelquests.KAngelQuests.questsMap
import io.github.zzzyyylllty.kangelquests.data.QuestStat
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender

fun maniuallyCompleteTask(action: String, q: String, t: String?, p: ProxyCommandSender?, slient: Boolean): Any {

    if (p !is Player) return "fail,Reason is Sender is not a Player"
    val quest = questsMap[p]?.quests?.get(q) ?: return "fail,Player Quest is null"
    when (action) {
        "complete" -> {
            if (t == null) {
                // 完成任务
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = questTask.value.goal
                }
                quest.questStat = QuestStat.COMPLETED

                // Agent
                if (!slient) quest.addon.agent.onComplete

            } else {
                quest.questTasks[t]?.progress = quest.questTasks[t]?.goal ?: 1
                if (!slient) quest.questTasks[t]?.addon?.agent?.onComplete

                return true
            }
        }

        "fail" -> {
            if (t == null) {
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = 0
                }
                quest.questStat = QuestStat.ACTIVE
                if (!slient) quest.addon.agent.onFail
            } else {

                quest.questTasks[t]?.progress =
                    quest.questTasks[t]?.goal ?: 0
                if (!slient) quest.questTasks[t]?.addon?.agent?.onFail
            }
            return true
        }

        "accept" -> {
            quest.questStat = QuestStat.ACTIVE
            if (!slient) quest.addon.agent.onAccept
            return true
        }

        "restart" -> {
            if (t == null) {
                for (questTask in quest.questTasks) {
                    quest.questTasks[questTask.key]?.progress = 0
                }
                quest.questStat = QuestStat.ACTIVE
                if (!slient) quest.addon.agent.onRestart
            } else {

                quest.questTasks.get(t)?.progress =
                    quest.questTasks.get(t)?.goal ?: 0
                if (!slient) quest.questTasks[t]?.addon?.agent?.onRestart
            }
            return true
        }

        // return@actionNow maniuallyCompleteTask("complete"...)


    }
    return "fail,Unexpected error"
}
