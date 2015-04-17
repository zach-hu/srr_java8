<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.util.*" %>
<%
	String key;
	Map types = (Map) request.getAttribute("typesList");
	List keys = new ArrayList(types.keySet());
%>
<div id="typeOptions" style="border: 1px solid #C0C0C0; background-color: #F5F5F5; height: 1.2em; padding: 2px 0px 2px 2px;">
<!-- span style="float: left;">Type</span-->
<a href="javascript:parent.hideTypesFrame();" style="float: right;"><img src="<%=contextPath%>/images/bar_close.gif" alt="Close." valign="bottom" border="0"></a>
</div>
<select name="type" id="type" size="<%= keys.size() %>" onchange="javascript:loadValue(this)" style="width: 100%;">
<%
for (Iterator iter = keys.iterator(); iter.hasNext();)
{
	key = (String) iter.next();
%>
	<option value="<%= key %>"><%= types.get(key) %></option>
<%
}
%>
</select>
</form>
</body>
</html>
<script type="text/javascript">
<!--
	function thisLoadPopup() {
		parent.resizeTypeOptions(document.getElementById('type').length + 1);
	}

	function loadValue(optionList) {
		var selectText = optionList.options[optionList.selectedIndex].text;
		parent.assignValue(optionList.value, selectText);
	}

	function unloadValue() {
		document.getElementById('type').selectedIndex = -1;
	}
//-->
</script>