<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	CurrCode currCode = (CurrCode) request.getAttribute("currCode");
	boolean newCurrCode = false;

	if (currCode == null)
	{
		currCode = new CurrCode();
		currCode.setDateEntered(d_today);
		currCode.setDateExpires(d_today);
		currCode.setOwner(uid);
		currCode.setStatus("02");
		newCurrCode = true;
	}
	else
	{
	    String newCurrCodeString = HiltonUtility.ckNull((String) request.getAttribute("newCurrCode"));
		if (newCurrCodeString.equals("Y"))
		{
	      newCurrCode = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, currCode.getOwner());
	String duplicateCurrCodeErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateCurrCodeErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateCurrCodeFailurePage" value="/admin/systemtables/currcode.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Currency Code</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateCurrCodeErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateCurrCodeErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=1 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px>Currency Code:&nbsp;</td>
			<td colspan=2><input type="text" name="CurrCode_currencyCode" value="<%=currCode.getCurrencyCode()%>" size=25 maxlength=15 <%	if (! newCurrCode) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td width=140px nowrap align=right>
<%	if (! newCurrCode) { %>
				<a href="javascript: if (verifyAction('Delete this currency code?')) { doSubmit('/browse/browse_sys_tables.jsp', 'CurrCodeDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Currency Code</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap>Currency Name:&nbsp;</td>
			<td colspan=3><input type="text" name="CurrCode_currencyName" value="<%=currCode.getCurrencyName()%>" size=70 maxlength=60></td>
		</tr>
		<tr>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td width=140px align=right nowrap>ISO Currency Code:&nbsp;</td>
					<td><input type="text" name="CurrCode_isoCurrency" value="<%=currCode.getIsoCurrency()%>" size=10 maxlength=3 onChange="alphaNumericCheck(this);"></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Conversion Rate:&nbsp;</td>
					<td><input type="text" name="CurrCode_conversionToBase" value="<%=currCode.getConversionToBase()%>" size=15 onchange="setBaseToCurrency();"></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Thousands Separator:&nbsp;</td>
					<td><input type="text" name="CurrCode_thousandsSeprtr" value="<%=currCode.getThousandsSeprtr()%>" size=10 maxlength=2></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Decimal Digits:&nbsp;</td>
					<td><input type="text" name="CurrCode_decimalDigits" value="<%=currCode.getDecimalDigits()%>" size=10></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Symbol Placement:&nbsp;</td>
					<td>
						<select name="CurrCode_symbolPlacement">
							<option value="$1" <% if (currCode.getSymbolPlacement().equals("$1")) { %> SELECTED <% } %>>$1</option>
							<option value="1$" <% if (currCode.getSymbolPlacement().equals("1$")) { %> SELECTED <%}%>>1$</option>
						</select>
					</td>
				</tr>
				</table>
			</td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td width=140px align=right nowrap>Symbol:&nbsp;</td>
					<td><input type="text" name="CurrCode_symbol" value="<%=currCode.getSymbol()%>" size=10 maxlength=5></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Base to Currency:&nbsp;</td>
					<td><input type="text" name="CurrCode_baseToCurrency" value="<%=currCode.getBaseToCurrency()%>" size=15 readonly="readonly"></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Decimal Separator:&nbsp;</td>
					<td><input type="text" name="CurrCode_decimalSeparator" value="<%=currCode.getDecimalSeparator()%>" size=10 maxlength=2></td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Leading Zero:&nbsp</td>
					<td>
						<input type="radio" name="CurrCode_leadingZero" value="0" <% if (currCode.getLeadingZero().equals("0")) { %>CHECKED<% } %> >0.1&nbsp;&nbsp;<input type="radio" name="CurrCode_leadingZero" value="1" <% if (currCode.getLeadingZero().equals("1")) { %>CHECKED<% } %>>.0
					</td>
				</tr>
				<tr>
					<td width=140px align=right nowrap>Negative Placement:&nbsp</td>
					<td>
						<select name="CurrCode_negativePlacement">
							<option value="($1)" <% if (currCode.getNegativePlacement().equals("($1)")) { %>SELECTED<% } %>>($1)</option>
							<option value="(1$)" <% if (currCode.getNegativePlacement().equals("(1$)")) { %>SELECTED<% } %>>(1$)</option>
							<option value="-$1" <% if (currCode.getNegativePlacement().equals("-$1")) { %>SELECTED<% } %>>-$1</option>
							<option value="$1-" <% if (currCode.getNegativePlacement().equals("$1-")) { %>SELECTED<% } %>>$1-</option>
							<option value="$-1" <% if (currCode.getNegativePlacement().equals("$-1")) { %>SELECTED<% } %>>$-1</option>
							<option value="-1$" <% if (currCode.getNegativePlacement().equals("-1$")) { %>SELECTED<% } %>>-1$</option>
							<option value="1-$" <% if (currCode.getNegativePlacement().equals("1-$")) { %>SELECTED<% } %>>1-$</option>
							<option value="1$-" <% if (currCode.getNegativePlacement().equals("1$-")) { %>SELECTED<% } %>>1$-</option>
						</select>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

		<br>
		<hr width=475px align=center class=browseHR>
		<br>

		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="CurrCode_status" onchange="setStatus();">
							<option value="01" <% if (currCode.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (currCode.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (currCode.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="CurrCode_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(currCode.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
				<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
				<td id="dateExpires">
					<input type=text name="CurrCode_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(currCode.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
					<a href="javascript: show_calendar('CurrCode_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
				</td>
			</tr>
			</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('CurrCode_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="CurrCode_owner" tabindex=51 size=30 maxlength=15 value="<%=currCode.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newCurrCode) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'CurrCodeUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'CurrCodeAdd'); void(0);">
<%	} %>
		<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
	</td>
	<td width=50% align=center><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "currcode-admin";
	setNavCookie("/admin/systemtables/currcode.jsp", "CurrCodeRetrieveById", "Currency Code <%=currCode.getCurrencyCode()%>");

	var status = "<%=currCode.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newCurrCode) { %>
			frm.CurrCode_currencyName.focus();
<%	} else { %>
			frm.CurrCode_currencyCode.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.CurrCode_status[frm.CurrCode_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setBaseToCurrency()
	{
		var conversionToBase = frm.CurrCode_conversionToBase.value;

		frm.CurrCode_baseToCurrency.value = nformat( 1 / conversionToBase, 5 );
	}

// End Hide script -->
</SCRIPT>