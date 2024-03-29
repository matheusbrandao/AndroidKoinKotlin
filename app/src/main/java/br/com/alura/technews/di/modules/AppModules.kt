package br.com.alura.technews.di.modules

import android.util.Log
import androidx.room.Room
import br.com.alura.technews.database.AppDatabase
import br.com.alura.technews.database.dao.NoticiaDAO
import br.com.alura.technews.repository.NoticiaRepository
import br.com.alura.technews.retrofit.webclient.NoticiaWebClient
import br.com.alura.technews.ui.viewmodel.FormularioNoticiaViewModel
import br.com.alura.technews.ui.viewmodel.ListaNoticiasViewModel
import br.com.alura.technews.ui.viewmodel.VisualizaNoticiaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val NOME_BANCO_DE_DADOS = "news.db"

val appModules = module {
    /*
        Factory - Sempre cria uma nova instância
     */
    factory {

    }

    /*
        Single - Cria somente uma instância

        get() = significa que o Koin vai tentar te alguma forma injetar a dependência
        que a instância precisa (estava context antes)

        O <AppDatabase> não é obrigatório, porém é interessante colocar
        para auxiliar na leitura do código e evitar erros de retorno
     */
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }

    single<NoticiaDAO> {
        get<AppDatabase>().noticiaDAO
    }

    single<NoticiaWebClient> {
        NoticiaWebClient(get())
    }

    single<NoticiaRepository> {
        NoticiaRepository(get(), get())
    }

    viewModel<ListaNoticiasViewModel> {
        ListaNoticiasViewModel(get())
    }

    viewModel<VisualizaNoticiaViewModel> { (id: Long) ->
        VisualizaNoticiaViewModel(id, get())
    }

    viewModel<FormularioNoticiaViewModel> {
        FormularioNoticiaViewModel(get())
    }
}