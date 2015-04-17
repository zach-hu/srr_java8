<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid);
 %>
<table border="0" cellspacing="0" cellpadding="2" height=100% width="100%">
	<tsa:tr>
		<td valign="top">
			<table border="0" cellspacing="2" width="100%" >
				<tsa:tr field="assetlocation-name">
					<!--In this part, We can choose or enter the value for the name for assetlocation-->
					<% if (role.getAccessRights("ASTN")>=0) {  %>
					<tsa:td field="assetlocation-name" align="right">
					<% if (role.getAccessRights("ASTN")>0) {  %><a href="javascript: browseLookup('AssetLocation_userId', 'user'); void(0);" title="Click here to choose the value for <tsa:label labelName="assetlocation-name" defaultString="Name" /> for this item or enter the value in the box on the right.">
					<tsa:label labelName="assetlocation-name" defaultString="Name" /></a>:&nbsp;
					<% } else { %>
					<tsa:label labelName="assetlocation-name" defaultString="Name" />:&nbsp;
					<%} %>
					</tsa:td>
					<%} %>
					<tsa:td noWrap="nowrap" field="assetlocation-name">
					<% if (action.equalsIgnoreCase("new")) { %>
					<tsa:input type="text" name="AssetLocation_userId" size="30" maxLength="30" value="<%=assetLocation.getUserId()%>" onchange="upperCase(this); getNewInfo('user', this);" disabled="<%=accessASTN%>" />
					<%} else {%>
					<tsa:input type="text" name="AssetLocation_userId" size="30" maxLength="30" value="${esapi:encodeForHTMLAttribute(userId)}" onchange="upperCase(this); getNewInfo('user', this);" disabled="<%=accessASTN%>" />
					<%}%>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="assetlocation-username">
					<!--In this part, if the access and permissions are rigth, we can get the user name-->
					<% if (role.getAccessRights("ASTN")>=0) { %>
					<tsa:td field="assetlocation-username" align="right">
					<tsa:label labelName="assetlocation-username" defaultString="User Name" />:&nbsp;
					</tsa:td>
					<tsa:td noWrap="nowrap" field="assetlocation-username"><tsa:input type="text" name="as_ownerName" size="30" maxLength="30" value="<%=owner.getDisplayName()%>" disabled="true" /></tsa:td>
					<% } %>
					<tsa:td colspan="3">&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr field="assetlocation-telephone">
					<!--In this part, we can get the the telephone and email for the owner-->
					<tsa:td field="assetlocation-telephone" align="right">
					<tsa:label labelName="assetlocation-telephone" defaultString="Telephone" />:&nbsp;
					</tsa:td>
					<tsa:td noWrap="nowrap" field="assetlocation-telephone"><tsa:input type="text" name="as_ownerTelephone" size="30" maxLength="30" value="<%=owner.getPhoneNumber()%>" disabled="true" /></tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr field="assetlocation-routing">
					<!--In this part, we can get the routing and details of the shipment-->
					<tsa:td field="assetlocation-routing" align="right">
					<tsa:label labelName="assetlocation-routing" defaultString="Routing" />:&nbsp;
					</tsa:td>
					<tsa:td noWrap="nowrap" field="assetlocation-routing"><tsa:input type="text" name="as_ownerRouting" size="30" maxLength="30" value="<%=owner.getRouting()%>" disabled="true" /></tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
				<tsa:tr field="assetlocation-room">
					<!--In this part, we can get the room and if the access and permissions are rigth, we can get the building-->
					<tsa:td field="assetlocation-room" align="right">
					<tsa:label labelName="assetlocation-room" defaultString="Room" />:&nbsp;
					</tsa:td>
					<tsa:td noWrap="nowrap" field="assetlocation-room" ><tsa:input type="text" name="AssetLocation_room" size="30" maxLength="20" value="<%=assetLocation.getRoom()%>" /></tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
			</table>
		</td>

		<td valign="top">
		<table border=0 cellpadding=0 cellspacing=2 width="100%">
			<tsa:tr field="assetlocation-dateassigned">
				<!--In this part, We can choose a Date or Enter a Date in a special format for it-->
				<tsa:td field="assetlocation-dateassigned" align="right" noWrap="nowrap">
				<tsa:label labelName="assetlocation-dateassigned" defaultString="Date Assigned" />:&nbsp;
				</tsa:td>
				<tsa:td><tsa:input type="text" name="AssetLocation_dateAssigned" size="25" maxLength="10" value='<%= (action.equalsIgnoreCase(\"new\")) ? HiltonUtility.getFormattedDate(assetLocation.getDateAssigned(),oid, userDateFormat) : \"\" %>'/>
				<a href="javascript: show_calendar('AssetLocation_dateAssigned', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
				</tsa:td>
			</tsa:tr>
			<tsa:tr>
			<td>&nbsp;</td>
			</tsa:tr>
			<tsa:tr>
			<td>&nbsp;</td>
			</tsa:tr>
			<tsa:tr field="assetlocation-email">
				<tsa:td field="assetlocation-email" align="right" noWrap="nowrap">
				<tsa:label labelName="assetlocation-email" defaultString="Email" />:&nbsp;
				</tsa:td>
				<tsa:td noWrap="nowrap" field="assetlocation-email"><tsa:input type="text" name="as_ownerEmail" size="30" maxLength="256" value="<%=owner.getMailId()%>" disabled="true" /></tsa:td>
			</tsa:tr>
			<tsa:tr field="assetlocation-shipTo">
				<tsa:td field="assetlocation-shipTo" align="right">
				<tsa:label labelName="assetlocation-shipTo" defaultString="Ship to" />:&nbsp;
				</tsa:td>
				<tsa:td noWrap="nowrap" field="assetlocation-shipTo"><tsa:input type="text" name="as_shipTo" size="30" maxLength="20" value="<%=owner.getShipToCode()%>" disabled="true" /> </tsa:td>
			</tsa:tr>
			<tsa:tr field="assetlocation-facility">
				<% if (role.getAccessRights("ASTF")>=0) { %>
				<tsa:td field="assetlocation-facility" align="right">
				<% if (role.getAccessRights("ASTF")>0) { %>
				<a href="javascript: browseLookup('AssetLocation_facility', 'ship_to'); void(0);" title='Click here to choose the value for <tsa:label labelName="assetlocation-facility" defaultString="Facility" /> for this item or enter the value in the box on the right.'>
				<tsa:label labelName="assetlocation-facility" defaultString="Facility" /></a>:&nbsp;
				<% } else { %>
				<tsa:label labelName="assetlocation-facility" defaultString="Facility" />:&nbsp;
				<% } %>
				</tsa:td>
				<tsa:td noWrap="nowrap" field="assetlocation-facility"><tsa:input type="text" name="AssetLocation_facility" size="30" maxLength="20" value="<%=assetLocation.getFacility()%>" disabled="<%=accessASTF%>"/> </tsa:td>
				<% } %>
			</tsa:tr>
			<tsa:tr field="assetlocation-building">
				<% if (role.getAccessRights("ASTB")>=0) { %>
				<tsa:td field="assetlocation-building" align="right">
				<% if (role.getAccessRights("ASTB")>0) { %><a href="javascript: browseStd('AssetLocation_building', 'ASTB'); void(0);" title='Click here to choose the value for <tsa:label labelName="assetlocation-building" defaultString="Building" /> for this item or enter the value in the box on the right.'>
				<tsa:label labelName="assetlocation-building" defaultString="Building" /></a>:&nbsp;
				<% } else { %>
				<tsa:label labelName="assetlocation-building" defaultString="Building" />:&nbsp;
				<% } %>
				</tsa:td>
				<tsa:td noWrap="nowrap" field="assetlocation-building" ><tsa:input type="text" name="AssetLocation_building" size="30" maxLength="20" value="<%=assetLocation.getBuilding()%>" disabled="<%=accessASTB%>" /> 	</tsa:td>
				<% } %>
			</tsa:tr>
		</table>
		</td>
	</tsa:tr>
</table>

<tsa:hidden name="AssetLocation_locationType" value=""/>
<tsa:hidden name="AssetLocation_lastChgBy" value=""/>
<tsa:hidden name="AssetLocation_dateChanged" value=""/>
