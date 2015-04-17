<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.List.*" %>

<%
	List stdAcctList = (List) request.getAttribute("stdAccountList");
	if (stdAcctList == null)
	{
		stdAcctList = new ArrayList();
	}
	String	s_acct_type = (String) request.getAttribute("formType");
	String	s_alloc_method = (String) request.getAttribute("allocMethod");
	int i_size = stdAcctList.size();

	if (s_acct_type == null)
	{
		s_acct_type = "";
	}
	if (s_acct_type.equalsIgnoreCase("CATALOG"))
	{
		s_acct_type = "CAT";
	}
	if (s_alloc_method == null)
	{
		s_alloc_method = "";
	}
%>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	window.parent.focus();
	frm = document.purchaseform;
	var myTable = window.parent.document.getElementById("accounts");
	var currentRow = myTable.rows.length - 1;

<%	for (int i = 0; i < i_size; i++)
		{
			Account account = (Account) stdAcctList.get(i);
%>
	window.parent.addNew();

	if (currentRow == 0)
	{
		window.parent.frm.Account_accountType.value = "<%=s_acct_type%>";
		window.parent.frm.Account_allocMethod.value = "<%=s_alloc_method%>";
		window.parent.frm.Account_fld1.value = "<%=account.getFld1()%>";
		window.parent.frm.Account_fld2.value = "<%=account.getFld2()%>";
		window.parent.frm.Account_fld3.value = "<%=account.getFld3()%>";
		window.parent.frm.Account_fld4.value = "<%=account.getFld4()%>";
		window.parent.frm.Account_fld5.value = "<%=account.getFld5()%>";
		window.parent.frm.Account_fld6.value = "<%=account.getFld6()%>";
		window.parent.frm.Account_fld7.value = "<%=account.getFld7()%>";
		window.parent.frm.Account_fld8.value = "<%=account.getFld8()%>";
		window.parent.frm.Account_fld9.value = "<%=account.getFld9()%>";
		window.parent.frm.Account_fld10.value = "<%=account.getFld10()%>";
		window.parent.frm.Account_fld11.value = "<%=account.getFld11()%>";
		window.parent.frm.Account_fld12.value = "<%=account.getFld12()%>";
		window.parent.frm.Account_fld13.value = "<%=account.getFld13()%>";
		window.parent.frm.Account_fld14.value = "<%=account.getFld14()%>";
		window.parent.frm.Account_fld15.value = "<%=account.getFld15()%>";
		window.parent.frm.Account_allocPercent.value = "<%=Utility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>";
		window.parent.frm.Account_allocAmount.value = "<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%>";
		window.parent.frm.Account_allocQty.value = "<%=HiltonUtility.getFormattedQuantity(account.getAllocQty(), oid)%>";

		if (window.parent.frm.Account_accountTitle && window.parent.frm.Account_accountTitle.value) {
			window.parent.frm.Account_accountTitle.value = "<%=account.getAccountTitle()%>";
		}
		
	}
	else
	{
		window.parent.frm.Account_accountType[currentRow].value = "<%=s_acct_type%>";
		window.parent.frm.Account_allocMethod[currentRow].value = "<%=s_alloc_method%>";
		window.parent.frm.Account_fld1[currentRow].value = "<%=account.getFld1()%>";
		window.parent.frm.Account_fld2[currentRow].value = "<%=account.getFld2()%>";
		window.parent.frm.Account_fld3[currentRow].value = "<%=account.getFld3()%>";
		window.parent.frm.Account_fld4[currentRow].value = "<%=account.getFld4()%>";
		window.parent.frm.Account_fld5[currentRow].value = "<%=account.getFld5()%>";
		window.parent.frm.Account_fld6[currentRow].value = "<%=account.getFld6()%>";
		window.parent.frm.Account_fld7[currentRow].value = "<%=account.getFld7()%>";
		window.parent.frm.Account_fld8[currentRow].value = "<%=account.getFld8()%>";
		window.parent.frm.Account_fld9[currentRow].value = "<%=account.getFld9()%>";
		window.parent.frm.Account_fld10[currentRow].value = "<%=account.getFld10()%>";
		window.parent.frm.Account_fld11[currentRow].value = "<%=account.getFld11()%>";
		window.parent.frm.Account_fld12[currentRow].value = "<%=account.getFld12()%>";
		window.parent.frm.Account_fld13[currentRow].value = "<%=account.getFld13()%>";
		window.parent.frm.Account_fld14[currentRow].value = "<%=account.getFld14()%>";
		window.parent.frm.Account_fld15[currentRow].value = "<%=account.getFld15()%>";
		window.parent.frm.Account_allocPercent[currentRow].value = "<%=Utility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>";
		window.parent.frm.Account_allocAmount[currentRow].value = "<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%>";
		window.parent.frm.Account_allocQty[currentRow].value = "<%=HiltonUtility.getFormattedQuantity(account.getAllocQty(), oid)%>";
		
		if (window.parent.frm.Account_accountTitle && currentRow && window.parent.frm.Account_accountTitle[currentRow]) {
			window.parent.frm.Account_accountTitle[currentRow].value = "<%=account.getAccountTitle()%>";
		}
	}

	window.parent.addUp(currentRow);
	currentRow++;
<%	} %>
	window.parent.deleteEmptyRows();

	window.top.hidePopWin();


// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>