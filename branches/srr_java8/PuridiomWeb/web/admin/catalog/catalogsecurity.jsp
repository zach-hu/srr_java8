<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%

	String icHeader = (String) request.getAttribute("CatalogSecurity_icHeader");
	String catalogId = (String) request.getAttribute("CatalogSecurity_catalogId");
	String itemNumber = (String) request.getAttribute("CatalogSecurity_itemNumber");
	String title = HiltonUtility.ckNull((String) request.getAttribute("Catalog_title"));
	List catalogSecurityList = HiltonUtility.ckNull((List) request.getAttribute("catalogSecurityList"));
	int numRows = 0;
	if(catalogSecurityList!= null){
		numRows = catalogSecurityList.size();
	}

	boolean bAllowEdit = true;

	String fromPage = (String) request.getAttribute("fromPage");
	String s_returnPage = "";
	String s_current_method = "";

	if(fromPage.equalsIgnoreCase("catalogjsp"))
	{
		s_returnPage = "/admin/catalog/catalog.jsp";
	}
	else
	{
		s_returnPage = "/admin/catalog/catalog_item.jsp";
	}

	String s_current_page = "/admin/catalog/catalogsecurity.jsp";
%>

<tsa:hidden name="icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="catalogId" value="<%=catalogId%>"/>
<tsa:hidden name="itemNumber" value="<%=itemNumber%>"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="deleteall" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogSecurity", "Catalog Security")%></div>
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
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="60%"><b>Catalog:</b>&nbsp;</td>
			 <td align=left width="40%"><%=title%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
	<tr>
		<td align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="<%=formWidth%>">
			<tr>
				<td>
				<table border="0" cellspacing="0" cellpadding="0" width="<%=formWidth%>">
					<tr><TD NOWRAP><tsa:hidden name="as_rows" value="<%=numRows%>"/></TD></tr>
					<tr>
						<td width="100%" align="left" valign="top">
						<table border="0" cellspacing="0" cellpadding=4 width="100%" class="browseRow">
							<tr align="right">					
								<td  colspan="8" nowrap width=30% class=formType>
									<a href="javascript: browse('cat_pool'); void(0);" class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "CATPOOL", "Catalog Pools")%></a>
								</td>
							</tr>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "securityType")%> width="100px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "securityType", "Type")%></td>
								<td <%=HtmlWriter.isVisible(oid, "accessID")%> width="100px"><a href="javascript: browseAccess(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accessID", "Access ID")%></a></td>
								<td <%=HtmlWriter.isVisible(oid, "accessIDDescription")%> width="200px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "accessIDDescription", "Access ID Description")%></td>
								<td <%=HtmlWriter.isVisible(oid, "owner")%> width="100px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "owner", "Owner")%></td>
								<td <%=HtmlWriter.isVisible(oid, "dateEntered")%> width="100px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateEntered", "Date Entered")%></td>
								<td <%=HtmlWriter.isVisible(oid, "dateChanged")%> width="100px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateChanged", "Date Changed")%></td>
								<td <%=HtmlWriter.isVisible(oid, "lastChangedBy")%> width="100px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "lastChangedBy", "Last Changed By")%></td>
								<td width="15px">&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td width="100%" align="left" valign="top">
						<table id="security" border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
							<%
								int i_rowcount = 0;
								if (catalogSecurityList != null && catalogSecurityList.size() > 0)
								{
									for (int i = 0; i < catalogSecurityList.size(); i++)
									{
										CatalogSecurity catalogSecurity = (CatalogSecurity) catalogSecurityList.get(i);
										String catalogSecurityOwner = UserManager.getInstance().getUserDisplayName(oid, catalogSecurity.getOwner());
										String catalogSecurityLastChangedBy = UserManager.getInstance().getUserDisplayName(oid, catalogSecurity.getLastChangedBy());
							%>
							<tr>
								<td <%=HtmlWriter.isVisible(oid, "securityType")%> width="100px">
								<tsa:hidden name="CatalogSecurity_catalogId" value="<%=catalogId%>"/><tsa:hidden name="CatalogSecurity_itemNumber" value="<%=itemNumber%>"/>
								<select name="CatalogSecurity_accessType" onfocus="setCurrentRow(<%=i%>);">
									<option value="UI" <% if (catalogSecurity.getAccessType().equals("UI")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user", "User")%></option>
									<option value="PI" <% if (catalogSecurity.getAccessType().equals("PI")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userPool", "Pool")%></option>
									<option value="UD" <% if (catalogSecurity.getAccessType().equals("UD")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userdepartment", "Department")%></option>
									<option value="UU1" <% if (catalogSecurity.getAccessType().equals("UU1")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udf1", "Ship To Code")%></option>
									<option value="UU2" <% if (catalogSecurity.getAccessType().equals("UU2")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udf2", "Location")%></option>
									<option value="UU3" <% if (catalogSecurity.getAccessType().equals("UU3")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udf3", "Region")%></option>
									<option value="UU4" <% if (catalogSecurity.getAccessType().equals("UU4")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udf4", "Name Udf4")%></option>
									<option value="UU5" <% if (catalogSecurity.getAccessType().equals("UU5")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-udf5", "Name Udf5")%></option>
									<option value="UL" <% if (catalogSecurity.getAccessType().equals("UL")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userlocation", "User Location")%></option>
									<option value="UT" <% if (catalogSecurity.getAccessType().equals("UT")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-user-type", "User Type")%></option>
									<option value="UR" <% if (catalogSecurity.getAccessType().equals("UR")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-userrequisitioner", "Requisitioner")%></option>
									<option value="HU" <% if (catalogSecurity.getAccessType().equals("HU")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-header-udf", "Header UDF1")%></option>
									<option value="LU" <% if (catalogSecurity.getAccessType().equals("LU")) { %> SELECTED <% } %>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cs-lineUdf", "Line UDF2")%></option>
								</select></td>
								<td <%=HtmlWriter.isVisible(oid, "accessID")%> width="100px"><input type="text" name="CatalogSecurity_accessId" size="20" value="<%=catalogSecurity.getAccessId()%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"> <%--input type="text" name="asAccessName" value="<%=accessName%>" onChange="changeAccessId();upperCase(this);" onfocus="setCurrentRow(<%=i%>);"--%></td>
								<td <%=HtmlWriter.isVisible(oid, "accessIDDescription")%> width="200px"><input type="text" name="CatalogSecurity_accessDescription" size="40" value="<%=catalogSecurity.getAccessDescription()%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);" disabled> <%--input type="text" name="asAccessName" value="<%=accessName%>" onChange="changeAccessId();upperCase(this);" onfocus="setCurrentRow(<%=i%>);"--%></td>
								<td <%=HtmlWriter.isVisible(oid, "owner")%> width="100px">
									<input type="text" name="as_catalogSecurityOwner" size="20" value="<%=catalogSecurityOwner%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);" disabled> <%--input type="text" name="asAccessName" value="<%=accessName%>" onChange="changeAccessId();upperCase(this);" onfocus="setCurrentRow(<%=i%>);"--%>
									<input type="hidden" name="CatalogSecurity_owner" value="<%=catalogSecurity.getOwner()%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"/> <%--input type="text" name="asAccessName" value="<%=accessName%>" onChange="changeAccessId();upperCase(this);" onfocus="setCurrentRow(<%=i%>);"--%></td>
								<td <%=HtmlWriter.isVisible(oid, "dateEntered")%> width="100px"><input type="text" name="CatalogSecurity_dateEntered" size="20" value="<%=HiltonUtility.getFormattedDate(catalogSecurity.getDateEntered(), oid, userDateFormat)%>" disabled onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
								<td <%=HtmlWriter.isVisible(oid, "dateChanged")%> width="100px"><input type="text" name="CatalogSecurity_dateChanged" size="20" value="<%=HiltonUtility.getFormattedDate(catalogSecurity.getDateChanged(), oid, userDateFormat)%>" disabled onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"></td>
								<td <%=HtmlWriter.isVisible(oid, "lastChangedBy")%> width="100px">
									<input type="text" name="as_catalogSecurityLastChangedBy" size="20" value="<%=catalogSecurityLastChangedBy%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);" disabled><%--input type="hidden" name="CatalogSecurity_lastChangedBy" size="20" value="<%=poSecurity.getLastChangedBy()%>"--%>
									<input type="hidden" name="CatalogSecurity_lastChangedBy" value="<%=catalogSecurity.getLastChangedBy()%>" onChange="upperCase(this);" onfocus="setCurrentRow(<%=i%>);"/><%--input type="hidden" name="CatalogSecurity_lastChangedBy" size="20" value="<%=poSecurity.getLastChangedBy()%>"--%></td>
								<%
									if (bAllowEdit)
											{
								%>
								<td id=delete_ <%=i%> width="15px"><a href="javascript: if (confirmDelete()) { deleteMe(<%=i%>); void(0); } "><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></a></td>
								<%
									} else
											{
								%>
								<td id=delete_ <%=i%> width="15px"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0></td>
								<%
									}
								i_rowcount++;
								%>
							</tr>
							<%
								}
								}
							%>
							<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/>
						</table>
						</td>
					</tr>
					<%
						if (bAllowEdit)
						{
					%>
					<tr>
						<td colspan="6"><br>
						<table border=0 cellspacing=0 cellpadding=2 width=600px align=center>
							<tr>
								<td nowrap>&nbsp;<a href="javascript: addNew(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "addNew", "Add new")%></B></font></a></td>
								<td>&nbsp;</td>
								<td align=right nowrap><a href="javascript: deleteAll(); void(0);"><font class="reset"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "deleteAll", "Delete all")%></B></font></a>&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
					<%
						}
					%>

				</table>
				</td>
			</tr>

		</table>
		</td>
	</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formWidth%>>
	<tr>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: saveCatalogSecurity(); void(0);">Save</a></div></td>
		<td width=50% align=center><div id="pxbutton"><a href="javascript: returnCatalog(); void(0);">Return</a></div></td>
	</tr>
</table>
<%@ include file="/admin/catalog/catalogsecurity_rows.jsp"%>
<%@ include file="/system/footer.jsp"%>


<table border=0 cellpadding=0 cellspacing=0 width=680px>
</table>


<SCRIPT value=JavaScript>
<!-- Hide script

 	var catalog = '<%=catalogId %>';

 	function saveCatalogSecurity()
	{
		if ( !validateFormCatalogSecurity() )
		{
			return false;
		}
    	var newInputField = "<input type='hidden' name='catalogId' value='" + "<%=catalogId %>" + "'>" +
     						"<input type='hidden' name='Catalog_catalogId' value='" + "<%=catalogId %>" + "'>" +
     						"<input type='hidden' name='CatalogItem_catalogId' value='" + "<%=catalogId %>" + "'>" ;

     	<% if(fromPage.equalsIgnoreCase("catalogjsp")) { %>
     		setHiddenFields(newInputField);
	 		doSubmit('/admin/catalog/catalog.jsp', 'CatalogSecurityUpdateLines;CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	 	<% } else if(fromPage.equalsIgnoreCase("catalogitemjsp")) { %>
	 		newInputField = newInputField + "<input type='hidden' name='CatalogItem_itemNumber' value='" + "<%=itemNumber %>" + "'>" ;
	 		setHiddenFields(newInputField);
	 		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogSecurityUpdateLines;CatalogItemRetrieveById');
	 	<% } %>
	 }

  function returnCatalog()
  {
     	var newInputField = "<input type='hidden' name='catalogId' value='" + "<%=catalogId %>" + "'>" +
     						"<input type='hidden' name='Catalog_catalogId' value='" + "<%=catalogId %>" + "'>" +
     						"<input type='hidden' name='CatalogItem_catalogId' value='" + "<%=catalogId %>" + "'>" ;
     	<% if(fromPage.equalsIgnoreCase("catalogjsp")) { %>
     		setHiddenFields(newInputField);
	 		doSubmit('/admin/catalog/catalog.jsp', 'CatalogRetrieveById;CatalogItemRetrieveCountByCatalog');
	 	<% } else if(fromPage.equalsIgnoreCase("catalogitemjsp")) { %>
	 		newInputField = newInputField + "<input type='hidden' name='CatalogItem_itemNumber' value='" + "<%=itemNumber %>" + "'>" ;
	 		setHiddenFields(newInputField);
	 		doSubmit('/admin/catalog/catalog_item.jsp', 'CatalogItemRetrieveById');
	 	<% } %>
  }
// End Hide script -->
</SCRIPT>
