document.addEventListener("DOMContentLoaded", () => {
    initPage();
});

const initPage = () => {
    const reservationDateText = document.querySelector(".inline_txt.selected");
    const displayInfoId = document.querySelector("#container").dataset.displayInfoId;
    const queryString = "/" + displayInfoId;

    reservationDateText.innerHTML = document.querySelector("#container").dataset.reservationDate + reservationDateText.innerHTML;
    ajaxRequest.get(URL_PATH.API + URL_PATH.PRODUCT + queryString, composePage);
};

const composePage = (selectedDisplayInfo) => {
    const callBack = new CallBack(selectedDisplayInfo);

    callBack.displayDetailInfos();
    callBack.displayProductImage();
    callBack.displayPriceInfo();

    new ButtonEvent(selectedDisplayInfo).addButtonEvent();
};

function CallBack(selectedDisplayInfo) {
    // displayProductImage에서 사용
    this.productImageList = selectedDisplayInfo.productImages;
    this.productImageContainer = document.querySelector(".visual_img");
    
    // displayDetailInfos에서 사용
    this.displayInfo = selectedDisplayInfo.displayInfo;
    this.descriptionContainer = document.querySelector(".top_title .title");
    this.detailInfoContainer = document.querySelectorAll(".dsc");
    this.openingHourContainer = this.detailInfoContainer[1];
    this.priceInfoContainer = this.detailInfoContainer[2];

    // displayPriceInfo에서 사용
    this.priceContainer = document.querySelector(".ticket_body");

    // 공통으로 사용
    this.productPriceList = selectedDisplayInfo.productPrices;
}

CallBack.prototype.displayProductImage = function() {
    const htmlTemplate = reserveTemplate.productImage;
    const bindTemplate = Handlebars.compile(htmlTemplate);
    const thumnailImage = this.productImageList.filter((image) => {
        return (image.type === "th");
    })[0];

    let minPrice = this.productPriceList[0].price;
    this.productPriceList.forEach(product => {
        minPrice = (minPrice > product.price) ? product.price : minPrice;
    });

    const data = {
        productId: thumnailImage.productId,
        saveFileName: thumnailImage.saveFileName,
        minPrice: inputCommaIntoNumber(minPrice)
    };

    this.productImageContainer.innerHTML = bindTemplate(data);
};

CallBack.prototype.displayDetailInfos = function() {
    this.descriptionContainer.innerText = this.displayInfo.productDescription;
    this.openingHourContainer.innerText = this.displayInfo.openingHours;
    this.productPriceList.forEach(price => {
        this.priceInfoContainer.innerText += (price.priceTypeName + " : " + inputCommaIntoNumber(price.price) + "원\n");
    });
};

function PriceInfo(priceInfo) {
    let discountResult = (priceInfo.price * (100 - priceInfo.discountRate)) / 100;

    this.priceTypeName = priceInfo.priceTypeName;
    this.price = inputCommaIntoNumber(priceInfo.price);
    this.discountPrice = inputCommaIntoNumber(discountResult);
    this.discountRate = priceInfo.discountRate.toFixed(0);
    this.productPriceId = priceInfo.productPriceId;
};

CallBack.prototype.displayPriceInfo = function() {
    const htmlTemplate = reserveTemplate.priceInfo;
    const bindTemplate = Handlebars.compile(htmlTemplate);
    let resultHtml = "";

    this.productPriceList.forEach(priceInfo => {
        const data = new PriceInfo(priceInfo);
        resultHtml += bindTemplate(data);
    })

    this.priceContainer.innerHTML = resultHtml;
};

function ButtonEvent(displayInfo) {
    // addButtonEvent에서 사용
    this.showAgreementButtons = document.querySelectorAll(".btn_agreement");
    this.agreeAllButton = document.querySelector(".chk_agree");
    this.agreeAllWrapper = document.querySelector(".bk_btn_wrap.disable");
    this.inputTelephone = document.querySelector("#tel");
    this.reservationButton = document.querySelector(".bk_btn");
    this.ticketerInfoList = document.querySelectorAll("#name, #tel, #email");

    // priceButton에서 사용
    this.MIN_COUNT = 0;
    this.MAX_COUNT = 10;
    this.minusButtonList = document.querySelectorAll(".ico_minus3");
    this.plusButtonList = document.querySelectorAll(".ico_plus3");
    this.totalCountText = document.querySelector("#totalCount");
    this.priceInfoList = displayInfo.productPrices;

    this.phoneReg = /01[016789]-\d{3,4}-\d{4}$/;
    this.emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
};

ButtonEvent.prototype.addButtonEvent = function () {
    this.showAgreementButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();

            let agreementContext = button.parentElement;
            if (agreementContext.classList.contains("open")) {
                agreementContext.classList.remove("open");
                button.firstElementChild.innerText = "보기";
                button.lastElementChild.classList.replace("fn-up2", "fn-down2");
            } else {
                agreementContext.classList.add("open");
                button.firstElementChild.innerText = "접기";
                button.lastElementChild.classList.replace("fn-down2", "fn-up2");
            }
        });
    });

    this.inputTelephone.addEventListener("change", () => {
        const warningMsg = this.inputTelephone.nextElementSibling;
        const isValid = this.phoneReg.test(this.inputTelephone.value);

        if (!isValid) {
            this.inputTelephone.value = "";
            warningMsg.style.visibility = "visible";
            setTimeout(() => {
                warningMsg.style.visibility = "hidden";
            }, 2 * 1000);
        }
    })

    this.agreeAllButton.addEventListener("change", () => {
        if (this.agreeAllWrapper.classList.contains("disable")) {
            this.agreeAllWrapper.classList.remove("disable");
        } else {
            this.agreeAllWrapper.classList.add("disable");
        }
    });

    this.reservationButton.addEventListener("click", () => {
        if (this.reservationButton.classList.contains("disable")) {
            return;
        }

        if (!this.isReservationValid(this.ticketerInfoList)) {
            return;
        }

        this.addReservation();
    });

    this.minusButtonList.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();

            const countContainer = button.nextElementSibling;
            const oppositeButton = button.parentElement.lastElementChild;
            const totalPriceText = button.parentElement.nextElementSibling.firstElementChild;

            const price = this.priceInfoList.filter(info => { 
                return (info.productPriceId === parseInt(countContainer.dataset.productPriceId));
            })[0].price;
            let currentCount = parseInt(countContainer.value);

            if (currentCount <= this.MIN_COUNT) {
                return;
            }

            countContainer.value = --currentCount;
            totalPriceText.innerText = inputCommaIntoNumber(price * currentCount);
            this.totalCountText.innerText = parseInt(this.totalCountText.innerText) - 1;

            if (currentCount === this.MAX_COUNT - 1) {
                oppositeButton.classList.remove("disabled");
            }

            if (currentCount <= this.MIN_COUNT) {
                button.classList.add("disabled");
            }
        });
    });

    this.plusButtonList.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();

            const countContainer = button.previousElementSibling;
            const oppositeButton = button.parentElement.firstElementChild;
            const totalPriceText = button.parentElement.nextElementSibling.firstElementChild;

            const price = this.priceInfoList.filter(info => {
                return (info.productPriceId === parseInt(countContainer.dataset.productPriceId));
            })[0].price;
            let currentCount = parseInt(countContainer.value);

            if (currentCount >= this.MAX_COUNT) {
                return;
            }

            countContainer.value = ++currentCount;
            totalPriceText.innerText = inputCommaIntoNumber(price * currentCount);
            this.totalCountText.innerText = parseInt(this.totalCountText.innerText) + 1;

            if (currentCount === this.MIN_COUNT + 1) {
                oppositeButton.classList.remove("disabled");
            }

            if (currentCount >= this.MAX_COUNT) {
                button.classList.add("disabled");
            }
        });
    });
};

ButtonEvent.prototype.isReservationValid = function (ticketerInfoList) {
    for (let index in ticketerInfoList) {
        if (ticketerInfoList[index].value === "") {
            alert("예매자 정보를 모두 입력해주세요.")
            return false;
        }
    }

    if(!this.emailReg.test(ticketerInfoList[2].value)) {
        alert("이메일 형식이 잘못되었습니다.");
        return false;
    }

    const totalCount = parseInt(document.querySelector("#totalCount").innerText);
    if (totalCount === 0) {
        alert("티켓을 1매 이상 선택해주세요.");
        return false;
    }

    return true;
};

function ReservationInfo(priceList) {
    const currentDate = new Date(document.querySelector("#container").dataset.reservationDate);

    this.displayInfoId = document.querySelector("#container").dataset.displayInfoId;
    this.productId = document.querySelector(".img_thumb").dataset.productId;
    this.reservationName = document.querySelector("#name").value;
    this.reservationTelephone = document.querySelector("#tel").value;
    this.reservationEmail = document.querySelector("#email").value;
    this.reservationYearMonthDay = dateFormatter(currentDate);
    this.prices = priceList;
};

ButtonEvent.prototype.addReservation = function () {
    const priceInfoList = document.querySelectorAll(".count_control_input");
    let priceList = [];

    priceInfoList.forEach(info => {
        if (info.value !== "0") {
            let priceResult = {};
            priceResult.productPriceId = info.dataset.productPriceId;
            priceResult.count = info.value;
            priceResult.reservationInfoId = 0;
            priceResult.reservationPriceInfoId = 0;
            priceList.push(priceResult);
        }
    });

    const reservationInfo = new ReservationInfo(priceList);
    const param = JSON.stringify(reservationInfo);
    ajaxRequest.post(URL_PATH.API + URL_PATH.RESERVATION, param, CONTENT_TYPE.JSON, function() {
        alert("예약이 완료되었습니다.");
        location.reload(true);
    });
    // ajaxRequest.post(URL_PATH.API + URL_PATH.RESERVATION, reservationInfo);
};

const reserveTemplate = {
    productImage:
        `<li class="item" style="width: 414px;"> <img alt="공연이미지" class="img_thumb" src="{{saveFileName}}" data-product-id="{{productId}}"> <span class="img_bg"></span>
                <div class="preview_txt">
                    <h2 class="preview_txt_tit"></h2> <em class="preview_txt_dsc">₩{{minPrice}}~ </em><em class="preview_txt_dsc">2019. 7. 1(월) ~ 2019. 8. 30(금), 잔여티켓 2769매</em> 
                </div>
            </li>`,

    priceInfo:
        `<div class="qty">
                <div class="count_control">
                    <div class="clearfix">
                        <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"></a> 
                        <input type="tel" class="count_control_input" value="0" readonly title="수량" data-product-price-id="{{productPriceId}}">
                        <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
                    </div>
                    <div class="individual_price on_color"><span class="total_price">0</span><span class="price_type">원</span></div>
                </div>
                <div class="qty_info_icon"> 
                    <strong class="product_amount"> <span>{{priceTypeName}}</span> </strong> 
                    <strong class="product_price"> <span class="price">{{price}}</span> <span class="price_type">원</span> </strong> 
                    <em class="product_dsc">{{discountPrice}}원 ({{discountRate}}% 할인가)</em> 
                </div>
            </div>`
}