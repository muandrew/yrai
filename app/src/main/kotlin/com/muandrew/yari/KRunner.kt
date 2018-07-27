package com.muandrew.yari

import org.openbw.bwapi4j.BW

fun main(args: Array<String>) {
    val aiModule = AIModule()
    val bw = BW(aiModule, BW.BridgeType.OPENBW, false)
    aiModule.initialize(bw)
    bw.startGame()
}