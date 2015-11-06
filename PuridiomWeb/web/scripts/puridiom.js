var browser = browserCheck();
var fieldsToAudit =  new Array();
var theFocus = null;
var allowEdit = true;
var popupAction = null;
var popupHandler = null;
var popupUrl = null;
var popupUserId = null;
var popupMailId = null;
var popupOrganizationId = null;
var popupParameters = "";
var openedFromPopup = false;
var validFileTypes = "";
var unloadComplete = false;
var strEnableAuditTrail = "Y";

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
	if(myPage.indexOf("req_item") >= 0 && frm.RequisitionLine_unitPrice != null) {
		frm.RequisitionLine_unitPrice.value = removeCommas(frm.RequisitionLine_unitPrice.value);
	}
	if(myPage.indexOf("req_item") >= 0 && frm.computed_subtotal != null) {

		frm.computed_subtotal.value = removeCommas(frm.computed_subtotal.value);
	}

  frm.successPage.value = page;
  if(myPage.indexOf("main_menu")>=0){
	frm.UserPreference_value.value = frm.userStickyNotes.value.replace(/\'/g,"&quot");
	if(frm.UserPreference_value.value != frm.originalUserStickyNotes.value){
		handlerList = "UserPreferenceUpdate;" + handlerList;
	}
  }
  setupHandlers(handlerList);
  if (validateForm())
  { 
	if(strEnableAuditTrail == "Y"){
		buildAuditedFields();
	}
    resetDisabledFlds();
    resetDummyFields();
//    validateFormPpe($(frm).serialize());
//    var fieldsA = $(frm).serializeArray();
    document.body.style.cursor = "wait";
    frm.target = "_self"
    frm.submit();
  }
}

function validateFormPpe(formData) {
//	 console.log(formData);
    // initialize
//	var md = new KJUR.crypto.MessageDigest({"alg": "md5", "prov": "cryptojs"});
//	//update data
//	//md.upateString(requestID);
//	md.updateString(formData);
//	//SHA1 hash result of string aaa which will be 7e240de74fb1ed08fa08d38063f6a6a91462a815
//	var hashValueHex = md.digest();
//	//alert(frm['sfbm'].value);
//	if (frm['sfbm']){
//		frm['sfbm'].value = frm['sfbm'].value + 'M' + hashValueHex
//	}
//	//alert(frm['sfbm'].value);
//	console.log(hashValueHex);
}

function isEmpty(s) {
  return ((s == null) || (trimString(s).length == 0));
}

function browserTest() {
  thisapp=navigator.appName;
         thisversion=navigator.appVersion;
  return thisapp;
}

function logOff() {
  var returnUrl = frm.ReturnURL.value ;
  if (returnUrl == null) returnUrl = "" ;
  if (confirm('Please Confirm Log Off.')) {
  	if (returnUrl.length == 0) {
	    doSubmit('index.jsp', 'UserLogoff');
	} else {
		doSubmit('portal-return.jsp', 'UserLogoff') ;
	}
  }
}

function helpMe() {
  unavailable();
}

function unavailable() {
  alert("This option is currently inactive.");
}

function doSubmitToPopupFull(url, handler) {
	doSubmitToPopup(url, handler, "1200", "1200")
}

function doSubmitToPopup (url, handler, w, h) {
  popupUrl = url;
  popupHandler = handler;
  popupUserId = frm.userId.value;
  popupMailId = frm.mailId.value;
  popupOrganizationId = frm.organizationId.value;

  w = w.toLowerCase().replace("width=","") ;
  w = w.toLowerCase().replace("px","") ;
  h = h.toLowerCase().replace("height=","") ;
  h = h.toLowerCase().replace("px","") ;

  w = parseInt(w) ;
  h = parseInt(h) ;

  if( typeof( window.innerWidth ) == 'number' ) {
    // Non-IE
    wWidth = window.innerWidth;
    wHeight = window.innerHeight;
  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    // IE 6+ in 'standards compliant mode'
    wWidth = document.documentElement.clientWidth;
    wHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    // IE 4 compatible
    wWidth = document.body.clientWidth;
    wHeight = document.body.clientHeight;
  }

  // Make it fit
  if (w > wWidth) {
  	w = wWidth - 60
  }
  if (h > wHeight) {
  	h = wHeight - 60
  }

  if (frm && frm.editFieldsApprover) {
	  popupParameters = popupParameters + ";editFieldsApprover=" + frm.editFieldsApprover.value;
  }
  showPopWin('system/popup_html.jsp', w, h, null, true);
}

function closePopup() {
	window.top.hidePopWin();
}

function doSubmitToLookup (url, handler, w, h) {
  popupUrl = url;
  popupHandler = handler;
  popupUserId = frm.userId.value;
  popupMailId = frm.mailId.value;
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

  lookup_window = window.open("system/popup_html.jsp", "lookup_window", winspecs);

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
  // currently we are not opening any popup windows
  return;
}

function thisLoadPopup() {
  return;
}
function thisUnLoadPopup() {
  return;
}

function thisUnLoad() {
	unloadComplete = true;
	return;
}

function upperCase (formField) {
  var x = formField.value;
  formField.value = x.toUpperCase();
}

function lowerCase (formField) {
  var x = formField.value;
  formField.value = x.toLowerCase();
}


function getNewInfo(x, fld, row) {
  var next = "";
  var fldName = fld.name;
  var code = fld.value;
  var code2 = "";
  var vendorId = "";
  var sup = "";
  var submit = false;
  var type = "";
  var setFld = "";
  var setFld2 = "";
  var handlerName = ""

  if (!isEmpty(fld.value)) {
    submit = true;
  }
  if (fldName == "InspectionLine_inspectCode")
  {
	handlerName = "InspectionCodeRetrieve";

	if (maxRows > 1) {
		if (!isEmpty(frm.InspectionLine_inspectCode[row].value))
		{
			submit = true;
		}
		else
		{
			frm.InspectionCode_description[row].value = "";
		}
	} else {
		if (!isEmpty(frm.InspectionLine_inspectCode.value))
		{
			submit = true;
		}
		else
		{
			frm.InspectionCode_description.value = "";
		}
	}
  }

  if (fldName == "ShipTo_shipToCode")
  {
    //handlerName="AddressRetrieveShipTo1";

	if (maxRows > 1) {
	    if (!isEmpty(frm.ShipTo_shipToCode[row].value))
	    {
	      submit = true;
	    }
	    else
	    {
	      frm.Address_addressLine1[row].value = "";
	    }
	} else {
	    if (!isEmpty(frm.ShipTo_shipToCode.value))
	    {
	      submit = true;
	    }
	    else
	    {
	      frm.Address_addressLine1.value = "";
	    }
	}
    type = "addr";
  }
  else if (fldName.indexOf("shipTo") >= 0)
  {
    //handlerName="AddressRetrieveShipTo2";

    if (fldName.substring(0,3) == "Req")
    {
      next = "RequisitionHeader_vendorCode";
    }
    else if (fldName.substring(0,2) == "Po")
    {
      next = "PoHeader_shipToContact";
    }

    if (! submit) {
      frm.Address_addressLine1.value = "";
      frm.Address_addressLine2.value = "";
      frm.Address_addressLine3.value = "";
      frm.Address_addressLine4.value = "";
      frm.Address_city.value = "";
      frm.Address_state.value = "";
      frm.Address_postalCode.value = "";
      frm.Address_country.value = "";

      if (fldName == "PoHeader_shipToCode") {
      	frm.PoHeader_shipToInv.value = "";
      }
    }
    type = "addr";
  }
  else if (fldName == "BillTo_billToCode")
  {
	handlerName="AddressRetrieveBillTo";

	if (maxRows > 1) {
	    if (!isEmpty(frm.BillTo_billToCode[row].value))
	    {
	      submit = true;
	    }
	    else
	    {
	      frm.Address_addressLine1[row].value = "";
	    }
	} else {
	   if (!isEmpty(frm.BillTo_billToCode.value))
	    {
	      submit = true;
	    }
	    else
	    {
	      frm.Address_addressLine1.value = "";
	    }
	}
    type = "addr";
  }
  else if (fldName.indexOf("billTo") >= 0)
  {
    handlerName = "AddressRetrieveBillTo";

    if (fldName.substring(0,2) == "Po") {
      next = "PoHeader_billToContact";
    }

    if (! submit) {
      frm.Address_addressLine1.value = "";
      frm.Address_addressLine2.value = "";
      frm.Address_addressLine3.value = "";
      frm.Address_addressLine4.value = "";
      frm.Address_city.value = "";
      frm.Address_state.value = "";
      frm.Address_postalCode.value = "";
      frm.Address_country.value = "";
    }
    type = "addr";
  }
  else if (fldName == "RequisitionLine_vendorId")
  {
    handlerName = "VendorRetrieveDefaults";

    if (! submit) {
    	if (frm.RequisitionLine_vendorName) {
    		frm.RequisitionLine_vendorName.value = "";
    	}
    	if (frm.RequisitionLine_vendContactCode) {
    		frm.RequisitionLine_vendContactCode.value = "";
    	}
    }
    type = "vendor";
    vendorId = code;
    code = "";
  }
  else if (fldName.indexOf("vendorId") >= 0)
  {
    handlerName = "VendorRetrieveDefaults";

    if (fldName.substring(0,3) == "Req") {
      next = "RequisitionHeader_vendContactCode";
    }
    else if (fldName.substring(0,2) == "Po") {
      next = "PoHeader_vendContactCode";
    }

    if (! submit) {
      frm.Address_addressLine1.value = "";
      frm.Address_addressLine2.value = "";
      frm.Address_addressLine3.value = "";
      frm.Address_addressLine4.value = "";
      frm.Address_city.value = "";
      frm.Address_state.value = "";
      frm.Address_postalCode.value = "";
      frm.Address_country.value = "";

      if (fldName.substring(0,3) == "Req") {
        frm.RequisitionHeader_vendContactCode.value = "";
        frm.RequisitionHeader_vendorAttn.value = "";
        frm.RequisitionHeader_contactPhone.value = "";
        frm.RequisitionHeader_contactMobilePhone.value = "";
      /*
		 * if (frm.as_pcard_code != null) { frm.as_pcard_code.value = "";
		 * frm.rqh_pcard_name.value = ""; frm.rqh_pcard_holder.value = "";
		 * frm.rqh_pcard_number.value = ""; frm.rqh_pcard_expires.value = "";
		 * frm.rqh_pcard_req.value = ""; setPcardImg(); }
		 */
        if (frm.Vendor_vendTerms) {
			frm.Vendor_vendTerms.value = "";
		}

      	if (frm.Contact_faxNumber) {
			frm.Contact_faxNumber.value = "";
		}

		if (frm.Contact_emailAddr) {
			frm.Contact_emailAddr.value = "";
		}

		if (frm.Contact_info1) {
			frm.Contact_info1.value = "";
		}

		if (frm.Contact_info2) {
			frm.Contact_info2.value = "";
		}

        frm.RequisitionHeader_vendorId.focus();
      }
      else if (fldName.substring(0,2) == "Po") {
        frm.PoHeader_vendContactCode.value = "";
        frm.PoHeader_contactAddr.value = "";
        frm.PoHeader_contactName.value = "";
        frm.PoHeader_contactPhone.value = "";
        frm.PoHeader_contactMobilePhone.value = "";
        frm.PoHeader_email.value = "";
		frm.Contact_info1.value = "";
		frm.Contact_info2.value = "";

        if (frm.PoHeader_ediOrder_option) {
        	frm.PoHeader_ediOrder_option.value = "P";
        }
        if(frm.PoHeader_ediOrder) {
        	frm.PoHeader_ediOrder.value = "P";
        }
        if (frm.PoHeader_faxNumber && document.getElementById("showFaxNumber"))
        {
        	frm.PoHeader_faxNumber.value = "";
        	document.getElementById("showFaxNumber").style.visibility = "hidden";
        	document.getElementById("showFaxNumber").style.display    = "none";
        	document.getElementById("showFaxNumber").style.height     = 0;
        }
        frm.VendorInsurance_vendorId.value = "";
        frm.PoHeader_vendContactCode.focus();
      }
      else if (fldName.substring(0,3) == "Rec") {
		if (frm.ReceiptHeader_vendorName)
			frm.ReceiptHeader_vendorName.value = "";
		if (frm.Contact_displayName)
			frm.Contact_displayName.value = "";
		if (frm.Contact_emailAddr)
			frm.Contact_emailAddr.value = "";
		if (frm.Contact_phoneNumber)
			frm.Contact_phoneNumber.value = "";
		if (frm.Contact_mobileNumber)
			frm.Contact_mobileNumber.value = "";
		if (frm.Contact_faxNumber)
			frm.Contact_faxNumber.value = "";
      }
    }
    type = "vendor";
    vendorId = code;
    code = "";
  }
  else if ( x == "contact" ) {
    handlerName = "ContactRetrieveWithAddress";

    if (fldName.substring(0,3) == "Req") {
      vendorId = frm.RequisitionHeader_vendorId.value;
    }
    else if (fldName.substring(0,2) == "Po") {
      vendorId = frm.PoHeader_vendorId.value;
    }

    if (!submit) {
      // Clear vendor attention field and retrieve default address
      if (fldName.substring(0,3) == "Req") {
        frm.RequisitionHeader_vendorAttn.value = "";
      }
      else if (fldName.substring(0,2) == "Po") {
        frm.PoHeader_contactName.value = "";
      }

      submit = true;
    }
    type = "contact";
  }
  else if ( x == "requisitioner" ) {

    if (fldName.substring(0,3) == "Req") {
      next = "RequisitionHeader_requiredDate";
    }
    if (!submit) {
      frm.as_requisitionerName.value = "";
    }
    type = "requisitioner";
    setFld = "as_requisitionerName";
  }
  else if ( x == "warehouse" ) {
     if (!submit) {
      if(frm.RequisitionLine_itemNumber!=undefined){
      	code2 = frm.RequisitionLine_itemNumber.value;
      }
    }
    type = "warehouse";
    setFld = "accountLine";
    if(code2 == "") return;
  }
  else if ( x == "commodity" ) {
     if (!submit) {
      frm.as_commodityName.value = "";
      if(frm.RequisitionLine_udf10Code!=undefined){
      	frm.RequisitionLine_udf10Code.value = "";
      }
    }
    type = "commodity";
    setFld = "as_commodityName";
    if(frm.RequisitionLine_udf10Code!=undefined){
    	setFld2 = "RequisitionLine_udf10Code";
    }
  }
   else if ( x == "department" ) {
     if (!submit) {
      if(frm.as_commodityName!=undefined){
    	 frm.as_commodityName.value = "";
      }
      if(frm.RequisitionHeader_departmentCode!=undefined){
      	frm.RequisitionHeader_departmentCode.value = "";
      }
    }
    type = "department";
    setFld = "RequisitionHeader_buyer";
    setFld2 = "as_buyerName";
  }
  else if ( x == "buyer" ) {
    if (fldName.substring(0,3) == "Req") {
      next = "RequisitionHeader_billToContact";
    }

    if (!submit) {
      frm.as_buyerName.value = "";
    }
    type = "buyer";
    setFld = "as_buyerName";
  }
  else if ( x == "authby" ) {
    if (fldName.substring(0,3) == "Req") {
      next = "RequisitionHeader_routing";
    }

    if (!submit) {
      frm.as_authbyName.value = "";
    }
    type = "authby";
    setFld = "as_authbyName";
  }
  else if ( x == "receiver" ) {
    if (fldName.substring(0,3) == "Rec") {
      next = "ReceiptHeader_receiptNotes";
    }

    if (!submit) {
      frm.as_forwardToName.value = "";
    }
    type = "receiver";
    setFld = "as_forwardToName";
  }
  else if ( x == "user" ) {
    if (fldName.indexOf("deptManager") > 0) {
      if (!submit) {
        frm.as_managerName.value = "";
      }
      type = "manager";
      setFld = "as_managerName";
    }
    else if (fldName.indexOf("deptApprv") > 0) {
		var appNumber = fldName.substring(fldName.length - 1, fldName.length);
		setFld = "as_deptApprv" + appNumber + "Name";
		setFld2 = "Department_apprv" + appNumber + "Amount";
		type = "manager";
		if (!submit) {
			document.getElementById(setFld).value = "";
			document.getElementById(setFld2).value = "";
		}
    }
    else
    {
      if (!submit) {
        frm.as_ownerName.value = "";
      }
      type = "owner";
      setFld = "as_ownerName";
    }
  }
  else if ( x == "approver" ) {
    if (!submit) {
      frm.as_approverName.value = "";
    }
    type = "approver";
    setFld = "as_approverName";
  }
  else if ( x == "inspector" ) {
	    if (!submit) {
	      frm.inspectorName.value = "";
	    }
	    type = "inspector";
	    setFld = "inspectorName";
	  }
  else if ( x == "engineer" ) {
	    if (!submit) {
	      frm.engineerName.value = "";
	    }
	    type = "engineer";
	    setFld = "engineerName";
	  }
  else if ( x == "remoteinspector" ) {
	    if (!submit) {
	      frm.remoteInspName.value = "";
	    }
	    type = "user";
	    setFld = "remoteInspName";
	  }
  else if ( x == "evaluator" ) {
    if (!submit) {
      frm.as_name.value = "";
    }
    type = "evaluator";
    setFld = "as_name";
  }
  else {
    return;
  }
  if (submit) {
  	if (isEmpty(handlerName)) {
  		handlerName = "DoNothing";
  	}
    if (type == "addr") {
		popupParameters = "Address_addressCode=" + code + ";as_type=" + x + ";as_code=" + code + ";as_next=" + next + ";as_row=" + row;

		setLookupParameters('/base/get_addr_info.jsp', handlerName);
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
    }
    else if (type == "vendor") {
		popupParameters = "Vendor_vendorId=" + vendorId + ";Contact_contactCode=" + code + ";as_type=" + x + ";as_code=" + code + ";as_next=" + next;

		setLookupParameters('/base/get_vendor_info.jsp', handlerName);
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
    }
    else if (type == "contact") {
		popupParameters = "Contact_vendorId=" + vendorId + ";Contact_contactCode=" + code + ";as_type=" + x + ";as_code=" + code + ";as_next=" + next;

		setLookupParameters('/base/get_vendor_info.jsp', handlerName);
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
    }
    else {
    	var str = new String;
    	str = code.substring(0,1);
        if(str == '@'){
        	x='poolUser';
        	popupParameters = "as_type=" + x + ";as_row=" + row + ";as_code=" + code.substring(1) + ";as_code2=" + code2 +
    		  				  ";as_next=" + next + ';as_supcode=' + sup + ';as_fld=' + setFld + ';as_fld2=' + setFld2;
    		setLookupParameters('/base/get_info.jsp', handlerName);
    		displayArea('getInfoFrame');
    		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
        } else {
        	popupParameters = "as_type=" + x + ";as_row=" + row + ";as_code=" + code + ";as_code2=" + code2 +
						  ";as_next=" + next + ';as_supcode=' + sup + ';as_fld=' + setFld + ';as_fld2=' + setFld2;
			setLookupParameters('/base/get_info.jsp', handlerName);
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
        }
    }
    submit = false;
  }
}

function setLookupParameters(url, handlerName) {
		popupUrl = url;
		popupHandler = handlerName;
		popupUserId = frm.userId.value;
		popupMailId = frm.mailId.value;
		popupOrganizationId = frm.organizationId.value;
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
  if (x == 0 && !frm.c_checkbox[x]) {
  	if ( frm.c_checkbox.checked ) {
    fld.value = 'Y';
  }
  }
  else if ( frm.c_checkbox[x].checked ) {
    fld.value = 'Y';
  }
  return true;
}

function setFlagFromCkbox(ckbox, fld) {
  if (ckbox.checked) {
    fld.value = "Y";
  } else {
    fld.value = "N";
  }
}

function setFlagFromCkbox(ckbox, fld, row) {
  if (ckbox.checked) {
    fld[row].value = "Y";
  } else {
    fld[row].value = "N";
  }
}

// this function allows you to specify the desired number of decimal places
function nformat (g, d){
  var t = new String(Math.round(g*Math.pow(10,d))/Math.pow(10,d));
  var x = t.indexOf(".");

  if (d == 0) {
  	if (x == -1) {
  		x = t.length;
  	} else {
		x = x - 1;  /* so decimal does not show */
	}
  }
  else {
	  var sg = new String(t);
	  var decs = 0;
	  var i = 0;

	  if (x >=0) {
	  	decs = (sg.substring(x,sg.length)).length;
	  }

	  while(decs <= d) {
	  	if (decs == 0) {
		  	sg = sg + ".0";
	  	} else {
		  	sg = sg + "0";
		}
	  	decs++;
	  }
	  t = sg;
	  x = t.indexOf(".");
  }

  return t.substring(0,x+d+1);
}

function checkDecimals (field, qtydecs){
	var decs = 0;
	var t = new String(field.value);
	var x = t.indexOf(".");
 
  	if (x > -1) {
  		decs = (t.substring(x+1,t.length)).length;
		if(decs > qtydecs){
			field.value = t.substring(0,x+qtydecs+1);
			addUp ();
		}
  	} 
}

function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function removeCommas( strValue ) {
	var objRegExp = /,/g; //search for commas globally
	//replace all matches with empty strings
	return strValue.replace(objRegExp,'');
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

    if (e.item(i).name == "RequisitionHeader_estimatedCost")
    {
    	e.item(i).readonly = false;
    	e.item(i).contentEditable = true;
    	e.item(i).disabled = false;
    }
  }

  frm.allowBrowse.value = "false";
}

function checkInputSettingsEdit()
{
  var e = frm.elements;

  for (i = 0; i < e.length; i++)
  {
    e.item(i).readOnly = false;
    e.item(i).contentEditable = true;
    if (e.item(i).type != "hidden")
    {
      e.item(i).disabled = false;
    }
  }

  frm.allowBrowse.value = "true";
}

function browseMe (formField, tableType ) {
  var url = "/browse/std_browse.jsp";
  fldObject = formField;
  fldFromObject = null;

  handler = "StdTableRetrieveBy";
  popupParameters = "StdTable_tableType=SHP";

  doSubmitToPopup(url, handler, 'WIDTH=700px', 'HEIGHT=500px');
}

function browse(x) {
  frm.browseName.value = x;

  doSubmit('/browse/browse.jsp', 'BrowseRetrieve');
}

function browseFilter(x, pg) {
	frm.browseName.value = x;

	if (pg == undefined) {
		pg = '/browse/browse_filter_options.jsp';
	}

	doSubmit(pg, 'BrowseGetOptions');
}

function reportFilter(x) {
  frm.browseName.value = x;

  popupParameters = "browseName=" + x;
  doSubmit('/browse/browse_filter_report_options.jsp', 'ReportGetOptions');
}

function reportQueueFilter(jasperFile, filterXml, reportTitleName) {
  frm.browseName.value = filterXml;
  frm.jasperFile.value = jasperFile;
  frm.reportTitleName.value = reportTitleName;

  doSubmit('/browse/browse_filter_reportqueue_options.jsp', 'ReportQueueMails');
}

function reportToModify(x) {
  frm.browseName.value = x;
  popupParameters = "browseNamer" + x;
  doSubmit('/browse/browse_filter_reportqueue_options_toModify.jsp','GetFieldsForReport;GetEntityMapForModule;ReportQueueMails');
}

function reportToModifyMakeGroup(x) {
  frm.browseName.value = x;
  popupParameters = "browseNamer" + x;
  doSubmit('/browse/browse_filter_reportqueue_options_toModify_makeGroup.jsp','ReportModifyMakeGroup;ReportQueueMails');
}

function reportToModifySelectEntity(x) {
  frm.browseName.value = x;
  popupParameters = "browseNamer" + x;
  doSubmit('/browse/browse_filter_reportqueue_options_toModify.jsp','EntityAtributesReflect;GetFieldsForReport;GetEntityMapForModule;ReportQueueMails');
}

function browseLookup(formField, xml, allowBrowsePopup) {
	var allowBrowseParameter = (allowBrowsePopup != undefined) ? new Boolean(allowBrowsePopup).toString() : frm.allowBrowse.value;

  if(formField.indexOf('vendContactCode') > 0){
    popupParameters = popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;
  }
  else if(formField.indexOf('Account_') >= 0 || formField == 'InvoiceLine_umCode' || formField == 'PerformanceDetail_evaluator'){
    popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
  }
  else if (formField.indexOf("PoLine_umCode") == 0) {
	// added on 08.31.05 for PoAdmin changes
  	popupParameters = popupParameters + "formField=" + formField +";browseName=" + xml;
  }
  else if (formField.indexOf("AssetLocation_") >= 0 && frm.AssetLocation_sequenceNo.value != "") {
  	popupParameters = popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=false";
  }
  else if (frm.organizationId.value == "QRI06P" && (formField.indexOf("_shipToCode") >= 0 || formField.indexOf("_billToCode") >= 0)) {
  	if (frm.country && !isEmpty(frm.country.value)) {
	  	popupParameters = popupParameters + "colname=Address_country;operator==;filter_txt=" + frm.country.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
	} else if (frm.UserProfile_locale && !isEmpty(frm.UserProfile_locale.value)) {
	  	popupParameters = popupParameters + "colname=Address_country;operator==;filter_txt=" + frm.UserProfile_locale.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
	}
  	popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;
  }
  else if(xml.indexOf("bomstage") >= 0){
    popupParameters = popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;
    popupParameters = popupParameters + ";colname=BomRouting_parentItem;operator==;filter_txt=" + frm.BomComponent_parentItem.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
  }
  /*
	 * else if(formField.indexOf('Catalog_poNumber') >= 0){ popupParameters =
	 * popupParameters + "formField=" + formField +";browseName=" + xml +
	 * ";allowBrowse=" + frm.allowBrowse.value; popupParameters =
	 * popupParameters + ";colname=PoHeader_vendorId;operator==;filter_txt=" +
	 * frm.Catalog_vendorId.value +
	 * ";logicalOperator=AND;originalFilter=Y;sort=N;"; }
	 */
  else{
    popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + allowBrowseParameter;
  }

  if (xml.indexOf("coda-") >= 0) {
	  doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieveCodaElements', 'WIDTH=900px', 'HEIGHT=600px');
  } else if (xml.indexOf("fdc-") >= 0) {
	  doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieveWoElements', 'WIDTH=900px', 'HEIGHT=600px');
  } else if (xml.indexOf("discrep-") >= 0){
	  doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'width=1000px', 'height=800px');
  } else {
	  doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=900px', 'HEIGHT=600px');
  }
}

function browseContactAddress(frmField,vendorId){
  popupParameters = "colname=Contact_id_vendorId;operator==;filter_txt=" + vendorId + ";logicalOperator=AND;originalFilter=N;sort=N;"
  browseLookup(frmField,'contact-address');
}


function browseInspectionCriteriaNo(frmField,inspectionCode){
	var fldObject = frmField;
	var xml = "inspectioncrit-no";

	if (currentRow > 0)
	{
		inspectionCode = frm.InspectionStd_inspectCode[window.parent.currentRow].value;
		popupParameters = "colname=InspectionCrit_id_inspectCode;operator==;index='" + currentRow + "';filter_txt=" + inspectionCode + ";logicalOperator=AND;originalFilter=N;sort=N;"
	}
	else
	{
		popupParameters = "colname=InspectionCrit_id_inspectCode;operator==;index='" + currentRow + "';filter_txt=" + inspectionCode + ";logicalOperator=AND;originalFilter=N;sort=N;"
	}
	browseLookup(frmField, 'inspectioncrit-no');
}


function browseStd(frmField, udf, multipleRows, allowBrowsePopup)
{
	if(udf.indexOf('RQ13') >=0)
	{
		browseCommodityByType(frmField);
	}
	else
	{
		if ((udf.indexOf('FBLK') >=0) || (udf.indexOf('FCOL') >=0) || (udf.indexOf('FGRD') >=0) || (udf.indexOf('FINK') >=0) || (frmField.indexOf('Property_') >= 0) || (multipleRows))
		{
			popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;index=" + currentRow + ";";
		}
		else
		{
			popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=" + udf + ";logicalOperator=AND;originalFilter=Y;sort=N;"
		}
		popupParameters = popupParameters + "tableType=" + udf + ";";
		browseLookup(frmField, 'stdtable', allowBrowsePopup);
	}
}

function browseMove(frmField, itemNo, itemLoc, icRc)
{
  if(frmField.indexOf('toLocation') >=0){
    popupParameters = "colname=InvLocation_id_itemNumber;operator==;filter_txt=" + itemNo + ";logicalOperator=AND;originalFilter=N;sort=N;"
    browseLookup(frmField,'std_inv_move');
  }
  else if(frmField.indexOf('toBin') >=0){
    popupParameters = "colname=InvBinLocation_itemNumber;operator==;filter_txt='" + itemNo + "';logicalOperator=AND;originalFilter=N;sort=N;"
    popupParameters = popupParameters + "colname=InvBinLocation_itemLocation;operator==;filter_txt='" + itemLoc + "';logicalOperator=AND;originalFilter=N;sort=N;"
    popupParameters = popupParameters + "colname=InvBinLocation_icRc;operator=<>;filter_txt=" + icRc + ";logicalOperator=AND;originalFilter=N;sort=N;"
    popupParameters = popupParameters + "colname=InvBinLocation_status;operator=<>;filter_txt=00;logicalOperator=OR;originalFilter=N;sort=N;"
    popupParameters = popupParameters + "colname=InvBinLocation_status;operator=isnull;filter_txt=00;logicalOperator=AND;originalFilter=N;sort=N;"
    browseLookup(frmField,'ext_inv_move');
  }else if(frmField.indexOf('toReturnBin') >=0){
    popupParameters = "colname=InvBinLocation_itemNumber;operator==;filter_txt=" + itemNo + ";logicalOperator=AND;originalFilter=N;sort=N;"
    popupParameters = popupParameters + "colname=InvBinLocation_itemLocation;operator==;filter_txt=" + itemLoc + ";logicalOperator=AND;originalFilter=N;sort=N;"
    browseLookup(frmField,'ext_inv_move');
  }
}

function browseAccount(formField, udf)
{
  var fldObject = formField;

  frm.stdTable_tableType.value = udf;
  browseLookup(fldObject, 'stdtable');
}

/* used for shipping schedule, billto schedule, and blanket security pages */
function browseSchedule(formField, xml)
{
  var fldObject = formField;
  popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
  doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
}

function browseComments()
{
  frm.browseName.value = "stdcomment";
  doSubmit('/browse/browse_std_comment.jsp', 'BrowseRetrieve');
}

// function trimString -- removes leading and trailing spaces from any string
function trimString(x) {
	while(''+x.charAt(x.length-1)==' ') {
		x = x.substring(0, x.length-1);
	}
	while('' + x.charAt(0) == ' ') {
		x = x.substring(1, x.length);
	}

	return x;
}

// function trim -- removes leading and trailing spaces from an input field and
// updates the input field
function trim (x) {
	x.value = trimString(x.value);
	return x.value;
}

function verifyAction(msg) {
  if ( confirm(msg) )
  {
    return true;
  }
  return false;
}

function browseStdAccounts() {
  popupParameters =  "browseName=stdaccount;formType=" + frm.formType.value + ";allocMethod=" + frm.as_alloc_method.value + ";allowBrowse=" + frm.allowBrowse.value;
  doSubmitToPopup('browse/browse_std_account.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
}

function scheduleRefresh() {
	doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
}


function reqSaveCheckAccount()
{
  if (isNA(reqnumber))
  {
    popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod + ";Account_icHeader=" + frm.RequisitionHeader_icReqHeader.value;
    doSubmitToPopup('/base/save_as_acc_check.jsp', 'AccountRetrieveByHeader', 'WIDTH=350', 'HEIGHT=165');
  }
  else
  {
    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
  }
}

function reqSave() {
	if (isNA(reqnumber)) {
		if (autoReqNumber && !showAutoReqNumber) {
			doSubmit(currentpage, "RequisitionGetFormNumber;" + currentmethod + ";" + currentprocessmethod);
		} else {
 			popupParameters = "formtype=REQ;formnumber=" + reqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
	} else {
		doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
	}
}

function recSave() {
	if (isEmpty(recNumber) || isNA(recNumber)) {
		doSubmit(currentpage, "ReceiptGetFormNumber;" + currentprocessmethod);
	} else {
		doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
	}
}

function rfqSave()
{
  if (isNA(rfqnumber))
  {
  		if (autoRfqNumber && !showAutoRfqNumber) {
			doSubmit(currentpage, "RfqGetFormNumber;" + currentprocessmethod);
		} else {
		    popupParameters = "formtype=RFQ;formnumber=" + rfqnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
		    doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	    }
  }
  else
  {
    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
  }
}

function poSaveCheckAccount()
{
  if (isNA(ponumber))
  {
    popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod + ";Account_icHeader=" + frm.PoHeader_icPoHeader.value;
    doSubmitToPopup('/base/save_as_acc_check.jsp', 'AccountRetrieveByHeader', 'WIDTH=350', 'HEIGHT=165');
  }
  else
  {
    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
  }
}

function poSave()
{
  if (isNA(ponumber))
  {
  		if (autoPoNumber && !showAutoPoNumber) {
			frm.PoHeader_poNumber.value = '';
			doSubmit(currentpage, "PoSave;" + currentprocessmethod);
		} else {
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
			// popupParameters = "formtype=PO;formnumber=" + ponumber +
			// ";fiscalyear=" + fiscalyear + ";action=save;";
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
  }
  else {
	  if (currentpage=="/orders/po_insurance.jsp" || currentpage=="/orders/po_compliance.jsp")
	  {
		  currentprocessmethod = "PoHeaderRetrieveById;VendorInsuranceUpdate;VendorInsuranceRetrieveById";
		  doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
	  }
	  else
	  {
		  doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
	  }
  }
}

function saleSave()
{
  if (isNA(saleNumber))
  {
    popupParameters = "formtype=SALE;formnumber=" + saleNumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
    doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
  }
  else
  {
    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
  }
}

function disbSave()
{
  if (isNA(disbnumber))
  {
	  if (autoDisbNumber && !showAutoDisbNumber) {
			frm.DisbHeader_disbNumber.value = '';
			doSubmit(currentpage, "OtcSave;DisbSetProperty;" + currentprocessmethod);
		} else {
			popupParameters = "formtype=DSB;formnumber=" + disbnumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
  }
  else
  {
    doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
  }
}

function invoiceSave()
{
  /*
	 * this method is called in the wizard view process when the user clicks on
	 * the save button current method is the method to save that page,
	 * currentprocessmethod is the method to retrieve that page
	 */
  doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
}

function vendorSave()
{
	if( currentpage=="/admin/supplier/supplier_info.jsp" || currentpage=="/admin/supplier/supplier_validation.jsp" )
	{
		/*
		 * if( frm.Vendor_status.value == '02' ) { frm.Vendor_status.value =
		 * '05'; }
		 */
		doSubmit('/admin/supplier/supplier_validation.jsp', currentmethod + ";" + currentprocessmethod);
	}
	else
	{
		  doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
	}
}

function createNewItem()
{
  if (frm.itemAction)
  {
    frm.itemAction.value = "CREATE";
  }

  doSubmit('/inventory/inv_item.jsp', 'DoNothing');
}

/* used for online help tutorial */
function popup_window( url, id, width, height )
{
   // extract the url parameters if any, and pass them to the called html
   var tempvar=document.location.toString(); // fetch the URL string
   var passedparams = tempvar.lastIndexOf("?");
   if(passedparams > -1)
      url += tempvar.substring(passedparams);
  popup = window.open( url, id, 'toolbar=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=no,width=' + width + ',height=' + height + '' );
  popup.focus();
}

function viewMyProfile() {
	doSubmit('user/user_profile.jsp', 'UserProfileValidateRetrieve;AuditTrailSetup');
}

function viewMyAccount() {
	if (frm.PackagePricing_packageType) {
		frm.PackagePricing_packageType.value = "G";
	} else {
		var newInputField = "<input type='hidden' name='PackagePricing_packageType' value='G'>";
		setHiddenFields(newInputField);
	}
	doSubmit('admin/user/user_account_info.jsp', 'PackagePricingRetrieveBy;OrganizationPackageRetrieveActive');
}

function textCounter(field, maxlimit) {
  if (field.value.length > maxlimit) // if too long...trim it!
    field.value = field.value.substring(0, maxlimit);
}

function finalizeReceipts() {
  clearFilters();
  browse("receipt-for-finalization");
}

function stepReceipts(step) {
	clearFilters();
	var stepField = "<input type='hidden' name='actionStep' value='"+step+"'>";
	setHiddenFields(stepField);
	browse("receipt-in-"+step);
}

function receiveFromNothing() {
	if (frm.organizationId.value == "VSE06P") {
		var newInputField = "<input type='hidden' name='receiptMethod' value='ReceiveFromNothing'>";
		setHiddenFields(newInputField);
		doSubmit('receipts/rec_general_info.jsp', 'ReceiptFullCreateFromNothing');
	} else {
		doSubmit('receipts/rec_entry.jsp', 'ReceiptCreateFromNothingInit');
	}
}

function packageReceiveNoPo(){
	var newInputField = "<input type='hidden' name='receiptMethod' value='ReceiveFromNoPo'>";
	setHiddenFields(newInputField);
	doSubmit('receipts/rec_general_info.jsp', 'ReceiptFullCreateFromNoPo');
}

function receiveByItem() {
	if (frm.organizationId.value == "HOY08P") {
	  	if (rcvByItemAccess >= 3) {
		    // Supervisor access to end user receiving (can receive anyone's
			// EUR)
		    // Access to any Receipt Required items
		    browse('receipt-by-item-enduser-rr-s');
		} else if (rcvByItemAccess > 0 ) {
		    browse('receipt-by-item-rr-by-dept');
		}
	} else {
	  if (rcvByEndUserAccess == 99  && rcvByItemAccess >= 3) {
	    // Supervisor access to end user receiving (can receive anyone's EUR)
	    // Access to any Receipt Required items
	    browseFilter('receipt-by-item-enduser-rr-s');
	  } else if (rcvByEndUserAccess == 99) {
	    // Supervisor access to end user receiving (can receive anyone's EUR)
	    browse('receipt-by-item-enduser-s');
	  } else if (rcvByEndUserAccess >=3 && rcvByItemAccess >= 3) {
	    // Non-Supervisor access to end user receiving (can only receive user's
		// own EUR)
	    // Access to any Receipt Required items
	    browse('receipt-by-item-enduser-rr');
	  } else if (rcvByEndUserAccess >= 3) {
	    // Non-Supervisor access to end user receiving (can only receive user's
		// own EUR)
	    browse('receipt-by-item-enduser');
	  } else if (rcvByItemAccess >= 3) {
	    // Access to any Receipt Required items
	    browse('receipt-by-item-rr');
	  } else {
	    alert("You do not have access to this option.  If you require access, please contact your system administrator.");
	  }
	}
}

function receiveFromTransfer() {
	browseFilter('requisitionheader-transfer');
}

function adjustReceipts() {
	if (frm.organizationId.value == "HOY08P") {
		if (MasterUser > 3) {
			browseFilter('receipt-for-adjustment-masteruser-rr-s');
		} else if (rcvAdjAccess >= 3) {
		    // Supervisor access to end user receiving (can receive anyone's
			// EUR)
		    // Access to any Receipt Required items
		    browseFilter('receipt-for-adjustment-enduser-rr-s');
		} else if (rcvAdjAccess > 0) {
		    browseFilter('receipt-for-adjustment-rr-by-dept');
		}
	} else {
		  if (rcvAdjAccess >= 3) {
		    if (rcvByEndUserAccess == 99  && rcvByItemAccess >= 3) {
		      // Supervisor access to end user receiving (can receive anyone's
				// EUR)
		      // Access to any Receipt Required items
		      browseFilter('receipt-for-adjustment-enduser-rr-s');
		    } else if (rcvByEndUserAccess == 99) {
		      // Supervisor access to end user receiving (can receive anyone's
				// EUR)
		      browseFilter('receipt-for-adjustment-enduser-s');
		    } else if (rcvByEndUserAccess >=3 && rcvByItemAccess >= 3) {
		      // Non-Supervisor access to end user receiving (can only receive
				// user's own EUR)
		      // Access to any Receipt Required items
		      browseFilter('receipt-for-adjustment-enduser-rr');
		    } else if (rcvByEndUserAccess >= 3) {
		      // Non-Supervisor access to end user receiving (can only receive
				// user's own EUR)
		      browseFilter('receipt-for-adjustment-enduser');
		    } else if (rcvByItemAccess >= 3) {
		      // Access to any Receipt Required items
		      browseFilter('receipt-for-adjustment-rr');
		    } else {
		      alert("You do not have access to this option.  If you require access, please contact your system administrator.");
		    }
		  } else {
		    alert("You do not have access to this option.  If you require access, please contact your system administrator.");
		  }
	}
}

function adjustReceiptsNoEndUser()
{
	if (rcvAdjAccess >= 3) {
		browseFilter('receipt-for-adjustment-no-enduser');
	} else {
		alert("You do not have access to this option.  If you require access, please contact your system administrator.");
	}
}

function returnAgainstReceipts() {
  if (rcvReturnAccess >= 3) {
    browseFilter("receipt-return-order");
  } else {
    alert("You do not have access to this option.  If you require access, please contact your system administrator.");
  }
}

function viewReceiptHistory() {
	if (rcvHistoryAccess > 0) {
		browseFilter("receipt-item-history");
	} else {
		alert("You do not have access to this option.  If you require access, please contact your system administrator.");
	}
}

function quickRcv() {
	if (quickRcvAccess > 0) {
		doSubmit('receipts/rec_quick_receive.jsp', 'DoNothing');
	} else {
		alert("You do not have access to this option.  If you require access, please contact your system administrator.");
	}
}

function addHiddenFields(hiddenFields) {
	var myCell = document.getElementById("hiddenFields");
	myCell.innerHTML = myCell.innerHTML + hiddenFields;
}

function setHiddenFields(hiddenFields) {
  var myCell = document.getElementById("hiddenFields");
  myCell.innerHTML = hiddenFields;
}

function getHiddenFields() {
  return  document.getElementById("hiddenFields").innerHTML;
}

function updated() {
    frm.lineUpdated.value = "true";
}

function notUpdated() {
    frm.lineUpdated.value = "false";
}

function setFieldFocus() {
  setFirstFocus();
}

function setFirstFocus() {
  if (document.forms.length > 0) {
    var field = document.forms[0];
    var fld;

    for (i = 0; i < field.length; i++) {
      fld = field.elements[i];
      if ((fld.type == "text" || fld.type == "password" || fld.type == "checkbox" || fld.type == "textarea" || fld.type.toString().charAt(0) == "s") && fld.isContentEditable && !fld.isDisabled) {
        var parentFld = fld.parentElement;
        var parentHidden = false;
        while (parentFld != null) {
          if (parentFld.style.visibility != "hidden" && parentFld.style.display != "none") {
            parentFld = parentFld.parentElement;
          } else {
            parentHidden = true;
            break;
          }
        }
        if (!parentHidden) {
          fld.focus();
          break;
        }
      }
    }
  }
}

function openAttachment(filename, documentType) {
  popupUrl = '/system/popupDocAttachment.jsp';
  popupHandler = "DocAttachmentDownloadFile";
  popupUserId = frm.userId.value;
  popupOrganizationId = frm.organizationId.value;
  popupParameters = "filename=" + filename;

  if (documentType != undefined) {
	  popupParameters = popupParameters + ";documentType=" + documentType;
  }

  if (theFocus == null) { theFocus = 'document_window'; }

  var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
  document_window = window.open(contextPath + "/system/popup_html.jsp", "document_window", winspecs);

  if (theFocus == 'main') {
    self.focus();
  }
  else {
    document_window.focus();
  }

  if (document_window.opener == null) document_window.opener = window;
  self.name = "main";
}

function setReportsQueue(module)
{
  // this will change when reports are specific to a certain module
  if(module == "REPINV")
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPPO')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPREQ')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPRFQ')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPADM')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'STDTABLEREP')
  {
    doSubmit('/reports/stdtables_report_types.jsp', 'DoNothing');
  }
  else if(module == 'REPSUP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPVCH')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPRCV')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REPBDG')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/reportqueue_types.jsp', 'ReportRetrieveByModule');
  }
  else
  {
    doSubmit('/reports/req_report_types.jsp', 'DoNothing');
  }
}

function setReports(module)
{
  // this will change when reports are specific to a certain module
  if(module == "INVREP")
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'POREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'REQREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'RFQREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'ADMREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'STDTABLEREP')
  {
    doSubmit('/reports/stdtables_report_types.jsp', 'DoNothing');
  }
  else if(module == 'SUPREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else if(module == 'VCHREP')
  {
    var newInputField = "<input type='hidden' name='Report_reportModule' value='" + module + "'>";
    setHiddenFields(newInputField);
    doSubmit('/reports/report_types.jsp', 'ReportRetrieveByModule');
  }
  else
  {
    doSubmit('/reports/req_report_types.jsp', 'DoNothing');
  }
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

//function getStyleSheetAttribute(selector, attribute) {
//  var rules = document.styleSheets[0].rules;
//
//  if (selector.indexOf(".") == 0) {
//    selector  = selector.substring(1);
//  }
//  if (rules != undefined) {
//    for (var i=0; i < rules.length; i++) {
//      var currentSelector = rules[i].selectorText;
//      if (currentSelector.indexOf(".") == 0) {
//        currentSelector  = currentSelector.substring(1);
//      }
//      if (currentSelector.indexOf("#") == 0) {
//        currentSelector  = currentSelector.substring(1);
//      }
//      if (currentSelector == selector) {
//        return rules(i).style.getAttribute(attribute);
//      }
//    }
//  }
//}

function getStyleSheetAttribute(selector, style, sheet) {
    var sheets = typeof sheet !== 'undefined' ? [sheet] : document.styleSheets;
    for (var i = 0, l = sheets.length; i < l; i++) {
        var sheet = sheets[i];
        if( !sheet.cssRules ) { continue; }
        for (var j = 0, k = sheet.cssRules.length; j < k; j++) {
            var rule = sheet.cssRules[j];
            if (rule.selectorText && rule.selectorText.split(',').indexOf(selector) !== -1) {
                return rule.style[style];
            }
        }
    }
    return null;
}

function allowInputEdit(fld, allowEdit) {
	if (fld){
	  if (allowEdit) {
	    fld.readOnly = false;
	    fld.contentEditable = true;
	    if (fld.type != "hidden") {
	      fld.disabled = false;
	    }
	  } else {
	    fld.readOnly = true;
	    fld.contentEditable = false;
	    if (fld.type != "hidden") {
	      fld.disabled = true;
	    }
	  }
    }
}

function validateUserSecurityForm() {
  var alertMessage = "";
  var w;

  if (frm.handler.value.indexOf("UserChangePassword") >= 0) {
    w = frm.password.value;
    if ( isEmpty( w ) )
      alertMessage += 'Current Password is required.\n';
    w = frm.newPassword.value;
    if ( isEmpty( w ) )
      alertMessage += 'New Password is required.\n';
    w = frm.confirmPassword.value;
    if ( isEmpty( w ) ) {
      alertMessage += 'Please enter the Confirmation for the User Password. Re-type your password.\n';
    } else if ( w != frm.newPassword.value ) {
      alertMessage += 'Password Confirmation does not match New User Password.\n';
    }
  }
  if (frm.handler.value.indexOf("UserChangeSecurityProfile") >= 0) {
    if (frm.securityAnswer) {
      w = frm.securityAnswer.value;
      if ( isEmpty( w ) ) {
        alertMessage += 'The Answer to your Current Security Question is required.\n';
      }
    }
    w = frm.newSecurityQuestion[frm.newSecurityQuestion.selectedIndex].value;
    if ( isEmpty( w ) ) {
      w = frm.newSecurityAnswer.value;
      if ( isEmpty( w ) ) {
        alertMessage += 'Security Question and Answer are required.\n';
      } else {
        alertMessage += 'A Security Question is required.\n';
      }
    } else {
      w = frm.newSecurityAnswer.value;
      if ( isEmpty( w ) )
        alertMessage += 'An Answer to your Security Question is required.\n';
    }
  }

  if ( alertMessage.length > 0 ) {
        alert ( 'Please fix the following problems:\n\n'+alertMessage );
        return false;
  }
  return true;
}

function createPunchoutRequest(catalog) {
	var pg = "";
	var handlers = "";
	var viewType = frm.viewType.value;

	if (frm.viewType.value == "CLASSIC") {
		pg = "/requests/req_summary.jsp";
		handlers = "RequisitionCreatePunchout;ExternalItemLookup;RequisitionRetrieve";
	} else {
		if (orgId.toLowerCase()=='bsc04p')
		{
			pg = "/requests/req_general_info.jsp";
		}
		else
		{
			pg = "/requests/req_review.jsp";
		}
		handlers = "RequisitionCreatePunchout;ExternalItemLookup;RequisitionRetrieve";
	}

	if (frm.organizationId.value == "WPC08P" && frm.punchOutAddAccount)
	{
		if (catalog == "CDW" && frm.userNameUdf3)
			frm.userNameUdf3.value = "1770";
		if (catalog == "CORP EXPRESS" && frm.userNameUdf3)
			frm.userNameUdf3.value = "8150";
		frm.punchOutAddAccount.value = "Y";
	}

	var hiddenFields = "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"P\">" +
			"<input type=hidden name=\"formtype\" value=\"REQ\">" +
			"<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">" +
			"<input type=hidden name=\"punchOutReturnHandler\" value=\"" + handlers + "\">" +
			"<input type=hidden name=\"punchOutReturnSuccessPage\" value=\"" + pg + "\">";
	setHiddenFields(hiddenFields);

	doSubmit('/system/error.jsp', 'BrowseExternalWebCatalog');
}

function loadNavMenu()
{
	displayNavMenu();
	checkHiddenMenu();
}

function replaceChars(value, out, add) {
	while (value.indexOf(out) > -1) {
		value = value.replace(out, add);
	}
	return value;
}

function replaceForKeywords(value) {
	value = value.replace(/([^0-9A-Za-z * \" \'])/g,'%');
	value = value.replace (/(%+)/g,'%');
	value = value.replace(/\'/g,'\'\'');
	value = value.replace(/\"/g,'&quot');
	return value;
}

function replaceForItemNumber(value) {
	value = value.replace(/([^0-9A-Za-z *])/g,'%');
	value = value.replace (/(%+)/g,'%');
	return value;
}

function isAttachmentExtValid(filename) {
	var ext = "";
	var ind = filename.indexOf(".");
	var invalidFileTypes = ".EXE.BAT.EXE.SH.CMD";

	if (ind > 0) {
		ext = filename.substring(ind + 1, filename.length).toUpperCase();
	}
	if (isEmpty(ext) || (!isEmpty(validFileTypes) && validFileTypes.indexOf(ext) < 0) || invalidFileTypes.indexOf(ext) >= 0) {
		alert( "Not allowed to upload files of type " + ext);
		return false;
	}
	return true;
}

function isXlsExtValid(filename) {
	var ext = "";
	var ind = filename.indexOf(".");

	if (isEmpty(validFileTypes)) {
		validFileTypes = ".XLS";
	}

	if (ind > 0) {
		ext = filename.substring(ind + 1, filename.length).toUpperCase();
	}
	if (isEmpty(ext) || (validFileTypes.indexOf(ext) < 0)) {
		var validFileTypeText = "";
		for (var i = 0; i < validFileTypes.length; i++) {
			var x = validFileTypes.charAt(i);
			if (x ==  ".") {
				validFileTypes = validFileTypes.substring(0, i + 1) + " " + validFileTypes.substring(i + 1, validFileTypes.length);
			}
		}
		alert("This is not a valid file type.  Please select a XLS file");
		return false
	}
	return true;
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

function resetDummyFields() {
	var dummyFields = document.getElementById("dummyFields");
	if (dummyFields != null && dummyFields != undefined) {
		dummyFields.innerHTML = "";
	}
}

function resetBrowseFilter() {
	if (frm.filter_txt) {
		frm.filter_txt.value = '';
	}
	if (frm.colname) {
		frm.colname.value = '';
	}
	if (frm.sort) {
		frm.sort.value = '';
	}
}

function doSubmitToNewTarget(page, handlerList, target) {
	setupHandlers(handlerList);

	frm.successPage.value = page;

	if (validateForm()) {
		var dummyFields = document.getElementById("dummyFields");
		var dummyFieldsHTML = "";
		if (dummyFields != null && dummyFields != undefined) {
			dummyFieldsHTML = dummyFields.innerHTML;
		}
// resetDisabledFlds();
		resetDummyFields();

		frm.target = target;
		frm.submit();

		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = dummyFieldsHTML;
		}
		return true;
	} else {
		return false;
	}
}

function numbersonly(myfield, e, dec)
{
  var key;
  var keychar;

  if (window.event)
    key = window.event.keyCode;
  else if (e)
    key = e.which;
  else
    return true;

  keychar = String.fromCharCode(key);

  // control keys
  if ((key==null) || (key==0) || (key==8) ||
      (key==9) || (key==13) || (key==27))
    return true;

  // numbers
  else if ((("0123456789").indexOf(keychar) > -1))
    return true;

  // decimal point jump
  else if (dec && (keychar == "."))
  {
     myfield.form.elements[dec].focus();
     return false;
  }
  else if ((".").indexOf(keychar) > -1)
        return true;
  else
    return false;
}

function thisUnLoadPopup() {
}

function checkemail(formField)
{
	var str = formField.value;
	var emailArray = str.split(";");

	for (x=0; x < emailArray.length; x++)
	{
		if (checkOneEmail(emailArray[x]))
		{
			// return true;
		}
		else
		{
			formField.select();
			return false;
		}
	}

	return true;
}

function checkOneEmail(email)
{
	var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i

	if (filter.test(email))
	{
		return true;
	}
	else
	{
		alert("Please input a valid email address!");
		return false;
	}
}

function browseCommodity(formField, xml, commodityType) {
	if (xml == "subcommodity") {
		var currentCode = document.getElementById(formField).value;
		popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
		doSubmitToPopup('browse/browse_subcommodity_tree.jsp', 'SubCommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
	} else if (!isEmpty(commodityType)) {
		var currentCode = document.getElementById(formField).value;
		popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
		doSubmitToPopup('browse/browse_commodity_tree.jsp', 'CommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
	} else {
		browseLookup(formField, xml);
	}
}


function checkVendorInsurance(processcode, poType)
{
	if (processcode == "INSURANCE" || processcode == "COMPLIANCE")
	{
		var vendorId = frm.VendorInsurance_vendorId.value;
		if (isEmpty(vendorId)) {
			if (processcode == "INSURANCE")
			{
				alert("A Supplier must be selected prior to entering Insurance Information.");
			}
			else if (processcode == "COMPLIANCE")
			{
				alert("A Supplier must be selected prior to entering Compliance Information.");
			}
			if (currentpage != "/orders/po_supplier.jsp")
			{
				doSubmit('/orders/po_supplier.jsp', currentmethod +';PoHeaderVendorRetrieveById');
			}
			return false;
		}
		if (isNA(ponumber))
		{
			var message = (poType == 'CT') ? 'Contract' : 'Order';

			if (processcode == "INSURANCE")
			{
				alert("The " + message + " must be saved prior to entering Insurance Information.");
				return false;
			}
			else if (processcode == "COMPLIANCE")
			{
				alert("The " + message + " must be saved prior to entering Compliance Information.");
				return false;
			}
		}
	}
	return true;
}

function browseSetup(browseName, browseType, fieldName) {
	var flds = frm.elements(fieldName);

	if (browseType.length > 0){
		browseStd(fieldName, browseType, false, true);
	} else if (flds != undefined && flds.length > 1) {
		browseSchedule(fieldName, browseName);
	} else {
		browseLookup(fieldName, browseName);
	}
}

function currencyChangeWarning(newCode) {
	if (isEmpty(newCode)) {
		newCode = "the new currency";
	}
	alert("Changing the currency may have an effect on your totals.  Please confirm your prices have been entered as " + newCode + ".");
}

function viewUserInfo(uid) {
	if (!isEmpty(uid)) {
		popupParameters = "uid=" + uid;
		doSubmitToPopup('/base/user_info.jsp', 'DoNothing', 'WIDTH=600', 'HEIGHT=350');
	}
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

function onLoadComplete()
{
	if(strEnableAuditTrail == "Y"){
		buildAuditFields();
	}
	hideArea("pageLoading");
	displayArea("pageForm");
	setFieldFocus();
}

function getFields(el)
{
	/*
	 * if(el.name.indexOf("RequisitionHeader_") > -1) { return true; } else {
	 * return false; }
	 */

    return false;
}

function buildAuditFields()
{
	setAuditTables();
	
	if (typeof YAHOO != "undefined")
	{
		
		var auditFieldsEl = YAHOO.util.Dom.get("auditFields") ;
		var auditFieldsId = "{ ic: ";
		auditFieldsId += "'" + buildAuditIc() + "'";

		fieldsToAudit = YAHOO.util.Dom.getElementsBy(getFields, 'input');
		for (var j = 0; j < fieldsToAudit.length; j++)
		{
			if (fieldsToAudit[j].type != "password")
			{
				if(auditFieldsId.search(fieldsToAudit[j].name) == -1)
					{
					auditFieldsId += ",";
					auditFieldsId += fieldsToAudit[j].name;
					if(fieldsToAudit[j].value.length > 0)
					{
						auditFieldsId += ":" + '"' + fieldsToAudit[j].value + '"';
					}
					else
					{
						auditFieldsId += ": NONE";
					}
				}
			}
		}

	    fieldsToAudit = YAHOO.util.Dom.getElementsBy(getFields, 'select');
		for (var j = 0; j < fieldsToAudit.length; j++)
		{
			if(auditFieldsId.search(fieldsToAudit[j].name) == -1)
			{
				auditFieldsId += ",";
		        auditFieldsId += fieldsToAudit[j].name;
		        if(fieldsToAudit[j].value.length > 0)
		        {
		        	auditFieldsId += ":" + '"' + fieldsToAudit[j].value + '"';
		        }
		        else
		        {
		        	auditFieldsId += ": NONE";
		        }
			}
	    }

	    auditFieldsId += "}";
		if(typeof(auditFieldsEl) !== 'undefined' && auditFieldsEl != null) {
			auditFieldsEl.value = auditFieldsId;
		}
	}
	else //jqueryBased
	{
		var auditFieldsEl = $(":input[name='auditFields']");
		var auditFieldsId = "{ ic: ";
		auditFieldsId += "'" + buildAuditIc() + "'";
		var fieldsToAuditJquery = getFieldsJquery();
		
		if (fieldsToAuditJquery){
			fieldsToAuditJquery.each(function(index, element) {
				
				if ($(element)[0].tagName != "password" && $(element)[0].tagName != "PASSWORD" )
				{
					var x = $(element)[0].name;
					
					if(auditFieldsId.search($(element)[0].name) == -1)
					{
						
						auditFieldsId += ",";
						auditFieldsId += $(element)[0].name;
						
						if($(element)[0].value.length > 0)
						{
							auditFieldsId += ":" + '"' + $(element)[0].value + '"';
						}
						else
						{
							auditFieldsId += ": NONE";
						}					
					}	
				}
			});
		}
		auditFieldsId += "}";
		
		if (auditFieldsId)
		{
			if(typeof(auditFieldsEl) !== 'undefined' && auditFieldsEl != null) {
				auditFieldsEl.val(auditFieldsId);
			}
		}
	}
	
}
function buildAuditedFields()
{
	if (typeof YAHOO != "undefined")
	{
		var auditFieldsEl = YAHOO.util.Dom.get("auditedFields");
		var auditFieldsId = "{ ic: ";
		auditFieldsId += "'" + buildAuditIc() + "'";

		
		fieldsToAudit = YAHOO.util.Dom.getElementsBy(getFields, 'input');

		for (var j = 0; j < fieldsToAudit.length; j++)
		{
			if (fieldsToAudit[j].type != "password")
			{
				if(auditFieldsId.search(fieldsToAudit[j].name) == -1)
				{
					auditFieldsId += ",";
					auditFieldsId += fieldsToAudit[j].name;
					if(fieldsToAudit[j].value.length > 0)
					{
						auditFieldsId += ":" + '"' + fieldsToAudit[j].value + '"';
					}
					else
					{
						auditFieldsId += ": NONE";
					}
				}
			}
		}
		
	    fieldsToAudit = YAHOO.util.Dom.getElementsBy(getFields, 'select');

	    var auditFieldsElJquery = $(":input[name='auditedFields']");
		for (var j = 0; j < fieldsToAudit.length; j++)
		{
			if(auditFieldsId.search(fieldsToAudit[j].name) == -1)
			{
				auditFieldsId += ",";
		        auditFieldsId += fieldsToAudit[j].name;
		        if(fieldsToAudit[j].value.length > 0)
		        {
		        	auditFieldsId += ":" + '"' + fieldsToAudit[j].value + '"';
		        }
		        else
		        {
		        	auditFieldsId += ": NONE";
		        }
			}
	    }
	    auditFieldsId += "}";
		if(typeof(auditFieldsEl) !== 'undefined' && auditFieldsEl != null) {
			if (navigator.appName == "Microsoft Internet Explorer" || navigator.appName == "Netscape")
				
				auditFieldsEl.value = auditFieldsId;
			else
				auditFieldsEl = auditFieldsId;
		}
		
	    
	}
	
	else //jquery based
	{
		var auditFieldsEl = $(":input[name='auditedFields']");
		var auditFieldsId = "{ ic: ";
		auditFieldsId += "'" + buildAuditIc() + "'";
		var fieldsToAuditJquery = getFieldsJquery();
		
		if (fieldsToAuditJquery){
			fieldsToAuditJquery.each(function(index, element) {
				
				if ($(element)[0].tagName != "password" && $(element)[0].tagName != "PASSWORD" )
				{
					var x = $(element)[0].name;
					
					if(auditFieldsId.search($(element)[0].name) == -1)
					{
						
						auditFieldsId += ",";
						auditFieldsId += $(element)[0].name;
						
						if($(element)[0].value.length > 0)
						{
							auditFieldsId += ":" + '"' + $(element)[0].value + '"';
						}
						else
						{
							auditFieldsId += ": NONE";
						}					
					}	
				}
			});
		}
		auditFieldsId += "}";
		
		if (auditFieldsId)
		{
			if(typeof(auditFieldsEl) !== 'undefined' && auditFieldsEl != null) {
				auditFieldsEl.val(auditFieldsId);
			}
		}
	}
}

function setAuditTables()
{
}

function buildAuditIc()
{
}
function setCurrentRow(row)
{
	currentRow = row;
}

function alphaNumericCheck(x)
{
	// x.value=x.value.toUpperCase().replace(/([^0-9A-Z () / -
	// _.])/g,"").trim();
	x.value=x.value.toUpperCase().replace(/([^0-9A-Z- () / _.])/g,"").trim();
}

function removeBadChars(strTemp) { 
    strTemp.value = strTemp.value.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|/g,"").trim(); 
} 

function isArray(x)
{
   return x && typeof x === 'object' && typeof x.length === 'number';
}

function setStatus()
{
	if (maxRows == 1)
	{
		frm.Account_status.value="02";
	}
	else
	{
		frm.Account_status[currentRow].value="02";
	}
}

function viewDefaultApprovalPolicy() {
	window.open("approval/Authorized Financial Authorization Limitations.pdf", "puridiom_window");
}

function isNumeric(sText)
{
   var validChars = "0123456789";
   var isNumber=true;
   var char;
   for (i = 0; i < sText.length && isNumber == true; i++)
   {
      char = sText.charAt(i);
      if (validChars.indexOf(char) == -1)
      {
         isNumber = false;
      }
   }
   return isNumber;
}

function formatDate(date, format) {
	if (isEmpty(format)) {
		format = frm.userDateFormat.value;
	}
	format = format.toUpperCase();
	var vData;
	var vMonth = date.getMonth() + 1;
	var vDay = date.getDate();
	var vYear = date.getYear();
	var vY4 = vYear;
	var vY2 = new String(vYear).substring(2,4);
	var vDD = (vDay.toString().length < 2) ? "0" + vDay : vDay;
	var vMM = (vMonth.toString().length < 2) ? "0" + vMonth : vMonth;

	switch (format) {
		case "YYYY-MM-DD" :
			vData = vY4 + "-" + vMM + "-" + vDD;
			break;
		case "YYYY.MM.DD" :
			vData = vY4 + "." + vMM + "." + vDD;
			break;
		case "YYYY.DD.MM" :
			vData = vY4 + "." + vDD + "." + vMM;
			break;
		case "YYYY-DD-MM" :
			vData = vY4 + "-" + vDD + "-" + vMM;
			break;
		case "YYYY\/DD\/MM" :
			vData = vY4 + "\/" + vDD + "\/" + vMM;
			break;

		case "MM\/DD\/YYYY" :
			vData = vMM + "\/" + vDD + "\/" + vY4;
			break;
		case "MM\/DD\/YY" :
			vData = vMM + "\/" + vDD + "\/" + vY2;
			break;
		case "MM-DD-YYYY" :
			vData = vMM + "-" + vDD + "-" + vY4;
			break;
		case "MM-DD-YY" :
			vData = vMM + "-" + vDD + "-" + vY2;
			break;
		case "MM.DD.YYYY" :
			vData = vMM + "." + vDD + "." + vY4;
			break;
		case "MM.DD.YY" :
			vData = vMM + "." + vDD + "." + vY2;
			break;
		case "DD\/MM\/YYYY" :
			vData = vDD + "\/" + vMM + "\/" + vY4;
			break;
		case "DD\/MM\/YY" :
			vData = vDD + "\/" + vMM + "\/" + vY2;
			break;
		case "DD-MM-YYYY" :
			vData = vDD + "-" + vMM + "-" + vY4;
			break;
		case "DD-MM-YY" :
			vData = vDD + "-" + vMM + "-" + vY2;
			break;
		case "DD.MM.YYYY" :
			vData = vDD + "." + vMM + "." + vY4;
			break;
		case "DD.MM.YY" :
			vData = vDD + "." + vMM + "-" + vY2;
			break;

		default :
			vData = vY4 + "-" + vMM + "-" + vDD;
	}
	return vData;
}


function createOrder(type)
{
	if (frm.PoHeader_poType != undefined && frm.PoHeader_poType != null) {
		frm.PoHeader_poType.value = type;
	} else {
		var newInputField = "<input type='hidden' name='PoHeader_poType' value='" + type + "'>";
		setHiddenFields(newInputField);
	}

	frm.viewType.value = "WIZARD";

	doSubmit('/orders/po_create.jsp', 'DoNothing');
}

function createOrderFromReq(type) {
	if (frm.PoHeader_poType != undefined && frm.PoHeader_poType != null) {
		frm.PoHeader_poType.value = type;
	} else {
		var newInputField = "<input type='hidden' name='PoHeader_poType' value='" + type + "'>";
		setHiddenFields(newInputField);
	}

	frm.viewType.value = "WIZARD";

	browse('po_reqbrowse');
}

function createRequisition(type)
{
	if (frm.RequisitionHeader_requisitionType != undefined && frm.RequisitionHeader_requisitionType != null) {
		frm.RequisitionHeader_requisitionType.value = type;
	} else {
		var newInputField = "<input type='hidden' name='RequisitionHeader_requisitionType' value='" + type + "'>";
		setHiddenFields(newInputField);
	}

	frm.viewType.value = "WIZARD";
	doSubmit('/requests/req_create.jsp', 'DoNothing');
}

function setInvalidFields(invalidFields) {
	while(!isEmpty(invalidFields)) {
		var fldName = "";
		if (invalidFields.indexOf(";") > 0) {
			fldName = trimString(invalidFields.substring(0, invalidFields.indexOf(";")));
			invalidFields = trimString(invalidFields.substring(invalidFields.indexOf(";") + 1));
		} else {
			fldName = trimString(invalidFields);
			invalidFields = "";
		}
		if (document.getElementById(fldName) != undefined) {
			document.getElementById(fldName).className = "invalidField";
		}
	}
}

function getCheckedRadioButtonValue(radioButtonName) {
	var radioButtons = document.getElementsByName(radioButtonName);
	var radioValue = "";

	if (radioButtons != undefined && radioButtons.length != undefined) {
		if (radioButtons.length == 1) {
			radioValue = radioButtons.value;
		} else {
			for (var x = 0; x < radioButtons.length; x ++) {
				if (radioButtons[x].checked) {
					radioValue = radioButtons[x].value;
					break;
				}
			}
		}
	}
	return radioValue;
}

function viewPoPdf(ic, number, type) {
	popupParameters = "PoHeader_icPoHeader=" + ic;
	popupParameters = popupParameters + ";uid=" + frm.userId.value;
	popupParameters = popupParameters + ";oid=" + frm.organizationId.value;
	popupParameters = popupParameters + ";viewNow=Y";
	doSubmitToPopup('', 'PrintPoPdf', 'width=775px', 'height=850px');
}

function isNA(val) {
	returnVal = val == "N/A" || val == "N&#x2f;A";
	return returnVal;
}

function arrayIndexOf(array, obj) {
	for (var i=0; i<array.length; i++) {
		if (array[i] == obj) {
			return i;
		}
	}
	return -1;
}

function getFieldsJquery() {
	return undefined;
}


