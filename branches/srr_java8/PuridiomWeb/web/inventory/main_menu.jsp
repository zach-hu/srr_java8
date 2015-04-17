<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<script language='Javascript1.2' src="<%=contextPath%>/menu/rq_arrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/shortcut_arrays.js"></script>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.BrowseManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<%
	BrowseManager.getInstance().cleanupBrowsesBySession(oid, sid);

	List	recentRequisitions = MenuManager.getRecentRequisitions(oid,uid);
	List	recentOrders = MenuManager.getRecentOrders(oid,uid);
	List	recentSolicitations = MenuManager.getRecentSolicitations(oid,uid);
	List	recentReqItems = MenuManager.getRecentReqItems(oid,uid);
	List	recentReqInvItems = MenuManager.getRecentReqInvItems(oid,uid);
	List	recentOrderItems = MenuManager.getRecentOrderItems(oid,uid);
	List	externalCatalogs = MenuManager.getExternalCatalogs(oid);
	List	whatsNew = MenuManager.getNews(oid);
	List	templateRequisitions = MenuManager.getTemplateRequisitions(oid, uid);
	String fiscalYear = HiltonUtility.getFiscalYear(oid, userTimeZone);
	String procurementDashBoardVisible = PropertiesManager.getInstance(oid).getProperty("MISC", "BUYERDASHBOARD","N");
	String hideSearchCatalogItems = PropertiesManager.getInstance(oid).getProperty("MISC", "HIDESEARCHCATALOG","N");
	String valueMonthReq = PropertiesManager.getInstance(oid).getProperty("MISC", "BarChartReq","N");
	if(HiltonUtility.isEmpty(valueMonthReq))
		valueMonthReq = "0";
	String shipToCode = user.getShipToCode() ;
	String nameUdf1 = user.getNameUdf1() ;
	String deptCode = user.getDepartment() ;
	String showProfile = "N" ;
	if (oid.equals("VSE06P") && (HiltonUtility.isEmpty(shipToCode) || HiltonUtility.isEmpty(nameUdf1) || HiltonUtility.isEmpty(deptCode))) {
		showProfile = "Y" ;
	}
	boolean isCatalogSecurityActive = false;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE","N");

	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);

	String userTZAbbreviation = Dates.getTimeZoneAbbreviation(userTimeZone);
	String viewTypeWizard = "WIZARD";
%>
<script language='Javascript1.2' type="text/javascript">

<!--

	var browseTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browse", "Browse", false)%>";
	var newTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "createnew", "Create New", false)%>";
	var browseMsrTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browsemsr", "Browse MSR", false)%>";
	var newMsrTxt = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "createnewmsr", "Create New MSR", false)%>";

	var requisitionactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionactions", "My Requisition Actions", false)%>";
			requisitionactions += " <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bar-chart-req-" + valueMonthReq + "Months", "", false)%>";
	var invoiceStatusGraph = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceStatusGraphTitle", "Invoice Actions", false)%>";
	var rfqByStatusActions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfqbystatusactions", "My Solicitation Actions", false)%>";
	var myactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "myactions", "My Action Items", false)%>";
	var itemsordered = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemsordered", "My Frequently Ordered Items", false)%>";
	var itemsrequested = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemsrequested", "My Frequently Requested Items", false)%>";
	var recentreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "recentreqs", "My Recent Requisitions", false)%>";
	var recentorders = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "recentorders", "My Recent Orders", false)%>";
	var recentsolicitations = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "recentsolicitations", "My Recent Solicitations", false)%>";
	var inventoryitems = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inventoryitems", "My Frequently Requested Inventory Items", false)%>";
	var receivingactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivingactions", "My Receiving Actions", false)%>";
	var browseoptions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "browseoptions", "My Browse Options", false)%>";
	var invoicecertification = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoicecertification", "Invoice Certification", false)%>";
	var poactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poactions", "My Po Actions", false)%>";
	var novedades = "<table border=0 cellpadding=0 cellspacing=0 class=mnav><tr><td valign=middle align=center width=50px><img src='<%=contextPath%>/images/star.gif' border=0></td><td class=mnav><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "novedades", "What's New?", false)%></td><td align=center width=50px><img src='<%=contextPath%>/images/star.gif' border=0></td></tr></table>";
	var context = "<%=contextPath.substring(1)%>";
	var incompletereqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "incompletereqs", "My Incomplete Requisitions", false)%>";
	var rejectedreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rejectedreqs", "My Rejected Requisitions", false)%>";
	var approvedreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvedreqs", "My Approved Requisitions", false)%>";
	var orderreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderreqs", "My Requisitions on Order", false)%>";
	var pendingapprovalreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pendingapprovalreqs", "Requisitions Waiting My Approval", false)%>";
	var closecancelreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "closecancelreqs", "Close/Cancel Open Requisitions", false)%>";
	var reqpipeline = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqpipeline", "Requisition Pipeline Browse", false)%>";
	var solicitationdueweek = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationdueweek", "Solicitations due this week", false)%>";
	var pendingapprovalrfqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pendingapprovalrfqs", "Solicitations Waiting My Approval", false)%>";
	var procurementDashBoard = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "procurementDashBoard", "Procurement DashBoard", false)%>";
	var itemsearch = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itemsearch", "Search Catalog Items", false)%>";
	var templaterequisitions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "templaterequisitions", "My Template Requisitions", false)%>";
	//var bidsclosedrfqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bidsclosedrfqs", "Solicitations to Award", false)%>";

	var solicitationswaitingresponse = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationswaitingresponse", "Solicitations Waiting My Response", false)%>";
	var assignedreqs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assignedreqs", "Requisitions Assigned to Me", false)%>";
	var poautoclose = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poautoclose", "PO Auto Close", false)%>";
	var forwardedreqs = "Requisitions To Be Priced";
	var incompleteorders = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "incompleteorders", "Purchase Orders that are Incomplete", false)%>";
	var waitingorders = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "waitingorders", "Purchase Orders Waiting My Approval", false)%>";
	var overdueorders = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "overdueorders", "Purchase Orders that are Overdue", false)%>";
	var bo60 = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bo60", "Blanket Orders that Expire within 60 days", false)%>";
	var standardsolicitacionquestions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardsolicitacionquestions", "Standard Solicitation Questions", false)%>";
	var bidboarddocs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bidboarddocs", "Bid Board Standard Documents", false)%>";
	var standardformdocs = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "standardforms", "Standard Form Documents", false)%>";
	var viewprequalifiedsuppliers = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewprequalifiedsuppliers", "Supplier Qualification", false)%>";
	var viewregisteredsuppliers = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewregisteredsuppliers", "View Registered Suppliers", false)%>";
	var buyerassignmentworkload = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerassignmentworkload", "Buyer Assignment Workload", false)%>";

	var procurementworkload = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "procurementworkload", "Procurement Workload", false)%>";

	var buyerworkloadsummary = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerworkloadsummary", "Buyer Workload Summary", false)%>";
	var assetactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetactions", "Asset Actions", false)%>";
	var viewreports = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewreports", "My Report", false)%>";

	var receivepackages = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivepackages", "Receive Packages", false)%>";
	var finalizePendingRec = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "finalizePendingRec", "Finalize Receipts", false)%>";
	var receiveitems = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiveitems", "Receive Items", false)%>";
	var adjustpackages = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "adjustpackages", "Adjust Receipt", false)%>";
	var returns = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returns", "Return Received Items", false)%>";
	var receivehistory = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivehistory", "View Receipt History", false)%>";
	var optionsasset = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "optionsearch", "Option Search", false)%>";

	var assetitemnumber = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetitemnumber", "View Asset By Item Number", false)%>";
	var assetbylocation = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetbylocation", "View Asset By Location", false)%>";
	var assetleaserwdat = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetleaserwdat", "View Lease Renewal due this month", false)%>";
	var assetwarrtyexpr = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetwarrtyexpr", "View Warranties to Expire this month", false)%>";
	var assetunprinted  = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "assetunprinted",  "View Unprinted Assets", false)%>";

	var accountspayableactions = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accountspayableactions", "Accounts Payable Actions", false)%>";
	var approvecheckrequests = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvecheckrequests", "Check Requests Waiting My Approval", false)%>";
	var incompleteinvoices = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "incompleteinvoices", "Invoices Not Yet Submitted", false)%>";
	var invoicespendingapproval = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoicespendingapproval", "Invoices Pending Approval", false)%>";
	var approvedinvoices = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvedinvoices", "Approved Invoices", false)%>";
	var invoiceswaitingmyapproval = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceswaitingmyapproval", "Invoices Waiting My Approval", false)%>";
	var vendorwithoutap =  "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accountspayablevendor", "Vendors Needing AP Reference Number", false)%>";
	var invoicepipeline = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoicepipeline", "Invoice Pipeline Browse", false)%>";

	var printChecks = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printChecks", "Print Checks", false)%>";
	var exportFixedAssets = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "exportFixedAssets", "Export Fixed Assets", false)%>";
	var exportGLTran = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "exportGLTran", "Export GL Transactions", false)%>";

	var schedulereports = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "schedulereports", "Schedule Reports", false)%>";
	var viewreports = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewreports", "View Scheduled Reports", false)%>";
	var viewpublicreports = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "viewpublicreports", "View Public Scheduled Reports", false)%>";

	var request = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Requests", "Requests", false)%>";
	var solicitation = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Solicitations", "Solicitations", false)%>";
	var order = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Order", "Order", false)%>";
	var supplier = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Supplier", "Supplier", false)%>";

	//View Browses Standar Inventory
	var approvedinventoryrequest = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvedinventoryrequest", "Approved Inventory Request", false)%>";
	var	itempendingdisbursement  = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "itempendingdisbursement", "Items Pending Disbursement", false)%>";
	var	backorderinventoryrequisitionbyline  = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "backorderinventoryrequisitionbyline", "Backorder Inventory Requisition by Line", false)%>";

	var reporttoday = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Report_today", "Due today", false)%>";
	var reportweek = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Report_week", "Due this week", false)%>";
	var reportmonth = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Report_month", "Due this month", false)%>";
	var reportquarter = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Report_quarter", "Due this quarter", false)%>";
	var reportyear = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Report_year", "Due this year", false)%>";

  document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/menu/main_menu_arrays.js' type='text/javascript'><\/scr" + "ipt>");
  document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/menu/main_menu_options.js' type='text/javascript'><\/scr" + "ipt>");
//-->
</script>

<tsa:hidden name="fromPage" value="/menu/main_menu.jsp"/>
<tsa:hidden name="tempEmail" value=""/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<% if (oid.equalsIgnoreCase("wpc08p")) { %>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf3" value="1770"/>
<% } %>
<%
	String exportFACompleted = (String) request.getAttribute("exportFACompleted");
	if (HiltonUtility.isEmpty(exportFACompleted))
		exportFACompleted = "N";
%>
<tsa:hidden name="exportFACompleted" value="<%=exportFACompleted%>"/>
<%
	String exportGLTranCompleted = (String) request.getAttribute("exportGLTranCompleted");
	if (HiltonUtility.isEmpty(exportGLTranCompleted))
		exportGLTranCompleted = "N";
%>
<tsa:hidden name="exportGLTranCompleted" value="<%=exportGLTranCompleted%>"/>
<%@ include file="/graph/graph-data.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var questAccess = 0;
	var bbdocsAccess = 0;
	var bbsupAccess = 0;
	var buyerAssignAccess = 0;
	<%	if (oid.equals("CTB06P") || !DictionaryManager.isVisible(oid, "procurementworkload")) { %>
			var procurementWorkloadAcess = 0;
	<% } else { %>
			var procurementWorkloadAcess = 1;
	<% } %>
	var apAccess = <%=role.getAccessRights("APINTER")%>;
	var checkReqAccess = <%=role.getAccessRights("REQK")%>;
	var stdFormAccess = <%=role.getAccessRights("STDFORM")%>;
	var closeOpenReqsAccess = <%=role.getAccessRights("CLOSEOPENREQS")%>;
	var reqPipelineBrowseAccess = <%=role.getAccessRights("REQPIPELINE")%>;
	var printChecksAccess = <%=role.getAccessRights("PRINTCHECKS")%>;
	var exportFixedAssetsAccess = <%=role.getAccessRights("EXPFIXEDASSETS")%>;
	var exportGLTranAccess = <%=role.getAccessRights("EXPGLTRAN")%>;
	var poAutoCloseAccess = <%=role.getAccessRights("AUTOCLOSE")%>;
	var boAccess = <%=role.getAccessRights("POBO")%>;
	var procurementDashBoardVis = '<%=procurementDashBoardVisible%>';

	if (rfqsActive) {
		questAccess = <%=role.getAccessRights("QUEST")%>;
		bbdocsAccess = <%=role.getAccessRights("BBDOCS")%>;
		bbsupAccess = <%=role.getAccessRights("BBSUP")%>;
	}
	if (posActive) {
		buyerAssignAccess = <%=role.getAccessRights("BUYRASSN")%>;
		buyer = <%=user.isABuyer()%>;
	}
  approver = <%=user.isAnApprover()%>;
  vchApprover = <%=user.isVchApprover()%>;
  overrider = <%=user.isAnOverrider()%>;
  owner = "${esapi:encodeForJavaScript(userId)}";
  isABuyer = "<%=user.isABuyer()%>";

  reqInProgress = "<%=DocumentStatus.REQ_INPROGRESS%>";
  reqRejected = "<%=DocumentStatus.REQ_REJECTED%>";
  reqRecalled = "<%=DocumentStatus.REQ_RECALLED%>";
  reqForwarded = "<%=DocumentStatus.REQ_FORWARDED%>";
  reqApproving = "<%=DocumentStatus.REQ_APPROVING%>";
  reqApproved = "<%=DocumentStatus.REQ_APPROVED%>";
  reqAmmended = "<%=DocumentStatus.REQ_AMMENDED%>";
  rfqPriced = "<%=DocumentStatus.RFQ_PRICED%>";
  invInProgress = "<%=DocumentStatus.INV_INPROGRESS%>";
  invBackordered = "<%=DocumentStatus.INV_BACKORDERED%>";
  rfqInProgress = "<%=DocumentStatus.RFQ_INPROGRESS%>";
  rfqOpenSolicitation = "<%=DocumentStatus.RFQ_OPENSOLICITATION%>";
  poInProgress =  "<%=DocumentStatus.PO_INPROGRESS%>";
  poApproving = "<%=DocumentStatus.PO_APPROVING%>";
  poClosed = "<%=DocumentStatus.CLOSED%>";

  //MenuArray0 = createMyRequisitionActions(MenuArray0[0]);
  MenuArray0 = createMyGraphActions(MenuArray0[0], reqGraph);
  MenuArray1 = createMyActionItems(MenuArray1[0]);
  MenuArray2 = createMyRecentOrderedItems(MenuArray2[0]);
  MenuArray3 = createMyRecentRequestedItems(MenuArray3[0]);
  MenuArray4 = createMyRecentRequisitions(MenuArray4[0]);
  MenuArray5 = createMyRecentOrders(MenuArray5[0]);
  MenuArray6 = createMyRecentRequestedInvItems(MenuArray6[0]);
  MenuArray7 = createMyReceivingActions(MenuArray7[0]);
  MenuArray21 = createMyBrowseOptions(MenuArray21[0]);
  MenuArray8 = createMyInvoiceOptions(MenuArray8[0]);
  MenuArray9 = createMyPoGraphActions(MenuArray9[0], poCountGraph);
  <%if(oid.equalsIgnoreCase("b2b"))
  {%>
  	MenuArray10 = createUpcomingB2BEvents(MenuArray10[0]);
   <%}
   else
   {%>
   	//MenuArray10 = createUpcomingEvents(MenuArray10[0]);
//   	MenuArray10 = createWhatsNew(MenuArray10[0]);
   <%}%>
   MenuArray11 = createMyRfqByStatusGraphActions(MenuArray11[0], rfqByStatusGraph);
   MenuArray12 = createMyRecentSolicitations(MenuArray12[0]);
   MenuArray13 = createMyAssetOptions(MenuArray13[0]);
   MenuArray14 = createMyAccountsPayableActions(MenuArray14[0]);
   <% if (role.getAccessRights("REPORTS") > 0) { %>
   MenuArray15 = createMyViewReports(MenuArray15[0]);
   <% } %>
   //MenuArray16 = createTopSuppliersGraph(MenuArray16[0], topSuppliersGraph);
   <% if(!hideSearchCatalogItems.equalsIgnoreCase("Y") ) { %>
   	if (reqAccess > 1)
	{
   		MenuArray17 = createItemSearch(MenuArray17[0]);
   	}
   <% } %>
   MenuArray18 = createMyTemplateRequisitions(MenuArray18[0]);
   MenuArray20 = createMyInvoiceStatusGraphActions(MenuArray20[0], invoiceStatusGraph);

  function createMyRecentRequestedItems(originalArray) {
    var options = new Array();
    var i = 1;
    options[0] = originalArray;

	if (reqAccess > 0) {
<%	int	reqItemsCount = recentReqItems.size();
    if (reqItemsCount > 10) {
      reqItemsCount = 10;
    }
    for (int i=0; i < reqItemsCount; i++) {
      RecentReqItem item = (RecentReqItem) recentReqItems.get(i);
      String desc = HiltonUtility.encodeHtml(item.getDescription());

      String number = item.getComp_id().getItemNumber();
      if (number.trim().length() > 0) {
        number = "#" + number;
      }

      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      String description = desc;

      desc = number + " - " + desc;
%>
      options[i] = new Array("<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;<%=desc%></span>","","<input type=checkbox name=reqItemCkbox value='Y'><input type=hidden name='reqItemNumber' value='<%=item.getComp_id().getItemNumber()%>'/><input type=hidden name='reqCatalogId' value='<%=item.getComp_id().getItemLocation()%>'><input type=hidden name='itemDescription' value=\"<%=description%>\">", "");
      i++;
<%	}
    if (reqItemsCount > 0) {%>
      var moreOptions = "<table border=0 width=100% cellpadding=0 cellspacing=0><tr><td><input type=button value='Request Items' onclick=" + '"' + "requestSelectedItems();" + '"' + "></td><td align=right><a href=\"javascript: moreRequestedItems(); void(0);\"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "moreitems", "More Items", false)%></a></td></tr></table>";
      options[i] = new Array("","", moreOptions, "", "");
<%	}%>
	}
    return options;
  }

  function createMyRecentRequestedInvItems(originalArray) {
    var options = new Array();
    var i = 1;
    var desc = "";
    options[0] = originalArray;

	if (reqAccess > 0 && (extInvActive || stdInvActive)) {
<%	reqItemsCount = recentReqInvItems.size();
    if (reqItemsCount > 10) {
      reqItemsCount = 10;
    }
    for (int i=0; i < reqItemsCount; i++) {
      RecentReqItem item = (RecentReqItem) recentReqInvItems.get(i);
      String	desc = HiltonUtility.encodeHtml(item.getDescription());
      String number = item.getComp_id().getItemNumber();
      if (number.trim().length() > 0) {
        number = "#" + number;
      }
      desc = number + " - " + desc;

      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");
%>
      options[i] = new Array("<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;<%=desc%></span>","","<input type=checkbox name=reqInvItemCkbox value=Y><input type=hidden name='reqInvItemNumber' value='<%=item.getComp_id().getItemNumber()%>'><input type=hidden name='reqInvitemLocation' value='<%=item.getComp_id().getItemLocation()%>'>", "");
      i++;
<%	}
    if (reqItemsCount > 0) {%>
      options[i] = new Array("","","<input type=button value='Request Inventory Items' onclick=" + '"' + "requestSelectedInvItems();" + '"' + ">", "");
<%	}%>
	}
    return options;
  }

  function createMyRecentOrderedItems(originalArray) {
    var options = new Array();
    var i = 1;
    options[0] = originalArray;

	if (poAccess > 0) {
<%	int	poItemsCount = recentOrderItems.size();
    if (poItemsCount > 10) {
      poItemsCount = 10;
    }
    for (int i=0; i < poItemsCount; i++) {
      RecentOrderItem item = (RecentOrderItem) recentOrderItems.get(i);
      String desc = HiltonUtility.encodeHtml(item.getDescription());
      String number = item.getComp_id().getItemNumber();

      if (number.trim().length() > 0) {
        number = "#" + number;
      }

      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      String description = desc;
      desc = number + " - " + desc;
%>
      options[i] = new Array("<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:195px; position: absolute;'>&nbsp;<%=desc%></span>","","<input type=checkbox name=orderItemCkbox value=Y><input type=hidden name='itemNumber' value='<%=item.getComp_id().getItemNumber()%>'><input type=hidden name='itemSource' value='<%=item.getItemSource()%>'><input type=hidden name='itemLocation' value='<%=item.getComp_id().getItemLocation()%>'><input type=hidden name='itemDescription' value=\"<%=description%>\">", "");
      i++;
<%	}
    if (poItemsCount > 0) {%>
      options[i] = new Array("","","<input type=button value='Order Items' onclick=" + '"' + "orderSelectedItems();" + '"' + ">", "");
<%	}%>
	}
    return options;
  }

  function createMyRecentRequisitions(originalArray) {
    var options = new Array();
    options[0] = originalArray;

	if (reqAccess > 0) {
<%	int	reqCount = recentRequisitions.size();
    if (reqCount > 10) {
      reqCount = 10;
    }
    for (int i=0; i < reqCount; i++) {
      RequisitionHeader req = (RequisitionHeader) recentRequisitions.get(i);
      String	desc = HiltonUtility.encodeHtml(req.getInternalComments());
      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      if (!HiltonUtility.isEmpty(desc)) {
      	desc = " - " + desc;
      }%>
    options[<%=i+1%>] = new Array("<%=headerEncoder.encodeForJavaScript(req.getRequisitionNumber())%>", "javascript: openReq('<%=req.getIcReqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow:hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(req.getStatus())%>)<%=desc%></span>", "", "popUp('Menu8<%=i%>', event);", "popDown('Menu8<%=i%>');");
	<% if(oid.equalsIgnoreCase("vse06p")) {%>
		Array8<%=i%> = createReqOptionsMenu(Array80[0], '<%=req.getIcReqHeader()%>', '<%=headerEncoder.encodeForJavaScript(req.getRequisitionNumber())%>', '<%=req.getRequisitionType()%>', '<%=req.getStatus()%>', '<%=req.getFiscalYear()%>');
	<% } else {%>
		Array8<%=i%> = createReqOptionsMenu(Array80[0], '<%=req.getIcReqHeader()%>', '<%=headerEncoder.encodeForJavaScript(req.getRequisitionNumber())%>', '<%=req.getRequisitionType()%>', '<%=req.getStatus()%>');
	<%}%>
    i++;
<%	}%>
	}
    return options;
  }

  function createMyRecentOrders(originalArray) {
    var options = new Array();
    options[0] = originalArray;

	if (poAccess > 0) {
<%	int	poCount = recentOrders.size();
    if (poCount > 10) {
      poCount = 10;
    }
    for (int i=0; i < poCount; i++) {
      PoHeader poHeader = (PoHeader) recentOrders.get(i);
      String	desc = HiltonUtility.encodeHtml(poHeader.getInternalComments());
      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      if (!HiltonUtility.isEmpty(desc)) {
      	desc = " - " + desc;
      }
      String poNumber = poHeader.getPoNumber();
      if (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0) {
      	poNumber = poNumber + " Rev " + String.valueOf(poHeader.getRevisionNumber());
      }
      if (poHeader.getReleaseNumber().compareTo(new BigDecimal(0)) > 0) {
      	poNumber = poNumber + " Rel " + String.valueOf(poHeader.getReleaseNumber());
      }
      %>
      options[<%=i+1%>] = new Array("<%=poNumber%>", "javascript: openOrder('<%=poHeader.getIcPoHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:85%; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(poHeader.getStatus())%>)<%=desc%></span>", "", "popUp('Menu51<%=i%>', event);", "popDown('Menu51<%=i%>');");
      Array51<%=i%> = createPoOptionsMenu(Array510[0], '<%=poHeader.getIcPoHeader()%>', '<%=poHeader.getPoNumber()%>', '<%=poHeader.getPoType()%>', '<%=poHeader.getStatus()%>');
      i++;
<%	}%>
	}
    return options;
  }

  function createMyRecentSolicitations(originalArray) {
    var options = new Array();
    options[0] = originalArray;

	if (rfqAccess > 0) {
<%	int	rfqCount = recentSolicitations.size();
    if (rfqCount > 10) {
      rfqCount = 10;
    }
    for (int i=0; i < rfqCount; i++) {
      RfqHeader rfqHeader = (RfqHeader) recentSolicitations.get(i);
      String	desc = HiltonUtility.encodeHtml(rfqHeader.getRfqDescription());
      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      if (!HiltonUtility.isEmpty(desc)) {
      	desc = " - " + desc;
      }
      String rfqNumber = rfqHeader.getRfqNumber();
      if (!HiltonUtility.isEmpty(rfqHeader.getRfqAmendment()) && !rfqHeader.getRfqAmendment().equals("0000") && !rfqHeader.getRfqAmendment().equals("0")) {
      	rfqNumber = rfqNumber + " - " + String.valueOf(rfqHeader.getRfqAmendment());
      }
      %>
      options[<%=i+1%>] = new Array("<%=rfqNumber%>", "javascript: openRfq('<%=rfqHeader.getIcRfqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;(<%=DocumentStatus.toString(rfqHeader.getStatus())%>)<%=desc%></span>", "", "popUp('Menu61<%=i%>', event);", "popDown('Menu61<%=i%>');");
      Array61<%=i%> = createRfqOptionsMenu(Array610[0], '<%=rfqHeader.getIcRfqHeader()%>', '<%=rfqHeader.getRfqNumber()%>', '<%=rfqHeader.getRfqType()%>');
      i++;
<%	}%>
	}
    return options;
  }

  function createWhatsNew(originalArray) {
    var options = new Array();
    var i = 1;
    options[0] = originalArray;

<%
	int	newsCount = whatsNew.size();
    for (int i=0; i < newsCount; i++) {
      News news = (News) whatsNew.get(i);
%>
      options[i] = new Array("<span class='<%=news.getNewsFont()%>'><%=news.getNewsText()%></span>","<%=news.getNewsLink()%>", "<img src='/" + context + "/images/<%=news.getNewsImage()%>'>", "", "<%=news.getNewsAltTag()%>", "");
      i++;
<%	}%>
    return options;
  }

  function createMyViewReports(originalArray)
  {
  	var options = new Array();
    var i = 1;

    options[0] = originalArray;
    <% if (role.getAccessRights("REPORTS") > 2) { %>
	options[i] = new Array(schedulereports,"javascript: void(0);","<span class=raquo>&raquo;</span>","","","popUp('Menu90', event);","popDown('Menu90');");
	Array90 = createRQMenu(Array90[0], 1)
	i++;
	<% } %>
    options[i] = new Array(viewreports,"javascript:viewReportQueueScheduled();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Report Queue Scheduled.");
    i++;
    //options[i] = new Array(viewreports,"javascript: void(0);","<span class=raquo>&raquo;</span>","","","popUp('Menu91',event);","popDown('Menu91');");
    //Array91 = createRQMenu(Array91[0], 2)
	options[i] = new Array(viewpublicreports,"javascript:viewReportQueuePublicScheduled();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Public Report Queue Scheduled.");
	i++;

    return options;
  }

  function createItemSearch(originalArray) {
    var options = new Array();
    var i = 1;
    var desc = "";
    options[0] = originalArray;

    options[i] = new Array("","javascript:viewReportQueueScheduled();void(0);","<input type='text' name='as_keywords' value='' size='28' onkeypress='javascript: if (event.keyCode == 13) searchCatalogItems();'>", "<td><img src='/" + context + "/images/button_search_sm.gif' class='button' onclick='searchCatalogItems();' onMouseOver='document.body.style.cursor=" + '"' + "hand" + '"' + "' onMouseOut='document.body.style.cursor=" + '"' + "default" + '"' + "'></td>");
    i++;

	return options;
  }

  function createMyTemplateRequisitions(originalArray) {
    var options = new Array();
    options[0] = originalArray;

	if (reqAccess > 0) {
<%	int	templateCount = templateRequisitions.size();
    if (templateCount > 10) {
      templateCount = 10;
    }
    for (int i=0; i < templateCount; i++) {
      RequisitionHeader req = (RequisitionHeader) templateRequisitions.get(i);
      String	desc = HiltonUtility.encodeHtml(req.getInternalComments());
      desc = desc.replaceAll("\r\n", " ");
      desc = desc.replaceAll("\r", " ");
      desc = desc.replaceAll("\n", " ");

      if (!HiltonUtility.isEmpty(desc)) {
      	desc = " - " + desc;
      }%>
      options[<%=i+1%>] = new Array("<%=headerEncoder.encodeForJavaScript(req.getRequisitionNumber())%>", "javascript: openReq('<%=req.getIcReqHeader()%>'); void(0);","<span class=raquo>&raquo;</span>", "<span style='text-overflow: ellipsis; overflow:hidden; white-space: nowrap; width:300px; position: absolute;'>&nbsp;<%=desc%></span>", "", "popUp('Menu80<%=i%>', event);", "popDown('Menu80<%=i%>');");
	  Array80<%=i%> = createTemplateReqOptionsMenu(Array800[0], '<%=req.getIcReqHeader()%>', '<%=headerEncoder.encodeForJavaScript(req.getRequisitionNumber())%>', '<%=req.getRequisitionType()%>', '<%=req.getOwner()%>');
      i++;
<%	}%>
	}
    return options;
  }
//-->
</script>

<tsa:hidden name="noMenuOptions" value="N"/>

<div><img src="<%=contextPath%>/images/none.gif" border="0" height="5px"></div>

<table border="0" cellspacing="0" cellpadding="2" width=<%=formEntryWidth%>>
<tr>
  <td width="10px">&nbsp;</td>
  <td valign="top" align=center>
    <table border="0" cellpadding="0" cellspacing="0" width="108px" height="95px" style="background-image: url('<%=contextPath%>/images/desk_calendar.gif');background-repeat:no-repeat;">
    <tr>
      <td width="6px"><img src="<%=contextPath%>/images/none.gif" border="0" width="6px" height="1px"></td>
      <td valign="top" width="44px">
        <div><img src="<%=contextPath%>/images/none.gif" border="0" height=7px></div>
        <table border="0" cellpadding=0 cellspacing="0" height=14px>
        <tr>
          <td valign="bottom" height="14px" class="menuDate"><%=HiltonUtility.getFormattedDate(d_today, oid, "d")%></td>
        </tr>
        </table>
      </td>
      <td><img src="<%=contextPath%>/images/none.gif" border="0" width="16px" height="1px"></td>
      <td valign="top" width="56px" align="right">
        <img src="<%=contextPath%>/images/none.gif" border="0" height=13px>
        <table border="0" cellpadding="0" cellspacing="0">
        <tr><td valign="bottom" class="smallText"><%=HiltonUtility.getFormattedDate(d_today, oid, "MMMMM")%></td></tr>
        </table>
        <div><img src="<%=contextPath%>/images/none.gif" border="0" height=1px></div>
        <table border="0" cellpadding="0" cellspacing="0" height=9px>
        <tr><td valign="top" class="smallText" height=10px><%=HiltonUtility.getFormattedDate(d_today, oid, "yyyy")%></td></tr>
        </table>
        <div><img src="<%=contextPath%>/images/none.gif" border="0" height=21px></div>
        <table border="0" cellpadding="0" cellspacing="0" height="10px">
        <tr><td valign="top" class="smallText" height="10px"><%=HiltonUtility.getFormattedDate(d_today, oid, "EEEEE")%></td></tr>
        </table>
      </td>
      <td><img src="<%=contextPath%>/images/none.gif" border="0" width="13px" height="1px"></td>
    </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="108px">
    <tr><td align=right><a href="javascript: doSubmit('user/regional_settings.jsp', 'DoNothing'); void(0);"><%=HiltonUtility.getFormattedDate(d_today, oid, "EEE, MMM d, yyyy h:mm a")%> <%=userTZAbbreviation%></a></td></tr>
	</table>
  </td>
  <td width=* valign="middle" align="center">
      <table border="0" cellpadding="2" cellspacing="0" width="100%" align="center">
    	<tr><td valign=top colspan="3"><img src="<%=contextPath%>/images/pwac.gif" border="0"></td></tr>
    	<tr>
		<td align=center>
			<table border="0" cellpadding="2" cellspacing="0" width="100%">
			<!--tr>
				<td width=100%>
					<table border=0 cellpadding=2 cellspacing=0 width=100%>
					<tr>
						<td width=25px valign=middle align=center><img src='<%=contextPath%>/images/star.gif' border=0></td>
						<td width=100px align=center><b><u><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "novedades", "What's New?", false)%></u></b></td>
						<td width=25px valign=middle align=center><img src='<%=contextPath%>/images/star.gif' border=0></td>
						<td width=*>&nbsp;</td>
					</tr>
					</table>
				</td>
			</tr-->
<%
	for (int i=0; i < whatsNew.size(); i++) {
		News news = (News) whatsNew.get(i);
%>
	      		<tr>
				<td align=center>
<%		if (!HiltonUtility.isEmpty(news.getNewsImage())) {%>
					<img src='<%=contextPath%>/images/<%=news.getNewsImage()%>'>
<%		}%>
				</td>
				<td>
<%		if (!HiltonUtility.isEmpty(news.getNewsLink())) {%>
			<span class='<%=news.getNewsFont()%>'><a href="<%=news.getNewsLink()%>" alt="<%=news.getNewsAltTag()%>"><%=news.getNewsText()%></a></font>
<%		} else {%>
			<span class='<%=news.getNewsFont()%>'><%=news.getNewsText()%></font>
<%		}%>
				</td>
			</tr>
<%	}%>
			</table>
		</td>
	</tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<table border="0" cellspacing="0" cellpadding="2" width=<%=formEntryWidth%>>
<tr>
  <td>&nbsp;</td>
  <td width="62%" valign="top" align="center" id="leftMenu">&nbsp;</td>
  <td>&nbsp;</td>
  <td width="34%" valign="top" align="center" id="rightMenu">&nbsp;</td>
  <td>&nbsp;</td>
</tr>
</table>
<!-- <a href="javascript: dashboard(); void(0);" class="formType">dashboard</a> -->
<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;

  var browser = browserCheck();
  var userId = '${esapi:encodeForJavaScript(userId)}';
  var showProf = '<%=showProfile%>' ;
  if (showProf == "Y") {
  	frm.noMenuOptions.value = "Y";
  	viewMyProfile();
  }
  hideArea("menuBarSpacer");
  setNavCookie("/menu/main_menu.jsp", "DoNothing", "Menu");
  hideArea("navTable");

<%	SimpleDateFormat sqlFormatter = new SimpleDateFormat ("yyyy-MM-dd");
  String	s_begin	= new String();
  String	s_end	= new String();
  String	s_exp	= new String();

  String	s_end_M	= new String();
  String	s_end_Y	= new String();

  String	s_beginVR	= new String();
  String	s_endVR	= new String();
  String	s_beginM	= new String();
  String	s_endM	= new String();
  String	s_now = Dates.today(userDateFormat, userTimeZone);

  Calendar rightNow = Calendar.getInstance();
  int	i_tofirstday = d_today.getDay() - 1;
  int	i_tolastday  = 5 - d_today.getDay();

  // CODE FOR REPORT QUEUE DATES

  Date 	d_oneMoreDay		= new Date(d_today.getYear(), d_today.getMonth(), d_today.getDate()+1);
  Date 	d_oneMoreWeek		= new Date(d_today.getYear(), d_today.getMonth(), d_today.getDate()+7);
  Date 	d_oneMoreMonth		= new Date(d_today.getYear(), d_today.getMonth()+1, d_today.getDate());
  Date 	d_oneMoreQuarter	= new Date(d_today.getYear(), d_today.getMonth()+3, d_today.getDate());
  Date 	d_oneMoreYear		= new Date(d_today.getYear()+1, d_today.getMonth(), d_today.getDate());

  String s_dateBegin		= HiltonUtility.getFormattedDate(d_today, oid, userDateFormat);
  String s_oneMoreDay		= HiltonUtility.getFormattedDate(d_oneMoreDay, oid, userDateFormat);
  String s_oneMoreWeek		= HiltonUtility.getFormattedDate(d_oneMoreWeek, oid, userDateFormat);
  String s_oneMoreMonth		= HiltonUtility.getFormattedDate(d_oneMoreMonth, oid, userDateFormat);
  String s_oneMoreQuarter	= HiltonUtility.getFormattedDate(d_oneMoreQuarter, oid, userDateFormat);
  String s_oneMoreYear		= HiltonUtility.getFormattedDate(d_oneMoreYear, oid, userDateFormat);

  rightNow.add(rightNow.DAY_OF_MONTH,  - i_tofirstday);
  s_begin = sqlFormatter.format(rightNow.getTime());

  rightNow = Calendar.getInstance();
  rightNow.add(rightNow.DAY_OF_MONTH, i_tolastday);
  s_end	= sqlFormatter.format(rightNow.getTime());

  rightNow.add(rightNow.DAY_OF_MONTH, 60);
  d_today = rightNow.getTime();
  s_exp = sqlFormatter.format(d_today);
%>
  var dayBegin = '<%=s_begin%>';
  var dayEnd = '<%=s_end%>';
  var dayNow = '<%=s_now%>';
  var dayExp = '<%=s_exp%>';
  var status = "";

  function changeDisplayArea(img, areaName) {
    var imgName = img.src;

    if (imgName.indexOf("<%=contextPath%>/images/arrow_minimize_blue.gif") >= 0) {
      hideMenuArea(areaName);
      img.src = "<%=contextPath%>/images/arrow_maximize_blue.gif";
    }
    else {
      displayMenuArea(areaName);
      img.src = "<%=contextPath%>/images/arrow_minimize_blue.gif";
    }
  }

  function viewReqsStatus(status)
  {
      setOriginalFilter("RequisitionHeader_status", "=", status);
      browse('myrequisitions');
  }

  function viewRfqsByStatus(status)
  {
      setOriginalFilter("RfqHeader_status", "=", status);
      //setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
      setOriginalFilter("RfqHeader_fiscalYear", "=", "<%=fiscalYear%>");
      browse('myrfqs');
  }

  function viewReqs(type) {
    if(type=="APPROVAL") {
      browse('req-approval-list');
      return;
    }
    else if(type=="INCOMPLETE") {
      status = '<%=DocumentStatus.REQ_INPROGRESS%>';
      setOriginalFilter("RequisitionHeader_status", "=", status);
      setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
    }
    else if(type=="REJECTED"){
      status = '<%=DocumentStatus.REQ_REJECTED%>';
      setOriginalFilter("RequisitionHeader_status", "=", status);
      setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
    }
    else if(type=="CLOSED"){
       	doSubmit("/browse/browse_auto_close.jsp");
      	return;
    }
    else if(type=="APPROVED"){
      status = '<%=DocumentStatus.REQ_APPROVED%>';
      setOriginalFilter("RequisitionHeader_status", "=", status);
      setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
    }
    else if(type=="ONORDER"){
      status = '<%=DocumentStatus.PO_INPROGRESS%>';
      setOriginalFilter("RequisitionHeader_status", "=", status);
      setOriginalFilter("RequisitionHeader_requisitionerCode", "=", userId);
    }
    else if(type=="ASSIGNED"){
<%      if (PropertiesManager.getInstance(oid).getProperty("ASSIGNMENT", "AssignByLine", "N").equalsIgnoreCase("Y") && !oid.equalsIgnoreCase("MSG07P")) {%>
      	browse('req-assigned-by-line');
      	return;
<%	} else if (oid.equalsIgnoreCase("test")) { %>
			setOriginalFilter("RequisitionHeader_status", ">=", "<%=DocumentStatus.REQ_APPROVED%>");
    	    setOriginalFilter("RequisitionHeader_status", "<=", "<%=DocumentStatus.PO_INPROGRESS%>");
	        setOriginalFilter("RequisitionHeader_assignedBuyer", "=", userId);
	        browse('print-assigned-reqs');
	        return;
<%	} else { %>
        setOriginalFilter("RequisitionHeader_status", ">=", "<%=DocumentStatus.REQ_APPROVED%>");
        setOriginalFilter("RequisitionHeader_status", "<=", "<%=DocumentStatus.PO_INPROGRESS%>");
        setOriginalFilter("RequisitionHeader_assignedBuyer", "=", userId);

       <% if (PropertiesManager.getInstance(oid).getProperty("ASSIGNMENT", "BATCHPRINT", "N").equalsIgnoreCase("Y")) {
    	%>
        browse('requisitionheader-assigned');
        return;
<%       }
	   } %>
    }
    else if (type == "FORWARDED"){
    	status = '<%=DocumentStatus.REQ_FORWARDED%>';
    	setOriginalFilter("RequisitionHeader_status", "=", status);
    	setOriginalFilter("RequisitionHeader_requisitionType", "=", "N");
        setOriginalFilter("RequisitionHeader_assignedBuyer", "=", userId);
    }
    else if (type=="CHECK") {
      	browse('req-check-approval-list');
      	return;
    }
    else if (type == "OPEN")
    {
    	browseFilter('myopenreqs');
    	return;
    }
    browse('requisitionheader');
  }

  function graphOrdersBrowse(type)
  {
      setOriginalFilter("PoHeader_poType", "=", type);
      setOriginalFilter("PoHeader_fiscalYear", "=", "<%=fiscalYear%>");
      browse('mypos');
  }

  function changePreferences() {
    unavailable();
  }

  function changePassword() {
    doSubmit('user/chg_pswd.jsp', 'DoNothing');
  }

  function changeSignaturePassword() {
    doSubmit('user/chg_signature_pswd.jsp', 'DoNothing');
  }

  function dashboard() {
    doSubmit('graph/dashboard.jsp', 'DoNothing');
  }



  function viewReportQueueScheduled() {
  	<% if (PropertiesManager.getInstance(oid).getProperty("REPORT OPTIONS", "ONCE FREQUENCY", "Y").equalsIgnoreCase("N")) { %>
  	setOriginalFilter("ReportQueue_frequency", "<>", "O");
  	<% } %>
	browse('report_queue');
  }

  function viewReportQueuePublicScheduled() {
  	<% if (PropertiesManager.getInstance(oid).getProperty("REPORT OPTIONS", "ONCE FREQUENCY", "Y").equalsIgnoreCase("N")) { %>
  	setOriginalFilter("ReportQueue_frequency", "<>", "O");
  	<% } %>
	browse('report_queue_public');
  }

  function viewReportQueueDueThisToday(){
 	setAdvancedFilter("ReportQueue_nextRun", ">=", '<%=s_dateBegin%>', "AND", "Y", "ASC");
	setOriginalFilter("ReportQueue_nextRun", "<=", '<%=s_dateBegin%>');
	setAdvancedFilter("ReportQueue_owner", "=", userId, "OR", "Y", "N");
	browse('report_queue');
  }

  function viewReportQueueDueThisWeek(){
 	setAdvancedFilter("ReportQueue_nextRun", ">=", '<%=s_dateBegin%>', "AND", "Y", "ASC");
	setOriginalFilter("ReportQueue_nextRun", "<=", '<%=s_oneMoreWeek%>');
	setAdvancedFilter("ReportQueue_owner", "=", userId, "OR", "Y", "N");
	browse('report_queue');
  }

  function viewReportQueueDueThisMonth(){
 	setAdvancedFilter("ReportQueue_nextRun", ">=", '<%=s_dateBegin%>', "AND", "Y", "ASC");
	setOriginalFilter("ReportQueue_nextRun", "<=", '<%=s_oneMoreMonth%>');
	setAdvancedFilter("ReportQueue_owner", "=", userId, "OR", "Y", "N");
	browse('report_queue');
  }

 function viewReportQueueDueThisQuarter(){
	setAdvancedFilter("ReportQueue_nextRun", ">=", '<%=s_dateBegin%>', "AND", "Y", "ASC");
	setOriginalFilter("ReportQueue_nextRun", "<=", '<%=s_oneMoreQuarter%>');
	setAdvancedFilter("ReportQueue_owner", "=", userId, "OR", "Y", "N");
	browse('report_queue');
  }

function viewReportQueueDueThisYear(){
 	setAdvancedFilter("ReportQueue_nextRun", ">=", '<%=s_dateBegin%>', "AND", "Y", "ASC");
	setOriginalFilter("ReportQueue_nextRun", "<=", '<%=s_oneMoreYear%>');
	setAdvancedFilter("ReportQueue_owner", "=", userId, "OR", "Y", "N");
	browse('report_queue');
  }

  function viewRfqsDueThisWeek() {
	setAdvancedFilter("RfqHeader_dueDate", ">=", dayBegin, "AND", "Y", "ASC");
	setOriginalFilter("RfqHeader_dueDate", "<=", dayEnd);
	setAdvancedFilter("RfqHeader_owner", "=", userId, "OR", "Y", "N");
	setOriginalFilter("RfqHeader_buyer", "=", userId);

    browse('rfqheader');
  }

  function viewOrders(type) {
    var newInputField;

    if (type=="INCOMPLETE") {
		setOriginalFilter("PoHeader_status", "=", "<%=DocumentStatus.PO_INPROGRESS%>");
		setAdvancedFilter("PoHeader_owner", "=", userId, "OR", "Y", "N");
		setOriginalFilter("PoHeader_buyerCode", "=", userId);
    }
    else if(type=="APPROVAL"){
      setOriginalFilter("ApprovalLog_callForward", "=", userId);
      browse('po-approval-list');
      return;
    }
    else if (type=="OVERDUE") {
		setOriginalFilter("PoHeader_requiredDate", "<", dayNow);
		setOriginalFilter("PoHeader_status", ">", "<%=DocumentStatus.PO_APPROVING%>");
		setOriginalFilter("PoHeader_status", "<", "<%=DocumentStatus.RCV_RECEIVED%>");
		setAdvancedFilter("PoHeader_owner", "=", userId, "OR", "Y", "N");
		setOriginalFilter("PoHeader_buyerCode", "=", userId);
    }
    else if (type=="BOEXPIRE") {
		setAdvancedFilter("PoHeader_expirationDate", ">=", dayNow, "AND", "Y", "ASC");
		setOriginalFilter("PoHeader_expirationDate", "<=", dayExp);
		setOriginalFilter("PoHeader_poType", "=", "BO");
		setAdvancedFilter("PoHeader_owner", "=", userId, "OR", "Y", "N");
		setOriginalFilter("PoHeader_buyerCode", "=", userId);
    }
    else {
		setOriginalFilter("PoHeader_poType", "=", type);
		setOriginalFilter("PoHeader_owner", "=", userId);
		setAdvancedFilter("PoHeader_buyerCode", "=", userId, "OR", "Y", "N");
	}

    browse('poheader');
  }

  function viewStandardQuestions() {
    frm.browseName.value = "stdquestion";
    doSubmit('/rfq/rfq_std_questions.jsp', 'BrowseRetrieve');
  }

  function viewStandardDocuments() {
    doSubmit('/admin/documents/std_documents.jsp','StdDocumentRetrieveAll');
  }

  function viewPrequalifiedSuppliers() {
    frm.browseName.value = "vendor-register";
    doSubmit("admin/supplier/prequalify_suppliers.jsp", "BrowseRetrieve");
  }

  function viewRegisteredSuppliers() {
    browse('vendor-register-all');
  }

  function createReq() {
    unavailable();
    //doSubmit('req/req_action.jsp?rq_action=create&type=P', 'CreateRequisition');
  }

  function openReq(ic) {
    var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";
    doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
  }

  function openOrder(ic) {
    var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";
    doSubmit('/orders/po_review.jsp','PoRetrieve');
  }

  function openRfq(ic) {
    var newInputField = "<input type='hidden' name='RfqHeader_icRfqHeader' value='" + ic + "'>";
    setHiddenFields(newInputField);
    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";
    
    doSubmit('/rfq/rfq_review.jsp','RfqRetrieve');
  }

  function requestSelectedItems() {
    var lookupHandler = "CatalogItemLookup";
    var pg = "/requests/req_review.jsp";
    var handlers = "RequisitionCreate;" + lookupHandler + ";RequisitionRetrieve";
    var itemElements = frm.elements.item("reqItemCkbox");
    var hiddenFields = "";

    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";

    for (var i=0; i < itemElements.length; i++) {
      var itemSelected = itemElements(i).checked;

      if (itemSelected) {
        var catalogId = frm.elements.item("reqCatalogId")(i).value;
        var itemNumber = frm.elements.item("reqItemNumber")(i).value;
        var itemDescription = frm.elements.item("itemDescription")(i).value;
        itemDescription = itemDescription.replace(/\"/g,'&quot;');

        hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + catalogId + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"RecentItem_description\" value=\"" + itemDescription + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"1\">";
      }
    }

    hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"P\">";
    hiddenFields = hiddenFields + "<input type=hidden name=\"formtype\" value=\"REQ\">";

    setHiddenFields(hiddenFields);

    doSubmit(pg, handlers);
  }

  function requestSelectedInvItems() {
    var lookupHandler = "InvItemLookup";
    var pg = "/requests/req_review.jsp";
    var handlers = "RequisitionCreate;" + lookupHandler + ";RequisitionRetrieve";
    var itemElements = frm.elements.item("reqInvItemCkbox");
    var hiddenFields = "";
    var reqtype = "S";
    var orgId = "<%=oid%>";

    if (orgId == "BSC04P")
    {
    	reqtype = "P";
    }
    
    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";

    for (var i=0; i < itemElements.length; i++) {
      var itemSelected = itemElements(i).checked;

      if (itemSelected) {
        var itemLocation = frm.elements.item("reqInvItemLocation")(i).value;
        var itemNumber = frm.elements.item("reqInvItemNumber")(i).value;

        hiddenFields = hiddenFields + "<input type=hidden name=\"InvLocation_itemLocation\" value=\"" + itemLocation + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"InvItem_itemNumber\" value=\"" + itemNumber + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"1\">";
      }
    }

    hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"" + reqtype + "\">";
    hiddenFields = hiddenFields + "<input type=hidden name=\"formtype\" value=\"REQ\">";

    setHiddenFields(hiddenFields);

    doSubmit(pg, handlers);
  }

  function orderSelectedItems() {
    var lookupHandler = "CatalogItemLookup";
    var pg = "/orders/po_review.jsp";
    var handlers = "PoCreate;" + lookupHandler + ";PoRetrieve";
    var itemElements = frm.elements.item("orderItemCkbox");
    var hiddenFields = "";

    frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";
    
    for (var i=0; i < itemElements.length; i++) {
      var itemSelected = itemElements(i).checked;

      if (itemSelected) {
        var itemSource = frm.elements.item("itemSource")(i).value;
        var itemLocation = frm.elements.item("itemLocation")(i).value;
        var itemNumber = frm.elements.item("itemNumber")(i).value;
        var itemDescription = frm.elements.item("itemDescription")(i).value;
        itemDescription = itemDescription.replace(/\"/g,'&quot;');

        hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_catalogId\" value=\"" + itemLocation + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"CatalogItem_itemNumber\" value=\"" + itemNumber + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"RecentItem_description\" value=\"" + itemDescription + "\">";
        hiddenFields = hiddenFields + "<input type=hidden name=\"quantity\" value=\"1\">";
      }
    }

    hiddenFields = hiddenFields + "<input type=hidden name=\"PoHeader_poType\" value=\"PO\">";
    hiddenFields = hiddenFields + "<input type=hidden name=\"formtype\" value=\"PO\">";

    setHiddenFields(hiddenFields);

    doSubmit(pg, handlers);
  }

  function viewBuyerAssignment() {
<%	if (PropertiesManager.getInstance(oid).getProperty("ASSIGNMENT", "AssignByLine", "N").equalsIgnoreCase("Y")) {%>
    frm.browseName.value = "buyer-assignment-wkld-by-line";
    setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
    setAdvancedFilter("RequisitionLine_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
<%	} else {%>
    frm.browseName.value = "buyer-assignment-wkld";
    setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "UNASSIGNED", "OR", "Y", "N");
    setAdvancedFilter("RequisitionHeader_assignedBuyer", "=", "PURCHASING", "", "Y", "N");
<%	}%>
    setHiddenFields("<input type=hidden name=\"assignFrom\" value=\"Unassigned\">");

    doSubmit('/workload/buyer_assignment.jsp', 'BrowseRetrieve');
  }

  function viewWorkloadSummary()
  {
  	//browse('buyer-workload-summary');
  	browse('prm-req-worlkloadview');
  }

  function checkGraphData()
  {
<%	if (! reqGraphHasData) { %>
			toggleDisplay(0);
<%	}
		if (! rfqByStatusGraphHasData) { %>
			toggleDisplay(11);
<%	}
    	if (! poGraphHasData) { %>
			toggleDisplay(9);
<%	}
    	if (! invoiceStatusGraphHasData) { %>
			toggleDisplay(20);
<%	}

%>
  }

    function viewRfqsWaitingResponse() {
        browse('myrfqs-waiting-response');
    }
        function viewRfqsWaitingApproval() {
    	setOriginalFilter("ApprovalLog_callForward", "=", userId);
        browse('myrfqs-waiting-approval');
    }

    /*function viewRfqsBidsClosed() {
        browse('rfq-bidsclosed');
    }*/

	function forwardRequisition(ic) {
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>" +
			"<input type='hidden' name='reqaction' value=FORWARD>" +
			"<input type='hidden' name='formType' value=REQ>";
		setHiddenFields(newInputField);
		frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";

		doSubmit('/requests/req_validate_no_popup.jsp', 'RequisitionValidate');
	}

	function viewRoutingList(ic) {
		popupParameters = "RequisitionHeader_icReqHeader=" + ic;
		popupParameters = popupParameters + ";reqaction=ROUTING";

		doSubmitToPopup('/requests/req_routinglist.jsp', 'RequisitionGetRoutingList', 'width=685px', 'height=450px');
	}

	function resendReqToApprover(ic) {
		popupParameters = "SendQueue_docic=" + ic;

		doSubmitToPopup('/requests/req_approver_email_resent.jsp', 'RequisitionResendApproverEmail', 'width=680px', 'height=350px');
	}

	function rerouteRequisition(ic) {
		unavailable();
	}

	function viewAssignedBuyer(ic) {
		unavailable();
	}

	function viewBiddersByReq(ic) {
		unavailable();
	}

	function viewBidsByReq(ic) {
		unavailable();
	}

	function showForwardButton() {
	}

	function viewAssets()
  	{
  		browse('invitem_asst');
  	}

  	function viewAssetsSearch()
  	{
  		doSubmit('/browse/asset-search.jsp','DoNothing');
  	}

  	function viewAssetsByLocation()
  	{
  		browseFilter('asset_location');
  	}

  	function viewInvoicesByStatus(status)
  	{
  		//setOriginalFilter("InvoiceHeader_status", ">", "<%=DocumentStatus.IVC_INPROGRESS%>");
  		//setOriginalFilter("InvoiceHeader_status", "<=", "<%=DocumentStatus.IVC_APPROVED%>");
  		setOriginalFilter("InvoiceHeader_status", "=", status);
  		browse('invoiceheader');
  	}


  	function viewInvoices(type)
  	{
  		var orgId = "<%=oid%>";
	    var newInputField;

		if (type=="APPROVAL")
	    {
	    	// invoices waiting my approval
			//setOriginalFilter("ApprovalLog_callForward", "=", userId);
			browse("invoice-approval-list");
			return;
		}
	    else if (type=="INCOMPLETE")
	    {
			setOriginalFilter("InvoiceHeader_status", "=", "<%=DocumentStatus.IVC_INPROGRESS%>");
			setOriginalFilter("InvoiceHeader_enteredBy", "=", userId);
	    }
	    else if (type=="APPROVING")
	    {
	    	//invoices pending approval
			setOriginalFilter("InvoiceHeader_status", ">=", "<%=DocumentStatus.IVC_APPROVING%>");
			setOriginalFilter("InvoiceHeader_status", "<", "<%=DocumentStatus.IVC_APPROVED%>");
	    }
	    else if (type=="APPROVED")
	    {
	    	setOriginalFilter("InvoiceHeader_status", "=", "<%=DocumentStatus.IVC_APPROVED%>");
	    	if (orgId == "AKDOC")
			{
				browse('invoices-approved');
				return;
			}
	    }

	    browse('invoiceheader');
  	}

	// Iventory Request

	function viewInventoryRequest(type)
  	{
		if (type=="APPROVED")
	    {
	    	// inventory request approvals
			browse("inventoryrequest-approve-list");
		}
   	}

	function viewDisbursement(type)
  	{
		if (type=="PENDING DISB")  //PENDING DISB
	    {
	    	// inventory request approvals
	    	setOriginalFilter("DisbHeader_status", "=", "5000");
		}
		browse("disbursement-pendingdisb");
   	}

  	function viewBackorderInvReqByline()
  	{
		browse("backorder-requisitionbyline");
   	}


  	function viewVendorAP(type) {
  		browse('supplier_ap');
  	}

	function viewStandardForms() {
		doSubmit('/admin/documents/std_attachments.jsp','StdAttachmentRetrieveAll');
  	}

	function printRequisitionPdf(ic) {
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		setHiddenFields(newInputField);
		frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";

		doSubmit('/requests/req_print_pdf_popup.jsp', 'RequisitionRetrieve');
	}

	function viewReqPdf(ic, number, type) {
			popupParameters = "RequisitionHeader_icReqHeader=" + ic;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		doSubmitToPopup('', 'PrintPdf', 'width=775px', 'height=850px');
	}

	function emailReqPdf(ic, number, type) {
		popupParameters = "RequisitionHeader_icReqHeader=" + ic;
		popupParameters = popupParameters + ";RequisitionHeader_requisitionNumber=" + number;
		popupParameters = popupParameters + ";RequisitionHeader_requisitionType=" + type;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		doSubmitToPopup('requests/req_email_pdf_popup.jsp', 'DoNothing', 'width=692px', 'height=325px');
	}

	function viewPoPdf(ic, number, type) {
		popupParameters = "PoHeader_icPoHeader=" + ic;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		doSubmitToPopup('', 'PrintPoPdf', 'width=775px', 'height=850px');
	}

	function emailPoPdf(ic) {
		popupParameters = "PoHeader_icPoHeader=" + ic;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		doSubmitToPopup('orders/po_email_pdf_popup.jsp', 'PoHeaderVendorRetrieveById', 'width=692px', 'height=325px');
	}

	function viewRfqPdf(ic, number, type) {
		popupParameters = "RfqHeader_icRfqHeader=" + ic;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";viewNow=Y";
		doSubmitToPopup('', 'PrintRfqPdf', 'wiqdth=775px', 'height=850px');
	}

	function forwardPo(ic) {
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='poaction' value=FORWARD>";
		setHiddenFields(newInputField);
		frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";

		doSubmit('/orders/po_validate_no_popup.jsp', 'PoValidate');
	}

	function searchCatalogItems()
	{
		var itemType = "CAT";
		var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
		newInputField = newInputField + "<input type='hidden' name='as_item_type' value='CAT'>";
		setHiddenFields(newInputField);
/*
		if (!isCriteriaEntered()) {
			alert("Please specify at least one search criteria.  Ex: Keyword(s): letterhead.");
			return false;
		}
*/
		frm.browseName.value = "catalogitem";

		var keywords = frm.as_keywords.value;
		keywords = replaceForKeywords(keywords);

		if ( !isEmpty(keywords) )
		{
			keywords = "%" + keywords + "%";
		}
		if(orgId!="WPC08P")
		{
			setAdvancedFilter("CatalogItem_description", "LIKE", keywords, "OR", "Y", "N");
			setAdvancedFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "OR", "Y", "N");
			setAdvancedFilter("CatalogItem_id_catalogId", "=", keywords, "OR", "Y", "N");
		}
		if(orgId=="WPC08P")
		{
			//setOriginalFilter("CatalogItem_description", "LIKE", keywords, "OR", "Y", "N");
			setOriginalFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "AND", "Y", "N");

			newInputField += "<input type='hidden' name='isCatalogSecurityActive' value='Y'>";
			setHiddenFields(newInputField);

		}
		setSort();
		doSubmit('browse/item_browse.jsp', 'RequisitionCreate;BrowseRetrieve');
	}

	function setSort()
	{
		var myCell = document.getElementById("filterFields");
		var filterFields = myCell.innerHTML;
		var colname = "CatalogItem_id_itemNumber";
		var sortOrder = "ASC";

		filterFields = filterFields + "<input type=hidden name=\"" + "colname" + "\" value=\"" + colname  + "\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "operator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "filter_txt\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "logicalOperator\" value=\"\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "originalFilter\" value=\"Y\">";
		filterFields = filterFields + "<input type=hidden name=\"" + "sort\" value=\"" + sortOrder + "\">";

		myCell.innerHTML = filterFields;
	}

	function createReqFromTemplate(ic)
	{
		var fiscalYear = "<%=fiscalYear%>";
		var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_fiscalYear' value='" + fiscalYear + "'>";
		setHiddenFields(newInputField);
		frm.viewType.value = "${esapi:encodeForHTMLAttribute(viewTypeWizard)}";
		doSubmit('/requests/req_review.jsp', 'RequisitionSaveas;RequisitionRetrieve');
	}

	function viewBuyerRemarks(ic, type)
	{
		if (type == "REQ")
		{
			var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
			newInputField = newInputField + "<input type='hidden' name='HistoryLog_icHeader' value='" + ic + "'>";
			setHiddenFields(newInputField);
			doSubmit('/requests/req_buyer_remarks.jsp', 'RequisitionHeaderRetrieveById;HistoryLogRetrieveBuyerRemarks');
		}
		else if (type == "RFQ")
		{
			var newInputField = "<input type='hidden' name='RfqHeader_icRfqHeader' value='" + ic + "'>";
			newInputField = newInputField + "<input type='hidden' name='RfqLine_icRfqHeader' value='" + ic + "'>";
			setHiddenFields(newInputField);
			doSubmit('/rfq/rfq_buyer_remarks.jsp', 'RfqRetrieveBuyerRemarks');
		}
		else if (type == "PO")
		{
			var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";
			newInputField = newInputField + "<input type='hidden' name='PoLine_icPoHeader' value='" + ic + "'>";
			setHiddenFields(newInputField);
			doSubmit('/orders/po_buyer_remarks.jsp', 'PoRetrieveBuyerRemarks');
		}
	}

	function moreRequestedItems() {
		browse('myreqitems');
	}

	function searchEvignaItems()
	{
		var itemType = "CAT";
		var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
		newInputField = newInputField + "<input type='hidden' name='as_item_type' value='CAT'>";
		setHiddenFields(newInputField);

		frm.browseName.value = "catalogitem";

		setAdvancedFilter("CatalogItem_id_catalogId", "=", "EVIGNA", "OR", "Y", "N");

		setSort();
		doSubmit('browse/item_browse.jsp', 'RequisitionCreate;BrowseRetrieve');
	}

	function viewReqPipeline(access)
	{
  		if( access == 1 )
  		{
			setOriginalFilter("RequisitionHeader_owner", "=", userId);
	  	}
  		else if (access == 2)
	  	{
			setOriginalFilter("RequisitionHeader_departmentCode", "=", userDepartment);
  		}
		browse('req-pipeline');
	}

	if (frm.exportFACompleted && frm.exportFACompleted.value == "Y")
	{
		frm.exportFACompleted.value = "";
		alert("Exported Completed Successfully.");
	}

	function expFixedAssets()
	{
		if (verifyAction("Export Fixed Assets ?"))
		{
			frm.exportFACompleted.value = "Y";
			doSubmit('menu/main_menu.jsp','InvoiceLineExportFixedAssets');
		}
	}

	if (frm.exportGLTranCompleted && frm.exportGLTranCompleted.value == "Y")
	{
		frm.exportGLTranCompleted.value = "";
		alert("Exported Completed Successfully.");
	}

	function expGLTran()
	{
		if (verifyAction("Export GL Transactions ?"))
		{
			frm.exportGLTranCompleted.value = "Y";
			doSubmit('menu/main_menu.jsp','InvoiceLineExportGLTran');
		}
	}

// End Hide script -->
</script>

<%@ include file="/system/prevent_caching.jsp" %>
