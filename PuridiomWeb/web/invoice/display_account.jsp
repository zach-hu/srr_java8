<%@page import="com.tsa.puridiom.currcode.CurrencyManager"%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table border=0 cellspacing=0 cellpadding=0 width=665px class="<%=className%>">
    <tr>
      <td>
        <table border=1 cellspacing=0 cellpadding=0 width=665px class="<%=className%>">
        <tr>
          <td>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class="<%=className%>">
            <tr>
              <td width=70% height=18px class="<%=className%>">&nbsp;<b><%=accountTitle%></b></td>
              <!--<td width=15% height=18px class="<%=className%>" align=right>&nbsp;<b>Percent Alloc.</b></td>-->
              <td width=15% height=18px class="<%=className%>" align=right>&nbsp;<b>Amount Alloc.</b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
		if (HiltonUtility.isEmpty(s_curr_code)) {
			s_curr_code = CurrencyManager.getInstance(oid).getCurrCode("").getCurrencyCode();
		}
		//bd_total_perc = new BigDecimal(0.00);
		//bd_total_amt = new BigDecimal(0.00);
		//acci = 0;
		BigDecimal bdTotalPerc = new BigDecimal(0.00);
		BigDecimal bdTotalAmt = new BigDecimal(0.00);
		acci = 0;
      	for (int i = 0; i < accountList.size(); i++)
        {
          Account account = (Account) accountList.get(i);

          BigDecimal bd_alloc_perc = account.getAllocPercent();
          BigDecimal bd_alloc_amt = account.getAllocAmount();

          bdTotalPerc = bdTotalPerc.add(bd_alloc_perc);
          bdTotalAmt = bdTotalAmt.add(bd_alloc_amt);

          String	s_account = "";
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
          acci++;
%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td width=70% class=browseRow><%=s_account%></td>
          <!--<td width=15% class=browseRow align=right><%=account.getAllocPercent().setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>-->
          <td width=25% class=browseRow align=right><%=account.getAllocAmount().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
        </tr>
        </table>

<%	}
        if (acci > 0) {%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr>
          <td class=browseRow>&nbsp;</td>
          <td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
        </tr>
        <tr>
          <td width=70% class=browseRow>&nbsp;</td>
          <!--<td width=15% class=browseRow align=right><%=bdTotalPerc.setScale(2, BigDecimal.ROUND_HALF_UP)%>%</td>-->
          <td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedCurrency(bdTotalAmt, s_curr_code, oid)%></td>
        </tr>
        </table>
<%	} %>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>