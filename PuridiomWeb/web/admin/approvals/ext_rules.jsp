<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	CurrencyManager currencyManager = CurrencyManager.getInstance(oid);
	String currencyCode = currencyManager.getBaseCurrencyCode();
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	List appRulesExtList = (List) request.getAttribute("appRulesExtList");
	List ruleFilenameList = (List) request.getAttribute("ruleFilenameList");

	int rulOrder = Integer.parseInt(propertiesManager.getProperty("MISC", "RULORDER", "10"));
	int extOrder = Integer.parseInt(propertiesManager.getProperty("MISC", "EXTORDER", "20"));
	int rioOrder = Integer.parseInt(propertiesManager.getProperty("MISC", "RIOORDER", "30"));
%>

<tsa:hidden name="returnpage" value="approvalsmenu"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="AppRulesExt_ruleType" value="REQ"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Extended Approval Rules</div>
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

<table border=0 cellpadding=0 cellspacing=0 height=300px>
<tr>
	<td valign=top>
		<table id="rules" border=0 cellspacing=0 cellpadding=2 align=center>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruleorder")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleorder", "Order")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruleactive")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleactive", "Active")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprulefilename")%> height=18px width=125px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-apprulefilename", "Rule Filename")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruleaction")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleaction", "Action")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruletext")%> height=18px width=125px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruletext", "Rule Text")%></td>
			<td height=18px width=100px nowrap><a href="javascript: browseSchedule('AppRulesExt_routto', 'approver-admin'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleapprover", "Approver")%></a></td>
			<td height=18px width=135px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleapprovername", "Approver Name")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprulefyiapprover")%> height=18px width=25px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-apprulefyiapprover", "FYI")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruleadvisor")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleadvisor", "Advisor")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprulerequiredapprover")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-apprulerequiredapprover", "Required")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprulenotes")%> height=18px width=25px nowrap align=center>&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprrulerequiredauthority")%> height=18px width=120px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-apprrulerequiredauthority", "Required Authority")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-appruleamount")%> height=18px width=120px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruleamount", "Approver Amount")%></td>
			<td <%=HtmlWriter.isVisible(oid, "ext-apprulecurrency")%> height=18px width=30px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-apprulecurrency", "")%></td>
		</tr>
		</table>
		<table border=0 cellspacing=0 cellpadding=2 width=680px>
		<tr>
			<td align=center>
				<table border=0 cellspacing=0 cellpadding=2 width=100%>
				<tr>
					<td width=50% align=center>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-addnew", "Add new")%></B></font></a></td>
					<td width=50% align=center><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-delall", "Delete all")%></B></font></a>&nbsp;</td>
				</tr>
				</table>
				<br><br>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				  <tr>
					<td width=50% align=center>
						<a href="javascript: doSubmit('/admin/approvals/approvals_menu.jsp', 'AppRulesExtUpdateByRuleType'); void(0);">
						<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
					</td>
					<td width=50% align=center><a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
				  </tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id="ruleFilenameOptions" style="visiblity:hidden;display:none;">
<%	if (ruleFilenameList != null && ruleFilenameList.size() > 0) {
			for (int ir = 0; ir < ruleFilenameList.size(); ir++) {
				String fileName = (String) ruleFilenameList.get(ir);%>
		<select name="AppRulesExt_ruleFilename" size=1 onfocus=setCurrentRow(currentRow);><option value="<%=fileName%>"><%=fileName%></option></select>
<%		}
		} else {%>
	<select name="AppRulesExt_ruleFilename" size=1 onfocus=setCurrentRow(currentRow);><option value=" "><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noneavailable", "None Available")%></option></select>
<%	}%>
</div>

<div id="ruleActionOptions" style="visiblity:hidden;display:none;">
	<option value="A" selected>Approve</option>
	<option value="L">Final Approver</option>
	<option value="Z">Bypass</option>
</div>

<div id="requiredAuthorityOptions" style="visiblity:hidden;display:none;">
	<option value="0" selected>0.00</option>
	<option value="TOTAL">Requisition Total</option>
</div>

<div id="propertyValues"  style="visiblity:hidden;display:none;"></div>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="ck_ruleActive" value=""/>
<tsa:hidden name="AppRulesExt_enabled" value=""/>
<tsa:hidden name="AppRulesExt_ruleOrder" value=""/>
<tsa:hidden name="AppRulesExt_ruleFilename" value=""/>
<tsa:hidden name="AppRulesExt_ruleAction" value=""/>
<tsa:hidden name="AppRulesExt_ruleText" value=""/>
<tsa:hidden name="AppRulesExt_routto" value=""/>
<tsa:hidden name="as_approverName" value=""/>
<tsa:hidden name="ck_fyiApprover" value=""/>
<tsa:hidden name="AppRulesExt_fyiApprover" value=""/>
<tsa:hidden name="ck_advisor" value=""/>
<tsa:hidden name="AppRulesExt_advisor" value=""/>
<tsa:hidden name="ck_requiredApprover" value=""/>
<tsa:hidden name="AppRulesExt_requiredApprover" value=""/>
<tsa:hidden name="AppRulesExt_notes" value=""/>
<tsa:hidden name="as_currencyCode" value=""/>
<tsa:hidden name="AppRulesExt_approverAmount" value=""/>
<tsa:hidden name="AppRulesExt_requiredAuthority" value=""/>
<tsa:hidden name="AppRulesExt_reqdAuthObject" value=""/>
<tsa:hidden name="AppRulesExt_reqdAuthName" value=""/>
<tsa:hidden name="AppRulesExt_reqdAuthSource" value=""/>
<tsa:hidden name="AppRulesExt_reqdAuthValue" value=""/>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("admin/approvals/ext_rules.jsp", "DoNothing", "Extended Approval Rules");

	currentRow = 1;

	var ruleFilenameOptionsHTML = document.getElementById("ruleFilenameOptions").innerHTML;
	var ruleActionOptionsHTML = document.getElementById("ruleActionOptions").innerHTML;
	var requiredAuthorityOptionsHTML = document.getElementById("requiredAuthorityOptions").innerHTML;
	var myTable = document.getElementById("rules");
	var count = myTable.rows.length - 1;

<%
	boolean standardRulesDisplayed = false;
	boolean rioRulesDisplayed = false;

	if (appRulesExtList != null) {
		for (int i=0; i < appRulesExtList.size(); i++) {
			AppRulesExt appRulesExt = (AppRulesExt) appRulesExtList.get(i);
			UserProfile approver = UserManager.getInstance().getUser(oid, appRulesExt.getRoutto());
			int ruleOrder = Integer.parseInt(String.valueOf(appRulesExt.getRuleOrder()));
			if (!standardRulesDisplayed && extOrder < ruleOrder) {
				if (!rioRulesDisplayed && rioOrder < ruleOrder && extOrder < rioOrder) {
%>
	addStandardRulePlacement();
	addRioRulePlacement();
<%
					standardRulesDisplayed = true;
					rioRulesDisplayed = true;
				} else if (!rioRulesDisplayed && rioOrder < ruleOrder && extOrder > rioOrder) {
%>
	addRioRulePlacement();
	addStandardRulePlacement();
<%
					standardRulesDisplayed = true;
					rioRulesDisplayed = true;
				} else {
%>
	addStandardRulePlacement();
<%
					standardRulesDisplayed = true;
				}
			} else if (!rioRulesDisplayed && rioOrder < ruleOrder) {
%>
	addRioRulePlacement();
<%
				rioRulesDisplayed = true;
			}
%>
	addNew();

	frm.ck_ruleActive[count - 1].checked = <%=appRulesExt.isEnabledValue()%>;
	frm.AppRulesExt_enabled[count - 1].value = "<%=appRulesExt.getEnabled()%>";
	frm.AppRulesExt_ruleOrder[count - 1].value = "<%=appRulesExt.getRuleOrder()%>";
	frm.AppRulesExt_ruleFilename[count - 1].value = "<%=appRulesExt.getRuleFilename()%>";
	frm.AppRulesExt_ruleAction[count - 1].value = "<%=appRulesExt.getRuleAction()%>";
	frm.AppRulesExt_ruleText[count - 1].value = "<%=appRulesExt.getRuleText()%>";
	frm.AppRulesExt_routto[count - 1].value = "<%=appRulesExt.getRoutto()%>";
	frm.as_approverName[count - 1].value = "<%=approver.getDisplayName()%>";
	frm.ck_fyiApprover[count - 1].checked = <%=appRulesExt.isFyiApproverValue()%>;
	frm.AppRulesExt_fyiApprover[count - 1].value = "<%=appRulesExt.getFyiApprover()%>";
	frm.ck_advisor[count - 1].checked = <%=appRulesExt.isAnAdvisorValue()%>;
	frm.AppRulesExt_advisor[count - 1].value = "<%=appRulesExt.getAdvisor()%>";
	frm.ck_requiredApprover[count - 1].checked = <%=appRulesExt.isRequiredApproverValue()%>;
	frm.AppRulesExt_requiredApprover[count - 1].value = "<%=appRulesExt.getRequiredApprover()%>";
	frm.AppRulesExt_notes[count - 1].value = "<%=appRulesExt.getNotes()%>";
	frm.as_currencyCode[count - 1].value = "<%=currencyCode%>";
	frm.AppRulesExt_approverAmount[count - 1].value = "<%=HiltonUtility.getFormattedDollar(appRulesExt.getApproverAmount(), oid)%>";
	frm.AppRulesExt_requiredAuthority[count - 1].value = "<%=appRulesExt.getRequiredAuthority()%>";
	frm.AppRulesExt_reqdAuthObject[count - 1].value = "<%=appRulesExt.getReqdAuthObject()%>";
	frm.AppRulesExt_reqdAuthName[count - 1].value = "<%=appRulesExt.getReqdAuthName()%>";
	frm.AppRulesExt_reqdAuthSource[count - 1].value = "<%=appRulesExt.getReqdAuthSource()%>";
	frm.AppRulesExt_reqdAuthValue[count - 1].value = "<%=appRulesExt.getReqdAuthValue()%>";
<%	}
	}
	if (!standardRulesDisplayed && !rioRulesDisplayed && extOrder < rioOrder) {
%>
	addStandardRulePlacement();
	addRioRulePlacement();
<%
		standardRulesDisplayed = true;
		rioRulesDisplayed = true;
	} else if (!standardRulesDisplayed && !rioRulesDisplayed && extOrder > rioOrder) {
%>
	addRioRulePlacement();
	addStandardRulePlacement();
<%
		standardRulesDisplayed = true;
		rioRulesDisplayed = true;
	} else if (!standardRulesDisplayed) {%>
	addStandardRulePlacement();
<%	standardRulesDisplayed = true;
	}
	if (!rioRulesDisplayed) {%>
	addRioRulePlacement();
<%	rioRulesDisplayed = true;
	}%>

	if (count <= 0) {
		addNew();
	}

	setRequiredAuthorityFromOptions();

	function addNew() {
		var ruleOrderHTML = "";

		if (count > 0) {
			ruleOrderHTML = "<a href=\"javascript: moveUp(" + count + "); void(0);\"><img src=\"<%=contextPath%>/images/up_arrows.gif\" class=\"processOnImg\" border=0 alt=\"Move Up\"></a>&nbsp;";
		} else {
			ruleOrderHTML = "<img src=\"<%=contextPath%>/images/none.gif\" border=0 width=14px height=14px>&nbsp;";
		}
		ruleOrderHTML = ruleOrderHTML + "<a href=\"javascript: moveDown(" + count + "); void(0);\"><img src=\"<%=contextPath%>/images/down_arrows.gif\" class=\"processOnImg\" border=0 alt=\"Move Down\" id=\"downLink_" + count + "\"></a>" +
			"<input type=hidden name=\"AppRulesExt_ruleOrder\" size=10 maxlength=5 value=\"\" onfocus='setCurrentRow(" + count + ");' onChange='nfilter(this);'>";

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		id = "ruleOrder_" + count;
		myCell.id = id;
		myCell.width = "50px";
		myCell.innerHTML = ruleOrderHTML;
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleorder")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleorder")%>

		myCell = createCell(myRow);
		id = "enabled_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.innerHTML = "<input type=checkbox name=\"ck_ruleActive\" value=\"Y\"  onfocus=\"setCurrentRow(currentRow);\" onclick=\"setEnabled(" + count + ");\" checked><input type=hidden name=\"AppRulesExt_enabled\" value=\"Y\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleenabled")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleenabled")%>

		myCell = createCell(myRow);
		id = "ruleFilename_" + count;
		myCell.id = id;
		myCell.width = "125px";
		myCell.innerHTML =  ruleFilenameOptionsHTML;
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulefilename")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulefilename")%>

		myCell = createCell(myRow);
		id = "ruleAction_" + count;
		myCell.id = id;
		myCell.width = "50px";
		if (navigator.appName == "Microsoft Internet Explorer")
			myCell.innerHTML = "<select  name=\"AppRulesExt_ruleAction\" size=1 onfocus='setCurrentRow(" + count + ");'>" + ruleActionOptionsHTML + "</select>";
			else
				myCell.innerHTML = "<select  name=\"AppRulesExt_ruleAction\" size=1 onfocus='setCurrentRow(" + count + ");'><OPTION value=A selected>Approve</OPTION> <OPTION value=L>Final Approver</OPTION> <OPTION value=Z>Bypass</OPTION></select>";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleaction")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleaction")%>

		myCell = createCell(myRow);
		id = "ruleText_" + count;
		myCell.id = id;
		myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"AppRulesExt_ruleText\" size=40 maxlength=255 value=\"\" onfocus='setCurrentRow(" + count + ");'><input type=hidden name=\"AppRulesExt_notes\" value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruletext")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruletext")%>

		myCell = createCell(myRow);
		id = "routto_" + count;
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRulesExt_routto\" size=18 maxlength=16 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleroutto")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleroutto")%>

		myCell = createCell(myRow);
		id = "approverName_" + count;
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"as_approverName\" size=24 value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleroutto")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleroutto")%>

		myCell = createCell(myRow);
		id = "fyiApprover_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_fyiApprover\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick=\"setFyiApprover(" + count + ");\"><input type=hidden name=\"AppRulesExt_fyiApprover\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulefyiapprover")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulefyiapprover")%>

		myCell = createCell(myRow);
		id = "advisor_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_advisor\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick=\"setAdvisor(" + count + ");\"><input type=hidden name=\"AppRulesExt_advisor\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleadvisor")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleadvisor")%>

		myCell = createCell(myRow);
		id = "requiredApprover_" + count;
		myCell.id = id;
		myCell.width = "50px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_requiredApprover\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick=\"setRequiredApprover(" + count + ");\"><input type=hidden name=\"AppRulesExt_requiredApprover\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulerequiredapprover")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulerequiredapprover")%>

		myCell = createCell(myRow);
		id = "notes_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<a href=\"javascript: getRuleNotes(" + count + "); void(0);\"><img src=\"<%=contextPath%>/images/notes_line.gif\" border=0></a>";
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulenotes")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulenotes")%>

		myCell = createCell(myRow);
		id = "requiredAuthority_" + count;
		myCell.id = id;
		myCell.width = "125px";
		if (navigator.appName == "Microsoft Internet Explorer")
			myCell.innerHTML = "<select name=\"as_requiredAuthority\" size=1 onfocus=\"setCurrentRow(currentRow);\" >" + requiredAuthorityOptionsHTML + "</select>" +
					"<input type=hidden name=\"AppRulesExt_requiredAuthority\" value=\"\">" +
					"<input type=hidden name=\"AppRulesExt_reqdAuthObject\" value=\"\">" +
					"<input type=hidden name=\"AppRulesExt_reqdAuthName\" value=\"\">" +
					"<input type=hidden name=\"AppRulesExt_reqdAuthSource\" value=\"\">" +
					"<input type=hidden name=\"AppRulesExt_reqdAuthValue\" value=\"\">";
					else
						myCell.innerHTML = "<select name=\"as_requiredAuthority\" size=1 onfocus=\"setCurrentRow(currentRow);\" >" +"<OPTION value=0 selected>0.00</OPTION> <OPTION value=TOTAL>Requisition Total</OPTION></select>" +
						"<input type=hidden name=\"AppRulesExt_requiredAuthority\" value=\"\">" +
						"<input type=hidden name=\"AppRulesExt_reqdAuthObject\" value=\"\">" +
						"<input type=hidden name=\"AppRulesExt_reqdAuthName\" value=\"\">" +
						"<input type=hidden name=\"AppRulesExt_reqdAuthSource\" value=\"\">" +
						"<input type=hidden name=\"AppRulesExt_reqdAuthValue\" value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulerequiredauthority")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulerequiredauthority")%>

		myCell = createCell(myRow);
		id = "approverAmount_" + count;
		myCell.id = id;
		myCell.width = "120px";
		myCell.innerHTML = "<input type=text name=\"AppRulesExt_approverAmount\" size=20 value=\"\" onchange=\"formatMe(" + count + ");\" onfocus='setCurrentRow(" + count + ");' style=\"text-align:right\">";
		<%=HtmlWriter.cellVisibility(oid, "ext-appruleamount")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-appruleamount")%>

		myCell = createCell(myRow);
		id = "currency_" + count;
		myCell.id = id;
		myCell.width = "30px";
		myCell.innerHTML = "<input type=text name=\"as_currencyCode\" size=5 value=\"\" style=\"border:0;\" readonly >";
		<%=HtmlWriter.cellVisibility(oid, "ext-apprulecurrency")%>
		<%=HtmlWriter.cellDisplay(oid, "ext-apprulecurrency")%>

		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "10px";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0);\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		currentRow = count;
		count++;
	}

	function addRioRulePlacement() {
		addNew();

		var row = count - 1;

		setInnerHTML( "ruleFilename_" + row, "<input type=text name=\"AppRulesExt_ruleFilename\" value=\"Restricted Item Approval Rules\" size=40 style=\"border:0;\">");

		frm.ck_ruleActive[row].checked = true;
		frm.ck_ruleActive[row].readOnly = true;
		frm.ck_ruleActive[row].disabled = true;
		frm.ck_ruleActive[row].contentEditable = false;
		frm.AppRulesExt_enabled[row].value = "Y";
		frm.AppRulesExt_ruleFilename[row].readOnly = true;
		frm.AppRulesExt_ruleFilename[row].contentEditable = false;
		frm.AppRulesExt_ruleOrder[row].value = "<%=rioOrder%>";

		hideArea("ruleAction_" + row);
		hideArea("ruleText_" + row);
		hideArea("routto_" + row);
		hideArea("approverName_" + row);
		hideArea("fyiApprover_" + row);
		hideArea("advisor_" + row);
		hideArea("requiredApprover_" + row);
		hideArea("notes_" + row);
		hideArea("requiredAuthority_" + row);
		hideArea("approverAmount_" + row);
		hideArea("currency_" + row);
		hideArea("delete_" + row);
	}

	function addStandardRulePlacement() {
		addNew();

		var row = count - 1;

		setInnerHTML( "ruleFilename_" + row, "<input type=text name=\"AppRulesExt_ruleFilename\" value=\"Standard Approval Rules\" size=30 style=\"border:0;\">");

		frm.ck_ruleActive[row].checked = true;
		frm.ck_ruleActive[row].readOnly = true;
		frm.ck_ruleActive[row].disabled = true;
		frm.ck_ruleActive[row].contentEditable = false;
		frm.AppRulesExt_enabled[row].value = "Y";
		frm.AppRulesExt_ruleFilename[row].readOnly = true;
		frm.AppRulesExt_ruleFilename[row].contentEditable = false;
		frm.AppRulesExt_ruleOrder[row].value = "<%=extOrder%>";

		hideArea("ruleAction_" + row);
		hideArea("ruleText_" + row);
		hideArea("routto_" + row);
		hideArea("approverName_" + row);
		hideArea("fyiApprover_" + row);
		hideArea("advisor_" + row);
		hideArea("requiredApprover_" + row);
		hideArea("notes_" + row);
		hideArea("requiredAuthority_" + row);
		hideArea("approverAmount_" + row);
		hideArea("currency_" + row);
		hideArea("delete_" + row);
	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function deleteMe(row) {
		if (confirm("Are you sure you wish to delete this rule?")) {
			deleteMeConfirmed(row);
		}
	}

	function deleteMeConfirmed(row) {
		var currentRows = myTable.rows.length - 1;

		if (currentRows == 0) {
			return;
		}
		else {
			for (var i = row; i < (currentRows - 1); i++) {
				moveDown(i);
			}
		}

		myTable = document.getElementById("rules");
		myTable.deleteRow(currentRows);

		if (currentRows <= 1) {
			frm.deleteall.value = "TRUE";
		}
		count--;

		if (count == 0) {
			addNew();
		}
	}

	function deleteAll() {
		if (confirm("Are you sure you wish to delete all extended rules listed?")) {
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("rules");
			count = myTable.rows.length - 1;

			for (i = count; i > 0; i--) {
				myTable.deleteRow(i);
			}
			count = 0;
			addNew();
		}
	}

	function formatMe(x) {
		var dollarDecimals = <%=s_dollar_decimals%>;
		var amount = eval(nfilter(frm.AppRulesExt_approverAmount[x]));
		if (frm.AppRulesExt_approverAmount[x].value != '') {
			frm.AppRulesExt_approverAmount[x].value = nformat(amount, dollarDecimals);
		}
	}

	function setEnabled(row) {
		var enabled = "N";
		var ckboxes = frm.elements.item("ck_ruleActive");

		if (ckboxes != undefined) {
			if (ckboxes.length != undefined && ckboxes.length > 1) {
				if (ckboxes[row].checked) {
					enabled = "Y";
				}
				frm.AppRulesExt_enabled[row].value = enabled;
			} else {
				if (ckboxes.checked) {
					enabled = "Y";
				}
				frm.AppRulesExt_enabled = enabled;
			}
		}
	}

	function setFyiApprover(row) {
		var fyiApprover = "N";
		var ckboxes = frm.elements.item("ck_fyiApprover");

		if (ckboxes != undefined) {
			if (ckboxes.length != undefined && ckboxes.length > 1) {
				if (ckboxes[row].checked) {
					fyiApprover = "Y";
				}
				frm.AppRulesExt_fyiApprover[row].value = fyiApprover;
			} else {
				if (ckboxes.checked) {
					fyiApprover = "Y";
				}
				frm.AppRulesExt_fyiApprover.value = fyiApprover;
			}
		}
	}

	function setAdvisor(row) {
		var advisor = "N";
		var ckboxes = frm.elements.item("ck_advisor");

		if (ckboxes != undefined) {
			if (ckboxes.length != undefined && ckboxes.length > 1) {
				if (ckboxes[row].checked) {
					advisor = "Y";
				}
				frm.AppRulesExt_advisor[row].value = advisor;
			} else {
				if (ckboxes.checked) {
					advisor = "Y";
				}
				frm.AppRulesExt_advisor.value = advisor;
			}
		}
	}

	function setRequiredApprover(row) {
		var requiredApprover = "N";
		var ckboxes = frm.elements.item("ck_requiredApprover");

		if (ckboxes != undefined) {
			if (ckboxes.length != undefined && ckboxes.length > 1) {
				if (ckboxes[row].checked) {
					requiredApprover = "Y";
				}
				frm.AppRulesExt_requiredApprover[row].value = requiredApprover;
			} else {
				if (ckboxes.checked) {
					requiredApprover = "Y";
				}
				frm.AppRulesExt_requiredApprover = requiredApprover;
			}
		}
	}

	function getRuleNotes(row) {
		var ruleNotes = frm.AppRulesExt_notes[row].value;
		var approvalRule =  frm.AppRulesExt_ruleText[row].value;

		popupParameters = "ruleType=EXT;rowCount=2;currentRow=" + row + ";approverId=" + frm.AppRulesExt_routto[row].value + ";ruleNotes=" + ruleNotes + ";approvalRule=" + approvalRule + ";allowEdit=true";
		doSubmitToPopup('/base/apprule_notes.jsp', 'DoNothing', 'WIDTH=500', 'HEIGHT=300');
	}

	function setReqdAuthOptions() {
		myTable = document.getElementById("rules");
		count = myTable.rows.length - 1;

		for (row = 0; row < count; row++) {
			var reqdAuth = frm.as_requiredAuthority[row][frm.as_requiredAuthority[row].selectedIndex].value;
			if (reqdAuth == "0") {
				frm.AppRulesExt_requiredAuthority[row].value = "0.00";
				frm.AppRulesExt_reqdAuthObject[row].value = "";
				frm.AppRulesExt_reqdAuthName[row].value = "";
				frm.AppRulesExt_reqdAuthSource[row].value = "";
				frm.AppRulesExt_reqdAuthValue[row].value = "";
			} else if (reqdAuth == "TOTAL") {
				frm.AppRulesExt_requiredAuthority[row].value = "0.00";
				frm.AppRulesExt_reqdAuthObject[row].value = "header";
				frm.AppRulesExt_reqdAuthName[row].value = "getTotal";
				frm.AppRulesExt_reqdAuthSource[row].value = "incomingRequestObjectMethod";
				frm.AppRulesExt_reqdAuthValue[row].value = "";
			}
		}
	}

	function setRequiredAuthorityFromOptions() {
		myTable = document.getElementById("rules");
		count = myTable.rows.length - 1;

		for (row = 0; row < count; row++) {
			var object = frm.AppRulesExt_reqdAuthObject[row].value;
			var source = frm.AppRulesExt_reqdAuthSource[row].value;
			var name = frm.AppRulesExt_reqdAuthName[row].value;

			if (object == "header" && name == "getTotal" && source == "incomingRequestObjectMethod") {
				frm.as_requiredAuthority[row].value = "TOTAL";
			} else {
				frm.as_requiredAuthority[row].value = "0";
			}
		}
	}

	function moveUp(row) {
		myTable = document.getElementById("rules");
		count = myTable.rows.length - 1;

		if (row > 0 && count > 1) {
			moveInnerTextUp("enabled", row);
			moveInnerTextUp("ruleFilename", row);
			moveInnerTextUp("ruleAction", row);
			moveInnerTextUp("ruleText", row);
			moveInnerTextUp("routto", row);
			moveInnerTextUp("approverName", row);
			moveInnerTextUp("fyiApprover", row);
			moveInnerTextUp("advisor", row);
			moveInnerTextUp("requiredApprover", row);
			moveInnerTextUp("requiredAuthority", row);
			moveInnerTextUp("approverAmount", row);
			moveInnerTextUp("currency", row);
			moveDisplayUp("notes", row);
			moveDisplayUp("delete", row);
		}
	}

	function moveDown(row) {
		myTable = document.getElementById("rules");
		count = myTable.rows.length - 1;

		if (row >= 0 && count > 1) {
			moveInnerTextDown("enabled", row);
			moveInnerTextDown("ruleFilename", row);
			moveInnerTextDown("ruleAction", row);
			moveInnerTextDown("ruleText", row);
			moveInnerTextDown("routto", row);
			moveInnerTextDown("approverName", row);
			moveInnerTextDown("fyiApprover", row);
			moveInnerTextDown("advisor", row);
			moveInnerTextDown("requiredApprover", row);
			moveInnerTextDown("requiredAuthority", row);
			moveInnerTextDown("approverAmount", row);
			moveInnerTextDown("currency", row);
			moveDisplayDown("notes", row);
			moveDisplayDown("delete", row);
		}
	}

	function moveInnerTextUp(id, row) {
		var upText = document.getElementById(id + "_" + row).innerHTML;
		var downText = document.getElementById(id + "_" + (row - 1)).innerHTML;

		setInnerHTML(id + "_" + (row), downText);
		setInnerHTML(id + "_" + (row - 1), upText);
		moveDisplayUp(id, row);
	}

	function moveInnerTextDown(id, row) {
		var downText = document.getElementById(id + "_" + row).innerHTML;
		var upText = document.getElementById(id + "_" + (row + 1)).innerHTML;

		setInnerHTML(id + "_" + (row + 1), downText);
		setInnerHTML(id + "_" + (row), upText);
		moveDisplayDown(id, row);
	}


	function moveDisplayUp(id, row) {
		var upElement = document.getElementById(id + "_" + row);
		var downElement = document.getElementById(id + "_" + (row - 1));
		var upVisiblity = upElement.style.visibility;
		var downVisiblity = downElement.style.visibility;
		var upDisplay = upElement.style.display;
		var downDisplay = downElement.style.display;

		setupAreaDisplay(id + "_" + (row), downVisiblity, downDisplay);
		setupAreaDisplay(id + "_" + (row - 1), upVisiblity, upDisplay);
	}

	function moveDisplayDown(id, row) {
		var downElement = document.getElementById(id + "_" + row);
		var upElement = document.getElementById(id + "_" + (row + 1));
		var downVisiblity = downElement.style.visibility;
		var upVisiblity = upElement.style.visibility;
		var downDisplay = downElement.style.display;
		var upDisplay = upElement.style.display;

		setupAreaDisplay(id + "_" + (row + 1), downVisiblity, downDisplay);
		setupAreaDisplay(id + "_" + (row), upVisiblity, upDisplay);
	}

	function validateForm() {
		var handler = frm.handler.value;
		var alertMessage = "";

		if (handler.indexOf("AppRulesExtUpdateByRuleType") >= 0) {
			var rioApprovalRow = -1;
			var stdApprovalRow = -1;
			var ruleOrder = 1;
			myTable = document.getElementById("rules");
			count = myTable.rows.length - 1;

			for (row = 0; row < count; row++) {
				var active = frm.ck_ruleActive[row].checked;
				var action = frm.AppRulesExt_ruleAction[row].value;
				var ruleText = frm.AppRulesExt_ruleText[row].value;
				var approver = frm.AppRulesExt_routto[row].value;
				var ruleFilename = frm.AppRulesExt_ruleFilename[row].value;

				if (ruleFilename == "Standard Approval Rules") {
					stdApprovalRow = row;
				}
				else if (ruleFilename == "Restricted Item Approval Rules") {
					rioApprovalRow = row;
				} else if (active && action != "Z") {
					// Require approver & rule text
					if (isEmpty(ruleText)) {
						alertMessage = alertMessage + "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruletextrequired", "You must enter Rule Text for every active approval rule")%>.";
					}
					if (isEmpty(approver)) {
						alertMessage = alertMessage + "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ext-appruletextrequired", "You must enter or select an approver for every active approval rule")%>.";
					}
				}
				if (!isEmpty(alertMessage)) {
					alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "validation-failed", "Please fix the following problems")%>:\n" + alertMessage);
					return false;
				}
				frm.AppRulesExt_ruleOrder[row].value = ruleOrder;
				ruleOrder++;
			}

			var propertyValuesHTML = "<input type=hidden name=\"Property_section\" value=\"MISC\">" +
				"<input type=hidden name=\"Property_property\" value=\"EXTORDER\">" +
				"<input type=hidden name=\"Property_value\" value=\"" + (stdApprovalRow  + 1) + "\">" +
				"<input type=hidden name=\"Property_section\" value=\"MISC\">" +
				"<input type=hidden name=\"Property_property\" value=\"RIOORDER\">" +
				"<input type=hidden name=\"Property_value\" value=\"" + (rioApprovalRow  + 1) + "\">";

			setInnerHTML("propertyValues", propertyValuesHTML);

			frm.handler.value = frm.handler.value  + "PropertyUpdateHandler;";

			if (rioApprovalRow > stdApprovalRow) {
				if (rioApprovalRow > -1) {
					myTable = document.getElementById("rules");
					myTable.deleteRow(rioApprovalRow + 1);
				}
				if (stdApprovalRow > -1) {
					myTable = document.getElementById("rules");
					myTable.deleteRow(stdApprovalRow + 1);
				}
			} else {
				if (stdApprovalRow > -1) {
					myTable = document.getElementById("rules");
					myTable.deleteRow(stdApprovalRow + 1);
				}
				if (rioApprovalRow > -1) {
					myTable = document.getElementById("rules");
					myTable.deleteRow(rioApprovalRow + 1);
				}
			}

			setReqdAuthOptions();
		}
		return true;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
