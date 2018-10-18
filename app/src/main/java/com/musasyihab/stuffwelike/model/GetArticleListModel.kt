package com.musasyihab.stuffwelike.model

data class GetArticleListModel(
        val resourceType: String,
        val articlesCount: Int,
        val _links: LinksModel,
        val _embedded: EmbeddedModel,
        val category: String,
        val language: String,
        val country: String
)