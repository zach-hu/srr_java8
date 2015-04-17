<tsa:hidden name="as_maxrows" value="1"/>
<script type="text/javascript">
<!--
frm = document.purchaseform;
var accountFld1Browse = "systable";
var accountFld2Browse = "systable";
var accountFld3Browse = "systable";

var maxRows = frm.as_maxrows.value;
var tableType = "AC";

//-->
</script>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<table border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap>
						 <a  href="javascript:browseAccountFld('CapitalClearingAccount_entity', 'AC01'); void(0); " title="" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "entity", "Entity")%>:</a>
					</td>
					<td>&nbsp;</td>
					<td nowrap>
						 <a  href="javascript:browseAccountFld('CapitalClearingAccount_department', 'AC02'); void(0); " title="" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dept", "Dept")%>:</a>
					</td>
					<td>&nbsp;</td>
					<td nowrap>
						 <a  href="javascript:browseAccountFld('CapitalClearingAccount_account', 'AC03'); void(0); " title="" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "account", "Account")%>:</a>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="CapitalClearingAccount_entity" value="<%=CapitalClearingAccount_values[0]%>" size="20" ONCHANGE="upperCase(this);"/>
					</td>
					<td>&nbsp;</td>
				   	<td>
						<input type="text" name="CapitalClearingAccount_department" value="<%=CapitalClearingAccount_values[1]%>" size="20" ONCHANGE="upperCase(this);"/>
					</td>
					<td>&nbsp;</td>
				   	<td>
						<input type="text" name="CapitalClearingAccount_account" value="<%=CapitalClearingAccount_values[2]%>" size="20" ONCHANGE="upperCase(this);"/>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>