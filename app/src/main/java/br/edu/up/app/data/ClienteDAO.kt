package br.edu.up.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDAO {

    @Query("select * from clientes")
    fun listar(): Flow<List<Cliente>>

    @Insert
    suspend fun inserir(cliente: Cliente)

    @Update
    suspend fun atualizar(cliente: Cliente)

    @Delete
    suspend fun excluir(cliente: Cliente)

    @Query("delete from clientes where id = :id")
    suspend fun excluir(id: Int)

    @Query("delete from clientes")
    suspend fun excluirTodos()
}