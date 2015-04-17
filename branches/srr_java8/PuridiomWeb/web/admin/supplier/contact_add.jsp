<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="java.math.*" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<%
	String s_status = "2000";
%>
<tsa:hidden name="allowBrowse" value="true"/>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
				<table border=0 width=100%>
<tsa:hidden name="Vendor_vendorId" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Contact_vendorId" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Address_addressType" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"/>
<tsa:hidden name="Contact_contactCode" tabindex=50 size=15 maxlength=15 value=""/>
<tsa:hidden name="Contact_contactType" tabindex=50 size=15 maxlength=15 value="ALTERNATIVE"/>


				<tr valign=bottom>
					<td></td>
					<td align=right>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "salutation", "Salutation")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "firstName", "First Name")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					</td>
				  <td align=left>&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "middleIntial", "Middle Intial")%>&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "lastName", "Last Name")%></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right>
						<input type=text name="Contact_salutation" tabindex=50 size=5 maxlength=5 value="">
						&nbsp;&nbsp;&nbsp;
						<input type=text name="Contact_firstName" tabindex=50 size=15 maxlength=20 value="">

					</td>
					<td align=left>
						&nbsp;&nbsp;&nbsp;
						<input type=text name="Contact_middleInit" tabindex=50 size=5 maxlength=2 value="">
						&nbsp;&nbsp;&nbsp;
						<input type=text name="Contact_lastName" tabindex=50 size=15 maxlength=20 value="">
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "title", "Title")%>:</td>
					<td align=left><input type=text name="Contact_contactTitle" tabindex=50 size=15 maxlength=30 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "phone", "Phone")%>:</td>
					<td align=left><input type=text name="Contact_phoneNumber" tabindex=50 size=15 maxlength=30 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fax", "Fax")%>:</td>
					<td align=left><input type=text name="Contact_faxNumber" tabindex=50 size=15 maxlength=30 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enail", "Email")%>:</td>
					<td align=left><input type=text name="Contact_emailAddr" tabindex=50 size=15 maxlength=50 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "info1", "Info 1")%>:</td>
					<td align=left><input type=text name="Contact_info1" tabindex=50 size=15 maxlength=50 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "info2", "Info 2")%>:</td>
					<td  align=left><input type=text name="Contact_info2" tabindex=50 size=15 maxlength=50 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%>:</td>
					<td align=left><input type=text name="Contact_info3" tabindex=50 size=15 maxlength=50 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "info4", "Info 4")%>:</td>
				  <td align=left><input type=text name="Contact_info4" tabindex=50 size=15 maxlength=50 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mainContact", "Main Contact")%>:&nbsp;<INPUT TYPE="checkbox" NAME="c_checkbox" ONCLICK="javascript: alert('Not available yet.');">
						<tsa:hidden name="Contact_default" tabindex=50 size=15 maxlength=15 value=""/>
					</td>
					<td align=left>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:&nbsp;
						<select name="Contact_status">
							<option value="01" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
							<option value="02" SELECTED><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
							<option value="03" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
						</select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addressCode", "Address Code")%>:</td>
					<td align=left><input type=text name="Contact_addressCode" tabindex=50 size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(Vendor_vendorId)}"></td>
					<td></td>
					<td ></td>
				</tr>
				<tr>
					<td></td>
					<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "conatctPassword", "Conatct Password")%>:</td>
				  <td align=left><input type=password name="Contact_contactPassword" autocomplete="off" tabindex=50 size=15 maxlength=12 value=""></td>
					<td></td>
					<td></td>
				</tr>
				<tr><td colspan=5><br></td></tr>
			</table>
	</td>
</tr>
</table>


<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<% if (( (String)request.getAttribute("pageFrom")).indexOf("contact") >=0) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactAdd;ContactAddressRetrieveBySupplier'); void(0);">Save</a></div></td>
<% } else if (( (String)request.getAttribute("pageFrom")).indexOf("address") >=0) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/address_add.jsp', 'ContactAdd'); void(0);">Save</a></div></td>
<% } %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/admin/supplier/supplier_contact_list.jsp', 'ContactAddressRetrieveBySupplier'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();

	//setTableHeights();

	function thisLoad()
	{
		f_StartIt();
<%	if (Integer.valueOf(s_status).intValue() >= 2005 && Integer.valueOf(s_status).intValue() != 2015) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
	}


// End Hide script -->
</SCRIPT>