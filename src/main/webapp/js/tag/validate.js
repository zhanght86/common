$.fn.checkEmail = function(){
   var value = $(this).val();
   //对电子邮件的验证
  // var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
   var myreg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if(value!="") {
     if(!myreg.test(value)) {
        //$(this)[0].focus();
        return false;
     }
    }
   	return true;
};

$.fn.checkMobile = function(){
	   var value = $(this).val();
	   // 对手机的验证(香港手机号码)
	   var myregForHongkong = /^[0-9]{8}$/;
	   // 对手机的验证
	   var myreg = /^0{0,1}(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/;
	   if (value != "") {
		   if (myregForHongkong.test(value)) {
			   return true;
		   }
		   if (myreg.test(value)) {
			   return true;
		   }
	   }
	   //$(this)[0].focus();
	   return false;
};

$.fn.checkTel = function(){
	   var value = $(this).val();
	   //对座机的验证
	   var myreg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	   if(value!="") {
	     if(!myreg.test(value)) {
	        //$(this)[0].focus();
	        return false;
	     }
	    }
	   	return true;
};