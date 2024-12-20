package io.github.zzzyyylllty.kangelquests.data.commands

import io.github.zzzyyylllty.kangelquests.tasks.returnPlayerOnlyTargetSelector
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.warning
import taboolib.platform.util.sendLang

@CommandHeader("kangelquests", ["kangelquest", "kq"], permission = "kangelquests.command")
object MainCommand {

    // 子节点
    @CommandBody
    val operationQuest = subCommand {
        val modifyPlayerQuestStat = subCommand {

            // 参数 user
            dynamic("players") {
                dynamic("quest") {
                    dynamic("modify") {
                        execute<CommandSender> { sender, context, argument ->
                            // 获取参数的值
                            for (p in Bukkit.selectEntities(sender, context["players"])) {
                                if (p is Player) {
                                    val q = context["players"]
                                    if (context["modify"].startsWith("quest:"))

                                        if (context["modify"].startsWith("task:"))

                                } else {
                                    warning("目标选择器 ${context["players"]} 其中有部分结果不是玩家，已跳过。")
                                }
                            }
                            sender.sendLang("COMMAND_NO_PARAMS", "modify")
                        }
                    }
                    execute<CommandSender> { sender, context, argument ->
                        sender.sendLang(
                            "COMMAND_NO_PARAMS",
                            "modify"
                        )
                    }
                }
                execute<CommandSender> { sender, context, argument ->
                    sender.sendLang(
                        "COMMAND_NO_PARAMS",
                        "quest, modify"
                    )
                }
                suggestion<CommandSender>(true) { sender, context -> returnPlayerOnlyTargetSelector() }
            }
        }


    }
}