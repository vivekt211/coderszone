function showInfo(msg){
  $.notify({
    // options
    icon: 'info-icon',
    title: 'INFO',
    message: msg,
    //url: 'https://github.com/mouse0270/bootstrap-notify',
    //target: '_blank'
  },{
    // settings
    element: 'body',
    position: null,
    type: "info",
    allow_dismiss: true,
    newest_on_top: false,
    showProgressbar: false,
    placement: {
      from: "bottom",
      align: "right"
    },
    offset: 50,
    spacing: 10,
    z_index: 1031,
    delay: 5000,
    timer: 1000,
    url_target: '_blank',
    mouse_over: null,
    animate: {
      enter: 'animated fadeInDown',
      exit: 'animated fadeOutUp'
    },
    onShow: null,
    onShown: null,
    onClose: null,
    onClosed: null,
    icon_type: 'class',
    template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
      '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">x</button>' +
      '<div class="noti-left">'+
      '<span data-notify="icon"></span> ' +
      '</div>'+
      '<div class="noti-right">'+
      '<span class="heading" data-notify="title">{1}</span> ' +
      '<span class="msg" data-notify="message">{2}</span>' +
      '</div>'+
    '</div>' 
  });
  
}

function showSuccess(msg){
  $.notify({
    // options
    icon: 'success-icon',
    title: 'SUCCESS',
    message: msg,
    //url: 'https://github.com/mouse0270/bootstrap-notify',
    //target: '_blank'
  },{
    // settings
    element: 'body',
    position: null,
    type: "success",
    allow_dismiss: true,
    newest_on_top: false,
    showProgressbar: false,
    placement: {
      from: "bottom",
      align: "right"
    },
    offset: 50,
    spacing: 10,
    z_index: 1031,
    delay: 5000,
    timer: 1000,
    url_target: '_blank',
    mouse_over: null,
    animate: {
      enter: 'animated fadeInDown',
      exit: 'animated fadeOutUp'
    },
    onShow: null,
    onShown: null,
    onClose: null,
    onClosed: null,
    icon_type: 'class',
    template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
      '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">x</button>' +
      '<div class="noti-left">'+
      '<span data-notify="icon"></span> ' +
      '</div>'+
      '<div class="noti-right">'+
      '<span class="heading" data-notify="title">{1}</span> ' +
      '<span class="msg" data-notify="message">{2}</span>' +
      '</div>'+
    '</div>' 
  });
  
}

function showError(msg){
  $.notify({
    // options
    icon: 'error-icon',
    title: 'ERROR',
    message: msg,
    //url: 'https://github.com/mouse0270/bootstrap-notify',
    //target: '_blank'
  },{
    // settings
    element: 'body',
    position: null,
    type: "error",
    allow_dismiss: true,
    newest_on_top: false,
    showProgressbar: false,
    placement: {
      from: "bottom",
      align: "right"
    },
    offset: 50,
    spacing: 10,
    z_index: 1031,
    delay: 5000,
    timer: 1000,
    url_target: '_blank',
    mouse_over: null,
    animate: {
      enter: 'animated fadeInDown',
      exit: 'animated fadeOutUp'
    },
    onShow: null,
    onShown: null,
    onClose: null,
    onClosed: null,
    icon_type: 'class',
    template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
      '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">x</button>' +
      '<div class="noti-left">'+
      '<span data-notify="icon"></span> ' +
      '</div>'+
      '<div class="noti-right">'+
      '<span class="heading" data-notify="title">{1}</span> ' +
      '<span class="msg" data-notify="message">{2}</span>' +
      '</div>'+
    '</div>' 
  });
}

function showNotification(type,msg){
  switch(type){
  case 'info':
    showInfo(msg);
    break;
  case 'success':
    showSuccess(msg);
    break;
  case 'error':
    showError(msg);
    default :
      //alert(type+", "+msg);
  }
}
function confirmAction(msg,successCallback){
  bootbox.confirm(msg, function(result) {
    successCallback(result);
  }); 
}