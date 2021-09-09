package me.apontini.ktemplate.user.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(val email: String, val name: String)
