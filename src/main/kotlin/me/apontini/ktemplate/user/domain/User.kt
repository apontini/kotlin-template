package me.apontini.ktemplate.user.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class User(@SerialName("_id") val id: String = UUID.randomUUID().toString(), var email: String, var name: String)