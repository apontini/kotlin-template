package me.apontini.ktemplate.user.application

import UnitTest
import me.apontini.ktemplate.user.domain.User
import me.apontini.ktemplate.user.exceptions.UserAlreadyExistsException
import me.apontini.ktemplate.user.infrastructure.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.*
import me.apontini.ktemplate.user.exceptions.UserNotFoundException
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.inject

internal class UserServiceTest : UnitTest() {

    override fun koinModule(): Module = module {
        single { mockk<UserRepository>() }
    }

    init {
        val repository: UserRepository by inject()
        val userService = UserService()

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

                it("should throw an exception and not save the user") {
                    shouldThrow<UserAlreadyExistsException> { userService.createUser("email@test.it", "pass") }
                    verify { repository.save(any()) wasNot Called }
                }
            }
        }

        describe("User deletion") {
            context("Correct execution") {
                every { repository.findById("userId") } returns User("userId", "email@test.it", "user name")
                justRun { repository.delete(any()) }
                userService.deleteUser("userId")

                it("should correctly delete the specified user") {
                    verify { repository.delete(User("userId", "email@test.it", "user name")) }
                }
            }

            context("The user doesn't exist") {
                every { repository.findById("userId") } returns null

                it("should throw an exception and should not delete any user") {
                    shouldThrow<UserNotFoundException> { userService.deleteUser("userId") }
                    verify(exactly = 0) { repository.delete(any()) }
                }
            }
        }
    }
}