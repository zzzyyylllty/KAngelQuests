package io.github.zzzyyylllty.kangelquests.data

import org.bukkit.Material

data class Addon(
    val gui: AddonGui,
    val dependency: ArrayList<AddonDependencySingle>,
)

data class AddonGui(
    val material: Material?,
    val nameStandard: String?,
    val nameProgressing: String?,
    val nameTracking: String?,
    val nameCompleted: String?,
    val loreeStandard: ArrayList<String?>,
    val loreProgressing: ArrayList<String?>,
    val loreTracking: ArrayList<String?>,
    val loreCompleted: ArrayList<String?>,
    val show: Boolean = false,
)

data class AddonDependencySingle(
    val conditions: ArrayList<AddonDependencySingleCondition>,
    val quests: LinkedHashMap<String, Int>,
)

data class AddonDependencySingleCondition(
    val dependencyOperator: String?,
    val value: Int?,
)

