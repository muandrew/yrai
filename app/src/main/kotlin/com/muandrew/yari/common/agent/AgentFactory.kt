package com.muandrew.yari.common.agent

import com.muandrew.yari.terran.agent.SCVAgent
import com.muandrew.yrai.agent.Agent
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.type.UnitType
import org.openbw.bwapi4j.unit.Unit

object AgentFactory {

    fun createAgent(bw: BW, unit: Unit): Agent {
        return when (unit.initialType) {
            UnitType.Terran_SCV -> SCVAgent(bw, unit.id)
            else -> NoOpAgent()
        }
    }
}