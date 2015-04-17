<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	List xlsDataObjects = (List) request.getAttribute("xlsDataObjects");
%>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr>
  <td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tr>
          <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
        </tr>
        <tr>
          <td nowrap class="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "uploadXlsFile", "Upload XLS File")%></div>
          </td>
        </tr>
        </table>
      </td>
      <td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
      <td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td align="center">
    <font class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "importBudgetSummary", "Import Budget Summary")%></font>
  </td>
</tr>
<tr><td><br></td></tr>
<% 
  for (int k = 0; k < xlsDataObjects.size(); k++) 
  {
	  XlsData xlsData = (XlsData) xlsDataObjects.get(k);
  
  if (xlsData != null) 
  {
%>
<tr>
  <td width="100%" align="center" valign="top">
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 600px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
		<table border="0" cellpadding="0" cellspacing="0" width="600px">
			<tr class="mnav" height="18px">
			<% List labels =  xlsData.getLabels();
			
			   for (int i = 0; i < labels.size(); i++) {
			%>
				<td nowrap class="mnav" align="center"><%= HiltonUtility.ckNull((String) labels.get(i)) %></td>
				<td width="15px">&nbsp;</td>
			<% 	   
			   }
			%>
			</tr>
		<%
				List content =  xlsData.getContent();
				String classType = "summary";
				
				for (int i = 0; i < content.size(); i++)
				{
					List contentRow = (List) content.get(i);
		%>
				<tr class="<%=classType%>" height="20px">
		<%			for (int j = 0; j < contentRow.size(); j++)
					{
		%>
					<td class="<%=classType%>" align="left" valign="top"><%= HiltonUtility.ckNull((String) contentRow.get(j)) %></td>
					<td class="<%=classType%>" width="15px">&nbsp;</td>
		<%			}
		%>
				</tr>
		<%
					if (classType.equals("browseRow"))
					{
						classType = "summary";
					} else {
						classType = "browseRow";
					}
				}
		%>
			<tr ><td nowrap colspan="10"><hr class=browseHR></td></tr>
		</table>
	   </div>
  </td>
</tr>
<% } else 
   {
%>
<tr>
  <td width="100%" align="center" valign="top">
  <B><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "importBudgetEmpty", "Import XLS data is missing") %></B>
  </td>
</tr>
<% }
 } 
%>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="655px">
	<TR>
		<TD align="center"><a HREF="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Budget menu"></a></TD>
</TR>
</TABLE>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>