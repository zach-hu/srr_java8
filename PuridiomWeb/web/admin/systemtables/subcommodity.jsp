<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	SubCommodity commodity = (SubCommodity) request.getAttribute("subCommodity");
	boolean newCommodity = false;

	if (commodity == null)
	{
		commodity = new SubCommodity();
		commodity.setDateEntered(d_today);
		commodity.setDateExpires(d_today);
		commodity.setOwner(uid);
		commodity.setStatus("02");
		newCommodity = true;
	}
	else
	{
	    String newCommodityString = HiltonUtility.ckNull((String) request.getAttribute("newCommodity"));
		if (newCommodityString.equals("Y"))
		{
	      newCommodity = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, commodity.getOwner());

	String duplicateCommodityErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateCommodityErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="duplicateCommodityFailurePage" value="/admin/systemtables/subcommodity.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Commodity Code</div>
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

<%	if (!HiltonUtility.isEmpty(duplicateCommodityErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateCommodityErrorMsg%></td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=100px align=right nowrap height=18px>Code:&nbsp;</td>
			<td><input type="text" name="SubCommodity_commodity" value="<%=commodity.getCommodity()%>" size=25 maxlength=15 <% if (! newCommodity) { %> disabled <% } %> onChange="upperCase(this);"></td>
			<td nowrap align=right>
<%	if (! newCommodity) { %>
				<a href="javascript: if (verifyAction('Delete this commodity code?')) { browseSubCommodityAdmin('SubCommodityDelete'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Commodity</a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=100px align=right nowrap>Description:&nbsp;</td>
			<td colspan=2><input type="text" name="SubCommodity_description" value="<%=commodity.getDescription()%>" size=70 maxlength=255></td>
		</tr>
		<tr>
			<td colspan=3 align=center>
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
						<select name="SubCommodity_status" onchange="setStatus();">
							<option value="01" <% if (commodity.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (commodity.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (commodity.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="SubCommodity_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(commodity.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="SubCommodity_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(commodity.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('SubCommodity_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('SubCommodity_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="SubCommodity_owner" tabindex=51 size=30 maxlength=15 value="<%=commodity.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
<%	if (! newCommodity) { %>
		<a href="javascript: browseSubCommodityAdmin('SubCommodityUpdate'); void(0);">
<%	} else { %>
		<a href="javascript: browseSubCommodityAdmin('SubCommodityAdd'); void(0);">
<%	} %>
		<img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a>
	</td>
	<td width=50% align=center><a href="javascript: browseSubCommodityAdmin(''); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "subcommodity-admin";
	setNavCookie("/admin/systemtables/subcommodity.jsp", "SubCommodityRetrieveById", "Commodity Code <%=commodity.getCommodity()%>");

<%	if (! newCommodity) { %>
			frm.SubCommodity_description.focus();
<%	} else { %>
			frm.SubCommodity_commodity.focus();
<%	} %>

	var status = "<%=commodity.getStatus()%>";
	setStatus();

	function setStatus() {
		//take notice of var status and how setStatusFields is called at beginning;
		status = frm.SubCommodity_status[frm.SubCommodity_status.selectedIndex].value;

		if ((status == "01") || (status == "03")) {
			displayArea("dateExpires");
		}
		else {
			hideArea("dateExpires");
		}
	}

	function browseSubCommodityAdmin(updateHandler) {
		var commodityType = "<%=HiltonUtility.ckNull(PropertiesManager.getInstance(oid).getProperty("MISC", "SubCommodityType", ""))%>";

		if (!isEmpty(updateHandler)) {
			updateHandler = updateHandler + ";";
		}

		if (!isEmpty(commodityType)) {
			doSubmit('browse/browse_subcommodity_tree_admin.jsp', updateHandler + 'SubCommodityRetrieveTree');
		} else {
			doSubmit('browse/browse_sys_tables.jsp', updateHandler + 'BrowseRetrieve')
		}
	}

// End Hide script -->
</SCRIPT>