<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.reports.itemusage.tasks.*" %>
<%@ page import="com.tsa.puridiom.entity.InvUsage" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List.*" %>
<%@ page import="java.util.Calendar" %>

<%
	String s_itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	ItemDetailUsage itemDetailUsage = (ItemDetailUsage) request.getAttribute("itemDetailUsage");

%>

<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemNumber%>"/>
<tsa:hidden name="itemAction" value="UPDATE"/>

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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Item Procurement & Usage History</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=600px height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table width=660px cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td nowrap align=right width=30%><b>Item Number:</b>&nbsp;</td>
			<td width=20%><%=s_itemNumber%></td>
			<td nowrap align=right width=30%>Print Date:&nbsp;</td>
			<td width=20%><%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%></td>
		</tr>
		<tr>
			<td nowrap align=right valign=top><b>Description:</b>&nbsp;</td>
			<td colspan=3 valign=top><%=HiltonUtility.ckNull(itemDetailUsage.getDescription())%></td>
		</tr>
		<tr>
			<td colspan=4><br></td>
		</tr>
		<tr>
			<td nowrap align=right>Primary User Name:&nbsp;</td>
			<td><%=HiltonUtility.ckNull(itemDetailUsage.getPrimUser())%></td>
			<td nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%> Code:&nbsp;</td>
			<td><%=HiltonUtility.ckNull(itemDetailUsage.getDeptCode())%></td>
		</tr>
		<tr>
			<td nowrap align=right>Forms Analyst:&nbsp;</td>
			<td><%=HiltonUtility.ckNull(itemDetailUsage.getFormAnalyst())%></td>
			<td colspan=2>&nbsp;</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=1 cellpadding=0 cellspacing=0 class=browseHdr width=660px>
		<tr><td>&nbsp;Procurement History</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=2 width=100%>
				<tr>
					<td nowrap align=right width=30%>Date Last Printed:&nbsp;</td>
					<td width=20%><%=HiltonUtility.getFormattedDate(itemDetailUsage.getLastPrinted(), oid, userDateFormat)%></td>
					<td nowrap align=right width=30%>Last PO No:&nbsp;</td>
					<td width=20%><%=HiltonUtility.ckNull(itemDetailUsage.getLastPoNum())%></td>
				</tr>
				<tr>
					<td nowrap align=right>Last Vendor:&nbsp;</td>
					<td><%=HiltonUtility.ckNull(itemDetailUsage.getLastVend())%></td>
					<td colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=4><br></td>
				</tr>
				<tr>
					<td nowrap align=right>Last Receipt Date:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedDate(itemDetailUsage.getLastRecDate(), oid, userDateFormat)%></td>
					<td nowrap align=right>Last Quantity Received:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getLastQtyRec(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Last Adjustment Date:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedDate(itemDetailUsage.getLastAdjDate(), oid, userDateFormat)%></td>
					<td nowrap align=right>Last Quantity Adjusted:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getLastQtyAdj(), oid)%></td>
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
	<td width=680px align=center>
		<table border=1 cellpadding=0 cellspacing=0 class=browseHdr width=660px>
		<tr><td>&nbsp;Usage History</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=2 width=100%>
				<tr>
					<td nowrap align=right width=30%>Quantity On Hand:&nbsp;</td>
					<td width=20%><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyOnHand(), oid)%></td>
					<td nowrap align=right width=30%>Quantity Allocated:&nbsp;</td>
					<td width=20%><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyAlloc(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Quantity Available:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyAvail(), oid)%></td>
					<td nowrap align=right>Reorder Point:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getReorderPoint(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Quantity Used Last 24 Months:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyLast24Months(), oid)%></td>
					<td nowrap align=right>Quantity Used Last 12 Months:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyLast12Months(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Quantity Used Last Month:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getQtyLastMonth(), oid)%></td>
					<td nowrap align=right>Average Monthly Usage:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getAvgMonthUsage(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Calculated Minimum On Hand:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getCalcMoh(), oid)%></td>
					<td nowrap align=right>Calculated EOQ:&nbsp;</td>
					<td><%=HiltonUtility.getFormattedQuantity(itemDetailUsage.getCalcEoq(), oid)%></td>
				</tr>
				<tr>
					<td nowrap align=right>Auto Reprint:&nbsp;</td>
					<td><%=HiltonUtility.ckNull(itemDetailUsage.getAutoReprint())%></td>
					<td colspan=2>&nbsp;</td>
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

<%
	int size = 0;
	List itemUsageList = itemDetailUsage.getUsage();
	if (itemUsageList != null)
	{
		size = itemUsageList.size();
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=1 cellpadding=0 cellspacing=0 class=browseHdr width=660px>
		<tr><td>&nbsp;Prior 24 Month History</td></tr>
		<tr>
			<td>
				<table border=0 cellpadding=2 cellspacing=2 width=100%>
				<tr>
					<td width=50px>&nbsp;</td>
					<td>
						<table border=0 cellpadding=2 cellspacing=2 width=100%>
<%
		for (int i = 0; i < size; i++)
		{
			InvUsage invUsage = (InvUsage) itemUsageList.get(i);

			if (i < size/2)
			{
%>
						<tr>
							<td nowrap><%=invUsage.getMonthName(invUsage.getUsageMonth())%></td>
							<td><%=invUsage.getUsageYear()%></td>
							<td align=right><%=HiltonUtility.getFormattedQuantity(invUsage.getQuantity(), oid)%></td>
						</tr>
<%		}
		} %>
						</table>
					</td>
					<td width=120px>&nbsp;</td>
					<td>
						<table border=0 cellpadding=2 cellspacing=2 width=100%>
<%
		for (int i = 0; i < size; i++)
		{
			InvUsage invUsage = (InvUsage) itemUsageList.get(i);

			if (i >= size/2)
			{
%>
						<tr>
							<td nowrap><%=invUsage.getMonthName(invUsage.getUsageMonth())%></td>
							<td><%=invUsage.getUsageYear()%></td>
							<td align=right><%=HiltonUtility.getFormattedQuantity(invUsage.getQuantity(), oid)%></td>
						</tr>
<%		}
		} %>
						</table>
					</td>
					<td width=50px>&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: this.print(); void(0);"><img class=button src="<%=contextPath%>/images/button_print.gif" border=0 alt="Print"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>


<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/forecast_history.jsp", "DoNothing", "Item Procurement and Usage History");
	frm = document.purchaseform;

	function printPreview()
	{
		popupParameters = "InvItem_itemNumber=<%=s_itemNumber%>";
		//doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

// End Hide script -->
</SCRIPT>