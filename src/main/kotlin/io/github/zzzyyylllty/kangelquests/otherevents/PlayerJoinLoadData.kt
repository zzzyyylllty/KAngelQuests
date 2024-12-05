package io.github.zzzyyylllty.kangelquests.otherevents

import org.bukkit.event.player.PlayerJoinEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submitAsync

@SubscribeEvent
fun onBlockJoinLoadData(e: PlayerJoinEvent) {
    submitAsync {}
}