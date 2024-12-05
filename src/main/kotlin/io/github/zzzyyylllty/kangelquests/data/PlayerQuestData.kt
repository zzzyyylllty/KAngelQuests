package io.github.zzzyyylllty.kangelquests.data

data class PlayerQuestData(
    var quests: LinkedHashMap<String, ActiveQuest>,
    var trackingQuest: Quest,
)
