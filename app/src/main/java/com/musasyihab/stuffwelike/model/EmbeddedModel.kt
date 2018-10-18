package com.musasyihab.stuffwelike.model

data class EmbeddedModel(
        val filters: List<FilterModel>,
        val articles: List<ArticleModel>
)