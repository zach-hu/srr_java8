<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_return_property_fields.jsp" %>
<%@ page import="java.math.*" %>

<tsa:hidden name="propertyAction" value="UPDATE"/>
<tsa:hidden name="indexOfPropertyToEdit" value=""/>
<tsa:hidden name="InvReturn_recDate" value="${InvReturn_recDate}"/>
<tsa:hidden name="InvReturn_recBy" value="${InvReturn_recBy}"/>
<tsa:hidden name="newAisle" value="${newAisle}"/>
<tsa:hidden name="newTier" value="${newTier}"/>
<tsa:hidden name="newRow" value="${newRow}"/>
<tsa:hidden name="newBin" value="${newBin}"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
	<td width=680px align=center>
		<table border=1 id=invPropertyListTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
<%

	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
	String s_bin_HdrIc = (String) request.getAttribute("InvBinLocation_hdrIc");
	String s_bin_IcRc = (String) request.getAttribute("InvBinLocation_icRc");
	String s_bin_IcRecLine = (String) request.getAttribute("InvBinLocation_icRecLine");
%>
				<td class=browseHdr height=18px nowrap>&nbsp;Property Records for "<%=invItemNumber%>" at the <%= invItemLocation %> Location</td>
				<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvBinLocation_icRc" value="<%=s_bin_IcRc%>"/>
				<tsa:hidden name="InvBinLocation_hdrIc" value="<%=s_bin_HdrIc%>"/>
				<tsa:hidden name="InvBinLocation_icRecLine" value="<%=s_bin_IcRecLine%>"/>
				<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
				<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
				<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
				<tsa:hidden name="InvProperty_icProperty" value=""/>
				<tsa:hidden name="RequisitionLine_icReqLine" value="${RequisitionLine_icReqLine}"/>
				<tsa:hidden name="toBin" value="${toBin}"/>
			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=680px>
				<tr>
				<td align=center width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%></b></td>
				<td align=center width=12%><b>Remarks</b></td>
				<td align=center width=5%><b>Edit</b></td>
				<td align=center width=6%><b>Delete</b></td>
				</tr>
				</table>

			<table border=0 cellspacing=0 cellpadding=1 width=680px>
<%
				if (serialNumbers != null && serialNumbers.size() > 0) {
					for (int i = 0; i < serialNumbers.size(); i++) {
						InvProperty prop = (InvProperty) serialNumbers.get(i); %>

						<tr>
							<td width=12% align=center><%= prop.getSerialNumber() %></td>
							<td width=12% align=center><%= prop.getRemarks() %></td>
							<td width=5% align=center><a href="javascript: viewProperty(<%=i%>);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Property Adjustment"></a></td>
							<td width=6% align=center><a href="javascript: deleteProperty(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Property Record"></a></td>
						</tr><%
					}
				} else {
					%><tr><td>No serial numbers attached</td></tr><%
				} %>
			</table>
		</td>
	</tr>
</table>
</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addProperty(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton">
		<a href="javascript:doSubmit('/inventory/inv_return_summary.jsp','InvReturnSetProperty;InvReturnCreatePreview'); void(0);">Return</a>
	</div></td>
</tr>
</table>
</td>
</tr>
</table>

<br>
</FORM>
<%@ include file="/system/footer.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function viewProperty(row) {
		frm.indexOfPropertyToEdit.value = row;
		doSubmit('/inventory/inv_return_property.jsp', 'InvReturnSetProperty');
	}

	function addProperty()
	{
		frm.propertyAction.value = "CREATE";
		doSubmit('/inventory/inv_return_property_add.jsp', 'InvReturnSetProperty');
	}

	function deleteProperty(row) {
		frm.indexOfPropertyToEdit.value = row;
		if (confirm("Are you sure you want to delete this property record?"))
		{
			doSubmit('/inventory/inv_return_property_list.jsp','InvReturnSetProperty;InvReturnDeleteProperty');
		}
	}

// End Hide script -->
</SCRIPT>