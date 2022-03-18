<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card class="md-card-plain">
          <md-card-header data-background-color="green">
            <h4 class="title">Q&A</h4>
            <p class="category">문의사항이 있으시면 남겨주세요.</p>
          </md-card-header>
        </md-card>
      </div>
      <div class="md-medium-size-50 md-xsmall-size-50 md-size-50">
        <md-button class="md-raised md-warning" @click="moveWrite()"
          >문의하기</md-button
        >
      </div>
      <div class="md-medium-size-50 md-xsmall-size-50 md-size-50" style="margin-left:20px;">
        <md-field>
          <md-select v-model="category" @md-selected="searchQna" placeholder="전체">
            <md-option
              v-for="(item, index) in categoryList"
              :key="index"
              :value="item"
            >
              {{ item }}
            </md-option>
          </md-select>
        </md-field>
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-80"
      >
        <qna-item
          v-for="(question, index) in questions"
          :key="index"
          v-bind="question"
        />
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import QnaItem from "@/pages/Qna/Child/QnaItem";

export default {
  name: "QnaList",
  components: {
    QnaItem,
  },
  data() {
    return {
      questions: [],
      categoryList: {},
      category: "",
      content: "",
    };
  },
  created() {
    http.get(`/qna`).then(({ data }) => {
      this.questions = data;
    });
    http.get(`/qna/category`).then(({ data }) => {
      this.categoryList = data;
    });
    this.category = "전체";
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QnaWrite" });
    },
    searchQna() {
      http
        .get(`/qna`, { params: { key: "category", word: this.category } })
        .then(({ data }) => {
          this.questions = data;
        });
    },
  },
};
</script>
