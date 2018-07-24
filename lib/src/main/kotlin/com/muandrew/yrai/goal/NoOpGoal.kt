package com.muandrew.yrai.goal

import com.muandrew.yrai.agent.Agent

class NoOpGoal(agent: Agent) : Goal<Agent>(agent) {

    override fun onActivate() {}

    override fun onProcess() {}

    override fun onTerminate() {}
}
