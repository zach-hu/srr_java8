<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.Utility" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<tsa:hidden name="fromPage" value="inv_property"/>

<%
	String s_bin_item_number = (String) request.getAttribute("InvBinLocation_itemNumber");
	String s_inv_item_number = (String) request.getAttribute("InvLocation_itemNumber");
	String s_inv_item_location = (String) request.getAttribute("InvLocation_itemLocation");
	String s_bin_HdrIc = (String) request.getAttribute("InvBinLocation_hdrIc");
	String s_bin_IcRc = (String) request.getAttribute("InvBinLocation_icRc");
	String s_bin_IcRecLine = (String) request.getAttribute("InvBinLocation_icRecLine");
	String s_propertIc = (String) request.getAttribute("InvProperty_icProperty");
	String	s_action = (String) request.getAttribute("propertyAction");
	String enteredPropertyListFrom = (String) request.getAttribute("enteredPropertyListFrom");
	String	s_title = "";

	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	String	tagNumber = "" ;
	String	contractNumber = "" ;
	String	poNumber = "" ;
	String	serialNumber = "" ;
	String	shipNumber = "" ;
	String	dateIn = Dates.today(userDateFormat, user.getTimeZone()) ;
	String	dateOut = "" ;
	String	receiptNumber = "" ;
	String	remarks = "" ;
	String	itemNumber = s_inv_item_number ;
	String	icRc = s_bin_IcRc ;
	String	cblOutNumber = "" ;
	String	armyNumber = "" ;

	BigDecimal	icProperty = new BigDecimal(0) ;

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Property Record";
	}
	else
	{
		s_title = "Property Adjustment";

		InvProperty invProperty = (InvProperty) request.getAttribute("invProperty");

		tagNumber = Utility.ckNull(invProperty.getTagNumber()) ;
		poNumber = Utility.ckNull(invProperty.getPoNumber()) ;
		contractNumber = Utility.ckNull(invProperty.getContract()) ;
		receiptNumber = Utility.ckNull(invProperty.getReceiptNumber()) ;
		remarks = Utility.ckNull(invProperty.getRemarks()) ;
		serialNumber = Utility.ckNull(invProperty.getSerialNumber()) ;
		icProperty = invProperty.getIcProperty() ;
		dateIn = HiltonUtility.getFormattedDate(invProperty.getDateIn(), oid, userDateFormat);
		dateOut = HiltonUtility.getFormattedDate(invProperty.getDateOut(), oid, userDateFormat);
		shipNumber = Utility.ckNull(invProperty.getShipNumber()) ;
		cblOutNumber = Utility.ckNull(invProperty.getCblOutNumber()) ;
		armyNumber = Utility.ckNull(invProperty.getArmyNumber()) ;

	}
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_inv_item_number%>"/>
<tsa:hidden name="InvBinLocation_icRc" value="<%=s_bin_IcRc%>"/>
<tsa:hidden name="InvBinLocation_hdrIc" value="<%=s_bin_HdrIc%>"/>
<tsa:hidden name="InvBinLocation_icRecLine" value="<%=s_bin_IcRecLine%>"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=s_inv_item_number%>"/>
<tsa:hidden name="InvLocation_itemLocation" value="<%=s_inv_item_location%>"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=s_bin_item_number%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>

<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="propertyAction" value="<%=s_action%>"/>
<tsa:hidden name="InvProperty_icProperty" value="<%=icProperty.toString() %>"/>
<tsa:hidden name="InvProperty_icRc" value="<%=s_bin_IcRc%>"/>
<tsa:hidden name="InvProperty_itemNumber" value="<%=s_inv_item_number%>"/>
<tsa:hidden name="enteredPropertyListFrom" value="${enteredPropertyListFrom}"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>


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
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item/Part #")%>:</b></td>
			<td width=100px nowrap><%=s_inv_item_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:</b></td>
			<td width=100px nowrap><%=s_inv_item_location%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<!--tr>
				<td align=left class=browseRow>
					<a href="javascript: doSubmit('/inventory/add_inv_bin.jsp','DoNothing'); void(0);"><IMG SRC="<%=contextPath%>/images/cmd_add_item.gif" BORDER="0" ALT="Add Location">&nbsp;Add Another Property Record</a>
				</td>
			</tr-->
			<tr>
				<td>
					<table border=0 cellpadding=0 cellspacing=0 align=center width=450px>
					<tr>
						<td>
							<table border=0 cellpadding=0 cellspacing=0>
							<!--
							<tr <%=HtmlWriter.isVisible(oid, "invprop-tagnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-tagnumber", "GPIN", true)%>:</a>&nbsp;</td>
								<td><input type=text name="InvProperty_tagNumber" size=30 maxlength=20 tabindex=2 value="<%=tagNumber%>" onchange="upperCase(this);"></td>
							</tr>
							-->
							<tr <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "serialNumber", "Serial #", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_serialNumber" size=50 maxlength=200 tabindex=6 value="<%=serialNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-datein")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-datein", "Date In Inventory")%>:&nbsp;</td>
								<td nowrap>
									<input type=text name="InvProperty_dateIn" size=15 maxlength=15 tabindex=7 value="<%=dateIn%>" onchange="checkDate(this);">
									<a href="javascript: show_calendar('InvProperty_dateIn', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</td>
							</tr>
							<!--
							<tr <%=HtmlWriter.isVisible(oid, "invprop-dateout")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-dateOut", "Date Out Of Inventory")%>:&nbsp</td>
								<td nowrap>
									<input type=text name="InvProperty_dateOut" size=15 maxlength=15 tabindex=8 value="<%=dateOut%>" onchange="checkDate(this);">
									<a href="javascript: show_calendar('InvProperty_dateOut', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
								</td>
							</tr>

							<tr <%=HtmlWriter.isVisible(oid, "invprop-contractnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-contractnumber", "Contract Number", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_contract" size=20 maxlength=20 tabindex=9 value="<%=contractNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-ponumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-ponumber", "PO Number", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_poNumber" size=20 maxlength=20 tabindex=10 value="<%=poNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-shipnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-shipnumber", "Shipping Number", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_shipNumber" size=20 maxlength=20 tabindex=11 value="<%=shipNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-cblOutNumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-cblOutNumber", "CBL Out", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_cblOutNumber" size=20 maxlength=20 tabindex=12 value="<%=cblOutNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-receiptnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-receiptnumber", "Receipt Number", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_receiptNumber" size=20 maxlength=20 tabindex=13 value="<%=receiptNumber%>" onchange="upperCase(this);"></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-armyNumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-armyNumber", "Army #", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_armyNumber" size=20 maxlength=20 tabindex=14 value="<%=armyNumber%>" onchange="upperCase(this);"></td>
							</tr>
-->
							<tr <%=HtmlWriter.isVisible(oid, "invprop-remarks")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invprop-remarks", "Remarks", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_remarks" size=50 maxlength=50 tabindex=15 value="<%=remarks%>" ></td>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr><td><br></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_property_list.jsp', 'InvPropertyRetrieveByIcRc'); void(0);">Return</a></div></td>
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

	function viewItem(row) {
		doSubmit('/requests/req_item.jsp', 'DoNothing');
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

	function submitThis()
	{
		if(frm.InvProperty_dateIn.value == '')
		{
			alert("Date In Inventory is empty. Please enter a date.");
			frm.InvProperty_dateIn.focus();
		}
<%--		else if(frm.InvProperty_dateOut.value == '')
//		{
//			alert("Date Out Of Inventory is empty. Please enter a date.");
//			frm.InvProperty_dateOut.focus();
//		} --%>
		else if(checkDate(frm.InvProperty_dateIn) <%--&& checkDate(frm.InvProperty_dateOut)--%>)
		{
			if (frm.propertyAction.value == "CREATE")
			{
				doSubmit('/inventory/inv_property_list.jsp', 'InvPropertyAdd;InvPropertyRetrieveByIcRc');
			}
			else
			{
				doSubmit('/inventory/inv_property_list.jsp', 'InvPropertyUpdate;InvPropertyRetrieveByIcRc');
			}
		}
	}


	function validateForm() {
	  var alertMessage = '';
      var handler = frm.handler.value;

      if (handler.indexOf("InvPropertyUpdate") >= 0 || handler.indexOf("InvPropertyAdd") >= 0) {
	  }

	  return true;
    }

// End Hide script -->
</SCRIPT>