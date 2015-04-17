<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	Vendor vendor = (Vendor) request.getAttribute("vendor");
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));

	String	s_current_process = "OWNERSHIP_INFO";
	String	s_current_page = "/admin/supplier/supplier_ownership_info.jsp";
	String	s_current_method = "VendorUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorAccount_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
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
<br>

<table cellpadding=0 cellspacing=2 width=<%=formEntryWidth%>>
<tr>
	<td align=center>
<%
	List ownershipTypesList = (List) request.getAttribute("ownershipTypeList");
	if (ownershipTypesList != null && ownershipTypesList.size() > 0){ %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-ownershipType", "Ownership Type", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
					<tsa:hidden name="Vendor_ownershipType" value="<%=vendor.getOwnershipType()%>"/>
<%	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < ownershipTypesList.size(); il++) {
			StdTable stdTable = (StdTable) ownershipTypesList.get(il);
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
					<tr><td><input name="as_ownershipType" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getOwnershipType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%> ONCLICK="setOwnershipType(<%=il%>);">&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
			if (ownershipTypesList.size()/(fl + 1) <= 2 && b_new_column){
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
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "ownershipType")%>>
		<tr><td align="center" valign="top"><br><br>There are no <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-ownershipType", "Ownership Type")%> records listed in your database.</td></tr>
		</table>
<%	}

	List classificationTypeList = (List) request.getAttribute("classificationTypeList");
	if (classificationTypeList != null && classificationTypeList.size() > 0) { %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-vendorClass", "Vendor Class", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
						<tsa:hidden name="Vendor_vendorClass" value="<%=vendor.getVendorClass()%>"/>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < classificationTypeList.size(); il++) {
				StdTable stdTable = (StdTable) classificationTypeList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorClass" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%> ONCLICK="setVendorClass(<%=il%>);">&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (classificationTypeList.size()/(fl + 1) <= 2 && b_new_column){
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
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr><td align="center" valign="top"><br><br>There are no <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-vendorClass", "Vendor Class")%> records listed in your database.</td></tr>
		</table>
<%	}

	List diverseCertifiedOrgList = (List) request.getAttribute("diverseCertifiedOrgList");
	if (diverseCertifiedOrgList != null && diverseCertifiedOrgList.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr><td><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%>  Check all that apply.</td></tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_diverseCertOrgs" value="<%=vendor.getDiverseCertOrgs()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < diverseCertifiedOrgList.size(); il++) {
				StdTable stdTable = (StdTable) diverseCertifiedOrgList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_diverseCertOrg" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getDiverseCertOrgs().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (diverseCertifiedOrgList.size()/(fl + 1) <= 2 && b_new_column){
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
		</tr>
		</table>
<%	} else {%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr><td align="center" valign="top"><br><br>There are no <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations")%> records listed in your database.</td></tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
			<td>Do you have a Supplier Diversity Program?</td>
			<td align=right width=25px><input type=radio name="Vendor_diversityProgram" value="Y" <% if (vendor.getDiversityProgram().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="Vendor_diversityProgram" value="N" <% if (vendor.getDiversityProgram().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorSubcontract")%>>
			<td>Do you subcontract with diverse suppliers?</td>
			<td align=right width=25px><input type=radio name="Vendor_subcontract" value="Y" <% if (vendor.getSubcontract().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="Vendor_subcontract" value="N" <% if (vendor.getSubcontract().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		</table>
	</td>
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
			var newInputField = "<tsa:hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}/>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		if (frm.handler.value.indexOf("VendorUpdate") >= 0)
		{
			setBusinessType();
			setVendorClass();
			setOwnershipType();
		}
		return true;
	}


	function setBusinessType() {
		if (frm.Vendor_businessType) {
			var businessTypes = frm.elements.item("as_businessType");
			if (businessTypes != undefined) {
				if (businessTypes.length > 1 ) {
					frm.Vendor_businessType.value = "";
					for (var i=0; i < frm.elements.item("as_businessType").length; i++){
						if (frm.as_businessType[i].checked) {
							frm.Vendor_businessType.value = frm.Vendor_businessType.value + frm.as_businessType[i].value;
						}
					}
				} else {
					if (frm.as_businessType.checked) {
						frm.Vendor_businessType.value = frm.as_businessType.value;
					}
				}
			}
		}
	}

	function setVendorClass() {
		if (frm.Vendor_vendorClass) {
			var vendorClasses = frm.elements.item("as_vendorClass");
			if (vendorClasses != undefined) {
				if (vendorClasses.length > 1 ) {
					frm.Vendor_vendorClass.value = "";
					for (var i=0; i < frm.elements.item("as_vendorClass").length; i++){
						if (frm.as_vendorClass[i].checked) {
							frm.Vendor_vendorClass.value = frm.Vendor_vendorClass.value + frm.as_vendorClass[i].value;
						}
					}
				} else {
					if (frm.as_vendorClass.checked) {
						frm.Vendor_vendorClass.value = frm.as_vendorClass.value;
					}
				}
			}
		}
	}

	function setOwnershipType() {
		if (frm.Vendor_ownershipType) {
			var ownershipTypes = frm.elements.item("as_ownershipType");
			if (ownershipTypes != undefined) {
				if (ownershipTypes.length > 1 ) {
					frm.Vendor_ownershipType.value = "";
					for (var i=0; i < frm.elements.item("as_ownershipType").length; i++){
						if (frm.as_ownershipType[i].checked) {
							frm.Vendor_ownershipType.value = frm.Vendor_ownershipType.value + frm.as_ownershipType[i].value;
						}
					}
				} else {
					if (frm.as_ownershipType.checked) {
						frm.Vendor_ownershipType.value = frm.as_ownershipType.value;
					}
				}
			}
		}
	}

	function setDiverseCertOrgs() {
		if (Vendor_diverseCertOrgs) {
			var diverseCertOrgs = frm.elements.item("as_diverseCertOrg");
			if (diverseCertOrgs != undefined) {
				if (diverseCertOrgs.length > 1) {
					frm.Vendor_diverseCertOrgs.value = "";
					for (var i=0; i < frm.elements.item("as_diverseCertOrg").length; i++){
						if (frm.as_diverseCertOrg[i].checked) {
							frm.Vendor_diverseCertOrgs.value = frm.Vendor_diverseCertOrgs.value + frm.as_diverseCertOrg[i].value;
						}
					}
				} else {
					if (frm.as_diverseCertOrg.checked) {
						frm.Vendor_diverseCertOrgs.value = frm.as_diverseCertOrg.value;
					}
				}
			}
		}
	}

// End Hide script -->
</SCRIPT>