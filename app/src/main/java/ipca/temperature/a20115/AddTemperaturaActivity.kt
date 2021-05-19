package ipca.temperature.a20115

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class AddTemperaturaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_temperatura)

        var editTextNome = findViewById<EditText>(R.id.editTextNome)
        var editTextTemp = findViewById<EditText>(R.id.editTextTemperatura)
        var button = findViewById<Button>(R.id.bt_guardar)

        button.setOnClickListener{

            var temp = Temperatura()
            temp.nomePessoa = editTextNome.text.toString()
            temp.temperatura = editTextTemp.text.toString().toFloat()
            temp.data = Date().toString()

            val returnIntent = Intent()
            returnIntent.putExtra("temp", temp.toJson(temp).toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

        }

    }

}