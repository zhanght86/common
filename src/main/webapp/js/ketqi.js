/**
 * customer fountion extend jquery
 * 
 * @author ketqi
 */
;
(function($) {
	// mark up html select read only
	$.fn.selectReadOnly = function() {
		var tem = $(this).children('option').index($("option:selected"));
		$(this).change(function() {
			$(this).children('option').eq(tem).attr("selected", true);
		});
	};

	//make table can hover and selected
	$.fn.operational = function(options){
		var $this = $(this);
		$.fn.operational.defaults={hoverClass:'hover',selectedClass:'selected'};
		var opts = $.extend({}, $.fn.operational.defaults, options);
		$(this).find("tbody tr").live("click",function(e){
			e = e || window.event;
			if (e.ctrlKey ) {$(this).toggleClass(opts.selectedClass);}
			else {$this.find("tbody tr").removeClass(opts.selectedClass);$(this).addClass(opts.selectedClass);}
		});
		$(this).find("tbody tr").live("mouseover",function(){$(this).addClass(opts.hoverClass);});
		$(this).find("tbody tr").live("mouseout",function(){$(this).removeClass(opts.hoverClass);});
	};

	//tip
	$.fn.tip = function(options){
		$.fn.tip.defaults={tipClass:'tip',left:5,top:3,delay:600,stay:5000};
		var opts = $.extend({}, $.fn.tip.defaults, options);
		var T;
		this.filter("[tip]").hover(function(){
				var i=$(this).attr("tip");
				var left = $(this).offset().left+opts.left+"px";
				var top = $(this).offset().top+$(this).height()+opts.top+"px";
				function openTip(){
					$("<div><div>").html(i)
						.appendTo("body")
						.addClass(opts.tipClass)
						.css({"position":"absolute","left":left,"top":top,"z-index":"9999"})
						.show("slow",function(){window.setTimeout(function(){$("div."+opts.tipClass).hide("slow");},opts.stay);});
				}
				T = window.setTimeout(openTip,opts.delay);},
			function(){window.clearTimeout(T);$("div."+opts.tipClass).remove();}
		).keydown(function(){window.clearTimeout(T);$("div."+opts.tipClass).remove();});
	};

})(jQuery);