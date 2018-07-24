package com.muandrew.yari.common.agent

import com.muandrew.yrai.agent.Agent
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.unit.Unit

open class UnitAgent<U : Unit>(val bw: BW, val id: Int) : Agent() {

    @Suppress("UNCHECKED_CAST")
    val unit: U
        get() {
            return bw.getUnit(id) as U
        }
}
