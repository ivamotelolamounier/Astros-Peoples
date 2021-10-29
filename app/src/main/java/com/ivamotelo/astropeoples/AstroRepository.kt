package com.ivamotelo.astropeoples

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

//TODO: 010 - Criar a classe responsável por carregar os dados
class AstrosRepository {

    //TODO: 011 - Criar função para carregar os astronautas
    fun loadAstros() : AstrosResult? {
        val client = OkHttpClient()  //Biblioteca para manipular http (implementation "com.squareup.okhttp3:okhttp")
        val request = Request
            .Builder()
            .url("http://api.open-notify.org/astros.json")  // Caminho BASE para requisição
            .build()

        val response = client.newCall(request).execute()  // Realiza a chamada passando a requisição
        return parseResultToJson(response.body?.string()) // Converte para o objeto AstrosResult da fun loadastros()
                                                          // 'body' é o corpo da requisição da url http://api.open-notify.org/astros.json
    }

    //TODO: 012 - Criar função para converter o json para classe java (Kotlin) AstrosResult
    private fun parseResultToJson(body: String?) =
        Gson().fromJson(body, AstrosResult::class.java)

}