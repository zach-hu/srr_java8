<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/ajaxlookup.js"></SCRIPT>

<%
	String s_itemnumber = (String) request.getAttribute("InvItem_itemNumber");
	String invDescription =  HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	String s_icManufacturer = (String) request.getAttribute("BomManufacturer_icManufacturer");
	String s_stageId = HiltonUtility.ckNull((String) request.getAttribute("BomComponent_stageId"));
	String s_icComponent = HiltonUtility.ckNull((String) request.getAttribute("BomComponent_icComponent"));
	String s_componentitem = HiltonUtility.ckNull((String) request.getAttribute("BomComponent_componentItem"));
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String s_icMethod = (String) request.getAttribute("BomMethod_icMethod") ;
	String bomSource = HiltonUtility.ckNull((String) request.getAttribute("bomSource"));
	if (bomSource == null) bomSource = "I" ;

	String	s_action = (String) request.getAttribute("bomAction");
	String	s_manufacturer_action = (String) request.getAttribute("bomManufacturerAction");

	String	s_title = "";

	if (s_manufacturer_action == null)
	{
		s_manufacturer_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_batchSize = bd_zero ;
	String		  s_vendorId = "" ;
	String		  s_vendorName = "" ;
	String		  s_dateEntered = "" ;
	String		  s_partNo = "" ;

	if (s_manufacturer_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Manufacturer";
	}
	else
	{
		s_title = "Edit Manufacturer";

		BomManufacturer bomManufacturer = (BomManufacturer) request.getAttribute("bomManufacturer");

		s_vendorName = HiltonUtility.ckNull(bomManufacturer.getVendorName()) ;
		s_vendorId = HiltonUtility.ckNull(bomManufacturer.getVendorId()) ;
		s_partNo = HiltonUtility.ckNull(bomManufacturer.getPartNumber()) ;
	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=invDescription%>"/>


<tsa:hidden name="BomComponent_icComponent" value="<%=s_icComponent%>"/>

<tsa:hidden name="BomManufacturer_icManufacturer" value="<%=s_icManufacturer%>"/>
<tsa:hidden name="BomManufacturer_owner" value="${userId}"/>
<tsa:hidden name="BomManufacturer_stageId" value="<%=s_stageId%>"/>
<tsa:hidden name="BomManufacturer_parentItem" value="<%=s_itemnumber%>"/>
<tsa:hidden name="BomManufacturer_componentItem" value="<%=s_componentitem%>"/>
<tsa:hidden name="BomManufacturer_icComponent" value="<%=s_icComponent%>"/>
<tsa:hidden name="BomMethod_icMethod" value="<%=s_icMethod%>"/>
<tsa:hidden name="BomRouting_icMethod" value="<%=s_icMethod%>"/>


<tsa:hidden name="bomSource" value="<%=bomSource %>"/>
<tsa:hidden name="bomAction" value="<%=s_action%>"/>
<tsa:hidden name="bomManufacturerAction" value="<%=s_manufacturer_action%>"/>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/bom_manufacturer.jsp"/>

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
			<td align=right><b>Routing Id:</b>;</td>
			<td width=100px nowrap><%=s_stageId%></td>
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
			<td width=80% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<% if (! s_manufacturer_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-manufacturerId", "Manufacturer Id")%>:&nbsp;</td>
					<% } else { %>
					<td align=right><a href="javascript: browseLookup('BomManufacturer_vendorId', 'supplier'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-manufacturerId", "Manufacturer Id")%>:</a>&nbsp;</td>
					<% } %>
					<td><input type=text size=15 name="BomManufacturer_vendorId"  tabindex="1" value="<%=s_vendorId%>"  <% if (! s_manufacturer_action.equalsIgnoreCase("CREATE")) { %> disabled <% } %> onchange="upperCase(this);lookupManufacturer(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-manufacturerName")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-manufacturerName", "Manufacturer Name", true)%>:&nbsp</td>
					<td><input type=text size=40 name="BomManufacturer_vendorName" TABINDEX=3 value="<%=s_vendorName%>" style="text-align:left"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "bmm-partNumber")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bmt-partNumber", "Part Number", true)%>:&nbsp</td>
					<td><input type=text size=30 name="BomManufacturer_partNumber" TABINDEX=3 value="<%=s_partNo%>" style="text-align:left"   onchange="upperCase(this);"></td>
				</tr>
				</table>
			</td>
			<td width=20% align=right valign=top>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a onclick="javascript: submitThis();void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/bom_component.jsp', 'BomComponentRetrieveById;BomManufacturerRetrieveByIcComponent');"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
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

      if (handler.indexOf("BomManufacturerAdd") >= 0) {
	      if (isEmpty(frm.BomManufacturer_vendorId.value))
	        	alertMessage += 'Manufacturer Id is a required entry.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function submitThis()
	{
		if (frm.bomManufacturerAction.value == "CREATE")
		{
			frm.bomManufacturerAction.value="UPDATE";
			doSubmit('/inventory/bom_component.jsp', 'BomManufacturerAdd;BomComponentRetrieveById;BomManufacturerRetrieveByIcComponent');
		}
		else
		{
			doSubmit('/inventory/bom_component.jsp', 'BomManufacturerUpdate;BomComponentRetrieveById;BomManufacturerRetrieveByIcComponent');
		}
	}

	function lookupManufacturer(idObject) {
		var idValue = idObject.value ;
		var url = "<%=requestURLPath%>" + "/TableLookup?uid=${esapi:encodeForJavaScript(userId)}&oid=<%=oid%>&process=vendor-retrieve-by-id.xml&resultObj=vendor";
        url = url + "&Vendor_vendorId=" + escape(idValue)
        lookupRequest(url, lookupManufacturerResponse) ;
	}

	function lookupManufacturerResponse() {
		if (http.readyState == 4) {
			var status = http.status ;
			if (http.status == 200) {
                  // Or for XML formatted response text:
                  var resp = http.responseXML ;
                  setResponseValue(http.responseXML, "VendorName", "BomManufacturer_vendorName") ;
            } else {
//                  document.getElementById('BomComponent_description').value = "" ;
                  alert ( "Manufacturer Id not found! ");
            }
		}
	}


// End Hide script -->
</SCRIPT>