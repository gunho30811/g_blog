<template>
    <div class="chat-button" @click="toggleChat" @mouseover="onMouseOver" @mouseleave="onMouseLeave">
      <img src="@/assets/chat.png" alt="Chat Icon" />
    </div>
  </template>
  
  <script>
  export default {
    emits: ["toggle-chat"], // 부모 컴포넌트로 이벤트 전달
    methods: {
      toggleChat() {
        console.log("Button clicked"); // 버튼 클릭 시 로그 출력
        this.$emit("toggle-chat"); // 부모 컴포넌트에 알림
      },
      onMouseOver() {
        console.log("Mouse over the button"); // 마우스 오버 시 로그 출력
      },
      onMouseLeave() {
        console.log("Mouse left the button"); // 마우스 떠날 때 로그 출력
      },
    },
  };
  </script>
  
  <style scoped>
  .chat-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    cursor: pointer;
    width: 50px;
    height: 50px;
    z-index: 1000;
    border: 2px solid black; /* 기본 검정 테두리 */
    border-radius: 50%; /* 동그란 버튼 */
    transition: border-color 0.3s ease; /* 테두리 색 전환 애니메이션 */
  }
  
  .chat-button:hover {
    border-color: red; /* 마우스 오버 시 빨간색 테두리 */
  }
  
  .chat-button img {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
  </style>
  