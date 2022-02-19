(function ($) {
    $.load = {
        loading: function () {
            var $div = `<div id="load" style="z-index:999"></div>`
            $(document.body).append($div);
            $('#load').load('/loading')
        },
        removeLoading: function () {
            $('#load').remove()
        }
    }
})(jQuery);