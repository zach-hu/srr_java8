<table border="0" cellspacing="0" cellpadding="1" width="100%">
	<tr>
  		<td nowrap align="right"><b>Supplier:</b></td>
  		<td width=200px><%=vendor.getVendorId()%></td>
	</tr>
	<tr>
   		<td nowrap align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "vendorName", "Supplier Name")%>:</b></td>
   		<td width=200px><%=vendor.getVendorName() %></td>
	</tr>
 </table>
 <table cellpadding="0" cellspacing="0" border="0">
	<tr>
      <td width=1000px height="1px" class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height=2px class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
</table>