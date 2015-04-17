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
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="bomType" value=""/>
<tsa:hidden name="bomAction" value=""/>
<tsa:hidden name="BomRouting_icRouting" value=""/>

<tsa:hidden name="itemAction" value="UPDATE"/>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');" title="Parent Item">Parent Item</a></td>
	<td align=center><a href="javascript: doSubmit('/inventory/bom_routings.jsp', 'BomMethodRetrieveByItem');" title="Methods">Methods</a></td>
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
				<table id=bomRoutingsTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Routings for Parent Item "<%=invItemNumber%>"</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td  align=center valign=bottom width=5%><b>Line</b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-stageId")%> align=center valign=bottom width=14%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-stageId", "Routing Id")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-description")%> align=center valign=bottom width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-description", "Description")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-workCenterId")%> align=center valign=bottom width=14%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-workCenterId", "Work Center")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-machineId")%> align=center valign=bottom width=14%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmr-machineId", "Machine Id")%></b></td>
							<td align=center valign=bottom width=10%><b>Delete</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
<%
	List bomRoutingList = (List)request.getAttribute("bomRoutingList");
	for(int i=0;i<bomRoutingList.size();i++)
	{
		BomRouting bomRouting = (BomRouting) bomRoutingList.get(i);
%>
						<tr>
							<td  width=5% align=right nowrap><%=(i + 1)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-stageId")%> width=14% align=left nowrap>
								<a href="javascript: viewRouting(<%=i%>);" title="Edit Routing"><%=HiltonUtility.ckNull(bomRouting.getStageId())%></a>
								<tsa:hidden id="bomRouting_<%=i%>" name="bomRouting_<%=i%>" value="<%=bomRouting.getIcRouting() %>"/>
								<tsa:hidden id="bomLine_<%=i%>" value="<%=(i + 1) %>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-description")%> width=40% align=left nowrap><%=HiltonUtility.ckNull(bomRouting.getDescription())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-workCenterId")%> width=14% align=left nowrap><%=HiltonUtility.ckNull(bomRouting.getWorkCenterId())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-machineId")%> width=14% align=left nowrap><%=HiltonUtility.ckNull(bomRouting.getMachineId())%></td>

							<td width=10% align=center nowrap><a href="javascript: deleteRouting(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Routing"></a></td>
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
	<td width=50% align=center><a href="javascript: addRouting(); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0 alt="Add"></a></td>
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

	function viewRouting(row)
	{
		var loc = document.getElementById("bomRouting_" + row);
		frm.BomRouting_icRouting.value = loc.value;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_routing.jsp','BomRoutingRetrieveById');
	}

	function deleteRouting(row) {
		var loc = document.getElementById("bomRouting_" + row);
		var line = document.getElementById("bomLine_" + row).value ;
		frm.bomAction.value = "DELETE";
		frm.BomRouting_icRouting.value = loc.value;
		confirm("Are you sure you want to delete line " + line + "?");
		doSubmit('inventory/bom_routings.jsp','BomRoutingDelete;BomRoutingRetrieveByItem');
	}

	function addRouting()
	{
		frm.bomAction.value = "CREATE";
		doSubmit('/inventory/bom_routing.jsp', 'DoNothing');
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