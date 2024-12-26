package io.github.zzzyyylllty.zaleplon.data


enum class Quest(

    /*
    * 静态任务
    *
    * QuestId 是任务的内部注册名。
    * QuestDifficulty 内置的任务组件，任务的难度。
    * QuestCategory 内置的任务组件，任务的难度。
    * QuestMetas 任务的元数据，用于自定义任务信息与其他的功能。
    *
    *
    * */
    var questId: String,
    var questDifficulty: String,
    var questCategory: ArrayList<String>?,
    var questMetas: LinkedHashMap<String, Any>,
    var questTasks: LinkedHashMap<String, Task>,
    var addon: Addon,
)