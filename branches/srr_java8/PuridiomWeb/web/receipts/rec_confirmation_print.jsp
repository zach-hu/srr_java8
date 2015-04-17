<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

<%	if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")) {%>
		receiveByItem();
<%	} else if (receiptMethod.equalsIgnoreCase("ReceiveFromNothing")) {%>
		receiveFromNothing() ;
<%	} else if (receiptMethod.equalsIgnoreCase("FinalizeReceipt")) {%>
		finalizeReceipts();
<%	} else if (receiptMethod.equalsIgnoreCase("Adjustment")) {%>
		adjustReceipts();
<%	} else if (receiptMethod.equalsIgnoreCase("Return")) {%>
		returnAgainstReceipts();
<%	}%>

// End Hide script -->
</SCRIPT>