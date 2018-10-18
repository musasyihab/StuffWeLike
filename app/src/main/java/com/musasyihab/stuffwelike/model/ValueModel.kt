package com.musasyihab.stuffwelike.model

data class ValueModel(
        val colorCode: String,
        val colorImage: String,
        val id: String,
        val priority: Int,
        val title: String,
        val _metadata: MetadataModel
)