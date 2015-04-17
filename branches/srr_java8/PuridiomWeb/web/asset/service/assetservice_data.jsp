<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<table border="0" cellspacing="0" cellpadding="2" width="450px">
	<tsa:tr>
		<!--In this part, if the access and permissions are rigth we can get the guy who initiated the service and the date that he made it-->
		<% if (role.getAccessRights("ASTN")>=0) { %>		
		<tsa:td field="assetservice-initiatedby" align="right" noWrap="nowrap">
		<% if (role.getAccessRights("ASTN")>0) { %><a href="javascript: browseLookup('AssetService_callInitiatedBy', 'user'); void(0);" title="Click here to choose the value for <tsa:label labelName="assetservice-initiatedby" defaultString="Initiated By" /> for this item or enter the value in the box on the right.">
		<tsa:label labelName="assetservice-initiatedby" defaultString="Initiated By" /></a>
		<% } else { %>
		<tsa:label labelName="assetservice-initiatedby" defaultString="Initiated By" />
		<% } %>
		</tsa:td>
		<tsa:td ><tsa:input type="mintext" name="AssetService_callInitiatedBy" maxLength="20" value="<%=assetService.getCallInitiatedBy()%>" /></tsa:td>
		<% } %>
		<% if (role.getAccessRights("ASTN")<0) { %>
		<tsa:td colspan="3">&nbsp;</tsa:td>		
		<%}else {%>
		<tsa:td>&nbsp;</tsa:td>		
		<%}%>
		
		<tsa:td field="assetservice-initiateddate" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-initiateddate" defaultString="Initiated Date" />
		</tsa:td>
		<tsa:td><tsa:input type="mintext" name="AssetService_dateInitiated" maxLength="10" value="<%= (action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(assetService.getServiceCallDate(),oid, userDateFormat): \"\" %>" />
		<a href="javascript: show_calendar('AssetService_dateInitiated', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="6">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, we can get the the call and response date for an asset service -->
		<tsa:td field="assetservice-servicecalldate" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-servicecalldate" defaultString="Service Call Date" />
		</tsa:td>		
		<tsa:td noWrap="nowrap"><tsa:input type="mintext" name="AssetService_serviceCallDate" maxLength="10" value="<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(assetService.getServiceCallDate(),oid, userDateFormat) : \"\" %>" />
		<a href="javascript: show_calendar('AssetService_serviceCallDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
		<tsa:td>&nbsp;</tsa:td>
		<tsa:td field="assetservice-responsedate" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-responsedate" defaultString="Response Date" />
		</tsa:td>		
		<tsa:td ><tsa:input type="mintext" name="AssetService_responseDate" maxLength="10" value="<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(HiltonUtility.ckNull(assetService.getResponseDate()),oid) : \"\" %>" />
		<a href="javascript: show_calendar('AssetService_responseDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, we can get the the completion date for an asset service -->
		<tsa:td field="assetservice-completiondate" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-completiondate" defaultString="Completion Date" />
		</tsa:td>		
		<tsa:td><tsa:input type="mintext" name="AssetService_completionDate" maxLength="10" value="<%=(action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(HiltonUtility.ckNull(assetService.getCompletionDate()),oid) : \"\" %>" />
		<a href="javascript: show_calendar('AssetService_completionDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
		</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="6">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, we can get the the action and the cost for made it -->
		<tsa:td field="assetservice-serviceaction" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-serviceaction" defaultString="Service Action" />
		</tsa:td>		
		<tsa:td noWrap="nowrap" ><tsa:input type="midtext" name="AssetService_serviceAction" maxLength="80" value="<%=assetService.getServiceAction()%>" /></tsa:td>
		<tsa:td>&nbsp;</tsa:td>
		<tsa:td field="assetservice-servicecost" align="right" noWrap="nowrap">
		<tsa:label labelName="assetservice-servicecost" defaultString="Service Cost" />
		</tsa:td>		
		<tsa:td noWrap="nowrap"><tsa:input type="text" name="AssetService_serviceCost" maxLength="15" value="<%=HiltonUtility.getFormattedDollar(assetService.getServiceCost(), oid)%>" onchange="formatPrice(this)" style="text-align:right" /></tsa:td>
	</tsa:tr>
</table>

<tsa:hidden name="AssetService_lastChgBy" value=""/>
<tsa:hidden name="AssetService_dateChanged" value=""/>
<tsa:hidden name="AssetLocation_tagNumber" value=""/>