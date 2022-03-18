<template>
  <div>
    <md-card>
      <md-card-header data-background-color="orange">
        <h4 class="title">인기 매물 TOP 5</h4>
        <p class="category">현재 회원들에게 가장 인기가 많은 매물입니다.</p>
      </md-card-header>
      <md-card-content>
        <top-apt-table table-header-color="orange"></top-apt-table>
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import TopAptTable from "./Child/TopAptTable.vue";

export default {
  name: "TopAptList",
  components: {
    TopAptTable,
  },
  props: {
    tableHeaderColor: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      aptlist: [],
    };
  },
  created() {
    http.get(`/map/TopAptList`).then(({ data }) => {
      this.aptlist = data;
    });
  },
};
</script>
