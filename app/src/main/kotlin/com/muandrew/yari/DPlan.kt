package com.muandrew.yari

class DPlan(
        val tasks: Set<DTask>?,
        val dependencies: Map<DTask, List<DTask>>
) {

}