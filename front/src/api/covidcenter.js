import { covidInstance } from "./index.js";

const covidcenter = covidInstance();

function covidcenterList(params, success, fail) {
  covidcenter.get(``, { params: params }).then(success).catch(fail);
}

export { covidcenterList };
