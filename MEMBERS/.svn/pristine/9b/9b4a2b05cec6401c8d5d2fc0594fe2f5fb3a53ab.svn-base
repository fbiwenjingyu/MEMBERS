$(function(){
  var isSuf = false;//余额是否充足
  $('#activeModal').on('okHide', function(e){
	isSuf=checkAcount();
    if(isSuf){
      $('#payModal').modal('show');
    }else{
      $('#payNoModal').modal('show');
    }
  });
  $('#payNoModal').on('okHide', function(e){
    location.href = ctx+"recharge/toAdd";
  });
});