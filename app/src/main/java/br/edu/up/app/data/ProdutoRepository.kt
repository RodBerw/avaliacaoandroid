package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow

interface ProdutoRepository {

    val produtos: Flow<List<Produto>>
    val clientes: Flow<List<Cliente>>
    suspend fun salvarProduto(produto: Produto)
    suspend fun excluirProduto(produto: Produto)
    suspend fun excluirTodosProduto()

    suspend fun salvarCliente(cliente: Cliente)
    suspend fun excluirCliente(cliente: Cliente)
    suspend fun excluirTodosCliente()

}