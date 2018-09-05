exports.getAjaxJsonData = function (pMethodType, pDataType, pUrl, pParam, debug, callback) {

	alert("my Ajax Module...........launched !!!");

    // Spring Security CSRF
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var paramData = pParam;

    if (pMethodType.toLowerCase() == "post" || pMethodType.toLowerCase() == "put") {
        paramData = JSON.stringify(pParam);
    }

    console.log(" HEADER : " + header);
    console.log(" TOKEN : " + token);

    if (debug) {
        alert("pMethodType => " + pMethodType + " // pDataType => " + pDataType + " // pUrl => " + pUrl + " // param => " + pParam);
    }

    $.ajax({
        headers:{
            "Accept":"application/json",
            "Content-Type":"application/json; charset=utf-8",
            "X-HTTP-Method-Override" : pMethodType   // 추가 ########################
            ,header:token
        },
        type:pMethodType,
        url:pUrl,
        dataType:pDataType,//"json",
        data:paramData,
        timeout:20000,
        async:true,
        cache:false,
        beforeSend:function(xhr) {
               // here it is
               //xhr.setRequestHeader(header, token);

           },
        success:function(data) {
            $("body").css("cursor", "default");

            if(typeof callback == "function") {
                callback(data);
            }
        },
        error: function(xhr, textStatus, errorThrown){
            ///*
            var errorMsg = 'status(code): ' + xhr.status + '\n';
            errorMsg += 'statusText: ' + xhr.statusText + '\n';
            errorMsg += 'responseText: ' + xhr.responseText + '\n';
            errorMsg += 'textStatus: ' + textStatus + '\n';
            errorMsg += 'errorThrown: ' + errorThrown;

            if (debug) alert(errorMsg);
            console.log(errorMsg);
            //*/

            if(typeof callback == "function") callback("error : " + errorThrown);

            //alert("error 111 : " + errorThrown);
        },
        done:function(e) {
            console.log("DONE");
        }
    });

    // Ajax Session Timeout Check
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader("AJAX", true); // 헤더에 추가하여 전송
        },

        error: function(xhr, status, err) {
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