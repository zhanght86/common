(function($) {
	//默认加载配置
	var optsDefault = {
		defaultLicall:function(li, opts) {
			li = $(li);
			opts.menu && li.menu(opts.menu);
			li.children("span").click(function() {
				$(this).liSpanClick();
			});
			li.children("input:checkbox").click(function() {
				$(this).liCheckboxClick();
			});
		},
		licall:function(li, opts) {
			optsDefault.defaultLicall(li, opts);
		}
	};
	/**
	 $.fn.plantTree = function(map, options, callback) {
	 var map = map || [
	 {
	 name: '一棵树'
	 }
	 ];
	 $.fn.plantTree.defaults = {checkbox:true,text:"name",children:"sub",menu:{"添加节点":function(ui) {ui.addNode();},"修改节点":function(ui) {ui.editNode()},"删除节点":function(ui) {ui.deleteNode()}}};
	 var opts = $.extend({}, $.fn.plantTree.defaults, options);
	 var tree = write(map, true);
	 $(tree).initTree({clickSpan:opts.clickSpan,change:opts.change,menu:opts.menu,open:opts.open,close:opts.close});
	 this.append(tree);
	 if(callback) callback();
	 function write(a, root) {
	 var i = $("<ul></ul>");
	 $.each(a, function(ii, k) {i.append(writeOne(k, true));});
	 return i;
	 }

	 function writeOne(k, cascade) {
	 var i = $("<li></li>");
	 if(opts.checkbox) {
	 var checkBox = $("<input type='checkbox'/>");
	 if(k[opts.value]) checkBox.val(k[opts.value]);
	 i.append(checkBox);
	 }
	 var la = $("<span></span>").html(k[opts.text]).attr("id", k[opts.id] ? k[opts.id] : "");
	 i.append(la);
	 cascade && k[opts.children] && (i.append(write(k[opts.children], false)));
	 return i;
	 }
	 };
	 */
	$.fn.initTree = function(options, callback) {
		var opts = $.extend({}, $.extend(optsDefault, options));
		if(this.is("ul")) {
			this.addClass("tree").find("li").prepend("<div class='toggle'></div>");
			opts.expand && this.find("ul").show() || this.find("ul").hide();
			var divs = this.find("li:has(ul)>div.toggle").addClass("border");
			opts.expand && divs.text("-") || divs.text("+");
			if(opts.clickSpan) this.find("span").live("click", opts.clickSpan);
			if(opts.change) this.find("input:checkbox").live("click", opts.change);
			if(!opts.checkbox) this.find("input:checkbox").css("display", "none");
			this.find("div.toggle").live("click", function() {
				if($(this).text() == "-") {
					!!opts.close && opts.close(this);
					var ul = $(this).text("+").siblings("ul").hide();
					ul.find("li ul").hide();
					ul.find("li div.toggle:contains('-')").text("+");
				} else if($(this).text() == "+") {
					!!opts.open && opts.open(this);
					$(this).text("-").siblings("ul").show();
				}
			});
			$.isFunction(opts.licall) && this.find("li").each(function() {
				var self = $(this);
				opts.licall(self, opts);
			});
			if(callback) callback();
		}
		else
			alert("this is not ul!");
	};
	$.fn.liSpanClick = function() {
		$(this).parents("ul.tree").find("span.selected").removeClass("selected");
		$(this).addClass("selected");
	};
	$.fn.liCheckboxClick = function() {
		if($(this).prop("checked")) {
			$(this).parents("li").children("input:checkbox").prop("checked", true);
			$(this).siblings("ul").find("input:checkbox").prop("checked", true);
		}else {
			$(this).siblings("ul").find("input:checkbox").prop("checked", false);
			$(this).parents("ul").each(function() {
				if($(this).find("input:checked").length < 1) {
					$(this).parent("li").children("input:checkbox").prop("checked", false);
				}
			});
		}
	};
	$.fn.menu = function(opts) {
		var li = $(this);
		//未将此结点添加至document上
		var menu;
		if(!opts["_added"]) {
			var r = ('_r' + Math.random()).replace(".", "_");//替换.号,以保证在取结点时,避免因为.号取得不正确的结果
			menu = $("<ul class='menu' id='" + r + "'></ul>");
			$.each(opts, function(i, field) {
				$("<li></li>").html(i).appendTo(menu).click(function() {
					var ui = menu[0]["_currentLi"];//用属性来代替动态调用
					ui && field(ui);
				});
			});
			menu.css({position:"absolute"}).hide();
			$("body").append(menu);
			opts["_added"] = r;
		} else {
			menu = $("#" + opts["_added"]);
		}

		this.children("span").bind("contextmenu", function(e) {
			menu[0]["_currentLi"] = li;//将相应属性重新修改,以便调用函数找到正确的li

			e = e || window.event;
			$(this).parents("ul.tree").find("span.selected").removeClass("selected");
			$(this).addClass("selected");
			var left = e.pageX + "px";
			var top = e.pageY + "px";
			menu.css({left:left,top:top}).show();//将菜单展示出来
			//取消默认的右键菜单
			return false;
		});
		$(document).click(function() {menu.hide();});
	};
	$.fn.addNode = function(value, text, opts, callback) {
		var opt = $.extend({}, optsDefault);
		opts && $.extend(opt, opts);
		value = value || "";
		text = text || "新建节点";
		var $li = $("<li><div class='toggle'></div><input type='checkbox' value='" + value + "'/><span>" + text + "</span></li>");
		if(this.children("ul").length == 1) this.children("ul").append($li);
		if(this.children("ul").length == 0) {
			$("<ul></ul>").append($li).appendTo(this);
			this.children("div.toggle").html("-").addClass("border");
		}
		$.isFunction(opt.licall) && opt.licall($li, opt);
		!opt.checkbox && $li.find("input:checkbox").css("display", "none");
		callback && callback($li);
	};
	$.fn.addNodeList = function(html, opts, callback) {
		var opt = $.extend({}, optsDefault);
		opts && $.extend(opt, opts);
		var $this = $(this);
		html = $(html);
		html.prepend("<div class='toggle'></div>");
		$this.children("ul").length ? ($this.children("ul").append(html)) : ($this.append($("<ul></ul>").append(html))).children("div.toggle").html("-").addClass("border");
		$.isFunction(opt.licall) && html.each(function() {
			opt.licall($(this), opt);
		});
		!opt.checkbox && html.find("input:checkbox").css("display", "none");
		callback && callback(html);
	};
	$.fn.editNode = function(value, text, callback) {
		var $this = $(this);
		if(!callback || ($.isFunction(callback) && callback($this))) {
			value && $this.children("input").val(value);
			text && $this.children("span").html(text);
		}
	};
	$.fn.deleteNode = function(callback) {
		if($.isFunction(callback) ? callback($uls) : true) {
			var $uls = $(this).parents("ul");
			this.remove();
			$uls.each(function() {
				if($(this).find("input:checkbox").length < 1) {
					$(this).parent("li").children("div.toggle").html("").removeClass("border");
					$(this).remove();
				}
			});
		}
	};
}(jQuery));