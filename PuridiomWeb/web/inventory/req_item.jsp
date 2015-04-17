<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.math.*" %>

<form name="purchaseform" target="_parent" action="/procure" method="POST">

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=1 id=itemTable cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td class=browseHdr height=18px nowrap>&nbsp;Inventory Item</td>
			</tr>
			<tr>
				<td class=browseRow>
<%
	InvItem invItem = (InvItem) request.getAttribute("retrieveInvItem");
	String s_item_number = invItem.getItemNumber();
	String s_item_description = invItem.getDescription();
	String s_item_taxable = invItem.getTaxable();
	String s_item_chargeable = invItem.getChargable();
	String s_item_kit = invItem.getKit();
	String s_item_restricted_item = invItem.getRestrictedItem();
	String s_item_ariba_export = invItem.getAribaExport();
	String s_item_usage_recalc = invItem.getUsageRecalc();
	String s_item_ssinterface = invItem.getSsInterface();
	String s_item_commodity = invItem.getCommodity();
	String s_item_abccode = invItem.getAbcCode();
	String s_item_unitoforder = invItem.getUnitOfOrder();
	String s_item_unitofissue = invItem.getUnitOfIssue();
	BigDecimal b_item_con_factor = invItem.getFactor();
	BigDecimal b_item_unitcost = invItem.getCost();
	BigDecimal b_item_issuecost = invItem.getIssueCost();
	BigDecimal b_item_avgcost = invItem.getAverageCost();
	BigDecimal b_item_lastcost = invItem.getLastCost();
	BigDecimal b_item_variance = invItem.getVariance();
	BigDecimal b_item_moh_total = invItem.getMohTot();
	BigDecimal b_item_eoq_total = invItem.getEoqTot();
	BigDecimal b_item_moh_months = invItem.getMohMonths();
	BigDecimal b_item_eoq_months = invItem.getEoqMonths();
	BigDecimal b_item_pull_qty = invItem.getPullIncrement();
	String s_item_lastpo = invItem.getLastPo();
	String s_item_receipt_req = invItem.getReceiptRequired();
	String s_item_buyercode = invItem.getBuyerCode();
	String s_item_managedby = invItem.getManagedBy();
	String s_udf1 = invItem.getUdf1Code();
	String s_udf2 = invItem.getUdf2Code();
	String s_udf3 = invItem.getUdf3Code();
	String s_udf4 = invItem.getUdf4Code();
	String s_udf5 = invItem.getUdf5Code();
	String s_udf6 = invItem.getUdf6Code();
	String s_udf7 = invItem.getUdf7Code();
	String s_udf8 = invItem.getUdf8Code();
	String s_udf9 = invItem.getUdf9Code();
	String s_udf10 = invItem.getUdf10Code();
	String s_udf11 = invItem.getUdf11Code();
	String s_udf12 = invItem.getUdf12Code();
	String s_udf13 = invItem.getUdf13Code();
	String s_udf14 = invItem.getUdf14Code();
	String s_udf15 = invItem.getUdf15Code();
	String test = "TEST";
%>
					<table border=0 cellspacing=0 cellpadding=1 width=80%>
					<tr>
						<td nowrap align=RIGHT width=12%><b>Item Number:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_number" SIZE="15" MAXLENGTH="15" value="<%=s_item_number%>" ONFOCUS="this.blur();" disabled></td>
						<td nowrap align=right rowspan=3>
							<b>Taxable:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_taxable.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>Chargeable:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_chargeable.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>Kit:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_kit.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>Restricted Item:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_restricted_item.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>Ariba Export:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_ariba_export.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>Usage Recalc:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_usage_recalc.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
							<b>S&S Interface:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (s_item_ssinterface.equals("Y")){%>CHECKED<%}%> value="" ONFOCUS="this.blur();"></b><BR>
						</td>
					</tr>
					<tr>
						<td nowrap align=RIGHT valign=top width=12%><b>Description:&nbsp</b></td>
						<td><TEXTAREA COLS=40 ROWS=5 NAME="inv_item_description" value="<%=s_item_description%>" ONFOCUS="this.blur();"></TEXTAREA></td>
						<td>&nbsp</td>
					</tr>
					<tr>
						<td width=20% align=RIGHT><b>Language:&nbsp</b></td>
						<td><select name="inv_item_language" tabindex=17>
						<option value="(Default)" SELECTED>(Default)</option>
						<option value="Spanish">Spanish</option>
						</select>
						</td>
						<td>&nbsp</td>
					</tr>
					</table>
					<table border=0 cellspacing=0 cellpadding=1 width=80%>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Commodity:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_commodity" SIZE="15" MAXLENGTH="15" value="<%=s_item_commodity%>" ONFOCUS="this.blur();" disabled></td>
						<td nowrap><b>ABC Code:&nbsp<INPUT TYPE="CHECKBOX" NAME="c_checkbox" ONFOCUS="this.blur();"></b>
								<INPUT TYPE="TEXT" NAME="inv_item_abccode" SIZE="1" MAXLENGTH="1" value="<%=s_item_abccode%>" ONFOCUS="this.blur();">
						</td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Unit of Order:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_unitoforder" SIZE="15" MAXLENGTH="15" value="<%=s_item_unitoforder%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 1:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf1" SIZE="15" MAXLENGTH="15" value="<%=s_udf1%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Unit of Issue:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_unitofissue" SIZE="15" MAXLENGTH="15" value="<%=s_item_unitofissue%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 2:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf2" SIZE="15" MAXLENGTH="15" value="<%=s_udf2%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Conversion Factor:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_conversion" SIZE="15" MAXLENGTH="15" value="<%=b_item_con_factor%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 3:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf3" SIZE="15" MAXLENGTH="15" value="<%=s_udf3%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Unit Cost:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_unitcost" SIZE="15" MAXLENGTH="15" value="<%=b_item_unitcost%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 4:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf4" SIZE="15" MAXLENGTH="15" value="<%=s_udf4%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Issue Cost:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_issuecost" SIZE="15" MAXLENGTH="15" value="<%=b_item_issuecost%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 5:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf5" SIZE="15" MAXLENGTH="15" value="<%=s_udf5%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Average Cost:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_avgcost" SIZE="15" MAXLENGTH="15" value="<%=b_item_avgcost%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>Total Min. On-Hand:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_minonhand" SIZE="15" MAXLENGTH="15" value="<%=b_item_moh_total%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Last Cost:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_lastcost" SIZE="15" MAXLENGTH="15" value="<%=b_item_lastcost%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>Total Econ. Order Qty:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_minorderqty" SIZE="15" MAXLENGTH="15" value="<%=b_item_eoq_total%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Variance:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_variance" SIZE="15" MAXLENGTH="15" value="<%=b_item_variance%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>Min. On-Hand Months:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_minonhandmonths" SIZE="15" MAXLENGTH="15" value="<%=b_item_moh_months%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%>&nbsp;</td>
						<td>&nbsp;</td>
						<td nowrap align=RIGHT><b>Econ. Order Qty. Months 1:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_minorderqtymonths" SIZE="15" MAXLENGTH="15" value="<%=b_item_eoq_months%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Receipt Option:</b></td>
						<td>
						<select name="inv_item_receiptoption" tabindex=17>
						<option value="Y" <% if (s_item_receipt_req.equals("Y")){%>SELECTED<%}%>>Receipt Required</option>
						<option value="N">Receipt Not Required</option>
						</select></td>
						<td nowrap align=RIGHT><b>Pull/Packaging Quantity:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_pullpackqty" SIZE="15" MAXLENGTH="15" value="<%=b_item_pull_qty%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Buyer Code:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_buyercode" SIZE="15" MAXLENGTH="15" value="<%=s_item_buyercode%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>Last Blanket Order #:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_blanketorder" SIZE="15" MAXLENGTH="15" value="<%=test%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>Managed By:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_managedby" SIZE="15" MAXLENGTH="15" value="<%=s_item_managedby%>" ONFOCUS="this.blur();"></td>
						<td>&nbsp</td>
					</tr>
					<tr>
						<td>&nbsp</td>
						<td>&nbsp</td>
						<td>&nbsp</td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>UDF 6:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_udf6" SIZE="15" MAXLENGTH="15" value="<%=s_udf6%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 7:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf7" SIZE="15" MAXLENGTH="15" value="<%=s_udf7%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>UDF 8:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_udf8" SIZE="15" MAXLENGTH="15" value="<%=s_udf8%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 9:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf9" SIZE="15" MAXLENGTH="15" value="<%=s_udf9%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>UDF 10:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_udf10" SIZE="15" MAXLENGTH="15" value="<%=s_udf10%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 11:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf11" SIZE="15" MAXLENGTH="15" value="<%=s_udf11%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>UDF 12:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_udf12" SIZE="15" MAXLENGTH="15" value="<%=s_udf12%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 13:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf13" SIZE="15" MAXLENGTH="15" value="<%=s_udf13%>" ONFOCUS="this.blur();"></td>
					</tr>
					<tr>
						<td nowrap align=RIGHT width=20%><b>UDF 14:</b>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="inv_item_udf14" SIZE="15" MAXLENGTH="15" value="<%=s_udf14%>" ONFOCUS="this.blur();"></td>
						<td nowrap align=RIGHT><b>UDF 15:&nbsp</b><INPUT TYPE="TEXT" NAME="inv_item_udf15" SIZE="15" MAXLENGTH="15" value="<%=s_udf15%>" ONFOCUS="this.blur();"></td>
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
	<td width=50% align=center><a href="javascript: doSubmit('requests/req_summary.jsp', 'RequisitionSave'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0></a></td>
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


// End Hide script -->
</SCRIPT>