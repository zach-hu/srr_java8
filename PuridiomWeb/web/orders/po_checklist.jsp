<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	int i;
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_po_type = (String) request.getAttribute("PoHeader_poType");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_itemLocation"));
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	List	checklistEntryList = (List) request.getAttribute("checklistEntryList");
	List	responseList =  (List) request.getAttribute("checklistResponseList");
	int i_rsprows = 0;
	int i_qstrows = 0;

	if (checklistEntryList != null) {
		i_qstrows = checklistEntryList.size();
	}
	if (responseList != null) {
		i_rsprows = responseList.size();
	}

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");

		if (s_po_number == null)
		{
			s_po_number = "N/A";
		}
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}
	if (s_fiscal_year == null)
	{
		s_fiscal_year = (String) request.getAttribute("fiscalYear");
	}

	String	s_current_process = "HEADER_REVIEWCHECKLIST";
	String	s_current_page = "/orders/po_checklist.jsp";
	String	s_current_method = "PoChecklistUpdateByHeader";
	String	s_current_process_method = "";
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Default_referenceType" value="POH"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="formNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="formStatus" value="<%=s_po_status%>"/>
<tsa:hidden name="fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="questionAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Order Checklist</div>
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 width=100% cellpadding=2 cellspacing=1>
		<tr valign=middle>
			<td colspan=2 width=2%>&nbsp;</td>
			<td width=6%><b><u>Yes</u></b></td>
			<td width=6%><b><u>No</u></b></td>
			<td width=6%><b><u>N/A</u></b></td>
			<td width=70%>&nbsp;</td>
		</tr>
<%
	String	s_response_type = "";
	String	s_type = "";
	int	i_rowcount = 0;

	for ( int ii = 0; ii < i_qstrows; ii++ )
	{
		ChecklistEntry checklistEntry = (ChecklistEntry) checklistEntryList.get(ii);
		BigDecimal	questionIc = checklistEntry.getIcQuestion();
		List	responseValueList = checklistEntry.getResponseValueList();
		String	s_response = "";
		String	s_text_response = "";

		if (i_rsprows > 0) {
			for ( int ir = 0; ir < i_rsprows; ir++ ) {
				ChecklistResponse checklistResponse = (ChecklistResponse) responseList.get(ir);
				if (checklistResponse.getComp_id().getIcQuestion().equals(questionIc)) {
					s_text_response = checklistResponse.getTextResponse();
					s_response = checklistResponse.getResponse();
					responseList.remove(ir);
					break;
				}
			}
		}

		s_response_type = checklistEntry.getResponseType().trim().toUpperCase();

		if (s_response_type.equals("YN") || s_response_type.equals("BOTH")) {
			if (responseValueList == null) {
				responseValueList = new ArrayList();
			}
		}

		i_rowcount++;
%>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;<b><%=i_rowcount%>.</b></td>
<%		if (s_response_type.equals("YN") || s_response_type.equals("BOTH")) {%>
			<td><input type=radio name="radio<%=i_rowcount-1%>" value="Y" <%if (s_response.equals("Y")) {%>CHECKED<%}%> onClick="setResponse('Y', <%=i_rowcount-1%>);"></td>
			<td><input type=radio name="radio<%=i_rowcount-1%>" value="N" <%if (s_response.equals("N")) {%>CHECKED<%}%> onClick="setResponse('N', <%=i_rowcount-1%>);"></td>
			<td>&nbsp;</td>
<%		} else if (s_response_type.equals("YNN")) {%>
			<td><input type=radio name="radio<%=i_rowcount-1%>" value="Y" <%if (s_response.equals("Y")) {%>CHECKED<%}%> onClick="setResponse('Y', <%=i_rowcount-1%>);"></td>
			<td><input type=radio name="radio<%=i_rowcount-1%>" value="N" <%if (s_response.equals("N")) {%>CHECKED<%}%> onClick="setResponse('N', <%=i_rowcount-1%>);"></td>
			<td><input type=radio name="radio<%=i_rowcount-1%>" value="NA" <%if (s_response.equals("NA")) {%>CHECKED<%}%> onClick="setResponse('NA', <%=i_rowcount-1%>);"></td>
<%		} else {%>
			<td colspan=3>&nbsp;</td>
<%		}%>
			<td>
				<%=checklistEntry.getQuestionText()%>
				<tsa:hidden name="ChecklistResponse_icQuestion" value="<%=checklistEntry.getIcQuestion()%>"/>
				<tsa:hidden name="ChecklistResponse_icHeader" value="<%=s_ic_po_header%>"/>
				<tsa:hidden name="ChecklistResponse_response" value="<%=s_response%>"/>
				<tsa:hidden name="ChecklistResponse_textResponse" value="<%=s_text_response%>"/>
				<tsa:hidden name="as_response_type" value="<%=s_response_type%>"/>
			</td>
		</tr>
<%		if (s_response_type.equals("MC") || s_response_type.equals("TEXT") || s_response_type.equals("BOTH")) {%>
		<tr>
			<td colspan=5>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 valign=top height=10px>
<%			if (s_response_type.equals("MC")) {
					for (int ir = 0; ir < responseValueList.size(); ir++) {
						ResponseValue responseValue = (ResponseValue) responseValueList.get(ir);
						String	rvalue = responseValue.getComp_id().getResponseValue();%>
				<tr height=10px>
					<td valign=middle align=right>
						<input type=radio name="radio<%=i_rowcount-1%>" value="<%=rvalue%>" <%if (s_response.equals(rvalue)) {%>CHECKED<%}%> onClick="setResponse('<%=rvalue%>', <%=i_rowcount-1%>);">
					</td>
					<td valign=middle nowrap>
						<%=responseValue.getResponseText()%>
					</td>
				</tr>
<%				}
				}
				if (s_response_type.equals("TEXT") || s_response_type.equals("BOTH")) {%>
				<tr height=10px><td><input type=text name="text_response" maxlength=60 size=65 value="<%=s_text_response%>" onChange="setText(this.value, <%=i_rowcount-1%>);">&nbsp;</td>
<%			}
				else {%>
					<td><tsa:hidden name="text_response" value=""/></td>
<%			}%>
				</tr>
				</table>
			</td>
		</tr>
<%		}
			else {%>
			<tsa:hidden name="text_response" value=""/>
<%		}
	}

	if (i_qstrows == 0) {%>
		<tr>
			<td>&nbsp;</td>
			<td align=center colspan=5>&nbsp;<b>A checklist has not yet been set up.</b></td>
		</tr>
<%	} %>
		</table>
	</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
	<td align=right><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<div id="classicNavigation">
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if ( (role.getAccessRights("PO") > 1) && ((s_po_status.compareTo(DocumentStatus.PO_REJECTED) <= 0)  || (bAdminChanges && s_po_status.compareTo(DocumentStatus.CANCELLED) < 0 && s_po_status.compareTo(DocumentStatus.PO_AMENDED) != 0)) )  { %>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoChecklistResponseUpdate;PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<%	}%>
</tr>
</table>
<%	} %>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
	var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
	var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
	var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";
	var browser = browserCheck();
	var qstRows = <%=i_qstrows%>;
	var thisrow ;

	function thisLoad()
	{
		f_StartIt();
<%	if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0) { %>
			checkInputSettings();
<%	} %>
	}

	function setResponse(val, row) {
		if (qstRows == 1) {
			frm.ChecklistResponse_response.value = val;
		}
		else {
			frm.ChecklistResponse_response[row].value = val;
		}
	}

	function setText(val, row) {
		if (qstRows == 1) {
			frm.ChecklistResponse_textResponse.value = val;
		}
		else {
			frm.ChecklistResponse_textResponse[row].value = val;
		}
	}

// End Hide script -->
</SCRIPT>