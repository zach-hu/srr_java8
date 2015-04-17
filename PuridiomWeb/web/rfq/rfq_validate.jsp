<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<%
	PropertiesManager	propertiesManager 	= PropertiesManager.getInstance(oid);
	ValidationRules rules = (ValidationRules) request.getAttribute("rules");
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("header");
	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_fiscalYear = rfqHeader.getFiscalYear();
	String rfqAction = (String) request.getAttribute("rfqaction");
	String s_doubleForward = propertiesManager.getProperty("RFQ OPTIONS", "DOUBLEFORWARD", "Y");
	boolean bForward = false;
	int i_lineCount = 0;
	String	s_current_process = "HEADER_VALIDATION";
	String	s_current_method = "";
	String	s_current_process_method = "";
	String	s_current_page = "/rfq/rfq_validate.jsp";

	if (HiltonUtility.isEmpty(rfqAction)) { rfqAction = "VALIDATE";	}

	//	Webpost Options
	String s_webpost = rfqHeader.getWebpost();
	if (HiltonUtility.isEmpty(s_webpost)) {s_webpost = "N";}

	String defaultAppPool = propertiesManager.getProperty("RFQ OPTIONS", "APPROVAL APP POOL", "");
	String defaultEstimatedCost = propertiesManager.getProperty("RFQ OPTIONS", "APPROVAL ESTIMATED COST", "0.00");
	BigDecimal estimatedCost = new BigDecimal(defaultEstimatedCost);
	boolean goToApproval = false;
	if (rfqHeader != null && rfqHeader.getEstimatedCost().compareTo(estimatedCost) > 0) {
		goToApproval = true;
	}
%>
<%@ include file="/rfq/rfq_hidden_fields.jsp" %>

<tsa:hidden name="AppPool_poolid" value="<%=defaultAppPool%>"/>
<tsa:hidden name="AppPooluser_poolid" value="<%=defaultAppPool%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RfqType.toString(rfqHeader.getRfqType())%> Validation Results</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr><td height=36px>&nbsp;</td></tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
	<%@ include file="/base/validation_rules.jsp" %>
	</td>
	<td align=right valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var rfqnumber = "<%= headerEncoder.encodeForJavaScript(s_rfqNumber) %>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	self.focus();
<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	displayArea('forward_link');
<%	} else {%>
	displayArea('validationrules');
<%		if (rules.getResult() == -1) { %>
			hideArea('forward_link');
<%		}
	}%>

	function checkHiddenMenu() {
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function rfqForward() {
<%
		if (s_view.equalsIgnoreCase("CLASSIC")) {
%>
		doSubmit('rfq/rfq_summary.jsp', 'RfqForward');
<%
		//} else if (s_webpost.compareTo("N") != 0 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.equals(DocumentStatus.RFQ_OPENAMENDMENT))) {
		} else if (s_doubleForward.equals("Y") && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.equals(DocumentStatus.RFQ_OPENAMENDMENT))) {
		//( s_rfqStatus.equals(DocumentStatus.RFQ_PURCHASING) || d_today.compareTo(rfqHeader.getAuctionEndDate()) > 0 )  {
%>
	<%	if (goToApproval) { %>
		doSubmit('rfq/rfq_forward_options.jsp', 'RfqRetrieve;AppPoolRetrieveById;AppPooluserRetrieveByPool');
	<%	} else { %>
		doSubmit('rfq/rfq_review.jsp', 'RfqForward');
	<%	} %>
<%
		} else {
%>
		doSubmit('rfq/rfq_supplier_award.jsp', 'RfqRetrieve');
		//doSubmit('rfq/rfq_intent_to_award.jsp', 'RfqRetrieve');
<%
		}
%>
	}

	function rfqReturn() {
		doSubmit('/rfq/rfq_return.jsp', 'RfqBidRetrieveByHeader;RfqVendorRetrieveByHeader');
	}

	function returnMe() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	doSubmit('rfq/rfq_summary.jsp', 'RfqRetrieve');
<%	} else { %>
	doSubmit('rfq/rfq_review.jsp', 'RfqRetrieve');
<%	} %>
  }

	function thisUnLoadPopup() {
		window.parent.showForwardButton();
	}

// end hiding contents -->
</SCRIPT>