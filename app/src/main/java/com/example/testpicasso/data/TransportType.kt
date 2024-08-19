package com.example.testpicasso.data

enum class TransportType (private val tipo : String) {
    BUS("BUS"),
    AIRPLANE("AIRPLANE"),
    TRAIN("TRAIN"),
    FERRY("FERRY");

    override fun toString(): String {
        return this.tipo
    }

}


