frm = document.purchaseform;
var browser = browserCheck();
var theFocus = null;
var allowEdit = true;
var popupAction = null;
var popupHandler = null;
var popupUrl = null;
var popupUserId = null;
var popupOrganizationId = null;
var popupParameters = "";
var doc_window = null;
var lookup_window = null;
var contextPath = "";

function browserCheck() {
	var bw = "";
	if (navigator.appName == "Netscape") {
		bw = "NS";
		if (navigator.appVersion.charAt(0) == "5") {
			bw = "NS6";
		}
	}
	else if (navigator.appVersion.indexOf("MSIE") != -1) {
		bw = "IE";
	}
	return bw;
}

function validateForm() {
	return true;
}

function doSubmit(page, handlerList) {
	var handlerValue = "";

	if (handlerList == null) {
		handlerList = "DoNothing"
	}

	if (handlerList.indexOf(";") > 0) {
		if (handlerList.lastIndexOf(";") != handlerList.length) {
			handlerList = handlerList + ";";
		}
		while (handlerList.length > 0) {
			var handlerName = handlerList.substring(0, handlerList.indexOf(";"));
			if (handlerList.indexOf(";") < handlerList.length) {
				handlerList = handlerList.substring(handlerList.indexOf(";") + 1);
			}
			else {
				handlerList = "";
			}
			if (handlerName.length > 0) {
				handlerName = handlerName + "Handler";
				handlerValue = handlerValue + handlerName + ";";
			}
		}
	}
	else {
		handlerValue = handlerList + "Handler;";
	}

	frm.handler.value = handlerValue;
	frm.successPage.value = page;

	if (validateForm()) {
		resetDisabledFlds();
		resetDummyFields();

		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";
		frm.target = "_self"
		frm.submit();
	}
}

function isEmpty(s) {
	return ((s == null) || (s.length == 0) || (s == ' '));
}

function browserTest() {
	thisapp=navigator.appName;
       	thisversion=navigator.appVersion;
	return thisapp;
}

function logOff() {
	if (confirm('Please Confirm Log Off.')) {
		doSubmit('index.jsp', 'VendorLogoff');
	}
}

function helpMe() {
	unavailable();
}

function unavailable() {
	alert("This option is not yet available.");
}

function doSubmitToLookup (url, handler, w, h) {
	popupUrl = url;
	popupHandler = handler;
	popupUserId = frm.userId.value;
	popupOrganizationId = frm.organizationId.value;

	openWindow(url, w, h);
}

function openWindow (url, w, h) {
	if (browser == "Netscape") {
		if (w == undefined) { w = 'WIDTH=500'; }
		if (h == undefined) { h = 'HEIGHT=300'; }
		if (theFocus == undefined) { theFocus = 'lookup'; }
	}
	else {
		if (w == null) { w = 'WIDTH=500'; }
		if (h == null) { h = 'HEIGHT=300'; }
		if (theFocus == null) { theFocus = 'lookup'; }
	}
	var winspecs = w +","+ h +",resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";

	lookup_window = window.open(contextPath + "/system/popup.jsp", "lookup_window", winspecs);

	if (theFocus == 'main') {
		self.focus();
	}
	else {
		lookup_window.focus();
	}

	if (lookup_window.opener == null) lookup_window.opener = window;
	self.name = "main";
}

function closeOpenWindows() {
	var browser = browserTest();

	if (lookup_window != null) {
		if (browser == "Netscape") {
			if (lookup_window.closed == false) {
				lookup_window.close();
			}
		}
		else {
			lookup_window.close();
		}
	}

	if (doc_window != null) {
		if (browser == "Netscape") {
			if (doc_window.closed == false) {
				doc_window.close();
			}
		}
		else {
			doc_window.close();
		}
	}
}

function thisLoadPopup() {
	return;
}

function thisLoad() {
	return;
}

function upperCase (formField) {
	var x = formField.value;
	formField.value = x.toUpperCase();
}

function openInfoWindow (url, w, h) {
	if (browser == "Netscape") {
		if (w == undefined) { w = 'WIDTH=500'; }
		if (h == undefined) { h = 'HEIGHT=300'; }
		if (theFocus == undefined) { theFocus = 'lookup'; }
	}
	else {
		if (w == null) { w = 'WIDTH=500'; }
		if (h == null) { h = 'HEIGHT=300'; }
		if (theFocus == null) { theFocus = 'lookup'; }
	}

	var winspecs = w +","+ h +",resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
	info_window = window.open(url, "info_window", winspecs);

	self.focus();

	if (info_window.opener == null) info_window.opener = window;
	self.name = "main";
}

function setCheckbox(fld,x) {
	fld.value = 'N';
	if ( frm.c_checkbox[x].checked ) {
		fld.value = 'Y';
	}
	return true;
}

// this function allows you to specify the desired number of decimal places
function nformat (g, d){
	var y = 0.505;
	var i = 0;
	if (g < 0) {
		y = -0.505;
	}
	while (i < d) {
		y = y * .1;
		i++;
	}
	g = eval(g);
	y = eval(y);
	g = g + y;
	var t = new String(g);
	var x = t.indexOf(".");

	if (d == 0) {
		x = x - 1;  /* so decimal does not show */
	}
	return t.substring(0,x+d+1);
}

function nfilter( objectFld )
{
	var cmp = "0123456789.-";
	var x = objectFld.value;
	var w = "";

	for ( var i = 0; i < x.length; i++) {
		tst = x.substring(i,i+1);
		if (cmp.indexOf(tst) >= 0) { w += tst; }
	}
	if ( w.length != x.length)
		objectFld.value = w;
	if ( w.length == 0 )
		w = "0";
	return w;
}

function nfiltervalue( x ) {
	var cmp = "0123456789.-";
	var w = "";

	for ( var i = 0; i < x.length; i++) {
		tst = x.substring(i,i+1);
		if (cmp.indexOf(tst) >= 0) { w += tst; }
	}
	if ( w.length == 0 )
		w = "0";
	return w;
}

function checkInputSettings()
{
	var e = frm.elements;

	for (i = 0; i < e.length; i++)
	{
		e.item(i).readOnly = true;
		e.item(i).contentEditable = false;
		if (e.item(i).type != "hidden")
		{
			e.item(i).disabled = true;
		}
	}

	frm.allowBrowse.value = "false";
}

function browseMe (formField, tableType ) {
	var url = "/browse/browse_popup.jsp";
	fldObject = formField;
	fldFromObject = null;

	handler = "StdTableRetrieveBy";
	popupParameters = "StdTable_tableType=SHP";

	doSubmitToLookup(url, handler);
}

function browse(x) {
	frm.browseName.value = x;

	doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
}

function browseFilter(x) {
	frm.browseName.value = x;

	doSubmit('/browse/browse_filter_options.jsp', 'BrowseGetOptions');
}

function browseLookup(formField, xml) {
	if(formField.indexOf('vendContactCode') > 0){
		popupParameters = popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;
	}
	else if(formField.indexOf('Account_') >= 0 || formField == 'InvoiceLine_umCode'){
		popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
	}
	else{
		popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;
	}
	doSubmitToLookup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
}

function browseStd(frmField, udf)
{
	if ((udf.indexOf('FBLK') >=0) || (udf.indexOf('FCOL') >=0) || (udf.indexOf('FGRD') >=0) || (udf.indexOf('FINK') >=0))
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;index=" + currentRow + ";";
	}
	else
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;"
	}
	//frm.StdTable_tableType.value = udf;
	browseLookup(frmField, 'stdtable');
}

function browseRfqVendors()
{
	frm.browseName.value = "rfqvendor";
	doSubmit('/browse/browse_filter_rfqvendor.jsp', 'BrowseGetOptions');
}

//function trim -- removes leading and trailing spaces
function trim (x) {
	while(''+x.value.charAt(x.value.length-1)==' ') {
		x.value=x.value.substring(0,x.value.length-1);
	}
	while(''+x.value.charAt(0)==' ') {
		x.value=x.value.substring(1,x.value.length);
	}

	return x.value;
}

function verifyAction(msg) {
	if ( confirm(msg) )
	{
		return true;
	}
	return false;
}

function browse(x) {
	frm.browseName.value = x;

	doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
}

function limit(field, maxlimit)
{
	if (field.value.length > maxlimit) {
		// field is too long...trim it!
		field.value = field.value.substring(0, maxlimit);
	}
}
function setHiddenFields(hiddenFields) {
	var myCell = document.getElementById("hiddenFields");
	myCell.innerHTML = hiddenFields;
}
function getHiddenFields() {
	return  document.getElementById("hiddenFields").innerHTML;
}

function resetDisabledFlds() {
  var e = frm.elements;

  for (i = 0; i < e.length; i++)
  {
    if (e.item(i).disabled) {
      e.item(i).readOnly = true;
      e.item(i).disabled = false;
    }
    e.item(i).className = "disabledTxtBox";
  }
}

function searchOrders() {
	if (frm.allowBrowse)
	{
		frm.allowBrowse.value = "true";
	}
	var vendorId = frm.vendorId.value;
	clearFilters();
	setOriginalFilter("PoHeader_vendorId", "=", vendorId);
	browse("poheader");
}

function searchOrdersPendingAck() {
	if (frm.allowBrowse) {
		frm.allowBrowse.value = "true";
	}
	clearFilters();
	browse("po-pending-acknowledgement");
}

function searchInvoices() {
	if (frm.allowBrowse)
	{
		frm.allowBrowse.value = "true";
	}
	var vendorId = frm.vendorId.value;
	clearFilters();
	setOriginalFilter("InvoiceHeader_vendorId", "=", vendorId);
	browse("invoiceheader");
}

function resetDummyFields() {
	var dummyFields = document.getElementById("dummyFields");
	if (dummyFields != null && dummyFields != undefined) {
		dummyFields.innerHTML = "";
	}
}

function searchSales() {
	setOriginalFilter("SaleHeader_status", "=", "8005");
	browse("sale-auctions");
}

function invoiceSave()
{
  /*
    this method is called in the wizard view process when the user clicks on the save button
    current method is the method to save that page, currentprocessmethod is the method to retrieve that page
  */
  doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
}


function setupHandlers(handlerList) {
	var handlerValue = "";

	if (handlerList == null) {
		handlerList = "DoNothing"
	}

	if (handlerList.indexOf(";") > 0) {
		if (handlerList.lastIndexOf(";") != handlerList.length) {
			handlerList = handlerList + ";";
		}
		while (handlerList.length > 0) {
			var handlerName = handlerList.substring(0, handlerList.indexOf(";"));
			if (handlerList.indexOf(";") < handlerList.length) {
				handlerList = handlerList.substring(handlerList.indexOf(";") + 1);
			}
			else {
				handlerList = "";
			}
			handlerName = handlerName + "Handler";
			handlerValue = handlerValue + handlerName + ";";
		}
	}
	else {
		handlerValue = handlerList + "Handler;";
	}

	frm.handler.value = handlerValue;
}
