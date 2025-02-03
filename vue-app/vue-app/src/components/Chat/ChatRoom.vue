<template>
    <div class="chat-room" v-if="isOpen">
        <div class="chat-header">
            <div class="header-title">
                <h2>Chat Room</h2>
            </div>
            <div class="header-controls">
                <!-- 톱니바퀴 아이콘 (Font Awesome 사용 예) -->
                <button class="settings-button">
                    <i class="fa fa-cog"></i>
                </button>
                <!-- 날짜 선택 달력 (HTML5 date input 사용) -->
                <VueDatePicker v-model="selectedDate" :locale="ko"
                    :format="'yyyy-MM-dd'"
                    > <!-- 기본 입력란 대신 달력 아이콘만 표시 -->
                </VueDatePicker>
            </div>
        </div>

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
    data() {
        return {
            newMessage: "",
            messages: [],
            inputKey: 0,
            selectedDate: new Date(),
        };
    },
    props: {
        isOpen: {
            type: Boolean,
            required: true,
        },
    },
    methods: {
        closeChat() {
            this.$emit("close-chat");
            console.log("closeChat() called in ChatRoom.vue");
        },
        sendMessage() {
            if (this.newMessage.trim() !== "") {
                const message = {
                    sender: "You",
                    text: this.newMessage,
                };
                this.messages.push(message);
                this.newMessage = "";
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
.chat-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #007bff;
    color: white;
}

.chat-room {
    position: fixed;
    bottom: 80px;
    right: 20px;
    width: 300px;
    height: 400px;
    background-color: white;
    border: 1px solid black;
    z-index: 1000;
    display: flex;
    flex-direction: column;
}

/* 헤더 영역 */
.chat-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #007bff;
    color: white;
}

.header-title h2 {
    margin: 0;
    font-size: 1.2em;
}

.header-controls {
    display: flex;
    align-items: center;
}

.settings-button {
    background: transparent;
    border: none;
    color: white;
    font-size: 1.2em;
    cursor: pointer;
    margin-right: 5px;
}

/* 기본적으로 HTML5의 date input을 사용합니다 */
.date-picker {
    border: none;
    padding: 3px;
    border-radius: 3px;
}


.chat-messages {
    /* 고정 높이 지정 (예: 250px) */
    height: 300px;
    overflow-y: auto;
    padding: 10px;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;

    /* 내용이 없을 때도 하단 정렬 */
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
}

.chat-input {
    padding: 10px;
    display: flex;
    /* 입력창은 내용에 맞게 높이가 조절되도록 함 */
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
