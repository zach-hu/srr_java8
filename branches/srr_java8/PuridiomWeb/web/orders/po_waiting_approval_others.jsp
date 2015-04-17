<%List poList = (List) request.getAttribute("myOrdersList");
	if(poList == null){		poList = new ArrayList();	}
if(poList.size() > 0)
{%>
		<br>
		<br>
      				<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0">
      					<tr>
      						<th>Other Orders Waiting My Approval...</th>
      					<tr>
      						<td valign="top" align="center" width="680px">
		      					<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
									<tr>
										<td width=5px>&nbsp;</td>
										<td width=670px class=browseHdrDk align=center valign=top>
											<table border=0 cellspacing=0 cellpadding=3 width=665px>
												<tr>
													<td nowrap height=18px class=browseHdrDk width=25% align=left> Type </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Po.# </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Order Date </td>
													<!--<td nowrap height=18px class=browseHdrDk width=15% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%> </td>-->
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Total </td>
													<td nowrap height=18px class=browseHdrDk width=15% align=left> Buyer </td>
												</tr>
											</table>
											<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
											<%for(int j=0; j<poList.size(); j++)
											  {
											  	  Object toApprove[] = (Object[])poList.get(j);
												  PoHeader poL = (PoHeader) toApprove[0];
												  BigDecimal flagIcHeader = poL.getIcPoHeader();%>
												  <table border=0 cellspacing=0 cellpadding=2 width=665px>
								    					<td align=left width=25% valign=top><%=OrderType.toString(poL.getPoType(), oid)%></td>
														<td align=left width=15% valign=top><a href="javascript:retrieveOrderApproval('<%=flagIcHeader%>');"><%=poL.getPoNumber()%></td>
														<td align=left width=15% valign=top><%=poL.getPoDate()%></td>
														<td align=left width=15% valign=top><%=HiltonUtility.getFormattedDollar(poL.getTotal(), oid)%></td>
														<td align=left width=15% valign=top><%=UserManager.getInstance().getUser(oid, poL.getBuyerCode()).getDisplayName()%></td>
													</table>
								        	  	<%}%>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%}%>
