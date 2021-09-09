package me.apontini.ktemplate.user.application

import me.apontini.ktemplate.user.domain.User
import me.apontini.ktemplate.user.exceptions.UserAlreadyExistsException
import me.apontini.ktemplate.user.infrastructure.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserService : KoinComponent {
    private val userRepository by inject<UserRepository>()

    fun createUser(email: String, name: String): User {
        userRepository.findByEmail(email)?.let { throw UserAlreadyExistsException() }

        return userRepository.save(
            User(email = email, name = name)
        )
    }

    fun getUserById(id: String): User? = userRepository.findById(id)

    fun updateUser(id: String, update: User.() -> User): User {
        val user = userRepository.findById(id)
        requireNotNull(user) { "User not found" }

        return userRepository.save(user.update())
    }

    fun deleteUser(id: String) {
        val user = userRepository.findById(id)
        requireNotNull(user) { "User doesn't exist" }

        userRepository.delete(user)
    }
}