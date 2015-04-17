<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>
<%@ page import="org.owasp.esapi.Encoder" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_itemnumber = (String) request.getAttribute("InvItem_itemNumber");
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	String s_icComponent = (String) request.getAttribute("BomComponent_icComponent");
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod");
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;

	String	s_action = (String) request.getAttribute("bomAction");
	String	s_title = "";

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_usageQty = bd_zero ;
	BigDecimal bd_scrapPerc = bd_zero ;
	BigDecimal bd_estCost = bd_zero ;

	String		  s_componentItem = "" ;
	String		  s_description = "" ;
	String		  s_unitOfMeasure = "" ;
	String		  s_fromDate = "" ;
	String		  s_thruDate = "" ;
	String		  s_stageId = "" ;
	String		  s_notes = "" ;

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Component";
	}
	else
	{
		s_title = "Edit Component";

		BomComponent bomComponent = (BomComponent) request.getAttribute("bomComponent");

		bd_usageQty = bomComponent.getUsageQty() ;
		bd_scrapPerc = bomComponent.getScrapPerc() ;
		bd_estCost = bomComponent.getEstCost() ;
		if (bd_estCost == null) bd_estCost = new BigDecimal(0) ;

		s_description = HiltonUtility.ckNull(bomComponent.getDescription()) ;
		s_notes = HiltonUtility.ckNull(bomComponent.getNotes()) ;
		s_stageId = HiltonUtility.ckNull(bomComponent.getStageId()) ;
		s_componentItem = HiltonUtility.ckNull(bomComponent.getComponentItem()) ;
		s_unitOfMeasure = HiltonUtility.ckNull(bomComponent.getUnitOfMeasure() ) ;

		s_fromDate = HiltonUtility.getFormattedDate(bomComponent.getFromDate(),oid, userDateFormat) ;
		s_thruDate = HiltonUtility.getFormattedDate(bomComponent.getThruDate(),oid, userDateFormat) ;
//		s_unitOfMeasure = HiltonUtility.ckNull(bomComponent.getUnitOfMeasure()) ;
	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>
<tsa:hidden name="BomComponent_icComponent" value="<%=s_icComponent%>"/>
<tsa:hidden name="BomComponent_parentItem" value="<%=s_itemnumber%>"/>
<tsa:hidden name="BomManufacturer_icManufacturer" value=""/>
<tsa:hidden name="BomManufacturer_owner" value="${userId}"/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icMethod" value="<%=s_icMethod%>"/>

<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="bomAction" value="<%=s_action%>"/>
<tsa:hidden name="bomManufacturerAction" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_component.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Parent Item:</b></td>
			<td width=100px nowrap><%=s_itemnumber%></td>
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
<tr><td colspan=2 class=error align=center><br><%=errorMsg%><br></td></tr>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=60% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<% if (! s_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-componentItem", "Component Item")%>:&nbsp;</td>
					<% } else { %>
					<td align=right><a href="javascript: browseLookup('BomComponent_componentItem', 'invcomponent'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-componentItem", "Component Item")%>:</a>&nbsp;</td>
					<% } %>
					<td><input type=text size=30 name="BomComponent_componentItem"  tabindex="1" value="<%=s_componentItem%>"  <% if (! s_action.equalsIgnoreCase("CREATE")) { %> disabled <% } %> onchange="upperCase(this);lookupComponent(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-description")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-description", "Description", true)%>:&nbsp</td>
					<td><input type=text size=50 name="BomComponent_description" TABINDEX=2 value="<%=s_description%>" style="text-align:left"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-usageQty")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-usageQty", "Usage Qty")%>:&nbsp</td>
					<td><input type=text size=12 name="BomComponent_usageQty" TABINDEX=3 value="<%=bd_usageQty%>" style="text-align:right" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-uom")%>>
					<td align=right nowrap><a href="javascript: browseLookup('BomComponent_unitOfMeasure', 'unitofmeasure'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmm-uom", "Unit Of Measure", true)%>:</a>&nbsp</td>
					<td><input type=text size=15 name="BomComponent_unitOfMeasure" TABINDEX=4 value="<%=s_unitOfMeasure%>" style="text-align:left" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-estCost")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-estCost", "Cost")%>:&nbsp</td>
					<td><input type=text size=15 name="BomComponent_estCost" TABINDEX=5 value="<%=bd_estCost%>" style="text-align:right" ></td>
				</tr>
				</table>
			</td>
			<td width=40% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-scrapPerc")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-scrapPerc", "Scrap %")%>:&nbsp</td>
					<td><input type=text size=12 name="BomComponent_scrapPerc" TABINDEX=6 value="<%=bd_scrapPerc%>" style="text-align:right" ></td>
				</tr>
				<tr>
					<td align=right><a href="javascript: browseLookup('BomComponent_stageId', 'bomstage'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-stageId", "Routing Id")%>:</a>&nbsp;</td>
					<td><input type=text size=15 name="BomComponent_stageId"  tabindex="7" value="<%=s_stageId%>"  onchange="upperCase(this);"></td>
				</tr>
<!--
				<tr <%=HtmlWriter.isVisible(oid, "bmc-fromDate")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-fromDate", "From Date")%>:&nbsp</td>
					<td><input type=text size=15 name="BomComponent_fromDate" TABINDEX=8 value="<%=s_fromDate%>" style="text-align:left" >
					<a href="javascript: show_calendar('BomComponent_fromDate', 'MM-dd-yyyy');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmc-thruDate")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-thruDate", "Thru Date")%>:&nbsp</td>
					<td><input type=text size=15 name="BomComponent_thruDate" TABINDEX=7 value="<%=s_thruDate%>" style="text-align:left" >
					<a href="javascript: show_calendar('BomComponent_thruDate', 'MM-dd-yyyy');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
-->
				</table>
			</td>
		</tr>
		</table>
		<br>
		<hr width=475px align=center class=browseHR>
		<br>
		<table border=0 cellspacing=0 cellpadding=0>
			<tr <%=HtmlWriter.isVisible(oid, "bmc-notes")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmc-notes", "Notes", true)%>:&nbsp</td>
					<td colspan=2>
					<textarea name="BomComponent_notes" cols=60 rows=4 onKeyDown="textCounter(this, 60);" onKeyUp="textCounter(this, 60);">${esapi:encodeForHTML(bomComponent.notes)}</textarea>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<br>
<hr width=475px align=center class=browseHR>
<br>
</tr>
</table>
<% 	if (! s_action.equalsIgnoreCase("CREATE")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=100% align=center valign=top>
				<table id=bomManufacturersTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100%>
					<table border=0 cellspacing=0 cellpadding=2 width=100%>
					<tr>
						<td class=browseHdr  width=80% height=18px nowrap>&nbsp;Manufacturers</td>
						<td class=browseHdr  width=20% align=center><a href="javascript: addManufacturer(); void(0);" title="Add Manufacturer">Add</a></td>
					</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td  align=center valign=bottom width=5%><b>Line</b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-manufacturerId")%> align=center valign=bottom width=20%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-manufacturerId", "Manufacturer Id")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-vendorName")%> align=center valign=bottom width=50%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-vendorName", "Manufacturer")%></b></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-partNumber")%> align=center valign=bottom width=20%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-partNumber", "Part Number")%></b></td>
							<td align=center valign=bottom width=10%><b>Delete</b></td>
						</tr>
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% classbrowseRow>
<%
	List bomManufacturerList = (List)request.getAttribute("bomManufacturerList");
    if (bomManufacturerList == null) bomManufacturerList = new ArrayList() ;

	for(int i=0;i<bomManufacturerList.size();i++)
	{
		BomManufacturer bomManufacturer = (BomManufacturer) bomManufacturerList.get(i);
%>
						<tr>
							<td  width=5% align=right nowrap><%=(i + 1)%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-vendorId")%> width=20% align=left nowrap>
								<a href="javascript: viewManufacturer(<%=i%>);" title="Edit Manufacturer"><%=bomManufacturer.getVendorId()%></a>
								<tsa:hidden id="bomManufacturer_<%=i%>" name="bomManufacturer_<%=i%>" value="<%=bomManufacturer.getIcManufacturer() %>"/>
								<tsa:hidden id="bomManufacturerLine_<%=i%>" name="bomManufacturerLine_<%=i%>" value="<%=(i + 1) %>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-vendorName")%> width=50% align=left nowrap><%=HiltonUtility.ckNull(bomManufacturer.getVendorName())%></td>
							<td <%=HtmlWriter.isVisible(oid, "bmt-partNumber")%> width=20% align=left nowrap><%=HiltonUtility.ckNull(bomManufacturer.getPartNumber())%></td>
							<td width=10% align=center nowrap><a href="javascript: deleteManufacturer(<%=i%>);"><IMG SRC="<%=contextPath%>/images/delete.gif" BORDER="0" ALT="Delete Manufacturer"></a></td>
						</tr>
<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
</tr>
</table>
<% }  %>
<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a onclick="javascript: submitThis(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_master_sub.jsp', 'BomMasterRetrieveByItem');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

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

	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("BomComponentAdd") >= 0) {
	      if (isEmpty(frm.BomComponent_componentItem.value))
	        	alertMessage += 'Component Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function submitThis()
	{
		if (frm.bomAction.value == "CREATE")
		{
			frm.bomAction.value="UPDATE";
			doSubmit('/inventory/bom_component.jsp', 'BomComponentAdd;BomComponentRetrieveById');
		}
		else
		{
			doSubmit('/inventory/bom_master_sub.jsp', 'BomComponentUpdate;BomMasterRetrieveByItem');
		}
	}


	function lookupComponent(objectId) {
        var itemValue = objectId.value;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userid)}&oid=<%=oid%>&process=invitem-retrieve-by.xml&resultObj=invItem";
		url = url + "&InvItem_itemNumber=" + escape(itemValue)
		lookupRequest(url, lookupComponentResponse) ;
	}

	function lookupComponentResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  var resp = http.responseXML ;
                  setResponseValue(http.responseXML, "Description", "BomComponent_description") ;
                  setResponseValue(http.responseXML, "UsageQty", "BomComponent_usageQty") ;
                  setResponseValue(http.responseXML, "UnitOfOrder", "BomComponent_unitOfMeasure") ;
                  setResponseValue(http.responseXML, "Cost", "BomComponent_estCost") ;
//                  setResponseValue(http.responseXML, "Notes", "BomComponent_notes") ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
                  alert ( "Item not found! ");
            }
		}
	}


	function viewManufacturer(row)
	{
		var loc = document.getElementById("bomManufacturer_" + row);
		frm.BomManufacturer_icManufacturer.value = loc.value ;
		frm.bomManufacturerAction.value = "UPDATE";
		doSubmit('/inventory/bom_manufacturer.jsp','BomManufacturerRetrieveById;BomManufacturerRetrieveByIcComponent');
	}

	function deleteManufacturer(row) {
		var loc = document.getElementById("bomManufacturer_" + row);
		var line = document.getElementById("bomManufacturerLine_" + row).value ;
		frm.bomManufacturerAction.value = "DELETE";
		frm.BomManufacturer_icManufacturer.value = loc.value;
		if (confirm("Are you sure you want to delete Manufacturer line " + line + "?")) {
			doSubmit('inventory/bom_component.jsp','BomManufacturerDelete;BomComponentRetrieveById;BomManufacturerRetrieveByIcComponent');
		}
	}

	function addManufacturer()
	{
		frm.bomManufacturerAction.value = "CREATE";
		doSubmit('/inventory/bom_manufacturer.jsp', 'DoNothing');
	}


// End Hide script -->
</SCRIPT>