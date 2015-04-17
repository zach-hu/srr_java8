<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_return_property_fields.jsp" %>
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

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>
<tsa:hidden name="RequisitionLine_icReqLine" value="${RequisitionLine_icReqLine}"/>

<tsa:hidden name="InvReturn_recDate" value="${InvReturn_recDate}"/>
<tsa:hidden name="InvReturn_recBy" value="${InvReturn_recBy}"/>
<tsa:hidden name="newAisle" value="${newAisle}"/>
<tsa:hidden name="newTier" value="${newTier}"/>
<tsa:hidden name="newRow" value="${newRow}"/>
<tsa:hidden name="newBin" value="${newBin}"/>
<tsa:hidden name="toBin" value="${toBin}"/>

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
			<tr>
				<td>
					<table border=0 cellpadding=0 cellspacing=0 align=center width=450px>
					<tr>
						<td>
							<table border=0 cellpadding=0 cellspacing=0>
							<tr <%=HtmlWriter.isVisible(oid, "invprop-serialnumber")%>>
								<td width=200px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "serialNumber", "Serial #", true)%>:&nbsp;</td>
								<td><input type=text name="InvProperty_serialNumber" size=50 maxlength=200 tabindex=6 value="<%=serialNumber%>" onchange="upperCase(this);"></td>
							</tr>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Add</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_return_property_list.jsp', 'InvReturnSetProperty'); void(0);">Return</a></div></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function submitThis()
	{
		doSubmit('/inventory/inv_return_property_list.jsp', 'InvReturnSetProperty;InvReturnAddProperty');
	}

// End Hide script -->
</SCRIPT>