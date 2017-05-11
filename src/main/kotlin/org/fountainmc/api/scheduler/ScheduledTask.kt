package org.fountainmc.api.scheduler

/**
 * [ScheduledTask] specifies a scheduled task.
 */
interface ScheduledTask {

    /**
     * Returns whether or not this scheduled task has been cancelled.
     * @return if the task is cancelled or not
     */
    val isCancelled: Boolean

    /**
     * Returns whether or not this scheduled task is done.
     * @return if the task is done or not
     */
    val isDone: Boolean

    /**
     * Cancels the task.
     */
    fun cancel()

    /**
     * Returns the [TaskSpecification] used to start this task.
     * @return the specification to return
     */
    val specification: TaskSpecification

    /**
     * Returns whether or not this task is running asynchronously.
     * @return if this task is asynchronous
     */
    val isAsync: Boolean
}
