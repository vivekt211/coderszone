 function notify(type,msg) {
        var n = noty({
            text        : msg,
            type        : type,
            dismissQueue: true,
            layout      : 'center',
            theme       : 'defaultTheme',
            buttons     : [
                {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
                    $noty.close();
                    //noty({dismissQueue: true, force: true, layout: layout, theme: 'defaultTheme', text: 'You clicked "Ok" button', type: 'success'});
                }
                }/*,
                {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
                    $noty.close();
                    noty({dismissQueue: true, force: true, layout: layout, theme: 'defaultTheme', text: 'You clicked "Cancel" button', type: 'error'});
                }
                }*/
            ]
        });
       // console.log('html: ' + n.options.id);
    }
 
 
 function notifyGoLogin(type,msg) {
   var n = noty({
       text        : msg,
       type        : type,
       dismissQueue: true,
       layout      : 'center',
       theme       : 'defaultTheme',
       buttons     : [
           {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
               $noty.close();
               //noty({dismissQueue: true, force: true, layout: layout, theme: 'defaultTheme', text: 'You clicked "Ok" button', type: 'success'});
           }
           },
           {addClass: 'btn btn-danger', text: 'Go To Login', onClick: function ($noty) {
               $noty.close();
               window.location = '/service/login';
             }
           }
       ]
   });
 }
   function notifyGoMyPage(type,msg) {
     var n = noty({
         text        : msg,
         type        : type,
         dismissQueue: true,
         layout      : 'center',
         theme       : 'defaultTheme',
         buttons     : [
             {  addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
                 $noty.close();
                 window.location = '/service/mypage';
                 }
             }
         ]
     });
  // console.log('html: ' + n.options.id);
   }
   function notifyGoHome(type,msg) {
     var n = noty({
         text        : msg,
         type        : type,
         dismissQueue: true,
         layout      : 'center',
         theme       : 'defaultTheme',
         buttons     : [
             {  addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
                 $noty.close();
                 window.location = '/service/home';
                 }
             }
         ]
     });
  // console.log('html: ' + n.options.id);
   }