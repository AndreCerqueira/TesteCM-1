package ipca.temperature.a20115

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.sql.Date
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    var temperaturas : MutableList<Temperatura> = arrayListOf()
    lateinit var listViewTemp: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewTemp = findViewById(R.id.listViewTemperaturas)
        val buttonAdd = findViewById<FloatingActionButton>(R.id.bt_add)
        val buttonMaior = findViewById<FloatingActionButton>(R.id.bt_maior)
        listViewTemp.adapter = TemperaturasAdapter()

        temperaturas.add(Temperatura("ze", 10f))
        temperaturas.add(Temperatura("manuel", 12f))
        temperaturas.add(Temperatura("pedro", 9f))

        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddTemperaturaActivity::class.java)
            startActivityForResult(intent, 1001)
        }

        buttonMaior.setOnClickListener() {

            val maior = maiorTemperatura()

            if (maior == -1f)
                Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "A maior temperatura é $maior!", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK){
                val jsonArticleStr  : String = data?.getStringExtra("temp")!!
                val jsonArticle = JSONObject(jsonArticleStr)
                val temp = Temperatura.fromJson(jsonArticle)
                temperaturas.add(temp)
                listViewTemp.adapter = TemperaturasAdapter()
            }
        }
    }

    inner class TemperaturasAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return temperaturas.size
        }

        override fun getItem(position: Int): Any {
            return temperaturas[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_temperatura, parent, false)

            val textViewNome = rowView.findViewById<TextView>(R.id.textViewNomePessoa)
            val textViewTemp = rowView.findViewById<TextView>(R.id.textViewTemperatura)
            val textViewData = rowView.findViewById<TextView>(R.id.textViewData)

            textViewNome.text = temperaturas[position].nomePessoa
            textViewTemp.text = temperaturas[position].temperatura.toString()
            textViewData.text = temperaturas[position].data

            return rowView
        }
    }

    fun maiorTemperatura(): Float {

        var maior: Float = -1f

        if (temperaturas.size > 0) {

            maior = temperaturas[0].temperatura!!

            for (i in 1 until temperaturas.size) {
                // && temperaturas[i].data == LocalDateTime.now().toString()
                if (temperaturas[i].temperatura!! > maior)
                    maior = temperaturas[i].temperatura!!

            }
        }

        return maior
    }



}