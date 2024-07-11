$(function() {

  'use strict';

  $('.menu-icon').click(function(e) {

  	var $this = $(this);

  	

  	if ( $('body').hasClass('show-sidebar') ) {
  		$('body').removeClass('show-sidebar');
  		$this.removeClass('active');
		$(".custom-overlay").css("display","none")
  	} else {
  		$('body').addClass('show-sidebar');	
  		$this.addClass('active');
		$(".custom-overlay").css("display","block")
  	}

  	e.preventDefault();

  });

  // click outisde offcanvas
	// $(document).mouseup(function(e) {
  //   var container = $(".sidebar");
  //   if (!container.is(e.target) && container.has(e.target).length === 0) {
  //     if ( $('body').hasClass('show-sidebar') ) {
	// 			$('body').removeClass('show-sidebar');
	// 			$('body').find('.js-menu-toggle').removeClass('active');
	// 		}
  //   }
	// });

    

});

function mobileViewUpdate() {
	var viewportWidth = $(window).width();
	if (viewportWidth < 1025) {
		$(".body").removeClass("show-sidebar")
		$(".menu-logo-left").removeClass("d-none")
	} else {
		$(".body").addClass("show-sidebar")
		$(".menu-logo-left").addClass("d-none")
		$(".custom-overlay").css("display","none")
	}
}

$(window).on('load resize', mobileViewUpdate);