<table border=0 cellspacing=0 cellpadding=2>
	<tr <%=HtmlWriter.isVisible(oid, "xrefcombo-code1-" + XrefCombo_xrefType)%>>
		<td nowrap align="right">
		<%	if (DictionaryManager.isLink(oid, "xrefcombo-code1-" + XrefCombo_xrefType)) { %>
			<a href="javascript: browseAccountFld('XrefCombo_code1', 'AC01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code1-" + XrefCombo_xrefType, "Code1")%></a>:
		<%	} else { %>
			<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code1-" + XrefCombo_xrefType, "Code1")%>:
		<%	} %>
		</td>
		<td>
			<input type="text" name="XrefCombo_code1" size="30" maxlength="30" value="<%=xrefCombo.getCode1()%>">
		</td>
	</tr>
	<tr <%=HtmlWriter.isVisible(oid, "xrefcombo-code2-" + XrefCombo_xrefType)%>>
		<td nowrap align="right">
		<%	if (DictionaryManager.isLink(oid, "xrefcombo-code2-" + XrefCombo_xrefType)) { %>
			<a href="javascript: browseAccountFld('XrefCombo_code2', 'AC02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code2-" + XrefCombo_xrefType, "Code2")%></a>:
		<%	} else { %>
			<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code2-" + XrefCombo_xrefType, "Code2")%>:
		<%	} %>
		</td>
		<td>
			<input type="text" name="XrefCombo_code2" size="30" maxlength="30" value="<%=xrefCombo.getCode2()%>">
		</td>
	</tr>
	<tr <%=HtmlWriter.isVisible(oid, "xrefcombo-code3-" + XrefCombo_xrefType)%>>
		<td nowrap align="right">
		<%	if (DictionaryManager.isLink(oid, "xrefcombo-code3-" + XrefCombo_xrefType)) { %>
			<a href="javascript: browseAccountFld('XrefCombo_code3', 'AC03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code3-" + XrefCombo_xrefType, "Code3")%></a>:
		<%	} else { %>
			<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code3-" + XrefCombo_xrefType, "Code3")%>:
		<%	} %>
		</td>
		<td>
			<input type="text" name="XrefCombo_code3" size="30" maxlength="30" value="<%=xrefCombo.getCode3()%>">
		</td>
	</tr>
	<tr <%=HtmlWriter.isVisible(oid, "xrefcombo-code4-" + XrefCombo_xrefType)%>>
		<td nowrap align="right">
		<%	if (DictionaryManager.isLink(oid, "xrefcombo-code4-" + XrefCombo_xrefType)) { %>
			<a href="javascript: browseAccountFld('XrefCombo_code4', 'AC04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code4-" + XrefCombo_xrefType, "Code4")%></a>:
		<%	} else { %>
			<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code4-" + XrefCombo_xrefType, "Code4")%>:
		<%	} %>
		</td>
		<td>
			<input type="text" name="XrefCombo_code4" size="30" maxlength="30" value="<%=xrefCombo.getCode4()%>">
		</td>
	</tr>
	<tr <%=HtmlWriter.isVisible(oid, "xrefcombo-status")%>>
		<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-status", "Status")%>:</td>
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

<%
	String dateEntered = "";
	String dateExpires = "";
	if (action.equalsIgnoreCase("old")) {
		dateEntered = HiltonUtility.getFormattedDate(xrefCombo.getDateEntered(),oid, userDateFormat);
		dateExpires = HiltonUtility.getFormattedDate(xrefCombo.getDateExpires(),oid, userDateFormat);
	}
%>

<tsa:hidden name="XrefCombo_xrefInd" value="<%=xrefCombo.getXrefInd()%>"/>
<tsa:hidden name="XrefCombo_xrefAmt" value="<%=xrefCombo.getXrefAmt()%>"/>
<tsa:hidden name="XrefCombo_dateEntered" value="<%=dateEntered%>"/>
<tsa:hidden name="XrefCombo_dateExpires" value="<%=dateExpires%>"/>
<tsa:hidden name="XrefCombo_owner" value="<%=xrefCombo.getOwner()%>"/>