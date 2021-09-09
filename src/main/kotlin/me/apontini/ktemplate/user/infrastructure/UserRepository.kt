package me.apontini.ktemplate.user.infrastructure

import me.apontini.ktemplate.user.domain.User

interface UserRepository {
    fun save(user: User): User
    fun delete(user: User)
    fun findByEmail(email: String): User?
    fun findById(id: String): User?
}