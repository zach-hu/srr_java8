<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	reqFormat = propertiesManager.getProperty("AUTONUMBER","ReqFormat","NNNNNN");
	String	reqMFormat = propertiesManager.getProperty("AUTONUMBER","ReqMFormat","NNNNNN");
	String	rfqFormat = propertiesManager.getProperty("AUTONUMBER","RfqFormat","NNNNNN");
	String	poFormat = propertiesManager.getProperty("AUTONUMBER","PoFormat","NNNNNN");
	String	dsbFormat = propertiesManager.getProperty("AUTONUMBER","DsbFormat","NNNNNN");
	String accountElements = propertiesManager.getProperty("FORM","ACCOUNTELEMENTS","15");
	String accountDescElements = propertiesManager.getProperty("FORM","ACCOUNTDESCELEMENTS","0");
	String colorSchemeDefault = propertiesManager.getProperty("MISC","COLORSCHEMEDEFAULT","orange");
	String udfLogoDefault = propertiesManager.getProperty("MISC","UDFLOGO","UDF1");
	String recentAcctSize = propertiesManager.getProperty("ACCOUNTS","RECENTACCOUNTSIZE","5");
	String reportOnceIsScheduled  = propertiesManager.getProperty("REPORT OPTIONS","ONCE FREQUENCY","");
	String reportPublicAccess	  = propertiesManager.getProperty("REPORT OPTIONS","PUBLIC ACCESS","");
	String reportFilePublicAccess = propertiesManager.getProperty("REPORT OPTIONS","FILE PUBLIC ACCESS","");
	String reportTypesAllowed	  = propertiesManager.getProperty("REPORT OPTIONS","TYPES ALLOWED","");
	String reportSizeProperty	  = propertiesManager.getProperty("REPORT OPTIONS", "REPORTSIZE", "");
	String reportSize = "";
	String reportUnitSize = "";
	String defaultDisplayOption = "";
	int msrReqAccess = role.getAccessRights("REQM") ;

	if (reportSizeProperty.length() >=2) {
		reportSize = reportSizeProperty.substring(0, reportSizeProperty.length() - 2);
	}
	if (reportSizeProperty.length() >=2) {
		reportUnitSize = reportSizeProperty.substring(reportSizeProperty.length() - 2, reportSizeProperty.length());
	}

	String fyBegin = propertiesManager.getProperty("MISC","fybegin","1");
	String dateFormat = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	String ownerOnly = propertiesManager.getProperty("MISC", "OwnerOnly", "Y");
	String adminEmailAddr = propertiesManager.getProperty("MAILEVENTS", "AdminEmailAddr","support@puridiom.com");
	String accountingEmailAddr = propertiesManager.getProperty("VENDOR-REGISTRATION", "Accounting Email Addr", "");
	String vendorRegNotificationEmailAddr = propertiesManager.getProperty("VENDOR-REGISTRATION", "Notification Email Addr", "");
	String qualificationReminderDays = propertiesManager.getProperty("VENDOR-REGISTRATION", "Qualification Reminder Days", "");
	String	languagesLoaded = propertiesManager.getProperty("MISC","LANGUAGES","");
	String	languages[] = new String[8];
	if (languagesLoaded == null) { languagesLoaded = ""; }

	for (int il = 0; il < 8; il++) {
		int index = languagesLoaded.indexOf(",");

		if (index > 0) {
			languages[il] = languagesLoaded.substring(0, index);
			languagesLoaded = languagesLoaded.substring(index + 1);
		} else {
			if (languagesLoaded.trim().length() > 0) {
				languages[il] = languagesLoaded;
				languagesLoaded = "";
			} else {
				languages[il] = "";
			}
		}
	}

	List listAutoGen = (List) request.getAttribute("autoGen");
	String s_gen_year_ast    = "";
	String s_next_number_ast = "";
	String s_gen_year_dsb    = "";
	String s_next_number_dsb = "";
	String s_gen_year_po     = "";
	String s_next_number_po  = "";
	String s_gen_year_req    = "";
	String s_next_number_req = "";
	String s_gen_year_reqm   = "";
	String s_next_number_reqm= "";
	String s_gen_year_rfq    = "";
	String s_next_number_rfq = "";
	for(int i=0; i<listAutoGen.size(); i++)
	{
		AutoGen autoGen = (AutoGen)listAutoGen.get(i);
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("AST"))
		{
			s_gen_year_ast    = autoGen.getComp_id().getGenYear();
			s_next_number_ast = autoGen.getNextNumber().toString();
		}
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("DSB"))
		{
			s_gen_year_dsb    = autoGen.getComp_id().getGenYear();
			s_next_number_dsb = autoGen.getNextNumber().toString();
		}
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("PO"))
		{
			s_gen_year_po    = autoGen.getComp_id().getGenYear();
			s_next_number_po = autoGen.getNextNumber().toString();
		}
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("REQ"))
		{
			s_gen_year_req    = autoGen.getComp_id().getGenYear();
			s_next_number_req = autoGen.getNextNumber().toString();
		}
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("REQM"))
		{
			s_gen_year_reqm    = autoGen.getComp_id().getGenYear();
			s_next_number_reqm = autoGen.getNextNumber().toString();
		}
		if(autoGen.getComp_id().getDocumentType().equalsIgnoreCase("RFQ"))
		{
			s_gen_year_rfq    = autoGen.getComp_id().getGenYear();
			s_next_number_rfq = autoGen.getNextNumber().toString();
		}
	}

	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>System Setup Options</div>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=100% id="systemSetupFrame" height=350px>
		<div id="SystemProcessing" style="visibility: visible; position:absolute; left:25px; height:500px;">
			<table border=0 cellspacing=0 cellpadding=0 width=100%>
			<tr>
				<td width=100% align=center valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "loadingInformation", "Loading system setup information")%>... <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pleaseWait", "Please wait")%>.</b><br>
			  <br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
			</tr>
			</table>
		</div>
	</td>
	<td width=180px align=right valign=top>
		<hr size=0 width=170px>
		<table border=0 width=170px valign=top cellpadding=2 cellspacing=0 id=processSteps>
<%	int istep = 0;%>
		<!--tr height=25px>
			<td><a href="javascript: setSystemOptions('Languages'); void(0);"><img id="LanguagesProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg"  border=0></a></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Languages'); void(0);" id="LanguagesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "languages", "Languages")%></a>
			</td>
		</tr-->
<%
	if (HiltonUtility.isEmpty(defaultDisplayOption)) {
		defaultDisplayOption = "AutoNumber";
	}
	istep++;%>
		<tr height=25px>
			<td id="AutoNumberProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('AutoNumber');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('AutoNumber');  void(0);" id="AutoNumberProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autoNumber", "Auto Number")%></a>
			</td>
		</tr>
<%	if (role.getAccessRights("PXADMIN") == 99) {
		istep++;%>
		<tr height=25px>
			<td id="LoginOptionsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('LoginOptions');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('LoginOptions');  void(0);" id="LoginOptionsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "loginOptions", "Login Options")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="PasswordProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('Password');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Password');  void(0);" id="PasswordProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "password", "Password")%></a>
			</td>
		</tr>
<%	}
	if (reqsActive && posActive) {
		istep++;%>
		<tr height=25px>
			<td id="AssignmentProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('Assignment');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Assignment');  void(0);" id="AssignmentProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assignment", "Assignment")%></a>
			</td>
		</tr>
<%	}%>
<%	istep++;%>
		<tr height=25px>
			<td><div id="pxsmallbutton"><a href="javascript: setSystemOptions('AcctUdfs');  void(0);"><img id="AcctUdfsProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg"  border=0></a></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('AcctUdfs');  void(0);" id="AcctUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accountOptions", "Account Options")%></a>
			</td>
		</tr>
<% istep++; %>
		<tr height=25px>
			<td id="UserProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('User');  void(0);"><%=istep%></a></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('User');  void(0);" id="UserProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userOptions", "User Options")%></a>
			</td>
		</tr>
<%	if (reqsActive) {
		istep++;%>
		<tr height=25px>
			<td id="ReqUdfsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReqUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReqUdfs'); void(0);" id="ReqUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionOptions", "Requisition Options")%></a>
			</td>
		</tr>
<%	}
	if (rfqsActive) {
		istep++;%>
		<tr height=25px>
			<td id="RfqUdfsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('RfqUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('RfqUdfs'); void(0);" id="RfqUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationOptions", "Solicitation Options")%></a>
			</td>
		</tr>
<%	}
	if (posActive) {
		istep++;%>
		<tr height=25px>
			<td id="PoUdfsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PoUdfs'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PoUdfs'); void(0);" id="PoUdfsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purchaseOrderOptions", "Purchase Order Options")%></a>
			</td>
		</tr>
<%	}
	if (posActive) {
		istep++;%>
		<tr height=25px>
			<td id="PoTypesProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PoTypes');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PoTypes');  void(0);" id="PoTypesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purchaseOrderTypes", "Purchase Order Types")%></a>
			</td>
		</tr>
<%	}
	if (receivingActive) {
		istep++;%>
		<tr height=25px>
			<td id="ReceiptSetupProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('ReceiptSetup'); void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('ReceiptSetup'); void(0);" id="ReceiptSetupProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivingOptions", "Receiving Options")%></a>
			</td>
		</tr>
<%	}
	if (vouchersActive) {
		istep++;%>
		<tr height=25px>
			<td id="VoucherSetupProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('VoucherSetup');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('VoucherSetup');  void(0);" id="VoucherSetupProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceSetup", "Invoice Setup")%></a>
			</td>
		</tr>
<%	}
	if (extRfqsActive) {
		istep++;%>
		<tr height=25px>
			<td id="VendorRegistrationProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('VendorRegistration');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('VendorRegistration');  void(0);" id="VendorRegistrationProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorRegistration", "Vendor Registration")%></a>
			</td>
		</tr>
<%	}
	if (role.getAccessRights("PXADMIN") == 99) {
		istep++;%>
		<tr height=25px>
			<td id="ReportProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('Report');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Report');  void(0);" id="ReportProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reportOptions", "Report Options")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="BrowseOptionsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('BrowseOptions');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('BrowseOptions');  void(0);" id="BrowseOptionsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseOptions", "Browse Options")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="CatalogOptionsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('CatalogOptions');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('CatalogOptions');  void(0);" id="CatalogOptionsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogOptions", "Catalog Options")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="PSEmailPropertiesProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('PSEmailProperties');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('PSEmailProperties');  void(0);" id="PSEmailPropertiesProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "psEmailProperties", "PS Email Properties")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="AuditTrailProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('AuditTrail');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('AuditTrail');  void(0);" id="AuditTrailProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "auditTrail", "Audit Trail")%></a>
			</td>
		</tr>
<%		istep++;%>
		<tr height=25px>
			<td id="UploadItemsProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('UploadItems');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('UploadItems');  void(0);" id="UploadItemsProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Upload Item Options")%></a>
			</td>
		</tr>
<%	}
	istep++;%>
		<tr height=25px>
			<td id="LogoProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('Logo');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Logo');  void(0);" id="LogoProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Logo")%></a>
			</td>
		</tr>
<%	istep++;%>
		<tr height=25px>
			<td id="MiscProcessImg"><div id="pxsmallbutton"><a href="javascript: setSystemOptions('Misc');  void(0);"><%=istep%></a></div></td>
			<td nowrap>
				<a href="javascript: setSystemOptions('Misc');  void(0);" id="MiscProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "miscellaneous", "Miscellaneous")%></a>
			</td>
		</tr>
		</table>
		<hr size=0 width=170px>

		<table border=0 width=170px valign=top cellpadding=2 cellspacing=0 id=processSteps>
		<tr><td width=50% align=center><div id="pxbutton"><a href="javascript: save(); void(0);">Save</a></div></td></tr>
		</table>
	</td>
</tr>
</table>

<input type=hidden name="CurrCode_currencyCode" value="<%=propertiesManager.getProperty("MISC","BASECURRENCY","USD")%>">;
<input type=hidden name="CurrCode_decimalDigits" value="<%=propertiesManager.getProperty("MISC","PRICEDECIMALS","2")%>">;
<input type=hidden name="Property_section" value="MISC">
<input type=hidden name="Property_property" value="Languages">
<input type=hidden name="Property_value" value="">
<input type=hidden name="allowBrowse" value="true">
<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/system_setup.jsp", "DoNothing", "System Setup Options");

	var voucherSetupHTML = "";
	var acctUdfsHTML = "";
	var reqUdfsHTML = "";
	var rfqUdfsHTML = "";
	var poUdfsHTML = "";
	var poTypesHTML = "";
	var logoHTML = "";
	var miscHTML = "";
	var assignmentHTML = "";
	var languagesHTML = "";
	var autoNumberHTML = "";
	var loginOptionsHTML = "";
	var passwordHTML = "";
	var selectedType = "";
	var vendorRegistrationHTML = "";
	var reportHTML = "";
	var psEmailProperties = "";
	var auditTrailHTML = "";
	var browseHTML = "";
	var catalogHTML = "";
	var receiptSetupHTML = "";
	var uploadItemsHTML = "";
	var userHTML = "";

	function thisLoad() {
		populateHTMLVariables();
		populateHTML();
		hideArea("SystemProcessing");
		setSystemOptions("<%=defaultDisplayOption%>");
		
		f_StartIt();
	}

	function save() {
		frm.CurrCode_currencyCode.value = frm.currencyCode.value;
		
		doSubmit('admin/admin_menu.jsp', 'PropertyUpdate;CurrCodeUpdate;AutoGenUpdateByGenYear');
	}

	function decimalDigits(field){
		frm.CurrCode_decimalDigits.value = field.value;
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

		var myHtml = "<div id=" + '"' + "AcctUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + acctUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Assignment" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + assignmentHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "AutoNumber" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + autoNumberHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Languages" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + languagesHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "LoginOptions" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + loginOptionsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Logo" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + logoHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Misc" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + miscHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Password" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px; vAlign:top;" + '"' + ">";
		myHtml = myHtml + passwordHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "PoTypes" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + poTypesHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "PoUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + poUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "ReqUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + reqUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "RfqUdfs" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + rfqUdfsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "VendorRegistration" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + vendorRegistrationHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "VoucherSetup" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + voucherSetupHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "Report" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + reportHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "PSEmailProperties" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + psEmailProperties;;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "AuditTrail" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + auditTrailHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "BrowseOptions" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + browseOptionsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "CatalogOptions" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + catalogHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "ReceiptSetup" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:340px;" + '"' + ">";
		myHtml = myHtml + receiptSetupHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "UploadItems" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + uploadItemsHTML;
		myHtml = myHtml + "</div>";

		myHtml = myHtml + "<div id=" + '"' + "User" + '"' + " style=" + '"' + "visibility: hidden; position:absolute; left:25px; height:400px;" + '"' + ">";
		myHtml = myHtml + userHTML;
		myHtml = myHtml + "</div>";

		return myHtml;
	}

	function populateHTMLVariables() {
		acctUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Account Options</b></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right width=200px>Show&nbsp;</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "FORM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AccountElements" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "1" + '"' + "<% if (accountElements.equals("1")) {%> selected<%}%>>1</option>" +
				"<option value=" + '"' + "2" + '"' + "<% if (accountElements.equals("2")) {%> selected<%}%>>2</option>" +
				"<option value=" + '"' + "3" + '"' + "<% if (accountElements.equals("3")) {%> selected<%}%>>3</option>" +
				"<option value=" + '"' + "4" + '"' + "<% if (accountElements.equals("4")) {%> selected<%}%>>4</option>" +
				"<option value=" + '"' + "5" + '"' + "<% if (accountElements.equals("5")) {%> selected<%}%>>5</option>" +
				"<option value=" + '"' + "6" + '"' + "<% if (accountElements.equals("6")) {%> selected<%}%>>6</option>" +
				"<option value=" + '"' + "7" + '"' + "<% if (accountElements.equals("7")) {%> selected<%}%>>7</option>" +
				"<option value=" + '"' + "8" + '"' + "<% if (accountElements.equals("8")) {%> selected<%}%>>8</option>" +
				"<option value=" + '"' + "9" + '"' + "<% if (accountElements.equals("9")) {%> selected<%}%>>9</option>" +
				"<option value=" + '"' + "10" + '"' + "<% if (accountElements.equals("10")) {%> selected<%}%>>10</option>" +
				"<option value=" + '"' + "11" + '"' + "<% if (accountElements.equals("11")) {%> selected<%}%>>11</option>" +
				"<option value=" + '"' + "12" + '"' + "<% if (accountElements.equals("12")) {%> selected<%}%>>12</option>" +
				"<option value=" + '"' + "13" + '"' + "<% if (accountElements.equals("13")) {%> selected<%}%>>13</option>" +
				"<option value=" + '"' + "14" + '"' + "<% if (accountElements.equals("14")) {%> selected<%}%>>14</option>" +
				"<option value=" + '"' + "15" + '"' + "<% if (accountElements.equals("15")) {%> selected<%}%>>15</option>" +
				"</select>&nbsp;Account Fields</td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Show&nbsp;</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "FORM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AccountDescElements" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "0" + '"' + "<% if (accountDescElements.equals("1")) {%> selected<%}%>>0</option>" +
				"<option value=" + '"' + "1" + '"' + "<% if (accountDescElements.equals("1")) {%> selected<%}%>>1</option>" +
				"<option value=" + '"' + "2" + '"' + "<% if (accountDescElements.equals("2")) {%> selected<%}%>>2</option>" +
				"<option value=" + '"' + "3" + '"' + "<% if (accountDescElements.equals("3")) {%> selected<%}%>>3</option>" +
				"<option value=" + '"' + "4" + '"' + "<% if (accountDescElements.equals("4")) {%> selected<%}%>>4</option>" +
				"<option value=" + '"' + "5" + '"' + "<% if (accountDescElements.equals("5")) {%> selected<%}%>>5</option>" +
				"<option value=" + '"' + "6" + '"' + "<% if (accountDescElements.equals("6")) {%> selected<%}%>>6</option>" +
				"<option value=" + '"' + "7" + '"' + "<% if (accountDescElements.equals("7")) {%> selected<%}%>>7</option>" +
				"<option value=" + '"' + "8" + '"' + "<% if (accountDescElements.equals("8")) {%> selected<%}%>>8</option>" +
				"<option value=" + '"' + "9" + '"' + "<% if (accountDescElements.equals("9")) {%> selected<%}%>>9</option>" +
				"<option value=" + '"' + "10" + '"' + "<% if (accountDescElements.equals("10")) {%> selected<%}%>>10</option>" +
				"<option value=" + '"' + "11" + '"' + "<% if (accountDescElements.equals("11")) {%> selected<%}%>>11</option>" +
				"<option value=" + '"' + "12" + '"' + "<% if (accountDescElements.equals("12")) {%> selected<%}%>>12</option>" +
				"<option value=" + '"' + "13" + '"' + "<% if (accountDescElements.equals("13")) {%> selected<%}%>>13</option>" +
				"<option value=" + '"' + "14" + '"' + "<% if (accountDescElements.equals("14")) {%> selected<%}%>>14</option>" +
				"<option value=" + '"' + "15" + '"' + "<% if (accountDescElements.equals("15")) {%> selected<%}%>>15</option>" +
				"</select>&nbsp;Account Description Fields</td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Show last&nbsp;</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RecentAccountSize" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "1" + '"' + "<% if (recentAcctSize.equals("1")) {%> selected<%}%>>1</option>" +
				"<option value=" + '"' + "2" + '"' + "<% if (recentAcctSize.equals("2")) {%> selected<%}%>>2</option>" +
				"<option value=" + '"' + "3" + '"' + "<% if (recentAcctSize.equals("3")) {%> selected<%}%>>3</option>" +
				"<option value=" + '"' + "4" + '"' + "<% if (recentAcctSize.equals("4")) {%> selected<%}%>>4</option>" +
				"<option value=" + '"' + "5" + '"' + "<% if (recentAcctSize.equals("5")) {%> selected<%}%>>5</option>" +
				"<option value=" + '"' + "6" + '"' + "<% if (recentAcctSize.equals("6")) {%> selected<%}%>>6</option>" +
				"<option value=" + '"' + "7" + '"' + "<% if (recentAcctSize.equals("7")) {%> selected<%}%>>7</option>" +
				"<option value=" + '"' + "8" + '"' + "<% if (recentAcctSize.equals("8")) {%> selected<%}%>>8</option>" +
				"<option value=" + '"' + "9" + '"' + "<% if (recentAcctSize.equals("9")) {%> selected<%}%>>9</option>" +
				"<option value=" + '"' + "10" + '"' + "<% if (recentAcctSize.equals("10")) {%> selected<%}%>>10</option>" +
				"<option value=" + '"' + "11" + '"' + "<% if (recentAcctSize.equals("11")) {%> selected<%}%>>11</option>" +
				"<option value=" + '"' + "12" + '"' + "<% if (recentAcctSize.equals("12")) {%> selected<%}%>>12</option>" +
				"<option value=" + '"' + "13" + '"' + "<% if (recentAcctSize.equals("13")) {%> selected<%}%>>13</option>" +
				"<option value=" + '"' + "14" + '"' + "<% if (recentAcctSize.equals("14")) {%> selected<%}%>>14</option>" +
				"<option value=" + '"' + "15" + '"' + "<% if (recentAcctSize.equals("15")) {%> selected<%}%>>15</option>" +
				"</select>&nbsp;Recent Accounts</td></tr>" +
			"<tr><td colspan=2><br></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Delete Zero Dollar Accounts:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DELETE ZERO DOLLAR ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACCOUNTS","DELETE ZERO DOLLAR ACCOUNTS","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACCOUNTS","DELETE ZERO DOLLAR ACCOUNTS","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACCOUNTS', 'DELETE ZERO DOLLAR ACCOUNTS');" + '"' + "></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Account allocation separator string:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AccountSeparator" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","AccountSeparator","-")%>" + '"' + " size=5></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Default Accounts By Commodity:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEFAULTBYCOMMODITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACCOUNTS","DEFAULTBYCOMMODITY","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACCOUNTS","DEFAULTBYCOMMODITY","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACCOUNTS', 'DEFAULTBYCOMMODITY');" + '"' + "></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Accounts Browse By XREF_COMBO:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ACCOUNT_BROWSE_XREF_COMBO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACCOUNTS","ACCOUNT_BROWSE_XREF_COMBO","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACCOUNTS","ACCOUNT_BROWSE_XREF_COMBO","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACCOUNTS', 'ACCOUNT_BROWSE_XREF_COMBO');" + '"' + "></td></tr>" +
			"<tr><td>&nbsp;</td><td align=right>Auto Save Accounts:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTO SAVE ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACCOUNTS","AUTO SAVE ACCOUNTS","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACCOUNTS","AUTO SAVE ACCOUNTS","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACCOUNTS', 'AUTO SAVE ACCOUNTS');" + '"' + "></td></tr>" +
			"</table>";
		userHTML = "<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>User Options</b></td></tr>" +
			"<tr><td align=right width=300px nowrap>Turn off Signature Password Fields:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "USER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDESIGFIELDS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("USER", "HIDESIGFIELDS", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("USER", "HIDESIGFIELDS", "N").equalsIgnoreCase("Y")) {%> checked <%}%> onchange=" + '"' + "setCkBoxValue(this, 'USER', 'HIDESIGFIELDS');" + '"' + "></td></tr>" +
			"</table>";
		assignmentHTML = "<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Buyer Assignment / Workload</b></td></tr>" +
			"<tr><td align=right width=300px nowrap>Create orders from assigned requisitions only:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AssignedOnly" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","AssignedOnly","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","AssignedOnly","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'AssignedOnly');" + '"' + "></td>" +
			"<tr><td align=right width=300px nowrap>Assign requisitions by line item:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AssignByLine" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","AssignByLine","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","AssignByLine","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'AssignByLine');" + '"' + "></td>" +
			"<tr><td colspan=2>&nbsp;</td></tr>" +
<%	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td colspan=2 height=30px nowrap>&nbsp;&nbsp;<b>Automatic Purchase Request Assignment</b></td></tr>" +
			"<tr><td align=right width=300px nowrap>Buyer Assigment Process:</td>" +
			"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
			"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BUYERASSIGNMENTPROCESS" + '"' + ">" +
			"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","BUYERASSIGNMENTPROCESS","N")%>" + '"' + ">" +
			"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","BUYERASSIGNMENTPROCESS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'BUYERASSIGNMENTPROCESS');" + '"' + "></td></tr>" +
			"<tr><td align=right width=300px nowrap><hr></td></tr>" +
			"<tr><td align=right width=300px nowrap>Auto Assign Buyer by Commodity Code:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AssignCommodity" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","AssignCommodity","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","AssignCommodity","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'AssignCommodity');" + '"' + "></td>" +
			"<tr><td align=right width=300px nowrap>Auto Assign Buyer by Suggested Buyer:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AssignSuggested" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","AssignSuggested","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","AssignSuggested","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'AssignSuggested');" + '"' + "></td></tr>" +
			"<tr><td align=right width=300px nowrap>Auto Assign Buyer by Vendor Buyer Rel:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AssignByVendorBuyerRel" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","AssignByVendorBuyerRel","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","AssignByVendorBuyerRel","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'AssignByVendorBuyerRel');" + '"' + "></td></tr>" +
			"<tr><td colspan=2>&nbsp;</td></tr>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Requisition Buyer Assignment</b></td></tr>" +
			"<tr><td align=right width=300px nowrap>Requisition Buyer Assignment:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ReqBuyerWarrantTotal" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","ReqBuyerWarrantTotal","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","ReqBuyerWarrantTotal","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'ReqBuyerWarrantTotal');" + '"' + "></td></tr>" +
			"<tr><td align=right width=300px nowrap>Batch Print Requisition Assignment:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BatchPrint" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","BatchPrint","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ASSIGNMENT","BatchPrint","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ASSIGNMENT', 'BatchPrint');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>PO Buyer Assignment</b></td></tr>" +
			"<tr><td align=right width=300px nowrap>PO Buyer Assignment:</td>" +
				"<td>" +
					"<table border=0 cellpadding=1 cellspacing=0>" +
					"<tr><td align=right><input type=radio name=" + '"' + "ASSIGNMENT_EDIT_VALUE" + '"' + " value=" + '"' + "DEFAULT" + '"' + "<% if (propertiesManager.getProperty("ASSIGNMENT","ASSIGNMENTOPTIONS","DEFAULT").equalsIgnoreCase("DEFAULT")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('ASSIGNMENT_EDIT_VALUE', 'ASSIGNMENT', 'ASSIGNMENTOPTIONS');" + '"' + "></td>" +
						"<td>Assign from Requisition Buyer</td></tr>" +
					"<tr><td align=right><input type=radio name=" + '"' + "ASSIGNMENT_EDIT_VALUE" + '"' + " value=" + '"' + "REQUISITIONERISBUYER" + '"' + "<% if (propertiesManager.getProperty("ASSIGNMENT","ASSIGNMENTOPTIONS","DEFAULT").equalsIgnoreCase("REQUISITIONERISBUYER")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('ASSIGNMENT_EDIT_VALUE', 'ASSIGNMENT', 'ASSIGNMENTOPTIONS');" + '"' + "></td>" +
						"<td>Assign from Requisition Requisitioner</td>" +
					"<tr><td align=right><input type=radio name=" + '"' + "ASSIGNMENT_EDIT_VALUE" + '"' + " value=" + '"' + "ASSIGNEDISBUYER" + '"' + "<% if (propertiesManager.getProperty("ASSIGNMENT","ASSIGNMENTOPTIONS","DEFAULT").equalsIgnoreCase("ASSIGNEDISBUYER")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('ASSIGNMENT_EDIT_VALUE', 'ASSIGNMENT', 'ASSIGNMENTOPTIONS');" + '"' + "></td>" +
						"<td>Assign from Requisition Assigned Buyer</td>" +
					"</table>" +
					"<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ASSIGNMENT" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ASSIGNMENTOPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ASSIGNMENT","ASSIGNMENTOPTIONS","DEFAULT")%>" + '"' + "></td></tr>" +
<%	}%>
			"</table>";
		autoNumberHTML = "<table border=0 cellspacing=0 cellpadding=2>" +
			"<tr><td colspan=6 height=30px>&nbsp;&nbsp;<b>Auto Numbering</b></td></tr>" +
			"<tr><td colspan=6><br></td></tr>" +
			"<tr><td colspan=6 height=30px>&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autoNumberDirections", "Please use Y to designate Year and N to designate the counter for the number format.")%></td></tr>" +
			"<tr><td width=5%>&nbsp;</td><td colspan=2 nowrap align=right><b>Enable</b></td><td align=left nowrap><b>Number Format</b></td><td align=left nowrap><b>Current Number</b></td><td align=left nowrap><b>Optional</b></td></tr>" +
<%	if (posActive) {%>
				"<tr><td width=10px>&nbsp;</td><td width=175px nowrap align=right>Purchase Orders:</td>" +
				"<td width=20px><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOPO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOPO","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOPO","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOPO');" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PoFormat" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=poFormat%>" + '"' + " size=15 onchange=" + '"' + "upperCase(this);" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "AutoGen_genYearPo" + '"' + " value=" + '"' + "<%=s_gen_year_po%>" + '"' + ">" +
				"<input type=text name=" + '"' + "AutoGen_nextNumberPo" + '"' + " value=" + '"' + "<%=s_next_number_po%>" + '"' + " size=15 maxlength=10></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWAUTOPO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","SHOWAUTOPO","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","SHOWAUTOPO","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'SHOWAUTOPO');" + '"' + "></td></tr>" +
			"<tr><td>&nbsp;</td><td nowrap align=right>Revisions:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOREV" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOREV","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOREV","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOREV');" + '"' + "></td>" +
				"<td>&nbsp;</td></tr>" +
<%	}
	if (reqsActive) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Requisitions:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOREQ" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOREQ","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOREQ","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOREQ');" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ReqFormat" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reqFormat%>" + '"' + " size=15 onchange=" + '"' + "upperCase(this);" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "AutoGen_genYearReq" + '"' + " value=" + '"' + "<%=s_gen_year_req%>" + '"' + ">" +
				"<input type=text name=" + '"' + "AutoGen_nextNumberReq" + '"' + " value=" + '"' + "<%=s_next_number_req%>" + '"' + " size=15 maxlength=10></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWAUTOREQ" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","SHOWAUTOREQ","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","SHOWAUTOREQ","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'SHOWAUTOREQ');" + '"' + "></td></tr>" +
<%	}
	if (msrReqAccess > 0) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Requisitions MSR:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOREQM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOREQM","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOREQM","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOREQM');" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REQMFORMAT" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reqMFormat%>" + '"' + " size=15 onchange=" + '"' + "upperCase(this);" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "AutoGen_genYearReqM" + '"' + " value=" + '"' + "<%=s_gen_year_reqm%>" + '"' + ">" +
				"<input type=text name=" + '"' + "AutoGen_nextNumberReqM" + '"' + " value=" + '"' + "<%=s_next_number_reqm%>" + '"' + " size=15 maxlength=10></td>" +
<%	}
	if (rfqsActive) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Solicitations:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTORFQ" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTORFQ","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTORFQ","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTORFQ');" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RfqFormat" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=rfqFormat%>" + '"' + " size=15 onchange=" + '"' + "upperCase(this);" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "AutoGen_genYearRfq" + '"' + " value=" + '"' + "<%=s_gen_year_rfq%>" + '"' + ">" +
				"<input type=text name=" + '"' + "AutoGen_nextNumberRfq" + '"' + " value=" + '"' + "<%=s_next_number_rfq%>" + '"' + " size=15 maxlength=10></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWAUTORFQ" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","SHOWAUTORFQ","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","SHOWAUTORFQ","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'SHOWAUTORFQ');" + '"' + "></td></tr>" +
<%	}
	if (stdInvActive || extInvActive) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Disbursements:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTODISBURS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTODISBURS","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTODISBURS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTODISBURS');" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DsbFormat" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=dsbFormat%>" + '"' + " size=15 onchange=" + '"' + "upperCase(this);" + '"' + "></td>" +
				"<td><input type=hidden name=" + '"' + "AutoGen_genYearDsb" + '"' + " value=" + '"' + "<%=s_gen_year_dsb%>" + '"' + ">" +
				"<input type=text name=" + '"' + "AutoGen_nextNumberDsb" + '"' + " value=" + '"' + "<%=s_next_number_dsb%>" + '"' + " size=15 maxlength=10></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWAUTODISB" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","SHOWAUTODISB","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","SHOWAUTODISB","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'SHOWAUTODISB');" + '"' + "></td></tr>" +
<%	}
	if (vouchersActive) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Voucher Control:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOVOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOVOUCHER","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOVOUCHER","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOVOUCHER');" + '"' + "></td>" +
				"<td colspan=2>&nbsp;</td></tr>" +
<%	}
	if (assetsActive) {%>
			"<tr><td width=5%>&nbsp;</td><td nowrap align=right>Asset:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOASSET" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","AUTOASSET","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUTONUMBER","AUTOASSET","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUTONUMBER', 'AUTOASSET');" + '"' + "></td>" +
				"<td colspan=2>&nbsp;</td></tr>" +
<%	}%>
			"<tr><td colspan=6><hr size=0 width=100%></td></tr>" +
			"<tr><td width=5%>&nbsp;</td><td colspan=2 align=right>Fiscal Year Beginning Month:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "FyBegin" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "1" + '"' + " <% if (fyBegin.equals("1")) {%> selected<%}%>>January</option>" +
				"<option value=" + '"' + "2" + '"' + " <% if (fyBegin.equals("2")) {%> selected<%}%>>February</option>" +
				"<option value=" + '"' + "3" + '"' + " <% if (fyBegin.equals("3")) {%> selected<%}%>>March</option>" +
				"<option value=" + '"' + "4" + '"' + " <% if (fyBegin.equals("4")) {%> selected<%}%>>April</option>" +
				"<option value=" + '"' + "5" + '"' + " <% if (fyBegin.equals("5")) {%> selected<%}%>>May</option>" +
				"<option value=" + '"' + "6" + '"' + " <% if (fyBegin.equals("6")) {%> selected<%}%>>June</option>" +
				"<option value=" + '"' + "7" + '"' + " <% if (fyBegin.equals("7")) {%> selected<%}%>>July</option>" +
				"<option value=" + '"' + "8" + '"' + " <% if (fyBegin.equals("8")) {%> selected<%}%>>August</option>" +
				"<option value=" + '"' + "9" + '"' + " <% if (fyBegin.equals("9")) {%> selected<%}%>>September</option>" +
				"<option value=" + '"' + "10" + '"' + " <% if (fyBegin.equals("10")) {%> selected<%}%>>October</option>" +
				"<option value=" + '"' + "11" + '"' + " <% if (fyBegin.equals("11")) {%> selected<%}%>>November</option>" +
				"<option value=" + '"' + "12" + '"' + " <% if (fyBegin.equals("12")) {%> selected<%}%>>December</option>" +
				"</select></td></tr>" +
			"<tr><td width=5%>&nbsp;</td><td colspan=2 align=right>Document Number Separator String:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DocSep" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","DocSep","")%>" + '"' + "size=5></td></tr>" +
            <%if(oid.equalsIgnoreCase("vse06p")) {%>
			"</table>"+
			"<table border=0 cellpadding=1 cellspacing=0>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Auto Number</b></td></tr>" +
			"<tr><td nowrap>Account Start At:</td>" +
				"<td colspan=1><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "STARTAT" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUTONUMBER","STARTAT","2")%>"+ '"' +"></td>" +
			"<td colspan=3>Account Length:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUTONUMBER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LENGTH" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","LENGTH","1")%>" + '"' + "></td>" +
			<%}%>
			"</table>";
		    languagesHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Languages</b></td></tr>" +
			"<tr><td width=50% align=center><table border=0 cellspacing=0 cellpadding=2 width=100%>" +
<% for (int i=0; i < 4; i++) {%>
			"<tr><td align=right><%=i + 1%>.</td>" +
				"<td><input type=text name=" + '"' + "as_language" + '"' + " value=" + '"' + "<%=languages[i]%>" + '"' + " size=35></td></tr>" +
<% }%>			"</table></td>" +
			"<td width=50% align=center><table border=0 cellspacing=0 cellpadding=2 width=100%>" +
<% for (int i=4; i < 8; i++) {%>
			"<tr><td align=right><%=i + 1%>.</td><td><input type=text name=" + '"' + "as_language" + '"' + " value=" + '"' + "<%=languages[i]%>" + '"' + " size=35></td></tr>" +
<% }%>			"</table></td>" +
			"</tr></table>";
		loginOptionsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td height=30px>&nbsp;&nbsp;<b>Login Options</b></td></tr>" +
			"<tr><td><table border=0 cellpadding=2 cellspacing=0>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Maximum Login Attempts:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MAXATTEMPTS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","MAXATTEMPTS","0")%>" + '"' + " size=15>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Idle Minutes before User Session will Expire:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "IdleMinutes" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("APPLICATION","IdleMinutes","30")%>" + '"' + ">" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Enable automatic registration at login:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "SQLCA" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REGISTRAR" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("SQLCA","REGISTRAR","1")%>" + '"' + ">" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Valid domains for registering email addreses:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VALIDDOMAIN" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","VALIDDOMAIN","")%>" + '"' + " size=50 onchange=" + '"' + "lowerCase(this);" + '"' + ">" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Invalid domains for registering email addreses:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "INVALIDDOMAIN" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","INVALIDDOMAIN","")%>" + '"' + " size=50 onchange=" + '"' + "lowerCase(this);" + '"' + ">" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Application URL:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "URL" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("APPLICATION","URL","https://my.puridiom.com")%>" + '"' + " size=50>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Bid Board URL:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BB-APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "URL" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BB-APPLICATION","URL","")%>" + '"' + " size=50>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Vendor Registration URL:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "URL" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION","URL","")%>" + '"' + " size=50>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Help Desk Email Address:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HelpDeskEmail" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("APPLICATION","HelpDeskEmail","support@puridiom.com")%>" + '"' + " size=50>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Require security question to reset password:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SECURITYQUESTION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","SECURITYQUESTION","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("LOGIN","SECURITYQUESTION","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'LOGIN', 'SECURITYQUESTION');" + '"' + "></td>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Automatically Generate User Id:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "USER" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AUTOGENERATEID" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("USER","AUTOGENERATEID","N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("USER","AUTOGENERATEID","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'LOGIN', 'AUTOGENERATEID');" + '"' + "></td>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Show self register button:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWSELFREGISTER" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","SHOWSELFREGISTER","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("LOGIN","SHOWSELFREGISTER","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'LOGIN', 'SHOWSELFREGISTER');" + '"' + "></td>" +
			"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Show forgot password link:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOWFORGOTPASSWORDLINK" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","SHOWFORGOTPASSWORDLINK","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("LOGIN","SHOWFORGOTPASSWORDLINK","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'LOGIN', 'SHOWFORGOTPASSWORDLINK');" + '"' + "></td>" +
			"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Remember me:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "LOGIN" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REMEMBERME" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("LOGIN","REMEMBERME","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("LOGIN","REMEMBERME","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'LOGIN', 'REMEMBERME');" + '"' + "></td>" +
			"</td></tr>" +
			"</table></td></tr>" +
		"</table>" +
		"<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td height=30px>&nbsp;&nbsp;<b>Application Environment Options</b></td></tr>" +
			"<tr><td><table border=0 cellpadding=2 cellspacing=0>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Display Application Environment Label:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISPLAYENV" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("APPLICATION", "DISPLAYENV", "N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("APPLICATION","DISPLAYENV","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'APPLICATION', 'DISPLAYENV');" + '"' + "></td>" +
				"</td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Choose the Application Environment:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "APPLICATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENVIRONMENT" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("APPLICATION", "ENVIRONMENT", "TEST")%>" + '"' + " size=50 onchange=" + '"' + "upperCase(this);" + '"' + ">" +
			"</table></td></tr>" +
		"</table>";
		logoHTML = "<table border=0 cellspacing=0 cellpadding=2>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Logo Settings</b></td></tr>" +
<%	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td nowrap align=right width=20%>Enable multiple form logos:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MULTIPLELOGO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","MULTIPLELOGO","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC", "MULTIPLELOGO", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'MULTIPLELOGO');" + '"' + "></td>" +
				"<td nowrap align=right width=20%>Trigger On:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UDFLOGO" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("")) {%> selected<%}%>>[None Selected]</option>" +
				"<option value=" + '"' + "UDF1" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF1")){%> selected<%}%>>UDF 1 Field</option>" +
				"<option value=" + '"' + "UDF2" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF2")){%> selected<%}%>>UDF 2 Field</option>" +
				"<option value=" + '"' + "UDF3" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF3")){%> selected<%}%>>UDF 3 Field</option>" +
				"<option value=" + '"' + "UDF4" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF4")){%> selected<%}%>>UDF 4 Field</option>" +
				"<option value=" + '"' + "UDF5" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF5")){%> selected<%}%>>UDF 5 Field</option>" +
				"<option value=" + '"' + "UDF6" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF6")){%> selected<%}%>>UDF 6 Field</option>" +
				"<option value=" + '"' + "UDF7" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF7")){%> selected<%}%>>UDF 7 Field</option>" +
				"<option value=" + '"' + "UDF8" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF8")){%> selected<%}%>>UDF 8 Field</option>" +
				"<option value=" + '"' + "UDF9" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF9")){%> selected<%}%>>UDF 9 Field</option>" +
				"<option value=" + '"' + "UDF10" + '"' + "<% if (udfLogoDefault.equalsIgnoreCase("UDF10")){%> selected<%}%>>UDF 10 Field</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>Logo File Name:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOGOFILENAME" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","LOGOFILENAME","logo.gif")%>" + '"' + " size=50></td></tr>" +
			"<tr><td nowrap align=right width=50%>Use Color Scheme Logos:</td>" +
				"<td colspan=3 width=50%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "COLORSCHEMELOGO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","COLORSCHEMELOGO","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","COLORSCHEMELOGO","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'COLORSCHEMELOGO');" + '"' + "></td></tr>" +
			"<tr><td nowrap align=right width=50%>Color Scheme Default:</td>" +
				"<td colspan=3 width=50%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "COLORSCHEMEDEFAULT" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "" + '"' + "<% if (colorSchemeDefault.equals("")) {%> selected<%}%>>[None Selected]</option>" +
				"<option value=" + '"' + "green" + '"' + "<% if (colorSchemeDefault.equals("green")) {%> selected<%}%>>Green</option>" +
				"<option value=" + '"' + "orange" + '"' + "<% if (colorSchemeDefault.equals("orange")) {%> selected<%}%>>Orange</option>" +
				"<option value=" + '"' + "purple" + '"' + "<% if (colorSchemeDefault.equals("purple")) {%> selected<%}%>>Purple</option>" +
				"<option value=" + '"' + "red" + '"' + "<% if (colorSchemeDefault.equals("red")) {%> selected<%}%>>Red</option>" +
				"<option value=" + '"' + "blue" + '"' + "<% if (colorSchemeDefault.equals("blue")) {%> selected<%}%>>Blue</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>URL for Logo:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LOGOURL" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","LOGOURL","")%>" + '"' + " size=50></td></tr>" +
<%	}%>
			"</table><br>";
		miscHTML = "<table border=0 cellspacing=0 cellpadding=2 width=100%>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Miscellaneous Settings</b></td></tr>" +
<%	if (reqsActive) {%>
			"<tr><td align=right>Show only requisitions for owner:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "OwnerOnly" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "Y" + '"' + " <% if (ownerOnly.equals("Y")) {%> selected<%}%>>Yes</option>" +
				"<option value=" + '"' + "N" + '"' + " <% if (ownerOnly.equals("N")) {%> selected<%}%>>No</option>" +
				"<option value=" + '"' + "A" + '"' + " <% if (ownerOnly.equals("A")) {%> selected<%}%>>Access Controlled</option>" +
				"</select></td></tr>" +
<%	}
	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td align=right>Default Vendor Status:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "vendstat" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "A" + '"' + "<% if (propertiesManager.getProperty("MISC","vendstat","A").equalsIgnoreCase("A")) {%> selected<%}%>>Active</option>" +
				"<option value=" + '"' + "I" + '"' + "<% if (propertiesManager.getProperty("MISC","vendstat","I").equalsIgnoreCase("I")) {%> selected<%}%>>Inactive</option>" +
				"<option value=" + '"' + "R" + '"' + "<% if (propertiesManager.getProperty("MISC","vendstat","R").equalsIgnoreCase("R")) {%> selected<%}%>>RFQ Only</option>" +
				"<option value=" + '"' + "T" + '"' + "<% if (propertiesManager.getProperty("MISC","vendstat","T").equalsIgnoreCase("T")) {%> selected<%}%>>Temporary</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>Default Receipt Option:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "recrequired" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "R" + '"' + "<% if (propertiesManager.getProperty("MISC","recrequired","R").equalsIgnoreCase("R")){%> selected<%}%>>Receipt Required</option>" +
				"<option value=" + '"' + "P" + '"' + "<% if (propertiesManager.getProperty("MISC","recrequired","R").equalsIgnoreCase("P")){%> selected<%}%>>Previously Received</option>" +
				"<option value=" + '"' + "N" + '"' + "<% if (propertiesManager.getProperty("MISC","recrequired","R").equalsIgnoreCase("N")){%> selected<%}%>>RFQ Only</option>" +
				"<option value=" + '"' + "E" + '"' + "<% if (propertiesManager.getProperty("MISC","recrequired","R").equalsIgnoreCase("E")){%> selected<%}%>>End User Receipt</option>" +
				"</select></td></tr>" +
			"<tr><td align=right><a href=\"javascript: browseLookup('commodityCode', 'commodity'); void(0);\">Punchout Commodity Override</a>:</td>" +
			"<td><input type=hidden name=" + '"' + "commodityCode" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PUNCHOUT","COMMODITYOVERRIDE","")%>" + '"' + " onchange=" + '"' + "setPropertyValue('PUNCHOUT', 'COMMODITYOVERRIDE', this.value);" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PUNCHOUT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "COMMODITYOVERRIDE" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PUNCHOUT","COMMODITYOVERRIDE","")%>" + '"' + "></td></tr>" +
<%	}%>
			"<tr><td align=right><a href=\"javascript: browseLookup('currencyCode', 'curr_code'); void(0);\">Base Currency</a>:</td>" +
			"<td><input type=hidden name=" + '"' + "currencyCode" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","BASECURRENCY","USD")%>" + '"' + " onchange=" + '"' + "setPropertyValue('MISC', 'BASECURRENCY', this.value);" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BASECURRENCY" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","BASECURRENCY","USD")%>" + '"' + " DISABLED></td></tr>" +
			"<tr><td align=right>Date Format:</td>" +
			"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DateFormat" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "MM-dd-yyyy" + '"' + " <% if (dateFormat.equals("MM-dd-yyyy")) {%> selected<%}%>>MM-dd-yyyy</option>" +
				"<option value=" + '"' + "MM.dd.yyyy" + '"' + " <% if (dateFormat.equals("MM.dd.yyyy")) {%> selected<%}%>>MM.dd.yyyy</option>" +
				"<option value=" + '"' + "dd-MM-yyyy" + '"' + " <% if (dateFormat.equals("dd-MM-yyyy")) {%> selected<%}%>>dd-MM-yyyy</option>" +
				"<option value=" + '"' + "dd.MM.yyyy" + '"' + " <% if (dateFormat.equals("dd.MM.yyyy")) {%> selected<%}%>>dd.MM.yyyy</option>" +
				"<option value=" + '"' + "yyyy-MM-dd" + '"' + " <% if (dateFormat.equals("yyyy-MM-dd")) {%> selected<%}%>>yyyy-MM-dd</option>" +
				"<option value=" + '"' + "yyyy.MM.dd" + '"' + " <% if (dateFormat.equals("yyyy.MM.dd")) {%> selected<%}%>>yyyy.MM.dd</option>" +
				"<option value=" + '"' + "yyyy-dd-MM" + '"' + " <% if (dateFormat.equals("yyyy-dd-MM")) {%> selected<%}%>>yyyy-dd-MM</option>" +
				"<option value=" + '"' + "yyyy.dd.MM" + '"' + " <% if (dateFormat.equals("yyyy.dd.MM")) {%> selected<%}%>>yyyy.dd.MM</option>" +
				"</select></td></tr>" +
<%	if (propertiesManager.isModuleActive("EXTENDED RFQS") || propertiesManager.isModuleActive("REQUEST FOR QUOTES")) {%>
			"<tr><td align=right>Bids Required:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BidsRequired" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("RFQ OPTIONS","BidsRequired","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("RFQ OPTIONS","BidsRequired","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'RFQ OPTIONS', 'BidsRequired');" + '"' + "></td>" +
			"<tr><td align=right>Bids Required Notification Message:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "bidmsg" + '"' + ">" +
				"<select name=" + '"' + "Property_value" + '"' + ">" +
				"<option value=" + '"' + "W" + '"' + "<% if (propertiesManager.getProperty("RFQ OPTIONS","bidmsg","W").equalsIgnoreCase("W")) {%> selected<%}%>>Warning</option>" +
				"<option value=" + '"' + "F" + '"' + "<% if (propertiesManager.getProperty("RFQ OPTIONS","bidmsg","W").equalsIgnoreCase("F")) {%> selected<%}%>>Fatal</option>" +
				"</select></td></tr>" +
			"<tr><td align=right>Bid Waiver Threshold Amount:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "bidthreshold" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("RFQ OPTIONS","bidthreshold","0")%>" + '"' + "></td></tr>" +
			"<tr><td align=right>Notify End Users After:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "notifyafter" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","notifyafter","0")%>" + '"' + "> Days</td></tr>" +
<%	}
	if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>

			"<tr><td nowrap align=right width=50%>Update Inventory Unit Price when item is Received:</td>" +
				"<td width=50%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UpdatePrice" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","UpdatePrices","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","UpdatePrices","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'UpdatePrice');" + '"' + "></td></tr>" +
			"<tr><td nowrap align=right width=50%>Active Dual U/M & Quantity:</td>" +
				"<td width=50%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "INVENTORY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DualUOMRequired" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("INVENTORY","DualUOMRequired","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("INVENTORY","DualUOMRequired","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'INVENTORY', 'DualUOMRequired');" + '"' + "></td></tr>" +
			"<tr><td align=right>Use Average Price when Requesting Inv. Items:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UseAverage" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","UseAverage","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","UseAverage","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'UseAverage');" + '"' + "></td></tr>" +
/*			"<tr><td align=right>Update Inventory Issue Price when item is Received:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UpdateIssuePrice" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","UpdateIssuePrice","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","UpdateIssuePrice","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'UpdateIssuePrice');" + '"' + "></td></tr>" +
*/			"<tr><td align=right>Use Factor:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "IssuePriceFactor" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","IssuePriceFactor","1")%>" + '"' + " size=10></td></tr>" +
			"<tr><td align=right>Inventory item usage recalculation threshold (in months): </td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "Inventory Usage" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "recalcthreshold" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("Inventory Usage","recalcthreshold","6")%>" + '"' + "></td></tr>" +

<%	}
	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td colspan=2 align=center>" +
				"<table border=0 cellpadding=1 cellspacing=0>" +
				"<tr><td align=right><input type=radio name=" + '"' + "MISC_COMMODITYTYPE_VALUE" + '"' + " value=" + '"' + "NIGP" + '"' + "<% if (propertiesManager.getProperty("MISC","COMMODITYTYPE","").equalsIgnoreCase("NIGP")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_COMMODITYTYPE_VALUE', 'MISC', 'COMMODITYTYPE');" + '"' + "></td>" +
					"<td>5 Digit NIGP Commodities&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><input type=radio name=" + '"' + "MISC_COMMODITYTYPE_VALUE" + '"' + " value=" + '"' + "NIGP-6" + '"' + "<% if (propertiesManager.getProperty("MISC","COMMODITYTYPE","").equalsIgnoreCase("NIGP-6")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_COMMODITYTYPE_VALUE', 'MISC', 'COMMODITYTYPE');" + '"' + "></td>" +
					"<td>6 Digit NIGP Commodities&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><input type=radio name=" + '"' + "MISC_COMMODITYTYPE_VALUE" + '"' + " value=" + '"' + "NIGP-11" + '"' + "<% if (propertiesManager.getProperty("MISC","COMMODITYTYPE","").equalsIgnoreCase("NIGP-11")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_COMMODITYTYPE_VALUE', 'MISC', 'COMMODITYTYPE');" + '"' + "></td>" +
					"<td>11 Digit NIGP Commodities&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><input type=radio name=" + '"' + "MISC_COMMODITYTYPE_VALUE" + '"' + " value=" + '"' + "UNSPSC" + '"' + "<% if (propertiesManager.getProperty("MISC","COMMODITYTYPE","").equalsIgnoreCase("UNSPSC")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_COMMODITYTYPE_VALUE', 'MISC', 'COMMODITYTYPE');" + '"' + "></td>" +
					"<td>UNSPSC Commodities&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><input type=radio name=" + '"' + "MISC_COMMODITYTYPE_VALUE" + '"' + " value=" + '"' + '"' + "<% if (HiltonUtility.isEmpty(propertiesManager.getProperty("MISC","COMMODITYTYPE",""))) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_COMMODITYTYPE_VALUE', 'MISC', 'COMMODITYTYPE');" + '"' + "></td>" +
					"<td>Other&nbsp;&nbsp;&nbsp;</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "COMMODITYTYPE" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","COMMODITYTYPE","")%>" + '"' + "></td>" +
				"</tr>" +
				"</table>" +
				"<table border=0 cellpadding=1 cellspacing=0>" +
				"<tr><td align=right><input type=radio name=" + '"' + "MISC_USESUBCOMMODITY_VALUE" + '"' + " value=" + '"' + "N" + '"' + "<% if (propertiesManager.getProperty("MISC","USE SUBCOMMODITY","N").equalsIgnoreCase("N")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_USESUBCOMMODITY_VALUE', 'MISC', 'USE SUBCOMMODITY');" + '"' + "></td>" +
					"<td>Use Commodities in Items&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><input type=radio name=" + '"' + "MISC_USESUBCOMMODITY_VALUE" + '"' + " value=" + '"' + "Y" + '"' + "<% if (propertiesManager.getProperty("MISC","USE SUBCOMMODITY","N").equalsIgnoreCase("Y")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_USESUBCOMMODITY_VALUE', 'MISC', 'USE SUBCOMMODITY');" + '"' + "></td>" +
					"<td>Use Sub Commodities in Items&nbsp;&nbsp;&nbsp;</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "USE SUBCOMMODITY" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","USE SUBCOMMODITY","N")%>" + '"' + "></td>" +
				"</tr>" +
				"</table>" +
			"</td></tr>" +
			"<tr><td nowrap align=right width=50%>Enable Insurance Category Level:</td>" +
				"<td width=50%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "INSCATLVLACTIVE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR","INSCATLVLACTIVE","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR","INSCATLVLACTIVE","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR', 'INSCATLVLACTIVE');" + '"' + "></td></tr>" +
<%	}
	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td align=right>Admin Email Address:</td>" +
			"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MAILEVENTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AdminEmailAddr" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MAILEVENTS","AdminEmailAddr","support@puridiom.com")%>" + '"' + " size=50></td></tr>" +
			"<tr><td align=right>Vendor Admin Email Address:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MAILEVENTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VENDORADMINEMAIL" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MAILEVENTS","VENDORADMINEMAIL","")%>" + '"' + " size=50></td></tr>" +
			"<tr><td align=right>URL for Terms and Conditions:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "TERMSCONDITIONSURL" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","TERMSCONDITIONSURL","")%>" + '"' + " size=50></td></tr>" +
			"<tr><td nowrap align=right width=50%>Hide Search Catalog on Main Menu:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDESEARCHCATALOG" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","HIDESEARCHCATALOG","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","HIDESEARCHCATALOG","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'HIDESEARCHCATALOG');" + '"' + "></td></tr>" +
			"<tr><td align=right>URL for Help:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HELPURL" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","HELPURL","")%>" + '"' + " size=50></td></tr>" +
			"<tr><td nowrap align=right width=50%>Allow Selection of Last Commodity Level Only:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOWSELECTIONLASTCOMMODITYLEVELONLY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","ALLOWSELECTIONLASTCOMMODITYLEVELONLY","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","ALLOWSELECTIONLASTCOMMODITYLEVELONLY","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'ALLOWSELECTIONLASTCOMMODITYLEVELONLY');" + '"' + "></td></tr>" +
			<%if(oid.equalsIgnoreCase("bly07p")) {%>
			"<tr><td colspan=2 align=center>" +
			"<table border=0 cellpadding=1 cellspacing=0>" +
			"<tr><td colspan=5 height=20px>Search Catalog Items Options By:</td></tr>" +
			"<tr>" +

				"<td align=right width=20%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN01", "Udf 1")%>:</td>" +
				"<td align=left width=5%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SearchByUdf1Code" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG ITEM","SearchByUdf1Code","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG ITEM","SearchByUdf1Code","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG ITEM', 'SearchByUdf1Code');" + '"' + "></td>" +

				"<td align=right width=20%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN02", "Udf 2")%>:</td>" +
				"<td align=left width=5%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SearchByUdf2Code" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG ITEM","SearchByUdf2Code","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG ITEM","SearchByUdf2Code","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG ITEM', 'SearchByUdf2Code');" + '"' + "></td>" +

				"<td align=right width=15%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN03", "Udf 3")%>:</td>" +
				"<td align=left width=5%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SearchByUdf3Code" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG ITEM","SearchByUdf3Code","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG ITEM","SearchByUdf3Code","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG ITEM', 'SearchByUdf3Code');" + '"' + "></td>" +

				"<td align=right width=5%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN04", "Udf 4")%>:</td>" +
				"<td align=left width=5%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SearchByUdf4Code" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG ITEM","SearchByUdf4Code","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG ITEM","SearchByUdf4Code","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG ITEM', 'SearchByUdf4Code');" + '"' + "></td>" +

				"<td align=right width=15%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN05", "Udf 5")%>:</td>" +
				"<td align=left width=5%><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SearchByUdf5Code" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG ITEM","SearchByUdf5Code","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG ITEM","SearchByUdf5Code","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG ITEM', 'SearchByUdf5Code');" + '"' + "></td>" +
			"</tr>" +
			"</table>" +
			"</td></tr>" +
			<% } %>
			<%if(oid.equalsIgnoreCase("vse06p")) {%>
			"<tr><td colspan=2 align=center><br>" +
				"<table border=0 cellpadding=1 cellspacing=0>" +
				"<tr><td colspan=4 height=20px>&nbsp;&nbsp;<b>Bar Chart Requisition Display</b></td></tr>" +
				"<tr><td width=5% align=right><input type=radio name=" + '"' + "MISC_BARCHARTREQ_VALUE" + '"' + " value=" + '"' + "3" + '"' + "<% if (propertiesManager.getProperty("MISC","BARCHARTREQ","").equalsIgnoreCase("3")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_BARCHARTREQ_VALUE', 'MISC', 'BARCHARTREQ');" + '"' + "></td>" +
					"<td width=20%>Last 3 Months</td>" +
					"<td width=5% align=right><input type=radio name=" + '"' + "MISC_BARCHARTREQ_VALUE" + '"' + " value=" + '"' + "6" + '"' + "<% if (propertiesManager.getProperty("MISC","BARCHARTREQ","").equalsIgnoreCase("6")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_BARCHARTREQ_VALUE', 'MISC', 'BARCHARTREQ');" + '"' + "></td>" +
					"<td width=20%>Last 6 Months</td>" +
					"<td width=5% align=right><input type=radio name=" + '"' + "MISC_BARCHARTREQ_VALUE" + '"' + " value=" + '"' + "12" + '"' + "<% if (propertiesManager.getProperty("MISC","BARCHARTREQ","").equalsIgnoreCase("12")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_BARCHARTREQ_VALUE', 'MISC', 'BARCHARTREQ');" + '"' + "></td>" +
					"<td width=20%>Last 12 Months</td>" +
					"<td width=5% align=right><input type=radio name=" + '"' + "MISC_BARCHARTREQ_VALUE" + '"' + " value=" + '"' + '"' + "<% if (HiltonUtility.isEmpty(propertiesManager.getProperty("MISC","BARCHARTREQ",""))) {%> checked<%}%> onclick=" + '"' + "setRadioValue('MISC_BARCHARTREQ_VALUE', 'MISC', 'BARCHARTREQ');" + '"' + "></td>" +
					"<td width=20%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%></td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BARCHARTREQ" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","BARCHARTREQ","")%>" + '"' + "></td>" +
				"</tr></table>" +
			"</td></tr>" +
			<% } %>

			"<tr><td colspan=2 align=center>" +
				"<table border=0 cellpadding=1 cellspacing=0>" +
				"<tr><td align=right>Edit Attachments</td>" +
					"<td align=left><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "EDITATTACHMENTS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("EDITATTACHMENTS","ENABLED","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("EDITATTACHMENTS","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'EDITATTACHMENTS', 'ENABLED');" + '"' + ">&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerdashboard", "Buyer Dashboard")%></td>" +
					"<td align=left><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BUYERDASHBOARD" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","BUYERDASHBOARD","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","BUYERDASHBOARD","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'MISC', 'BUYERDASHBOARD');" + '"' + ">&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right>Alerts Configuration</td>" +
					"<td align=left><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ALERT_CONFIGURATION" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ALERT_CONFIGURATION","ENABLED","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ALERT_CONFIGURATION","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ALERT_CONFIGURATION', 'ENABLED');" + '"' + ">&nbsp;&nbsp;&nbsp;</td>" +
					"<td align=right>Validation Rules Management</td>" +
					"<td align=left><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VALIDATIONRULESADMIN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VALIDATIONRULESADMIN","ENABLED","Y")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VALIDATIONRULESADMIN","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VALIDATIONRULESADMIN', 'ENABLED');" + '"' + ">&nbsp;&nbsp;&nbsp;</td>" +
				"</tr></table>" +
			"</td></tr>" +
			"<tr>"+
			"<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dashboardColumns", "NÂº of columns dashboard page")%>:</td>" +
				"<td>&nbsp;<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DASHBOARDCOLUMNS" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"'  + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","DASHBOARDCOLUMNS","2")%>" + '"' + "></td>" +
			"</tr>" +
<%	}%>
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Decimal Place Display</b></td></tr>" +
			"<tr><td colspan=2 align=center>" +
				"<table border=0 cellpadding=1 cellspacing=0>" +
				"<tr><td align=right>Dollar Amounts:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DOLLARDECIMALS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","DOLLARDECIMALS","2")%>" + '"' + "></td>" +
				"<td align=right>Unit Price:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PRICEDECIMALS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","PRICEDECIMALS","2")%>" + '"' + "></td>" +
				"<td align=right>Quantities:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "QTYDECIMALS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"'  + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","QTYDECIMALS","2")%>" + '"' + " onchange=" + '"' + "javascript: decimalDigits(this); void(0);" + '"' + "></td></tr></table>" +
				"</td></tr></table>" +
			"<br>";

		passwordHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td height=30px>&nbsp;&nbsp;<b>Password Parameters</b></td></tr>" +
			"<tr><td valign=top><table border=0 cellpadding=2 cellspacing=0>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Minimum Password Length:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PASSLENGTH" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","PASSLENGTH","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Minimum Number Digits Required:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MINDIGIT" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","MINDIGIT","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Minimum Number Non-Alphanumeric Characters Required:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MINNONALPHA" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","MINNONALPHA","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Days Between Forced Password Changes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DAYSVALID" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","DAYSVALID","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Warning Days:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PASSEXPWARNINGDAYS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","PASSEXPWARNINGDAYS","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Require New Password:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "NEWPASS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","NEWPASS","N")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Number of Prior Passwords:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PRIORPASS" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","PRIORPASS","0")%>" + '"' + " size=15></td></tr>" +
				"<tr><td width=5px>&nbsp;</td><td align=right>Password Case Sensitive:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PASSCASESENSITIVE" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","PASSCASESENSITIVE","N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("MISC","PASSCASESENSITIVE","N").equalsIgnoreCase("Y")) {%> checked<%}%> onclick=" + '"' + "setCkBoxValue(this, 'MISC', 'PASSCASESENSITIVE'); setPasswordDisplay();" + '"' + "></td>" +
				"<tr id='passwordCaseSensitive'><td width=5px>&nbsp;</td><td align=right>Minimum Number of Upper Case Letters:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MINPASSUPPER" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","MINPASSUPPER","0")%>" + '"' + " size=15></td></tr>" +
				"<tr id='passwordCaseSensitive'><td width=5px>&nbsp;</td><td align=right>Minimum Number of Lower Case Letters:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MINPASSLOWER" + '"' + ">" +
					"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC","MINPASSLOWER","0")%>" + '"' + " size=15></td></tr>" +
			"</table></td></tr>" +
			"</table>";
		rfqUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Solicitation Options</b></td></tr>" +
			"<tr><td colspan=2 align=right>Approval Pool:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "APPROVAL APP POOL" + '"' + ">" +
				"&nbsp;<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("RFQ OPTIONS","APPROVAL APP POOL","")%>" + '"' + " size=15></td></tr>" +
			"<tr><td colspan=2 align=right>Competitive and Estimated Dollar:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RFQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "APPROVAL ESTIMATED COST" + '"' + ">" +
				"&nbsp;<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("RFQ OPTIONS","APPROVAL ESTIMATED COST","0.00")%>" + '"' + " size=15 style=" + '"' + "text-align:right" + '"' + " onchange=" + '"' + "addUp(this);" + '"' + "></td></tr>" +
			"</table>";
		poUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Purchase Order Options</b></td></tr>" +
<%	if (rfqsActive || extRfqsActive) {%>
			"<tr><td colspan=2 align=right>Bid Waiver Check:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BIDWAIVER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BIDWAIVER","ENABLED","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("BIDWAIVER","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'BIDWAIVER', 'ENABLED');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Bid Waiver Amount:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BIDWAIVER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AMOUNT" + '"' + ">" +
				"&nbsp;<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BIDWAIVER","AMOUNT","25000")%>" + '"' + " size=15></td></tr>" +
<%	}
	if (vouchersActive) {%>
			"<tr><td colspan=2 align=right>Price Tolerance Check:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PriceToleranceCheck" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","PriceToleranceCheck","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","PriceToleranceCheck","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'PriceToleranceCheck');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Order Tolerance:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "Tolerance" + '"' + ">" +
				"&nbsp;<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","Tolerance","0")%>" + '"' + " size=4>&nbsp;%</td></tr>" +
<%	}%>
			"<tr><td colspan=2 align=right>Enable Daily Release Limit:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "EnableDailyReleaseLimit" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","EnableDailyReleaseLimit","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","EnableDailyReleaseLimit","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'EnableDailyReleaseLimit');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>User Daily Release Limit:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UserDailyReleaseLimit" + '"' + ">" +
				"&nbsp;<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","UserDailyReleaseLimit","0.00")%>" + '"' + " size=15 style=" + '"' + "text-align:right" + '"' + " onchange=" + '"' + "addUp(this);" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Allow administrative changes on PO:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AdminChanges" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","AdminChanges","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","AdminChanges","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'AdminChanges');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Allow One-Time ShipTo Address:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOW ONETIME SHIPTO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "ALLOW ONETIME SHIPTO", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "ALLOW ONETIME SHIPTO", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'ALLOW ONETIME SHIPTO');" + '"' + "></td></tr>" +
<%	if (role.getAccessRights("POCT") > 0) {	%>
			"<tr><td colspan=2 align=right>Allow creation of PO from Contract:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CONTRACTSAVEASPO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","CONTRACTSAVEASPO","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","CONTRACTSAVEASPO","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'CONTRACTSAVEASPO');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Restrict Contract Access:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RESTRICTCONTRACTACCESS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","RESTRICTCONTRACTACCESS","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","RESTRICTCONTRACTACCESS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'RESTRICTCONTRACTACCESS');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Contracts are Restricted to Primary Supplier's Affiliations:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RESTRICTSUPPLIERAFFILIATIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","RESTRICTSUPPLIERAFFILIATIONS","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","RESTRICTSUPPLIERAFFILIATIONS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'RESTRICTSUPPLIERAFFILIATIONS');" + '"' + "></td></tr>" +
<%	}
	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td colspan=2 align=right>Hide Standard Terms & Conditions:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PRINT T&Cs" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","PRINT T&Cs","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS","PRINT T&Cs","Y").equalsIgnoreCase("N")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'PRINT T&Cs');" + '"' + "></td></tr>" +
<%	}%>
			"<tr><td colspan=2 align=right>Supplier Info URL:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SUPPLIERINFOURL" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS","SupplierInfoUrl","")%>" + '"' + " size=45 maxLength=255></td></tr>" +
			"<tr><td colspan=2 align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-enforceSingleAccounts", "Enforce Single Account Allocation")%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SINGLE ACCOUNT ALLOCATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'SINGLE ACCOUNT ALLOCATION');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-showOnlyPaymentSchedules", "Show Only Payment Schedules")%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SHOW ONLY PAYMENT SCHEDULES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "SHOW ONLY PAYMENT SCHEDULES", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "SHOW ONLY PAYMENT SCHEDULES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'SHOW ONLY PAYMENT SCHEDULES');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Punchout Commodity</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "XML COMMODITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "XML COMMODITY", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "XML COMMODITY", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'XML COMMODITY');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Default Taxable from Commodity</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "TAXABLEFROMCOMMODITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "TAXABLEFROMCOMMODITY", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "TAXABLEFROMCOMMODITY", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'TAXABLEFROMCOMMODITY');" + '"' + "></td></tr>" +

<%	if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y")) {
		if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td colspan=2 align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin-restrictNonStdItem", "Restrict Non Standard Item")%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RESTRICTNONSTANDARDITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "RESTRICTNONSTANDARDITEM", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "RESTRICTNONSTANDARDITEM", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'RESTRICTNONSTANDARDITEM');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poFromNothingException", "Orders containing Non Standard Items must be sent for approval")%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO APPROVALS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CREATEDFROMNOTHING" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO APPROVALS", "CREATEDFROMNOTHING", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO APPROVALS", "CREATEDFROMNOTHING", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO APPROVALS', 'CREATEDFROMNOTHING');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "filterByLocale", "Filter PO Approvals by Locale")%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO APPROVALS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "FILTERBYLOCALE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO APPROVALS", "FILTERBYLOCALE", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO APPROVALS", "FILTERBYLOCALE", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO APPROVALS', 'FILTERBYLOCALE');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Re-Assign Changes to PO Buyer:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CHANGEASSIGNBUYER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "CHANGEASSIGNBUYER", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "CHANGEASSIGNBUYER", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'CHANGEASSIGNBUYER');" + '"' + "></td></tr>" +
<%		}
	}%>
			"<tr><td colspan=2 align=right>Display UDF fields on Review & Finalize</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISPLAYRFUDFS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO OPTIONS", "DISPLAYRFUDFS", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("PO OPTIONS", "DISPLAYRFUDFS", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'PO OPTIONS', 'DISPLAYRFUDFS');" + '"' + "></td></tr>" +
			"</table>";
		reqUdfsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Requisition Options</b></td></tr>" +
			"<tr><td colspan=2 align=right>Allow One-Time ShipTo Address</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOW ONETIME SHIPTO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SHIPTO", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SHIPTO", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'ALLOW ONETIME SHIPTO');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Allow One-Time Supplier Address</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOW ONETIME SUPPLIER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SUPPLIER", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "ALLOW ONETIME SUPPLIER", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'ALLOW ONETIME SUPPLIER');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Hide Public Option on Save As Template</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDE PUBLIC TEMPLATE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "HIDE PUBLIC TEMPLATE", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "HIDE PUBLIC TEMPLATE", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'HIDE PUBLIC TEMPLATE');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Allow Single Account Allocation</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SINGLE ACCOUNT ALLOCATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'SINGLE ACCOUNT ALLOCATION');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Hide My Favorite Accounts</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDE FAVORITE ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "HIDE FAVORITE ACCOUNTS", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "HIDE FAVORITE ACCOUNTS", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'HIDE FAVORITE ACCOUNTS');" + '"' + "></td>" +
			"<tr><td colspan=2 align=right>Restrict Non Standard Item</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RESTRICTNONSTANDARDITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "RESTRICTNONSTANDARDITEM", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "RESTRICTNONSTANDARDITEM", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'RESTRICTNONSTANDARDITEM');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>General Information Field Dependencies</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "GENERAL INFO FIELD DEPENDENCIES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "GENERAL INFO FIELD DEPENDENCIES", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "GENERAL INFO FIELD DEPENDENCIES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'GENERAL INFO FIELD DEPENDENCIES');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Punchout Commodity</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "XML COMMODITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "XML COMMODITY", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "XML COMMODITY", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'XML COMMODITY');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Default Taxable from Commodity</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "TAXABLEFROMCOMMODITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "TAXABLEFROMCOMMODITY", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "TAXABLEFROMCOMMODITY", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'TAXABLEFROMCOMMODITY');" + '"' + "></td></tr>" +
/*			"<tr><td colspan=2 align=right>Release Request Dollar Amount</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ReleaseRequestDollarAmount" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS","ReleaseRequestDollarAmount","0")%>" + '"' + " size=15></td></tr>" +
*/
			"<tr><td colspan=2 align=right>Default Receipt Option from Catalog Item</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEF REC OPTION FROM CAT ITEM" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "DEF REC OPTION FROM CAT ITEM", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "DEF REC OPTION FROM CAT ITEM", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'DEF REC OPTION FROM CAT ITEM');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Display UDF fields on Review & Finalize</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISPLAYRFUDFS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRFUDFS", "N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRFUDFS", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'DISPLAYRFUDFS');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Display Bid Waiver & Request Category Fields on Review and Finalize</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISPLAYRBIDREQCAT" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRBIDREQCAT", "N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "DISPLAYRBIDREQCAT", "N").equalsIgnoreCase("Y")) {%>checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'DISPLAYRBIDREQCAT');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Require UDF10 if UDF7 is set.</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REQUIREUDF10IFUDF7" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "REQUIREUDF10IFUDF7", "N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "REQUIREUDF10IFUDF7", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'REQUIREUDF10IFUDF7');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 align=right>Populate Vendor on line from header when line value is empty.</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REQ OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DEFAULTLINEVENDFROMHEAD" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REQ OPTIONS", "DEFAULTLINEVENDFROMHEAD", "N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REQ OPTIONS", "DEFAULTLINEVENDFROMHEAD", "N").equalsIgnoreCase("Y")) {%> checked <%}%> onchange=" + '"' + "setCkBoxValue(this, 'REQ OPTIONS', 'DEFAULTLINEVENDFROMHEAD');" + '"' + "></td></tr>" +
			"</table>";
		voucherSetupHTML = "<table border=0 cellspacing=0 cellpadding=1 width=480px>" +
			"<tr><td colspan=2 height=18px>&nbsp;&nbsp;<b>Invoice Voucher Setup</b></td></tr>" +
			"<tr><td align=right width=260>Auto Matching Method:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AutoMethod" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","AutoMethod","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","AutoMethod","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'AutoMethod');" + '"' + "></td>" +
				"<td align=right width=160>Voucher Tolerance:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "Tolerance" + '"' + ">" +
				"<input type=text size=3 name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","Tolerance","0")%>" + '"' + " size=15></td></tr>" +
			"<tr><td align=right>Auto PO Closeout:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AutoClose" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","AutoClose","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","AutoClose","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'AutoClose');" + '"' + "></td>" +
			"<td align=right>Max Dollar Value:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MaxDollar" + '"' + ">" +
				"<input type=text size=3 name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","MaxDollar","0")%>" + '"' + " size=15></td></tr>" +
			"<tr><td align=right>Update Received Qty Upon Approval:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UpdateReceivedQuantity" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","UpdateReceivedQuantity","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","UpdateReceivedQuantity","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'UpdateReceivedQuantity');" + '"' + "></td>" +
			"<td align=right width=160>Voucher Neg. Tolerance:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ToleranceNeg" + '"' + ">" +
				"<input type=text size=3 name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","ToleranceNeg","0")%>" + '"' + " size=15></td>" +
			"<tr><td align=right>Invoice Awarded POs:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "AWARDEDPO" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","AWARDEDPO","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","AWARDEDPO","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'AWARDEDPO');" + '"' + "></td>" +
			"<tr><td align=right>AP User Email Address:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "APUSEREMAILADDRESS" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' +  "<%=propertiesManager.getProperty("VOUCHER","APUSEREMAILADDRESS","")%>" + '"' + " size=30></td>" +
			"<tr><td colspan=3 height=18px>&nbsp;&nbsp;<b>Exception Error Processing Options (Auto Matching)</b></td></tr>" +
			"<tr><td align=right width=250px>Enable Price Tolerance Exceptions:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PriceToleranceException" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","PriceToleranceException","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","PriceToleranceException","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'PriceToleranceException');" + '"' + "></td>" +
			"<tr><td align=right width=250px>Enable Receipt Quantity Exceptions:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ReceiptQuantityException" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","ReceiptQuantityException","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","ReceiptQuantityException","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'ReceiptQuantityException');" + '"' + "></td>" +
			"<tr><td align=right width=250px>Enable Maximum Dollar Exceptions:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MaxDollarException" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","MaxDollarException","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","MaxDollarException","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'MaxDollarException');" + '"' + "></td>" +
			"<tr><td align=right width=250px>Enable Over Payment Exceptions:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "OverPaymentException" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","OverPaymentException","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","OverPaymentException","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'OverPaymentException');" + '"' + "></td>" +
			"<tr><td align=right width=250>Enable No Invoice Quantity Exceptions:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "NoInvoiceQtyException" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER","NoInvoiceQtyException","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER","NoInvoiceQtyException","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER', 'NoInvoiceQtyException');" + '"' + "></td>" +
			"<tr><td colspan=3 height=18px>&nbsp;&nbsp;<b>User Editing Options</b></td></tr>" +
			"<tr><td align=right width=250px>Allow AP Reference Edit:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOW APREFERENCE EDIT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","ALLOW APREFERENCE EDIT","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER OPTIONS","ALLOW APREFERENCE EDIT","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER OPTIONS', 'ALLOW APREFERENCE EDIT');" + '"' + "></td>" +
			"<tr><td align=right width=250px>Allow Line Item Description Edit:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOW ITEMDESC EDIT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","ALLOW ITEMDESC EDIT","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER OPTIONS","ALLOW ITEMDESC EDIT","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER OPTIONS', 'ALLOW ITEMDESC EDIT');" + '"' + "></td>" +
			"<tr><td align=right width=250px>Don't Edit Payment Information:</td>" +
				"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "NOTALLOWPAYMENTINFORMATIONEDIT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","NOTALLOWPAYMENTINFORMATIONEDIT","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER OPTIONS","NOTALLOWPAYMENTINFORMATIONEDIT","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER OPTIONS', 'NOTALLOWPAYMENTINFORMATIONEDIT');" + '"' + "></td></tr>" +
			"<tr><td align=right width=250px valign=top>Allow Supplier Remit To Address Entry:</td>" +
				"<td colspan=2>" +
					"<table border=0 cellpadding=1 cellspacing=0>" +
					"<tr><td align=right><input type=radio name=" + '"' + "VOUCHER_REMITTOEDIT_VALUE" + '"' + " value=" + '"' + "ENABLE" + '"' + "<% if (propertiesManager.getProperty("VOUCHER OPTIONS","REMITTOEDIT","ENABLE").equalsIgnoreCase("ENABLE")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('VOUCHER_REMITTOEDIT_VALUE', 'VOUCHER OPTIONS', 'REMITTOEDIT');" + '"' + "></td>" +
						"<td>Always</td></tr>" +
					"<tr><td align=right><input type=radio name=" + '"' + "VOUCHER_REMITTOEDIT_VALUE" + '"' + " value=" + '"' + "DISABLE" + '"' + "<% if (propertiesManager.getProperty("VOUCHER OPTIONS","REMITTOEDIT","ENABLE").equalsIgnoreCase("DISABLE")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('VOUCHER_REMITTOEDIT_VALUE', 'VOUCHER OPTIONS', 'REMITTOEDIT');" + '"' + "></td>" +
						"<td>Never</td>" +
					"<tr><td align=right><input type=radio name=" + '"' + "VOUCHER_REMITTOEDIT_VALUE" + '"' + " value=" + '"' + "ENABLEFROMNOTHING" + '"' + "<% if (propertiesManager.getProperty("VOUCHER OPTIONS","REMITTOEDIT","ENABLE").equalsIgnoreCase("ENABLEFROMNOTHING")) {%> checked<%}%> onclick=" + '"' + "setRadioValue('VOUCHER_REMITTOEDIT_VALUE', 'VOUCHER OPTIONS', 'REMITTOEDIT');" + '"' + "></td>" +
						"<td>When created from nothing</td>" +
					"</table>" +
					"<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REMITTOEDIT" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","REMITTOEDIT","ENABLE")%>" + '"' + "></td></tr>" +
			"<tr><td align=right width=250px>Current Month Begin Date (MM-dd-yyyy):</td>" +
					"<td colspan=2><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CURRENT MONTH BEGIN DATE" + '"' + ">" +
					"<input type=text size=10 name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","CURRENT MONTH BEGIN DATE","")%>" + '"' + " size=15></td></tr>" +
			"<tr><td colspan=3 height=18px>&nbsp;&nbsp;<b>Account Allocations</b></td></tr>" +
			"<tr><td align=right width=250px>Line Item Distribution:</td>" +
				"<td width=30px><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "LINE ITEM DISTRIBUTION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","LINE ITEM DISTRIBUTION","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER OPTIONS","LINE ITEM DISTRIBUTION","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER OPTIONS', 'LINE ITEM DISTRIBUTION');" + '"' + "></td>" +
				"<td align=left>&nbsp;</td></tr>" +
			"<tr><td align=right width=250px>Use Tax Credit Account:</td>" +
				"<td width=30px><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VOUCHER OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "USETAX CREDIT ACCOUNT" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VOUCHER OPTIONS","USETAX CREDIT ACCOUNT","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VOUCHER OPTIONS","USETAX CREDIT ACCOUNT","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VOUCHER OPTIONS', 'USETAX CREDIT ACCOUNT');" + '"' + "></td>" +
				"<td align=left><a href='javascript: setDefaultUseTaxAccount();'>Set Default</a></td></tr>" +
			"<tr><td align=right width=250px>Approvers can Edit Invoice Allocation:</td>" +
				"<td width=30px><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACCOUNTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "EDIT ACCOUNT ON APPROVALS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACCOUNTS","EDIT ACCOUNT ON APPROVALS","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACCOUNTS","EDIT ACCOUNT ON APPROVALS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACCOUNTS', 'EDIT ACCOUNT ON APPROVALS');" + '"' + "></td>" +
				"<td align=left>&nbsp;</td></tr>" +
			"</table>";
		poTypesHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=4 height=30px>&nbsp;&nbsp;<b>PO Types</b></td></tr>" +
			"<tr><td width=60px align=right><b>Code</b></td><td width=120px><b>Original Type</b></td><td width=140px><b>User Defined Type</b></td><td><b>Numbering Prefix</b></td></tr>" +
			"<tr><td width=60px align=right>PO&nbsp;&nbsp;</td><td nowrap>Purchase Order</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","PO"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PO_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","PO_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>BO&nbsp;&nbsp;</td><td nowrap>Blanket Order</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BO" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","BO"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BO_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","BO_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>RO&nbsp;&nbsp;</td><td nowrap>Release Order</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RO" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","RO"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RO_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","RO_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>DO&nbsp;&nbsp;</td><td nowrap>Delivery Order</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DO" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","DO"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DO_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","DO_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>DR&nbsp;&nbsp;</td><td nowrap>Delivery Release</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DR" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","DR"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DR_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","DR_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>SO&nbsp;&nbsp;</td><td nowrap>Service Order</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SO" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SO"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SO_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SO_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>SB&nbsp;&nbsp;</td><td nowrap>Service Blanket</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SB" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SB"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SB_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SB_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>SR&nbsp;&nbsp;</td><td nowrap>Service Release</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SR" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SR"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SR_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","SR_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"<tr><td width=60px align=right>CT&nbsp;&nbsp;</td><td nowrap>Contract</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CT" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","CT"," ")%>" + '"' + " size=30></td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "PO TYPES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CT_PREFIX" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("PO TYPES","CT_PREFIX","")%>" + '"' + " size=2></td></tr>" +
			"</table>";
			vendorRegistrationHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
				"<tr><td height=30px>&nbsp;&nbsp;<b>Vendor Registration</b></td></tr>" +
				"<tr><td align=right>Use Puridiom Supplier ID:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "USE PURIDIOM SUPPLIER ID" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "USE PURIDIOM SUPPLIER ID", "Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "USE PURIDIOM SUPPLIER ID", "Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'USE PURIDIOM SUPPLIER ID');" + '"' + "></td></tr>" +
				"<tr><td align=right>Need Accounting Notification:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ACCOUNTING NOTIFICATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "ACCOUNTING NOTIFICATION", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "ACCOUNTING NOTIFICATION", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'ACCOUNTING NOTIFICATION');" + '"' + "></td></tr>" +
				"<tr><td align=right>Accounting Email Address:</td><td>&nbsp;<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ACCOUNTING EMAIL ADDR" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=accountingEmailAddr%>" + '"' + " size=35></td>" +
				"<tr><td align=right>Vendor Registration Notification Email Address:</td><td>&nbsp;<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "NOTIFICATION EMAIL ADDR" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=vendorRegNotificationEmailAddr%>" + '"' + " size=35></td>" +
				"<tr><td align=right>Send Acceptance Notification to Supplier:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VENDOR ACCEPTANCE NOTIFICATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VENDOR ACCEPTANCE NOTIFICATION", "N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VENDOR ACCEPTANCE NOTIFICATION", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VENDOR ACCEPTANCE NOTIFICATION');" + '"' + "></td></tr>" +
				"<tr><td align=right>Qualification Reminder Every:</td><td>&nbsp;<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "QUALIFICATION REMINDER DAYS" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=qualificationReminderDays%>" + '"' + " size=2>&nbsp;Days</td></tr>" +
				"<tr><td align=right>Display <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN01", "UDF 01")%> as checkboxes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VN01CHECKBOXES" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VN01CHECKBOXES", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VN01CHECKBOXES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VN01CHECKBOXES');" + '"' + "></td></tr>" +
				"<tr><td align=right>Display <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN02", "UDF 02")%> as checkboxes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VN02CHECKBOXES" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VN02CHECKBOXES", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VN02CHECKBOXES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VN02CHECKBOXES');" + '"' + "></td></tr>" +
				"<tr><td align=right>Display <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN03", "UDF 03")%> as checkboxes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VN03CHECKBOXES" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VN03CHECKBOXES", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VN03CHECKBOXES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VN03CHECKBOXES');" + '"' + "></td></tr>" +
				"<tr><td align=right>Display <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN04", "UDF 04")%> as checkboxes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VN04CHECKBOXES" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VN04CHECKBOXES", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VN04CHECKBOXES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VN04CHECKBOXES');" + '"' + "></td></tr>" +
				"<tr><td align=right>Display <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN05", "UDF 05")%> as checkboxes:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VN05CHECKBOXES" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "VN05CHECKBOXES", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "VN05CHECKBOXES", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'VN05CHECKBOXES');" + '"' + "></td></tr>" +
				"<tr><td align=right>Display Vendor UDFs on Vendor Information Page:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "VENDOR-REGISTRATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISPLAYVENDORUNDFSONVENDORINFOPG" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("VENDOR-REGISTRATION", "DISPLAYVENDORUNDFSONVENDORINFOPG", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("VENDOR-REGISTRATION", "DISPLAYVENDORUNDFSONVENDORINFOPG", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'VENDOR-REGISTRATION', 'DISPLAYVENDORUNDFSONVENDORINFOPG');" + '"' + "></td></tr>" +
				"<tr><td align=right>Require Acceptance of Terms & Conditions Upon Registration & Guest Login:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BB-APPLICATION" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "TERMSACCEPTANCEREQUIRED" + '"' + ">" +
						"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BB-APPLICATION", "TERMSACCEPTANCEREQUIRED", "N")%>" + '"' + ">" +
						"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("BB-APPLICATION", "TERMSACCEPTANCEREQUIRED", "N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'BB-APPLICATION', 'TERMSACCEPTANCEREQUIRED');" + '"' + "></td></tr>" +
			"</table>";
			reportHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
				"<tr><td height=30px>&nbsp;&nbsp;<b>Display Report Options</b></td></tr>" +
				"<tr><td align=right>Include one-time reports in scheduled report list:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORT OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ONCE FREQUENCY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reportOnceIsScheduled%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (reportOnceIsScheduled.equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'ONCE FREQUENCY');" + '"' + "></td></tr>" +
				"<tr><td align=right>Set the default access to Reports as Public:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORT OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PUBLIC ACCESS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reportPublicAccess%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (reportPublicAccess.equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'PUBLIC ACCESS');" + '"' + "></td></tr>" +
				"<tr><td align=right>Allow users to set reports to public/private:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORT OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "FILE PUBLIC ACCESS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reportFilePublicAccess%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (reportFilePublicAccess.equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'FILE PUBLIC ACCESS');" + '"' + "></td></tr>" +
				"<tr><td height=30px>&nbsp;&nbsp;<b>Max Size</b></td></tr>" +
				"<tr><td align=right>Attachment Report max size:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORT OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "REPORTSIZE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%= reportSizeProperty %>" + '"' + ">" +
				"<input type=text id=" + '"' + "reportSize" + '"' + " name=" + '"' + "reportSize" + '"' + " value=" + '"' + "<%= reportSize %>" + '"' + " size=4 onBlur=" + '"' + "javascript:setPropertyValue('REPORT OPTIONS', 'REPORTSIZE', document.getElementById('reportSize').value + document.getElementById('reportUnitSize').value);" + '"' + ">" +
				"<select id=" + '"' + "reportUnitSize" + '"' + " name=" + '"' + "reportUnitSize" + '"' + " onChange=" + '"' + "javascript:setPropertyValue('REPORT OPTIONS', 'REPORTSIZE', document.getElementById('reportSize').value + document.getElementById('reportUnitSize').value);" + '"' + ">" +
				"<option value=" + '"' + "KB" + '"' + " <% if (reportUnitSize.equals("KB")) {%> selected<%}%>>KB</option>" +
				"<option value=" + '"' + "MB" + '"' + " <% if (reportUnitSize.equals("MB")) {%> selected<%}%>>MB</option>" +
				"<option value=" + '"' + "GB" + '"' + " <% if (reportUnitSize.equals("GB")) {%> selected<%}%>>GB</option>" +
				"</select></td></tr>" +
			"<tr><td align=right width=275px nowrap>Maximum number of rows returned:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MAXROWS" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REPORTS","MAXROWS","5000")%>" + '"' + " onchange=" + '"' + "nfilter(this);" + '"' + "></td></tr>" +
				"<tr><td height=30px>&nbsp;&nbsp;<b>Types</b>" +
				"<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REPORT OPTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "TYPES ALLOWED" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=reportTypesAllowed%>" + '"' + "></td></tr>" +
				"<tr><td align=right>PDF:</td>" +
				"<td><input type=checkbox name=" + '"' + "ckboxPDF" + '"' + " value='Y' <% if (reportTypesAllowed.indexOf("PDF")>=0) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'TYPES ALLOWED');" + '"' + "></td></tr>" +
				"<tr><td align=right>HTML:</td>" +
				"<td><input type=checkbox name=" + '"' + "ckboxHTML" + '"' + " value='Y' <% if (reportTypesAllowed.indexOf("HTML")>=0) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'TYPES ALLOWED');" + '"' + "></td></tr>" +
				"<tr><td align=right>XLS:</td>" +
				"<td><input type=checkbox name=" + '"' + "ckboxXLS" + '"' + " value='Y' <% if (reportTypesAllowed.indexOf("XLS")>=0) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'TYPES ALLOWED');" + '"' + "></td></tr>" +
				"<tr><td align=right>CSV:</td>" +
				"<td><input type=checkbox name=" + '"' + "ckboxCSV" + '"' + " value='Y' <% if (reportTypesAllowed.indexOf("CSV")>=0) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REPORT OPTIONS', 'TYPES ALLOWED');" + '"' + "></td></tr>" +
			"</table>";
			psEmailProperties = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
				"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>PS Email Properties Setup</b></td></tr>" +
				"<tr><td align=right>Extra BCC excluded domain:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "EMAILPROPS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BCCEXCLUDEDOMAIN" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("EMAILPROPS","BCCEXCLUDEDOMAIN","")%>" + '"' + " size=10></td></tr>" +
				"<tr><td align=right>Punchline:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "EMAILPROPS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "PUNCHLINE" + '"' + ">" +
				"<textarea cols=50 rows=2 name=" + '"' + "Property_value" + '"' + '><%=propertiesManager.getProperty("EMAILPROPS","PUNCHLINE","")%></textarea></td></tr>' +
				"<tr><td align=right>Currency update email report:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "EMAILPROPS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CURRENCYUPDATEEMAILREPORT" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("EMAILPROPS","CURRENCYUPDATEEMAILREPORT","")%>" + '"' + " size=50>" +
				"<tr><td align=right>Po receiving Reminder email:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "ACTIVATE EMAIL NOTIFICATION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ACTIVATE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("ACTIVATE EMAIL NOTIFICATION","ACTIVATE","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("ACTIVATE EMAIL NOTIFICATION","ACTIVATE","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'ACTIVATE EMAIL NOTIFICATION', 'ACTIVATE');" + '"' + "></td></tr>" +
				"</td></tr>" +
			"</table>";
			auditTrailHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Audit Trail Administration</b></td></tr>" +
<%	if (reqsActive) {%>
			"<tr><td align=right width=275px nowrap>Approval Rules Setup:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUDITTRAIL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ApprovalRulesSetup" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUDITTRAIL","ApprovalRulesSetup","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUDITTRAIL","ApprovalRulesSetup","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUDITTRAIL', 'ApprovalRulesSetup');" + '"' + "></td></tr>" +
<%	}%>
			"<tr><td align=right width=275px nowrap>Supplier Management:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUDITTRAIL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "SupplierManagement" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUDITTRAIL","SupplierManagement","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUDITTRAIL","SupplierManagement","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUDITTRAIL', 'SupplierManagement');" + '"' + "></td></tr>" +
<%	if (budgetActive) {%>
			"<tr><td align=right width=275px nowrap>Budget Management:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUDITTRAIL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "BudgetManagement" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUDITTRAIL","BudgetManagement","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUDITTRAIL","BudgetManagement","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUDITTRAIL', 'BudgetManagement');" + '"' + "></td></tr>" +
<%	}%>
			"<tr><td align=right width=275px nowrap>User Profile:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUDITTRAIL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UserProfile" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUDITTRAIL","UserProfile","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUDITTRAIL","UserProfile","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUDITTRAIL', 'UserProfile');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>User Log:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "AUDITTRAIL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UserLog" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("AUDITTRAIL","UserLog","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("AUDITTRAIL","UserLog","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'AUDITTRAIL', 'UserLog');" + '"' + "></td></tr>" +
			"</table>";

			browseOptionsHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Browse Options</b></td></tr>" +
			"<tr><td align=right width=275px nowrap>Show details always:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BROWSE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DetailVisible" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BROWSE","DetailVisible","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("BROWSE","DetailVisible","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'BROWSE', 'DetailVisible');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Maximum number of rows returned:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BROWSE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "MAXROWS" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BROWSE","MAXROWS","5000")%>" + '"' + " onchange=" + '"' + "nfilter(this);" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Apply Locale Filter to POs and RFQs:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "BROWSE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "APPLYLOCALEFILTER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("BROWSE","APPLYLOCALEFILTER","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("BROWSE","APPLYLOCALEFILTER","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'BROWSE', 'APPLYLOCALEFILTER');" + '"' + "></td></tr>" +
			"</table>";

			catalogHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Catalog Options</b></td></tr>" +
			"<tr><td align=right width=275px nowrap>Enable Catalog Security:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG SECURITY DEFAULTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "CATALOGSECURITYACTIVE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG SECURITY DEFAULTS","CATALOGSECURITYACTIVE","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG SECURITY DEFAULTS","CATALOGSECURITYACTIVE","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG SECURITY DEFAULTS', 'CATALOGSECURITYACTIVE');" + '"' + "></td></tr>" +
				"<tr><td align=right width=275px nowrap>Use drop down box for Catalogs on Advanced Item Search:</td>" +
					"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "CATALOG OPTIONS" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "USEDROPDOWN" + '"' + ">" +
					"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("CATALOG OPTIONS","USEDROPDOWN","N")%>" + '"' + ">" +
					"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("CATALOG OPTIONS","USEDROPDOWN","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'CATALOG OPTIONS', 'USEDROPDOWN');" + '"' + "></td></tr>" +
				"</table>";

			receiptSetupHTML = "<table border=0 cellspacing=0 cellpadding=2 width=480px>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Receipt Setup Options</b></td></tr>" +
			"<tr><td align=right width=275px nowrap>Allow receipt of negative quantities:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC DEFAULTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOWNEGATIVEQUANTITIES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC DEFAULTS","ALLOWNEGATIVEQUANTITIES","")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC DEFAULTS","ALLOWNEGATIVEQUANTITIES","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC DEFAULTS', 'ALLOWNEGATIVEQUANTITIES');" + '"' + "></td></tr>" +
<%	if (role.getAccessRights("PXADMIN") == 99) {%>
			"<tr><td align=right width=275px nowrap>Receiver can not be equal to Requisitioner:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC DEFAULTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RECEIVERNOTEQUALREQUISITIONER" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC DEFAULTS","RECEIVERNOTEQUALREQUISITIONER","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC DEFAULTS","RECEIVERNOTEQUALREQUISITIONER","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC DEFAULTS', 'RECEIVERNOTEQUALREQUISITIONER');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Double-Step Receiving:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC DEFAULTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DOUBLE-STEP RECEIVING" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC DEFAULTS","DOUBLE-STEP RECEIVING","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC DEFAULTS","DOUBLE-STEP RECEIVING","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC DEFAULTS', 'DOUBLE-STEP RECEIVING');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Disable End User for Adjust Receipt:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC DEFAULTS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DISABLE END USER ADJUST" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC DEFAULTS","DISABLE END USER ADJUST","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC DEFAULTS","DISABLE END USER ADJUST","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC DEFAULTS', 'DISABLE END USER ADJUST');" + '"' + "></td></tr>" +
			"<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-receiptOptions", "Receipt Options", true)%></b></td></tr>" +
			"<tr><td align=right width=275px nowrap>Validate Receipt Quantity:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "RECEIVING" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "VALIDATERECQUANTITY" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("RECEIVING","VALIDATERECQUANTITY","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("RECEIVING","VALIDATERECQUANTITY","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'RECEIVING', 'VALIDATERECQUANTITY');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defaultreceiptoption", "No Default", false)%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "X" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","X","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","X","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'X');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptrequired", "Receipt Required", false)%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "R" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","R","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","R","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'R');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyreceived", "Previously Received", false)%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "P" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","P","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","P","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'P');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noreceiptrequired", "No Receipt Required", false)%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "N" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","N","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","N","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'N');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enduserreceipt", "End User Receipt", false)%>:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "E" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","E","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","E","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'E');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Hide forward to selection on receipts other than package receipts:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDEFORWARDTOSELECTION" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","HIDEFORWARDTOSELECTION","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","HIDEFORWARDTOSELECTION","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'HIDEFORWARDTOSELECTION');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Hide forward to selection on all receipts:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "HIDEFORWARDTOONALL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","HIDEFORWARDTOONALL","N")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","HIDEFORWARDTOONALL","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'HIDEFORWARDTOONALL');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Allow changes to supplier:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ALLOWSUPPLIERCHANGE" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","ALLOWSUPPLIERCHANGE","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","ALLOWSUPPLIERCHANGE","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'ALLOWSUPPLIERCHANGE');" + '"' + "></td></tr>" +
			"<tr><td align=right width=275px nowrap>Enable Receive All Option:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "REC SELECTIONS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "RECEIVEALL" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("REC SELECTIONS","RECEIVEALL","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'REC SELECTIONS', 'RECEIVEALL');" + '"' + "></td></tr>" +
<%	}%>
			"</table>";
		uploadItemsHTML = "<table border=0 cellpadding=1 cellspacing=0 width=480px>" +
			"<tr><td colspan=3 height=30px>&nbsp;&nbsp;<b>Upload Item Options</b></td></tr>" +
			"<tr><td nowrap>Item #:</td>" +
				"<td colspan=1><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ITEMNUMBER" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","ITEMNUMBER","1")%>"+ '"' +"></td>" +
			"<td colspan=3>Description:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "DESCRIPTION" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","DESCRIPTION","2")%>" + '"' + "></td>" +
			"<td colspan=3>Qty:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "QTY" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"'  + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","QTY","3")%>" + '"' + "></td>" +
			"<td colspan=3>UOM:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UOM" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"'  + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","UOM","4")%>" + '"' + "></td>" +
			"<td colspan=3>Unit Price:</td>" +
				"<td colspan=3><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UNITPRICE" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "5" + '"'  + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","UNITPRICE","5")%>" + '"' + "></td></tr>" +
			"</table>" +
			"<br>" +
			"<table border=0 cellpadding=1 cellspacing=0 width=480px>" +
			"<tr><td nowrap align=right>Upload Items File Image:</td>" +
				"<td colspan=3>&nbsp;<input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "MISC" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "UPLOADITEMSFILEIMAGE" + '"' + ">" +
				"<input type=text name=" + '"' + "Property_value" + '"' + " size=" + '"' + "20" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("MISC", "UPLOADITEMSFILEIMAGE", "TemplateXls.png")%>"+ '"' +"></td></tr>" +
			"<tr><td colspan=4>&nbsp;</td></tr>" +
			"<tr><td align=right>Upload Items Access:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADITEMS" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADITEMS","ENABLED","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("UPLOADITEMS","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'UPLOADITEMS', 'ENABLED');" + '"' + "></td></tr>" +
			"<tr><td align=right>Upload Images Access:</td>" +
				"<td><input type=hidden name=" + '"' + "Property_section" + '"' + " value=" + '"' + "UPLOADIMAGES" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_property" + '"' + " value=" + '"' + "ENABLED" + '"' + ">" +
				"<input type=hidden name=" + '"' + "Property_value" + '"' + " value=" + '"' + "<%=propertiesManager.getProperty("UPLOADIMAGES","ENABLED","Y")%>" + '"' + ">" +
				"<input type=checkbox name=" + '"' + "ckbox" + '"' + " value='Y' <% if (propertiesManager.getProperty("UPLOADIMAGES","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange=" + '"' + "setCkBoxValue(this, 'UPLOADIMAGES', 'ENABLED');" + '"' + "></td>" +
			"</tr></table>";

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

		if(s=='PO OPTIONS' && p=='PRINT T&Cs')
		{
			if (ckbox.checked == true) {
				value = "N";
			}
			else {
				value = "Y";
			}
		}

		if(s=='REPORT OPTIONS' && p=='TYPES ALLOWED')
		{
			var value = "-" + ckbox.name.substr(5,4);
			if (ckbox.checked == true) {
				action = "a";
			}
			else {
				action = "r";
			}
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

	function validateForm() {
		//set value for Languages
		var languages = "";
		var temp = "";

		for (var i=0; i < 8; i++) {
			temp = frm.as_language[i].value;

			if (!isEmpty(temp)) {
				if (isEmpty(languages)) {
					languages = temp;
				}
				else {
					languages = languages + "," + temp;
				}
			}
		}

		setPropertyValue("MISC", "Languages", languages);

		return true;
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

	function setPasswordDisplay() {
		var caseSensitive = getPropertyValue("MISC", "PASSCASESENSITIVE", "N");
		if (caseSensitive == "Y") {
			displayArea("passwordCaseSensitive");
		} else {
			hideArea("passwordCaseSensitive");
		}
	}

	function setDefaultUseTaxAccount()
	{
		doSubmit('/invoice/iv_usetax_account.jsp', 'UseTaxAccountRetrieveById');
	}

	function addUp(field)
	{
		field.value = nformat(eval(nfilter(field)), <%=Integer.valueOf(s_quantity_decimals).intValue()%>);
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
