(function($) {
  'use strict';
  $(function() {
    var sidebar = $('.sidebar');

    //Add active class to nav-link based on url dynamically
    //Active class can be hard coded directly in html file also as required
    var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
    console.log(current);
//    $('.nav li a', sidebar).each(function() {
//      var $this = $(this);
//      if (current === "") {
//        //for root url
//        if ($this.attr('href').indexOf("index.html") !== -1) {
//          $(this).parents('.nav-item').last().addClass('active');
//          if ($(this).parents('.sub-menu').length) {
//            $(this).closest('.collapse').addClass('show');
//            $(this).addClass('active');
//          }
//        }
//      } else {
//        //for other url
//        if ($this.attr('href').indexOf(current) !== -1) {
//          $(this).parents('.nav-item').last().addClass('active');
//          if ($(this).parents('.sub-menu').length) {
//            $(this).closest('.collapse').addClass('show');
//            $(this).addClass('active');
//          }
//        }
//      }
//    })
    $('.nav li a', sidebar).each(function() {
          var $this = $(this);
          if (current === "") {
            //for root url
            if ($this.attr('href').indexOf("index.html") !== -1) {
              $(this).parents('.nav-item').last().addClass('active');
              if ($(this).parents('.sub-menu').length) {
                $(this).closest('.collapse').addClass('show');
                $(this).addClass('active');
              }
            }
          } else {
                var path = window.location.pathname;
                var aHref = $this.attr('href');
                if (1 == (path.split('/').length -1)) {
                    if (aHref == path) {
                        $(this).parents('.nav-item').last().addClass('active');
                        if ($(this).parents('.sub-menu').length) {
                            $(this).closest('.collapse').addClass('show');
                            $(this).addClass('active');
                        }
                    }
                } else if (1 < (path.split('/').length -1)) {
                    path = '/'+path.split('/')[1];
                    if (aHref == path) {
                        $(this).parents('.nav-item').last().addClass('active');
                        if ($(this).parents('.sub-menu').length) {
                            $(this).closest('.collapse').addClass('show');
                            $(this).addClass('active');
                        }
                    }
                }
          }
    });

    //Close other submenu in sidebar on opening any

    sidebar.on('show.bs.collapse', '.collapse', function() {
      sidebar.find('.collapse.show').collapse('hide');
    });


    //Change sidebar and content-wrapper height
    applyStyles();

    function applyStyles() {
      //Applying perfect scrollbar
      if ($('.scroll-container').length) {
        const ScrollContainer = new PerfectScrollbar('.scroll-container');
      }
    }

    //checkbox and radios
    $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');


    $(".purchace-popup .popup-dismiss").on("click",function(){
      $(".purchace-popup").slideToggle();
    });
  });
})(jQuery);