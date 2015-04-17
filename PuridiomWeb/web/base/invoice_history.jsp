
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr height=18px>
			<td colspan=5 align=center>&nbsp;</td>
		</tr>
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="645px">
		<tr class="mnav" height="18px">
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateTime", "Date/Time")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enteredBy", "Entered By")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoice", "Invoice")%> #</td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "total", "Total")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ip", "IP")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td></td>
		</tr>
<%	if (invoiceHeaderList != null)
		{
			for (int i = 0; i < invoiceHeaderList.size(); i++)
			{
				InvoiceHeader invoiceHeader = (InvoiceHeader) invoiceHeaderList.get(i);

				String	s_user = invoiceHeader.getEnteredBy();
				UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);

				String status = DocumentStatus.toString(invoiceHeader.getStatus(), oid);
				String s_timeZone = propertiesManager.getProperty("MISC","TIMEZONE","");
%>
			<tr height=18px>
				<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getDateEntered(), oid, userDateFormat)%>&nbsp;<%=s_timeZone%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=userProfile.getDisplayName()%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=invoiceHeader.getInvoiceNumber()%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=status%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=invoiceHeader.getId()%></td>
				<td nowrap="nowrap">
					<a href="javascript: viewInvNow('<%=invoiceHeader.getIcIvcHeader()%>'); void(0);"><img class="button" src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="View Pdf" ></a>
				</td>
			</tr>
<%
			}
		}

		if ( invoiceHeaderList == null || invoiceHeaderList.size() <= 0 ) {%>
			<tr height="18px">
				<td colspan="5" align="center"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInvoiceHistoryAvailable", "There is no invoice history available at this time")%>!</b></td>
			</tr>
<%	}%>
			</table>

				<iframe id="recframe" name="recframe" height="85px" width="230px" marginheight="0" hspace="0" frameborder="0" scrolling="no" src="" style="position: absolute; border:none; overflow-y:hidden; overflow-x:hidden; display:none;"></iframe>
