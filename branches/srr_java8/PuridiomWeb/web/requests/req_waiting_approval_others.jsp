<%List reqList = (List) request.getAttribute("myRequisitionList");
	if(reqList == null){		reqList = new ArrayList();	}
if(reqList.size() > 0)
{%>
		<tsa:label labelName="otherRequisitionsWaitingApproval" defaultString="Other Requisitions Waiting My Approval"/>
		<br>
		<br>
      				<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0">
      					<tsa:tr>
      						<tsa:td valign="top" align="center" width="680px">
		      					<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
									<tsa:tr>
										<tsa:td width="5px">&nbsp;</tsa:td>
										<tsa:td width="670px" cssClass="browseHdrDk" align="center" valign="top">
											<table border=0 cellspacing=0 cellpadding=3 width=665px>
												<tsa:tr>
													<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="25%" align="left"> <tsa:label labelName="type" defaultString="Type"/> </tsa:td>
													<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="req" defaultString="Req"/>.# </tsa:td>
													<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="requestDate" defaultString="Request Date"/> </tsa:td>
													<!--<td nowrap height=18px class=browseHdrDk width=15% align=left> <tsa:label labelName="status" defaultString="Status"/> </td>-->
													<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="total" defaultString="Total"/> </tsa:td>
													<tsa:td noWrap="nowrap" height="18px" cssClass="browseHdrDk" width="15%" align="left"> <tsa:label labelName="requisitioner" defaultString="Requisitioner"/> </tsa:td>
												</tsa:tr>
											</table>
											<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
											<%
											List reqPrinted = new ArrayList();
											for(int j=0; j<reqList.size(); j++)
											{
												  Object reqsToApprove[] = (Object[])reqList.get(j);
												  RequisitionHeader reqL = (RequisitionHeader) reqsToApprove[0];
												  BigDecimal flagIcHeader = reqL.getIcReqHeader();
												  if (!reqPrinted.contains(reqL))
												  {
													  reqPrinted.add(reqL);
											 %>
												  <table border=0 cellspacing=0 cellpadding=2 width=665px>
								    					<tsa:td align="left" width="25%" valign="top"><%=RequisitionType.toString(RequisitionType.toString(reqL.getRequisitionType(), oid), oid)%></tsa:td>
														<tsa:td align="left" width="15%" valign="top"><a href="javascript:retrieveReqApproval('<%=flagIcHeader%>');"><%=headerEncoder.encodeForHTML(reqL.getRequisitionNumber())%></tsa:td>
														<%--<td align=left width=15% valign=top><%=reqL.getRequisitionNumber()%></td>--%>
														<tsa:td align="left" width="15%" valign="top"><%=HiltonUtility.getFormattedDate(reqL.getRequisitionDate(), oid, userDateFormat)%></tsa:td>
														<!--<td align=left width=15% valign=top><%=DocumentStatus.toString(reqL.getStatus(), oid)%></td>-->
														<tsa:td align="left" width="15%" valign="top"><%=HiltonUtility.getFormattedDollar(reqL.getTotal(), oid)%></tsa:td>
														<tsa:td align="left" width="15%" valign="top"><%=UserManager.getInstance().getUser(oid, reqL.getRequisitionerCode()).getDisplayName()%></tsa:td>
														<!--<td align=left width=15% valign=top><%=reqL.getRequisitionerCode()%></td>-->
													</table>
								        	<%
								        		}
											}
											%>
										</div>
									</tsa:td>
								</tsa:tr>
							</table>
						</tsa:td>
					</tsa:tr>
				</table>
<%}
else
{%>
<br>
<%}%>