	var b_submit = false;

	function start()
	{
		timeRemainingSetup();
		setup(frm.due_date.value);
		repeat();
	}

	function timeRemainingSetup() {
		var dueDate = frm.due_date.value;
		var dueTime = frm.due_time.value
		
		if ( dueTime.length > 0 ) {
			if (dueTime == "24:00") {
				dueDate = dueDate + "  00:00";
			}
			else {
				dueDate = dueDate + "  " + dueTime;
			}
		} else {
			dueDate = dueDate + "  00:00";
		}
		frm.due_date.value = dueDate;
	}

	function repeat() 
	{
//		if (!b_submit) {
			down();
			setTimeout("repeat()",1000);
//		}
	}

	function setup(day)
	{
		if ( (browserCheck() == "NS6") || (browserCheck() == "NS") ) {
			today	= (getDateNS(frm.today.value)).getTime();
			the_day = (getDateNS(day)).getTime();
		}
		else {
			today = (new Date(frm.today.value)).getTime();
			the_day = (new Date(day)).getTime();
		}
		time = (the_day - today);
		frm.time2.value=time;
	}

	function down()
	{
		frm.time2.value = frm.time2.value - 1000;
		time = document.purchaseform.time2.value;
		days = (time - (time % 86400000)) / 86400000;
		time = time - (days * 86400000);
		hours = (time - (time % 3600000)) / 3600000;
		time = time - (hours * 3600000);
		mins = (time - (time % 60000)) / 60000;
		time = time - (mins * 60000);
		secs = (time - (time % 1000)) / 1000;
		if(days==1) out = "1 day, ";
		else out = days+" days, ";
		if(hours < 10) out = out+"0";
		out = out+hours+":";
		if(mins < 10) out = out+"0";
		out = out+mins+":";
		if(secs < 10) out = out+"0";
		out = out+secs;
		if(days+hours+mins+secs> 0) 
		{
			frm.time.value = out;
		}
		else if (!b_submit)
		{
			var area = document.getElementById("auctionCountdownText");
			area.style.visibility = "hidden";
			area.style.display = "none";
			frm.time.value = "Closed";
			doSubmit('', 'RfqRetrieve;SetEventPage');
			b_submit = true;
		}
	}