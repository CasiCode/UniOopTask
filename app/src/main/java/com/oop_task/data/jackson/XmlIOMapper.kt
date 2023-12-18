package com.oop_task.data.jackson

import android.content.Context
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.oop_task.data.jackson.entities.EntityListXML
import java.io.File
import java.io.FileWriter

object XmlIOMapper {
    val mapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
    }

    inline fun <reified T : Any> write(context: Context, filename: String, obj: T) {
        try {
            val xml = mapper.writeValueAsString(obj)
            val dir = File(context.filesDir, "xml")
            if (!dir.exists()) {
                dir.mkdir()
            }
            val file = File(dir, filename)
            FileWriter(file).apply {
                append(xml)
                flush()
                close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun writeEntityList(context: Context, filename: String, list: EntityListXML) {
        try {
            val xml = mapper.writeValueAsString(list)
            val dir = File(context.filesDir, "xml")
            if (!dir.exists()) {
                dir.mkdir()
            }
            val file = File(dir, filename)
            FileWriter(file).apply {
                append(xml)
                flush()
                close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inline fun <reified T : Any> parseAs(context: Context, filename: String): T {
        val filePath = context.filesDir.toString() + "/xml/" + filename
        val file = File(filePath)
        return mapper.readValue(file.readText())
    }
}