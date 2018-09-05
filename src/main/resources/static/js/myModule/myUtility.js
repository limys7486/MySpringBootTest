


function formatDate(timeValue) {
	var date = new Date(timeValue);
	return date.getFullYear() + "-" + (date.getMonth()+1 >= 10 ? date.getMonth()+1 : '0'+(date.getMonth()+1)) + "-"  + date.getDate()
}