<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	List contractsList = (List) request.getAttribute("contractsList");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));

	String	s_current_process = "CONTRACTS";
	String	s_current_page = "/admin/supplier/supplier_contracts.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	String insCategoryLevelActive = PropertiesManager.getInstance(oid).getProperty("VENDOR", "INSCATLVLACTIVE", "N");
%>

<tsa:hidden name="VendorAccount_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorInsurance_icPoHeader" value=""/>
<tsa:hidden name="VendorInsurance_contNumber" value=""/>
<tsa:hidden name="Address_addressCode" value=""/>
<tsa:hidden name="currentPage" value="/admin/supplier/supplier_info.jsp"/>
<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendorName%>&nbsp;</div>
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

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td width="680px" align="center" valign="top">
		<table border=1 cellspacing=0 cellpadding=0 width="500px" class=browseHdr align="center">
		<tr>
			<td align="center">
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contract", "Contract #")%></b></td>
					<!--<td width=15% height=18px class=browseHdr>&nbsp;<b>Type</b></td>-->
					<td width="20%" height=18px class=browseHdr>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></b></td>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b>Effective Date</b></td>
					<td width="20%" height=18px class=browseHdr>&nbsp;<b>Expiration Date</b></td>
					<td width="15%" height=18px class=browseHdr align="right">&nbsp;<b>Amount</b></td>
					<!--<td width=15% height=18px class=browseHdr>&nbsp;<b>Requestor</b></td>-->
					<td width="5%" height=18px class=browseHdr>&nbsp;</td>
				</tr>
				</table>
			</td>
	    </tr>
		<tr>
			<td>
				<div id="Contracts">
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (contractsList != null && contractsList.size() > 0)
		{
			for (int i = 0; i < contractsList.size(); i++)
			{
				PoHeader poHeader = (PoHeader) contractsList.get(i);
				String	s_buyer_code = poHeader.getBuyerCode();
				UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
%>
				<tr>
					<td width="20%"><a href="javascript: selectMe('<%=poHeader.getIcPoHeader()%>','<%=poHeader.getPoNumber()%>','<%=poHeader.getIclLevel()%>'); void(0);"><%=poHeader.getPoNumber()%></a></td>
					<!--<td width="15%"><%=OrderType.toString(poHeader.getPoType())%></td>-->
					<td width="20%"><%=DocumentStatus.toString(poHeader.getStatus())%></td>
					<td width="20%"><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%></td>
					<td width="20%"><%=HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), oid, userDateFormat)%></td>
					<td width="15%" align="right">$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></td>
					<!--<td width="15%"><%=buyer.getDisplayName()%></td>-->
					<td width="5%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6"><b>Description:</b>&nbsp;<%=poHeader.getInternalComments()%><br><br></td>
				</tr>
<%		}
		}
		else
		{
%>
				<tr><td class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-nocontractsmsg", "There are no contracts associated with this supplier.")%></td></tr>
<%	} %>
				</table>
				</div>
			</td>
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

	function selectMe(icPoHeader, contnumber, iclLevel)
	{
	<%	if (insCategoryLevelActive.equalsIgnoreCase("Y")) { %>
	var newInputField = "<input type=hidden name='PoHeader_poNumber' value='" + contnumber + "'>" +
			"<input type=hidden name='InsCategoryLevel_iclLevel' value='" + iclLevel + "'>" +
			"<input type=hidden name='VendorInsuranceDefault_vendorId' value='${esapi:encodeForJavaScript(Vendor_vendorId)}'>";
		setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_contract.jsp', 'VendorInsuranceDefaultInsCategoryLevelValidate');
	<%	} else { %>
		frm.VendorInsurance_icPoHeader.value = icPoHeader;
		frm.VendorInsurance_contNumber.value = contnumber;
		doSubmit('/admin/supplier/supplier_ins_contract.jsp', 'VendorInsuranceRetrieveById');
	<%	} %>
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