package com.example.redink

class CProducts  {

    val productId:String
    val productName: String
    val images: String
    val prices:String

    constructor(productId: String, productName: String, images: String, prices: String) {
        this.productId = productId
        this.productName = productName
        this.images = images
        this.prices = prices
    }
}