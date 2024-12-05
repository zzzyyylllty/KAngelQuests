package io.github.zzzyyylllty.kangelquests.data

data class Objective(
    /*
    * 静态目标
    *
    * Objective       任务类型，形如 Objective.BLOCK_BREAK。
    * Meta            目标元数据，形如 Key:"BLOCK" Value:"COAL_ORE" 。
    *
    * */
    val objective: ObjectiveType,
    val meta: LinkedHashMap<String, Any>,
    val requirement: String?,
    val run: String?,
)
