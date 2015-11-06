<%
	String	contextPath = "/puridiom";
	String	requestURLPath = "/puridiom";

// 	if (com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(contextPath)) {
// 		contextPath = "/puridiom";
// 	}
// 	else {
// 		if (contextPath.endsWith("/")) {
// 			contextPath = contextPath.substring(0, contextPath.lastIndexOf("/"));
// 		}
// 		if (!com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(requestURLPath)) {
// 			if (requestURLPath.indexOf(contextPath) > 0) {
// 				requestURLPath = requestURLPath.substring(0, requestURLPath.indexOf(contextPath) + contextPath.length());
// 			}
// 		}
// 	}
// 	if (com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(requestURLPath)) {
// 		requestURLPath = contextPath;
// 	}
%>
<script language='Javascript1.2' type="text/javascript">
<!--
	var contextPath = "<%=contextPath%>";
//-->
</script>
