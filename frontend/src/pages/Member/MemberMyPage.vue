<template>
  <form>
    <md-card>
      <md-card-header data-background-color="blue">
        <h4 class="title">내 정보</h4>
        <p class="category">your profile</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field> ID: {{ userInfo.userid }} </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>가입일: {{ userInfo.joindate }} </label>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field> 이름: {{ userInfo.username }} </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field> 이메일: {{ userInfo.email }} </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>휴대전화: {{ userInfo.phone }}</label>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-warning" @click="moveUpdateMember">정보수정</md-button>
            <md-button class="md-raised md-danger" @click="deleteMember">회원탈퇴</md-button>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created(){console.log(this.userInfo)},
  methods: {
    moveUpdateMember() {
      this.$router.replace({
        name: "MemberUpdate",
        params: { userid: this.userInfo.userid },
      });
    },
    deleteMember() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "MemberDelete",
          params: { userid: this.userInfo.userid },
        });
      }
    },
  }
};
</script>

<style></style>
