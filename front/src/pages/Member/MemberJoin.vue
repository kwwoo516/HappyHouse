<template>
  <form>
    <md-card>
      <md-card-header data-background-color="green">
        <h4 class="title">내 정보</h4>
        <p class="category">your profile</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="idClass">
              <label>아이디</label>
              <md-input
                ref="userid"
                v-model="userid"
                type="text"
                @input="idCheck"
              ></md-input>
              <span class="md-error">이미 존재하는 ID 입니다.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="pwdClass">
              <label>비밀번호</label>
              <md-input
                id="userpwd"
                ref="userpwd"
                v-model="userpwd"
                type="password"
                @input="pwdCheck"
              ></md-input>
              <span class="md-error">비밀번호는 8자 이상입니다.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="pwdchkClass">
              <label>비밀번호 재확인</label>
              <md-input
                ref="pwdchk"
                v-model="pwdchk"
                type="password" @input="pwdchkCheck"
              ></md-input>
              <span class="md-error">동일한 비밀번호를 다시 입력해주세요.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="nameClass">
              <label>이름</label>
              <md-input
                ref="username"
                v-model="username"
                type="text"
              ></md-input>
              <span class="md-error">이름을 입력해주세요.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="emailClass">
              <label>본인확인 이메일</label>
              <md-input ref="email" v-model="email" type="email"></md-input>
              <span class="md-error">이메일을 입력해주세요.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50"></div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field :class="phoneClass">
              <label>휴대전화</label>
              <md-input ref="phone" v-model="phone" type="number"></md-input>
              <span class="md-error">전화번호를 입력해주세요.</span>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-success" @click="joinMember"
              >Join</md-button
            >
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "MemberJoin",
  data() {
    return {
      userid: "",
      userpwd: "",
      username: "",
      email: "",
      phone: "",
      pwdchk: "",
      idMsg: false,
      pwdMsg: false,
      pwdchkMsg: false,
      nameMsg: false,
      emailMsg: false,
      phoneMsg: false,
    };
  },
  computed: {
    idClass() {
      return {
        "md-invalid": this.idMsg,
      };
    },
    pwdClass() {
      return {
        "md-invalid": this.pwdMsg,
      };
    },
    pwdchkClass() {
      return {
        "md-invalid": this.pwdchkMsg,
      };
    },
    nameClass() {
      return {
        "md-invalid": this.nameMsg,
      };
    },
    emailClass() {
      return {
        "md-invalid": this.emailMsg,
      };
    },
    phoneClass() {
      return {
        "md-invalid": this.phoneMsg,
      };
    },
  },
  methods: {
    joinMember() {
      let err = true;
      let msg = "";

      !this.userid && ((err = false), this.$refs.userid.$el.focus());
      err &&
        (!this.userpwd || this.userpwd.length < 8) &&
        ((err = false),
        this.$refs.userpwd.$el.focus(),
        (this.idMsg = false),
        (this.pwdMsg = true));
      err &&
        (!this.pwdchk || this.userpwd !== this.pwdchk) &&
        ((err = false),
        this.$refs.pwdchk.$el.focus(),
        (this.idMsg = false),
        (this.pwdMsg = false),
        (this.pwdchkMsg = true));
      err &&
        !this.username &&
        ((err = false),
        this.$refs.username.$el.focus(),
        (this.idMsg = false),
        (this.pwdMsg = false),
        (this.pwdchkMsg = false),
        (this.nameMsg = true));
      err &&
        !this.email &&
        ((err = false),
        this.$refs.email.$el.focus(),
        (this.idMsg = false),
        (this.pwdMsg = false),
        (this.pwdchkMsg = false),
        (this.nameMsg = false),
        (this.emailMsg = true));
      err &&
        !this.phone &&
        ((err = false),
        this.$refs.phone.$el.focus(),
        (this.idMsg = false),
        (this.pwdMsg = false),
        (this.pwdchkMsg = false),
        (this.nameMsg = false),
        (this.emailMsg = false),
        (this.phoneMsg = true));

      if (err) {
        http
          .post(`/user/register`, {
            userid: this.userid,
            userpwd: this.userpwd,
            username: this.username,
            email: this.email,
            phone: this.phone,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "Home" });
          });
      }
    },
    idCheck() {
      http
        .get(`/user/idcheck`, {
          params: { ckid: this.userid },
        })
        .then(({ data }) => {
          if (data == 0) {
            this.idMsg = false;
          } else {
            this.idMsg = true;
          }
        });
    },
    pwdCheck() {
      if(this.userpwd.length < 8) {
            this.pwdMsg = true;
      } else {
            this.pwdMsg = false;
      }
    },
    pwdchkCheck() {
      if(this.pwdchk.length < 8 || this.userpwd !== this.pwdchk) {
            this.pwdchkMsg = true;
      } else {
            this.pwdchkMsg = false;
      }
    },
  },
};
</script>

<style></style>
