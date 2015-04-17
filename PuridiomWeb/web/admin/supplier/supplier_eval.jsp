<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.SupplierPerformanceRatings" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dates.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

  Vendor vendor = (Vendor) request.getAttribute("vendor");
  List ratingsList = (List) request.getAttribute("performanceDetailList");
  String	errorMsg = (String) request.getAttribute("errorMsg");
  String	PerformanceDetail_icPoHeader = (String) request.getAttribute("PerformanceDetail_icPoHeader");
  String returnPage = (String)request.getAttribute("returnPage");
  if(HiltonUtility.isEmpty(returnPage)){	returnPage = "admin/admin_menu.jsp";	}
  String returnHandler = (String)request.getAttribute("returnHandler");
  if(HiltonUtility.isEmpty(returnHandler)){	returnHandler = "DoNothing";	}
  String s_po_number = (String)request.getAttribute("poNumber");
  String poRated = (String) request.getAttribute("PoHeader_rated");
  System.out.println("poRated = " + poRated);
  if(HiltonUtility.isEmpty(poRated)){	poRated = "N";	}
  BigDecimal vendorRating = (BigDecimal)request.getAttribute("vendorRating");
  if(vendorRating == null){ 	vendorRating = new BigDecimal(0);		}
  BigDecimal   PoHeader_vendorRating = (BigDecimal)request.getAttribute("PoHeader_vendorRating");
  if(PoHeader_vendorRating  ==  null){ 	  PoHeader_vendorRating = new BigDecimal(0);		}
  BigDecimal   ordersRated = (BigDecimal)request.getAttribute("ordersRated");
  if(ordersRated  ==  null){ 	  ordersRated = new BigDecimal(0);		}
  boolean allowAssignment = false;
%>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Vendor_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendor.getVendorName()%>"/>
<tsa:hidden name="PerformanceDetail_icPoHeader" value="<%=PerformanceDetail_icPoHeader%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=PerformanceDetail_icPoHeader%>"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow" colspan="2"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12">Supplier Performance Evaluation</div>
			</td>
			<td nowrap class="hdr12" valign="middle">&nbsp;</td>
		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
    <%@ include file="/admin/supplier/supplier_evaluation_name.jsp" %>
	</td>
</tr>
</table>
<br>
<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
	<table border="0" cellpadding=2 cellspacing="0" width=680px>
	<tr>
	  <td width="100%" align=center class=error><%=errorMsg%></td>
	</tr>
	</table>
<%	}%>
<br>
<%
	String evalQty = propertiesManager.getProperty("SUPPLIER EVAL", "QTY","10");
	int qty = ratingsList.size();
%>
<table border="0" cellpadding="2" cellspacing="0" width="680px">
<tr>
	<td align=right>
		<table border=0 cellspacing="0" cellpadding="1" width=100%>
		<tr>
			<td nowrap align=right class=label>Order #:</td>
			<td nowrap align=left><%=s_po_number%></td>
			<td nowrap align=right class=label vAlign=top rowspan=2>Order Rating:</td>
			<td nowrap align=center rowspan=2 vAlign=top><% if (poRated.equals("Y")) {%><%=SupplierPerformanceRatings.getRatingImage(oid, PoHeader_vendorRating, false)%><br><b><%=PoHeader_vendorRating%></b><%} else {%>N/A<%}%></td>
			<td nowrap align=right class=label vAlign=top rowspan=2>Vendor Rating:</td>
			<td nowrap align=center rowspan=2><%=SupplierPerformanceRatings.getRatingImage(oid, vendorRating, false)%><br><b><%=vendorRating%></b></td>
	  	</tr>
	  	<tr>
			<td nowrap align=right class=label>Orders Rated:</td>
			<td nowrap align=center><b><a href="javascript: getOrdersRated(); void(0);"><%=ordersRated%></a></b></td>
	  	</tr>
	  	</table>
	</td>
</tr>
</table>
<br>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=665px>
		<tr>
			<td nowrap height=18px class=browseHdrDk width=36% align=left>Evaluation Name</td>
			<td nowrap height=18px class=browseHdrDk width=14% align=left>Rating</td>
			<td nowrap height=18px class=browseHdrDk width=20% align=left><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></td>
			<td nowrap height=18px class=browseHdrDk width=12% align=left>Date</td>
			<td nowrap height=18px class=browseHdrDk width=18% align=left>Evaluator</td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
<%
		int rowCount = 0;
		BigDecimal bdStatus = new BigDecimal(0);
		for (int i = 0;i < qty; i++) {
			PerformanceDetail perfDetail = (PerformanceDetail)ratingsList.get(i);
			String evalName = perfDetail.getComp_id().getEvalSection();
			if (!HiltonUtility.isEmpty(evalName)) {
				try {
					BigDecimal bdTempStatus = new BigDecimal(perfDetail.getStatus());
					if (bdTempStatus.compareTo(bdStatus) < 0 || bdStatus.compareTo(new BigDecimal(0)) == 0) {
						bdStatus = bdTempStatus;
					}
				} catch (Exception e) {
				}
%>
		<tr class="browseRow">
			<td height=18px class="browseRow" align=left width=36% valign=top><%=evalName%></td>
			<td height=18px class="browseRow" align=left width=14% valign=top><%=SupplierPerformanceRatings.getText(perfDetail.getEvalRating())%></td>
			<td height=18px class="browseRow" align=left width=20% valign=top id="statusText_<%=rowCount%>"><%=DocumentStatus.toString(perfDetail.getStatus(), oid)%></td>
			<td height=18px class="browseRow" align=left width=12% valign=top><%=HiltonUtility.getFormattedDate(perfDetail.getEvalDate(), oid, userDateFormat)%></td>
			<td height=18px class="browseRow" align=left width=18% valign=top rowspan=2>
				<tsa:hidden name="PerformanceDetail_evalSection" value="<%=perfDetail.getComp_id().getEvalSection()%>"/>
				<tsa:hidden name="PerformanceDetail_evalNumber" value="<%=perfDetail.getComp_id().getEvalNumber()%>"/>
				<tsa:hidden name="PerformanceDetail_dateAssigned" value="<%=perfDetail.getDateAssigned()%>"/>
				<tsa:hidden name="PerformanceDetail_assignedBy" value="<%=perfDetail.getAssignedBy()%>"/>
				<tsa:hidden name="PerformanceDetail_status" value="<%=perfDetail.getStatus() %>"/>
				<tsa:hidden name="PerformanceDetail_isNew" value="<%=perfDetail.getIsNew() %>"/>
				<tsa:hidden name="PerformanceDetail_evalRating" value="<%=perfDetail.getEvalRating() %>"/>
				<tsa:hidden name="PerformanceDetail_evalWeight" value="<%=perfDetail.getEvalWeight() %>"/>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td><input type="text" name="PerformanceDetail_evaluator" value="<%=perfDetail.getEvaluator() %>" <% if (role.getAccessRights("VEND") < 99 || !(perfDetail.getIsNew().equals("Y"))) {%>DISABLED<%} %> onchange="upperCase(this); getNewInfo('evaluator', this, <%=rowCount %>);"></td>
					<td valign=middle>&nbsp;<%if(role.getAccessRights("VEND") == 99){%><a href="javascript: setEvaluator(<%=rowCount %>); void(0);"><img src="<%=contextPath%>/images/browser.gif" border="0"></a><%} %></td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="as_name" disabled="disabled" value="<%=com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, perfDetail.getEvaluator()).getDisplayName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height=18px class="browseRow" align=left colspan=4 valign=top>
				<b>&nbsp;&nbsp;Notes:</b>&nbsp;<%=perfDetail.getInternalNotes()%>
			</td>
		</tr>
<%			rowCount++;
			}
		}%>
		</table>
		</div>
	</td>
</tr>
<tr><td colspan=2 align=center><div id="norows" style="visiblity:hidden;display:none" class="error"><br>No Evaluations have been Setup.</div></td></tr>
</table>
<br>
<br>
<tsa:hidden name="pageFrom" value="supplier"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
<%	if (rowCount > 0 && role.getAccessRights("VEND") == 99 && !(poRated.equals("Y")) && bdStatus.compareTo(new BigDecimal(DocumentStatus.SUP_PERFORMANCE_INCOMPLETE)) <= 0) {
	allowAssignment = true;
%>
	<td width=50% align="center"><a href="javascript: updatePerformance(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border="0" tabIndex="50"></a></td>
<%	}%>
	<td width=50% align="center"><a href="javascript: doSubmit('<%=headerEncoder.encodeForHTMLAttribute(returnPage) %>', '<%=returnHandler %>'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border="0" tabIndex="51"></a></td>
</tr>
</table>

<div id="dummyFields" style="display:none;">
<tsa:hidden name="PerformanceDetail_status" value=""/>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

  //setNavCookie("/admin/supplier/supplier_eval.jsp", "VendorRetrieveById", menuText);

<%	if (rowCount < 1) {%>
	displayArea("norows");
<%	}
		if (!allowAssignment) {%>
		frm.allowBrowse.value = "false";
<%	}%>

	function updatePerformance() {
  		doSubmit("<%=headerEncoder.encodeForJavaScript(returnPage) %>", "VendorPerformanceAssignEvaluators;<%=returnHandler%>");
	}

	function setEvaluator(index)
	{
		currentRow = index;
		browseLookup('PerformanceDetail_evaluator', 'user');
	}

	function getOrdersRated()
	{
		popupParameters = "colname=PoHeader_vendorId;operator==;filter_txt=" + frm.Vendor_vendorId.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('', 'po-rated');
	}

// End Hide script -->
</SCRIPT>
