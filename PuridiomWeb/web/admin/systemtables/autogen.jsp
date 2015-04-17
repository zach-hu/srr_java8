<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.AutoGenType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	AutoGen autoGen = (AutoGen) request.getAttribute("autoGen");
	boolean newAutoGen = false;

	if (autoGen == null)
	{
		autoGen = new AutoGen();
		AutoGenPK comp_id = new AutoGenPK();
		comp_id.setGenYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		autoGen.setComp_id(comp_id);
		autoGen.setDateEntered(d_today);
		autoGen.setDateExpires(d_today);
		autoGen.setOwner(uid);
		autoGen.setStatus("02");
		newAutoGen = true;
	}
	AutoGenPK autoGenPK = autoGen.getComp_id();
	UserProfile owner = UserManager.getInstance().getUser(oid, autoGen.getOwner());
	String duplicateAutoGenErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateAutoGenErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateAutoGenFailurePage" value="/admin/systemtables/autogen.jsp"/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>AutoGen</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateAutoGenErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateAutoGenErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=100px align=right nowrap height=18px>Counter Type:&nbsp;</td>
			<td>
<% 	if (! newAutoGen) { %>
				<tsa:hidden name="AutoGen_documentType" value="<%=autoGenPK.getDocumentType()%>"/>
				<tsa:hidden name="AutoGen_rangeMax" value="<%=autoGen.getRangeMax()%>"/>
				<input type=text name="documentType" value="<%=AutoGenType.toString(autoGenPK.getDocumentType(), oid)%>" size=30 disabled>
<%	} else {%>
<select name="AutoGen_documentType" onchange="setDocType();">
					<%
							Map oTypes = DictionaryManager.getInstance("autogen-type", oid).getPropertyMap();
							Iterator it = oTypes.entrySet().iterator() ;
							while (it.hasNext()) {
								Map.Entry e = (Map.Entry) it.next() ;
								String ikey = (String) e.getKey() ;
								String itxt = (String) e.getValue() ;
								%>
					<option value="<%=ikey%>"> <%=itxt%></option>

								<%
							}
					%>
				</select>
<% 	} %>
			</td>
			<td nowrap align=right>
<%	if (! newAutoGen) { %>
				<a href="javascript: if (verifyAction('Delete this fiscal year?')) { doSubmit('/browse/browse_sys_tables.jsp', 'AutoGenDelete;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Fiscal Year</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:&nbsp;</td>
			<td>
			<%	if (! newAutoGen) { %>
				<input type="text" name="AutoGen_genYear" value="<%=autoGenPK.getGenYear()%>" size=20 maxlength=4 disabled>
				<%	} else {%>
				<input type="text" name="AutoGen_genYear" value="<%=autoGenPK.getGenYear()%>" size=20 maxlength=4>
				<%	} %>
			</td>
			<td width=100%>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td>Active:</td>
					<td>
						<input type=checkbox name="cbox" value="" <% if (autoGen.getActiveYear().equals("Y")) {%>CHECKED<%}%> ONCLICK="setCheckbox(frm.AutoGen_activeYear)">
						<tsa:hidden name="AutoGen_activeYear" value="<%=autoGen.getActiveYear()%>"/>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap>Counter:&nbsp;</td>
			<td colspan=2><input type="text" name="AutoGen_nextNumber" value="<%=autoGen.getNextNumber()%>" size=20></td>
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
						<select name="AutoGen_status" onchange="setStatus();">
							<option value="01" <% if (autoGen.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (autoGen.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (autoGen.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="AutoGen_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(autoGen.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="AutoGen_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(autoGen.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('AutoGen_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('AutoGen_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="AutoGen_owner" tabindex=51 size=30 maxlength=15 value="<%=autoGen.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newAutoGen) { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'AutoGenUpdate;BrowseRetrieve'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript:  doSubmit('/browse/browse_sys_tables.jsp', 'AutoGenAdd'); void(0);">Save</a></div>
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
	frm.browseName.value = "fiscalyear";

	var status = "<%=autoGen.getStatus()%>";
	setStatus();

	function setFieldFocus()
	{
<%	if (! newAutoGen) { %>
			frm.AutoGen_nextNumber.focus();
<%	} else { %>
			frm.AutoGen_documentType.focus();
<%	} %>
	}

	function setStatus()
	{
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.AutoGen_status[frm.AutoGen_status.selectedIndex].value;

		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function setDocType()
	{
		frm.AutoGen_documentType.value = frm.AutoGen_documentType[frm.AutoGen_documentType.selectedIndex].value;
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
// End Hide script -->
</SCRIPT>