<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_autoNumber = propertiesManager.getProperty("AUTONUMBER", "AutoInv", "N");
	String s_appendAltNo = propertiesManager.getProperty("INVENTORY", "APPEND ALTNO", "N");
	String s_itemCrossRef = propertiesManager.getProperty("INVENTORY", "itemCrossRef", "N");
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String s_item_approvals = propertiesManager.getProperty("ITEM APPROVALS", "APPROVENEWINVENTORYITEM", "N");
	String	s_action = (String) request.getAttribute("itemAction");
	String	s_title = "";
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));

	if (HiltonUtility.isEmpty(s_action))
	{
		s_action = "CREATE";
	}

	String s_item_number = "";
	String s_description = "";
	String s_source = "";
	String s_taxable = "";
	String s_chargeable = "";
	String s_kit = "";
	String s_restricted_item = "";
	String s_ariba_export = "";
	String s_cap_property = "";
	String s_gfp_property = "";
	String s_fgp_property = "";
	String s_usage_recalc = "";
	String s_ss_interface = "";
	String s_action_code = "";
	String s_asset_code = "";
	String s_receipt_required = "";
	String s_buyer_code = "";
	String s_buyer_name = "";
	String altPartNo1 = "" ;
	String altPartNo2 = "" ;
	String altPartNo3 = "" ;
	String s_nsnNumber = "" ;
	String s_critSparePart = "";
	String s_status = "";
	String s_disable_save="";

	String s_item_type = "INV" ;
	String s_barcode_data = "" ;
	String s_drawing_no = "" ;

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_factor = HiltonUtility.getBigDecimalFormatted(new BigDecimal(1), 5);
	BigDecimal bd_cost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	BigDecimal bd_issue_cost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	BigDecimal bd_average_cost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	BigDecimal bd_last_cost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	BigDecimal bd_variance = HiltonUtility.getBigDecimalFormatted(bd_zero, 5);
	BigDecimal bd_moh_total = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_eoq_total = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_moh_months = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_eoq_months = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_pull_increment = HiltonUtility.getBigDecimalFormatted(new BigDecimal(1), 5);
	boolean	   validateError = false ;

	InvItem invItem = (InvItem) request.getAttribute("invItem");
	s_item_number = invItem.getItemNumber();

	s_description = invItem.getDescription();
	s_source = invItem.getSource();
	s_taxable = invItem.getTaxable();
	s_chargeable = invItem.getChargable();
	s_kit = invItem.getKit();
	s_restricted_item = invItem.getRestrictedItem();
	s_ariba_export = invItem.getAribaExport();
	s_cap_property = invItem.getCapProperty();
	s_gfp_property = invItem.getGfpProperty();
	s_fgp_property = invItem.getFgpProperty();
	s_usage_recalc = invItem.getUsageRecalc();
	s_ss_interface = invItem.getSsInterface();
	s_action_code = invItem.getActionCode();
	s_asset_code = invItem.getAssetCode();
	s_receipt_required = invItem.getReceiptRequired();
	s_buyer_code = invItem.getBuyerCode();
	s_nsnNumber = invItem.getNsnNumber() ;
	s_critSparePart = HiltonUtility.ckNull((String)invItem.getCritSparePart());
	s_status = HiltonUtility.ckNull(invItem.getApproveStatus());
	if(s_status.compareTo(DocumentStatus.INV_APPROVING) >= 0 && s_status.compareTo(DocumentStatus.INV_APPROVED) <= 0)
	{
		s_disable_save = "Y";
	}
	s_drawing_no = invItem.getDrawingNo() ;
	s_barcode_data = invItem.getBarcodeData() ;
	s_item_type = invItem.getItemType() ;

	bd_factor = HiltonUtility.getBigDecimalFormatted(invItem.getFactor(), 5);
	bd_cost = HiltonUtility.getFormattedPrice(invItem.getCost(), oid);
	bd_issue_cost = HiltonUtility.getFormattedPrice(invItem.getIssueCost(), oid);
	bd_average_cost = HiltonUtility.getFormattedPrice(invItem.getAverageCost(), oid);
	bd_last_cost = HiltonUtility.getFormattedPrice(invItem.getLastCost(), oid);
	bd_variance = HiltonUtility.getBigDecimalFormatted(invItem.getVariance(), 5);
	bd_moh_total = HiltonUtility.getFormattedQuantity(invItem.getMohTot(), oid);
	bd_eoq_total = HiltonUtility.getFormattedQuantity(invItem.getEoqTot(), oid);
	bd_moh_months = HiltonUtility.getFormattedQuantity(invItem.getMohMonths(), oid);
	bd_eoq_months = HiltonUtility.getFormattedQuantity(invItem.getEoqMonths(), oid);
	bd_pull_increment = HiltonUtility.getFormattedQuantity(invItem.getPullIncrement(), oid);

	List itemCrossRefList = (List) request.getAttribute("itemCrossRefList") ;
	if (s_nsnNumber == null) s_nsnNumber = "" ;
	int c = 0;
	for (int x = 0; x < itemCrossRefList.size(); x++) {
		ItemCrossRef xref = (ItemCrossRef) itemCrossRefList.get(x) ;
		String altNo = xref.getAltItemNumber() ;
		if (! altNo.equals(s_nsnNumber)) {
			if (c == 0) altPartNo1 = altNo ;
			if (c == 1) altPartNo2 = altNo ;
			if (c == 2) altPartNo3 = altNo ;
			c++ ;
		}
	}

	UserProfile owner = UserManager.getInstance().getUser(oid, invItem.getOwner());
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	if (s_action.equalsIgnoreCase("CREATE"))
	{
%>
<tsa:hidden name="InvItem_appointedFlag" value="N"/>
<%	} %>
<tsa:hidden name="InvItem_failurePage" value="/inventory/inv_item.jsp"/>
<tsa:hidden name="InvItem_itemType" value="<%=s_item_type%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormCatalog_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormData_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormProduct_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormState_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=HiltonUtility.ckNull(invItem.getIcHeaderHistory())%>"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="itemAction" value="<%=s_action%>"/>
<tsa:hidden name="newItemNumber" value=""/>
<tsa:hidden name="inventoryaction" value="APPROVE"/>
<tsa:hidden name="saveasFailurePage" value="/inventory/inv_item.jsp"/>
<tsa:hidden name="currentPage" value="/inventory/inv_item.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></div>
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
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Inventory #:</b></td>
			<td width=125px><%=invItem.getItemNumber()%></td>
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
<div id="mybuttons">
<%@ include file="/inventory/inv_approval_buttons.jsp" %>
</div>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td valign="top">
		<table border="0" cellpadding="0" cellspacing="0">
		<tr <%=HtmlWriter.isVisible(oid, "approvalNotes")%>>
			<td width=50x></td>
			<td colspan=3><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalNotes", "Notes")%>:</b></td>
		</tr>
		<tr>
			<td width=50x></td>
			<td <%=HtmlWriter.isVisible(oid, "approvalNotes")%>><TEXTAREA NAME="ApprovalLog_approverNotes" WRAP="VIRTUAL" ROWS="8" COLS="67"></TEXTAREA></td>
		</tr>
		</table>
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width=50px></td>
			<td nowrap align="left" valign="top" class=label width=300px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-description", "Description", true)%>:&nbsp;</td>
			<td></td>
		</tr>
		<tr>
			<td width=50px></td>
			<td>
				<textarea wrap="virtual" name="InvItem_description" tabindex=4 rows=6 cols=65 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);">${esapi:encodeForHTML(invItem.description)}</textarea>
			</td>
			<td>
				<table border="0" cellpadding="0" cellspacing="1">
<% if (s_itemCrossRef.equalsIgnoreCase("Y")) { %>
					<tr <%=HtmlWriter.isVisible(oid, "inv-nsnNumber")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-nsnNumber", "NSN")%>:&nbsp;</td>
						<td><%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo1")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo1", "Alt. Part #1")%>:&nbsp;</td>
						<td><%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo2")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo2", "Alt. Part #2")%>:&nbsp;</td>
						<td><%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo3")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo3", "Alt. Part #3")%>:&nbsp;</td>
						<td><%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %></td>
					</tr>
<% } %>
				</table>
			</td>
		</tr>
		</table>

		<hr width=600px align=center class=browseHR>
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width=100px></td>
			<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
				<tr <%=HtmlWriter.isVisible(oid, "inv-drawingNo")%>>
					<td nowrap align="right" width=120px><div id=drawingNoA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-drawingNo", "Drawing No.", true)%>:&nbsp;</div></td>
					<td><%=invItem.getDrawingNo()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-commodity")%>>
					<td nowrap align="right" width=120px><a href="javascript: browseCommodity('InvItem_commodity', 'commodity', '<%=PropertiesManager.getInstance(oid).getProperty("MISC", "COMMODITYTYPE", "")%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-commodity", "Commodity", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getCommodity()%></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-commodityName", "Commodity Name")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%>><%=CommodityManager.getInstance().getCommodityDescription(oid, invItem.getCommodity())%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
					<td nowrap align="right"><div id=unitOfIssueA><a href="javascript: browseLookup('InvItem_unitOfIssue', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfIssue", "Unit of Issue", true)%>:</a>&nbsp;</div></td>
					<td><%=invItem.getUnitOfIssue()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-conversionFactor")%>>
					<td nowrap align="right"><div id=conversionFactorA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-conversionFactor", "Conversion Factor", true)%>:&nbsp;</div></td>
					<td><%=bd_factor%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-issueCost")%>>
					<td nowrap align="right"><div id=issueCostA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-issueCost", "Issue Cost", true)%>:&nbsp;</div></td>
					<td><%=bd_issue_cost%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitCost", "Unit Cost", true)%>:&nbsp;</td>
					<td><%=bd_cost%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfOrder")%>>
					<td nowrap align="right"><a href="javascript: browseLookup('InvItem_unitOfOrder', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfOrder", "Unit Of Order", true)%>:</A>&nbsp;</td>
					<td><%=invItem.getUnitOfOrder()%></td>
				</tr>
		<% if (s_duomRequired.equalsIgnoreCase("Y")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-duomUmCode")%>>
					<td nowrap align="right"><a href="javascript: browseLookup('InvItem_duomUmCode', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-duomUmCode", "Secondary UM", true)%>:</A>&nbsp;</td>
					<td><%=invItem.getDuomUmCode()%></td>
				</tr>
		<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-averageCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-averageCost", "Average Cost")%>:&nbsp;</td>
					<td><%=bd_average_cost%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-lastCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-lastCost", "Last Cost")%>:&nbsp;</td>
					<td><%=bd_last_cost%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-variance")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-variance", "Variance", true)%>:&nbsp;</td>
					<td><%=bd_variance%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-receiptOptions")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-receiptOptions", "Receipt Option")%>:&nbsp;</td>
					<% if (s_receipt_required.equals("R")) { %><td>Receipt Required</td> <% } %>
					<% if (s_receipt_required.equals("P")) { %><td>Previously Received</td> <% } %>
					<% if (s_receipt_required.equals("N")) { %><td>No Receipt Required</td> <% } %>
					<% if (s_receipt_required.equals("E")) { %><td>End User Receipt</td> <% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST02")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf2Code', 'LN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST02", "UDF 2", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf2Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST04")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST04", "UDF 4", true)%>:&nbsp;</td>
					<td><%=invItem.getUdf4Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST06")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf6Code','ST06');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST06", "UDF 6", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf6Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST08")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf8Code','ST08');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST08", "UDF 8", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf8Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST10")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf10Code','ST10');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST10", "UDF 10", true)%>:&nbsp;</td>
					<td><%=invItem.getUdf10Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST12")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf12Code','ST12');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST12", "UDF 12", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf12Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST14")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf14Code','ST14');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST14", "UDF 14", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf14Code()%></td>
				</tr>
				</table>
			</td>
			<td width=100px></td>
			<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
				<tr <%=HtmlWriter.isVisible(oid, "inv-abcCode")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-abcCode", "ABC Code", true)%>:&nbsp;</td>
					<td><%=invItem.getAbcCode()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-totalMinOnHand")%>>
					<td nowrap align="right"><div id=totalMinOnHandA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-totalMinOnHand", "Total Min. On Hand")%>:&nbsp;</div></td>
					<td><%=bd_moh_total%></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-totalEconomicOrderQuantity")%>>
					<td nowrap align="right"><div id=totalEconomicOrderQuantityA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-totalEconomicOrderQuantity", "Total Econ. Order Qty")%>:&nbsp;</div></td>
					<td><%=bd_eoq_total%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-minOnHandMonths")%>>
					<td nowrap align="right"><div id=minOnHandMonthsA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-minOnHandMonths", "Min. On-Hand Months", true)%>:&nbsp;</div></td>
					<td><%=bd_moh_months%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-economicOrderQuantityMonths")%>>
					<td nowrap align="right"><div id=economicOrderQuantityMonthsA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-economicOrderQuantityMonths", "Econ. Order Qty. Months", true)%>:&nbsp;</div></td>
					<td><%=bd_eoq_months%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-pullPackagingQuantity")%>>
					<td nowrap align="right"><div id=pullPackagingQuantityA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-pullPackagingQuantity", "Pull/Packaging Quantity", true)%>:&nbsp;</div></td>
					<td><%=bd_pull_increment%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-lastBlanketOrderNumber")%>>
					<td nowrap align="right"><div id=poNumberA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-lastBlanketOrderNumber", "Last Blanket Order #", true)%>:&nbsp;</div></td>
					<td><%=invItem.getPoNumber()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-buyer")%>>
					<td nowrap align="right"><div id=buyerA><a href="javascript: browseLookup('InvItem_buyerCode', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-buyer", "Buyer", true)%>:</a>&nbsp;</div></td>
					<td><%=s_buyer_code%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-buyerName")%>>
					<td nowrap align="right"><div id=buyerNameA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-buyerName", "Buyer Name")%>:&nbsp;</div></td>
					<td><%=buyer.getDisplayName()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-itemNumberSuperceded")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumberSuperceded", "Superceded by", true)%>:&nbsp;</td>
					<td><%=invItem.getItemNumberSuperceded()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-manufacturer")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-mfgName", "Manufacturer", true)%>:&nbsp;</td>
					<td><%=invItem.getMfgName()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-modelnumber")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-modelnumber", "Model #", true)%>:&nbsp;</td>
					<td><%=invItem.getModelNumber()%></td>
				</tr>
<% if (! s_itemCrossRef.equalsIgnoreCase("Y")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-nsnNumber")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-nsnNumber", "NSN")%>:&nbsp;</td>
					<td><%=invItem.getNsnNumber()%></td>
				</tr>
<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST01")%>>
					<td nowrap align="right">
	<% if (DictionaryManager.isLink(oid, "inv-ST01")) { %>
					<a href="javascript: browseStd('InvItem_udf1Code','ST01');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST01", "UDF 1", true)%>:</a>&nbsp;</td>
	<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST01", "UDF 1", true)%>:&nbsp;</td>
<%	}	%>
				<td><%=invItem.getUdf1Code()%></td>
				</tr>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST03")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST03", "UDF 3", true)%>:&nbsp;</td>
					<td><%=invItem.getUdf3Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST05")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf5Code','ST05');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST05", "UDF 5", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf5Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST07")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf7Code','ST07');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST07", "UDF 7", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf7Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST09")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf9Code','ST09');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST09", "UDF 9", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf9Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST11")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf11Code','ST11');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST11", "UDF 11", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf11Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST13")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf13Code','ST13');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST13", "UDF 13", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf13Code()%></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST15")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf15Code','ST15');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST15", "UDF 15", true)%>:</a>&nbsp;</td>
					<td><%=invItem.getUdf15Code()%></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>


		<hr width=475px align=center class=browseHR>

		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "inv-taxable")%> nowrap align="right" width=120px><div id=taxableA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-taxable", "Taxable")%>:&nbsp;</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-taxable")%>>
				<div id=taxableB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_taxable.equals("Y")){%>CHECKED<%}%> tabindex=180 value="Y" onclick="setCheckbox(frm.InvItem_taxable,0);">
				<tsa:hidden name="InvItem_taxable" value="<%=s_taxable%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%> nowrap align="right" width=100px><div id=chargeableA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-chargeable", "Chargeable")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%>>
				<div id=chargeableB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_chargeable.equals("Y")){%>CHECKED<%}%> tabindex=182 value="Y" onclick="setCheckbox(frm.InvItem_chargable,1);">
				<tsa:hidden name="InvItem_chargable" value="<%=s_chargeable%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-kit")%> nowrap align="right" width=90px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-kit", "Kit")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-kit")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_kit.equals("Y")){%>CHECKED<%}%> tabindex=184 value="Y" onclick="setCheckbox(frm.InvItem_kit,2);">
				<tsa:hidden name="InvItem_kit" value="<%=s_kit%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%> nowrap align="right" width=105px><div id=restrictedItemA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-restrictedItem", "Restricted Item")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%>>
				<div id=restrictedItemB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_restricted_item.equals("Y")){%>CHECKED<%}%> tabindex=186 value="Y" onclick="setCheckbox(frm.InvItem_restrictedItem,3);">
				<tsa:hidden name="InvItem_restrictedItem" value="<%=s_restricted_item%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%> nowrap align="right" width="120px"><tsa:label labelName="inv-critSparePart" defaultString="Critical Spare Part"/>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%>>
				<input type="checkbox" name="c_checkbox" <% if (s_critSparePart.equals("Y")) {%>checked<%}%> tabindex="187" value="Y" onclick="setCheckbox(frm.InvItem_critSparePart,4);">
				<tsa:hidden name="InvItem_critSparePart" value="<%=s_critSparePart%>"/>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "inv-asset")%> nowrap align="right" width=120px><div id=assetA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-asset", "Asset")%>:&nbsp;</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-asset")%>>
				<div id=assetB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_asset_code.equals("Y")){%>CHECKED<%}%> tabindex=188 value="Y" onclick="setCheckbox(frm.InvItem_assetCode,5);">
				<tsa:hidden name="InvItem_assetCode" value="<%=s_asset_code%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%> nowrap align="right" width=105px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-sAndSInterface", "S&S Interface")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_ss_interface.equals("Y")){%>CHECKED<%}%> tabindex=189 value="Y" onclick="setCheckbox(frm.InvItem_ssInterface,6);">
				<tsa:hidden name="InvItem_ssInterface" value="<%=s_ss_interface%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%> nowrap align="right" width=100px><div id=capAssetA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-capProperty", "Cap. Asset")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%>>
				<div id=capAssetB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_cap_property.equals("Y")){%>CHECKED<%}%> tabindex=190 value="Y" onclick="checkOnlyOne(6);setCheckbox(frm.InvItem_capProperty,7);">
				<tsa:hidden name="InvItem_capProperty" value="<%=s_cap_property%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%> nowrap align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-gfpProperty", "GFP")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_gfp_property.equals("Y")){%>CHECKED<%}%> tabindex=194 value="Y" onclick="checkOnlyOne(7);setCheckbox(frm.InvItem_gfpProperty,8);">
				<tsa:hidden name="InvItem_gfpProperty" value="<%=s_gfp_property%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%> nowrap align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-fgpProperty", "FGP")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_fgp_property.equals("Y")){%>CHECKED<%}%> tabindex=198 value="Y" onclick="checkOnlyOne(8);setCheckbox(frm.InvItem_fgpProperty,9);">
				<tsa:hidden name="InvItem_fgpProperty" value="<%=s_fgp_property%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%> nowrap align="right" width=100px><div id=usageRecalcA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-usageRecalc", "Usage Recalc")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%>>
				<div id=usageRecalcB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_usage_recalc.equals("Y")){%>CHECKED<%}%> tabindex=200 value="Y" onclick="setCheckbox(frm.InvItem_usageRecalc,10);">
				<tsa:hidden name="InvItem_usageRecalc" value="<%=s_usage_recalc%>"/>
				</div>
			</td>
		</tr>
		</table>

		<hr width=475px align=center class=browseHR>
		<table border=0 cellpadding=1 cellspacing=0 width=475px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
						<% if (invItem.getStatus().equals("01")) { %> <td>Temporary</td> <% } %>
						<% if (invItem.getStatus().equals("02")) { %> <td>Permanent</td> <% } %>
						<% if (invItem.getStatus().equals("03")) { %> <td>Inactive</td> <% } %>
						<% if (invItem.getStatus().equals("04")) { %> <td>On Hold</td> <% } %>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedDate(invItem.getDateEntered(), oid, userDateFormat)%>
					</td>
				</tr>
				<tr id="dateExpires">
					<td align=right>Date Expires:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedDate(invItem.getDateExpires(), oid, userDateFormat)%></td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvItem_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td><%=invItem.getOwner()%></td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td><%=owner.getDisplayName()%></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

	</td>
</tr>
</table>

<br>
<br>

<% if(!s_disable_save.equalsIgnoreCase("Y")) { %>
<table border="0" cellpadding="0" cellspacing="0" width=600px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveItem(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>
<% } %>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function approveMe()
	{
		if (frm.ApprovalLog_approverNotes)
		{
			if (frm.ApprovalLog_approverNotes.value == null || frm.ApprovalLog_approverNotes.value == "")
			{
				alert("Please enter approver Notes!");
				return;
			}
		}
		hideAreaWithBlock("approve_link");
		doSubmit("/inventory/inv_forward.jsp", "InventoryApprove");
	}

	function rejectMe()
	{
		frm.inventoryaction.value = "REJECT";
		if (frm.ApprovalLog_approverNotes.value.length <= 0)
		{
			alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nobuyernotes", "Please first enter notes explaining why you are rejecting this Invoice")%>.');
		}
		else
		{
			//doSubmit("/invoice/iv_forward_options.jsp", "InvoiceReject");
			doSubmit("/inventory/inv_forward.jsp", "InventoryReject");
		}
	}


// End Hide script -->
</SCRIPT>
