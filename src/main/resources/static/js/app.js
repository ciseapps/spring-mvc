(function ( $ ) {

    $.fn.postJSON = function( url, data, callback ) {

        if ( jQuery.isFunction( data ) ) {
            callback = data;
            data = undefined;
        }

        return jQuery.ajax({
            url: url,
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: callback
        });
    };

}( jQuery ));

$(window).resize(function() {
    $('.select2').css('width', "100%");
});