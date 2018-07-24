package com.muandrew.yari

import com.muandrew.yari.common.agent.AgentFactory
import com.muandrew.yari.common.debug.UnitHighlighter
import com.muandrew.yrai.agent.Agent
import org.openbw.bwapi4j.BW
import org.openbw.bwapi4j.BWEventListener
import org.openbw.bwapi4j.Player
import org.openbw.bwapi4j.Position
import org.openbw.bwapi4j.unit.Unit

class AIModule : BWEventListener {

    private lateinit var bw: BW
    private lateinit var self: Player
    private var listeners: MutableList<Listener> = mutableListOf()
    private var unitAgents: MutableMap<Int, Agent> = mutableMapOf()
//    private val abstractAgents = listOf()

    fun init(bw: BW) {
        this.bw = bw
    }

    override fun onStart() {
        // v(s) =  r(s) + gamma * argmax a ( sum ( t(s,a,s') + v(s')) )
        for (p in bw.allPlayers) {
            println("id: ${p.id} name: ${p.name}")
            if ("bwapi" == p.name) {
                self = p
            }
        }
        listeners.add(UnitHighlighter(bw, self))
    }

    override fun onEnd(isWinner: Boolean) {

    }

    override fun onFrame() {
        for (listener in listeners) {
            listener.onFrame()
        }
        val units = bw.getUnits(self)
        for (unit in units) {
            val agent = getAgent(unit)
            agent.onFrame()
        }
    }

    private fun getAgent(unit: Unit): Agent {
        return unitAgents[unit.id] ?: {
            val agent = AgentFactory.createAgent(bw, unit)
            unitAgents.put(unit.id, agent)
            agent
        }()
    }

    override fun onSendText(text: String) {

    }

    override fun onReceiveText(player: Player, text: String) {

    }

    override fun onPlayerLeft(player: Player) {

    }

    override fun onNukeDetect(target: Position) {

    }

    override fun onUnitDiscover(unit: Unit) {

    }

    override fun onUnitEvade(unit: Unit) {

    }

    override fun onUnitShow(unit: Unit) {

    }

    override fun onUnitHide(unit: Unit) {

    }

    override fun onUnitCreate(unit: Unit) {

    }

    override fun onUnitDestroy(unit: Unit) {

    }

    override fun onUnitMorph(unit: Unit) {

    }

    override fun onUnitRenegade(unit: Unit) {

    }

    override fun onSaveGame(gameName: String) {

    }

    override fun onUnitComplete(unit: Unit) {

    }

    interface Listener {
        fun onFrame()
    }
}
