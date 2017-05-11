package org.fountainmc.api.scheduler

import java.util.concurrent.TimeUnit

/**
 * [TaskSpecification] specifies how often a task should be run.
 */
class TaskSpecification private constructor(
        /**
         * Return how many ticks to wait before executing a task.
         * @return ticks to wait
         */
        val delay: Int,
        /**
         * Return how many ticks occur between each task run.
         * @return ticks to wait before executing again
         */
        val repeat: Int,
        /**
         * Return if this is a repeating task.
         * @return whether or not this is a repeating task
         */
        val isRepeating: Boolean,
        /**
         * Return this `TaskSpecification`'s owner.
         * @return the owner
         */
        val plugin: Any) {
    companion object {

        /**
         * Requests a task be started on the next tick (sync) or as soon as reasonably possible (async). There is no timing
         * guarantee.
         * @param plugin the plugin
         * *
         * @return the task specification
         */
        fun nextTick(plugin: Any): TaskSpecification {
            return TaskSpecification(0, 0, false, plugin)
        }

        /**
         * Requests a task be run after a specified period of time.
         * @param time the time to wait
         * *
         * @param unit the unit of time to wait
         * *
         * @param plugin the plugin
         * *
         * @return the task specification
         */
        fun after(time: Int, unit: TimeUnit, plugin: Any): TaskSpecification {
            return TaskSpecification((unit.toMillis(time.toLong()) / 50).toInt(), 0, false, plugin)
        }

        /**
         * Requests a task be run after the specified period of ticks have passed.
         * @param time the time to wait
         * *
         * @param plugin the plugin
         * *
         * @return the task specification
         */
        fun afterTicks(time: Int, plugin: Any): TaskSpecification {
            return TaskSpecification(time, 0, false, plugin)
        }

        /**
         * Requests a task be run every specified period of time.
         * @param delay the time to wait before first invocation
         * *
         * @param time the time to wait between each invocation
         * *
         * @param unit the unit of time
         * *
         * @param plugin the plugin
         * *
         * @return the time specification
         */
        fun every(delay: Int, time: Int, unit: TimeUnit, plugin: Any): TaskSpecification {
            val t = (unit.toMillis(time.toLong()) / 50).toInt()
            val d = (unit.toMillis(delay.toLong()) / 50).toInt()
            return TaskSpecification(d, t, true, plugin)
        }

        /**
         * Requests a task be run every specified period of time in ticks.
         * @param delay the time to wait before first invocation
         * *
         * @param time the time to wait between each invocation
         * *
         * @param plugin the plugin
         * *
         * @return the time specification
         */
        fun everyTicks(delay: Int, time: Int, plugin: Any): TaskSpecification {
            return TaskSpecification(delay, time, true, plugin)
        }
    }
}
