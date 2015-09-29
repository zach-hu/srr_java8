<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
hi my name is zach
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	Encoder encoder = DefaultEncoder.getInstance();
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
	String s_current_page = "/inventory/inv_item.jsp";
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
	String s_doc_comment="";

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
	BigDecimal bd_sum_qtyOnHand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_moh_months = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_eoq_months = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_pull_increment = HiltonUtility.getBigDecimalFormatted(new BigDecimal(1), 5);
	boolean	   validateError = false ;

	InvItem invItem;

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		invItem = new InvItem();
		s_taxable = "N";
		s_chargeable = "Y";
		s_asset_code = "N";
		s_action_code = "F";
		s_ariba_export = "N";
		s_cap_property = "N";
		s_gfp_property = "N";
		s_fgp_property = "N";
		s_usage_recalc = "N";
		s_source = "(Default)";
		s_restricted_item = "N";
		s_receipt_required = "R";
		s_critSparePart = "N";
		invItem.setOwner(uid);
		invItem.setStatus("02");
		invItem.setDateEntered(d_today);
		invItem.setDateExpires(d_today);
	}
	else
	{
		invItem = (InvItem) request.getAttribute("invItem");
		s_item_number = invItem.getItemNumber();

		if (invItem.getStatus().equals("04")) {
			validateError = true ;
		}
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
		s_doc_comment = invItem.getIcText().toString();
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
		bd_sum_qtyOnHand = HiltonUtility.getFormattedQuantity(invItem.getQtyOnHandInvBinLocation(), oid);
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
<tsa:hidden name="InvFormCatalog_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormData_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormProduct_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="InvFormState_itemNumber" value="<%=s_item_number%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=HiltonUtility.ckNull(invItem.getIcHeaderHistory())%>"/>
<tsa:hidden name="StdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="itemAction" value="<%=s_action%>"/>
<tsa:hidden name="newItemNumber" value=""/>
<% if (s_action.equalsIgnoreCase("CREATE")) { %>
	<tsa:hidden name="historyStatus" value="CREATE"/>
<% } %>
<tsa:hidden name="saveasFailurePage" value="/inventory/inv_item.jsp" />
<tsa:hidden name="currentPage" value="/inventory/inv_item.jsp"/>
<% if(!"".equals(s_doc_comment)) { %>
<tsa:hidden name="DocComment_icHeader" value="<%=s_doc_comment%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_doc_comment%>"/>
<tsa:hidden name="DocAttachment_icLine" value="0"/>
<% } else { %>
<tsa:hidden name="DocComment_icHeader" value="0"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="0"/>
<tsa:hidden name="DocAttachment_icLine" value="0"/>
<% } %>

<%String validate = (String)request.getAttribute("validate");
	if(validate == null){	validate = "FALSE";	}
	String validateAction = (String)request.getAttribute("validateAction");
	if(validateAction == null){	validateAction = "";	}
%>
<tsa:hidden name="validate" value="<%=validate%>"/>
<tsa:hidden name="validateAction" value="<%=validateAction%>"/>
<tsa:hidden name="autoNumber" value="<%=s_autoNumber%>"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12">Inventory Item Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
<%	if (invItem.getLastPo().length() > 0) { %>
		<tr>
			<td colspan="2" nowrap align="right">
				<b>Last Order:<%=invItem.getLastPo()%></b>
			</td>
		</tr>
<%	} %>
		</table>
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="600px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr><td class=error align=center><br><%=errorMsg%></td></tr>
<% if (validateError) { %>
<tr><td class=error align=center>Item availability will remain on hold until validation passes! </td></tr>
<% } %>
</table>

<br>
<% if (oid.equals("SRR10P")) { %>
<%@ include file="/inventory/inv_srr_item.jsp" %>
<%} else {%>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td valign="top">
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width=30px></td>
			<td nowrap align="left" class=label width=80px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item/Part #", true)%>:&nbsp;</td>
			<td>
				<INPUT TYPE="TEXT" NAME="InvItem_itemNumber" SIZE="25" MAXLENGTH="30" tabindex=2 value="<%=encoder.encodeForHTMLAttribute(s_item_number)%>"  onchange="upperCase(this);itemDuplicateCheck(this);" <%if (s_action.equalsIgnoreCase("UPDATE") || s_autoNumber.equalsIgnoreCase("Y")) { %> disabled <% } %> >
			</td>
<% if (s_action.equalsIgnoreCase("DISABLED-CREATE")) { %>
			<td <%=HtmlWriter.isVisible(oid, "inv-autonumber")%>>
				<div id=invAutoNumber>
				&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-autonumber", "Auto Number")%>:&nbsp;<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_autoNumber.equals("Y")){%>CHECKED<%}%> tabindex=32 value="Y" onclick="setCheckbox(frm.autoNumber,0); autoNumberCheck(frm.InvItem_itemNumber, this.checked);">
				</div>
			</td>
<% } else { %>
			<td width=120px></td>
<% } %>
			<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-actionCode", "Action Code")%>:&nbsp;</td>
			<td>
				<select name="InvItem_actionCode" tabindex=3 onchange="setFormTypeFields();" <%if (s_action.equalsIgnoreCase("UPDATE")) { %> disabled <% } %> >
					<option value="B" <% if (s_action_code.indexOf("B") >= 0) { %>SELECTED<%}%>>Buy</option>
					<option value="M" <% if (s_action_code.indexOf("M") >= 0) { %>SELECTED<%}%>>Make</option>
					<option value="F" <% if (s_action_code.indexOf("F") >= 0) { %>SELECTED<%}%>>Fill</option>
				</select>
			</td>
		</tr>
		</table>
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width=20px></td>
			<td nowrap align="left" valign="top" class=label width=300px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-description", "Description", true)%>:&nbsp;</td>
			<td></td>
		</tr>
		<tr>
			<td width=20px></td>
			<td>
				<textarea wrap="virtual" name="InvItem_description" tabindex=4 rows=6 cols=65 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this,255);">${esapi:encodeForHTML(invItem.description)}</textarea>
			</td>
			<td>
				<table border="0" cellpadding="0" cellspacing="1">
<% if (s_itemCrossRef.equalsIgnoreCase("Y")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "inv-nsnNumber")%>>
							<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-nsnNumber", "NSN")%>:&nbsp;</td>
							<td><INPUT TYPE="TEXT" NAME="InvItem_nsnNumber" SIZE="20" MAXLENGTH="20" tabindex=5 value="<%=encoder.encodeForHTMLAttribute(s_nsnNumber)%>" onchange="upperCase(this); <%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %>"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo1")%>>
							<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo1", "Alt. Part #1")%>:&nbsp;</td>
							<td><INPUT TYPE="TEXT" NAME="InvItem_altPartNo1" SIZE="20" MAXLENGTH="30" tabindex=6 value="<%=encoder.encodeForHTMLAttribute(altPartNo1)%>" onchange="upperCase(this); <%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %>"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo2")%>>
							<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo2", "Alt. Part #2")%>:&nbsp;</td>
							<td><INPUT TYPE="TEXT" NAME="InvItem_altPartNo2" SIZE="20" MAXLENGTH="30" tabindex=7 value="<%=encoder.encodeForHTMLAttribute(altPartNo2)%>" onchange="upperCase(this);<%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %>"></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "inv-altPartNo3")%>>
							<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-altPartNo3", "Alt. Part #3")%>:&nbsp;</td>
							<td><INPUT TYPE="TEXT" NAME="InvItem_altPartNo3" SIZE="20" MAXLENGTH="30" tabindex=8 value="<%=encoder.encodeForHTMLAttribute(altPartNo3)%>" onchange="upperCase(this);<%if (s_appendAltNo.equalsIgnoreCase("Y")) { %> updateDescription(); <% } %>"></td>
						</tr>
<% } %>
				</table>
			</td>
		</tr>
		</table>

		<hr width=475px align=center class=browseHR>
		<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
				<tr <%=HtmlWriter.isVisible(oid, "inv-drawingNo")%>>
					<td nowrap align="right" width=120px><div id=drawingNoA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-drawingNo", "Drawing No.", true)%>:&nbsp;</div></td>
					<td><div id=drawingNoB><INPUT TYPE="TEXT" NAME="InvItem_drawingNo" size=20 maxlength=30 tabindex=10 value="<%=encoder.encodeForHTMLAttribute(invItem.getDrawingNo())%>"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-commodity")%>>
					<td nowrap align="right" width=120px><a href="javascript: browseCommodity('InvItem_commodity', 'commodity', '<%=PropertiesManager.getInstance(oid).getProperty("MISC", "COMMODITYTYPE", "")%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-commodity", "Commodity", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_commodity" size=15 maxlength=15 tabindex=15 value="<%=encoder.encodeForHTMLAttribute(invItem.getCommodity())%>"></td>
				</tr>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-commodityName", "Commodity Name")%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%>><input type=text name="as_commodityName" size=25 value="<%=CommodityManager.getInstance().getCommodityDescription(oid, invItem.getCommodity())%>" onfocus="this.blur();" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
					<td nowrap align="right"><div id=unitOfIssueA><a href="javascript: browseLookup('InvItem_unitOfIssue', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfIssue", "Unit of Issue", true)%>:</a>&nbsp;</div></td>
					<td><div id=unitOfIssueB><INPUT TYPE="TEXT" NAME="InvItem_unitOfIssue" SIZE="15" MAXLENGTH="15" tabindex=20  value="<%=encoder.encodeForHTMLAttribute(invItem.getUnitOfIssue())%>" onchange="upperCase(this);"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-conversionFactor")%>>
					<td nowrap align="right"><div id=conversionFactorA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-conversionFactor", "Conversion Factor", true)%>:&nbsp;</div></td>
					<td><div id=conversionFactorB><INPUT TYPE="TEXT" NAME="InvItem_factor" SIZE="15" MAXLENGTH="15" tabindex=25  value="<%=bd_factor%>" style="text-align:right"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-issueCost")%>>
					<td nowrap align="right"><div id=issueCostA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-issueCost", "Issue Cost", true)%>:&nbsp;</div></td>
					<td><div id=issueCostA><INPUT TYPE="TEXT" NAME="InvItem_issueCost" SIZE="15" MAXLENGTH="15" tabindex=26 value="<%=bd_issue_cost%>" style="text-align:right"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitCost", "Unit Cost", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_cost" SIZE="15" MAXLENGTH="15" tabindex=30  value="<%=bd_cost%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfOrder")%>>
					<td nowrap align="right"><a href="javascript: browseLookup('InvItem_unitOfOrder', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfOrder", "Unit Of Order", true)%>:</A>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_unitOfOrder" SIZE="15" MAXLENGTH="15" tabindex=35  value="<%=encoder.encodeForHTMLAttribute(invItem.getUnitOfOrder())%>" onchange="upperCase(this);"></td>
				</tr>
		<% if (s_duomRequired.equalsIgnoreCase("Y")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-duomUmCode")%>>
					<td nowrap align="right"><a href="javascript: browseLookup('InvItem_duomUmCode', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-duomUmCode", "Secondary UM", true)%>:</A>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_duomUmCode" SIZE="15" MAXLENGTH="15" tabindex=35  value="<%=encoder.encodeForHTMLAttribute(invItem.getDuomUmCode())%>" onchange="upperCase(this);"></td>
				</tr>
		<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-averageCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-averageCost", "Average Cost")%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_averageCost" SIZE="15" MAXLENGTH="15" tabindex=40 value="<%=bd_average_cost%>" style="text-align:right" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-lastCost")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-lastCost", "Last Cost")%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_lastCost" SIZE="15" MAXLENGTH="15" tabindex=45 value="<%=bd_last_cost%>" style="text-align:right" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-variance")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-variance", "Variance", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_variance" SIZE="15" MAXLENGTH="15" tabindex=50 value="<%=bd_variance%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-receiptOptions")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-receiptOptions", "Receipt Option")%>:&nbsp;</td>
					<td>
						<select name="InvItem_receiptRequired" tabindex=55>
							<option <% if (s_receipt_required.equals("R")) { %> selected <% } %> value="R">Receipt Required</option>
							<option <% if (s_receipt_required.equals("P")) { %> selected <% } %> value="P">Previously Received</option>
		<%	if (!oid.equals("MSG07P")) {%>
							<option <% if (s_receipt_required.equals("N")) { %> selected <% } %> value="N">No Receipt Required</option>
		<%	}%>
							<option <% if (s_receipt_required.equals("E")) { %> selected <% } %> value="E">End User Receipt</option>
						</select>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST02")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf2Code', 'LN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST02", "UDF 2", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf2Code" SIZE="15" MAXLENGTH="15" tabindex=60 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf2Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST04")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST04", "UDF 4", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf4Code" SIZE="15" MAXLENGTH="15" tabindex=65 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf4Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST06")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf6Code','ST06');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST06", "UDF 6", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf6Code" SIZE="15" MAXLENGTH="15" tabindex=70 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf6Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST08")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf8Code','ST08');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST08", "UDF 8", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf8Code" SIZE="15" MAXLENGTH="15" tabindex=75 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf8Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST10")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf10Code','ST10');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST10", "UDF 10", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf10Code" SIZE="15" MAXLENGTH="15" tabindex=80 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf10Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST12")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf12Code','ST12');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST12", "UDF 12", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf12Code" SIZE="15" MAXLENGTH="15" tabindex=85 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf12Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST14")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf14Code','ST14');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST14", "UDF 14", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf14Code" SIZE="15" MAXLENGTH="15" tabindex=90 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf14Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				</table>
			</td>
			<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
				<tr <%=HtmlWriter.isVisible(oid, "inv-language")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-language", "Language", true)%>:&nbsp;</td>
					<td>
						<select name="InvItem_source" tabindex=95>
						<option value="(Default)" <% if ( s_source.equals("(Default)") ) { %>SELECTED<%}%> >(Default)</option>
						<option value="" <% if ( s_source.equals("") ) { %>SELECTED<%}%> ></option>
						</select>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-abcCode")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-abcCode", "ABC Code", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_abcCode" SIZE="1" MAXLENGTH="1" TABINDEX=100 value="<%=encoder.encodeForHTMLAttribute(invItem.getAbcCode())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-totalMinOnHand")%>>
					<td nowrap align="right"><div id=totalMinOnHandA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-totalMinOnHand", "Total Min. On Hand")%>:&nbsp;</div></td>
					<td><div id=totalMinOnHandB><INPUT TYPE="TEXT" NAME="InvItem_mohTot" SIZE="15" MAXLENGTH="15" tabindex=105 value="<%=bd_moh_total%>" style="text-align:right" disabled></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-totalEconomicOrderQuantity")%>>
					<td nowrap align="right"><div id=totalEconomicOrderQuantityA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-totalEconomicOrderQuantity", "Total Econ. Order Qty")%>:&nbsp;</div></td>
					<td><div id=totalEconomicOrderQuantityB><INPUT TYPE="TEXT" NAME="InvItem_eoqTot" SIZE="15" MAXLENGTH="15" tabindex=110 value="<%=bd_eoq_total%>" style="text-align:right" disabled></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-minOnHandMonths")%>>
					<td nowrap align="right"><div id=minOnHandMonthsA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-minOnHandMonths", "Min. On-Hand Months", true)%>:&nbsp;</div></td>
					<td><div id=minOnHandMonthsB><INPUT TYPE="TEXT" NAME="InvItem_mohMonths" SIZE="15" MAXLENGTH="15" tabindex=115  value="<%=bd_moh_months%>" style="text-align:right" ></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-economicOrderQuantityMonths")%>>
					<td nowrap align="right"><div id=economicOrderQuantityMonthsA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-economicOrderQuantityMonths", "Econ. Order Qty. Months", true)%>:&nbsp;</div></td>
					<td><div id=economicOrderQuantityMonthsB><INPUT TYPE="TEXT" NAME="InvItem_eoqMonths" SIZE="15" MAXLENGTH="15" tabindex=120  value="<%=bd_eoq_months%>" style="text-align:right"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-pullPackagingQuantity")%>>
					<td nowrap align="right"><div id=pullPackagingQuantityA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-pullPackagingQuantity", "Pull/Packaging Quantity", true)%>:&nbsp;</div></td>
					<td><div id=pullPackagingQuantityB><INPUT TYPE="TEXT" NAME="InvItem_pullIncrement" SIZE="15" MAXLENGTH="15" tabindex=125  value="<%=bd_pull_increment%>" style="text-align:right"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-lastBlanketOrderNumber")%>>
					<td nowrap align="right"><div id=poNumberA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-lastBlanketOrderNumber", "Last Blanket Order #", true)%>:&nbsp;</div></td>
					<td><div id=poNumberB><INPUT TYPE="TEXT" NAME="InvItem_poNumber" SIZE="15" MAXLENGTH="20" tabindex=130  value="<%=encoder.encodeForHTMLAttribute(invItem.getPoNumber())%>" ></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-buyer")%>>
					<td nowrap align="right"><div id=buyerA><a href="javascript: browseLookup('InvItem_buyerCode', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-buyer", "Buyer", true)%>:</a>&nbsp;</div></td>
					<td><div id=buyerB><INPUT TYPE="TEXT" NAME="InvItem_buyerCode" SIZE="15" MAXLENGTH="15" tabindex=135  value="<%=encoder.encodeForHTMLAttribute(s_buyer_code)%>" ONCHANGE="upperCase(this); getNewInfo('buyer', this);"></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-buyerName")%>>
					<td nowrap align="right"><div id=buyerNameA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-buyerName", "Buyer Name")%>:&nbsp;</div></td>
					<td><div id=buyerNameB><INPUT TYPE="TEXT" NAME="as_buyerName" SIZE="15" value="<%=encoder.encodeForHTMLAttribute(buyer.getDisplayName())%>" disabled></div></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-itemNumberSuperceded")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumberSuperceded", "Superceded by", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_itemNumberSuperceded" SIZE="30" MAXLENGTH="30" tabindex=137  value="<%=encoder.encodeForHTMLAttribute(invItem.getItemNumberSuperceded())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-manufacturer")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-mfgName", "Manufacturer", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_mfgName" SIZE="25" MAXLENGTH="30" tabindex=140  value="<%=encoder.encodeForHTMLAttribute(invItem.getMfgName())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-modelnumber")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-modelnumber", "Model #", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_modelNumber" SIZE="25" MAXLENGTH="30" tabindex=142  value="<%=encoder.encodeForHTMLAttribute(invItem.getModelNumber())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
<% if (! s_itemCrossRef.equalsIgnoreCase("Y")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-nsnNumber")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-nsnNumber", "NSN")%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_nsnNumber" SIZE="25" MAXLENGTH="20" tabindex=145 value="<%=encoder.encodeForHTMLAttribute(invItem.getNsnNumber())%>"></td>
				</tr>
<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST01")%>>
					<td nowrap align="right">
	<% if (DictionaryManager.isLink(oid, "inv-ST01")) { %>
					<a href="javascript: browseStd('InvItem_udf1Code','ST01');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST01", "UDF 1", true)%>:</a>&nbsp;</td>
	<%	} else {	%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST01", "UDF 1", true)%>:&nbsp;</td>
<%	}	%>
				<td><INPUT TYPE="TEXT" NAME="InvItem_udf1Code" SIZE="15" MAXLENGTH="15" tabindex=150 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf1Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST03")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST03", "UDF 3", true)%>:&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf3Code" SIZE="15" MAXLENGTH="15" tabindex=154 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf3Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST05")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf5Code','ST05');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST05", "UDF 5", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf5Code" SIZE="15" MAXLENGTH="15" tabindex=158 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf5Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST07")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf7Code','ST07');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST07", "UDF 7", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf7Code" SIZE="15" MAXLENGTH="15" tabindex=162 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf7Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST09")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf9Code','ST09');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST09", "UDF 9", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf9Code" SIZE="15" MAXLENGTH="15" tabindex=166 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf9Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST11")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf11Code','ST11');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST11", "UDF 11", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf11Code" SIZE="15" MAXLENGTH="15" tabindex=170 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf11Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST13")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf13Code','ST13');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST13", "UDF 13", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf13Code" SIZE="15" MAXLENGTH="15" tabindex=174 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf13Code())%>" ONCHANGE="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-ST15")%>>
					<td nowrap align="right"><a href="javascript: browseStd('InvItem_udf15Code','ST15');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-ST15", "UDF 15", true)%>:</a>&nbsp;</td>
					<td><INPUT TYPE="TEXT" NAME="InvItem_udf15Code" SIZE="15" MAXLENGTH="15" tabindex=178 value="<%=encoder.encodeForHTMLAttribute(invItem.getUdf15Code())%>" ONCHANGE="upperCase(this);"></td>
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
				<tsa:hidden name="InvItem_taxable" value="<%=encoder.encodeForHTMLAttribute(s_taxable)%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%> nowrap align="right" width=100px><div id=chargeableA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-chargeable", "Chargeable")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%>>
				<div id=chargeableB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_chargeable.equals("Y")){%>CHECKED<%}%> tabindex=182 value="Y" onclick="setCheckbox(frm.InvItem_chargable,1);">
				<tsa:hidden name="InvItem_chargable" value="<%=encoder.encodeForHTMLAttribute(s_chargeable)%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-kit")%> nowrap align="right" width=90px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-kit", "Kit")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-kit")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_kit.equals("Y")){%>CHECKED<%}%> tabindex=184 value="Y" onclick="setCheckbox(frm.InvItem_kit,2);">
				<tsa:hidden name="InvItem_kit" value="<%=encoder.encodeForHTMLAttribute(s_kit)%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%> nowrap align="right" width=105px><div id=restrictedItemA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-restrictedItem", "Restricted Item")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%>>
				<div id=restrictedItemB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_restricted_item.equals("Y")){%>CHECKED<%}%> tabindex=186 value="Y" onclick="setCheckbox(frm.InvItem_restrictedItem,3);">
				<tsa:hidden name="InvItem_restrictedItem" value="<%=encoder.encodeForHTMLAttribute(s_restricted_item)%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%> nowrap align="right" width="120px"><tsa:label labelName="inv-critSparePart" defaultString="Critical Spare Part"/>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%>>
				<input type="checkbox" name="c_checkbox" <% if (s_critSparePart.equals("Y")) {%>checked<%}%> tabindex="187" value="Y" onclick="setCheckbox(frm.InvItem_critSparePart,4);">
				<tsa:hidden name="InvItem_critSparePart" value="<%=encoder.encodeForHTMLAttribute(s_critSparePart)%>"/>
			</td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "inv-asset")%> nowrap align="right" width=120px><div id=assetA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-asset", "Asset")%>:&nbsp;</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-asset")%>>
				<div id=assetB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_asset_code.equals("Y")){%>CHECKED<%}%> tabindex=188 value="Y" onclick="setCheckbox(frm.InvItem_assetCode,5);">
				<tsa:hidden name="InvItem_assetCode" value="<%=encoder.encodeForHTMLAttribute(s_asset_code)%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%> nowrap align="right" width=105px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-sAndSInterface", "S&S Interface")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_ss_interface.equals("Y")){%>CHECKED<%}%> tabindex=189 value="Y" onclick="setCheckbox(frm.InvItem_ssInterface,6);">
				<tsa:hidden name="InvItem_ssInterface" value="<%=encoder.encodeForHTMLAttribute(s_ss_interface)%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%> nowrap align="right" width=100px><div id=capAssetA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-capProperty", "Cap. Asset")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%>>
				<div id=capAssetB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_cap_property.equals("Y")){%>CHECKED<%}%> tabindex=190 value="Y" onclick="checkOnlyOne(6);setCheckbox(frm.InvItem_capProperty,7);">
				<tsa:hidden name="InvItem_capProperty" value="<%=encoder.encodeForHTMLAttribute(s_cap_property)%>"/>
				</div>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%> nowrap align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-gfpProperty", "GFP")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_gfp_property.equals("Y")){%>CHECKED<%}%> tabindex=194 value="Y" onclick="checkOnlyOne(7);setCheckbox(frm.InvItem_gfpProperty,8);">
				<tsa:hidden name="InvItem_gfpProperty" value="<%=encoder.encodeForHTMLAttribute(s_gfp_property)%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%> nowrap align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-fgpProperty", "FGP")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%>>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_fgp_property.equals("Y")){%>CHECKED<%}%> tabindex=198 value="Y" onclick="checkOnlyOne(8);setCheckbox(frm.InvItem_fgpProperty,9);">
				<tsa:hidden name="InvItem_fgpProperty" value="<%=encoder.encodeForHTMLAttribute(s_fgp_property)%>"/>
			</td>
			<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%> nowrap align="right" width=100px><div id=usageRecalcA><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-usageRecalc", "Usage Recalc")%>:</div></td>
			<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%>>
				<div id=usageRecalcB>
				<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if ( s_usage_recalc.equals("Y")){%>CHECKED<%}%> tabindex=200 value="Y" onclick="setCheckbox(frm.InvItem_usageRecalc,10);">
				<tsa:hidden name="InvItem_usageRecalc" value="<%=encoder.encodeForHTMLAttribute(s_usage_recalc)%>"/>
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
					<td>
						<select name="InvItem_status" onchange="setStatus();" tabIndex=250>
							<option value="01" <% if (invItem.getStatus().equals("01")) { %> selected <% } %>>Temporary</option>
							<option value="02" <% if (invItem.getStatus().equals("02")) { %> selected <% } %>>Permanent</option>
							<option value="03" <% if (invItem.getStatus().equals("03")) { %> selected <% } %>>Inactive</option>
							<option value="04" <% if (invItem.getStatus().equals("04")) { %> selected <% } %>>On Hold</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InvItem_dateEntered" tabindex=252 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invItem.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				<tr id="dateExpires">
					<td align=right>Date Expires:&nbsp;</td>
					<td>
						<input type=text name="InvItem_dateExpires" tabindex=254 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(invItem.getDateExpires(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvItem_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InvItem_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InvItem_owner" tabindex=256 size=30 maxlength=15 value="<%=encoder.encodeForHTMLAttribute(invItem.getOwner())%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" tabindex=258 size=30 value="<%=encoder.encodeForHTMLAttribute(owner.getDisplayName())%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>

	</td>
	<td align="right" valign="top">
<%--	if (s_action.equalsIgnoreCase("UPDATE")) { --%>
		<table border="1" cellpadding="0" cellspacing="0" width=150px>
		<tr><td class=browseHdr>&nbsp;Item Options</td></tr>
		<tr>
			<td>
				<table border="0" cellpadding=2 cellspacing=2 width="100%">
					<tr>
						<td>&nbsp;<a href="javascript: validateMe('VALIDATE'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-validate", "Validate", true)%></a></td>
					</tr>
					<tr>
						<td>&nbsp;<a href="javascript: comments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-comments", "Comments")%></a></td>
					</tr>
					<tr>
						<td>&nbsp;<a href="javascript: attachments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-attachments", "Attachments")%></a></td>
					</tr>
    				<% if (!(assetsActive && s_asset_code.equalsIgnoreCase("Y"))) { %>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-itemlocations")%>>
    					<td>&nbsp;<a href="javascript: doSubmit('/inventory/inv_item_locations.jsp', 'InvLocationRetrieveByItem'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-locations", "Locations", true)%></a></td>
    				</tr>
    				<% } %>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemform")%>>
    					<td>&nbsp;<a href="javascript:  doSubmit('/inventory/forms/inv_item_form.jsp', 'InvFormDataRetrieveById'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemform", "Item Form", true)%></a></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-itemproducts")%>>
    					<td>&nbsp;<a href="javascript:  browse('products'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemproducts", "Products", true)%></a></td>
    				</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemregions")%>>
    					<td>&nbsp;<a href="javascript:  browse('regions'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemregions", "Regions", true)%></a></td>
    				</tr>
<!--   				<tr>
    					<td>&nbsp;<a href="javascript:  browse('catalogs'); void(0);">Catalogs</a></td>
    				</tr>
--> 				<tr <%=HtmlWriter.isVisible(oid, "inv-itembom")%>>
    					<td><div id="bom">&nbsp;<a href="javascript:  bomCheck(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itembom", "Bill of Material", true)%></a></div></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-itembomtree")%>>
    					<td><div id="bomTree">&nbsp;<a href="javascript:  bomTree(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itembomtree", "Component Treeview", true)%></a></div></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-itemwhereused")%>>
    					<td><div id="whereUsed">&nbsp;<a href="javascript:  browse('inv-where-used'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemwhereused", "Where Used", true)%></a></div></td>
    				</tr>
<% 					if(s_item_approvals.equalsIgnoreCase("Y")) { %>
    				<tr>
    					<td>&nbsp;<a href="javascript: browseHistoryItem(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemhistory", "Item History", true)%></a></td>
    				</tr>
<% 					} %>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-itemforcasthistory")%>>
    					<td>&nbsp;<a href="javascript:  doSubmit('/inventory/forecast_history.jsp', 'InvItemDetailUsage'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemforcasthistory", "Forecast History", true)%></a></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-orderhistory")%>>
    					<td>&nbsp;<a href="javascript:  browse('inv-pohist'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-orderhistory", "Order History", true)%></a></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-receipthistory")%>>
    					<td>&nbsp;<a href="javascript:  browse('inv-receipt-history'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-receipthistory", "Receipt History", true)%></a></td>
    				</tr>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-transferhistory")%>>
    					<td>&nbsp;<a href="javascript:  browse('inv-transfer-history'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-transferhistory", "Transfer History", true)%></a></td>
    				</tr>
<% if (! (s_autoNumber.equals("Y"))) { %>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-saveas")%>>
    					<td>&nbsp;<a href="javascript:  saveas(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-saveas", "Save As", true)%></a></td>
    				</tr>
<% } %>
    				<tr <%=HtmlWriter.isVisible(oid, "inv-deleteitem")%>>
						<td><a href="javascript: void(0);" ONCLICK="javascript: deleteItem();">
							<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteItem", "Delete Item")%>
						</a>
						</td>
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

	var commentAttach = false;
	var status = "<%=encoder.encodeForJavaScript(invItem.getStatus())%>";
	var navMenu = getNavCookie("navArray");
	var itemType = "<%=encoder.encodeForJavaScript(invItem.getActionCode())%>" ;
	initTableLookup("<%=requestURLPath%>","<%=oid%>","${esapi:encodeForJavaScript(userId)}") ;
	var pageObject
	setFormTypeFields() ;

	//if (navMenu.indexOf("Item #") < 0)
	//{
		setNavCookie("/inventory/inv_item.jsp", "InvItemRetrieveById", "Item # <%=headerEncoder.encodeForJavaScript(invItem.getItemNumber())%>", true);
	//}
	setStatus();

	function invItemSave()
	{
		var r  ;
		if (isEmpty(frm.InvItem_itemNumber.value))
		{
			alert('You must enter a valid Item Number.');
			//frm.itemAction.value="UPDATE";
			//doSubmit('/inventory/inv_item_validate.jsp', 'InvItemAdd;InvItemValidate');
		} else {
	<%		if(s_item_approvals.equalsIgnoreCase("Y")) {	%>
				if (!commentAttach)
				{
					frm.itemAction.value="UPDATE";
					frm.InvItem_status.value="03";
					doSubmit('/inventory/inv_routing_list.jsp', 'InvItemAdd;InvItemRetrieveById');
				}
	<%		} else {	%>
			if (!commentAttach)
			{
				frm.itemAction.value="UPDATE";
				doSubmit('/inventory/inv_item.jsp', 'InvItemAdd;InvItemValidate');
			}
	<%		}  %>
		  }
	}

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

	function checkOnlyOne(pos)
	{
		if(frm.c_checkbox[pos].checked)
		{
			if(pos == 6)
			{
				frm.c_checkbox[7].checked = false;
				setCheckbox(frm.InvItem_gfpProperty, 7);
				frm.c_checkbox[8].checked = false;
				setCheckbox(frm.InvItem_fgpProperty, 8);
			}
			else if(pos == 7)
			{
				frm.c_checkbox[6].checked = false;
				setCheckbox(frm.InvItem_capProperty, 6);
				frm.c_checkbox[8].checked = false;
				setCheckbox(frm.InvItem_fgpProperty, 8);
			}
			else if(pos == 8)
			{
				frm.c_checkbox[6].checked = false;
				setCheckbox(frm.InvItem_capProperty, 6);
				frm.c_checkbox[7].checked = false;
				setCheckbox(frm.InvItem_gfpProperty, 7);
			}
		}
	}

	function saveItem()
	{
		if (umConversionCheck()) {
		if (frm.itemAction.value == "CREATE")
		{
<% if (s_itemCrossRef.equalsIgnoreCase("Y")) {	%>
			if (! showDuplicates()) {
				invItemSave() ;
			}
<% } else { %>
			invItemSave();
<% } %>
		}
		else
		{
			frm.itemAction.value="UPDATE";
			//doSubmit('/inventory/inv_item.jsp', 'InvItemUpdate');
			validateMe();
		}
  	  }
	}

	function saveProcess()
	{
		var handler = "";
		frm.itemAction.value="UPDATE";
		if (frm.itemAction.value == "CREATE")
		{
			handler = "InvItemAdd";
		}
		else
		{
			handler = "InvItemUpdate";
		}
		return handler;
	}

	function browse(x)
	{
		var updateHandler = saveProcess();

		if (x == 'regions')
		{
			setOriginalFilter("StdTable_id_tableType", "=", "STAT");
			frm.browseName.value = 'stdtable';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_regions.jsp', updateHandler + ';InvFormStateRetrieveByItem;BrowseRetrieve');
		}
		else if (x == 'products')
		{
			setOriginalFilter("StdTable_id_tableType", "=", "PROD");
			frm.browseName.value = 'stdtable';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_products.jsp', updateHandler + ';InvFormProductRetrieveByItem;BrowseRetrieve');
		}
		else if (x == 'catalogs')
		{
			frm.browseName.value = 'inv-catalog';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_catalogs.jsp', updateHandler + ';InvFormCatalogRetrieveByItem;BrowseRetrieve');
		}
		else
		{
    		if (x == 'inv-pohist')
    		{
				setAdvancedFilter("PoLine_itemNumber", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>", "OR", "Y", "N");
				setAdvancedFilter("PoLine_altItemNumber", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>", "", "Y", "N");
    		}
    		if (x == 'inv-transfer-history')
    		{
    			setOriginalFilter("RequisitionLine_itemNumber", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>");
    		}
    		if (x == 'inv-receipt-history')
    		{
				setAdvancedFilter("PoLine_itemNumber", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>", "OR", "Y", "N");
				setAdvancedFilter("PoLine_altItemNumber", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>", "OR", "Y", "N");
    		}
    		if (x == 'inv-where-used')
    		{
    			setOriginalFilter("BomComponent_componentItem", "=", "<%=headerEncoder.encodeForJavaScript(s_item_number)%>");
    		}

			frm.browseName.value = x;
    		doSubmit('/browse/browse_inv_item_history.jsp', updateHandler + ';BrowseRetrieve');
    	}
	}

	function bomCheck() {
	  if (frm.InvItem_actionCode.value == 'M') {
			doSubmit('/inventory/bom_master.jsp', 'BomMethodRetrieveByItem');
	  } else {
	  		alert("Bill of Material option is only valid for Action Code MAKE!") ;
  	  }
	}

	function bomTree() {
	  if (frm.InvItem_actionCode.value == 'M')
	  {
			doSubmit('/inventory/bom_component_tree.jsp', 'BomComponentRetrieveTree');
	  } else {
	  		alert("Bill of Material option is only valid for Action Code MAKE!") ;
  	  }
  	}

	function validateItem(action)
	{
<%		if(s_item_approvals.equalsIgnoreCase("Y")) {%>
			frm.InvItem_status.value="03";
			doSubmit('/inventory/inv_routing_list.jsp', 'InvItemUpdate;InvItemRetrieveById');
<%		} else { %>
			doSubmit('/inventory/inv_item_validate.jsp', 'InvItemUpdate;InvItemValidate');
<%		}  %>
	}

	function validateInv() {
		if (isEmpty(frm.InvItem_itemNumber.value)) {
			alert("You must enter a valid item number");
			return false;
		}
	    saveItem();
		return true;
	}

	function validateMe(action)
	{
		frm.validate.value = "TRUE";
		frm.validateAction.value = action;
		//saveItem();
		validateItem();
	}

	function saveas()
	{
	    popupParameters = "formtype=INVITEM;action=saveas";
	    doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function setStatus()
	{
		status = frm.InvItem_status[frm.InvItem_status.selectedIndex].value;
		if ((status == "01"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function deleteItem() {
		if (confirm("Are you sure you want to delete \"" + frm.InvItem_itemNumber.value  + "\"?")){
			frm.browseName.value = 'invitem-admin';
			doSubmit('/browse/browse.jsp','InvItemDelete;BrowseRetrieve');
		}
	}

	function setFormTypeFields() {
	   itemType = frm.InvItem_actionCode.value ;
		if (itemType == "M") {
		// Hide fields not required for Make (BOM) items
			displayArea("bom") ;
			displayArea("whereUsed") ;
			displayArea("bomTree") ;

			hideArea("unitOfIssueA") ;
			hideArea("unitOfIssueB") ;
			hideArea("conversionFactorA") ;
			hideArea("conversionFactorB") ;
			hideArea("issueCostA") ;
			hideArea("issueCostB") ;
			hideArea("taxableA") ;
			hideArea("taxableB") ;
			hideArea("chargeableA") ;
			hideArea("chargeableB") ;
			hideArea("assetA") ;
			hideArea("assetB") ;
			hideArea("capAssetA") ;
			hideArea("capAssetB") ;
			hideArea("usageRecalcA") ;
			hideArea("usageRecalcB") ;
			hideArea("restrictedItemA") ;
			hideArea("restrictedItemB") ;
			hideArea("totalMinOnHandA") ;
			hideArea("totalMinOnHandB");
			hideArea("totalEconomicOrderQuantityA") ;
			hideArea("totalEconomicOrderQuantityB") ;
			hideArea("minOnHandMonthsA");
			hideArea("minOnHandMonthsB");
			hideArea("economicOrderQuantityMonthsA") ;
			hideArea("economicOrderQuantityMonthsB") ;
			hideArea("pullPackagingQuantityA") ;
			hideArea("pullPackagingQuantityB") ;

			hideArea("poNumberA") ;
			hideArea("poNumberB") ;
			hideArea("buyerA") ;
			hideArea("buyerB") ;
			hideArea("buyerNameA") ;
			hideArea("buyerNameB") ;

		} else {
		// Show all fields
			hideArea("bom") ;
			hideArea("whereUsed") ;
			hideArea("bomTree") ;
			displayArea("unitOfIssueA") ;
			displayArea("unitOfIssueB") ;
			displayArea("conversionFactorA") ;
			displayArea("conversionFactorB") ;
			displayArea("issueCostA") ;
			displayArea("issueCostB") ;
			displayArea("taxableA") ;
			displayArea("taxableB") ;
			displayArea("chargeableA") ;
			displayArea("chargeableB") ;
			displayArea("assetA") ;
			displayArea("assetB") ;
			displayArea("capAssetA") ;
			displayArea("capAssetB") ;
			displayArea("usageRecalcA") ;
			displayArea("usageRecalcB") ;
			displayArea("restrictedItemA") ;
			displayArea("restrictedItemB") ;
			displayArea("totalMinOnHandA") ;
			displayArea("totalMinOnHandB");
			displayArea("totalEconomicOrderQuantityA") ;
			displayArea("totalEconomicOrderQuantityB") ;
			displayArea("minOnHandMonthsA");
			displayArea("minOnHandMonthsB");
			displayArea("economicOrderQuantityMonthsA") ;
			displayArea("economicOrderQuantityMonthsB") ;
			displayArea("pullPackagingQuantityA") ;
			displayArea("pullPackagingQuantityB") ;
			displayArea("poNumberA") ;
			displayArea("poNumberB") ;
			displayArea("buyerA") ;
			displayArea("buyerB") ;
			displayArea("buyerNameA") ;
			displayArea("buyerNameB") ;
		}
	}

	function autoNumberCheck(fld, isChecked) {
		if (isChecked) {
		   fld.value = "" ;
		   fld.readonly = true ;
		   fld.disabled = true ;
		   frm.InvItem_description.focus() ;
		} else {
		    fld.readonly = false ;
			fld.disabled = false ;
			fld.focus() ;
		}
	}

	function itemDuplicateCheck(idObject) {
	    var idValue = idObject.value ;
	    if (tableLookup(idValue, "invitem-retrieve-by.xml", "invItem", "InvItem_description" )) {
	    	alert(getRowCount()) ;
	    	alert ( getResponseValue(1,"Description", "") ) ;
	    	alert ( getResponseValue(1,"Description", "") ) ;

            alert ( "Item number [" + frm.InvItem_itemNumber.value + "] already exists in inventory! \n\n");

			frm.InvItem_itemNumber.value = "";
			frm.InvItem_itemNumber.focus() ;
		}
	}

	function updateDescription() {
	    var desc = frm.InvItem_description.value ;
	    var altStr = "" ;
	    var start = desc.toUpperCase().indexOf(" NSN:") ;
		if ( start < 0) {
			start = desc.toUpperCase().indexOf(" ALT:") ;
		}
		if (start >= 0) {
			desc = desc.substring(0, start) ;
		}

		if (! isEmpty(frm.InvItem_nsnNumber.value)) {
			altStr = altStr + " NSN: " + frm.InvItem_nsnNumber.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo1.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo1.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo2.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo2.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo3.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo3.value ;
		}
		frm.InvItem_description.value = desc + altStr ;
	}

	function showDuplicates() {
			var nsnNumber = frm.InvItem_nsnNumber.value;
			var altPartNo1 = frm.InvItem_altPartNo1.value;
			var altPartNo2 = frm.InvItem_altPartNo2.value;
			var altPartNo3 = frm.InvItem_altPartNo3.value;
			popupParameters = "" ;
			if (! isEmpty(nsnNumber)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + nsnNumber + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + nsnNumber + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo1)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo1 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo1 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo2)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo2 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo2 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo3)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo3 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo3 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(popupParameters)) {
				popupParameters = popupParameters + "formField=InvItem_itemNumber;browseName=itemcrossref-list;allowBrowse=" + frm.allowBrowse.value;

				doSubmitToLookup('browse/browse_itemcrossref_list.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=600px') ;
				return true ;
			} else {
				return false ;
			}

	}

	function umValidation(idObject) {
	    var idValue = idObject.value ;
	    pageObject = idObject ;
	    if (! tableLookup(idValue, "unitofmeasure-retrieve-by-id.xml", "unitOfMeasure", "UnitOfMeasure_umCode" ) ) {
	    	alert("Unit of measure code is invalid!") ;
	    	setTimeout( 'setFocusDelayed()', 100 );
	    	return false ;
	    }
	    return true ;
	}

	function umConversionCheck() {
	    var ok = true ;
	    var uoo = frm.InvItem_unitOfOrder.value ;
	    var uoi = frm.InvItem_unitOfIssue.value ;
	    if (! (frm.InvItem_unitOfIssue.value == frm.InvItem_unitOfOrder.value)) {
	  		ok=confirm("Warning!\n\nThe UNIT OF ORDER (" + uoo + ") and UNIT OF ISSUE (" + uoi + ") have different values! Please verify the conversion factor is correct for this combination. Click ok if the values reflect the correct conversion factor for this combination.\n\nExample: Unit Of Issue = EA    Unit Of Order = HU   (Conversion Factor = 100)");
	    }

	    return ok ;
	}

	function setFocusDelayed() {
		pageObject.focus() ;
	}

	function browseHistoryItem()
	{
		popupParameters = "browseName=invhistory" + ";allowBrowse=Y";
		popupParameters = popupParameters + ";colname=HistoryLog_icHeader;operator==;filter_txt=" + frm.ApprovalLog_icHeader.value + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=800', 'HEIGHT=700');
	}

	function comments()
	{
		commentAttach = true;
		if (frm.itemAction.value == "CREATE") {
			if (validateInv()) {
				frm.itemAction.value = "UPDATE";
				doSubmit('/inventory/inv_notes.jsp', 'InvItemUpdate;DocCommentRetrieveByLine');
			}
		}
		else {
			if (validateInv()) {
				doSubmit('/inventory/inv_notes.jsp', 'InvItemUpdate;DocCommentRetrieveByLine');
			}
		}
	}

	function attachments()
	{
		commentAttach = true;
		if (frm.itemAction.value == "CREATE") {
			if (validateInv()) {
				frm.itemAction.value = "UPDATE";
				doSubmit('/inventory/inv_line_attachments.jsp', 'DocAttachmentRetrieveByLine');
			}
		}
		else {
			if (validateInv()) {
				doSubmit('/inventory/inv_line_attachments.jsp', 'InvItemUpdate;DocAttachmentRetrieveByLine');
			}
		}
	}

</SCRIPT>
<%}%>