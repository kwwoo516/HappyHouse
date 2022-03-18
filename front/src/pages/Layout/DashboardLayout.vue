<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/dashboard">
        <md-icon>dashboard</md-icon>
        <p>MAIN</p>
      </sidebar-link>
      <sidebar-link to="/board">
        <md-icon>content_paste</md-icon>
        <p>공지사항</p>
      </sidebar-link>
      <sidebar-link to="/house">
        <md-icon>home</md-icon>
        <p>아파트 정보</p>
      </sidebar-link>
      <sidebar-link to="/qna">
        <md-icon>library_books</md-icon>
        <p>QnA</p>
      </sidebar-link>
      <sidebar-link :to="{ name: 'MyPage' }">
        <md-icon>person</md-icon>
        <p>마이페이지</p>
      </sidebar-link>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content> </dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";

export default {
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu,
  },
  data() {
    return {
      sidebarBackground: "ssafy",
      sidebarBackgroundImage: require("@/assets/img/sidebar-2.jpg"),
    };
  },
};
</script>
