<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%pageContext.setAttribute("oid", oid); %>
<table border="0" cellspacing="0" cellpadding="2" width="450px">
	<tsa:tr>
		<!--In this part, We get the date changed and entered for an assetnote -->		
		<tsa:td field="assetnote-datechanged" align="right">
		<tsa:label labelName="assetnote-datechanged" defaultString="Date Changed" />
		</tsa:td>
		<tsa:td noWrap="nowrap"><tsa:input type="mintext" name="AssetNote_dateChanged" maxLength="10" value="<%=HiltonUtility.getFormattedDate(assetNote.getDateChanged(),oid, userDateFormat)%>" disabled="true" /></tsa:td>
		<tsa:td>&nbsp;</tsa:td>
		<tsa:td field="assetnote-dateentered" align="right">
		<tsa:label labelName="assetnote-dateentered" defaultString="Date Entered" />
		</tsa:td>		
		<tsa:td noWrap="nowrap"><tsa:input type="mintext" name="AssetNote_dateEntered" maxLength="10" value="<%=HiltonUtility.getFormattedDate(assetNote.getDateEntered(),oid, userDateFormat)%>" disabled="true" /></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="6">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr valign="top">
		<!--In this part, if the access and permissions are rigth, we can get the userId-->
		<% if (role.getAccessRights("ASTN")>=0) { %>
		<tsa:td field="assetnote-enteredby" align="right">
		<% if (role.getAccessRights("ASTN")>0) { %>
		<a href="javascript: browseLookup('AssetNote_userId', 'user'); void(0);" title="Click here to choose the value for <tsa:label labelName="assetnote-enteredby" defaultString="Entered By" /> for this item or enter the value in the box on the right.">
		<tsa:label labelName="assetnote-enteredby" defaultString="Entered By" /></a>
		<% } else { %>
		<tsa:label labelName="assetnote-enteredby" defaultString="Entered By" />
		<% } %>
		</tsa:td>		
		<tsa:td noWrap="nowrap">
		<% if (action.equalsIgnoreCase("new")) { %>
			<tsa:input type="text" name="AssetNote_userId" size="30" maxLength="15" value="<%=assetNote.getUserId()%>" onchange="upperCase(this); getNewInfo('user', this);" disabled="<%=accessASTN%>"></tsa:input>
		<%} else {%>
			<tsa:input type="text" name="AssetNote_userId" size="30" maxLength="15" value="${esapi:encodeForHTMLAttribute(userId)}" onchange="upperCase(this); getNewInfo('user', this);" disabled="<%=accessASTN%>"></tsa:input>
		<%}%>
		</tsa:td>
		<% } %>
		<tsa:td colspan="3">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr valign="top">
		<!--In this part, if the access ans permissions are rigth, we can get the owner name-->
		<% if (role.getAccessRights("ASTN")>=0) { %>
		<tsa:td align="right"></tsa:td>
		<tsa:td noWrap="nowrap"><tsa:input type="text" name="as_ownerName" size="30" maxLength="256" value="<%=owner.getDisplayName()%>" disabled="true" /></tsa:td>
		<% } %>
		<tsa:td colspan="3">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr valign="top">
		<!--In this part, We can get information and details about assetnote -->
		<tsa:td field="assetnote-description" align="right">
		<tsa:label labelName="assetnote-description" defaultString="Description" />
		</tsa:td>		
		<tsa:td colspan="4" noWrap="nowrap">
			<tsa:input type="textarea" name="AssetNote_stdText" cols="50" rows="6">${esapi:encodeForHTML(assetNote.stdText)}</tsa:input>
		</tsa:td>
	</tsa:tr>
</table>

<tsa:hidden name="AssetNote_lastChgBy" value=""/>
<tsa:hidden name="AssetLocation_tagNumber" value=""/>