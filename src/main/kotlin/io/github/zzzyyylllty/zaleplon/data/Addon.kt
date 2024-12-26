package io.github.zzzyyylllty.zaleplon.data

import org.bukkit.inventory.ItemStack

data class Addon(
    val gui: AddonGui?,
    val dependency: ArrayList<AddonDependencySingle>?,
    val agent: Agent?,
)

data class AddonGui(
    val material: ItemStack?,
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

data class Agent(
    val onComplete: String?,
    val onFail: String?,
    val onAccept: String?,
    val onRestart: String?,
    val onProgress: String?, // 只适用于task
    val onProgressFail: String?, // 只适用于task
)

