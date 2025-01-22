<template>
  <AppHeader/>
  <HelloWorld msg="Welcome to Your Vue.js App"/>
  <ChatButton @toggle-chat="toggleChat" />
  <ChatRoom :isOpen="isChatOpen"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import AppHeader from './components/Header/AppHeader.vue'
import ChatButton from "./components/Chat/ChatButton.vue";
import ChatRoom from "./components/Chat/ChatRoom.vue";

export default {
  name: 'App',
  components: {
    HelloWorld,
    AppHeader,
    ChatButton,
    ChatRoom
  },

  data(){
    return{
      isChatOpen: false,
    };
  },
  methods: {
    toggleChat(){
      this.isChatOpen= !this.isChatOpen;
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body{
  margin: 0;
}
</style>
