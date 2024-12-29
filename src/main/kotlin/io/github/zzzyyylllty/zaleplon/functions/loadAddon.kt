package io.github.zzzyyylllty.zaleplon.functions

import io.github.zzzyyylllty.zaleplon.data.*
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import taboolib.common.util.asList

fun loadAddon(config: YamlConfiguration, quest: Quest, task: Task?, taskid: String?): Addon {

    var gui: AddonGui? = null
    val dependency: ArrayList<AddonDependencySingle>? = null
    val agent: Agent? = null

    val questname = quest.questDisplayName ?: "<null>"
    val questdesc = quest.questDisplayLore ?: "<null>"

    fun resolvePlaceholdersString(string: String): String {
        string.replace("{questname}", questname)
        string.replace("{name}", questname)
        PlaceholderAPI.setPlaceholders(null, string)
        return string
    }

    fun resolvePlaceholdersList(list: ArrayList<String>): ArrayList<String> {
        val returns = ArrayList<String>()
        for (s in list) {
            returns.add(solvePapi(resolvePlaceholdersString(s), null))
        }
        return returns
    }


    if (task == null) {
        gui = AddonGui(
            ItemStack(Material.valueOf(config["$quest.addon.gui.material"].toString())).,
            config["$quest.addon.gui.display-name.standard"].toString(),
            config["$quest.addon.gui.display-name.tracking"].toString(),
            config["$quest.addon.gui.display-name.completed"].toString(),
            config["$quest.addon.gui.display-lore.standard"]?.asList(),
            config["$quest.addon.gui.display-name.tracking"]?.asList(),
            config["$quest.addon.gui.display-name.completed"]?.asList(),
            config["$quest.addon.gui.show.before"].toString().toBoolean(),
            config["$quest.addon.gui.show.progressing"].toString().toBoolean(),
            config["$quest.addon.gui.show.completed"].toString().toBoolean(),
        )
        val i = 1
        while (config["$quest.addon.dependency"] == null) {
            dependency?.add(
                AddonDependencySingle(
                    config["$quest.addon.dependency.$i.conditions"]?.asList(),
                    config["$quest.addon.dependency.$i.quests"]?.asList(),
                )
            )
        }
    }


    return Addon(gui, dependency, agent)
}

