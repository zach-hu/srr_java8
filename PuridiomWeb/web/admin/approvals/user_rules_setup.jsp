<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	appUdf1 = HiltonUtility.ckNull((String)propertiesManager.getProperty("APPROVALS", "APPUDF1", ""));
	if(appUdf1.indexOf("VendorId") >= 0)
	{
		appUdf1 = "VendorId";
	}
	String[] propertiesUdf1 = appUdf1.split(",");
	String browseUdf1 = "";
	if( propertiesUdf1.length > 4 ){
		browseUdf1 = propertiesUdf1[4];
	}
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_userId = HiltonUtility.ckNull((String) request.getAttribute("AppRule_userId"));
	boolean poolUser = false;
	String	currencyCode = CurrencyManager.getInstance(oid).getBaseCurrencyCode();
	String approverName = "";
	BigDecimal approvalAmount = new BigDecimal(0);
	BigDecimal excludeLess = new BigDecimal(0);
	String approveByLine = "";

	if (s_userId.startsWith("@"))
	{
		poolUser = true;
	} else
	{
		UserProfile approver = UserManager.getInstance().getUser(oid, s_userId);
		currencyCode = CurrencyManager.getInstance(oid).getCurrencyCodeForCountry(approver.getLocale());
		approverName = approver.getDisplayName();
		approvalAmount = approver.getApprovalAmount();
		excludeLess = approver.getExcludeLess();
		approveByLine = approver.getApproveByLine();
	}

	List appRuleList = (List) request.getAttribute("appRuleList");
	StringBuffer	appRuleTemplate = new StringBuffer();
	StringBuffer	appRuleTemplateMultiple = new StringBuffer();

	if (DictionaryManager.isVisible(oid, "appruleudf1")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1") + " = \" + " + "frm.AppRule_udf1Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1") + " = \" + " + "frm.AppRule_udf1Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf2")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf2", "UDF 2") + " = \" + " + "frm.AppRule_udf2Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf2", "UDF 2") + " = \" + " + "frm.AppRule_udf2Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf3")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf3", "UDF 3") + " = \" + " + "frm.AppRule_udf3Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf3", "UDF 3") + " = \" + " + "frm.AppRule_udf3Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf4")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf4", "UDF 4") + " = \" + " + "frm.AppRule_udf4Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf4", "UDF 4") + " = \" + " + "frm.AppRule_udf4Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf5")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf5", "UDF 5") + " = \" + " + "frm.AppRule_udf5Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf5", "UDF 5") + " = \" + " + "frm.AppRule_udf52Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf6")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf6", "UDF 6") + " = \" + " + "frm.AppRule_udf6Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf6", "UDF 6") + " = \" + " + "frm.AppRule_udf6Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf7")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf7", "UDF 7") + " = \" + " + "frm.AppRule_udf7Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf7", "UDF 7") + " = \" + " + "frm.AppRule_udf7Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf8")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf8", "UDF 8") + " = \" + " + "frm.AppRule_udf8Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf8", "UDF 8") + " = \" + " + "frm.AppRule_udf8Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf9")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf9", "UDF 9") + " = \" + " + "frm.AppRule_udf9Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf9", "UDF 9") + " = \" + " + "frm.AppRule_udf9Code[row].value + \"]");
	}
	if (DictionaryManager.isVisible(oid, "appruleudf10")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf10", "UDF 10") + " = \" + " + "frm.AppRule_udf10Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf10", "UDF 10") + " = \" + " + "frm.AppRule_udf10Code[row].value + \"]");
	}
%>

<tsa:hidden name="AppRule_userId" value="<%=headerEncoder.encodeForHTMLAttribute(s_userId)%>"/>
<tsa:hidden name="UserProfile_userId" value="<%=s_userId%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>
<tsa:hidden name="deleteall" value=""/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%= approverName %>&nbsp;(<%=headerEncoder.encodeForHTML(s_userId)%>)</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
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

<br>
<br>

<% 	if (!poolUser)
	{
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "authorizationAmount", "Authorization Amount")%>:&nbsp;</td>
	<td>
		<table border=0 celpadding=0 cellspacing=0>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> width=30px><%=currencyCode%></td>
			<td><input type="text" name="UserProfile_approvalAmount" value="<%=HiltonUtility.getFormattedDollar(approvalAmount, oid)%>" onchange="formatMe(this);" size="20" style="text-align:right"></td>
		</tr>
		</table>
	</td>
	<td>
		Use Amounts Below <input type="checkbox" name="cbox" value="" <% if (approveByLine.equals("Y")) { %> CHECKED <% } %> onClick="setCheckbox(frm.UserProfile_approveByLine)">
		<tsa:hidden name="UserProfile_approveByLine" value="<%= approveByLine %>"/>
	</td>
</tr>
<tr>
	<td valign=top align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "minimumAmount", "Minimum Amount")%>:&nbsp;</td>
	<td>
		<table border=0 celpadding=0 cellspacing=0>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> width=30px><%=currencyCode%></td>
			<td><input type="text" name="UserProfile_excludeLess" value="<%=HiltonUtility.getFormattedDollar(excludeLess, oid)%>" onchange="formatMe(this);" size="20" style="text-align:right"></td>
		</tr>
		</table>
	</td>
	<td>&nbsp;</td>
</tr>
</table>
<% } %>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width=10px>&nbsp;</td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
							<%--To set up a BROWSE use the Label Manager!--%>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1", false, true, "AppRule_udf1Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf2", "UDF 2", false, true, "AppRule_udf2Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf3", "UDF 3", false, true, "AppRule_udf3Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf4")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf4", "UDF 4", false, true, "AppRule_udf4Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf5")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf5", "UDF 5", false, true, "AppRule_udf5Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf6")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf6", "UDF 6", false, true, "AppRule_udf6Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf7")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf7", "UDF 7", false, true, "AppRule_udf7Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf8")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf8", "UDF 8", false, true, "AppRule_udf8Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf9")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf9", "UDF 9", false, true, "AppRule_udf9Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf10")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf10", "UDF 10", false, true, "AppRule_udf10Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulefyiapprover")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulefyiapprover", "FYI")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleadvisor")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleadvisor", "Advisor")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulerequiredapprover")%> height=18px width=75px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulerequiredapprover", "Required")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulenotes")%> height=18px width=25px nowrap align=center>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> height=18px width=25px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulecurrency", "")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleamount", "Amount")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleexcludeless", "Exclude Less")%></td>
							<td width=10px><img src="<%=contextPath%>/images/none.gif" border=0 width=9px height=1px></td>
						</tr>
						</table>

						<table id="rules" border=0 cellspacing=0 cellpadding=2 width=100%>
<%	if (appRuleList != null && appRuleList.size() > 0)
		{
			for (int i = 0; i < appRuleList.size(); i++)
			{
				AppRule appRule = (AppRule) appRuleList.get(i);
				AppRulePK appRulePK = appRule.getComp_id();

%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> class=browseRow width=100px><input type="text" name="AppRule_udf1Code" value="<%=appRulePK.getUdf1Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> class=browseRow width=100px><input type="text" name="AppRule_udf2Code" value="<%=appRulePK.getUdf2Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> class=browseRow width=100px><input type="text" name="AppRule_udf3Code" value="<%=appRulePK.getUdf3Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf4")%> class=browseRow width=100px><input type="text" name="AppRule_udf4Code" value="<%=appRulePK.getUdf4Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf5")%> class=browseRow width=100px><input type="text" name="AppRule_udf5Code" value="<%=appRulePK.getUdf5Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf6")%> class=browseRow width=100px><input type="text" name="AppRule_udf6Code" value="<%=appRulePK.getUdf6Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf7")%> class=browseRow width=100px><input type="text" name="AppRule_udf7Code" value="<%=appRulePK.getUdf7Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf8")%> class=browseRow width=100px><input type="text" name="AppRule_udf8Code" value="<%=appRule.getUdf8Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf9")%> class=browseRow width=100px><input type="text" name="AppRule_udf9Code" value="<%=appRule.getUdf9Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf10")%> class=browseRow width=100px><input type="text" name="AppRule_udf10Code" value="<%=appRule.getUdf10Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulefyiapprover")%> class=browseRow width=25px align=center><input type="checkbox" name="ck_fyiApprover" value="Y" <% if (appRule.isAnFyiApprover()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setFyiApprover(<%=i%>);"><tsa:hidden name="AppRule_fyiApprover" value="<%=appRule.getFyiApprover()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleadvisor")%> class=browseRow width=25px align=center><input type="checkbox" name="ck_advisor" value="Y" <% if (appRule.isAnAdvisor()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setAdvisor(<%=i%>);"><tsa:hidden name="AppRule_advisor" value="<%=appRule.getAdvisor()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulerequiredapprover")%> class=browseRow width=75px align=center><input type="checkbox" name="ck_requiredApprover" value="Y" <% if (appRule.isARequiredApprover()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setRequiredApprover(<%=i%>);"><tsa:hidden name="AppRule_requiredApprover" value="<%=appRule.getRequiredApprover()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulenotes")%> class=browseRow width=25px align=center><a href="javascript: getRuleNotes(<%=i%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" border=0></a><tsa:hidden name="AppRule_notes" value="<%=appRule.getNotes()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> class=browseRow width=25px><%=currencyCode%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> class=browseRow width=120px><input type="text" name="AppRule_amount" value="<%=HiltonUtility.getFormattedDollar(appRule.getAmount(), oid)%>" size="20" onchange="formatMe(<%=i%>);" onfocus="setCurrentRow(<%=i%>);" style="text-align:right"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> class=browseRow width=120px><input type="text" name="AppRule_excludeLess" value="<%=HiltonUtility.getFormattedDollar(appRule.getExcludeLess(), oid)%>" size="20" onchange="formatMe(<%=i%>);" onfocus="setCurrentRow(<%=i%>);" style="text-align:right"></td>
							<td id=delete_<%=i%> class=browseRow width=10px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
						</tr>
<%		}
		} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td width=50% align=center nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
	<td>&nbsp;</td>
	<td width=50% align=center nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveApprovalRules(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('approver-admin'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "approver-admin";
	setNavCookie("/admin/approvals/user_rules_setup.jsp", "AppRuleRetrieveBy", "Approval Rules");

	var myTable = document.getElementById("rules");
	var count = myTable.rows.length;

	if (count <= 0)
	{
		addNew();
	}

	currentRow = 0;

	function addNew()
	{
		myRow = createRow(myTable);

		myCell = createCell(myRow);
		id = "udf1";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf1Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf1")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf1")%>

		myCell = createCell(myRow);
		id = "udf2";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf2Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf2")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf2")%>

		myCell = createCell(myRow);
		id = "udf3";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf3Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf3")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf3")%>

		myCell = createCell(myRow);
		id = "udf4";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf4Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf4")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf4")%>

		myCell = createCell(myRow);
		id = "udf5";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf5Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf5")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf5")%>

		myCell = createCell(myRow);
		id = "udf6";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf6Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf6")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf6")%>

		myCell = createCell(myRow);
		id = "udf7";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf7Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf7")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf7")%>

		myCell = createCell(myRow);
		id = "udf8";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf8Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf8")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf8")%>

		myCell = createCell(myRow);
		id = "udf9";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf9Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf9")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf9")%>

		myCell = createCell(myRow);
		id = "udf10";
		myCell.id = id;
		myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"AppRule_udf10Code\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this);\" onfocus='setCurrentRow(" + count + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "appruleudf10")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleudf10")%>

		myCell = createCell(myRow);
		id = "fyiApprover";
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_fyiApprover\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick='setFyiApprover(" + count + ");'><input type=hidden name=\"AppRule_fyiApprover\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "apprulefyiapprover")%>
		<%=HtmlWriter.cellDisplay(oid, "apprulefyiapprover")%>

		myCell = createCell(myRow);
		id = "advisor";
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_advisor\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick='setAdvisor(" + count + ");'><input type=hidden name=\"AppRule_advisor\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "appruleadvisor")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleadvisor")%>

		myCell = createCell(myRow);
		id = "requiredApprover";
		myCell.id = id;
		myCell.width = "75px";
		myCell.align = "center";
		myCell.innerHTML = "<input type=checkbox name=\"ck_requiredApprover\" value=\"Y\" onfocus='setCurrentRow(" + count + ");' onclick='setRequiredApprover(" + count + ");'><input type=hidden name=\"AppRule_requiredApprover\" value=\"N\">";
		<%=HtmlWriter.cellVisibility(oid, "apprulerequiredapprover")%>
		<%=HtmlWriter.cellDisplay(oid, "apprulerequiredapprover")%>

		myCell = createCell(myRow);
		id = "notes";
		myCell.id = id;
		myCell.width = "25px";
		myCell.align = "center";
		myCell.innerHTML = "<a href=\"javascript: getRuleNotes(" + count + "); void(0);\"><img src=\"<%=contextPath%>/images/notes_line.gif\" border=0></a><input type=hidden name=\"AppRule_notes\" value=\"\">";
		<%=HtmlWriter.cellVisibility(oid, "apprulerequiredapprover")%>
		<%=HtmlWriter.cellDisplay(oid, "apprulerequiredapprover")%>

		myCell = createCell(myRow);
		id = "currency";
		myCell.id = id;
		myCell.width = "25px";
		myCell.innerHTML = "<%=currencyCode%>";
		<%=HtmlWriter.cellVisibility(oid, "apprulecurrency")%>
		<%=HtmlWriter.cellDisplay(oid, "apprulecurrency")%>

		myCell = createCell(myRow);
		id = "amount";
		myCell.id = id;
		myCell.width = "120px";
		myCell.innerHTML = "<input type=text name=\"AppRule_amount\" size=20 value=\"\" onchange=\"formatMe(" + count + ");\" onfocus='setCurrentRow(" + count + ");' style=\"text-align:right\">";
		<%=HtmlWriter.cellVisibility(oid, "appruleamount")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleamount")%>

		myCell = createCell(myRow);
		id = "excludeless";
		myCell.id = id;
		myCell.width = "120px";
		myCell.innerHTML = "<input type=text name=\"AppRule_excludeLess\" size=20 value=\"\" onchange=\"formatMe(" + count + ");\" onfocus='setCurrentRow(" + count + ");' style=\"text-align:right\">";
		<%=HtmlWriter.cellVisibility(oid, "appruleexcludeless")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleexcludeless")%>

		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "10px";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0);\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		currentRow = count;
		count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function saveApprovalRules()
	{
		var handlers = 'AppRuleUpdateByUser;BrowseRetrieve';

		<% if (!poolUser) { %>
		handlers = 'UserProfileUpdate;' + handlers;
		<% } %>

		doSubmit('/browse/browse.jsp', handlers);
	}


	function deleteMe(row)
	{
		if (confirm("Are you sure you wish to delete this rule?"))
		{
			var currentRows = myTable.rows.length;

			if (currentRows == 0)
			{
				return;
			}
			else if (currentRows > 1)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{
					var amount = frm.AppRule_amount[i + 1].value;
					var excludeLess = frm.AppRule_excludeLess[i + 1].value;

					frm.AppRule_udf1Code[i].value = frm.AppRule_udf1Code[i + 1].value;
					frm.AppRule_udf2Code[i].value = frm.AppRule_udf2Code[i + 1].value;
					frm.AppRule_udf3Code[i].value = frm.AppRule_udf3Code[i + 1].value;
					frm.AppRule_udf4Code[i].value = frm.AppRule_udf4Code[i + 1].value;
					frm.AppRule_udf5Code[i].value = frm.AppRule_udf5Code[i + 1].value;
					frm.AppRule_udf6Code[i].value = frm.AppRule_udf6Code[i + 1].value;
					frm.AppRule_udf7Code[i].value = frm.AppRule_udf7Code[i + 1].value;
					frm.AppRule_udf8Code[i].value = frm.AppRule_udf8Code[i + 1].value;
					frm.AppRule_udf9Code[i].value = frm.AppRule_udf9Code[i + 1].value;
					frm.AppRule_udf10Code[i].value = frm.AppRule_udf10Code[i + 1].value;
					frm.ck_fyiApprover[i].checked = frm.ck_fyiApprover[i + 1].checked;
					frm.AppRule_fyiApprover[i].value = frm.AppRule_fyiApprover[i + 1].value;
					frm.ck_advisor[i].checked = frm.ck_advisor[i + 1].checked;
					frm.AppRule_advisor[i].value = frm.AppRule_advisor[i + 1].value;
					frm.ck_requiredApprover[i].checked = frm.ck_requiredApprover[i + 1].checked;
					frm.AppRule_requiredApprover[i].value = frm.AppRule_requiredApprover[i + 1].value;
					frm.AppRule_amount[i].value = amount;
					frm.AppRule_excludeLess[i].value = excludeLess;
				}
			}

			myTable = document.getElementById("rules");
			myTable.deleteRow(currentRows - 1);

			if (currentRows <= 1)
			{
				frm.deleteall.value = "TRUE";
			}
			count--;

			if (count == 0)
			{
				addNew();
			}
		}
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all rules for this Approver?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("rules");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function formatMe(x)
	{
		var dollarDecimals = <%=s_dollar_decimals%>;

		if (x.name == "UserProfile_approvalAmount")
		{
			var amount = eval(nfilter(frm.UserProfile_approvalAmount));
			if (frm.UserProfile_approvalAmount.value != '')
			{
				frm.UserProfile_approvalAmount.value = nformat(amount, dollarDecimals);
			}
		}
		else 	if (x.name == "UserProfile_excludeLess")
		{
			var excludeLess = eval(nfilter(frm.UserProfile_excludeLess));
			if (frm.UserProfile_excludeLess.value != '')
			{
				frm.UserProfile_excludeLess.value = nformat(excludeLess, dollarDecimals);
			}
		}
		else
		{
			if (frm.AppRule_amount[x])
			{
				var amount = eval(nfilter(frm.AppRule_amount[x]));
				if (frm.AppRule_amount[x].value != '')
				{
					frm.AppRule_amount[x].value = nformat(amount, dollarDecimals);
				}
			}
			else
			{
				var amount = eval(nfilter(frm.AppRule_amount));
				if (frm.AppRule_amount.value != '')
				{
					frm.AppRule_amount.value = nformat(amount, dollarDecimals);
				}
			}

			if (frm.AppRule_excludeLess[x])
			{
				var excludeLess = eval(nfilter(frm.AppRule_excludeLess[x]));
				if (frm.AppRule_excludeLess[x].value != '')
				{
					frm.AppRule_excludeLess[x].value = nformat(excludeLess, dollarDecimals);
				}
			}
			else
			{
				var excludeLess = eval(nfilter(frm.AppRule_excludeLess));
				if (frm.AppRule_excludeLess.value != '')
				{
					frm.AppRule_excludeLess.value = nformat(excludeLess, dollarDecimals);
				}
			}
		}
	}

	function setCheckbox(fld)
	{
		fld.value = 'N';
		if ( frm.cbox.checked )
		{
			fld.value = 'Y';
		}
		return true;
	}

	function browseSetup(browseName, browseType, fieldName) {
		if (browseType.length > 0){
			browseStd(fieldName, browseType, true);
		} else if (browseName == "commodity") {
			browseCommodity(fieldName, 'commodity', '<%=PropertiesManager.getInstance(oid).getProperty("MISC", "COMMODITYTYPE", "")%>');
		} else if (browseName == "costCenter") {
			browseAccountFld('Account_fld3', browseType);
		} else {
			browseSchedule(fieldName, browseName);
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
				frm.AppRule_fyiApprover[row].value = fyiApprover;
			} else {
				if (ckboxes.checked) {
					fyiApprover = "Y";
				}
				frm.AppRule_fyiApprover.value = fyiApprover;
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
				frm.AppRule_advisor[row].value = advisor;
			} else {
				if (ckboxes.checked) {
					advisor = "Y";
				}
				frm.AppRule_advisor.value = advisor;
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
				frm.AppRule_requiredApprover[row].value = requiredApprover;
			} else {
				if (ckboxes.checked) {
					requiredApprover = "Y";
				}
				frm.AppRule_requiredApprover.value = requiredApprover;
			}
		}
	}

	function getRuleNotes(row) {
		var  ruleNotes = "";
		var	approvalRule = "";

		if (count > 1) {
			ruleNotes = frm.AppRule_notes[row].value;
			approvalRule = "<%=appRuleTemplateMultiple%>";
		} else {
			ruleNotes = frm.AppRule_notes.value;
			approvalRule = "<%=appRuleTemplate%>";
		}

		popupParameters = "rowCount=" + count + ";currentRow=" + row + ";approverId=" + frm.AppRule_userId.value + ";ruleNotes=" + ruleNotes + ";approvalRule=" + approvalRule + ";allowEdit=true";
		doSubmitToPopup('/base/apprule_notes.jsp', 'DoNothing', 'WIDTH=500', 'HEIGHT=300');
	}

	function browseCommodity(formField, xml, commodityType) {
		if (xml == "subcommodity") {
			popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('browse/browse_subcommodity_tree.jsp', 'SubCommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else if (!isEmpty(commodityType)) {
			popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";allowBrowse=" + frm.allowBrowse.value;
			doSubmitToPopup('browse/browse_commodity_tree.jsp', 'CommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else {
			browseSchedule(formField, xml);
		}
	}

	function browseVendor(frmField, multipleRows)
	{
		browseLookup(frmField, 'vendorbrowse-udf1', true);
	}

	function viewBrowseUDF1() {
		<% if(browseUdf1.equals("")){ %>
			browseSetup('user','','AppRule_udf1Code');
		<% }else{%>
			eval(<%=browseUdf1%>);
		<% }%>
	}

// End Hide script -->
</SCRIPT>