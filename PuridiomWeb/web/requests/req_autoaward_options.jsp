<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.menu.MenuManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_req_ic_header = (String) request.getAttribute("RequisitionHeader_icReqHeader");
	String	s_req_number = "";
	String	s_req_status = (String) request.getAttribute("RequisitionHeader_status");
	String	s_req_type = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionType"));
	String	s_req_subtype = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_rqSubType"));
	String	s_fiscal_year = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_fiscalYear"));
	String	s_item_location = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_itemLocation"));
	String	s_external_website = HiltonUtility.ckNull((String) PropertiesManager.getInstance(oid).getProperty("REQ DEFAULTS", "EXTERNALWEBSITE","N"));

	String	s_current_process = "SHOPCART";
	String	s_current_page = "/requests/req_items.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	boolean print_bsc_rm_msg = false;
	boolean print_bsc_bx_msg = false;

	List	reqLineList = (List) request.getAttribute("requisitionLineList");
	int	i_linecount = 0;

	if (reqLineList != null)
	{
		i_linecount = reqLineList.size();

		if (i_linecount > 0)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(0);
			s_req_number = reqLine.getRequisitionNumber();
		}
	}
	if (HiltonUtility.isEmpty(s_req_number))
	{
		s_req_number = HiltonUtility.ckNull((String) request.getAttribute("RequisitionHeader_requisitionNumber"));
	}

	if (HiltonUtility.isEmpty(s_req_number))	{	s_req_number = "N/A";	}
	if (HiltonUtility.isEmpty(s_req_status))	{	s_req_status = "1000";			}
	if (HiltonUtility.isEmpty(s_req_type))		{	s_req_type = "P";				}


	String FilenameXls = null;
    if (request.getAttribute("FilenameXls") != null)
    {
    FilenameXls = (String) request.getAttribute("FilenameXls");
    }

    RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
    String s_curr_code = requisitionHeader.getCurrencyCode();

    boolean isCatalogSecurityActive = false;
	String catalogSecurityActive = PropertiesManager.getInstance(oid).getProperty("CATALOG SECURITY DEFAULTS", "CATALOGSECURITYACTIVE","N");
	pageContext.setAttribute("oid", oid);
	//isCatalogSecurityActive = catalogSecurityActive.equalsIgnoreCase("Y") && CatalogSecurityManager.getInstance().isEnabledPunchoutCatalog(oid,user);
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="<%=request.getAttribute(\"RequisitionHeader_requiredBy\")%>"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="frompage" value="shopcart"/>
<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<tsa:hidden name="applicationType" value="WEB"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>
<tsa:label labelName="autoAward" defaultString="Auto-Award" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>
<tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
			<td width=125px><%=headerEncoder.encodeForHTML(s_req_number)%></td>
		</tr>
		<tr>
			<td align=right><b>
<tsa:label labelName="status" defaultString="Status" />:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_req_status, oid)%></td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=450px class=browseHdrDk>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
				<tr>
					<td width=7% height=18px class=browseHdrDk nowrap>

					<tsa:label labelName="req-hdg-lineNumber" defaultString="Line #" />
					</td>
					<tsa:td field="req-itemNumber" width="18%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" />
					</tsa:td>
					<tsa:td field="req-commodity" width="15%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-commodity" defaultString="Commodity" />
					</tsa:td>
					<tsa:td field="req-quantity" width="10%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-quantity" defaultString="Quantity" />
					</tsa:td>
					<tsa:td field="req-uom" width="10%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-uom" defaultString="UOM" />
					</tsa:td>
					<tsa:td field="req-unitCost" width="10%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" />
					</tsa:td>
					<tsa:td field="req-extendedCost" width="15%" noWrap="nowrap" height="18px" cssClass="browseHdrDk" >
					<tsa:label labelName="req-hdg-extendedCost" defaultString="Extended Cost" />
					</tsa:td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		if (i_linecount <= 0) { %>
				<tr>
					<td align=center><br><b><tsa:label labelName="noitems" defaultString="There are currently no items in your shopping cart." /></b><br><br></td>
				</tr>
<%		}
			for (int i = 0; i < i_linecount; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
				BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
				<tr>
					<td width=7% height=18px class=browseRow nowrap align=right><a href="javascript: viewItem(<%=i%>); void(0);" onMouseOver="highlightRow(<%=i%>*2);highlightRow((<%=i%>*2) + 1);" onMouseOut="removeHighlight(<%=i%>);" title="Click here to View/Modify Item Details."><%=i + 1%></a>&nbsp;&nbsp;</td>
					<tsa:td field="req-itemNumber" width="18%" height="18px" cssClass="browseRow" noWrap="nowrap" >
						<%=reqLine.getItemNumber()%>
						<tsa:hidden id="icReqLine_<%=i%>" name="icReqLine_<%=i%>" value="<%=reqLine.getIcReqLine()%>"/>
					</tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" >
					<%=reqLine.getCommodityCode()%>&nbsp;&nbsp;
					</tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">
					<%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>&nbsp;&nbsp;
					</tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" >
					<%=reqLine.getUmCode()%>
					</tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">
					<%=HiltonUtility.getFormattedPriceCurrency(bd_unit_price, s_curr_code, oid)%>&nbsp;&nbsp;
					</tsa:td>
					<tsa:td field="req-extendedCost" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right">
					<%=HiltonUtility.getFormattedCurrency(bd_extended_cost, s_curr_code, oid)%>&nbsp;&nbsp;
					</tsa:td>
				</tr>
				<tr>
					<td width=5% height=18px class=browseRow nowrap>&nbsp;</td>
					<td colspan=6 height=18px class=browseRow><%=reqLine.getDescription()%></td>
				</tr>
				<%if(oid.equalsIgnoreCase("bly07p")) {%>
				<tr>
					<td>&nbsp;</td>
					<td width=5% height="18px" class="browseRow" nowrap><tsa:label labelName="req-LN09" defaultString="Line UDF9" checkRequired="true"/>&nbsp;</td>
					<td colspan=4 height="18px" class="browseRow"><%=reqLine.getUdf9Code()%></td>
				</tr>
				<%}%>
<%
				if((reqLine.getUmCode()).equals("RM") && (reqLine.getCatalogId()).equals("LETTERHEAD")){
					print_bsc_rm_msg = true;
				}
				if((reqLine.getUmCode()).equals("BX") && (reqLine.getCatalogId()).equals("ENVELOPE")){
					print_bsc_bx_msg = true;
				}		%>
<%		} %>
				</table>
			</td>
		</tr>
		</table>

		<br><br>

		<!-- Start fieldset -->
		<fieldset style="width:450px"><legend><b><tsa:label labelName="autoAwardOptions" defaultString="Auto-Award Options" /></b></legend>
			<table border=0 cellspacing=0 cellpadding=2 width=100% align=center class=browseRow>
				<tr>
					<td valign=top class=browseRow><table border=0 cellspacing=0 cellpadding=2><tr><td nowrap><table border=0 cellspacing=0 cellpadding=2>
                      <tr>
                        <td width="5px" align="center"><tsa:input type="checkbox" name="bySupplier" checked="true" />                        </td>
                        <td nowrap><tsa:label labelName="splitItemsSupplier" defaultString="Split Items by Supplier (One Order per Supplier)" /></td>
                      </tr>
                      <tr>
                        <td width="5px" align="center"><tsa:input type="checkbox" name="byCatalog" checked="true" />                        </td>
                        <td nowrap><tsa:label labelName="splitItemsCatalog" defaultString="Split Items by Catalog ID (One Order per Catalog)" /></td>
                      </tr>
                    </table></td>
						</tr>
					</table>
				  </td>
					<td valign=top class=browseRow>
					</td>
				</tr>
			</table>
		</fieldset>

	</td>

</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center>
		<div id="pxbutton">
		    <a href="javascript: doSubmit('menu/main_menu.jsp', 'RequisitionAutoAward'); void(0);"><tsa:label labelName="award" defaultString="award" /></a>
		</div>
	</td>
	<td width=50% align=center>
		<div id="pxbutton">
		    <a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><tsa:label labelName="return" defaultString="return" /></a>
		</div>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=s_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=s_item_location%>";

	setTableHeights();

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("<%=RequisitionType.toString(s_req_type, oid)%>") < 0)
	{
		setNavCookie("/requests/req_items.jsp", "RequisitionRetrieve", "<%=RequisitionType.toString(s_req_type, oid)%>");
	}

    function ToTotals(){
    doSubmit("/requests/req_totals.jsp", "RequisitionRetrieve");
    }


	function ToReview(){
    doSubmit("/requests/req_review.jsp", "RequisitionHeaderUpdate;RequisitionRetrieve");
    }


	function thisLoad() {
		f_StartIt();
<%	if (s_req_type.equals("S") || s_req_type.equals("T") || s_req_type.equals("I")) {

		if (propertiesManager.isModuleActive("Extended Inventory") || propertiesManager.isModuleActive("Standard Inventory")) {%>
		hideArea("itemtype");
			if (frm.as_item_type) {
				frm.as_item_type[1].checked = "TRUE";
			}
<%		}
	} %>
	}

	function setTableHeights() {
		setTableHeight("itemTable", "browseRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}

	function viewItem(row)
	{
		var num = document.getElementById("icReqLine_" + row);
		frm.RequisitionLine_icReqLine.value = num.value;
		doSubmit('/requests/req_item.jsp','RequisitionLineRetrieve');
	}

	function removeHighlight(row) {
		row = row * 2;
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];

		setRowClassName(myRow, "browseRow");

		myRow = myTable.rows[row + 1];

		setRowClassName(myRow, "browseRow");
	}

	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }

// End Hide script -->
</SCRIPT>