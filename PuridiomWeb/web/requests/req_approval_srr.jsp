<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.usermanager.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates" %>
<%@ page import="com.tsagate.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tsa.puridiom.entity.RequisitionLine" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%
String	oid = (String) request.getAttribute("organizationId");
String	language = (String)request.getAttribute("language");
PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
pageContext.setAttribute("oid", oid);
pageContext.setAttribute("language", language);
String	currencyCode = reqHeader.getCurrencyCode() ;
String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
%>

<%
    int	acci = 0;
    List accList = (List) reqHeader.getAccountList();

    if (accList != null && accList.size() > 0)
    {
%>
          <td valign=top>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr>
              <td><b>Account:&nbsp;</b></td>
<%
      for (int i = 0; i < accList.size(); i++)
      {
        Account account = (Account) accList.get(i);

        String	s_account = "";
        String s_fld4 = "";
		String s_fld5 = "";
        String	s_accArray[] = new String[15];

        s_accArray[0] = account.getFld1();
        s_accArray[1] = account.getFld2();
        s_accArray[2] = account.getFld3();
        s_accArray[3] = account.getFld4();
        s_accArray[4] = account.getFld5();
        s_accArray[5] = account.getFld6();
        s_accArray[6] = account.getFld7();
        s_accArray[7] = account.getFld8();
        s_accArray[8] = account.getFld9();
        s_accArray[9] = account.getFld10();
        s_accArray[10] = account.getFld11();
        s_accArray[11] = account.getFld12();
        s_accArray[12] = account.getFld13();
        s_accArray[13] = account.getFld14();
        s_accArray[14] = account.getFld15();

        for (int j = 0; j <15; j++)
        {
          if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
          {
            if (s_account.length() > 0)
            {
              s_account = s_account + s_account_separator + s_accArray[j];
            }
            else
            {
              s_account = s_accArray[j];
            }
          }
        }
        if ( !HiltonUtility.isEmpty(s_accArray[3]))
		{
			s_fld4 = s_accArray[3];
		}
		if ( !HiltonUtility.isEmpty(s_accArray[4]))
		{
			s_fld5 = s_accArray[4];
		}
        acci++;
%>
             <td nowrap><%=s_account%></td>
              <td width=15px>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
<%		} %>
            </tr>
            </table>
          </td>
<%	} %>
          <td width=100%>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
			<tsa:tr field="req-subtotal">
              <td width=72% class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</td>
              <td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getSubtotal(), currencyCode, oid)%></td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td width=15% class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getSubtotal(), currencyCode, oid)%>)</td>
              <% } %>
              <td width=15% class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-taxAmount">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-taxAmount" defaultString="Tax" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getTaxAmount(), currencyCode, oid)%></td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getTaxAmount(), currencyCode, oid)%>)</td>
              <% } %>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-shippingCharges">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shipping" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getShippingCharges(), currencyCode, oid)%></td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getShippingCharges(), currencyCode, oid)%>)</td>
              <% } %>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-shippingTaxAmount">
        <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
        <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getShippingTaxAmt(), currencyCode, oid)%></td>
        <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
        <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getShippingTaxAmt(), currencyCode, oid)%>)</td>
        <% } %>
        <td class=browseRow nowrap align=right>&nbsp;</td>
      </tsa:tr>
            <tsa:tr field="req-otherCharges">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getOtherCharges(), currencyCode, oid)%></td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getOtherCharges(), currencyCode, oid)%>)</td>
              <% } %>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-otherTaxAmount">
        <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</td>
        <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getOtherTaxAmount(), currencyCode, oid)%></td>
        <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
        <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getOtherTaxAmount(), currencyCode, oid)%>)</td>
        <% } %>
        <td class=browseRow nowrap align=right>&nbsp;</td>
      </tsa:tr>
            <tsa:tr field="req-Total">
              <td class=browseRow nowrap align=right><b><tsa:label labelName="req-total" defaultString="Total" />:</b></td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getTotal(), currencyCode, oid)%></td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td class=browseRow nowrap align=right>(<%=HiltonUtility.getCurrencyConvert(reqHeader.getTotal(), currencyCode, oid)%>)</td>
              <% } %>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
       	  	<tsa:tr field="req-estCost">
       	  		<td class=browseRow nowrap align=right><b><tsa:label labelName="req-estCost" defaultString="Est. Cost" checkRequired="false"/>:</b></td>
       	  		<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getEstimatedCost(), currencyCode, oid) %></td>
       	  	</tsa:tr>
            </table>
          </td>
        </tr>
        </table>

      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<%
  if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
  List	bList	= (List) request.getAttribute("budgetReviewList") ;
  String	udfArray[] = (String[]) request.getAttribute("budgetUdfArray") ;
  String	labelArray[] = (String[]) request.getAttribute("budgetLabelArray") ;
  String	sBudgetColumns = (String) request.getAttribute("budgetColumns") ;
  if (sBudgetColumns == null) sBudgetColumns = "0" ;
  if (bList == null) bList = new ArrayList() ;
  int	budgetColumns = Integer.parseInt(sBudgetColumns) ;
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td align=center valign=top>
        <table border=1  cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
        <tr>
          <td class=browseHdrDk>
            <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
            <tr height=18px>
              <td class=browseHdrDk width= "38%" nowrap align=left>&nbsp;<tsa:label labelName="bgt-string" defaultString="Account" /></td>
              <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Amount</td>
		      <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Budgeted</td>
	          <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Balance</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=100% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td>
            <table id=budgetRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
    for (int i = 0; i < bList.size(); i++)
    {
    	Object[] bTab = (Object[]) bList.get(i) ;
		BudgetTran bTran;
		BudgetAudit bAudit;
		BudgetCenter bCenter;
		BigDecimal	tran_amount;

		//if (oid.equalsIgnoreCase("WPC08P"))
		//{
		//	tran_amount = (BigDecimal) bTab[0];
		//	bCenter = (BudgetCenter) bTab[1];
		//} else {
			bTran = (BudgetTran) bTab[0];
			bAudit = (BudgetAudit) bTab[1];
			bCenter = (BudgetCenter) bTab[2];
			tran_amount = bAudit.getTranAmt();
		//}

		String bCenterData[] = new String[15];
		bCenterData[0] = bCenter.getBudget1() ;
		bCenterData[1] = bCenter.getBudget2() ;
		bCenterData[2] = bCenter.getBudget3() ;
		bCenterData[3] = bCenter.getBudget4() ;
		bCenterData[4] = bCenter.getBudget5() ;
		bCenterData[5] = bCenter.getBudget6() ;
		bCenterData[6] = bCenter.getBudget7() ;
		bCenterData[7] = bCenter.getBudget8() ;
		bCenterData[8] = bCenter.getBudget9() ;
		bCenterData[9] = bCenter.getBudget10() ;
		bCenterData[10] = bCenter.getBudget11() ;
		bCenterData[11] = bCenter.getBudget12() ;
		bCenterData[12] = bCenter.getBudget13() ;
		bCenterData[13] = bCenter.getBudget14() ;
		bCenterData[14] = bCenter.getBudget15() ;
		BigDecimal	bd_amount = HiltonUtility.getFormattedDollar(tran_amount, oid);
		BigDecimal	bd_budgeted = HiltonUtility.getFormattedDollar(bCenter.getBudgeted(), oid);
		BigDecimal	bd_preencumbered = HiltonUtility.getFormattedDollar(bCenter.getPreEncumbered(),oid) ;
		BigDecimal	bd_encumbered = HiltonUtility.getFormattedDollar(bCenter.getEncumbered(),oid) ;
		BigDecimal	bd_expensed = HiltonUtility.getFormattedDollar(bCenter.getExpensed(),oid) ;
		BigDecimal	bd_balance = HiltonUtility.getFormattedDollar(bd_budgeted.subtract(bd_preencumbered).subtract(bd_encumbered).subtract(bd_expensed),oid) ;
%>
        <tr>
<%
		String glAcctStr = "" ;
    	for (int ix = 0; ix < budgetColumns; ix++)
    	{
    		if (glAcctStr.length() > 0) glAcctStr = glAcctStr + s_account_separator ;
    		glAcctStr = glAcctStr + bCenterData[ix] ;
    } %>

	          <td class=browseRow width= "38%" nowrap><%=glAcctStr%></td>
	          <td class=browseRow width= "20%" nowrap align=right><%=bd_amount%></td>
	          <td class=browseRow width= "20%" nowrap align=right><%=bd_budgeted%></td>
	          <td class=browseRow width= "20%" nowrap align=right><%=bd_balance%></td>
            </tr>
            <%
            	String	cmtStr = bCenter.getComments() ;
            	if (cmtStr == null) cmtStr = "" ;
            	if (cmtStr.trim().length() > 0)
            	{ %>
            <tr>
              <td colspan=<%=budgetColumns + 3%> class=browseRow><%=cmtStr.trim()%></td>
            </tr>
            <% } %>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
    </table>
  </td>
</tr>
</table>

<br>

<% } %>
