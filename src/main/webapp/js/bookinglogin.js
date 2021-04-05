document.addEventListener("DOMContentLoaded", () => {
    initPage();
});

const initPage = () => {
    new ButtonEvent().addButtonEvent();
}

function ButtonEvent() {
    this.viewReservationButton = document.querySelector(".login_btn.confirm");
    this.inputEmail = document.querySelector("#resrv_id");
    this.emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
};

ButtonEvent.prototype.addButtonEvent = function() {
    this.viewReservationButton.addEventListener("click", (event) => {
        const isValid = this.emailRule.test(this.inputEmail.value);

        if (!isValid) {
            alert("이메일 형식이 잘못되었습니다.");
            this.inputEmail.value = "";
            event.preventDefault();
        }
    });
};