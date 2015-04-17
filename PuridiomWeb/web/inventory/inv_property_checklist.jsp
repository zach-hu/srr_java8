<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="java.math.*" %>

<tsa:hidden name="propertyAction" value="UPDATE"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

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
	String disbLineIcDsbLine = (String) request.getParameter("DisbLine_icDsbLine");
	String bd_ic_dsb_header = (String) request.getParameter("Account_icHeader");
	String bd_ic_dsb_line = (String) request.getParameter("Account_icLine");
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired"));

	String status = HiltonUtility.ckNull((String)request.getAttribute("DisbHeader_status"));
	System.out.println(status);
	boolean editable = false;
	if (status.compareTo(DocumentStatus.INV_INPROGRESS) == 0)
	{
		editable = true;
	}

	String fromPage = (String) request.getParameter("fromPage");
	String returnPage = null;
	String returnMethod = null;
	String quantity = null;
	boolean enforceQuantity = true;

	if (fromPage.equals("dsb_item")) {
		returnPage = "/inventory/dsb_item.jsp";
		returnMethod = "DisbLineRetrieve;CommodityRetrieveById";
		quantity = (String) request.getParameter("DisbLine_quantity");
	} else {
		returnPage = "/inventory/ext_inv_move.jsp";
		returnMethod = "InvBinLocationRetrieveById";
		quantity = (String) request.getParameter("qtyToMove");

		if (quantity == null || quantity.length() == 0) {
			enforceQuantity = false;
		}
	}

	Map selectedProperty = (Map) request.getAttribute("selectedProperty");

	List propertyList = (List)request.getAttribute("invPropertyList");
%>
				<td class=browseHdr height=18px nowrap>
					<table border=0 width="100%" cellpadding=0 cellspacing=0>
						<tr class=browseHdr height=18px><td nowrap>
							&nbsp;Property Records for "<%=invItemNumber%>" at the <%= invItemLocation %> Location
						</td><td align="right" nowrap>
						<%	if (editable) { %>
							<a href="javascript: selectAll()">Select All</a>
						<%	} %>
						</td></tr>
					</table>
				</td>
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
				<tsa:hidden name="DisbLine_icDsbLine" value="<%=disbLineIcDsbLine%>"/>
				<tsa:hidden name="Account_icHeader" value="<%= bd_ic_dsb_header%>"/>
				<tsa:hidden name="Account_icLine" value="<%= bd_ic_dsb_line%>"/>
				<tsa:hidden name="lineCount" value="${lineCount}"/>
				<tsa:hidden name="DisbHeader_disbType" value="${DisbHeader_disbType}"/>
				<tsa:hidden name="DisbHeader_status" value="${DisbHeader_status}"/>
				<tsa:hidden name="Commodity_commodity" value="${Commodity_commodity}"/>
				<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>
				<tsa:hidden name="propertyListSize" value="<%= propertyList.size() %>"/>
				<tsa:hidden name="qtyToMove" value="${qtyToMove}"/>
				<tsa:hidden name="newAisle" value="${newAisle}"/>
				<tsa:hidden name="newRow" value="${newRow}"/>
				<tsa:hidden name="newTier" value="${newTier}"/>
				<tsa:hidden name="duomQtyToMove" value="${duomQtyToMove}"/>
			</tr>
			<tr>
			<td class=browseRow>
				<table border=0	cellpadding=1 cellspacing=0 width=680px>
				<tr>
				<td width="8%">&nbsp;</td>
				<td width="35%" <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-serialnumber", "Serial #")%></b></td>
				<td width="22%" <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-datein", "Date In")%></b></td>
				<td width="35%" <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-locbrw-inv-owner", "Recorded By") %></b></td>
				</tr>
<%
	if (propertyList != null)
	{
		List pList = null;
		if (selectedProperty.containsKey(s_bin_IcRc)) {
			pList = (List) selectedProperty.get(s_bin_IcRc);
		} else {
			pList = new ArrayList();
		}

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
							<tsa:hidden name="invProperty_<%=i%>" value="<%=icProperty%>"/>
							<% String checked = ""; if (pList.contains(icProperty.toString())) checked = "checked"; %>
							<td>
							<%	if (editable) { %>
								<input type="checkbox" name="checkbox_<%=i%>" <%=checked%> id="checkbox_<%=i%>"/>
							<%	} %>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-serialnumberl")%> align=center><%=serialNumber %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-datein")%> align=center><%=dateIn %></td>
							<td <%=HtmlWriter.isVisible(oid, "invprop-owner")%> align=center><%=HiltonUtility.ckNull(invProperty.getOwner())%></td>
						</tr>
<%	}

		if (propertyList.size() <= 0)
		{ %>
						<tr><td align=center colspan="4">There are currently no property records available for this location.</td></tr>
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
<%	if (editable) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: save(); void(0);">OK</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton">
		<a href="javascript: returnToItem(); void(0);">Return</a>
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

	function save()
	{
		<% if (enforceQuantity) { %>
			var quantity = <%=quantity%>;
			var quantityChecked = 0;

			for (var i = 0; true; i++) {
				var box = document.getElementById('checkbox_' + i);

				if (box != null && typeof(box) != 'undefined') {
					if (box.checked == true) {
						quantityChecked++;
					}
				} else {
					break;
				}
			}

			if (quantity != quantityChecked) {
				alert('You must select ' + quantity + ' serial number(s).');
				return;
			}
		<% } %>

		doSubmit('<%=returnPage%>','DisbSetProperty;DisbSubmitPropertyChecklist;<%= returnMethod %>');
	}

	function returnToItem()
	{
		doSubmit('<%=returnPage%>','DisbSetProperty;<%= returnMethod %>');
	}

	function selectAll()
	{
		for (var i = 0; true; i++) {
			var box = document.getElementById('checkbox_' + i);

			if (box != null && typeof(box) != 'undefined') {
				box.checked = true;
			} else {
				break;
			}
		}
	}
// End Hide script -->
</SCRIPT>