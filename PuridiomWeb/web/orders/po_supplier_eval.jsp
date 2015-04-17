<%@ page language="java" errorPage="/system/error.jsp" %>
<%@page import="org.owasp.esapi.reference.DefaultEncoder"%>
<%@page import="org.owasp.esapi.Encoder"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.PerformanceDetail" %>
<%@ page import="com.tsa.puridiom.common.documents.SupplierPerformanceRatings" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dates.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	Vendor vendor = (Vendor) request.getAttribute("vendor");
	if(vendor == null)
	{
		vendor = new Vendor();
	}
	List ratingsList = (List) request.getAttribute("performanceDetailList");
	BigDecimal	bd_icPoHeader = poHeader.getIcPoHeader();
	String icPoHeader = bd_icPoHeader.toString();
	String	returnPage = (String)request.getAttribute("returnPage");
	String	returnHandler = (String)request.getAttribute("returnHandler");
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	fromEmail = (String) request.getAttribute("fromEmail");
	String	poNumber = (String) request.getAttribute("poNumber");
	String	poRated = poHeader.getRated();
	BigDecimal	poVendorRating = poHeader.getVendorRating();
	BigDecimal	vendorRating = (BigDecimal)request.getAttribute("vendorRating");
	BigDecimal	ordersRated = (BigDecimal)request.getAttribute("ordersRated");
	boolean allowEvaluation = false;

	if (ratingsList == null) {	ratingsList = new ArrayList();	}
	if (HiltonUtility.isEmpty(returnPage)) {	returnPage = "orders/po_supplier_eval_conf.jsp";	}
	if (HiltonUtility.isEmpty(returnHandler)) {	returnHandler = "DoNothing";	}
	if (HiltonUtility.isEmpty(poRated)) {	poRated = "N";	}
	if (HiltonUtility.isEmpty(fromEmail)) {	fromEmail = "N";	}
	if (poVendorRating  ==  null) { 	  poVendorRating = new BigDecimal(0);		}
	if (ordersRated  ==  null) { 	  ordersRated = new BigDecimal(0);		}
%>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="Vendor_vendorId" value="<%=vendor.getVendorId()%>"/>
<tsa:hidden name="Vendor_vendorName" value="<%=vendor.getVendorName()%>"/>
<tsa:hidden name="PerformanceDetail_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="poNumberDisplay" value="<%=poHeader.getDisplayPoNumber()%>"/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tr>
			<td height="1px" class="darkShadow" colspan="2"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="supplier-performance-evaluation" defaultString="Supplier Performance Evaluation" /></div>
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
			<td nowrap align=right class=label><tsa:label labelName="po-order-number" defaultString="Order #" />:</td>
			<td nowrap align=left><%=poNumber%></td>
			<td nowrap align=right class=label vAlign=top rowspan=2><tsa:label labelName="order-rating" defaultString="Order Rating" />:</td>
			<td nowrap align=center rowspan=2 vAlign=top><% if (poRated.equals("Y")) {%><%=SupplierPerformanceRatings.getRatingImage(oid, poVendorRating, false)%><br><b><%=poVendorRating%></b><%} else {%>N/A<%}%></td>
			<td nowrap align=right class=label vAlign=top rowspan=2><tsa:label labelName="vendor-rating" defaultString="Vendor Rating" />:</td>
			<td nowrap align=center rowspan=2><%=SupplierPerformanceRatings.getRatingImage(oid, vendorRating, false)%><br><b><%=vendorRating%></b></td>
	  	</tr>
	  	<tr>
			<td nowrap align=right class=label><tsa:label labelName="orders-rated" defaultString="Orders Rated" />:</td>
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
			<td nowrap height=18px class=browseHdrDk width=35% align=left><tsa:label labelName="evaluation-name" defaultString="Evaluation Name" /></td>
			<td nowrap height=18px class=browseHdrDk width=15% align=left><tsa:label labelName="rating" defaultString="Rating" /></td>
			<td nowrap height=18px class=browseHdrDk width=20% align=left><tsa:label labelName="status" defaultString="Status" /></td>
			<td nowrap height=18px class=browseHdrDk width=12% align=left><tsa:label labelName="date" defaultString="Date" /></td>
			<td nowrap height=18px class=browseHdrDk width=18% align=left><tsa:label labelName="evaluator" defaultString="Evaluator" /></td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
		<tr><td><b><tsa:label labelName="noRecordsFoundMessage" defaultString="There were no records found in the database matching your criteria." /></b></td></tr>
		</table>
		</div>
		<table id=browseRows border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>
<%
		int rowCount = 0;
		BigDecimal bdStatus = new BigDecimal(0);
		Encoder encoder = DefaultEncoder.getInstance();
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
				if (rowCount > 0) {
%>
		<tr><td colspan=5><hr size=0></td></tr>
<%			}%>
		<tr class="browseRow">
			<td height=18px class="browseRow" align=left width=35% valign=top><%=evalName%></td>
			<td height=18px class="browseRow" align=left width=15% valign=top>
<%	if (perfDetail.getEvaluator().equals(uid) && (perfDetail.getStatus().equals(DocumentStatus.SUP_PERFORMANCE_INCOMPLETE) || perfDetail.getStatus().equals(DocumentStatus.SUP_PERFORMANCE_INPROGRESS))) {
			allowEvaluation = true; %>
				<select name="rating" onchange="setEvalDate(<%=rowCount%>);">
					<option value="<%=SupplierPerformanceRatings.UNSATISFACTORY %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(0))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.UNSATISFACTORY)%></option>
					<option value="<%=SupplierPerformanceRatings.POOR %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(1))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.POOR)%></option>
					<option value="<%=SupplierPerformanceRatings.MARGINAL %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(2))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.MARGINAL)%></option>
					<option value="<%=SupplierPerformanceRatings.GOOD %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(3))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.GOOD)%></option>
					<option value="<%=SupplierPerformanceRatings.EXCELLENT %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(4))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.EXCELLENT)%></option>
					<option value="<%=SupplierPerformanceRatings.NONE %>" <%if(perfDetail.getEvalRating().equals(new BigDecimal(9))) {%>selected <%} %>><%=SupplierPerformanceRatings.getText(SupplierPerformanceRatings.NONE)%></option>
				</select>
<%	} else {%>
				<%=SupplierPerformanceRatings.getText(perfDetail.getEvalRating())%>
				<tsa:hidden name="rating" value=""/>
<%	}%>
			</td>
			<td height=18px class="browseRow" align=left width=20% valign=top id="statusText_<%=rowCount%>"><%=DocumentStatus.toString(perfDetail.getStatus(), oid)%></td>
			<td height=18px class="browseRow" align=left width=12% valign=top><%=HiltonUtility.getFormattedDate(perfDetail.getEvalDate(), oid, userDateFormat)%></td>
			<td height=18px class="browseRow" align=left width=18% valign=top><%=com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, perfDetail.getEvaluator()).getDisplayName()%>
				<tsa:hidden name="PerformanceDetail_evalSection" value="<%=perfDetail.getComp_id().getEvalSection()%>"/>
				<tsa:hidden name="PerformanceDetail_evalNumber" value="<%=perfDetail.getComp_id().getEvalNumber()%>"/>
				<tsa:hidden name="PerformanceDetail_dateAssigned" value="<%=perfDetail.getDateAssigned()%>"/>
				<tsa:hidden name="PerformanceDetail_assignedBy" value="<%=perfDetail.getAssignedBy()%>"/>
				<tsa:hidden name="PerformanceDetail_status" value="<%=perfDetail.getStatus() %>"/>
				<tsa:hidden name="PerformanceDetail_isNew" value="<%=perfDetail.getIsNew() %>"/>
				<tsa:hidden name="PerformanceDetail_evalDate" value="<%=perfDetail.getEvalDate() %>"/>
				<tsa:hidden name="PerformanceDetail_evalRating" value="<%=perfDetail.getEvalRating() %>"/>
				<tsa:hidden name="PerformanceDetail_evalWeight" value="<%=perfDetail.getEvalWeight() %>"/>
				<tsa:hidden name="PerformanceDetail_evaluator" value="<%=perfDetail.getEvaluator() %>"/>
				<tsa:hidden name="originalStatus" value="<%=perfDetail.getStatus() %>"/>
				<tsa:hidden name="originalStatusText" value="<%=DocumentStatus.toString(perfDetail.getStatus(), oid)%>"/>
			</td>
		</tr>
		<tr>
			<td vAlign=top align=right><b><tsa:label labelName="notes" defaultString="Notes" />:</b></td>
			<td height=18px class="browseRow" align=left colspan=4 valign=top>
<%	if (perfDetail.getEvaluator().equals(uid) && (perfDetail.getStatus().equals(DocumentStatus.SUP_PERFORMANCE_INCOMPLETE) || perfDetail.getStatus().equals(DocumentStatus.SUP_PERFORMANCE_INPROGRESS))) {%>
				<tsa:input labelName="notes" type="textarea" wrap="virtual" name="PerformanceDetail_internalNotes" tabIndex="4" rows="5" cols="80" onkeydown="textCounter(this, 2000);" onkeyup="textCounter(this,2000);" onchange="textCounter(this,2000);"><%= encoder.encodeForHTML(perfDetail.getInternalNotes())%></tsa:input>
<%	} else {%>
				<%=perfDetail.getInternalNotes()%>&nbsp;<tsa:hidden name="PerformanceDetail_internalNotes" value="<%=perfDetail.getInternalNotes()%>"/>
<%	}%>
			</td>
		</tr>
<%			rowCount++;
			}
		}%>
		</table>
		</div>
	</td>
</tr>
<tr><td colspan=2 align=center><div id="norows" style="visiblity:hidden;display:none" class="error"><br><tsa:label labelName="noEvaluationsMessage" defaultString="No Evaluations have been Setup." /></div></td></tr>
</table>
<br>
<br>
<tsa:hidden name="pageFrom" value="supplier"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
<%	if (allowEvaluation) {%>
	<td width=50% align="center"><div id="pxbutton"><a href="javascript: updatePerformance(); void(0);"><tsa:label labelName="save" defaultString="Save" /></a></div></td>
<%	}
		if (!fromEmail.equals("Y")) {%>
	<td width=50% align="center"><div id="pxbutton"><a href="javascript: doSubmit('<%= headerEncoder.encodeForJavaScript(returnPage) %>', '<%= headerEncoder.encodeForJavaScript(returnHandler) %>'); void(0);"><tsa:label labelName="return" defaultString="Return" /></a></div></td>
<%	}%>
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
<%	}%>
		frm.allowBrowse.value = "false";

	function updatePerformance() {
  		doSubmit("<%= headerEncoder.encodeForJavaScript(returnPage) %>", "VendorPerformanceUpdate;<%= headerEncoder.encodeForJavaScript(returnHandler) %>");
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

	function setEvalDate(indexRow)
	{
		var columns = frm.elements.item("rating");
		if (columns != undefined && columns.length > 1)
		{
			rating = frm.rating[indexRow][frm.rating[indexRow].selectedIndex].value;
			frm.PerformanceDetail_evalRating[indexRow].value = rating;
			if (rating == '9')
			{
				frm.PerformanceDetail_evalDate[indexRow].value = '';
				frm.PerformanceDetail_status[indexRow].value = frm.originalStatus[indexRow].value;
				setInnerText("statusText_" + indexRow, frm.originalStatusText[indexRow].value);
			}
			else
			{
				frm.PerformanceDetail_evalDate[indexRow].value = '<%=Dates.today(userDateFormat,user.getTimeZone())%>';
				frm.PerformanceDetail_status[indexRow].value = '<%=DocumentStatus.SUP_PERFORMANCE_COMPLETE%>';
				setInnerText("statusText_" + indexRow, "<%=DocumentStatus.toString(DocumentStatus.SUP_PERFORMANCE_COMPLETE, oid)%>");
			}
		}
		else
		{
			rating = frm.rating[frm.rating.selectedIndex].value;
			frm.PerformanceDetail_evalRating.value = rating;
			if (rating == '9')
			{
				frm.PerformanceDetail_evalDate.value = '';
				frm.PerformanceDetail_status.value = frm.originalStatus.value;
				setInnerText("statusText_" + indexRow, frm.originalStatusText.value);
			}
			else
			{
				frm.PerformanceDetail_evalDate.value = '<%=Dates.today(userDateFormat, userTimeZone)%>';
				frm.PerformanceDetail_status.value = '<%=DocumentStatus.SUP_PERFORMANCE_COMPLETE%>';
				setInnerText("statusText_" + indexRow, "<%=DocumentStatus.toString(DocumentStatus.SUP_PERFORMANCE_COMPLETE, oid)%>");
			}
		}
	  }

// End Hide script -->
</SCRIPT>
