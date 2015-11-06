<%
	boolean	assetsActive = PropertiesManager.getInstance(oid).isModuleActive("ASSETS");
	boolean	recsActive = PropertiesManager.getInstance(oid).isModuleActive("RECEIVING");
	boolean	reqsActive = PropertiesManager.getInstance(oid).isModuleActive("REQUISITIONS");
	boolean	rfqsActive = PropertiesManager.getInstance(oid).isModuleActive("REQUEST FOR QUOTES");
	boolean	extRfqsActive = PropertiesManager.getInstance(oid).isModuleActive("EXTENDED RFQS");
	boolean	posActive = PropertiesManager.getInstance(oid).isModuleActive("PURCHASE ORDERS");
	boolean	receivingActive = PropertiesManager.getInstance(oid).isModuleActive("RECEIVING");
	boolean	extInvActive = PropertiesManager.getInstance(oid).isModuleActive("EXTENDED INVENTORY");
	boolean	stdInvActive = PropertiesManager.getInstance(oid).isModuleActive("STANDARD INVENTORY");
	boolean	vouchersActive = PropertiesManager.getInstance(oid).isModuleActive("VOUCHERS");
	boolean	salesActive = PropertiesManager.getInstance(oid).isModuleActive("SALES");
	boolean	bomActive = PropertiesManager.getInstance(oid).isModuleActive("BOM");
	boolean budgetActive = PropertiesManager.getInstance(oid).isModuleActive("ACCOUNT BUDGET");
	String  doubleStepReceiving = PropertiesManager.getInstance(oid).getProperty("REC DEFAULTS", "DOUBLE-STEP RECEIVING", "N");
	String  singlePageReceipt = PropertiesManager.getInstance(oid).getProperty("REC DEFAULTS", "SINGLEPAGERECEIPT", "N");
	String AppEnv = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "ENVIRONMENT", "");
	String displayEnv = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "DISPLAYENV", "N");
	String orgStatus = "";
	if (!HiltonUtility.isEmpty(oid)) {
		orgStatus = OrganizationManager.getInstance().getOrganization(oid).getStatus();
	}
%>
<script language='Javascript1.2' type="text/javascript">
<!--
	var browser = browserCheck();
	var assetAccess = 0;
	var disbAccess = 0;
	var reqAccess = 0;
	var rfqAccess = 0;
	var poAccess = 0;
	var questAccess = 0;
	var rcvAccess = 0;
	var rcvByPkgAccess = 0;
	var rcvByEndUserAccess = 0;
	var rcvFromNothingAccess = 0;
	var rcvByItemAccess = 0;
	var rcvFinalizeAccess = 0;
	var rcvAdjAccess = 0;
	var rcvReturnAccess = 0;
	var rcvHistoryAccess = 0;
	var quickRcvAccess = 0;
	var invAccess = 0;
	var invoiceAccess = 0;
	var salesAccess = 0;
	var repReqAccess = 0;
	var repRfqAccess = 0;
	var repPoAccess = 0;
	var repInvAccess = 0;
	var repAdminAccess = 0;
	var repRecAccess = 0;
	var repVchAccess = 0;
	var repBdgAccess = 0;
	var returnAccess = 0;
	var bomAccess = 0;
	var approver = false;
	var buyer = false;
	var assetsActive = <%=assetsActive%>;
	var recsActive = <%=recsActive%>;
	var reqsActive = <%=reqsActive%>;
	var rfqsActive = <%=rfqsActive%>;
	var extRfqsActive = <%=extRfqsActive%>;
	var posActive = <%=posActive%>;
	var receivingActive = <%=receivingActive%>;
	var extInvActive = <%=extInvActive%>;
	var stdInvActive = <%=stdInvActive%>;
	var vouchersActive = <%=vouchersActive%>;
	var salesActive = <%=salesActive%>;
	var bomActive = <%=bomActive%>;
	var dashboardAccess = 0;
	var MasterUser = 0;
	var catalogItems = 0;
	var disableEndUserAdjust = <%=PropertiesManager.getInstance(oid).getProperty("REC DEFAULTS", "DISABLE END USER ADJUST", "N").equalsIgnoreCase("Y")%>;

	if (assetsActive) {
		assetAccess = <%=role.getAccessRights("ASSETS")%>;
	}
	if (recsActive) {
		recAccess = <%=role.getAccessRights("RCV")%>;
		receiver = <%=user.isAReceiverValue()%>;
	}
	if (reqsActive) {
		reqAccess = <%=role.getAccessRights("REQ")%>;
		approver = <%=user.isAnApprover()%>;
	}
	if (rfqsActive) {
		rfqAccess = <%=role.getAccessRights("RFQ")%>;
		questAccess = <%=role.getAccessRights("QUEST")%>;
		bbdocsAccess = <%=role.getAccessRights("BBDOCS")%>;
		bbsupAccess = <%=role.getAccessRights("BBSUP")%>;
	}
	if (posActive) {
		poAccess = <%=role.getAccessRights("PO")%>;
		buyerAssignAccess = <%=role.getAccessRights("BUYRASSN")%>;
		buyer = <%=user.isABuyer()%>;
	}
	if (receivingActive) {
		rcvAccess = <%=role.getAccessRights("RCV")%>;
		rcvByPkgAccess = <%=role.getAccessRights("RCVBYPKG")%>;
		rcvByEndUserAccess = <%=role.getAccessRights("RCVBYENDUSER")%>;
		rcvFromNothingAccess = <%=role.getAccessRights("RCVNOTHING")%>;
		rcvByItemAccess = <%=role.getAccessRights("RCVBYITEM")%>;
		rcvFinalizeAccess = <%=role.getAccessRights("RCVFINALIZE")%>;
		rcvAdjAccess = <%=role.getAccessRights("RCVADJUST")%>;
		rcvReturnAccess = <%=role.getAccessRights("RCVRETURN")%>;
		rcvHistoryAccess = <%=role.getAccessRights("RCVHISTORY")%>;
		quickRcvAccess = <%=role.getAccessRights("QUICKRCV")%>;
	}
	if (salesActive) {
		salesAccess = <%=role.getAccessRights("SALES")%>;
	}
	if (vouchersActive) {
		invoiceAccess = <%=role.getAccessRights("VOUCHERS")%>;
	}
	if (extInvActive || stdInvActive) {
		invAccess = <%=role.getAccessRights("INV")%>;
		disbAccess = <%=role.getAccessRights("DISB")%>;
		returnAccess = <%=role.getAccessRights("REQI")%>;
	}

	if (bomActive) {
		bomAccess = <%=role.getAccessRights("BOM")%>;
	}
	adminAccess  = <%=role.getAccessRights("ADMIN")%>;
	MasterUser = <%=role.getAccessRights("MUI")%>;
	bbsupAccess =  <%=role.getAccessRights("ADMIN")%>;
	catalogItems = <%=role.getAccessRights("CATITEM")%>;

	// ********* reports access *******************
	repReqAccess = <%=role.getAccessRights("REPREQ")%>;
	repRfqAccess = <%=role.getAccessRights("REPRFQ")%>;
	repPoAccess = <%=role.getAccessRights("REPPO")%>;
	repInvAccess = <%=role.getAccessRights("REPINV")%>;
	repAdminAccess = <%=role.getAccessRights("REPADM")%>;
	repRecAccess = <%=role.getAccessRights("REPREC")%>;
	repVchAccess = <%=role.getAccessRights("REPVCH")%>;
	repBdgAccess = <%=role.getAccessRights("REPBDG")%>;
	repVndAccess = <%=role.getAccessRights("REPVEND")%>;
	// ********* end reports access *******************

	//procurement dashboard access
	dashboardAccess = <%=role.getAccessRights("BUYDASH")%>;

	//These options must be populated in this order!
	Array50 = createRequisitionOptions();
	Array51 = createSolicitationOptions();
	Array58 = createSaleOptions();

	var receiptOptions = new Array();
	receiptOptions[1] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivepackages", "Receive Packages", false)%>";
	receiptOptions[2] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "finalizePendingRec", "Finalize Receipts", false)%>";
	receiptOptions[3] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivefromnothing", "Receive From Nothing", false)%>";
	receiptOptions[4] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveitems", "Receive Items", false)%>";
	receiptOptions[5] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "adjustpackages", "Adjust Receipts", false)%>";
	receiptOptions[6] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returns", "Return Received Items", false)%>";
	receiptOptions[7] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveItemHistory", "View History By Item", false)%><!--View Receipt Item History-->";
	receiptOptions[8] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivefrom", "Create New Receipt", false)%>";
	receiptOptions[9] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsePackage", "Browse Package Receipts", false)%>";
	receiptOptions[10]= "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseReceipt", "View History By Receipt", false)%><!--View Receipt History-->";
	receiptOptions[11]= "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseItem", "Browse by Item", false)%>";
	receiptOptions[12]= "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseAccount", "Browse by Account", false)%>";
	receiptOptions[13]= "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseDocument", "Browse by Document", false)%>";
	receiptOptions[14]= "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "quickReceive", "Quick Receiving", false)%>";

	Array52 = createOrderOptions();
	Array60 = createReceiptMenu(receiptOptions,"<%=headerEncoder.encodeForJavaScript(oid)%>", "<%=headerEncoder.encodeForJavaScript(doubleStepReceiving)%>", "<%=headerEncoder.encodeForJavaScript(singlePageReceipt)%>");
	Array61 = createInvoiceOptions();

	var inventoryOptions = new Array();
	inventoryOptions[1] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "newitem", "New Item", false)%>";
	inventoryOptions[2] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseitem", "Browse", false)%>";
	inventoryOptions[3] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "backorder", "Items On Backorder", false)%>";
	inventoryOptions[4] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "formapproval", "Form Approval", false)%>";
	inventoryOptions[5] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "administrative", "Administration", false)%>";
	inventoryOptions[6] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "newreturninv", "New Inventory Request Return", false)%>";
	inventoryOptions[7] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsereturn", "Browse Inventory Returns", false)%>";
	inventoryOptions[8] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "newdisb", "Create New Disbursement", false)%>";
	inventoryOptions[9] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsedisb", "Browse Disbursements", false)%>";
	inventoryOptions[10] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsebom", "Bills of Material", false)%>";
	inventoryOptions[11] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "newreturnotc", "New Over the Counter Return", false)%>";
	Array55 = createInventoryOptions(inventoryOptions);

	var reportOptions = new Array();
	reportOptions[1] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "administrative", "Administrative", false)%>";
	reportOptions[2] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requests", "Requisition", false)%>";
	reportOptions[3] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitation", "Solicitation", false)%>";
	reportOptions[4] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Orders", false)%>";
	reportOptions[5] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipt", "Receipts", false)%>";
	reportOptions[6] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "voucher", "Invoice", false)%>";
	reportOptions[7] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inventory", "Inventory", false)%>";
	reportOptions[8] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budget", "Budget", false)%>";
	reportOptions[9] = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierreports", "Supplier", false)%>";
	//Array57 = createReportOptions(reportOptions);

	Array57 = createReportQueueOptions(reportOptions);

	Array62 = createAssetOptions();

//-->
</SCRIPT>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="handler" value=""/>
<tsa:hidden name="successPage" value=""/>
<tsa:hidden name="failurePage" value="/system/error.jsp"/>
<tsa:hidden name="userId" value="<%=headerEncoder.encodeForHTMLAttribute(uid) %>"/>
<tsa:hidden name="userDepartment" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userLocale" value="<%=user.getLocale()%>"/>
<tsa:hidden name="organizationId" value="<%=oid%>"/>
<tsa:hidden name="mailId" value="<%=headerEncoder.encodeForHTMLAttribute(user.getMailId())%>"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="viewType" value="WIZARD"/>
<tsa:hidden name="language" value="${language}"/>
<tsa:hidden name="userTimeZone" value="<%=user.getTimeZone()%>"/>
<%
	String noMenuOptions = HiltonUtility.ckNull((String)request.getAttribute("noMenuOptions"));
	boolean displayMenuOptions = false;
	if (!noMenuOptions.equals("Y") && authenticated) {
		displayMenuOptions = true;
	}
%>

<div id=pageLoading style="display:block;visibility:visible;">
<table border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%>>
<tr><td width=100% align=center valign=top><br><b>Loading Page... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
</div>

<div id=pageForm style="display:none;visibility:hidden;">

<table border="0" cellpadding="0" cellspacing="0" width=<%=formWidth%>>
<tr>
<%
	String logoFilename = PropertiesManager.getInstance(oid).getProperty("MISC", "LOGOFILENAME", "logo.gif");
	String logoImgPath = "/puridiom/images/" + colorscheme;
	//http://srr.puridiom.com/puridiom/images/logo.jpg
	
	
	boolean colorSchemeLogos = PropertiesManager.getInstance(oid).getProperty("MISC", "COLORSCHEMELOGO", "Y").equals("Y");

	if (!colorSchemeLogos) {
		logoImgPath = "/puridiom" + "/images";
	}
	if (isXpress) {
		logoFilename = "logox.gif";
	}

	if ( oid.equalsIgnoreCase("vse06p") ) {
			//VSE does not want the logo to be a link
%>
	<td width=250px><img src="<%=com.tsa.puridiom.userpreference.UserPreferenceManager.getUserLogo(oid, requestURLPath + "/images", logoFilename)%>"	align="bottom" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "centerhome", "Menu", false)%>" border=0 height=50px></td>
<%	} else {	%>
	<td width=250px>
		<% if (!HiltonUtility.isEmpty(PropertiesManager.getInstance(oid).getProperty("MISC", "LOGOURL", ""))) { %>
		<a href="<%=PropertiesManager.getInstance(oid).getProperty("MISC", "LOGOURL", "http://www.puridiom.com") %>" target="_top">
		<% }%>
		<img src="<%=com.tsa.puridiom.userpreference.UserPreferenceManager.getUserLogo(oid, logoImgPath, logoFilename)%>"	align="bottom" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "centerhome", "Menu", false)%>" border=0 height=50px>
		</a>
	</td>
	<% if (displayEnv.equalsIgnoreCase("Y")) { %>
		<td width=460px valign="middle" align="center" class="mrow"><font size="3"><b><%= AppEnv %></b></font></td>
	<% } %>
<%	}

	if (!HiltonUtility.isEmpty(oid) && displayMenuOptions) {
		if (!orgStatus.equals("02")) {
			Date trialExpirationDate = OrganizationManager.getInstance().getOrganization(oid).getDateExpires();
			if (trialExpirationDate == null) {
				trialExpirationDate = new Date();
			}
			BigDecimal daysToExpire = HiltonUtility.getDateDifference(Dates.getCurrentDate(""), trialExpirationDate);
			if (daysToExpire.compareTo(new BigDecimal(0)) > 0) {
%>
	<td valign="middle" width=* align=center valign=middle class=error>Your FREE Trial Will Expire in <%=daysToExpire%> days!</td>
<%			} else if (trialExpirationDate.equals(new Date())) {%>
	<td valign="middle" width=* align=center valign=middle>Your FREE Trial Expires today!</td>
<%			}
		}
	}	%>
	<td valign="bottom">
	<%	if (displayMenuOptions) {%>
		<table border=0 cellpadding="0" cellspacing="0" width="100%" height="100%">
		<tr><td align=right height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "welcome", "Welcome", false)%> <%=headerEncoder.encodeForHTML(user.getDisplayName())%>!&nbsp;</td></tr>
		<%	if (!HiltonUtility.isEmpty(oid)) {%>
		<tr><td align=right height=18px valign=top><%=OrganizationManager.getInstance().getOrganizationName(oid)%>&nbsp;</td></tr>
		<%	} else {%>
		<tr><td height=18px><br></td></tr>
		<%	}%>
		</table>
	<%	}%>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr class="mrow">
				<th height="16px">&nbsp;</th>
	<%	if (displayMenuOptions) {%>
				<th align="center" width="50px" height="16px"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);" onClick="closeOpenWindows();" target="_parent"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "menu", "Menu", false)%></a></th>
	<%		if (role.getAccessRights("ADMIN") > 0 || role.getAccessRights("CATITEM") > 0 || role.getAccessRights("CAT") > 0) {%>
				<th align="center" valign="middle" width="1px" height="16px" class=separator>|</th>
				<th align="center" width="50px" height="16px"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);" onClick="closeOpenWindows();" target="_parent" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "admin", "Admin", false)%></a></th>
	<%		}
			if (user.getUserType().equals("ADMIN") && isXpress) {%>
				<th align="center" valign="middle" width="1px" height="16px" class=separator>|</th>
				<th align="center" width="75px" height="16px"><a href="javascript: viewMyAccount(); void(0);" onClick="closeOpenWindows();" target="_parent"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "myaccount", "My Account", false)%></a></th>
	<%		}%>
				<th align="center" valign="middle" width="1px" height="16px" class=separator>|</th>
				<th align="center" width="75px" height="16px"><a href="javascript: viewMyProfile(); void(0);" onClick="closeOpenWindows();" target="_parent"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "profile", "My Profile", false)%></a><div id="profileFields" style="visibility: hidden; display:none;"></div></th>
				<th align="center" valign="middle" width="1px" height="16px" class=separator>|</th>
	<%	}
			else
			{ %>
				<th align="center" valign="middle" class=formtype><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "applicationName", "Puridiom 4.0", false)%></td>
<%		} %>
				<th align="center" width="40px" height="16px" nowrap><a href="javascript:window.open('<%=PropertiesManager.getInstance(oid).getProperty("MISC", "HELPURL", "http://www.puridiom.com/helpx") %>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "help", "Help", false)%></a>&nbsp;</th>
	<%	if (displayMenuOptions) {%>
				<th align="center" valign="middle" width="1px" height="16px" class=separator>|</th>
				<th align="center" width=60px height="16px" nowrap><a href="javascript: logOff(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "logoff", "Log Off", false)%></a>&nbsp;</th>
	<%	}%>
			</tr>
		</table>
	</td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width=<%=formWidth%>>
<tr><td class="mnav"><img src="<%=contextPath%>/images/none.gif" border="0" height="1px" width="100%" class="mnav"></td></tr>
</table>

<% if (displayMenuOptions) {%>
<table border="0" cellspacing="0" cellpadding="0" width=<%=formWidth%> height="22px">
	<tr class="mnav">
<%	if (reqsActive && role.getAccessRights("REQ") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu50')" onMouseOver="popUp('Menu50',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisition", "Requisitions", false)%></a></td>
<%	}
		if (rfqsActive && role.getAccessRights("RFQ") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu51')" onMouseOver="popUp('Menu51',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitations", "Solicitations", false)%></a></td>
<%	}
		if (salesActive && role.getAccessRights("SALES") > 0) {%>
		<td nowrap align=center width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu58')" onMouseOver="popUp('Menu58',event)" class=mnav>Surplus</a></td>
<%	}
		if (posActive && role.getAccessRights("PO") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu52')" onMouseOver="popUp('Menu52',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orders", "Orders", false)%></a></td>
<%	}
		if (receivingActive && (role.getAccessRights("RCVBYPKG") >= 3 || role.getAccessRights("RCVBYITEM") >= 3 || role.getAccessRights("RCVBYENDUSER") >= 3 || role.getAccessRights("RCVFINALIZE") >= 3 || (role.getAccessRights("RCVADJUST") >=3 && (role.getAccessRights("RCVBYITEM") >= 3 || role.getAccessRights("RCVBYENDUSER") >= 3))  || role.getAccessRights("RCVRETURN") >= 3 || role.getAccessRights("RCVHISTORY") > 0 || role.getAccessRights("QUICKRCV") > 0)) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu60')" onMouseOver="popUp('Menu60',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receipts", "Receipts", false)%></a></td>
<%	}
		if (vouchersActive && role.getAccessRights("VOUCHERS") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu61')" onMouseOver="popUp('Menu61',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoices", "Invoices", false)%></a></td>
<%	}
		if ((extInvActive || stdInvActive) && role.getAccessRights("INV") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu55')" onMouseOver="popUp('Menu55',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inventory", "Inventory", false)%></a></td>
<%	}
		if (role.getAccessRights("REPORTS") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu57')" onMouseOver="popUp('Menu57',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reports", "Reports", false)%></a></td>
		<!-- <td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu57')" onMouseOver="popUp('Menu57',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reports", "Reports", false)%></a></td> -->
<%	}
		if (assetsActive && role.getAccessRights("ASSETS") > 0) {%>
		<td nowrap align="center" width=85px><a href="javascript: void(0);" onMouseOut="popDown('Menu62')" onMouseOver="popUp('Menu62',event)" class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assets", "Assets", false)%></a></td>
<%	}%>
		<td width=*>&nbsp;</td>
	</tr>
</table>
<% }%>

<div id="menuBarSpacer"><img src="<%=contextPath%>/images/none.gif" border="0" height="20px"></div>
<%@ include file="/menu/navigation_menu.jsp"%>
