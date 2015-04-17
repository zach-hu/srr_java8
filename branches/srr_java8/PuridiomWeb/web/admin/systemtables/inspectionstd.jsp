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
	List inspectionStdList = (List) request.getAttribute("inspectionStdList");
	HashMap inspMap = (HashMap) request.getAttribute("inspectionMap") ;
	//System.out.println(inspectionStdList.size());
	InspectionStd inspectionStd = null;
	boolean newInspectionStd = false;
	String inspectType = "";
	String standardCode = "";
	String standardDescription = "";
	String cgdNo = "" ;
	String cgdRev = "0" ;

	Date dateEntered = null;

	if (inspectionStdList == null)
	{
		inspectionStdList = new ArrayList();
	}
	if (inspectionStdList.size() <= 0)
	{
		inspectionStd = new InspectionStd();
		inspectionStd.setDateEntered(d_today);
		inspectionStd.setStatus("01");
		inspectionStd.setOwner(uid);
		newInspectionStd = true;
	}
	else
	{
		inspectionStd = (InspectionStd) inspectionStdList.get(0);
		inspectType = inspectionStd.getInspectType();
		standardCode = inspectionStd.getStandardCode();
		standardDescription = inspectionStd.getDescription();
		cgdNo = inspectionStd.getCgdNo() ;
		BigDecimal rev = inspectionStd.getCgdRev() ;
		if (rev  ==  null)  rev = new BigDecimal(0) ;
		cgdRev = rev.toString() ;
		dateEntered = inspectionStd.getDateEntered();
	}

	String status = inspectionStd.getStatus() ;

	UserProfile owner = UserManager.getInstance().getUser(oid, inspectionStd.getOwner());

%>
<tsa:hidden name="allowBrowse" value="TRUE"></tsa:hidden>
<tsa:hidden name="as_rows" value="<%=inspectionStdList.size()%>"/>
<tsa:hidden name="as_maxrows" value="0"/>
<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Inspection Standard Criteria</div>
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

<table border=0 cellspacing=0 cellpadding=0 width=780px>
<tr>
	<td>
		<table id="upperInspectionCodes" border=0 cellpadding=2 cellspacing=2 align=center>
		<tsa:tr>
			<tsa:td align="center"><tsa:label labelName="ins-stdCode" defaultString="Standard Code"/></tsa:td>
			<tsa:td align="center"><tsa:label labelName="ins-stdCgdNo" defaultString="CGD No."/></tsa:td>
			<tsa:td align="center"><tsa:label labelName="ins-stdCgdRev" defaultString="CGD Rev"/></tsa:td>
			<tsa:td align="center"><tsa:label labelName="ins-stdDescription" defaultString="Description"/></tsa:td>
			<tsa:td field ="ins-type" noWrap="nowrap" align="center"><tsa:label labelName="ins-type" defaultString="Inspection Type" checkRequired="true"/></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td><input type="text" name="InspectionStd_standardCode" value="<%=standardCode%>" size="15" maxlength="15" onchange="upperCase(this);" disabled></tsa:td>
			<tsa:td><input type="text" name="InspectionStd_cgdNo" value="<%=cgdNo%>" size="15" maxlength="20" onchange="upperCase(this);" ></tsa:td>
			<tsa:td><input type="text" name="InspectionStd_cgdRev" value="<%=cgdRev%>" size="5" maxlength="5" onchange="upperCase(this);" ></tsa:td>
			<tsa:td><input type="text" name="InspectionStd_description" value="<%=standardDescription%>" size="75" maxlength="2000"/></tsa:td>
			<td id="ins-type" <%=HtmlWriter.isVisible(oid, "ins-inspection")%>>
						<select name="InspectionStd_inspectType" value="<%=inspectType%>">
							<option value="RI"  <% if (inspectType.equals("RI") || HiltonUtility.isEmpty(inspectType)) {%> SELECTED <%}%>><tsa:label labelName="ins-receiptInspection" defaultString="Receipt Inspection (RICP)"></tsa:label></option>
							<option value="GI" <% if (inspectType.equals("GI") ) {%> SELECTED <%}%>><tsa:label labelName="ins-generalInspection" defaultString="General Inspection"></tsa:label></option>
						</select>
			</td>
			<tsa:td width="25px">&nbsp;</tsa:td>
			<tsa:td noWrap="nowrap" align="right"></tsa:td>
		</tsa:tr>

		</table>

		<br>

		<hr width="580px" align="center" color="#9999CC">

		<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="center" height="20px"><b>Inspection Criteria</b></td>
		</tr>
		<tr>
			<tsa:td>
		<table id="inspection" border="0" cellpadding="3" cellspacing="0" align="center">
				<tsa:tr>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td field="ins-inspCode"  noWrap="nowrap" align="center"><a href="javascript: browseSchedule('InspectionStd_inspectCode','inspectionCode'); void(0);"><tsa:label labelName="ins-code" defaultString="Code"/></a></tsa:td>
					<tsa:td field="ins-critNo" noWrap="nowrap" align="center"><a href="javascript: browseInspCrit(); void(0);"><tsa:label labelName="ins-critNo" defaultString="Criteria No"/></a></tsa:td>
					<tsa:td noWrap="nowrap" align="center"><tsa:label labelName="ins-critDescription" defaultString="Description"/>:</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
					<tsa:td>&nbsp;</tsa:td>
				</tsa:tr>
<%
	String s_set_row = "";
	int i_rowcount = 0;
	for ( i = 0; i < inspectionStdList.size(); i++)
	{
		s_set_row = "setCurrentRow(" + i_rowcount + "); standardCritCheck(" + i +");";
		InspectionStd insStd = (InspectionStd) inspectionStdList.get(i);
//		InspectionStd critDescription = (InspectionStd) inspMap.get("crit" + Integer.toString(i + 1));

		inspectType = HiltonUtility.ckNull((String) insStd.getInspectType());
		String inspectCode = HiltonUtility.ckNull((String) insStd.getInspectCode());
		String critNo = HiltonUtility.ckNull((String) insStd.getCritNo());
		String critDesc = HiltonUtility.ckNull((String) insStd.getCritText());
		String critDefaultFlag = HiltonUtility.ckNull((String) insStd.getDefaultFlag());
		String critTextEdit = "" ;
		if (critDefaultFlag == null) {
			critDefaultFlag = "N" ;
		} else if (critDefaultFlag.equalsIgnoreCase("Y")) {
			critTextEdit = "disabled" ;
		}
		//String defaultFlag = HiltonUtility.ckNull((String) critDesciption.getDefaultFlag());

%>
				<tsa:tr>
					<tsa:td id="space">&nbsp;</tsa:td>
					<tsa:td id="ins-code"><input type="text" name="InspectionStd_inspectCode" value="<%=inspectCode%>" size="15" maxlength="1" onchange="upperCase(this);" onFocus="<%=s_set_row %>">
						<tsa:hidden name="InspectionStd_defaultFlag" value="N"></tsa:hidden>
					</tsa:td>
					<tsa:td id="ins-critNo"><input type="text" name="InspectionStd_critNo" value="<%=critNo%>" size="15" maxlength="2" onFocus="<%=s_set_row %>" onchange="javascript: standardCritCheck(<%=i%>); void(0);"/></tsa:td>
					<tsa:td id="ins-critDesc"><textarea name="InspectionStd_critText" cols="70" rows="5"  <%=critTextEdit%> onfocus="<%=s_set_row%>"><%=critDesc%></textarea></tsa:td>
					<tsa:td id="space2">&nbsp;</tsa:td>
					<tsa:td id="ins_del_<%=i%>"><a href="javascript: if (confirm('Are you sure you wish to delete this Standard Criteria Code?')) { deleteMe(<%=i%>); } void(0);" tabindex="999"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></tsa:td>

				</tsa:tr>
	<% i_rowcount++;
	}
	%>
		</table>
				<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>" />
			</tsa:td>
		</tr>

				</table>

				<br>
			</td>
		</tr>
</table>

<br>
<table  border=0 cellspacing=0 cellpadding=0  width="780px">
		<tsa:tr>
		<td></td>
		<% if (status.compareTo("02") < 0) {  %>
			<tsa:td align="center"><a href="javascript: addNewCode(); void(0);"><font class="reset"><b>Add New Criteria</b></font></a></tsa:td>
			<%} %>
		</tsa:tr>
		<tsa:tr>
		<td></td>
			<tsa:td align="center"><hr width="580px"color="#9999CC"></hr></tsa:td>
		</tsa:tr>

</table>

<br>
<br>
<table border=0 cellpadding=1 cellspacing=0 width=480px height=75px align=center>
		<tr>
			<td width=100% valign=top>
				<table border=0>
				<tr>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;</td>
					<td>
						<select name="InspectionStd_status" onchange="setStatus();">
							<% if (status.compareTo("02") < 0) { %>
							<option value="01" <% if (inspectionStd.getStatus().equals("01")) { %> SELECTED <% } %>>Temporary</option>
							<% } %>
							<% if (status.compareTo("03") < 0) { %>
							<option value="02" <% if (inspectionStd.getStatus().equals("02")) { %> SELECTED <% } %>>Permanent</option>
							<% } %>
							<option value="03" <% if (inspectionStd.getStatus().equals("03")) { %> SELECTED <% } %>>Inactive</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right>Date Entered:&nbsp;</td>
					<td>
						<input type=text name="InspectionStd_dateEntered" size=15 maxlength=15 value="<%=HiltonUtility.getFormattedDate(inspectionStd.getDateEntered(), oid, userDateFormat)%>" disabled>
					</td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0>
				<tr>
					<td align=right><a href="javascript: browseLookup('InspectionStd_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
					<td>
						<input type=text name="InspectionStd_owner" tabindex=51 size=30 maxlength=15 value="<%=inspectionStd.getOwner()%>" onchange="upperCase(this); getNewInfo('user', this);">
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

<br>

<table border=0 cellpadding=0 cellspacing=0 width=780px>
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: if (validate()){submitThis()}">Save</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: submitCancel(); void(0);">Cancel</a></div></tsa:td>
</tsa:tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm=document.purchaseform;
	frm.browseName.value = "inspectionstd-admin";
	var maxRows = frm.as_maxrows.value;
	var myTable = document.getElementById("inspection");
	var TotalRows = myTable.rows.length - 1;
	var maxRows = frm.as_maxrows.value;
	var accRows = frm.as_rows.value;

	if (TotalRows <= 0)
	{
		TotalRows = 0;
		codes = "ABCDEFGHIJKLM" ;
		for (i = 0; i < codes.length; i++) {
			addNew(codes.charAt(i));
		}
	}
	setCurrentRow(0);



	function thisLoad()
	{
		f_StartIt();
		<% if (status.compareTo("01") > 0) { %>
			checkInputSettings();
			allowInputEdit(frm.InspectionStd_status, true);
		<% } %>
	}

	function browseInspCrit() {

		browseInspectionCriteriaNo('InspectionStd_critNo', frm.InspectionStd_inspectCode[currentRow].value);
	}

	function browse(x)
	{
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}


	function addNewCode() {
		addNew("") ;
	}

	function addNew(code)
	{
		//frm.deleteall.value = "FALSE";
		myTable = document.getElementById("inspection");
		TotalRows = TotalRows+1;
		count = myTable.rows.length;
		myRow = myTable.insertRow(count);
		maxRows++;

		myCell = myRow.insertCell();
		id = "space";
		myCell.id = id;
		myCell.innerHTML = "&nbsp;";


		myCell = myRow.insertCell();
		id = "ins-code";
		myCell.id = id;
		html = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionStd_inspectCode\" SIZE=\"15\" MAXLENGTH=\"1\" value=\"" + code + "\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");standardCritCheck(" + (count - 1) +");'>";
		html = html + "<INPUT TYPE=HIDDEN name=\"InspectionStd_defaultFlag\" value=\"N\">" ;
		myCell.innerHTML = html ;

		myCell = myRow.insertCell();
		id = "ins-critNo";
		myCell.id = id;
		myCell.innerHTML = "<INPUT TYPE=\"TEXT\" NAME=\"InspectionStd_critNo\" SIZE=\"15\" MAXLENGTH=\"1\" value=\"\" ONCHANGE=\"upperCase(this);\" ONFOCUS='setCurrentRow(" + (count - 1) + ");standardCritCheck(" + (count - 1) +");'>";

		myCell = myRow.insertCell();
		id = "ins-critDesc";
		myCell.id = id;
		myCell.innerHTML = "<TEXTAREA NAME=\"InspectionStd_critText\" COLS=\"70\" ROWS=\"5\"  ONFOCUS='setCurrentRow(" + (count - 1) + ");standardCritCheck(" + (count - 1) +");'>";
/*
		myCell = myRow.insertCell();
		id = "ins-defaultFlag";
		myCell.id = id;
		myCell.align = "CENTER";
		myCell.innerHTML = "<INPUT TYPE=\"checkbox\" NAME=\"c_checkbox\" VALUE=\"Y\" ONCLICK=\"setCheckbox(frm.InspectionStd_defaultFlag_" + (count - 1) + ", " + (count - 1) + ")\"/>" +
		"<INPUT TYPE=\"hidden\" NAME=\"InspectionStd_defaultFlag_" + (count - 1) +"\" VALUE=\"\"/>";
*/

		myCell = myRow.insertCell();
		id = "space2";
		myCell.id = id;
		myCell.innerHTML = "&nbsp;";

		myCell = myRow.insertCell();
		id = "insp_del_" + (count - 1);
		myCell.id = id;
		myCell.innerHTML = "<A href=\"javascript: if (confirm('Are you sure you wish to delete this Standard Criteria Code?')) { deleteMe(" + (count - 1) + "); } void(0)\" TABINDEX=\"999\"><IMG SRC=\"<%=contextPath%>/images/delete.gif\" ALT=\"Delete\" BORDER=\"0\"></A>";



		setCurrentRow(count - 1);
	}

	function setCurrentRow(row)
	{
		currentRow = row;
	}

	function standardCritCheck(row) {

		if (frm.InspectionStd_defaultFlag[row].value == "Y") {
			frm.InspectionStd_critText[row].disabled = true ;
		}
	}

	function deleteMe(row)
	{
		maxRows = maxRows - 1;	//needs to be set so that when validateForm is called it has the appropriate row count
		accRows = accRows = 1;


		myTable = document.getElementById("inspection");
		var currentRows = myTable.rows.length - 1;

		if (currentRows == 0)
		{
			return;
		}
		else if (currentRows > 1)
		{
			for (var i = row; i < (currentRows - 1); i++)
			{
				var fld1 = frm.InspectionStd_inspectCode[i + 1].value;
				var fld2 = frm.InspectionStd_critNo[i + 1].value;
				var fld3 = frm.InspectionStd_critText[i + 1].value;
				var fld4;
/*
				if (frm.c_checkbox[i + 1].checked){
					frm.c_checkbox[i].checked = true;
					fld4 = "Y";
					updateDefaultFlag(i, fld4);
				}else{
					frm.c_checkbox[i].checked = false;
					fld4 = 'N';
					updateDefaultFlag(i, fld4);
				}
*/

				frm.InspectionStd_inspectCode[i].value = fld1;
				frm.InspectionStd_critNo[i].value = fld2;

				frm.InspectionStd_critText[i].value = fld3;
			}
		}

		myTable = document.getElementById("inspection");
		myTable.deleteRow(currentRows);
	}

	function viewInpsectionCodes()
	{
	}

	function submitCancel() {
		frm.allowBrowse.value ="TRUE" ;
		browse('inspectionstd-admin');
	}

	function submitThis()
	{
		doSubmit('/browse/browse_sys_tables.jsp', 'InspectionStdUpdate;BrowseRetrieve');
	}

	function updateDefaultFlag(x, val)
	{
		var field1 = document.getElementById(x);
		field1.value = val;

	}

	function validate()
	{
		if ( frm.InspectionStd_description.value == ""){
			alert("You must fill in a standard description before saving.");
			return false;
		}

		return true;
	}


	function setDeleteAll()
	{
		frm.deleteall.value = "TRUE";
	}

// End Hide script -->
</SCRIPT>