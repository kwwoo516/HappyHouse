import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/Dashboard.vue";

import Board from "@/pages/Board.vue";
import BoardList from "@/pages/Board/BoardList.vue";
import Vue from "vue";
import VueRouter from "vue-router";

import BoardView from "@/pages/Board/BoardView.vue";
import BoardUpdate from "@/pages/Board/BoardUpdate.vue";
import BoardWrite from "@/pages/Board/BoardWrite.vue";
import BoardDelete from "@/pages/Board/BoardDelete.vue";

import Qna from "@/pages/Qna.vue";
import QnaList from "@/pages/Qna/QnaList.vue";
import QnaWrite from "@/pages/Qna/QnaWrite.vue";
import QnaUpdate from "@/pages/Qna/QnaUpdate.vue";
import QnaDelete from "@/pages/Qna/QnaDelete.vue";

import House from "@/pages/House.vue";

import Member from "@/pages/Member.vue";
import MemberJoin from "@/pages/Member/MemberJoin.vue";
import MemberLogin from "@/pages/Member/MemberLogin.vue";
import MemberMyPage from "@/pages/Member/MemberMyPage.vue";
import MemberDelete from "@/pages/Member/MemberDelete.vue";
import MemberUpdate from "@/pages/Member/MemberUpdate.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const alreadyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    next();
  } else {
    alert("이미 가입하셨습니다!");
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: Dashboard,
      },
      {
        path: "/house",
        name: "House",
        component: House,
      },
      {
        path: "/user",
        name: "Member",
        component: Member,
        children: [
          {
            path: "singin",
            name: "SignIn",
            component: MemberLogin,
          },
          {
            path: "singup",
            name: "SignUp",
            beforeEnter: alreadyAuthUser,
            component: MemberJoin,
          },
          {
            path: "mypage",
            name: "MyPage",
            beforeEnter: onlyAuthUser,
            component: MemberMyPage,
          },
          {
            path: "delete/:userid",
            name: "MemberDelete",
            component: MemberDelete,
          },
          {
            path: "update/:userid",
            name: "MemberUpdate",
            component: MemberUpdate,
          },
        ],
      },
      {
        path: "/board",
        name: "Board",
        component: Board,
        redirect: "/board/list",
        children: [
          {
            path: "list",
            name: "BoardList",
            component: BoardList,
          },
          {
            path: "write",
            name: "BoardWrite",
            component: BoardWrite,
          },
          {
            path: "view/:articleno",
            name: "BoardView",
            component: BoardView,
          },
          {
            path: "update/:articleno",
            name: "BoardUpdate",
            component: BoardUpdate,
          },
          {
            path: "delete/:articleno",
            name: "BoardDelete",
            component: BoardDelete,
          },
        ],
      },
      {
        path: "/qna",
        name: "Qna",
        component: Qna,
        redirect: "/qna/list",
        children: [
          {
            path: "list",
            name: "QnaList",
            component: QnaList,
          },
          {
            path: "write",
            name: "QnaWrite",
            beforeEnter: onlyAuthUser,
            component: QnaWrite,
          },
          {
            path: "update/:articleno",
            name: "QnaUpdate",
            component: QnaUpdate,
          },
          {
            path: "delete/:articleno",
            name: "QnaDelete",
            component: QnaDelete,
          },
        ],
      },
    ],
  },
];

// configure router
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes, // short for routes: routes
  linkExactActiveClass: "nav-item active",
});

export default router;
