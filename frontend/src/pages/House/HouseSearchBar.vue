<template>
  <div>
    <div class="md-layout md-gutter">
      <div class="md-layout-item">
        <md-field>
          <md-select v-model="sidoCode" @md-selected="gugunList">
            <md-option
              v-for="(sido, index) in sidos"
              :key="index"
              :value="sido.value"
            >
              {{ sido.text }}
            </md-option>
          </md-select>
        </md-field>
      </div>
      <div class="md-layout-item">
        <md-field>
          <md-select v-model="gugunCode" @md-selected="searchApt">
            <md-option
              v-for="(gugun, index) in guguns"
              :key="index"
              :value="gugun.value"
            >
              {{ gugun.text }}
            </md-option>
          </md-select>
        </md-field>
      </div>
    </div>

    <!-- <md-button class="md-primary md-raised" @click="movie = 'pulp-fiction'"
      >Set Pulp Fiction</md-button
    > -->
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>

<style></style>
