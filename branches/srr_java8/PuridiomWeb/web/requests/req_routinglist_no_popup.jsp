<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.ApprovalLog" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%@ page import="com.tsagate.foundation.utility.Utility" %>
<%
	Map routingListGroups = (Map) request.getAttribute("routingListGroups");
	List approvalRuleList = (List) request.getAttribute("finalRuleList");
	List accountCar = (List) request.getAttribute("accountCar");
	List balanceCar = (List) request.getAttribute("balanceCar");
	if (accountCar == null) {
		accountCar =  new ArrayList();
	}
	if (balanceCar == null) {
		balanceCar =  new ArrayList();
	}
	RequisitionHeader reqHeader = (RequisitionHeader)request.getAttribute("requisitionHeader");
	String ruleStatus = HiltonUtility.ckNull((String) request.getAttribute("ruleStatus"));
	String ruleError = Utility.ckNull((String) request.getAttribute("ruleError"));
	String noApprovalNeed = HiltonUtility.ckNull((String)request.getAttribute("NoApprovalNeed"));
	String onlyApproval = HiltonUtility.ckNull((String)request.getAttribute("OnlyApproval"));
	String noApprovalNeedAutoAward = HiltonUtility.ckNull((String)request.getAttribute("NoApprovalNeedAutoAward"));
	String belowNoApproval = HiltonUtility.ckNull((String)request.getAttribute("BelowNoApproval"));
	String rqSubType = HiltonUtility.ckNull((String)request.getAttribute("RequisitionHeader_rqSubType"));
	String noApprovalNeedWithOtherApproval = HiltonUtility.ckNull((String) request.getAttribute("ApprovalNeedWithOtherApproval"));
	String buyerCommodityCode = HiltonUtility.ckNull((String)request.getAttribute("BuyerCommodityCode"));
	String extraSubjectFYI = HiltonUtility.ckNull((String) request.getAttribute("extraSubjectFYI"));
	String byPassRoutingListString = HiltonUtility.ckNull((String) request.getAttribute("byPassRoutingList"));
	String reqAction = (String)request.getAttribute("reqaction");
	String pricingAction = HiltonUtility.ckNull((String)request.getAttribute("pricingAction"));
	String s_req_number = reqHeader.getRequisitionNumber();
	String s_curr_code = reqHeader.getCurrencyCode();
	String accountStr = "";
	String userDefinedRuleNotes = HiltonUtility.ckNull((String) request.getAttribute("userDefinedRuleNotes"));
	boolean userDefinedApprovals = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "UserDefinedApprovals", "N").equals("Y");
	boolean enforceExcludeLess = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "EnforceExcludeLess", "N").equals("Y");
	boolean sendToAlwaysChecked = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "SENDTO-ALWAYSCHECKED", "N").equals("Y");
	boolean keepCheckedBoxDisplay = PropertiesManager.getInstance(oid).getProperty("APPROVERS","REUSEAPPROVERS","N").equals("Y");
	boolean displayApprovalPolicy = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "DISPLAYDEFAULTPOLICY", "N").equals("Y");
	boolean byPassRoutingList = byPassRoutingListString.equalsIgnoreCase("true");
	boolean bPassed = !ruleStatus.equalsIgnoreCase("FAILED");
	boolean byPassCarOverBudget = false;
	boolean bForward = false;
	int	iapprovers = 0;

	List requisitionLineList = (List) request.getAttribute("requisitionLineList");
	NumberFormat currencyFormat  = NumberFormat.getCurrencyInstance();

	if (reqAction == null) { reqAction = "FORWARD"; }

	if (userDefinedApprovals) {
		String approvalRule = "USER-DEFINED";
		Map ruleMap = (Map) routingListGroups.get(approvalRule);
		if (ruleMap == null) {
			ruleMap = new HashMap();
			List routingList = (List) ruleMap.get("ruleRoutingList");
			if (routingList == null) {
				routingList =  new ArrayList();
			}
			BigDecimal amountToApprove = (BigDecimal) ruleMap.get("amountToApprove");
			if (amountToApprove == null) {
				amountToApprove = new BigDecimal(0);
			}

			ruleMap.put("amountToApprove", amountToApprove);
			ruleMap.put("ruleRoutingList", routingList);
			routingListGroups.put(approvalRule, ruleMap);

			approvalRuleList.add(approvalRule);
		}
	}

	boolean enableDistributionList = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "EnableDistributionList", "N").equals("Y");
	if(keepCheckedBoxDisplay)
	{
		if (reqHeader.getStatus().compareTo(DocumentStatus.REQ_REJECTED) >= 0 && reqHeader.getStatus().compareTo(DocumentStatus.REQ_RECALLED) <= 0)
		{
			keepCheckedBoxDisplay = true;
		}
		else if(reqHeader.getStatus().compareTo(DocumentStatus.REQ_PLANNING_RECALLED) >= 0 && reqHeader.getStatus().compareTo(DocumentStatus.REQ_PLANNING_REJECTED) <= 0)
		{
			keepCheckedBoxDisplay = true;
		}
		else
		{
			keepCheckedBoxDisplay = false;
		}
	}
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/dynamicTables.js' type='text/javascript'></script>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%= reqHeader.getIcReqHeader() %>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=reqHeader.getRequisitionType()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionerCode" value="<%=reqHeader.getRequisitionerCode()%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=rqSubType%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="formField" value="ApprovalLog_userId"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="reqaction" value="<%=reqAction%>"/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>
<tsa:hidden name="fromPage" value="routinglist"/>
<tsa:hidden name="addUser" value=""/>
<tsa:hidden name="addUserRule" value=""/>
<tsa:hidden name="addUserAmount" value=""/>
<tsa:hidden name="fyiApprover" value=""/>
<tsa:hidden name="insertBefore" value="0"/>
<tsa:hidden name="extraSubjectFYI" value="<%=extraSubjectFYI%>"/>
<tsa:hidden name="returnTo" value=""/>
<tsa:hidden name="routTo" value=""/>
<tsa:hidden name="approveForAll" value="N"/>
<tsa:hidden name="NoApprovalNeed" value=""/>
<tsa:hidden name="NoApprovalNeedAutoAward" value=""/>
<tsa:hidden name="AppPooluser_poolid" value=""/>
<tsa:hidden name="updateAppBy" value="Y"/>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
					<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-generateRoutingList", "Generate Routing List")%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table border=0 cellspacing=0 cellpadding=1 width=100%>
				<tr>
				  <td align=right><b><tsa:label labelName="req-requisition" defaultString="Requisition #"/>&nbsp;:</b></td>
				  <td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
				</tr>
				<tr>
				  <td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
				  <td width=125px><%=DocumentStatus.toString(reqHeader.getStatus())%></td>
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
	</td>
</tr>
</table>

<%	if (!byPassRoutingList) {%>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td width=25px>&nbsp;</td>
	<td align=center>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr><td><br></td></tr>
<%		for (int j = 0; j < accountCar.size(); j++)
		{
			accountStr = HiltonUtility.ckNull((String) accountCar.get(j));
			String balanceAmount  = HiltonUtility.ckNull((String) balanceCar.get(j));
			if(!HiltonUtility.isEmpty(accountStr)) { %>
				<tr><td class=boldMessage>This requisition exceeds CAR #<%=accountStr%> budget amount.  You may return and change the requisition to be under the remaining amount (below), or Forward this requisition to initiate a request for an increase to the CAR value.
				<br>Remaining in this CAR:#<%=accountStr%> <%=HiltonUtility.getCurrency(balanceAmount, s_curr_code, oid)%></td></tr>
				<tsa:hidden name="Balance<%=j%><%=accountStr%>" value="<%=balanceAmount %>"/>
<%				byPassCarOverBudget = true;
			}
		}
		for (int ir=0; ir < approvalRuleList.size(); ir++)
		{
			String approvalRuleOverBudget = (String) approvalRuleList.get(ir);
			if(approvalRuleOverBudget.equalsIgnoreCase("[Requisition Total >= 50,000.00]"))
			{   %>
				<tr><td class=boldMessage>This requisition for a CAR budget exceeds $50,000.00 and will be routed to Bob Cushing
				<br>for final approval</td></tr>
<%			}
		}
		if(!byPassCarOverBudget)
		{
			if (bPassed) {%>
			<tr><td class=boldMessage <%=HtmlWriter.isVisible(oid, "req-msgGenerateRoutingList")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "msgGenerateRoutingList", "Below is a list of approvers who can approve for each of the cost center selections you made in the requisition.  Choose the approver you want the requisition to route to (using the up/down buttons as necessary); however, keep in mind, that depending on the dollar value of your requisition, you could have a high-level approver pre-selected.  If this is the case, and you want additional approvers to see the requisition before the highest level approver, you need to check all the required approvers based on their fiscal authority shown. When you are finished selecting the approvers, click on the forward button to begin the routing")%>.</td></tr>
	<%		} else {%>
			<tr><td class=boldMessage><%=ruleError%></td></tr>
	<%		}
		}%>
		</table>

		<table border=0 cellpadding=2 cellspacing=0>
		<tr><td><br></td></tr>
		<tr>
			<td class=label align=right><tsa:label labelName="req-requisitionTotal" defaultString="Requisition Total"/>:</td>
			<td><%=HiltonUtility.getCurrency(reqHeader.getTotal(), s_curr_code, oid)%></td><td>&nbsp</td>
			<td>[ <%=HiltonUtility.getCurrencyConvert(reqHeader.getTotal(), s_curr_code, oid)%> ]</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<table border="0" cellspacing="0" cellpadding="2" width="680px">
<%	if (bPassed || byPassCarOverBudget) {%>
	<%	if (enableDistributionList) { %>
	<tr>
		<td valign=top align=center>
			<table border="0" cellspacing="0" cellpadding="0" width="570px">
			<tr>
				<td align=left nowrap>
					<a href="javascript: addDistributionList(); void(0);"><img src="<%=contextPath%>/images/cmd_add_approver.gif" border=0></a>
					<a href="javascript: addDistributionList(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addDistributionList", "Add Distribution List")%></a>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	<%	} %>
	<tr>
	<td valign=top align=center>
		<div id="routinglist" style="display:none;">
<%
		for (int ir=0; ir < approvalRuleList.size(); ir++) {
			String approvalRule = (String) approvalRuleList.get(ir);
			Map ruleMap = (Map) routingListGroups.get(approvalRule);
			List routingList = (List) ruleMap.get("ruleRoutingList");
			BigDecimal amountToApprove = (BigDecimal) ruleMap.get("amountToApprove");
			if(approvalRule.equalsIgnoreCase("[Budget=OverBudget]"))
			{
				for (int j = 0; j < accountCar.size(); j++)
				{
					accountStr = (String) accountCar.get(j);
					approvalRule = "[CAR=" + accountStr + "]" + approvalRule;%>
					<br>
<%				}
			}
			if(approvalRule.equalsIgnoreCase("[Requisition Total >= 50,000.00]"))
			{
				amountToApprove = new BigDecimal(0);
			}
			if (routingList == null) { routingList = new ArrayList(); }
%>
        <tsa:hidden name="firstGroupApproverSeq" value="<%=iapprovers%>"/>

		<br>
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalRule", "Approval Rule")%>:</td>
					<td><%=approvalRule%></td>
				</tr>
				</table>
				<% if(amountToApprove.compareTo(new BigDecimal(1000000.01)) != 0) { %>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingRequiredAuthority", "Required Authority")%>:</td>
					<td><%=HiltonUtility.getCurrencyConvert(amountToApprove, s_curr_code, oid)%></td>
				</tr>
				</table>
				<% } %>
			</td>
		</tr>
		<tr height="20px">
			<td>
				<table border="0" align="center" width="25px">
			<% if (reqAction.equalsIgnoreCase("FORWARD") && bPassed && routingList.size() > 1) { %>
				<tr height="25px">
					<td align="center"><a href="javascript: moveUp(<%=ir%>); void(0);"><img src="<%=contextPath%>/images/up_arrows.gif" class="processOnImg" border="0" alt="Move Up"></a></td>
				</tr>
				<tr height="25px">
					<td align="center"><a href="javascript: moveDown(<%=ir%>); void(0);"><img src="<%=contextPath%>/images/down_arrows.gif" class="processOnImg" border="0" alt="Move Down"></a></td>
				</tr>
			<% } %>
				</table>
			</td>
		    <td align="center" valign="top">
				<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 0px; width: 684px; align:center; overflow-y:auto; overflow-x:auto;">
				<table border="0" cellspacing="0" cellpadding="1" width="680px" height=18px>
				<tr>
					<td class="browseHdrDk" nowrap width="4%"><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=0></td>
					<td class="browseHdrDk" nowrap width="22%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver Name")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="15%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="15%" align=right <%=HtmlWriter.isVisible(oid, "routingMinAuthority")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingMinAuthority", "Min. $ Authority")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="20%" align=right <%=HtmlWriter.isVisible(oid, "routingAuthority")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAuthority", "Authority")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="2%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=16px height=1px></td>
					<td class="browseHdrDk" nowrap width="5%" align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingSend", "Send")%></td>
				<%	if(keepCheckedBoxDisplay) { %>
					<td class="browseHdrDk" nowrap width="5%" align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingKeep", "Keep")%></td>
					<td class="browseHdrDk" nowrap width="10%" align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateApproved", "Date Approved")%></td>
				<%	} %>
					<td class="browseHdrDk" nowrap width="2%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></td>
				</tr>
				</table>
				<table id="approvers<%=ir%>" border="0" cellpadding="1" cellspacing="0" width="680px" class="browseRow">
		<%	boolean authorityFound = false;
				boolean displayVPApproverLink = false;
				int irow = 0;

				for (int i = 0; i < routingList.size(); i++) {
					ApprovalLog appLog = (ApprovalLog) routingList.get(i);
					String classType = "browseRow";
					String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
					if (appLog.getApproved().equals("A")) {
						s_approved_date = "Current Approver";
						classType = "summary";
					}

					String s_assigned_date = HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat);
					String s_aprd_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
					String	s_approver = appLog.getComp_id().getUserId();
					String	s_callForward = appLog.getCallForward();
					String	callForwardName = "";
					String  notes = appLog.getApproverNotes();

					if (!s_callForward.startsWith("@") && !s_approver.equals(s_callForward)) {
						UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());
						callForwardName = callForward.getDisplayName();
					} else if (s_callForward.startsWith("@") && !s_approver.equals(appLog.getPoolid())) {
						// Call Forward for user is set to a pool
						callForwardName = appLog.getPooldesc();
					}

					if (reqAction.equalsIgnoreCase("FORWARD")) {
						if (HiltonUtility.isEmpty(callForwardName)) {
							callForwardName = "&nbsp;-&nbsp";
						//Functionality to allow requisitioners to specify the call forward for an approver will be added later
						//	callForwardName = "<a href=\"javascript: callForward('" + s_approver + "'); void(0);\"><img src=\"" + contextPath + "/images/call_forward.gif\" border=0></a>";
						}
					} else {
						callForwardName = "&nbsp;-&nbsp";
					}

					String	authority = "-&nbsp;";
					String	minAuthority = "-&nbsp;";
					if (!appLog.isAnFyiApprover() && !appLog.isAnAdvisor()) {
						authority = HiltonUtility.getCurrencyConvert(appLog.getApproverAmount(), s_curr_code, oid);
						minAuthority = HiltonUtility.getCurrencyConvert(appLog.getExcludeLess(), s_curr_code, oid);
					}
					if (s_approver.equals("NONE FOUND")) { %>
				<tr height="15px">
					<td>&nbsp;</td>
					<td class=error colspan=4><%=s_approver%> - <%=appLog.getRuleNotes()%></td>
				</tr>
		<%			displayVPApproverLink = true;
					} else {
					if(notes.equalsIgnoreCase("No Approval Necessary"))
					{ %>
						<tr height="15px">
						<td>&nbsp;</td>
						<td colspan=4>
							<%=appLog.getApproverNotes()%>
							<tsa:hidden name="originalFyiApprover" value="<%=appLog.getFyiApprover()%>"/>
							<tsa:hidden name="ApprovalLog_fyiApprover" value="<%=appLog.getFyiApprover()%>"/>
							<tsa:hidden name="originalAdvisor" value="<%=appLog.getAdvisor()%>"/>
							<tsa:hidden name="ApprovalLog_advisor" value="<%=appLog.getAdvisor()%>"/>
							<tsa:hidden name="originalRequiredApprover" value="<%=appLog.getRequiredApprover()%>"/>
							<tsa:hidden name="ApprovalLog_requiredApprover" value="<%=appLog.getRequiredApprover()%>"/>
							<tsa:hidden name="originalApprovalAmount" value="<%=appLog.getApproverAmount()%>"/>
							<tsa:hidden name="ApprovalLog_approvalAmount" value="<%=appLog.getApproverAmount()%>"/>
							<tsa:hidden name="ApprovalLog_sendTo" value=""/>
						<%	if(keepCheckedBoxDisplay) { %>
							<input type=hidden name="ApprovalLog_keepApprover" value="">
						<%	} %>
						</td>
						</tr>
				<%  }
					else
					{ 	%>
				<tr height="15px">
					<td width="4%" class="<%=classType%>" NOWRAP align="right">
		<%		if (appLog.getApproved().equals("Y"))
				{
						if (appLog.getRecommendation().equals("A")) {%>
						<img src="<%=contextPath%>/images/check_partial.gif">
		<%			} else {%>
						<img src="<%=contextPath%>/images/check.gif">
		<%			}
				} %>
						<span id="sequence_<%=ir%>_<%=irow%>"><%=irow + 1%><%// Temp. comment out for demo purposed appLog.getComp_id().getSequence()%></span>
						&nbsp;
					</td>
					<td width="22%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>" NOWRAP>
		<%		if ((reqAction.equalsIgnoreCase("FORWARD") && bPassed && routingList.size() > 1) || (approvalRule.indexOf("[Budget=OverBudget]") > 0)) { %>
						<a href="javascript: void(0);" onClick="highlightRow(<%=ir%>,<%=irow%>);"><span id="approverText_<%=ir%>_<%=irow%>"><%=appLog.getApproverName()%></span></a>&nbsp;
		<%		} else { %>
						<%=appLog.getApproverName()%>
		<%		} %>
						<span id="fyiApproverText_<%=ir%>_<%=irow%>">
		<%		if (appLog.isAnFyiApprover()) {%>
						<img src="<%=contextPath%>/images/fyi_text_blue.gif" border=0 alt="This approver will receive an FYI notification upon approval of this requisition.">
		<%		}%>
						<tsa:hidden name="originalFyiApprover" value="<%=appLog.getFyiApprover()%>"/>
						<tsa:hidden name="ApprovalLog_fyiApprover" value="<%=appLog.getFyiApprover()%>"/>
						</span>
						<span id="advisorText_<%=ir%>_<%=irow%>" align=left>
		<%		if (appLog.isAnAdvisor()) {%>
						<img src="<%=contextPath%>/images/double_astrick.gif" border=0 alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAdvisorMsg", "Advisor - will make a recommendation for or against the approval of this requisition.")%>">
		<%		}%>
						<tsa:hidden name="originalAdvisor" value="<%=appLog.getAdvisor()%>"/>
						<tsa:hidden name="ApprovalLog_advisor" value="<%=appLog.getAdvisor()%>"/>
						</span>
						<span id="requiredApproverText_<%=ir%>_<%=irow%>">
						<tsa:hidden name="originalRequiredApprover" value="<%=appLog.getRequiredApprover()%>"/>
						<tsa:hidden name="ApprovalLog_requiredApprover" value="<%=appLog.getRequiredApprover()%>"/>
						</span>
					</td>
					<td width="15%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%> class="<%=classType%>" NOWRAP><span id="callForwardText_<%=ir%>_<%=irow%>"><%=callForwardName%>&nbsp;</span></td>
					<td width="15%" <%=HtmlWriter.isVisible(oid, "routingMinAuthority")%> class="<%=classType%>" align="right" NOWRAP><span id="minAuthorityText_<%=ir%>_<%=irow%>"><%=minAuthority%></span></td>
					<td width="20%" <%=HtmlWriter.isVisible(oid, "routingAuthority")%> class="<%=classType%>" align="right" NOWRAP><span id="authorityText_<%=ir%>_<%=irow%>"><%=authority%></span></td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
						<tsa:hidden name="originalApprovalAmount" value="<%=appLog.getApproverAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approvalAmount" value="<%=appLog.getApproverAmount()%>"/>
					</td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
		<%		if (!HiltonUtility.isEmpty(appLog.getRuleNotes())) {%>
						<a href="javascript: getRuleNotes(<%=ir%>,<%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" alt="View approval rule notes." border="0"></a>
		<%		}%>
					</td>
					<td width="3%" class="<%=classType%>" align=center>
						<span id="sendToText_<%=ir%>_<%=irow%>">
						<input type=checkbox name="sendTo" value="Y" <%	if ( sendToAlwaysChecked || appLog.isSendTo() || buyerCommodityCode.equalsIgnoreCase("Y") ) {%>checked<% if (appLog.isARequiredApprover() || buyerCommodityCode.equalsIgnoreCase("Y") || approvalRule.indexOf("[Requisition Total >= 50,000.00]") > 0 || approvalRule.indexOf("[Budget=OverBudget]") > 0) {%> disabled<%}} else if (enforceExcludeLess && appLog.getExcludeLess().compareTo(amountToApprove) > 0) {%> disabled<%}%>>
						<tsa:hidden name="ApprovalLog_sendTo" value=""/>
						</span>
					</td>
				<%	if(keepCheckedBoxDisplay) { %>
					<td width="3%" class="<%=classType%>" align=center>
						<span id="keepToText_<%=ir%>_<%=irow%>">
						<input type=checkbox name="keepTo" <%if(!appLog.getKeepApprovedFlag().equalsIgnoreCase("Y")){%> disabled <%}%> value="">
						<input type=hidden name="ApprovalLog_keepApprover" value="">
						</span>
					</td>
					<td width="14%" class="<%=classType%>" NOWRAP><span id="approvedDate_<%=ir%>_<%=irow%>"><%=s_approved_date%>&nbsp;</span></td>
				<%	} %>
					<td width="2%" NOWRAP class="<%=classType%>">
						<span id="deleteLink_<%=ir%>_<%=irow%>" style="border:1">
		<%		if ((appLog.getRuleType().equals("MAN")) && reqAction.equalsIgnoreCase("FORWARD")) { %>
						<a href="javascript: deleteUser(<%=ir%>, <%=irow%>, <%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a>
		<%		} else {%>&nbsp;
						<!--a href="javascript: cannotDeleteUser(<%=ir%>, <%=irow%>, <%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/delete_disabled.gif" alt="Required Approver - Cannot Delete" border="0"></a-->
		<%		} %>
					<tsa:hidden name="tableIndex" value="<%=ir%>"/>
		<%		}	%>
						</span>
					</td>
					<span id="hiddenFields_<%=ir%>_<%=irow%>">
						<tsa:hidden name="ruleNotes" value="<%=appLog.getRuleNotes()%>"/>
				        <tsa:hidden name="ApprovalLog_sequence" value="<%=appLog.getComp_id().getSequence()%>"/>
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
		                <%if(keepCheckedBoxDisplay) {  %>
		                <tsa:hidden name="ApprovalLog_keepDateAssigned" value="<%=s_assigned_date%>"/>
		                <tsa:hidden name="ApprovalLog_keepDateApproved" value="<%=s_aprd_date%>"/>
		                <%} %>
		                <tsa:hidden name="ApprovalLog_keepApprovedFlag" value="<%=appLog.getKeepApprovedFlag()%>"/>
		                <!--
		                ApprovalLog_dateAssigned
		                ApprovalLog_dateApproved
		                -->
					</span>
				</tr>
		<%		}
					iapprovers++;
					irow++;

				} %>
				</table>
<%	if (approvalRule.indexOf("[Distribution List = ") < 0) { %>
				<hr size=0 width=100% align=center>
				<table border="0" cellpadding="2" cellspacing="0" class="browseRow" align=center>
				<tr>
		<%	if (displayVPApproverLink) {%>
					<td <%=HtmlWriter.isVisible(oid, "routingAddVPApprover")%> align=right><a href="javascript: addVPApprover(<%=ir%>); void(0);"><img src="<%=contextPath%>/images/cmd_add_approver.gif" alt="Click here to add a VP or SVP approver for Approval Rule: <%=approvalRule%>." border=0></a></td>
					<td <%=HtmlWriter.isVisible(oid, "routingAddVPApprover")%>><a href="javascript: addVPApprover(<%=ir%>); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAddVPApprover", "I know the VP/SVP that should approve")%></a></td>
		<%	} else {%>
					<td <%=HtmlWriter.isVisible(oid, "routingAddApprover")%> align=right><a href="javascript: addUser(<%=ir%>, 'N'); void(0);"><img src="<%=contextPath%>/images/cmd_add_approver.gif" alt="Click here to add an approver for Approval Rule: <%=approvalRule%>." border=0></a></td>
					<td <%=HtmlWriter.isVisible(oid, "routingAddApprover")%>><a href="javascript: addUser(<%=ir%>, 'N'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAddApprover", "My approver is not listed")%></a></td>
		<%	}%>
					<td width=50px>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "routingAddFyiApprover")%>><a href="javascript: addUser(<%=ir%>, 'Y'); void(0);"><img src="<%=contextPath%>/images/fyi_text_blue.gif" border=0></a></td>
					<td <%=HtmlWriter.isVisible(oid, "routingAddFyiApprover")%>><a href="javascript: addUser(<%=ir%>, 'Y'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAddFyiApprover", "I would like to send an FYI notification")%></a></td>
				</tr>
				</table>
<%	} %>
		        </div>

		        <tsa:hidden name="approvalRule" value="<%=approvalRule%>"/>
		        <tsa:hidden name="amountToApprove" value="<%=amountToApprove%>"/>
		      </td>
		  </tr>
		</table>
<%		}%>
		<br>

		<table border=0 cellspacing=2 cellpadding=0>
		<tr><td align=center>* <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-multipleApproverDesc", "* Approvers listed multiple times will only receive a notification to approve once.")%>.</tr>
		<tr <%=HtmlWriter.isVisible(oid, "routingAdvisorMsg")%>><td align=center><img src="<%=contextPath%>/images/double_astrick.gif" border=0>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAdvisorMsg", "Advisor - will make a recommendation for or against the approval of this requisition.")%></td></tr>
		</table>
<%	} %>

		<br>

		<table border="0" cellspacing="2" cellpadding="2" valign="bottom" width="680px">
		<tr id="_link">
		<%	if (reqAction.equalsIgnoreCase("FORWARD")) {
					if (iapprovers > 0) { %>
			<td align="center"><div id="pxbutton"><a href="javascript: reqForward(); void(0);">Continue</a></div></td>
		<%		}
				} %>
			<td align="center"><div id="pxbutton"><a href="javascript: doSubmitHide()">Return</a></div></td>
		</tr>
		</table>
		</div>
	</td>
</tr>
<% if (displayApprovalPolicy) {%>
<%	if (bPassed && reqAction.equalsIgnoreCase("FORWARD") && !byPassRoutingList) { %>
<tr>
	<td align=center>
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width=25px>&nbsp;</td>
			<td>
				<br><br>
				<table border=0 cellspacing=0 cellpadding=0 width=575px>
				<tr>
					<td class=browseHdr valign=top>
						<div id="helpBorder" class=browseHdr style="border:solid 2px; background: #FFFFFF; padding: 0px; width: 100%; align:center; overflow-y:auto; overflow-x:auto;">
						<table border=0 cellspacing=0 cellpadding=0 height=18px width=100%>
						<tr><td class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalRoutingListHelp", "Approval Routing List Help")%> - <font style="font-Weight:normal"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "clickBellowDetailedHelp", "Click on any of the links below for detailed help")%>.</font></td></tr>
						<tr><td height="1px" class=browseHdrDk><img src="<%=contextPath%>/images/none.gif" width=100% height=1px /></td></tr>
						</table>
						<table border=0 cellpadding=1 cellspacing=0 width=98% class=browseRow align=center>
						<tr height="22px">
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/cmd_add_approver.gif" alt="Click here for help if your approver is not listed." border=0></td><td nowrap><a href="javascript: addApproverHelp();  void(0);">Add approver</a></td-->
							<td valign=middle align=center  <%=HtmlWriter.isVisible(oid, "routingRemoveApproverHelp")%>><img src="<%=contextPath%>/images/delete.gif" alt="Click here for help if you wish to remove an approver from the list." border=0></td><td nowrap><a href="javascript: removeApproverHelp(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingRemoveApproverHelp", "Remove Approver")%></a></td>
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/dollars.gif" alt="Click here for help if your approver's dollar authority is incorrect.'" border=0></td><td nowrap><a href="javascript: requestAuthorityCorrection(); void(0);">Change approval authority</a></td-->
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/call_forward.gif" alt="Click here for help if your approver is currently on leave." border=0></td><td nowrap><a href="javascript: callForwardHelp(); void(0);">Set call forward for approver</a></td-->
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/delete_disabled.gif" alt="Click here to view details about required approvers." border=0></td><td nowrap><a href="javascript: requiredApproverHelp(); void(0);">Approver is required</a></td-->
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/fyi_text_blue.gif" alt="Click here for help if you wish to send an FYI notification to an approver once this requisition has been awarded." border=0></td><td nowrap><a href="javascript: fyiApproverHelp(); void(0);">FYI approval notification will be sent</a></td-->
						<!--/tr>
						<tr-->
							<td valign=middle align=center <%=HtmlWriter.isVisible(oid, "routingApprovalPolicyHelp")%>><a href="javascript: viewApprovalPolicy(); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0></a></td>
							<td colspan=5 height=22px valign=middle <%=HtmlWriter.isVisible(oid, "routingApprovalPolicyHelp")%>><a href="javascript: viewApprovalPolicy(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalPolicyHelp", "View Financial Authorization Limitations Policy")%></a></td>
						</tr>
						</table>
						</div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<%	}%>
<%} %>
<tr>
	<td align=center width="100%" valign=middle>
		<div id="noroutinglist" style="display:none;">
			<table border=0 cellspacing=0 cellpadding=0 align=center height=150px>
		    	<tr valign=middle>
		          <td valign="middle"><img src='<%=contextPath%>/images/alert.gif' valign="middle" border="0"></td>
		          <td valign="middle" class="basic"><b>Please wait while we forward your request.</b></td>
		         </tr>
		    </table>
		</div>
	</td>
</tr>
</table>
<!-- dummy fields to prevent js errors -->
<div id="dummyFields" style="display:none;">
<tsa:hidden name="approverName" value=""/>
<tsa:hidden name="ruleNotes" value=""/>
<tsa:hidden name="ApprovalLog_ruleType" value=""/>
<tsa:hidden name="ApprovalLog_ruleNotes" value=""/>
<tsa:hidden name="ApprovalLog_userId" value=""/>
<tsa:hidden name="ApprovalLog_sequence" value=""/>
</div>

<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
	<%	if ((noApprovalNeed.equalsIgnoreCase("Y") || noApprovalNeedAutoAward.equalsIgnoreCase("Y")) && !buyerCommodityCode.equalsIgnoreCase("Y"))
	{ %>
			displayArea('noroutinglist');
			doSubmitApprove('N');
	<%}
	else
	{
		if (byPassRoutingList && reqAction.equals("FORWARD")) {%>
			displayArea('noroutinglist');
			reqForward();
		<%} else if (bPassed || byPassCarOverBudget) {%>
			displayArea('routinglist');
		<%}
	}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	var allowSelfApproval = <%=PropertiesManager.getInstance(oid).getProperty("APPROVALS", "SELF-APPROVER", "Y").equals("Y")%>;
	var currentRow = -1;
	var currentTable = -1;
	//list should show up to allow user to add approvers. -RR
	//Approvals(Routing list) will be byPassed if an XML rule type of "Z" was true -RR 03/10/05

	var usersArray = new Array();
	var poolsArray = new Array();

	function reqForward() {
		hideAreaWithBlock('_link');
		frm.reqaction.value = "FORWARD";

		if (!isApprovalAuthorityMet()) {
			displayArea('_link');
			return false;
		}

		doSubmit('/requests/req_forward.jsp', 'ApprovalLogUpdateFinalList;RequisitionForward');
	}

	function doSubmitApprove(overrideAll)
	{
	  	frm.returnTo.value="N";
	  	frm.reqaction.value="APPROVE";
	  	frm.NoApprovalNeed.value='<%=noApprovalNeed%>';
	  	<% if(reqHeader.getRequisitionType().equalsIgnoreCase("C"))
	  	{ %>
	  		frm.NoApprovalNeedAutoAward.value='N';
	  	<% } else { %>
	  		frm.NoApprovalNeedAutoAward.value='<%=noApprovalNeedAutoAward%>';
	  	<% } %>
	  	hideAreaWithBlock('_link');
	  	frm.approveForAll.value = overrideAll;
	    doSubmit('/requests/req_forward.jsp', 'RequisitionApprove');
	}

	function reqForward2() {
		hideAreaWithBlock('_link');
		frm.reqaction.value = "FORWARD";

		var rowCount = document.all.approvers.rows.length;
		var approvers = "";
		for (var i=0; i < rowCount; i++) {
			if (frm.ApprovalLog_ruleType[i].value == "MAN") {
				if (approvers.length > 0) {
					approvers = approvers + "\n" + frm.approverName[i].value;
				} else {
					approvers = frm.approverName[i].value;
				}
			}
		}

		if (approvers.length > 0) {
			displayArea('_link');
			frm.reqaction.value = "";

			if (!confirm("The following will be added as approvers for future requisitions matching\n <%//approvalRule%>:\n\n" + approvers)) {
				return false;
			}
		}
		doSubmit('/requests/req_forward.jsp', 'ApprovalLogUpdateList;RequisitionForward');
  }

	function doSubmitHide() {
		hideAreaWithBlock('_link');
		doSubmit('requests/req_review.jsp', 'DoNothing;RequisitionRetrieve');
	}

	function addUser(tableIndex, fyiApprover) {
		var approvalRule = getApprovalRule(tableIndex);
		var amtToApprove = getAmountToApprove(tableIndex);
		var seqElements = frm.firstGroupApproverSeq;
		var insertBefore = 0;

		if (seqElements != undefined) {
			if (seqElements.length != undefined && seqElements.length > 1 && seqElements.length > tableIndex) {
				insertBefore = seqElements[tableIndex].value;
			} else {
				insertBefore = 0;
			}
		}

		frm.insertBefore.value = insertBefore;

		if (approvalRule == "USER-DEFINED") {
			popupParameters="browseName=user-defined-approver";
<%	if (oid.equalsIgnoreCase("vse06p")) {%>
			popupParameters=popupParameters + ";AppRule_udf1Code=<%=reqHeader.getDepartmentCode()%>";
<%	}%>
		} 
		else if (approvalRule.indexOf("FPE") >= 0) {
			popupParameters="browseName=approver-fpe";
		}
		else {
			popupParameters="browseName=approver";
		}
		popupParameters=popupParameters + ";formField=ApprovalLog_userId";
		popupParameters=popupParameters + ";addUserRule=" + approvalRule;
		popupParameters=popupParameters + ";addUserAmount=" + amtToApprove;
		popupParameters=popupParameters + ";fyiApprover=" + fyiApprover;
		popupParameters=popupParameters + ";formtype=" + frm.formtype.value;
		doSubmitToPopup('browse/browse_approvers.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}

	function deleteUser(tableIndex, tableRow, row) {
		var ruleType =frm.ApprovalLog_ruleType[row].value;

		if (ruleType != "MAN") {
			var instructions = "Please provide a reason for removing " + frm.approverName[row].value + " from this list:";
			popupParameters = "instructions=" + instructions + ";notesType=REMOVE";
			doSubmitToPopup("/requests/req_routing_notes.jsp", "DoNothing");

		} else {
			deleteUserConfirmed(tableIndex, tableRow, true);
		}
	}

	function deleteUserConfirmed(tableIndex, tableRow, updateWhenComplete) {
		var myTable = document.getElementById("approvers" + tableIndex);
		var sequenceFlds = frm.ApprovalLog_sequence;
		var formRow = getFormRow(tableIndex, tableRow);

		if (sequenceFlds != undefined && sequenceFlds.length != undefined && sequenceFlds.length > 1 && formRow < sequenceFlds.length) {
			var currentSeq = eval(frm.ApprovalLog_sequence[formRow].value);
			var tempSeq = 0;

			for (var i = 0; i < sequenceFlds.length; i++) {
				tempSeq = eval(frm.ApprovalLog_sequence[i].value);

				if (tempSeq > currentSeq) {
					frm.ApprovalLog_sequence[i].value = (tempSeq - 1);
				}
			}
		}
		myTable.deleteRow(tableRow);

		var rowCount = myTable.rows.length;
		if (tableRow < rowCount) {
			for (var i = tableRow; i < rowCount; i++) {
				setNewId("sequence_" + tableIndex + "_" + (i+1), "sequence_" + tableIndex + "_" + i);
				setInnerText("sequence_" + tableIndex + "_" + i, (i+1));
			}
		}

		if (updateWhenComplete) {
			doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
		}
	}

	function highlightRow(tableIndex, row) {
		var myTable = document.getElementById("approvers" + tableIndex);
		if (currentTable == tableIndex && currentRow == row) {
			removeHighlight(tableIndex, row);
		} else {
			if (currentTable > -1 && currentRow > -1) {
				removeHighlight(currentTable, currentRow);
			}

			var myRow = myTable.rows[row];

			setRowClassName(myRow, "selectedRow");
			setCurrentTable(tableIndex);
			setCurrentRow(row);
		}
	}

	function removeHighlight(tableIndex, row) {
		var myTable = document.getElementById("approvers" + tableIndex);
		var myRow = myTable.rows[row];

		setRowClassName(myRow, "browseRow");
		setCurrentTable(-1);
		setCurrentRow(-1);
	}

	function setCurrentTable(tableIndex) {
		currentTable = tableIndex;
	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function moveUp(tableIndex) {
		if (currentRow == -1) {
			alert("You must first select an approver to move up on the list.");
		}
		else if (currentRow > 0) {
			var myTable = document.getElementById("approvers" + tableIndex);
			var rowCount = myTable.rows.length;
			var sequenceFlds = frm.ApprovalLog_sequence;

			if (sequenceFlds != undefined && sequenceFlds.length != undefined && sequenceFlds.length > 1) {
				var formRow = getFormRow(tableIndex, currentRow);
				var initialSeq = eval(frm.ApprovalLog_sequence[formRow].value);
				var newSeq = eval(frm.ApprovalLog_sequence[formRow - 1].value);
				var tempSeq = eval(0);

				for (var i = 0; i < sequenceFlds.length; i++) {
					tempSeq = eval(frm.ApprovalLog_sequence[i].value);
					if (tempSeq < initialSeq && tempSeq >= newSeq) {
						frm.ApprovalLog_sequence[i].value = (tempSeq + 1);
					}
				}
				frm.ApprovalLog_sequence[formRow].value = newSeq;
			}

			moveInnerTextUp("approverText", tableIndex, currentRow);
			moveInnerTextUp("callForwardText", tableIndex, currentRow);
			moveInnerTextUp("authorityText", tableIndex, currentRow);
			moveInnerTextUp("minAuthorityText", tableIndex, currentRow);
			moveInnerHtmlUp("sendToText", tableIndex, currentRow);
			moveInnerHtmlUp("keepToText", tableIndex, currentRow);
			moveInnerHtmlUp("fyiApproverText", tableIndex, currentRow);
			moveInnerHtmlUp("advisorText", tableIndex, currentRow);
			moveInnerHtmlUp("requiredApproverText", tableIndex, currentRow);
			moveInnerHtmlUp("hiddenFields", tableIndex, currentRow);

			setDeleteLink(tableIndex, currentRow);
			setDeleteLink(tableIndex, currentRow - 1);

			highlightRow(tableIndex, currentRow - 1);
		}
	}

	function moveDown(tableIndex) {
		var myTable = document.getElementById("approvers" + tableIndex);
		var rowCount = myTable.rows.length;

		if (currentRow == -1) {
			alert("You must first select an approver to move down on the list.");
		}
		if (currentRow > -1 && currentRow < (rowCount - 1)) {
			var sequenceFlds = frm.ApprovalLog_sequence;

			if (sequenceFlds != undefined && sequenceFlds.length != undefined && sequenceFlds.length > 1) {
				var formRow = getFormRow(tableIndex, currentRow);
				var initialSeq = eval(frm.ApprovalLog_sequence[formRow].value);
				var newSeq = eval(frm.ApprovalLog_sequence[formRow + 1].value);
				var tempSeq = eval(0);

				for (var i = 0; i < sequenceFlds.length; i++) {
					tempSeq = eval(frm.ApprovalLog_sequence[i].value);
					if (tempSeq > initialSeq && tempSeq <= newSeq) {
						frm.ApprovalLog_sequence[i].value = (tempSeq - 1);
					}
				}
				frm.ApprovalLog_sequence[formRow].value = newSeq;
			}

			moveInnerTextDown("approverText", tableIndex, currentRow);
			moveInnerTextDown("callForwardText", tableIndex, currentRow);
			moveInnerTextDown("minAuthorityText", tableIndex, currentRow);
			moveInnerTextDown("authorityText", tableIndex, currentRow);
			moveInnerHtmlDown("fyiApproverText", tableIndex, currentRow);
			moveInnerHtmlDown("advisorText", tableIndex, currentRow);
			moveInnerHtmlDown("requiredApproverText", tableIndex, currentRow);
			moveInnerHtmlDown("sendToText", tableIndex, currentRow);
			moveInnerHtmlDown("keepToText", tableIndex, currentRow);
			moveInnerHtmlDown("hiddenFields", tableIndex, currentRow);

			setDeleteLink(tableIndex, currentRow);
			setDeleteLink(tableIndex, currentRow + 1);

			highlightRow(tableIndex, currentRow + 1);
		}
	}

	function moveInnerHtmlUp(id, tableIndex, currentRow) {
		var upHTML = document.getElementById(id + "_" + tableIndex + "_" + currentRow).innerHTML;
		var downHTML = document.getElementById(id + "_" + tableIndex + "_" + (currentRow - 1)).innerHTML;
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow), downHTML);
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow - 1), upHTML);
	}

	function moveInnerHtmlDown(id, tableIndex, currentRow) {
		var downHTML = document.getElementById(id + "_" + tableIndex + "_" + currentRow).innerHTML;
		var upHTML = document.getElementById(id + "_" + tableIndex + "_" + (currentRow + 1)).innerHTML;
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow + 1), downHTML);
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow), upHTML);
	}

	function moveInnerTextUp(id, tableIndex, currentRow) {
		var upText = document.getElementById(id + "_" + tableIndex + "_" + currentRow).innerHTML;
		var downText = document.getElementById(id + "_" + tableIndex + "_" + (currentRow - 1)).innerHTML;
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow), downText);
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow - 1), upText);
	}

	function moveInnerTextDown(id, tableIndex, currentRow) {
		var downText = document.getElementById(id + "_" + tableIndex + "_" + currentRow).innerHTML;
		var upText = document.getElementById(id + "_" + tableIndex + "_" + (currentRow + 1)).innerHTML;
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow + 1), downText);
		setInnerHTML(id + "_" + tableIndex + "_" + (currentRow), upText);
	}

	function updateList() {
		doSubmit('/requests/req_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
	}

	function validateForm() {
		var dummyFields = document.getElementById("dummyFields");
		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = "";
		}
		if (frm.handler.value.indexOf("RequisitionForward") >= 0)  {
			setSendToValues(true);
		}
		else if (frm.handler.value.indexOf("ApprovalLogAdd") >= 0 || frm.handler.value.indexOf("ApprovalLogUpdateList") >= 0)  {
			setSendToValues(false);
		}
		return true;
	}

	function requestAuthorityCorrection() {
	}

	function addApproverHelp() {
		popupParameters = "helpType=ADDAPPROVERMSG";
		doSubmitToPopup("/requests/req_routing_help.jsp", "DoNothing");
	}

	function removeApproverHelp() {
		popupParameters = "helpType=REMOVEAPPROVERMSG";
		doSubmitToPopup("/requests/req_routing_help.jsp", "DoNothing", "width=800px", "height=800px");
	}

	function callForwardHelp() {
		popupParameters = "helpType=CALLFORWARDMSG";
		doSubmitToPopup("/requests/req_routing_help.jsp", "DoNothing");
	}

	function fyiApproverHelp() {
	}

	function callForward(currentApproverId) {
		popupParameters = "helpType=CALLFORWARD;currentApproverId=" + currentApproverId;
		doSubmitToPopup("/requests/req_routing_help.jsp", "DoNothing");
	}

	function setFyiApprover(ind) {
		var recordCount = <%=iapprovers%>;
		var originalFyiApprover = "N";
		var originalRequiredApprover = "N";
		var currentFyiApprover = "N";
		var currentRequiredApprover = "N";
		var fyiApproverFld;

		if (recordCount > 1) {
			originalFyiApprover = frm.originalFyiApprover[ind].value;
			currentFyiApprover = frm.ApprovalLog_fyiApprover[ind].value;
			originalRequiredApprover = frm.originalRequiredApprover[ind].value;
			fyiApproverFld = frm.ApprovalLog_fyiApprover[ind];
		} else {
			originalFyiApprover = frm.originalFyiApprover.value;
			currentFyiApprover = frm.ApprovalLog_fyiApprover.value;
			originalRequiredApprover = frm.originalRequiredApprover.value;
			fyiApproverFld = frm.ApprovalLog_fyiApprover;
		}

		if (originalFyiApprover == "Y" && originalRequiredApprover =="Y") {
			alert("This approver is required by the system for this approval rule and cannot be changed.");
			return false;
		} else if (currentFyiApprover == "Y") {
			newImg = "<%=contextPath%>/images/fyi_bw.gif";
			newFyiApprover = "N";
		} else {
			newImg = "<%=contextPath%>/images/fyi.gif";
			newFyiApprover = "Y";
		}

		fyiApproverFld.value = newFyiApprover;
		var myImg = document.getElementById("fyi_" + ind);
		myImg.src = newImg;
	}

	function cannotDeleteUser() {
		alert("This approver is required and cannot be removed.  Contact your administrator if you feel this is incorrect.");
	}

	function getRuleNotes(tableIndex, row) {
		var approverId = "";
		var ruleNotes = "";
		var approvalRule = getApprovalRule(tableIndex);

		approverId = frm.ApprovalLog_userId[row].value;
		ruleNotes = frm.ruleNotes[row].value;

		popupParameters = "currentRow=" + row + ";approverId=" + approverId + ";ruleNotes=" + ruleNotes + ";approvalRule=" + approvalRule + ";allowEdit=false";
		doSubmitToPopup('/base/apprule_notes.jsp', 'DoNothing', 'WIDTH=500', 'HEIGHT=300');
	}

	function viewApprovalPolicy() {
<% if (PropertiesManager.getInstance(oid).getProperty("APPROVALS", "DisplayDefaultPolicy", "N").equals("Y")) {%>
		viewDefaultApprovalPolicy();
<%	} else {%>
		openAttachment('Authorized Financial Authorization Limitations.pdf');
<%	}%>
	}

	function getApprovalRule(tableIndex) {
		var approvalRules = frm.approvalRule;
		var approvalRule = "";

		if (approvalRules != undefined) {
			if (approvalRules.length != undefined && approvalRules.length > 1) {
				approvalRule = frm.approvalRule[tableIndex].value;
			} else if (frm.approvalRule) {
				approvalRule = frm.approvalRule.value;
			}
		} else if (frm.approvalRule) {
			approvalRule = frm.approvalRule.value;
		}

		return approvalRule;
	}

	function getAmountToApprove(tableIndex) {
		var amounts = frm.amountToApprove;
		var amount = 0;

		if (amounts != undefined) {
			if (amounts.length != undefined && amounts.length > 1) {
				amount = frm.amountToApprove[tableIndex].value;
			} else if (frm.amountToApprove) {
				amount = frm.amountToApprove.value;
			}
		} else if (frm.amountToApprove) {
			amount = frm.amountToApprove.value;
		}

		return amount;
	}

	function isApprovalAuthorityMet() {
		var ruleGroups = 0;
		var approvalRules = frm.approvalRule;
		var approvers = getNumberOfApprovers();
		var msg = "";
		var overBudget = false;

		if (approvalRules != undefined) {
			if (approvalRules.length != undefined) {
				ruleGroups = approvalRules.length;
			} else {
				ruleGroups = 1;
			}
		}
		for (var i=0; i < ruleGroups; i++) {
			var amt = getAmountToApprove(i);
			var rule = getApprovalRule(i);
			var authorityMet = false;
			for (var ia=0; ia < approvers; ia++) {
				var approverUdfValues = getApproverUdfValues(ia);
				if (rule == approverUdfValues) {
					var approverAmt = getApproverAuthority(ia);
					if (eval(approverAmt) >= eval(amt)) {
						if (getApproverUserId(ia) == frm.RequisitionHeader_requisitionerCode.value && !allowSelfApproval)
						{
							var authorityMet = false;
						}
						else
						{
							authorityMet = true;
							break;
						}
					}
				}
				else
				{
					authorityMet = true;
				}
			}

			if (!authorityMet && (rule == "USER-DEFINED") && (eval(amt) == eval(0))) {
				authorityMet = true;
			}
			if (rule.indexOf("[Budget=OverBudget]") >= 0 || rule.indexOf("[Requisition Total >= 50,000.00]") >= 0) {
				authorityMet = true;
				overBudget = true;
			}

			if (!authorityMet) {
				msg = msg + "\n" + rule;
			}
			if(rule == "ADMIN CHECK REV") {
				msg="";
				overBudget = true;
			}
		}
		<% if(HiltonUtility.isEmpty(rqSubType)) { %>
			if (!isEmpty(msg) && !overBudget) {
				alert("Approval authority has not been met for the following rule(s): \n" + msg);
				return false;
			}
		<% } %>
		return true;
	}

	function getApproverUdfValues(row) {
		var approverUdfValues = "";
		var approvers = getNumberOfApprovers();

		if (approvers == 1) {
			approverUdfValues = frm.ApprovalLog_udfValues.value;
		} else if (approvers > 1) {
			approverUdfValues = frm.ApprovalLog_udfValues[row].value;
		}

		return approverUdfValues;
	}

	function getApproverAuthority(row) {
		var approverAmt = 0;
		var sendtoCkboxes = frm.sendTo;
		var approvers = getNumberOfApprovers();
		var sendto = false;

		if (approvers == 1) {
			sendto = sendtoCkboxes.checked;
		} else if (approvers > 1) {
			sendto = sendtoCkboxes[row].checked;
		}

		if (sendto) {
			if (approvers == 1) {
				if (frm.ApprovalLog_fyiApprover.value != "Y" && frm.ApprovalLog_advisor.value != "Y") {
					approverAmt = frm.ApprovalLog_approverAmount.value;
				}
			} else if (approvers > 1) {
				if (frm.ApprovalLog_fyiApprover[row].value != "Y" && frm.ApprovalLog_advisor[row].value != "Y") {
					approverAmt = frm.ApprovalLog_approverAmount[row].value;
				}
			}
		}
		return approverAmt;
	}

	function getApproverUserId(row) {
		var approverUserId = "";
		var approvers = getNumberOfApprovers();

		if (approvers == 1) {
			approverUserId = frm.ApprovalLog_userId.value;
		} else if (approvers > 1) {
			approverUserId = frm.ApprovalLog_userId[row].value;
		}

		return approverUserId;
	}

	function getNumberOfApprovers() {
		var sendtoCkboxes = frm.sendTo;
		var approvers = 0;

		if (sendtoCkboxes != undefined) {
			if (sendtoCkboxes.length != undefined && sendtoCkboxes.length > 1) {
				approvers = sendtoCkboxes.length;
			} else {
				approvers = 1;
			}
		}

		return approvers;
	}

	function setDeleteLink(tableIndex, tableRow) {
		var rowCount = 0;

		for (var i = 0; i < tableIndex; i++) {
			var myTable = document.getElementById("approvers" + i);
			rowCount = rowCount + myTable.rows.length;
		}

		var row = rowCount + tableRow;
		var requiredApprover = frm.ApprovalLog_requiredApprover[row].value;
		var ruleType = frm.ApprovalLog_ruleType[row].value;
		var	newLink = "&nbsp;";

		if (ruleType == "MAN") {
			newLink = "<a href=\"javascript: deleteUser(" + tableIndex + "," + tableRow + ", " + row + "); void(0);\"><img src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=\"0\">";
		}

		setInnerHTML("deleteLink_" + tableIndex + "_" + tableRow, newLink);
	}

	function setSendToValues(deleteRows) {
		var sendtoCkboxes = frm.sendTo;
		var keepToCkboxes = "";
<%		if(keepCheckedBoxDisplay) { %>
			keepToCkboxes = frm.keepTo;
<%		} %>
		var sendto = false;

		if (sendtoCkboxes != undefined) {
			if (sendtoCkboxes.length != undefined && sendtoCkboxes.length > 1) {
				var tableIndex = 0;
				var tableRow = 0;
				var prevIndex = -1;
				var rows = sendtoCkboxes.length;

				for (var i=0; i < rows; i++) {
					sendto = sendtoCkboxes[i].checked;
<%					if(keepCheckedBoxDisplay) { %>
						keepto = keepToCkboxes[i].checked;
<%					} %>
					if (rows > 1) {
						tableIndex = frm.tableIndex[i].value;
					} else {
						tableIndex = frm.tableIndex.value;
					}

					if (tableIndex == prevIndex) {
						tableRow++;
					} else {
						tableRow = 0;
					}

					if (sendto) {
						if (rows > 1) {
							frm.ApprovalLog_sendTo[i].value = "Y";
<%							if(keepCheckedBoxDisplay) { %>
								if(keepto)
								{
									frm.ApprovalLog_keepApprover[i].value = "Y";
								}
<%							}	%>
						} else {
							frm.ApprovalLog_sendTo.value = "Y";
<%							if(keepCheckedBoxDisplay) { %>
								if(keepto)
								{
									frm.ApprovalLog_keepApprover.value = "Y";
								}
<%							} %>
						}
					} else {
						if (rows > 1) {
							frm.ApprovalLog_sendTo[i].value = "N";
						} else {
							frm.ApprovalLog_sendTo.value = "N";
						}
						if (deleteRows) {
							deleteUserConfirmed(tableIndex, tableRow, false);
							i--;
							rows--;
							tableRow--;
						}
					}
					prevIndex = tableIndex;
				}
			} else  {
				sendto = sendtoCkboxes.checked;
				if (sendto) {
					frm.ApprovalLog_sendTo.value = "Y";
				} else {
					frm.ApprovalLog_sendTo.value = "N";
				}
			}
		}
	}

	function getFormRow(tableIndex, currentRow) {
		var rowCount = 0;

		for (var i = 0; i < tableIndex; i++) {
			var myTable = document.getElementById("approvers" + i);
			rowCount = rowCount + myTable.rows.length;
		}

		return (rowCount + currentRow);
	}

	function addVPApprover(tableIndex) {
		var approvalRule = getApprovalRule(tableIndex);
		var amtToApprove = getAmountToApprove(tableIndex);
		var seqElements = frm.firstGroupApproverSeq;
		var insertBefore = 0;

		if (seqElements != undefined) {
			if (seqElements.length != undefined && seqElements.length > 1 && seqElements.length > tableIndex) {
				insertBefore = seqElements[tableIndex].value;
			} else {
				insertBefore = 0;
			}
		}

		frm.insertBefore.value = insertBefore;

		popupParameters="browseName=approver-vp";
		popupParameters=popupParameters + ";formField=ApprovalLog_userId";
		popupParameters=popupParameters + ";addUserRule=" + approvalRule;
		popupParameters=popupParameters + ";addUserAmount=" + amtToApprove;
		popupParameters=popupParameters + ";fyiApprover=N";
		doSubmitToPopup('browse/browse_approvers.jsp', 'BrowseRetrieve', 'WIDTH=650', 'HEIGHT=500');
	}

	function addDistributionList()
	{
		var approvalRule = getApprovalRule(0);
		var amtToApprove = getAmountToApprove(0);
		var seqElements = frm.firstGroupApproverSeq;

		frm.insertBefore.value = 0;

		usersArray = new Array();
		poolsArray = new Array();

		popupParameters="browseName=app-pooluser-s-distribution";
		popupParameters=popupParameters + ";formField=ApprovalLog_userId";
		popupParameters=popupParameters + ";addUserRule=" + approvalRule;
		popupParameters=popupParameters + ";addUserAmount=" + amtToApprove;
		popupParameters=popupParameters + ";fyiApprover=" + "N";
		doSubmitToPopup('browse/browse_req_s_pool.jsp', 'BrowseRetrieve', 'WIDTH=700', 'HEIGHT=500');
	}
// end hiding contents -->
</SCRIPT>