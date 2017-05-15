/*
 * Public logging utilities
 *
 * Log levels:
 * 1. Trace
 * 2. Debug
 * 3. Info
 * 4. Warn
 * 5. Error
 */
package org.fountainmc.api.utils

import org.fountainmc.api.Fountain
import org.slf4j.Logger

/**
 * Get the logger of the current plugin.
 */
val currentPluginLogger: Logger
    inline get() {
        /*
         * IMPORTANT NOTE: Keep this inline so that 'currentPlugin' is fast with Reflection.getCallerClass().
         * Otherwise it'd have to walk up the stack with Thread.stackTrace which can be up to a hundred times slower.
         */
        return requireNotNull(Fountain.pluginManager.currentPlugin()) {
            "Not in a plugin context!"
        }.logger
    }

/**
 * Log the result of calling [lazyMessage] at the trace level if enabled.
 *
 * This is a faster and more idiomatic alternative to format strings,
 * and should be prefered when possible, since it has no overhead.
 *
 * @param throwable an exception to long alongside the message
 * @param lazyMessage the function to compute the message to log
 */
inline fun Logger.trace(throwable: Throwable? = null, lazyMessage: () -> String) {
    if (isTraceEnabled) {
        trace(lazyMessage(), throwable)
    }
}

/**
 * Log the result of calling [lazyMessage] at the debug level if enabled.
 *
 * This is a faster and more idiomatic alternative to format strings,
 * and should be prefered when possible, since it has no overhead.
 *
 * @param throwable an exception to long alongside the message
 * @param lazyMessage the function to compute the message to log
 */
inline fun Logger.debug(throwable: Throwable? = null, lazyMessage: () -> String) {
    if (isDebugEnabled) {
        debug(lazyMessage(), throwable)
    }
}


/**
 * Log the result of calling [lazyMessage] at the info level if enabled.
 *
 * This is a faster and more idiomatic alternative to format strings,
 * and should be prefered when possible, since it has no overhead.
 *
 * @param throwable an exception to long alongside the message
 * @param lazyMessage the function to compute the message to log
 */
inline fun Logger.info(throwable: Throwable? = null, lazyMessage: () -> String) {
    if (isInfoEnabled) {
        info(lazyMessage(), throwable)
    }
}


/**
 * Log the result of calling [lazyMessage] at the warning level if enabled.
 *
 * This is a faster and more idiomatic alternative to format strings,
 * and should be prefered when possible, since it has no overhead.
 *
 * @param throwable an exception to long alongside the message
 * @param lazyMessage the function to compute the message to log
 */
inline fun Logger.warn(throwable: Throwable? = null, lazyMessage: () -> String) {
    if (isWarnEnabled) {
        warn(lazyMessage(), throwable)
    }
}

/**
 * Log the result of calling [lazyMessage] at the error level if enabled.
 *
 * This is a faster and more idiomatic alternative to format strings,
 * and should be prefered when possible, since it has no overhead.
 *
 * @param throwable an exception to long alongside the message
 * @param lazyMessage the function to compute the message to log
 */
inline fun Logger.error(throwable: Throwable? = null, lazyMessage: () -> String) {
    if (isErrorEnabled) {
        error(lazyMessage(), throwable)
    }
}




