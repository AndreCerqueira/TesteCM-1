package ipca.temperature.a20115

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Temperatura : Entity{

    var nomePessoa: String? = null
    var temperatura: Float? = null
    var data: String? = null

    constructor() {

    }

    constructor(nomePessoa: String?, temperatura: Float?) {
        this.nomePessoa = nomePessoa
        this.temperatura = temperatura
        this.data = Date().toString()
    }

    companion object {
        fun fromJson(jsonObject: JSONObject) : Temperatura {
            val temp = Temperatura()
            temp.nomePessoa = if (!jsonObject.isNull("nome" )) jsonObject.getString("nome" )else null
            temp.temperatura = if (!jsonObject.isNull("temperatura" )) jsonObject.getString("temperatura").toFloat() else null
            temp.data = if (!jsonObject.isNull("data" )) jsonObject.getString("data") else null

            return temp
        }
    }

}