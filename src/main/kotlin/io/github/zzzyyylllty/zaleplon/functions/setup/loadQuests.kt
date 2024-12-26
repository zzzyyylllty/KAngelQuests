package io.github.zzzyyylllty.zaleplon.functions.setup

import io.github.zzzyyylllty.zaleplon.Zaleplon.console
import io.github.zzzyyylllty.zaleplon.Zaleplon.loadedQuests
import io.github.zzzyyylllty.zaleplon.data.*
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import taboolib.common.util.asList
import taboolib.module.lang.asLangText
import java.io.File

fun loadQuests() {
    info(console.asLangText("LOG_READ_QUEST_START"))

    val files = File(getDataFolder(), "quests").listFiles() ?: run {
        info(console.asLangText("LOG_READ_QUEST_START"))
        return
    }

    if (!File(getDataFolder(), "quests").exists()) {
        releaseResourceFile("quests/example.yml")
    }

    for (file in files) {
        try {
            info("正在处理任务文件 ${file.getName()} ...")
            loadSingleQuestFile(file)
            info("正在处理任务文件 ${file.getName()} ... 完成.")
        } catch (exception: IllegalArgumentException) {
            info("处理任务文件 ${file.getName()} 时发生意外错误 \n Stacktrace: $exception")
        }
    }

    info("任务加载完成! LOOTLIST:${lootList} LOOTINSTANCE${lootInstanceList}")
}


fun loadSingleQuestFile(file: File) {

    val config = YamlConfiguration.loadConfiguration(file)
    info(config.getKeys(true))

    for (quest in config.getKeys(false)) {

        val keys = config.getKeys(true)

        val questDifficulty = config["quest.meta.difficulty"].toString()
        val questCategory = config["quest.meta.category"]?.asList()
        val questTasks = LinkedHashMap<String, Task>()
        val questMetas = LinkedHashMap<String, Any>()
        val loadedTasks = ArrayList<String>()

        for (nodes in keys) {
            if (nodes.split(".").size == 3 && nodes.split(".")[1] == "meta" && nodes.split(".")[0] == quest) {
                questMetas[nodes.split(".")[2]] = config[nodes.split(".")[2]] ?: "None"
            } else if (nodes.split(".")[1] == "tasks" && nodes.split(".")[0] == quest && loadedTasks.contains(
                    nodes.split(
                        "."
                    )[2]
                )
            ) {
                val taskid = nodes.split(".")[2]
                loadedTasks.add(taskid)
                var i = 1
                val objectives = ArrayList<String>()
                while (true) {
                    if (config["$quest.tasks.$taskid.display.objectives.$i"] == null) {
                        if (i == 1) error(console.asLangText("DEBUG_NO_OBJECTIVES", quest, taskid))
                        break
                    }

                    objectives[i] = Objective(
                        ObjectiveType.valueOf(config["$quest.tasks.$taskid.display.objectives.$i.objective"]),
                        config["$quest.tasks.$taskid.display.objectives.$i.meta"]
                    )
                    i++
                }

                questTasks[taskid] = Task(
                    config["$quest.tasks.$taskid.display.taskname"].toString(),
                    config["$quest.tasks.$taskid.display.tasklore"].toString(), // TODO TASKLORE: AUTO
                    config["$quest.description"].toString(),
                )
            }
        }

        val addon: Addon


        loadedQuests[quest] = Quest(
            config["$quest.name"].toString(),
            config["$quest.description"].toString(),

            )

    }
}
