var myAjax = (function () {

	var getData = function (_methodType, _dataType, _url, _param, _debug, _callback) {
		//alert("my Ajax Module...........launched 3333 !!!");

		//Spring Security CSRF
		var csrf_token = $("#_csrf").attr("content");
		var csrf_header = $("#_csrf_header").attr("content");

		var paramData = _param;

		if (_methodType.toLowerCase() == "post" || _methodType.toLowerCase() == "put") {
			paramData = JSON.stringify(_param);
		}

		console.log("CSRF_HEADER : " + csrf_header); // CSRF_HEADER : X-CSRF-TOKEN
		console.log("CSRF_TOKEN : " + csrf_token);

		if (_debug) {
			alert("_methodType => " + _methodType + " // _dataType => " + _dataType + " // _url => " + _url + " // param => " + _param);
		}

		$.ajax({
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json; charset=utf-8",
				"X-HTTP-Method-Override": _methodType   // 추가 ########################
			},
			type: _methodType,
			url: _url,
			dataType: _dataType,//"json",
			data: paramData,
			timeout: 20000,
			async: true,
			cache: false,
			beforeSend: function (xhr) {
				xhr.setRequestHeader(csrf_header, csrf_token);
			},
			success: function (data) {
				$("body").css("cursor", "default");

				if (typeof _callback == "function") {
					_callback(data);
				}
			},
			error: function (xhr, textStatus, errorThrown) {
				var errorMsg = 'status(code): ' + xhr.status + '\n';
				errorMsg += 'statusText: ' + xhr.statusText + '\n';
				errorMsg += 'responseText: ' + xhr.responseText + '\n';
				errorMsg += 'textStatus: ' + textStatus + '\n';
				errorMsg += 'errorThrown: ' + errorThrown;

				if (_debug) alert(errorMsg);
				console.log(errorMsg);

				if (typeof callback == "function") callback("error : " + errorThrown);

				//alert("error 111 : " + errorThrown);
			},
			done: function (e) {
				console.log("DONE");
			}
		});

		// Ajax Session Timeout Check
		$.ajaxSetup({
			beforeSend: function (xhr) {
				xhr.setRequestHeader("AJAX", true); // 헤더에 추가하여 전송
			},

			error: function (xhr, status, err) {
				if (xhr.status == 401) {
					alert("401");
				} else if (xhr.status == 403) {
					alert("403");
				} else {
					alert("예외가 발생했습니다. 관리자에게 문의하세요.");
				}
			}
		});
	}

	return {
		getData : getData
	}

}) ();