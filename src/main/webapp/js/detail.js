document.addEventListener("DOMContentLoaded", () => {
    const COMMENT_LIMIT = 3;
    const displayInfoId = document.querySelector("#container").dataset.displayInfoId;
    const queryString = "/" + displayInfoId + "?limit=" + COMMENT_LIMIT;
    
    ajaxRequest.get(URL_PATH.API + URL_PATH.PRODUCT + queryString, executeCallBack);
    addButtonEvent();
    viewReservation();
})

/**
 * 콜백 함수 호출
 * @param selectedDisplayInfo 
 */
const executeCallBack = (selectedDisplayInfo) => {
    productCallBack.displayProductInfos(selectedDisplayInfo);
    displayDetailInfos(selectedDisplayInfo);
    reviewCallBack.displayComments(selectedDisplayInfo);
}

const productCallBack = {
    /**
     * 배경 이미지 및 소개글
     * @param selectedDisplayInfo 
     */
    displayProductInfos(selectedDisplayInfo) {
        const productImageList = selectedDisplayInfo.productImages;
        let productDescription = selectedDisplayInfo.displayInfo.productDescription;
        let productImageContainer = document.querySelector(".visual_img.detail_swipe");
        let productImageCountText = document.querySelector(".num.off span");

        const htmlTemplate = detailTemplate.productImage;
        const bindTemplate = Handlebars.compile(htmlTemplate);

        let mainImage = productImageList.filter((image) => {
            return (image.type === "ma");
        })[0];

        let etcImage = productImageList.filter((image) => {
            return (image.type === "et");
        })[0];

        let infoImageList = [mainImage];
        let imageCount = infoImageList.length;

        if (etcImage !== undefined) {
            infoImageList.push(etcImage);
            infoImageList.push(mainImage);
            imageCount++;
        }

        infoImageList.forEach(infoImage => {
            infoImage.productDescription = productDescription;
        })

        let resultHtml = infoImageList.reduce((prev, image) => {
            return prev + bindTemplate(image);
        }, "");

        productImageCountText.innerText = imageCount;
        productImageContainer.innerHTML = resultHtml;
        this._displayProductContent(selectedDisplayInfo);
    },

    /**
     * 상품 content 출력
     * @param selectedDisplayInfo 
     */
    _displayProductContent(selectedDisplayInfo) {
        let productContent = selectedDisplayInfo.displayInfo.productContent;
        let productContentContainer = document.querySelector(".store_details .dsc");

        productContentContainer.innerText += productContent;
    }
}

/**
 * 상세 정보, 오시는 길
 * @param selectedDisplayInfo 
 */
const displayDetailInfos = (selectedDisplayInfo) => {
    let detailInfoContainer = document.querySelector(".detail_info .in_dsc");
    let locationContainer = document.querySelector(".box_store_info.no_topline");
    let displayInfo = selectedDisplayInfo.displayInfo;

    const htmlTemplate = detailTemplate.location;
    const bindTemplate = Handlebars.compile(htmlTemplate);

    displayInfo.displayInfoImageUrl = selectedDisplayInfo.displayInfoImage.saveFileName;

    detailInfoContainer.innerText += selectedDisplayInfo.displayInfo.productContent;
    locationContainer.innerHTML = bindTemplate(displayInfo) + locationContainer.innerHTML;
}

/**
 * 버튼 이벤트 등록
 */
const addButtonEvent = () => {
    const openButton = document.querySelector(".bk_more._open");
    const closeButton = document.querySelector(".bk_more._close");
    const slideButtons = document.querySelectorAll(".btn_prev, .btn_nxt");
    const tabButtons = document.querySelectorAll(".anchor");

    openButton.addEventListener("click", (event) => {
        event.preventDefault();
        openButton.style.display = "none";
        closeButton.style.display = "block";
        document.querySelector(".store_details").classList.remove("close3");
    });

    closeButton.addEventListener("click", (event) => {
        event.preventDefault();
        document.querySelector(".store_details").classList.add("close3");
        closeButton.style.display = "none";
        openButton.style.display = "block";
    });

    slideButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();
            slideImageController.slideImage(event);
        })
    })

    tabButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();
            changeTab(button);
        });
    })
}

/**
 * 탭 변경
 * @param currentTab 
 */
const changeTab = (currentTab) => {
    const activeTab = document.querySelector(".anchor.active");
    if (currentTab === activeTab) {
        return;
    }

    const detailTab = document.querySelector(".item.active._detail");
    let prevTab = document.querySelector(".anchor.active");
    let prevWrapper;
    let currentWrapper;

    prevTab.classList.remove("active");
    currentTab.classList.add("active");

    if (currentTab.parentNode === detailTab) {
        prevWrapper = document.querySelector(".detail_location");
        currentWrapper = document.querySelector(".detail_area_wrap.hide");
    } else {
        prevWrapper = document.querySelector(".detail_area_wrap");
        currentWrapper = document.querySelector(".detail_location.hide");
    }

    prevWrapper.classList.add("hide");
    currentWrapper.classList.remove("hide");
}

const slideImageController = {
    LEN: document.querySelector(".container_visual").clientWidth,
    count: 1,

    /**
     * 배경 이미지 슬라이드
     * @param direction
     */
    slideImage(event) {
    const currentButton = event.target;
        const nextButton = document.querySelector(".btn_nxt");
        const isNext = ((currentButton === nextButton) || (currentButton.parentNode === nextButton));

        let pageContainer = document.querySelector(".figure_pagination");
        let totalPage = parseInt(pageContainer.querySelector(".num.off span").innerText);
        let currentPage = parseInt(pageContainer.querySelector(".num").innerText);

        if (currentPage === 1 && !isNext) {
            return;
        }

        if (totalPage === 1 && isNext) {
            return;
        }

        const productImageList = document.querySelectorAll(".visual_img.detail_swipe .item");
        const translateLen = isNext ? this.count : this.count - 2;

        this._setImageStyle(productImageList, 1, (translateLen * this.LEN));

        if (currentPage === totalPage && isNext) {
            setTimeout(() => {
                this._setImageStyle(productImageList, 0, 0);
            }, 1 * 1000);
            this.count = 0;
        }
        this.count += isNext ? 1 : -1;

        this._displayPageNumber(pageContainer, currentPage, isNext);
    },

    /**
     * 이미지 스타일 설정
     * @param productImageList 
     * @param second 
     * @param translateLen 
     */
    _setImageStyle(productImageList, second, translateLen) {
        productImageList.forEach(productImage => {
            productImage.style.transition = "transform " + second + "s";
            productImage.style.transform = "translateX(-" + translateLen + "px)";
        });
    },

    /**
     * 현재 이미지 순서 출력
     * @param pageContainer 
     * @param isNext 
     */
    _displayPageNumber(pageContainer, currentPage, isNext) {
        const TOTAL_PAGE = 2;

        currentPage = isNext ? ++currentPage : --currentPage;
        if (currentPage > TOTAL_PAGE) {
            currentPage %= TOTAL_PAGE;
        }

        pageContainer.querySelector(".num").innerText = currentPage;
        this._changeButtonStatus(currentPage);
    },

    /**
     * 버튼 상태 변경
     * @param firstPage 
     */
    _changeButtonStatus(currentPage) {
        let prevButton = document.querySelector(".spr_book2.ico_arr6_lt");

        if (currentPage !== 1) {
            prevButton.classList.remove("off");
        } else {
            prevButton.classList.add("off");
        }
    }
}

const detailTemplate = {
    productImage:
            `<li class="item" style="width: 414px; height: 414px;">
                <img alt="공연이미지" class="img_thumb" src="{{saveFileName}}">
                    <span class="img_bg"></span>
                    <div class="visual_txt">
                        <div class="visual_txt_inn">
                            <h2 class="visual_txt_tit"><span>{{productDescription}}</span></h2>
                            <p class="visual_txt_dsc"></p>
                        </div>
                    </div>
            </li>`,

        location:
            `<a href="#" class="store_location" title="지도웹으로 연결">
                <img class="store_map img_thumb" alt="map" src={{displayInfoImageUrl}}>
                <span class="img_border"></span>
                <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
            </a>
                <h3 class="store_name">엔에이치엔티켓링크(주)</h3>
                <div class="store_info">
                    <div class="store_addr_wrap"><span class="fn fn-pin2"></span>
                        <p class="store_addr store_addr_bold">{{placeStreet}}</p>
                        <p class="store_addr">
                            <span class="addr_old">지번</span>
                            <span class="addr_old_detail">{{placeLot}}</span>
                        </p>
                        <p class="store_addr addr_detail">{{placeName}}</p>
                    </div>
                    <div class="lst_store_info_wrap">
                        <ul class="lst_store_info">
                            <li class="item">
                                <span class="item_lt">
                                    <i class="fn fn-call2"></i><span class="sr_only">전화번호</span>
                                </span> 
                                <span class="item_rt">
                                    <a href="tel:{{telephone}}" class="store_tel">{{telephone}}</a>
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>`
}