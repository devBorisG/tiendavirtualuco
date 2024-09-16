package com.example.tiendavirtualuco.reportes

data class Report(val code: String,
                  val status: String,
                  val date: String,
                  val method: String,
                  val tracking: String,
                  val product: String,
                  val price: String)
