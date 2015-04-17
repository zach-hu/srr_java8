<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/menu/invRetOptionArrays.js"></script>
<%
String viewAction = (String)request.getAttribute("viewAction");
InvItem	invItem = (InvItem) request.getAttribute("invItem");
if (invItem == null) {
  invItem = new InvItem();
}

String allowBrowse = "true";
String disabled = "";
if(viewAction == null)
{
 viewAction = "browse";
 allowBrowse = "false";
 disabled = "DISABLED";
}%>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_return_property_fields.jsp" %>
<script language='Javascript1.2' type="text/javascript">
<!--
viewAction = "<%=headerEncoder.encodeForJavaScript(viewAction)%>";

Array91 = createInvRetOptionsMenu(Array91[0]);

// -->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");

	InvReturn invReturn =(InvReturn)request.getAttribute("invReturn");
	if (invReturn == null) {
		invReturn = new InvReturn();
	}

	if (request.getAttribute("InvReturn_recDate") != null &&
			((String)request.getAttribute("InvReturn_recDate")).length() > 0 &&
			Dates.getDate((String)request.getAttribute("InvReturn_recDate")) != null) {
		invReturn.setRecDate(Dates.getDate((String)request.getAttribute("InvReturn_recDate")));
	}

	if (request.getAttribute("InvReturn_recBy") != null) {
		invReturn.setRecBy((String)request.getAttribute("InvReturn_recBy"));
	} else {
		invReturn.setRecBy(uid);
	}

	InvBinLocation bin = (InvBinLocation)request.getAttribute("invBinLocation");
	if (bin == null) {
		bin = new InvBinLocation();
	}

	if (request.getAttribute("newAisle") != null) {
		bin.setAisle((String)request.getAttribute("newAisle"));
	}

	if (request.getAttribute("newTier") != null) {
		bin.setTier((String)request.getAttribute("newTier"));
	}

	if (request.getAttribute("newRow") != null) {
		bin.setLocrow((String)request.getAttribute("newRow"));
	}

	if (request.getAttribute("newBin") != null) {
		bin.setBin((String)request.getAttribute("newBin"));
	}

	RequisitionLine requisitionLine = (RequisitionLine)request.getAttribute("requisitionLine");
	DisbLine disbLine = (DisbLine)request.getAttribute("disbLine");

	String s_commodity = invItem.getCommodity() ;
	String s_umCode = invItem.getUnitOfIssue() ;
	String s_duomUmCode = invItem.getDuomUmCode() ;

	String duomQty = invReturn.getDuomQty().toString() ;

	String handler = "InvReturnCreate";
	String browseName = "inv-returns-reqline";

	if (requisitionLine != null) {
		s_commodity = requisitionLine.getCommodityCode() ;
		s_umCode = requisitionLine.getUmCode() ;
	}

	if (disbLine != null) {
		s_commodity = disbLine.getCommodityCode();
		s_umCode = disbLine.getUmCode();
		handler = "InvReturnDsbCreate";
		browseName = "inv-returns-dsbline";
	}

	Commodity commodity = CommodityManager.getInstance().getCommodity(oid, s_commodity) ;
	boolean isDualUm = false ;
	boolean isSerNoRequired=false ;

	if (commodity != null) {
		String atemp = commodity.getSerialNumbersRequired() ;
		String umtemp = commodity.getDuomRequired() ;
		if (atemp == null) atemp = "N" ;
		if (umtemp == null) umtemp = "N" ;
		isSerNoRequired = atemp.equalsIgnoreCase("Y") ;
		if (s_duomRequired.equalsIgnoreCase("Y")) isDualUm = umtemp.equalsIgnoreCase("Y") ;
	}

	String returnMethod = HiltonUtility.ckNull((String)request.getAttribute("returnMethod"));
	boolean isOTC = false;
	if (returnMethod.equalsIgnoreCase("OTC")) {
		handler = "InvReturnOtcCreate";
		isOTC = true;
	}

	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
%>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="InvReturn_lineNo" value="<%=invReturn.getLineNo()%>"/>
<%-- <tsa:hidden name="InvReturn_requisitionNumber" value="<%=invReturn.getRequisitionNumber()%>"> --%>
<tsa:hidden name="InvReturn_icReqHeader" value="<%=invReturn.getIcReqHeader()%>"/>
<tsa:hidden name="InvReturn_icReqLine" value="<%=invReturn.getIcReqLine()%>"/>
<tsa:hidden name="InvReturn_icDsbHeader" value="<%=invReturn.getIcDsbHeader()%>"/>
<tsa:hidden name="InvReturn_icDsbLine" value="<%=invReturn.getIcDsbLine()%>"/>
<tsa:hidden name="InvReturn_icBin" value=""/>
<tsa:hidden name="InvBinLocation_newQty" value="<%=invReturn.getRecAmount()%>"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=invReturn.getItemNumber()%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="<%=HiltonUtility.ckNull(bin.getItemLocation())%>"/>

<% if (requisitionLine != null) { %>
	<tsa:hidden name="RequisitionLine_icReqLine" value="<%= requisitionLine.getIcReqLine() %>"/>
<% } else { %>
	<tsa:hidden name="RequisitionLine_icReqLine" value="<%= invReturn.getIcReqLine() %>"/>
<% } %>

<% if (disbLine != null) { %>
	<tsa:hidden name="DisbLine_icDsbLine" value="<%= disbLine.getIcDsbLine() %>"/>
<% } %>

<%-- <tsa:hidden name="InvReturn_itemNumber" value="<%=invReturn.getItemNumber()%>"/> --%>

<%-- set the icrc for the property serial numbers link --%>
<tsa:hidden name="InvBinLocation_icRc" value="${disbLine.icRc}"/>
<tsa:hidden name="InvLocation_itemNumber" value="${disbLine.itemNumber}"/>
<tsa:hidden name="InvLocation_itemLocation" value="${disbLine.itemLocation}"/>
<tsa:hidden name="Commodity_commodity" value="${commodity.commodity}"/>
<tsa:hidden name="fromPage" value="dsb_item"/>
<tsa:hidden name="binAction" value=""/>
<tsa:hidden name="showAddBinLocation" value=""/>

<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
  		<tr>
  			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
  		</tr>
  		<tr>
  			<td nowrap class="hdr12" valign="middle">
  				<div style="margin-left:10px; margin-right:10px" class=hdr12>Return to Inventory</div>
  			</td>
  		</tr>
		</table>
	</td>
	<td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align="right" height="30px">
		<!--table border="0" cellspacing="0" cellpadding="1" width="100%"-->
		<!-- part2-->
		<table cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="1000px" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="2" cellspacing="0" width="680px">
  <tr>
    <td width="350px">&nbsp;</td>
<%    if (viewAction.equalsIgnoreCase("browse")) { %>
    <td width="200px" align="right" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
    <% } %>
  </tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="440px" class="browseHdr">
		<tr>
			<td width="100%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRowHdr">
				<tr>
					<td width="125px" align="right" class="browseRowHdr" nowrap align="center"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-dateReceived", "Date Received")%>:&nbsp;</td>
					<td nowrap><input <%=disabled%> type="text" name="InvReturn_recDate" size="15" maxlength="15" tabindex="9"  value="<%=HiltonUtility.getFormattedDate(invReturn.getRecDate(),oid) %>" onchange="checkDate(this);">
					<%	if (allowBrowse.equalsIgnoreCase("true")) { %>
						<a href="javascript: show_calendar('InvReturn_recDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign="bottom" border="0"></a>
					<%	} %>
					</td>
					<td>&nbsp;</td>
					<td width="125px" align="right" class="browseRowHdr" nowrap><a href="javascript: browseLookup('InvReturn_recBy', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-recBy", "Received By", true)%>:</a>&nbsp;</td>
					<td><input <%=disabled%> type="text" name="InvReturn_recBy" value="<%=invReturn.getRecBy()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
      				<td width="125px" align="right" class="browseRowHdr" nowrap>
      				<%	if (isOTC) { %>
      					<a href="javascript: browseLookup('InvReturn_itemNumber', 'invitem');"><tsa:label labelName="inv-itemNumber" /></a>:&nbsp;
      				<%	} else { %>
      					<tsa:label labelName="inv-itemNumber" />:&nbsp;
      				<%	} %>
      				</td>
      				<td><input disabled type="text" name="InvReturn_itemNumber" value="<%=invReturn.getItemNumber()%>"/></td>
      				<td>&nbsp;</td>
      			<%	if (disbLine != null) { %>
      				<td width="125px" align="right" class="browseRowHdr" nowrap><tsa:label labelName="inv-requisitionNumber" />:&nbsp;</td>
      				<td><input disabled type="text" name="InvReturn_disbNumber" value="<%=invReturn.getDisbNumber()%>"></td>
      			<%	} else { %>
      				<td width="125px" align="right" class="browseRowHdr" nowrap><tsa:label labelName="inv-requisitionNumber" />:&nbsp;</td>
      				<td><input disabled type="text" name="InvReturn_requisitionNumber" value="<%=headerEncoder.encodeForHTMLAttribute(invReturn.getRequisitionNumber())%>"></td>
      			<%	} %>
				</tr>
				<%	if (isOTC) { %>
				<tr>
      				<td width="125px" align="right" class="browseRowHdr" nowrap>
      					<a href="javascript: browseInvLocation('InvReturn_itemLocation', 'invlocation');"><tsa:label labelName="inv-itemLocation" defaultString="Item Location" /></a>:&nbsp;
      				</td>
      				<td><input disabled type="text" name="InvReturn_itemLocation" value=""/></td>
      				<td>&nbsp;</td>
      				<td>&nbsp;</td>
      				<td>&nbsp;</td>
				</tr>
				<%	} %>
				<!--start -->
				<tr>
  					<td width="125px" align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-supplier", "Supplier", true)%>:&nbsp;</td>
  					<td><input disabled type="text" name="vendorId" value="<%=bin.getVendorId()%>"></td>
  					<td>&nbsp;</td>
					<td width=125px align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf1Code', 'BN01');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN01", "UDF 1")%>:</a>&nbsp;</td>
					<td><input type=text name="InvBinLocation_udf1Code" size=15 maxlength=15 tabindex=21 value="<%=bin.getUdf1Code()%>" onchange="upperCase(this);"></td>
  				</tr>
				<tr>
  					<td width="125px" align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-manufacturerNumber", "Manufacturer No", true)%>:&nbsp;</td>
  					<td><input disabled type="text" name="InvBinLocation_manufactNo" value="<%=bin.getManufactNo()%>"></td>
  					<td>&nbsp;</td>
					<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN02", "UDF 2", true)%>:&nbsp;</td>
					<td nowrap>
						<input type=text name="InvBinLocation_udf2Code" size=15 maxlength=15 tabindex=23 value="<%=bin.getUdf2Code()%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('InvBinLocation_udf2Code', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
					</td>
  				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
					<td width=125px align=right nowrap>&nbsp;</td>
					<td align=center nowrap><b>Primary UOM</b></td>
<% if (isDualUm) { %>
					<td align=center nowrap><b>Secondary UOM</b></td>
<% } else { %>
					<td>&nbsp;</td>
<% } %>
				</tr>
				<tr>
					<td width=125px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfIssue", "Unit Of Measure")%>:&nbsp;</td>
					<td><input type=text size=15 name="unitOfIssue" TABINDEX=2 value="<%=s_umCode%>" style="text-align:right" disabled></td>
<% if (isDualUm) { %>
					<td nowrap><input type=text size=15 name="duomUmCode" TABINDEX=2 value="<%=s_duomUmCode%>" style="text-align:right" disabled></td>
<% } else { %>
					<td>&nbsp;</td>
<% } %>
					<td width=125px align=right nowrap><a href="javascript: browseStd('InvBinLocation_udf3Code', 'BN03');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-BN03", "UDF 3")%>:</a>&nbsp;</td>
					<td><input type=text name="InvBinLocation_udf3Code" size=15 maxlength=15 tabindex=21 value="<%=bin.getUdf3Code()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
  					<td width="125px" align=right nowrap>Quantity:&nbsp;</td>
  					<td><input <%if(!isOTC){%>disabled<%}%> type="text" name="InvReturn_recAmount" value="<%=HiltonUtility.getFormattedQuantity(invReturn.getRecAmount(), oid)%>" style="text-align:right" onchange="addUp(this);">&nbsp;&nbsp;&nbsp;</td>
<% if (isDualUm) { %>
					<td><input <%=disabled%> type=text name="InvReturn_duomQty" size=15 tabindex=15 value="<%=duomQty%>" style="text-align:right"></td>
<% } %>
<!--  				<td width="100px" align=right nowrap>UDF 3:&nbsp;</td>
  					<td><input disabled type="text" name="vendorId" value="<%=bin.getUdf3Code()%>"></td>
-->
  				</tr>
  				<tr>
  					<td width="125px" align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-unitCost", "Unit Cost")%>:&nbsp;</td>
  					<td><input disabled type="text" name="unitCost" value="<%=HiltonUtility.getFormattedPrice(bin.getCost(), oid)%>" style="text-align:right"></td>
  					<td>&nbsp;</td>
<!--  				<td width="100px" align=right nowrap>UDF 3:&nbsp;</td>
  					<td><input disabled type="text" name="vendorId" value="<%=bin.getUdf3Code()%>"></td>
-->
  				</tr>
  				<tr>
  					<td width="125px" align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin-inv-lotNumber", "Lot Number", true)%>:&nbsp;</td>
  					<td><input disabled type="text" name="lotNumber" value="<%=bin.getLot()%>"></td>
  					<td>&nbsp;</td>
  					<td width="125px" align=right nowrap>Reject Return:&nbsp;</td>
	      				<td nowrap><select name="InvReturn_rejectReturn" size="1">
  						<option value=""></option>
                        <option value="Y">Yes</option>
                        <option value="N">No</option>
                        </select>
                    </td>
<!--  				<td width="100px" align=right nowrap>UDF 4:&nbsp;</td>
  					<td><input disabled type="text" name="vendorId" value="<%=bin.getUdf4Code()%>"></td>
-->
  				</tr>
  				<tr>
  					<td width="125px" align=right nowrap>Facility:&nbsp;</td>
  					<td><input disabled type="text" name="itemLocation" value="${esapi:encodeForHTMLAttribute(invBinLocation.itemLocation)}"></td>
  					<td>&nbsp;</td>
  				<% if (isSerNoRequired) { %>
	  				<td colspan=2 align=right nowrap>
	  					<a href="javascript: viewProperty();"><IMG SRC="<%=contextPath%>/images/asset3.gif" BORDER="0" ALT="Property Serial Numbers">Property Serial Numbers</a>
	  				</td>
  				<% } else { %>
  					<td colspan=2>&nbsp;</td>
  				<% } %>
  				</tr>
  				<tr>
  					<td width="125px" align=right nowrap>&nbsp;</td>
  					<td>&nbsp;</td>
  					<td>&nbsp;</td>
<!--  				<td width="100px" align=right nowrap>UDF 5:&nbsp;</td>
  					<td><input disabled type="text" name="vendorId" value="<%=bin.getUdf5Code()%>"></td>
-->
  				</tr>
						<!--end -->
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%
	String toBin = "";
	Object toBinObj = (Object)request.getParameter("toBin");

	if (toBinObj instanceof String) {
		toBin = (String)toBinObj;
	}
	if (toBinObj instanceof BigDecimal) {
		toBin = ((BigDecimal)toBinObj).toString();
	}

	if (HiltonUtility.isEmpty(toBin)) {
		toBin = bin.getIcRc().toString();
	}
%>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" width="680px">
		<table id="itemTable" border="1" cellspacing="0" cellpadding="0" width="520px" class="browseRowHdr">
		<tr>
			<td width="100%" align="center" valign="top">
				<table border="0" cellspacing="1" cellpadding="2" width="100%" class="browseRowHdr">
				<tr class="browseHdr">
					<td width="30%" colspan="2" align="CENTER">Location</td>
<!--					<td width="20%" align="center">Row</td>
					<td width="20%" align="center">Tier</td>
					<td width="20%" align="center">Bin</td>
-->
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRowHdr">
				<tr>
					<td>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-aisle")%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-aisle", "Aisle", true)%></b>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-row")%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-row", "Row", true)%></b>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-tier")%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-tier", "Tier", true)%></b>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-bin")%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-bin", "Bin", true)%></b>&nbsp;</td>
				</tr>
				<tr>
				<%	if (isOTC) { %>
					<td width=125px align=right nowrap><a href="javascript: browseMoveBin(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-toBin", "Storage Location")%>:</a></td>
				<%	} else { %>
					<td width=125px align=right nowrap><a href="javascript: browseMove('toBin', '<%=invReturn.getItemNumber()%>', '<%=HiltonUtility.ckNull(bin.getItemLocation())%>', '<%=bin.getIcRc()%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-toBin", "Storage Location")%>:</a></td>
				<%	} %>
					<td <%=HtmlWriter.isVisible(oid, "inv-aisle")%>><input <%=disabled%> type="text" name="newAisle" value="<%=bin.getAisle()%>" size=15 maxlength=15 onchange="upperCase(this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "inv-row")%>><input <%=disabled%> type="text" name="newTier" value="<%=bin.getTier()%>" size=15 maxlength=15 onchange="upperCase(this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "inv-tier")%>><input <%=disabled%> type="text" name="newRow" value="<%=bin.getLocrow()%>" size=15 maxlength=15 onchange="upperCase(this);"></td>
					<td <%=HtmlWriter.isVisible(oid, "inv-bin")%>><input <%=disabled%> type="text" name="newBin" value="<%=bin.getBin()%>" size=15 maxlength=15 onchange="upperCase(this);">
						<tsa:hidden name="toBin" value="<%=toBin%>"/>
						<tsa:hidden name="newUdf1Code" value="<%=bin.getUdf1Code()%>"/>
					</td>
					<%--
					<td width="10%" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "BN01", "UDF 1 CODE")%>:</td>
					<td width="20%" align="center" class="browseRowHdr" nowrap><input <%=disabled%> type="text" name="newUdf1Code" value="<%=bin.getUdf1Code()%>" size="20" disabled>
					--%>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<div id="dummyFields" style="display:none;">

</div>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<% if(viewAction.equalsIgnoreCase("browse"))
{%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
<%}
else
{%>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
<%}%>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value = "<%=browseName%>";
	function returnMe()
	{
		var icBin = frm.toBin.value;
		var alertMessage = "";

		if (isEmpty(icBin))
			alertMessage += 'Please Select a Bin.\n';

		<%	if (isDualUm) { %>
		if (frm.InvReturn_duomQty)
		{
			var duomQty = nformat(eval(nfilter(frm.InvReturn_duomQty)),0);
			if (duomQty <= 0)
				alertMessage += 'Must enter Secondary UOM greater than zero.\n';
		}
		<%	} %>
		<%	if (isSerNoRequired) { %>
		if (frm.qty)
		{
			var qtyV = nformat(eval(nfilter(frm.qty)),0);
			var serialNumbers = 0;
			<%	if (serialNumbers != null) { %>
			serialNumbers = <%=serialNumbers.size()%>;
			<%	} %>
			if (qtyV != serialNumbers)
				alertMessage += 'Must have same qty of serial numbers as qty on this Inv Return.\n';
		}
		<%	} %>

		if (trim(frm.InvReturn_itemNumber) == "") {
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item Number")%> is required.\n';
		}

		<%	if (isOTC) { %>
		if (trim(frm.InvReturn_itemLocation) == "") {
			alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemLocation", "Item Location")%> is required.\n';
		}
		<%	} %>
		if (frm.InvReturn_recAmount && eval(nfilter(frm.InvReturn_recAmount)) == 0) {
			alertMessage += 'You must enter a quantity greater than ZERO.\n';
		}

		if (!(!isEmpty(trim(frm.newAisle)) || !isEmpty(trim(frm.newAisle)) || !isEmpty(trim(frm.newAisle)) || !isEmpty(trim(frm.newAisle))))
		{
			alertMessage += 'Must have at least a ' +
				'<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-aisle", "Aisle")%>, ' +
				'<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-row", "Row")%>, ' +
				'<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-tier", "Tier")%> or ' +
				'<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-bin", "Bin")%>.\n';
		}

		if (alertMessage.length > 0)
		{
			alert('Please fix the following problems:\n\n' + alertMessage);
			return false;
		}
		else
		{
			frm.InvReturn_icBin.value = icBin;
		}

		doSubmit('/inventory/inv_return_forward.jsp', '<%=handler%>');

		return true;
	}

	function viewProperty() {
		frm.binAction.value = "PROPERTY";
		doSubmit('/inventory/inv_return_property_list.jsp', 'InvReturnSetProperty');
	}

	function createInvReturnOtc(itemNumber)
	{
		var newInputField = "<input type='hidden' name='InvItem_itemNumber' value='" + itemNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='returnMethod' value='<%=headerEncoder.encodeForHTML(returnMethod)%>'>";
		setHiddenFields(newInputField);
		doSubmit('/inventory/inv_return_summary.jsp', 'InvReturnOtcCreatePreview');
	}

	function addUp(field)
	{
		var qty_dec = <%=Integer.valueOf(s_quantity_decimals).intValue()%>;
		var q = nformat(eval(nfilter(field)),qty_dec);
		field.value = q;
		if (frm.InvReturn_recAmount) { frm.InvReturn_recAmount.value = q; }
		if (frm.InvBinLocation_newQty) { frm.InvBinLocation_newQty.value = q; }
	}

	function browseInvLocation(field, browse)
	{
		popupParameters = "colname=InvLocation_id_itemNumber;operator==;filter_txt='" + frm.InvReturn_itemNumber.value + "';logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup(field, browse);
	}

	function browseMoveBin()
	{
		var itemNumber = '';
		var itemLocation = '';

		if (frm.InvReturn_itemNumber) {
			itemNumber = frm.InvReturn_itemNumber.value;
		}
		if (frm.InvReturn_itemLocation) {
			itemLocation = frm.InvReturn_itemLocation.value;
		}

		if (!isEmpty(itemNumber) && !isEmpty(itemLocation)) {
			frm.showAddBinLocation.value = "Y";
		} else {
			frm.showAddBinLocation.value = "N";
		}
		browseMove('toBin', itemNumber, itemLocation, '<%=bin.getIcRc()%>');
	}

// End Hide script -->
</SCRIPT>