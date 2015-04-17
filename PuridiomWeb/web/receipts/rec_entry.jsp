<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.InspectionLevel" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsagate.foundation.utility.UniqueKeyGenerator"%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	priceDecimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	shipToInv = "N";
	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	if (receiptHeader == null) {
		receiptHeader = new ReceiptHeader();
	} else {
		shipToInv = receiptHeader.getShipToInv();
	}
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	} else {
		shipToInv = poHeader.getShipToInv();
	}
	PoLine poLine = (PoLine) request.getAttribute("poLine");
	if (poLine == null) {
		poLine = new PoLine();
	}
	Address vendor = (Address) poHeader.getVendorAddress();
	if (vendor == null) {
		vendor = new Address();
	}
	Address shipTo = (Address) poHeader.getShipToAddress();
	if (shipTo == null) {
		shipTo = new Address();
	} else {
		shipToInv = shipTo.getInventory();
	}
	String	receiptNumber = "N/A";
	String	poNumber = "N/A";
	String	receiptMethod = (String) request.getAttribute("receiptMethod");
	if (HiltonUtility.isEmpty(receiptMethod)) {
		receiptMethod = "ReceiveFromNothing";
	}
	String	createAction = "SAVE";
	BigDecimal	bd_zero = new BigDecimal(0);
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=receiptHeader.getIcRecHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptHeader.getReceiptNumber()%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=receiptHeader.getReceiptStatus()%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=receiptHeader.getFiscalYear()%>"/>
<tsa:hidden name="ReceiptHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="O"/>
<tsa:hidden name="ReceiptHeader_shipToCode" value="<%=poHeader.getShipToCode()%>"/>
<tsa:hidden name="ReceiptHeader_shipToInv" value="<%=shipToInv%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_fiscalYear" value="<%=poHeader.getFiscalYear()%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=poHeader.getPoType()%>"/>
<tsa:hidden name="PoHeader_shipToInv" value="<%=shipToInv%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="createAction" value="<%=createAction%>"/>
<tsa:hidden name="receiptAction" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="InvProperty_flag" value="I"/>
<tsa:hidden name="count_ini" value = "0"/>
<tsa:hidden name="count_item" value= "0"/>
<tsa:hidden name="count_aux" value= "0"/>
<tsa:hidden name="InvBinLocation_tempIc" value="<%=UniqueKeyGenerator.getInstance().getUniqueKey().toString()%>"/>
<tsa:hidden name="Contact_info1" value=""/>
<tsa:hidden name="Contact_info2" value=""/>
<tsa:hidden name="Contact_faxNumber" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt/Order Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td nowrap align=right><b>Order #:</b></td>
			<td width=100px><%=poNumber%></td>
		</tr>
		<tr>
			<td align=right><b>Receipt #:</b></td>
			<td width=100px><%=receiptNumber%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=receiptInfoTable border=1 cellspacing=0 cellpadding=0 width=605px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Receipt Information</td>
				</tr>
				<tr>
					<td class=browseRow align=center>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td align=center valign=top>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr <%=HtmlWriter.isVisible(oid, "po-supplier")%>>
									<td align="right" nowrap><a href="javascript: browseSupplier('PoHeader_vendorId'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-supplier", "Supplier")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-supplier", "Supplier")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-supplier", "Supplier", true)%></a>:&nbsp;</td>
									<td nowrap>
										<input type="text" name="PoHeader_vendorId" tabindex=1 size="15" maxlength="15" value="" onchange="upperCase(this); getNewInfo('vendor', this);">
		<%//	if ( bTempVendor && role.getAccessRights("VEND") == 99 ) {	%>
					<!--a href="javascript: addTempVendor(); void(0);"><img src="<%=contextPath%>/images/add.gif" alt="Click here to add this supplier to Puridiom." border="0"></a-->
		<%	//} else {	%> (Code)<%	//}	%>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine1")%>>
									<td align="right" nowrap><a href="javascript: browseContactAddress('PoHeader_vendContactCode', frm.PoHeader_vendorId.value); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine1", "Company Name")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine1", "Company Name")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine1", "Company Name")%></a>:&nbsp;</td>
									<td><input type="text" name="Address_addressLine1" size="20" maxlength="40" value="<%=vendor.getAddressLine1()%>" onfocus="this.blur();" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine2")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine2", "Address 2")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_addressLine2" size="20" maxlength="40" value="<%=vendor.getAddressLine2()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine3")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine3", "Address 3")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_addressLine3" size="20" maxlength="40" value="<%=vendor.getAddressLine3()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine4")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-addressLine4", "Address 4")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_addressLine4" size="20" maxlength="40" value="<%=vendor.getAddressLine4()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-city")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-city", "City")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_city" size="20" maxlength="30" value="<%=vendor.getCity()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-state")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-state", "State")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_state" size="20" maxlength="15" value="<%=vendor.getState()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-zip")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-zip", "Postal Code")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_postalCode" size="20" maxlength="15" value="<%=vendor.getPostalCode()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-country")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-country", "Country")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="Address_country" size="20" maxlength="25" value="<%=vendor.getCountry()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr>
									<td align=right nowrap>&nbsp;</td>
									<td width=50%>
										<tsa:hidden name="Address_inventory_message" value="Inventory Location"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-attention")%>>
									<td align="right" nowrap><a href="javascript: browseContactAddress('PoHeader_vendContactCode', frm.PoHeader_vendorId.value); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-attention", "Attention")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-attention", "Attention")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-attention", "Attention", true)%></a>:&nbsp;</td>
									<td>
										<input type="text" name="PoHeader_vendContactCode" tabindex=10 size="20" maxlength="15" value="" onchange="getNewInfo('contact', this);">
										<tsa:hidden name="PoHeader_contactAddr" value="<%=poHeader.getContactAddr()%>"/>
										<tsa:hidden name="PoHeader_shipViaCode" value="<%=poHeader.getShipViaCode()%>"/>
										<tsa:hidden name="PoHeader_fobCode" value="<%=poHeader.getFobCode()%>"/>
										<tsa:hidden name="PoHeader_termsCode" value="<%=poHeader.getTermsCode()%>"/>
										<tsa:hidden name="PoHeader_vendorClass" value="<%=poHeader.getVendorClass()%>"/>
										<tsa:hidden name="PoHeader_inspectionReqd" value="<%=poHeader.getInspectionReqd()%>"/>
										<tsa:hidden name="PoHeader_ediOrder_option" value="<%=poHeader.getEdiOrder()%>"/>
										<tsa:hidden name="PoHeader_ediOrder" value="<%=poHeader.getEdiOrder()%>"/>
										<tsa:hidden name="VendorInsurance_vendorId" value="<%=poHeader.getVendorId()%>"/>
									</td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-attention")%>>
									<td>&nbsp;</td>
									<td><input type="text" name="PoHeader_contactName" size="20" maxlength="40" value="<%=poHeader.getContactName()%>" readonly disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-phone")%>>
									<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-phone", "Phone")%>:&nbsp;</TD>
									<td><input type="text" name="PoHeader_contactPhone" size="20" maxlength="30" value="<%=poHeader.getContactPhone()%>" readonly disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-sup-mobilePhone")%>>
									<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-sup-mobilePhone", "Mobile Phone")%>:&nbsp;</TD>
									<td><input type="text" name="PoHeader_contactMobilePhone" size="20" maxlength="30" value="<%=poHeader.getContactMobilePhone()%>" readonly disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "cnt-email")%>>
									<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cnt-email", "Contact Email")%>:&nbsp;</td>
									<td width="50%"><input type="text" name="PoHeader_email" size="35" maxlength="50" value="<%//poHeader.getEmail()%>" onfocus="this.blur()" disabled></td>
								</tr>
								</table>
							</td>
							<td align=center valign=top class=browseRow>
								<table border=0 cellspacing=0 cellpadding=0 class=browseRow width=100%>
								<tr <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>>
									<td align=right nowrap width=50%><a href="javascript: browseLookup('PoHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCode", "Ship To")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCode", "Ship To")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCode", "Ship To", true)%></a>:&nbsp;</td>
									<td width=50%><input type=text name="PoHeader_shipToCode" tabindex=1 size=15 maxlength=15 value="<%=poHeader.getShipToCode()%>" onchange="upperCase(this); frm.ReceiptHeader_shipToCode.value = this.value; getNewShipToInfo(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine1")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-addressLine1", "Address 1")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_addressLine1" size=20 maxlength=40 value="<%=shipTo.getAddressLine1()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine2")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-addressLine2", "Address 2")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_addressLine2" size=20 maxlength=40 value="<%=shipTo.getAddressLine2()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine3")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-addressLine3", "Address 3")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_addressLine3" size=20 maxlength=40 value="<%=shipTo.getAddressLine3()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine4")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-addressLine4", "Address 4")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_addressLine4" size=20 maxlength=40 value="<%=shipTo.getAddressLine4()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-city")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-city", "City")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_city" size=20 maxlength=30 value="<%=shipTo.getCity()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-state")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-state", "State")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_state" size=20 maxlength=15 value="<%=shipTo.getState()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-zip")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-zip", "Zip")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_postalCode" size=20 maxlength=15 value="<%=shipTo.getPostalCode()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "po-shp-country")%>>
									<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-country", "Country")%>:&nbsp;</td>
									<td width=50%><input type=text name="asShipToAddress_country" size=20 maxlength=25 value="<%=shipTo.getCountry()%>" onfocus="this.blur()" disabled></td>
								</tr>
								<tr><td colspan=2><br></td></tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-numberOfPackages")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-numberOfPackages", "# of Packages", true)%>:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_pkgsReceived" value="<%=receiptHeader.getPkgsReceived().setScale(0, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" tabIndex=20 size=10 onchange="formatPkgQty()"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-packingSlip", "Packing Slip", true)%>:&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_packingSlip" value="<%=receiptHeader.getPackingSlip()%>" tabIndex=21 onchange="upperCase(this);" maxLength=15></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-carrierCode")%>>
									<td nowrap align=right><b><a href="javascript: browseStd('ReceiptHeader_carrierCode', 'CARR'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code")%> for this receipt or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-carrierCode", "Carrier Code", true)%>:</a>&nbsp;</b></td>
									<td nowrap><input type=text name="ReceiptHeader_carrierCode" value="<%=receiptHeader.getCarrierCode()%>" tabIndex=22 onchange="upperCase(this);" maxLength=15></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptDate")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptDate", "Receipt Date")%>:</b>&nbsp;</td>
									<td nowrap><input type=text name="ReceiptHeader_receiptDate" value="<%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>" tabindex=23></td>
									<td><a href="javascript: show_calendar('ReceiptHeader_receiptDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%>:&nbsp;</b></td>
									<td nowrap><tsa:hidden name="ReceiptHeader_receivedBy" value="<%=receiptHeader.getReceivedBy()%>"/><%=receiptHeader.getReceivedBy()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receivedBy")%>>
									<td nowrap align=right>&nbsp;</td>
									<td nowrap><%=UserManager.getInstance().getUser(oid, receiptHeader.getReceivedBy()).getDisplayName()%></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO01",poHeader.getPoType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO01", "UDF1", poHeader.getPoType())%>:&nbsp;</b></td>
									<td nowrap><input type=text name="PoHeader_udf1Code" value="<%=poHeader.getUdf1Code()%>" tabindex=24 onchange="upperCase(this);"></td></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO02",poHeader.getPoType())%>>
									<td align="right" nowrap>
<%	if (DictionaryManager.isLink(oid, "po-PO02")) {	%>
										<a href="javascript: browseStd('PoHeader_udf2Code', 'PO02'); void(0);" title="Click here to choose a value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO02", "UDF2",poHeader.getPoType())%> for this order or enter a value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO02", "UDF2",poHeader.getPoType(), true)%>:</a>&nbsp;
<%	} else {	%>
										<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO02", "UDF2", poHeader.getPoType(), true)%>:&nbsp;
<%	}	%>
									</td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
									<td <%=HtmlWriter.isVisible(oid, "po-PO02")%> >
										<select tabindex=23 name="PoHeader_udf2Code" size=1 onchange="setUdf2Code();">
<%
			if (HiltonUtility.isEmpty(poHeader.getUdf2Code())) {
				poHeader.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
			}
			Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
			Iterator inspectionIterator = inspectionLevels.keySet().iterator();
			String	inspectionLevelCode = "";
			String inspectionLevelName = "";
			while (inspectionIterator.hasNext())
			{
				inspectionLevelCode = (String) inspectionIterator.next();
				inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);

				if (inspectionLevelCode.equals("DEFAULT")) {
					continue;
				}
	%>
										<option value="<%=inspectionLevelCode%>" <%if (poHeader.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
	<%	}	%>
										</select>
									</td>
<%	} else { %>
									<td nowrap><input type=text name="PoHeader_udf2Code" value="<%=poHeader.getUdf2Code()%>" tabindex=25 onchange="upperCase(this);"></td></td>
<%	}	%>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO03",poHeader.getPoType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO03", "UDF3", poHeader.getPoType())%>:&nbsp;</b></td>
									<td nowrap><input type=text name="PoHeader_udf3Code" value="<%=poHeader.getUdf3Code()%>" tabindex=26 onchange="upperCase(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO04",poHeader.getPoType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO04", "UDF4", poHeader.getPoType())%>:&nbsp;</b></td>
									<td nowrap><input type=text name="PoHeader_udf4Code" value="<%=poHeader.getUdf4Code()%>" tabindex=27 onchange="upperCase(this);"></td>
								</tr>
								<tr <%=HtmlWriter.isVisible(oid, "rec-po-PO05",poHeader.getPoType())%>>
									<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-PO05", "UDF5", poHeader.getPoType())%>:&nbsp;</b></td>
									<td nowrap><input type=text name="PoHeader_udf5Code" value="<%=poHeader.getUdf5Code()%>" tabindex=28 onchange="upperCase(this);"></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan=2 align=center>
							<br>
								<table border=0 cellspacing=0 cellpadding=0 class=browseRow>
								<tr <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%>>
									<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNotes", "Notes", true)%>:</b>&nbsp;</td>
									<td><textarea name="ReceiptHeader_receiptNotes" cols=90 rows=3  onKeyDown="textCounter(this, 225);" onKeyUp="textCounter(this,225);" tabIndex=29>${esapi:encodeForHTML(receiptHeader.receiptNotes)}</textarea></td>
								</tr>
								</table>
							<td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<br>

		<table border=0 cellpadding=2 cellspacing=0 width=675px>
		<tr><td>&nbsp;<a href="javascript: viewAllItemDetails(); void(0);">View Details for All Items</a></td></tr>
		</table>

		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=670px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRowHdr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "rec-lineNumber")%> width=6% class=browseHdr nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-lineNumber", "Line #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-itemNumber")%> width=30% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-itemNumber", "Item/Part #")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-unitCost")%> width=20% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-unitCost", "Unit Cost")%></td>
					<!--td <%=HtmlWriter.isVisible(oid, "rec-quantityOrdered")%> width=20% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityOrdered", "Ordered")%></td-->
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityUom")%> width=5% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityUom", "UOM")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-quantityAccepted")%> width=11% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityAccepted", "Accepted")%></td>
					<td <%=HtmlWriter.isVisible(oid, "rec-receiptNotes")%> width=3% class=browseHdr nowrap>&nbsp;</td>
					<td width=3% class=browseHdr nowrap>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=right>
		<table border=0 cellpadding=1 cellspacing=0>
		<tr>
			<td align=right><a href="javascript: createNewItem(); void(0);"><img src="<%=contextPath%>/images/cmd_add_item.gif" border=0 alt="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-addItem", "Add Item", false)%>"></a></td>
			<td nowrap>&nbsp;<a href="javascript: createNewItem(); void(0);" title="Click here to add a new Receipt Item"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-addItem", "Add Item", false)%></a></td>
			<td width=10px>&nbsp;</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id="defaultItemDetails" style="visibility: hidden; display: none;">
	<div id="itemDetails" style="visibility: hidden; display: none;">
	<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
	<tr>
<%	if (!DictionaryManager.isLink(oid, "rec-endUser")) {	%>
		<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</td>
<%	} else {	%>
		<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%> height=16px class=label align=right nowrap><a href="javascript: setCurrentRow([ROW_NUMBER]); browseLookup('PoLine_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "Requisitioner")%> in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUser", "End User", false)%>:</a></td>
<%	}%>
		<td <%=HtmlWriter.isVisible(oid, "rec-endUser")%>><input type=text name="PoLine_requisitionerCode" tabindex="23" size="25" maxlength=20 value="<%=poLine.getRequisitionerCode()%>" onchange="upperCase(this);  getNewInfo('requisitioner', this, [ROW_NUMBER]);" <%if(oid.equalsIgnoreCase("vse06p")){%> disabled <%}%>></td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18% class=label align=right nowrap><%if(!oid.equalsIgnoreCase("vse06p")){%><a href="javascript: setCurrentRow([ROW_NUMBER]); <% if (oid.equalsIgnoreCase("QRI06P")) {%>browseCommodity('PoLine_udf1Code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');<% } else {%> browseStd('PoLine_udf1Code', 'LN01');<%}%> void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1")%> for this item or enter the value in the box on the right." tabIndex=-1><%}%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN01", "Line UDF 1", true)%></a>:</td>
<%	if (oid.equalsIgnoreCase("vse06p")) {	%>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18%>
			<input type=checkbox name="c_checkbox" tabindex="9" <% if (poLine.getUdf1Code().equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(PoLine_udf1Code, 0);">
			<tsa:hidden name="PoLine_udf1Code" value="<%=poLine.getUdf1Code()%>"/>
		</td>
<%	} else {	%>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN01")%> height=16px width=18%><input type=text name="PoLine_udf1Code" tabindex="35" size=15 maxlength=15 value="<%=poLine.getUdf1Code()%>" onchange="upperCase(this); "></td>
<%	}%>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%> height=16px class=label align=right nowrap><a href="javascript: setCurrentRow([ROW_NUMBER]); browseStd('PoLine_udf4Code', 'LN04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN04", "Line UDF 4", true)%></a>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN04")%>height=16px ><input type=text name="PoLine_udf4Code" tabindex="41" size=15 maxlength=15 value="<%=poLine.getUdf4Code()%>" onchange="upperCase(this); "></td>
	</tr>
	<tr>
		<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> align=right nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-endUserName", "End User Name")%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-endUserName")%> nowrap><input type=text name="as_requisitionerName" size=25 value="<%=UserManager.getInstance().getUser(oid, uid).getDisplayName()%>" onfocus="this.blur();" disabled></td>
<%	if (oid.equalsIgnoreCase("vse06p")) { %>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-LN02", "Line UDF2", true)%>:&nbsp;</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> >
			<select tabindex="37" name="PoLine_udf2Code" size=1>
<%
			if (HiltonUtility.isEmpty(poLine.getUdf2Code()))
			{
				poLine.setUdf2Code(DictionaryManager.getInstance("inspection-level", oid).getLabelProperty("DEFAULT", ""));
			}
			Map inspectionLevels = new TreeMap(DictionaryManager.getInstance("inspection-level", oid).getPropertyMap());
			Iterator inspectionIterator = inspectionLevels.keySet().iterator();
			String	inspectionLevelCode = "";
			String inspectionLevelName = "";
			while (inspectionIterator.hasNext())
			{
				inspectionLevelCode = (String) inspectionIterator.next();
				inspectionLevelName = (String) inspectionLevels.get(inspectionLevelCode);

				if (inspectionLevelCode.equals("DEFAULT"))
				{
					continue;
				}
%>
					<option value="<%=inspectionLevelCode%>" <%if (poLine.getUdf2Code().equalsIgnoreCase(inspectionLevelCode)) {%>SELECTED<%}%>><%=inspectionLevelName%></value>
<%		}	%>
				</select>
		</td>
<%	} else { %>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=15% class=label align=right nowrap><a href="javascript: setCurrentRow([ROW_NUMBER]); browseStd('PoLine_udf2Code', 'LN02'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN02", "Line UDF 2", true)%></a>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN02")%> height=16px width=18%><input type=text name="PoLine_udf2Code" tabindex="37" size=15 maxlength=15 value="<%=poLine.getUdf2Code()%>" onchange="upperCase(this); "></td>
<%	}	%>
	<%if (!oid.equalsIgnoreCase("vse06p")) { %>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px  class=label align=right nowrap><a href="javascript: setCurrentRow([ROW_NUMBER]); browseStd('PoLine_udf5Code', 'LN05'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN05", "Line UDF 5", true)%></a>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN05")%>height=16px ><input type=text name="PoLine_udf5Code" tabindex="43" size=15 maxlength=15 value="<%=poLine.getUdf5Code()%>" onchange="upperCase(this); "></td>
	<% } %>
	</tr>
	<tr>
		<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px  class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-asset", "Asset", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-asset")%> height=16px colspan=3>
			<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
			<tr>
				<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-asset")%>><input type="checkbox" name="c_asset" value=Y onclick="setCurrentRow([ROW_NUMBER]); setAsset();"></td>
				<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-gfpProperty")%> height=16px  class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-gfpProperty", "Gov't Property", false)%>:</td>
				<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-gfpProperty")%>><input type="checkbox" name="c_gfp" value=Y onclick="setCurrentRow([ROW_NUMBER]); setGfp(InvProperty_flag);"></td>
				<tsa:hidden name="gfp" value="I"/>
				<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-fgpProperty")%> height=16px  class=label align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-fgpProperty", "Foreign Gov't Property", false)%>:</td>
				<td valign=middle <%=HtmlWriter.isVisible(oid, "rec-fgpProperty")%>><input type="checkbox" name="c_fgp" value=Y onclick="setCurrentRow([ROW_NUMBER]); setFgp(InvProperty_flag);"></td>
			</tr>
			</table>
		</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=15% class=label align=right nowrap><a href="javascript: setCurrentRow([ROW_NUMBER]); browseStd('PoLine_udf3Code', 'LN03'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3")%> for this item or enter the value in the box on the right." tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-po-LN03", "Line UDF 3", true)%></a>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-po-LN03")%> height=16px width=16%><input type=text name="PoLine_udf3Code" tabindex="39" size=15 maxlength=15 value="<%=poLine.getUdf3Code()%>" onchange="upperCase(this); "></td>
	</tr>
	</table>
	<tsa:hidden name="PoLine_icPoLine" value=""/>
	</div>
</div>

<div id="defaultInvProperties" style="visibility: hidden; display: none;">
	<hr size=0>
	<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow>
	<tr>
		<td align=right width=5px>[INV_PROPERTY_ROW_DISPLAY]:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-tagnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-tagnumber", "GPIN", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-tagnumber")%>>
			<input type=text name="InvProperty_tagNumber" value="" size=35>
			<tsa:hidden name="InvProperty_itemNumber" value="[INV_PROPERTY_ITEM_NUMBER]"/>
			<tsa:hidden name="InvProperty_icRc" value="[INV_PROPERTY_IC_RC]"/>
			<tsa:hidden name="InvProperty_icPoLine" value="[INV_PROPERTY_IC_PO_LINE]"/>
			<tsa:hidden name="InvProperty_receiptRow" value="[ROW_NUMBER]"/>
		</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-serialnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-serialnumber", "Serial #", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-serialnumber")%>><input type=text name="InvProperty_serialNumber" value="" size=50></td>
		<td width=5px align=center><a href="javascript: deleteInvProperty([ROW_NUMBER],[INV_PROPERTY_ROW]); void(0);"><img src="<%=contextPath%>/images/delete.gif" border=0 alt="Delete Property Record" tabIndex=-1></a></td>
	</tr>
	<tr>
		<td align=right width=5px>&nbsp;</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-contractnumber")%> align=right nowrap><a href="javascript: void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-contractnumber", "Contract", false)%></a>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-contractnumber")%>><input type=text name="InvProperty_contract" value=""></td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-shipnumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-shipnumber", "Shipping Number", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-shipnumber")%> colspan=2><input type=text name="InvProperty_shipNumber" value=""></td>
	</tr>
	<tr>
		<td align=right width=5px>&nbsp;</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-ponumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-ponumber", "PO Number", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-ponumber")%>><input type=text name="InvProperty_poNumber" value=""></td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-cblout")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-cblout", "CBL Out", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-cblout")%> colspan=2><input type=text name="InvProperty_cblOutNumber" value=""></td>
	</tr>
		<td align=right width=5px>&nbsp;</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-armynumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-armynumber", "Army #", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-armynumber")%> colspan=4><input type=text name="InvProperty_armyNumber" value=""></td>
	</tr>
	<tr>
		<td align=right width=5px>&nbsp;</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-remarks")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-invprop-remarks", "Remarks", false)%>:</td>
		<td <%=HtmlWriter.isVisible(oid, "rec-invprop-remarks")%> colspan=4><input type=text name="InvProperty_remarks" value="" size=50></td>
	</tr>
	</table>
</div>


<div id="dummyFields" style="display:none;">
<tsa:hidden name="ReceiptLine_udf3Code" value=""/>
<tsa:hidden name="ReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="ReceiptLine_qtyRejected" value=""/>
<tsa:hidden name="ReceiptLine_qtyAccepted" value=""/>
<tsa:hidden name="balance" value=""/>
<tsa:hidden name="originalQtyReceived" value=""/>
<tsa:hidden name="ReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="ReceiptLine_icRecLine" value=""/>
<tsa:hidden name="ReceiptLine_icPoLine" value=""/>
<tsa:hidden name="ReceiptLine_receiptNotes" value=""/>
<tsa:hidden name="ReceiptLine_inspectionCode" value=""/>
<tsa:hidden name="ReceiptLine_rejectionCode" value=""/>
<tsa:hidden name="ReceiptLine_dispositionCode" value=""/>
<tsa:hidden name="PoLine_quantity" value=""/>
<tsa:hidden name="PoLine_umCode" value=""/>
<tsa:hidden name="PoLine_umFactor" value=""/>
<tsa:hidden name="PoLine_itemNumber" value=""/>
<tsa:hidden name="PoLine_altItemNumber" value=""/>
<tsa:hidden name="PoLine_description" value=""/>
<tsa:hidden name="PoLine_unitPrice" value=""/>
<tsa:hidden name="PoLine_itemSource" value=""/>
<tsa:hidden name="PoLine_itemLocation" value=""/>
<tsa:hidden name="PoLine_asset" value=""/>
<tsa:hidden name="PoLine_receiptRequired" value=""/>
<tsa:hidden name="PoLine_icPoLine" value=""/>
<tsa:hidden name="PoLine_udf1Code" value=""/>
<tsa:hidden name="PoLine_udf2Code" value=""/>
<tsa:hidden name="PoLine_udf3Code" value=""/>
<tsa:hidden name="PoLine_udf4Code" value=""/>
<tsa:hidden name="PoLine_udf5Code" value=""/>
<tsa:hidden name="PoLine_requisitionerCode" value=""/>
<tsa:hidden name="as_requisitionerName" value=""/>
<tsa:hidden name="PoLine_catalogId" value=""/>
<tsa:hidden name="createReturn" value=""/>
<tsa:hidden name="returnReceiptHeader_receiptNotes" value=""/>
<tsa:hidden name="returnReceiptHeader_pkgsReceived" value=""/>
<tsa:hidden name="returnReceiptHeader_packingSlip" value=""/><!--RMA #-->
<tsa:hidden name="returnReceiptHeader_carrierCode" value=""/>
<tsa:hidden name="returnReceiptHeader_returnDate" value=""/>
<tsa:hidden name="returnReceiptHeader_icRecHeader" value=""/>
<tsa:hidden name="returnReceiptHeader_icPoHeader" value=""/>
<tsa:hidden name="returnReceiptLine_icRecHeader" value=""/>
<tsa:hidden name="returnReceiptLine_qtyReceived" value=""/>
<tsa:hidden name="returnReceiptLine_qtyRejected" value=""/>
<tsa:hidden name="returnReceiptLine_qtyReturned" value=""/>
<tsa:hidden name="returnReceiptLine_icPoLine" value=""/>
<tsa:hidden name="returnReceiptLine_dispositionCode" value=""/>
<tsa:hidden name="returnReceiptLine_receiptNotes" value=""/>
<tsa:hidden name="receiptLineFactor" value=""/>
<tsa:hidden name="count_flag" value=""/>
<tsa:hidden name="icRc" value=""/>

</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="forward_link"><a href="javascript: finalizeReceipt(); void(0);"><img class=button src="<%=contextPath%>/images/button_finalize.gif" border=0 tabIndex=1000></a></div></td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 tabIndex=1002></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var info_window = null;
	var populated = false;
	var uomArray = new Array();
	var currentRow = 0;
	var qtyDecimals  = <%=Integer.valueOf(quantityDecimals).intValue()%>;
	var displayRejectionCode = <%=DictionaryManager.isVisible(oid, "rec-rejectionCode")%>;
	var displayDispositionCode = <%=DictionaryManager.isVisible(oid, "rec-dispositionCode")%>;
	var sum = 1;

	addNewItem("<%=poLine.getIcPoLine()%>");

	function receiveAllForItem(row) {
		var balance = nformat(frm.balance[row].value, qtyDecimals);
		frm.ReceiptLine_qtyReceived[row].value = balance;
		frm.ReceiptLine_qtyRejected[row].value = nformat(0, qtyDecimals);

		checkBalance(row);
		if (frm.ReceiptLine_qtyReceived[row + 1] && frm.ReceiptLine_qtyReceived[row + 1].type != "hidden") {
			frm.ReceiptLine_qtyReceived[row + 1].focus();
		}
	}

	function checkBalance(row) {
		var receiptQty = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived[row])), qtyDecimals);
		var rejectedQty = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected[row])), qtyDecimals);
		var acceptedQty = nformat(receiptQty - rejectedQty, qtyDecimals);
		frm.ReceiptLine_qtyReceived[row].value = receiptQty;
		frm.ReceiptLine_qtyAccepted[row].value = acceptedQty;
	}

	function viewReceiptHistory(row) {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
	}

	function hideReceiptHistory(row) {
		var d = document.all("itemReceiptHistory");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptHistory");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
	}

	function viewReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "block";
		}
	}

	function hideReceiptNotes(row) {
		var d = document.all("itemReceiptNotes");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("receiptNotes");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
	}

	function receiveAll() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			receiveAllForItem(i);
		}
	}

	function finalizeReceipt() {
		if (validatePoLineInfo() && validateInventoryInfo()) {
			frm.count_ini.value = 0;
			frm.count_aux.value = 0;
			frm.receiptMethod.value ="ReceiveFromNothing";
			frm.receiptAction.value = "FORWARD";
			frm.failurePage.value = "/system/error_popup.jsp";
			for(x=0;x <= frm.count_item.value;x++){
				if((frm.count_flag[x].value != null) && (frm.count_flag[x].value > 0)){
					frm.count_aux.value = frm.count_aux.value*1 + sum;
				}
			}
			if((frm.ReceiptLine_qtyReceived.value == undefined) && (frm.count_flag.value == undefined)){
				for(x=0;x <= frm.count_item.value;x++)
				{
					if(frm.ReceiptLine_qtyReceived[x].value == frm.count_flag[x].value + ".00")
					{
						frm.count_ini.value = frm.count_ini.value*1 + sum*1;
					}
				}
			}
			if (doSubmitToNewTarget('receipts/rec_validate.jsp', 'ReceiptValidate', 'lookup_window')) {
				hideAreaWithBlock('forward_link');
			}
			frm.failurePage.value = "/system/error.jsp";
		}
	}

	function validateInventoryInfo() {
		return true;
	}

	function validatePoLineInfo() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			if (isEmpty(frm.PoLine_itemNumber[i].value) && isEmpty(frm.PoLine_description[i].value)) {
				alert("You must enter either an item number or description for all items.");
				return false;
			}
			frm.PoLine_quantity[i].value = frm.ReceiptLine_qtyReceived[i].value;
			frm.PoLine_itemLocation[i].value = frm.ReceiptHeader_shipToCode.value;
		}
		return true;
	}

	function checkInventory(row) {
		var shipToInv = frm.ReceiptHeader_shipToInv.value;
		var itemLocation = frm.ReceiptHeader_shipToCode.value;
		var itemNumber = frm.PoLine_altItemNumber[row].value;
		var itemCost = frm.PoLine_unitPrice[row].value;
		var umCode = frm.PoLine_umCode[row].value;
		var receiptOption = frm.PoLine_receiptRequired[row].value;
		var icPoLine = frm.ReceiptLine_icPoLine[row].value;
		var qtyReceived = frm.ReceiptLine_qtyAccepted[row].value;
		var receiptLineFactor = frm.receiptLineFactor[row].value;

		if (isEmpty(itemNumber)) {
			itemNumber = frm.PoLine_itemNumber[row].value;
		}

		if (shipToInv == "Y" && (receiptOption == "R" || receiptOption == "E")) {
			if (qtyReceived == 0) {
				popupParameters = "InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value;
				setLookupParameters('/system/hide_iframe.jsp', 'InvBinLocationDeleteItemByTempIc');
				displayArea('getInfoFrame');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			} else {
				popupParameters = "InvItem_itemNumber=" + itemNumber + ";InvBinLocation_itemNumber=" + itemNumber + ";InvBinLocation_itemLocation=" + itemLocation + ";InvLocation_itemNumber=" + itemNumber + ";InvLocation_itemLocation=" + itemLocation + ";qtyReceived=" + qtyReceived + ";index=" + row + ";InvBinLocation_tempIc=" + frm.InvBinLocation_tempIc.value + ";InvBinLocation_cost=" + itemCost + ";PoLine_icPoLine=" + icPoLine + ";PoLine_umCode=" + umCode + ";receiptRow=" + row + ";receiptLineFactor=" + receiptLineFactor;
				doSubmitToPopup('receipts/rec_inv_bin_locations.jsp', 'InvItemRetrieveById;InvBinLocationRetrieveByItem', 'WIDTH=700px', 'HEIGHT=500px');
			}
		}
	}

	function formatPkgQty() {
		frm.ReceiptHeader_pkgsReceived.value = nformat(eval(nfilter(frm.ReceiptHeader_pkgsReceived)), 0);
	}

	function viewAssetRelated(iclinekey) {
  		var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
  			newInputField = newInputField + "<input type='hidden' name='allowBrowse' value='true'>";
  			newInputField = newInputField + "<input type='hidden' name='action'		value='rec-item-entry'>";
			newInputField = newInputField + "<input type='hidden' name='process'	value='AssetRetrieveByIcLineKey'>";
			newInputField = newInputField + "<input type='hidden' name='urlret'		value='/receipts/rec_item_entry'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_rec_browse.jsp", "AssetRetrieveByIcLineKey");
 	}

	function setCurrentRow(row) {
		currentRow = row;
	}

	function checkInspectionRequirements() {
		var inspectionReqd = <%=poHeader.isInspectionRequired()%>;
		if (inspectionReqd) {
			var items = frm.elements.item("ReceiptLine_qtyReceived");
			var itemCount = 0;

			if (items.length != undefined){
				itemCount = items.length - 1;
			}

			for (var i=0; i < itemCount; i++) {
				var receiptQty = eval(nfilter(frm.ReceiptLine_qtyReceived[i]));

				if (receiptQty > 0 && isEmpty(frm.ReceiptLine_inspectionCode[i].value)) {
					alert("An Inspection Code is required for all items received for this order.");
					return false;
				}
			}
		}
		return true;
	}

	function setReceived(row) {
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyReceived[row]));
		var qtyRejected = eval(nfilter(frm.ReceiptLine_qtyRejected[row]));

		if (qtyRejected > qtyReceived) {
			alert("You cannot reject more than the quantity you have received.");
			qtyRejected = qtyReceived;

			frm.ReceiptLine_qtyRejected[row].value = qtyReceived;
		}
		checkBalance(row);
	}

	function checkRejected(row) {
		var qtyRejected = nformat(eval(nfilter(frm.ReceiptLine_qtyRejected[row])), qtyDecimals);

		if (qtyRejected > 0) {
			if (displayRejectionCode) {
				viewRejectionCode(row);
				frm.ReceiptLine_rejectionCode[row].select();
			}
			if (displayDispositionCode) {
				viewDispositionCode(row);
				if (!displayRejectionCode) {
					frm.ReceiptLine_dispositionCode[row].select();
				}
			}
		}
		else {
			frm.createReturn[row].value = "N";
			hideRejectionCode(row);
			hideDispositionCode(row);
		}
	}

	function viewRejectionCode(row) {
		var d = document.all("itemRejectionCode");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var d = document.all("itemReturnOption");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
	}

	function hideRejectionCode(row) {
		var d = document.all("itemRejectionCode");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var d = document.all("itemReturnOption");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
	}

	function viewDispositionCode(row) {
		var d = document.all("itemDispositionCode");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
	}

	function hideDispositionCode(row) {
		var d = document.all("itemDispositionCode");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
	}

	function viewAllItemDetails() {
		var items = frm.elements.item("ReceiptLine_qtyReceived");
		var itemCount = 0;

		if (items.length != undefined){
			itemCount = items.length - 1;
		}
		for (var i=0; i < itemCount; i++) {
			viewItemDetails(i);
		}
	}

	function viewItemDetails(row) {
		var d = document.all("openItemDetails");
		if (d.length > 1) {
			d(row).style.visibility = "hidden";
			d(row).style.display = "none";
		} else {
			d.style.visibility = "hidden";
			d.style.display = "none";
		}
		var t = document.all("closeItemDetails");
		if (t.length > 1) {
			t(row).style.visibility = "visible";
			t(row).style.display = "block";
		} else {
			t.style.visibility = "visible";
			t.style.display = "block";
		}
		var s = document.all("itemDetails");
		if (s.length > 1) {
			s(row).style.visibility = "visible";
			s(row).style.display = "block";
		} else {
			s.style.visibility = "visible";
			s.style.display = "block";
		}
	}

	function hideItemDetails(row) {
		var d = document.all("openItemDetails");
		if (d.length > 1) {
			d(row).style.visibility = "visible";
			d(row).style.display = "block";
		} else {
			d.style.visibility = "visible";
			d.style.display = "block";
		}
		var t = document.all("closeItemDetails");
		if (t.length > 1) {
			t(row).style.visibility = "hidden";
			t(row).style.display = "none";
		} else {
			t.style.visibility = "hidden";
			t.style.display = "none";
		}
		var s = document.all("itemDetails");
		if (s.length > 1) {
			s(row).style.visibility = "hidden";
			s(row).style.display = "none";
		} else {
			s.style.visibility = "hidden";
			s.style.display = "none";
		}
	}

	function returnRejected(row) {
		var originalQtyReceived = eval(nfilter(frm.originalQtyReceived[row]));
		var qtyReceived = eval(nfilter(frm.ReceiptLine_qtyReceived[row]));
		var totalQtyRecieved = eval(originalQtyReceived + qtyReceived);

		popupParameters = "ReceiptHeader_icPoHeader=" + frm.ReceiptHeader_icPoHeader.value;
		popupParameters = popupParameters + ";icPoLine=" + frm.ReceiptLine_icPoLine[row].value;
		popupParameters = popupParameters + ";qtyRejected=" + frm.ReceiptLine_qtyRejected[row].value;
		popupParameters = popupParameters + ";qtyReceived=" + totalQtyRecieved;
		popupParameters = popupParameters + ";dispositionCode=" + frm.ReceiptLine_dispositionCode[row].value;
		popupParameters = popupParameters + ";currentRow=" + row;
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		doSubmitToPopup('receipts/rec_return_popup.jsp', 'ReceiptCreateRetrieve', 'width=692px', 'height=325px');
	}

    function browseSupplier(formField)
	{
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

	function getItemInfo(row) {
		// No need to call the server if the item number is empty
		if (!isEmpty(frm.PoLine_itemNumber[row].value)) {
//			doSubmit('/orders/po_item.jsp', 'PoLineItemLookup;PoLineRetrieve');
		}
	}

	function formatUnitPrice (row) {
		var price_dec = <%=Integer.valueOf(priceDecimals).intValue()%>;
		var p = nformat(eval(nfilter(frm.PoLine_unitPrice[row])),price_dec);
		var q = nformat(eval(nfilter(frm.ReceiptLine_qtyReceived[row])), qtyDecimals);
		var f = eval(nfilter(frm.PoLine_umFactor[row]));

		if (f == 0) { f = 1; }

		frm.PoLine_umFactor[row].value = f;
		frm.PoLine_unitPrice[row].value = p;
		frm.PoLine_quantity[row].value = q;

		if (p == (nformat(eval(0.00),price_dec)) && q == nformat(eval(0.00),qtyDecimals)) {
			frm.PoLine_unitPrice[row].value = '';
			frm.PoLine_quantity[row].value = '';
		}
	}

	function updateUMFactor(row) {
		var open = true;
		var browser = browserTest();
		var factor = "";
		var code = "";

		frm.PoLine_umCode[row].value = trim(frm.PoLine_umCode[row]);

		if (info_window != null) {
			if (browser == "Netscape") {
				if (info_window.closed == false) {
					info_window.setUomCode("PoLine_");
					open = false;
				}
			}
			else {
				info_window.setUomCode("PoLine_");
				open = false;
			}
		}

		if (open == true) {
			if (uomArray.length > 0 || populated) {
				code = frm.PoLine_umCode[row].value;
				for (var i = 0; i < uomArray.length; i++) {
					if (code == (uomArray[i][0]).toString()) {
						factor = uomArray[i][1];
						break;
					}
				}

				setUmFactor(factor, row);
			}
			else {
				popupParameters = "as_prefix=PoLine_;as_row=" + row;

				setLookupParameters('/base/get_uom_info.jsp', 'UnitOfMeasureRetrieveAll');
				document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
			}
		}
	}

	function setUmFactor(factor, row) {
		frm.PoLine_umFactor[row].value = factor;
	}

	function populateUOM(uoma)
	{
		var e = 0;

		for (var i = 0; i < uoma.length; i++) {
			uomArray[e] = new Array(uoma[i][0], uoma[i][1]);
			e++;
		}

		populated = true;
	}

	function deleteItem(row) {
		var myTable = document.getElementById("itemTable");
		var currentRows = myTable.rows.length;

		if (currentRows == 1) {
			return;
		}
		else if (currentRows == 2) {
			frm.PoLine_quantity[0].value = "";
			frm.PoLine_umCode[0].value = "";
			frm.PoLine_umFactor[0].value = "1";
			frm.PoLine_itemNumber[0].value = "";
			frm.PoLine_description[0].value = "";
			frm.PoLine_unitPrice[0].value = "";
			frm.PoLine_itemSource[0].value = "";
			frm.PoLine_itemLocation[0].value = "";
			frm.PoLine_asset[0].value = "";
			frm.PoLine_udf1Code[0].value = "";
			frm.PoLine_udf2Code[0].value = "";
			frm.PoLine_udf3Code[0].value = "";
			frm.PoLine_udf4Code[0].value = "";
			frm.PoLine_udf5Code[0].value = "";
			frm.PoLine_receiptRequired[0].value = "R";
			frm.PoLine_requisitionerCode[0].value = "";
			frm.PoLine_catalogId[0].value = "";
			frm.icRc[0].value = "";
		}
		else if (currentRows > 2) {
			for (var i = row; i < (currentRows - 1); i++) {
				var quantity = frm.PoLine_quantity[i + 1].value;
				var umCode = frm.PoLine_umCode[i + 1].value;
				var umFactor = frm.PoLine_umFactor[i + 1].value;
				var itemNumber = frm.PoLine_itemNumber[i + 1].value;
				var description = frm.PoLine_description[i + 1].value;
				var unitPrice =frm.PoLine_unitPrice[i + 1].value;
				var itemSource = frm.PoLine_itemSource[i + 1].value;
				var itemLocation = frm.PoLine_itemLocation[i + 1].value;
				var asset = frm.PoLine_asset[i + 1].value;
				var udf1Code = frm.PoLine_udf1Code[i + 1].value;
				var udf2Code = frm.PoLine_udf2Code[i + 1].value;
				var udf3Code = frm.PoLine_udf3Code[i + 1].value;
				var udf4Code = frm.PoLine_udf4Code[i + 1].value;
				var udf5Code = frm.PoLine_udf5Code[i + 1].value;
				var receiptRequired =frm.PoLine_receiptRequired[i + 1].value;
				var requisitionerCode = frm.PoLine_requisitionerCode[i + 1].value;
				var catalogId = frm.PoLine_catalogId[i + 1].value;
				var icRc = frm.icRc[i + 1].value;

				frm.PoLine_quantity[i].value = quantity;
				frm.PoLine_umCode[i].value = umCode;
				frm.PoLine_umFactor[i].value = umFactor;
				frm.PoLine_itemNumber[i].value = itemNumber;
				frm.PoLine_description[i].value = description;
				frm.PoLine_unitPrice[i].value = unitPrice;
				frm.PoLine_itemSource[i].value = itemSource;
				frm.PoLine_itemLocation[i].value = itemLocation;
				frm.PoLine_asset[i].value = asset;
				frm.PoLine_udf1Code[i].value = udf1Code;
				frm.PoLine_udf2Code[i].value = udf2Code;
				frm.PoLine_udf3Code[i].value = udf3Code;
				frm.PoLine_udf4Code[i].value = udf4Code;
				frm.PoLine_udf5Code[i].value = udf5Code;
				frm.PoLine_receiptRequired[i].value = receiptRequired;
				frm.PoLine_requisitionerCode[i].value = requisitionerCode;
				frm.PoLine_catalogId[i].value = catalogId;
				frm.icRc[i].value = icRc;
			}
		}

		if (currentRows > 2) {
			myTable.deleteRow(currentRows - 1);
		}
	}

	function getItemDetailsHTML(row) {
		var defaultObj = document.getElementById("defaultItemDetails");
		var defaultHTML = defaultObj.innerHTML;
		defaultHTML = replaceChars(defaultHTML, "[ROW_NUMBER]", row);
		return defaultHTML;
	}

	function createNewItem() {
		//Gets a new PoLine_icPoLine then calls addNewItem(ic);
		frm.count_item.value = frm.count_item.value*1 + sum*1;

		popupParameters = "PoHeader_icPoHeader=" + frm.PoHeader_icPoHeader.value;

		setLookupParameters('/base/get_new_poline_info.jsp', 'PoLineCreate;PoLineDelete');
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
	}

	function addNewItem(ic) {
		var myTable = document.getElementById("itemTable");
		var currentRows = myTable.rows.length;
		var row = currentRows - 1;
		var myRow;
		var myCell;

		myRow = createRow(myTable);

		myCell = createCell(myRow);
		myCell.width = "100%";
		myCell.align = "center";
		myCell.vAlign = "top";
		myCell.innerHTML = "<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow id=\"item_" + row + "\"></table>";

		var myItemTable = document.getElementById("item_" + row);

		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.width = "6%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = nformat((row + 1), 1) + "&nbsp;";
<%	if (!DictionaryManager.isVisible(oid, "rec-lineNumber")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "30%";
		myCell.className = "browseRow";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"PoLine_itemNumber\" size=25 maxlength=30 value=\"\" onchange=\"upperCase(this); getItemInfo(" + row + ");  void(0);\">";
<%	if (!DictionaryManager.isVisible(oid, "rec-itemNumber")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "20%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"PoLine_unitPrice\" size=20 maxlength=25 value=\"\" style=\"text-align:right\" onchange=\"formatUnitPrice(" + row + ");\">";
<%	if (!DictionaryManager.isVisible(oid, "rec-unitCost")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "12%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_qtyReceived\" size=10 maxlength=25 value=\"\" style=\"text-align:right\" onchange=\"checkBalance(" + row + "); checkInventory(" + row + ");\">" +
						"<input type=hidden name=\"balance\" value=\"\">" +
						"<input type=hidden name=\"originalQtyReceived\" value=\"\">" +
						"<input type=hidden name=\"ReceiptLine_icRecHeader\" value=\"<%=receiptHeader.getIcRecHeader()%>\">" +
						"<input type=hidden name=\"ReceiptLine_icRecLine\" value=\"\">" +
						"<input type=hidden name=\"ReceiptLine_icPoLine\" value=\"\">" +
						"<input type=hidden name=\"PoLine_itemSource\" value=\"\">" +
						"<input type=hidden name=\"PoLine_itemLocation\" value=\"\">" +
						"<input type=hidden name=\"PoLine_quantity\" value=\"\">" +
						"<input type=hidden name=\"PoLine_receiptRequired\" value=\"R\">" +
						"<input type=hidden name=\"PoLine_asset\" value=\"\">" +
						"<input type=hidden name=\"createReturn\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_receiptNotes\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_pkgsReceived\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_packingSlip\" value=\"\"><!--RMA #-->" +
						"<input type=hidden name=\"returnReceiptHeader_carrierCode\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_returnDate\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_icRecHeader\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptHeader_icPoHeader\" value=\"<%=receiptHeader.getIcPoHeader()%>\">" +
						"<input type=hidden name=\"returnReceiptLine_icRecHeader\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_qtyReceived\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_qtyRejected\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_qtyReturned\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_icPoLine\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_dispositionCode\" value=\"\">" +
						"<input type=hidden name=\"returnReceiptLine_receiptNotes\" value=\"\">"  +
						"<input type=hidden name=\"receiptLineFactor\" value=\"\">" +
						"<input type=hidden name=\"count_flag\" value=\"\">" +
						"<input type=hidden name=\"icRc\" value=\"\">";
<%	if (!DictionaryManager.isVisible(oid, "rec-quantityReceived")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "5%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"PoLine_umCode\" size=3 maxlength=15 value=\"EA\" onchange=\"upperCase(this); updateUMFactor(" + row + ");\">" +
				 "<input type=hidden name=\"PoLine_umFactor\" value=\"1\">";
<%	if (!DictionaryManager.isVisible(oid, "rec-quantityUom")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "12%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_qtyRejected\" size=10 value=\"\" style=\"text-align:right\" onchange=\"setReceived(" + row + "); checkRejected(" + row + ");\">";
<%	if (!DictionaryManager.isVisible(oid, "rec-quantityRejected")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "12%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_qtyAccepted\" size=10 value=\"\" style=\"text-align:right\" readonly>";
<%	if (!DictionaryManager.isVisible(oid, "rec-quantityAccepted")) {%>
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	}%>

		myCell = createCell(myRow);
		myCell.width = "3%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<div id=\"receiptNotes\"><a href=\"javascript: viewReceiptNotes(" + row + "); void(0);\"><img src=\"<%=contextPath%>/images/cmd_edit.gif\" border=0 alt=\"Enter Item Receipt Notes\" tabIndex=-1></a></div>";

		myCell = createCell(myRow);
		myCell.width = "3%";
		myCell.className = "browseRow";
		myCell.align = "right";
		myCell.nowrap = true;
		myCell.innerHTML = "<div id=\"deleteItemLink\"><a href=\"javascript: deleteItem(" + row + "); void(0);\"><img src=\"<%=contextPath%>/images/delete.gif\" border=0 alt=\"Delete Item Receipt\" tabIndex=-1></a></div>";

		/***** NEW ROW FOR ITEM TABLE *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "&nbsp;";

<%	if (!DictionaryManager.isVisible(oid, "rec-altItemNumber")) {%>
		myCell.colSpan = 9;
		myCell.innerHTML = "<input type=hidden name=\"PoLine_altItemNumber\" maxlength=30 value=\"\">";
		myCell.style.visibility = "hidden";
		myCell.style.display = "none";
<%	} else {%>
		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 8;
		myCell.vAlign = "top";
		myCell.innerHTML = "<input type=text name=\"PoLine_altItemNumber\" size=25 maxlength=30 value=\"\" disabled> (<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-altItemNumber", "Alt. Item/Part #")%>)";
<%	}%>

		/***** NEW ROW FOR ITEM TABLE *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "&nbsp;";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 2;
		myCell.vAlign = "top";
		myCell.innerHTML = "<textarea wrap=\"virtual\" name=\"PoLine_description\" rows=5 cols=60 onKeyDown=\"textCounter(this, 2000);\" onKeyUp=\"textCounter(this,2000);\" onchange=\"textCounter(this,2000);\"></textarea>";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 4;
		myCell.vAlign = "top";
		myCell.align = "right";
		myCell.innerHTML = "<table border=0 cellspacing=0 cellpadding=1 class=browseRow id=\"inspectionCodes_"  + row +  "\"></table>";

		var myCodesTable = document.getElementById("inspectionCodes_" + row);

		/***** ROW FOR INSPECTION CODE *****/
		var myCodesRow = createRow(myCodesTable);
<%	if (!DictionaryManager.isVisible(oid, "rec-inspectionCode")) {%>
		myCodesRow.style.visibility = "hidden";
		myCodesRow.style.display = "none";
<%	}%>

		myCell = createCell(myCodesRow);
		myCell.className = "label";
		myCell.height = "16px";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: setCurrentRow(" + row + "); browseStd('ReceiptLine_inspectionCode', 'INSP', true); void(0);\" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%>:</a>";

		myCell = createCell(myCodesRow);
		myCell.height = "16px";
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_inspectionCode\" size=20 onchange=\"upperCase(this);\">";

		/***** ROW FOR REJECTION CODE *****/
		myCodesRow = createRow(myCodesTable);
		myCodesRow.id = "itemRejectionCode";
		myCodesRow.style.visibility = "hidden";
		myCodesRow.style.display = "none";

		myCell = createCell(myCodesRow);
		myCell.className = "label";
		myCell.height = "16px";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: setCurrentRow(" + row + "); browseStd('ReceiptLine_rejectionCode', 'REJ', true); void(0);\" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code")%>:</a>";

		myCell = createCell(myCodesRow);
		myCell.height = "16px";
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_rejectionCode\" size=20 onchange=\"upperCase(this);\">";

		/***** ROW FOR DISPOSITION CODE *****/
		myCodesRow = createRow(myCodesTable);
		myCodesRow.id = "itemDispositionCode";
		myCodesRow.style.visibility = "hidden";
		myCodesRow.style.display = "none";

		myCell = createCell(myCodesRow);
		myCell.className = "label";
		myCell.height = "16px";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: setCurrentRow(" + row + "); browseStd('ReceiptLine_dispositionCode', 'DISP', true); void(0);\" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code", true)%>:</a>";

		myCell = createCell(myCodesRow);
		myCell.height = "16px";
		myCell.innerHTML = "<input type=text name=\"ReceiptLine_dispositionCode\" size=20 onchange=\"upperCase(this);\">";

		/***** ROW FOR RETURN OPTION *****/
		myCodesRow = createRow(myCodesTable);
		myCodesRow.id = "itemReturnOption";
		myCodesRow.style.visibility = "hidden";
		myCodesRow.style.display = "none";

		myCell = createCell(myCodesRow);
		myCell.colSpan = 2;
		myCell.height = "16px";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: returnRejected(" + row + "); void(0);\" tabIndex=-1><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-returnRejectedItems", "Return Rejected Items")%></a>";

		/***** END CODES TABLE ROW CREATION *****/

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.nowrap = true;
		myCell.innerHTML = "&nbsp;";

		/***** NEW ROW FOR ITEM TABLE *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "&nbsp;";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 2;
		myCell.vAlign = "top";
		myCell.innerHTML = "<div id=\"openItemDetails\"><table border=0 cellspacing=0 cellpadding=1 id=\"openItemDetailsTable_" + row + "\"></table></div>" +
				"<div id=\"closeItemDetails\" style=\"visibility:hidden;display:none;\"><table border=0 cellspacing=0 cellpadding=1 id=\"closeItemDetailsTable_" + row + "\"></table></div>";

		/***** CREATE OPEN/CLOSE ITEM DETAIL LINKS TABLE *****/
		var myItemDetailsTable = document.getElementById("openItemDetailsTable_" + row);
		var myItemDetailsRow = createRow(myItemDetailsTable);

		myCell = createCell(myItemDetailsRow);
		myCell.vAlign = "middle";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: viewItemDetails(" + row + "); void(0);\" tabIndex=\"-1\"><img src=\"<%=contextPath%>/images/plus.gif\" border=0></a>";

		myCell = createCell(myItemDetailsRow);
		myCell.vAlign = "middle";
		myCell.innerHTML = "<a href=\"javascript: viewItemDetails(" + row + "); void(0);\" tabIndex=\"-1\">View Item Details</a>";

		myItemDetailsTable = document.getElementById("closeItemDetailsTable_" + row);
		myItemDetailsRow = createRow(myItemDetailsTable);

		myCell = createCell(myItemDetailsRow);
		myCell.vAlign = "middle";
		myCell.align = "right";
		myCell.innerHTML = "<a href=\"javascript: hideItemDetails(" + row + "); void(0);\" tabIndex=\"-1\"><img src=\"<%=contextPath%>/images/minus.gif\" border=0></a>";

		myCell = createCell(myItemDetailsRow);
		myCell.vAlign = "middle";
		myCell.innerHTML = "<a href=\"javascript: hideItemDetails(" + row + "); void(0);\" tabIndex=\"-1\">Hide Item Details</a>";
		/***** END CREATE OPE/CLOSE ITEM DETAIL LINKS TABLE *****/

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 4;
		myCell.vAlign = "top";
		myCell.align = "right";
		myCell.innerHTML = "<div id=\"itemReceiptNotes\" style=\"visibility: hidden; display: none;\"><table border=0 cellspacing=0 cellpadding=1 class=browseRow id=\"itemReceiptNotesTable_" + row + "\"></table></div>";

		/***** CREATE ITEM RECEIPT NOTES TABLE *****/
		var myItemReceiptNotesTable = document.getElementById("itemReceiptNotesTable_" + row);
		var myItemReceiptNotesRow = createRow(myItemReceiptNotesTable);

		myCell = createCell(myItemReceiptNotesRow);
		myCell.vAlign = "middle";
		myCell.align = "right";
		myCell.width = "5%";
		myCell.innerHTML = "<a href=\"javascript: hideReceiptNotes(" + row + "); void(0);\" tabIndex=\"-1\"><img src=\"<%=contextPath%>/images/bar_close.gif\" border=0 alt='Hide Item Receipt Notes'></a>";

		myCell = createCell(myItemReceiptNotesRow);
		myCell.vAlign = "middle";
		myCell.width = "95%";
		myCell.innerHTML = "<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rcl-rec-receiptNotes", "Notes", false)%>:";

		myItemReceiptNotesRow = createRow(myItemReceiptNotesTable);

		myCell = createCell(myItemReceiptNotesRow);
		myCell.colSpan = 2;
		myCell.innerHTML = "<textarea name=\"ReceiptLine_receiptNotes\" cols=45 rows=4 onKeyDown=\"textCounter(this, 225);\" onKeyUp=\"textCounter(this,225);\"></textarea>";
		/***** END CREATE ITEM RECEIPT NOTES TABLE *****/

		myCell = createCell(myRow);
		myCell.className="browseRow";
		myCell.nowrap = true;
		myCell.innerHTML = "&nbsp;";

		/***** NEW ROW FOR ITEM TABLE *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.nowrap = true;
		myCell.innerHTML = "&nbsp;";

		myCell = createCell(myRow);
		myCell.colSpan = 6;
		myCell.className = "browseRow";
		myCell.innerHTML = getItemDetailsHTML(row);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.nowrap = true;
		myCell.innerHTML = "&nbsp;";
		/***** END NEW ROW FOR ITEM TABLE *****/


		/***** NEW ROW FOR ITEM TABLE TO HOLD INV PROPERTY RECORDS *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.innerHTML = "&nbsp;";

		myCell = createCell(myRow);
		myCell.className = "browseRow";
		myCell.colSpan = 6;
		myCell.vAlign = "top";
		myCell.innerHTML = "<div id=\"invPropertyArea_" + row + "\" style=\"visibility: hidden; display: none;\"><table border=0 cellspacing=0 cellpadding=1 id=\"invPropertiesTable_" + row + "\"></table>" +
			"<tr><td nowrap><a href=\"javascript: addNewInvProperty(" + row + "); void(0);\">Add New Property to Line Item " + (row + 1) + "</td>	</tr></div>";
		/***** END NEW ROW FOR ITEM TABLE TO HOLD INV PROPERTY RECORDS *****/

		/***** NEW ROW FOR ITEM TABLE *****/
		myRow = createRow(myItemTable);

		myCell = createCell(myRow);
		myCell.colSpan = 8;
		myCell.align = "center";
		myCell.innerHTML = "<hr size=0 width=100%>";
		/***** END NEW ROW FOR ITEM TABLE *****/

		frm.PoLine_icPoLine[row].value = ic;
		frm.ReceiptLine_icPoLine[row].value = ic;
	}

	function getNewShipToInfo(fld) {
		var next = "ReceiptHeader_pkgsReceived";
		var fldName = fld.name;
		var code = fld.value;

		if (isEmpty(fld.value)) {
			frm.asShipToAddress_addressLine1.value = "";
			frm.asShipToAddress_addressLine2.value = "";
			frm.asShipToAddress_addressLine3.value = "";
			frm.asShipToAddress_addressLine4.value = "";
			frm.asShipToAddress_city.value = "";
			frm.asShipToAddress_state.value = "";
			frm.asShipToAddress_postalCode.value = "";
			frm.asShipToAddress_country.value = "";
			frm.ReceiptHeader_shipToInv.value = "";
			frm.PoHeader_shipToInv.value = "";
	    } else {
		    popupParameters = "Address_addressCode=" + code + ";as_type=receiptShipTo;as_code=" + code + ";as_next=" + next;

			setLookupParameters('/base/get_addr_info.jsp', 'AddressRetrieveShipTo');
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
	    }
	}
	function setGfp(InvProperty_flag) {
		if (frm.c_gfp[currentRow].checked) {
			frm.c_asset[currentRow].checked = false;
			frm.c_fgp[currentRow].checked = false;
			frm.InvProperty_flag.value = "A";

			setupInvProperty();
		} else {
			frm.InvProperty_flag.value = "I";
			hideArea("invPropertyArea_" + currentRow);
		}
		//var newInputField = "";
		frm.gfp[currentRow].value =  frm.InvProperty_flag.value;
		//newInputField = newInputField + "<input type='hidden' name='gfp' value='<%=poLine.getUdf3Code()%>'>";
		//setHiddenFields(newInputField);
	}

	function setFgp(InvProperty_flag) {
		if (frm.c_fgp[currentRow].checked) {
			frm.c_asset[currentRow].checked = false;
			frm.c_gfp[currentRow].checked = false;
			frm.InvProperty_flag.value = "A";
			setupInvProperty();
		} else {
			frm.InvProperty_flag.value = "I";
			hideArea("invPropertyArea_" + currentRow);
		}
	}

	function setAsset() {
		if (frm.c_asset[currentRow].checked) {
			frm.c_gfp[currentRow].checked = false;
			frm.c_fgp[currentRow].checked = false;

			setupInvProperty();
		} else {
			hideArea("invPropertyArea_" + currentRow);

		}
	}

	function setupInvProperty() {
		displayArea("invPropertyArea_" + currentRow);

		var myTable = document.getElementById("invPropertiesTable_" + currentRow);
		var currentRows = myTable.rows.length;

		if (currentRows == 0) {
			addNewInvProperty(currentRow);
		}
	}
	function getInvPropertiesHTML(row, invPropertyRow) {
		var defaultObj = document.getElementById("defaultInvProperties");
		var defaultHTML = defaultObj.innerHTML;
		defaultHTML = replaceChars(defaultHTML, "[ROW_NUMBER]", row);
		defaultHTML = replaceChars(defaultHTML, "[INV_PROPERTY_ROW_DISPLAY]", invPropertyRow);
		defaultHTML = replaceChars(defaultHTML, "[INV_PROPERTY_ROW]", (invPropertyRow - 1));

		var itemNumber = frm.PoLine_itemNumber[row].value;
		var icRc = frm.icRc[row].value;
		var icPoLine = frm.ReceiptLine_icPoLine[row].value ;

		if (isEmpty(itemNumber)) {
			itemNumber = "N/A";
		}
		if (isEmpty(icRc)) {
			icRc = "0";
		}
		if (isEmpty(icPoLine)) {
			icPoLine = "0" ;
		}

		defaultHTML = replaceChars(defaultHTML, "[INV_PROPERTY_ITEM_NUMBER]", itemNumber);
		defaultHTML = replaceChars(defaultHTML, "[INV_PROPERTY_IC_PO_LINE]", icPoLine);
		defaultHTML = replaceChars(defaultHTML, "[INV_PROPERTY_IC_RC]", icRc);

		return defaultHTML;
	}

	function addNewInvProperty(row) {
		var myTable = document.getElementById("invPropertiesTable_" + row);
		var currentRows = myTable.rows.length;
		var invPropertyRow = currentRows + 1;
		var myRow;
		var myCell;

		frm.InvProperty_flag.value = "A";
		if( frm.count_flag[row].length == undefined ){
			frm.count_flag[row].value = frm.count_flag[row].value*1 + sum*1;
		}

		myRow = createRow(myTable);

		myCell = createCell(myRow);

		myCell.innerHTML = getInvPropertiesHTML(row, invPropertyRow);
	}

	function deleteInvProperty(row, invPropertyRow) {
		var itemInvPropertyRow = 0;
		var prevInvPropertyRows = 0;
		var myTable;
		var currentRows;
		var totalRows;

		frm.InvProperty_flag.value = "A";
		if( frm.count_flag[row].length == undefined ){
			frm.count_flag[row].value = frm.count_flag[row].value*1 - sum*1;
		}

		for (var i = 0; i < row; i++) {
			myTable = document.getElementById("invPropertiesTable_" + i);

			if (myTable != undefined) {
				currentRows = myTable.rows.length;
				prevInvPropertyRows = prevInvPropertyRows + currentRows;
			}
		}
		invPropertyRow = invPropertyRow + prevInvPropertyRows;
		myTable = document.getElementById("invPropertiesTable_" + row);
		currentRows = myTable.rows.length;

		totalRows = currentRows + prevInvPropertyRows;

		if (totalRows == 0) {
			return;
		}
		else if (totalRows == 1) {
				frm.InvProperty_tagNumber[0].value = "";
				frm.InvProperty_serialNumber[0].value = "";
				frm.InvProperty_contract[0].value = "";
				frm.InvProperty_shipNumber[0].value = "";
				frm.InvProperty_poNumber[0].value = "";
				frm.InvProperty_itemNumber[0].value = "";
				frm.InvProperty_icRc[0].value = "";
				frm.InvProperty_remarks[0].value =  "";
				frm.InvProperty_icPoLine[0].value = "" ;
		}
		else if (totalRows > 1) {
			for (var i = invPropertyRow; i < totalRows; i++) {
				var tagNumber = frm.InvProperty_tagNumber[i + 1].value;
				var serialNumber = frm.InvProperty_serialNumber[i + 1].value;
				var contract = frm.InvProperty_contract[i + 1].value;
				var shipNumber = frm.InvProperty_shipNumber[i + 1].value;
				var poNumber = frm.InvProperty_poNumber[i + 1].value;
				var itemNumber = frm.InvProperty_itemNumber[i + 1].value;
				var icRc = frm.InvProperty_icRc[i + 1].value;
				var remarks = frm.InvProperty_remarks[i + 1].value;
				var icPoLine = frm.InvProperty_icPoLine[i + 1].value ;

				frm.InvProperty_tagNumber[i].value = tagNumber;
				frm.InvProperty_serialNumber[i].value = serialNumber;
				frm.InvProperty_contract[i].value = contract;
				frm.InvProperty_shipNumber[i].value = shipNumber;
				frm.InvProperty_poNumber[i].value = poNumber;
				frm.InvProperty_itemNumber[i].value = itemNumber;
				frm.InvProperty_icRc[i].value = icRc;
				frm.InvProperty_remarks[i].value = remarks;
				frm.InvProperty_icPoLine[i].value = icPoLine ;
			}
		}

		if (currentRows > 1) {
			myTable.deleteRow(currentRows - 1);
		}
	}

	function validateForm() {
		var handlerList = frm.handler.value;

		if (handlerList.indexOf("ReceiptUpdate") >= 0 || handlerList.indexOf("ReceiptCreateFromNothing") >= 0 || handlerList.indexOf("ReceiptCreateForward") >= 0) {
			var shipToInv = frm.ReceiptHeader_shipToInv.value;

//			if (shipToInv == "Y") {
				if (handlerList.lastIndexOf(";") != handlerList.length) {
					handlerList = handlerList + ";";
				}
				frm.handler.value = handlerList + "InvPropertyAddListHandler"

				var defaultInvProperties = document.getElementById("defaultInvProperties");
				if (defaultInvProperties != null && defaultInvProperties != undefined) {
					defaultInvProperties.innerHTML = "";
				}
//			}
		}
		return true;
	}

	function setInvPropertyIcRc(icRc, row) {
		var invProperties = document.getElementsByName("InvProperty_receiptRow");
		var invPropertyCount = 0;

		frm.icRc[row].value = icRc;

		if (invProperties != undefined) {
			invPropertyCount = invProperties.length;
		}

		for (var i=0; i < invPropertyCount; i++) {
			var receiptRow = invProperties[i].value;
			if (receiptRow == row) {
				frm.InvProperty_icRc[i].value = icRc;
			}
		}
	}

	function getItemInfo(row) {
		if (isEmpty(frm.PoLine_itemNumber[row].value)) {
			// No need to call the server if the item number is empty, but clear altItemNumber
			frm.PoLine_altItemNumber[row].value = "";
		} else {
			var icPoHeader = frm.PoHeader_icPoHeader.value;
			var icPoLine = frm.PoLine_icPoLine[row].value;
			var itemNumber = frm.PoLine_itemNumber[row].value;
			var shipToInv = frm.ReceiptHeader_shipToInv.value;

			popupParameters = "PoHeader_icPoHeader=" + icPoHeader + ";PoLine_icPoHeader=" + icPoHeader + ";PoLine_icPoLine=" + icPoLine + ";PoLine_itemNumber=" + itemNumber + ";currentRow=" + row + ";shipToInv=" + shipToInv;

			setLookupParameters('/receipts/rec_get_poline_info.jsp', 'ReceiptPoLineItemLookup');
			displayArea('getInfoFrame');
			document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';
		}
	}

	function altItemLookup(row) {
		closeOpenWindows();
		var itemNumber = frm.PoLine_itemNumber[row].value;

	  	popupParameters = "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=%" + itemNumber + "%;logicalOperator=AND;originalFilter=Y;sort=N";
		popupParameters = popupParameters + ";formField=PoLine_itemNumber;index=" + row + ";browseName=itemcrossref;allowBrowse=" + frm.allowBrowse.value;

		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

// End Hide script -->
</SCRIPT>