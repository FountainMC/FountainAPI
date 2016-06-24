package org.fountainmc.api.scheduler;

/**
 * {@link ScheduledTask} specifies a scheduled task.
 */
public interface ScheduledTask {

    /**
     * Returns whether or not this scheduled task has been cancelled.
     * @return if the task is cancelled or not
     */
    boolean isCancelled();

    /**
     * Returns whether or not this scheduled task is done.
     * @return if the task is done or not
     */
    boolean isDone();

    /**
     * Cancels the task.
     */
    void cancel();

    /**
     * Returns the {@link TaskSpecification} used to start this task.
     * @return the specification to return
     */
    TaskSpecification getSpecification();

    /**
     * Returns whether or not this task is running asynchronously.
     * @return if this task is asynchronous
     */
    boolean isAsync();
}
