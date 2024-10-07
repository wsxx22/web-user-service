<template>
  <div id="app">
    <h1>Web User Registration</h1>
    <RegisterForm />
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import RegisterForm from './components/RegisterForm.vue';

@Options({
  components: {
    RegisterForm,
  },
})
export default class App extends Vue {}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 40px;
}

h1 {
  color: #42b983;
}
</style>
