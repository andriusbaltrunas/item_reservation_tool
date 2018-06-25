$(document).ready(function () {
    $(".js-open-dialog").click(function () {
        $(".js-date-val").val('');
        var that = $(this);
        var uuid = that.data("uuid");
        var title = that.data("title");

        $(".modal-title").text(title);
        $("#js-hidden-uuid").val(uuid);

        $("#itemDialog").addClass("show");
        $("#itemDialog").attr("style", "display:block");
    });

    $(".js-close").click(function () {
        $("#itemDialog").removeClass("show");
        $("#itemDialog").attr("style", "display: none");
    });
});