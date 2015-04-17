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
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod");
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;
%>

<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>
<tsa:hidden name="bomType" value=""/>
<tsa:hidden name="bomAction" value=""/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icRouting" value=""/>
<tsa:hidden name="BomTask_icRouting" value=""/>
<tsa:hidden name="BomComponent_icComponent" value=""/>
<tsa:hidden name="BomManufacturer_icComponent" value=""/>

<tsa:hidden name="itemAction" value="UPDATE"/>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
		<td class=menuSubTitle  width=30% height=25px nowrap>&nbsp;<a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById');" title="Parent Item">Parent Item:</a> <%=invItemNumber%></td>
		<td class=body  width=60% height=25px ><%=invDescription%></td>
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
					<td width=100%>
					<table border=0 cellspacing=0 cellpadding=2 width=100%>
					<tr>
						<td class=hdr12  width=80% height=25px nowrap>&nbsp;Routings</td>
						<td class=browseHdr  width=20% align=center><a href="javascript: addRouting(); void(0);" title="Add Routing">Add</a></td>
					</tr>
					</table>
					</td>
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
								<tsa:hidden id="bomRoutingLine_<%=i%>" name="bomRoutingLine_<%=i%>" value="<%=(i + 1) %>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmr-description")%> width=40% align=left ><%=HiltonUtility.ckNull(bomRouting.getDescription())%></td>
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
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=bomComponentsTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100%>
					<table border=0 cellspacing=0 cellpadding=2 width=100%>
					<tr>
						<td class=hdr12  width=80% height=25px nowrap>&nbsp;Components</td>
						<td class=browseHdr  width=20% align=center><a href="javascript: addComponent(); void(0);" title="Add Component">Add</a></td>
					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "bmc-stageId")%> align=left valign=bottom width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bom-stageId", "Stage")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-bomLine")%> align=left valign=bottom width=5%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bom-bomLine", "Line")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-componentItem")%> align=left valign=bottom width=15%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-componentItem", "Item")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-description")%> align=left valign=bottom width=44%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-description", "Description")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-usageQty")%> align=right valign=bottom width=9%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-usageQty", "Usage Qty")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-scrapPerc")%> align=right valign=bottom width=9%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-scrapPerc", "Scrap %")%></b></td>
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
							<td <%=HtmlWriter.isVisible(oid, "bmc-stageId")%> width=10% align=left nowrap><%=HiltonUtility.ckNull(bomComponent.getStageId())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-bomLine")%> width=6% align=left nowrap>
								<a href="javascript: viewComponent(<%=i%>);" title="Edit Component"><%=(i + 1)%></a>
								<tsa:hidden id="bomComponent_<%=i%>" name="bomComponent_<%=i%>" value="<%=bomComponent.getIcComponent() %>"/>
								<tsa:hidden id="bomComponentLine_<%=i%>" name="bomComponentLine_<%=i%>" value="<%=(i + 1)%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-componentItem")%> width=14% align=left nowrap><%=bomComponent.getComponentItem()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-description")%> width=44% align=left ><%=bomComponent.getDescription()%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-getUsageQty")%> width=9% align=right nowrap><%=HiltonUtility.getFormattedQuantity(bomComponent.getUsageQty(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmc-scrapPerc")%> width=9% align=right nowrap><%=bomComponent.getScrapPerc()%></td>
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
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_master.jsp', 'BomMethodRetrieveByItem'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
//	var navMenu = getNavCookie("navArray");
//	if (navMenu.indexOf("Routing/Components") < 0)
//	{
//		setNavCookie("/inventory/bom_master_sub.jsp", "BomMasterRetrieveByItem", "Routing/Components");
//	}

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

	function viewMethod(row)
	{
		var loc = document.getElementById("bomMethod_" + row);
		frm.BomMethod_icMethod.value = loc.value;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_method.jsp','BomMethodRetrieveById');
	}

	function deleteMethod(row) {
		var loc = document.getElementById("bomMethod_" + row);
		var line = document.getElementById("bomMethodLine_" + row).value ;
		frm.bomAction.value = "DELETE";
		frm.BomMethod_icMethod.value = loc.value;
		if (confirm("Are you sure you want to delete Method line " + line + "?")) {
			doSubmit('inventory/bom_master.jsp','BomMethodDelete;BomMasterRetrieveByItem');
		}
	}

	function addMethod()
	{
		frm.bomAction.value = "CREATE";
		doSubmit('/inventory/bom_method.jsp', 'DoNothing');
	}

	function viewRouting(row)
	{
		var loc = document.getElementById("bomRouting_" + row);
		frm.BomRouting_icRouting.value = loc.value;
		frm.BomTask_icRouting.value = loc.value ;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_routing.jsp','BomRoutingRetrieveById;BomTaskRetrieveByIcRouting');
	}

	function deleteRouting(row) {
		var loc = document.getElementById("bomRouting_" + row);
		var line = document.getElementById("bomRoutingLine_" + row).value ;
		frm.bomAction.value = "DELETE";
		frm.BomRouting_icRouting.value = loc.value;
		frm.BomTask_icRouting.value = loc.value ;
		if (confirm("Are you sure you want to delete routing line " + line + "?")) {
			doSubmit('inventory/bom_master_sub.jsp','BomRoutingDelete;BomMasterRetrieveByItem');
		}
	}

	function addRouting()
	{
		frm.bomAction.value = "CREATE";
		doSubmit('/inventory/bom_routing.jsp', 'DoNothing');
	}

	function viewComponent(row)
	{
		var loc = document.getElementById("bomComponent_" + row);
		frm.BomComponent_icComponent.value = loc.value;
		frm.BomManufacturer_icComponent.value = loc.value ;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_component.jsp','BomComponentRetrieveById;BomManufacturerRetrieveByIcComponent');
	}

	function deleteComponent(row) {
		var loc = document.getElementById("bomComponent_" + row);
		var line = document.getElementById("bomComponentLine_" + row).value ;
		frm.bomAction.value = "DELETE";
		frm.BomComponent_icComponent.value = loc.value;
		if (confirm("Are you sure you want to delete component line " + line + "?")) {
			doSubmit('inventory/bom_master_sub.jsp','BomComponentDelete;BomMasterRetrieveByItem');
		}
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