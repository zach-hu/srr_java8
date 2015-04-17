<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>


<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
  	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	Contact contact = (Contact) request.getAttribute("contact");

	BigDecimal bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_po_number = poHeader.getPoNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	String	s_req_number = poHeader.getRequisitionNumber();
	String	s_po_type = poHeader.getPoType();
	String	s_po_status = poHeader.getStatus();
	String	s_buyer_code = poHeader.getBuyerCode();
	String	s_vendor_id = poHeader.getVendorId();
	String	s_vend_contact_code = poHeader.getVendContactCode();
	String   s_cxmlAction = poHeader.getEdiOrder();
	if(HiltonUtility.isEmpty(s_cxmlAction))	{		s_cxmlAction = "P";		}

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	String	s_contactEmail = "";
	String	s_info1 = "";
	String	s_info2 = "";
	Address vendor = (Address) poHeader.getVendorAddress();
	if (vendor != null)
	{
		s_address_line_1 = vendor.getAddressLine1();
	    s_address_line_2 = vendor.getAddressLine2();
	    s_address_line_3 = vendor.getAddressLine3();
	    s_address_line_4 = vendor.getAddressLine4();
	    s_city = vendor.getCity();
	    s_state = vendor.getState();
	    s_postal_code = vendor.getPostalCode();
	    s_country = vendor.getCountry();
	}

	if(contact != null)
	{
		s_contactEmail = contact.getEmailAddr();
		s_info1 = contact.getInfo1();
		s_info2 = contact.getInfo2();
	}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";			}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status = DocumentStatus.PO_INPROGRESS;			}

  	boolean bTempVendor = false;
  	if ( oid.equalsIgnoreCase("vse06p") && s_vendor_id.equals(s_req_number) && !HiltonUtility.isEmpty(s_req_number) )
  	{
  		bTempVendor = true;
  	}

  	String	s_current_process = "HEADER_SUPPLIER";
  	String	s_current_page = "/orders/po_supplier.jsp";
  	String	s_current_method = "PoHeaderUpdate";
  	String	s_current_process_method = "";
  	boolean bAllowEdit = true;
  	if ( (role.getAccessRights("PO") < 2) || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0) {
	    bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code)) {
		bAllowEdit = false;
	}
  	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}
%>

<%@page import="com.tsa.puridiom.entity.Contact"%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoSecurity_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=poHeader.getRevisionNumber()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=s_po_status%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=poHeader.getFiscalYear()%>"/>
<tsa:hidden name="PoHeader_itemLocation" value="<%=poHeader.getItemLocation()%>"/>
<tsa:hidden name="PoHeader_buyerCode" value="<%=poHeader.getBuyerCode()%>"/>
<tsa:hidden name="PoHeader_contractNo" value="<%=poHeader.getContractNo()%>"/>
<tsa:hidden name="PoHeader_currencyCode" value="<%=poHeader.getCurrencyCode()%>"/>
<tsa:hidden name="SubContractor_poNumber" value="<%=poHeader.getPoNumber()%>"/>
<tsa:hidden name="SubContractor_releaseNumber" value="<%=poHeader.getReleaseNumber()%>"/>
<tsa:hidden name="SubContractor_subName" value=""/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="blanketAction" value="true"/>
<tsa:hidden name="formAction" value=""/>
<tsa:hidden name="Schedule_icHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="Schedule_documentType" value="POH"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="VendorInsurance_contNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
<tsa:hidden name="PoHeader_billToCode" value="<%=poHeader.getBillToCode()%>"/>

<%if(oid.equalsIgnoreCase("bsc04p")){ %>
	<tsa:hidden name="VendorInsurance_contType" value="<%=poHeader.getPoType()%>"/>
	<tsa:hidden name="VendorInsurance_contEffective" value="<%=poHeader.getEffectiveDate()%>"/>
	<tsa:hidden name="VendorInsurance_contExpires" value="<%=poHeader.getExpirationDate()%>"/>
	<tsa:hidden name="VendorInsurance_contAdmin" value="<%=poHeader.getBuyerCode()%>"/>
	<tsa:hidden name="VendorInsurance_contOwner" value="<%=poHeader.getRequisitionerCode()%>"/>
	<tsa:hidden name="VendorInsurance_contStatus" value="<%=poHeader.getStatus()%>"/>
	<tsa:hidden name="VendorInsurance_contRequestDate" value="<%=poHeader.getPoDate()%>"/>
	<tsa:hidden name="VendorInsurance_contDescription" value="<%=poHeader.getOtherDescription()%>"/>
<%} %>
<table width=<%=formWidth%> cellpadding="0" cellspacing="0" border="0">
<tr>
  <td valign="top" width="135px" height="30px">
    <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
    <tr>
      <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class="hdr12" valign="middle">
        <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="information-supplier" defaultString="Supplier Information" /></div>
      </td>
    </tr>
    </table>
  </td>
  <td valign="bottom" width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign="bottom" align="right" height="30px" width=*>
    <table border=0 cellspacing="0" cellpadding="1" width="100%">
<%	int i_colspan = 1;%>
    <tr>
      <td nowrap align="right"><b><tsa:label labelName="order-number" defaultString="Order #" />:</b></td>
      <td width="100px"><%=s_po_number%></td>
<%	if (bd_release_number.compareTo(bd_zero) > 0)
    {
      i_colspan = i_colspan + 2; %>
      <td nowrap align=right><b><tsa:label labelName="release-number" defaultString="Release #" />:</b></td>
      <td width=100px><%=bd_release_number%></td>
<%	}
    if (bd_revision_number.compareTo(bd_zero) > 0)
    {
      i_colspan = i_colspan + 2; %>
      <td nowrap align=right><b><tsa:label labelName="revision-number" defaultString="Revision #" />:</b></td>
      <td width=100px><%=bd_revision_number%></td>
<%	} %>
    </tr>
    <tr>
      <td colspan=<%=i_colspan%> nowrap align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
      <td width="100px"><%=DocumentStatus.toString(s_po_status, oid)%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width=100%>
    <tr>
      <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<%@ include file="/orders/po_info.jsp" %>
<br>

<%	if ( bTempVendor ) {	%>
<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr><td class="error" align="center"><tsa:label labelName="tempopary-vendor" defaultString="This is a *TEMPORARY* vendor" /></td></tr>
</table>
<%	}	%>


<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td valign="top">
    <table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
    <tr>
      <td width="50%" align="center" valign="top">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tsa:tr field="po-supplier">
          <td align="right" nowrap><a href="javascript: browseSupplier('PoHeader_vendorId'); void(0);" title="Click here to choose the <tsa:label labelName="po-supplier" defaultString="Supplier" noinstance="true" /> for this order or enter the <tsa:label labelName="po-supplier" defaultString="Supplier" noinstance="true" /> in the box on the right."><tsa:label labelName="po-supplier" defaultString="Supplier" checkRequired="true" noinstance="true" /></a>:&nbsp;</td>
          <td nowrap>
          	<tsa:input labelName="po-supplier" type="mintext" name="PoHeader_vendorId" tabIndex="2" maxLength="15" value="<%=s_vendor_id%>" onchange="upperCase(this); getNewInfo('vendor', this); updatePromiseDate();" />
<%	if ( bTempVendor && role.getAccessRights("VEND") == 99 ) {	%>
			<a href="javascript: addTempVendor(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add this supplier to Puridiom." border="0"></a>
<%	} else {	%>
          	(Code)
<%	}	%>
          </td>
        </tsa:tr>
        <tsa:tr field="po-sup-addressLine1">
          <td align="right" nowrap><a href="javascript: browseContactAddress('PoHeader_vendContactCode', frm.PoHeader_vendorId.value); void(0);" title="Click here to choose the <tsa:label labelName="po-sup-addressLine1" defaultString="Company Name" noinstance="true" /> for this order or enter the <tsa:label labelName="po-sup-addressLine1" defaultString="Company Name" noinstance="true" /> in the box on the right."><tsa:label labelName="po-sup-addressLine1" defaultString="Company Name" checkRequired="true" noinstance="true" /></a>:&nbsp;</td>
          <td>
            <tsa:input labelName="po-sup-addressLine1" type="midtext" name="Address_addressLine1" maxLength="40" value="<%=s_address_line_1%>" onfocus="this.blur();" disabled="true" />
          </td>
        </tsa:tr>
        <tsa:tr field="po-sup-addressLine2">
          <td align="right" nowrap><tsa:label labelName="po-sup-addressLine2" defaultString="Address 2" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-addressLine2" type="midtext" name="Address_addressLine2" maxLength="40" value="<%=s_address_line_2%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-addressLine3">
          <td align="right" nowrap><tsa:label labelName="po-sup-addressLine3" defaultString="Address 3" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-addressLine3" type="midtext" name="Address_addressLine3" maxLength="40" value="<%=s_address_line_3%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-addressLine4">
          <td align="right" nowrap><tsa:label labelName="po-sup-addressLine4" defaultString="Address 4" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-addressLine4" type="midtext" name="Address_addressLine4" maxLength="40" value="<%=s_address_line_4%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-city">
          <td align="right" nowrap><tsa:label labelName="po-sup-city" defaultString="City" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-city" type="midtext" name="Address_city" maxLength="30" value="<%=s_city%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-state">
          <td align="right" nowrap><tsa:label labelName="po-sup-state" defaultString="State" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-state" type="midtext" name="Address_state" maxLength="15" value="<%=s_state%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-zip">
          <td align="right" nowrap><tsa:label labelName="po-sup-zip" defaultString="Postal Code" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-zip" type="midtext" name="Address_postalCode" maxLength="15" value="<%=s_postal_code%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-country">
          <td align="right" nowrap><tsa:label labelName="po-sup-country" defaultString="Country" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="po-sup-country" type="midtext" name="Address_country" maxLength="25" value="<%=s_country%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        </table>
      </td>
      <td width="50%" align="center" valign="top">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
       <tr <%=HtmlWriter.isVisible(oid, "po-sup-attention", s_po_type)%>>
         <td align="right" nowrap><a href="javascript: notEnterContact();browseContactAddress('PoHeader_vendContactCode', frm.PoHeader_vendorId.value); void(0);" title="Click here to choose the <tsa:label labelName="po-sup-attention" defaultString="Attention" /> for this order or enter the <tsa:label labelName="po-sup-attention" defaultString="Attention" /> in the box on the right."><tsa:label labelName="po-sup-attention" defaultString="Attention" checkRequired="true" /></a>:&nbsp;
        </td>
          <td>
            <tsa:input labelName="po-sup-attention" type="midtext" name="PoHeader_vendContactCode" tabIndex="4" maxLength="15" value="<%=s_vend_contact_code%>" onchange="getNewInfo('contact', this);" />
            <%	if (oid.equalsIgnoreCase("dtn07p")) { %>
						<a href="javascript: enterContact(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add a non-standard contact" border="0"></a>
			<%	} %>
            <tsa:hidden name="PoHeader_contactAddr" value="<%=poHeader.getContactAddr()%>"/>
            <tsa:hidden name="PoHeader_shipViaCode" value="<%=poHeader.getShipViaCode()%>"/>
            <tsa:hidden name="PoHeader_fobCode" value="<%=poHeader.getFobCode()%>"/>
            <tsa:hidden name="PoHeader_termsCode" value="<%=poHeader.getTermsCode()%>"/>
            <tsa:hidden name="PoHeader_vendorClass" value="<%=poHeader.getVendorClass()%>"/>
            <tsa:hidden name="PoHeader_inspectionReqd" value="<%=poHeader.getInspectionReqd()%>"/>
          </td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "po-sup-contactName", s_po_type)%>>
          <td align=right><tsa:label labelName="po-sup-contactName" defaultString="Contact Name" noinstance="true" />:&nbsp;</td>
          <td><tsa:input labelName="po-sup-contactName" type="midtext" name="PoHeader_contactName" maxLength="40" value="<%=poHeader.getContactName()%>" readonly="true" disabled="true" /></td>
        </tr>
        <tsa:tr field="po-sup-phone">
		  <td align=right><tsa:label labelName="po-sup-phone" defaultString="Phone" noinstance="true" />:&nbsp;</TD>
          <td><tsa:input labelName="po-sup-phone" type="midtext" name="PoHeader_contactPhone" maxLength="30" value="<%=poHeader.getContactPhone()%>" readonly="true" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="po-sup-mobilePhone">
		  <td align=right><tsa:label labelName="po-sup-mobilePhone" defaultString="Mobile Phone" noinstance="true" />:&nbsp;</TD>
          <td><tsa:input labelName="po-sup-mobilePhone" type="midtext" name="PoHeader_contactMobilePhone" maxLength="30" value="<%=poHeader.getContactMobilePhone()%>" readonly="true" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="cnt-email">
          <td align="right" nowrap><tsa:label labelName="cnt-email" defaultString="Contact Email" noinstance="true" />:&nbsp;</td>
          <td width="50%"><tsa:input labelName="cnt-email" type="maxtext" name="PoHeader_email" maxLength="50" value="<%=s_contactEmail%>" onfocus="this.blur()" disabled="true" /></td>
        </tsa:tr>
        <tsa:tr field="cnt-info1">
			<td align=right><tsa:label labelName="cnt-info1" defaultString="Info 1" checkRequired="true" noinstance="true" />:</td>
			<td  colspan=3><tsa:input labelName="cnt-info1" type="maxtext" name="Contact_info1" tabIndex="9" maxLength="50" value="<%=s_info1%>" readonly="true" disabled="true" /></td>
		</tsa:tr>
		<tsa:tr field="cnt-info2">
			<td align=right><tsa:label labelName="cnt-info2" defaultString="Info 2" checkRequired="true" noinstance="true" />:</td>
			<td  colspan=3><tsa:input labelName="cnt-info2" type="maxtext" name="Contact_info2" tabIndex="10" maxLength="50" value="<%=s_info2%>" readonly="true" disabled="true" /></td>
		</tsa:tr>
		<%	String s_fax_number = "";
        	if (!HiltonUtility.isEmpty(s_vendor_id)) {
        		Object vendorEntity = VendorManager.getInstance().getVendor(oid, s_vendor_id);
        		if(vendorEntity instanceof Vendor)
        			s_fax_number = ((Vendor)vendorEntity).getFaxNumber();
        	} %>
        <tsa:tr field="po-sup-faxNumber">
			<td align=right><tsa:label labelName="sup-faxNumber" defaultString="Fax Number" noinstance="true" />:&nbsp;</TD>
			<td><tsa:input labelName="sup-faxNumber" type="maxtext" name="PoHeader_faxNumber" tabIndex="11" maxLength="50" value="<%=s_fax_number%>" readonly="true" disabled="true" /></td>
		</tsa:tr>
        <tr><td colspan="2"><br></td></tr>
        <tr <%=HtmlWriter.isVisible(oid, "po-poSendMethod", s_po_type)%>>
          <td align="right" nowrap><tsa:label labelName="po-poSendMethod" defaultString="PO Send Method" checkRequired="true" noinstance="true" />:&nbsp;</td>
          <td nowrap><select name="PoHeader_ediOrder_option" size="1" onchange="checkBlanketAction();">
                        <option <% if (s_cxmlAction.equals("P")) {%> SELECTED <%}%> value="P">Print PO</option>
                        <option <% if (s_cxmlAction.equals("F")) {%> SELECTED <%}%> value="F">Fax PO</option>
                        <option <% if (s_cxmlAction.equals("E")) {%> SELECTED <%}%> value="E">EDI PO</option>
                        <option <% if (s_cxmlAction.equals("M")) {%> SELECTED <%}%> value="M">Email PO</option>
                        <option <% if (s_cxmlAction.equals("X")) {%> SELECTED <%}%> value="X">XML Order</option>
                    </select><tsa:hidden name="PoHeader_ediOrder" value="<%=s_cxmlAction%>"/></td>
        </tr>
        </table>
      </td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
  <td rowspan="3" align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
<%	} %>
    </tr>

<%	if (s_po_type.equals("CT"))
		{
			List subContractorList = (List) request.getAttribute("subContractorList");
%>
    <tr height="100px">
    	<td colspan="2" valign="top" align=center width=100%>
    		<br>
			<table border=1 cellspacing=0 cellpadding=0 width=515px class=browseHdr>
			<tr>
				<td>
					<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
					<tr>
						<td width=80% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="po-sup-subContractors" defaultString="Sub-Contractors" noinstance="true" /></b></td>
<%		if (user.isAnAdminBuyer() || user.getUserId().equals(s_buyer_code)) {%>
						<td align="right"><a href="javascript: addSubContractor(); void(0);"><tsa:label labelName="add-new" defaultString="Add new" /></a></td>
						<td align="center"><a href="javascript: addSubContractor(); void(0);"><img src="<%=contextPath%>/images/cmd_edit.gif" border="0" alt="Click here to add <tsa:label labelName="po-sup-subContractors" defaultString="Sub-Contractors" noinstance="true" />"></a></td>
<%		} %>
					</tr>
					</table>
				</td>
	    	</tr>
			<tr>
				<td>
					<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (subContractorList != null && subContractorList.size() > 0)
			{
				for (int i = 0; i < subContractorList.size(); i++)
				{
					SubContractor subContractor = (SubContractor) subContractorList.get(i);
%>
					<tr height="18px">
					<%	if (propertiesManager.getProperty("PO OPTIONS","RESTRICTSUPPLIERAFFILIATIONS","N").equalsIgnoreCase("Y")) {%>
						<td width="50%" class="browseRow">
							<%=subContractor.getComp_id().getSubName()%>
						</td>
					<%	} else {%>
						<td width="50%" class="browseRow">
							<a href="javascript: updateSubContractor('<%=subContractor.getComp_id().getSubName()%>'); void(0);"><%=subContractor.getComp_id().getSubName()%></a>
						</td>
					<%	}%>
						</td>
						<td width="30%" class="browseRow">
							<%=subContractor.getCity()%>&nbsp;<%=subContractor.getState()%>
						</td>
						<td align="center" class="browseRow"><a href="javascript: deleteSubContractor('<%=subContractor.getComp_id().getSubName()%>'); void(0);"><img src="<%=contextPath%>/images/delete.gif" border="0" alt="Delete <%=subContractor.getComp_id().getSubName()%>"></a></td>
					</tr>
<%			}
			}
			else
			{ %>
					<tr><td class=browseRow><tsa:label labelName="po-sup-nosubContractors" defaultString="There are no sub-contractors associated with this contract." /></td></tr>
<%		} %>
					</table>
				</td>
			</tr>
			</table>
    	</td>
    </tr>
<%	}
		String supplierInfoUrl = propertiesManager.getProperty("PO OPTIONS", "SupplierInfoUrl", "");
		if (!HiltonUtility.isEmpty(supplierInfoUrl)) {%>
	<tr>
		<td align=center width=100% colspan=2 vAlign=top>
	    	<table border=0 cellpadding=0 cellspacing=0 width=100%>
	    	<tr><td align=center width=100%><a href="<%=supplierInfoUrl%>" target="_blank"><%=supplierInfoUrl%></a></td></tr>
			</table>
		</td>
	</tr>
<%	}%>
    </table>
  </td>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width=<%=formEntryWidth%>>
<tr>
  <td width="50%" align="center"><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoHeaderUpdate;PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></td>
  <td width="50%" align="center"><a href="javascript: doSubmit('/orders/po_summary.jsp', 'PoRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;
  var ponumber = "<%=s_po_number%>";
  var fiscalyear = "<%=poHeader.getFiscalYear()%>";
  var currentpage = "<%=s_current_page%>";
  var currentmethod = "<%=s_current_method%>";
  var currentprocessmethod = "<%=s_current_process_method%>";

  	function thisLoad()
  	{
	    f_StartIt();
		<%	if (!bAllowEdit) { %>
			checkInputSettings();
		<%	} else if(bAllowEdit) { %>
			setInvalidFields("<%=invalidFields%>");
		<%	}%>
		<% if (s_po_status.compareTo(DocumentStatus.PO_AWARDED) >= 0) {%>
			if (frm.PoHeader_vendorId)
				allowInputEdit(frm.PoHeader_vendorId, false);
		<%	}%>
  	}

	function checkBlanketAction()
    {
      frm.PoHeader_ediOrder.value = frm.PoHeader_ediOrder_option.value;
    }

    function addSubContractor()
    {
<%	if (propertiesManager.getProperty("PO OPTIONS","RESTRICTSUPPLIERAFFILIATIONS","N").equalsIgnoreCase("Y")) {%>
		if (isEmpty(frm.PoHeader_vendorId.value)) {
			alert("You must select a primary supplier before assigning <tsa:label labelName="po-sup-subContractors" defaultString="Sub-Contractors" noinstance="true" />.");
			return false;
		} else if (ponumber == 'N/A') {
			alert("You must save the order before assigning <tsa:label labelName="po-sup-subContractors" defaultString="Sub-Contractors" noinstance="true" />.");
			return false;
		} else {
	    	popupParameters = "PoHeader_poNumber=" + frm.PoHeader_poNumber.value + ";";
	    	popupParameters = popupParameters + "PoHeader_releaseNumber=" + frm.PoHeader_releaseNumber.value + ";";
	    	popupParameters = popupParameters + "PoHeader_contractNo=" + frm.PoHeader_contractNo.value + ";";
	    	popupParameters = popupParameters + "colname=VendorAffiliate_id_vendorId;operator==;filter_txt=" + frm.PoHeader_vendorId.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
			popupParameters = popupParameters + "browseName=vendoraffiliate-subcontractor;allowBrowse=" + frm.allowBrowse.value;
	    	popupParameters = popupParameters + ";SubContractor_poNumber=<%=poHeader.getPoNumber()%>;SubContractor_releaseNumber=<%=poHeader.getReleaseNumber()%>;SubContractor_contractno=<%=poHeader.getContractNo()%>";
	    	popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=bd_ic_po_header%>;PoHeader_vendorId=" + frm.PoHeader_vendorId.value;
			popupParameters = popupParameters + ";PoHeader_vendContactCode=" + frm.PoHeader_vendContactCode.value;
			popupParameters = popupParameters + ";PoHeader_contactName=" + frm.PoHeader_contactName.value;
			popupParameters = popupParameters + ";PoHeader_ediOrder=" + frm.PoHeader_ediOrder.value;
	    	doSubmitToPopup('/browse/browse_popup.jsp', 'PoHeaderUpdate;BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
		}
<%	} else {%>
		if (ponumber == 'N/A') {
			alert("You must save the order before assigning <tsa:label labelName="po-sup-subContractors" defaultString="Sub-Contractors" noinstance="true" />.");
			return false;
		} else {
	    	frm.formAction.value = "CREATE";
	    	popupParameters = "SubContractor_poNumber=<%=poHeader.getPoNumber()%>;SubContractor_releaseNumber=<%=poHeader.getReleaseNumber()%>;SubContractor_contractno=<%=poHeader.getContractNo()%>";
	    	popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=bd_ic_po_header%>;PoHeader_vendorId=" + frm.PoHeader_vendorId.value;
			popupParameters = popupParameters + ";PoHeader_vendContactCode=" + frm.PoHeader_vendContactCode.value;
			popupParameters = popupParameters + ";PoHeader_contactName=" + frm.PoHeader_contactName.value;
			popupParameters = popupParameters + ";PoHeader_ediOrder=" + frm.PoHeader_ediOrder.value;
			doSubmitToPopup('/orders/po_sub_contractor.jsp', 'PoHeaderUpdate', 'WIDTH=550px', 'HEIGHT=450px');
		}
<%	}%>
    }

    function updateSubContractor(subName)
    {
    	frm.formAction.value = "UPDATE";
		popupParameters = "SubContractor_poNumber=<%=poHeader.getPoNumber()%>;SubContractor_releaseNumber=<%=poHeader.getReleaseNumber()%>;SubContractor_contractno=<%=poHeader.getContractNo()%>;SubContractor_subName=" + subName;
		popupParameters = popupParameters + ";PoHeader_icPoHeader=<%=bd_ic_po_header%>;PoHeader_vendorId=" + frm.PoHeader_vendorId.value;
		popupParameters = popupParameters + ";PoHeader_vendContactCode=" + frm.PoHeader_vendContactCode.value;
		popupParameters = popupParameters + ";PoHeader_contactName=" + frm.PoHeader_contactName.value;
		popupParameters = popupParameters + ";PoHeader_ediOrder=" + frm.PoHeader_ediOrder.value;

		doSubmitToPopup('/orders/po_sub_contractor.jsp', 'PoHeaderUpdate;SubContractorRetrieveById', 'WIDTH=550px', 'HEIGHT=450px');
    }

	function updatePromiseDate()
    {
    	popupParameters = popupParameters + ";updatePromisedDate=Y";
    }

    function deleteSubContractor(subName)
    {
    	if (confirm("Delete " + subName + " from this contract?"))
    	{
    		frm.SubContractor_subName.value = subName;
			doSubmit('/orders/po_supplier.jsp', 'PoHeaderUpdate;SubContractorDeleteById;PoHeaderVendorRetrieveById');
		}
    }

    function browseSupplier(formField)
	{
		notEnterContact();
		popupParameters = popupParameters + "formField=" + formField +";browseName=vendor;allowBrowse=" + frm.allowBrowse.value;

<%	if (oid.equalsIgnoreCase("qri06p")) {	%>
			var country = "US";
<%		if (!HiltonUtility.isEmpty(poHeader.getUdf1Code())) {	%>
				country = "<%=poHeader.getUdf1Code()%>";
<%		}	%>
			popupParameters = popupParameters + ";supplierCountry=" + country;
<%	}	%>

		doSubmitToPopup('/browse/supplier_filter_options.jsp', 'VendorRetrieveBrowseOptions', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function invalidContact()
	{
		alert("The contact code entered currently does not exist for this supplier.  Please click on the link to select a valid contact code from the list.");
		frm.PoHeader_vendContactCode.value = "";
	}

	function addTempVendor()
	{
		var newInputField = "<input type=hidden name='tempVendorId' value='" + frm.PoHeader_vendorId.value + "'>";
		newInputField = newInputField + "<input type=hidden name='tempVendorName' value='" + frm.Address_addressLine1.value + "'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_info.jsp', 'DoNothing');
	}

	function enterContact()
	{
		frm.PoHeader_vendContactCode.value = "non-standard";
		frm.PoHeader_email.value = "";

		allowInputEdit(frm.PoHeader_vendContactCode, false);
		allowInputEdit(frm.PoHeader_contactName, true);
		frm.PoHeader_contactName.focus();
		allowInputEdit(frm.PoHeader_contactPhone, true);
		allowInputEdit(frm.PoHeader_contactMobilePhone, true);
	}

	function notEnterContact()
	{
		allowInputEdit(frm.PoHeader_vendContactCode, true);
		allowInputEdit(frm.PoHeader_contactName, false);
		allowInputEdit(frm.PoHeader_contactPhone, false);
		allowInputEdit(frm.PoHeader_contactMobilePhone, false);
	}

// End Hide script -->
</SCRIPT>
