//import { getAjaxJsonData } from "./myAjaxModule.js"

//var myAjaxModule = require('./myAjaxModule');

var memberModule = (function() {

    var findAll = function (obj, callback) {
        console.log("findAll .........");



        $.getJSON('/members/', callback);

		// getAjaxJsonData(pMethodType, pDataType, pUrl, pParam, debug, callback)
        //myAjaxModule.getAjaxJsonData('get', 'json', '/members/', obj, false, callback);

    }

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
	}

    return {
        findAll : findAll,
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

