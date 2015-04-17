<SCRIPT value=JavaScript>
<!-- Hide script

	function addNew()
	{
<%	String commodityDepartment = "", commodityBuyerCode = "", commodityBuyerName = "";
%>
		frm.deleteall.value = "FALSE";
		myTable = document.getElementById("commoditydeparment");
		TotalRows = TotalRows+1;

		myRow = createRow(myTable);
		maxRows++;

		myCell = createCell(myRow);
		myCell.innerHTML = "";

		myCell = createCell(myRow);
		id = "acc_0";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"HIDDEN\" NAME=\"CommodityDepartmentBuyer_commodity\" SIZE=\"20\" MAXLENGTH=\"20\" value=\"<%=s_commodity%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' >";
		<%=HtmlWriter.cellVisibility(oid, "commCommodity")%>
		<%=HtmlWriter.cellDisplay(oid, "commCommodity")%>


		myCell = createCell(myRow);
		id = "acc_1";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"CommodityDepartmentBuyer_departmentCode\" SIZE=\"20\" MAXLENGTH=\"20\" value=\"<%=commodityDepartment%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' >";
		<%=HtmlWriter.cellVisibility(oid, "commDepartment")%>
		<%=HtmlWriter.cellDisplay(oid, "commDepartment")%>

		myCell = createCell(myRow);
		id = "acc_2";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"CommodityDepartmentBuyer_userId\" SIZE=\"20\" MAXLENGTH=\"20\" value=\"<%=commodityBuyerCode%>\" ONCHANGE=\"upperCase(this); \" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' >";
		<%=HtmlWriter.cellVisibility(oid, "commBuyer")%>
		<%=HtmlWriter.cellDisplay(oid, "commBuyer")%>

		myCell = createCell(myRow);
		id = "acc_3";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"as_userName\" SIZE=\"40\" MAXLENGTH=\"40\" value=\"<%=commodityBuyerName%>\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (maxRows - 1) + ");' DISABLED>";
		<%=HtmlWriter.cellVisibility(oid, "commName")%>
		<%=HtmlWriter.cellDisplay(oid, "commName")%>

		myCell = createCell(myRow);
		id = "acc_del_" + (maxRows - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Row?')) { deleteMe(" + (maxRows - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";

	}

	function deleteMe(row)
	{
		var accRows = maxRows;

		myTable = document.getElementById("commoditydeparment");

		if (accRows == 0)
		{
			return;
		}
		else if (accRows > 1)
		{
			for (var i = row; i < (accRows - 1); i++)
			{
				var fld1 = frm.CommodityDepartmentBuyer_commodity[i + 1].value;
				var fld2 = frm.CommodityDepartmentBuyer_departmentCode[i + 1].value;
				var fld3 = frm.CommodityDepartmentBuyer_userId[i + 1].value;
				var fld4 = frm.as_userName[i + 1].value;

				frm.CommodityDepartmentBuyer_commodity[i].value = fld1;
				frm.CommodityDepartmentBuyer_departmentCode[i].value = fld2;
				frm.CommodityDepartmentBuyer_userId[i].value = fld3;
				frm.as_userName[i].value = fld4;
			}
		}
		deleteRow(myTable, accRows);

		accRows = accRows - 1;

		if (accRows <= 0)
		{
			frm.deleteall.value = "TRUE";
		}

		maxRows = maxRows - 1;

		if (accRows <= 0)
		{
			addNew();
		}

	}


	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Rows?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("commoditydeparment");
			count = maxRows;

			maxRows = 0;

			for(i = 0; i < count; i++)
			{
				myTable.deleteRow(1);
			}

			addNew();
		}
	}

// End Hide script -->
</SCRIPT>