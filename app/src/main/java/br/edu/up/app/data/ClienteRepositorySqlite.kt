package br.edu.up.app.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClienteRepositorySqlite
    @Inject constructor(val clienteDAO: ClienteDAO) : ClienteRepository {

    override val clientes: Flow<List<Cliente>> get() = clienteDAO.listar()
    override suspend fun salvar(cliente: Cliente) {
        if (cliente.id == 0){
            clienteDAO.inserir(cliente)
        } else {
            clienteDAO.atualizar(cliente)
        }
    }
    override suspend fun excluir(cliente: Cliente){
        clienteDAO.excluir(cliente)
    }

    override suspend fun excluirTodos(){
        clienteDAO.excluirTodos()
    }

    init {
        CoroutineScope(Job()).launch {

            clienteDAO.excluirTodos()
            delay(1000)
            val clientes = clientes()
            for(p in clientes){
                p.id = 0
                clienteDAO.inserir(p)
            }
        }
    }

    companion object {
        fun clientes(): MutableList<Cliente> {
            val lista = mutableListOf(
                Cliente(
                    1,
                    "000",
                    "Carlos",
                    25,
                    "carlos.jpg",
                ),
                Cliente(
                    2,
                    "000",
                    "Julia",
                    22,
                    "julia.jpg",
                ),
                Cliente(
                    3,
                    "000",
                    "Marta",
                    23,
                    "marta.jpg",
                ),
                Cliente(
                    4,
                    "000",
                    "Pedro",
                    28,
                    "pedro.jpg",
                ),
                Cliente(
                    5,
                    "000",
                    "Sabrine",
                    24,
                    "sabrine.jpg",
                ),
                Cliente(
                    6,
                    "000",
                    "Tiago",
                    30,
                    "tiago.jpg",
                ),)
            return lista
        }
    }
}