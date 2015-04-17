<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
		List vendorAccountList = (List) request.getAttribute("vendorAccountList");
%>

<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier Accounts</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td align=right>
				<table border=0 cellspacing=0 cellpadding=1 width=135px>
				<tr>
						<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);">Supplier Info</a> | </b></td>
						<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactAddressRetrieveBySupplier'); void(0);">Contacts/Addresses</a> | </b></td>
						<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_notes.jsp', 'VendorRetrieveById'); void(0);">Notes/UDFs</a> | </b></td>
						<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_certification_types.jsp', 'StdTableRetrieveBy;VendorRetrieveById'); void(0);">Certification Types</a> | </b></td>
						<td align=left NOWRAP <%=HtmlWriter.isVisible(oid, "sup-contracts")%>><b><a href="javascript: doSubmit('/admin/supplier/supplier_contracts.jsp', 'ContractsRetrieveByVendorId'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-contracts", "Ins./Contract")%></a></b></td>
				</tr>
				</table>
			</td>
		</tr>
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
<br>

<tsa:hidden name="Vendor_vendorId" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAccount_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Contact_vendorId" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Address_addressType" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<tsa:hidden name="deleteall" value=""/>



	<table width=680px cellpadding=10 cellspacing=0 border=0>
	 <tr>
	  <td>

		<table border=1 width=480px cellspacing=0 cellpadding=0 class=browseHdr align=center>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td nowrap width=200px height=18px class=browseHdr><b>&nbsp;Account Number</b></td>
							<td nowrap height=18px class=browseHdr><b>Description</b></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=2 width=360px align=center>
						<tr>
							<td colspan=4>
								<table id=vendorAccount border=0 cellspacing=0 cellpadding=2 width=350px align=center>
<%
		if (vendorAccountList != null)
		{
			for (int i = 0; i < vendorAccountList.size(); i++)
			{
				VendorAccount vendorAccount = (VendorAccount) vendorAccountList.get(i);
				VendorAccountPK vendorAccountPK = vendorAccount.getComp_id();
				String s_account = "12345";
%>
								<tr>
									<td id=accountNumber class=browseRow width=80px><input type=text name="VendorAccount_accountNumber" value="<%=vendorAccountPK.getAccountNumber()%>" size=35 maxlength=30></td>
									<td id=accountDescription class=browseRow width=150px><input type=text name="VendorAccount_description" value="<%=vendorAccount.getDescription()%>" size=65 maxlength=60></td>
									<td id=delete_<%=i%> class=browseRow width=25px><a href="javascript: if (confirmDelete(<%=i%>)) { deleteMe(<%=i%>); void(0); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
								</tr>
<%		}
		} %>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=4>
								<br>
								<table border=0 cellspacing=0 cellpadding=2 width=100% align=center>
								<tr>
									<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B>Add new</B></font></a></td>
									<td>&nbsp;</td>
									<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B>delete all</B></font></a>&nbsp;</td>
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

<br><br>


<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorAccountUpdateByVendor;VendorRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 tabIndex=20></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 tabIndex=21></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentRow = 0;
	var myTable = document.getElementById("vendorAccount");
	var count = myTable.rows.length;

	if (count <= 0)
	{
		addNew();
	}

	function addNew()
	{
		myRow = createRow(myTable);

		myCell = createCell(myRow);
		id = "accountNumber";
		//myCell.width = "100px";
		myCell.innerHTML = "<input type=text name=\"VendorAccount_accountNumber\" size=35 maxlength=30 value=\"\">";

		myCell = createCell(myRow);
		id = "accountDescription";
		myCell.id = id;
		//myCell.width = "200px";
		myCell.innerHTML = "<input type=text name=\"VendorAccount_description\" size=65 maxlength=60 value=\"\";'>";

		myCell = createCell(myRow);
		id = "delete_" + count;
		myCell.id = id;
		myCell.width = "25px";
		myCell.innerHTML = "<a href=\"javascript: if (confirmDelete(" + count + ")) { deleteMe(" + count + "); void(0); } \"><img name=\"deletethis\" src=\"<%=contextPath%>/images/delete.gif\" alt=\"Delete\" border=0></a>";

		if (frm.VendorAccount_accountNumber[count])
		{
			frm.VendorAccount_accountNumber[count].focus();
			frm.VendorAccount_accountNumber[count].fireEvent("onfocus");
		}
		else
		{
			frm.VendorAccount_accountNumber.focus();
			frm.VendorAccount_accountNumber.fireEvent("onfocus");
		}

		currentRow = count;
		count++;
	}

	function deleteMe(row)
	{
			var currentRows = myTable.rows.length;

			if (currentRows == 0)
			{
				return;
			}
			else if (currentRows > 1)
			{
				for (var i = row; i < (currentRows - 1); i++)
				{
					var account = frm.VendorAccount_accountNumber[i + 1].value;
					var description = frm.VendorAccount_description[i + 1].value;

					frm.VendorAccount_accountNumber[i].value = account;
					frm.VendorAccount_description[i].value = description;
				}
			}

			myTable = document.getElementById("vendorAccount");
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
	}

	function deleteAll()
	{
		if (confirm("Are you sure you wish to delete all Accounts for this Supplier?"))
		{
			frm.deleteall.value = "TRUE";
			myTable = document.getElementById("vendorAccount");

			count = myTable.rows.length;
			for (i = 0;i < count; i++)
			{
				myTable.deleteRow(0);
			}
			count = 0;
			addNew();
		}
	}

	function validateForm()
	{
		frm.deleteall.value = "TRUE";
		var rowcount = frm.elements.item("VendorAccount_accountNumber");

		if (rowcount.length != undefined)
		{
			for (var i = rowcount.length; i > 0; i--)
			{
				if (frm.VendorAccount_accountNumber[i - 1].value.length <= 0)
				{
					deleteMe(i-1);
				}
			}
		}

		rowcount = frm.elements.item("VendorAccount_accountNumber");
		if (rowcount.length != undefined)
		{
			for (var i = 0; i < rowcount.length; i++)
			{
				if (frm.VendorAccount_accountNumber[i].value.length > 0)
				{
					frm.deleteall.value = "FALSE";
				}
			}
		}
		else
		{
			if (frm.VendorAccount_accountNumber.value.length > 0)
			{
				frm.deleteall.value = "FALSE";
			}
		}

		return true;
	}

	function confirmDelete(row)
	{
		var rowcount = document.all.vendorAccount.rows.length;
		if (rowcount > 1)
		{
			if (frm.VendorAccount_accountNumber[row].value.length > 0)
			{
				confirm('Are you sure you wish to delete this Account from this Supplier?');
			}
		}
		else
		{
			if (frm.VendorAccount_accountNumber.value.length > 0)
			{
				confirm('Are you sure you wish to delete this Account from this Supplier?');
			}
		}
		return true;
	}



// End Hide script -->
</SCRIPT>