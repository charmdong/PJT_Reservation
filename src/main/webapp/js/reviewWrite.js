document.addEventListener("DOMContentLoaded", () => {
    reviewWritePageController.initPage();
});

const reviewWritePageController = {
    checkList: document.querySelectorAll(".rating_rdo"),
    score: document.querySelector(".star_rank"),
    writeInfo: document.querySelector(".review_write_info"),
    textArea: document.querySelector(".review_textarea"),
    textLen: document.querySelector(".guide_review > span"),
    thumbList: document.querySelector(".lst_thumb li"),
    deleteButton: document.querySelector(".spr_book.ico_del"),
    inputImage: document.querySelector("#reviewImageFileOpenInput"),
    formContainer: document.querySelector(".review_content"),
    reviewRegister: document.querySelector(".bk_btn"),
    LIMIT: 400,

    initPage() {
        this.addButtonEvent();
    },

    addButtonEvent() {
        // 별점
        this.checkList.forEach(check => {
            check.addEventListener("click", () => {
                const value = check.value;
                
                this.checkList.forEach(check => {
                    check.checked = false;
                });

                for(var index = 0; index < value; index++) {
                    this.checkList[index].checked = true;
                }

                this.score.innerText = value;
                this.score.classList.remove("gray_star");
            });
        });

        // Input Review
        this.writeInfo.addEventListener("click", () => {
            this.writeInfo.style.visibility = "hidden";
            this.textArea.focus();
        });

        this.textArea.addEventListener("focusout", () => {
            if(this.textArea.value.length === 0) {
                this.writeInfo.style.visibility = "visible";
            }
        });

        this.textArea.addEventListener("keyup", () => {
            if(this.textArea.value.length >= this.LIMIT) {
                this.textArea.value = this.textArea.value.slice(0, this.LIMIT);
                this.textLen.innerText = this.LIMIT;
                return;
            }

            this.textLen.innerText = this.textArea.value.length;
        });

        // 사진 등록 - 1장만
        this.inputImage.addEventListener("change", (event) => {
            this.addImage(event);
        });

        this.deleteButton.addEventListener("click", () => {
            this.thumbList.style.display = "none";
            this.thumbList.innerHTML = "";
        });

        // 리뷰 등록 
        this.reviewRegister.addEventListener("click", (event) => {
            event.preventDefault(); 
            
            if (!this.validReviewContext()) {
                return;
            }
            
            const infoId = document.querySelector("#container");
            const reservationInfoId = infoId.dataset.reservationInfoId;
            const queryString = "/" + reservationInfoId + "/comments";

            const param = {
                reservationInfoId: reservationInfoId,
                productId: infoId.dataset.productId,
                comment: this.textArea.value,
                score: this.score.innerText
            };

            const formData = new FormData(this.formContainer); // TODO: 파일 날리기

            ajaxRequest.post(URL_PATH.API + URL_PATH.RESERVATION + queryString, JSON.stringify(param), CONTENT_TYPE.JSON, function() {
                alert("리뷰가 등록되었습니다.");
                window.location.href = "myreservation";
            });
        });
    },

    addImage(event){
        const image = event.target.files[0];
        const imageUrl = window.URL.createObjectURL(image);
        
        if(!this.validImage(image)) {
            return;
        }

        document.querySelector(".item_thumb").src = imageUrl;
        this.thumbList.style.display = "inline-block";
    },

    validReviewContext() {
        if(this.score.innerText === "0") {
            alert("별점을 등록해주세요.");
            return false;
        }

        if(this.textArea.value.length < 5) {
            alert("리뷰는 최소 5자 이상 작성해주세요.");
            return false;
        }
        
        return true;
    },

    validImage(image) {
        if(this.thumbList.querySelectorAll("img").length > 1) {
            alert("사진은 한 장만 등록 가능합니다.");
            return false;
        }

        const result = (['image/jpeg',
            'image/png',
            'image/jpg'].indexOf(image.type) > -1);
        
        if(!result) {
            alert("이미지 확장자가 유효하지 않습니다.");
            return false;
        }

        return true;
    },
};