<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ page import="com.tsa.puridiom.entity.PoLine" %>
<%
	PoLine poLine = (PoLine) request.getAttribute("poLine");

	if (poLine == null) {
		poLine = new PoLine();
	}
%>
<html>
<head></head>

<body>

<script language="JavaScript1.2">
<!--  hide script from old browsers

	parent.addNewItem("<%=poLine.getIcPoLine()%>");

	parent.hideArea("getInfoFrame");

// end hiding contents -->
</script>

</body>

</html>