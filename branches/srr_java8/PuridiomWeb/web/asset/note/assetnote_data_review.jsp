<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);%>
<table border="0" cellspacing="0" cellpadding="2" width="250px">
	<tsa:tr>
	<tsa:td>
		<table>
		<tsa:tr>
		<!--In this part, We get the date changed for an assetnote -->
		<tsa:td align="right"><tsa:label labelName="assetnote-datechanged" defaultString="Date Changed" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetNote.getDateChanged(),oid, userDateFormat)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
		<!--In this part, We get the date entered for an assetnote -->
		<tsa:td align="right"><tsa:label labelName="assetnote-dateentered" defaultString="Date Entered" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(assetNote.getDateEntered(),oid, userDateFormat)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
		<!--In this part, We get the guy who enter the respective asset -->
		<tsa:td align="right"><tsa:label labelName="assetnote-enteredby" defaultString="Entered By" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetNote.getUserId()%> - <%=owner.getDisplayName()%> </tsa:td>
		</tsa:tr>
		<tsa:tr>
		<!--In this part, We get information about assetnote -->
		<tsa:td align="right"><tsa:label labelName="assetnote-description" defaultString="Description" /></tsa:td>
		<tsa:td noWrap="nowrap">:<%=assetNote.getStdText()%></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	</tsa:tr>
</table>
