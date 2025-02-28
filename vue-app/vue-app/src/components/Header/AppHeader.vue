<template>
    <header class="blog-header">
        <div class="logo">
            <a href="/">건호 블로그</a>
        </div>

        <!-- 햄버거 메뉴 버튼 -->
        <button class="menu-toggle" @click="toggleMenu">☰</button>

        <!-- 네비게이션 메뉴 -->
        <nav class="nav-links" :class="{ 'active': isMenuOpen }">
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/write">Write a Post</a></li>
                <li><a href="/about">About</a></li>
            </ul>
        </nav>

        <!-- 검색 바 -->
        <div class="search-bar">
            <input type="text" placeholder="Search..." v-model="searchQuery" @keyup.enter="performSearch" />
            <button @click="performSearch">🔍</button>
        </div>
    </header>
</template>

<script>
export default {
    data() {
        return {
            searchQuery: '',
            isMenuOpen: false,
        };
    },
    methods: {
        performSearch() {
            console.log('Searching for:', this.searchQuery);
            alert(`Searching for: ${this.searchQuery}`);
        },
        toggleMenu() {
            this.isMenuOpen = !this.isMenuOpen;
        }
    },
};
</script>

<style>
/* ✅ Google Fonts 추가 (가독성 좋은 폰트) */
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700&display=swap');

/* ✅ 기본 스타일 */
body {
    font-family: 'Noto Sans KR', sans-serif;
    -webkit-font-smoothing: antialiased; /* 맥OS에서 폰트 깨짐 방지 */
    -moz-osx-font-smoothing: grayscale; /* macOS용 가독성 개선 */
}

.blog-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 20px;
    background-color: #333;
    color: #fff;
    position: relative;
}

/* ✅ 반응형 폰트 크기 적용 */
.logo a {
    text-decoration: none;
    color: #fff;
    font-weight: bold;
    font-size: clamp(1.2rem, 2vw, 1.8rem); /* 반응형 폰트 (최소 1.2rem, 최대 1.8rem) */
}

/* 네비게이션 */
.nav-links ul {
    list-style: none;
    display: flex;
    gap: 20px;
}

.nav-links a {
    text-decoration: none;
    color: #fff;
    font-size: clamp(0.9rem, 1.2vw, 1.2rem); /* 반응형 폰트 */
}

/* 검색 바 */
.search-bar {
    display: flex;
    align-items: center;
    gap: 10px;
}

.search-bar input {
    padding: 5px;
    border: none;
    border-radius: 4px;
    width: 180px;
    font-size: clamp(0.9rem, 1vw, 1.1rem);
}

.search-bar button {
    padding: 5px 10px;
    border: none;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    border-radius: 4px;
    font-size: clamp(0.9rem, 1vw, 1.1rem);
}

.search-bar button:hover {
    background-color: #b30000;
}

/* 📌 반응형 스타일 */
@media (max-width: 1024px) {
    .blog-header {
        flex-wrap: wrap;
        padding: 10px 15px;
    }

    /* 햄버거 메뉴 */
    .menu-toggle {
        display: block;
        font-size: 1.8em;
        background: none;
        border: none;
        color: white;
        cursor: pointer;
        margin-top: -0.3em;

    }

    .nav-links {
        display: none;
        position: absolute;
        top: 83px;
        left: 0;
        width: 100%;
        background-color: #444;
        text-align: center;
        padding: 10px 0;
    }

    .nav-links ul {
        flex-direction: column;
        padding: 0;
    }

    .nav-links.active {
        display: block;
    }

    .search-bar {
        width: 100%;
        justify-content: center;
        margin-top: 10px;
    }

    .search-bar input {
        width: 70%;
    }
}

/* 📌 초소형 기기 (아이폰 SE, 갤럭시 S) */
@media (max-width: 480px) {
    .blog-header {
        flex-direction: column;
        align-items: center;
    }

    .search-bar input {
        width: 90%;
    }

    .menu-toggle {
        position: absolute;
        top: 10px;
        right: 10px;
    }
}
</style>
