<tsa:hidden name="Vendor_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendor.getVendorName()%>"/>
<tsa:hidden name="Contact_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="Address_addressType" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<!--  needed for supplier certification types  -->
<tsa:hidden name="StdTable_tableType" value="VSBA"/>
<!--  needed for supplier documents  -->
<tsa:hidden name="VendorDocument_icRfqHeader" value="0"/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="allowEdit" value="Y"/>
<tsa:hidden name="returnPage" value="/admin/supplier/supplier_info.jsp"/>
<tsa:hidden name="returnHandler" value="VendorRetrieveById"/>
<tsa:hidden name="Vendor_buyer" value="<%=vendor_buyer%>"/>
<!--  needed for temp vendor add from PO process  -->
<%	String icPoHeader = HiltonUtility.ckNull((String) request.getAttribute("PoHeader_icPoHeader"));	%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>