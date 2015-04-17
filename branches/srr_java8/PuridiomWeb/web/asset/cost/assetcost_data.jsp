<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%pageContext.setAttribute("oid", oid); %>
<table border=0 cellspacing=0 cellpadding=2 width="450px">
	<tsa:tr>
		<!--In this part, We show the date when we received assets -->
		<tsa:td field="assetcost-received" align="right" noWrap="nowrap">
		<tsa:label labelName="assetcost-received" defaultString="Received" />
		</tsa:td>		
		<tsa:td noWrap="nowrap"><tsa:input type="mintext" name="AssetCost_dateReceived" maxLength="10" value="<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(assetCost.getDateReceived(),oid, userDateFormat) : \"\" %>" />
			<a href="javascript: show_calendar('AssetCost_dateReceived', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
		<tsa:td>&nbsp;</tsa:td>
		<!--In this part, We show the date when we purchased assets -->
		<tsa:td field="assetcost-purchased" align="right" noWrap="nowrap">
		<tsa:label labelName="assetcost-purchased" defaultString="Purchased" />
		</tsa:td>		
		<tsa:td colspan="4" noWrap="nowrap"><tsa:input type="mintext" name="AssetCost_dateEntered" maxLength="10"  value="<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(assetCost.getDateEntered(),oid, userDateFormat) : \"\" %>" />
			<a href="javascript: show_calendar('AssetCost_dateEntered', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="5">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We show the Format in dollars about the asset amount that we have, also show the amount of purchase orders-->
		<tsa:td field="assetcost-amount" align="right">
		<tsa:label labelName="assetcost-amount" defaultString="Amount" />
		</tsa:td>		
		<tsa:td noWrap="nowrap"><tsa:input type="midtext" name="AssetCost_amount" maxLength="15" value="<%=HiltonUtility.getFormattedDollar(assetCost.getAmount(), oid)%>" onchange="formatPrice(this)" style="text-align:right" /></tsa:td>
		<tsa:td>&nbsp;</tsa:td>		
		<tsa:td field="assetcost-ponumber" align="right">
		<a href="javascript: browseOrders(); void(0);">
		<tsa:label labelName="assetcost-ponumber" defaultString="PO Number" />
		</a>
		</tsa:td>
		<tsa:td noWrap="nowrap"><tsa:input type="midtext" name="AssetCost_poNumber" maxLength="30" value="<%=assetCost.getPoNumber()%>" /></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We show the extendlife about an asset and who is our supplier -->
		<tsa:td field="assetcost-extendlife" align="right">
		<tsa:label labelName="assetcost-extendlife" defaultString="Extend Life" />
		</tsa:td>		
		<tsa:td noWrap="nowrap">
		<% if (assetCost.getExtendLifeFlag().equalsIgnoreCase("1")) {%>
		<tsa:input type="checkbox" name="AssetCost_extendLifeFlag" value="1" checked="true" />
		<%}else{ %>
		<tsa:input type="checkbox" name="AssetCost_extendLifeFlag" value="1" />
		<%} %>
		</tsa:td>
		<tsa:td>&nbsp;</tsa:td>		
		<tsa:td field="assetcost-supplier" align="right">
		<% if (role.getAccessRights("ASTP")>0) { %><a href="javascript: browseStd('AssetCost_poVendor', 'ASTP'); void(0);" title="Click here to choose the value for <tsa:label labelName="assetcost-supplier" defaultString="Supplier" /> for this item or enter the value in the box on the right.">
		<tsa:label labelName="assetcost-supplier" defaultString="Supplier" /></a>
		<% } else { %>
		<tsa:label labelName="assetcost-supplier" defaultString="Supplier" />
		<% } %>
		</tsa:td>
		<tsa:td noWrap="nowrap"><tsa:input type="midtext" name="AssetCost_poVendor" maxLength="40" value="<%=assetCost.getPoVendor()%>" /></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="5">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We show more details and information about assets-->
		<tsa:td field="assetcost-description" align="right">
		<tsa:label labelName="assetcost-description" defaultString="Description" />
		</tsa:td>		
		<tsa:td colspan="4" noWrap="nowrap">
			<tsa:input type="textarea" name="AssetCost_description" cols ="55" rows="6">${esapi:encodeForHTML(assetCost.description)}</tsa:input>
		</tsa:td>

	</tsa:tr>
</table>

<tsa:hidden name="AssetCost_dateChanged" value=""/>
<tsa:hidden name="AssetCost_lastChgBy" value=""/>
<tsa:hidden name="AssetLocation_tagNumber" value=""/>