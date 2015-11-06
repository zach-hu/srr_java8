<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
	AppPool appPool = (AppPool) request.getAttribute("appPool");
	boolean newAppPool = false;

	if (appPool == null)
	{
		appPool = new AppPool();
		appPool.setPoolType("A");
		newAppPool = true;
	}
%>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteall" value="true"/>
<tsa:hidden name="AppPooluser_poolid" value="<%=appPool.getPoolid()%>"/>
<tsa:hidden name="AppPool_poolType" value="<%=appPool.getPoolType()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Approval Pool</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width="100%">
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
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td width=130px align=right nowrap height=18px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "poolId", "Pool Id")%>:&nbsp;</td>
			<td><input type="text" name="AppPool_poolid" value="<%=appPool.getPoolid()%>" size=25 maxlength=15 <%	if (! newAppPool) { %> disabled <% } %> onChange="upperCase(this); setPoolid();"></td>
			<td nowrap align=right>
<%	if (! newAppPool) { %>
				<a href="javascript: if (verifyAction('Delete this approval pool?')) { doSubmit('browse/browse.jsp', 'AppPoolDelete;AppPooluserDelete;BrowseRetrieve'); }" >
				<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deletePool", "Delete Pool")%></a>
<%	} %>
			</td>
		</tr>
		<tr>
			<td width=130px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "description", "Description")%>:&nbsp;</td>
			<td colspan=2><input type="text" name="AppPool_pooldesc" value="<%=appPool.getPooldesc()%>" size=60 maxlength=60></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td nowrap align=right>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalRequiredUsersPool", "Approval is required by all users in this pool")%>&nbsp;
			</td>
			<td nowrap>
				<input type="checkbox" name="cbox" onclick="setCheckbox(this, frm.AppPool_poolflag1);" value="Y" <% if (appPool.getPoolflag1().equals("Y")) { %> CHECKED <% } %>>
				<tsa:hidden name="AppPool_poolflag1" value="<%=appPool.getPoolflag1()%>"/>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td nowrap align=right>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "localDistributionPool", "Local Distribution Pool")%>&nbsp;
			</td>
			<td nowrap>
				<input type="checkbox" name="cbox" onclick="setCheckbox(this, frm.AppPool_poolflag2);" value="Y" <% if (appPool.getPoolflag2().equals("Y")) { %> CHECKED <% } %>>
				<input type="hidden" name="AppPool_poolflag2" value="<%=appPool.getPoolflag2()%>">
			</td>
		</tr>
		</table>

		<br>

		<br>
		<table border=0 cellpadding=0 cellspacing=0 width=450px align=center>
		<tr>
			<td align=center width=450px>
				<table border=1 cellspacing=0 cellpadding=0 width=450px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=100% height=18px class=browseHdr align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvers", "Approvers")%></b></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=2 width=450px align=center>
						<tr>
							<td>
								<table border=0 cellpadding=0 cellspacing=2 width=350px align=center>
								<tr>
									<td nowrap width=125px>&nbsp;<a href="javascript: browseSchedule('AppPooluser_userId', 'approver'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-userId", "User ID")%></a></td>
									<td nowrap width=165px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "name", "Name")%></td>
									<td nowrap width=165px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "title", "Title")%></td>
									<td nowrap width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalAmount", "Approval Amount")%></td>
									<td width=25px>&nbsp;</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<table id=approvers border=0 cellspacing=0 cellpadding=2 width=350px align=center>
<%
		List appPooluserList = (List) request.getAttribute("appPooluserList");
		if (appPooluserList != null)
		{
			for (int i = 0; i < appPooluserList.size(); i++)
			{
				AppPooluser appPooluser = (AppPooluser) appPooluserList.get(i);
				AppPooluserPK appPooluserPK = appPooluser.getComp_id();
				String s_approver = appPooluserPK.getUserId();
				UserProfile approver = UserManager.getInstance().getUser(oid, s_approver);
%>
								<tr>
									<td id=userId class=browseRow width=150px><input type=text name="AppPooluser_userId" value="<%=appPooluserPK.getUserId()%>" size=20 maxlength=15 onchange="upperCase(this); getNewInfo('approver', this, '<%=i%>');" onfocus="setCurrentRow(<%=i%>);"></td>
									<td id=approverName class=browseRow width=150px><input type=text name="as_approverName" value="<%=approver.getDisplayName()%>" size=30 disabled></td>
									<td id=title class=browseRow width=150px><input type=text name="as_title" value="<%=approver.getTitle()%>" size=30 disabled></td>
									<td id=approvalAmount class=browseRow width=125px><input type=text name="as_approvalAmount" value="<%=HiltonUtility.getFormattedDollar(approver.getApprovalAmount(), oid)%>" STYLE="text-align:right" size=15 disabled></td>
									<td id=delete_<%=i%> class=browseRow width=25px><a href="javascript: deleteMe(<%=i%>); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
								</tr>
<%		}
		} %>

								</table>
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<br>
								<table border=0 cellspacing=0 cellpadding=2 width=450px align=center>
								<tr>
									<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
									<td>&nbsp;</td>
									<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
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
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
<%	if (! newAppPool) { %>
		<div id="pxbutton"><a href="javascript: doSubmit('/browse/browse.jsp', 'AppPoolUpdate;AppPooluserUpdateByPool;BrowseRetrieve'); void(0);">Save</a></div>
<%	} else { %>
		<div id="pxbutton"><a href="javascript: finalizeAppPool(); void(0);">Save</a></div>
<%	} %>
	</td>
	<td width=50% align=center>
		<div id="pxbutton"><a href="javascript: browse('app_pool'); void(0);">Cancel</a></div>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "app_pool";
	setNavCookie("/admin/approvals/app_pool.jsp", "DoNothing", "Approval Pool <%=appPool.getPoolid()%>");

	var currentRow = 0;
	var myTable = document.getElementById("approvers");
	var count = myTable.rows.length;

	if (count <= 0)
	{
		addNew();
	}

	function addNew()
	{
		myRow = myTable.insertRow(count);

		myCell = myRow.insertCell(-1);
		id = "userId";
		myCell.id = id;
		myCell.width = "150px";
		myCell.innerHTML = "<input type=text name=\"AppPooluser_userId\" size=20 maxlength=15 value=\"\" onchange=\"upperCase(this); getNewInfo('approver', this, " + count + ");\" onfocus='setCurrentRow(" + count + ");'>";

		myCell = myRow.insertCell(-1);
		id = "approverName";
		myCell.width = "150px";
		myCell.innerHTML = "<input type=text name=\"as_approverName\" size=30 value=\"\" disabled>";

		myCell = myRow.insertCell(-1);
		id = "title";
		myCell.width = "150px";
		myCell.innerHTML = "<input type=text name=\"as_title\" size=30 value=\"\" disabled>";

		myCell = myRow.insertCell(-1);
		id = "approvalAmount";
		myCell.id = id;
		myCell.width = "125px";
		myCell.innerHTML = "<input type=text name=\"as_approvalAmount\" size=15 value=\"\" disabled>";

		myCell = myRow.insertCell(-1);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.innerHTML = "<a href=\"javascript:deleteMe(" + count + "); void(0);\"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		if (frm.AppPooluser_userId[count])
		{
			frm.AppPooluser_userId[count].focus();
			if (navigator.appName == "Microsoft Internet Explorer")
			frm.AppPooluser_userId[count].fireEvent("onfocus");
		}
		else
		{
			if (navigator.appName == "Microsoft Internet Explorer")
				frm.AppPooluser_userId.fireEvent("onfocus");
			
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
		//if (confirm("Are you sure you wish to delete this approver from this approval pool?"))
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
					var approver = frm.AppPooluser_userId[i + 1].value;
					var approverName = frm.as_approverName[i + 1].value;
					var title = frm.as_title[i + 1].value;
					var amount = frm.as_approvalAmount[i + 1].value;

					frm.AppPooluser_userId[i].value = approver;
					frm.as_approverName[i].value = approverName;
					frm.as_title[i].value = amount;
					frm.as_approvalAmount[i].value = amount;
				}
			}

			myTable = document.getElementById("approvers");
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
		if (confirm("Are you sure you wish to delete all approvers for this Approval Pool?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("approvers");
			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function setPoolid()
	{
		frm.AppPooluser_poolid.value = frm.AppPool_poolid.value;
	}

	function validateForm()
	{
		frm.deleteall.value = "TRUE";
		var rowcount = frm.elements.item("AppPooluser_userId");

		if (rowcount.length != undefined)
		{
			for (var i = rowcount.length; i > 0; i--)
			{
				if (frm.AppPooluser_userId[i - 1].value.length <= 0)
				{
					deleteMe(i-1);
				}
			}
		}

		rowcount = frm.elements.item("AppPooluser_userId");
		if (rowcount.length != undefined)
		{
			for (var i = 0; i < rowcount.length; i++)
			{
				if (frm.AppPooluser_userId[i].value.length > 0)
				{
					frm.deleteall.value = "FALSE";
				}
			}
		}
		else
		{
			if (frm.AppPooluser_userId.value.length > 0)
			{
				frm.deleteall.value = "FALSE";
			}
		}

		return true;
	}

	function setCheckbox(cbox, fld)
	{
		fld.value = 'N';
		if ( cbox.checked )
		{
			fld.value = 'Y';
		}
		return true;
	}


	function finalizeAppPool() {
		if ( validateAppPool_poolid()) {
			doSubmit('/browse/browse.jsp', 'AppPoolAdd;AppPooluserUpdateByPool;BrowseRetrieve');
		}
	}

	function validateAppPool_poolid() {		
		return validate(frm.AppPool_poolid,"You must enter an value in Pool Id.");
	}
	
	function validate(Field, Message) {
		var var1= Field.value;
		if (var1 == null || var1 == ""){
			alert(Message);
			Field.focus();
			return false;
		}else{
			return true;
		}
	}

	
	
// End Hide script -->
</SCRIPT>