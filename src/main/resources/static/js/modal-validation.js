$(document).ready(function() {
    const POST_TITLE_LENGTH = "Title should be between 4 and 50 symbols";
    const POST_CONTENT_LENGTH = "Content should be between 8 and 2000 symbols";

    $("#editContentButton").on("click", function (event) {
        let titleLenght = $("#editPostTitleInput").val().length;
        let contentLenght = $("#editPostContentInput").val().length;
        let error = false;

        let titleErrorElement = $("#editPostTitleHint");
        let contentErrorElement = $("#editPostContentHint");

        if (titleLenght < 4 || titleLenght > 50) {
            titleErrorElement.html(POST_TITLE_LENGTH);
            error = true;
        } else  {
            titleErrorElement.empty();
        }

        if (contentLenght < 8 || contentLenght > 2000) {
            contentErrorElement.html(POST_CONTENT_LENGTH);
            error = true;
        } else  {
            contentErrorElement.html("")
        }

        if (error === true) {
            event.preventDefault();
        }
    })
});