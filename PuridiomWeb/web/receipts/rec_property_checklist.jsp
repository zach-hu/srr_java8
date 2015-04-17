<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	List invPropertyList = (List)request.getAttribute("invPropertyList");

	String icRc = "0";
	if (invPropertyList != null && invPropertyList.size() > 0) {
		icRc = ((InvProperty)invPropertyList.get(0)).getIcRc().toString();
	}

	String itemNumber = HiltonUtility.ckNull((String)request.getAttribute("InvProperty_itemNumber"));
	String icRecLine = HiltonUtility.ckNull((String)request.getAttribute("InvProperty_icRecLine"));
	String icPoLine = HiltonUtility.ckNull((String)request.getAttribute("InvProperty_icPoLine"));

	if (HiltonUtility.isEmpty(userDateFormat)) {
		userDateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
	}
	String today = Dates.today(userDateFormat, userTimeZone);
%>

<input type=hidden name="InvProperty_icRc" value="<%=icRc%>">
<input type=hidden name="InvProperty_itemNumber" value="<%=itemNumber%>">
<input type=hidden name="InvProperty_status" value="00">
<input type=hidden name="InvProperty_icRecLine" value="<%=icRecLine%>">
<input type=hidden name="InvProperty_icPoLine" value="<%=icPoLine%>">
<input type=hidden name="InvProperty_source" value="REC">

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
	<td width=680px align=center>
		<table border=1 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td class=browseHdr height=18px nowrap>
					<table border=0 width="100%" cellpadding=0 cellspacing=0>
						<tr class=browseHdr height=18px><td nowrap>
							&nbsp;Property Records
						</td><td align="right" nowrap>
						</td></tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=browseRow>
					<table border=0	cellpadding=1 cellspacing=0 width=680px>
					<tr>
						<td width="50%" <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%></b></td>
						<td width="20%" <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-datein", "Date In")%></b></td>
						<td width="25%" <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-owner", "Recorded By") %></b></td>
						<td width="5%">&nbsp;</td>
					</tr>
					</table>
					<table id="invPropertyTable" border=0 cellpadding=1 cellspacing=0 width=680px>
<%
	if (invPropertyList != null && invPropertyList.size() > 0)
	{
		for (int i = 0; i < invPropertyList.size(); i++)
		{
			InvProperty invProperty = (InvProperty) invPropertyList.get(i);

			String serialNumber = HiltonUtility.ckNull(invProperty.getSerialNumber());
			String dateIn = HiltonUtility.getFormattedDate(invProperty.getDateIn(), oid, userDateFormat);
			String owner = HiltonUtility.ckNull(invProperty.getOwner());
%>
					<tr>
						<td width="50%" <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> align=center>
							<input type=text id="InvProperty_serialNumber" name="InvProperty_serialNumber" value="<%=serialNumber%>" size=50 maxlength=200 onchange="upperCase(this);">
						</td>
						<td width="20%" <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center>
							<%=dateIn%>
							<input type=hidden id="InvProperty_dateIn" name="InvProperty_dateIn" value="<%=dateIn%>">
						</td>
						<td width="25%" <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center>
							<%=owner%>
							<input type=hidden id="InvProperty_owner" name="InvProperty_owner" value="<%=owner%>">
						</td>
						<td width="5%">
							<a href="javascript: if (confirm('Are you sure you wish to delete this serial number?')) { deleteMe(<%=i%>); } void(0);">
								<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">
							</a>
						</td>
					</tr>
<%		}
	} %>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td>
					<a href="javascript: addNew(); void(0);"><font class="reset"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addnew", "Add new")%></b></font></a>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: save(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: close(); void(0);">Close</a>
	</div></td>
</tr>
</table>
</td>
</tr>
</table>

<br>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	/*var invPropertyTable = document.getElementById("invPropertyTable");
	var rowsTable = invPropertyTable.rows.length;
	if (rowsTable == 0)
	{
		addNew();
	}*/

	function addNew()
	{
		var myTable = document.getElementById("invPropertyTable");
		var maxRows = myTable.rows.length;

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		myCell.align = "center";
		myCell.width = "50%";
		myCell.innerHTML = "<input type=\"text\" id=\"InvProperty_serialNumber\" name=\"InvProperty_serialNumber\" size=\"50\" maxlenght=\"200\" VALUE=\"\" onchange=\"upperCase(this);\">";
		<%=HtmlWriter.cellVisibility(oid, "invprop-serialnumber")%>
		<%=HtmlWriter.cellDisplay(oid, "invprop-serialnumber")%>

		myCell = createCell(myRow);
		myCell.align = "center";
		myCell.width = "20%";
		myCell.innerHTML = "<%=today%><input type=\"hidden\" id=\"InvProperty_dateIn\" name=\"InvProperty_dateIn\" VALUE=\"<%=today%>\">";
		<%=HtmlWriter.cellVisibility(oid, "invprop-datein")%>
		<%=HtmlWriter.cellDisplay(oid, "invprop-datein")%>

		myCell = createCell(myRow);
		myCell.align = "center";
		myCell.width = "25%";
		myCell.innerHTML = "<%=uid%><input type=\"hidden\" id=\"InvProperty_owner\" name=\"InvProperty_owner\" VALUE=\"<%=uid%>\">";
		<%=HtmlWriter.cellVisibility(oid, "invprop-owner")%>
		<%=HtmlWriter.cellDisplay(oid, "invprop-owner")%>

		myCell = createCell(myRow);
		myCell.width = "5%";
		myCell.innerHTML = "<a href=\"javascript: if (confirm('Are you sure you wish to delete this serial number?')) { deleteMe(" + (maxRows) + "); } void(0);\">" +
						   "<img src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=\"0\"></a>";
	}

	function deleteMe(row)
	{
		var myTable = document.getElementById("invPropertyTable");
		var maxRows = myTable.rows.length - 1;

		if (maxRows > 0)
		{
			for (var i = row; i < maxRows; i++)
			{
				var serialNumber = frm.InvProperty_serialNumber[i + 1].value;
				var dateIn = frm.InvProperty_dateIn[i + 1].value;
				var owner = frm.InvProperty_owner[i + 1].value;

				frm.InvProperty_serialNumber[i].value = serialNumber;
				frm.InvProperty_dateIn[i].value = dateIn;
				frm.InvProperty_owner[i].value = owner;
			}
		}

		deleteRow(myTable, maxRows);
	}

	function save()
	{
		var myTable = document.getElementById("invPropertyTable");
		var maxRows = myTable.rows.length;

		if (maxRows > 0)
		{
			if (maxRows > 1)
			{
				for (var i = 0; i < maxRows; i++)
				{
					if (frm.InvProperty_serialNumber[i] && trim(frm.InvProperty_serialNumber[i]) == "")
					{
						alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
						frm.InvProperty_serialNumber[i].focus();
						return false;
					}
				}
			}
			else
			{
				var serialNumber = document.getElementById("InvProperty_serialNumber");
				if (serialNumber && trim(serialNumber) == "")
				{
					alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%> is required");
					serialNumber.focus();
					return false;
				}
			}
		}

		doSubmit('/receipts/rec_property_checklist_temp.jsp', 'InvPropertyUpdateForReceipt;InvPropertyRetrieveByIcRecLine');
		//window.top.hidePopWin();
	}

	function close()
	{
		window.top.hidePopWin();
	}

// End Hide script -->
</SCRIPT>