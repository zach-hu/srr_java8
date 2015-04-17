<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

	InsCategoryLevel insCategoryLevel = (InsCategoryLevel) request.getAttribute("insCategoryLevel");
	boolean newInsCategoryLevel = false;

	if (insCategoryLevel == null)
	{
		insCategoryLevel = new InsCategoryLevel();
		insCategoryLevel.setDateEntered(Dates.getSqlDate(Dates.today(userDateFormat, userTimeZone), userDateFormat));
		insCategoryLevel.setDateExpires(Dates.getSqlDate(Dates.today(userDateFormat, userTimeZone), userDateFormat));
		insCategoryLevel.setOwner(uid);
		insCategoryLevel.setStatus("02");
		newInsCategoryLevel = true;
	}
	else
	{
	    String newInsCategoryLevelString = HiltonUtility.ckNull((String) request.getAttribute("newInsCategoryLevel"));
		if (newInsCategoryLevelString.equals("Y"))
		{
			newInsCategoryLevel = true;
	    }
	}
	UserProfile owner = UserManager.getInstance().getUser(oid, insCategoryLevel.getOwner());
	String duplicateInsCategoryLevelErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateInsCategoryLevelErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="InsCategoryLevel_icIcl" value="<%=insCategoryLevel.getIcIcl()%>"/>
<tsa:hidden name="duplicateInsCategoryLevelFailurePage" value="/admin/systemtables/inscategorylevel.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Insurance Category Level <%=insCategoryLevel.getIclLevel()%></div>
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

<%	if (!HiltonUtility.isEmpty(duplicateInsCategoryLevelErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
  <td width=100% align=center class=error><%=duplicateInsCategoryLevelErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellspacing=0 cellpadding=0 width=450px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=500px align=center>
		<tr <%=HtmlWriter.isVisible(oid, "sup-iclLevel")%>>
			<td width=200px align=right nowrap height=18px><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-iclLevel", "Level", true)%>:&nbsp;</b></td>
			<td><%=insCategoryLevel.getIclLevel()%></td>
			<td align=right></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-iclDescription")%>>
			<td width=200px align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-iclDescription", "Description", true)%>:&nbsp;</b></td>
			<td colspan=2><%=insCategoryLevel.getIclDescription()%></td>
		</tr>
		</table>

		<br>

		<table border=0 cellpadding=1 cellspacing=0 width=450px align=center>
		<tr>
			<td align=center width=150px>&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center width=50px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-iclrequired", "Required")%></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=right width=120px class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-icllimit", "Minimal Dollar Limit", true)%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage1")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage1", "Coverage 1", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired1().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired1); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired1" value="<%=insCategoryLevel.getIclRequired1()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum1" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum1(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage2")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage2", "Coverage 2", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired2().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired2); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired2" value="<%=insCategoryLevel.getIclRequired2()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum2" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum2(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage3")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage3", "Coverage 3", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired3().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired3); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired3" value="<%=insCategoryLevel.getIclRequired3()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum3" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum3(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage4")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage4", "Coverage 4", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired4().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired4); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired4" value="<%=insCategoryLevel.getIclRequired4()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum4" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum4(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage5")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage5", "Coverage 5", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired5().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired5); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired5" value="<%=insCategoryLevel.getIclRequired5()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum5" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum5(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "sup-coverage6")%>>
			<td align=right class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-coverage6", "Coverage 6", true)%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-waiver")%> align=center>
				<input type=checkbox name="ckbox" value="Y"<% if (insCategoryLevel.getIclRequired6().equals("Y")) {%>checked<%}%> onclick="setFlagFromCkbox(this, frm.InsCategoryLevel_iclRequired6); void(0);" disabled>
				<tsa:hidden name="InsCategoryLevel_iclRequired6" value="<%=insCategoryLevel.getIclRequired6()%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "sup-limit")%> align=center>
				<input type=text name="InsCategoryLevel_iclMinimum6" value="<%=HiltonUtility.getFormattedPrice(insCategoryLevel.getIclMinimum6(), oid)%>" style="text-align:right" onchange="addUp(this);" size=12 disabled>
			</td>
		</tr>
		</table>

		<br>

		<hr width=680px align=center class=browseHR>
		<br>
		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="InsCategoryLevel_status" onchange="setStatus();" disabled>
							<option value="01" <% if (insCategoryLevel.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (insCategoryLevel.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (insCategoryLevel.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InsCategoryLevel_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(insCategoryLevel.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr id="dateExpires">
					<td nowrap align=right>Date Expires:&nbsp;</td>
					<td>
						<input type=text name="InsCategoryLevel_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(insCategoryLevel.getDateExpires(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:&nbsp;</td>
					<td>
						<input type=text name="InsCategoryLevel_owner" tabindex=51 size=30 maxlength=15 value="<%=insCategoryLevel.getOwner()%>" disabled>
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
	<td align=center><div id="pxbutton"><a href="javascript: closeMe(); void(0);">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var status = "<%=insCategoryLevel.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newInsCategoryLevel) { %>
			frm.InsCategoryLevel_iclDescription.focus();
<%	} else { %>
			frm.InsCategoryLevel_iclLevel.focus();
<%	} %>
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function setStatus()
	{
		status = frm.InsCategoryLevel_status[frm.InsCategoryLevel_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			document.getElementById("dateExpires").style.display = "";
		}
		else
		{
			document.getElementById("dateExpires").style.display = "none";
		}
	}

	function closeMe() {
		window.top.hidePopWin();
	}

// End Hide script -->
</SCRIPT>