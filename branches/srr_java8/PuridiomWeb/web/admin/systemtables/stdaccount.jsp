<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	List accountList = (List) request.getAttribute("accountList");
	Account stdAccount = null;
	boolean newAccount = false;

	if (accountList == null)
	{
		accountList = new ArrayList();
	}
	if (accountList.size() <= 0)
	{
		stdAccount = new Account();
		AccountPK comp_id = new AccountPK();
		comp_id.setSequence(new BigDecimal(1));
		stdAccount.setComp_id(comp_id);
		stdAccount.setDateEntered(d_today);
		stdAccount.setDateExpires(d_today);
		stdAccount.setOwner(uid);
		stdAccount.setStatus("02");
		newAccount = true;
	}
	else
	{
		stdAccount = (Account) accountList.get(0);
	}
	AccountPK stdAccountPK = stdAccount.getComp_id();
	UserProfile owner = UserManager.getInstance().getUser(oid, stdAccount.getOwner());
%>

<tsa:hidden name="Account_icHeader" value="<%=stdAccountPK.getIcHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="newAccount" value="<%=newAccount%>"/>
<tsa:hidden name="as_rows" value="<%=accountList.size()%>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Account Allocation</div>
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

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 align=center>
		<tr>
			<td>Account Title:&nbsp;</td>
			<td><input type=text name="Account_accountTitle" value="<%=stdAccount.getAccountTitle()%>" size=50 maxlength=45 onchange="alphaNumericCheck(this);"></td>
			<td width=25px>&nbsp;</td>
			<td nowrap align=right>
<%	if (! newAccount) { %>
				<a href="javascript: if (verifyAction('Delete this Account?')) { setDeleteAll(); doSubmit('/browse/browse_sys_tables.jsp', 'StdAccountUpdate;BrowseRetrieve'); void(0); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Account</a>
<%	} %>
			</td>
		</tr>
		</table>

		<br>
		<br>

		<table border=0 cellpadding=0 cellspacing=0 align=center>
		<tr>
			<td>
				<table id="accounts" border=0 cellpadding=2 cellspacing=2 align=center>
				<tr>
					<td>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "AC01")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld1', 'AC01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC01", "ACC UDF1")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC02")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld2', 'AC02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC02", "ACC UDF2")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC03")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld3', 'AC03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC03", "ACC UDF3")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC04")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld4', 'AC04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC04", "ACC UDF4")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC05")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld5', 'AC05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC05", "ACC UDF5")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC06")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld6', 'AC06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC06", "ACC UDF6")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC07")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld7', 'AC07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC07", "ACC UDF7")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC08")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld8', 'AC08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC08", "ACC UDF8")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC09")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld9', 'AC09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC09", "ACC UDF9")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC10")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld10', 'AC10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC10", "ACC UDF10")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC11")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld11', 'AC11'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC11", "ACC UDF11")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC12")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld12', 'AC12'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC12", "ACC UDF12")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC13")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld13', 'AC13'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC13", "ACC UDF13")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC14")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld14', 'AC14'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC14", "ACC UDF14")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "AC15")%> nowrap align=center><a href="javascript: browseAccountFld('Account_fld15', 'AC15'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "AC15", "ACC UDF15")%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "allocationPercent")%> nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "allocationPercent", "Percent")%></td>
					<td>&nbsp;</td>
				</tr>
<%
	String s_set_row = "";
	int i_rowcount = 0;

	for (int i = 0; i < accountList.size(); i++)
	{
		s_set_row = "ONFOCUS='setCurrentRow(" + i_rowcount + ");'";
		stdAccount = (Account) accountList.get(i);
		AccountPK accountPk = (AccountPK) stdAccount.getComp_id();
		String	s_alloc_title = stdAccount.getAccountTitle();
%>
				<tr>
					<td td=acc_num_<%=i%> align=right><%=i+1%>
						<tsa:hidden name="Account_sequence" value="<%=accountPk.getSequence()%>"/>
						<tsa:hidden name="Account_accountType" value="STD"/>
						<tsa:hidden name="Account_allocMethod" value="P"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "AC01")%> id=acc01><input type=text name="Account_fld1" value="<%=stdAccount.getFld1()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC02")%> id=acc02><input type=text name="Account_fld2" value="<%=stdAccount.getFld2()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC03")%> id=acc03><input type=text name="Account_fld3" value="<%=stdAccount.getFld3()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC04")%> id=acc04><input type=text name="Account_fld4" value="<%=stdAccount.getFld4()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC05")%> id=acc05><input type=text name="Account_fld5" value="<%=stdAccount.getFld5()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC06")%> id=acc06><input type=text name="Account_fld6" value="<%=stdAccount.getFld6()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC07")%> id=acc07><input type=text name="Account_fld7" value="<%=stdAccount.getFld7()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC08")%> id=acc08><input type=text name="Account_fld8" value="<%=stdAccount.getFld8()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC09")%> id=acc09><input type=text name="Account_fld9" value="<%=stdAccount.getFld9()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC10")%> id=acc10><input type=text name="Account_fld10" value="<%=stdAccount.getFld10()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC11")%> id=acc11><input type=text name="Account_fld11" value="<%=stdAccount.getFld11()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC12")%> id=acc12><input type=text name="Account_fld12" value="<%=stdAccount.getFld12()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC13")%> id=acc13><input type=text name="Account_fld13" value="<%=stdAccount.getFld13()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC14")%> id=acc14><input type=text name="Account_fld14" value="<%=stdAccount.getFld14()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>
					<td <%=HtmlWriter.isVisible(oid, "AC15")%> id=acc15><input type=text name="Account_fld15" value="<%=stdAccount.getFld15()%>" size=15 maxlength=50 onchange="upperCase(this);" <%=s_set_row%>></td>

					<td <%=HtmlWriter.isVisible(oid, "allocationPercent")%> id=percent align=right><input type=text id="allocPercent_<%=i%>" name="Account_allocPercent" value="<%=Utility.getBigDecimalFormatted(stdAccount.getAllocPercent(), 2)%>" size=10 maxlength=25 onchange="addUp(<%=i%>);"></td>
					<td id=acc_del_<%=i%>><a href="javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(<%=i%>); } void(0);" tabindex="999"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
				</tr>
<%		i_rowcount++;
		}	//end for loop
%>
				</table>

				<br>

				<table border=0 cellpadding=2 cellspacing=2 align=center width=100%>
				<tr>
					<td align=left width=45%><a href="javascript: addNew(); void(0);"><font class="reset"><b>Add New</b></font></a></td>
					<td align=right width=45%><a href="javascript: deleteAll(); void(0);"><font class="reset"><b>Delete All</b></font></a></td>
					<td align=right><input type=text name="as_perc_tot" value="" size=10 maxlength=10 onfocus="this.blur();" disabled></td>
					<td>&nbsp;&nbsp;</td>
				</tr>
				</table>

				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
			</td>
		</tr>
		</table>

		<br>
		<hr width=475px align=center color="#9999CC">
		<br>

		<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="Account_status" onchange="setStatus();">
							<option value="01" <% if (stdAccount.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<option value="02" <% if (stdAccount.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<option value="03" <% if (stdAccount.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="Account_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdAccount.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr>
					<td id="dateExpires" align=right>Date Expires:&nbsp;</td>
					<td id="dateExpires">
						<input type=text name="Account_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(stdAccount.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('Account_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('Account_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="Account_owner" tabindex=51 size=30 maxlength=15 value="<%=stdAccount.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'StdAccountUpdate;BrowseRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('stdaccount-admin'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "stdaccount-admin";
	setNavCookie("/admin/systemtables/stdaccount.jsp", "AccountRetrieveByHeader", "Account <%=stdAccount.getAccountTitle()%>");

	var myTable = document.getElementById("accounts");
	var TotalRows = myTable.rows.length - 1;
	var maxRows = frm.as_maxrows.value;
	var accRows = frm.as_rows.value;
	var tableType = "AC";

	var accountFld1Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld1", "systable")%>";
	var accountFld2Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld2", "systable")%>";
	var accountFld3Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld3", "systable")%>";
	var accountFld4Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld4", "systable")%>";
	var accountFld5Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld5", "systable")%>";
	var accountFld6Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld6", "systable")%>";
	var accountFld7Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld7", "systable")%>";
	var accountFld8Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld8", "systable")%>";
	var accountFld9Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld9", "systable")%>";
	var accountFld10Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld10", "systable")%>";
	var accountFld11Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld11", "systable")%>";
	var accountFld12Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld12", "systable")%>";
	var accountFld13Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld13", "systable")%>";
	var accountFld14Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld14", "systable")%>";
	var accountFld15Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld15", "systable")%>";

	if (TotalRows == 0)
	{
		addNew();
	}
	setCurrentRow(0);
	total();

	var status = "<%=stdAccount.getStatus()%>";
	setStatus();

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function setStatus()
	{
		status = frm.Account_status[frm.Account_status.selectedIndex].value;
		if ((status == "01") || (status == "03"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function addNew()
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("accounts");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;
		myRow = myTable.insertRow(count);
		maxRows++;

		myCell = myRow.insertCell();
		id = "acc_num_" + (count - 1);
		myCell.id = id;
		myCell.align = "RIGHT";
		myCell.innerHTML = count + "<INPUT TYPE=\"HIDDEN\" NAME=\"Account_sequence\" value=" + count + "><INPUT TYPE=\"HIDDEN\" NAME=\"Account_accountType\" value=\"STD\"><INPUT TYPE=\"HIDDEN\" NAME=\"Account_allocMethod\" value=\"P\">";

		myCell = myRow.insertCell();
		id = "acc_1";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld1\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC01")%>
		<%=HtmlWriter.cellDisplay(oid, "AC01")%>

		myCell = myRow.insertCell();
		id = "acc_2";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld2\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC02")%>
		<%=HtmlWriter.cellDisplay(oid, "AC02")%>

		myCell = myRow.insertCell();
		id = "acc_3";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld3\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC03")%>
		<%=HtmlWriter.cellDisplay(oid, "AC03")%>

		myCell = myRow.insertCell();
		id = "acc_4";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld4\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC04")%>
		<%=HtmlWriter.cellDisplay(oid, "AC04")%>

		myCell = myRow.insertCell();
		id = "acc_5";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld5\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC05")%>
		<%=HtmlWriter.cellDisplay(oid, "AC05")%>

		myCell = myRow.insertCell();
		id = "acc_6";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld6\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC06")%>
		<%=HtmlWriter.cellDisplay(oid, "AC06")%>

		myCell = myRow.insertCell();
		id = "acc_7";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld7\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC07")%>
		<%=HtmlWriter.cellDisplay(oid, "AC07")%>

		myCell = myRow.insertCell();
		id = "acc_8";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld8\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC08")%>
		<%=HtmlWriter.cellDisplay(oid, "AC08")%>

		myCell = myRow.insertCell();
		id = "acc_9";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld9\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC09")%>
		<%=HtmlWriter.cellDisplay(oid, "AC09")%>

		myCell = myRow.insertCell();
		id = "acc_10";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld10\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC10")%>
		<%=HtmlWriter.cellDisplay(oid, "AC10")%>

		myCell = myRow.insertCell();
		id = "acc_11";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld11\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC11")%>
		<%=HtmlWriter.cellDisplay(oid, "AC11")%>

		myCell = myRow.insertCell();
		id = "acc_12";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld12\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC12")%>
		<%=HtmlWriter.cellDisplay(oid, "AC12")%>

		myCell = myRow.insertCell();
		id = "acc_13";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld13\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC13")%>
		<%=HtmlWriter.cellDisplay(oid, "AC13")%>

		myCell = myRow.insertCell();
		id = "acc_14";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld14\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC14")%>
		<%=HtmlWriter.cellDisplay(oid, "AC14")%>

		myCell = myRow.insertCell();
		id = "acc_15";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_fld15\" SIZE=\"15\" MAXLENGTH=\"50\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");'>";
		<%=HtmlWriter.cellVisibility(oid, "AC15")%>
		<%=HtmlWriter.cellDisplay(oid, "AC15")%>

		myCell = myRow.insertCell();
		myCell.align = "right";
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"Account_allocPercent\" ID=allocPercent_" + (count - 1) + " SIZE=\"10\" MAXLENGTH=\"25\" value=\"\" ONCHANGE=\"addUp(" + (count - 1) + ");\">";
		<%=HtmlWriter.cellVisibility(oid, "allocationPercent")%>
		<%=HtmlWriter.cellDisplay(oid, "allocationPercent")%>

		myCell = myRow.insertCell();
		id = "acc_del_" + (count - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Account?')) { deleteMe(" + (count - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

		distributeRemaining((count - 1));

		setCurrentRow(count - 1);
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function total ()
	{
		var p = 0;
		var e = 0;

		accRows = myTable.rows.length - 1;

		if (accRows > 1)
		{
			for ( var i = 0; i < accRows; i++)
			{
				e = frm.Account_allocPercent[i].value;
				if ( e.length > 0 )
				{
					p = p + eval(nfilter(frm.Account_allocPercent[i]));
				}
			}
		}
		else
		{
			e = frm.Account_allocPercent.value;
			if ( e.length > 0 )
			{
				p = p + eval(nfilter(frm.Account_allocPercent));
			}
		}

		frm.as_perc_tot.value = nformat(p,2);
	}

	function addUp (ix)
    {
    	if (maxRows == 1)
    	{
    		var p = eval(nfilter(frm.Account_allocPercent));
			frm.Account_allocPercent.value = nformat(eval(nfilter(frm.Account_allocPercent)), 2);
    	}
		else
		{
			var p = eval(nfilter(frm.Account_allocPercent[ix]));
			frm.Account_allocPercent[ix].value = nformat(eval(nfilter(frm.Account_allocPercent[ix])), 2);
		}
		total();
	}

	function distributeRemaining(count)
	{
		if (maxRows > 0)
		{
			var percentTotal = frm.as_perc_tot.value;
			var percentLeft = nformat(100.00 - percentTotal, 2);
			if (percentLeft < 0)
			{
				percentLeft = "0.00";
			}

			myCell = document.getElementById("allocPercent_" + count);
			myCell.value = percentLeft;
			frm.as_perc_tot.value = "100.00";
		}
	}

	function deleteMe(row)
	{
		maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count
		accRows = accRows = 1;

		myTable = document.getElementById("accounts");
		var currentRows = myTable.rows.length - 1;

		if (currentRows == 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var fld1 = frm.Account_fld1[i + 1].value;
				var fld2 = frm.Account_fld2[i + 1].value;
				var fld3 = frm.Account_fld3[i + 1].value;
				var fld4 = frm.Account_fld4[i + 1].value;
				var fld5 = frm.Account_fld5[i + 1].value;
				var fld6 = frm.Account_fld6[i + 1].value;
				var fld7 = frm.Account_fld7[i + 1].value;
				var fld8 = frm.Account_fld8[i + 1].value;
				var fld9 = frm.Account_fld9[i + 1].value;
				var fld10 = frm.Account_fld10[i + 1].value;
				var fld11 = frm.Account_fld11[i + 1].value;
				var fld12 = frm.Account_fld12[i + 1].value;
				var fld13 = frm.Account_fld13[i + 1].value;
				var fld14 = frm.Account_fld14[i + 1].value;
				var fld15 = frm.Account_fld15[i + 1].value;
				var perc = frm.Account_allocPercent[i + 1].value;

				frm.Account_fld1[i].value = fld1;
				frm.Account_fld2[i].value = fld2;
				frm.Account_fld3[i].value = fld3;
				frm.Account_fld4[i].value = fld4;
				frm.Account_fld5[i].value = fld5;
				frm.Account_fld6[i].value = fld6;
				frm.Account_fld7[i].value = fld7;
				frm.Account_fld8[i].value = fld8;
				frm.Account_fld9[i].value = fld9;
				frm.Account_fld10[i].value = fld10;
				frm.Account_fld11[i].value = fld11;
				frm.Account_fld12[i].value = fld12;
				frm.Account_fld13[i].value = fld13;
				frm.Account_fld14[i].value = fld14;
				frm.Account_fld15[i].value = fld15;
				frm.Account_allocPercent[i].value = perc;
			}
		}

		myTable = document.getElementById("accounts");
		myTable.deleteRow(currentRows);
/*
		if (currentRows <= 1)
		{
			frm.deleteall.value = "TRUE";
		}
*/
		//to fix totals percent
		var totalPercent = 0;

		currentRows = currentRows - 1;

			if (currentRows > 1)
			{
				for (r = 0; r < currentRows; r++)
				{
					totalPercent = totalPercent + eval(nfilter(frm.Account_allocPercent[r]));
				}
			}
			else if (currentRows == 1)
			{

				if (frm.Account_allocPercent[0])
				{
					totalPercent = eval(nfilter(frm.Account_allocPercent[0]));
				}
				else
				{
					var perc = document.all("Account_allocPercent");
					totalPercent = eval(nfilter(perc));
				}
			}
			frm.as_perc_tot.value = nformat(totalPercent,2);

	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Accounts?"))
		{
			//frm.deleteall.value = "TRUE";
			myTable = document.getElementById("accounts");
			count = myTable.rows.length - 1;

			maxRows = 0;

			for(i = 0; i < count; i++)
			{
				myTable.deleteRow(1);
			}

			addNew();
			frm.Account_allocPercent.value = "100.00";
		}
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("StdAccountUpdate") >= 0) {
			var p = eval(nfilter(frm.as_perc_tot));
			var msg = "";
			if ( p != 100 )
			{
				msg = 'Percentage (' + frm.as_perc_tot.value + ') must equal 100%!'
			}
			if (isEmpty(frm.Account_accountTitle.value))
			{
				if (!isEmpty(msg))
				{
					msg = msg + "\n";
				}
				msg = msg + "You must enter a title for this account.";
			}
			if (!isEmpty(msg))
			{
				alert(msg);
				return false;
			}
		}
		return true;
	}

	function setDeleteAll()
	{
		frm.deleteall.value = "TRUE";
	}

// End Hide script -->
</SCRIPT>