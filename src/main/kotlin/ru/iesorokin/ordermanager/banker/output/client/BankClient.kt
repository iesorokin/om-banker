package ru.iesorokin.ordermanager.banker.output.client

import org.apache.http.client.utils.URIBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.iesorokin.ordermanager.banker.error.BankResponseException

@Component
class BankClient(
        private val restTemplateBank: RestTemplate,
        @Value("\${banker.task.urlToImplement}")
        private val urlToImplement: String
) {

    fun bankProcess(
            extOrderId: String
    ) {
        //use urlToImplement to rest to bank system
    }

}