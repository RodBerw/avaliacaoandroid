package br.edu.up.app.data

import br.edu.up.app.R

class Fotos{
    companion object {
        fun get(key: String): Int {
            val mapOfFotos = mapOf(
                "cafe1.jpg" to R.drawable.cafe1,
                "cafe2.jpg" to R.drawable.cafe2,
                "cafe3.jpg" to R.drawable.cafe3,
                "cafe4.jpg" to R.drawable.cafe4,
                "cafe5.jpg" to R.drawable.cafe5,
                "cafe6.jpg" to R.drawable.cafe6,
                "cafe7.jpg" to R.drawable.cafe7,
                "aperitivo1.jpg" to R.drawable.aperitivo1,
                "aperitivo2.jpg" to R.drawable.aperitivo2,
                "aperitivo3.jpg" to R.drawable.aperitivo3,
                "aperitivo4.jpg" to R.drawable.aperitivo4,
                "aperitivo5.jpg" to R.drawable.aperitivo5,
                "aperitivo6.jpg" to R.drawable.aperitivo6,
                "salada1.jpg" to R.drawable.salada1,
                "salada2.jpg" to R.drawable.salada2,
                "salada3.jpg" to R.drawable.salada3,
                "sopa1.jpg" to R.drawable.sopa1,
                "sopa2.jpg" to R.drawable.sopa2,
                "sopa3.jpg" to R.drawable.sopa3,
                "carne1.jpg" to R.drawable.carne1,
                "carne2.jpg" to R.drawable.carne2,
                "carne3.jpg" to R.drawable.carne3,
                "carne4.jpg" to R.drawable.carne4,
                "frango1.jpg" to R.drawable.frango1,
                "frango2.jpg" to R.drawable.frango2,
                "frango3.jpg" to R.drawable.frango3,
                "sobremesa1.jpg" to R.drawable.sobremesa1,
                "sobremesa2.jpg" to R.drawable.sobremesa2,
                "sobremesa3.jpg" to R.drawable.sobremesa3,
                "sobremesa4.jpg" to R.drawable.sobremesa4,
                //"bebida1.jpg" to R.drawable.bebida1,
                "bebida2.jpg" to R.drawable.bebida2,
                "bebida3.jpg" to R.drawable.bebida3,
                "bebida4.jpg" to R.drawable.bebida4,
                "bebida5.jpg" to R.drawable.bebida5,
                "carlos.jpg" to R.drawable.carlos,
                "julia.jpg" to R.drawable.julia,
                "marta.jpg" to R.drawable.marta,
                "pedro.jpg" to R.drawable.pedro,
                "sabrine.jpg" to R.drawable.sabrine
            )
            return mapOfFotos[key] ?: R.drawable.semfoto
        }
    }
}