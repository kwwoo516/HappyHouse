<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="blue">
            <h4 class="title">공지사항</h4>
            <p class="category">공지사항 페이지입니다.</p>
          </md-card-header>
          <md-card-content>
            <board-item-table table-header-color="blue"></board-item-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
//import { BoardItemTable } from "@/pages/Board/Child/BoardItemTable";
import BoardItemTable from './Child/BoardItemTable.vue';

export default {
  components: {
    BoardItemTable,
  },
};
</script>
