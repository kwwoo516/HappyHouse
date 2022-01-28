<template>
  <div>
    <md-table @md-selected="onSelect">
      <md-table-row
      >
      
        <md-table-cell>
          <img :src="news0.img" alt="" style="width:auto;">
        </md-table-cell>
        <md-table-cell><a :href="news0.link" target='_blank'>{{ news0.content }}</a></md-table-cell>
        
      </md-table-row>
      <md-table-row
      >
        <md-table-cell>
          <img :src="news1.img" alt="" style="width:auto;">
        </md-table-cell>
        <md-table-cell><a :href="news1.link" target='_blank'>{{ news1.content }}</a></md-table-cell>
      </md-table-row>
      <md-table-row
      >
        <md-table-cell>
          <img :src="news2.img" alt="" style="width:auto;">
        </md-table-cell>
        <md-table-cell><a :href="news2.link" target='_blank'>{{ news2.content }}</a></md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "house-news-table",
  data() {
    return {
      news0: [],
      news1: [],
      news2: [],
    };
  },
  mounted() {
    http.get(`/map/housenews`).then(({ data }) => {
      this.news0 = data[0];
      this.news1 = data[1];
      this.news2 = data[2];
    });
  },
  methods: {
    onSelect: function (items) {
      this.selected = items;
    },
  },
};
</script>
<style scoped>
a {
  text-align: left;
}
</style>
