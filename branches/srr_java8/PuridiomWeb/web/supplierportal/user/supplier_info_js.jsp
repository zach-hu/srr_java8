<script value=JavaScript>
<!--Hide Script
	frm = document.purchaseform;

	var alertMessage = "";
	var focusFld = null;
   	
<%	if (b_display_commodities) { %>
	checkImages();
<%	}%>

	frm.sup_vendor_name.focus();


	function checkImages() {
		var img = "/puridiom/xbidboard/images/delete.gif";
		for (var i = 0; i < 5; i++) {
			if ( isEmpty(frm.com_commodity_code[i].value) ) {
				img = "/puridiom/xbidboard/images/none.gif";
			}
			else {
				img = "/puridiom/xbidboard/images/delete.gif";
			}

			if (i == 0) {	frm.delImg0.src = img;	}
			if (i == 1) {	frm.delImg1.src = img;	}
			if (i == 2) {	frm.delImg2.src = img;	}
			if (i == 3) {	frm.delImg3.src = img;	}
			if (i == 4) {	frm.delImg4.src = img;	}
		}
	}

	function getRows () {
		var rowCnt = 0;
		var totalrows = 0;

		for (var i = 0; i < 5; i++) {
			if (!isEmpty(frm.com_commodity_code[i].value)) {
				totalrows++;
			}
		}

		for ( var i = 0; i < 5; i++) {
			if ( isEmpty(frm.com_commodity_code[i].value) ) {
				deleteMe(i);
				if ( (i < 4) && (rowCnt < totalrows) ){
					i--;
				}
			} 
			else {
				rowCnt = rowCnt + 1;
			}
		}

		frm.as_rows.value = asRows + rowCnt;
        }

	function deleteMe(ix) {
		var k = 4;

		if (ix > k) {
			return;
		}
		for ( var it = ix; it < k; it++) {
			var j = it + 1;
			frm.com_commodity_code[it].value = frm.com_commodity_code[j].value;
		}
		frm.com_commodity_code[k].value = ''; 
		checkImages();
	}

	function browseCommodities () {
		var selected = "";
		var selectCnt = 0;
		var args = "table=commodities";

		for (var i = 0; i < 5; i++) {
			if ( !isEmpty(frm.com_commodity_code[i].value) ) {
				if ( selected.length > 0 ) {
					selected = selected + "\u0008" + frm.com_commodity_code[i].value;
				}
				else {
					selected = frm.com_commodity_code[i].value;
				}
				selectCnt++;
			}
		}
		args = args + "&selected=" + selected + "&selectCnt=" + selectCnt;
		openWindow('/puridiom/xbidboard/browse/commodity_browse.jsp?' + args,'WIDTH=700','HEIGHT=500');
	}

	function checkValues() {
		focusFld = null;

		validatePhone(frm.sup_vendor_fax_number, "<%=slb_fax%>");
		validatePhone(frm.con_contact_phone_number, "<%=slb_phone%>");
		validatePhone(frm.con_contact_fax_number, "<%=slb_contact_fax%>");
		validatePhone(frm.con_alt_phone_number, "<%=slb_alt_phone%>");
		validatePhone(frm.con_alt_fax_number, "<%=slb_alt_contact_fax%>");
		validateEmail(frm.con_contact_email_addr, "<%=slb_email_addr%>");
		validateEmail(frm.con_alt_email_addr, "<%=slb_alt_email_addr%>");

		if (alertMessage.length > 0) {
			alert("Please correct the following problems:\n" + alertMessage);
			focusFld.focus();
			alertMessage = "";
			return false;
		}
		else {
			checkRequired();

			if (alertMessage.length > 0) {
				if (verifyAction("The following information has not been entered and is required for qualification:" + "\n\n" + alertMessage + "\n\n" + "Click OK to complete prequalification information now.\n") ) {
					focusFld.focus();
					alertMessage = "";
					return false;
				}
			}
		}

		getRows();

		return true;
	}

	function validatePhone(fld, type) {
		var cmp = "0123456789.-() ";
		var x = fld.value;
	
		for ( var i = 0; i < x.length; i++) {
			tst = x.substring(i,i+1);
			if (cmp.indexOf(tst) < 0) {
				alertMessage = alertMessage + "\n" + type + " Number is not valid."; 
				if (focusFld == null) {
					focusFld = fld;
				}
				return false;
			}
		}
	}

	function validateEmail(fld, type) {
		var txt = trim(fld);
		var icom = txt.indexOf(".com");
		var iorg = txt.indexOf(".org");
		var igov = txt.indexOf(".gov");
		var inet = txt.indexOf(".net");
		var imil = txt.indexOf(".mil");
		var iedu = txt.indexOf(".edu");

		if (txt.length <= 0) {
			return;
		}
		else if (txt.indexOf("@") < 2) {
			alertMessage = alertMessage + "\n" + type +  " seems wrong. Please check the prefix and '@' sign.";
			if (focusFld == null) {
				focusFld = fld;
			}
			return;
	      	}
		else if ( (icom < 5) && (iorg < 5) && (igov < 5) && (inet < 5) && (imil < 5) && (iedu < 5) ) {
			alertMessage = alertMessage + "\n" + type + " seems wrong. Please check the suffix for accuracy."
				+ "(It should include a .com, .edu, .net, .org, .gov or .mil)";
			if (focusFld == null) {
				focusFld = fld;
			}
			return;
	      	}

		return;
	}

	function ckRequired (fld, type) {
		if (isEmpty(fld.value)) {
			alertMessage = alertMessage + "\n" + type;

			if (focusFld == null) {
				focusFld = fld;
			}
		}
	}


	function checkRequired () {
<%
/*****	SUPPLIER ADDRESS FIELDS REQUIRED  *****/

	if ( brq_address_line1 ) {%>
		ckRequired(frm.sup_vendor_name, "<%=slb_address_line1%>");
<%	}
	if ( brq_address_line2 ) {%>
		ckRequired(frm.sup_address_line_2, "<%=slb_address_line2%>");
<%	}
	if ( brq_address_line3 ) {%>
		ckRequired(frm.sup_address_line_3, "<%=slb_address_line3%>");
<%	}
	if ( brq_address_line4 ) {%>
		ckRequired(frm.sup_address_line_4, "<%=slb_address_line4%>");
<%	}
	if ( brq_city ) {%>
		ckRequired(frm.sup_address_city, "<%=slb_city%>");
<%	}
	if ( brq_state ) {%>
		ckRequired(frm.sup_address_state, "<%=slb_state%>");
<%	}
	if ( brq_zip ) {%>
		ckRequired(frm.sup_address_postal_code, "<%=slb_zip%>");
<%	}
	if ( brq_country ) {%>
		ckRequired(frm.sup_address_country, "<%=slb_country%>");
<%	}
	if ( brq_fax ) {%>
		ckRequired(frm.sup_vendor_fax_number, "<%=slb_fax%>");
<%	}
/*****	SUPPLIER CONTACT REQUIRED FIELDS  ******/

	if ( brq_first_name ) {%>
		ckRequired(frm.con_contact_first_name, "<%=slb_first_name%>");
<%	}
	if ( brq_middle_init ) {%>
		ckRequired(frm.con_contact_middle_init, "<%=slb_middle_init%>");
<%	}
	if ( brq_last_name ) {%>
		ckRequired(frm.con_contact_last_name, "<%=slb_last_name%>");
<%	}
	if ( brq_title ) {%>
		ckRequired(frm.con_contact_title, "<%=slb_title%>");
<%	}
	if ( brq_email_addr ) {%>
		ckRequired(frm.con_contact_email_addr, "<%=slb_email_addr%>");
<%	}
	if ( brq_phone ) {%>
		ckRequired(frm.con_contact_phone_number, "<%=slb_phone%>");
<%	}
	if ( brq_contact_fax ) {%>
		ckRequired(frm.con_contact_fax_number, "<%=slb_contact_fax%>");
<%	}
/*****	ALTERNATE CONTACT REQUIRED FIELDS  ******/

	if ( brq_alt_first_name ) {%>
		ckRequired(frm.con_alt_first_name, "<%=slb_alt_first_name%>");
<%	}
	if ( brq_alt_middle_init ) {%>
		ckRequired(frm.con_alt_middle_init, "<%=slb_alt_middle_init%>");
<%	}
	if ( brq_alt_last_name ) {%>
		ckRequired(frm.con_alt_last_name, "<%=slb_alt_last_name%>");
<%	}
	if ( brq_alt_title ) {%>
		ckRequired(frm.con_alt_title, "<%=slb_alt_title%>");
<%	}
	if ( brq_alt_email_addr ) {%>
		ckRequired(frm.con_alt_email_addr, "<%=slb_alt_email_addr%>");
<%	}
	if ( brq_alt_phone ) {%>
		ckRequired(frm.con_alt_phone_number, "<%=slb_alt_phone%>");
<%	}
	if ( brq_alt_contact_fax ) {%>
		ckRequired(frm.con_alt_fax_number, "<%=slb_alt_contact_fax%>");
<%	}
/*****	SUPPLIER COMPANY REQUIRED FIELDS  ******/

	if ( brq_duns ) {%>
		ckRequired(frm.sup_vendor_duns, "<%=slb_duns%>");
<%	}
	if ( brq_sic ) {%>
		ckRequired(frm.sup_vendor_sic, "<%=slb_sic%>");
<%	}
	if ( brq_commodities ) {%>
		var rows = 0;

		for (var i = 0; i < 5; i++) {
			if (!isEmpty(frm.com_commodity_code[i].value)) {
				rows++;
			}
		}

		if ( rows <= 0 ) {
			alertMessage = alertMessage + "\n" + "<%=slb_commodities%> is required for qualification.";

			if (focusFld == null) {
				focusFld = frm.com_commodity_code[0];
			}
		}
<%	}
	if ( brq_socio_economic ) {%>
		ckRequired(frm.sup_vendor_class, "<%=slb_socio_economic%>");
<%	}
	if ( brq_web_address ) {%>
		ckRequired(frm.sup_web_address, "<%=slb_web_address%>");
<%	}
	if ( brq_vendor_terms ) {%>
		ckRequired(frm.sup_vend_terms, "<%=slb_vendor_terms%>");
<%	}
	if ( brq_years_in_bus ) {%>
		ckRequired(frm.sup_years_in_business, "<%=slb_years_in_bus%>");
<%	}
	if ( brq_lead_days ) {%>
		ckRequired(frm.sup_lead_days, "<%=slb_lead_days%>");
<%	}
	if ( brq_ein_number ) {%>
		ckRequired(frm.sup_vendor_ein, "<%=slb_ein_number%>");
<%	}
	if ( brq_edi_vendor ) {%>
		if (!frm.sup_edi_vendor[0].checked) {
			frm.sup_edi_vendor[1].checked = true;
		}
<%	}
	if ( brq_edi_address ) {%>
		ckRequired(frm.sup_edi_address, "<%=slb_edi_address%>");
<%	}
	if ( brq_notes ) {%>
		ckRequired(frm.sup_notes, "<%=slb_notes%>");
<%	}
/*****	SUPPLIER UDF REQUIRED FIELDS	*****/

	if ( brq_udf01 ) {%>
		ckRequired(frm.sup_vend_udf_1, "<%=slb_udf01%>");
<%	}
	if ( brq_udf02 ) {%>
		ckRequired(frm.sup_vend_udf_2, "<%=slb_udf02%>");
<%	}	if ( brq_udf03 )
	if ( brq_udf03 ) {%>
		ckRequired(frm.sup_vend_udf_3, "<%=slb_udf03%>");
<%	}
	if ( brq_udf04 ) {%>
		ckRequired(frm.sup_vend_udf_4, "<%=slb_udf04%>");
<%	}
	if ( brq_udf05 ) {%>
		ckRequired(frm.sup_vend_udf_5, "<%=slb_udf05%>");
<%	}
	if ( brq_udf06 ) {%>
		ckRequired(frm.sup_vend_udf_6, "<%=slb_udf06%>");
<%	}
	if ( brq_udf07 ) {%>
		ckRequired(frm.sup_vend_udf_7, "<%=slb_udf07%>");
<%	}
	if ( brq_udf08 ) {%>
		ckRequired(frm.sup_vend_udf_8, "<%=slb_udf08%>");
<%	}
	if ( brq_udf09 ) {%>
		ckRequired(frm.sup_vend_udf_9, "<%=slb_udf09%>");
<%	}
	if ( brq_udf10 ) {%>
		ckRequired(frm.sup_vend_udf_10, "<%=slb_udf10%>");
<%	}%>

	}

	function submitThis() {
		if (frm.as_redirect.value == "/puridiom/xbidboard/supplier/supplier_complete.jsp") {
			if (checkValues()) {
				frm.submit();
			}
			return false;
		}
		else if ( (frm.as_action.value == "UPDATE") ) {
			if ( verifyAction('Your changes will not be saved!') ) {
				frm.as_action.value = "REDIRECT";
			}
			else {
				return false;
			}
		}

		frm.action = "/puridiom/xbidboard/base/update.jsp";
		frm.submit();
	}

//-->
</script>