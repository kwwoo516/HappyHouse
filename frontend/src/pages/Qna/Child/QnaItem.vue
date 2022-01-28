<template>
  <md-card>
    <div v-if="replyCnt > 0">
      <md-card-header data-background-color="orange">
        <h4 class="title">[{{ category }}] {{ subject }}</h4>
        <p class="category">답변완료</p>
      </md-card-header>
    </div>
    <div v-else>
      <md-card-header data-background-color="default">
        <h4 class="title">[{{ category }}] {{ subject }}</h4>
        <p class="category">미답변</p>
      </md-card-header>
    </div>
    <md-card-content>
      <div>
        <md-table table-header-color="orange">
          <md-table-row>
            <md-table-cell md-label="userid"
              >작성자 : {{ userid }} / 문의날짜 : {{ regtime }}
            </md-table-cell>
          </md-table-row>
          <md-table-row>
            <md-table-cell md-label="content">{{ content }}</md-table-cell>
          </md-table-row>
          <md-table-row v-if="userInfo != null && userInfo.userid == userid">
            <md-table-cell md-label="content"
              ><md-button class="md-raised" @click="moveModifyQuestion()"
                >작성글 수정</md-button
              ><md-button class="md-raised" @click="deleteQuestion()"
                >작성글 삭제</md-button
              ></md-table-cell
            >
          </md-table-row>
          <md-table-row v-for="(reply, index) in replies" :key="index">
            <md-table-cell md-label="content"
              ><h4>{{ reply.userid }} : {{ reply.content }}</h4>
              <div v-if="userInfo != null && userInfo.admin == 1">
                <md-button class="md-raised" @click="deleteReply(reply.replyno)"
                  >댓글 삭제</md-button
                >
              </div>
            </md-table-cell>
          </md-table-row>
          <div
            class="
              md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100
            "
            v-if="userInfo != null && userInfo.admin == 1"
          >
            <md-field>
              <label>댓글</label>
              <md-input v-model="replyinput" type="text"></md-input>
              <md-button class="md-raised md-warning" @click="writeReply()"
                >댓글 작성</md-button
              >
            </md-field>
          </div>
        </md-table>
      </div>
    </md-card-content>
  </md-card>
</template>

<script>
import http from "@/util/http-common";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    content: String,
    hit: Number,
    regtime: String,
    category: String,
  },
  data() {
    return {
      replies: [],
      replyinput: "",
      replyCnt: 0,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    http.get(`/qnareply/` + this.articleno).then(({ data }) => {
      this.replies = data;
      this.replyCnt = data.length;
    });
  },
  methods: {
    moveModifyQuestion() {
      this.$router.replace({
        name: "QnaUpdate",
        params: { articleno: this.articleno },
      });
    },
    deleteQuestion() {
      http.delete("/qna/" + this.articleno).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        window.location.reload();
      });
    },
    writeReply(articleno) {
      http
        .post(`/qnareply`, {
          articleno: this.articleno,
          userid: this.userInfo.userid,
          content: this.replyinput,
        })
        .then(({ data }) => {
          let msg = "댓글 작성시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "댓글 등록이 완료되었습니다.";
          }
          this.pageUpdate();
        });
    },
    deleteReply(replyno) {
      http.delete("/qnareply/" + replyno).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        this.pageUpdate();
      });
    },
    pageUpdate() {
      http.get(`/qnareply/` + this.articleno).then(({ data }) => {
        this.replies = data;
        this.replyCnt = data.length;
      });
    },
  },
};
</script>

<style></style>
