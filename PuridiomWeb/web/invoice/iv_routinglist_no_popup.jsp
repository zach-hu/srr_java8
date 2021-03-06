<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.ApprovalLog" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%
	Map routingListGroups = (Map) request.getAttribute("routingListGroups");
	List approvalRuleList = (List) request.getAttribute("finalRuleList");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String ruleStatus = HiltonUtility.ckNull((String) request.getAttribute("ruleStatus"));
	String ruleError = HiltonUtility.ckNull((String) request.getAttribute("ruleError"));
	String extraSubjectFYI = HiltonUtility.ckNull((String) request.getAttribute("extraSubjectFYI"));
	String byPassRoutingListString = HiltonUtility.ckNull((String) request.getAttribute("byPassRoutingList"));
	String ivcAction = (String)request.getAttribute("invoiceaction");
	String pricingAction = HiltonUtility.ckNull((String)request.getAttribute("pricingAction"));
	String s_ivc_number = invoiceHeader.getInvoiceNumber();
	String s_curr_code = invoiceHeader.getCurrencyCode();
	String	s_buyer_code = "";
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	String userDefinedRuleNotes = HiltonUtility.ckNull((String) request.getAttribute("userDefinedRuleNotes"));
	boolean userDefinedApprovals = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "UserDefinedApprovals", "N").equals("Y");
	boolean enforceExcludeLess = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "EnforceExcludeLess", "N").equals("Y");
	boolean sendToAlwaysChecked = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "SENDTO-ALWAYSCHECKED", "N").equals("Y");
	int	auto_award_rawa = role.getAccessRights("RAWA");
	boolean byPassRoutingList = byPassRoutingListString.equalsIgnoreCase("true");
	boolean bPassed = !ruleStatus.equalsIgnoreCase("FAILED");
	boolean bForward = false;
	int	iapprovers = 0;

	NumberFormat currencyFormat  = NumberFormat.getCurrencyInstance();

	if (ivcAction == null) { ivcAction = "FORWARD"; }

	if (approvalRuleList == null) {
		approvalRuleList = new ArrayList();
	}

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
	

%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/dynamicTables.js' type='text/javascript'></script>
<input type=hidden name="formtype" value="IVC">
<input type=hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>">
<input type=hidden name="ivcaction" value="<%=ivcAction %>">
<input type=hidden name="fromPage" value="routinglist">
<input type=hidden name="extraSubjectFYI" value="<%=extraSubjectFYI%>">
<input type=hidden name="insertBefore" value="0">
<input type=hidden name="addUser" value="">
<input type=hidden name="addUserRule" value="">
<input type=hidden name="addUserAmount", value="">
<input type=hidden name="fyiApprover" value="">

<table border="0" cellpadding="0" cellspacing="0" width="680px">
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
					<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-generateRoutingList", "Generate Routing List")%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table border="0" cellspacing="0" cellpadding="1" width="100%">
				<tr>
				  <td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceNumber", "Invoice #")%>:</b></td>
				  <td width=125px><%=s_ivc_number%></td>
				</tr>
				<tr>
				  <td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
				  <td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
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
<%		if (bPassed) {%>
		<tr><td class=boldMessage <%=HtmlWriter.isVisible(oid, "ivc-msgGenerateRoutingList")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-msgGenerateRoutingList", "Below is a list of approvers who can approve for each of the department selections you made in the order.  Choose the approver you want the order to route to (using the up/down buttons as necessary); however, keep in mind, that depending on the dollar value of your order, you could have a high-level approver pre-selected.  If this is the case, and you want additional approvers to see the order before the highest level approver, you need to check all the required approvers based on their fiscal authority shown. When you are finished selecting the approvers, click on the forward button to begin the routing")%>.</td></tr>
<%		} else {%>
		<tr><td class=boldMessage><%=ruleError%></td></tr>
<%		}%>
		</table>

		<table border=0 cellpadding=2 cellspacing=0>
		<tr><td><br></td></tr>
		<tr>
			<td class=label align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivcTotal", "Invoice Total")%>:</td>
			<td><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTotal(), s_curr_code, oid)%></td><td>&nbsp</td>
			<td>[ <%=HiltonUtility.getCurrencyConvert(invoiceHeader.getInvoiceTotal(), s_curr_code, oid)%> ]</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<table border="0" cellspacing="0" cellpadding="2" width="680px">
<%	if (bPassed) {%>
<tr>
	<td valign=top align=center>
		<div id="routinglist" style="display:none;">
<%
		for (int ir=0; ir < approvalRuleList.size(); ir++) {
			String approvalRule = (String) approvalRuleList.get(ir);
			Map ruleMap = (Map) routingListGroups.get(approvalRule);
			List routingList = (List) ruleMap.get("ruleRoutingList");
			BigDecimal amountToApprove = (BigDecimal) ruleMap.get("amountToApprove");
			if (routingList == null) { routingList = new ArrayList(); }
%>
        <input type=hidden name="firstGroupApproverSeq" value="<%=iapprovers%>">

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
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingRequiredAuthority", "Required Authority")%>:</td>
					<td><%=HiltonUtility.getCurrencyConvert(amountToApprove, s_curr_code, oid)%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr height="20px">
			<td>
				<table border="0" align="center" width="25px">
			<% if (ivcAction.equalsIgnoreCase("FORWARD") && bPassed && routingList.size() > 1) { %>
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
				<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 0px; width: 574px; align:center; overflow-y:auto; overflow-x:auto;">
				<table border="0" cellspacing="0" cellpadding="1" width="570px" height=18px>
				<tr>
					<td class="browseHdrDk" nowrap width="4%"><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=0></td>
					<td class="browseHdrDk" nowrap width="26%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver Name")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="23%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="18%" align=right <%=HtmlWriter.isVisible(oid, "routingMinAuthority")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingMinAuthority", "Min. $ Authority")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="20%" align=right <%=HtmlWriter.isVisible(oid, "routingAuthority")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAuthority", "Authority")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="2%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=16px height=1px></td>
					<td class="browseHdrDk" nowrap width="5%" align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingSend", "Send")%></td>
					<td class="browseHdrDk" nowrap width="2%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></td>
				</tr>
				</table>
				<table id="approvers<%=ir%>" border="0" cellpadding="1" cellspacing="0" width="570px" class="browseRow">
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

					String	s_approver = appLog.getComp_id().getUserId();
					String	s_callForward = appLog.getCallForward();
					String	callForwardName = "";

					if (!s_callForward.startsWith("@") && !s_approver.equals(s_callForward)) {
						UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());
						callForwardName = callForward.getDisplayName();
					} else if (s_callForward.startsWith("@") && !s_approver.equals(appLog.getPoolid())) {
						// Call Forward for user is set to a pool
						callForwardName = appLog.getPooldesc();
					}

					if (ivcAction.equalsIgnoreCase("FORWARD")) {
						if (HiltonUtility.isEmpty(callForwardName)) {
							callForwardName = "&nbsp;-&nbsp";
						//Functionality to allow buyers to specify the call forward for an approver will be added later
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
					} else {%>
				<tr height="15px">
					<td width="4%" class="<%=classType%>" NOWRAP align="right">
		<%		if (appLog.getApproved().equals("Y"))	{
						if (appLog.getRecommendation().equals("A")) {%>
						<img src="<%=contextPath%>/images/check_partial.gif">
		<%			} else {%>
						<img src="<%=contextPath%>/images/check.gif">
		<%			}
					} %>
						<span id="sequence_<%=ir%>_<%=irow%>"><%=irow + 1%><%// Temp. comment out for demo purposed appLog.getComp_id().getSequence()%></span>
						&nbsp;
					</td>
					<td width="26%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>" NOWRAP>
		<%		if (ivcAction.equalsIgnoreCase("FORWARD") && bPassed && routingList.size() > 1) { %>
						<a href="javascript: void(0);" onClick="highlightRow(<%=ir%>,<%=irow%>);"><span id="approverText_<%=ir%>_<%=irow%>"><%=appLog.getApproverName()%></span></a>&nbsp;
		<%		} else { %>
						<%=appLog.getApproverName()%>
		<%		} %>
						<span id="fyiApproverText_<%=ir%>_<%=irow%>">
		<%		if (appLog.isAnFyiApprover()) {%>
						<img src="<%=contextPath%>/images/fyi_text_blue.gif" border=0 alt="This approver will receive an FYI notification upon approval of this order.">
		<%		}%>
						<input type=hidden name="originalFyiApprover" value="<%=appLog.getFyiApprover()%>">
						<input type=hidden name="ApprovalLog_fyiApprover" value="<%=appLog.getFyiApprover()%>">
						</span>
						<span id="advisorText_<%=ir%>_<%=irow%>" align=left>
		<%		if (appLog.isAnAdvisor()) {%>
						<img src="<%=contextPath%>/images/double_astrick.gif" border=0 alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAdvisorMsg", "Advisor - will make a recommendation for or against the approval of this order.")%>">
		<%		}%>
						<input type=hidden name="originalAdvisor" value="<%=appLog.getAdvisor()%>">
						<input type=hidden name="ApprovalLog_advisor" value="<%=appLog.getAdvisor()%>">
						</span>
						<span id="requiredApproverText_<%=ir%>_<%=irow%>">
						<input type=hidden name="originalRequiredApprover" value="<%=appLog.getRequiredApprover()%>">
						<input type=hidden name="ApprovalLog_requiredApprover" value="<%=appLog.getRequiredApprover()%>">
						</span>
					</td>
					<td width="23%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%> class="<%=classType%>" NOWRAP><span id="callForwardText_<%=ir%>_<%=irow%>"><%=callForwardName%>&nbsp;</span></td>
					<td width="18%" <%=HtmlWriter.isVisible(oid, "routingMinAuthority")%> class="<%=classType%>" align="right" NOWRAP><span id="minAuthorityText_<%=ir%>_<%=irow%>"><%=minAuthority%></span></td>
					<td width="20%" <%=HtmlWriter.isVisible(oid, "routingAuthority")%> class="<%=classType%>" align="right" NOWRAP><span id="authorityText_<%=ir%>_<%=irow%>"><%=authority%></span></td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
						<input type=hidden name="originalApprovalAmount" value="<%=appLog.getApproverAmount()%>">
						<input type=hidden name="ApprovalLog_approvalAmount" value="<%=appLog.getApproverAmount()%>">
					</td>
					<td width="2%" NOWRAP class="<%=classType%>" align=center>
		<%		if (!HiltonUtility.isEmpty(appLog.getRuleNotes())) {%>
						<a href="javascript: getRuleNotes(<%=ir%>,<%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" alt="View approval rule notes." border="0"></a>
		<%		}%>
					</td>
					<td width="5%" class="<%=classType%>" align=center>
						<span id="sendToText_<%=ir%>_<%=irow%>">
						<input type=checkbox name="sendTo" value="Y" <%	if ( sendToAlwaysChecked || appLog.isSendTo() ) {%>checked<% if (appLog.isARequiredApprover()) {%> disabled<%}} else if (enforceExcludeLess && appLog.getExcludeLess().compareTo(amountToApprove) > 0) {%> disabled<%}%>>
						<input type=hidden name="ApprovalLog_sendTo" value="">
						</span>
					</td>
					<td width="2%" NOWRAP class="<%=classType%>">
						<span id="deleteLink_<%=ir%>_<%=irow%>" style="border:1">
		<%	//	Functionality to allow buyers to delete system generated approvers will be added later
				//	if ((!appLog.isARequiredApprover() || appLog.getRuleType().equals("MAN")) && poAction.equalsIgnoreCase("FORWARD")) { %>
		<%		if ((appLog.getRuleType().equals("MAN")) && ivcAction.equalsIgnoreCase("FORWARD")) { %>
						<a href="javascript: deleteUser(<%=ir%>, <%=irow%>, <%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0"></a>
		<%		} else {%>&nbsp;
						<!--a href="javascript: cannotDeleteUser(<%=ir%>, <%=irow%>, <%=iapprovers%>); void(0);"><img src="<%=contextPath%>/images/delete_disabled.gif" alt="Required Approver - Cannot Delete" border="0"></a-->
		<%		}%>
						</span>
					</td>
					<span id="hiddenFields_<%=ir%>_<%=irow%>">
						<input type=hidden name="ruleNotes" value="<%=appLog.getRuleNotes()%>">
				        <input type=hidden name="tableIndex" value="<%=ir%>">
						<input type=hidden name="ApprovalLog_sequence" value="<%=appLog.getComp_id().getSequence()%>">
						<input type=hidden name="ApprovalLog_userId" value="<%=appLog.getComp_id().getUserId()%>">
		                <input type=hidden name="ApprovalLog_amount" value="<%=appLog.getAmount()%>">
		                <input type=hidden name="ApprovalLog_approverAmount" value="<%=appLog.getApproverAmount()%>">
		                <input type=hidden name="ApprovalLog_approved" value="<%=appLog.getApproved()%>">
		                <input type=hidden name="ApprovalLog_udfValues" value="<%=appLog.getUdfValues()%>">
		                <input type=hidden name="ApprovalLog_authorized" value="<%=appLog.getAuthorized()%>">
		                <input type=hidden name="ApprovalLog_alternateUserid" value="<%=appLog.getAlternateUserid()%>">
		                <input type=hidden name="ApprovalLog_approverType" value="<%=appLog.getApproverType()%>">
		                <input type=hidden name="ApprovalLog_ruleType" value="<%=appLog.getRuleType()%>">
		                <input type=hidden name="ApprovalLog_poolid" value="<%=appLog.getPoolid()%>">
		                <input type=hidden name="ApprovalLog_pooldesc" value="<%=appLog.getPooldesc()%>">
		                <input type=hidden name="ApprovalLog_ruleAction" value="<%=appLog.getRuleAction()%>">
		                <input type=hidden name="ApprovalLog_approverSig" value="<%=appLog.getApproverSig()%>">
		                <input type=hidden name="ApprovalLog_callForward" value="<%=appLog.getCallForward()%>">
		                <input type=hidden name="ApprovalLog_approverName" value="<%=appLog.getApproverName()%>">
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
		        </div>

		        <input type=hidden name="approvalRule" value="<%=approvalRule%>">
		        <input type=hidden name="amountToApprove" value="<%=amountToApprove%>">
		      </td>
		  </tr>
		</table>
<%		}%>
		<br>

		<table border=0 cellspacing=2 cellpadding=0>
		<tr><td align=center>* <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-multipleApproverDesc", "* Approvers listed multiple times will only receive a notification to approve once.")%>.</tr>
		<tr <%=HtmlWriter.isVisible(oid, "routingAdvisorMsg")%>><td align=center><img src="<%=contextPath%>/images/double_astrick.gif" border=0>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAdvisorMsg", "Advisor - will make a recommendation for or against the approval of this order.")%></td></tr>
		</table>
<%	} %>

		<br>

		<table border="0" cellspacing="2" cellpadding="2" valign="bottom" width="680px">
		<tr id="_link">
		<%	if (ivcAction.equalsIgnoreCase("FORWARD")) {
				if (iapprovers > 0) { %>
			<td align="center"><div id="pxbutton"><a href="javascript: ivForward(); void(0);">Forward</a></div></td>
		<%		}
			} %>
			<td align="center"><div id="pxbutton"><a href="javascript: doSubmitHide()">Return</a></div></td>
		</tr>
		</table>
		</div>
	</td>
</tr>
<%	if (bPassed && ivcAction.equalsIgnoreCase("FORWARD") && !byPassRoutingList) { %>
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
							<!--td valign=middle align=center><img src="<%=contextPath%>/images/fyi_text_blue.gif" alt="Click here for help if you wish to send an FYI notification to an approver once this order has been awarded." border=0></td><td nowrap><a href="javascript: fyiApproverHelp(); void(0);">FYI approval notification will be sent</a></td-->
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
<tr>
	<td align=center width="100%" valign=middle>
		<div id="noroutinglist" style="display:none;">
			<table border=0 cellspacing=0 cellpadding=0 align=center height=150px>
		    	<tr valign=middle>
		          <td valign="middle"><img src='<%=contextPath%>/images/alert.gif' valign="middle" border="0"></td>
		          <td valign="middle" class="basic"><b>Please wait while we forward your order.</b></td>
		         </tr>
		    </table>
		</div>
	</td>
</tr>
</table>
<!-- dummy fields to prevent js errors -->
<div id="dummyFields" style="display:none;">
<input type=hidden name=approverName value="">
<input type=hidden name=ruleNotes value="">
<input type=hidden name=ApprovalLog_ruleType value="">
<input type=hidden name=ApprovalLog_ruleNotes value="">
<input type=hidden name=ApprovalLog_userId value="">
<input type=hidden name=ApprovalLog_sequence value="">
</div>

<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

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
<%	if(false)
	{ %>
		doSubmit('orders/po_forward_options.jsp', 'PoAward');
<%	}
	else if (byPassRoutingList && ivcAction.equals("FORWARD")) {%>
		displayArea('noroutinglist');
		ivForward();
<%} else if (bPassed) {%>
		displayArea('routinglist');
<%}%>

	function ivForward() {
		hideAreaWithBlock('_link');
		frm.ivcaction.value = "FORWARD";

		if (!isApprovalAuthorityMet()) {
			displayArea('_link');
			return false;
		}

		doSubmit('/invoice/iv_forwarded.jsp', 'ApprovalLogUpdateFinalList;InvoiceForward');
	}

	function doSubmitHide() {
		hideAreaWithBlock('_link');
		doSubmit('invoice/iv_review.jsp', 'DoNothing;InvoiceRetrieve');
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
		} else {
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
			doSubmitToPopup("/orders/po_routing_notes.jsp", "DoNothing");

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
			doSubmit('/orders/po_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
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
		doSubmit('/orders/po_routinglist_no_popup.jsp', 'ApprovalLogUpdateList');
	}

	function validateForm() {
		var dummyFields = document.getElementById("dummyFields");
		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = "";
		}
		if (frm.handler.value.indexOf("PoForward") >= 0)  {
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
		doSubmitToPopup('/orders/po_routing_help.jsp', 'DoNothing', 'WIDTH=650', 'HEIGHT=500');
	}

	function removeApproverHelp() {
		popupParameters = "helpType=REMOVEAPPROVERMSG";
		doSubmitToPopup('/orders/po_routing_help.jsp', 'DoNothing', 'WIDTH=650', 'HEIGHT=500');
	}

	function callForwardHelp() {
		popupParameters = "helpType=CALLFORWARDMSG";
		doSubmitToPopup('/orders/po_routing_help.jsp', 'DoNothing', 'WIDTH=650', 'HEIGHT=500');
	}

	function fyiApproverHelp() {
	}

	function callForward(currentApproverId) {
		popupParameters = "helpType=CALLFORWARD;currentApproverId=" + currentApproverId;
		doSubmitToPopup('/orders/po_routing_help.jsp', 'DoNothing', 'WIDTH=650', 'HEIGHT=500');
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
						if (!allowSelfApproval) {
						} else {
							authorityMet = true;
							break;
						}
					}
				}
			}

			if (!authorityMet && (rule == "USER-DEFINED") && (eval(amt) == eval(0))) {
				authorityMet = true;
			}

			if (!authorityMet) {
				msg = msg + "\n" + rule;
			}
		}

		if (!isEmpty(msg)) {
			alert("Approval authority has not been met for the following rule(s): \n" + msg);
			return false;
		}
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
		var sendto = false;

		if (sendtoCkboxes != undefined) {
			if (sendtoCkboxes.length != undefined && sendtoCkboxes.length > 1) {
				var tableIndex = 0;
				var tableRow = 0;
				var prevIndex = -1;
				var rows = sendtoCkboxes.length;

				for (var i=0; i < rows; i++) {
					sendto = sendtoCkboxes[i].checked;
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
						} else {
							frm.ApprovalLog_sendTo.value = "Y";
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


// end hiding contents -->
</SCRIPT>