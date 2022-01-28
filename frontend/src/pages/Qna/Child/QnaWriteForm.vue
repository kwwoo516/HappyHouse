<template>
  <md-card>
    <form @submit="onSubmit">
      <md-card-header data-background-color="orange">
        <h4 class="title">
          <md-field>
            <div class="md-medium-size-30 md-xsmall-size-30 md-size-30">
              <md-select v-model="question.category">
                <md-option
                  v-for="(item, index) in categoryList"
                  :key="index"
                  :value="item"
                >
                  {{ item }}
                </md-option>
              </md-select>
            </div>
            <div class="md-medium-size-100 md-xsmall-size-100 md-size-100">
              <md-input
                id="subject"
                v-model="question.subject"
                type="text"
                required
                placeholder="제목 입력"
              ></md-input>
            </div>
          </md-field>
        </h4>
      </md-card-header>
      <md-card-content>
        <div>
          <md-table table-header-color="orange">
            <md-table-row>
              <md-table-cell md-label="userid">
                <md-field>
                  <md-input
                    id="userid"
                    :disabled="isUserid"
                    v-model="question.userid"
                    type="text"
                    required
                    placeholder="작성자 입력..."
                  ></md-input>
                </md-field>
                <md-field>
                  <md-input
                    id="userid"
                    :disabled="isUserid"
                    v-model="question.regtime"
                    type="text"
                    placeholder="작성일"
                  ></md-input>
                </md-field>
              </md-table-cell>
            </md-table-row>
            <md-table-row>
              <md-table-cell md-label="content">
                <md-field>
                  <md-textarea
                    id="content"
                    v-model="question.content"
                    placeholder="문의하실 내용을 입력해주세요"
                  ></md-textarea> </md-field
              ></md-table-cell>
            </md-table-row>
            <md-table-row>
              <md-table-cell
                ><md-button
                  type="submit"
                  class="md-raised md-warning"
                  v-if="this.type === 'register'"
                  >글작성</md-button
                ><md-button type="submit" class="md-raised md-warning"
                  >글수정</md-button
                ></md-table-cell
              >
            </md-table-row>
          </md-table>
        </div>
      </md-card-content>
    </form>
  </md-card>
</template>

<script>
import http from "@/util/http-common";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaWriteForm",
  props: {
    type: { type: String },
  },
  data() {
    return {
      question: {},
      isUserid: false,
      categoryList: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      http.get(`/qna/${this.$route.params.articleno}`).then(({ data }) => {
        this.question = data;
      });
      this.isUserid = true;
    } else {
      this.question.userid = this.userInfo.userid;
    }
    http.get(`/qna/category`).then(({ data }) => {
      this.categoryList = data;
    });
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      //   let err = true;
      //   let msg = "";
      //   !this.question.userid &&
      //     ((msg = "작성자 입력해주세요"),
      //     (err = false),
      //     this.$refs.userid.focus());
      //   err &&
      //     !this.question.subject &&
      //     ((msg = "제목 입력해주세요"),
      //     (err = false),
      //     this.$refs.subject.focus());
      //   err &&
      //     !this.question.content &&
      //     ((msg = "내용 입력해주세요"),
      //     (err = false),
      //     this.$refs.content.focus());

      //   if (!err) alert(msg);
      //   else
      this.type === "register" ? this.registQuestion() : this.modifyQuestion();
    },
    registQuestion() {
      http
        .post(`/qna`, {
          //userid: this.question.userid,
          userid: this.userInfo.userid,
          subject: this.question.subject,
          content: this.question.content,
          category: this.question.category,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "QnaList" });
        });
    },
    modifyQuestion() {
      http
        .put(`/qna`, {
          articleno: this.question.articleno,
          userid: this.question.userid,
          subject: this.question.subject,
          content: this.question.content,
          category: this.question.category,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnaList" });
        });
    },
  },
};
</script>

<style></style>
