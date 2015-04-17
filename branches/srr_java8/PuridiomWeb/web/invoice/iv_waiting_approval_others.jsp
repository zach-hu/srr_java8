<%List ivcList = (List) request.getAttribute("myInvoiceList");
	if(ivcList == null){		ivcList = new ArrayList();	}
if(ivcList.size() > 0)
{%>
		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "otherInvoicessWaitingApproval", "Other Invoices Waiting My Approval")%>
		<br>
		<br>
      				<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0">
      					<tr>
      						<td valign="top" align="center" width="680px">
		      					<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
									<tr>
										<td width=5px>&nbsp;</td>
										<td width=670px class=browseHdrDk align=center valign=top>
											<table border=0 cellspacing=0 cellpadding=3 width=665px>
												<tr>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceNumber", "Invoice Number")%>.# </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDate", "Invoice Date")%> </td>
													<!--<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%> </td>-->
													<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceTotal", "Invoice Total")%> </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-enteredby", "Entered By")%> </td>
												</tr>
											</table>
											<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
											<%
											List ivcPrinted = new ArrayList();
											for(int j=0; j<ivcList.size(); j++)
											{
												  Object ivcsToApprove[] = (Object[])ivcList.get(j);
												  InvoiceHeader ivcL = (InvoiceHeader) ivcsToApprove[0];
												  BigDecimal flagIcHeader = ivcL.getIcIvcHeader();
												  if (!ivcPrinted.contains(ivcL))
												  {
													  ivcPrinted.add(ivcL);
											 %>
												  <table border=0 cellspacing=0 cellpadding=2 width=665px>
								    					<td align=left width=15% valign=top><a href="javascript:retrieveIvcApproval('<%=flagIcHeader%>');"><%=ivcL.getInvoiceNumber()%></td>
														<!--<td align=left width=15% valign=top><%=ivcL.getInvoiceNumber()%></td>-->
														<td align=left width=15% valign=top><%=HiltonUtility.getFormattedDate(ivcL.getInvoiceDate(), oid, userDateFormat)%></td>
														<!--<td align=left width=15% valign=top><%=DocumentStatus.toString(ivcL.getStatus(), oid)%></td>-->
														<td align=left width=15% valign=top><%=HiltonUtility.getFormattedDollar(ivcL.getInvoiceTotal(), oid)%></td>
														<td align=left width=15% valign=top><%=UserManager.getInstance().getUser(oid, ivcL.getEnteredBy()).getDisplayName()%></td>
														<!--<td align=left width=15% valign=top><%=ivcL.getEnteredBy()%></td>-->
													</table>
								        	<%
								        		}
											}
											%>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%}
else
{%>
<br>
<%}%>