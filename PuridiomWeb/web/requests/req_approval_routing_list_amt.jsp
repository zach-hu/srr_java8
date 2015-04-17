<%@ page import="java.text.NumberFormat" %>
<table id="routingTable" border="0" cellspacing="2" cellpadding="0" width="665px" class="<%=backgroundClass%>">
<tsa:tr>
	<tsa:td>
<%
		NumberFormat currencyFormat  = NumberFormat.getCurrencyInstance();
		List routingList = (List) request.getAttribute("routingList");
		if (routingList == null) { routingList = new ArrayList(); }
%>
		<br>

		<table border="0" cellspacing="0" cellpadding="0" width="655px" class="summary">
		<tsa:tr height="20px">
			<tsa:td width="100%" align="center" valign="top">
				<div id="browseBorder" class="browseHdrDk" style="border: solid 2px; background: #FFFFFF; padding: 0px; width: 655px; align: center; overflow-y: auto; overflow-x: auto;">
				<table border="0" cellspacing="0" cellpadding="1" width="651px" height="18px">
				<tsa:tr>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="5%"><img src="<%=contextPath%>/images/none.gif" border="0" width="10px" height="0"></tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="22%" field="routingApproverName"><tsa:label labelName="routingApproverName" defaultString="Approver Name"/>&nbsp;</tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="22%" field="routing-callForward"><tsa:label labelName="routing-callForward" defaultString="Call Forward"/>&nbsp;</tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="22%" field="routingBackupApprover"><tsa:label labelName="routingBackupApprover" defaultString="Backup Approver"/>&nbsp;</tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="13%" field="routingAssigned" align="right"><tsa:label labelName="routingAssigned" defaultString="Date Assigned"/>&nbsp;</tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="15%" field="routingApproved" align="right"><tsa:label labelName="routingApproved" defaultString="Date Approved"/>&nbsp;</tsa:td>
					<tsa:td cssClass="browseHdrDk" noWrap="nowrap" width="4%" align="right"><img src="<%=contextPath%>/images/none.gif" border="0" width="10px" height="1px"></tsa:td>
				</tsa:tr>
				</table>
				<table id="approvers0" border="0" cellpadding="1" cellspacing="0" width="651px" class="browseRow">
<%			for (int i = 0; i < routingList.size(); i++) {
					ApprovalLog appLog = (ApprovalLog) routingList.get(i);
					String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
					String classType = "browseRow";
					if (appLog.getApproved().equals("A")) {
						s_approved_date = "<b>Current Approver</b>";
						classType = "selectedRow";
					}

					String	s_approver = appLog.getComp_id().getUserId();
					String	s_callForward = appLog.getCallForward();
					String	backupApproverName = "";
					String	s_altApprover = appLog.getAlternateUserid();
					String	callForwardName = "";
					String	altApproverMsg = "";
					UserProfile approver = UserManager.getInstance().getUser(oid, s_approver);
					UserProfile altApprover = UserManager.getInstance().getUser(oid, s_altApprover);
					UserProfile backupApprover = UserManager.getInstance().getUser(oid, appLog.getBackupApprover());

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
					backupApproverName = backupApprover.getDisplayName();
					if (HiltonUtility.isEmpty(backupApproverName)) {
						backupApproverName = "&nbsp;-&nbsp";
					}
					if (!HiltonUtility.isEmpty(appLog.getApproverNotes())) {
						approvalNotes.put(s_approver, appLog.getApproverNotes());
					}
					if (appLog.getApproved().equals("Y")) {
						if (appLog.getApproverType().equals("P")) {
							altApproverMsg = altApprover.getDisplayName() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnBehalf", "approved on behalf of") + "  " + appLog.getPooldesc() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalPool", "Pool");
						} else if (!s_approver.equals(s_altApprover) && !appLog.isAnFyiApprover()) {
							altApproverMsg = altApprover.getDisplayName() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnBehalf", "approved on behalf of") + "  " + approver.getDisplayName();
						}
					} else if(appLog.getApproved().equals("D")){
						String	s_approver_deferred = appLog.getDeferId();
						if(!HiltonUtility.isEmpty(s_approver_deferred))
						{
							UserProfile approverDeferred = UserManager.getInstance().getUser(oid, s_approver_deferred);
							altApproverMsg = approver.getDisplayName() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnDefer", "deferred approval to") + "  " + approverDeferred.getDisplayName();
						}
					}
		%>
				<tsa:tr height="15px">
					<tsa:td width="5%" cssClass="<%=classType%>" noWrap="nowrap" align="right">
		<%		if (appLog.getApproved().equals("Y"))	{
						 if (appLog.isAnAdvisor()) {
						 	if (appLog.getRecommendation().equals("Y")) {%>
						<img src="<%=contextPath%>/images/check_partial.gif">
		<%				} else if (appLog.getRecommendation().equals("R")) {%>
						<img src="<%=contextPath%>/images/reject_partial.gif">
		<%				}
						} else {%>
						<img src="<%=contextPath%>/images/check.gif">
		<%			}
					} %>
						<b><span id="sequence_0_<%=i%>"><%=appLog.getComp_id().getSequence()%></span></b>
						&nbsp;
					</tsa:td>
		<%		if (!HiltonUtility.isEmpty(altApproverMsg))	{ %>
					<tsa:td colspan="2" field="routingApproverName" cssClass="<%=classType%>" noWrap="nowrap">
						<%=altApproverMsg%>
					</tsa:td>
		<%		} else {%>
					<tsa:td width="22%" field="routingApproverName" cssClass="<%=classType%>" noWrap="nowrap">
						<%=appLog.getApproverName()%>
			<%		if (appLog.isAnFyiApprover()) {%>
						<img src="<%=contextPath%>/images/fyi_text_blue.gif" border="0" alt="This approver will receive an FYI notification upon approval of this requisition.">
			<%		} else if (appLog.isAnAdvisor()) {%>
						<img src="<%=contextPath%>/images/double_astrick.gif" border="0" alt='<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAdvisorMsg", "Advisor - will make a recommendation for or against the approval of this requisition.")%>'>
			<%		}%>
					</tsa:td>
					<tsa:td width="22%" field="routing-callForward" cssClass="<%=classType%>" noWrap="nowrap"><span id="callforwardText_0_<%=i%>"><%=callForwardName%>&nbsp;</span></tsa:td>
		<%		}%>
					<tsa:td width="22%" field="routingBackupApprover" cssClass="<%=classType%>" noWrap="nowrap"><span id="backupApproverText_0_%=i%&gt;"><%=backupApproverName%>&nbsp;</span></tsa:td>
					<tsa:td width="13%" field="routingAssigned" cssClass="<%=classType%>" align="right" noWrap="nowrap"><%=HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat)%></tsa:td>
					<tsa:td width="15%" field="routingApproved" cssClass="<%=classType%>" align="right" noWrap="nowrap"><%=s_approved_date%></tsa:td>
					<tsa:td width="2%" noWrap="nowrap" cssClass="<%=classType%>" align="center">
						<tsa:hidden name="originalApprovalAmount" value="<%=appLog.getApproverAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approvalAmount" value="<%=appLog.getApproverAmount()%>"/>
					</tsa:td>
					<tsa:td width="2%" noWrap="nowrap" cssClass="<%=classType%>" align="center">
		<%		if (!HiltonUtility.isEmpty(appLog.getRuleNotes())) {%>
						<a href="javascript: getRuleNotes(<%=0%>,<%=i%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" alt="View approval rule notes." border="0"></a>
		<%		}%>
					</tsa:td>
					<span id="hiddenFields_0_<%=i%>">
						<tsa:hidden name="approverName" value="<%=approver.getDisplayName()%>"/>
						<tsa:hidden name="approverUserType" value="<%=approver.getUserType()%>"/>
						<tsa:hidden name="ruleNotes" value="<%=appLog.getRuleNotes()%>"/>
						<tsa:hidden name="tableIndex" value="0"/>
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
				</tsa:tr>
				<tsa:tr>
					<tsa:td cssClass="<%=classType%>">&nbsp;</tsa:td>
					<tsa:td colspan="6" cssClass="<%=classType%>">
						<table border="0" cellpadding="2" cellspacing="0" class="<%=classType%>">
						<tsa:tr><tsa:td cssClass="<%=classType%>"><tsa:label labelName="routingApprovalRule" defaultString="Approval Rule"/>:</tsa:td><tsa:td><%=appLog.getUdfValues()%></tsa:td></tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
			<%	if ((i+1) < routingList.size()) {%>
				<tsa:tr><tsa:td colspan="7"><img src="<%=contextPath%>/images/none.gif" border="0" height="1px" width="100%" class="browseHdrDk"></tsa:td></tsa:tr>
		<%		}
				} %>
				</table>
		        </div>
		      </tsa:td>
		  </tsa:tr>
		</table>
		<br>
		<table border="0" cellspacing="2" cellpadding="0" class="<%=backgroundClass%>" width="100%">
		<tsa:tr><tsa:td align="center" cssClass="<%=backgroundClass%>">* <tsa:label labelName="app-multipleApproverDesc" defaultString="* Approvers listed multiple times will only receive a notification to approve once."/>.</tsa:td></tsa:tr>
		<tsa:tr field="routingAdvisorMsg"><tsa:td align="center"><img src="<%=contextPath%>/images/double_astrick.gif" border="0">&nbsp;<tsa:label labelName="routingAdvisorMsg" defaultString="Advisor - will make a recommendation for or against the approval of this requisition."/></tsa:td></tsa:tr>
		</table>
		<br>
	</tsa:td>
</tsa:tr>
</table>