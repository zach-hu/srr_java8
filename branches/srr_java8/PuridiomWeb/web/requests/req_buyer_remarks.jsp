<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
		RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
		HistoryLog historyLog = (HistoryLog) request.getAttribute("historyLog");
		String s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
		if (HiltonUtility.isEmpty(s_from_page))
		{
			//from main menu
			s_from_page = HiltonUtility.ckNull((String) request.getAttribute("fromPage"));
		}
		if (s_from_page.equalsIgnoreCase("review"))
		{
			s_from_page = "/requests/req_review.jsp";
		}
		else if (s_from_page.equalsIgnoreCase("summary"))
		{
			s_from_page = "/requests/req_summary.jsp";
		}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=requisitionHeader.getIcReqHeader()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=headerEncoder.encodeForHTML(requisitionHeader.getRequisitionNumber())%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="125px"><%=DocumentStatus.toString(requisitionHeader.getStatus(), oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<%	if (user.isABuyer()) {	%>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td valign="top" align="center">
		<table border="0" cellspacing="0" cellpadding="0" height="100%">
		<tsa:tr>
			<tsa:td>
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<a href="javascript: browseStd('RequisitionHeader_buyerRemarks', 'BYRM'); void(0);"><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks"></tsa:label>:</a>&nbsp;
			<%	} else { %>
				<tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks"></tsa:label>:&nbsp;
			<%	} %>
			</tsa:td>
			<tsa:td>
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="55" maxLength="90" readOnly="readonly" disabled="disabled"></tsa:input>
			<%	} else { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="55" maxLength="90"></tsa:input>
			<%	} %>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>
<%	}	%>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td valign="top" align="center">
		<table border="1" cellspacing="0" cellpadding="0" height="100%" class="mnav">
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="645px">
				<tsa:tr cssClass="mnav" height="18px">
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="dateTime" defaultString="Date/Time"></tsa:label></tsa:td>
					<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="user" defaultString="User"></tsa:label></tsa:td>
					<tsa:td width="15px" cssClass="mnav">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" cssClass="mnav"><tsa:label labelName="description" defaultString="Description"></tsa:label></tsa:td>
				</tsa:tr>
<%
	List	historyLogList = (List) request.getAttribute("historyLogList");
	if (historyLogList != null)
	{
		for (int i = 0; i < historyLogList.size(); i++)
		{
			HistoryLog history = (HistoryLog) historyLogList.get(i);
			String	s_user = history.getUserid();
			String	s_date = HiltonUtility.getFormattedDate(history.getLogDate(), oid, userDateFormat);
			String	s_time = history.getLogTime();
			String	s_description = history.getDescription();

			if (s_user == null)			{	s_user = "";			}
			if (s_date == null)			{	s_date = "";			}
			if (s_time == null)			{	s_time = "";			}
			if (s_description == null)	{	s_description = "";	}

			UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
				<tsa:tr height="18px">
					<tsa:td noWrap="nowrap" valign="top"><%=s_date%>&nbsp;<%=s_time%></tsa:td>
					<tsa:td width="15px">&nbsp;</tsa:td>
					<tsa:td noWrap="nowrap" valign="top"><%=userProfile.getDisplayName()%></tsa:td>
					<tsa:td width="15px">&nbsp;</tsa:td>
					<tsa:td valign="top"><%=s_description%></tsa:td>
				</tsa:tr>
<%
		}
	}
	if (historyLogList == null || historyLogList.size() <= 0)
	{
%>
				<tsa:tr height="18px">
					<tsa:td colspan="5" align="center"><b><tsa:label labelName="thereAreNoProcurementRemarks" defaultString="There are no procurement remarks available at this time"></tsa:label>!</b></tsa:td>
				</tsa:tr>
<%
		}
%>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
<%	if (user.isABuyer()) {	%>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitThis('<%=s_from_page%>', 'RequisitionUpdateBuyerRemarks;RequisitionRetrieve'); void(0);"><tsa:label labelName="req-save" defaultString="Save"></tsa:label></a></div></tsa:td>
<%	}	%>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('<%=s_from_page%>', 'RequisitionRetrieve'); void(0);"><tsa:label labelName="req-return" defaultString="Return"></tsa:label></a></div></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie("/requests/req_buyer_remarks.jsp", "RequisitionHeaderRetrieveById", "Procurement Remarks");

	function submitThis(page, handlers)
	{
		if (frm.RequisitionHeader_buyerRemarks)
		{
			frm.RequisitionHeader_buyerRemarks.value = trim(frm.RequisitionHeader_buyerRemarks);
			if (frm.RequisitionHeader_buyerRemarks.value == "")
			{
				alert("Please enter a Remark to Save");
				return false;
			}
		}
		doSubmit(page, handlers);
	}

// End Hide script -->
</SCRIPT>