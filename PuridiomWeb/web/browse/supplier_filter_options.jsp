<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/actb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/common.js"></SCRIPT>

<%
	String	formField = HiltonUtility.ckNull((String) request.getAttribute("formField"));
	String	commodity = HiltonUtility.ckNull((String) request.getAttribute("supplierCommodity"));
	String	supplierclass = HiltonUtility.ckNull((String) request.getAttribute("supplierClass"));
	String	suppliercountry = HiltonUtility.ckNull((String) request.getAttribute("supplierCountry"));
	String	parentVendorId = HiltonUtility.ckNull((String) request.getAttribute("parentVendorId"));
	String	originalBrowseName = HiltonUtility.ckNull((String) request.getAttribute("browseName"));

	Object vendorNameListObject = request.getAttribute("vendorNameList");
	Object vendorClassListObject = request.getAttribute("vendorClassList");
	List vendorNameList = new ArrayList();
	List vendorClassList = new ArrayList();
	if (vendorNameListObject instanceof List) {
		vendorNameList = (List) vendorNameListObject;
	} else if (vendorNameListObject instanceof String) {
		vendorNameList.add(vendorNameListObject);
	}

	if (vendorClassListObject instanceof List) {
		vendorClassList = (List) vendorClassListObject;
	} else if (vendorClassListObject instanceof String) {
		vendorNameList.add(vendorNameListObject);
	}
%>
<div id="pageForm" style="display:none;visibility:hidden;">
<tsa:hidden name="formField" value="<%=formField%>"/>
<tsa:hidden name="fromPage" value="<%=formField%>"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="allowBrowse" value=""/>
<tsa:hidden name="tableType" value=""/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td valign="top" width="135px" height="30px">
    <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
    <tr>
      <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class="hdr12" valign="middle">
        <div style="margin-left:10px; margin-right:10px" class="hdr12">Supplier Search Options</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign="bottom" align="right" height="30px">
    <table cellpadding="0" cellspacing="0" border="0">
    <tr>
      <td width="1000px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<table width="680px" border="0" cellpadding="1" cellspacing="1">
<tr><td colspan="2" align="center">Fill in any of the following criteria to help narrow your search.</td></tr>
<tr><td colspan="2"><br></td></tr>
<tr>
	<td align="right" width="50%"><b>Supplier ID:</b>&nbsp;</td>
	<td width="50%"><input type="text" name="as_supplierId" value="" onchange="upperCase(this);"></td>
</tr>
<tr>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorName", "Supplier Name")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_supplierName" name="as_supplierName" value="" size="40"></td>
</tr>
<tr>
	<td align="right" width="50%"><a href="javascript: browseMe('commodity');"><b>Commodity:</b></a></td>
	<td width="50%"><input type="text" name="as_supplierCommodity" value="<%=headerEncoder.encodeForHTMLAttribute(commodity)%>" onchange="upperCase(this);"></td>
</tr>
<tr>
	<td align="right" width="50%"><a href="javascript: browseMe('vendorclass');"><b>Socio-Economic Class:</b></a>&nbsp;</td>
	<td width="50%"><input type="text" id="as_supplierClass" name="as_supplierClass" value="<%=headerEncoder.encodeForHTMLAttribute(supplierclass)%>" onchange="upperCase(this);"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "terms")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "terms", "Terms")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_terms" name="as_supplierTerms" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "notes")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "notes", "Notes")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_notes" name="as_supplierNotes" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "apReference")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "apReference", "AP Reference")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" name="as_apReference" value="" onchange="upperCase(this);"></td>
</tr>
<tr>
	<td align="right" width="50%"><b>Country:</b>&nbsp;</td>
	<td width="50%"><input type="text" name="as_supplierCountry" value="<%=headerEncoder.encodeForHTMLAttribute(suppliercountry)%>" onchange="upperCase(this);"></td>
</tr>
<tr>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "city", "City")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_city" name="as_supplierCity" value="" size="40"></td>
</tr>
<tr>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "state", "State")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_state" name="as_supplierState" value="" size="40"></td>
</tr>
<tr>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "zip", "Zip/Postal")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_zip" name="as_supplierZip" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN01")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN01", "Supplier UDF 1")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf1" name="as_supplierUdf1" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN02")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN02", "Supplier UDF 2")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf2" name="as_supplierUdf2" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN03")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN03", "Supplier UDF 3")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf3" name="as_supplierUdf3" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN04")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN04", "Supplier UDF 4")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf4" name="as_supplierUdf4" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN05")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN05", "Supplier UDF 5")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf5" name="as_supplierUdf5" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN06")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN06", "Supplier UDF 6")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf6" name="as_supplierUdf6" value="" size="40"></td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "VN07")%>>
	<td align="right" width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "VN07", "Supplier UDF 7")%>:</b>&nbsp;</td>
	<td width="50%"><input type="text" id="as_udf7" name="as_supplierUdf7" value="" size="40"></td>
</tr>

</table>

<br>

<table width="680px" border="0">
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: search(); void(0);">Search</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Cancel</a></div></td>
</tr>
</table>

</div>
<div id="pageLoading" style="display:block;visibility:visible;">
	<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr><td width=100% align=center valign=top><br><b>Loading Page... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td></tr>
	</table>
</div>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.allowBrowse.value = window.parent.frm.allowBrowse.value;
	document.body.style.cursor = "wait";

	var browseAffiliates = <%=originalBrowseName.equals("vendor-affiliates") && !HiltonUtility.isEmpty(parentVendorId)%>;

<%	if ((vendorNameList != null) && (vendorNameList.size() <= 5000)) { %>
		var nameArray = new Array();
		var i = 0;
<%		for (int i = 0; i < vendorNameList.size(); i++)
			{
				if(!HiltonUtility.isEmpty((String)vendorNameList.get(i)))
				{
					String tmpVendor = (String)vendorNameList.get(i);
					if (tmpVendor.indexOf("\"") >= 0)
					{
						continue;
					}
%>
					nameArray[i] = "<%=tmpVendor%>";
					i++;
				<%}
		} %>
		var option1 = new actb(document.getElementById('as_supplierName'), nameArray);
<%	}
		if (vendorClassList != null) { %>
		var classArray = new Array();
		var i = 0;
<%		for (int i = 0; i < vendorClassList.size(); i++)
			{ %>
				classArray[i] = "<%=vendorClassList.get(i)%>";
				i++;
<%		} %>
			var option2 = new actb(document.getElementById('as_supplierClass'), classArray);
<%	} %>

	document.body.style.cursor = "";

	function search()
	{
		setFilterOptions();

		if (frm.as_supplierCommodity.value.length > 0)
		{
			browseMe('vendor_comm_rel');
		}
		else
		{
			if(frm.formField.value == "InvoiceVendor_vendorId")
			{
				browseMe('consolidatedvendor');
			}
			else if (browseAffiliates)
			{
				setOriginalFilter("VendorAffiliate_id_vendorId", "=", "<%=parentVendorId%>");
				browseMe('vendor-affiliates');
			}
			else
			{
				browseMe('vendor');
			}
		}
	}

	function browseMe(x)
	{
		frm.browseName.value = x;
		if (x == 'commodity')
		{
			frm.formField.value = 'as_supplierCommodity';
		}
		else if (x == 'vendorclass')
		{
			frm.browseName.value = 'stdtable';
			frm.formField.value = 'as_supplierClass';
			frm.tableType.value = 'VSBA';
			setOriginalFilter("StdTable_id_tableType", "=", "VSBA");
		}

		doSubmit('browse/browse_popup.jsp', 'BrowseRetrieve');
	}

	function setFilterOptions()
	{
		var id = frm.as_supplierId.value;
		var name = frm.as_supplierName.value;
		var commodity = frm.as_supplierCommodity.value;
		var supplierclass = frm.as_supplierClass.value;
		var apreference = frm.as_apReference.value;
		var country = frm.as_supplierCountry.value;
		var city = frm.as_supplierCity.value;
		var state = frm.as_supplierState.value;
		var zip = frm.as_supplierZip.value;
		var udf1 = frm.as_supplierUdf1.value;
		var udf2 = frm.as_supplierUdf2.value;
		var udf3 = frm.as_supplierUdf3.value;
		var udf4 = frm.as_supplierUdf4.value;
		var udf5 = frm.as_supplierUdf5.value;
		var udf6 = frm.as_supplierUdf6.value;
		var udf7 = frm.as_supplierUdf7.value;
		var terms = frm.as_supplierTerms.value;
		var notes = frm.as_supplierNotes.value;
		setOriginalFilter("Vendor_vendorId", "=", id);
		setOriginalFilter("Vendor_vendorName", "LIKE", name);
		setOriginalFilter("VendorCommRel_id_commodityCode", "LIKE", commodity);
		setOriginalFilter("Vendor_vendorClass", "=", supplierclass);
		setOriginalFilter("Vendor_apReference", "=", apreference);
		setOriginalFilter("Address_country", "=", country);
		setOriginalFilter("Address_city", "=", city);
		setOriginalFilter("Address_state", "=", state);
		setOriginalFilter("Address_postalCode", "=", zip);
		setOriginalFilter("Vendor_vendUdf1", "=", udf1);
		setOriginalFilter("Vendor_vendUdf2", "=", udf2);
		setOriginalFilter("Vendor_vendUdf3", "=", udf3);
		setOriginalFilter("Vendor_vendUdf4", "=", udf4);
		setOriginalFilter("Vendor_vendUdf5", "=", udf5);
		setOriginalFilter("Vendor_vendUdf6", "=", udf6);
		setOriginalFilter("Vendor_vendUdf7", "=", udf7);
		setOriginalFilter("Vendor_vendTerms", "=", terms);
		setOriginalFilter("Vendor_notes", "LIKE", notes);
	}

	hideArea("pageLoading");
	displayArea("pageForm");

// End Hide script -->
</SCRIPT>