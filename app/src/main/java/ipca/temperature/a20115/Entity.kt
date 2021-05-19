package ipca.temperature.a20115

import org.json.JSONObject

interface Entity {

    fun toJson(temperatura: Temperatura) : JSONObject {
        val jsonObject = JSONObject()

        jsonObject.put("nome" , temperatura.nomePessoa)
        jsonObject.put("temperatura" , temperatura.temperatura)
        jsonObject.put("data" , temperatura.data)

        return jsonObject
    }

}