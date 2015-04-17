<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<div id="validationrules" style="display:none;">
<!-- start rounded corners -->
<div id="container" style="width: 100%; align:left; margin:5;">
<b class="rtop">
  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
</b>
<table id=browseRows border="0" cellpadding="0" cellspacing="0" width=100%>
	<tsa:tr cssClass="browseHdr" height="18px">
		<tsa:td noWrap="nowrap" cssClass="browseHdr" align="center">&nbsp;</tsa:td>
		<tsa:td width="15px" cssClass="browseHdr">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" cssClass="browseHdr"><tsa:label labelName="rul-title-message" defaultString="Message" /></tsa:td>
		<tsa:td width="15px">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" cssClass="browseHdr" align="center"><tsa:label labelName="rul-title-severity" defaultString="Severity" /></tsa:td>
		<tsa:td width="15px">&nbsp;</tsa:td>
	</tsa:tr>
<%	List rulesList = rules.getRules();
		String classType = "summary";
		String severity = "";
		String invalidFields = "";
		int counter = 1;
		for (int i = 0; i < rulesList.size(); i++)
		{
			ValidationRule vRule = (ValidationRule)rulesList.get(i);
			if (vRule.isResult())
			{
				//we dont need to display if it passes
				continue;
			}
			if (vRule.getSeverity().equalsIgnoreCase("E"))
			{
				severity = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rul-value-error", "Error");
				if (!HiltonUtility.isEmpty(vRule.getFieldName())) {
					invalidFields += vRule.getFieldName() + ";";
				}
			}
			else if (vRule.getSeverity().equalsIgnoreCase("W"))
			{
				severity = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rul-value-warning", "Warning");
				if (!HiltonUtility.isEmpty(vRule.getFieldName())) {
					invalidFields += vRule.getFieldName() + ";";
				}
			}
%>
			<tsa:tr cssClass="<%=classType%>" height="20px">
				<tsa:td noWrap="nowrap" cssClass="<%=classType%>" align="right" valign="top">
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						<a href="<%=vRule.getMessageLink()%>" onclick="javascript: rowSelect='<%=counter%>';" onMouseOver="highlightRow(<%=counter%>);" onMouseOut="removeHighlight(<%=counter%>);">
					<%	}%>
						<%=counter%>.
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						</a>
					<%	}%>						
				</tsa:td>
				<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
				<tsa:td cssClass="<%=classType%>" align="left" valign="top">
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						<a href="<%=vRule.getMessageLink()%>" onclick="javascript: rowSelect='<%=counter%>';" onMouseOver="highlightRow(<%=counter%>);" onMouseOut="removeHighlight(<%=counter%>);">
					<%	}%>				
					<%=vRule.getMessage(oid, language)%>
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						</a>
					<%	}%>					
				</tsa:td>
				<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
				<tsa:td cssClass="<%=classType%>" noWrap="nowrap" align="center" valign="top">
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						<a href="<%=vRule.getMessageLink()%>" onclick="javascript: rowSelect='<%=counter%>';" onMouseOver="highlightRow(<%=counter%>);" onMouseOut="removeHighlight(<%=counter%>);">
					<%	}%>				
						<%=severity%>
					<%	if (vRule.getMessageLink() != null && vRule.getMessageLink().length() > 0) {%>
						</a>
					<%	}%>
				</tsa:td>
				<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
			</tsa:tr>
		<%if(classType.equalsIgnoreCase("browseRow"))
    		{
				classType = "summary";
    		}
			else if(classType.equalsIgnoreCase("summary"))
			{
				classType = "browseRow";
			}
			counter++;
		} %>
	<tsa:tr><tsa:td noWrap="nowrap" colspan="7"><hr class=browseHR></tsa:td></tsa:tr>
	<tsa:tr height="20px">
		<tsa:td noWrap="nowrap" colspan="7" align="right"><tsa:label labelName="req-validation-result" defaultString="Validation Result"></tsa:label>&nbsp;:&nbsp;<b>
				<%if(rules.getResult() == 1)
			{
			 out.print("Passed");
			}
			else if (rules.getResult() == 0)
			{
			 out.print("Passed With Warnings");
			}
			else if (rules.getResult() == -1)
			{
			 out.print("Failed");
			}%>!</b>
		</tsa:td>
	</tsa:tr>
</table>
<b class="rbottom">
  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
</b>
</div>
<!-- end rounded corners -->
</div>

<%	if (rules.getResult() == 1) { %>
			<div id="novalidationrules" style="display:none;">
			<table width=100%>
			<tsa:tr>
				<tsa:td align="center" valign="middle"><b><tsa:label labelName="req-all-validations-passed" defaultString="All validations passed!"></tsa:label></b></tsa:td>
			</tsa:tr>
			</table>
			</div>
<%	}%>

<tsa:hidden name="invalidFields" value="<%=invalidFields%>"/>