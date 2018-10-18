package com.musasyihab.stuffwelike.model

data class FilterModel(
        val values: List<ValueModel>,
        val id: String,
        val priority: Int,
        val title: String,
        val _metadata: MetadataModel
)