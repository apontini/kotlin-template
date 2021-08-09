package com.kchat.user.application

import UnitTest
import com.kchat.user.domain.User
import com.kchat.user.exceptions.UserAlreadyExistsException
import com.kchat.user.infrastructure.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.inject

internal class UserServiceTest : UnitTest() {

    override fun koinModule(): Module = module {
        single { mockk<UserRepository>() }
    }

    init {
        val repository: UserRepository by inject()
        val userService: UserService = UserService()

        describe("User creation") {
            context("Correct execution") {
                val capturedUser = slot<User>()
                every { repository.findByEmail("email@test.it") } returns null
                every { repository.save(capture(capturedUser)) } returns User("userId", "email@test.it", "user name")
                val createdUser = userService.createUser("email@test.it", "user name")

                it("should correctly save the user") {
                    capturedUser.captured.email shouldBe "email@test.it"
                    capturedUser.captured.name shouldBe "user name"
                }

                it("should return the created user") {
                    createdUser.email shouldBe "email@test.it"
                    createdUser.name shouldBe "user name"
                }
            }

            context("The email already exists") {
                every { repository.findByEmail("email@test.it") } returns User("userId", "email@test.it", "user name")

                it("should not save the user") {
                    verify { repository.save(any()) wasNot Called }
                }

                it("should throw an exception") {
                    shouldThrow<UserAlreadyExistsException> { userService.createUser("email@test.it", "pass") }
                }
            }
        }
    }
}