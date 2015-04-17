<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%
	DisbLine disbLine = (DisbLine) request.getAttribute("disbLine");
	BigDecimal bd_ic_dsb_header = disbLine.getIcDsbHeader();
	BigDecimal bd_ic_dsb_line = disbLine.getIcDsbLine();
	String s_dsb_number = disbLine.getDisbNumber();
	String s_dsb_status = (String) request.getAttribute("DisbHeader_status");
	String s_dsb_type = (String) request.getAttribute("DisbHeader_disbType");
	String s_line_count = (String) request.getAttribute("lineCount");
	BigDecimal s_original_qty_requested = (BigDecimal)request.getAttribute("originalQtyRequested");
	String	s_from_page = HiltonUtility.ckNull((String) request.getAttribute("frompage"));
	String	s_return_page = "";
	String	s_return_method = "";
	BigDecimal bd_line_number = Utility.getBigDecimalFormatted(disbLine.getLineNumber(), 0);
	Commodity commodity = (Commodity) request.getAttribute("commodity");
	String  itemNumber = HiltonUtility.ckNull(disbLine.getItemNumber()) ;
	String  itemLocation = HiltonUtility.ckNull(disbLine.getItemLocation()) ;

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String	s_fiscal_year = (String) request.getAttribute("DisbHeader_fiscalYear");

	if (s_dsb_number == null || s_dsb_number.length() <= 0)	{	s_dsb_number = "N/A";	}
	if (s_from_page.equalsIgnoreCase("shopcart"))
	{
		s_return_page = "/inventory/dsb_items.jsp";
		s_return_method = "DisbSetProperty;DisbLineRetrieveByHeader";
	}
	else
	{
		s_return_page = "/inventory/dsb_review.jsp";
		s_return_method = "DisbSetProperty;DisbursementRetrieve";
	}
	BigDecimal qtyOnHand = new BigDecimal(0);
	InvLocation invLocation = (InvLocation) request.getAttribute("invLocation");
	if (invLocation != null)
	{
		qtyOnHand = invLocation.getQtyOnHand();
	}

	boolean isDualUm = false ;
	boolean isSerNoRequired=false ;

	if (commodity != null) {
		String atemp = commodity.getSerialNumbersRequired() ;
		String umtemp = commodity.getDuomRequired() ;
		if (atemp == null) atemp = "N" ;
		if (umtemp == null) umtemp = "N" ;
		isSerNoRequired = atemp.equalsIgnoreCase("Y") ;
		if (s_duomRequired.equalsIgnoreCase("Y")) isDualUm = umtemp.equalsIgnoreCase("Y") ;
	}

	Map selectedProperty = (Map) request.getAttribute("selectedProperty");
	List pList = new ArrayList();
	if (selectedProperty != null && selectedProperty.containsKey(disbLine.getIcRc().toString())) {
		pList = (List) selectedProperty.get(disbLine.getIcRc().toString());
	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value="<%=bd_ic_dsb_line%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=HiltonUtility.ckNull(disbLine.getDisbNumber())%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="DisbHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="DisbLine_lineNumber" value="<%=bd_line_number%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="Account_icLine" value="<%=bd_ic_dsb_line%>"/>
<tsa:hidden name="Account_accountType" value="DBL"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="DocComment_icLine" value="<%=bd_ic_dsb_line%>"/>
<tsa:hidden name="lineCount" value="<%=s_line_count%>"/>
<tsa:hidden name="formtype" value="DSB"/>
<tsa:hidden name="frompage" value="<%=s_from_page%>"/>
<tsa:hidden name="DisbLine_icReqLine" value="<%=disbLine.getIcReqLine()%>"/>
<tsa:hidden name="lineToRetrieve" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>

<%-- set the icrc for the property serial numbers link --%>
<tsa:hidden name="InvBinLocation_icRc" value="${disbLine.icRc}"/>
<tsa:hidden name="InvLocation_itemNumber" value="${disbLine.itemNumber}"/>
<tsa:hidden name="InvLocation_itemLocation" value="${disbLine.itemLocation}"/>
<tsa:hidden name="Commodity_commodity" value="${commodity.commodity}"/>
<tsa:hidden name="fromPage" value="dsb_item"/>
<tsa:hidden name="binAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Line Item <%=bd_line_number%> of <%=s_line_count%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Disbursement #:</b></td>
			<td width=100px><%=s_dsb_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=100px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=460px>
		<br>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0 width=460px>
				<tr>
					<td nowrap align=right width=125px><tsa:label labelName="itemNumber"/>:&nbsp;</td>
					<td><input type=text name="DisbLine_itemNumber" tabindex=1 size=20 maxlength=30 value="<%=HiltonUtility.ckNull(disbLine.getItemNumber())%>" disabled></td>
					<td colspan=2 class=error>
						&nbsp;
<%					if (disbLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
						* CANCELLED *
<%					} %>
					</td>
				</tr>
				<tr>
					<td nowrap align=right valign=top>Description:&nbsp;</td>
					<td colspan=4><textarea wrap="virtual" name="DisbLine_description" tabindex=4 rows=5 cols=60 onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000);"><%=HiltonUtility.ckNull(disbLine.getDescription())%></textarea></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<td align=right width=180px valign=top>
		<br>
		<br>
<%
	int linecount = Integer.valueOf(s_line_count).intValue();
	if (linecount > 1)
	{ %>
		View Item:
<%	for (int i = 1; i <= linecount; i++)
		{
			if (i == bd_line_number.intValue())
			{ %>
				&nbsp;<%=i%>
<%		}
			else
			{ %>
				&nbsp;<a href="javascript: retrieveLine(<%=i%>);"><%=i%></a>
<%		}
		}
	}%>
		<br>
<%	if (s_dsb_type.equals("O") && s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0)
		{
			//do not display the Item Options Table
		}
		else
		{%>
		<table border=1 cellpadding=0 cellspacing=0>
		<tr><td class=browseHdr>Item Options</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=180px>
<%		if (!s_dsb_type.equals("O"))
			{
				if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0 && disbLine.getStatus().compareTo(DocumentStatus.CANCELLED) < 0) {%>
				<tr height=15px>
					<td nowrap align=center>
<%				if (s_view.equalsIgnoreCase("CLASSIC")) { %>
						<a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbLineCancel;DisbursementRetrieve');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');">Cancel Item
<%				} else if (s_view.equalsIgnoreCase("WIZARD")) { %>
						<a href="javascript: doSubmit('<%=s_return_page%>', 'DisbLineCancel;<%=s_return_method%>');" ONCLICK="return verifyAction('Cancel line <%=bd_line_number%>?');">Cancel Item
<%				} %>
					</td>
				</tr>
<%			}
				if (!HiltonUtility.isNA(s_dsb_number)) {%>
				<tr height=15px>
					<td nowrap align=center>&nbsp;<A HREF="javascript: viewItemHistory();">Item History</A></TD>
				</tr>
<%			}
			}
			else
			{
				if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0) { %>
				<tr height=15px>
					<td nowrap align=center>
<%		if (s_view.equalsIgnoreCase("CLASSIC")) { %>
						<a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbLineDelete;DisbursementRetrieve');" ONCLICK="if (verifyAction('Are you sure you want to delete this item?')) { return true; } else { return false; }">
<%		} else if (s_view.equalsIgnoreCase("WIZARD")) { %>
						<a href="javascript: doSubmit('<%=s_return_page%>', 'DisbSetProperty;DisbLineDelete;<%=s_return_method%>');" ONCLICK="if (verifyAction('Are you sure you want to delete this item?')) { return true; } else { return false; }">
<%		} %>
							<IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0">&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteItem", "Delete Item")%>
						</a>
					</td>
				</tr>
<%			}
			} %>
				</table>
<%	} %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=460px>
		<table border=0 cellpadding=0 cellspacing=0 height=100% width=460px>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td nowrap align=right width=125px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uom", "U/M")%>:&nbsp;</td>
					<td><input type=text name="DisbLine_umCode" tabindex=3 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbLine.getUmCode())%>" disabled></td>
				</tr>
				<tr>
					<td nowrap align=right>Quantity:&nbsp;</td>
					<td><input type=text name="DisbLine_quantity" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%>" onchange="verifyQty(); addUp();" style="text-align:right"></td>
					<tsa:hidden name="old_DisbLine_qty" value="<%=disbLine.getQuantity()%>"/>
					<tsa:hidden name="DisbLine_icRc" value="<%=disbLine.getIcRc()%>"/>
				</tr>
<%				if (isDualUm) { %>
				<tr>
					<td nowrap align=right width=125px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-duomUnitOfMeasure", "Secondary U/M")%>:&nbsp;</td>
					<td><input type=text name="DisbLine_duomUmCode" tabindex=3 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbLine.getDuomUmCode())%>" disabled></td>
				</tr>
				<tr>
					<td nowrap align=right>Secondary Quantity:&nbsp;</td>
					<td><input type=text name="DisbLine_duomQuantity" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedQuantity(disbLine.getDuomQuantity(), oid)%>" onchange="verifyDuomQty()" style="text-align:right"></td>
					<tsa:hidden name="old_DisbLine_duomqty" value="<%=disbLine.getDuomQuantity()%>"/>
				</tr>
<% } %>
				<tr>
					<td nowrap align=right>Price:&nbsp;</td>
					<td><input type=text name="DisbLine_unitPrice" tabindex=7 size=15 value="<%=HiltonUtility.getFormattedPrice(disbLine.getUnitPrice(), oid)%>" style="text-align:right" disabled></td>
				</tr>
				<tr>
					<td nowrap align=right>Line Total:&nbsp;</td>
					<td><input type=text name="DisbLine_lineTotal" tabindex=9 size=15 value="<%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%>" style="text-align:right" disabled></td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<% if (!oid.equalsIgnoreCase("VSE06P")) { %>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorId", "Supplier Code")%>:&nbsp;</td>
					<td><input type=text name="DisbLine_vendorId" tabindex=11 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbLine.getVendorId())%>" disabled></td>
					<% } %>
				</tr>
				<tsa:tr field="lotNumber">
					<td nowrap align=right><tsa:label labelName="lotNumber"/>:&nbsp;</td>
					<td><input type=text name="DisbLine_lot" tabindex=13 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbLine.getLot())%>" disabled></td>
				</tsa:tr>
				<tr>
					<% if (!oid.equalsIgnoreCase("VSE06P")) { %>
					<td nowrap align=right><tsa:label labelName="manufacturerNumber" />:&nbsp;</td>
					<td><input type=text name="DisbLine_manufactNo" tabindex=15 size=15 maxlength=20 value="<%=HiltonUtility.ckNull(disbLine.getManufactNo())%>" disabled></td>
					<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-location")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:&nbsp;</td>
					<td><input type=text name="DisbLine_itemLocation" tabindex=13 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbLine.getItemLocation())%>" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-quantityOnHand")%>>
					<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-quantityOnHand", "Qty on Hand")%>:&nbsp;</td>
					<td><input type=text name="DisbLine_qtyOnHand" tabindex=13 size=15 maxlength=15 value="<%=HiltonUtility.getFormattedQuantity(qtyOnHand, oid)%>" style="text-align:right" disabled></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top width=460px>
		<table border=0 cellpadding=0 cellspacing=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=1 cellspacing=0 width=460px>
				<tr>
					<td width=125px>&nbsp;</td>
					<td nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "aisle", "Storage Location")%></td>
					<% if (!oid.equalsIgnoreCase("VSE06P")) { %>
					<td nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "row", "Row")%></td>
					<td nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "tier", "Tier")%></td>
					<td nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin", "Bin")%></td>
					<td nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN01", "UDF 1 CODE")%></td>
					<% } %>
				</tr>
				<tr>
					<td width=125px align=right nowrap>
					<%	if (s_dsb_status.compareTo(DocumentStatus.INV_DISBURSED) >= 0) { %>
						Bin Location:&nbsp;
					<%	} else { %>
						<a href="javascript: browseBins('DisbLine_icRc', '<%=itemNumber%>', '<%=itemLocation%>', frm.DisbLine_icRc.value); void(0);">Bin Location:</a>&nbsp;
					<%	} %>
					</td>
					<td align=center><input type=text name="DisbLine_aisle" tabindex=17 size=12 value="<%=HiltonUtility.ckNull(disbLine.getAisle())%>" disabled></td>
					<% if (!oid.equalsIgnoreCase("VSE06P")) { %>
					<td align=center><input type=text name="DisbLine_locrow" tabindex=17 size=12 value="<%=HiltonUtility.ckNull(disbLine.getLocrow())%>" disabled></td>
					<td align=center><input type=text name="DisbLine_tier" tabindex=17 size=12 value="<%=HiltonUtility.ckNull(disbLine.getTier())%>" disabled></td>
					<td align=center><input type=text name="DisbLine_bin" tabindex=17 size=12 value="<%=HiltonUtility.ckNull(disbLine.getBin())%>" disabled></td>
					<td align=center><input type=text name="DisbLine_udf1Code" tabindex=17 size=12 value="<%=HiltonUtility.ckNull(disbLine.getUdf1Code())%>" disabled></td>
					<% } %>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	<% if (commodity != null && "Y".equals(commodity.getSerialNumbersRequired())) { %>
		<td align=right valign=bottom><a href="javascript: viewProperty();">
			<IMG SRC="<%=contextPath%>/images/asset3.gif" BORDER="0" ALT="Property Serial Numbers">
			Property Serial Numbers
		</a></td>
	<% } %>
</tr>
</table>

<br>
<br>


<br>

<table <%=HtmlWriter.isVisible(oid, "itemAccounts")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% class=browseHdr nowrap><a href="javascript: doSubmit('/inventory/dsb_accounts_ln.jsp','DisbLineUpdate;AccountRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit Items"></a></td>
					<td width=68% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
					<td width=15% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>

<%
	List accountList = (List) disbLine.getAccountList();
	BigDecimal bd_total_perc = new BigDecimal(0.00);
	BigDecimal bd_total_amt = new BigDecimal(0.00);

	if (accountList != null)
	{
		for (int i = 0;i < accountList.size(); i++)
		{
			Account account = (Account) accountList.get(i);

			BigDecimal bd_alloc_perc = account.getAllocPercent();
			BigDecimal bd_alloc_amt = account.getAllocAmount();

			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);

			String	s_account = "";
			String	s_accArray[] = new String[15];
			s_accArray[0] = account.getFld1();
			s_accArray[1] = account.getFld2();
			s_accArray[2] = account.getFld3();
			s_accArray[3] = account.getFld4();
			s_accArray[4] = account.getFld5();
			s_accArray[5] = account.getFld6();
			s_accArray[6] = account.getFld7();
			s_accArray[7] = account.getFld8();
			s_accArray[8] = account.getFld9();
			s_accArray[9] = account.getFld10();
			s_accArray[10] = account.getFld11();
			s_accArray[11] = account.getFld12();
			s_accArray[12] = account.getFld13();
			s_accArray[13] = account.getFld14();
			s_accArray[14] = account.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=70% class=browseRow><%=headerEncoder.encodeForHTML(s_account)%></td>
					<td width=15% class=browseRow align=right><%=Utility.getBigDecimalFormatted(bd_alloc_perc, 2)%>%</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
				</tr>
				</table>
<%	}
	} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=70% class=browseRow>&nbsp;</td>
					<td width=15% class=browseRow align=right><%=Utility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
					<td width=15% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
				</tr>
				</table>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td valign=middle>&nbsp;
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

<%	if ( !s_dsb_type.equals("O") ) {%>
<table <%=HtmlWriter.isVisible(oid, "itemComments")%> border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td width=2% class=browseHdr nowrap><a href="javascript: doSubmit('/inventory/dsb_notes_ln.jsp', 'DisbLineUpdate;DocCommentRetrieveByLine'); void(0);"><img id='edit' valign='baseline' src='<%=contextPath%>/images/cmd_edit.gif' class='processOnImg' border=0 alt="Edit Items"></a></td>
					<td width=73% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
					<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
					<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
					<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	List commentList = (List) disbLine.getDocCommentList();
	String s_placeString = "";
	if (commentList != null)
	{
		for (int i = 0;i < commentList.size(); i++)
		{
			DocComment docComment = (DocComment) commentList.get(i);
			String s_title = docComment.getCommentTitle();
			String s_print = docComment.getCommentPrint();
			String s_bold = docComment.getCommentBold();
			String s_place = docComment.getCommentPlace();
			if (s_place.equals("A")){
				s_placeString = "After";
			}
			else if (s_place.equals("B")){
				s_placeString = "Before";
			} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow><%=headerEncoder.encodeForHTML(s_title)%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_placeString%></td>
				</tr>
				</table>

<% 	}
	} %>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td valign=middle>&nbsp;
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbLineUpdate;DisbursementRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbursementRetrieve'); void(0);">Return</a></div></td>
<%	}
		else if (s_view.equalsIgnoreCase("WIZARD"))
		{
%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('<%=s_return_page%>', 'DisbSetProperty;DisbLineUpdate;<%=s_return_method%>'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_return_page%>', 'DisbSetProperty;<%=s_return_method%>'); void(0);">Return</a></div></td>
<%	} %>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	dsbType = "<%=headerEncoder.encodeForJavaScript(s_dsb_type)%>";

<%	if (s_dsb_status.compareTo(DocumentStatus.INV_DISBURSED) >= 0) { %>
	checkInputSettings();
<%	} %>

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
			myText.innerText = "View All " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}


	function verifyQty()
	{
		var originalQty = <%=HiltonUtility.getFormattedQuantity(s_original_qty_requested, oid)%>;
//		if (frm.DisbLine_quantity.value > originalQty && dsbType != "O")
//		{
//			alert("You may not disburse more than what was requested!");
//			frm.DisbLine_quantity.value = nformat(originalQty, <%=s_quantity_decimals%>);
//		}
//		else
//		{
//			frm.DisbLine_quantity.value = nformat(frm.DisbLine_quantity.value, <%=s_quantity_decimals%>);
//		}
		frm.DisbLine_quantity.value = nformat(eval(nfilter(frm.DisbLine_quantity)), <%=s_quantity_decimals%>);
	}

	function addUp()
	{
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
		var prc_dec = <%=Integer.valueOf(s_price_decimals).intValue()%>;

		var q = nformat(eval(nfilter(frm.DisbLine_quantity)),qty_dec);
		var p = nformat(eval(nfilter(frm.DisbLine_unitPrice)),prc_dec);

		frm.DisbLine_quantity.value = q;
		frm.DisbLine_unitPrice.value = p;

		frm.DisbLine_lineTotal.value = nformat( p * q, prc_dec );
	}

	function verifyDuomQty()
	{
		frm.DisbLine_duomQuantity.value = nformat(eval(nfilter(frm.DisbLine_duomQuantity)), <%=s_quantity_decimals%>);
	}

	function retrieveLine(linenumber)
	{
		frm.lineToRetrieve.value = linenumber;
		doSubmit('/inventory/dsb_item.jsp', 'DisbSetProperty;DisbLineUpdate;DisbLineRetrieveByLineNumber;CommodityRetrieveById');
	}

	function viewItemHistory()
	{
		popupParameters = "HistoryLog_icLineHistory=<%= disbLine.getIcLineHistory() %>;formtype=DSB;DisbLine_icDsbLine=<%= bd_ic_dsb_line %>;disbNumber=<%= s_dsb_number %>";
		doSubmitToPopup('/inventory/dsb_history.jsp', 'DisbSetProperty;HistoryLogRetrieveByHistoryLine', 'width=675px', 'height=350px');
	}

	function viewProperty() {
		frm.binAction.value = "PROPERTY";
		doSubmit('/inventory/inv_property_checklist.jsp', 'DisbSetProperty;DisbLineUpdate;InvPropertyRetrieveByIcRc');
	}

	function browseBins(frmField, itemNo, itemLoc, icRc)
	{
	    popupParameters = "colname=InvBinLocation_itemNumber;operator==;filter_txt=" + itemNo + ";logicalOperator=AND;originalFilter=N;sort=N;"
	    popupParameters = popupParameters + "colname=InvBinLocation_itemLocation;operator==;filter_txt=" + itemLoc + ";logicalOperator=AND;originalFilter=N;sort=N;"
	    popupParameters = popupParameters + "colname=InvBinLocation_icRc;operator=<>;filter_txt=" + icRc + ";logicalOperator=AND;originalFilter=N;sort=N;"
	    browseLookup(frmField,'ext_inv_move');
	}

	function submitThis(page, handlers)
	{
		var message = "";

	<%	if (isDualUm) { %>
		if (!(frm.DisbLine_duomQuantity && frm.DisbLine_duomQuantity.value > 0)) {
			message = "You must enter a quantity greater than ZERO for the Secondary Quantity.";
		}
	<%	} %>

		if (message == "")
		{
		<% if (commodity != null && "Y".equals(commodity.getSerialNumbersRequired())) { %>
			var valQuantity = 0;

			if (frm.DisbLine_quantity) {
				valQuantity = nformat(eval(nfilter(frm.DisbLine_quantity)),0);
			}

			if (valQuantity != <%=pList.size()%>)
			{
				message = "Need to enter serial number for each quantity entered.";
			}
		<% } %>
		}

		if (message != "") {
			alert(message);
		} else {
			doSubmit(page, handlers);
		}
	}

// End Hide script -->
</SCRIPT>