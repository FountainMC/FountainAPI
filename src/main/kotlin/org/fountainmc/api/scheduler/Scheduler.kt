package org.fountainmc.api.scheduler

import java.util.concurrent.Executor

/**
 * The [Scheduler] class represents a task scheduler, which may or may not be asynchronous.

 * `Scheduler` makes no timing guarantees (it will make a best-effort attempt to execute on the desired tick).
 */
interface Scheduler {

    /**
     * Schedules a task to run.
     * @param runnable the runnable to execute
     * *
     * @param specification the task specification to use
     * *
     * @return the task invoked
     */
    fun schedule(runnable: Runnable, specification: TaskSpecification): ScheduledTask

    /**
     * Returns whether or not this scheduler runs tasks asynchronously.
     * @return whether or not this scheduler runs tasks asynchronously
     */
    val isAsync: Boolean

    /**
     * Cancel the specified task.
     * @param task the task to cancel
     */
    fun cancel(task: ScheduledTask)

    /**
     * Cancel all tasks with a specified plugin.
     * @param plugin the plugin to use
     */
    fun cancelAll(plugin: Any)

    /**
     * Shut down the scheduler.
     */
    fun shutdown()

    /**
     * Wraps this scheduler as an executor.
     *
     * @param plugin the plugin to use for submitting requests
     * @return this scheduler, wrapped as an executor
     */
    fun asExecutor(plugin: Any): Executor {
        return Executor { command -> schedule(command, TaskSpecification.nextTick(plugin)) }
    }
}
