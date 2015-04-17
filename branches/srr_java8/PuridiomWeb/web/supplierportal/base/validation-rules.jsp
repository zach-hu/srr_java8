<div id="validationrules" style="display:none;">
<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 500px; height: 200px; align:center; overflow-y:visible; overflow-x:auto;">
<table border="0" cellpadding="0" cellspacing="0" width="485px">
	<tr class="mnav" height="18px">
		<td nowrap class="mnav" align="center">&nbsp;</td>
		<td width="15px" class="mnav">&nbsp;</td>
		<td nowrap class="mnav">Message</td>
		<td width="15px">&nbsp;</td>
		<td nowrap class="mnav" align="center">Severity</td>
		<td width="15px">&nbsp;</td>
	</tr>
<%	List rulesList = rules.getRules();
		String classType = "summary";
		String severity = "";
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
				severity = "Error";
			}
			else if (vRule.getSeverity().equalsIgnoreCase("W"))
			{
				severity = "Warning";
			}
%>
			<tr class="<%=classType%>" height="20px">
				<td nowrap class="<%=classType%>" align="right" valign="top"><%=counter%>.</td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<td class="<%=classType%>" align="left" valign="top"><%=vRule.getMessage()%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<td class="<%=classType%>" nowrap align="center" valign="top"><%=severity%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
			</tr>
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
	<tr ><td nowrap colspan="7"><hr class=browseHR></td></tr>
	<tr height="20px">
		<td nowrap colspan="7" align="right">Validation Result&nbsp;:&nbsp;<b>
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
		</td>
	</tr>
</table>
</div>
</div>