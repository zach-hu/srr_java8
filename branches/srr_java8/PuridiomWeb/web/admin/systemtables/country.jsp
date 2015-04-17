<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	Country country = (Country) request.getAttribute("country");
	boolean newCountry = false;

	if (country == null)
	{
		country = new Country();
		country.setDateEntered(d_today);
		country.setDateExpires(d_today);
		country.setOwner(uid);
		country.setStatus("02");
		newCountry = true;
	}
	else
	{
	    String newCountryString = HiltonUtility.ckNull((String) request.getAttribute("newCountry"));
		if (newCountryString.equals("Y"))
		{
	      newCountry = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, country.getOwner());
	String duplicateCountryErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateCountryErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateCountryFailurePage" value="/admin/systemtables/country.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country", false)%></div>
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

<%	if (!HiltonUtility.isEmpty(duplicateCountryErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateCountryErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=1 cellspacing=0 width=450px align=center>
		<tr>
			<td width=140px align=right nowrap height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country", false)%> Code:&nbsp;</td>
			<td colspan=2><input type="text" name="Country_countryCode" value="<%=country.getCountryCode()%>" size=25 maxlength=15 <%	if (! newCountry) { %> disabled <% } %> onChange="alphaNumericCheck(this);"></td>
			<td width=140px nowrap align=right>
<%	if (! newCountry) { %>
				<a href="javascript: if (verifyAction('Delete this country?')) { doSubmit('/browse/browse_sys_tables.jsp', 'CountryDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete", false)%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country", false)%></a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "countryname", "Country Name", false)%>:&nbsp;</td>
			<td colspan=3><input type="text" name="Country_countryName" value="<%=country.getCountryName()%>" size=70 maxlength=60></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseStd('Country_language', 'LANG'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "language-instr", "Click here to select the default Language for this Country or enter the Language in the box on the right", false)%>."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "language", "Language", false)%>:</a>&nbsp;</td>
			<td colspan=3><input type="text" name="Country_language"  value="<%=country.getLanguage()%>" size=30 maxlength=4 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseLookup('Country_currencyCode', 'curr_code'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currency", "Currency", false)%> for this <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country", false)%> or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currency", "Currency", false)%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currency", "Currency", false)%>:</a>&nbsp;</td>
			<td colspan=3><input type="text" name="Country_currencyCode"  value="<%=country.getCurrencyCode()%>" size=30 maxlength=25 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseStd('Country_timeZone', 'TMZN'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "timezone-instr", "Click here to select the default Time Zone for this Country or enter the Time Zone in the box on the right", false)%>."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "timezone", "Time Zone", false)%>:</a>&nbsp;</td>
			<td colspan=3><input type="text" name="Country_timeZone"  value="<%=country.getTimeZone()%>" size=30 maxlength=4 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td width=140px align=right nowrap><a href="javascript: browseStd('Country_dateFormat', 'DFMT'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateformat-instr", "Click here to select the default Date Format for this Country or enter the Date Format in the box on the right", false)%>."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateformat", "Date Format", false)%>:</a>&nbsp;</td>
			<td colspan=3><input type="text" name="Country_dateFormat"  value="<%=country.getDateFormat()%>" size=30 maxlength=10 onchange="upperCase(this);"></td>
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
						<select name="Country_status" onchange="setStatus();">
							<option value="01" <% if (country.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (country.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (country.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="Country_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(country.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
				<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
				<td id="dateExpires">
					<input type=text name="Country_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(country.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
					<a href="javascript: show_calendar('Country_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
				</td>
			</tr>
			</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('Country_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="Country_owner" tabindex=51 size=30 maxlength=15 value="<%=country.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newCountry) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'CountryUpdate;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'CountryAdd'); void(0);">
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
	frm.browseName.value = "country-admin";
	setNavCookie("/admin/systemtables/country.jsp", "CountryRetrieveById", "Country <%=country.getCountryCode()%>");

	var status = "<%=country.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newCountry) { %>
			frm.Country_countryName.focus();
<%	} else { %>
			frm.Country_countryCode.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.Country_status[frm.Country_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

// End Hide script -->
</SCRIPT>