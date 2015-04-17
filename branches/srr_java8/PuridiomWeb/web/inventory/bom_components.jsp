<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String invModule = propertiesManager.getProperty("Modules", "Standard Inventory", "N");
	String invItemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="bomType" value=""/>
<tsa:hidden name="bomAction" value=""/>
<tsa:hidden name="BomComponent_icComponent" value=""/>
<tsa:hidden name="bomSource" value="<%bomSource%>"/>

<tsa:hidden name="itemAction" value="UPDATE"/>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');" title="Parent Item">Parent Item</a></td>
	<td align=center><a href="javascript: doSubmit('/inventory/bom_routings.jsp', 'BomRoutingRetrieveByItem');" title="Routings">Routing</a></td>
	<td align=center><a href="javascript: doSubmit('/inventory/bom_components.jsp', 'BomComponentRetrieveByItem');" title="Components">Components</a></td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=bomComponentsTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Components for Parent Item "<%=invItemNumber%>"</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bmc-stageId")%> align=left valign=bottom width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bom-stageId", "Stage")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-bomLine")%> align=left valign=bottom width=5%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bom-bomLine", "Line")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-componentItem")%> align=left valign=bottom width=15%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-componentItem", "Item")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-description")%> align=left valign=bottom width=30%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-description", "Description")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-usageQty")%> align=right valign=bottom width=9%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-usageQty", "Usage Qty")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-scrapPerc")%> align=right valign=bottom width=9%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-scrapPerc", "Scrap %")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-fromDate")%> align=left valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-fromDate", "From Date")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-thruDate")%> align=left valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-thruDate", "To Date")%></b></td>
							<td align=center valign=bottom width=6%><b>Delete</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
<%
	List bomComponentList = (List)request.getAttribute("bomComponentList");
	for(int i=0;i<bomComponentList.size();i++)
	{
		BomComponent bomComponent = (BomComponent) bomComponentList.get(i);
		String s_item_number = bomComponent.getComponentItem() ;
%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bmc-stageId")%> width=10% align=left nowrap><%=bomComponent.getStageId()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-bomLine")%> width=6% align=left nowrap>
								<a href="javascript: viewComponent(<%=i%>);" title="Edit Component"><%=(i + 1)%></a>
								<tsa:hidden id="bomComponent_<%=i%>" name="bomComponent_<%=i%>" value="<%=bomComponent.getIcComponent() %>"/>
								<tsa:hidden id="bomLine_<%=i%>" name="bomLine_<%=i%>" value="<%=(i + 1)%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-componentItem")%> width=14% align=left nowrap><%=bomComponent.getComponentItem()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-description")%> width=30% align=left nowrap><%=bomComponent.getDescription()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-getUsageQty")%> width=9% align=right nowrap><%=HiltonUtility.getFormattedQuantity(bomComponent.getUsageQty(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-scrapPerc")%> width=9% align=right nowrap><%=bomComponent.getScrapPerc()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-fromDate")%> width=8% align=left nowrap><%=HiltonUtility.getFormattedDate(bomComponent.getFromDate(), oid, userDateFormat)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-thruDate")%> width=8% align=left nowrap><%=HiltonUtility.getFormattedDate(bomComponent.getThruDate(), oid, userDateFormat)%></td>
							<td width=6% align=center nowrap><a href="javascript: deleteComponent(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Component"></a></td>
						</tr>
<% } %>
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
	<td width=50% align=center><a href="javascript: addComponent(); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0 alt="Add"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

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

	function viewComponent(row)
	{
		var loc = document.getElementById("bomComponent_" + row);
		frm.BomComponent_icComponent.value = loc.value;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_component.jsp','BomComponentRetrieveById');
	}

	function deleteComponent(row) {
		var loc = document.getElementById("bomComponent_" + row);
		var line = document.getElementById("bomLine_" + row).value ;
		frm.bomAction.value = "DELETE";
		frm.BomComponent_icComponent.value = loc.value;
		confirm("Are you sure you want to delete line " + line + "?");
		doSubmit('inventory/bom_components.jsp','BomComponentDelete;BomComponentRetrieveByItem');
	}

	function addComponent()
	{
		frm.bomAction.value = "CREATE";
		doSubmit('/inventory/bom_component.jsp', 'DoNothing');
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


// End Hide script -->
</SCRIPT>