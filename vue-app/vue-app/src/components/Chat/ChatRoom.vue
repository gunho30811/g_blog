<template>
  <div class="chat-room" v-if="isOpen">
    <h2>Chat Room</h2>
    <button @click="closeChat">Close</button>
  </div>
</template>
  
<script>
export default {
  props: {
    isOpen: {
      type: Boolean,
      required: true, // 부모로부터 상태를 받아옵니다
    },
  },
  methods: {
    closeChat() {
      this.$emit("close-chat"); // 부모로 이벤트 전달
      console.log("closeChat() called in ChatRoom.vue");
    },
  },
  watch: {
    isOpen(newVal) {
      console.log(`ChatRoom.vue: isOpen changed to ${newVal}`);
    },
  },
};
</script>
  
<style scoped>
.chat-room {
  position: fixed;
  bottom: 80px;
  right: 20px;
  width: 300px;
  height: 200px;
  background-color: white;
  border: 1px solid black;
  z-index: 1000;
}
</style>
  