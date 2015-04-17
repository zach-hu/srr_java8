<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<table border="0" cellspacing="0" cellpadding="2" width="250px">
	<tsa:tr>
		<!--In this part, We can get the guy who initiated the service-->
		<tsa:td  noWrap="nowrap" align="right" ><tsa:label labelName="assetservice-initiatedby" defaultString="Initiated By" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetService.getCallInitiatedBy()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We can get the date when a service had been initiated-->
		<tsa:td  noWrap="nowrap" align="right" > <tsa:label labelName="assetservice-initiateddate" defaultString="Initiated Date" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetService.getServiceCallDate(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We can get the call date in a respective format -->
		<tsa:td align="right" ><tsa:label labelName="assetservice-servicecalldate" defaultString="Service Call Date" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetService.getServiceCallDate(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We can get the response date in a respective format -->
		<tsa:td noWrap="nowrap" align="right" > <tsa:label labelName="assetservice-responsedate" defaultString="Response Date" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetService.getResponseDate(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We can get the completion date in a respective format -->
		<tsa:td  noWrap="nowrap" align="right" ><tsa:label labelName="assetservice-completiondate" defaultString="Completion Date" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetService.getCompletionDate(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part, We can get the service action -->
		<tsa:td align="right" ><tsa:label labelName="assetservice-serviceaction" defaultString="Service Action" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetService.getServiceAction()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetservice-servicecost" defaultString="Service Cost" /></tsa:td>
		<tsa:td noWrap="nowrap">:<%=HiltonUtility.getFormattedDollar(assetService.getServiceCost(), oid)%></tsa:td>
	</tsa:tr>
</table>
