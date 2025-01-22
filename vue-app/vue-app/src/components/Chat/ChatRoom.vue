<template>
    <div class="chat-room" v-if="isOpen">
        <h2>Chat Room</h2>


        <!-- 대화 화면 -->
        <div class="chat-messages">
            <p v-for="(message, index) in messages" :key="index">
                <strong>{{ message.sender }}:</strong> {{ message.text }}
            </p>
        </div>


        <!-- 채팅 입력창 -->
        <div class="chat-input">
            <input type="text" v-model="newMessage" placeholder="Type your message here..."
                @keypress.enter="sendMessage" />
            <button @click="sendMessage">Send</button>
        </div>




    </div>
</template>

<script>
export default {

    data(){
        return{
            newMessage:"",
            messages:[],
            inputKey:0,
        }
    },

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
        sendMessage() {
            if (this.newMessage.trim() !== "") {
                const message = {
                    sender: "You", // 임시 사용자 이름
                    text: this.newMessage,
                };
                this.messages.push(message); // 메시지 추가
                this.newMessage = ""; // 입력창 초기화
                console.log("after reset: newMessage =", this.newMessage);
            }
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
    height: 400px;
    background-color: white;
    border: 1px solid black;
    z-index: 1000;
}

.chat-header {
  background-color: #007bff;
  color: white;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.chat-input {
  display: flex;
  padding: 10px;
}

.chat-input input {
  flex: 1;
  padding: 5px;
  margin-right: 10px;
}

.chat-input button {
  padding: 5px 10px;
}
</style>