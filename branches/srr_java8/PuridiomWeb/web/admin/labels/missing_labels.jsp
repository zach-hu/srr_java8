<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.io.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%
	String currentProcess = (String) request.getAttribute("currentProcess");
	currentProcess = HiltonUtility.ckNull(currentProcess);
%>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<!-- start rounded corners -->
<div id="container" style="width: <%=formWidth%>; align:left; margin:5;">
<b class="rtop">
  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
</b>
<table cellpadding=0 cellspacing=0 border=0 class=browseHdr width=100%>
<tr>
	<td>
		<table border=0 cellpadding=1 cellspacing=0 class=browseHdr width=100%>
		<tr class=browseHdr>
			<td class=browseHdr width="4%" align=center><input type=checkbox name="selectAll" onClick="flagAll(this.checked, 'c_checkbox');">&nbsp;</td>
			<td class=browseHdr width="24%" nowrap>LABEL CODE</td>
			<td class=browseHdr width="43%" nowrap>DEFAULT LABEL VALUE</td>
			<td class=browseHdr width="19%" nowrap>
				MODULE ACCESS<br>
				<select name="selectAllModuleAccess" onChange="flagAllSelection(this.selectedIndex, 'Labels_moduleAccess');">
					<option value="ADMIN">ADMIN</option>
					<option value="APPROVALS">APPROVALS</option>
					<option value="DOCUMENTS">DOCUMENTS</option>
					<option value="GENERAL">GENERAL</option>
					<option value="INVENTORY">INVENTORY</option>
					<option value="INVOICE">INVOICE</option>
					<option value="LOGIN">LOGIN</option>
					<option value="PURCHASE ORDERS">PURCHASE ORDERS</option>
					<option value="PURIDIOMX ADMIN">PURIDIOMX ADMIN</option>
					<option value="RECEIVING">RECEIVING</option>
					<option value="REQUISITIONS">REQUISITIONS</option>
					<option value="SCHEDULES">SCHEDULES</option>
					<option value="REQUEST FOR QUOTES">REQUEST FOR QUOTES</option>
					<option value="SUPPLIER">SUPPLIER</option>
					<option value="CONTACT">SUPPLIER CONTACT</option>
					<option value="SUPPLIERPORTAL">SUPPLIER PORTAL</option>
					<option value="SUPPLIERPORTALCONTACT">SUPPLIER PORTAL CONTACT</option>
					<option value="SYSTEMTABLES">SYSTEM TABLES</option>
					<option value="TOTALS">TOTALS</option>
					<option value="USER">USER</option>
				</select>
			</td>
			<td class=browseHdr width="5%" align=center nowrap>VISIBLE<br><input type=checkbox name="selectAllVisible" onClick="flagAll(this.checked, 'c_visible');">&nbsp;</td>
			<td class=browseHdr width="5%" align=center>ALLOW EDIT<br><input type=checkbox name="selectAllEdit" onClick="flagAll(this.checked, 'c_allowEdit');">&nbsp;</td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdr style="border:solid 2px; background: #FFFFFF; padding: 2px; width: <%=formWidth%>; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=1 cellspacing=0 cellpadding=2 width=100% class=browseRow>
		<tr><td><b>There were no missing label properties found at this time.</b></td></tr>
		</table>
		</div>

		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
<%
	String rowClass = "browseRow";
	PropertyResourceBundle bundle = (PropertyResourceBundle) DictionaryManager.getInstance("missinglabels", oid).bundle;
	int irows = 0;
	if (bundle != null) {
		Enumeration elabels = bundle.getKeys();

		while (elabels.hasMoreElements()) {
			String labelCode = (String) elabels.nextElement();
			String labelValue = DictionaryManager.getInstance("missinglabels", oid).getProperty(labelCode);

			labelCode = labelCode.toUpperCase();
%>
		<tr>
			<td class=<%=rowClass%>>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=<%=rowClass%>>
				<tr class="<%=rowClass%>">
					<td height=18px class="<%=rowClass%>" width="4%" align=center>
						<tsa:hidden name="originalClassName" value="<%=rowClass%>"/>
						<input type=checkbox name="c_checkbox" value=Y onClick="setFlagFromCkbox(this, frm.as_selectedRow, <%=irows%>);highlightRow(<%=irows%>);"><tsa:hidden name="as_selectedRow" value="N"/>
					</td>
					<td height=18px class="<%=rowClass%>" width="25%"><%=labelCode%><tsa:hidden name="Labels_labelCode" value="<%=labelCode%>"/></td>
					<td height=18px class="<%=rowClass%>" width="45%"><input type="text" name="Labels_labelValue" value="<%=labelValue%>" size="80" maxLegth="2000"></td>
					<td height=18px class="<%=rowClass%>" width="15%">
						<select name="Labels_moduleAccess">
							<option value="ADMIN">ADMIN</option>
							<option value="APPROVALS">APPROVALS</option>
							<option value="DOCUMENTS">DOCUMENTS</option>
							<option value="GENERAL">GENERAL</option>
							<option value="INVENTORY">INVENTORY</option>
							<option value="INVOICE">INVOICE</option>
							<option value="LOGIN">LOGIN</option>
							<option value="PURCHASE ORDERS">PURCHASE ORDERS</option>
							<option value="PURIDIOMX ADMIN">PURIDIOMX ADMIN</option>
							<option value="RECEIVING">RECEIVING</option>
							<option value="REQUISITIONS">REQUISITIONS</option>
							<option value="SCHEDULES">SCHEDULES</option>
							<option value="REQUEST FOR QUOTES">REQUEST FOR QUOTES</option>
							<option value="SUPPLIER">SUPPLIER</option>
							<option value="SUPPLIERPORTAL">SUPPLIER PORTAL</option>
							<option value="CONTACT">SUPPLIER CONTACT</option>
							<option value="SUPPLIERPORTALCONTACT">SUPPLIER PORTAL CONTACT</option>
							<option value="TOTALS">TOTALS</option>
							<option value="USER">USER</option>
						</select>
					</td>
					<td height=18px class="<%=rowClass%>" width="6%" align=center><input type=checkbox name="c_visible" value=Y onClick="setFlagFromCkbox(this, frm.Labels_visible, <%=irows%>);"><tsa:hidden name="Labels_visible" value="Y"/></td>
					<td height=18px class="<%=rowClass%>" width="5%" align=center><input type=checkbox name="c_allowEdit" value=Y onClick="setFlagFromCkbox(this, frm.Labels_allowEdit, <%=irows%>);"><tsa:hidden name="Labels_allowEdit" value="Y"/></td>
				</tr>
				</table>
				</div>
			</td>
		</tr>
<%
			if (rowClass.equals("browseRow")) {
				rowClass = "summary";
			} else {
				rowClass = "browseRow";
			}
			irows++;
		}
	}
%>
		</table>
		</div>
	</td>
</tr>
</table>
<b class="rbottom">
  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
</b>
</div>
<!-- end rounded corners -->

<br>
<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
<%	if (irows > 0) {%>
	<td width="50%" align="center"><div id="pxbutton"><a href="javascript: saveMe(); void(0);">Save</a></div></td>
<%	}%>
	<td width="50%" align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td>
</tr>
</table>

<tsa:hidden name="currentProcess" value="<%=currentProcess%>"/>
<!-- dummy fields to prevent js errors with only one record -->
<tsa:hidden name="as_selectedRow" value="N"/>
<tsa:hidden name="Labels_labelCode" value=""/>
<tsa:hidden name="Labels_visible" value=""/>
<tsa:hidden name="Labels_allowEdit" value=""/>
<tsa:hidden name="Labels_moduleAccess" value=""/>
<tsa:hidden name="as_selectedRow" value="N"/>
<tsa:hidden name="originalClassName" value=""/>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	var totalRows = <%=irows%>;
	var pageSize = <%=irows%>;
	var pageCount = 1;
	var filterSet = false;

	browseDisplaySetup();

	function saveMe() {
		if (confirm("Unselected labels will be removed from missing labels in order to refresh the list.  Continue?")) {
			frm.browseName.value = 'labels-admin';

			var moduleAccess = frm.Labels_moduleAccess[0][frm.Labels_moduleAccess[0].selectedIndex].value;
			frm.currentProcess.value = moduleAccess;
			setFilter("Labels_moduleAccess", "=", moduleAccess);

			doSubmit('/admin/labels/labels.jsp', 'LabelsAddSelectedListRefresh;LabelsDeleteMissingLabelsFile;BrowseRetrieve');
		} else {
			return false;
		}
	}

	function returnMe() {
		history.go(-1);
	}

	function highlightRow(row) {
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];

		if (frm.as_selectedRow[row].value == "Y") {
			setRowClassName(myRow, "selectedRow");
		} else {
			setRowClassName(myRow, frm.originalClassName[row].value);
		}
	}

	function flagAll(checkAll, ckboxName) {
		var checkboxes = document.all(ckboxName);

		if (checkboxes != null) {
			var myObject = document.all("browseRows");
			var myTable;

			if (myObject.length > 1) {
				myTable = myObject(currentPage - 1);
			} else {
				myTable = myObject;
			}
			var pageRows = myTable.rows.length;
			var startingRow = eval(0);
			var endingRow = eval(pageRows);

			if (checkboxes.length > 1) {
				for (i = startingRow; i < endingRow; i++) {
					var cbox = checkboxes[i];
					cbox.checked = checkAll;
					cbox.fireEvent("onClick");
				}
			} else {
				var cbox = checkboxes;
				cbox.checked = checkAll;
				cbox.fireEvent("onClick");
			}
		}
	}

	function flagAllSelection(selectedInd, fldName) {
		var flds = document.all(fldName);

		if (flds != null) {
			var myObject = document.all("browseRows");
			var myTable;

			if (myObject.length > 1) {
				myTable = myObject(currentPage - 1);
			} else {
				myTable = myObject;
			}
			var pageRows = myTable.rows.length;
			var startingRow = eval(0);
			var endingRow = eval(pageRows);

			if (flds.length > 1) {
				for (i = startingRow; i < endingRow; i++) {
					var fld = flds[i];
					fld.selectedIndex = selectedInd;
					fld.fireEvent("onChange");
				}
			} else {
				var fld = flds;
				fld.selectedIndex = selectedInd;
				fld.fireEvent("onChange");
			}
		}
	}

//-->
</script>