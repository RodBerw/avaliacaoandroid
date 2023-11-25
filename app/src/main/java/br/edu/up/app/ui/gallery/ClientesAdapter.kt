package br.edu.up.app.ui.gallery

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import br.edu.up.app.data.Cliente
import br.edu.up.app.data.Fotos
import br.edu.up.app.databinding.FragmentItemClienteBinding
//import br.edu.up.app.ui.produto.databinding.FragmentItemProdutoBinding

class ClientesAdapter(
    private val clientes: List<Cliente>,
    val viewModel: ClienteViewModel
    ) :
    RecyclerView.Adapter<ClientesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemClienteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCliente = clientes[position]

        //Carregamento local
        val idFoto = Fotos.get(itemCliente.foto)
        holder.imgFoto.setImageResource(idFoto)

        //Carregamento remoto
//        holder.imgFoto.load(R.drawable.semfoto)
//        Firebase.storage.getReference(itemProduto.foto)
//            .downloadUrl.addOnSuccessListener { imageUrl ->
//                holder.imgFoto.load(imageUrl)
//            }

        holder.txtNome.text = itemCliente.nome
        holder.txtIdade.text = itemCliente.idade.toString()

        //clique para editar item da lista
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(itemCliente)
            val action = ClientesFragmentDirections.actionNavGalleryToClienteFragment()
            view.findNavController().navigate(action)
        }

        //clique para excluir item da lista
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("ATENÇÃO: Tem certeza que deseja excluir?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(itemCliente)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                    //ignorar
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = clientes.size

    inner class ViewHolder(binding: FragmentItemClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgFoto: ImageView = binding.imgFoto
        val txtNome: TextView = binding.txtNome
        val txtIdade: TextView = binding.txtIdade
    }

}