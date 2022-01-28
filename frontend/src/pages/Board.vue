<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-15 md-xsmall-size-15 md-size-15"
      ></div>
      <div
        class="md-layout-item md-medium-size-70 md-xsmall-size-70 md-size-70"
      >
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Board",
};
</script>
