package io.github.zzzyyylllty.kangelquests.data.kether

import taboolib.module.kether.KetherParser
import taboolib.module.kether.actionNow
import taboolib.module.kether.scriptParser
import taboolib.module.kether.switch


@KetherParser(["ktest"], shared = true)
fun parser1() = scriptParser {
    it.switch {
        case("accept") {
            val quest = it.nextToken()
            actionNow {


            }
        }
    }
}