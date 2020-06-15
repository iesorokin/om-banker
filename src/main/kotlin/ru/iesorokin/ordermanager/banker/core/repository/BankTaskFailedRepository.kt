package ru.iesorokin.ordermanager.banker.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.iesorokin.ordermanager.banker.input.dto.BankerTaskMessage

interface BankTaskFailedRepository : MongoRepository<BankerTaskMessage, String>