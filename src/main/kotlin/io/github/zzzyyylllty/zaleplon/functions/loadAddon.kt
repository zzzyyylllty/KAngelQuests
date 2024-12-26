package io.github.zzzyyylllty.zaleplon.functions

import io.github.zzzyyylllty.zaleplon.data.*
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration

fun loadAddon(config: YamlConfiguration, quest: Quest, task: Task?): Addon {

    val gui: AddonGui? = null
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
            Material.valueOf()
            val nameStandard : String ?,
        val nameProgressing: String?,
        val nameTracking: String?,
        val nameCompleted: String?,
        val loreeStandard: ArrayList<String?>,
        val loreProgressing: ArrayList<String?>,
        val loreTracking: ArrayList<String?>,
        val loreCompleted: ArrayList<String?>,
        val show: Boolean = false,
        )
    }
    return Addon(gui, dependency, agent)
}
