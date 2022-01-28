<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <board-write-form type="modify" />
      </div>
    </div>
  </div>
</template>

<script>
import BoardWriteForm from "@/pages/Board/Child/BoardWriteForm.vue";

export default {
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
