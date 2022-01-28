<template>
  <div>
    <md-card md-with-hover>
      <div @click="openLoadView">
        <!-- <md-ripple> -->
        <md-card-media-actions>
          <md-card-media>
            <img src="@/assets/img/apt.png" alt="Cover" />
          </md-card-media>
          <md-card-header-text>
            <div class="apt-title">
              <div class="md-title">{{ house.아파트 }}</div>
              <div class="md-subhead">{{ house.법정동 }}</div>
            </div>
            <p>거래금액 : {{ house.거래금액 }}</p>
            <p>거래일 : {{ house.년 }}-{{ house.월 }}-{{ house.일 }}</p>
            <p>건축년도 : {{ house.건축년도 }}</p>
            <p>전용면적 : {{ house.전용면적 }}</p>
            <p>층 : {{ house.층 }}</p>
          </md-card-header-text>

          <md-card-actions> </md-card-actions>
          <md-card-actions>
            <div v-if="isWishList">
              <button class="wishBtn" @click="delMyList">
                <md-icon>favorite</md-icon>
              </button>
            </div>
            <div v-else>
              <button class="customBtn" @click="myList">
                <md-icon>favorite</md-icon>
              </button>
            </div>
          </md-card-actions>
        </md-card-media-actions>
        <!-- </md-ripple> -->
      </div>
      <div v-if="isMap">
        <house-load-view :addr="this.addr"></house-load-view>
      </div>
    </md-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions, mapState } from "vuex";
import HouseLoadView from "@/pages/House/Child/HouseLoadView.vue";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
      isMap: false,
      addr: "",
      wishlist: [],
    };
  },
  props: {
    house: Object,
  },
  components: {
    HouseLoadView,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    isWishList() {
      let msg = false;
      this.wishlist.forEach((element) => {
        if (element.aptName == this.house.아파트) {
          //console.log(element.aptName, this.house.아파트);
          msg = true;
          return msg;
        }
      });
      return msg;
    },
  },
  created() {
    if (this.userInfo != null) {
      http
        .get(`/map/wishlist`, { params: { userid: this.userInfo.userid } })
        .then(({ data }) => {
          this.wishlist = data;
        });
    }
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    openLoadView() {
      this.addr = "서울특별시 " + this.house.법정동 + " " + this.house.지번;
      this.isMap = !this.isMap ? true : false;
    },
    myList() {
      if (this.userInfo != null) {
        http
          .post(`/map/wishlist`, {
            aptName: this.house.아파트,
            dongCode: this.house.동코드,
            dongName: this.house.법정동,
            jibun: this.house.지번,
            dealAmount: this.house.거래금액,
            userid: this.userInfo.userid,
          })
          .then(({ data }) => {
            console.log(data);
            let msg = "위시리스트 등록시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "위시리스트 등록이 완료되었습니다.";
              alert(msg);
              this.wishListUpdate();
            }
          });
      } else {
        alert("로그인 후 어쩌구~~");
      }
    },
    delMyList() {
      http
        .delete(`/map/wishlist`, {
          params: {
            userid: this.userInfo.userid,
            aptname: this.house.아파트,
          },
        })
        .then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "위시리스트 삭제가 완료되었습니다.";
            alert(msg);
            this.wishListUpdate();
          }
        });
    },
    wishListUpdate() {
      http
        .get(`/map/wishlist`, { params: { userid: this.userInfo.userid } })
        .then(({ data }) => {
          this.wishlist = data;
        });
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style lang="scss" scoped>
.md-card img {
  width: 320px;
  height: 200px;
  object-fit: cover;
}
.apt-title {
  font-size: 20px;
}

.customBtn {
  box-shadow: inset 0px 1px 0px 0px #ffffff;
  background: linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
  background-color: #ffffff;
  border-radius: 31px;
  border: 1px solid #dcdcdc;
  display: inline-block;
  cursor: pointer;
  color: #666666;
  font-family: Arial;
  font-size: 15px;
  font-weight: bold;
  padding: 5px 5px;
  text-decoration: none;
  text-shadow: 0px 1px 0px #ffffff;
}
.wishBtn {
  box-shadow: inset 0px 1px 0px 0px #ffffff;
  background: linear-gradient(to bottom, #ef027d 5%, #ff5bb0 100%);
  background-color: #ef027d;
  border-radius: 31px;
  border: 1px solid #dcdcdc;
  display: inline-block;
  cursor: pointer;
  color: #666666;
  font-family: Arial;
  font-size: 15px;
  font-weight: bold;
  padding: 5px 5px;
  text-decoration: none;
  text-shadow: 0px 1px 0px #ffffff;
}
.customBtn:hover {
  background: linear-gradient(to bottom, #ef027d 5%, #ff5bb0 100%);
  background-color: #ef027d;
}
.customBtn:active {
  position: relative;
  top: 1px;
}
</style>
