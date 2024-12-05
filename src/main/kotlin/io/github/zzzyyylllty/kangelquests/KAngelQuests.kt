package io.github.zzzyyylllty.kangelquests

import io.github.zzzyyylllty.kangelquests.data.PlayerQuestData
import org.bukkit.entity.Player
import org.bukkit.event.Event
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object KAngelQuests : Plugin() {

    lateinit var plugin: KAngelQuests
    lateinit var questsMap: LinkedHashMap<Player, PlayerQuestData>
    lateinit var registeredObjectives: ArrayList<Event>
    lateinit var runningObjectives: ArrayList<Event>

    @Config("config.yml")
    lateinit var config: ConfigFile

    override fun onEnable() {
        info("Launching")
    }

    override fun onDisable() {
        info("Successfully running ExamplePlugin!")
    }

}