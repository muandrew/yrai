package com.muandrew.yari.common.debug;

import com.muandrew.yari.AIModule
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.Player
import org.openbw.bwapi4j.type.Color

class UnitHighlighter(val bw: BW, val self: Player) : AIModule.Listener {

    override fun onFrame() {
        val units = bw.getUnits(self)
        val md = bw.mapDrawer
        for (unit in units) {
            md.drawCircleMap(unit.position, 10, Color.CYAN)
        }
    }
}

class BWEMHightlighter(val bw: BW, val map: bwem.map.Map) : AIModule.Listener {

    override fun onFrame() {
        val md = bw.mapDrawer
        for (chokePoint in map.chokePoints) {
            val itr = chokePoint.geometry.iterator()
            var prevPoint = itr.next()
            itr.forEachRemaining {
                md.drawLineMap(prevPoint.toPosition(), it.toPosition(), Color.CYAN)
                prevPoint = it
            }
        }
    }
}