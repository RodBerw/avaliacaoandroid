package br.edu.up.app.ui.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.data.Cliente
import br.edu.up.app.data.Produto
import br.edu.up.app.databinding.FragmentClienteBinding
import br.edu.up.app.databinding.FragmentProdutoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : ClienteViewModel by activityViewModels()
        val binding = FragmentClienteBinding.inflate(layoutInflater)

        val cliente = viewModel.cliente
        binding.inputClienteNome.setText(cliente.nome)
        binding.inputClienteIdade.setText(cliente.idade)
        binding.inputClienteFoto.setText(cliente.foto)

        binding.btnSalvar.setOnClickListener {
            val clienteSalvar = Cliente(
                cliente.id,
                cliente.docId,
                binding.inputClienteNome.text.toString(),
                binding.inputClienteIdade.text.toString().toInt(),
                binding.inputClienteFoto.text.toString(),
            )
            viewModel.cliente = clienteSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return binding.root
    }
}