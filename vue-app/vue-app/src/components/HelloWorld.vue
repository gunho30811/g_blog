<template>
  <div>
    <h1>API 연결 테스트</h1>
    <div v-if="loading">Loading...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <p>{{ message }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      message: "", // 서버에서 받은 메시지
      loading: true,
      error: null,
    };
  },
  mounted() {
    // 하드코딩으로 id=1 요청
    axios.get('/api/user', { params: { id: 1 } })
      .then(response => {
        this.message = response.data; // 서버에서 받은 문자열
        this.loading = false;
      })
      .catch(error => {
        this.error = error.message;
        this.loading = false;
      });
  },
};
</script>
