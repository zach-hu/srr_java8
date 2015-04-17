<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ include file="/system/footer.jsp" %>

<%
	int invPropertyCount = 0;
	List invPropertyList = (List)request.getAttribute("invPropertyList");
	if (invPropertyList != null && invPropertyList.size() > 0) {
		invPropertyCount = invPropertyList.size();
	}
%>

<SCRIPT value=JavaScript>
<!-- Hide script
	if (window.parent.frm.invPropertyCount) {
		window.parent.frm.invPropertyCount.value = <%=invPropertyCount%>;
	}
	window.top.hidePopWin();

// End Hide script -->
</SCRIPT>