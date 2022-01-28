<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <qna-write-form type="register" />
      </div>
    </div>
  </div>
</template>

<script>
import QnaWriteForm from "@/pages/Qna/Child/QnaWriteForm.vue";

export default {
  name: "QnaWrite",
  components: {
    QnaWriteForm,
  },
};
</script>
