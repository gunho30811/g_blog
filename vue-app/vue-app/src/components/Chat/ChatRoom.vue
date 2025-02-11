<template>
    <div class="chat-room" v-if="isOpen">
        <div class="chat-header">
            <div class="header-title">
                <h2>채팅</h2>
            </div>
            <div class="header-controls">
                <!-- 톱니바퀴 아이콘 (Font Awesome 사용 예) -->
                <button class="settings-button" @click="openNicknameModal">
                    <i class="fa fa-cog"></i>
                </button>
                <!-- 날짜 선택 달력 -->
                <VueDatePicker v-model="selectedDate" locale="ko" :format="'yyyy-MM-dd'"> <!-- 기본 입력란 대신 달력 아이콘만 표시 -->
                </VueDatePicker>
            </div>
        </div>

        <!-- 닉네임 변경 모달 -->
        <div v-if="isNicknameModalOpen" class="modal-overlay">
            <div class="modal">
                <h3>닉네임 변경</h3>
                <input v-model="newNickname" placeholder="새 닉네임을 입력하세요" />
                <button @click="changeNickname(newNickname)">변경</button>
                <button @click="closeNicknameModal">취소</button>
            </div>
        </div>

        <!-- 대화 화면 -->
        <div class="chat-messages" ref="chatMessages">
            <p v-for="(message, index) in messages" :key="index">
                <strong :style="{ color: message.color }">{{ message.sender }}:</strong> {{ message.text }}
                <span class="message-time">{{ formatTime(message.timestamp) }}</span>
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
            nickname: this.generateRandomNickname("기본닉네임"),  // ✅ 기본 닉네임 랜덤 생성
            newNickname: "",
            nicknameColor: this.generateRandomColor(),  // ✅ 초기 색상 랜덤 설정
            isNicknameModalOpen: false, // 모달 상태
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
        async sendMessage() {
            if (this.newMessage.trim() !== "") {
                const message = {
                    sender: this.nickname,
                    text: this.newMessage,
                    timestamp: new Date(),
                    color: this.nicknameColor,  // 닉네임 색상 추가
                };
                try {
                await fetch("http://localhost:8080/app/api/chat/send", {
                    method: "POST",
                    headers: {
                    "Content-Type": "application/json;charset=UTF-8",  // ✅ Content-Type 명확하게 설정
                    "Accept": "application/json"
                },
                    body: JSON.stringify({
                        username: message.sender, // Spring이 username을 받음
                        message: message.text, // 메시지 내용
                        timestamp: message.timestamp, // 날짜
                    }),
                });
                console.log("✅ 메시지 전송 완료", message);

                this.messages.push(message);
                this.newMessage = "";
                console.log("after reset: newMessage =", this.newMessage);

                // 메시지 추가 후 자동 스크롤
                this.$nextTick(() => {
                    setTimeout(() => {
                        this.scrollToBottom();
                    }, 50);
                });

            } catch(error){
                console.error("❌ 메시지 전송 실패", error);
            }
        }},
        changeNickname(newName) {
            this.nickname = newName.trim() !== ""
                ? newName
                : this.generateRandomNickname("기본닉네임");  // ✅ 닉네임 미입력 시 기본 + 랜덤 숫자

            this.nicknameColor = this.generateRandomColor();  // ✅ 랜덤 색상 변경
            this.isNicknameModalOpen = false;
        },
        scrollToBottom() {
            const chatContainer = this.$refs.chatMessages;
            if (chatContainer) {
                chatContainer.scrollTop = chatContainer.scrollHeight;
            }
        },
        formatTime(timestamp) {
            const date = new Date(timestamp);
            return date.toLocaleTimeString("ko-KR", { hour: "2-digit", minute: "2-digit" });
        },
        openNicknameModal() {
            this.isNicknameModalOpen = true;
        },
        closeNicknameModal() {
            this.isNicknameModalOpen = false;
        },
        generateRandomColor() {
            const letters = "0123456789ABCDEF";
            let color = "#";
            for (let i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;  // 랜덤 HEX 색상 반환
        },
        generateRandomNickname(baseName) {
            const randomNumber = Math.floor(1000 + Math.random() * 9000);  // 4자리 랜덤 숫자
            return `${baseName}${randomNumber}`;
        },


    },
    watch: {
        isOpen(newVal) {
            console.log(`ChatRoom.vue: isOpen changed to ${newVal}`);
        },
    },
    mounted() {
        this.scrollToBottom(); // 페이지 로드 시에도 스크롤을 아래로 이동
    },
};
</script>

<style scoped>
.settings-button {
    background: transparent;
    border: none;
    font-size: 1.5em;
    cursor: pointer;
}


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
    width: 70px;

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

    text-align: left;

    /* 내용이 없을 때도 하단 정렬 */
    display: flex;
    flex-direction: column;
    /*justify-content: flex-end;*/
}

.chat-messages p {
    margin: 0;
    margin-top: 2px;
    padding: 0;
}

.message-time {
    font-size: 0.8em;
    color: gray;
    margin-left: 10px;
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
