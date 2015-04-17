frm = document.purchaseform;
var sDate = "";
var strDate = "";
var strDateArray = "";
var strDay = "";
var strMonth = "";
var strYear = "";
var intday = "";
var intMonth = "";
var intYear = "";
var booFound = false;
var strSeparatorArray = new Array("-"," ","/",".");
var intElementNr = "";
var err = 0;

function checkDate(datefield) {
	if (chkdate(datefield) == false) {
		datefield.select();
		alert("Please enter a valid date.");
		datefield.focus();
		return false;
	}
	else {
		return true;
	}
}

function chkdate(datefield) {
	sDate = datefield.value;

	if (sDate.length < 1) {
		return true;
	}

	parseDate(sDate);

	if (booFound == false) {
		return false;
	}

	intday = parseInt(strDay, 10);
	if (isNaN(intday)) {
		err = 2;
		return false;
	}

	intMonth = parseInt(strMonth, 10);
	if (isNaN(intMonth)) {
		err = 3;
		return false;
	}

	intYear = parseInt(strYear, 10);
	if (isNaN(intYear) || strYear.length > 4) {
		err = 4;
		return false;
	}

	if (intMonth > 12 || intMonth < 1) {
		err = 5;
		return false;
	}

	if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
		err = 6;
		return false;
	}

	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
		err = 7;
		return false;
	}

	if (intMonth == 2) {
		if (intday < 1) {
			err = 8;
			return false;
		}
		if (LeapYear(intYear) == true) {
			if (intday > 29) {
				err = 9;
				return false;
			}
		}
		else {
			if (intday > 28) {
				err = 10;
				return false;
			}
		}
	}
	return true;
}

function parseDate(strDate) {
	strDateArray = "";
	strDay = "";
	strMonth = "";
	strYear = "";
	intday = "";
	intMonth = "";
	intYear = "";
	booFound = false;
	intElementNr = "";
	err = 0;

	for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
		if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
			strDateArray = strDate.split(strSeparatorArray[intElementNr]);
			if (strDateArray.length != 3) {
				err = 1;
				return false;
			}
			else {
				if (strDateArray[0].length <= 2) {
					strMonth = strDateArray[0];
					strDay = strDateArray[1];
					strYear = strDateArray[2];
				}
				else if (strDateArray[0].length == 4) {
					strYear = strDateArray[0];
					strMonth = strDateArray[1];
					strDay = strDateArray[2];
				}
			}
			booFound = true;
		}
	}

/*	if (booFound == false) {
		if (strDate.length > 5) {
			strMonth = strDate.substr(0, 2);
			strDay = strDate.substr(2, 2);
			strYear = strDate.substr(4);
		}
	} */

	if (strYear.length == 2) {
		strYear = '20' + strYear;
	}
}

function LeapYear(intYear) {
	if (intYear % 100 == 0) {
		if (intYear % 400 == 0) { return true; }
	}
	else {
		if ((intYear % 4) == 0) { return true; }
	}
	return false;
}

function compareDate(laterdate, earlierdate) {
	var ldate = laterdate.value;
	var edate = earlierdate.value;
	
	parseDate(ldate);
	var laterdate = new Date(strYear, strMonth - 1, strDay);

	parseDate(edate);
	var earlierdate = new Date(strYear, strMonth - 1, strDay);

	var difference = laterdate.getTime() - earlierdate.getTime();
	if (difference < 0) {
		return false;
	} else {
		return true;
	}
}