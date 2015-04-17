<table border=0 cellspacing=0 cellpadding=2>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code1-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right">
	 	<% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code1-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	 	<a href="javascript: browseLookup('XrefCombo_code1', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code1-"+XrefCombo_xrefType+"-source", "EMPTY")%>'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code1-"+XrefCombo_xrefType, "Code1")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code1-"+XrefCombo_xrefType, "Code1")%> Code in the box on the right.">
	 	<% } %>
	 	<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code1-"+XrefCombo_xrefType, "Code1")%></a></td>
	   <td><input type="text" name="XrefCombo_code1" size="30" maxlength="30" value="<%=xrefCombo.getCode1()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code2-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right">
	 	<% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code2-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	 	<a href="javascript: browseStd('XrefCombo_code2', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code2-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code2-"+XrefCombo_xrefType, "Code2")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code2-"+XrefCombo_xrefType, "Code2")%> Code in the box on the right.">
	 	<% } %>
	 	<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code2-"+XrefCombo_xrefType, "Code2")%></a></td>
	   <td><input type="text" name="XrefCombo_code2" size="30" maxlength="30" value="<%=xrefCombo.getCode2()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code3-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right">
	 	<% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	 	<% if (XrefCombo_xrefType.equalsIgnoreCase("CMOD")) { %>
	 	<a href="javascript: browseLookup('XrefCombo_code3', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType, "Code3")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType, "Code3")%> Code in the box on the right.">
	 	<% } else { %>
	 	<a href="javascript: browseStd('XrefCombo_code3', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType, "Code3")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType, "Code3")%> Code in the box on the right.">
	 	<% } %>
	 	<% } %>
	 	<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code3-"+XrefCombo_xrefType, "Code3")%></a></td>
	   <td><input type="text" name="XrefCombo_code3" size="30" maxlength="30" value="<%=xrefCombo.getCode3()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code4-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right">
	   <% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code4-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	   <a href="javascript: browseStd('XrefCombo_code4', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code4-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code4-"+XrefCombo_xrefType, "Code4")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code4-"+XrefCombo_xrefType, "Code4")%> Code in the box on the right.">
	   <% } %>
	   <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code4-"+XrefCombo_xrefType, "Code4")%></a></td>
	   <td><input type="text" name="XrefCombo_code4" size="30" maxlength="30" value="<%=xrefCombo.getCode4()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code5-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right">
	 	<% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code5-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	    <a href="javascript: browseStd('XrefCombo_code5', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code5-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code5-"+XrefCombo_xrefType, "Code5")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code5-"+XrefCombo_xrefType, "Code5")%> Code in the box on the right.">
	    <% } %>
	   <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code5-"+XrefCombo_xrefType, "Code5")%></td>
	   <td><input type="text" name="XrefCombo_code5" size="30" maxlength="30" value="<%=xrefCombo.getCode5()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code6-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right">
	    <% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code6-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	    <a href="javascript: browseStd('XrefCombo_code6', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code6-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code6-"+XrefCombo_xrefType, "Code6")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code6-"+XrefCombo_xrefType, "Code6")%> Code in the box on the right.">
	    <% } %>
	   <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code6-"+XrefCombo_xrefType, "Code6")%></td>
	   <td><input type="text" name="XrefCombo_code6" size="30" maxlength="30" value="<%=xrefCombo.getCode6()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code7-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right">
	    <% if (!((String) DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code7-"+XrefCombo_xrefType+"-source", "EMPTY")).equalsIgnoreCase("EMPTY")) { %>
	    <a href="javascript: browseStd('XrefCombo_code7', '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code7-"+XrefCombo_xrefType+"-source", "EMPTY")%>',false); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code7-"+XrefCombo_xrefType, "Code7")%> Code for this requisition or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code7-"+XrefCombo_xrefType, "Code7")%> Code in the box on the right.">
	    <% } %>
	   <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code7-"+XrefCombo_xrefType, "Code7")%></td>
	   <td><input type="text" name="XrefCombo_code7" size="30" maxlength="30" value="<%=xrefCombo.getCode7()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code8-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code8-"+XrefCombo_xrefType, "Code8")%></td>
	   <td><input type="text" name="XrefCombo_code8" size="30" maxlength="30" value="<%=xrefCombo.getCode8()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code9-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code9-"+XrefCombo_xrefType, "Code9")%></td>
	   <td><input type="text" name="XrefCombo_code9" size="30" maxlength="30" value="<%=xrefCombo.getCode9()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code10-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code10-"+XrefCombo_xrefType, "Code10")%></td>
	   <td><input type="text" name="XrefCombo_code10" size="30" maxlength="30" value="<%=xrefCombo.getCode10()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code11-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code11-"+XrefCombo_xrefType, "Code11")%></td>
	   <td><input type="text" name="XrefCombo_code11" size="30" maxlength="30" value="<%=xrefCombo.getCode11()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code12-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code12-"+XrefCombo_xrefType, "Code12")%></td>
	   <td><input type="text" name="XrefCombo_code12" size="30" maxlength="30" value="<%=xrefCombo.getCode12()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code13-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code13-"+XrefCombo_xrefType, "Code13")%></td>
	   <td><input type="text" name="XrefCombo_code13" size="30" maxlength="30" value="<%=xrefCombo.getCode13()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code14-"+XrefCombo_xrefType)%>>
	   <td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code14-"+XrefCombo_xrefType, "Code14")%></td>
	   <td><input type="text" name="XrefCombo_code14" size="30" maxlength="30" value="<%=xrefCombo.getCode14()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "autacc-code15-"+XrefCombo_xrefType)%>>
	 	<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "autacc-code15-"+XrefCombo_xrefType, "Code15")%></td>
	   <td><input type="text" name="XrefCombo_code15" size="30" maxlength="30" value="<%=xrefCombo.getCode15()%>" onChange="javascript: valuesCheck(this,'<%=XrefCombo_xrefType%>'); void(0);"></td>
	 </tr>
	 <tr <%=HtmlWriter.isVisible(oid, "xrefcombo-status")%>>
  		<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-status", "Status")%></td>
  		<%String opStatus = xrefCombo.getStatus();%>
    	<td>
    	<select name="XrefCombo_status" style="width: 100px">
          <option value="01" <% if (opStatus.equalsIgnoreCase("01")) {%> selected<%}%>> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "temporary", "Temporary")%></option>
          <option value="02" <% if (opStatus.equalsIgnoreCase("02")) {%> selected<%}%>> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "permanent", "Permanent")%></option>
          <option value="03" <% if (opStatus.equalsIgnoreCase("03")) {%> selected<%}%>> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inactive", "Inactive")%></option>
          </select>
    	</td>
  	 </tr>
</table>

<tsa:hidden name="XrefCombo_xrefInd" value="<%=xrefCombo.getXrefInd()%>"/>
<tsa:hidden name="XrefCombo_xrefAmt" value="<%=xrefCombo.getXrefAmt()%>"/>
<tsa:hidden name="XrefCombo_dateEntered" value="<% if (action.equalsIgnoreCase(\"old\")) { %><%=HiltonUtility.getFormattedDate(xrefCombo.getDateEntered(),oid, userDateFormat)%><% } %>"/>
<tsa:hidden name="XrefCombo_dateExpires" value="<% if (action.equalsIgnoreCase(\"old\")) { %><%=HiltonUtility.getFormattedDate(xrefCombo.getDateExpires(),oid, userDateFormat)%><% } %>"/>
<tsa:hidden name="XrefCombo_owner" value="<%=xrefCombo.getOwner()%>"/>