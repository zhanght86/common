/**
 * requestUrl:访问地址;如:api/common/LocationAction
 * 省份下拉框的id:province 城市下拉框的id:city 区域下拉框的id:region 详细地址数据框id:address
 * 邮政编码输入框的id:postalCode
 * 区号输入框的id:regionCode(存储的是code)
 */
function LocationManager(requestUrl) {
	this.requestUrl = requestUrl + "!";

	//初始化
	this.intiLocation = function (province, city, region) {
		$.post(this.requestUrl + "provinceList.action", {}, function (re) {
			var $province = $("#province").empty();
			var html = "<option value='' id=''>===请选择省份===</option>";
			var result = re["result"];
			for (var i = 0; i < result.length; i++) {
				html += "<option value='" + result[i]["cnName"] + "' id='" + result[i]["code"] + "'>" + result[i]["cnName"] + "</option>";
			}
			$province.append(html);
			if (province) {
				$province.val(province);
			}
			if (city) {
				that.getCity(city, region);
			}
		},"json");
	};

	//加载省份
	this.getProvince = function () {
		$.post(this.requestUrl + "provinceList.action", {}, function (re) {
			var $province = $("#province").empty();
			var html = "<option value='' id=''>===请选择省份===</option>";
			var result = re["result"];
			for (var i = 0; i < result.length; i++) {
				html += "<option value='" + result[i]["cnName"] + "' id='" + result[i]["code"] + "'>" + result[i]["cnName"] + "</option>";
			}
			$province.append(html);
		},"json");
	};

	//加载city并回调
	this.queryCity = function (city, region, callback) {
		var provinceId = $("#province option:selected").attr("id");
		if (provinceId) {
			$.post(this.requestUrl + "cityList.action", {vercode: provinceId}, function (re) {
				var $city = $("#city").empty();
				var result = re["result"];
				var html = "<option value='' id=''>===请选择城市===</option>";
				for (var i = 0; i < result.length; i++) {
					html += "<option value='" + result[i]["cnName"] + "' id='" + result[i]["code"] + "'>" + result[i]["cnName"] + "</option>";
				}
				$city.append(html);

				$("#city").val(city);
				if (region) {
					that.queryRegion(region, callback);
				}
			},"json");
		}
	};

	//加载区域并回调
	this.queryRegion = function (region, callback) {
		var cityId = $("#city option:selected").attr("id");
		if (cityId) {
			$.post(this.requestUrl + "countyList.action", {vercode: cityId}, function (re) {
				var $region = $("#region").empty();
				var result = re["result"];
				var html = "<option value='' id=''>===请选择区县===</option>";
				for (var i = 0; i < result.length; i++) {
					html += "<option value='" + result[i]["cnName"] + "' id='" + result[i]["code"] + "'>" + result[i]["cnName"] + "</option>";
				}
				$region.append(html);

				$("#region").val(region);
				that.queryPostalCode(callback);
			},"json");
		}
	};

	//获取邮政编码和区号
	this.queryPostalCode = function (callback) {
		var county = $("#region").find("option:selected").attr("id");
		if (county) {
			$.post(this.requestUrl + "location.action", {vercode: county}, function (re) {
				var result = re["result"];
				if (result) {
					$("#postalCode").val(result["postal"]);
					$("#regionCode").val(result["code"]);
					if (callback && typeof(callback) === "function") {
						callback();
					}
				}
			},"json");
		}
	};

	// 加载city
	this.getCity = function (city, region) {
		this.queryCity(city, region, null);
	};

	// 加载区域
	this.getRegion = function (region) {
		this.queryRegion(region, null);
	};

	//获取邮政编码和区号
	this.getPostalCode = function () {
		this.queryPostalCode(null);
	};

	//根据省市县生成详细地址
	this.makeAddress = function () {
		var province = $("#province").find("option:selected");
		var city = $("#city").find("option:selected");
		var county = $("#region").find("option:selected");
		var path = "";
		if (province.attr("id")) {
			path += $.trim(province.text());
		}
		if (city.attr("id")) {
			path += $.trim(city.text());
		}
		if (county.attr("id")) {
			path += $.trim(county.text());
		}
		$("#address").val(path);
	};

	var that = this;
	//绑定事件
	// 省
	$("#province").bind("change",function () {
		that.getCity(0, 0);
		that.makeAddress();
	});

	// 市
	$("#city").bind("change",function () {
		$("#regionCode").val($("#city option:selected").attr("id"));
		that.getRegion(0);
		that.makeAddress();
	});

	// 区
	$("#region").bind("change",function () {
		that.makeAddress();
		that.getPostalCode();
	});
}

