package br.edu.up.app.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ClientesRepositoryFirebase
    @Inject constructor(val clientesRef : CollectionReference): ClienteRepository {

    private var _clientes = MutableStateFlow(listOf<Cliente>())
    override val clientes: Flow<List<Cliente>> get() = _clientes.asStateFlow()

    init {
        clientesRef.addSnapshotListener { snapshot, _ ->
            if (snapshot == null){
                _clientes = MutableStateFlow(listOf())
            } else {
                var clientes = mutableListOf<Cliente>()
                snapshot.documents.forEach { doc ->
                    val cliente = doc.toObject<Cliente>()
                    if (cliente != null){
                        cliente.docId = doc.id
                        clientes.add(cliente)
                    }
                }
                _clientes.value = clientes
            }

        }
    }

    override suspend fun salvar(cliente: Cliente) {
        if(cliente.docId.isNullOrEmpty()){
            var doc = clientesRef.document()
            cliente.docId = doc.id
            doc.set(cliente)
        } else {
            clientesRef.document(cliente.docId).set(cliente)
        }
    }

    override suspend fun excluir(cliente: Cliente) {
        clientesRef.document(cliente.docId).delete()
    }

    override suspend fun excluirTodos() {
        _clientes.collect{ clientes ->
            clientes.forEach{ cliente ->
                clientesRef.document(cliente.docId).delete()
            }
        }
    }
}