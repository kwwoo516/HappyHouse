<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <house-search-bar></house-search-bar>
        <div class="md-layout-item md-medium-size-40 md-xsmall-size-40 md-size-40" style="float:left">
        <house-list />
        </div>
        <div class="md-layout-item md-medium-size-60 md-xsmall-size-60 md-size-60" style="float:left; margin-top:50px;">
        <maps></maps>
        </div>
        <!-- <house-detail /> -->
      </div>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/pages/House/HouseSearchBar.vue";
import HouseList from "@/pages/House/HouseList.vue";
import Maps from "./Maps.vue";
// import HouseDetail from "@/pages/House/HouseDetail.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    Maps,
    // HouseDetail,
  },
};
</script>
