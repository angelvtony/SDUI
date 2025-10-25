package com.example.sdui.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


@Composable
fun RenderDynamicUI(json: String) {
    val element = remember { Json.decodeFromString<JsonElement>(json) }
    RenderElement(element)
}

@Composable
fun RenderElement(element: JsonElement) {
    val obj = element.jsonObject
    when (obj["type"]?.jsonPrimitive?.content) {
        "column" -> Column {
            obj["children"]?.jsonArray?.forEach { child ->
                RenderElement(child)
            }
        }

        "text" -> Text(obj["text"]?.jsonPrimitive?.content ?: "")
        "textfield" -> {
            var value by remember { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(obj["label"]?.jsonPrimitive?.content ?: "") }
            )
        }

        "button" -> Button(onClick = {}) {
            Text(obj["text"]?.jsonPrimitive?.content ?: "Button")
        }
    }
}


