<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.currcode.CurrencyManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	String s_userId = HiltonUtility.ckNull((String) request.getAttribute("AppRule_userId"));
	
	String	currencyCode = CurrencyManager.getInstance(oid).getBaseCurrencyCode();
	String approverName = "";
	
	if (!s_userId.startsWith("@")) 
	{
		UserProfile approver = UserManager.getInstance().getUser(oid, s_userId);
		currencyCode = CurrencyManager.getInstance(oid).getCurrencyCodeForCountry(approver.getLocale());
		approverName = approver.getDisplayName();
	}
	
	List appRuleList = (List) request.getAttribute("appRuleList");
%>

<tsa:hidden name="AppRule_userId" value="<%=s_userId%>"/>
<tsa:hidden name="UserProfile_userId" value="<%=s_userId%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%= oid %>"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="appMenuAction" value="SWAP"/>
<tsa:hidden name="OriginalApprover_userId" value="<%=s_userId%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%= approverName %>&nbsp;(<%=s_userId%>)</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width="50%" align=right><a href="javascript: browseLookup('NewApprover_userId', 'approver'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "Select Replacement Approver")%>:</a>&nbsp;</td>
	<td width="50%" ><input type="text" name="NewApprover_userId" value="" size="20" READONLY DISABLED></td>
</tr>
</table>

<br>
<br>

<table width=680px border=0 cellpadding=0 cellspacing=0>
<tr>
	<td align="center">
				<table border=1 cellspacing=0 cellpadding=0 class="browseHdr">
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 class="browseHdr">
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf1", "UDF 1", false, true, "AppRule_udf1Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf2", "UDF 2", false, true, "AppRule_udf2Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf3", "UDF 3", false, true, "AppRule_udf3Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf4")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf4", "UDF 4", false, true, "AppRule_udf4Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf5")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf5", "UDF 5", false, true, "AppRule_udf5Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf6")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf6", "UDF 6", false, true, "AppRule_udf6Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf7")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf7", "UDF 7", false, true, "AppRule_udf7Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf8")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf8", "UDF 8", false, true, "AppRule_udf8Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf9")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf9", "UDF 9", false, true, "AppRule_udf9Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf10")%> height=18px width=50px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleudf10", "UDF 10", false, true, "AppRule_udf10Code")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulefyiapprover")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulefyiapprover", "FYI")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleadvisor")%> height=18px width=25px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleadvisor", "Advisor")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulerequiredapprover")%> height=18px width=75px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulerequiredapprover", "Required")%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> height=18px width=25px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apprulecurrency", "")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleamount", "Amount")%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> height=18px width=120px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "appruleexcludeless", "Exclude Less")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>

						<table id="rules" border=0 cellspacing=0 cellpadding=2 width=100%>
<%	String rowClass = "browseRow";
		if (appRuleList != null && appRuleList.size() > 0)
		{
			for (int i = 0; i < appRuleList.size(); i++)
			{
				AppRule appRule = (AppRule) appRuleList.get(i);
				AppRulePK appRulePK = appRule.getComp_id();

%>
						<tr height="18px" class="<%=rowClass%>">
							<td <%=HtmlWriter.isVisible(oid, "appruleudf1")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf1Code()%>	</td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf2")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf2Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf3")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf3Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf4")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf4Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf5")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf5Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf6")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf6Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf7")%> class="<%=rowClass%>" width="50px"><%=appRulePK.getUdf7Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf8")%> class="<%=rowClass%>" width="50px"><%=appRule.getUdf8Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf9")%> class="<%=rowClass%>" width="50px"><%=appRule.getUdf9Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleudf10")%> class="<%=rowClass%>" width="50px"><%=appRule.getUdf10Code()%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulefyiapprover")%> class="<%=rowClass%>" width="25px" align="center"><%=appRule.getFyiApprover()%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleadvisor")%> class="<%=rowClass%>" width="25px" align="center"><%=appRule.getAdvisor()%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulerequiredapprover")%> class="<%=rowClass%>" width="75px" align="center"><%=appRule.getRequiredApprover()%></td>
							<td <%=HtmlWriter.isVisible(oid, "apprulecurrency")%> class="<%=rowClass%>" width="25px"><%=currencyCode%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleamount")%> class="<%=rowClass%>" width="120px" align="right"><%=HiltonUtility.getFormattedCurrency(appRule.getAmount(), currencyCode, oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "appruleexcludeless")%> class="<%=rowClass%>" width="120px" align="right"><%=HiltonUtility.getFormattedCurrency(appRule.getExcludeLess(), currencyCode, oid)%></td>
						</tr>
<%			if ( !HiltonUtility.isEmpty(appRule.getNotes()) ) {	%>
						<tr class="<%=rowClass%>">
							<td>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "apprulenotes")%>  colspan="15" class="<%=rowClass%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "notes", "Notes")%>:&nbsp;<%=appRule.getNotes()%></td>
						</tr>
<%			}
				if (rowClass.equals("browseRow"))
				{
					rowClass = "summary";
				} else {
					rowClass = "browseRow";
				}
			}
		}
		else
		{	%>
						<tr height="30px">
							<td align="center"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ThereAreNoApprovalRules", "There are no approval rules associated with this user")%>.</b></td>
						</tr>
<%	}	%>
						</table>
					</td>
				</tr>
				</table>
			</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: swapApprovers(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: browse('approver-admin'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "approver-admin";
	setNavCookie("/admin/approvals/user_rules_swap.jsp", "AppRuleRetrieveBy", "Approval Rule Swap");

	function swapApprovers()
	{
<%	if (appRuleList == null || appRuleList.size() <= 0) {	%>
			alert("There are no approval rules associated with this user.");
<%	} else {	%>
			if (frm.NewApprover_userId.value.length <= 0)
			{
				alert("Please select an approver!");
			}
			else
			{
				if (confirm("Are you sure you want to re-assign all these rules?"))
				{
					doSubmit('/browse/browse.jsp', 'AppRuleSwapApprover;BrowseRetrieve');
					//doSubmit('/admin/approvals/user_rules_setup.jsp', 'AppRuleSwapApprover;AppRuleRetrieveByUser');
				}
			}
<%	}%>
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