package com.muandrew.yari.terran.agent

import com.muandrew.yari.common.agent.UnitAgent
import com.muandrew.yrai.goal.Goal
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.unit.MineralPatch
import org.openbw.bwapi4j.unit.SCV

class SCVAgent(bw: BW, id: Int) : UnitAgent<SCV>(bw, id) {

    init {
        goal = GatherGoal(this)
    }

    class GatherGoal(agent: SCVAgent) : Goal<SCVAgent>(agent) {
        override fun onActivate() {}

        override fun onProcess() {
            val unit = agent.unit
            if (unit.isIdle) {
                var closestDistance = Integer.MAX_VALUE
                var closestPatch: MineralPatch? = null
                for (mineralPatch in agent.bw.mineralPatches) {
                    val distance = unit.getDistance(mineralPatch)
                    if (distance < closestDistance) {
                        closestDistance = distance
                        closestPatch = mineralPatch
                    }
                }
                if (closestPatch != null) {
                    unit.gather(closestPatch)
                }
            }
        }

        override fun onTerminate() {}
    }
}