<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	process = (String) request.getAttribute("process");
	String	groupId = (String) request.getAttribute("SecurityGroup_groupId");
	SecurityGroup securityGroup = new SecurityGroup();
	UserRole userRole = new UserRole();

	process = HiltonUtility.ckNull(process);

	if (!HiltonUtility.isEmpty(groupId) && !process.equals("ADDUSERROLE")) {
		userRole = UserRoleManager.getInstance().getUserRole(groupId, oid);
		securityGroup = GroupManager.getInstance().getSecurityGroup(oid, groupId);
	} else {
		process = "ADDUSERROLE";
	}
%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>User Role Profile</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=2>
<%	if (process.equals("ADDUSERROLE")) {%>
		<tr>
			<td width=400px align=right><b>New User Role</b></td>
		</tr>
<%	} else {%>
		<tr>
			<td width=400px align=right><b>Role Id:</b></td>
			<td width=400px><%=userRole.getRoleId()%><tsa:hidden name="SecurityGroup_groupId" value="<%=userRole.getRoleId()%>"/></td>
		</tr>
<%	}%>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%	if (!process.equals("ADDUSERROLE")) {%>
<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td width=100% align=center>
		<a href="javascript: deleteRole(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete User Role" border="0"><font style="text-decoration:none;">&nbsp;</font>Delete User Role</a>
	</td>
</tr>
</table>

<br>
<%	}%>
<table border=0 cellspacing=0 cellpadding=2 width=680px>
<tr>
	<td width=100% align=center>
		<table border=0 cellpadding=2 cellspacing=0>
<%	if (process.equals("ADDUSERROLE")) {%>
		<tr>
			<td align=right><b>Role Id:</b></td>
			<td>
				<input type=text name="UserRole_roleId" value="<%=userRole.getRoleId()%>" tabindex=1 size=20 maxlength=15 taborder=1 ONCHANGE="this.value=this.value.toUpperCase(); frm.SecurityGroup_groupId.value=this.value;">
				<tsa:hidden name="SecurityGroup_groupId" value="<%=userRole.getRoleId()%>"/>
				<tsa:hidden name="SecurityGroup_status" value="02"/>
			</td>
		</tr>
<%	}%>
		<tr>
			<td align=right><b>Description:</b></td>
			<td><input type=text name="SecurityGroup_groupDescription" value="<%=headerEncoder.encodeForHTMLAttribute(securityGroup.getGroupDescription())%>" tabindex=3 size=65 maxlength=40 taborder=3></td>
		</tr>
		<tr>
			<td align=right><b>Idle Minutes:</b></td>
			<td><input type=text name="SecurityGroup_idlemin" value="<%=securityGroup.getIdlemin()%>" tabindex=4 size=10 maxlength=4></td>
		</tr>
		<tr>
			<td align=right><b>Access Level:</b></td>
			<td>
				<select name="flags" tabindex=5>
					<option value="">&nbsp;</option>
					<option value="V">View</option>
					<option value="M">View / Maintain</option>
					<option value="C">View / Maintain / Create </option>
					<option value="S">View / Create / Maintain / Delete</option>
				</select>
				<input type=button value="Set All" tabindex=6 onclick="resetFlags(); void(0);"></td>
		</tr>
		</table>

		<br>

		<table border=0 cellspacing=0 cellpadding=2 width=505px class=browseHdrDk>
		<tr>
			<td width=265px class=browseHdrDk>&nbsp;<b>Profile Name</b></td>
			<td width=230px class=browseHdrDk><b>Access Level</b></td>
			<td width=10px class=browseHdrDk>&nbsp;</td>
		</tr>
		</table>
		<div class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 505px; height: 358px; align:center; overflow-y:auto">
		<table id="profileRecords" border=0 cellspacing=0 cellpadding=2>
<%
	Map userAccessRights = userRole.getAccessProperties();

	boolean fromSecurityTypes = false;
	Map securityTypesTemp = propertiesManager.getSection("$" + userRole.getRoleId());
	if (securityTypesTemp.isEmpty()) {
		fromSecurityTypes = true;
		securityTypesTemp = propertiesManager.getSection("SECURITY TYPES");
	}

	final Map securityTypes = securityTypesTemp;

	Map securityTypesOrdered = new TreeMap(new Comparator()
	{
        public int compare(Object o1, Object o2)
        {
            Comparable value1 = (Comparable) securityTypes.get(o1);
            Comparable value2 = (Comparable) securityTypes.get(o2);

            return value1.compareTo(value2) ;
        }
        public boolean equals(Object obj)
        {
            return obj == this ;
        }
	});

	securityTypesOrdered.putAll(securityTypes) ;
	Iterator typeIterator = securityTypesOrdered.keySet().iterator();
	String	accessType = "";
	String	accessName = "";
	String	accessLevel = "";

	while (typeIterator.hasNext()) {
		accessType = (String) typeIterator.next();
		accessName = (String) securityTypes.get(accessType);
		if (!fromSecurityTypes) {
			accessName = "Edit " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, accessName, accessName);
		}

		if (userAccessRights.containsKey(accessType)) {
			accessLevel = (String) userAccessRights.get(accessType);
		} else {
			accessLevel = "";
		}
%>
		<tr>
			<td width=265px><%=accessName%></td>
			<td width=235px align=right>
				<tsa:hidden name="SecurityProfile_groupId" value="<%=userRole.getRoleId()%>"/>
				<tsa:hidden name="SecurityProfile_profileType" value="G"/>
				<tsa:hidden name="SecurityProfile_profile" value="<%=accessType%>"/>
				<select name="SecurityProfile_flags">
					<option value="" <% if (accessLevel == null || accessLevel.trim().length() == 0) {%>selected<%}%>></option>
					<option value="V" <% if (accessLevel.equals("V")) {%>selected<%}%>>View</option>
					<option value="M" <% if (accessLevel.equals("M")) {%>selected<%}%>>View / Maintain</option>
					<option value="C" <% if (accessLevel.equals("C")) {%>selected<%}%>>View / Maintain / Create</option>
					<option value="S" <% if (accessLevel.equals("S")) {%>selected<%}%>>View / Maintain / Create / Delete</option>
				</select>
			</td>
			<td width=5px>&nbsp;</td>
		</tr>
<%	}%>

		</table>
		</div>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: saveRoleProfile(); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('browse/browse.jsp', 'BrowseRetrieve'); void(0);">Cancel</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.browseName.value = "admin-securitygroup";

	function resetFlags() {
		var flagIndex = frm.flags.selectedIndex;
		var recordCount = document.getElementById("profileRecords").rows.length;

		if (recordCount == 1) {
			frm.SecurityProfile_flags.selectedIndex = flagIndex;
		}
		else {
			for (var i = 0; i < recordCount; i++) {
				frm.SecurityProfile_flags[i].selectedIndex = flagIndex;
			}
		}
	}

	function validateForm() {
		if (frm.handler.value == "UserRoleAdd") {
			if (isEmpty(frm.UserRole_roleId.value)) {
				alert("You must enter a Role Id.");
				return false;
			}
		}
		return true;
	}

	function saveRoleProfile() {
<%	if (process.equals("ADDUSERROLE")) {%>
		addRoleProfile();
<%	} else {%>
		doSubmit('browse/browse.jsp', 'SecurityGroupProfileUpdate;BrowseRetrieve')
<%	}%>
	}

	function addRoleProfile() {
		var groupIds = document.all("SecurityProfile_groupId");
		var groupId = frm.UserRole_roleId.value;

		if (groupIds.length > 1) {
			for (var i=0; i < groupIds.length; i++) {
			groupIds(i).value = groupId;
			}
		} else {
			groupIds.value = groupId;
		}
		doSubmit('browse/browse.jsp', 'SecurityGroupProfileUpdate;BrowseRetrieve');
	}

	function deleteRole() {
		if (verifyAction("Delete this User Role?")) {
			var groupIds = document.all("SecurityProfile_groupId");

			doSubmit('browse/browse.jsp', 'SecurityGroupProfileDelete;BrowseRetrieve');
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>