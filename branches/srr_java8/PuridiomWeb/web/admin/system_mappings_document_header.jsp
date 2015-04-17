<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	reqToRfqReqUdf01 = propertiesManager.getProperty("REQ TO RFQ", "req_udf01", "");
	String	reqToRfqReqUdf02 = propertiesManager.getProperty("REQ TO RFQ", "req_udf02", "");
	String	reqToRfqReqUdf03 = propertiesManager.getProperty("REQ TO RFQ", "req_udf03", "");
	String	reqToRfqReqUdf04 = propertiesManager.getProperty("REQ TO RFQ", "req_udf04", "");
	String	reqToRfqReqUdf05 = propertiesManager.getProperty("REQ TO RFQ", "req_udf05", "");
	String	reqToRfqReqUdf06 = propertiesManager.getProperty("REQ TO RFQ", "req_udf06", "");
	String	reqToRfqReqUdf07 = propertiesManager.getProperty("REQ TO RFQ", "req_udf07", "");
	String	reqToRfqReqUdf08 = propertiesManager.getProperty("REQ TO RFQ", "req_udf08", "");
	String	reqToRfqReqUdf09 = propertiesManager.getProperty("REQ TO RFQ", "req_udf09", "");
	String	reqToRfqReqUdf10 = propertiesManager.getProperty("REQ TO RFQ", "req_udf10", "");
	String	reqToRfqReqUdf11 = propertiesManager.getProperty("REQ TO RFQ", "req_udf11", "");
	String	reqToRfqReqUdf12 = propertiesManager.getProperty("REQ TO RFQ", "req_udf12", "");
	String	reqToRfqReqUdf13 = propertiesManager.getProperty("REQ TO RFQ", "req_udf13", "");
	String	reqToRfqReqUdf14 = propertiesManager.getProperty("REQ TO RFQ", "req_udf14", "");
	String	reqToRfqReqUdf15 = propertiesManager.getProperty("REQ TO RFQ", "req_udf15", "");
	///
	String	reqToPoReqUdf01 = propertiesManager.getProperty("REQ TO PO", "UDF1", "");
	String	reqToPoReqUdf02 = propertiesManager.getProperty("REQ TO PO", "UDF2", "");
	String	reqToPoReqUdf03 = propertiesManager.getProperty("REQ TO PO", "UDF3", "");
	String	reqToPoReqUdf04 = propertiesManager.getProperty("REQ TO PO", "UDF4", "");
	String	reqToPoReqUdf05 = propertiesManager.getProperty("REQ TO PO", "UDF5", "");
	String	reqToPoReqUdf06 = propertiesManager.getProperty("REQ TO PO", "UDF6", "");
	String	reqToPoReqUdf07 = propertiesManager.getProperty("REQ TO PO", "UDF7", "");
	String	reqToPoReqUdf08 = propertiesManager.getProperty("REQ TO PO", "UDF8", "");
	String	reqToPoReqUdf09 = propertiesManager.getProperty("REQ TO PO", "UDF9", "");
	String	reqToPoReqUdf10 = propertiesManager.getProperty("REQ TO PO", "UDF10", "");
	String	reqToPoReqUdf11 = propertiesManager.getProperty("REQ TO PO", "UDF11", "");
	String	reqToPoReqUdf12 = propertiesManager.getProperty("REQ TO PO", "UDF12", "");
	String	reqToPoReqUdf13 = propertiesManager.getProperty("REQ TO PO", "UDF13", "");
	String	reqToPoReqUdf14 = propertiesManager.getProperty("REQ TO PO", "UDF14", "");
	String	reqToPoReqUdf15 = propertiesManager.getProperty("REQ TO PO", "UDF15", "");
    ///
	String	rfqToPoRfqUdf01 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf01", "");
	String	rfqToPoRfqUdf02 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf02", "");
	String	rfqToPoRfqUdf03 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf03", "");
	String	rfqToPoRfqUdf04 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf04", "");
	String	rfqToPoRfqUdf05 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf05", "");
	String	rfqToPoRfqUdf06 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf06", "");
	String	rfqToPoRfqUdf07 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf07", "");
	String	rfqToPoRfqUdf08 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf08", "");
	String	rfqToPoRfqUdf09 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf09", "");
	String	rfqToPoRfqUdf10 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf10", "");
	String	rfqToPoRfqUdf11 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf11", "");
	String	rfqToPoRfqUdf12 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf12", "");
	String	rfqToPoRfqUdf13 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf13", "");
	String	rfqToPoRfqUdf14 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf14", "");
	String	rfqToPoRfqUdf15 = propertiesManager.getProperty("RFQ TO PO", "rfq_udf15", "");
	///
	String	poToVoucherPoUdf01 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf01", "");
	String	poToVoucherPoUdf02 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf02", "");
	String	poToVoucherPoUdf03 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf03", "");
	String	poToVoucherPoUdf04 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf04", "");
	String	poToVoucherPoUdf05 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf05", "");
	String	poToVoucherPoUdf06 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf06", "");
	String	poToVoucherPoUdf07 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf07", "");
	String	poToVoucherPoUdf08 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf08", "");
	String	poToVoucherPoUdf09 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf09", "");
	String	poToVoucherPoUdf10 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf10", "");
	String	poToVoucherPoUdf11 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf11", "");
	String	poToVoucherPoUdf12 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf12", "");
	String	poToVoucherPoUdf13 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf13", "");
	String	poToVoucherPoUdf14 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf14", "");
	String	poToVoucherPoUdf15 = propertiesManager.getProperty("PO TO VOUCHER", "po_udf15", "");
	///
	String defaultDisplayOption = "";

%>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Document Mappings</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> height=300px>
<tr>
	<td align=center valign=top width=100% id="systemSetupFrame">
		<div id="SystemProcessing" style="visibility: visible; position:absolute; left:25px; width:100%" height:300px>
			<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td width=100% align=center valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "loadingSystemMappingInformation", "Loading system mapping information")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
			</tr>
			</table>
		</div>
	</td>
	<td width=180px align=right valign=top><hr size=0 width=170px>
	  <table border=0 width=170px valign=top cellpadding=2 cellspacing=0 id=processSteps>
<%	int istep = 0;
	if (reqsActive && rfqsActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "ReqToSolicitation";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReqToSolicitation'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReqToSolicitation'); void(0);" id="ReqToSolicitationProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqToSolicitation", "Requisition to Solicitation")%></a>			</td>
		</tr>
<%	}
	if (reqsActive && posActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "ReqToPo";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReqToPo'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReqToPo'); void(0);" id="ReqToPoProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqToPo", "Requisition to Order")%></a>			</td>
		</tr>
<%	}
	if (rfqsActive && posActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "SolicitationToPo";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('SolicitationToPo');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('SolicitationToPo');  void(0);" id="SolicitationToPoProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationToPo", "Solicitation to Order")%></a>			</td>
		</tr>
<%	}
	if (posActive && vouchersActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "PoToVoucher";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PoToVoucher');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PoToVoucher');  void(0);" id="PoToVoucherProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poToInvoice", "Order to Invoice")%></a>			</td>
		</tr>
<%	}%>
	  </table>
		<hr size=0 width=170px>
	</td>
</tr>
</table>

<tsa:hidden name="allowBrowse" value="true"/>
<br><br><br><br><br><br><br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'PropertyUpdate'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/system_mappings_document_header.jsp", "DoNothing", "Document Mappings");

	var reqToSolicitationHTML = "";
	var reqToPoHTML = "";
	var solicitationToPoHTML = "";
	var poToVoucherHTML = "";
	var selectedType = "";

	function thisLoad() {
		populateHTMLVariables();
		populateHTML();
		hideArea("SystemProcessing");
		setSystemOptions("<%=defaultDisplayOption%>");

		f_StartIt();
	}

	function populateHTML() {
		var myFrame = document.getElementById("systemSetupFrame");
		var myHtml = myFrame.innerHTML + getHtml();

		myFrame.innerHTML = myHtml;
	}

	function setSystemOptions(type) {
		if (type != selectedType) {
			var myText = document.getElementById(type + "ProcessLink");
			myText.className = "processOn";

			if (selectedType.length > 0) {
				myText = document.getElementById(selectedType + "ProcessLink");
				myText.className = "processOff";

				if (selectedType == "Password") {
					hideArea("passwordCaseSensitive");
				}

				hideArea(selectedType);
			}

			if (type == "Password") {
				setPasswordDisplay();
			}

			displayArea(type);

			selectedType = type;
		}
	}

	function getHtml() {

		var myHtml = "<div id=" + '"' + "PoToVoucher" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + poToVoucherHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "ReqToSolicitation" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + reqToSolicitationHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "ReqToPo" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:310px; border:1px" + '"' + ">";
		myHtml = myHtml + reqToPoHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "SolicitationToPo" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + solicitationToPoHTML;
		myHtml = myHtml + "</div>";

		return myHtml;
	}

	function populateHTMLVariables() {
		poToVoucherHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Order to Voucher Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF01" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf01.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF02" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf02.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF03" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf03.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF04" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf04.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF05" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf05.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF06" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf06.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF07" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf07.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF08" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf08.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF09" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf09.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF10" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf10.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF11" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf11.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF12" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf12.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF13" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf13.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf13.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF14" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf14.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf14.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>PURCHASE ORDER UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TO VOUCHER" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_UDF15" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poToVoucherPoUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf1Code")) {%> selected<%}%>>VOUCHER UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf2Code")) {%> selected<%}%>>VOUCHER UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf3Code")) {%> selected<%}%>>VOUCHER UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf4Code")) {%> selected<%}%>>VOUCHER UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf5Code")) {%> selected<%}%>>VOUCHER UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf6Code")) {%> selected<%}%>>VOUCHER UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf7Code")) {%> selected<%}%>>VOUCHER UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf8Code")) {%> selected<%}%>>VOUCHER UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf9Code")) {%> selected<%}%>>VOUCHER UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf10Code")) {%> selected<%}%>>VOUCHER UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf11Code")) {%> selected<%}%>>VOUCHER UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf12Code")) {%> selected<%}%>>VOUCHER UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf13Code")) {%> selected<%}%>>VOUCHER UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf14Code")) {%> selected<%}%>>VOUCHER UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (poToVoucherPoUdf15.equals("udf15Code")) {%> selected<%}%>>VOUCHER UDF 15</option>" +
				"</select></td></tr>" +
			"</table>";
		reqToPoHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Requisition to Order Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>REQUISITION UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf01.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>REQUISITION UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf02.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>REQUISITION UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf03.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>REQUISITION UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf04.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>REQUISITION UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf05.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>REQUISITION UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF6" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf06.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>REQUISITION UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF7" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf07.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>REQUISITION UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF8" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf08.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>REQUISITION UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF9" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf09.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>REQUISITION UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF10" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf10.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>REQUISITION UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF11" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf11.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>REQUISITION UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF12" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf12.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>REQUISITION UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF13" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf13.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf13.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>REQUISITION UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF14" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf14.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf14.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>REQUISITION UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF15" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToPoReqUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (reqToPoReqUdf15.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"</table>";
		reqToSolicitationHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Requisition to Solicitation Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>REQUISITION UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf01" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf01.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>REQUISITION UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf02" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf02.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>REQUISITION UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf03" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf03.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>REQUISITION UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf04" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf04.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>REQUISITION UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf05" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf05.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>REQUISITION UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf06" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf06.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>REQUISITION UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf07" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf07.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>REQUISITION UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf08" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf08.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>REQUISITION UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf09" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf09.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>REQUISITION UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf10" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf10.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>REQUISITION UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf11" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf11.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>REQUISITION UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf12" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf12.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>REQUISITION UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf13" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf13.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>REQUISITION UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf14" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf14.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>REQUISITION UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ TO RFQ" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "req_udf15" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqToRfqReqUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "udf1Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf1Code")) {%> selected<%}%>>SOLICITATION UDF 1</option>" +
				"<option value=" + '"' + "udf2Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf2Code")) {%> selected<%}%>>SOLICITATION UDF 2</option>" +
				"<option value=" + '"' + "udf3Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf3Code")) {%> selected<%}%>>SOLICITATION UDF 3</option>" +
				"<option value=" + '"' + "udf4Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf4Code")) {%> selected<%}%>>SOLICITATION UDF 4</option>" +
				"<option value=" + '"' + "udf5Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf5Code")) {%> selected<%}%>>SOLICITATION UDF 5</option>" +
				"<option value=" + '"' + "udf6Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf6Code")) {%> selected<%}%>>SOLICITATION UDF 6</option>" +
				"<option value=" + '"' + "udf7Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf7Code")) {%> selected<%}%>>SOLICITATION UDF 7</option>" +
				"<option value=" + '"' + "udf8Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf8Code")) {%> selected<%}%>>SOLICITATION UDF 8</option>" +
				"<option value=" + '"' + "udf9Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf9Code")) {%> selected<%}%>>SOLICITATION UDF 9</option>" +
				"<option value=" + '"' + "udf10Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf10Code")) {%> selected<%}%>>SOLICITATION UDF 10</option>" +
				"<option value=" + '"' + "udf11Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf11Code")) {%> selected<%}%>>SOLICITATION UDF 11</option>" +
				"<option value=" + '"' + "udf12Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf12Code")) {%> selected<%}%>>SOLICITATION UDF 12</option>" +
				"<option value=" + '"' + "udf13Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf13Code")) {%> selected<%}%>>SOLICITATION UDF 13</option>" +
				"<option value=" + '"' + "udf14Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf14Code")) {%> selected<%}%>>SOLICITATION UDF 14</option>" +
				"<option value=" + '"' + "udf15Code" + '"' + "<% if (reqToRfqReqUdf15.equals("udf15Code")) {%> selected<%}%>>SOLICITATION UDF 15</option>" +
				"</select></td></tr>" +
			"</table>";
		solicitationToPoHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Solicitation to Order Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>SOLICITATION UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf01" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf01.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>SOLICITATION UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf02" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf02.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>SOLICITATION UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf03" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf03.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>SOLICITATION UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf04" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf04.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>SOLICITATION UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf05" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf05.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>SOLICITATION UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf06" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf06.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>SOLICITATION UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf07" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf07.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>SOLICITATION UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf08" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf08.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>SOLICITATION UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ TO PO" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "rfq_udf09" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqToPoRfqUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf01")) {%> selected<%}%>>PURCHASE ORDER UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf02")) {%> selected<%}%>>PURCHASE ORDER UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf03")) {%> selected<%}%>>PURCHASE ORDER UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf04")) {%> selected<%}%>>PURCHASE ORDER UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf05")) {%> selected<%}%>>PURCHASE ORDER UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf06")) {%> selected<%}%>>PURCHASE ORDER UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf07")) {%> selected<%}%>>PURCHASE ORDER UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf08")) {%> selected<%}%>>PURCHASE ORDER UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf09")) {%> selected<%}%>>PURCHASE ORDER UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf10")) {%> selected<%}%>>PURCHASE ORDER UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf11")) {%> selected<%}%>>PURCHASE ORDER UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf12")) {%> selected<%}%>>PURCHASE ORDER UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf13")) {%> selected<%}%>>PURCHASE ORDER UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf14")) {%> selected<%}%>>PURCHASE ORDER UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (rfqToPoRfqUdf09.equals("po_udf15")) {%> selected<%}%>>PURCHASE ORDER UDF 15</option>" +
				"</select></td></tr>" +
			"</table>";
	}

	function setCkBoxValue(ckbox, s, p) {
		var value = "";
		var action = "c";

		if (ckbox.checked == true) {
			value = "Y";
		}
		else {
			value = "N";
		}

		setPropertyValue(s, p, value, action);
	}

	function setPropertyValue(s, p, v, a) {
		var count = document.all.Property_property.length;
		var section = "";
		var property = "";

		for (var i=0; i < count; i++) {
			section = frm.Property_section[i].value;
			property = frm.Property_property[i].value;

			if (section == s && property == p) {
				if (a=="a") {
					frm.Property_value[i].value = frm.Property_value[i].value + v;
				}
				else if (a=="r") {
					frm.Property_value[i].value = removeFrom(frm.Property_value[i].value,v);
				}
				else {
					frm.Property_value[i].value = v;
				}
			}
		}
	}

	function removeFrom(orgText, textToRemove) {
		while (orgText.indexOf(textToRemove) >= 0) {
			var subOrgTextBegin = orgText.substring(0, orgText.indexOf(textToRemove));
			var subOrgTextEnd   = orgText.substring(orgText.indexOf(textToRemove)+textToRemove.length, orgText.length)
			orgText = subOrgTextBegin + subOrgTextEnd;
		}
		return orgText;
	}

	function setRadioValue(fldName, s, p) {
		var options = frm.elements.item(fldName);
		var value = "";

		if (options != undefined && options.length != undefined) {
			var ind = 0;
	        for (var i = 0; i < options.length; i++) {
	            if (options[i].checked) {
    	            ind = i;
        	        break;
            	}
	        }
			value = options[ind].value;
		}

		setPropertyValue(s, p, value);
	}


	function getPropertyValue(s, p, v) {
		var count = document.all.Property_property.length;
		var section = "";
		var property = "";
		var setValue = v;

		for (var i=0; i < count; i++) {
			section = frm.Property_section[i].value;
			property = frm.Property_property[i].value;

			if (section == s && property == p) {
				setValue = frm.Property_value[i].value;
			}
		}

		return setValue;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
