package io.github.zzzyyylllty.zaleplon

import io.github.zzzyyylllty.zaleplon.data.ObjectiveType
import io.github.zzzyyylllty.zaleplon.data.PlayerQuestData
import io.github.zzzyyylllty.zaleplon.data.Quest
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile
import taboolib.platform.util.asLangText

object Zaleplon : Plugin() {

    lateinit var plugin: Zaleplon
    lateinit var questsMap: LinkedHashMap<Player, PlayerQuestData>
    lateinit var registeredObjectives: ArrayList<ObjectiveType>
    lateinit var runningObjectives: ArrayList<ObjectiveType>
    lateinit var loadedQuests: LinkedHashMap<String, Quest>
    var console = Bukkit.getConsoleSender()

    @Config("config.yml")
    lateinit var config: ConfigFile

    override fun onEnable() {
        info(console.asLangText("LOG_START"))
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

}