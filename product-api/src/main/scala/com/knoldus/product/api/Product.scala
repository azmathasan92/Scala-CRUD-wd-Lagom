package com.knoldus.product.api

import play.api.libs.json.{Format, Json}

case class Product(id: String, title: String, price: String, description: String)

object Product {
  implicit val format: Format[Product] = Json.format[Product]
}
