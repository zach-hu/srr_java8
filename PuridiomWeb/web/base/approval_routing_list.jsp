<%@ page import="java.text.NumberFormat" %>
<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<table id=routingTable border=0 cellspacing=2 cellpadding=0 width=665px class=<%=backgroundClass%>>
<tr>
	<td>
<%
		Map routingListGroups = (Map) request.getAttribute("routingListGroups");
		List approvalRuleList = (List) request.getAttribute("finalRuleList");
		String poolApprover = HiltonUtility.ckNull((String) request.getAttribute("poolApprover"));
		NumberFormat currencyFormat  = NumberFormat.getCurrencyInstance();
		int iapprovers = 0;

		if (approvalRuleList == null) {
			approvalRuleList = new ArrayList();
		}

		for (int ir=0; ir < approvalRuleList.size(); ir++) {
			String approvalRule = (String) approvalRuleList.get(ir);
			Map ruleMap = (Map) routingListGroups.get(approvalRule);
			List routingList = (List) ruleMap.get("ruleRoutingList");
			BigDecimal amountToApprove = (BigDecimal) ruleMap.get("amountToApprove");
			if (routingList == null) { routingList = new ArrayList(); }
%>
		<br>
<%	if (!HiltonUtility.isEmpty(approvalRule)) {%>
		<table border="0" cellspacing="0" cellpadding="0" class=<%=backgroundClass%>>
		<tr>
			<td class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalRule", "Approval Rule")%>:</td>
			<td><%=approvalRule%></td>
		</tr>
		<tr>
			<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingRequiredAuthority", "Required Authority")%>:</td>
			<td><%=HiltonUtility.getCurrencyConvert(amountToApprove, currencyCode, oid)%></td>

		</tr>
		</table>
<%	}%>
		<table border="0" cellspacing="0" cellpadding="0" width=655px class=summary>
		<tr height="20px">
			<td width=100% align=center valign=top>
				<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 0px; width:655px; align:center; overflow-y:auto; overflow-x:auto;">
				<table border="0" cellspacing="0" cellpadding="1" width="651px" height=18px>
				<tr>
					<td class="browseHdrDk" nowrap width="5%"><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=0></td>
					<td class="browseHdrDk" nowrap width="22%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver Name")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="22%" <%=HtmlWriter.isVisible(oid, "routingCallForward")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingCallForward", "Call Forward")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="22%" <%=HtmlWriter.isVisible(oid, "routingBackupApprover")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingBackupApprover", "Backup Approver")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="13%" <%=HtmlWriter.isVisible(oid, "routingAssigned")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAssigned", "Date Assigned")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="15%" <%=HtmlWriter.isVisible(oid, "routingApproved")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproved", "Date Approved")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="4%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></td>
				</tr>
				</table>
				<table id="approvers<%=ir%>" border="0" cellpadding="1" cellspacing="0" width="651px" class="browseRow">
		<%	boolean authorityFound = false;
				int irow = 0;

				for (int i = 0; i < routingList.size(); i++) {
					ApprovalLog appLog = (ApprovalLog) routingList.get(i);
					String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
					String classType = "browseRow";

					String	s_approver = appLog.getComp_id().getUserId();
					String	s_callForward = appLog.getCallForward();
					String	s_altApprover = appLog.getAlternateUserid();
					String	s_backupApprover = appLog.getBackupApprover();

					if (appLog.getApproved().equals("A")) {
						s_approved_date = "Current Approver";
						classType = "selectedRow";
					}

					UserProfile approver = UserManager.getInstance().getUser(oid, s_approver);
					UserProfile altApprover = UserManager.getInstance().getUser(oid, s_altApprover);
					UserProfile backupApprover = UserManager.getInstance().getUser(oid, s_backupApprover);
					String	callForwardName = "";
					String	backupApproverName = backupApprover.getDisplayName();
					String	altApproverName = altApprover.getDisplayName();
					String	altApproverMsg = "";

					if (!s_callForward.startsWith("@") && !s_approver.equals(s_callForward)) {
						UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());
						callForwardName = callForward.getDisplayName();
					} else if (s_callForward.startsWith("@") && !s_approver.equals(appLog.getPoolid())) {
						// Call Forward for user is set to a pool
						callForwardName = appLog.getPooldesc();
					}
					if (HiltonUtility.isEmpty(callForwardName)) {
						callForwardName = "&nbsp;-&nbsp";
					}

					if (HiltonUtility.isEmpty(backupApproverName)) {
						backupApproverName = "&nbsp;-&nbsp";
					}
					if (!HiltonUtility.isEmpty(appLog.getApproverNotes())) {
						if (!s_approver.equals(s_altApprover) && !HiltonUtility.isEmpty(s_altApprover)) {
							approvalNotes.put(altApproverName + " (" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "onBehalf", "on behalf of") + " " + approver.getDisplayName() + ") ", appLog.getApproverNotes());
						} else {
							approvalNotes.put(approver.getDisplayName(), appLog.getApproverNotes());
						}
					}
					if (appLog.getApproved().equals("Y")) {
						if (appLog.getApproverType().equals("P")) {
							altApproverMsg = altApproverName + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnBehalf", "approved on behalf of") + "  " + appLog.getPooldesc() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalPool", "Pool");
						} else if (!s_approver.equals(s_altApprover)) {
							altApproverMsg = altApproverName + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnBehalf", "approved on behalf of") + "  " + approver.getDisplayName();
						}
					}
		%>
				<tr height="15px">
					<td width="5%" class="<%=classType%>" NOWRAP align="right">
		<%		if (appLog.getApproved().equals("Y"))	{%>
						<img src="<%=contextPath%>/images/check.gif">
		<%		} %>
						<span id="sequence_<%=ir%>_<%=irow%>"><%=irow + 1%></span>
						&nbsp;
					</td>
		<%		if (!HiltonUtility.isEmpty(altApproverMsg))	{ %>
					<td colspan=2 <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>" NOWRAP>
						<%=altApproverMsg%>
					</td>
		<%		} else {%>
					<td width="22%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>" NOWRAP>
						<%=appLog.getApproverName()%>
					</td>
					<td width="22%" <%=HtmlWriter.isVisible(oid, "routingCallForward")%> class="<%=classType%>" NOWRAP><span id="callforwardText_<%=ir%>_<%=irow%>"><%=callForwardName%>&nbsp;</span></td>
		<%		}%>
					<td width="22%" <%=HtmlWriter.isVisible(oid, "routingBackupApprover")%> class="<%=classType%>" NOWRAP><span id="backupApproverText_<%=ir%>_<%=irow%>"><%=backupApproverName%>&nbsp;</span></td>
					<td width="13%" <%=HtmlWriter.isVisible(oid, "routingAssigned")%> class="<%=classType%>" align="right" NOWRAP><%=HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat)%></td>
					<td width="15%" <%=HtmlWriter.isVisible(oid, "routingApproved")%> class="<%=classType%>" align="right" NOWRAP><%=s_approved_date%></td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
						<tsa:hidden name="originalApprovalAmount" value="<%=appLog.getApproverAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approvalAmount" value="<%=appLog.getApproverAmount()%>"/>
					</td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
		<%		if (!HiltonUtility.isEmpty(appLog.getRuleNotes())) {%>
						<a href="javascript: getRuleNotes(<%=ir%>,<%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" alt="View approval rule notes." border="0"></a>
		<%		}%>
					</td>
					<span id="hiddenFields_<%=ir%>_<%=irow%>">
						<tsa:hidden name="approverName" value="<%=approver.getDisplayName()%>"/>
						<tsa:hidden name="approverUserType" value="<%=approver.getUserType()%>"/>
						<tsa:hidden name="ruleNotes" value="<%=appLog.getRuleNotes()%>"/>
						<tsa:hidden name="tableIndex" value="<%=ir%>"/>
						<tsa:hidden name="ApprovalLog_userId" value="<%=appLog.getComp_id().getUserId()%>"/>
						<tsa:hidden name="ApprovalLog_amount" value="<%=appLog.getAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approverAmount" value="<%=appLog.getApproverAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approved" value="<%=appLog.getApproved()%>"/>
						<tsa:hidden name="ApprovalLog_udfValues" value="<%=appLog.getUdfValues()%>"/>
						<tsa:hidden name="ApprovalLog_authorized" value="<%=appLog.getAuthorized()%>"/>
						<tsa:hidden name="ApprovalLog_alternateUserid" value="<%=appLog.getAlternateUserid()%>"/>
						<tsa:hidden name="ApprovalLog_approverType" value="<%=appLog.getApproverType()%>"/>
						<tsa:hidden name="ApprovalLog_ruleType" value="<%=appLog.getRuleType()%>"/>
						<tsa:hidden name="ApprovalLog_poolid" value="<%=appLog.getPoolid()%>"/>
						<tsa:hidden name="ApprovalLog_pooldesc" value="<%=appLog.getPooldesc()%>"/>
						<tsa:hidden name="ApprovalLog_ruleAction" value="<%=appLog.getRuleAction()%>"/>
						<tsa:hidden name="ApprovalLog_approverSig" value="<%=appLog.getApproverSig()%>"/>
						<tsa:hidden name="ApprovalLog_callForward" value="<%=appLog.getCallForward()%>"/>
						<tsa:hidden name="ApprovalLog_approverName" value="<%=appLog.getApproverName()%>"/>
					</span>
				</tr>
		<%
					iapprovers++;
					irow++;
				} %>
				</table>
		        </div>

		        <tsa:hidden name="approvalRule" value="<%=approvalRule%>"/>
		        <tsa:hidden name="amountToApprove" value="<%=amountToApprove%>"/>
		      </td>
		  </tr>
		</table>
<%		}%>
		<br>
		<table border=0 cellspacing=2 cellpadding=0 class="<%=backgroundClass%>" width=100%>
		<tr><td align=center class="<%=backgroundClass%>">* <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-multipleApproverDesc", "Approvers listed multiple times will only receive a notification to approve once.")%></tr>
		</table>
		<br>
	</td>
</tr>
</table>