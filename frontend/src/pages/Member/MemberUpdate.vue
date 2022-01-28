<template>
  <form>
    <md-card>
      <md-card-header data-background-color="green">
        <h4 class="title">내 정보</h4>
        <p class="category">your profile</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>아이디</label>
              <md-input v-model="user.userid" type="text"></md-input>
              <span class="md-error">There is an error</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>비밀번호</label>
              <md-input v-model="user.userpwd" type="password"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>비밀번호 재확인</label>
              <md-input v-model="user.pwdchk" type="password"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>이름</label>
              <md-input v-model="user.username" type="text"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>본인확인 이메일</label>
              <md-input v-model="user.email" type="email"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>휴대전화</label>
              <md-input v-model="user.phone" type="text"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-success" @click="updateMember"
              >수정완료</md-button
            >
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberUpdate",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
        username: "",
        email: "",
        phone: "",
      },
      hasMessages: false,
    };
  },
  created() {
    http.get(`/user/${this.$route.params.userid}`).then(({ data }) => {
      this.user = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
    updateMember() {
      http
        .put(`/user`, {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          phone: this.user.phone,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            this.SET_USER_INFO(this.user);
          }
          alert(msg);

          this.$router.push({ name: "MyPage" });
        });
    },
  },
};
</script>

<style></style>
