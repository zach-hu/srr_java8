<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRule" %>
<%@ page import="com.tsagate.foundation.rule.ValidationRules" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<%
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	autoRoutingList = propertiesManager.getProperty("VOUCHER", "AUTOROUTINGLIST", "N");
	String	extendedRouting = propertiesManager.getProperty("VOUCHER", "ExtendedRouting", "N");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("header");
	String invoiceAction = (String) request.getAttribute("invoiceaction");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String	s_current_process = "HEADER_VALIDATION";
	String	s_current_page = "/invoice/iv_validation.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	boolean bForward = false;

	if (HiltonUtility.isEmpty(invoiceAction)) { invoiceAction = "VALIDATE";	}
	String annio = Integer.toString(d_today.getYear());
%>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceVendor_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_accountType" value="IVH"/>
<tsa:hidden name="formtype" value="IVC"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="invoiceaction" value="<%=invoiceAction%>"/>
<tsa:hidden name="invoiceExceptionFailurePage" value="/invoice/iv_exceptions.jsp"/>
<tsa:hidden name="today_date" value="<%=annio%>"/>
<tsa:hidden name="InvoiceHeader_invoiceDate" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid, userDateFormat)%>"/>
<tsa:hidden name="Current_year" value="I"/>

<tsa:hidden name="DocComment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Validation Results</div>
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
  <td valign=bottom align=middle width=*>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b>Invoice #:</b></td>
      <td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
    </tr>
    <tr>
      <td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
      <td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
	</table>
  </td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr><td height=36px>&nbsp;</td></tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<%@ include file="/base/validation_rules.jsp" %>
	</td>
	<td align=right valign=top><%@ include file="/invoice/iv_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
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
	
	function thisUnLoadPopup()
	{
		window.parent.showForwardButton();
	}

	function checkHiddenMenu() {
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function invoiceForward() {
		frm.invoiceaction.value="FORWARD";
<% if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y") &&
			propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
     	  doSubmit('/invoice/iv_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
<% } else {
		if (autoRoutingList.equals("Y") && !extendedRouting.equals("Y")) { %>
			doSubmit('/invoice/iv_auto_routing_list.jsp', 'InvoiceCheckExceptions');
	<%	} else if(extendedRouting.equals("Y")){ %>
			doSubmit('/invoice/iv_routinglist_no_popup.jsp', 'InvoiceCheckExceptions;InvoiceGenerateRoutingList');
	<%	} else { %>
			doSubmit('/invoice/iv_routinglist.jsp', 'InvoiceCheckExceptions;InvoiceGenerateRoutingList');
	<%	} %>
<% } %>
	}

// end hiding contents -->
</SCRIPT>