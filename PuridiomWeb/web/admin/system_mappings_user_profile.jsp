<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	///
	String	acctMapUdf01 = propertiesManager.getProperty("ACCT NAME UDFS","UDF1","");
	String	acctMapUdf02 = propertiesManager.getProperty("ACCT NAME UDFS","UDF2","");
	String	acctMapUdf03 = propertiesManager.getProperty("ACCT NAME UDFS","UDF3","");
	String	acctMapUdf04 = propertiesManager.getProperty("ACCT NAME UDFS","UDF4","");
	String	acctMapUdf05 = propertiesManager.getProperty("ACCT NAME UDFS","UDF5","");
	String	acctMapDept = propertiesManager.getProperty("ACCT NAME UDFS","DEPT","");
	String	acctMapCostCenter = propertiesManager.getProperty("ACCT NAME UDFS","COST CENTER","");
	String	acctMapShipTo = propertiesManager.getProperty("ACCT NAME UDFS","SHIP TO","");
	String	acctMapLocale = propertiesManager.getProperty("ACCT NAME UDFS","LOCALE","");
	String	poMapUdf01 = propertiesManager.getProperty("PO NAME UDFS","UDF1","");
	String	poMapUdf02 = propertiesManager.getProperty("PO NAME UDFS","UDF2","");
	String	poMapUdf03 = propertiesManager.getProperty("PO NAME UDFS","UDF3","");
	String	poMapUdf04 = propertiesManager.getProperty("PO NAME UDFS","UDF4","");
	String	poMapUdf05 = propertiesManager.getProperty("PO NAME UDFS","UDF5","");
	String	poMapDept = propertiesManager.getProperty("PO NAME UDFS","DEPT","");
	String	poMapLocale = propertiesManager.getProperty("PO NAME UDFS","LOCALE","");
	String	rfqMapUdf01 = propertiesManager.getProperty("RFQ NAME UDFS","UDF1","");
	String	rfqMapUdf02 = propertiesManager.getProperty("RFQ NAME UDFS","UDF2","");
	String	rfqMapUdf03 = propertiesManager.getProperty("RFQ NAME UDFS","UDF3","");
	String	rfqMapUdf04 = propertiesManager.getProperty("RFQ NAME UDFS","UDF4","");
	String	rfqMapUdf05 = propertiesManager.getProperty("RFQ NAME UDFS","UDF5","");
	String	rfqMapDept = propertiesManager.getProperty("RFQ NAME UDFS","DEPT","");
	String	rfqMapLocale = propertiesManager.getProperty("RFQ NAME UDFS","LOCALE","");
	String	reqMapUdf01 = propertiesManager.getProperty("NAME UDFS","UDF1","");
	String	reqMapUdf02 = propertiesManager.getProperty("NAME UDFS","UDF2","");
	String	reqMapUdf03 = propertiesManager.getProperty("NAME UDFS","UDF3","");
	String	reqMapUdf04 = propertiesManager.getProperty("NAME UDFS","UDF4","");
	String	reqMapUdf05 = propertiesManager.getProperty("NAME UDFS","UDF5","");
	String	reqMapDept = propertiesManager.getProperty("NAME UDFS","DEPT","");
	String	reqMapLocale = propertiesManager.getProperty("NAME UDFS","LOCALE","");

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>User Profile Mappings</div>
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
	if (HiltonUtility.isEmpty(defaultDisplayOption)) {
		defaultDisplayOption = "AcctUdfs";
	}
	istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('AcctUdfs');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('AcctUdfs');  void(0);" id="AcctUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accounts", "Accounts")%></a>			</td>
		</tr>
<%	if (reqsActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "ReqUdfs";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReqUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReqUdfs'); void(0);" id="ReqUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitions", "Requisitions")%></a>			</td>
		</tr>
<%	}
	if (rfqsActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "RfqUdfs";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('RfqUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('RfqUdfs'); void(0);" id="RfqUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitations", "Solicitations")%></a>			</td>
		</tr>
<%	}
	if (posActive) {
		if (HiltonUtility.isEmpty(defaultDisplayOption)) {
			defaultDisplayOption = "PoUdfs";
		}
		istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PoUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PoUdfs'); void(0);" id="PoUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purchaseOrders", "Purchase Orders")%></a>			</td>
		</tr>
<%	}%>
	  </table>
		<hr size=0 width=170px>
	</td>
</tr>
</table>

<tsa:hidden name="allowBrowse" value="true"/>
<br>
<br>

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
	setNavCookie("/admin/system_mappings_user_profile.jsp", "DoNothing", "User Profile Mappings");

	var acctUdfsHTML = "";
	var reqUdfsHTML = "";
	var rfqUdfsHTML = "";
	var poUdfsHTML = "";
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

		var myHtml = "<div id=" + '"' + "AcctUdfs" + '"' + " style=" + '"' + "visibility: hidden; display: block; position:absolute; left:25px; height:310px;" + '"' + ">";
		myHtml = myHtml + acctUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "PoUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:310px;" + '"' + ">";
		myHtml = myHtml + poUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "ReqUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:310px;" + '"' + ">";
		myHtml = myHtml + reqUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "RfqUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + rfqUdfsHTML;
		myHtml = myHtml + "</div>";

		return myHtml;
	}

	function populateHTMLVariables() {
		acctUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Account Udf Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>USER DEFINED FIELD 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapUdf01.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapUdf01.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapUdf01.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapUdf01.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapUdf01.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapUdf01.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapUdf01.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapUdf01.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapUdf01.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapUdf01.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapUdf01.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapUdf01.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapUdf01.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapUdf01.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapUdf01.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>USER DEFINED FIELD 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapUdf02.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapUdf02.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapUdf02.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapUdf02.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapUdf02.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapUdf02.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapUdf02.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapUdf02.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapUdf02.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapUdf02.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapUdf02.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapUdf02.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapUdf02.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapUdf02.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapUdf02.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>USER DEFINED FIELD 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapUdf03.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapUdf03.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapUdf03.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapUdf03.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapUdf03.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapUdf03.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapUdf03.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapUdf03.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapUdf03.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapUdf03.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapUdf03.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapUdf03.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapUdf03.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapUdf03.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapUdf03.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>USER DEFINED FIELD 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapUdf04.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapUdf04.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapUdf04.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapUdf04.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapUdf04.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapUdf04.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapUdf04.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapUdf04.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapUdf04.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapUdf04.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapUdf04.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapUdf04.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapUdf04.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapUdf04.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapUdf04.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>USER DEFINED FIELD 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapUdf05.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapUdf05.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapUdf05.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapUdf05.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapUdf05.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapUdf05.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapUdf05.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapUdf05.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapUdf05.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapUdf05.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapUdf05.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapUdf05.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapUdf05.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapUdf05.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapUdf05.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEPT" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapDept.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapDept.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapDept.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapDept.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapDept.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapDept.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapDept.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapDept.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapDept.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapDept.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapDept.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapDept.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapDept.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapDept.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapDept.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapDept.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "label", "Cost Center")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "COST CENTER" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapCostCenter.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapCostCenter.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapCostCenter.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapCostCenter.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapCostCenter.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapCostCenter.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapCostCenter.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapCostCenter.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapCostCenter.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapCostCenter.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapCostCenter.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapCostCenter.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapCostCenter.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapCostCenter.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapCostCenter.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapCostCenter.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>8.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "label", "Ship To Code")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHIP TO" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapShipTo.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapShipTo.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapShipTo.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapShipTo.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapShipTo.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapShipTo.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapShipTo.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapShipTo.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapShipTo.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapShipTo.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapShipTo.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapShipTo.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapShipTo.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapShipTo.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapShipTo.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapShipTo.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>9.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locale", "Locale")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCT NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOCALE" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (acctMapLocale.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "Account_fld1" + '"' + "<% if (acctMapLocale.equals("Account_fld1")) {%> selected<%}%>>Account UDF 1</option>" +
				"<option value=" + '"' + "Account_fld2" + '"' + "<% if (acctMapLocale.equals("Account_fld2")) {%> selected<%}%>>Account UDF 2</option>" +
				"<option value=" + '"' + "Account_fld3" + '"' + "<% if (acctMapLocale.equals("Account_fld3")) {%> selected<%}%>>Account UDF 3</option>" +
				"<option value=" + '"' + "Account_fld4" + '"' + "<% if (acctMapLocale.equals("Account_fld4")) {%> selected<%}%>>Account UDF 4</option>" +
				"<option value=" + '"' + "Account_fld5" + '"' + "<% if (acctMapLocale.equals("Account_fld5")) {%> selected<%}%>>Account UDF 5</option>" +
				"<option value=" + '"' + "Account_fld6" + '"' + "<% if (acctMapLocale.equals("Account_fld6")) {%> selected<%}%>>Account UDF 6</option>" +
				"<option value=" + '"' + "Account_fld7" + '"' + "<% if (acctMapLocale.equals("Account_fld7")) {%> selected<%}%>>Account UDF 7</option>" +
				"<option value=" + '"' + "Account_fld8" + '"' + "<% if (acctMapLocale.equals("Account_fld8")) {%> selected<%}%>>Account UDF 8</option>" +
				"<option value=" + '"' + "Account_fld9" + '"' + "<% if (acctMapLocale.equals("Account_fld9")) {%> selected<%}%>>Account UDF 9</option>" +
				"<option value=" + '"' + "Account_fld10" + '"' + "<% if (acctMapLocale.equals("Account_fld10")) {%> selected<%}%>>Account UDF 10</option>" +
				"<option value=" + '"' + "Account_fld11" + '"' + "<% if (acctMapLocale.equals("Account_fld11")) {%> selected<%}%>>Account UDF 11</option>" +
				"<option value=" + '"' + "Account_fld12" + '"' + "<% if (acctMapLocale.equals("Account_fld12")) {%> selected<%}%>>Account UDF 12</option>" +
				"<option value=" + '"' + "Account_fld13" + '"' + "<% if (acctMapLocale.equals("Account_fld13")) {%> selected<%}%>>Account UDF 13</option>" +
				"<option value=" + '"' + "Account_fld14" + '"' + "<% if (acctMapLocale.equals("Account_fld14")) {%> selected<%}%>>Account UDF 14</option>" +
				"<option value=" + '"' + "Account_fld15" + '"' + "<% if (acctMapLocale.equals("Account_fld15")) {%> selected<%}%>>Account UDF 15</option>" +
				"</select></td></tr>" +
			"</table>";
		poUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>PO UDF Mapping</b></td><td align=center><b>Protect</b></td><td>&nbsp;</td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=150px>User Defined Field 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_routing" + '"' + "<% if (poMapUdf01.equals("PoHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "PoHeader_shipViaCode" + '"' + "<% if (poMapUdf01.equals("PoHeader_shipViaCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "PoHeader_shipToCode" + '"' + "<% if (poMapUdf01.equals("PoHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "PoHeader_billToCode" + '"' + "<% if (poMapUdf01.equals("PoHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "PoHeader_itemLocation" + '"' + "<% if (poMapUdf01.equals("PoHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "PoHeader_priorityCode" + '"' + "<% if (poMapUdf01.equals("PoHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "PoHeader_taxCode" + '"' + "<% if (poMapUdf01.equals("PoHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "PoHeader_billToContact" + '"' + "<% if (poMapUdf01.equals("PoHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapUdf01.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapUdf01.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapUdf01.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapUdf01.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapUdf01.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTUDF1" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF1","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF1","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTUDF1');" + '"' + "></td>" +
			"<tr><td align=right>2.&nbsp;</td><td width=150px>User Defined Field 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_routing" + '"' + "<% if (poMapUdf02.equals("PoHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "PoHeader_shipViaCode" + '"' + "<% if (poMapUdf02.equals("PoHeader_shipViaCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "PoHeader_shipToCode" + '"' + "<% if (poMapUdf02.equals("PoHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "PoHeader_billToCode" + '"' + "<% if (poMapUdf02.equals("PoHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "PoHeader_itemLocation" + '"' + "<% if (poMapUdf02.equals("PoHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "PoHeader_priorityCode" + '"' + "<% if (poMapUdf02.equals("PoHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "PoHeader_taxCode" + '"' + "<% if (poMapUdf02.equals("PoHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "PoHeader_billToContact" + '"' + "<% if (poMapUdf02.equals("PoHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapUdf02.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapUdf02.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapUdf02.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapUdf02.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapUdf02.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTUDF2" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF2","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF2","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTUDF2');" + '"' + "></td>" +
			"<tr><td align=right>3.&nbsp;</td><td width=150px>User Defined Field 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_routing" + '"' + "<% if (poMapUdf03.equals("PoHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "PoHeader_shipViaCode" + '"' + "<% if (poMapUdf03.equals("PoHeader_shipViaCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "PoHeader_shipToCode" + '"' + "<% if (poMapUdf03.equals("PoHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "PoHeader_billToCode" + '"' + "<% if (poMapUdf03.equals("PoHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "PoHeader_itemLocation" + '"' + "<% if (poMapUdf03.equals("PoHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "PoHeader_priorityCode" + '"' + "<% if (poMapUdf03.equals("PoHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "PoHeader_taxCode" + '"' + "<% if (poMapUdf03.equals("PoHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "PoHeader_billToContact" + '"' + "<% if (poMapUdf03.equals("PoHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapUdf03.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapUdf03.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapUdf03.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapUdf03.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapUdf03.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTUDF3" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF3","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF3","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTUDF3');" + '"' + "></td>" +
			"<tr><td align=right>4.&nbsp;</td><td width=150px>User Defined Field 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_routing" + '"' + "<% if (poMapUdf04.equals("PoHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "PoHeader_shipViaCode" + '"' + "<% if (poMapUdf04.equals("PoHeader_shipViaCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "PoHeader_shipToCode" + '"' + "<% if (poMapUdf04.equals("PoHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "PoHeader_billToCode" + '"' + "<% if (poMapUdf04.equals("PoHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "PoHeader_itemLocation" + '"' + "<% if (poMapUdf04.equals("PoHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "PoHeader_priorityCode" + '"' + "<% if (poMapUdf04.equals("PoHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "PoHeader_taxCode" + '"' + "<% if (poMapUdf04.equals("PoHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "PoHeader_billToContact" + '"' + "<% if (poMapUdf04.equals("PoHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapUdf04.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapUdf04.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapUdf04.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapUdf04.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapUdf04.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTUDF4" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF4","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF4","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTUDF4');" + '"' + "></td>" +
			"<tr><td align=right>5.&nbsp;</td><td width=150px>User Defined Field 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_routing" + '"' + "<% if (poMapUdf05.equals("PoHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "PoHeader_shipViaCode" + '"' + "<% if (poMapUdf05.equals("PoHeader_shipViaCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "PoHeader_shipToCode" + '"' + "<% if (poMapUdf05.equals("PoHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "PoHeader_billToCode" + '"' + "<% if (poMapUdf05.equals("PoHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "PoHeader_itemLocation" + '"' + "<% if (poMapUdf05.equals("PoHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "PoHeader_priorityCode" + '"' + "<% if (poMapUdf05.equals("PoHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "PoHeader_taxCode" + '"' + "<% if (poMapUdf05.equals("PoHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "PoHeader_billToContact" + '"' + "<% if (poMapUdf05.equals("PoHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapUdf05.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapUdf05.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapUdf05.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapUdf05.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapUdf05.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTUDF5" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF5","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTUDF5","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTUDF5');" + '"' + "></td>" +
			"<tr><td align=right>6.&nbsp;</td><td width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEPT" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapDept.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapDept.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapDept.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapDept.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapDept.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapDept.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTDEPT" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTDEPT","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTDEPT","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTDEPT');" + '"' + "></td>" +
			"<tr><td align=right>7.&nbsp;</td><td width=150px>Locale</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOCALE" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (poMapLocale.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "PoHeader_udf1Code" + '"' + "<% if (poMapLocale.equals("PoHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "PoHeader_udf2Code" + '"' + "<% if (poMapLocale.equals("PoHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "PoHeader_udf3Code" + '"' + "<% if (poMapLocale.equals("PoHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "PoHeader_udf4Code" + '"' + "<% if (poMapLocale.equals("PoHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "PoHeader_udf5Code" + '"' + "<% if (poMapLocale.equals("PoHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td>" +
				"<td align=center><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PROTECTLOCALE" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO NAME UDFS","PROTECTLOCALE","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO NAME UDFS","PROTECTLOCALE","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO NAME UDFS', 'PROTECTLOCALE');" + '"' + "></td></tr>" +
			"</table>";
		reqUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Requisition UDF Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=175px>User Defined Field 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_routing" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RequisitionHeader_shippingCode" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RequisitionHeader_shipToCode" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToCode" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_buyer" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_buyer")) {%> selected<%}%>>Buyer</option>" +
				"<option value=" + '"' + "RequisitionHeader_itemLocation" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RequisitionHeader_priorityCode" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RequisitionHeader_taxCode" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToContact" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapUdf01.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=175px>User Defined Field 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_routing" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RequisitionHeader_shippingCode" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RequisitionHeader_shipToCode" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToCode" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_buyer" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_buyer")) {%> selected<%}%>>Buyer</option>" +
				"<option value=" + '"' + "RequisitionHeader_itemLocation" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RequisitionHeader_priorityCode" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RequisitionHeader_taxCode" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToContact" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapUdf02.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=175px>User Defined Field 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_routing" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RequisitionHeader_shippingCode" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RequisitionHeader_shipToCode" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToCode" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_buyer" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_buyer")) {%> selected<%}%>>Buyer</option>" +
				"<option value=" + '"' + "RequisitionHeader_itemLocation" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RequisitionHeader_priorityCode" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RequisitionHeader_taxCode" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToContact" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapUdf03.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=175px>User Defined Field 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_routing" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RequisitionHeader_shippingCode" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RequisitionHeader_shipToCode" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToCode" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_buyer" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_buyer")) {%> selected<%}%>>Buyer</option>" +
				"<option value=" + '"' + "RequisitionHeader_itemLocation" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RequisitionHeader_priorityCode" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RequisitionHeader_taxCode" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToContact" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapUdf04.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=175px>User Defined Field 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_routing" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RequisitionHeader_shippingCode" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RequisitionHeader_shipToCode" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToCode" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RequisitionHeader_buyer" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_buyer")) {%> selected<%}%>>Buyer</option>" +
				"<option value=" + '"' + "RequisitionHeader_itemLocation" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RequisitionHeader_priorityCode" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RequisitionHeader_taxCode" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RequisitionHeader_billToContact" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_billToContact")) {%> selected<%}%>>Bill To Attention</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapUdf05.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEPT" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapDept.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapDept.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapDept.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapDept.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapDept.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapDept.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>Locale</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOCALE" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (reqMapLocale.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf1Code" + '"' + "<% if (reqMapLocale.equals("RequisitionHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf2Code" + '"' + "<% if (reqMapLocale.equals("RequisitionHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf3Code" + '"' + "<% if (reqMapLocale.equals("RequisitionHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf4Code" + '"' + "<% if (reqMapLocale.equals("RequisitionHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RequisitionHeader_udf5Code" + '"' + "<% if (reqMapLocale.equals("RequisitionHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"</table>";
		rfqUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Solicitation UDF Mapping</b></td></tr>" +
			"<tr><td align=right>1.&nbsp;</td><td width=150px>User Defined Field 1</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF1" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapUdf01.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_routing" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RfqHeader_shippingCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RfqHeader_shipToCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RfqHeader_billToCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RfqHeader_itemLocation" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RfqHeader_priorityCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RfqHeader_taxCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>2.&nbsp;</td><td width=150px>User Defined Field 2</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF2" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapUdf02.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_routing" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RfqHeader_shippingCode" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RfqHeader_shipToCode" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RfqHeader_billToCode" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RfqHeader_itemLocation" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RfqHeader_priorityCode" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RfqHeader_taxCode" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapUdf02.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>3.&nbsp;</td><td width=150px>User Defined Field 3</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF3" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapUdf03.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_routing" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RfqHeader_shippingCode" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RfqHeader_shipToCode" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RfqHeader_billToCode" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RfqHeader_itemLocation" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RfqHeader_priorityCode" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RfqHeader_taxCode" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapUdf03.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>4.&nbsp;</td><td width=150px>User Defined Field 4</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF4" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapUdf04.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_routing" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RfqHeader_shippingCode" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RfqHeader_shipToCode" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RfqHeader_billToCode" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RfqHeader_itemLocation" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RfqHeader_priorityCode" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RfqHeader_taxCode" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapUdf04.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>5.&nbsp;</td><td width=150px>User Defined Field 5</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDF5" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapUdf05.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_routing" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_routing")) {%> selected<%}%>>Routing</option>" +
				"<option value=" + '"' + "RfqHeader_shippingCode" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_shippingCode")) {%> selected<%}%>>Ship Via</option>" +
				"<option value=" + '"' + "RfqHeader_shipToCode" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_shipToCode")) {%> selected<%}%>>Ship To Address</option>" +
				"<option value=" + '"' + "RfqHeader_billToCode" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_billToCode")) {%> selected<%}%>>Bill To Address</option>" +
				"<option value=" + '"' + "RfqHeader_itemLocation" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_itemLocation")) {%> selected<%}%>>Inventory Location</option>" +
				"<option value=" + '"' + "RfqHeader_priorityCode" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_priorityCode")) {%> selected<%}%>>Priority</option>" +
				"<option value=" + '"' + "RfqHeader_taxCode" + '"' + "<% if (rfqMapUdf01.equals("RfqHeader_taxCode")) {%> selected<%}%>>Tax Code</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapUdf05.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>6.&nbsp;</td><td width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEPT" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapDept.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapDept.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapDept.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapDept.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapDept.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapDept.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>7.&nbsp;</td><td width=175px>Locale</td>" +
				"<td><input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ NAME UDFS" + '"' + ">" +
				"<input type=" + '"' + "hidden" + '"' + " name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOCALE" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (rfqMapLocale.equals("")) {%> selected<%}%>>[No Mapping]</option>" +
				"<option value=" + '"' + "RfqHeader_udf1Code" + '"' + "<% if (rfqMapLocale.equals("RfqHeader_udf1Code")) {%> selected<%}%>>User Defined Field 1</option>" +
				"<option value=" + '"' + "RfqHeader_udf2Code" + '"' + "<% if (rfqMapLocale.equals("RfqHeader_udf2Code")) {%> selected<%}%>>User Defined Field 2</option>" +
				"<option value=" + '"' + "RfqHeader_udf3Code" + '"' + "<% if (rfqMapLocale.equals("RfqHeader_udf3Code")) {%> selected<%}%>>User Defined Field 3</option>" +
				"<option value=" + '"' + "RfqHeader_udf4Code" + '"' + "<% if (rfqMapLocale.equals("RfqHeader_udf4Code")) {%> selected<%}%>>User Defined Field 4</option>" +
				"<option value=" + '"' + "RfqHeader_udf5Code" + '"' + "<% if (rfqMapLocale.equals("RfqHeader_udf5Code")) {%> selected<%}%>>User Defined Field 5</option>" +
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
