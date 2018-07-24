package com.muandrew.yrai.goal

import com.muandrew.yrai.agent.Agent
import java.util.*

abstract class StackGoal<A : Agent>(agent: A) : Goal<A>(agent) {

    private val subGoals: Stack<Goal<A>> = Stack()

    override fun onProcess() {
        status = processSubGoals()
    }

    private fun processSubGoals(): Status {
        while (!subGoals.isEmpty()
                && (subGoals.peek().status == Status.COMPLETE
                        || subGoals.peek().status == Status.FAILED)) {
            val goal = subGoals.pop()
            goal.terminate()
        }
        return if (!subGoals.isEmpty()) {
            val status = subGoals.peek().process()
            if (status == Status.COMPLETE && subGoals.size > 1) {
                Status.ACTIVE
            } else {
                status
            }
        } else {
            Status.COMPLETE
        }
    }

    override fun onTerminate() {
        while (!subGoals.isEmpty()) {
            subGoals.pop().terminate()
        }
    }

    override fun handleMessage(telegram: Telegram): Boolean {
        return if (subGoals.isEmpty()) {
            false
        } else {
            subGoals.peek().handleMessage(telegram)
        }
    }

    /**
     * Since this is stack based, lifo.
     *
     * @param goal The Goal to add.
     */
    fun pushSubGoal(goal: Goal<A>) {
        goal.parent = this
        subGoals.push(goal)
    }

    /**
     * Push with goals in order to Stack.
     *
     * The goals will be added from the end to the beginning to the stack.
     * This means that the goals will be processed in the order you expect
     * them to be processed in.
     *
     * @param goals A List of Goals to add.
     */
    fun pushSubGoals(goals: List<Goal<A>>) {
        for (goal in goals.reversed()) {
            pushSubGoal(goal)
        }
    }
}
