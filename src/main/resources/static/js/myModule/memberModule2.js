
var memberModule = (function() {
	var ajax ;

	$.getScript("/js/myModule/myAjaxModule.js", function() {
		ajax = myAjax;
	});

    var getMember = function (_username, _callback) {
        console.log("findAll .........");

        ajax.getData("get", "json", "/members/"+_username, '', false, _callback);

        //$.getJSON('/members/', callback);
    };

	var findPostAll = function (obj, callback) {
        console.log("findPostAll .........");

        $.ajax({
            type: 'post',
            url: '/members',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: 'json',
            success: callback
        })
	};

    return {
	    getMember : getMember,
        findPostAll : findPostAll
	}
//    var findOne = function (obj, callback) {
//	    console.log("findOne .........");
//
//	    $.getJSON('/members/' + obj.bno, callback);
//    }
//
//    var insert = function(obj, callback) {
//        console.log("insert .........");
//
//        $.ajax({
//            type: 'post',
//            url: '/members/' + obj.bno,
//            data: JSON.stringify(obj),
//            dataType: 'json',
//            contentType: 'json',
//            success: callback
//        })
//    }
//
//    var update = function(obj, callback) {
//
//        $.ajax({
//            type: 'put',
//            url: '/members/' + obj.bno,
//            dataType: 'json',
//            data: JSON.stringify(obj),
//            contentType: 'json',
//            success: callback
//        })
//    }
//
//    var remove = function(obj, callback) {
//        console.log("delete ............");
//
//        $.ajax({
//            type: 'delete',
//            url: '/members/' + obj.bno,
//            dataType: 'json',
//            contentType: 'json',
//            success: callback
//        })
//    }
//
//    return {
//        findAll : findAll,
//        findOne : findOne,
//        insert : insert,
//        update : update,
//        remove : remove
//    }
}) ();

