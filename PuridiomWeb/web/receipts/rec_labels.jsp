<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.common.entity.*" %>

<%
	String	icHeader = (String) request.getAttribute("ReceiptHeader_icRecHeader");
	String	receiptNumber = (String) request.getAttribute("ReceiptHeader_receiptNumber");
	String	receiptType = (String) request.getAttribute("ReceiptHeader_receiptType");
	String	poStatus = (String) request.getAttribute("PoHeader_poStatus");
	String	poNumber = (String) request.getAttribute("PoHeader_poNumber");
	String	s_fiscal_year = (String) request.getAttribute("PoHeader_fiscalYear");
	String	releaseNumber = (String) request.getAttribute("PoHeader_releaseNumber");
	String	revisionNumber = (String) request.getAttribute("PoHeader_revisionNumber");
	String	receiptMethod = (String) request.getAttribute("receiptMethod");

	String	allowEdit = (String) request.getAttribute("allowEdit");
	String	returnPage = (String) request.getAttribute("returnPage");

	if (HiltonUtility.isEmpty(returnPage))
	{
		returnPage = "/receipts/rec_confirmation.jsp";
	}
	String	returnHandler = (String) request.getAttribute("returnHandler");
	if (HiltonUtility.isEmpty(returnHandler))
	{
		returnHandler = "ReceiptLineRetrieveByHeader";
	}
	String	browseId = HiltonUtility.ckNull((String) request.getAttribute("browseId"));
	boolean editMode = false;

%>
<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=icHeader%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=receiptNumber%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=receiptType%>"/>
<tsa:hidden name="PoHeader_poStatus" value="<%=poStatus%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=poNumber%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=releaseNumber%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=revisionNumber%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>

<tsa:hidden name="filename" value=""/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="returnHandler" value="<%=returnHandler%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="browseId" value="<%=browseId%>"/>
<%
	if (HiltonUtility.isEmpty(receiptNumber)) {
		receiptNumber = "N/A";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Receipt Labels</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<div id="lineList" style="visibility: visible; display: block'">
			<table border=0 cellpadding=1 cellspacing=0 width=500px>
			<tr>
				<td width=15% class=browseHdrDk>Item #</td>
				<td width=45% class=browseHdrDk>Description</td>
				<td width=10% class=browseHdrDk>Asset Number</td>
				<td width=10% class=browseHdrDk>Tag Number</td>
				<td width=10% class=browseHdrDk>Serial Number</td>
				<td width=5% class=browseHdrDk align=center>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=8 vAlign=top>
					<div id="noReceiptLinesMsg" style="visibility: hidden; display: none;">
					<table border=0 cellpadding=1 cellspacing=0 width=500px>
					<tr><td width=100% align=center><br>There are no available property records to print.<br></td></tr>
					</table>
					</div>
					<table id="assets" border=0 cellpadding=1 cellspacing=0 width=680px>

<%
	List receiptLineList = (List) request.getAttribute("receiptLineList");

	if (receiptLineList != null) {
		for (int i = 0; i < receiptLineList.size(); i++) {
			ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i) ;
			PoLine poLine = receiptLine.getPoLine() ;
			List propertyList = (List) receiptLine.getInvPropertyList() ;
			if (propertyList != null) {
			for (int ix = 0; ix < propertyList.size(); ix++) {
				InvProperty invProperty = (InvProperty) propertyList.get(ix) ;
			%>
			<tr>
				<td width=15% valign="top" class="browseRow"><%=poLine.getItemNumber()%></td>
				<td width=45% valign="top" class="browseRow"><%=poLine.getDescription()%></td>
				<td width=10% valign="top" class="browseRow"><%=invProperty.getAssetNumber()%></td>
				<td width=10% valign="top" class="browseRow"><%=invProperty.getTagNumber()%></td>
				<td width=10% valign="top" class="browseRow"><%=invProperty.getSerialNumber()%></td>
				<td width=5% valign="top">
					<a href="javascript: printReceiptLineLabels('<%=receiptLine.getIcRecLine().toString()%>','<%=invProperty.getAssetNumber()%>'); void(0);"> <img src="<%=contextPath%>/images/barcode_sm.gif" border="0" alt="Print Label"></a>
				</td>
			</tr>

<%			}
		} %>

<%	}
	} %>
					</table>
				</td>
			</tr>
			</table>
			<br>
		</div>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: printReceiptHeaderLabels(); void(0);"><img class=button src="<%=contextPath%>/images/button_print.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('<%=returnPage%>', '<%=returnHandler%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var myTable = document.getElementById("assets");
	var totalRows = myTable.rows.length
	var inEditMode = "<%=editMode%>";
	var currentPage = "/receipts/rec_labels.jsp";
	var returnPage = "<%=returnPage%>";
	var returnHandler = "<%=returnHandler%>";
	var icHeader = "<%=icHeader%>" ;

	function validateForm() {
		return true ;
	}


  function printReceiptLineLabels(ic, aNum) {
    popupParameters="ReceiptLine_icRecLine=" + ic  ;
	popupParameters = popupParameters + ";InvProperty_assetNumber=" + aNum;

    doSubmitToPopup('', 'PrintRecLabelsByLinePdf', 'width=400px', 'height=600px');
  }

	function printReceiptHeaderLabels() {
    	popupParameters="ReceiptLine_icRecHeader=" + icHeader + ";viewNow=Y" ;
	    doSubmitToPopup('', 'PrintRecLabelsByHeaderPdf', 'width=400px', 'height=600px');
	}
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>