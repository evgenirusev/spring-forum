$(document).ready(function () {

    $("#registerSubmitButton").on("click", function (e) {
        let response = grecaptcha.getResponse();

        if (response.length == 0) {
            e.preventDefault();
            $("#captchaError").html("You cannot leave Captcha Code empty");
        }
    })
})