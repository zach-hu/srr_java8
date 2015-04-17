<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
	String 	s_alternate = propertiesManager.getProperty("INVENTORY", "ITEMCROSSREF", "N");
	
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
%>
<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
<tsa:hidden name="InvLocation_itemBinLocation" value=""/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shopping_cart", "Shopping Cart")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
<tr>
	<td width=<%=formWidth%> align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
		<tr>
			<td width=50% align=center valign=top>
				<table id=invLocationsTable border=0 cellspacing=0 cellpadding=0 width=<%=formWidth%> class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Physical Count Entry [Location: ${esapi:encodeForHTML(InvBinLocation_itemLocation)}]</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=1 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td class=browseHdr width=4% <%=HtmlWriter.isVisible(oid, "bin-inv-aisle")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-aisle", "Aisle", true)%></td>
							<td class=browseHdr width=4% <%=HtmlWriter.isVisible(oid, "bin-inv-row")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-row", "Row", true)%></td>
							<td class=browseHdr width=4% <%=HtmlWriter.isVisible(oid, "bin-inv-tier")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-tier", "Tier", true)%></td>
							<td class=browseHdr width=4% <%=HtmlWriter.isVisible(oid, "bin-inv-bin")%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bin", "Bin", true)%></td>
							<td class=browseHdr align=left width=8%>Actual Quantity</td>
							<% if (s_duomRequired.equalsIgnoreCase("Y")) { %>
								<td class=browseHdr align=left width=8%>Dual Quantity</td>
							<% } %>
							<td class=browseHdr align=left width=20%>Item Number</td>
<% if (s_alternate.equalsIgnoreCase("Y")) { %>
							<td class=browseHdr align=left width=10%>NSN</td>
<% } %>
							<td class=browseHdr align=left width=50%>Description</td>
							<!--
							<td align=center width=8%><b>Qty Available</b></td>
							<td align=center width=8%><b>Row</b></td>
							<td align=center width=8%><b>Tier</b></td>
							<td align=center width=5%><b>Bin</b></td>
							<td align=center width=5%><b>Lot</b></td>
							<td align=center width=11%><b>Item Date</b></td>
							-->
						</tr>
<!--
						</table>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
-->
<%
	List locList = (List)request.getAttribute("invBinLocationList");
	int	i_rowCount = locList.size();
	if (locList != null)
	{
		for(int i=0;i<i_rowCount;i++)
		{
			Object rObj[] = (Object[]) locList.get(i);
			InvItem invItem = (InvItem) rObj[0] ;
			InvBinLocation invBinLocation = (InvBinLocation) rObj[1] ;

			Commodity commodity = null ;
			String  commodityDuom = null ;
			if (s_duomRequired.equalsIgnoreCase("Y")) {
				commodityDuom = "DISABLED" ;
				if (rObj.length > 2) {
					commodity = (Commodity) rObj[2] ;
					if (commodity != null && commodity.getDuomRequired().equalsIgnoreCase("Y")) {
						commodityDuom = "" ;
					}
				}
			}

			BigDecimal	bd_ir_rc = invBinLocation.getIcRc();
			String	s_item_number = HiltonUtility.ckNull(invBinLocation.getItemNumber());
			String	s_aisle = HiltonUtility.ckNull(invBinLocation.getAisle());
			String	s_description = HiltonUtility.ckNull(invItem.getDescription());
			String	s_nsn = HiltonUtility.ckNull(invItem.getNsnNumber());
			String	s_locrow = HiltonUtility.ckNull(invBinLocation.getLocrow());
			String	s_tier = HiltonUtility.ckNull(invBinLocation.getTier());
			String	s_bin = HiltonUtility.ckNull(invBinLocation.getBin());
			String	s_lot = HiltonUtility.ckNull(invBinLocation.getLot());
			String	s_itemdate = HiltonUtility.getFormattedDate(invBinLocation.getItemDate(), oid, userDateFormat);

			if (HiltonUtility.isEmpty(s_aisle)) s_aisle = "&nbsp;" ;
			if (HiltonUtility.isEmpty(s_locrow)) s_locrow = "&nbsp;" ;
			if (HiltonUtility.isEmpty(s_tier)) s_tier = "&nbsp;" ;
			if (HiltonUtility.isEmpty(s_bin)) s_bin = "&nbsp;" ;

			BigDecimal	bd_phys_actual = HiltonUtility.getFormattedQuantity(invBinLocation.getPhysActual(), oid);
			BigDecimal	bd_phys_duom  = HiltonUtility.getFormattedQuantity(invBinLocation.getDuomPhysActual(), oid);
			BigDecimal	bd_qty_on_hand = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyOnHand(), oid);
			BigDecimal	bd_qty_alloc = HiltonUtility.getFormattedQuantity(invBinLocation.getQtyAlloc(), oid);
			BigDecimal	bd_qty_avail = bd_qty_on_hand.subtract(bd_qty_alloc);
%>
						<tr>
							<td class=browseRow width=4% align=left nowrap><%=s_aisle%></td>
							<td class=browseRow width=4% align=left nowrap><%=s_locrow%></td>
							<td class=browseRow width=4% align=left nowrap><%=s_tier%></td>
							<td class=browseRow width=4% align=left nowrap><%=s_bin%></td>
							<td class=browseRow width=8% align=left><input type=text name="InvBinLocation_physActual_Array" style="text-align:right;" size="15" value="<%=bd_phys_actual%>" onchange="javascript: setChangedFlag(<%=i%>);" ></td>
							<% if (s_duomRequired.equalsIgnoreCase("Y")) { %>
								<td class=browseRow width=8% align=left><input type=text name="InvBinLocation_duomPhysActual_Array" style="text-align:right;" size="15" value="<%=bd_phys_duom%>" onchange="javascript: setChangedFlag(<%=i%>);"  <%=commodityDuom%>></td>
							<% } %>
							<td class=browseRow width=15% align=left nowrap><%=s_item_number%></td>
<% if (s_alternate.equalsIgnoreCase("Y")) { %>
							<td class=browseRow width=15% align=left nowrap><%=s_nsn%></td>
<% } %>
							<td class=browseRow width=46% align=left ><%=s_description%></td>
							<!--
							<td width=8% align=right nowrap><%=bd_qty_avail%></td>
							<td width=8% align=left nowrap><%=s_locrow%></td>
							<td width=8% align=left nowrap><%=s_tier%></td>
							<td width=5% align=left nowrap><%=s_bin%></td>
							<td width=5% align=left nowrap><%=s_lot%></td>
							<td width=11% align=left nowrap><%=s_itemdate%></td>
							-->
							<!--td width=8% align=center nowrap><a href="javascript: viewLocation(<%//=i%>);"><IMG SRC="<%=contextPath%>/images/cmd_search_item.gif" BORDER="0" ALT="View"></a></td-->
							<tsa:hidden name="InvBinLocation_icRc_Array" value="<%=bd_ir_rc%>"/>
							<tsa:hidden name="changed" value="N"/>
						</tr>
<% 	}
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('inventory/physical_menu.jsp', 'InvBinLocationBatchUpdatePhysActual'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('inventory/physical_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
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

	var qtyDecimals  = <%=Integer.valueOf(quantityDecimals).intValue()%>;

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

	function setChangedFlag(row) {
		var physActual = nformat(eval(nfilter(frm.InvBinLocation_physActual_Array[row])), qtyDecimals);
		frm.InvBinLocation_physActual_Array[row].value = physActual;
		
		var chgElement = document.getElementsByName("changed") ;
		<% if (i_rowCount > 1) { %>
			chgElement[row].value = "Y" ;
		<% } else { %>
			chgElement.value = "Y" ;
		<% } %>
	}

	function viewLocation(row) {
		var loc = document.getElementById("invLocation_" + row);
		frm.InvLocation_itemLocation.value = loc.value;
		doSubmit('inventory/edit_location.jsp','InvLocationRetrieveById');
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