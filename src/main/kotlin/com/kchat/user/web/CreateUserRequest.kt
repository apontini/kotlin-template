package com.kchat.user.web

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(val email: String, val name: String)
