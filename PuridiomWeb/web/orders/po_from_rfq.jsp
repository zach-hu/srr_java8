<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus"%>
<%@ page import="com.tsa.puridiom.common.documents.OrderType"%>
<%@page import="com.tsa.puridiom.browse.BrowseUtility"%>
<%@ page import="com.tsa.puridiom.entity.*"%>
<%@ page import="java.math.*"%>

<%
	List poList = (List) request.getAttribute("poList");
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
%>

<br><br>
<div id="message" align="center" style="width: 665px; text-align: center;">The following Orders were created from Solicitation <font class=hdr12>#<%=rfqHeader.getRfqNumber()%>&nbsp;</font>: </div>

<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;" align="center">
<table border=0 cellspacing=0 cellpadding=3 width=665px>
	<tr>
		<th class=browseHdrDk nowrap height=18px width=5% align=left>Type</th>
		<th class=browseHdrDk nowrap height=18px width=20% align=left>PO #</th>
		<th class=browseHdrDk nowrap height=18px width=35% align=center>Supplier</th>
		<th class=browseHdrDk nowrap height=18px width=15% align=center>Total</th>
		<th class=browseHdrDk nowrap height=18px width=25% align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></th>
	</tr>
	<%
		for (int i = 0; i < poList.size(); i++)
		{
			PoHeader poHeader = (PoHeader) poList.get(i);
			String classtd = "";
			if (i % 2 == 0)
			{
				classtd = "browseRow";
			} else
			{
				classtd = "summary";
			}
	%>
	<tr class="<%=classtd%>">
		<!--In this part we get the Po Type -->
		<td class="<%=classtd%>" align=center valign=top><%=BrowseUtility.formatBrowseColumnValue(poHeader.getPoType(), "PO-TYPE", oid, userDateFormat)%></td>
		<!--In this part we get the Po Number -->
		<td class="<%=classtd%>" align=left valign=top><a href="javascript: viewPo('<%=poHeader.getIcPoHeader()%>', '<%=poHeader.getPoType()%>'); void(0);"><%=poHeader.getPoNumber()%></a></td>
		<!--In this part we get the Supplier -->
		<td class="<%=classtd%>" align=center valign=top><%=BrowseUtility.formatBrowseColumnValue(poHeader.getVendorName(), "SUPPLIER", oid, userDateFormat)%></td>
		<!--In this part we get the Total -->
		<td class="<%=classtd%>" align=right valign=top><%=BrowseUtility.formatBrowseColumnValue(poHeader.getTotal(), "TOTAL", oid, userDateFormat)%></td>
		<!--In this part we get the Status -->
		<td class="<%=classtd%>" align=center valign=top><%=BrowseUtility.formatBrowseColumnValue(poHeader.getStatus(), "STATUS", oid, userDateFormat)%></td>
	</tr>
	<%
	}
	%>
</table>
</div>

<br>
<br>
<div id="buttons" align="center" style="width: 665px; text-align: center;">
	<div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
</div>

<div id="hiddenFields" style="display:none;"></div>

<%@ include file="/system/footer.jsp"%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function viewPo(ic, type) {
		frm.viewType.value = "WIZARD";
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/orders/po_review.jsp','PoRetrieve');
	}

	function goToMainMenu() {

	}

// End Hide script -->
</SCRIPT>
