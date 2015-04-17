<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
%>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Approval Setup Options</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center valign=top width=60%>
			<table border=0 cellspacing=0 cellpadding=2>
<%	if (propertiesManager.isModuleActive("MSR")) {%>
			<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>MSR Approvals</b></td><td>&nbsp;</td></tr>
			<tr>
				<td align=right width=100px>
					<tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="ENGINEERAPPROVE"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"ENGINEERAPPROVE\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <%if (propertiesManager.getProperty("APPROVALS", "ENGINEERAPPROVE", "N").equalsIgnoreCase("Y")) {%>checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'ENGINEERAPPROVE');"/>
				</td>
				<td width=65%><tsa:label labelName="engineerApproval" defaultString="Enable Engineer Approval on MSR" checkRequired="false"/></td>
			</tr>
<% } %>
			<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b>Requisition Approvals</b></td><td>&nbsp;</td></tr>
<%	if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
			<tr>
				<td align=right width=100px>
					<tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="ApproveInvRequests"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"ApproveInvRequests\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","ApproveInvRequests","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'ApproveInvRequests');">
				</td>
				<td width=65%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approveInventoryRequests", "Approve Inventory Requests")%></td>
			</tr>
<%	}%>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="TermApprove"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"TermApprove\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","TermApprove","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'TermApprove');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "endApprovalDollarReached", "End approval list when dollar amount is reached")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="EnforceExcludeLess"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"EnforceExcludeLess\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","EnforceExcludeLess","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'EnforceExcludeLess');">
				</td>
				<td nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enforceMinDollarAuthorityApprovers", "Enforce min. dollar authority for user selection of approvers")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="Notify"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"Notify\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","Notify","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'Notify');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "notifyNextApproverEmail", "Notify next approver via email")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="Zero Approvals"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"Zero Approvals\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","Zero Approvals","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'Zero Approvals');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "forwardZeroDollarRequisitionPurchasing", "Forward zero dollar requisition to purchasing")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="ManualApproverZeroAuthority"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"ManualApproverZeroAuthority\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","ManualApproverZeroAuthority","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'ManualApproverZeroAuthority');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "setApprovalAmountZeroManuallyApprovers", "Set approval amount to zero when manually adding approvers")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="UserDefinedApprovals"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"UserDefinedApprovals\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","UserDefinedApprovals","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'UserDefinedApprovals');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allowDefinedApprovers", "Allow user defined approvers")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="AllowApprovalEscalation"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"AllowApprovalEscalation\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","AllowApprovalEscalation","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'AllowApprovalEscalation');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enableApprovalEscalation", "Enable approval escalation")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="FYIFIRSTAPPROVALS"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"FYIFIRSTAPPROVALS\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","FYIFIRSTAPPROVALS","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'FYIFIRSTAPPROVALS');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fyiFirstApprovals", "FYI Emails are sent before other approvals")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="sendTo-alwaysChecked"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"sendTo-alwaysChecked\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","sendTo-alwaysChecked","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'sendTo-alwaysChecked');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalsSendAlwaysChecked", "Approvals Send To always checked")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="EnableDistributionList"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"EnableDistributionList\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","EnableDistributionList","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'EnableDistributionList');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalsEnableDistributionList", "Enable Distribution List")%></td>
			</tr>
			<tr <%=HtmlWriter.isVisible(oid, "admin-routingApprovalPolicyHelp")%>>
				<td align=right><tsa:hidden name="Property_section" value="APPROVALS"/>
					<tsa:hidden name="Property_property" value="DisplayDefaultPolicy"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"DisplayDefaultPolicy\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("APPROVALS","DisplayDefaultPolicy","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'APPROVALS', 'DisplayDefaultPolicy');">
				</td>
				<td>
					<table border=0 cellpadding=1 cellspacing=0>
					<tr>
						<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "displayDefaultFinancialAuthorizationLimitations", "Display Default Financial Authorization Limitations Policy")%></td>
					  <td width=30px valign=middle align=right><a href="javascript: viewDefaultApprovalPolicy(); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0></a></td>
						<td height=22px valign=middle><a href="javascript: viewDefaultApprovalPolicy(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defaultPolicy", "Default Policy")%></a></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<table border=0 cellpadding=1 cellspacing=0>
					<tr>
						<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "resortRoutingList", "Resort routing list by")%></td>
						<td align=right><input type=radio name="APPROVALS_RESORTBY_VALUE" value="DOLLARAUTHORITY"<% if (propertiesManager.getProperty("APPROVALS","RESORTBY","").equalsIgnoreCase("DOLLARAUTHORITY")) {%> checked<%}%> onclick="setRadioValue('APPROVALS_RESORTBY_VALUE', 'APPROVALS', 'RESORTBY');"></td>
						<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dollarAuthority", "Dollar Authority")%></td>
						<td align=right><input type=radio name="APPROVALS_RESORTBY_VALUE" value="RULE"<% if (propertiesManager.getProperty("APPROVALS","RESORTBY","").equalsIgnoreCase("RULE")) {%> checked<%}%> onclick="setRadioValue('APPROVALS_RESORTBY_VALUE', 'APPROVALS', 'RESORTBY');"></td>
						<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rule", "Rule")%></td>
						<td align=right><input type=radio name="APPROVALS_RESORTBY_VALUE" value= '"' + '"' + "<% if (HiltonUtility.isEmpty(propertiesManager.getProperty("APPROVALS","RESORTBY",""))) {%> checked<%}%> onclick="setRadioValue('APPROVALS_RESORTBY_VALUE', 'APPROVALS', 'RESORTBY');"></td>
						<td>None</td>
						<td><tsa:hidden name="Property_section" value="APPROVALS"/>
						<tsa:hidden name="Property_property" value="RESORTBY"/>
						<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"APPROVALS\",\"RESORTBY\",\"\")%>"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationApprovals", "Solicitation Approvals")%></b></td></tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="RFQ APPROVALS"/>
					<tsa:hidden name="Property_property" value="ENABLED"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"RFQ APPROVALS\",\"ENABLED\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("RFQ APPROVALS","ENABLED","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'RFQ APPROVALS', 'ENABLED');">
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enableSolicitationapprovals", "Enable solicitation approvals")%></td>
			</tr>
			<tr><td colspan=2 height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderApprovals", "Order Approvals")%></b></td></tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="MODULES"/>
					<tsa:hidden name="Property_property" value="Po Approvals"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"MODULES\",\"Po Approvals\",\"N\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("MODULES","Po Approvals","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'MODULES', 'Po Approvals');">

				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enableOrderApprovals", "Enable order approvals")%></td>
			</tr>
			<tr>
				<td align=right><tsa:hidden name="Property_section" value="PO APPROVALS"/>
					<tsa:hidden name="Property_property" value="CONTRACTAPPROVALS"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"PO APPROVALS\",\"CONTRACTAPPROVALS\",\"Y\")%>"/>
					<input type=checkbox name="ckbox" value='Y'
						<% if (propertiesManager.getProperty("PO APPROVALS","CONTRACTAPPROVALS","Y")
								.equalsIgnoreCase("Y")) {%> checked<%}%>
						onchange="setCkBoxValue(this, 'PO APPROVALS', 'CONTRACTAPPROVALS');"/>
				</td>
				<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enableContractApprovals", "Enable contract approvals")%></td>
			</tr>
			</table>
	</td>
	<td valign=top width=40%>
		<table border=0 cellspacing=0 cellpadding=2 width=100%>
		<tr><td colspan=2 height=30px nowrap>&nbsp;&nbsp;<b>Invoice Approvals</b></td><td>&nbsp;</td></tr>
		<tr>
			<td valign=top align=right width=100px>
				<tsa:hidden name="Property_section" value="VOUCHER"/>
				<tsa:hidden name="Property_property" value="AutoRoutingList"/>
				<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"VOUCHER\",\"AutoRoutingList\",\"N\")%>"/>
				<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("VOUCHER","AutoRoutingList","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'VOUCHER', 'AutoRoutingList');">
			</td>
			<td>Auto Generate Routing List (Based on Requisitioners)</td>
		</tr>
		<tr>
			<td valign=top align=right>
				<tsa:hidden name="Property_section" value="VOUCHER"/>
				<tsa:hidden name="Property_property" value="ExtendedngRules"/>
				<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"VOUCHER\",\"ExtendedRouting\",\"N\")%>"/>
				<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("VOUCHER","ExtendedRouting","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'VOUCHER', 'ExtendedRouting');">
			</td>
			<td>Enable Extended Routing List Rules (Applies Requisition Routing Rules to Invoice Routing)</td>
		</tr>
		<tr>
			<td valign=top align=right>
				<tsa:hidden name="Property_section" value="VOUCHER"/>
				<tsa:hidden name="Property_property" value="INVOICEOWNERCANAPPROVE"/>
				<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"VOUCHER\",\"INVOICEOWNERCANAPPROVE\",\"N\")%>"/>
				<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("VOUCHER","INVOICEOWNERCANAPPROVE","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'VOUCHER', 'INVOICEOWNERCANAPPROVE');">
			</td>
			<td>Invoice Owner can Approve</td>
		</tr>
		<tr>
			<td valign=top align=right>
				<tsa:hidden name="Property_section" value="IVC APPROVALS"/>
				<tsa:hidden name="Property_property" value="UserDefinedApprovals"/>
				<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"IVC APPROVALS\",\"UserDefinedApprovals\",\"N\")%>"/>
				<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("IVC APPROVALS","UserDefinedApprovals","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'IVC APPROVALS', 'UserDefinedApprovals');">
			</td>
			<td><tsa:label labelName="ivc-allowDefinedApprovers" defaultString="Allow user defined approvers" /></td>

		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'PropertyUpdate'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<tsa:hidden name="allowBrowse" value="true"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/approvals/approvals_setup.jsp", "DoNothing", "Approval Setup Options");

	function setCkBoxValue(ckbox, s, p) {
		var value = "";
		var action = "c";

		if (ckbox.checked == true) {
			value = "Y";
		}
		else {
			value = "N";
		}
		setPropertyValue(s, p, value, action);
	}

	function setPropertyValue(s, p, v, a) {
		var count = document.all.Property_property.length;
		var section = "";
		var property = "";

		for (var i=0; i < count; i++) {
			section = frm.Property_section[i].value;
			property = frm.Property_property[i].value;

			if (section == s && property == p) {
				if (a=="a") {
					frm.Property_value[i].value = frm.Property_value[i].value + v;
				}
				else if (a=="r") {
					frm.Property_value[i].value = removeFrom(frm.Property_value[i].value,v);
				}
				else {
					frm.Property_value[i].value = v;
				}
			}
		}
	}

	function removeFrom(orgText, textToRemove) {
		while (orgText.indexOf(textToRemove) >= 0) {
			var subOrgTextBegin = orgText.substring(0, orgText.indexOf(textToRemove));
			var subOrgTextEnd   = orgText.substring(orgText.indexOf(textToRemove)+textToRemove.length, orgText.length)
			orgText = subOrgTextBegin + subOrgTextEnd;
		}
		return orgText;
	}

	function setRadioValue(fldName, s, p) {
		var options = frm.elements.item(fldName);
		var value = "";

		if (options != undefined && options.length != undefined) {
			var ind = 0;
	        for (var i = 0; i < options.length; i++) {
	            if (options[i].checked) {
    	            ind = i;
        	        break;
            	}
	        }
			value = options[ind].value;
		}

		setPropertyValue(s, p, value);
	}

	function getPropertyValue(s, p, v) {
		var count = document.all.Property_property.length;
		var section = "";
		var property = "";
		var setValue = v;

		for (var i=0; i < count; i++) {
			section = frm.Property_section[i].value;
			property = frm.Property_property[i].value;

			if (section == s && property == p) {
				setValue = frm.Property_value[i].value;
			}
		}

		return setValue;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>