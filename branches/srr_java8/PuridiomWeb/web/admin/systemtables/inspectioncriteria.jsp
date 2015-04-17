<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	int i;
	InspectionCrit inspectionCrit = (InspectionCrit) request.getAttribute("inspectionCrit");

	if (inspectionCrit == null) {
		inspectionCrit = new InspectionCrit() ;
		InspectionCritPK pk = new InspectionCritPK() ;
		inspectionCrit.setComp_id(pk) ;
		inspectionCrit.setStatus("01") ;
		inspectionCrit.getComp_id().setCritNo("") ;
		inspectionCrit.getComp_id().setInspectCode("") ;
		inspectionCrit.setDescription("") ;
		inspectionCrit.setDefaultFlag("N") ;
		inspectionCrit.setDateEntered(d_today) ;
		inspectionCrit.setOwner(uid) ;
	}


	UserProfile owner = UserManager.getInstance().getUser(oid, uid);

%>
<tsa:hidden name="allowBrowse" value="TRUE"></tsa:hidden>

<br>

<table width=<%=formWidth %> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Criteria Admin</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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


<br>

<table border=0 cellspacing=0 cellpadding=0 width=700px>
<tr>
	<td width=100% valign=top>
		<table id="inspection" border="0" cellpadding="3" cellspacing="0" align="center">
				<tsa:tr>
					<tsa:td field="ins-inspCode"  noWrap="nowrap" align="center"><a href="javascript: browseCodes('InspectionCrit_inspectCode','inspectionCode'); void(0);"><tsa:label labelName="ins-code" defaultString="Inspection Code"/></a></tsa:td>
					<tsa:td id="ins-code"><input type="text" name="InspectionCrit_inspectCode" value="<%=inspectionCrit.getComp_id().getInspectCode()%>"  size="15" maxlength="1" onchange="upperCase(this);" ></tsa:td>

					<tsa:td field="ins-critNo" noWrap="nowrap" align="center"><tsa:label labelName="ins-critNo" defaultString="Criteria No"/></a></tsa:td>
					<tsa:td id="ins-critNo"><input type="text" name="InspectionCrit_critNo" value="<%=inspectionCrit.getComp_id().getCritNo()%>"  size="15" maxlength="2" /></tsa:td>

					<tsa:td field="ins-defaultFlag" align="center" noWrap="nowrap"><tsa:label labelName="ins-defaultFlag" defaultString="Default Flag"/></tsa:td>
					<tsa:td id="ins-defaultFlag" field="ins-defaultFlag" align="center">
						<input type="checkbox" name="c_checkbox" value="Y" <%if (inspectionCrit.getDefaultFlag().equals("Y")) {%>CHECKED<%}%> onclick="setCheckbox(frm.InspectionCrit_defaultFlag, 0)"/>
						<input id="1" type="hidden" name="InspectionCrit_defaultFlag" value="<%=inspectionCrit.getDefaultFlag()%>"/>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td noWrap="nowrap" align="right" colspan=""><tsa:label labelName="ins-critDescription" defaultString="Description"/></tsa:td>
					<tsa:td id="ins-critDesc" colspan="3"><textarea name="InspectionCrit_description" cols="90" rows="5" ><%=inspectionCrit.getDescription()%></textarea></tsa:td>
				</tsa:tr>
		</table>
	</td>
</tr>
</table>

<br>
<table border=0 cellpadding=1 cellspacing=0 width=475px height=75px align=center>
		<tr>
			<td width=40% valign=top align=center>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="InspectionCrit_status" onchange="setStatus();">
							<% if (inspectionCrit.getStatus().compareTo("02") < 0) { %>
							<option value="01" <% if (inspectionCrit.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<% } %>
							<% if (inspectionCrit.getStatus().compareTo("03") < 0) { %>
							<option value="02" <% if (inspectionCrit.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<% } %>
							<option value="03" <% if (inspectionCrit.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InspectionCrit_dateEntered" size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(inspectionCrit.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InspectionCrit_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InspectionCrit_owner" tabindex=51 size=30 maxlength=15 value="<%=inspectionCrit.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
					</td>
				</tr>
				<tr>
					<td nowrap align=right>Owner Name:&nbsp;</td>
					<td>
						<input type=text name="as_ownerName" size=30 value="<%=owner.getDisplayName()%>" disabled>
					</td>
				</tr>
				</table>
			</td>
		</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=600px>
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: if (validate()){submitThis()}">Save</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: browse('inspectioncriteria-admin'); void(0);">Cancel</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
	frm.browseName.value = "inspectioncriteria-admin";


	function thisLoad()
	{
		f_StartIt();
		<% if (inspectionCrit.getStatus().compareTo("01") > 0) { %>
			checkInputSettings();
			<% } %>

		<% if (inspectionCrit.getStatus().compareTo("03") < 0) { %>
		allowInputEdit(frm.InspectionCrit_status, true);
		<% } %>
	}


	function browse(x)
	{
		frm.browseName.value = x;
		frm.allowBrowse.value="TRUE" ;
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function browseCodes(fld, x)
	{
		browseLookup(fld, x);
	}



	function setCurrentRow(row)
	{
		currentRow = row;
	}



	function viewInpsectionCodes()
	{
	}

	function submitThis()
	{
		doSubmit('/browse/browse_sys_tables.jsp', 'InspectionCritUpdate;BrowseRetrieve');
	}

	function updateDefaultFlag(x, val)
	{
		var field1 = document.getElementById(x);
		field1.value = val;

	}

	function validate()
	{
		if ( frm.InspectionCrit_inspectCode.value == ""){
			alert("You must fill in a inspection code before saving.");
			return false;
		}
		if ( frm.InspectionCrit_critNo.value == ""){
			alert("You must fill in a Criteria Number before saving.");
			return false;
		}

		return true;
	}

// End Hide script -->
</SCRIPT>