<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String invModule = propertiesManager.getProperty("Modules", "Standard Inventory", "N");
	String invItemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvLocation_itemNumber"));
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String s_commodity = HiltonUtility.ckNull((String) request.getAttribute("InvItem_commodity"));
	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String invDescription = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	if (!HiltonUtility.isEmpty(invDescription)) {
		if (invDescription.length() > 30) {
			invDescription = " - \"" + invDescription.substring(0, 30) + "...\"";
		} else {
			invDescription = " - \"" + invDescription + "\"";
		}
	}

	Commodity commodity = CommodityManager.getInstance().getCommodity(oid, s_commodity) ;
	boolean isAsset = false ;
	String dualUmRequired = "N" ;
	String serNoRequired = "N" ;
	if (commodity != null) {
		serNoRequired = HiltonUtility.ckNull(commodity.getSerialNumbersRequired()) ;
		dualUmRequired = HiltonUtility.ckNull(commodity.getDuomRequired()) ;
	}

	String errorMsg = (String)request.getAttribute("errorMsg");
	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
%>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=ESAPI.encoder().encodeForHTML(invItemNumber)%>"/>
<tsa:hidden name="binAction" value=""/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Item Locations</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=100%>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=820px>
<tr>
	<td class=error align=center>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td class=error align=center><%=errorMsg%><br></td></tr>
		</table>
	</td>
</tr>
<tr>
<tr>
	<td width=820px align=left>
		<table border=0 cellpadding=0 cellspacing=0 width=820px>
		<tr>
			<td width=100% align=left valign=top>
				<table id=invLocationsTable border=1 cellspacing=0 cellpadding=0 width=820px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>s for "<%=invItemNumber%>"<%=invDescription%></td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-location")%> align=left valign=bottom width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-location", "Item Location")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-unitOfMeasure")%> align=left valign=bottom width=7%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-unitOfMeasure", "UOM")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityOnHand")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-quantityOnHand", "Qty on Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityOnOrder")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-quantityOnOrder", "Qty on Order")%></b></td>
							<!--td align=center valign=bottom width=10%><b>Backorder</b></td-->
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-allocated")%> align=right valign=bottom width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-allocated", "Allocated")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-minimumOnHand")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-minimumOnHand", "Min on Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-maximumOnHand")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-maximumOnHand", "Max on Hand")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-economicOrderQuantity")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-economicOrderQuantity", "EOQ")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-economicStockQuantity")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-economicStockQuantity", "ESQ")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityRequested")%> align=right valign=bottom width=8%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "locbrw-inv-quantityRequested", "Requested")%></b></td>
							<!--td align=center width=5%><b>Edit</b></td-->
<% if (invModule.indexOf('Y')>=0) { %>
							<td id="moveLabel" align=center valign=bottom width=5%><b>Move</b></td>
							<td id="deleteLabel" align=center valign=bottom width=5%><b>Delete</b></td>
<% }else{ %>
							<td id="deleteLabel" align=center valign=bottom width=10%><b>Delete</b></td>
<%	} %>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
						<tsa:hidden name="InvLocation_itemLocation" value=""/>
<%
	InvItem invItem = (InvItem) request.getAttribute("invItem") ;
	List locList = (List)request.getAttribute("invLocation");
	for(int i=0;i<locList.size();i++)
	{
		InvLocation invLocation = (InvLocation) locList.get(i);
		InvLocationPK invLocationPK = invLocation.getComp_id();
		String s_location = invLocationPK.getItemLocation();
		String s_item_number = invLocationPK.getItemNumber();
%>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-location")%> width=12% align=left>
								<a href="javascript: viewLocation(<%=i%>);" title="Edit Location"><%=s_location%></a>
								<tsa:hidden id="<%= \"invLocation_\" + i %>" name="<%= \"invLocation_\" + i %>" value="<%=s_location%>"/>
								<tsa:hidden id="<%= \"invNumber_\" + i%>" name="<%= \"invNumber_\" + i %>" value="<%=s_item_number%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-unitOfMeasure")%> width=7% align=left nowrap><%=headerEncoder.encodeForHTML(HiltonUtility.ckNull(s_umCode))%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityOnHand")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyOnHand(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityOnOrder")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyOnOrder(), oid)%></td>
							<!--td width=9% align=right nowrap><%//=HiltonUtility.getFormattedQuantity(invLocation.getQtyPendOrder(), oid)%></td-->
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-allocated")%> width=10% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyAlloc(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-minimumOnHand")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getMinOnHand(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-maximumOnHand")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getMaxOnHand(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-economicOrderQuantity")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyEoq(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-economicStockQuantity")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyEsq(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-quantityRequested")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getQtyRequested(), oid)%></td>
							<!--td width=5% align=center nowrap><a href="javascript: viewLocation(<%=i%>);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Edit Location"></a></td-->
<% if (invModule.indexOf('Y') >= 0) { %>
							<td id="moveLocations" width=5% align=center nowrap><a href="javascript: moveItems(<%=i%>);"><IMG SRC="<%=contextPath%>/images/square_arrows_right.gif" BORDER="0" ALT="Move"></a></td>
							<td id="deleteLocation" width=5% align=center nowrap><a href="javascript: deleteLocation(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Location"></a></td>
<% } else{ %>
							<td id="deleteLocation" width=10% align=center nowrap><a href="javascript: deleteLocation(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Location"></a></td>
<% } %>
						</tr>
<% if (dualUmRequired.equals("Y")) { %>

						<tr>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-location")%> width=12% align=left>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomUmCode")%> width=7% align=left nowrap><%=HiltonUtility.ckNull(s_duomUmCode)%></td>
							<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomQtyOnHand")%> width=8% align=right nowrap><%=HiltonUtility.getFormattedQuantity(invLocation.getDuomQtyOnHand(), oid)%></td>
						</tr>
<% } %>

<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=820px>
<tr>
	<td id="addLocation" width=50% align=center><div id="pxbutton"><a href="javascript: addLocation(); void(0);">Add Location</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);">Return</a></div></td>
</tr>
</table>

<br>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_item_locations.jsp", "InvLocationRetrieveByItem", "Locations");
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

	function viewLocation(row)
	{
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		frm.binAction.value = "UPDATE";
		doSubmit('/inventory/inv_location.jsp','InvLocationRetrieveById');
	}

	function moveItems(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		doSubmit('inventory/std_inv_move.jsp','InvLocationRetrieveById');
	}

	function deleteLocation(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		confirm("Are you sure you want to delete " + frm.InvLocation_itemLocation.value + "?");
		doSubmit('inventory/inv_item_locations.jsp','InvLocationDelete');
	}

	function addLocation()
	{
		frm.binAction.value = "CREATE";
		doSubmit('/inventory/inv_location.jsp', 'InvLocationRetrieveById');
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

	function thisLoad()
	{
		var invAccess = <%=role.getAccessRights("INV")%>;
		f_StartIt();
		if (invAccess <= 1)
		{
			$('#addLocation, #deleteLocation, #deleteLabel, #moveLocation, #moveLabel').hide();
		}
	}


// End Hide script -->
</SCRIPT>