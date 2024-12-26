package io.github.zzzyyylllty.zaleplon.functions.setup

import io.github.zzzyyylllty.zaleplon.Zaleplon
import io.github.zzzyyylllty.zaleplon.Zaleplon.loadedQuests
import io.github.zzzyyylllty.zaleplon.data.Addon
import io.github.zzzyyylllty.zaleplon.data.Quest
import io.github.zzzyyylllty.zaleplon.data.Task
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import taboolib.common.util.asList
import taboolib.platform.util.asLangText
import java.io.File

fun loadQuests() {
    info(Zaleplon.console.asLangText("LOG_READ_QUEST_START"))

    val files = File(getDataFolder(), "quests").listFiles() ?: run {
        info(Zaleplon.console.asLangText("LOG_READ_QUEST_START"))
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

        for (nodes in keys) {
            if (nodes.split(".").size == 3 && nodes.split(".")[1] == "meta" && nodes.split(".")[0] == quest) {
                questMetas[nodes.split(".")[2]] = config[nodes.split(".")[2]] ?: "None"
            } else if (nodes.split(".").size == 3 && nodes.split(".")[1] == "meta" && nodes.split(".")[0] == quest) {
                questMetas[nodes.split(".")[2]] = config[nodes.split(".")[2]] ?: "None"
            }
        }

        val addon: Addon


        loadedQuests[quest] = Quest(
            quest,

            )

    }
}
