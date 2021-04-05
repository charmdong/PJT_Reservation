document.addEventListener('DOMContentLoaded', function () {
    let showMore = document.querySelector(".btn");
    showMore.addEventListener("click", getMoreInfos);

    ajaxRequest.get(URL_PATH.API + URL_PATH.PROMOTION, displayPromotions);
    ajaxRequest.get(URL_PATH.API + URL_PATH.CATEGORY, displayCategories);
    sendProductRequest();
    viewReservation();
});

// product.js
/**
 * 상품 정보 관련 ajax통신
 * @param categoryId 
 */
function sendProductRequest(categoryId = 0) {
    let currentDisplayCnt = document.querySelectorAll(".lst_event_box .item").length;
    let queryString = "?start=" + currentDisplayCnt + "&categoryId=" + categoryId;

    ajaxRequest.get(URL_PATH.API + URL_PATH.PRODUCT + queryString, displayProducts);
}

/**
 * 더보기 - 활성화된 카테고리 관련 공연 정보
 */
function getMoreInfos() {
    let currentCategoryId = document.querySelector(".anchor.active").parentNode.dataset.category;

    sendProductRequest(currentCategoryId);
}

/**
 * 해당 카테고리 관련 공연 정보 추가 제공
 * @param productInfoList 
 * @param currentDisplayCnt 
 */
function displayProducts(productInfoList) {
    let productList = productInfoList.items;
    let totalDisplayCnt = productInfoList.totalCount;
    let currentDisplayCnt = document.querySelectorAll(".lst_event_box .item").length;
    let productContainers = document.querySelectorAll(".lst_event_box");
    let displayText = document.querySelector(".pink");
    let htmlTemplate = mainTemplate.item;
    let listLength = productList.length;

    currentDisplayCnt += listLength;
    displayText.innerText = totalDisplayCnt + "개";

    for (var index = 0; index < listLength; index++) {
        let product = productList[index];
        let resultHtml = htmlTemplate.replace("{displayInfoId}", product.displayInfoId)
            .replace(/{description}/g, product.description)
            .replace("{imageUrl}", product.productImageUrl)
            .replace("{placeName}", product.placeName)
            .replace("{content}", product.content);

        productContainers[index % 2].innerHTML += resultHtml;
    }

    changeButtonStatus(currentDisplayCnt, totalDisplayCnt);
}

/**
 * 더보기 버튼 display 상태 변경
 * @param currentDisplayCnt 
 * @param totalDisplayCnt 
 */
function changeButtonStatus(currentDisplayCnt, totalDisplayCnt) {
    let button = document.querySelector(".btn");

    if (currentDisplayCnt >= totalDisplayCnt) {
        button.style.display = "none";
        return;
    }

    if (button.style.display === "none") {
        button.style.display = "inline-block";
    }
}


// promotion.js
/**
 * 프로모션 이미지 로드
 * @param promotionList 
 */
function displayPromotions(dataList) {
    let htmlTemplate = mainTemplate.promotion;
    let promotionContainer = document.querySelector(".visual_img");
    let promotionList = dataList.items;
    let resultHtml = "";

    promotionList.forEach(promotion => {
        resultHtml += htmlTemplate.replace("{filePath}", promotion.productImageUrl);
    });

    resultHtml += htmlTemplate.replace("{filePath}", promotionList[0].productImageUrl);
    promotionContainer.innerHTML += resultHtml;

    let promotionImageList = promotionContainer.querySelectorAll(".visual_img .item");
    let listLength = promotionImageList.length;

    setTimeout(() => {
        slidePromotions(promotionImageList, listLength);
    }, 2 * 1000);
}

let count = 0;
const imgWidth = document.querySelector(".visual_img").clientWidth;
/**
 * 프로모션 슬라이드
 * @param promotionImageList 
 * @param listLength 
 */
function slidePromotions(promotionImageList, listLength) {
    let isLast = (count >= listLength - 1);
    let transformSecond;
    let translateLen;
    let second;

    if (isLast) {
        transformSecond = "0s";
        translateLen = 0;
        second = 0;
        count = -1;
    } else {
        transformSecond = "1.5s";
        translateLen = imgWidth * (count + 1);
        second = 3;
    }

    promotionImageList.forEach(promotionImage => {
        promotionImage.style.transition = "transform " + transformSecond;
        promotionImage.style.transform = "translateX(-" + translateLen + "px)";
    })

    count++;

    setTimeout(() => {
        slidePromotions(promotionImageList, listLength);
    }, second * 1000);
}


// category.js
/**
 * 카테고리 로드
 * @param categoryList 
 */
function displayCategories(dataList) {
    let htmlTemplate = mainTemplate.category;
    let categoryContainer = document.querySelector(".event_tab_lst");
    let categoryList = dataList.items;
    let resultHtml = "";

    categoryList.forEach(category => {
        resultHtml += htmlTemplate.replace("{id}", category.id)
            .replace("{name}", category.name);
    })

    categoryContainer.innerHTML += resultHtml;
    addCategoryEvent();
}

/**
 * 카테고리 eventListener 추가
 */
function addCategoryEvent() {
    let tabList = document.querySelectorAll(".section_event_tab span");
    tabList.forEach(tab => {
        tab.addEventListener("click", changeTab);
    });
}

/**
 * 탭 변경
 * @param event 
 */
function changeTab(event) {
    let prevCategory = document.querySelector(".anchor.active");
    let currentCategory = event.target.parentNode;
    let currentCategoryId = currentCategory.parentNode.dataset.category;

    prevCategory.classList.remove("active");
    currentCategory.classList.add("active");

    removeDisplayList();
    sendProductRequest(currentCategoryId);
}

/**
 * 선택된 카테고리 공연 정보 제공을 위한 이전 카테고리 공연 정보 제거
 */
function removeDisplayList() {
    let displayList = document.querySelectorAll(".lst_event_box");

    displayList.forEach(event => {
        event.innerHTML = "";
    })
}

const mainTemplate = {
    promotion:
            `<li class="item" style="background-image: url({filePath}); width: 100%;">
                <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                    <div class="event_txt">
                        <h4 class="event_txt_tit"></h4>
                        <p class="event_txt_adr"></p>
                        <p class="event_txt_dsc"></p>
                    </div>
                </a>
            </li>`,

        item:
            `<li class="item">
                <a href="detail?displayInfoId={displayInfoId}" class="item_book">
                    <div class="item_preview">
                        <img alt="{description}" class="img_thumb" src="{imageUrl}">
                        <span class="img_border"></span>
                    </div>
                    <div class="event_txt">
                        <h4 class="event_txt_tit"> <span>{description}</span> <small class="sm">{placeName}</small> </h4>
                        <p class="event_txt_dsc">{content}</p>
                    </div>
                </a>
            </li>`,

        category:
            `<li class="item" data-category="{id}">
                <a class="anchor"> <span>{name}</span> </a>
            </li>`
}