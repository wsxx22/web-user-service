package com.project.webuserservice.service

import com.project.webuserservice.dto.UserDto
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

}
