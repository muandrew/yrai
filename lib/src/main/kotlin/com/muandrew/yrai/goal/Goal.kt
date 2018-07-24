package com.muandrew.yrai.goal

import com.muandrew.yrai.agent.Agent

abstract class Goal<A : Agent>(val agent: A) {

    var parent: Goal<A>? = null

    var status: Status = Status.INACTIVE

    fun process(): Status {
        activateIfInactive()
        onProcess()
        return status
    }

    fun terminate() {
        onTerminate()
    }

    /**
     * Act on the Agent here.
     *
     * You can also change the state of the goal here.
     */
    abstract fun onProcess()

    /**
     * Override to perform actions when just activated.
     */
    open fun onActivate() {}

    /**
     * Override and perform any actions that needs to be done when the Goal terminates.
     */
    open fun onTerminate() {}

    open fun handleMessage(@Suppress("UNUSED_PARAMETER") telegram: Telegram): Boolean {
        return false
    }

    private fun activateIfInactive() {
        if (status != Status.ACTIVE) {
            status = Status.ACTIVE
            activate()
        }
    }

    private fun activate() {
        onActivate()
    }

    enum class Status {
        ACTIVE,
        INACTIVE,
        COMPLETE,
        FAILED
    }
}
