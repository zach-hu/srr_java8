<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<%

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String invItemNumber = (String) request.getAttribute("InvItem_itemNumber");
	String s_itemnumber = (String) request.getAttribute("InvLocation_itemNumber");
	String	errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String s_umCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_unitOfOrder"));
	String s_duomUmCode = HiltonUtility.ckNull((String) request.getAttribute("InvItem_duomUmCode"));

	String	s_action = (String) request.getAttribute("binAction");
	String	s_title = "";

	if (s_action == null)
	{
		s_action = "CREATE";
	}

	BigDecimal bd_zero = new BigDecimal(0);
	BigDecimal bd_qtyonhand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_qtyonorder = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_qtyalloc = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_qtypendorder = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_backorder = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_minonhand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_maxonhand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_averagecost = HiltonUtility.getFormattedDollar(bd_zero, oid);
	BigDecimal bd_qtyeoq = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_qtyesq = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_qtyavailable = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_duomqtyonhand = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_duomqtyalloc = HiltonUtility.getFormattedQuantity(bd_zero, oid);
	BigDecimal bd_duomqtyavailable = HiltonUtility.getFormattedQuantity(bd_zero, oid);

	String s_udf1 = "";
	String s_udf2 = "";
	String s_udf3 = "";
	String s_udf4 = "";
	String s_udf5 = "";
	String s_autoreplenish = "";
	String s_primelocation = "";
	BigDecimal bd_icInvAccount = new BigDecimal(0);

	if (s_action.equalsIgnoreCase("CREATE"))
	{
		s_title = "New Location";
	}
	else
	{
		s_title = "Edit Location";

		InvLocation invLocation = (InvLocation) request.getAttribute("invLocation");
		bd_qtyonhand = HiltonUtility.getFormattedQuantity(invLocation.getQtyOnHand(), oid);
		bd_qtyonorder = HiltonUtility.getFormattedQuantity(invLocation.getQtyOnOrder(), oid);
		bd_qtyalloc = HiltonUtility.getFormattedQuantity(invLocation.getQtyAlloc(), oid);
		bd_qtypendorder = HiltonUtility.getFormattedQuantity(invLocation.getQtyPendOrder(), oid);
		bd_backorder = HiltonUtility.getFormattedQuantity((BigDecimal) request.getAttribute("Backorder"), oid);
		bd_minonhand = HiltonUtility.getFormattedQuantity(invLocation.getMinOnHand(), oid);
		bd_maxonhand = HiltonUtility.getFormattedQuantity(invLocation.getMaxOnHand(), oid);
		bd_averagecost = HiltonUtility.getFormattedPrice(invLocation.getAverageCost(), oid);
		bd_qtyeoq = HiltonUtility.getFormattedQuantity(invLocation.getQtyEoq(), oid);
		bd_qtyesq = HiltonUtility.getFormattedQuantity(invLocation.getQtyEsq(), oid);

		bd_duomqtyonhand = HiltonUtility.getFormattedQuantity(invLocation.getDuomQtyOnHand(), oid);
		bd_duomqtyalloc = HiltonUtility.getFormattedQuantity(invLocation.getDuomQtyAlloc(), oid);

		if ( (bd_qtyonhand.subtract(bd_qtyalloc)).compareTo(new BigDecimal(0)) > 0)
		{
			bd_qtyavailable = bd_qtyonhand.subtract(bd_qtyalloc);
		}

		if ( (bd_duomqtyonhand.subtract(bd_duomqtyalloc)).compareTo(new BigDecimal(0)) > 0)
		{
			bd_duomqtyavailable = bd_duomqtyonhand.subtract(bd_duomqtyalloc);
		}

		s_udf1 = HiltonUtility.ckNull(invLocation.getUdf1Code());
		s_udf2 = HiltonUtility.ckNull(invLocation.getUdf2Code());
		s_udf3 = HiltonUtility.ckNull(invLocation.getUdf3Code());
		s_udf4 = HiltonUtility.ckNull(invLocation.getUdf4Code());
		s_udf5 = HiltonUtility.ckNull(invLocation.getUdf5Code());
		s_autoreplenish = HiltonUtility.ckNull(invLocation.getAutoReplenish());
		s_primelocation = HiltonUtility.ckNull(invLocation.getPrimeLocation());
		bd_icInvAccount = invLocation.getIcInvAccount();
	}
	String dualUmRequired = HiltonUtility.ckNull((String) request.getAttribute("dualUmRequired")) ;
	String serNoRequired = HiltonUtility.ckNull((String) request.getAttribute("serNoRequired")) ;
%>

<tsa:hidden name="InvItem_itemNumber" value="<%=invItemNumber%>"/>

<tsa:hidden name="InvItem_commodity" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_commodity\"))%>"/>
<tsa:hidden name="InvItem_unitOfOrder" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_unitOfOrder\"))%>"/>
<tsa:hidden name="InvItem_duomUmCode" value="<%=HiltonUtility.ckNull((String) request.getAttribute(\"InvItem_duomUmCode\"))%>"/>
<tsa:hidden name="dualUmRequired" value="<%=dualUmRequired%>"/>
<tsa:hidden name="serNoRequired" value="<%=serNoRequired%>"/>

<tsa:hidden name="itemAction" value="UPDATE"/>
<tsa:hidden name="InvLocation_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvBinLocation_itemNumber" value="<%=s_itemnumber%>"/>
<tsa:hidden name="InvBinLocation_itemLocation" value="${InvLocation_itemLocation}"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_icInvAccount%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="binAction" value="<%=s_action%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/inventory/inv_location.jsp"/>

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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="70%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item/Part #")%>:</b>&nbsp;</td>
			 <td align=left width="30%"><%=headerEncoder.encodeForHTML(s_itemnumber)%></td>
		</tr>
		<tr>
			 <td align=right width="70%"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:</b>&nbsp;</td>
			 <td align=left width="30%">${esapi:encodeForHTML(InvLocation_itemLocation)}</td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td colspan=2 class=error align=center><br><%=errorMsg%><br></td></tr>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
<%	if (s_action.equalsIgnoreCase("CREATE")) { %>
					<td align=right><a href="javascript: browseLookup('InvLocation_itemLocation', 'item_location'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:</a>&nbsp;<input type=text size=15 name="InvLocation_itemLocation"  tabindex="1" value="${esapi:encodeForHTMLAttribute(InvLocation_itemLocation)}" disabled>
<%	} else { %>
				<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-location", "Item Location")%>:&nbsp;<input type=text size=15 name="InvLocation_itemLocation"  tabindex="1" value="${esapi:encodeForHTMLAttribute(InvLocation_itemLocation)}" disabled>
<%	} %>
					</td>
					<td align=right nowrap>
<%	if (s_action.equalsIgnoreCase("UPDATE")) { %>
						<a href="javascript: doSubmit('/inventory/inv_item_accounts.jsp', 'AccountRetrieveByLine');">Account Allocations</a>
<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-autoReplenish")%> align=right nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-autoReplenish", "Auto Replenish")%>:&nbsp;
						<input type=checkbox name="c_checkbox" tabindex=17 <% if (s_autoreplenish.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.InvLocation_autoReplenish, 0);">
						<tsa:hidden name="InvLocation_autoReplenish" value="<%=s_autoreplenish%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-primeLocation")%> align=right nowrap>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-primeLocation", "Prime Location")%>:&nbsp;
						<input type=checkbox name="c_checkbox" tabindex=18 <% if (s_primelocation.equals("Y")) { %> CHECKED <% } %> value="Y" onclick="setCheckbox(frm.InvLocation_primeLocation, 1);">
						<tsa:hidden name="InvLocation_primeLocation" value="<%=s_primelocation%>"/>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "inv-binLocations")%> align=right nowrap>&nbsp;
<%	if (s_action.equalsIgnoreCase("UPDATE")) { %>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-binLocations", "Bin Location(s)")%>:&nbsp;<a href="javascript: viewBin();"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0"  TABINDEX=18 ALT="Bin Location Adjustment"></a>
<%	} %>
					</td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=36% align=right valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td align=right nowrap>Primary UOM</td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>Secondary UOM</td>
<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-unitOfIssue", "Unit Of Issue")%>:&nbsp<input type=text size=15 name="InvLocation_unitOfIssue" TABINDEX=2 value="${invItem.unitOfIssue}" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="InvLocation_duomUmCode" TABINDEX=2 value="<%=s_duomUmCode%>" style="text-align:right" disabled></td>
<% } %>
				</tr>

				<tr <%=HtmlWriter.isVisible(oid, "inv-quantityOnHand")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-quantityOnHand", "Qty on Hand")%>:&nbsp<input type=text size=15 name="InvLocation_qtyOnHand" TABINDEX=2 value="<%=bd_qtyonhand%>" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="InvLocation_duomQtyOnHand" TABINDEX=2 value="<%=bd_duomqtyonhand%>" style="text-align:right" disabled></td>
<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-quantityOnOrder")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-quantityOnOrder", "Qty on Order", true)%>:&nbsp<input type=text size=15 name="InvLocation_qtyOnOrder" TABINDEX=3 value="<%=bd_qtyonorder%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-allocated")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-allocated", "Allocated")%>:&nbsp<input type=text size=15 name="InvLocation_qtyAlloc" TABINDEX=4 value="<%=bd_qtyalloc%>" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="InvLocation_duomQtyAlloc" TABINDEX=2 value="<%=bd_duomqtyalloc%>" style="text-align:right" disabled></td>
<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-available")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-available", "Available")%>:&nbsp<input type=text size=15 name="qtyAvailable" TABINDEX=5 value="<%=HiltonUtility.getFormattedQuantity(bd_qtyavailable, oid)%>" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="duomQtyAvailable" TABINDEX=5 value="<%=HiltonUtility.getFormattedQuantity(bd_duomqtyavailable, oid)%>" style="text-align:right" disabled></td>
<% } %>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-backorder")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-backorder", "Backorder")%>:&nbsp<input type=text size=15 name="backorder" TABINDEX=6 value="<%=bd_backorder%>" style="text-align:right" disabled></td>
<% if (dualUmRequired.equalsIgnoreCase("Y")) { %>
					<td align=right nowrap>&nbsp<input type=text size=15 name="backorder" TABINDEX=6 value="<%=bd_backorder%>" style="text-align:right" disabled></td>
<% } %>
				</tr>
				</table>
			</td>
			<td width=28% align=right valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr><td>&nbsp</td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-minimumOnHand")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-minimumOnHand", "Min on Hand", true)%>:&nbsp<input type=text size=15 name="InvLocation_minOnHand" TABINDEX=7 value="<%=bd_minonhand%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-maximumOnHand")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-maximumOnHand", "Max on Hand", true)%>:&nbsp<input type=text size=15 name="InvLocation_maxOnHand" TABINDEX=8 value="<%=bd_maxonhand%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-averageCost")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-averageCost", "Average Cost")%>:&nbsp<input type=text size=15 name="InvLocation_averageCost" TABINDEX=9 value="<%=bd_averagecost%>" style="text-align:right" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-economicOrderQuantity")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-economicOrderQuantity", "EOQ", true)%>:&nbsp<input type=text size=15 name="InvLocation_qtyEoq" TABINDEX=10 value="<%=bd_qtyeoq%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-economicStockQuantity")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-economicStockQuantity", "ESQ", true)%>:&nbsp<input type=text size=15 name="InvLocation_qtyEsq" TABINDEX=11 value="<%=bd_qtyesq%>" style="text-align:right"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-quantityPendingOrder")%>>
					<td align=right nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-quantityPendingOrder", "Qty Pend Order", true)%>:&nbsp<input type=text size=15 name="InvLocation_qtyPendOrder" TABINDEX=12 value="<%=bd_qtypendorder%>" style="text-align:right"></td>
				</tr>
				</table>
			</td>
			<td width=25% align=right valign=top>
				<table border=0 cellspacing=0 cellpadding=0>
				<tr><td>&nbsp</td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-IN01")%>>
					<td align=right nowrap><a href="javascript: browseStd('InvLocation_udf1Code', 'IN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-IN01", "UDF 1", true)%>:</a>&nbsp<input type=text size=15 name="InvLocation_udf1Code" TABINDEX=12 value="<%=s_udf1%>" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-IN02")%>>
					<td align=right nowrap><a href="javascript: browseStd('InvLocation_udf2Code', 'IN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-IN02", "UDF 2", true)%>:</a>&nbsp<input type=text size=15 name="InvLocation_udf2Code" TABINDEX=13 value="<%=s_udf2%>" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-IN03")%>>
					<td align=right nowrap><a href="javascript: browseStd('InvLocation_udf3Code', 'IN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-IN03", "UDF 3", true)%>:</a>&nbsp<input type=text size=15 name="InvLocation_udf3Code" TABINDEX=14 value="<%=s_udf3%>" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-IN04")%>>
					<td align=right nowrap><a href="javascript: browseStd('InvLocation_udf4Code', 'IN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-IN04", "UDF 4", true)%>:</a>&nbsp<input type=text size=15 name="InvLocation_udf4Code" TABINDEX=15 value="<%=s_udf4%>" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "inv-IN05")%>>
					<td align=right nowrap><a href="javascript: browseStd('InvLocation_udf5Code', 'IN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-IN05", "UDF 5", true)%>:</a>&nbsp<input type=text size=15 name="InvLocation_udf5Code" TABINDEX=16 value="<%=s_udf5%>" onchange="upperCase(this);"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				</table>
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
			<td id="saveMe" width=50% align=center><div id="pxbutton"><a href="javascript: submitThis(); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/inventory/inv_item_locations.jsp', 'InvLocationRetrieveByItem'); void(0);">Return</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/inv_location.jsp", "InvLocationRetrieveById", "${esapi:encodeForJavaScript(InvLocation_itemLocation)}");
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

	function viewBin() {
		doSubmit('/inventory/inv_bin_locations.jsp', 'InvBinLocationRetrieveByItem');
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

      if (handler.indexOf("InvLocationAdd") >= 0) {
	      if (isEmpty(frm.InvLocation_itemLocation.value))
	        	alertMessage += 'Item Location is required.\n';

	      if (alertMessage.length > 0) {
	            alert ('Please fix the following problems:\n\n' + alertMessage);
	            return false;
		  }
	  }

	  return true;
    }

	function submitThis()
	{
		if (frm.binAction.value == "CREATE")
		{
			doSubmit('/inventory/inv_item_locations.jsp', 'InvLocationAdd;InvLocationRetrieveByItem');
			//frm.binAction.value="UPDATE";
			//doSubmit('/inventory/inv_location.jsp', 'InvLocationAdd;InvLocationRetrieveById');
		}
		else
		{
			doSubmit('/inventory/inv_item_locations.jsp', 'InvLocationUpdate;InvLocationRetrieveByItem');
		}
	}

	function thisLoad()
	{
		var invAccess = <%=role.getAccessRights("INV")%>;
		f_StartIt();
		if (invAccess <= 1)
		{
			$('#saveMe').hide();
			checkInputSettings();
		}
	}


// End Hide script -->
</SCRIPT>