<%
		List recentAccountList = (List) request.getAttribute("recentAccountList");
		if (recentAccountList != null)
		{
			int recentAccountSize = recentAccountList.size();
			String s_displaySize = propertiesManager.getProperty("ACCOUNTS", "RECENTACCOUNTSIZE", "5");
			int i_displaySize = Integer.valueOf(s_displaySize).intValue();
			if (recentAccountSize > i_displaySize)
			{
				recentAccountSize = i_displaySize;
			}
			if (recentAccountSize > 0)
			{
%>

<br>
<br>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align="center">
		<table border=1 cellspacing=0 cellpadding=0 width=365px class=browseHdr>
		<tr>
			<td>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
				<tr "height=18px">
					<td width="100%" align="center" class="browseHdr"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "myFavoriteAccounts", "My Favorite Accounts")%></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=2 width=365px align=center>
<%			String sep = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
				for (int i = 0; i < recentAccountSize; i++)
				{
					RecentAccount recentAccount = (RecentAccount) recentAccountList.get(i);
					String accountString = recentAccount.getComp_id().getAccountString();
%>
				<tr height="18px">
					<td width="2%">&nbsp;</td>
					<td width="75%" nowrap><b><%=accountString%></b></td>
					<td width="10%"><a href="javascript: selectMe('<%=accountString%>', '<%=sep%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "select", "Select")%></a>&nbsp;</td>
				<%	String s_allow_accounts = propertiesManager.getProperty(labelPrefix.toUpperCase() + " OPTIONS", "SINGLE ACCOUNT ALLOCATION", "N");
					if(s_allow_accounts.equalsIgnoreCase("N")) { %>
					<td width="10%" align="right"><a href="javascript: addMe('<%=accountString%>', '<%=sep%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "add", "Add")%></a>&nbsp;</td>
				<%	} %>
					<td width="2%">&nbsp;</td>
				</tr>
<%			}	%>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%		}	//end if size > 0
		}	//end if not null	%>

<SCRIPT value=JavaScript>
<!-- Hide script

	function selectMe(acctString, separator)
	{
		var acctArray = new Array();
		acctArray = acctString.split(separator);

		if (maxRows < 1)
		{
			addNew();
		}

		if (maxRows > 1)
		{
			if ( frm.Account_fld1[currentRow] )
			{
				if (acctArray.length > 0)		{	frm.Account_fld1[currentRow].value = acctArray[0];		}	else	{	frm.Account_fld1[currentRow].value = "";	}
			}
			if ( frm.Account_fld2[currentRow] )
			{
				if (acctArray.length > 1)		{	frm.Account_fld2[currentRow].value = acctArray[1];		}	else	{	frm.Account_fld2[currentRow].value = "";	}
			}
			if ( frm.Account_fld3[currentRow] )
			{
				if (acctArray.length > 2)		{	frm.Account_fld3[currentRow].value = acctArray[2];		}	else	{	frm.Account_fld3[currentRow].value = "";	}
			}
			if ( frm.Account_fld4[currentRow] )
			{
				if (acctArray.length > 3)		{	frm.Account_fld4[currentRow].value = acctArray[3];		}	else	{	frm.Account_fld4[currentRow].value = "";	}
			}
			if ( frm.Account_fld5[currentRow] )
			{
				if (acctArray.length > 4)		{	frm.Account_fld5[currentRow].value = acctArray[4];		}	else	{	frm.Account_fld5[currentRow].value = "";	}
			}
			if ( frm.Account_fld6[currentRow] )
			{
				if (acctArray.length > 5)		{	frm.Account_fld6[currentRow].value = acctArray[5];		}	else	{	frm.Account_fld6[currentRow].value = "";	}
			}
			if ( frm.Account_fld7[currentRow] )
			{
				if (acctArray.length > 6)		{	frm.Account_fld7[currentRow].value = acctArray[6];		}	else	{	frm.Account_fld7[currentRow].value = "";	}
			}
			if ( frm.Account_fld8[currentRow] )
			{
				if (acctArray.length > 7)		{	frm.Account_fld8[currentRow].value = acctArray[7];		}	else	{	frm.Account_fld8[currentRow].value = "";	}
			}
			if ( frm.Account_fld9[currentRow] )
			{
				if (acctArray.length > 8)		{	frm.Account_fld9[currentRow].value = acctArray[8];		}	else	{	frm.Account_fld9[currentRow].value = "";	}
			}
			if ( frm.Account_fld10[currentRow] )
			{
				if (acctArray.length > 9)		{	frm.Account_fld10[currentRow].value = acctArray[9];		}	else	{	frm.Account_fld10[currentRow].value = "";	}
			}
			if ( frm.Account_fld11[currentRow] )
			{
				if (acctArray.length > 10)	{	frm.Account_fld11[currentRow].value = acctArray[10];	}	else	{	frm.Account_fld11[currentRow].value = "";	}
			}
			if ( frm.Account_fld12[currentRow] )
			{
				if (acctArray.length > 11)	{	frm.Account_fld12[currentRow].value = acctArray[11];	}	else	{	frm.Account_fld12[currentRow].value = "";	}
			}
			if ( frm.Account_fld13[currentRow] )
			{
				if (acctArray.length > 12)	{	frm.Account_fld13[currentRow].value = acctArray[12];	}	else	{	frm.Account_fld13[currentRow].value = "";	}
			}
			if ( frm.Account_fld14[currentRow] )
			{
				if (acctArray.length > 13)	{	frm.Account_fld14[currentRow].value = acctArray[13];	}	else	{	frm.Account_fld14[currentRow].value = "";	}
			}
			if ( frm.Account_fld15[currentRow] )
			{
				if (acctArray.length > 14)	{	frm.Account_fld15[currentRow].value = acctArray[14];	}	else	{	frm.Account_fld15[currentRow].value = "";	}
			}
		}
		else
		{
			if ( frm.Account_fld1 )
			{
				if (acctArray.length > 0)		{	frm.Account_fld1.value = acctArray[0];		}	else	{	frm.Account_fld1.value = "";	}
			}
			if ( frm.Account_fld2 )
			{
				if (acctArray.length > 1)		{	frm.Account_fld2.value = acctArray[1];		}	else	{	frm.Account_fld2.value = "";	}
			}
			if ( frm.Account_fld3 )
			{
				if (acctArray.length > 2)		{	frm.Account_fld3.value = acctArray[2];		}	else	{	frm.Account_fld3.value = "";	}
			}
			if ( frm.Account_fld4 )
			{
				if (acctArray.length > 3)		{	frm.Account_fld4.value = acctArray[3];		}	else	{	frm.Account_fld4.value="";	}
			}
			if ( frm.Account_fld5 )
			{
				if (acctArray.length > 4)		{	frm.Account_fld5.value = acctArray[4];		}	else	{	frm.Account_fld5.value="";	}
			}
			if ( frm.Account_fld6 )
			{
				if (acctArray.length > 5)		{	frm.Account_fld6.value = acctArray[5];		}	else	{	frm.Account_fld6.value = "";	}
			}
			if ( frm.Account_fld7 )
			{
				if (acctArray.length > 6)		{	frm.Account_fld7.value = acctArray[6];		}	else	{	frm.Account_fld7.value = "";	}
			}
			if ( frm.Account_fld8 )
			{
				if (acctArray.length > 7)		{	frm.Account_fld8.value = acctArray[7];		}	else	{	frm.Account_fld8.value = "";	}
			}
			if ( frm.Account_fld9 )
			{
				if (acctArray.length > 8)		{	frm.Account_fld9.value = acctArray[8];		}	else	{	frm.Account_fld9.value = "";	}
			}
			if ( frm.Account_fld10 )
			{
				if (acctArray.length > 9)		{	frm.Account_fld10.value = acctArray[9];		}	else	{	frm.Account_fld10.value = "";	}
			}
			if ( frm.Account_fld11 )
			{
				if (acctArray.length > 10)	{	frm.Account_fld11.value = acctArray[10];	}	else	{	frm.Account_fld11.value = "";	}
			}
			if ( frm.Account_fld12 )
			{
				if (acctArray.length > 11)	{	frm.Account_fld12.value = acctArray[11];	}	else	{	frm.Account_fld12.value = "";	}
			}
			if ( frm.Account_fld13 )
			{
				if (acctArray.length > 12)	{	frm.Account_fld13.value = acctArray[12];	}	else	{	frm.Account_fld13.value = "";	}
			}
			if ( frm.Account_fld14 )
			{
				if (acctArray.length > 13)	{	frm.Account_fld14.value = acctArray[13];	}	else	{	frm.Account_fld14.value = "";	}
			}
			if ( frm.Account_fld15 )
			{
				if (acctArray.length > 14)	{	frm.Account_fld15.value = acctArray[14];	}	else	{	frm.Account_fld15.value = "";	}
			}
		}
	}

	function addMe(acctString, separator)
	{
		addNew();
		selectMe(acctString, separator);
	}

// End Hide script -->
</SCRIPT>