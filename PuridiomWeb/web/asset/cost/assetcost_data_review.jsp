<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<table border="0" cellspacing="0" cellpadding="2" width="305px">
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-received" defaultString="Received" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(HiltonUtility.ckNull(assetCost.getDateReceived()),oid, userDateFormat)%>	</tsa:td>

	</tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-purchased" defaultString="Purchased" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(HiltonUtility.ckNull(assetCost.getDateEntered()),oid, userDateFormat)%> </tsa:td>
	    </tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-amount" defaultString="Amount" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDollar(assetCost.getAmount(), oid)%></tsa:td>
	   </tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-ponumber" defaultString="PO Number" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetCost.getPoNumber()%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-extendlife" defaultString="Extend Life" /></div> </tsa:td>
		<tsa:td noWrap="nowrap">: <% if (assetCost.getExtendLifeFlag().equalsIgnoreCase("1")) {%> <tsa:label labelName="assetcost-yes" defaultString="YES" /> <%} else {%> <tsa:label labelName="assetcost-no" defaultString="NO" /> <%}%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-supplier" defaultString="Supplier" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetCost.getPoVendor()%></tsa:td>

		</tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
		<tsa:td align="right"><div style="text-align: right"><tsa:label labelName="assetcost-description" defaultString="Description" /></div></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetCost.getDescription()%></tsa:td>

	</tsa:tr>
</table>

