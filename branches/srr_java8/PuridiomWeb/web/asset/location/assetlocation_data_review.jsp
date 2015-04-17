<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<!--In this part, We get information of assetlocation like UserId, the format for the date, displayname, phone number, shipcode, etc.-->
<table border="0" cellspacing="0" cellpadding="2" width="250px">
	<tsa:tr>

		<tsa:td align="right"><tsa:label labelName="assetlocation-name" defaultString="Name" /></tsa:td>
		<tsa:td >:<%=assetLocation.getUserId()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right"><tsa:label labelName="assetlocation-dateassigned" defaultString="Date Assigned" /></tsa:td>
		<tsa:td>: <%=HiltonUtility.getFormattedDate(assetLocation.getDateAssigned(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-username" defaultString="User Name" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=owner.getDisplayName()%> </tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-telephone" defaultString="Telephone" /></tsa:td>
		<tsa:td noWrap="nowrap">:<%=owner.getPhoneNumber()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-email" defaultString="Email" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=owner.getMailId()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-routing" defaultString="Routing" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=owner.getRouting()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-shipTo" defaultString="Ship to" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=owner.getShipToCode()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-telephone" defaultString="Telephone" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetLocation.getTelephone()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="assetlocation-facility" defaultString="Facility" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetLocation.getFacility()%> </tsa:td>

	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" > <tsa:label labelName="assetlocation-room" defaultString="Room" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetLocation.getRoom()%> 	</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" > <tsa:label labelName="assetlocation-building" defaultString="Building" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=assetLocation.getBuilding()%> </tsa:td>

	</tsa:tr>
</table>