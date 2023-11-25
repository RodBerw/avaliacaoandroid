package br.edu.up.app.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.edu.up.app.R
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.ProdutoRepository
import br.edu.up.app.databinding.FragmentListaClientesBinding
import br.edu.up.app.databinding.FragmentListaProdutosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ClientesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val banco = BancoSQLite.get(requireContext())
//        val repository = ProdutoRepository(banco.produtoDao())
//        val viewModel = ProdutoViewModel(repository)

        //Injeção automática de dependência
        val viewModel : ClienteViewModel by activityViewModels()

        val binding = FragmentListaClientesBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.clientes.collect{ clientes ->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = ClientesAdapter(clientes, viewModel)
                }
            }
        }
        return binding.root
    }
}