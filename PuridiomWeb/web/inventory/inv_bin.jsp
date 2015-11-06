<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<tsa:hidden name="fromPage" value="inv_bin"/>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String invItemNumber = (String) request.getAttribute("InvItem_itemNumber");
	String s_bin_item_number = (String) request.getAttribute("InvBinLocation_itemNumber");
	String s_inv_item_number = (String) request.getAttribute("InvLocation_itemNumber");
	String s_inv_item_location = (String) request.getAttribute("InvLocation_itemLocation");
	String fromPage = (String)request.getAttribute("fromPage");
	String propertyListSize = (String)request.getAttribute("propertyListSize");

	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;

	String	s_action = (String) request.getAttribute("binAction");
	String	s_title = "";

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	String s_bin_aisle = "";
	String s_bin_bin = "";
	BigDecimal bd_bin_cost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	String s_bin_HdrIc = "";
	String s_bin_IcRc = "";
	String s_bin_IcRecLine = "";
	String s_bin_itemDate = "";
	String s_bin_itemLocation = "";
	String s_bin_itemNumber = "";
	String s_bin_locrow = "";
	String s_bin_lot = "";
	String s_bin_manufactNo = "";
	BigDecimal bd_bin_qtyAlloc = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_bin_qtyOnHand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	String s_bin_tier = "";
	String s_bin_udf1 = "";
	String s_bin_udf2 = "";
	String s_bin_udf3 = "";
	String s_bin_udf4 = "";
	String s_bin_udf5 = "";
	String s_bin_vendorId = "";
	String s_bin_chargeCode = "";
	BigDecimal b_bin_IcRc = null;
	BigDecimal bd_qty_avail = HiltonUtility.getFormattedQuantity(bd_zero, oid);

	BigDecimal bd_bin_duomQtyAlloc = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_bin_duomQtyOnHand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_bin_duomAvailable = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	String isShelfLifeRqd = HiltonUtility.ckNull((String) request.getAttribute("isShelfLifeRqd"));
	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Inventory Bin";
		s_bin_itemDate = HiltonUtility.getFormattedDate(d_today, oid, userDateFormat);
	}
	else
	{
		s_title = "Bin Location Adjustment";

		InvBinLocation invBinLocation = (InvBinLocation) request.getAttribute("invBinLocation");

		s_bin_aisle = invBinLocation.getAisle();
		if (s_bin_aisle==null){s_bin_aisle = "";}

		s_bin_bin = invBinLocation.getBin();
		if (s_bin_bin==null){s_bin_bin = "";}

		bd_bin_cost = HiltonUtility.getFormattedPrice(invBinLocation.getCost(), oid);
		s_bin_HdrIc = invBinLocation.getHdrIc();

		b_bin_IcRc = invBinLocation.getIcRc();
		BigDecimal b_bin_IcRecLine = invBinLocation.getIcRecLine();

		if (b_bin_IcRc != null) {	s_bin_IcRc = String.valueOf(b_bin_IcRc);	}
		if (b_bin_IcRecLine != null) {	s_bin_IcRecLine = String.valueOf(b_bin_IcRecLine);	}

		s_bin_itemDate = HiltonUtility.getFormattedDate(invBinLocation.getItemDate(), oid);

		s_bin_itemLocation = invBinLocation.getItemLocation();
		s_bin_itemNumber = invBinLocation.getItemNumber();
		s_bin_locrow = invBinLocation.getLocrow();
		if (s_bin_locrow==null){s_bin_locrow = "";}
		s_bin_lot = invBinLocation.getLot();
		if (s_bin_lot==null){s_bin_lot = "";}
		s_bin_manufactNo = invBinLocation.getManufactNo();
		if (s_bin_manufactNo==null){s_bin_manufactNo = "";}

		bd_bin_qtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
		bd_bin_qtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);

		if (fromPage != null && fromPage.equals("inv_property_list") && propertyListSize != null &&
			propertyListSize.length() > 0) {
			bd_bin_qtyOnHand = new BigDecimal(propertyListSize);
		}

		bd_bin_duomQtyAlloc = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomQtyAlloc(), oid);
		bd_bin_duomQtyOnHand = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomQtyOnHand(), oid);

		s_bin_tier = invBinLocation.getTier();
		if (s_bin_tier==null){s_bin_tier = "";}
		s_bin_udf1 = invBinLocation.getUdf1Code();
		if (s_bin_udf1==null){s_bin_udf1 = "";}
		s_bin_udf2 = invBinLocation.getUdf2Code();
		if (s_bin_udf2==null){s_bin_udf2 = "";}
		s_bin_udf3 = invBinLocation.getUdf3Code();
		if (s_bin_udf3==null){s_bin_udf3 = "0";}
		s_bin_udf4 = invBinLocation.getUdf4Code();
		if (s_bin_udf4==null){s_bin_udf4 = "";}
		s_bin_udf5 = invBinLocation.getUdf5Code();
		if (s_bin_udf5==null){s_bin_udf5 = "";}
		s_bin_vendorId = invBinLocation.getVendorId();
		if (s_bin_vendorId==null){s_bin_vendorId = "";}
		s_bin_chargeCode = invBinLocation.getChargeCode();
		if (s_bin_chargeCode==null){s_bin_chargeCode = "";}

		String s_qty_avail = (String) request.getAttribute("qtyAvailable");
		if (s_qty_avail == null)
		{
			s_qty_avail = "0";
		}
		bd_qty_avail = new BigDecimal(s_qty_avail);
		bd_qty_avail = HiltonUtility.getFormattedQuantity(bd_qty_avail, oid);
		if ( (bd_bin_duomQtyOnHand.subtract(bd_bin_duomQtyAlloc)).compareTo(new BigDecimal(0)) > 0)
		{
			bd_bin_duomAvailable = bd_bin_duomQtyOnHand.subtract(bd_bin_duomQtyAlloc);
		}

	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="itemAction" value="UPDATE"/>
<tsa:hidden name="InvBinLocation_icRc" value="<%=s_bin_IcRc%>"/>
<tsa:hidden name="InvBinLocation_hdrIc" value="<%=s_bin_HdrIc%>"/>
<tsa:hidden name="InvBinLocation_icRecLine" value="<%=s_bin_IcRecLine%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=s_inv_item_number%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=s_inv_item_location%>"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=s_bin_item_number%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="binAction" value="<%=s_action%>"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>
<tsa:hidden name="fromSave" value=""/>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_bin_udf3%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
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
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="50%"><a href="javascript: validateBinLocation(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			 <td align=left width="50%"><a href="javascript: validateBinLocation(); void(0);">Validate Information</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item/Part #")%>:</b>&nbsp;</td>
			 <td align=left width="50%"><%=s_bin_item_number%></td>
		</tr>
		<tr>
			 <td align=right width="50%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:</b>&nbsp;</td>
			 <td align=left width="50%">${esapi:encodeForHTML(InvBinLocation_itemLocation)}</td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<!--tr>
				<td align=left class=browseRow>
					<a href="javascript: doSubmit('/inventory/add_inv_bin.jsp','DoNothing'); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_add_item.gif" BORDER="0" ALT="Add Location">&nbsp;Add Another Bin Location</a>
				</td>
			</tr-->
			<tr>
				<td>
					<table border=0 cellpadding=0 cellspacing=0 align=center>
					<tr>
						<td>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-aisle")%>><b><a href="javascript: browseXrefCombo('InvBinLocation_aisle', 'xref-combo-aisle'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-aisle", "Aisle", true)%></a></b>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-row")%>><b><a href="javascript: browseXrefCombo('InvBinLocation_locrow', 'xref-combo-row'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-row", "Row", true)%></a></b>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-tier")%>><b><a href="javascript: browseXrefCombo('InvBinLocation_tier', 'xref-combo-tier'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-tier", "Tier", true)%></a></b>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-bin")%>><b><a href="javascript: browseXrefCombo('InvBinLocation_bin', 'xref-combo-bin'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-bin", "Bin", true)%></a></b>&nbsp;</td>
					</tr>
					<tr>
						<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Location")%>:&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-aisle")%>><input type=text name="InvBinLocation_aisle" size=15 maxlength=15 tabindex=31 value="<%=s_bin_aisle%>" onchange="upperCase(this);"></td>
						<td <%=HtmlWriter.isVisible(oid, "inv-row")%>><input type=text name="InvBinLocation_locrow" size=15 maxlength=15 tabindex=33 value="<%=s_bin_locrow%>" onchange="upperCase(this);"></td>
						<td <%=HtmlWriter.isVisible(oid, "inv-tier")%>><input type=text name="InvBinLocation_tier" size=15 maxlength=15 tabindex=35 value="<%=s_bin_tier%>" onchange="upperCase(this);"></td>
						<td <%=HtmlWriter.isVisible(oid, "inv-bin")%>><input type=text name="InvBinLocation_bin" size=15 maxlength=15 tabindex=37 value="<%=s_bin_bin%>" onchange="upperCase(this);"></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
				<td>
					<table border=0 cellpadding=0 cellspacing=0 align=center width=450px>
<%	if (s_action.equalsIgnoreCase("UPDATE")) { %>
					<tr <%=HtmlWriter.isVisible(oid, "bin-inv-reasonCode")%>>
						<td width=125px align=right nowrap><a href="javascript: browseStd('InvBinLocation_reasonCode', 'IVRC'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-reasonCode", "Reason Code", true)%>:</a>&nbsp;</td>
						<td><input type=text name="InvBinLocation_reasonCode" size=15 maxlength=15 tabindex=1 onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "bin-inv-reasonDescription")%>>
						<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-reasonDescription", "Reason Description")%>:&nbsp;</td>
						<td><input type=text name="InvBinLocation_reasonDescription" size=60 maxlength=60 tabindex=3 onchange="upperCase(this);"></td>
					</tr>
<%	} %>
					</table>
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
				<td>
					<table border=0 cellpadding=0 cellspacing=0 align=center width=450px>
					<tr>
						<td>
							<table border=0 cellpadding=0 cellspacing=0>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-supplier")%>>
								<td width=125px align=right nowrap><a href="javascript: browseLookup('InvBinLocation_vendorId', 'supplier'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-supplier", "Supplier", true)%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_vendorId" size=15 maxlength=15 tabindex=5 value="<%=s_bin_vendorId%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-manufacturerNumber")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-manufacturerNumber", "Manufacturer No", true)%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_manufactNo" size=15 maxlength=20 tabindex=7 value="<%=s_bin_manufactNo%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-dateInInventory")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-dateInInventory", "Date In Inventory")%>:&nbsp;</td>
								<td nowrap>
									<input type=text name="InvBinLocation_itemDate" size=15 maxlength=15 tabindex=9 value="<%=s_bin_itemDate%>" onchange="checkDate(this);">
									<a href="javascript: show_calendar('InvBinLocation_itemDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-unitCost")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-unitCost", "Unit Cost")%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_cost" size=15 tabindex=11 value="<%=bd_bin_cost%>" style="text-align:right"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-lotNumber")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-lotNumber", "Lot Number", true)%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_lot" size=15 maxlength=15 tabindex=13 value="<%=s_bin_lot%>" onchange="upperCase(this);"></td>
							</tr>

							<tr>
								<td width=125px align=right nowrap>&nbsp;</td>
								<td align=center nowrap><b>Primary UOM</b></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
								<td align=center nowrap><b>Secondary UOM</b></td>
<% } %>
							</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
					<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfIssue", "Unit Of Issue")%>:&nbsp;</td>
					<td><input type=text size=15 name="InvLocation_unitOfIssue" TABINDEX=2 value="${invItem.unitOfIssue}" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="InvLocation_duomUmCode" TABINDEX=2 value="<%=s_duomUmCode%>" style="text-align:right" disabled></td>
<% } %>
				</tr>

							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-quantityOnHand")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-quantityOnHand", "Quantity on Hand")%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_qtyOnHand" size=15 tabindex=15 value="<%=bd_bin_qtyOnHand%>" onchange="addUp();" style="text-align:right"></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
								<td><input type=text name="InvBinLocation_duomQtyOnHand" size=15 tabindex=15 value="<%=bd_bin_duomQtyOnHand%>" onchange="addUp();" style="text-align:right"></td>
<% } %>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-allocated")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-allocated", "Allocated")%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_qtyAlloc" size=15 tabindex=17 value="<%=bd_bin_qtyAlloc%>" onchange="addUp();" style="text-align:right"></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
								<td><input type=text name="InvBinLocation_duomQtyAlloc" size=15 tabindex=17 value="<%=bd_bin_duomQtyAlloc%>" onchange="addUp();" style="text-align:right"></td>
<% } %>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-available")%>>
								<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-available", "Available")%>:&nbsp;</td>
								<td><input type=text name="qtyAvailable" size=15 tabindex=19 value="<%=bd_qty_avail%>" disabled style="text-align:right"></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
								<td><input type=text name="duomQtyAvailable" size=15 tabindex=19 value="<%=bd_bin_duomAvailable%>" disabled style="text-align:right"></td>
<% } %>
							</tr>
							</table>
						</td>
						<td>
							<table border=0 cellpadding=0 cellspacing=0>
							<% if (!oid.equals("SRR10P")) { %>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN01")%>>
								<td width=150px align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf1Code', 'BN01');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN01", "UDF 1")%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_udf1Code" size=15 maxlength=15 tabindex=21 value="<%=s_bin_udf1%>" onchange="upperCase(this);"></td>
							</tr>
							<%} else { %>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN01")%>>
								<td width=150px align=right nowrap><a href="javascript: browseSetup('vsrr-proj','','InvBinLocation_udf1Code');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN01", "UDF 1")%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_udf1Code" size=15 maxlength=15 tabindex=21 value="<%=s_bin_udf1%>" onchange="upperCase(this);"></td>
							</tr>
							<%} %>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN02")%>>
								<td width=150px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN02", "UDF 2", true)%>:&nbsp;</td>
								<td nowrap>
									<input type=text name="InvBinLocation_udf2Code" size=15 maxlength=15 tabindex=23 value="<%=s_bin_udf2%>" onchange="checkDate(this);">
									<a href="javascript: show_calendar('InvBinLocation_udf2Code', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN03")%>>
								<td width=150px align=right nowrap><a href="javascript: browseLookup('InvBinLocation_udf3Code', 'msr');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN03", "UDF 3", true)%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_udf3Code" size=15 maxlength=15 tabindex=25 value="<%=s_bin_udf3%>" onchange="upperCase(this);"></td>
							</tr>
							<%	if (s_action.equalsIgnoreCase("UPDATE")) { %>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN04")%>>
								<td width=150px align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf4Code', 'BN04');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN04", "UDF 4", true)%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_udf4Code" size=15 maxlength=15 tabindex=27 value="<%=s_bin_udf4%>" onchange="upperCase(this);"></td>
							</tr>
							<%} %>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-BN05")%>>
								<td width=150px align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf5Code', 'BN05');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN05", "UDF 5", true)%>:</a>&nbsp;</td>
								<td><input type=text name="InvBinLocation_udf5Code" size=15 maxlength=15 tabindex=29 value="<%=s_bin_udf5%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "bin-inv-chargecode")%>>
								<td width=150px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-chargecode", "InvBinLocation_chargeCode", true)%>:&nbsp;</td>
								<td><input type=text name="InvBinLocation_chargeCode" size=15 maxlength=15 tabindex=29 value="<%=s_bin_chargeCode%>" onchange="upperCase(this);"></td>
							</tr>
							<tr>
								<td colspan=3>&nbsp;</td>
							</tr>
							<tr>
								<% if (!s_action.equals("CREATE") && serNoRequired.equalsIgnoreCase("Y")) { %>
									<td colspan=3 align="right">
										<a href="javascript: viewProperty();">
											<IMG SRC="<%=contextPath%>/images/asset3.gif" BORDER="0"
												ALT="Property Adjustment">
											Property Serial Numbers
										</a>
									</td>
								<% } else { %>
									<td colspan=3>&nbsp;</td>
								<% } %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem'); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	allowInputEdit(frm.InvBinLocation_udf3Code,false);

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);

			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function viewItem(row) {
		doSubmit('/requests/req_item.jsp', 'DoNothing');
	}


	function highlightItem(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
	}

	function removeItemHighlight(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
	}

	function submitThis()
	{
		if (eval(nfilter(frm.InvBinLocation_qtyOnHand)) < eval(nfilter(frm.InvBinLocation_qtyAlloc)))
		{
			alert("You cannot decrease the amount on hand below what is already allocated!");
		}
		else if (frm.InvBinLocation_aisle.value.length <= 0 && frm.InvBinLocation_locrow.value.length <= 0 && frm.InvBinLocation_tier.value.length <= 0 && frm.InvBinLocation_bin.value.length <= 0)
		{
			alert("At least one of the following need data: Aisle, row, tier, or bin.");
		}
		else if (frm.InvBinLocation_itemDate && !chkdate(frm.InvBinLocation_itemDate)) {
			alert("<%=DictionaryManager.getLabel(oid, "bin-inv-dateInInventory", "Date In Inventory")%> is not a valid date.");
			frm.InvBinLocation_itemDate.focus();
		}
		else if (frm.InvBinLocation_udf2Code && !chkdate(frm.InvBinLocation_udf2Code)) {
			alert("<%=DictionaryManager.getLabel(oid, "bin-inv-BN02", "UDF 2")%> is not a valid date.");
			frm.InvBinLocation_udf2Code.focus();
		}
		<%if(isShelfLifeRqd.equals("Y")){%> 
			else if (frm.InvBinLocation_udf2Code && isEmpty(frm.InvBinLocation_udf2Code.value)) {
				alert("A Shelf Life date is required for this item");
				frm.InvBinLocation_udf2Code.focus();
			}
		<%}%>
		else
		{
			frm.fromSave.value = "Y";
			if (frm.binAction.value == "CREATE")
			{
				//doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationAdd;InvBinLocationRetrieveByItem');
				doSubmit('/inventory/inv_bin_validation.jsp', 'InvBinLocationAdd;InvBinLocationValidate');
			}
			else
			{
				//doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationUpdate;InvBinLocationRetrieveByItem');
				doSubmit('/inventory/inv_bin_validation.jsp', 'InvBinLocationUpdate;InvBinLocationValidate');
			}
		}
	}

	function addUp()
	{
		var qtyOnHand = frm.InvBinLocation_qtyOnHand.value;
		var qtyAlloc = frm.InvBinLocation_qtyAlloc.value;
		var qtyAvailable = eval(qtyOnHand - qtyAlloc);
		frm.InvBinLocation_qtyOnHand.value = nformat(qtyOnHand, 2);
		frm.InvBinLocation_qtyAlloc.value = nformat(qtyAlloc, 2);
		frm.qtyAvailable.value = nformat(qtyAvailable, 2);
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
		qtyOnHand = frm.InvBinLocation_duomQtyOnHand.value;
		qtyAlloc = frm.InvBinLocation_duomQtyAlloc.value;
		qtyAvailable = eval(qtyOnHand - qtyAlloc);
		frm.InvBinLocation_duomQtyOnHand.value = nformat(qtyOnHand, 2);
		frm.InvBinLocation_duomQtyAlloc.value = nformat(qtyAlloc, 2);
		frm.duomQtyAvailable.value = nformat(qtyAvailable, 2);
<%}%>
	}

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvBinLocationUpdate") >= 0 || handler.indexOf("InvBinLocationAdd") >= 0) {
	      if ((frm.InvBinLocation_reasonCode != undefined) && isEmpty(frm.InvBinLocation_reasonCode.value))
	        	alertMessage += 'You should specify a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-reasonCode", "Reason Code")%>';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }
      if(strEnableAuditTrail == "Y"){
	  	buildAuditedFields();
      }
	  return true;
    }

    function onLoadComplete()
    {
    	if(strEnableAuditTrail == "Y"){
    		buildAuditFields();
    	}
    	hideArea("pageLoading");
    	displayArea("pageForm");
    	setFieldFocus();
    }
    function setAuditTables()
	{
		frm.auditTables.value = "InvBinLocation";
	}
	function getFields(el)
	{
		if(el.type != "hidden" && el.name.indexOf("InvBinLocation_") > -1)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
	function getFieldsJquery()
	{
		var jQuerySelector = ":input:not([type=hidden])[name^='InvBinLocation_']";
		var fieldsToAuditJquery = $(jQuerySelector);
		return fieldsToAuditJquery;
    }
    function buildAuditIc()
	{
		return frm.InvBinLocation_icRc.value;
	}

    function viewProperty() {
		frm.binAction.value = "PROPERTY";
		doSubmit('/inventory/inv_property_list.jsp', 'InvPropertyRetrieveByIcRc');
	}

	function browseXrefCombo(field, xml)
	{
		popupParameters = "";
		if (frm.InvBinLocation_aisle && trim(frm.InvBinLocation_aisle) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code1;operator==;filter_txt=" + frm.InvBinLocation_aisle.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_locrow && trim(frm.InvBinLocation_locrow) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code2;operator==;filter_txt=" + frm.InvBinLocation_locrow.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_tier && trim(frm.InvBinLocation_tier) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code3;operator==;filter_txt=" + frm.InvBinLocation_tier.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		if (frm.InvBinLocation_bin && trim(frm.InvBinLocation_bin) != "") {
			popupParameters = popupParameters + "colname=XrefCombo_code4;operator==;filter_txt=" + frm.InvBinLocation_bin.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		}
		browseLookup(field, xml);
	}

	function validateBinLocation()
	{
		if (frm.binAction.value == "CREATE") {
			doSubmit('/inventory/inv_bin_validation.jsp', 'InvBinLocationAdd;InvBinLocationValidate');
		} else {
			doSubmit('/inventory/inv_bin_validation.jsp', 'InvBinLocationUpdate;InvBinLocationValidate');
		}
	}

// End Hide script -->
</SCRIPT>