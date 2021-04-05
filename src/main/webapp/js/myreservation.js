document.addEventListener("DOMContentLoaded", () => {
    initPage();
});

const initPage = () => {
    const reservationEmail = getCookie("reservationEmail");
    const queryString = "?reservationEmail=" + reservationEmail;

    ajaxRequest.get(URL_PATH.API + URL_PATH.RESERVATION + queryString, composePage);
};

const composePage = (reservationInfo) => {
    const constructPage = new ConstructPage(reservationInfo);
    
    constructPage.displayReservationList();
    constructPage.displaySummary();
    constructPage.addButtonEvent();
    viewReservation();
};

function ConstructPage(reservationInfo) {
    this.TOTAL = 0;
    this.CONFIRMED = 1;
    this.USED = 2;
    this.CANCELED = 3;

    // displayReservationList에서 사용
    this.confirmedList = document.querySelector(".wrap_mylist .confirmed");
    this.usedList = document.querySelector(".wrap_mylist .used");
    this.canceledList = document.querySelector(".wrap_mylist .cancel");
    this.reservationList = reservationInfo.reservations;
    this.reservationCount = reservationInfo.size;

    // displaySummary에서 사용
    this.summaryCountList = document.querySelectorAll(".link_summary_board .figure");
    this.confirmCount = 0;
    this.usedCount = 0;
    this.cancelCount = 0;
    this.countList = [0, 0, 0, 0];

    this.summaryHeaderList = document.querySelectorAll(".link_booking_details");
    this.noList = document.querySelector(".err");
};

/**
 * 템플릿용 예약 정보 생성자
 * @param {*} reservation 
 */
function ReservationInfo(reservation) {
    this.reservationInfoId = reservation.reservationInfoId;
    this.categoryName = reservation.displayInfo.categoryName;
    this.reservationDate = dateFormatter(new Date(reservation.reservationDate));
    this.productId = reservation.productId;
    this.productDescription = reservation.displayInfo.productDescription;
    this.placeName = reservation.displayInfo.placeName;
    this.totalPrice = inputCommaIntoNumber(reservation.totalPrice);
};

ConstructPage.prototype.displayReservationList = function() {
    if (this.reservationCount === 0) {
        this.summaryHeaderList.forEach(listHeader => {
            listHeader.style.display = "none";
        })
        this.noList.style.display = "block";
        return;
    }

    const currentDate = new Date();
    const bindTemplate = Handlebars.compile(myReservationTemplate.cardItem);

    this.reservationList.forEach(reservation => {
        let data = new ReservationInfo(reservation);

        if (reservation.cancelFlag === true) {
            data.type = "cancel";
            this.canceledList.innerHTML += bindTemplate(data);
            return;
        }

        const reservationDate = new Date(reservation.reservationDate);

        if (reservationDate > currentDate) {
            data.type = "confirmed";
            this.confirmedList.innerHTML += bindTemplate(data);
        } else {
            data.type = "used";
            this.usedList.innerHTML += bindTemplate(data);
        }
    });

    this.changeUrl();
};

ConstructPage.prototype.displaySummary = function() {
    this.countList[this.CONFIRMED] = this.confirmedList.querySelectorAll("article").length;
    this.countList[this.USED] = this.usedList.querySelectorAll("article").length;
    this.countList[this.CANCELED] = this.canceledList.querySelectorAll("article").length;
    
    this.summaryCountList[this.CONFIRMED].innerText = this.countList[this.CONFIRMED];
    this.summaryCountList[this.USED].innerText = this.countList[this.USED];
    this.summaryCountList[this.CANCELED].innerText = this.countList[this.CANCELED];

    this.countList[this.TOTAL] = (this.countList[this.CONFIRMED] + this.countList[this.USED] + this.countList[this.CANCELED]);
    this.summaryCountList[this.TOTAL].innerText = this.countList[this.TOTAL];
};

ConstructPage.prototype.addButtonEvent = function() {
    const summaryButtons = document.querySelectorAll(".link_summary_board");
    const cancelButtons = document.querySelectorAll(".card.confirmed .btn");

    summaryButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();

            if (button.classList.contains("on")) {
                return;
            }

            const prevButton = document.querySelector(".link_summary_board.on");

            button.classList.add("on");
            prevButton.classList.remove("on");
        });
    });

    cancelButtons.forEach(button => {
        button.addEventListener("click", () => {
            const isCancel = confirm("해당 예약을 취소하시겠습니까?");

            if (!isCancel) {
                return;
            }

            this.confirmCount--;
            this.cancelCount++;

            const reservationInfoId = button.parentNode.parentNode.dataset.reservationInfoId;
            const queryString = "/" + reservationInfoId;

            ajaxRequest.put(URL_PATH.API + URL_PATH.RESERVATION + queryString, () => {
                button.style.display = "none";
                const changeNode = document.getElementById(reservationInfoId);
                this.canceledList.appendChild(changeNode);
                alert("예약이 취소되었습니다.");
                this.displaySummary();
            });
        });
    });
};

ConstructPage.prototype.changeUrl = function() {
    const reviewButtons = this.usedList.querySelectorAll(".booking_cancel .link_booking_details");
    const infoIdList = this.usedList.querySelectorAll(".card_detail");
    const len = reviewButtons.length;
    
    for(var index = 0; index < len; index++) {
        const button = reviewButtons[index];
        const reservationInfoId = infoIdList[index].dataset.reservationInfoId;
        const productId = infoIdList[index].dataset.productId;

        button.nextElementSibling.href += ("?reservationInfoId=" + reservationInfoId + "&productId=" + productId);
    }
};

const myReservationTemplate = {
    cardItem: 
            `<article class="card_item" id={{reservationInfoId}}>
                <a href="#" class="link_booking_details">
                    <div class="card_body">
                        <div class="left"></div>
                        <div class="middle">
                            <div class="card_detail" data-reservation-info-id={{reservationInfoId}} data-product-id={{productId}}>
                                <em class="booking_number">No.{{reservationInfoId}}</em>
                                <h4 class="tit">{{categoryName}}/{{productDescription}}</h4>
                                <ul class="detail">
                                    <li class="item">
                                        <span class="item_tit">일정</span>
                                        <em class="item_dsc">
                                            {{reservationDate}}
                                        </em>
                                    </li>
                                    <li class="item">
                                        <span class="item_tit">내역</span>
                                        <em class="item_dsc">
                                            {{productDescription}}
                                        </em>
                                    </li>
                                    <li class="item">
                                        <span class="item_tit">장소</span>
                                        <em class="item_dsc">
                                            {{placeName}}
                                        </em>
                                    </li>
                                    <li class="item">
                                        <span class="item_tit">업체</span>
                                        <em class="item_dsc">
                                            더 페스타
                                        </em>
                                    </li>
                                </ul>
                                <div class="price_summary">
                                    <span class="price_tit">결제 예정금액</span>
                                    <em class="price_amount">
                                        <span>{{totalPrice}}</span>
                                        <span class="unit">원</span>
                                    </em>
                                </div>
                                {{#classifyType type}}
                                    {{type}}
                                {{/classifyType}}
                            </div>
                        </div>
                        <div class="right"></div>
                    </div>
                    <div class="card_footer">
                        <div class="left"></div>
                        <div class="middle"></div>
                        <div class="right"></div>
                    </div>
                </a>
                <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
            </article>`
}

Handlebars.registerHelper("classifyType", (type) => {
    if (type === "cancel") {
        return;
    }

    if (type === "confirmed") {
        return `<div class="booking_cancel">
                    <button class="btn"><span>취소</span></button>
                </div>`;
    } else { // case : used
        return `<div class="booking_cancel">
                    <a href="reviewWrite"><button class="btn"><span>예매자 리뷰 남기기</span></button></a>
                </div>`;
    }
});