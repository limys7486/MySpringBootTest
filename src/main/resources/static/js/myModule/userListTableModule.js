var userListTableModule = (function () {

	var dataTable = null;

	var initTable = function (obj) {
		dataTable = obj;

		var table = dataTable.DataTable({
			"bFilter": true,
			"pageLength": 10,
			"paging": true,
			"info": false,
			"searching": true,
			"bProcessing": true,
			"ordering": true,

			"select": false,
			//"bInfo": true,

			"bLengthChange": true,
			"bStateSave": true,
			//"bJQueryUI" : true,

			"sPaginationType": "full_numbers",
			"pagingType": "full_numbers",
			//aaSorting:[[0, "desc"]],

			"fixedHeader": true,
			"responsive": false,
			//"bAutoWidth": false,

			"scrollY": "260",  //"scrollY": "260px",
			"bScrollCollapse": true,
			//scroller: {
			//   rowHeight: 300
			// },

			"bDestroy": true,

//			"processing": true,
//			"serverSide": false,
//			"ajax": {
//				url : "http://localhost:8080/members",
//				type : "get",
//				dataType : "json",
//				data: function (d) {
//					d.myMode = "update_mode";
//				},
//				error : function(e) {
//					console.log(e);
//				}
//			},

			"language": {
				search: 'Search : ',

				info: 'Showing page _PAGE_ of _PAGES_',
				infoEmpty: '', //"No records available",
				infoFiltered: '(filtered from _MAX_ total records)',

				lengthMenu: 'Display _MENU_ records per page',

				zeroRecords: 'No Data',
				emptyTable: 'No Data',
				paginate: {
					'first': '<<',
					'previous': '<',
					'next': '>',
					'last': '>>'
				}
			},

			"dom": 'r<"scrollDiv"t><"bottom"<"row"<"col-xs-2"B><"col-xs-5"p><"col-xs-3"i><"col-xs-2"f>>>',//'Bfrtip',

			"buttons": [
				{
					extend: 'copyHtml5',
					text: '<span><i class="fa fa-files-o"></i></span>',
					titleAttr: 'Copy'
				},
				{
					extend: 'excelHtml5',
					text: '<span><i class="fa fa-file-excel-o"></i></span>',
					titleAttr: 'Excel'
				},
				{
					extend: 'pdfHtml5',
					text: '<span><i class="fa fa-file-pdf-o"></i></span>',
					titleAttr: 'PDF'
				}
			],


			"columns": [
				{data: "username"},
				{data: "realname"}, // 주의) json key값이 모두 소문자로 들어옴
				{data: "email"},
				{
					data: "address",
					title: "My Address",
					render: function (d) {
						return d.length > 10 ? d.substr(0, 10) + '…' : d;
					}
				},
				{data: "mobile"},
				{data: "rolename"},
				{
					data: "updatedate",
					type: "datetime",
					render: function (d) {
						return moment(d).format("YYYY-MM-DD HH:mm:ss");
					}
				},
				{data: "enabled"},
				{data: "blank"},
				{data: "blank"}
			],

			"columnDefs": [
				{className: 'text-center', targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]},

				//{
				//"targets": [6],
				//"render": function (data, type, row) {
				//return "<center><span class=\"badge\" style=\"font-size:10pt;background-color:#BABABA\">"+data+"</span></center>";
				//}
				//}
				//},

				//{
				//"targets": [3],
				//"render": function (data, type, row) {
				//return "<center><span class=\"label label-default\" style=\"font-size:10pt\">"+data+"</span></center>";
				//}
				//},
				//{
				//"targets": [6],
				//"render": function (data, type, row) {
				//return "<center><span class=\"badge\" style=\"font-size:10pt;background-color:#BABABA\">"+data+"</span></center>";
				//}
				//},
				//{
				//"targets": [10],
				//"visible": false,  // column visible : hidden
				//"searchable": false
				//},

				{
					"targets": [8],
					"render": function (data, type, row) {
						var username = row.username;

						var ret = "<div style='text-align:center' id=\'" + row.username +"\'>";
						ret += "<a href='#'><span class='btn btn-warning modbtn' name='modbtn'>edit</span></a>&nbsp;";
						ret += "<a href='#'><span class='btn btn-danger delbtn' name='delbtn'>delete</span></a>";
						ret += "</div>";

						return ret;
					},
					"data": null,
					"defaultContent": "", // extra column
					"orderable": false,
					"order": []
				}
				,
				{
					"targets": [9],
					"data": null,
					"defaultContent": "", // extra column
					"orderable": false,
					"order": []
				}
			],

			"rowCallback": function (row, data) {
			},
			"drawCallback": function (settings) {
			}
		});
	}

	var getSelectedRowData = function () {
		table = dataTable.DataTable();

		var data = table.rows(['.selected']).data().toArray();
		return data;
	}

	var addUserList = function (list) {
		var table = dataTable.DataTable();

		table.clear();
		table.rows.add(list).draw();  // List 로 데이터를 받음 (content-type:application/json)
		table.columns.adjust().draw();

		//$('#myTable').dataTable().fnAdjustColumnSizing();
		//$('#myTable').dataTable().rows().recalcHeight().draw();
		//table.resize();
	}

	return {
		initTable: initTable,
		addUserList: addUserList,
		getSelectedRowData: getSelectedRowData
	}

})();