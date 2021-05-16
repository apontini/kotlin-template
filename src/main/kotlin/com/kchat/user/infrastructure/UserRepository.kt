package com.kchat.user.infrastructure

import com.kchat.user.domain.User

interface UserRepository {
    fun save(user: User): User
    fun delete(user: User)
    fun findByEmail(email: String): User?
    fun findById(id: String): User?
    fun update(newUser: User): User
}