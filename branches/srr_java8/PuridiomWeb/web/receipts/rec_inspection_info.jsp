<div style="margin-top: 10px;margin-left: 10px;font-size: 10px;">
<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "highlightedFldMessage", "Fields highlighted in yellow are required.",true)%>
</div>
<!-- variable necessary for validation -->
<% String actionStep = (String)request.getAttribute("actionStep"); %>
<tsa:hidden name="actionStep" value="<%=actionStep%>"/>