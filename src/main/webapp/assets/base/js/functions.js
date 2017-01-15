(function($) {
  "use strict";

  $(document).foundation({
    equalizer : {
      // Specify if Equalizer should make elements equal height once they become stacked.
      equalize_on_stack: true
    },
    offcanvas : {
      // Sets method in which offcanvas opens.
      // [ move | overlap_single | overlap ]
      open_method: 'move',
      close_on_click : true
    },
    accordion: {
      // allow multiple accordion panels to be active at the same time
      multi_expand: false,
      // allow accordion panels to be closed by clicking on their headers
      // setting to false only closes accordion panels when another is opened
      toggleable: true
    }
  });

  //Run When Document Ready
  $(document).on('ready', function() {
    initPageFx();
    initAutoHideNav();
    initMegaMenu();
    initBackTop();
    initSharingButtons();
    initFooterHide();
  });

  //Animsition - Page Effects
  //===================================
  function initPageFx() {
    $('.animsition').animsition({
      inClass: 'fade-in',
      outClass: 'fade-out-down-sm',
      inDuration: 1000,
      outDuration: 800,
      linkElement: '#primaryMenu a:not([target="_blank"]):not([href^=#])',
      loading: true,
      loadingParentElement: 'body', 
      loadingClass: 'loadSpinner',
      unSupportCss: [
                      'animation-duration',
                      '-webkit-animation-duration',
                      '-o-animation-duration'
                    ],
      overlay : false,
      overlayClass : 'animsition-overlay-slide',
      overlayParentElement : 'body'
    });
  }



  //Auto Hide Navigation
  //===============================================================================
  function initAutoHideNav() {
    var header = document.querySelector('#autoHide');

    new Headroom(header, {
      tolerance: {
        down : 2,
        up : 5
      },
      offset : 100,
      classes: {
        initial: 'slide',
        pinned: 'slideReset',
        unpinned: 'slideUp'
      }
    }).init();
  }

  //MegaMenu - Superfish
  //===============================================================================
  function initMegaMenu() {
    $('#primaryMenu > ul').superfish({
      popUpSelector: 'ul,.megaMenuContent',
      delay: 250,
      speed: 350,
      animation: {opacity:'show'},
      animationOut:  {opacity:'hide'},
      cssArrows: false // disable generation of arrow mark-up
    });

    $('#mobileMenuTrigger').on('click', function() {
      $( '#primaryMenu' ).toggleClass('show');
      return false;
    });
  }



  
  //Back To Top
  //===============================================================================
  function initBackTop() {
    // browser window scroll (in pixels) after which the "back to top" link is shown
    var offset = 500,
        //browser window scroll (in pixels) after which the "back to top" link opacity is reduced
        offset_opacity = 1200,
        //duration of the top scrolling animation (in ms)
        scroll_top_duration = 700,
        //grab the "back to top" link
        $back_to_top = $('.backTop');

    //hide or show the "back to top" link
    $(window).on('scroll', function() {
        ($(this).scrollTop() > offset) ? $back_to_top.addClass('is-visible') : $back_to_top.removeClass('is-visible fade-out');
        if ($(this).scrollTop() > offset_opacity) {
            $back_to_top.addClass('fade-out');
        }
    });

    //smooth scroll to top
    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body,html').animate({
            scrollTop: 0
        }, scroll_top_duration
            );
    });
  }

  //Pretty Social (Sharing Buttons)
  //===============================================================================
  function initSharingButtons() {
    $('.prettySocial').prettySocial();
  }
  
  function initFooterHide() {
        var offset = 50;
        var footer=$(".footerWrapper");
        if(footer && $("body").width()> 1024){
          footer.addClass("fixed-pos");
          var realOffset=$(document).height()-$(window).height()-offset;
          var footer = $('.footerWrapper');
          if(realOffset < 5){
            footer.removeClass('no-show fade-out');
          }else{
            footer.addClass('no-show fade-out');
          //hide or show the "footer
            $(window).on('scroll', function() {
                ($(this).scrollTop() > realOffset) ? footer.removeClass('no-show fade-out') : footer.addClass('no-show fade-out');
            });
          }
        }
        
       

    
  }

})(jQuery);
