<template>
  <div v-if="houses && houses.length != 0" class="content">
    <div class="md-layout">
      <p>총 {{houses.length}}개의 결과</p>
        <house-list-row
          v-for="(house, index) in houses"
          :key="index"
          :house="house"
        />
    </div>
  </div>
  <div v-else class="content">주택 목록이 없습니다.</div>
</template>

<script>
import HouseListRow from "@/pages/House/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style lang="scss" scoped>
  .md-layout {
    display: block;
    flex-wrap: wrap;
  }
</style>
