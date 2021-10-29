package com.ivamotelo.astropeoples

import com.google.gson.annotations.SerializedName

//TODO: 007 - Criar classe para representar o astronauta
// http://api.open-notify.org/astros.json

data class AstrosPeople(
    @SerializedName("craft") val craft: String,
    @SerializedName("name") val name: String
)