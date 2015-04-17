<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String s_dollar_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "DollarDecimals", "2");

	Department department = (Department) request.getAttribute("department");
	boolean newDepartment = false;

	if (department == null)
	{
		department = new Department();
		department.setDateEntered(d_today);
		department.setDateExpires(d_today);
		department.setOwner(uid);
		department.setStatus("02");
		newDepartment = true;
	}
	else
	{
	    String newDepartmentString = HiltonUtility.ckNull((String) request.getAttribute("newDepartment"));
		if (newDepartmentString.equals("Y"))
		{
	      newDepartment = true;
	    }
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, department.getOwner());
	UserProfile manager = UserManager.getInstance().getUser(oid, department.getDeptManager());

	String duplicateDepartmentErrorMsg = HiltonUtility.ckNull((String)request.getAttribute("duplicateDepartmentErrorMsg"));
%>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="deleteall" value=""/>
<tsa:hidden name="DepartmentBuyer_departmentCode" value="<%=department.getDepartmentCode()%>"/>
<tsa:hidden name="duplicateDepartmentFailurePage" value="/admin/systemtables/department.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<%	if (! newDepartment && role.getAccessRights("DEPT") == 99) { %>
		<tr>
			<td align=right valign=middle>
				<a href="javascript: if (verifyAction('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dept-confirmDelete", "Delete this department?")%>')) { doSubmit('browse/browse_sys_tables.jsp', 'DepartmentDelete;BrowseRetrieve'); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%></a>
			</td>
		</tr>
<%	} %>
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

<%	if (!HiltonUtility.isEmpty(duplicateDepartmentErrorMsg)) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
  <td width=100% align=center class=error><%=duplicateDepartmentErrorMsg%></td>
</tr>
</table>
<br>
<%	} %>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center>
		<table border=0 cellpadding=1 cellspacing=0 align=center>
		<tr>
			<td width=130px align=right nowrap height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "departmentCode", "Department Code")%>:&nbsp;</td>
			<td><input type="text" name="Department_departmentCode" value="<%=department.getDepartmentCode()%>" size=25 maxlength=15 <%	if (! newDepartment) { %> disabled <% } %> onChange="alphaNumericCheck(this); setDeptBuyer();"></td>
		</tr>
		<tr>
			<td width=130px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptartmentName", "Department Name")%>:&nbsp;</td>
			<td colspan=2><input type="text" name="Department_departmentName" value="<%=department.getDepartmentName()%>" size=30 maxlength=60></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "dept-division")%>>
			<td <%=HtmlWriter.isVisible(oid, "dept-division")%> width=130px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dept-division", "Parent Departmet")%>:&nbsp;</td>
			<td <%=HtmlWriter.isVisible(oid, "dept-division")%>><input type="text" name="Department_division" value="<%=department.getDivision()%>" size=30 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
			<td>
				<select name="Department_status" onchange="setStatus();">
					<option value="01" <% if (department.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
					<option value="02" <% if (department.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
					<option value="03" <% if (department.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateEntered", "Date Entered")%>:&nbsp;</td>
			<td>
				<input type=text name="Department_dateEntered" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(department.getDateEntered(), oid, userDateFormat)%>" disabled>
			</td>
		</tr>
		<tr>
			<td id="dateExpires" align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExpires", "Date Expires")%>:&nbsp;</td>
			<td id="dateExpires">
				<input type=text name="Department_dateExpires" tabindex=51 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(department.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
				<a href="javascript: show_calendar('Department_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
			</td>
		</tr>
		<tr>
			<td align=right><a href="javascript: browseLookup('Department_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
			<td>
				<input type=text name="Department_owner" tabindex=51 size=30 maxlength=15 value="<%=department.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
			</td>
		</tr>
		<tr>
			<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ownerName", "Owner Name")%>:&nbsp;</td>
			<td>
				<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
			</td>
		</tr>
		</table>

		<br>
	</td>
	<td width=50% align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100% align=center>
		<tr>
			<td align=center width=100%>
				<table border=1 cellspacing=0 cellpadding=0 width=400px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvers", "Approvers")%></b></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=2 width=400px align=center>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=2 width=390px align=center>
								<tr>
									<td nowrap width=125px><a href="javascript: browseLookup('Department_deptManager', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptmanager", "Department Manager")%></a></td>
									<td nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dept-managerName", "Manager Name")%></td>
									<td nowrap width=115px align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptassignamount", "Up to Amount")%></td>
								</tr>
								</table>
								<table id=deptapprovers border=0 cellspacing=0 cellpadding=2 width=390px align=center>
								<tr>
									<td class=browseRow width=125px <%=HtmlWriter.isVisible(oid, "deptmanager")%>><input type="text" name="Department_deptManager" value="<%=department.getDeptManager()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);"></td>
									<td class=browseRow width=150px <%=HtmlWriter.isVisible(oid, "dept-managerName")%>><input type="text" name="as_managerName" value="<%=manager.getDisplayName()%>" size=30 disabled></td>
									<td class=browseRow width=115px><input type=text name="Department_managerAmount" value="<%=HiltonUtility.getFormattedDollar(department.getManagerAmount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);"></td>
								</tr>
								</table>
								<br>
								<table border=0 cellpadding=0 cellspacing=2 width=390px align=center>
								<tr>
									<td nowrap width=125px><a href="javascript: browseApprovers(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approver", "Approver")%></a></td>
									<td nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approverName", "Approver Name")%></td>
									<td nowrap width=115px align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptassignamount", "Up to Amount")%></td>
								</tr>
								</table>

								<table id=deptapprovers border=0 cellspacing=0 cellpadding=2 width=390px align=center>
								<tr>
									<td class=browseRow width=125px <%=HtmlWriter.isVisible(oid, "deptapprv1")%>><input type="text" name="Department_deptApprv1" value="<%=department.getDeptApprv1()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);" onfocus="setCurrentAppRow(1);"></td>
									<td class=browseRow width=150px <%=HtmlWriter.isVisible(oid, "deptapprv1")%>><input type="text" name="as_deptApprv1Name" value="<%=UserManager.getInstance().getUserDisplayName(oid, department.getDeptApprv1())%>" size=30 disabled></td>
									<td class=browseRow width=115px <%=HtmlWriter.isVisible(oid, "deptapprv1")%>><input type=text name="Department_apprv1Amount" value="<%=HiltonUtility.getFormattedDollar(department.getApprv1Amount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);" onfocus="setCurrentAppRow(1);"></td>
								</tr>
								<tr>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv2")%>><input type="text" name="Department_deptApprv2" value="<%=department.getDeptApprv2()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);" onfocus="setCurrentAppRow(2);"></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv2")%>><input type="text" name="as_deptApprv2Name" value="<%=UserManager.getInstance().getUserDisplayName(oid, department.getDeptApprv2())%>" size=30 disabled></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv2")%>><input type=text name="Department_apprv2Amount" value="<%=HiltonUtility.getFormattedDollar(department.getApprv2Amount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);" onfocus="setCurrentAppRow(2);"></td>
								</tr>
								<tr>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv3")%>><input type="text" name="Department_deptApprv3" value="<%=department.getDeptApprv3()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);" onfocus="setCurrentAppRow(3);"></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv3")%>><input type="text" name="as_deptApprv3Name" value="<%=UserManager.getInstance().getUserDisplayName(oid, department.getDeptApprv3())%>" size=30 disabled></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv3")%>><input type=text name="Department_apprv3Amount" value="<%=HiltonUtility.getFormattedDollar(department.getApprv3Amount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);" onfocus="setCurrentAppRow(3);"></td>
								</tr>
								<tr>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv4")%>><input type="text" name="Department_deptApprv4" value="<%=department.getDeptApprv4()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);" onfocus="setCurrentAppRow(4);"></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv4")%>><input type="text" name="as_deptApprv4Name" value="<%=UserManager.getInstance().getUserDisplayName(oid, department.getDeptApprv4())%>" size=30 disabled></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv4")%>><input type=text name="Department_apprv4Amount" value="<%=HiltonUtility.getFormattedDollar(department.getApprv4Amount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);" onfocus="setCurrentAppRow(4);"></td>
								</tr>
								<tr>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv5")%>><input type="text" name="Department_deptApprv5" value="<%=department.getDeptApprv5()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('user', this);" onfocus="setCurrentAppRow(5);"></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv5")%>><input type="text" name="as_deptApprv5Name" value="<%=UserManager.getInstance().getUserDisplayName(oid, department.getDeptApprv5())%>" size=30 disabled></td>
									<td class=browseRow <%=HtmlWriter.isVisible(oid, "deptapprv5")%>><input type=text name="Department_apprv5Amount" value="<%=HiltonUtility.getFormattedDollar(department.getApprv5Amount(), oid)%>" size=15 style="text-align:right" onchange="formatMeApp(this);" onfocus="setCurrentAppRow(5);"></td>
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
				<table border=1 cellspacing=0 cellpadding=0 width=400px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyers", "Buyers")%></b></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=2 width=400px align=center>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=2 width=390px align=center>
								<tr>
									<td nowrap width=125px>&nbsp;<a href="javascript: browseSchedule('DepartmentBuyer_userId', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyer", "Buyer")%></a></td>
									<td nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerName", "Buyer Name")%></td>
									<td nowrap width=100px align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptassignamount", "Up to Amount")%></td>
									<td width=15px>&nbsp;</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table id=deptbuyers border=0 cellspacing=0 cellpadding=2 width=390px align=center>
<%
		List deptBuyerList = (List) request.getAttribute("departmentBuyerList");
		if (deptBuyerList != null)
		{
			for (int i = 0; i < deptBuyerList.size(); i++)
			{
				DepartmentBuyer deptBuyer = (DepartmentBuyer) deptBuyerList.get(i);
				DepartmentBuyerPK deptBuyerPK = deptBuyer.getComp_id();
				String s_deptBuyer = deptBuyerPK.getUserId();
				UserProfile buyer = UserManager.getInstance().getUser(oid, s_deptBuyer);
%>
								<tr>
									<td id=userId class=browseRow width=125px><input type=text name="DepartmentBuyer_userId" value="<%=s_deptBuyer%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('buyer', this, '<%=i%>');" onfocus="setCurrentRow(<%=i%>);"></td>
									<td id=buyerName class=browseRow width=150px><input type=text name="as_buyerName" value="<%=buyer.getDisplayName()%>" size=30 disabled></td>
									<td id=assignAmount class=browseRow width=100px><input type=text name="DepartmentBuyer_assignAmount" value="<%=HiltonUtility.getFormattedDollar(deptBuyer.getAssignAmount(), oid)%>" size=15 onchange="formatMe(<%=i%>);" onfocus="setCurrentRow(<%=i%>);"></td>
<%				if (role.getAccessRights("DEPT") == 99) {%>
									<td id=delete_<%=i%> class=browseRow width=15px><a href="javascript: if (confirmDelete(<%=i%>)) { deleteMe(<%=i%>); void(0); } "><img src="<%=contextPath%>/images/delete.gif" alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delete", "Delete")%>" border=0></a></td>
<%				}%>
								</tr>
<%		}
	} %>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<br>
								<table border=0 cellspacing=0 cellpadding=2 width=100% align=center>
								<tr>
									<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnew", "Add new")%></B></font></a></td>
									<td>&nbsp;</td>
									<td align=right nowrap><% if (role.getAccessRights("DEPT") == 99) {%><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "delall", "Delete all")%></B></font></a><%}%>&nbsp;</td>
								</tr>
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
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=50% align=center>
		<div id="pxbutton">
<%	if (! newDepartment) { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'DepartmentUpdate;DepartmentBuyerUpdateByDept;BrowseRetrieve'); void(0);">
<%	} else { %>
		<a href="javascript: doSubmit('/browse/browse_sys_tables.jsp', 'DepartmentAdd'); void(0);">
<%	} %>
		Save</a></div>
	</td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browse('department-admin'); void(0);">Cancel</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "department-admin";
	setNavCookie("/admin/systemtables/department.jsp", "DepartmentRetrieveById", "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%> <%=department.getDepartmentCode()%>");

	var currentRow = 0;
	var myTable = document.getElementById("deptbuyers");
	var count = myTable.rows.length;
	var status = "<%=department.getStatus()%>";
	var dollarDecimals = <%=s_dollar_decimals%>;
	var currentAppRow = 1;

	setStatus();

	function setFieldFocus()
	{
<%	if (!newDepartment) { %>
			frm.Department_departmentName.focus();
<%	} else { %>
			frm.Department_departmentCode.focus();
<%	} %>

		if (count <= 0)
		{
			addNew();
		}
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function setStatus()
	{
		status = frm.Department_status[frm.Department_status.selectedIndex].value;
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
		myRow = myTable.insertRow(count);

		myCell = myRow.insertCell();
		id = "userId";
		myCell.id = id;
		myCell.width = "150px";
		myCell.innerHTML = "<input type=text name=\"DepartmentBuyer_userId\" size=20 maxlength=15 value=\"\" onchange=\"upperCase(this); getNewInfo('buyer', this, " + count + ");\" onfocus='setCurrentRow(" + count + ");'>";

		myCell = myRow.insertCell();
		id = "buyerName";
		myCell.width = "150px";
		myCell.innerHTML = "<input type=text name=\"as_buyerName\" size=30 value=\"\" disabled>";

		myCell = myRow.insertCell();
		id = "assignAmount";
		myCell.id = id;
		myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"DepartmentBuyer_assignAmount\" size=15 value=\"\" onchange=\"formatMe(" + count + ");\" onfocus='setCurrentRow(" + count + ");'>";

		myCell = myRow.insertCell();
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.innerHTML = "<a href=\"javascript: if (confirmDelete(" + count + ")) { deleteMe(" + count + "); void(0); } \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		if (frm.DepartmentBuyer_userId[count])
		{
			frm.DepartmentBuyer_userId[count].focus();
			frm.DepartmentBuyer_userId[count].fireEvent("onfocus");
		}
		else
		{
			frm.DepartmentBuyer_userId.focus();
			frm.DepartmentBuyer_userId.fireEvent("onfocus");
		}

		currentRow = count;
		count++;
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function deleteMe(row)
	{
		//if (confirm("Are you sure you wish to delete this buyer from this <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%>?"))
		//{
			var currentRows = myTable.rows.length;

			if (currentRows == 0)
			{
				return;
			}
			else if (currentRows > 1)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{
					var buyer = frm.DepartmentBuyer_userId[i + 1].value;
					var buyerName = frm.as_buyerName[i + 1].value;
					var amount = frm.DepartmentBuyer_assignAmount[i + 1].value;

					frm.DepartmentBuyer_userId[i].value = buyer;
					frm.as_buyerName[i].value = buyerName;
					frm.DepartmentBuyer_assignAmount[i].value = amount;
				}
			}

			myTable = document.getElementById("deptbuyers");
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
		//}
	}

	function deleteAll()
	{
		if (confirm("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deptbuyer-confirmDelall", "Are you sure you wish to delete all buyers for this Department?")%>"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("deptbuyers");
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
		if (frm.DepartmentBuyer_assignAmount[x])
		{
			var amount = eval(nfilter(frm.DepartmentBuyer_assignAmount[x]));
			if (frm.DepartmentBuyer_assignAmount[x].value != '')
			{
				frm.DepartmentBuyer_assignAmount[x].value = nformat(amount, dollarDecimals);
			}
		}
		else
		{
			var amount = eval(nfilter(frm.DepartmentBuyer_assignAmount));
			if (frm.DepartmentBuyer_assignAmount.value != '')
			{
				frm.DepartmentBuyer_assignAmount.value = nformat(amount, dollarDecimals);
			}
		}
	}

	function formatMeApp(fld)
	{
		var amount = eval(nfilter(fld));
		if (fld.value != '')
		{
			fld.value = nformat(amount, dollarDecimals);
		}
	}

	function setDeptBuyer()
	{
		frm.DepartmentBuyer_departmentCode.value = frm.Department_departmentCode.value;
	}

	function validateForm()
	{
		frm.deleteall.value = "TRUE";
		var rowcount = frm.elements.item("DepartmentBuyer_userId");

		if (rowcount.length != undefined)
		{
			for (var i = rowcount.length; i > 0; i--)
			{
				if (frm.DepartmentBuyer_userId[i - 1].value.length <= 0)
				{
					deleteMe(i-1);
				}
			}
		}

		rowcount = frm.elements.item("DepartmentBuyer_userId");
		if (rowcount.length != undefined)
		{
			for (var i = 0; i < rowcount.length; i++)
			{
				if (frm.DepartmentBuyer_userId[i].value.length > 0)
				{
					frm.deleteall.value = "FALSE";
				}
			}
		}
		else
		{
			if (frm.DepartmentBuyer_userId.value.length > 0)
			{
				frm.deleteall.value = "FALSE";
			}
		}

		return true;
	}

	function confirmDelete(row)
	{
		var rowcount = document.all.deptbuyers.rows.length;
		if (rowcount > 1)
		{
			if (frm.DepartmentBuyer_userId[row].value.length > 0)
			{
				confirm('Are you sure you wish to delete this buyer from this <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%>?');
			}
		}
		else
		{
			if (frm.DepartmentBuyer_userId.value.length > 0)
			{
				confirm('Are you sure you wish to delete this buyer from this <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%>?');
			}
		}
		return true;
	}

	function setCurrentAppRow(row) {
		currentAppRow = row;
	}

	function browseApprovers() {
		var fldName = "Department_deptApprv" + currentAppRow;

		browseLookup(fldName, 'approver');
	}

// End Hide script -->
</SCRIPT>