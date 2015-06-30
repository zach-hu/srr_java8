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

	String	s_current_process = "HEADER_INSPECTION";
	//String	s_current_page = "/requests/req_msr_inspections.jsp";
	String s_current_page = "/requests/req_inspections.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	boolean print_bsc_rm_msg = false;
	boolean print_bsc_bx_msg = false;
	boolean warningNonStandard = false;

	List  reqLineList = (List) request.getAttribute("requisitionInspections") ;
	int	i_linecount = 0;

	if (reqLineList != null)
	{
		i_linecount = reqLineList.size();

		if (i_linecount > 0)
		{
			RequisitionLine reqLine = (RequisitionLine) reqLineList.get(0) ;
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


	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
    String s_curr_code = requisitionHeader.getCurrencyCode();

	boolean fpeUser = user.isAFpe() ;
	boolean engineerUser = user.isAEngineer() ;
	boolean msrEngineer = user.isAnAdministeredBy();
	
	boolean disableEdit = true;
	
	switch(s_req_status){
	case DocumentStatus.REQ_PLANNING:
		if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid))
			disableEdit = false;	
		break;
	case DocumentStatus.REQ_PLANNING_RECALLED:
		if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid) || fpeUser || msrEngineer)
			disableEdit = false;
		break;
	case DocumentStatus.REQ_PLANNING_REJECTED:
		if(HiltonUtility.ckNull(requisitionHeader.getOwner()).equals(uid) || fpeUser || msrEngineer)	
			disableEdit = false;
		break;
	case DocumentStatus.REQ_PLANNING_APPROVING:
		if(fpeUser || msrEngineer)
			disableEdit = false;
		break;
		
	} 
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>

<tsa:hidden name="InspectionHeader_icInspNo" value="-1"/>
<tsa:hidden name="InspectionHeader_icMsrLine" value=""/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value=""/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_req_number%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=s_req_status%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_req_type%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="<%=s_req_subtype%>"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=s_fiscal_year%>"/>
<tsa:hidden name="RequisitionHeader_requiredBy" value="${RequisitionHeader_requiredBy}"/>
<tsa:hidden name="RequisitionHeader_itemLocation" value="<%=s_item_location%>"/>
<tsa:hidden name="Account_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="lineCount" value="<%=i_linecount%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="frompage" value="inspection"/>
<tsa:hidden name="fromstep" value="req-inspection"/>

<tsa:hidden name="punchOutReturnHandler" value=""/>
<tsa:hidden name="punchOutReturnSuccessPage" value=""/>
<tsa:hidden name="icHeader" value="<%=s_req_ic_header%>"/>
<tsa:hidden name="showLinkGetItemInfo" value="N"/>
<tsa:hidden name="punchOutAddAccount" value="N"/>
<tsa:hidden name="nonStandardItem" value="Y"/>
<% if (oid.equalsIgnoreCase("wpc08p")) { %>
<tsa:hidden name="userNameUdf1" value="<%=user.getDepartment()%>"/>
<tsa:hidden name="userNameUdf2" value="<%=user.getNameUdf2()%>"/>
<tsa:hidden name="userNameUdf3" value="1770"/>
<% } %>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
	<% if (s_req_type.equals("M")) {%>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="req-type" defaultString="Req Type"/>&nbsp;<tsa:label labelName="inspection_cart" defaultString="Inspection Info"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } else { %>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="inspection_cart" defaultString="Inspection Info"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
	<% } %>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="req-requisition" defaultString="Requisition #"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=headerEncoder.encodeForHTML(s_req_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="status" defaultString="Status"></tsa:label>:</b></tsa:td>
			<tsa:td width="150px"><%=DocumentStatus.toString(s_req_status, oid)%></tsa:td>
		</tsa:tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tsa:tr>
			<tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>
<% if(s_external_website.equalsIgnoreCase("Y")){ %>
<table width="<%=formEntryWidth%>" cellpadding="0" cellspacing="0" border="0">
	<tsa:tr>
		<tsa:td align="right" cssClass="browseRow"><a href="javascript: window.open('https://login.ihserc.com/login/erc'); void(0);"><img src="<%=contextPath%>/images/cmd_search_item.gif" border="0" alt="Go to Haystack Login Page"></a>&nbsp;
		<a href="javascript: window.open('https://login.ihserc.com/login/erc'); void(0);" title="Click here go Haystack Login Page."><tsa:label labelName="haystackLoginPage" defaultString="Haystack Login Page"></tsa:label></a></tsa:td>
	</tsa:tr>
</table>
<br>
<% }%>

<div style="width: <%= formEntryWidth %>; align: center;">
	<div style="width: 76%; valign: top; float: left;">
		<!-- start rounded corners -->
		<div id="container" style="width: 100%; align: center; margin: 10;">
		<b class="rtop">
		  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
		</b>
		<table cellpadding="0" cellspacing="2" border="0" class="browseHdr" width="100%"  >
		<tsa:tr>
			<tsa:td>
				<table border="0" cellpadding="2" cellspacing="0" width="100%" height="18px">
				<tsa:tr>
					<tsa:td width="5%" height="12px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-lineNumber" defaultString="Line #"></tsa:label></tsa:td>
					<tsa:td field="req-itemNumber" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #"></tsa:label></tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-commodity" defaultString="Commodity"></tsa:label></tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-quantity" defaultString="Quantity"></tsa:label></tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap"><tsa:label labelName="req-hdg-uom" defaultString="UOM" docType="s_req_type" checkRequired="false"></tsa:label></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost"></tsa:label></tsa:td>
					<tsa:td field="req-extendedCost" width="12%" height="18px" cssClass="browseHdr" noWrap="nowrap" align="right"><tsa:label labelName="req-hdg-extendedCost" defaultString="Extended Cost"></tsa:label></tsa:td>
				</tsa:tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td width="100%" align="center" valign="top">
				<table id="itemRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%		if (i_linecount <= 0) { %>
				<tsa:tr>
					<tsa:td align="center" colspan="7"><br><b><tsa:label labelName="noitems" defaultString="There are currently no items in your shopping cart." docType="s_req_type"></tsa:label></b><br><br></tsa:td>
				</tsa:tr>
<%		}
			int iRow = 0;
			for (int i = 0; i < i_linecount; i++)
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i) ;

				BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
				BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
				BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
				BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
				<tsa:tr>
					<tsa:td width="5%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=i + 1%></tsa:td>
					<tsa:td field="req-itemNumber" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap">
						<%=reqLine.getItemNumber()%>
						<input type="hidden" id="icReqLine_<%=i %>" name="icReqLine_<%=i %>" value="<%=reqLine.getIcReqLine()%>">
					</tsa:td>
					<tsa:td field="req-commodity" width="15%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getCommodityCode()%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-quantity" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-uom" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap"><%=reqLine.getUmCode()%></tsa:td>
					<tsa:td field="req-unitCost" width="10%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_unit_price, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
					<tsa:td field="req-extendedCost" width="12%" height="18px" cssClass="browseRow" noWrap="nowrap" align="right"><%=HiltonUtility.getCurrency(bd_extended_cost, s_curr_code, oid)%>&nbsp;&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td width="5%" height="18px" cssClass="browseRow" noWrap="nowrap">&nbsp;</tsa:td>
					<%if(reqLine.getDescription() != null) {
						String desc = HiltonUtility.ckNull((String) reqLine.getDescription());
						String descAux = "";
						String cad[] = desc.split(" ");
						int a = 85, cont = 1;
						if(cad.length < 2)
						{
							for (int c = 0; c < desc.length(); c++)
							{
				        			if(c > a)
				        			{
				        				descAux += " " + desc.charAt(c);
				        				cont ++;
										a = 85;
				        				a = a * cont;
				        			}
				        			else
				        			{
				        				descAux += desc.charAt(c);
				        			}
							}
						} else {
							descAux = desc;
						} %>
						<tsa:td colspan="7" height="18px" cssClass="browseRow"><%=descAux%></tsa:td>
					<% } %>
				</tsa:tr>

				<%
				List inspList = reqLine.getInspectionList() ;
				if (inspList != null ) {
					%>
					<tr>
						<tsa:td colspan="7" height="18px">
							<table cellpadding="0" cellspacing="2" border="0"  width="100%">
							<tr>
							<% if (disableEdit) { %>
							<tsa:td width="8%" height="18px" noWrap="nowrap" align="center">&nbsp;</tsa:td>
							<% } else { %>
							<tsa:td width="8%" height="18px" noWrap="nowrap" align="center"><a href="javascript: newInspection('<%=reqLine.getIcReqLine()%>', '<%=reqLine.getIcLineHistory()%>');void(0);" title="Click here to Create Inspection Details." onMouseOver="highlightRow(<%=iRow%>);" onMouseOut="removeHighlight(<%=iRow%>);">Add</a></tsa:td>
							<% }  %>
							<tsa:td width="12%" height="18px" noWrap="nowrap"><tsa:label labelName="ins-inspectType" defaultString="Inspection" checkRequired="true"></tsa:label></tsa:td>
							<tsa:td width="12%" height="18px"  noWrap="nowrap"><tsa:label labelName="ins-inspectorId" defaultString="Inspector Id" checkRequired="true"></tsa:label></tsa:td>
							<tsa:td width="12%" height="18px" noWrap="nowrap"><tsa:label labelName="ins-engineerId" defaultString="Engineer Id" checkRequired="true"></tsa:label></tsa:td>
							<tsa:td width="8%" height="18px" noWrap="nowrap" align="center">&nbsp;</tsa:td>
						</tr>
						<%
							for (int ix = 0; ix < inspList.size(); ix++) {
							InspectionHeader insp = (InspectionHeader) inspList.get(ix) ;
							%>
							<tr>
								<tsa:td width="8%" height="18px"  noWrap="nowrap">&nbsp;</tsa:td>
							    <% String inspType = insp.getInspectType() ;
							    		String inspDesc = "Receipt Inspection" ;
							    		if (inspType == null) inspType = "RI" ;
							    		if (inspType.equals("FI"))   {
							    			inspDesc = "Field Inspection" ;
							    		} else if (inspType.equals("GI"))   {
							    			inspDesc = "General Inspection" ;
							    		} else if (inspType.equals("CG"))   {
							    			inspDesc = "CGD Inspection" ;
							    		}
							    %>
								<tsa:td width="12%"  height="18px" ><a href="javascript: viewInspection('<%=reqLine.getIcReqLine()%>','<%=insp.getComp_id().getIcInspNo()%>',<%=insp.getComp_id().getIcMsrLine() %>); void(0);" title="Click here to View/Modify Inspection Details." onMouseOver="highlightRow(<%=iRow%>);" onMouseOut="removeHighlight(<%=iRow%>);"><%=inspDesc %></a>&nbsp;&nbsp;
									</tsa:td>
								<tsa:td  width="12%" height="18px" ><%=insp.getInspectorId() %></a></tsa:td>
								<tsa:td  width="12%" height="18px"><%=insp.getEngineerId() %></a></tsa:td>
							<% if (disableEdit) { %>
							<tsa:td width="8%" height="18px" noWrap="nowrap" align="center">&nbsp;</tsa:td>
							<% } else { %>
							<tsa:td width="8%" height="18px" noWrap="nowrap" align="center"><a href="javascript: deleteInspectionDetail('<%=insp.getComp_id().getIcInspNo()%>','<%=insp.getComp_id().getIcMsrLine()%>', '<%=inspDesc%>');void(0);" title="Click here to Delete Inspection Details." onMouseOver="highlightRow(<%=iRow%>);" onMouseOut="removeHighlight(<%=iRow%>);"><img id='delete' valign='baseline' src='<%=contextPath%>/images/delete.gif'  border=0 alt="Delete"></a></tsa:td>
							<% }  %>
							</tr>
						<%
						iRow++;
						}
						%>
						<% iRow++; %>
						</table>
				</tsa:td>
				</tr>
		<% 		} 		%>

<%		iRow++;
		} %>
				</table>
			</tsa:td>
		</tsa:tr>
		</table>
		<b class="rbottom">
		  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
		</b>
		</div>
		<!-- end rounded corners -->
	</div>

<%	if (s_view.equals("WIZARD")) { %>
	<div style="align: right;"><%@ include file="/requests/req_sidebar.jsp" %></div>
<%	} %>
</div>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionHeaderUpdate;RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border="0" alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></tsa:td>
</tsa:tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;
	var reqnumber = "<%=headerEncoder.encodeForJavaScript(s_req_number)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscal_year)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=headerEncoder.encodeForJavaScript(s_item_location)%>";

//	setTableHeights();

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

	function removeHighlight(row) {
		row = row * 2;
		var myTable = document.getElementById("browseRows");
		var myRow = myTable.rows[row];

		setRowClassName(myRow, "browseRow");

		myRow = myTable.rows[row + 1];

		setRowClassName(myRow, "browseRow");
	}

	function addItem()
	{
		var line_count = frm.lineCount.value;
		line_count++;
		frm.lineCount.value = line_count;
		frm.showLinkGetItemInfo.value = "Y";

		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
			frm.nonStandardItem.value = "N";
		<% } %>
		doSubmit('/requests/req_item.jsp','RequisitionLineCreate');
	}

	function browseExternalCatalog(catalog) {
		var punchoutHandlers = "BrowseExternalWebCatalog";
		var handlers = "RequisitionRetrieve";
<% if (s_view.equals("CLASSIC")) { %>
		var pg = "/requests/req_summary.jsp";
<%	} else if (s_view.equals("WIZARD")) { %>
		var pg = "/requests/req_review.jsp";
<%	}%>
		var hiddenFields = "<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">";
		setHiddenFields(hiddenFields);
		<% if (oid.equalsIgnoreCase("wpc08p")) { %>
		if (catalog == "CDW" && frm.userNameUdf3)
			frm.userNameUdf3.value = "1770";
		if (catalog == "CORP EXPRESS" && frm.userNameUdf3)
			frm.userNameUdf3.value = "8150";
		frm.punchOutAddAccount.value = "Y";
		<% } %>

		<%	if (PropertiesManager.getInstance(oid).getProperty("ACCOUNTS", "PUNCHOUTADDACCOUNT", "N").equalsIgnoreCase("Y")) { %>
		frm.punchOutAddAccount.value = "Y";
		<%	} %>

		if (isEmpty(frm.RequisitionHeader_requisitionNumber.value) || frm.RequisitionHeader_requisitionNumber.value == "N/A") {
			handlers = "RequisitionGetFormNumber;" + handlers;
		}

		frm.punchOutReturnHandler.value = "ExternalItemLookup;" + handlers;
		frm.punchOutReturnSuccessPage.value = pg;

		doSubmit('/system/error.jsp', punchoutHandlers);
	}

	function setFirstFocus() {
		if (frm.as_itemNumber != undefined) {
			frm.as_itemNumber.focus();
		}
    }


	function highlightRow(row) {
		return ;
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row) {
		return ;
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function viewInspection(icReqLine, inspNo, icMsrLine)
	{
		frm.RequisitionLine_icReqLine.value =  icReqLine ;
		frm.InspectionHeader_icInspNo.value = inspNo ;
		frm.InspectionHeader_icMsrLine.value = icMsrLine ;

		doSubmit('/requests/inspection_detail.jsp','InspectionRetrieveDetail');
	}


	function newInspection(icReqLine, icMsrLine)
	{

		frm.InspectionHeader_icInspNo.value = "-1" ;
		frm.InspectionHeader_icMsrLine.value = icMsrLine ;
		frm.RequisitionLine_icReqLine.value =  icReqLine ;
		doSubmit('/requests/inspection_detail.jsp','RequisitionLineUpdate;InspectionRetrieveDetail');
	}

	function deleteInspectionDetail(inspNo, icMsrLine,  inspType)
	{
		if (confirm("Are you sure you wish to delete (" + inspType + ")?")) {
			frm.InspectionHeader_icInspNo.value = inspNo ;
			frm.InspectionHeader_icMsrLine.value = icMsrLine ;
			doSubmit('/requests/req_inspections.jsp','InspectionDetailDelete;RequisitionRetrieveInspections');
		}
	}


// End Hide script -->
</SCRIPT>