<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
		RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
		BigDecimal icRfqHeader = rfqHeader.getIcRfqHeader();
		String s_rfqNumber = rfqHeader.getRfqNumber();
		String s_rfqAmendment = rfqHeader.getRfqAmendment();
		String	s_rfqStatus = rfqHeader.getStatus();
		String s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
		if (HiltonUtility.isEmpty(s_from_page))
		{
			//from main menu
			s_from_page = HiltonUtility.ckNull((String) request.getAttribute("fromPage"));
		}
		if (s_from_page.equalsIgnoreCase("review"))
		{
			s_from_page = "/rfq/rfq_review.jsp";
		}
		else if (s_from_page.equalsIgnoreCase("summary"))
		{
			s_from_page = "/rfq/rfq_summary.jsp";
		}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=icRfqHeader%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
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

<br>
<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="590px" align="center">
<%	int reqlinecount = 0;
		List rfqLineList = (List) request.getAttribute("rfqLineList");
		List buyerRemarksList = (List) request.getAttribute("buyerRemarksList");
		List reqNumberList = (List) request.getAttribute("reqNumberList");
		List reqDisplayList = new ArrayList();
		if (rfqLineList != null && reqNumberList != null)
		{
			for (int i = 0; i < rfqLineList.size(); i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				String reqNumber = (String) reqNumberList.get(i);
				if (HiltonUtility.isEmpty(reqNumber) || reqDisplayList.contains(reqNumber) || reqNumber.equals("0"))
				{
					continue;
				}
				else
				{
					reqDisplayList.add(reqNumber);
				}
				if (i > 0 && reqlinecount > 0)
				{	%>
		<tr height="50px"><td colspan="4"><hr class=browseHR></td></tr>
<%			}	%>
		<tr>
			<td nowrap>
				<tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:&nbsp;<a href="javascript: reqPreview('<%=rfqLine.getIcReqLine()%>'); void(0);"><%=reqNumber%></a>&nbsp;
				<tsa:hidden name="RequisitionLine_icReqLine" value="<%=rfqLine.getIcReqLine()%>"/>
			</td>
			<td width="50px">&nbsp;</td>
			<td nowrap align="right">
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<a href="javascript: setCurrentRow('<%=reqlinecount%>'); browseStd('RequisitionHeader_buyerRemarks', 'BYRM'); void(0);"><tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" /></a>:&nbsp;
			<%	} else { %>
				<tsa:label labelName="procurementRemarks" defaultString="Procurement Remarks" />:&nbsp;
			<%	} %>
			</td>
			<td>
			<%	if (DictionaryManager.isLink(oid, "procurementRemarks")) { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="60" maxLength="90" readonly="true" disabled="true" labelName="procurementRemarks" />
			<%	} else { %>
				<tsa:input type="text" name="RequisitionHeader_buyerRemarks" value="" size="60" maxLength="90" labelName="procurementRemarks" />
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
							<td nowrap class="mnav"><tsa:label labelName="rfq_datetime" defaultString="Date/Time" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="rfq_user" defaultString="User" /></td>
							<td width="15px" class="mnav">&nbsp;</td>
							<td nowrap class="mnav"><tsa:label labelName="rfq_description" defaultString="Description" /></td>
						</tr>
<%				if (historyLogList != null && historyLogList.size() > 0)
					{
						for (int j = 0; j < historyLogList.size(); j++)
						{
							HistoryLog history = (HistoryLog) historyLogList.get(j);
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
						<tr height="18px">
							<td nowrap valign="top"><%=s_date%>&nbsp;<%=s_time%></td>
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
							<td colspan="5" align="center"><b><tsa:label labelName="msg_no_proc_remark" defaultString="There are no procurement remarks available at this time!" /></b></td>
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
		if (reqlinecount == 0)
		{	%>
		<tr><td colspan="4" align="center"><b><tsa:label labelName="msg_no_req_item" defaultString="There are no requisition items on this solicitation" />.</b></td></tr>
<%	}	%>
		</table>
	</td>
</tr>
</table>

<br>
<br>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (user.isABuyer() && reqlinecount > 0) {	%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('<%=s_from_page%>', 'RfqUpdateBuyerRemarks;RfqRetrieve'); void(0);">Save</a></div></td>
<%	}	%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_from_page%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	setNavCookie("/rfq/rfq_buyer_remarks.jsp", "RfqLineRetrieveByHeader", "Procurement Remarks");

	function reqPreview(ic)
	{
		popupParameters = "RequisitionLine_icReqLine=" + ic;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieveByLine', 'WIDTH=680px', 'HEIGHT=540px');
	}

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
		doSubmit(page, handlers)
	}

// End Hide script -->
</SCRIPT>