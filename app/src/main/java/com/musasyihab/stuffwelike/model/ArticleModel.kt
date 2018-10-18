package com.musasyihab.stuffwelike.model

data class ArticleModel(
        val description: String,
        val media: List<MediaModel>,
        val url: String,
        val sku: String,
        val title: String,
        val price: PriceModel,
        val _metadata: MetadataModel,
        val _links: LinksModel
)