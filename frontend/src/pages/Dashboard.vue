<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-10 md-xsmall-size-10 md-size-10"
      ></div>
      <div
        class="md-layout-item md-medium-size-80 md-xsmall-size-80 md-size-80"
        style="margin-bottom: 40px"
      >
        <md-content class="md-elevation-18">
          <div class="jb-wrap">
            <div class="jb-image">
              <img
                src="@/assets/img/4422.jpg"
                alt="Designed by upklyak / Freepik"
                width="auto"
              />
            </div>
            <!-- <img src="@/assets/img/6268.jpg" alt="" width="500px"/> -->
            <div class="jb-text">
              <p>HAPPY HOUSE</p>
            </div>
          </div>
        </md-content>
        <a href="http://www.freepik.com" target="_blank" style="float: right"
          >Designed by upklyak / Freepik</a
        >
      </div>
      <div
        class="md-layout-item md-medium-size-10 md-xsmall-size-10 md-size-10"
      ></div>

      <div
        class="md-layout-item md-medium-size-10 md-xsmall-size-10 md-size-10"
      ></div>
      <div
        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-20"
      >
        <stats-card data-background-color="green">
          <template slot="header">
            <md-icon>
              <router-link :to="{ name: 'House' }">store</router-link></md-icon
            >
          </template>

          <template slot="content">
            <p class="category">Revenue</p>
            <h3 class="title">
              <router-link :to="{ name: 'House' }">아파트 찾기</router-link>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>date_range</md-icon>
              아파트 정보를 확인하세요
            </div>
          </template>
        </stats-card>
      </div>
      <div
        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-20"
      >
        <stats-card data-background-color="orange">
          <template slot="header">
            <md-icon>
              <router-link :to="{ name: 'SignUp' }"
                >people</router-link
              ></md-icon
            >
          </template>

          <template slot="content">
            <p class="category">이용하기</p>
            <h3 class="title">
              <router-link :to="{ name: 'SignUp' }">회원가입</router-link>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon class="text-danger">warning</md-icon>
              회원가입 후 이용하세요
            </div>
          </template>
        </stats-card>
      </div>
      <div
        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-20"
      >
        <stats-card data-background-color="red">
          <template slot="header">
            <md-icon>
              <router-link :to="{ name: 'Qna' }">
                info_outline</router-link
              ></md-icon
            >
          </template>

          <template slot="content">
            <p class="category">Q&amp;A</p>
            <h3 class="title">
              <router-link :to="{ name: 'Qna' }">문의사항</router-link>
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>local_offer</md-icon>
              문의사항을 알려주세요
            </div>
          </template>
        </stats-card>
      </div>
      <div
        class="md-layout-item md-medium-size-50 md-xsmall-size-100 md-size-20"
        style="margin-bottom: 20px"
      >
        <stats-card data-background-color="blue">
          <template slot="header">
            <a href="https://www.instagram.com/hellossafy/" target="_blank">
              <i class="fab fa-instagram"></i>
            </a>
          </template>

          <template slot="content">
            <p class="category">Follow</p>
            <h3 class="title">
              <a href="https://www.instagram.com/hellossafy/" target="_blank"
                >SSAFY</a
              >
            </h3>
          </template>

          <template slot="footer">
            <div class="stats">
              <md-icon>update</md-icon>
              SAFFY 정보를 확인하세요!
            </div>
          </template>
        </stats-card>
      </div>
      <div
        class="md-layout-item md-medium-size-10 md-xsmall-size-10 md-size-10"
      ></div>
      <div
        class="md-layout-item md-medium-size-10 md-xsmall-size-10 md-size-10"
      ></div>
      <div
        class="md-layout-item md-medium-size-40 md-xsmall-size-40 md-size-40"
      >
        <top-apt-list></top-apt-list>
      </div>
      <div
        class="md-layout-item md-medium-size-40 md-xsmall-size-40 md-size-40"
      >
        <apt-news-card>
          <template slot="content">
            <span class="md-nav-tabs-title">News:</span>
            <md-tabs class="md-success" md-alignment="left">
              <md-tab id="tab-home" md-label="시장동향" md-icon="cloud">
                <apt-news-table></apt-news-table>
              </md-tab>

              <md-tab id="tab-pages" md-label="정책/제도" md-icon="cloud">
                <policy-news-table></policy-news-table>
              </md-tab>

              <md-tab id="tab-posts" md-label="업계동향" md-icon="cloud">
                <house-news-table></house-news-table>
              </md-tab>
            </md-tabs>
          </template>
        </apt-news-card>
      </div>
    </div>
  </div>
</template>

<script>
import TopAptList from "./DashBoard/TopAptList.vue";
import AptNewsCard from "./DashBoard/AptNewsCard.vue";
import AptNewsTable from "./DashBoard/Child/AptNewsTable.vue";
import PolicyNewsTable from "./DashBoard/Child/PolicyNewsTable.vue";
import HouseNewsTable from "./DashBoard/Child/HouseNewsTable.vue";
import StatsCard from "../components/Cards/StatsCard.vue";

export default {
  components: {
    TopAptList,
    AptNewsCard,
    AptNewsTable,
    PolicyNewsTable,
    HouseNewsTable,
    StatsCard,
  },
};
</script>
<style scoped>
.jb-wrap {
  margin: 10px auto;
  position: relative;
}
.jb-wrap img {
  width: 100%;
  vertical-align: middle;
}
.jb-text {
  padding: 5px 10px;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.jb-text p {
  color: azure;
  font-size: 80px;
  font-weight: 600;
  text-shadow: 2px 2px 6px gray;
}
</style>
