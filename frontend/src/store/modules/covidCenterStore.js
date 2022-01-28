import { covidcenterList } from "@/api/covidcenter.js";

const covidCenterStore = {
  namespaced: true,
  state: {
    covidcenters: [],
    covidcenter: null,
  },

  getters: {},

  mutations: {
    SET_COVIDCENTER_LIST: (state, covidcenters) => {
      state.covidcenters = covidcenters;
    },
    SET_DETAIL_COVIDCENTER: (state, covidcenter) => {
      state.covidcenter = covidcenter;
    },
  },

  actions: {
    getCovidCenterList: ({ commit }) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_COVID_CENTER_API_KEY;

      const params = {
        serviceKey: decodeURIComponent(SERVICE_KEY),
        page: 1,
        perPage: 300,
      };

      covidcenterList(
        params,
        (response) => {
          // console.dir(response);
          commit("SET_COVIDCENTER_LIST", response.data.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default covidCenterStore;
