package com.muandrew.yari

import org.openbw.bwapi4j.BW
import java.awt.image.ColorModel

fun main(args: Array<String>) {
    ColorModel.getRGBdefault()
    val aiModule = AIModule()
    val bw = BW(aiModule, BW.BridgeType.OPENBW, false)
    aiModule.init(bw)
    bw.startGame()
}