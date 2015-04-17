<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<table width="100%">
<tr><td align="center">Working <img src="images/processing.gif" border="0"></td></tr>
</table>

<script>
var frm = document.purchaseform;
browse('asset');
</script>
