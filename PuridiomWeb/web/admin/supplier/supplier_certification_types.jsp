<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	Vendor vendor = (Vendor) request.getAttribute("vendor");
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));

	String	s_current_process = "CERTIFICATION_TYPES";
	String	s_current_page = "/admin/supplier/supplier_certification_types.jsp";
	String	s_current_method = "VendorUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorAccount_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="Vendor_vendorClass" value="<%=vendor.getVendorClass()%>"/>
<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%></div>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>

<%
	List certificationTypesList = (List) request.getAttribute("stdTableList");
	if (certificationTypesList.size() == 0){ %>
	<td align="center" valign="top"><br><br>There are no Certification Types listed in your database.</td>
<%	} else {%>
	<td valign=top>
		<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
		<tr>
			<td width="10%">&nbsp;</td>
			<td width=45% valign=top>
			<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < certificationTypesList.size(); il++) {
			StdTable stdTable = (StdTable) certificationTypesList.get(il);
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
				<tr>
					<td nowrap><input name="as_selected" type=checkbox <%if (vendor.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%> ONCLICK="setVendorClass(<%=il%>);">&nbsp;<%=stdTable.getDescription()%>
						<tsa:hidden name="as_vendorClass" value="<%=stdTablePK.getTableKey()%>"/>
					</td>
				</tr>
<%			fl = il;
			if (certificationTypesList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
			</table>
			</td>
			<td width=45% valign=top>
			<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
		}%>
			</table>
			</td>
		</tr>
		</table>
	</td>
<%	}%>
	<td valign=top align="right"><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setVendorClass(row) {
		frm.Vendor_vendorClass.value = "";
		if (frm.elements.item("as_vendorClass").length > 1 ) {
			for (var i=0; i < frm.elements.item("as_vendorClass").length; i++){
				if (frm.as_selected[i].checked) {
					frm.Vendor_vendorClass.value = frm.Vendor_vendorClass.value + frm.as_vendorClass[i].value;
				}
			}
		} else {
			if (frm.as_selected.checked) {
				frm.Vendor_vendorClass.value = frm.as_vendorClass.value;
			}
		}
	}

	function uploadDocs()
	{
		var newInputField = "<input type=hidden name='VendorDocument_icRfqHeader' value=0>" +
				"<input type=hidden name='VendorDocument_vendorId' value=<%=vendor.getVendorId()%>>" +
				"<input type=hidden name=allowEdit value=Y>" +
				"<input type=hidden name=returnPage value='/admin/supplier/supplier_certification_types.jsp'>" +
				"<input type=hidden name=returnHandler value='StdTableRetrieveBy;VendorRetrieveById'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_attachments.jsp', 'VendorUpdate;VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function validateForm()
	{
		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		return true;
	}

// End Hide script -->
</SCRIPT>