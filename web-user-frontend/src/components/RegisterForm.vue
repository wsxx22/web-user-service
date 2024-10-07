<template>
  <div class="register-form">
    <form @submit.prevent="registerUser">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" v-model="username" id="username" placeholder="Enter your username" />
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" v-model="password" id="password" placeholder="Enter your password" />
      </div>

      <button type="submit"> {{ 'Register' }} </button>
    </form>

    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import axios from 'axios';

export default {
  name: 'RegisterForm',
  setup() {
    const username = ref('');
    const password = ref('');
    const errorMessage = ref('');
    const successMessage = ref('');

    const registerUser = async () => {
      errorMessage.value = '';
      successMessage.value = '';

      try {
        await axios.post('http://localhost:8080/api/users/register', {
          username: username.value,
          password: password.value,
        });
        successMessage.value = 'User registered successfully!';
      } catch (error: any) {
        errorMessage.value = error.response?.data || 'Registration failed. Please try again.';
      } finally {
        username.value = ''
        password.value = ''
      }
    };

    return {
      username,
      password,
      errorMessage,
      successMessage,
      registerUser,
    };
  },
};
</script>

<style scoped>
.register-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:disabled {
  background-color: #aaa;
}

.error {
  color: red;
  margin-top: 10px;
}

.success {
  color: green;
  margin-top: 10px;
}
</style>
