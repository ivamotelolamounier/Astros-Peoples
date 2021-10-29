package com.ivamotelo.astropeoples

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: 018 - fazer o handle do clique do botão
        button_load_data.setOnClickListener {
            launchAsyncTask()
        }
    }

    //TODO: 013 - Criar função para exibir os dados carregados
    // oriundo de 'fun loadAstros() : AstrosResult, da classe AstroRepository.ky -> classe AstroResult.kt -> AstroPeoples.kt
    fun showData(astros: List<AstrosPeople>?) {
        textview_data.text = ""                                                 // Limpa o texteview antes de carregar os novos dados
        astros?.forEach { people ->                                             // Concatenação dos dados Json
            textview_data.append("${people.name} - ${people.craft} \n\n\n")     // "Nome astronauta" e "Estação espacial" mais duas quebras de linha
        }
    }

    //TODO: 014 - Criar função para exibir a ProgressBar
    fun showLoadingIndicator(){
        progressbar_load_indicator.visibility = View.VISIBLE
    }

    //TODO: 015 - Criar função para esconder a ProgressBar
    fun hideLoadingIndicator() {
        progressbar_load_indicator.visibility = View.GONE
    }

    //TODO: 017 - Criar função para lançar a Task
    fun launchAsyncTask() {
        AstrosTask().execute()
    }

/*
TODO: 016 - Criar classe interna para rodar a tarefa assincrona
Este é o cerne do estudo das Threads, aqui é onde é lançado o modo assincrono
através de 'asyncTask, que roda uma tarefa em background e após, sincronizar
com a thread principal (UI).
'inner classe' permite acessar classes externas
*/
    inner class AstrosTask() : AsyncTask<Void, Void, List<AstrosPeople>?>() {
        private val repository = AstrosRepository()

        // momento anterior a execução da função 'asyncTask, quando ainda é possível
        // fazer alterações na UI, no caso, mostra a 'progressBar'
        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        // Função que lança a thread secundária de fato em background enquanto a
        // thread primária continua ativa, no caso a UI, evitando travamentos
        override fun doInBackground(vararg params: Void?): List<AstrosPeople>? {
            val result = repository.loadAstros()
            return result?.people
        }

        // Método executado após o lançamento e retorno da função "doInBackground()"
        // no caso, esconde a progressBar e mostra o resultado na "fun showData()
        override fun onPostExecute(result: List<AstrosPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    // Realiza eventuais sincronizações (informações na UI) enquanto a thread secundária está
    // sendo executada -> Durante a execução, nem antes e nem depois. Apenas como exemplo
    // existem formas mais eficazes de fazer isto
    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
        }
    }
}