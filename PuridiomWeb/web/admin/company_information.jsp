<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>My Account - Company Name & Address</div>
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

<table border=0 cellspacing=0 cellpadding=2 width=<%=formEntryWidth%>>
<tr>
	<td width="30%" align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "companyName", "Company Name")%>:<b></td>
	<td width="70%">
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="NAME"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Name", "")%>" tabindex=1 size=65 maxlength=100 taborder=1/>
	</td>
</tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "address", "Address")%>:</b></td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="ADDR1"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Addr1", "")%>" tabindex=2 size=65 maxlength=100 taborder=2>
	</td>
</tr>
<tr>
	<td align=right>&nbsp;</td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="ADDR2"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Addr2", "")%>" tabindex=2 size=65 maxlength=100 taborder=2>
	</td>
</tr>
<tr>
	<td align=right>&nbsp;</td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="ADDR3"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Addr3", "")%>" tabindex=2 size=65 maxlength=100 taborder=2>
	</td>
</tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "city", "City")%>:</b></td>
	<td nowrap>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="CITY"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "City", "")%>" tabindex=2 size=35 maxlength=30 taborder=2>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="STATE"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "State", "")%>" tabindex=2 size=5 maxlength=5 taborder=2>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="POSTALCODE"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "PostalCode", "")%>" tabindex=2 size=14 maxlength=15 taborder=2>
	</td>
</tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country")%>:</b></td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="COUNTRY"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Country", "")%>" tabindex=2 size=35 maxlength=100 taborder=2>
	</td>
</tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "phone", "Phone")%>:</b></td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="PHONE"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Phone", "")%>" tabindex=2 size=35 maxlength=100 taborder=2>
	</td>
</tr>
<tr>
	<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fax", "Fax")%>:</b></td>
	<td>
		<tsa:hidden name="Property_section" value="COMPANY"/>
		<tsa:hidden name="Property_property" value="FAX"/>
		<input type=text name="Property_value" value="<%=propertiesManager.getProperty("COMPANY", "Fax", "")%>" tabindex=2 size=35 maxlength=100 taborder=2>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
	<%	if (isXpress) {%>

			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/user/user_account_info.jsp', 'PropertyUpdate;PackagePricingRetrieveAll'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: viewMyAccount(); void(0);">Cancel</a></div></td>
	<% } else { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'PropertyUpdate'); void(0);">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
	<% } %>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/company_information.jsp", "DoNothing", "Company Information");

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>