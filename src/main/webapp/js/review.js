document.addEventListener('DOMContentLoaded', function () {
    initPage();
});

const initPage = () => {
    this.displayInfoId = document.querySelector("#container").dataset.displayInfoId;
    this.queryString = "/" + this.displayInfoId;
   
    ajaxRequest.get(URL_PATH.API + URL_PATH.PRODUCT + this.queryString, reviewCallBack.displayComments.bind(reviewCallBack));
};