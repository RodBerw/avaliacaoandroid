package br.edu.up.app.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.Cliente
import br.edu.up.app.data.ClienteRepository
import br.edu.up.app.data.ProdutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is gallery Fragment"
//    }
//    val text: LiveData<String> = _text

@HiltViewModel
class ClienteViewModel
    @Inject
    constructor(val repository: ProdutoRepository) : ViewModel() {

        var cliente: Cliente = Cliente()

        private var _clientes = MutableStateFlow(listOf<Cliente>())
        val clientes: Flow<List<Cliente>> = _clientes

        init {
            viewModelScope.launch {
                repository.clientes.collect{clientes ->
                    _clientes.value = clientes
                }
            }
        }

        fun novo(){
            this.cliente = Cliente()
        }

        fun editar(cliente: Cliente){
            this.cliente = cliente
        }

        fun salvar() = viewModelScope.launch {
            repository.salvarCliente(cliente)
        }

        fun excluir(cliente: Cliente) = viewModelScope.launch {
            repository.excluirCliente(cliente)
        }
}