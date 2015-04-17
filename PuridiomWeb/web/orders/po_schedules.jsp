<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.Schedule" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");

	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	s_ic_po_header = (String) request.getAttribute("PoHeader_icPoHeader");
	String	s_po_number = (String) request.getAttribute("PoHeader_poNumber");
	String	s_release_number = (String) request.getAttribute("PoHeader_releaseNumber");
	String	s_revision_number = (String) request.getAttribute("PoHeader_revisionNumber");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	s_po_status = (String) request.getAttribute("PoHeader_status");
	String s_buyer_code = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_buyerCode"));
	String s_vendor_id = HiltonUtility.ckNull((String) request.getAttribute("VendorInsurance_vendorId"));
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	String	returnPage = "";

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");

	String	s_po_type = (String) request.getAttribute("PoHeader_poType");

	String	s_current_process = "SCHEDULES";
	String	s_current_page = "/orders/po_schedules.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}

	if (s_po_number == null)
	{
		s_po_number = (String) request.getAttribute("formNumber");
	}
	if (s_ic_po_header == null)
	{
		s_ic_po_header = (String) request.getAttribute("DocComment_icHeader");
	}
	if (s_po_status == null)
	{
		s_po_status = (String) request.getAttribute("formStatus");
	}

	String	allowBrowse = (String) request.getAttribute("allowBrowse");

	if (allowBrowse == null) {
		allowBrowse = "true";
	}

	boolean bAllowEdit = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equalsIgnoreCase(s_buyer_code))
	{
		bAllowEdit = false;
	}

	String	s_document_type = (String) request.getAttribute("Schedule_documentType");
	String	s_type = (String) request.getAttribute("Schedule_scheduleType");
	String	s_ic_header = (String) request.getAttribute("Schedule_icHeader");
	String	s_title = "";

	// if (s_type.equals("R"))	{	s_title = "Performance";	}
	// if (s_type.equals("D"))	{	s_title = "Delivery";			}
	// if (s_type.equals("M"))	{	s_title = "Milestone";		}
	// if (s_type.equals("P"))	{	s_title = "Payment";			}
	// if (s_type.equals("O"))	{	s_title = "Other";				}

	if (s_view.equals("WIZARD")) {
		returnPage = "/orders/po_review.jsp";
	} else {
		returnPage = "/orders/po_summary.jsp";
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=s_release_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=s_revision_number%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=s_buyer_code%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="resetReceipt" value="false"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="<%=s_document_type%>"/>
<tsa:hidden name="Schedule_icHeader" value="<%=s_ic_header%>"/>
<tsa:hidden name="Schedule_lineNumber" value=""/>
<tsa:hidden name="Schedule_scheduleType" value=""/>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="formAction" value=""/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=s_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=s_vendor_id%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="contractEvents" defaultString="Contract Events" /></div></td></tr>
		</table>
	</td>
	<td valign=bottom width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px" width="*">
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
<%
	s_title = "Milestone";
	List mList = (List) request.getAttribute("milestonesList");
%>

	<br>
	<table border=0 cellspacing=0 cellpadding=0 width=675px>
	<tr>
		<td nowrap height=18px align=center class=browseHdrDk width=100% title="<tsa:label labelName="po-milestone-events-helper" defaultString="" />">
			<tsa:label labelName="milestoneEvents" defaultString="Milestone Events" />
		</td>
	</tr>
	 </table>

	<table id=milestonesList border=0 cellspacing=0 cellpadding=0 width=680px>
	<tr>
		<td width=5px>&nbsp;</td>
		<td width=670px class=browseHdrDk align=center valign=top>
			<table border=0 cellspacing=0 cellpadding=2 width=665px>
			<tr>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="scheduleDate" defaultString="Schedule Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="revisedDate" defaultString="Revised Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=50%><tsa:label labelName="description" defaultString="Description" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="completionDate" defaultString="Completion Date" /></td>
<%	if (bAllowEdit) {	%>
				<td nowrap height=18px class=browseHdrDk width=5%><tsa:label labelName="delete" defaultString="Delete" /></td>
<%	}	%>
			</tr>
			</table>
			<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
			<div id="noRecordsM" style="visibility: hidden; display: none;">
			<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
			<tr><td><b><tsa:label labelName="noEventsMessage" defaultString="<%= \"There are currently no \" + s_title + \"&nbsp;Events.\" %>" /></b></td></tr>
			</table>
			</div>

			<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%
		for (int il = 0; il < mList.size(); il++) {
			Schedule schedule = (Schedule) mList.get(il);
%>
			<tr>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=50%>
					<% if (bAllowEdit) { %><a href="javascript: updateSchedule('<%=schedule.getComp_id().getLineNumber()%>','M'); void(0);" title="Click here to View/Modify Descripition Details."><% } %><%=schedule.getDescription()%><% if (bAllowEdit) { %></a><% } %>
				</td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat)%></td>
<%			if (bAllowEdit) {	%>
				<td align=center valign=top width=5%><a href="javascript: deleteSchedule(<%=schedule.getComp_id().getLineNumber()%>,'M'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete"></td>
<%			}	%>
			</tr>
<%		}
			if (bAllowEdit) {	%>
			<tr>
				<td height=18px class=browseRow ><a href="javascript: addSchedule('M'); void(0);" title="Click here to add a new event.">Add Event</a></td>
			</tr>
<%		}	%>
			</table>
			</div>
		</td>
		<td width=5px>&nbsp;</td>
	</tr>
	</table>

	<br>
	<table border=0 cellspacing=0 cellpadding=0 width=675px>
	<tr>
		<td nowrap height=18px align=center class=browseHdrDk width=100% title="<tsa:label labelName="po-delivery-events-helper" defaultString="" />">
			<tsa:label labelName="deliveryEvents" defaultString="Delivery Events" />
		</td>
	</tr>
	 </table>
<%
	s_title = "Delivery";
	List dList = (List) request.getAttribute("deliveryList");
%>
	<table id=deliveryList border=0 cellspacing=0 cellpadding=0 width=680px>
	<tr>
		<td width=5px>&nbsp;</td>
		<td width=670px class=browseHdrDk align=center valign=top>
			<table border=0 cellspacing=0 cellpadding=2 width=665px>
			<tr>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="scheduleDate" defaultString="Schedule Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="revisedDate" defaultString="Revised Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=50%><tsa:label labelName="description" defaultString="Description" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="completionDate" defaultString="Completion Date" /></td>
<%	if (bAllowEdit) {	%>
				<td nowrap height=18px class=browseHdrDk width=5%><tsa:label labelName="delete" defaultString="Delete" /></td>
<%	}	%>
			</tr>
			</table>
			<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
			<div id="noRecordsD" style="visibility: hidden; display: none;">
			<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
			<tr><td><b><tsa:label labelName="noEventsMessage" defaultString="<%= \"There are currently no \" + s_title + \"&nbsp;Events.\" %>" /></b></td></tr>
			</table>
			</div>
			<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%
		for (int il = 0; il < dList.size(); il++) {
			Schedule schedule = (Schedule) dList.get(il);
%>
			<tr>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=50%>
					<% if (bAllowEdit) { %><a href="javascript: updateSchedule('<%=schedule.getComp_id().getLineNumber()%>','D'); void(0);" title="Click here to View/Modify Descripition Details."><% } %><%=schedule.getDescription()%><% if (bAllowEdit) { %></a><% } %>
				</td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat)%></td>
<%		if (bAllowEdit) {	%>
				<td align=center valign=top width=5%><a href="javascript: deleteSchedule(<%=schedule.getComp_id().getLineNumber()%>,'D'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete"></td>
<%		}	%>
			</tr>
<%	}
		if (bAllowEdit) {	%>
			<tr>
				<td height=18px class=browseRow><a href="javascript: addSchedule('D'); void(0);" title="Click here to add a new event.">Add Event</a></td>
			</tr>
<%	}	%>
			</table>
			</div>
		</td>
		<td width=5px>&nbsp;</td>
	</tr>
	</table>

<%
	s_title = "Payment";
	List pList = (List) request.getAttribute("paymentList");
%>

	<br>
	<table border=0 cellspacing=0 cellpadding=0 width=675px>
	<tr>
		<td nowrap height=18px align=center class=browseHdrDk width=100% title="<tsa:label labelName="po-payment-events-helper" defaultString="" />">
			<tsa:label labelName="paymentEvents" defaultString="Payment Events" />
		</td>
	</tr>
	 </table>

	<table id=paymentList border=0 cellspacing=0 cellpadding=0 width=680px>
	<tr>
		<td width=5px>&nbsp;</td>
		<td width=670px class=browseHdrDk align=center valign=top>
			<table border=0 cellspacing=0 cellpadding=2 width=665px>
			<tr>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="scheduleDate" defaultString="Schedule Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="revisedDate" defaultString="Revised Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=50%><tsa:label labelName="description" defaultString="Description" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="completionDate" defaultString="Completion Date" /></td>
<%	if (bAllowEdit) {	%>
				<td nowrap height=18px class=browseHdrDk width=5%><tsa:label labelName="delete" defaultString="Delete" /></td>
<%	}	%>
			</tr>
			</table>
			<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
			<div id="noRecordsP" style="visibility: hidden; display: none;">
			<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
			<tr><td><b><tsa:label labelName="noEventsMessage" defaultString="<%= \"There are currently no \" + s_title + \"&nbsp;Events.\" %>" /></b></td></tr>
			</table>
			</div>
			<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%
		for (int il = 0; il < pList.size(); il++) {
			Schedule schedule = (Schedule) pList.get(il);
%>
			<tr>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=50%>
					<% if (bAllowEdit) { %><a href="javascript: updateSchedule('<%=schedule.getComp_id().getLineNumber()%>','P'); void(0);" title="Click here to View/Modify Descripition Details."><% } %><%=schedule.getDescription()%><% if (bAllowEdit) { %></a><% } %>
				</td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat)%></td>
<%		if (bAllowEdit) {	%>
				<td align=center valign=top width=5%><a href="javascript: deleteSchedule(<%=schedule.getComp_id().getLineNumber()%>,'P'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete"></td>
<%		}	%>
			</tr>
<%	}
		if (bAllowEdit) {	%>
			<tr>
				<td height=18px class=browseRow><a href="javascript: addSchedule('P'); void(0);" title="Click here to add a new event.">Add Event</a></td>
			</tr>
<%	}	%>
			</table>
			</div>
		</td>
		<td width=5px>&nbsp;</td>
	</tr>
	</table>


<%
	s_title = "Performance";
	List rList = (List) request.getAttribute("performanceList");
%>

	<br>
	<table border=0 cellspacing=0 cellpadding=0 width=675px>
	<tr>
		<td nowrap height=18px align=center class=browseHdrDk width=100% title="<tsa:label labelName="po-performance-events-helper" defaultString="" />">
			<tsa:label labelName="performanceEvents" defaultString="Performance Events" />
		</td>
	</tr>
	 </table>

	<table id=performanceList border=0 cellspacing=0 cellpadding=0 width=680px>
	<tr>
		<td width=5px>&nbsp;</td>
		<td width=670px class=browseHdrDk align=center valign=top>
			<table border=0 cellspacing=0 cellpadding=2 width=665px>
			<tr>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="scheduleDate" defaultString="Schedule Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="revisedDate" defaultString="Revised Date" /></td>
				<td nowrap height=18px class=browseHdrDk width=50%><tsa:label labelName="description" defaultString="Description" /></td>
				<td nowrap height=18px class=browseHdrDk width=15%><tsa:label labelName="completionDate" defaultString="Completion Date" /></td>
<%	if (bAllowEdit) {	%>
				<td nowrap height=18px class=browseHdrDk width=5%><tsa:label labelName="delete" defaultString="Delete" /></td>
<%	}	%>
			</tr>
			</table>
			<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
			<div id="noRecordsR" style="visibility: hidden; display: none;">
			<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
			<tr><td><b><tsa:label labelName="noEventsMessage" defaultString="<%= \"There are currently no \" + s_title + \"&nbsp;Events.\" %>" /></b></td></tr>
			</table>
			</div>
			<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%
		for (int il = 0; il < rList.size(); il++) {
			Schedule schedule = (Schedule) rList.get(il);
%>
			<tr>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getScheduleDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getRevisedDate(), oid, userDateFormat)%></td>
				<td height=18px class=browseRow width=50%>
					<% if (bAllowEdit) { %><a href="javascript: updateSchedule('<%=schedule.getComp_id().getLineNumber()%>','R'); void(0);" title="Click here to View/Modify Descripition Details."><% } %><%=schedule.getDescription()%><% if (bAllowEdit) { %></a><% } %>
				</td>
				<td height=18px class=browseRow width=15%><%=HiltonUtility.getFormattedDate(schedule.getCompletionDate(), oid, userDateFormat)%></td>
<%		if (bAllowEdit) {	%>
				<td align=center valign=top width=5%><a href="javascript: deleteSchedule(<%=schedule.getComp_id().getLineNumber()%>,'R'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete"></td>
<%		}	%>
			</tr>
<%	}
		if (bAllowEdit) {	%>
			<tr>
				<td height=18px class=browseRow><a href="javascript: addSchedule('R'); void(0);" title="Click here to add a new event.">Add Event</a></td>
			</tr>
<%	}	%>
			</table>
			</div>
		</td>
		<td width=5px>&nbsp;</td>
	</tr>
	</table>

	<table width=100% border=0>
	<tr>
	</tr>
	</table>
</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=2 align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var rowSelect;
	frm = document.purchaseform;

	var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscal_year) %>";
	var browser = browserCheck();
	var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
	var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
	var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";
	var totalRowsM = <%=mList.size()%>;
	var totalRowsD = <%=dList.size()%>;
	var totalRowsR = <%=rList.size()%>;
	var totalRowsP = <%=pList.size()%>;

	if (totalRowsM <= 0) {
		document.getElementById("noRecordsM").style.visibility = "visible";
		document.getElementById("noRecordsM").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}
	if (totalRowsD <= 0) {
		document.getElementById("noRecordsD").style.visibility = "visible";
		document.getElementById("noRecordsD").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}
	if (totalRowsR <= 0) {
		document.getElementById("noRecordsR").style.visibility = "visible";
		document.getElementById("noRecordsR").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}
	if (totalRowsP <= 0) {
		document.getElementById("noRecordsP").style.visibility = "visible";
		document.getElementById("noRecordsP").style.display = "block";
		document.getElementById("browseBorder").style.height = "25px";
	}

	function addSchedule(ltype)
	{
		setHiddenFields("");

		frm.formAction.value = "CREATE";
		frm.Schedule_scheduleType.value = ltype;
		popupParameters = "formAction=CREATE;Schedule_scheduleType=" + ltype + ";Schedule_icHeader=<%=s_ic_header%>;Schedule_documentType=<%=s_document_type%>;Schedule_lineNumber=" ;

		doSubmitToPopup('/orders/po_contract_add_schedule.jsp', 'DoNothing', 'width=675px', 'height=350px');
	}

	function updateSchedule(line_number, ltype)
	{
		setHiddenFields("");

		frm.formAction.value = "UPDATE";
		frm.Schedule_lineNumber.value = line_number;
		frm.Schedule_scheduleType.value = ltype;
		popupParameters = "formAction=UPDATE;Schedule_scheduleType=" + ltype + ";Schedule_icHeader=<%=s_ic_header%>;Schedule_documentType=<%=s_document_type%>;Schedule_lineNumber=" + line_number + ";" ;
		doSubmitToPopup('/orders/po_contract_add_schedule.jsp', 'ScheduleRetrieveById', 'width=675px', 'height=350px');
	}

	function deleteSchedule(line_number,ltype)
	{
		if (verifyAction("Delete this schedule?"))
		{
			frm.Schedule_lineNumber.value = line_number;
			frm.Schedule_scheduleType.value = ltype;

			//popupParameters = "formAction=DELETE;Schedule_scheduleType=" + ltype + ";Schedule_icHeader=<%=s_ic_header%>;Schedule_documentType=<%=s_document_type%>;Schedule_lineNumber=" + line_number + ";" ;
			//doSubmitToPopup('/orders/po_schedules.jsp', 'ScheduleDeleteById;ScheduleRetrieveAllLinesBy');

			frm.formAction.value = "DELETE";
			doSubmit('/orders/po_schedules.jsp', 'ScheduleDeleteById;ScheduleRetrieveAllLinesByHeader');
		}
		else
		{
			return false;
		}
	}

// End Hide script -->
</SCRIPT>


