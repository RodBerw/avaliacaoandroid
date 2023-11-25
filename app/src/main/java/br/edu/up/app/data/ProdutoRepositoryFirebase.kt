//package br.edu.up.app.data
//
//import com.google.firebase.firestore.CollectionReference
//import com.google.firebase.firestore.ktx.toObject
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.collect
//import javax.inject.Inject
//
//class ProdutoRepositoryFirebase
//    @Inject constructor(val produtosRef : CollectionReference): ProdutoRepository {
//
//    private var _produtos = MutableStateFlow(listOf<Produto>())
//    override val produtos: Flow<List<Produto>> get() = _produtos.asStateFlow()
//
//    init {
//        produtosRef.addSnapshotListener { snapshot, _ ->
//            if (snapshot == null){
//                _produtos = MutableStateFlow(listOf())
//            } else {
//                var produtos = mutableListOf<Produto>()
//                snapshot.documents.forEach { doc ->
//                    val produto = doc.toObject<Produto>()
//                    if (produto != null){
//                        produto.docId = doc.id
//                        produtos.add(produto)
//                    }
//                }
//                _produtos.value = produtos
//            }
//
//        }
//    }
//
//    override suspend fun salvarProduto(produto: Produto) {
//        if(produto.docId.isNullOrEmpty()){
//            var doc = produtosRef.document()
//            produto.docId = doc.id
//            doc.set(produto)
//        } else {
//            produtosRef.document(produto.docId).set(produto)
//        }
//    }
//
//    override suspend fun excluirProduto(produto: Produto) {
//        produtosRef.document(produto.docId).delete()
//    }
//
//    override suspend fun excluirTodosProduto() {
//        _produtos.collect{ produtos ->
//            produtos.forEach{ produto ->
//                produtosRef.document(produto.docId).delete()
//            }
//        }
//    }
//}