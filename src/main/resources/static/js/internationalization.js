$(document).ready(function() {
    $("#languageSelect").change(function () {
        var selectedOption = $('#languageSelect').val();
        if (selectedOption != '') {
            window.location.replace('?lang=' + selectedOption);
        }
    });
});