package br.edu.up.app.ui.produto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.Produto
import br.edu.up.app.data.ProdutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel
    @Inject constructor(val repository: ProdutoRepository) : ViewModel() {

    var produto: Produto = Produto()

    private var _produtos = MutableStateFlow(listOf<Produto>())
    val produtos: Flow<List<Produto>> = _produtos

    init {
        viewModelScope.launch {
            repository.produtos.collect{produtos ->
                _produtos.value = produtos
            }
        }
    }

    fun novo(){
        this.produto = Produto()
    }

    fun editar(produto: Produto){
        this.produto = produto
    }

    fun salvar() = viewModelScope.launch {
        repository.salvarProduto(produto)
    }

    fun excluir(produto: Produto) = viewModelScope.launch {
        repository.excluirProduto(produto)
    }
}