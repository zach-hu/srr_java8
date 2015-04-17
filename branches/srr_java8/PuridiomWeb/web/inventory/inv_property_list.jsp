<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.math.*" %>

<%
	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;
%>

<tsa:hidden name="fromPage" value="inv_property_list"/>
<tsa:hidden name="propertyAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<td>
	<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
	<td width=680px align=center>
		<table border=1 id=invPropertyListTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
<%

	String invItemBinNumber = (String) request.getAttribute("InvBinLocation_itemNumber");
	String invItemNumber = (String) request.getAttribute("InvLocation_itemNumber");
	String invItemLocation = (String) request.getAttribute("InvLocation_itemLocation");
	String s_bin_HdrIc = (String) request.getAttribute("InvBinLocation_hdrIc");
	String s_bin_IcRc = (String) request.getAttribute("InvBinLocation_icRc");
	String s_bin_IcRecLine = (String) request.getAttribute("InvBinLocation_icRecLine");
	String fromPage = (String) request.getAttribute("fromPage");
	List propertyList = (List)request.getAttribute("invPropertyList");

	if (fromPage != null && fromPage.equals("inv_property")) {
		fromPage = (String) request.getAttribute("enteredPropertyListFrom");
	}

%>
				<td class=browseHdr height=18px nowrap>&nbsp;Property Records for "<%=invItemNumber%>" at the <%= invItemLocation %> Location</td>
				<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvBinLocation_icRc" value="<%=s_bin_IcRc%>"/>
				<tsa:hidden name="InvBinLocation_hdrIc" value="<%=s_bin_HdrIc%>"/>
				<tsa:hidden name="InvBinLocation_icRecLine" value="<%=s_bin_IcRecLine%>"/>
				<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
				<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invItemBinNumber%>"/>
				<tsa:hidden name="InvLocation_itemNumber" value="<%=invItemNumber%>"/>
				<tsa:hidden name="InvLocation_itemLocation" value="<%=invItemLocation%>"/>
				<tsa:hidden name="InvProperty_icProperty" value=""/>
				<tsa:hidden name="binAction" value=""/>
				<tsa:hidden name="propertyListSize" value="<%= propertyList.size() %>"/>
				<tsa:hidden name="enteredPropertyListFrom" value="${fromPage}"/>
			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=680px>
				<tr>
<!--
				<td <%=HtmlWriter.isVisible(oid, "invprop-tagnumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-tagnumber", "GPIN")%></b></td>
-->
				<td <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> align=center width=12%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-datein", "Date In")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center width=14%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-owner", "Recorded By") %></b></td>
<!--
				<td <%=HtmlWriter.isVisible(oid, "invprop-dateout")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-dateout", "Date Out")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-ponumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-ponumber", "Order Number")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-contractnumber")%> align=center width=10%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-contractnumber", "Contract")%></b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-receiptnumber")%> align=center width=6%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-receiptnumber", "Receipt")%></b></td>
-->
				<td align=center width=5%><b>Edit</b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-move")%> align=center width=5%><b>Move</b></td>
				<td <%=HtmlWriter.isVisible(oid, "invprop-attach")%> align=center width=6%><b>Attach</b></td>
				<td align=center width=6%><b>Delete</b></td>
				</tr>
				</table>

			<table border=0 cellspacing=0 cellpadding=1 width=680px>
<%
	if (propertyList != null)
	{
		for(int i = 0; i < propertyList.size(); i++)
		{
			InvProperty invProperty = (InvProperty) propertyList.get(i);
			String contractNumber = invProperty.getContract() ;
			String poNumber = invProperty.getPoNumber() ;
			String tagNumber = invProperty.getTagNumber() ;
			String receiptNumber = invProperty.getReceiptNumber() ;
			String serialNumber = invProperty.getSerialNumber() ;
			BigDecimal icRc = invProperty.getIcRc() ;
			BigDecimal icProperty = invProperty.getIcProperty() ;
			String dateOut = HiltonUtility.getFormattedDate(invProperty.getDateOut(), oid, userDateFormat) ;
			String dateIn = HiltonUtility.getFormattedDate(invProperty.getDateIn(), oid, userDateFormat) ;
			if (poNumber==null){poNumber = "";}
			if (contractNumber==null){contractNumber = "";}
			if (tagNumber==null){tagNumber = "";}
			if (receiptNumber==null){receiptNumber = "";}
			if (serialNumber==null){serialNumber = "";}
			if (icRc==null){icRc = new BigDecimal(0); }
			if (icProperty==null){icProperty = new BigDecimal(0); }

%>
						<tr>
							<tsa:hidden id="invProperty_<%=i%>" name="invProperty_<%=i%>" value="<%=icProperty%>"/>
							<tsa:hidden id="gpin_<%=i%>" name="gpin_<%=i%>" value="<%=tagNumber%>"/>
<!--
							<td <%=HtmlWriter.isVisible(oid, "invprop-tagnumber")%> width=10% align=center><%=tagNumber%></td>
-->
							<td <%=HtmlWriter.isVisible(oid, "invprop-serialnumberl")%> width=12% align=center><%=serialNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-datein")%> width=10% align=center><%=dateIn %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-owner")%> width=12% align=center><%=HiltonUtility.ckNull(invProperty.getOwner())%></td>
<!--
							<td <%=HtmlWriter.isVisible(oid, "invprop-dateout")%> width=10% align=center><%=dateOut %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-ponumber")%> width=10% align=center><%=poNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-contractnumber")%> width=10% align=center><%=contractNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-receiptnumber")%> width=6% align=center><%=receiptNumber %></td>
-->
							<td width=5% align=center><a href="javascript: viewProperty(<%=i%>);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="Property Adjustment"></a></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-move")%> width=5% align=center><a href="javascript: moveProperty(<%=i%>);"><IMG SRC="<%=contextPath%>/images/square_arrows_right.gif" BORDER="0" ALT="Move Property"></a></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-attach")%> width=6% align=center><a href="javascript: viewPropertyAttachments(<%=i%>);"><IMG SRC="<%=contextPath%>/images/clip.gif" BORDER="0" ALT="Attachments"></a></td>
							<td width=6% align=center><a href="javascript: deleteProperty(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Property Record"></a></td>
						</tr>
<%	}

		if (propertyList.size() <= 0)
		{ %>
						<tr><td align=center>There are currently no property records available for this location.</td></tr>
<%	}
	} %>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: addProperty(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton">
		<% if (fromPage.equals("inv_bin")) { %>
			<a href="javascript:frm.binAction.value='UPDATE';doSubmit('/inventory/inv_bin.jsp','InvBinLocationRetrieveById;InvItemRetrieveById'); void(0);">Return</a>
		<% } else { %>
			<a href="javascript:doSubmit('/inventory/inv_bin_locations.jsp','InvBinLocationRetrieveByItem'); void(0);">Return</a>
		<% } %>
	</div></td>
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

	function viewProperty(row) {
		var num = document.getElementById("invProperty_" + row);
		frm.InvProperty_icProperty.value = num.value;
		frm.propertyAction.value = "UPDATE";
		doSubmit('/inventory/inv_property.jsp', 'InvPropertyRetrieveById');
	}

	function moveProperty(row) {
		var num = document.getElementById("invProperty_" + row);
		frm.InvProperty_icProperty.value = num.value;
		doSubmit('/inventory/inv_property_move.jsp','InvPropertyRetrieveById;InvBinLocationRetrieveById');
	}

	function viewPropertyAttachments(row) {
		var num = document.getElementById("invProperty_" + row);
		frm.InvProperty_icProperty.value = num.value;
	    var newInputField = "<input type=hidden name=DocAttachment_icHeader value='" + num.value + "'>";
        newInputField = newInputField + "<input type=hidden name=returnPage value='/inventory/inv_property_list.jsp'>";
        newInputField = newInputField + "<input type=hidden name=returnHandler value='InvPropertyRetrieveByIcRc'>";
   		var gpin = document.getElementById("gpin_" + row);
	    newInputField = newInputField + "<input type=hidden name=gpin value='" + gpin.value + "'>";
        setHiddenFields(newInputField);
		doSubmit('/inventory/inv_property_attachments.jsp', 'DocAttachmentRetrieveByHeader');
	}

	function addProperty()
	{
		frm.propertyAction.value = "CREATE";
		doSubmit('/inventory/inv_property.jsp', 'DoNothing');
	}

	function deleteProperty(row) {
		var num = document.getElementById("invProperty_" + row);
		frm.InvProperty_icProperty.value = num.value;
		if (confirm("Are you sure you want to delete this property record?"))
		{
			doSubmit('/inventory/inv_property_list.jsp','InvPropertyDelete');
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