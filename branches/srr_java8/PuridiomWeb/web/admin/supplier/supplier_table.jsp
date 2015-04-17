<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="java.math.*" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	String s_status = "2000";
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Address Table</div>
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
<table border=0 cellspacing=0 cellpadding=1 width=135px>
		<tr>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);">Supplier</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactAddressRetrieveBySupplier'); void(0);">Contacts</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/admin/supplier/supplier_notes.jsp', 'VendorRetrieveById'); void(0);">Notes / UDFs</a></b></td>
		</tr>
</table>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=800px>
<tr>
	<td valign=top>
				<table border=0 width=100%>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=18% align=left nowrap><b>Address Line 1</b></td>
							<td width=17% align=center nowrap><b>Line 2</b></td>
							<td width=14% align=center nowrap><b>Line 3</b></td>
							<td width=14% align=center nowrap><b>Line 4</b></td>
							<td width=17% align=center nowrap><b>City</b></td>
							<td width=5% align=center nowrap><b>State</b></td>
							<td width=8% align=center><b>Postal Code</b></td>
							<td width=7% align=center nowrap><b>Delete</b></td>
						</tr>
						</table>
						<tsa:hidden name="Address_addressCode" value=""/>
						<tsa:hidden name="Address_addressType" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
						<tsa:hidden name="Contact_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
						<tsa:hidden name="Vendor_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	List addrList = (List)request.getAttribute("address");
	for(int i=0;i<addrList.size();i++)
	{
		Address address = (Address) addrList.get(i);
		AddressPK addressPK = (AddressPK) address.getComp_id();
%>
						<tr>
							<td width=18% align=left nowrap><a href="javascript: viewAddress('<%=addressPK.getAddressCode()%>','<%=addressPK.getAddressType()%>');void(0);"><%=HiltonUtility.ckNull(address.getAddressLine1())%></a></td>
							<td width=17% align=center nowrap><%=HiltonUtility.ckNull(address.getAddressLine2())%></td>
							<td width=14% align=center nowrap><%=HiltonUtility.ckNull(address.getAddressLine3())%></td>
							<td width=14% align=center nowrap><%=HiltonUtility.ckNull(address.getAddressLine4())%></td>
							<td width=17% align=center nowrap><%=HiltonUtility.ckNull(address.getCity())%></td>
							<td width=5% align=center nowrap><%=HiltonUtility.ckNull(address.getState())%></td>
							<td width=8% align=center nowrap><%=HiltonUtility.ckNull(address.getPostalCode())%></td>
							<td width=7% align=center nowrap><a href="javascript: deleteAddress('<%=addressPK.getAddressCode()%>','<%=addressPK.getAddressType()%>');void(0);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Item"></a></td>
						</tr>
<% } %>
						</table>
				<tr><td colspan=5><br></td></tr>
			</table>
	</td>
</tr>
</table>


<br>
<tsa:hidden name="pageFrom" value="address"/>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=33% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorUpdate'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=33% align=center><a href="javascript: doSubmit('/admin/supplier/address_add_setup.jsp', 'ContactRetrieveBySupplier'); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0></a></td>
	<td width=33% align=center><a href="javascript: doSubmit('/admin/supplier/supplier_info.jsp', 'VendorRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();

	//setTableHeights();

	function thisLoad()
	{
		f_StartIt();
<%	if (Integer.valueOf(s_status).intValue() >= 2005 && Integer.valueOf(s_status).intValue() != 2015) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewAddress(code,type) {
		frm.Address_addressCode.value = code;
		frm.Address_addressType.value = type;
		doSubmit('/admin/supplier/address_details.jsp','AddressRetrieveById');
	}

	function deleteAddress(code,type) {
		frm.Address_addressCode.value = code;
		frm.Address_addressType.value = type;
		if (confirm("Are you sure you want to delete \"" + frm.Address_addressType.value  + "\"?")){
			doSubmit('/admin/supplier/supplier_addresses.jsp','AddressDelete;AddressRetrieveBySupplier');
		}
	}


// End Hide script -->
</SCRIPT>