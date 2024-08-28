//Global variables used in the compareTimes() function

var notificationsSize = 0;
beforeload1 = new Date();

//Loads JSON object data into the notification table
function CreateAndPopulateNotificationTable(){
	$.getJSON('NotificationList.json', function(data) {
		
		$('#notifications').empty();
		
		//Sort the data by Time and Date
		var sortedData = sortTimeDate(data);
		
		var table = $('<table class="table" id="tablenotifications"></table>').appendTo($('#notifications'));
		
		for(var i=0; i<sortedData.length; i++){
			var item = sortedData[i];
			var newLabel = $('<label for="'+item.notification+'" class="notification" id="'+item.id+'">'+item.notification+ '</label>');
			var timestampLabel = $('<label for="'+item.date+ '" class="timeStamp">' + item.date +", " + item.time+ '</label>');

			var newFieldset = $('<fieldset>');
			newFieldset.append(newLabel);
			
			var timestampFieldset = $('<p></p>');
			timestampFieldset.append(timestampLabel);
		
			var newCell = $('<td></td>');
			newCell.append(timestampFieldset);
			newCell.append(newFieldset);
						
			var newRow = $('<tr class="heading"></tr>');
			newRow.append(newCell);

			table.append(newRow);		
		}
		
		generateTimeFormat(sortedData);
	});	
}

//This function is used for the functionality of the notification bar.
//It compares the time that the page was loaded to the time the notification was added to the database
//and uses this information the display the number of new notifications
function generateTimeFormat(data) {
	
	var browserTime = beforeload1.getHours() + ":" + beforeload1.getMinutes() + ":" + beforeload1.getSeconds();
	var browseTime = convertTimeFormat(browserTime);


	//Needed because getMonth() is from 0-11 
	var month = beforeload1.getMonth() + 1;
	
	//Browser date
	var browserDateInNumberOfDays = beforeload1.getDate() + month * 30 + beforeload1.getFullYear() * 365;

	var notifications = [];
	
	//Create Simple JSON object
	for(var i = 0; i<data.length; i++){
		var item = data[i];
		notifications.push({"Date": item.date , "Time": item.time});
	}
	
	//Convert Stored Time to New Format
	for(var i=0; i < data.length; i++){
		var dbTime = convertTimeFormat(notifications[i].Time);
		notifications[i].Time = dbTime;
		var dbDate = convertStoredDateToNymberOfDays(notifications[i].Date);
		notifications[i].Date = dbDate;
	}
		
	pushUpdate(notifications, browseTime, browserDateInNumberOfDays);
}

function pushUpdate(notifications, browseTime, browserDateInNumberOfDays){
	//Increment Counter if Notification has been added after browser time
	var counter = 0;
	for(var i=0; i<notifications.length; i++){
		var index = notifications[i];
		if(index.Time > browseTime && index.Date >= browserDateInNumberOfDays)
		counter += 1;	
	}
	
	if(notifications.length>0 && counter != 0){
		$('#alertnotify').remove();
		$('#notificationCount').append('<span id="alertnotify">'+counter+'</span>');
	}
	else{
		$('#alertnotify').remove();
	}
}

function convertTimeFormat(time){
	var rawTime = time;
	var splitRawTime = rawTime.split(':');
	var formatedTime = (+splitRawTime[0]) * 60 * 60 + (+splitRawTime[1]) * 60 + (+splitRawTime[2]);
	return formatedTime;
}

function convertStoredDateToNymberOfDays(date) {
	var rawDate = date;
	var splitRawDate = rawDate.split('-');
	var formatedDate = (+splitRawDate[0]) * 365 + (+splitRawDate[1]) * 30 + (+splitRawDate[2]);
	return formatedDate;
}

function sortTimeDate(data){
	
	var dataToBeSorted = data;
	
	//Sort by Time
	dataToBeSorted.sort(function(a, b) { 
		return (a.time < b.time ? -1 : (a.time > b.time ? 1 : 0)); 
	});
	
	//Sort by date
	dataToBeSorted.sort(function(a, b) { 
		return (a.date < b.date ? -1 : (a.date > b.date ? 1 : 0)); 
	});
	
	//Reverse order to begin with most recent
	dataToBeSorted.reverse(); 
	var sortedData = dataToBeSorted;
	
	return sortedData;
}




