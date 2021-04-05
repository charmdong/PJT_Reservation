const HTTP_METHOD = {
    GET: "GET",
    POST: "POST",
    PUT: "PUT"
};

const URL_PATH = {
    API: "./api",
    CATEGORY: "/categories",
    PRODUCT: "/products",
    PROMOTION: "/promotions",
    RESERVATION: "/reservations"
};

const CONTENT_TYPE = {
    FORM_DATA: 'multipart/form-data',
    JSON: 'application/json;',
    HTML_FORM: 'application/x-www-form-urlencoded;'
};

const ajaxRequest = {
    STATUS_SUCCESS : 200,

    post(url, param, contentType, callBack) { 
        let request = new XMLHttpRequest();

        request.open(HTTP_METHOD.POST, url, true);
        request.setRequestHeader('Content-type', contentType);
        request.onreadystatechange = function () {
            if (this.readyState !== XMLHttpRequest.DONE) {
                return;
            }
    
            if (this.status === ajaxRequest.STATUS_SUCCESS) {
                callBack();
            } else {
                alert("Error code : " + this.status);
            }
        }

        if (param !== null) {
            request.send(param);
        }
    },

    get(url, callBack) {
        if(typeof (callBack) !== "function") {
            alert("The type of callback is not 'function'");
            return;
        }

        let request = new XMLHttpRequest();

        request.open(HTTP_METHOD.GET, url, true);
        request.onreadystatechange = function () {
            if (this.readyState !== XMLHttpRequest.DONE) {
                return;
            }

            if (this.status === ajaxRequest.STATUS_SUCCESS) {
                let parsedData = JSON.parse(this.response);
                callBack(parsedData);
            } else {
                alert("Error code : " + this.status); 
            }
        }

        request.send();
    },

    put(url, callBack) {
        let request = new XMLHttpRequest();

        request.open(HTTP_METHOD.PUT, url, true);
        request.onreadystatechange = function () {
            if (this.readyState !== XMLHttpRequest.DONE) {
                return;
            }

            if (this.status === ajaxRequest.STATUS_SUCCESS) {
                callBack();
            } else {
                alert("Error code : " + this.status); 
            }
        }

        request.send();
    }
};

const reviewCallBack = {
    /**
     * 평점
     * @param selectedDisplayInfo 
     */
    _displayScore(selectedDisplayInfo) {
        let averageScore = selectedDisplayInfo.averageScore;
        let scoreGraph = document.querySelector(".graph_value");
        let scoreText = document.querySelector(".text_value span");
        let commentCountContainer = document.querySelector(".green");

        scoreGraph.style.width = (averageScore / 5 * 100).toFixed(0) + "%";
        scoreText.innerText = averageScore.toFixed(1);
        commentCountContainer.innerText = selectedDisplayInfo.commentCount + "건";
    },

    /**
     * 예매자 한줄평
     * @param selectedDisplayInfo
     * @param isTotal
     */
    displayComments(selectedDisplayInfo) {
        let commentList = selectedDisplayInfo.comments;
        let commentContainer = document.querySelector(".list_short_review");

        const htmlTemplate = commonTemplate.comment;
        const bindTemplate = Handlebars.compile(htmlTemplate);

        this._displayScore(selectedDisplayInfo);

        commentList.forEach(comment => {
            let date = new Date(comment.createDate);
            let commentDate = date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".";

            comment.createDate = commentDate;
            comment.score = comment.score.toFixed(1);
        })

        let resultHtml = commentList.reduce((prev, comment) => {
            return prev + bindTemplate(comment);
        }, "");

        commentContainer.innerHTML = resultHtml;
    }
}

const viewReservation = () => {
    const reservationEmail = getCookie("reservationEmail");
    const viewReservation = document.querySelector(".viewReservation");

    if(reservationEmail !== "") {
        viewReservation.innerText = reservationEmail;
    }
};

const getCookie = (cookieName) => {
    let name = cookieName + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}

const inputCommaIntoNumber = (data) => {
    return Number(data).toLocaleString();
};

const dateFormatter = (date) => {
    let year = date.getFullYear();
    let month = (date.getMonth() + 1);
    month = month >= 10 ? month : ('0' + month);
    let day = date.getDate();
    day = day >= 10 ? day : ('0' + day);
    return year + '.' + month + '.' + day;
};

const commonTemplate = {
    comment:
        `<li class="list_item">
            <div>
            {{#if commentImages}}
                {{#displayCommentImage commentImages}}
                    {{commentImages}}
                {{/displayCommentImage}}                                        
            {{else}}
                <div class="review_area no_img">
            {{/if}}
                <h4 class="resoc_name"></h4>
                <p class="review">{{comment}}</p>
                </div>
                <div class="info_area">
                    <div class="review_info">
                        <span class="grade">{{score}}</span>
                        <span class="name">{{reservationEmail}}</span>
                        <span class="date">{{createDate}} 방문</span>
                    </div>
                </div>
            </div>
        </li>`
}

Handlebars.registerHelper("displayCommentImage", (commentImageList) => {
    const commentImageUrl = commentImageList[0].saveFileName;
    const imageCount = commentImageList.length;

    return `<div class="review_area">
                <div class="thumb_area">
                    <a href="#" class="thumb" title="이미지 크게 보기"> 
                        <img width="90" height="90" class="img_vertical_top" src="` + commentImageUrl + `" alt="리뷰이미지">
                    </a>
                    <span class="img_count">` + imageCount + `</span>
                </div>`;
});