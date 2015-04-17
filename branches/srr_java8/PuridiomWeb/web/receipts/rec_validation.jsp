<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	String receiptAction = (String) request.getAttribute("receiptAction");
	String receiptMethod = (String) request.getAttribute("receiptMethod");	
	String s_msr_number = HiltonUtility.ckNull((String) request.getAttribute("poMsrNumber"));

	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_current_process = "HEADER_VALIDATION";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	String	s_current_page = "/receipts/rec_validation.jsp" ;
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	receiptNumber = receiptHeader.getReceiptNumber();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader() ;
	
	String s_method = "DoNothing";
	
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
	
	String s_enabled_steps =  propertiesManager.getProperty("RECEIVING", "ENABLED RECEIVING IN N-STEP", "N");

	String s_rec_type = receiptHeader.getReceiptType() ;
	if (HiltonUtility.isEmpty(receiptAction)) {
		receiptAction = "VALIDATE";
	}

	List receiptLineList = (List)request.getAttribute("receiptLineList");
	int	i_linecount = 0;
	if (receiptLineList != null) {
		i_linecount = receiptLineList.size();
	}

	String	s_ic_req_header = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_icReqHeader"));
	String	s_req_number = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_requisitionNumber"));
	if (poHeader==null) {
		poHeader = new PoHeader();
	}
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	if (HiltonUtility.isEmpty(s_po_number)) {
		s_po_number = poHeader.getPoNumber();
	}

	BigDecimal	bd_revision_number = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(0);
	Object	o_revision_number = (Object)request.getAttribute("PoHeader_revisionNumber");
	Object	o_release_number = (Object)request.getAttribute("PoHeader_releaseNumber");
	if (o_revision_number != null) {
		bd_revision_number = new BigDecimal(o_revision_number.toString());
	}
	if (o_release_number != null) {
		bd_release_number = new BigDecimal(o_release_number.toString());
	}
	if (bd_revision_number.compareTo(new BigDecimal(0)) == 0) {
		bd_revision_number = poHeader.getRevisionNumber();
	}
	if (bd_release_number.compareTo(new BigDecimal(0)) == 0) {
		bd_release_number = poHeader.getReleaseNumber();
	}

	String	s_revision_number = bd_revision_number.toString() ;
	String	s_release_number = bd_release_number.toString() ;

	if (requisitionHeader==null) {
		requisitionHeader = new RequisitionHeader();
	}


%>

	<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
	<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
	<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptHeader.getReceiptType()%>"/>
	<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
	<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
	<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
	<tsa:hidden name="ReceiptHeader_icReqHeader" value="<%=receiptHeader.getIcReqHeader()%>"/>
	<tsa:hidden name="receiptStatus" value="<%=s_rec_status%>"/>

	<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
	<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
	<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
	<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
	<tsa:hidden name="PoLine_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>

	<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_ic_req_header%>"/>
	<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_ic_req_header%>"/>
	<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>

	<tsa:hidden name="Account_icHeader" value="<%=bd_ic_rec_header%>"/>
	<tsa:hidden name="Account_icLine" value="0"/>
	<tsa:hidden name="Account_accountType" value="RCH"/>
	<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_rec_header%>"/>
	<tsa:hidden name="DocComment_icLine" value="0"/>
	<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_rec_header%>"/>


	<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
	<tsa:hidden name="receiptAction" value="<%=receiptAction%>"/>
	<tsa:hidden name="formType" value="REC"/>

	<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
	<tsa:hidden name="sendBmcEmail" value="N"/>
	<tsa:hidden name="poMsrNumber" value="<%=s_msr_number %>"/>

	<tsa:hidden name="receiptMethodTemp" value="<%=receiptMethod%>"/>
<%
for (int i=0; i<receiptLineList.size(); i++) {
	ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i); %>
	<tsa:hidden name="ReceiptLine_icRecLine" value="<%=receiptLine.getIcRecLine()%>"/>
<%
}
%>
<div style="display:none;">
<%@ include file="/receipts/rec_info.jsp" %>
</div>

<%
	if(s_enabled_steps.equals("Y"))
	{
		if(receiptMethod.equalsIgnoreCase("ReceiveByOrder")){
			if(HiltonUtility.isEmpty(receiptNumber) || receiptNumber.equals("N/A")){
				s_method = "ReceiptGetFormNumber;ReceiptUpdateNSteps";
			}
			else
			{
				s_method = "ReceiptUpdateNSteps";
			}
		}
	}
%>
<br>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validationResults", "Validation Results")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
			<table border=0 cellspacing=0 cellpadding=1 width=100%>
			<tr>
			<td align=right>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} else if (!HiltonUtility.isEmpty(s_req_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Transfer Request #:</b>&nbsp;<%=headerEncoder.encodeForHTML(s_req_number)%>
		<%  } %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
		</tr>
		</table>
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
	<td align=right valign=top><%@ include file="/receipts/rec_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

  	var navMenu = getNavCookie("navArray");
	var fiscalyear = "<%=receiptHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var recType = '<%=receiptHeader.getReceiptType()%>' ;
	var recNumber = "<%=s_rec_number%>";

	if (navMenu.indexOf("<%=ReceiptType.toString(s_rec_type, oid)%>") < 0)
	{
		setNavCookie(currentpage, currentprocessmethod, "<%=ReceiptType.toString(s_rec_type, oid)%>");
	}

<%	if (rules.getResult() == 1) { %>
	displayArea('novalidationrules');
	displayArea('forward_link');
<%	} else {%>
	displayArea('validationrules');
	<%	if (rules.getResult() == -1) { %>
	hideArea('forward_link');
<%		}
	}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}


  function returnMe()
  {
	doSubmit('receipts/rec_items.jsp', 'ReceiptLineRetrieveByHeader');
  }

  function thisUnLoadPopup()
  {
      window.parent.showForwardButton();
  }

	function receiptForward() {
		var s_method = '<%=s_method%>';
		if (frm.receiptMethod.value == "ReceiveByPackage") {
			frm.ReceiptHeader_receiptStatus.value = "<%=DocumentStatus.RCV_PENDINGFINALIZATION%>";
		  	if (isEmpty(recNumber) || recNumber == "N/A") {
				doSubmit('receipts/rec_forward.jsp', s_method+';ReceiptGetFormNumber;ReceiptForward');
			} else {
				doSubmit("receipts/rec_forward.jsp", s_method+";ReceiptForward");
			}
		} else if (frm.receiptMethod.value == "FinalizeReceipt" || frm.receiptMethod.value == "ReceiveByOrder" || frm.receiptMethod.value == "Return") {
			if (frm.receiptMethod.value == "ReceiveByOrder") {
				frm.sendBmcEmail.value = "Y";
			}
			frm.receiptMethod.value = "FinalizeReceipt";
			if (isEmpty(recNumber) || recNumber == "N/A") {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptGetFormNumber;ReceiptUpdate');
			} else {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptUpdate');
			}
		} else if (frm.receiptMethod.value == "ReceiveByTransfer") {
			frm.receiptMethodTemp.value = "FinalizeReceipt";
			if (isEmpty(recNumber) || recNumber == "N/A") {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptGetFormNumber;ReceiptTransferUpdate');
			} else {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptTransferUpdate');
			}
		} else if (frm.receiptMethod.value == "ReceiveFromNothing") {
			frm.receiptMethod.value = "ReceiveFromNothing";
		  	if (isEmpty(recNumber) || recNumber == "N/A") {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptGetFormNumber;ReceiptCreateFromNothing');
			} else {
				doSubmit('receipts/rec_confirmation.jsp', s_method+';ReceiptCreateFromNothing');
			}
		} else {
		  	if (isEmpty(recNumber) || recNumber == "N/A") {
				doSubmit('receipts/rec_forward.jsp', s_method+';ReceiptGetFormNumber;ReceiptCreateForward');
			} else {
				doSubmit('receipts/rec_forward.jsp', s_method+';ReceiptCreateForward');
			}
		}
	}

	function receiptSubmit() {
		var s_method = '<%=s_method%>';
		doSubmit('receipts/rec_review.jsp', s_method + ';ReceiptRetrieve');
	}

// end hiding contents -->
</SCRIPT>