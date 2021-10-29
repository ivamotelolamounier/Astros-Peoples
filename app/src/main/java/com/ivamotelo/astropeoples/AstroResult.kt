package com.ivamotelo.astropeoples

import com.google.gson.annotations.SerializedName

//TODO: 009 Criar classe para representar o resultado da api
// http://api.open-notify.org/astros.json

data class AstrosResult(
    @SerializedName("message") val message: String,
    @SerializedName("number") val number: Int,
    @SerializedName("people") val people: List<AstrosPeople>
)