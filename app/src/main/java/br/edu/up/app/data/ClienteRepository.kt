package br.edu.up.app.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ClienteRepository {

    val clientes: Flow<List<Cliente>>
    suspend fun salvar(cliente: Cliente)
    suspend fun excluir(cliente: Cliente)
    suspend fun excluirTodos()

}