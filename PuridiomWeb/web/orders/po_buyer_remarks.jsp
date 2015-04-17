<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
		PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
		BigDecimal icPoHeader = poHeader.getIcPoHeader();
		String	s_po_number = poHeader.getPoNumber();
		BigDecimal	bd_zero = new BigDecimal(0);
		BigDecimal	bd_release_number = poHeader.getReleaseNumber();
		BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
		String	s_po_status = poHeader.getStatus();
		String s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
		if (HiltonUtility.isEmpty(s_from_page))
		{
			//from main menu
			s_from_page = HiltonUtility.ckNull((String) request.getAttribute("fromPage"));
		}
		if (s_from_page.equalsIgnoreCase("review"))
		{
			s_from_page = "/orders/po_review.jsp";
		}
		else if (s_from_page.equalsIgnoreCase("summary"))
		{
			s_from_page = "/orders/po_summary.jsp";
		}

%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
<tsa:hidden name="isPoBuyerRemarks" value="no"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<br>
<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="590px" align="center">
<%	int reqlinecount = 0;
		List poLineList = (List) request.getAttribute("poLineList");
		List buyerRemarksList = (List) request.getAttribute("buyerRemarksList");
		List reqNumberList = new ArrayList();
		int procurementAccess = 0;
		if (poLineList != null && poLineList.size() > 0)
		{
			for (int i = 0; i < poLineList.size(); i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				String reqNumber = poLine.getRequisitionNumber();
				if (HiltonUtility.isEmpty(reqNumber) || reqNumberList.contains(reqNumber))
				{
					continue;
				}
				else
				{
					reqNumberList.add(reqNumber);
				}
				if (i > 0)
				{	
					procurementAccess++;
				%>
		<tr height="50px"><td colspan="4"><hr class=browseHR></td></tr>
<%			}	%>
		<tr>
			<td nowrap>
				<tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:&nbsp;<a href="javascript: reqPreview('<%=poLine.getIcReqLine()%>'); void(0);"><%=reqNumber%></a>&nbsp;
				<tsa:hidden name="RequisitionLine_icReqLine" value="<%=poLine.getIcReqLine()%>"/>
			</td>
			<td width="50px">&nbsp;</td>
			<td nowrap align="right">
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<a href="javascript: setCurrentRow('<%= headerEncoder.encodeForJavaScript(reqlinecount+"") %>'); browseStd('RequisitionHeader_buyerRemarks', 'BYRM'); void(0);"><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" /></a>:&nbsp;
			<%	} else { %>
				<tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" />:&nbsp;
			<%	} %>
			</td>
			<td>
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="60" maxLength="90" readonly="true" disabled="true" />
			<%	} else { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="60" maxLength="90" />
			<%	} %>
			</td>
		</tr>

<%			reqlinecount++;
				if (buyerRemarksList != null && buyerRemarksList.size() > 0)
				{
					List historyLogList = (List) buyerRemarksList.get(i);
%>
		<tr><td colspan="4"></td></tr>
		<tr>
			<td colspan="4">
				<table border=1 cellspacing=0 cellpadding=0 height=100% class="mnav">
				<tr>
					<td width="100%" align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="590px">
						<tr class="mnav" height="18px">
							<td nowrap class="mnav"><tsa:label labelName="date-and-time" defaultString="Date/Time" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="user" defaultString="User" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="description" defaultString="Description" /></td>
						</tr>
<%				if (historyLogList != null && historyLogList.size() > 0)
					{
						for (int j = 0; j < historyLogList.size(); j++)
						{
							HistoryLog history = (HistoryLog) historyLogList.get(j);
							String	s_user = history.getUserid();
							String	s_date = HiltonUtility.getFormattedDate(history.getLogDate(), oid, userDateFormat);
							String	s_time = history.getLogTime();
							PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
							String  s_timeZone = propertiesManager.getProperty("MISC","TIMEZONE","");
							String	s_description = history.getDescription();

							if (s_user == null)			{	s_user = "";			}
							if (s_date == null)			{	s_date = "";			}
							if (s_time == null)			{	s_time = "";			}
							if (s_timeZone == null)		{	s_timeZone = "";		}
							if (s_description == null)	{	s_description = "";	}

							UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
						<tr height="18px">
							<td nowrap valign="top"><%=s_date%>&nbsp;<%=s_time%>&nbsp;<%=s_timeZone%></td>
							<td width="15px">&nbsp;</td>
							<td nowrap valign="top"><%=userProfile.getDisplayName()%></td>
							<td width="15px">&nbsp;</td>
							<td valign="top"><%=s_description%></td>
						</tr>
<%					}
					}
					if (historyLogList == null || historyLogList.size() <= 0)
					{	%>
						<tr height="18px">
							<td colspan="5" align="center"><b><tsa:label labelName="no-procurement-remarks" defaultString="There are no procurement remarks available at this time!" /></b></td>
						</tr>
<%				}	%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<%			}
			}
		}
		if (procurementAccess == 0)
		{%>
			<tr>
				<td nowrap align="right">
				<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
					<a href="javascript: browseStd('PoHeader_buyerRemarks', 'BYRM'); void(0);"><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" /></a>:&nbsp;
				<%	} else { %>
					<tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" />:&nbsp;
				<%	} %>
				</td>
				<td>
				<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
					<tsa:input type="text" name="PoHeader_buyerRemarks" value="" size="60" maxLength="90" readonly="true" disabled="true" />
				<%	} else { %>
					<tsa:input type="text" name="PoHeader_buyerRemarks" value="" size="60" maxLength="90" />
				<%	} %>
				</td>
			</tr>
			<%List buyerPoRemarksList = (List) request.getAttribute("buyerPoRemarksList");
			if (buyerPoRemarksList != null && buyerPoRemarksList.size() > 0)
				{
					List historyLogList = (List) buyerPoRemarksList.get(0);%>
		<tr><td colspan="4"></td></tr>
		<tr>
			<td colspan="4">
				<table border=1 cellspacing=0 cellpadding=0 height=100% class="mnav">
				<tr>
					<td width="100%" align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="590px">
						<tr class="mnav" height="18px">
							<td nowrap class="mnav"><tsa:label labelName="date-and-time" defaultString="Date/Time" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="user" defaultString="User" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="description" defaultString="Description" /></td>
						</tr>
<%				if (historyLogList != null && historyLogList.size() > 0)
					{
						for (int j = 0; j < historyLogList.size(); j++)
						{
							HistoryLog history = (HistoryLog) historyLogList.get(j);
							String	s_user = history.getUserid();
							String	s_date = HiltonUtility.getFormattedDate(history.getLogDate(), oid, userDateFormat);
							String	s_time = history.getLogTime();
							PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
							String  s_timeZone = propertiesManager.getProperty("MISC","TIMEZONE","");
							String	s_description = history.getDescription();

							if (s_user == null)			{	s_user = "";			}
							if (s_date == null)			{	s_date = "";			}
							if (s_time == null)			{	s_time = "";			}
							if (s_timeZone == null)		{	s_timeZone = "";		}
							if (s_description == null)	{	s_description = "";	}

							UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
						<tr height="18px">
							<td nowrap valign="top"><%=s_date%>&nbsp;<%=s_time%>&nbsp;<%=s_timeZone%></td>
							<td width="15px">&nbsp;</td>
							<td nowrap valign="top"><%=userProfile.getDisplayName()%></td>
							<td width="15px">&nbsp;</td>
							<td valign="top"><%=s_description%></td>
						</tr>
<%					}
					}
					if (historyLogList == null || historyLogList.size() <= 0)
					{	%>
						<tr height="18px">
							<td colspan="5" align="center"><b><tsa:label labelName="no-procurement-remarks" defaultString="There are no procurement remarks available at this time!" /></b></td>
						</tr>
<%				}	%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<%			}
	}%>

		</table>
	</td>
</tr>
</table>

<br>
<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (user.isABuyer() ) {	%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: updateBuyerRemarks(); void(0);"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
<%	}	%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%= headerEncoder.encodeForJavaScript(s_from_page) %>', 'PoRetrieve'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie("/orders/po_buyer_remarks.jsp", "PoLineRetrieveByHeader", "Procurement Remarks");

	function updateBuyerRemarks()
	{
		<%if(reqlinecount > 0)
		{%>
			if (frm.RequisitionHeader_buyerRemarks)
			{
				frm.RequisitionHeader_buyerRemarks.value = trim(frm.RequisitionHeader_buyerRemarks);
				if (frm.RequisitionHeader_buyerRemarks.value == "")
				{
					alert("<tsa:label labelName='remark-save-to' defaultString='Please enter a Remark to Save' />");
					return false;
				}
			}
			doSubmit('<%= headerEncoder.encodeForJavaScript(s_from_page) %>', 'PoUpdateBuyerRemarks;PoRetrieve');
		<%}
		else
		{%>
			if (frm.PoHeader_buyerRemarks)
			{
				frm.PoHeader_buyerRemarks.value = trim(frm.PoHeader_buyerRemarks);
				if (frm.PoHeader_buyerRemarks.value == "")
				{
					alert("<tsa:label labelName='remark-save-to' defaultString='Please enter a Remark to Save' />");
					return false;
				}
			}
			doSubmit('<%= headerEncoder.encodeForJavaScript(s_from_page) %>', 'PoUpdateBuyerRemarksFromNothing;PoRetrieve');
		<%}%>
	}

	function setPoBuyerRemarks(isPofromNothing)
	{
		frm.isPoBuyerRemarks.value = isPoFromNothing;
	}

	function reqPreview(ic)
	{
		popupParameters = "RequisitionLine_icReqLine=" + ic;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieveByLine', 'WIDTH=680px', 'HEIGHT=540px');
	}

// End Hide script -->
</SCRIPT>