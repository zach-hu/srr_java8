<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String s_tableType = HiltonUtility.ckNull((String) request.getAttribute("tableType"));
	String s_tableTypeMod = null;
	String title = null;
	if (s_tableType.indexOf("RQ") >= 0)
	{
		s_tableTypeMod = "req-" + s_tableType;
		title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_tableTypeMod, "Record");
	}
	else if (s_tableType.indexOf("PO") >= 0)
	{
		s_tableTypeMod = "po-" + s_tableType;
		title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_tableTypeMod, "Record");
	}
	else
	{
		title = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, s_tableType, "Record");
	}

	StdTable stdTable = (StdTable) request.getAttribute("stdTable");
	boolean newStdTable = false;

	if (stdTable == null)
	{
		stdTable = new StdTable();
		StdTablePK comp_id = new StdTablePK();
		comp_id.setTableType(s_tableType);
		stdTable.setComp_id(comp_id);
		stdTable.setDateEntered(d_today);
		stdTable.setDateExpires(d_today);
		stdTable.setOwner(uid);
		stdTable.setStatus("02");
		newStdTable = true;
	}
	else
	{
	    String newStdTableString = HiltonUtility.ckNull((String) request.getAttribute("newStdTable"));
		if (newStdTableString.equals("Y"))
		{
	      newStdTable = true;
	    }
	}

	StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id();
	UserProfile owner = UserManager.getInstance().getUser(oid, stdTable.getOwner());
	String duplicateRecordErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateRecordErrorMsg"));
%>

<tsa:hidden name="StdTable_tableType" value="<%=s_tableType%>"/>
<tsa:hidden name="tableType" value="<%=s_tableType%>"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateRecordFailurePage" value="/admin/systemtables/stdtable.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=title%></div>
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

<%	if (!HiltonUtility.isEmpty(duplicateRecordErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateRecordErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
<%	if (!s_tableType.equalsIgnoreCase("VSBA") && !s_tableType.equalsIgnoreCase("VBOT")  && !s_tableType.equalsIgnoreCase("VDCO")) {
	String maxlength="15";

	if (s_tableType.indexOf("AC") == 0) {
		maxlength="18";
	}
	if (s_tableType.equalsIgnoreCase("DFMT")) {
		maxlength = "10";
	}
%>
		<tr>
			<td width=100px align=right nowrap height=18px>Code:&nbsp;</td>
			<td><input type="text" name="StdTable_tableKey" value="<%=stdTablePK.getTableKey()%>" size=25 maxlength=<%=maxlength%> <%	if (! newStdTable) { %> disabled <% } %> onChange="alphaNumericCheck2(this);<% if (s_tableType.equals("DFMT")) {%>correctDateFormat(this);<%}%>"></td>
			<td nowrap align=right>
<%		if (! newStdTable) { %>
				<a href="javascript: if (verifyAction('Delete this record?')) { doSubmit('/browse/browse_sys_tables.jsp', 'StdTableDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete <%=title%></a>
<%		} %>
			</td>
		</tr>
<%	} else { %>
			<tsa:hidden name="StdTable_tableKey" value="<%=stdTablePK.getTableKey()%>"/>
<%	} %>
		<tr>
			<td width=100px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="StdTable_description" value="<%=stdTable.getDescription()%>" size=70 maxlength=255></td>
		</tr>
<%	if(s_tableType.equalsIgnoreCase("PO12")){ %>
		<tr>
			<td width=100px align=right nowrap>SBI Code:&nbsp;</td>
			<td colspan=2><input type="text" name="StdTable_udf1Code" value="<%=stdTable.getUdf1Code()%>" maxlength=255></td>
		</tr>
<%	} %>
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
						<select name="StdTable_status" onchange="setStatus();">
							<option value="01" <% if (stdTable.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (stdTable.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (stdTable.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="StdTable_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdTable.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
				<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
				<td id="dateExpires">
					<input type=text name="StdTable_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdTable.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
					<a href="javascript: show_calendar('StdTable_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
				</td>
			</tr>
			</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('StdTable_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="StdTable_owner" tabindex=51 size=30 maxlength=15 value="<%=stdTable.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newStdTable) { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'StdTableUpdate;BrowseRetrieve'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'StdTableAdd'); void(0);">Save</a></div>
<%	} %>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setOriginalFilter("StdTable_id_tableType", "=", "<%=s_tableType%>");
	frm.browseName.value = "${esapi:encodeForJavaScript(browseName)}";

	setNavCookie("/admin/systemtables/stdtable.jsp", "StdTableRetrieveById", "<%=title%> <%=stdTablePK.getTableKey()%>");

	var status = "<%=stdTable.getStatus()%>";
	setStatus();

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.StdTable_status[frm.StdTable_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setFieldFocus()
	{
<%	if ((! newStdTable) || (s_tableType.equalsIgnoreCase("VSBA") || s_tableType.equalsIgnoreCase("VBOT") || s_tableType.equalsIgnoreCase("VDCO"))) { %>
			frm.StdTable_description.focus();
<%	} else { %>
			frm.StdTable_tableKey.focus();
<%	} %>
	}

	function alphaNumericCheck2(x)
	{
		x.value=x.value.toUpperCase().replace(/([^0-9A-Z- () / * _.])/g,"").trim();
	}

	function correctDateFormat(fld) {
		var dateFormat = fld.value;
		if (!isEmpty(dateFormat) && (dateFormat.length == 10 || dateFormat.length == 8)) {
			dateFormat = replaceChars(dateFormat, "Y", "y");
			dateFormat = replaceChars(dateFormat, "D", "d");
			dateFormat = replaceChars(dateFormat, "m", "M");
		}
		fld.value = dateFormat;
	}
// End Hide script -->
</SCRIPT>

