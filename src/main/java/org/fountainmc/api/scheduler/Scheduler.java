package org.fountainmc.api.scheduler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.Executor;

/**
 * The {@link Scheduler} class represents a task scheduler, which may or may not be asynchronous.
 *
 * {@code Scheduler} makes no timing guarantees (it will make a best-effort attempt to execute on the desired tick).
 */
@ParametersAreNonnullByDefault
public interface Scheduler {
    /**
     * Schedules a task to run.
     * @param runnable the runnable to execute
     * @param specification the task specification to use
     * @return the task invoked
     */
    ScheduledTask schedule(Runnable runnable, TaskSpecification specification);

    /**
     * Returns whether or not this scheduler runs tasks asynchronously.
     * @return whether or not this scheduler runs tasks asynchronously
     */
    boolean isAsync();

    /**
     * Cancel the specified task.
     * @param task the task to cancel
     */
    void cancel(ScheduledTask task);

    /**
     * Cancel all tasks with a specified plugin.
     * @param plugin the plugin to use
     */
    void cancelAll(Object plugin);

    /**
     * Shut down the scheduler.
     */
    void shutdown();

    /**
     * Returns an executor.
     * @param plugin the plugin to use
     * @return the executor
     */
    default Executor asExecutor(Object plugin) {
        return command -> schedule(command, TaskSpecification.nextTick(plugin));
    }
}
