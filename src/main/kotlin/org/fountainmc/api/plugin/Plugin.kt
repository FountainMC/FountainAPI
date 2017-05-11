package org.fountainmc.api.plugin

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Plugin(val id: String, val name: String = "", val version: String = "", val description: String = "", val url: String = "", val author: Array<String> = arrayOf())
