<template>
  <div>
    <md-table v-model="aptlist" :table-header-color="tableHeaderColor">
      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="동네">{{ item.dongName }}</md-table-cell>
        <md-table-cell md-label="아파트">{{ item.aptName }}</md-table-cell>
        <md-table-cell md-label="가격">{{ item.dealAmount }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "TopAptTable",
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
