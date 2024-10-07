package com.project.webuserservice.service

import com.project.webuserservice.dto.UserDto
import com.project.webuserservice.exception.ErrorMessage
import com.project.webuserservice.exception.UserAlreadyExistsException
import com.project.webuserservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UserServiceSpec extends Specification {

	@Autowired
	UserService userService

	@Autowired
	UserRepository userRepository

	def "should register new user successfully"() {
		given:
		def username = "testuser"
		def password = "TestPassword123"
		def newUser = UserDto.builder().username(username).password(password).build()

		when: "register the user"
		def user = userService.register(newUser)

		then: "user should be saved in the database"
		user != null
		user.username == username
		userRepository.existsByUsername(username)
	}

	def "should not register new user if username already exists"() {
		given:
		def username = "testuser"
		def password = "TestPassword123"
		def newUser = UserDto.builder().username(username).password(password).build()

		when: "register the user"
		userService.register(newUser)
		userService.register(newUser)

		then: "user should be saved in the database"
		def exception = thrown(UserAlreadyExistsException)
		exception.message == ErrorMessage.USERNAME_EXISTS
	}

	def "should not valid credentials"() {
		given:
		def newUser = UserDto.builder().username(username).password(password).build()

		when: "register the user"
		userService.register(newUser)

		then:
		def exception = thrown(IllegalArgumentException)
		exception.message == exceptionMessage

		where:
		username 	| password 		| exceptionMessage
		"test"		| "Test12345@"	| ErrorMessage.INVALID_USERNAME_FORMAT
		"test123"	| "test4"		| ErrorMessage.INVALID_PASSWORD
	}

}
