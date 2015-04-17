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
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>
<tsa:hidden name="bomType" value=""/>
<tsa:hidden name="bomAction" value=""/>
<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="BomMethod_icMethod" value=""/>
<tsa:hidden name="BomRouting_icRouting" value=""/>
<tsa:hidden name="BomRouting_icMethod" value=""/>
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
				<table id=bomMethodsTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100%>
					<table border=0 cellspacing=0 cellpadding=2 width=100%>
					<tr>
						<td class=hdr12  width=80% height=25px nowrap>&nbsp;Methods</td>
						<td class=browseHdr  width=20% align=center><a href="javascript: addMethod(); void(0);" title="Add Method">Add</a></td>

					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td  align=center valign=bottom width=5%><b>Line</b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-methodId")%> align=center valign=bottom width=14%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-methodId", "Method")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-batchSize")%> align=center valign=bottom width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-batchSize", "Batch")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-uom")%> align=center valign=bottom width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-uom", "UOM")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-description")%> align=center valign=bottom width=44%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-description", "Description")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-createDate")%> align=center valign=bottom width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-createDate", "Create Date")%></b></td>
							<!--td align=center width=5%><b>Edit</b></td-->
							<td align=center valign=bottom width=10%><b>Delete</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
<%
	List bomMethodList = (List)request.getAttribute("bomMethodList");
	for(int i=0;i<bomMethodList.size();i++)
	{
		BomMethod bomMethod = (BomMethod) bomMethodList.get(i);
%>
						<tr>
							<td  width=5% align=right nowrap><%=(i + 1)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-methodId")%> width=14% align=left nowrap>
								<a href="javascript: viewMethod(<%=i%>);" title="Open Method"><%=bomMethod.getMethodId()%></a>
								<tsa:hidden id="bomMethod_<%=i%>" name="bomMethod_<%=i%>" value="<%=bomMethod.getIcMethod() %>"/>
								<tsa:hidden id="bomMethodLine_<%=i%>" name="bomMethodLine_<%=i%>" value="<%=(i + 1) %>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-batchSize")%> width=10% align=right nowrap><%=bomMethod.getBatchSize() %></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-uom")%> width=10% align=left nowrap><%=HiltonUtility.ckNull(bomMethod.getUnitOfMeasure())%></td>

							<td <%=HtmlWriter.isVisible(oid, "bmm-description")%> width=44% align=left nowrap><%=HiltonUtility.ckNull(bomMethod.getDescription())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmm-createDate")%> width=12% align=left nowrap><%=HiltonUtility.getFormattedDate(bomMethod.getDateEntered(), oid, userDateFormat)%></td>
							<td width=10% align=center nowrap><a href="javascript: deleteMethod(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Method"></a></td>
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
<%if (bomSource.equalsIgnoreCase("M")) {%>
	<td width=50% align=center><a href="javascript: setBrowseName(); resetNavCookie(6); doSubmit('/browse/browse.jsp','BrowseRetrieveById');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<% } else { %>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
<% } %>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("BOM Item Detail") < 0)
	{
		setNavCookie("/inventory/bom_master.jsp", "BomMasterRetrieveByItem", "BOM Item Detail");
	}

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
		frm.BomMethod_icMethod.value = loc.value ;
		frm.BomRouting_icMethod.value = loc.value ;
		frm.bomAction.value = "UPDATE";
		doSubmit('/inventory/bom_master_sub.jsp','BomMasterRetrieveByItem');
	}

	function editMethod(row)
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
			doSubmit('inventory/bom_master.jsp','BomMethodDelete;BomMethodRetrieveByItem');
		}
	}

	function addMethod()
	{
		frm.bomAction.value = "CREATE";
		doSubmit('/inventory/bom_method.jsp', 'DoNothing');
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