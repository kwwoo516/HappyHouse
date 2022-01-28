<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item">
        <md-card class="md-card-plain">
          <md-card-header data-background-color="green">
            <h4 class="title">로드뷰 {{this.addr}}</h4>
          </md-card-header>

          <md-card-content>
            <div id="roadview" style="width: 100%; height: 300px"></div>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initLoadView()
      : this.addKakaoMapScript();
  },
  props: {
    addr : String,
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initLoadView);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=5910f388d8ea76969320c90e392024b2";
      document.head.appendChild(script);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=5910f388d8ea76969320c90e392024b2&libraries=services";
      document.head.appendChild(script);
    },
    initLoadView() {
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div

      var roadview = new kakao.maps.Roadview(roadviewContainer); // 로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      // 위도, 경도 가져오기
      var geocoder = new kakao.maps.services.Geocoder();

      var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var x = parseFloat(result[0].x).toFixed(6);
          var y = parseFloat(result[0].y).toFixed(6);
          var position = new kakao.maps.LatLng(y, x);

          // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
          roadviewClient.getNearestPanoId(position, 100, function (panoId) {
            if(panoId != null) roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            
          });
        }
      };
      geocoder.addressSearch(this.addr, callback);
    },
  },
};
</script>

<style></style>
