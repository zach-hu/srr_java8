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
	CurrencyManager currencyManager = CurrencyManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_app_udf_1 = HiltonUtility.ckNull((String) request.getAttribute("AppRule_udf1Code"));
	String s_app_description = HiltonUtility.ckNull((String) request.getAttribute("AppRule_Description"));
	String	appUdf1 = HiltonUtility.ckNull((String)propertiesManager.getProperty("APPROVALS", "APPUDF1", ""));
	if(appUdf1.indexOf("VendorId") >= 0)
	{
		appUdf1 = "VendorId";
	}
	String s_description = "";
	String fromMain = HiltonUtility.ckNull((String) request.getAttribute("fromMainMenu"));
	List appRuleList = (List) request.getAttribute("appRuleList");
	StringBuffer	appRuleTemplate = new StringBuffer();
	StringBuffer	appRuleTemplateMultiple = new StringBuffer();
	StdTable stdTable = (StdTable) request.getAttribute("stdTable");
	if (stdTable != null)
	{
		s_description = stdTable.getDescription();
	}
	if(HiltonUtility.isEmpty(s_description))
	{
		s_description = s_app_description;
	}

	if (DictionaryManager.isVisible(oid, "appruleudf1")) {
		appRuleTemplate.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1") + " = \" + " + "frm.AppRule_udf1Code.value + \"]");
		appRuleTemplateMultiple.append("[" + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1") + " = \" + " + "frm.AppRule_udf1Code.value + \"]");
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

<!--input type="HIDDEN" name="AppRule_userId" value=""-->
<!--input type="HIDDEN" name="UserProfile_userId" value=""-->
<tsa:hidden name="UserProfile_organizationId" value="<%=oid%>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="AppRule_udf1Code" value="<%=s_app_udf_1%>"/>
<tsa:hidden name="fromMainMenu" value="<%=fromMain%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_app_udf_1%>&nbsp;-&nbsp;<%=s_description%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width=5px>&nbsp;</td>
	<td>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2>
						<tr>
					<%	 if (fromMain.equals("true")) { %>
							<td height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprover", "Approver")%></td>
					<%   } else {  %>
							<td height=18px width=100px nowrap><a href="javascript: browseSchedule('AppRule_userId', 'approver-admin'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprover", "Approver")%></a></td>
					<%   } 		  %>
							<td height=18px width=135px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver Name")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf2", "UDF 2", false, true, "AppRule_udf2Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> height=18px width=100px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf3", "UDF 3", false, false, "AppRule_udf3Code")%></td>
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
							<td <%=HtmlWriter.isVisible(oid, "apprulenotes")%> height=18px width=25px nowrap align=center class=label>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> height=18px width=30px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulecurrency", "")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleamount", "Amount")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleexcludeless", "Exclude Less")%></td>
							<td width=10px><img src="<%=contextPath%>/images/none.gif" border=0 width=9px height=1px></td>
						</tr>
						</table>

						<table id="rules" border=0 cellspacing=0 cellpadding=2>
<%	if (appRuleList != null && appRuleList.size() > 0)
		{
			for (int i = 0; i < appRuleList.size(); i++)
			{
				AppRule appRule = (AppRule) appRuleList.get(i);
				AppRulePK appRulePK = appRule.getComp_id();
				String s_userId = (String) appRulePK.getUserId();
				UserProfile approver = UserManager.getInstance().getUser(oid, s_userId);
				BigDecimal bd_approvalAmount = new BigDecimal(0);
				BigDecimal bd_excludeLess = new BigDecimal(0);
				if (approver.getApproveByLine().equalsIgnoreCase("Y")) {
					bd_approvalAmount = HiltonUtility.getFormattedDollar(appRule.getAmount(), oid);
					bd_excludeLess = HiltonUtility.getFormattedDollar(appRule.getExcludeLess(), oid);
				} else {
					bd_approvalAmount = HiltonUtility.getFormattedDollar(approver.getApprovalAmount(), oid);
					bd_excludeLess = HiltonUtility.getFormattedDollar(approver.getExcludeLess(), oid);
				}
				if (!approver.getStatus().equals("03")) {
%>
						<tr>
							<td class=browseRow width=90px><input type="text" name="AppRule_userId" value="<%=s_userId%>" size="15" maxlength="20" onchange="upperCase(this); getNewInfo('approver', this, <%=i%>);" onfocus="setCurrentRow(<%=i%>);">
								<tsa:hidden name="UserProfile_userId" value="<%=s_userId%>"/>
							</td>
							<td class=browseRow width=135px nowrap><input type="text" name="as_approverName" value="<%=approver.getDisplayName()%>" size=24 onfocus="this.blur();" disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> width=100px class=browseRow><input type="text" name="as_udf1Code" value="<%=appRulePK.getUdf1Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="this.blur();" disabled></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> width=100px class=browseRow><input type="text" name="AppRule_udf2Code" value="<%=appRulePK.getUdf2Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> width=100px class=browseRow><input type="text" name="AppRule_udf3Code" value="<%=appRulePK.getUdf3Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf4")%> width=100px class=browseRow><input type="text" name="AppRule_udf4Code" value="<%=appRulePK.getUdf4Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf5")%> width=100px class=browseRow><input type="text" name="AppRule_udf5Code" value="<%=appRulePK.getUdf5Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf6")%> width=100px class=browseRow><input type="text" name="AppRule_udf6Code" value="<%=appRulePK.getUdf6Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf7")%> width=100px class=browseRow><input type="text" name="AppRule_udf7Code" value="<%=appRulePK.getUdf7Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf8")%> width=100px class=browseRow><input type="text" name="AppRule_udf8Code" value="<%=appRule.getUdf8Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf9")%> width=100px class=browseRow><input type="text" name="AppRule_udf9Code" value="<%=appRule.getUdf9Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf10")%> width=100px class=browseRow><input type="text" name="AppRule_udf10Code" value="<%=appRule.getUdf10Code()%>" size="15" maxlength="20" onchange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulefyiapprover")%> class=browseRow width=25px align=center><input type="checkbox" name="ck_fyiApprover" value="Y" <% if (appRule.isAnFyiApprover()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setFyiApprover(<%=i%>);"><tsa:hidden name="AppRule_fyiApprover" value="<%=appRule.getFyiApprover()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleadvisor")%> class=browseRow width=25px align=center><input type="checkbox" name="ck_advisor" value="Y" <% if (appRule.isAnAdvisor()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setAdvisor(<%=i%>);"><tsa:hidden name="AppRule_advisor" value="<%=appRule.getAdvisor()%>"/></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulerequiredapprover")%> class=browseRow width=75px align=center><input type="checkbox" name="ck_requiredApprover" value="Y" <% if (appRule.isARequiredApprover()) {%>checked<%}%> onfocus="setCurrentRow(<%=i%>);" onclick="setRequiredApprover(<%=i%>);"><tsa:hidden name="AppRule_requiredApprover" value="<%=appRule.getRequiredApprover()%>"/></td>
						<%	if (fromMain.equals("true")) { %>
							<td>&nbsp;&nbsp;&nbsp;</td>
						<% 	} else { %>
							<td <%=HtmlWriter.isVisible(oid, "apprulenotes")%> class=browseRow width=25px align=center><a href="javascript: getRuleNotes(<%=i%>); void(0);"><img src="<%=contextPath%>/images/notes_line.gif" border=0></a><tsa:hidden name="AppRule_notes" value="<%=appRule.getNotes()%>"/></td>
						<% 	} %>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> width=30px align=right><input type=text name="as_currencyCode" size=5 value="<%=currencyManager.getCurrencyCodeForCountry(approver.getLocale())%>" style="border:0; text-align:right;" readonly></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> class=browseRow width=120px><input type="text" name="AppRule_amount" value="<%=bd_approvalAmount%>" size="20" onchange="formatMe(<%=i%>); setApproveByLine();" onfocus="setCurrentRow(<%=i%>);" style="text-align:right"></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> class=browseRow width=120px><input type="text" name="AppRule_excludeLess" value="<%=bd_excludeLess%>" size="20" onchange="formatMe(<%=i%>); setApproveByLine();" onfocus="setCurrentRow(<%=i%>);" style="text-align:right">
								<tsa:hidden name="UserProfile_approvalAmount" value="<%=approver.getApprovalAmount()%>"/>
								<tsa:hidden name="UserProfile_excludeLess" value="<%=approver.getExcludeLess()%>"/>
								<tsa:hidden name="UserProfile_approveByLine" value="<%=approver.getApproveByLine()%>"/>
							</td>
					<%	if (fromMain.equals("true")) { %>
							<td>&nbsp;&nbsp;</td>
					<% 	} else { %>
							<td id=delete_<%=i%> class=browseRow width=10px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
					<% 	} %>
						</tr>
<%				}
			}
		} %>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<br>
					<%	if (!fromMain.equals("true")) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=450px align=center>
						<tr>
							<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
							<td>&nbsp;</td>
							<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
						</tr>
						</table>
					<% 	} %>
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
<br>

<%	if (fromMain.equals("true")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
  <tr>
  	<%	if(appUdf1.equalsIgnoreCase("VendorId")) {%>
			<td width=50% align=center><a href="javascript: returning(); browse('vendor-udf1'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
	<% 	} else 	{ %>
			<td width=50% align=center><a href="javascript: returning(); browse('app-udf1'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
	<% 	} %>
	</tr>
</table>
<%	} else { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
  <tr>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'AppRuleUserApprovalAmountUpdate;AppRuleUpdateByUdf1;BrowseRetrieve'); void(0);">
		Save</a></div>
	</td>
	<%	if(appUdf1.equalsIgnoreCase("VendorId")) {%>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('vendor-udf1'); void(0);">Cancel</a></div></td>
	<% 	} else 	{ %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/approvals/approvals_menu.jsp', 'DoNothing'); void(0);">Cancel</a><div></td>
	<% 	} %>
  </tr>
</table>
<%	} 	%>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
<%	if(appUdf1.equalsIgnoreCase("VendorId")) {%>
	frm.browseName.value = "vendor-udf1";
<% 	} else 	{ %>
	frm.browseName.value = "app-udf1";
<% 	} %>
	setNavCookie("/admin/approvals/user_rules_setup.jsp", "AppRuleRetrieveBy", "Approval Rules");

	var myTable = document.getElementById("rules");
	var count = myTable.rows.length;

	if (count <= 0)
	{
		addNew();
	}

	function addNew()
	{
		myRow = createRow(myTable);
		var innerHTMLString;

		myCell = createCell(myRow);
		id = "userId";
		myCell.id = id;
		innerHTMLString = "<input type=text name=\"AppRule_userId\" size=15 maxlength=20 value=\"\" onchange=\"upperCase(this); getNewInfo('approver', this, " + count + ");\" onfocus='setCurrentRow(" + count + ");'>";
		innerHTMLString = innerHTMLString + "<input type=hidden name=\"UserProfile_userId\" value=\"\">";
		myCell.innerHTML = innerHTMLString;

		myCell = createCell(myRow);
		id = "userName";
		myCell.id = id;
		myCell.innerHTML = "<input type=text name=\"as_approverName\" size=24 maxlength=20 value=\"\" onfocus='this.blur();' disabled>";

		myCell = createCell(myRow);
		id = "udf1";
		myCell.id = id;
		myCell.innerHTML = "<input type=text name=\"as_udf1Code\" size=15 maxlength=20 value=\"<%=s_app_udf_1%>\" onchange=\"upperCase(this);\" onfocus='this.blur();' disabled>";
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
		myCell.width = "30px";
		myCell.align = "right";
		myCell.innerHTML = "<input type=text name=\"as_currencyCode\" size=5 value=\"\" style=\"border:0; text-align:right;\" readonly >";
		<%=HtmlWriter.cellVisibility(oid, "apprulecurrency")%>
		<%=HtmlWriter.cellDisplay(oid, "apprulecurrency")%>

		myCell = createCell(myRow);
		id = "amount";
		myCell.id = id;
		myCell.width = "120px";
		myCell.innerHTML = "<input type=text name=\"AppRule_amount\" size=20 value=\"\" onchange=\"formatMe(" + count + "); setApproveByLine();\" onfocus='setCurrentRow(" + count + ");' style=\"text-align:right\">";
		<%=HtmlWriter.cellVisibility(oid, "appruleamount")%>
		<%=HtmlWriter.cellDisplay(oid, "appruleamount")%>

		myCell = createCell(myRow);
		id = "excludeless";
		myCell.id = id;
		myCell.width = "120px";
		innerHTMLString = "<input type=text name=\"AppRule_excludeLess\" size=20 value=\"\" onchange=\"formatMe(" + count + "); setApproveByLine();\" onfocus='setCurrentRow(" + count + ");' style=\"text-align:right\">";
		innerHTMLString = innerHTMLString + "<input type=hidden name=\"UserProfile_approvalAmount\" value=\"0\">"
		innerHTMLString = innerHTMLString + "<input type=hidden name=\"UserProfile_excludeLess\" value=\"0\">"
		innerHTMLString = innerHTMLString + "<input type=hidden name=\"UserProfile_approveByLine\" value=\"0\">"
		myCell.innerHTML = innerHTMLString;
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
					var userId	 = frm.AppRule_userId[i + 1].value;
					var udf1Code = frm.as_udf1Code[i + 1].value;
					var amount = frm.AppRule_amount[i + 1].value;
					var userApprovalAmt = frm.UserProfile_approvalAmount[i + 1].value;
					var excludeLess = frm.AppRule_excludeLess[i + 1].value;
					var userExcludeLess = frm.UserProfile_excludeLess[i + 1].value;
					var approveByLine = frm.UserProfile_approveByLine[i + 1].value;

					frm.AppRule_userId[i].value = userId;
					frm.UserProfile_userId[i].value = userId;
					frm.UserProfile_userId[i].value = userId;
					frm.as_approverName[i].value = frm.as_approverName[i + 1].value;
					frm.as_udf1Code[i].value = udf1Code;
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
					frm.UserProfile_approvalAmount[i].value = userApprovalAmt;
					frm.AppRule_excludeLess[i].value = excludeLess;
					frm.UserProfile_excludeLess[i].value = userExcludeLess;
					frm.UserProfile_approveByLine[i].value = approveByLine;
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
		} else {
			browseSchedule(fieldName, browseName);
		}
	}

	function setApproveByLine() {
		if (count == 1) {
			frm.UserProfile_approveByLine.value = "Y";
		} else {
			frm.UserProfile_approveByLine[currentRow].value = "Y";
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


	function thisLoad()
	{
		f_StartIt();
<%			if (fromMain.equals("true")) { %>
				checkInputSettings();
<%			} %>
	}

	function returning()
	{
			frm.allowBrowse.value = "true";
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

// End Hide script -->
</SCRIPT>
