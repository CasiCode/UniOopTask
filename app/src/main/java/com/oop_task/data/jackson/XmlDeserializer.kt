package com.oop_task.data.jackson

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object XmlDeserializer {
    val mapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    inline fun <reified T : Any> parseAs(path: String): T {
        val resource = object {}.javaClass
            .getResourceAsStream(path)?.use {
                it.reader(Charsets.UTF_8).readText()
            }
        return mapper.readValue(resource ?: error("invalid resource path"))
    }
}