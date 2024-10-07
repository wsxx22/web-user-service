package com.project.webuserservice.controller

import com.project.webuserservice.dto.UserDto
import com.project.webuserservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerSpec extends Specification {

	UserService userService = Mock(UserService)

	@Autowired
	MockMvc mvc

	@Autowired
	UserController userController

	def 'should register user'() {
		given:
		def username = "testuser"
		def password = "TestPassword123"
		def newUser = UserDto.builder().username(username).password(password).build()

		when:
		userService.register(_ as UserDto) >> newUser

		then:
		mvc.perform(post("/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
                {
                    "username": "testuser",
                    "password": "TestPassword123"
                }
                """))
				.andExpect(status().isCreated())
				.andExpect(jsonPath('$.username').value("testuser"))

	}
}
