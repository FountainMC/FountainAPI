package org.fountainmc.api.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * {@link TaskSpecification} specifies how often a task should be run.
 */
public final class TaskSpecification {

    private final int delay;
    private final int repeat;
    private final boolean repeating;
    private final Object plugin;

    private TaskSpecification(int delay, int repeat, boolean repeating, Object plugin) {
        this.delay = delay;
        this.repeat = repeat;
        this.repeating = repeating;
        this.plugin = plugin;
    }

    /**
     * Requests a task be started on the next tick (sync) or as soon as reasonably possible (async). There is no timing
     * guarantee.
     * @param plugin the plugin
     * @return the task specification
     */
    public static TaskSpecification nextTick(Object plugin) {
        return new TaskSpecification(0, 0, false, plugin);
    }

    /**
     * Requests a task be run after a specified period of time.
     * @param time the time to wait
     * @param unit the unit of time to wait
     * @param plugin the plugin
     * @return the task specification
     */
    public static TaskSpecification after(int time, TimeUnit unit, Object plugin) {
        return new TaskSpecification((int) (unit.toMillis(time) / 50), 0, false, plugin);
    }

    /**
     * Requests a task be run after the specified period of ticks have passed.
     * @param time the time to wait
     * @param plugin the plugin
     * @return the task specification
     */
    public static TaskSpecification afterTicks(int time, Object plugin) {
        return new TaskSpecification(time, 0, false, plugin);
    }

    /**
     * Requests a task be run every specified period of time.
     * @param delay the time to wait before first invocation
     * @param time the time to wait between each invocation
     * @param unit the unit of time
     * @param plugin the plugin
     * @return the time specification
     */
    public static TaskSpecification every(int delay, int time, TimeUnit unit, Object plugin) {
        int t = (int) (unit.toMillis(time) / 50);
        int d = (int) (unit.toMillis(delay) / 50);
        return new TaskSpecification(d, t, true, plugin);
    }

    /**
     * Requests a task be run every specified period of time in ticks.
     * @param delay the time to wait before first invocation
     * @param time the time to wait between each invocation
     * @param plugin the plugin
     * @return the time specification
     */
    public static TaskSpecification everyTicks(int delay, int time, Object plugin) {
        return new TaskSpecification(delay, time, true, plugin);
    }

    /**
     * Return how many ticks to wait before executing a task.
     * @return ticks to wait
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Return how many ticks occur between each task run.
     * @return ticks to wait before executing again
     */
    public int getRepeat() {
        return repeat;
    }

    /**
     * Return if this is a repeating task.
     * @return whether or not this is a repeating task
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Return this {@code TaskSpecification}'s owner.
     * @return the owner
     */
    public Object getPlugin() {
        return plugin;
    }
}
