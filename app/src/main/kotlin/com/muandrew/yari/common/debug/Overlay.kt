package com.muandrew.yari.common.debug;

import com.muandrew.yari.AIModule
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.Player
import org.openbw.bwapi4j.type.Color

class UnitHighlighter(val bw: BW, val self: Player): AIModule.Listener {

    override fun onFrame() {
        val units = bw.getUnits(self)
        val md = bw.mapDrawer
        for (unit in units) {
            md.drawCircleMap(unit.position, 10, Color.CYAN)
        }
    }
}