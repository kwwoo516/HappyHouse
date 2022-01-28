<template>
  <form>
    <md-card>
      <md-card-header data-background-color="green">
        <input v-model="article.subject" />
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>작성자 {{userInfo.userid}}</label>
              <md-input disabled></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>작성일</label>
              <md-input v-model="article.regtime" disabled></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>조회수</label>
              <md-input v-model="article.hit" disabled></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100">
            <md-field maxlength="5">
              <label>내용</label>
              <md-textarea v-model="article.content"></md-textarea>
            </md-field>
          </div>
          <div
            v-if="this.type === 'register'"
            class="md-layout-item md-size-100 text-right"
          >
            <md-button class="md-raised md-success" @click="registArticle"
              >작성완료</md-button
            >
          </div>
          <div
            v-if="this.type === 'modify'"
            class="md-layout-item md-size-100 text-right"
          >
            <md-button class="md-raised md-success" @click="modifyArticle"
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
import { mapState } from "vuex";

const memberStore = "memberStore";


export default {
  name: "BoardWriteForm",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        this.article = data;
      });
      this.isUserid = true;
    }
  },
  methods: {
    registArticle() {
      http
        .post(`/board`, {
          userid: this.userInfo.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/board`, {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "BoardList" });
        });
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style></style>
