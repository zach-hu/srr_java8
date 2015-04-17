<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	reqHeaderToLinesUdf01 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF1", "");
	String	reqHeaderToLinesUdf02 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF2", "");
	String	reqHeaderToLinesUdf03 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF3", "");
	String	reqHeaderToLinesUdf04 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF4", "");
	String	reqHeaderToLinesUdf05 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF5", "");
	String	reqHeaderToLinesUdf06 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF6", "");
	String	reqHeaderToLinesUdf07 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF7", "");
	String	reqHeaderToLinesUdf08 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF8", "");
	String	reqHeaderToLinesUdf09 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF9", "");
	String	reqHeaderToLinesUdf10 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF10", "");
	String	reqHeaderToLinesUdf11 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF11", "");
	String	reqHeaderToLinesUdf12 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF12", "");
	String	reqHeaderToLinesUdf13 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF13", "");
	String	reqHeaderToLinesUdf14 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF14", "");
	String	reqHeaderToLinesUdf15 = propertiesManager.getProperty("REQ HEADER TO LINES", "UDF15", "");
	String 	reqHeaderToLinesPriority = propertiesManager.getProperty("REQ HEADER TO LINE", "PRIORITY", "");
	///
	String	rfqHeaderToLinesUdf01 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF1", "");
	String	rfqHeaderToLinesUdf02 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF2", "");
	String	rfqHeaderToLinesUdf03 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF3", "");
	String	rfqHeaderToLinesUdf04 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF4", "");
	String	rfqHeaderToLinesUdf05 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF5", "");
	String	rfqHeaderToLinesUdf06 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF6", "");
	String	rfqHeaderToLinesUdf07 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF7", "");
	String	rfqHeaderToLinesUdf08 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF8", "");
	String	rfqHeaderToLinesUdf09 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF9", "");
	String	rfqHeaderToLinesUdf10 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF10", "");
	String	rfqHeaderToLinesUdf11 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF11", "");
	String	rfqHeaderToLinesUdf12 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF12", "");
	String	rfqHeaderToLinesUdf13 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF13", "");
	String	rfqHeaderToLinesUdf14 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF14", "");
	String	rfqHeaderToLinesUdf15 = propertiesManager.getProperty("RFQ HEADER TO LINES", "UDF15", "");
	///
	String	poHeaderToLinesUdf01 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF1", "");
	String	poHeaderToLinesUdf02 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF2", "");
	String	poHeaderToLinesUdf03 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF3", "");
	String	poHeaderToLinesUdf04 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF4", "");
	String	poHeaderToLinesUdf05 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF5", "");
	String	poHeaderToLinesUdf06 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF6", "");
	String	poHeaderToLinesUdf07 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF7", "");
	String	poHeaderToLinesUdf08 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF8", "");
	String	poHeaderToLinesUdf09 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF9", "");
	String	poHeaderToLinesUdf10 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF10", "");
	String	poHeaderToLinesUdf11 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF11", "");
	String	poHeaderToLinesUdf12 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF12", "");
	String	poHeaderToLinesUdf13 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF13", "");
	String	poHeaderToLinesUdf14 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF14", "");
	String	poHeaderToLinesUdf15 = propertiesManager.getProperty("PO HEADER TO LINES", "UDF15", "");
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Document Line Item Mappings</div>
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
	if (reqsActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "ReqHeaderToLines";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReqHeaderToLines'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReqHeaderToLines'); void(0);" id="ReqHeaderToLinesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqHeaderToLines", "Requisition Header to Lines")%></a>			</td>
		</tr>
<%	}
	if (posActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "PoHeaderToLines";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PoHeaderToLines'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PoHeaderToLines'); void(0);" id="PoHeaderToLinesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poHeaderToLines", "Order Header to Lines")%></a>			</td>
		</tr>
<%	}
	if (rfqsActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "RfqHeaderToLines";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('RfqHeaderToLines'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('RfqHeaderToLines'); void(0);" id="RfqHeaderToLinesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqHeaderToLines", "Solicitation Header to Lines")%></a>			</td>
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
	setNavCookie("/admin/system_mappings_document_header_lines.jsp", "DoNothing", "Document Line Item Mappings");

	var reqHeaderToLinesHTML = "";
	var rfqHeaderToLinesHTML = "";
	var poHeaderToLinesHTML = "";
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

		var myHtml = "<div id=" + '"' + "ReqHeaderToLines" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + reqHeaderToLinesHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "RfqHeaderToLines" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + rfqHeaderToLinesHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "PoHeaderToLines" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + poHeaderToLinesHTML;
		myHtml = myHtml + "</div>";

		return myHtml;
	}

	function populateHTMLVariables() {
		reqHeaderToLinesHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Requisition Header to Lines Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf01.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf02.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf03.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf04.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf05.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF6" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf06.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF7" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf07.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF8" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf08.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF9" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf09.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF10" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf10.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF11" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf11.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF12" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf12.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF13" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf13.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf13.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF14" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf14.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf14.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>REQUISITION HEADER UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF15" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesUdf15.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>REQUISITION HEADER UDF PRIORITY</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PRIORITY" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqHeaderToLinesUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "req_udf01" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf01")) {%> selected<%}%>>REQUISITION LINE UDF 1</option>" +
				"<option value=" + '"' + "req_udf02" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf02")) {%> selected<%}%>>REQUISITION LINE UDF 2</option>" +
				"<option value=" + '"' + "req_udf03" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf03")) {%> selected<%}%>>REQUISITION LINE UDF 3</option>" +
				"<option value=" + '"' + "req_udf04" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf04")) {%> selected<%}%>>REQUISITION LINE UDF 4</option>" +
				"<option value=" + '"' + "req_udf05" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf05")) {%> selected<%}%>>REQUISITION LINE UDF 5</option>" +
				"<option value=" + '"' + "req_udf06" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf06")) {%> selected<%}%>>REQUISITION LINE UDF 6</option>" +
				"<option value=" + '"' + "req_udf07" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf07")) {%> selected<%}%>>REQUISITION LINE UDF 7</option>" +
				"<option value=" + '"' + "req_udf08" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf08")) {%> selected<%}%>>REQUISITION LINE UDF 8</option>" +
				"<option value=" + '"' + "req_udf09" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf09")) {%> selected<%}%>>REQUISITION LINE UDF 9</option>" +
				"<option value=" + '"' + "req_udf10" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf10")) {%> selected<%}%>>REQUISITION LINE UDF 10</option>" +
				"<option value=" + '"' + "req_udf11" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf11")) {%> selected<%}%>>REQUISITION LINE UDF 11</option>" +
				"<option value=" + '"' + "req_udf12" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf12")) {%> selected<%}%>>REQUISITION LINE UDF 12</option>" +
				"<option value=" + '"' + "req_udf13" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf13")) {%> selected<%}%>>REQUISITION LINE UDF 13</option>" +
				"<option value=" + '"' + "req_udf14" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf14")) {%> selected<%}%>>REQUISITION LINE UDF 14</option>" +
				"<option value=" + '"' + "req_udf15" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_udf15")) {%> selected<%}%>>REQUISITION LINE UDF 15</option>" +
				"<option value=" + '"' + "req_commodity" + '"' + "<% if (reqHeaderToLinesPriority.equals("req_commodity")) {%> selected<%}%>>REQUISITION LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"</table>";
		poHeaderToLinesHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Order Header to Lines Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>PO HEADER UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf01.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>PO HEADER UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf02.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>PO HEADER UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf03.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>PO HEADER UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf04.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>PO HEADER UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf05.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>PO HEADER UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF6" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf06.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>PO HEADER UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF7" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf07.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>PO HEADER UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF8" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf08.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>PO HEADER UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF9" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf09.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>PO HEADER UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF10" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf10.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>PO HEADER UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF11" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf11.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>PO HEADER UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF12" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf12.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>PO HEADER UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF13" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf13.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf13.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>PO HEADER UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF14" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf14.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf14.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>PO HEADER UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF15" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poHeaderToLinesUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "po_udf01" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf01")) {%> selected<%}%>>PO LINE UDF 1</option>" +
				"<option value=" + '"' + "po_udf02" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf02")) {%> selected<%}%>>PO LINE UDF 2</option>" +
				"<option value=" + '"' + "po_udf03" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf03")) {%> selected<%}%>>PO LINE UDF 3</option>" +
				"<option value=" + '"' + "po_udf04" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf04")) {%> selected<%}%>>PO LINE UDF 4</option>" +
				"<option value=" + '"' + "po_udf05" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf05")) {%> selected<%}%>>PO LINE UDF 5</option>" +
				"<option value=" + '"' + "po_udf06" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf06")) {%> selected<%}%>>PO LINE UDF 6</option>" +
				"<option value=" + '"' + "po_udf07" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf07")) {%> selected<%}%>>PO LINE UDF 7</option>" +
				"<option value=" + '"' + "po_udf08" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf08")) {%> selected<%}%>>PO LINE UDF 8</option>" +
				"<option value=" + '"' + "po_udf09" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf09")) {%> selected<%}%>>PO LINE UDF 9</option>" +
				"<option value=" + '"' + "po_udf10" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf10")) {%> selected<%}%>>PO LINE UDF 10</option>" +
				"<option value=" + '"' + "po_udf11" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf11")) {%> selected<%}%>>PO LINE UDF 11</option>" +
				"<option value=" + '"' + "po_udf12" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf12")) {%> selected<%}%>>PO LINE UDF 12</option>" +
				"<option value=" + '"' + "po_udf13" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf13")) {%> selected<%}%>>PO LINE UDF 13</option>" +
				"<option value=" + '"' + "po_udf14" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf14")) {%> selected<%}%>>PO LINE UDF 14</option>" +
				"<option value=" + '"' + "po_udf15" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_udf15")) {%> selected<%}%>>PO LINE UDF 15</option>" +
				"<option value=" + '"' + "po_commodity" + '"' + "<% if (poHeaderToLinesUdf15.equals("po_commodity")) {%> selected<%}%>>PO LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"</table>";
		rfqHeaderToLinesHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Solicitation Header to Lines Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>RFQ HEADER UDF 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf01.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>RFQ HEADER UDF 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf02.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>RFQ HEADER UDF 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf03.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>RFQ HEADER UDF 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf04.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>RFQ HEADER UDF 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf05.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px>RFQ HEADER UDF 6</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF6" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf06.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>RFQ HEADER UDF 7</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF7" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf07.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px>RFQ HEADER UDF 8</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF8" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf08.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px>RFQ HEADER UDF 9</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF9" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf09.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>10.&nbsp;</td><td width=175px>RFQ HEADER UDF 10</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF10" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf10.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>11.&nbsp;</td><td width=175px>RFQ HEADER UDF 11</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF11" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf11.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>12.&nbsp;</td><td width=175px>RFQ HEADER UDF 12</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF12" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf12.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>13.&nbsp;</td><td width=175px>RFQ HEADER UDF 13</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF13" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf13.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>14.&nbsp;</td><td width=175px>RFQ HEADER UDF 14</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF14" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf14.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>15.&nbsp;</td><td width=175px>RFQ HEADER UDF 15</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ HEADER TO LINES" + '"' + "/>" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF15" + '"' + "/>" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "rfq_udf01" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf01")) {%> selected<%}%>>RFQ LINE UDF 1</option>" +
				"<option value=" + '"' + "rfq_udf02" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf02")) {%> selected<%}%>>RFQ LINE UDF 2</option>" +
				"<option value=" + '"' + "rfq_udf03" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf03")) {%> selected<%}%>>RFQ LINE UDF 3</option>" +
				"<option value=" + '"' + "rfq_udf04" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf04")) {%> selected<%}%>>RFQ LINE UDF 4</option>" +
				"<option value=" + '"' + "rfq_udf05" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf05")) {%> selected<%}%>>RFQ LINE UDF 5</option>" +
				"<option value=" + '"' + "rfq_udf06" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf06")) {%> selected<%}%>>RFQ LINE UDF 6</option>" +
				"<option value=" + '"' + "rfq_udf07" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf07")) {%> selected<%}%>>RFQ LINE UDF 7</option>" +
				"<option value=" + '"' + "rfq_udf08" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf08")) {%> selected<%}%>>RFQ LINE UDF 8</option>" +
				"<option value=" + '"' + "rfq_udf09" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf09")) {%> selected<%}%>>RFQ LINE UDF 9</option>" +
				"<option value=" + '"' + "rfq_udf10" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf10")) {%> selected<%}%>>RFQ LINE UDF 10</option>" +
				"<option value=" + '"' + "rfq_udf11" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf11")) {%> selected<%}%>>RFQ LINE UDF 11</option>" +
				"<option value=" + '"' + "rfq_udf12" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf12")) {%> selected<%}%>>RFQ LINE UDF 12</option>" +
				"<option value=" + '"' + "rfq_udf13" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf13")) {%> selected<%}%>>RFQ LINE UDF 13</option>" +
				"<option value=" + '"' + "rfq_udf14" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf14")) {%> selected<%}%>>RFQ LINE UDF 14</option>" +
				"<option value=" + '"' + "rfq_udf15" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_udf15")) {%> selected<%}%>>RFQ LINE UDF 15</option>" +
				"<option value=" + '"' + "rfq_commodity" + '"' + "<% if (rfqHeaderToLinesUdf15.equals("rfq_commodity")) {%> selected<%}%>>RFQ LINE COMMODITY</option>" +
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
