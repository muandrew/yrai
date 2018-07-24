package com.muandrew.yrai.agent

import com.muandrew.yrai.goal.Goal
import com.muandrew.yrai.goal.NoOpGoal

abstract class Agent {

    @Suppress("LeakingThis")
    var goal: Goal<*> = NoOpGoal(this)
        set(value) {
            field.terminate()
            field = value
        }

    fun onFrame() {
        goal.process()
    }
}
