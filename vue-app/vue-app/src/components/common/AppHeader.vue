<template>
    <header class="blog-header">
        <div class="logo">
            <a href="/">건호 블로그</a>
        </div>
        <nav class="nav-links">
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/write">Write a Post</a></li>
                <li><a href="/about">About</a></li>
            </ul>
        </nav>
        <div class="search-bar">
            <input type="text" placeholder="Search posts..." v-model="searchQuery" @keyup.enter="performSearch" />
            <button @click="performSearch">Search</button>
        </div>
    </header>
</template>

<script>
export default {
    data() {
        return {
            searchQuery: '',
        };
    },
    methods: {
        performSearch() {
            // 검색 기능 로직 추가 (예: 서버 호출 또는 라우팅)
            console.log('Searching for:', this.searchQuery);
            alert(`Searching for: ${this.searchQuery}`);
        },
    },
};
</script>

<style>
.blog-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 30px;
    background-color: #333;
    color: #fff;
}

.logo a {
    text-decoration: none;
    font-size: 1.5em;
    color: #fff;
    font-weight: bold;
}

.nav-links ul {
    list-style: none;
    display: flex;
    gap: 20px;
}

.nav-links a {
    text-decoration: none;
    color: #fff;
    font-size: 1em;
}

.search-bar {
    display: flex;
    gap: 10px;
}

.search-bar input {
    padding: 5px;
    border: none;
    border-radius: 4px;
}

.search-bar button {
    padding: 5px 10px;
    border: none;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    border-radius: 4px;
}

.search-bar button:hover {
    background-color: #0056b3;
}
</style>
