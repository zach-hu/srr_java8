<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="binAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
	<td width=680px align=center>
		<table border=1 id=invBinPropertyTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
<%
	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
%>
				<td class=browseHdr height=18px nowrap>&nbsp;Property Record for "<%=invItemNumber%>" at the <%= invItemLocation %> Location</td>
				<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
				<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
				<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
				<tsa:hidden name="InvBinLocation_icRc" value=""/>
			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=680px>
				<tr>
				<td <%=HtmlWriter.isVisible(oid, "prop-gpin")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-gpin", "GPIN")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-serial")%> align=center width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-serial", "Serial #")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-datein")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-datein", "Date In")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-dateout")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-dateout", "Date Out")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-order")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-order", "Order Number")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-contract")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-contract", "Contract")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "prop-receipt")%> align=center width=6%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prop-receipt", "Receipt")%></b></td>

				</tr>
				</table>

			<table border=0 cellspacing=0 cellpadding=1 width=680px>
						<tr>
							<tsa:hidden id="invProperty_<%=i%>" name="invProperty_<%=i%>" value="<%=icProperty%>"/>
							<td <%=HtmlWriter.isVisible(oid, "prop-gpin")%> width=10% align=center>VSE0001</td>
							<td <%=HtmlWriter.isVisible(oid, "prop-serial")%> width=12% align=center>1S200029A400</td>
							<td <%=HtmlWriter.isVisible(oid, "prop-datein")%> width=10% align=center>05/20/2006</td>
							<td <%=HtmlWriter.isVisible(oid, "prop-dateout")%> width=10% align=center></td>
							<td <%=HtmlWriter.isVisible(oid, "prop-order")%> width=10% align=center>2007-001210</td>
							<td <%=HtmlWriter.isVisible(oid, "prop-contract")%> width=10% align=center></td>
							<td <%=HtmlWriter.isVisible(oid, "prop-receipt")%> width=6% align=center>0</td>
						</tr>
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
<!--
	<td width=50% align=center><a href="javascript: addBin(); void(0);"><img class=button src="<%=contextPath%>/images/button_add.gif" border=0></a></td>
-->
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_bin_locations.jsp','InvBinLocationRetrieveByItem'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
</FORM>
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

	function viewBin(row) {
		var num = document.getElementById("invLocationBin_" + row);
		frm.InvBinLocation_icRc.value = num.value;
		frm.binAction.value = "UPDATE";
		doSubmit('/inventory/inv_bin.jsp', 'InvBinLocationRetrieveById');
	}

	function moveItems(row) {
		var loc = document.getElementById("invLocationBin_" + row);
		frm.InvBinLocation_icRc.value = loc.value;
		doSubmit('/inventory/ext_inv_move.jsp','InvBinLocationRetrieveById');
	}

	function addBin()
	{
		frm.binAction.value = "CREATE";
		doSubmit('/inventory/inv_bin.jsp', 'DoNothing');
	}

	function deleteBin(row) {
		var qtyOnHand = document.getElementById("qtyOnHand_" + row);
		if (eval(qtyOnHand.value > 0))
		{
			alert("Bin Location quantity on hand is not zero.  Cannot delete!");
		}
		else
		{
			var num = document.getElementById("invLocationBin_" + row);
			frm.InvBinLocation_icRc.value = num.value;
			if (confirm("Are you sure you want to delete this bin location?"))
			{
				doSubmit('/inventory/inv_bin_locations.jsp','InvBinLocationDelete');
			}
		}
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