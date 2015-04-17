<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	String s_ic_po_header = bd_ic_po_header.toString();
	BigDecimal	bd_ic_header_history = poHeader.getIcHeaderHistory();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String	s_curr_code = poHeader.getCurrencyCode();
	String	s_receipt_required = poHeader.getReceiptRequired();
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	poAction = (String)request.getAttribute("poaction");
	String	s_current_process = "HEADER_VALIDATION";
	String	s_current_page = "/orders/po_validation.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String	icHeader = bd_ic_po_header.toString();
	String	s_buyer_code = poHeader.getBuyerCode();
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String	s_new_po_approval = propertiesManager.getProperty("MODULES", "NEW PO APPROVALS", "N");
	//out.println(s_new_po_approval);
	boolean fromReq = false;

	if (poHeader.getIcReqHeader().compareTo(new BigDecimal(0)) > 0) {
		fromReq = true;
	}

	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());
	if (poAction == null){ poAction = "VALIDATE";	}
	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status = DocumentStatus.PO_INPROGRESS;	}

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
%>
<%@ include file="/orders/po_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="validationResults" defaultString="Validation Results" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr><td height=36px>&nbsp;</td></tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<%@ include file="/base/validation_rules.jsp" %>
	</td>
	<td align=right valign=top><%@ include file="/orders/po_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var ponumber = "<%=s_po_number%>";
	var fiscalyear = "<%=poHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	displayArea('forward_link');
<%	} else {%>
	displayArea('validationrules');
	<%	if (rules.getResult() == -1) { %>
	hideArea('forward_link');
<%		}
	}%>

	self.focus();

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function thisUnLoadPopup()
	{
		window.parent.showForwardButton();
	}

	function orderForward() {
		frm.poaction.value = "FORWARD";
		<%
		if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) { %>
			<%	if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
	     	  doSubmit('/orders/po_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
	      	<% } else { %>
				doSubmit('/orders/po_budget_check_no_popup.jsp', 'PoBudgetCheck');
	      	<% } %>
	<%-- 	} else if (propertiesManager.isModuleActive("PO APPROVALS") && !fromReq && (!s_po_type.equals("CT") &&
			propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "N").equalsIgnoreCase("Y"))) { --%>
//			doSubmit('/orders/po_routinglist_no_popup.jsp', 'PoGetRoutingList');
	<% 	} else if (s_new_po_approval.equalsIgnoreCase("Y"))
		{ %>
			doSubmit('/orders/po_routinglist_no_popup.jsp', 'PoGetRoutingList');		
<%		} else if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y")/* &&
					(propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))*/) { %>
			 //doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
			 doSubmit('/orders/po_award_options.jsp', 'PoAwardOptions');
	<% 	} else { %>
			frm.poForwardOption.value = "F";	/*  award Order*/
			doSubmit('/orders/po_forward_options.jsp', 'PoForward');
	<% 	} %>
	}

// end hiding contents -->
</SCRIPT>