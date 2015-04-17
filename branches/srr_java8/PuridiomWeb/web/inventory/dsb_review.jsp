<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@page import="com.tsa.puridiom.commodity.CommodityManager"%>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/filter.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/DisbOptionArrays.js"></script>

<%@ include file="/system/header.jsp"%>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%
	String s_account_separator = PropertiesManager.getInstance(oid).getProperty("MISC", "AccountSeparator", "-");
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	if(requisitionHeader == null)
	{
		requisitionHeader = new RequisitionHeader();
	}
	BigDecimal b_icReqHeader = disbHeader.getIcReqHeader();
	BigDecimal b_icDisbHeader = disbHeader.getIcDsbHeader();
	String s_dsb_status = disbHeader.getStatus();
	String s_dsb_type = disbHeader.getDisbType();
	String s_dsb_number = disbHeader.getDisbNumber();

	if (s_dsb_number == null || s_dsb_number.length() <= 0)
	{
		s_dsb_number = "N/A";
	}

	String s_disbDate = "";
	Date d_disbDate = disbHeader.getDisbDate();
	if (d_disbDate != null)
	{
		s_disbDate = HiltonUtility.getFormattedDate(d_disbDate, oid, userDateFormat);
	}

	String s_fiscalYear = disbHeader.getFiscalYear();
	String s_requisitioner = disbHeader.getRequisitionerCode();
	String s_itemLocation = disbHeader.getItemLocation();
	String s_intComments = disbHeader.getInternalComments();
	BigDecimal b_subTotal = disbHeader.getSubtotal();

	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner);

	int linecount = 0;
	List disbList = (List) request.getAttribute("disbLineList");
	if (disbList != null)
	{
		linecount = disbList.size();
	}

	String s_current_process = "HEADER_REVIEW";
	String s_current_page = "/inventory/dsb_review.jsp";
	String s_current_method = "DisbSetProperty";
	String s_current_process_method = "DisbSetProperty";

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");

	boolean validateDuom = true;
	boolean validateSerialNumber = true;

	Map selectedProperty = (Map) request.getAttribute("selectedProperty");
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	disbNumber = "<%=s_dsb_number%>";
	disbStatus = "<%=s_dsb_status%>";
	disbursed = "<%=DocumentStatus.INV_DISBURSED%>";
	backorder = "<%=DocumentStatus.INV_BACKORDERED%>";

	Array91 = createDisbOptionsMenu(Array91[0]);

// -->
</SCRIPT>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value=""/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=disbHeader.getDisbNumber()%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=b_icReqHeader%>"/>
<tsa:hidden name="DisbHeader_icAccount" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="Account_icHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=b_icDisbHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="lineCount" value="<%=linecount%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="disbaction" value=""/>
<tsa:hidden name="cancelledItems" value="Y" />

<%-- commodity id to retrieve the commodity for the dsb_item page; set value in javascript --%>
<tsa:hidden name="Commodity_commodity" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12>Disbursement Information</div>
				</td>
			</tr>
		</table>
		</td>
		<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
			<tr>
				<td align=right><b>Disbursement #:</b></td>
				<td width=125px><%=s_dsb_number%></td>
			</tr>
			<tr>
				<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
				<td width=125px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
		</td>
	</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=middle width=100% height=34px>
	<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
	<tr>
		<%
			if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0)
			{
				if (!s_dsb_type.equals("O"))
				{
		%>
		<td align=right nowrap><a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'DisbForward'); void(0);">Forward</a></td>
		<td align=right nowrap><!-- a href="javascript: unavailable(); void(0);">Cancel</a--></td>
		<%
			} else
				{
		%>
		<td width="70%" align=right nowrap><!--a href="javascript: doSubmit('/inventory/dsb_forward.jsp', 'OtcSave'); void(0);">Forward</a></td-->
		<%
			}
			}
		%>
		<td width="10%" nowrap>&nbsp;</td>
		<td width="20%" align="center" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
	</tr>
	</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
	<tr>
		<td width=100% align=left>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
			<tr>
				<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
					<tr>
						<td align=center>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
							<tr>
								<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
							<tr>
								<td nowrap align=right width=35%><b>Disbursement Date:&nbsp;</b></td>
								<td nowrap width=65%><%=HiltonUtility.getFormattedDate(disbHeader.getDisbDate(), oid, userDateFormat)%></td>
							</tr>
							<tr>
								<td nowrap align=right width=35%><b>Inventory Location:&nbsp;</b></td>
								<td nowrap width=65%><%=disbHeader.getItemLocation()%></td>
							</tr>
							<%
								if (!s_dsb_type.equals("O"))
								{
							%>
							<tr>
								<td nowrap align=right width=35%><b>Requisition Number:&nbsp;</b></td>
								<td nowrap width=65%><%=headerEncoder.encodeForHTML(disbHeader.getRequisitionNumber())%></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td nowrap align=right width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%>:&nbsp;</b></td>
								<td nowrap width=65%><%=disbHeader.getRequisitionerCode()%></td>
							</tr>
							<tr>
								<td nowrap align=right width=35%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionerName", "Requisitioner Name")%>:&nbsp;</b></td>
								<td nowrap width=65%><%=requisitioner.getDisplayName()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-department")%>>
								<td align=right valign=top width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-department", "Department")%>:</b></td>
								<td nowrap valign=top><%=requisitionHeader.getDepartmentCode()%></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
				<%
					String s_ship_address_line_1 = "";
					String s_ship_address_line_2 = "";
					String s_ship_address_line_3 = "";
					String s_ship_address_line_4 = "";
					String s_ship_city = "";
					String s_ship_state = "";
					String s_ship_postal_code = "";
					String s_ship_country = "";

					//Address shipTo = (Address) disbHeader.getShipToAddress();
					Address shipTo = (Address) requisitionHeader.getShipToAddress();
					if (shipTo != null)
					{
						s_ship_address_line_1 = shipTo.getAddressLine1();
						s_ship_address_line_2 = shipTo.getAddressLine2();
						s_ship_address_line_3 = shipTo.getAddressLine3();
						s_ship_address_line_4 = shipTo.getAddressLine4();
						s_ship_city = shipTo.getCity();
						s_ship_state = shipTo.getState();
						s_ship_postal_code = shipTo.getPostalCode();
						s_ship_country = shipTo.getCountry();
					}
				%>
				<td width=300px align=center valign=top>
					<%	if (!s_dsb_type.equals("O"))
					{ %>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
					<tr>

						<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
							<tr>
								<td class=browseHdr height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>>
								<td class=browseRow height=12px nowrap><b><%=disbHeader.getShipToCode()%></b></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine1")%>>
								<td class=browseRow height=12px nowrap><%=s_ship_address_line_1%></td>
							</tr>
						</table>

						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<%
								if (!HiltonUtility.isEmpty(s_ship_address_line_2))
								{
							%>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine2")%>>
								<td class=browseRow height=12px nowrap><%=s_ship_address_line_2%></td>
							</tr>
							<%
								}
								if (!HiltonUtility.isEmpty(s_ship_address_line_3))
								{
							%>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine3")%>>
								<td class=browseRow height=12px nowrap><%=s_ship_address_line_3%></td>
							</tr>
							<%
								}
								if (!HiltonUtility.isEmpty(s_ship_address_line_4))
								{
							%>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine4")%>>
								<td class=browseRow height=12px nowrap><%=s_ship_address_line_4%></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td class=browseRow height=12px nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-country")%>>
								<td class=browseRow height=12px nowrap><%=s_ship_country%></td>
							</tr>
						</table>

						<%
							if (!HiltonUtility.isEmpty(requisitionHeader.getShipAttn()))
							{
						%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr <%=HtmlWriter.isVisible(oid, "req-shp-attention")%>>
								<td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shp-attention", "Attn")%>:&nbsp;<%=requisitionHeader.getShipAttn()%></td>
							</tr>
						</table>
						<%
							}
						%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr <%=HtmlWriter.isVisible(oid, "req-requiredDate")%>>
								<td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-requiredDate", "Required Date")%>:&nbsp;<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%> <tsa:hidden name="RequisitionHeader_requiredBy" value="<%=HiltonUtility.getFormattedDate(requisitionHeader.getRequiredDate(), oid, userDateFormat)%>"/></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-priority")%>>
								<td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-priority", "Priority")%>:&nbsp;<%=requisitionHeader.getPriorityCode()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-shipVia")%>>
								<td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shipVia", "Ship Via")%>:&nbsp;<%=requisitionHeader.getShippingCode()%></td>
							</tr>
							<tr <%=HtmlWriter.isVisible(oid, "req-routing")%>>
								<td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-routing", "Routing")%>:&nbsp;<%=requisitionHeader.getRouting()%></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<% } %>
				</td>
				<td rowspan=3 align=right valign=top><%@ include file="/inventory/dsb_sidebar.jsp"%></td>
			</tr>
			<tr>
				<td><br>
				</td>
			</tr>
		</table>

		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td align=center width=680px>
				<table border=0 cellspacing=0 cellpadding=0 width=680px class=browseHdr>
					<tr>
						<td>
						<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
							<tr>
								<td>
								<table border=0 cellspacing=0 cellpadding=0 width=680px class=browseHdr>
									<tr>
										<td width=60% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
										<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Percent Alloc.</b></td>
										<td width=20% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>

							<%
								BigDecimal bd_total_perc = new BigDecimal(0.00);
								BigDecimal bd_total_amt = new BigDecimal(0.00);
								List accList = (List) request.getAttribute("accountList");
								if (accList != null)
								{
									for (int i = 0; i < accList.size(); i++)
									{
										Account account = (Account) accList.get(i);

										BigDecimal bd_alloc_perc = account.getAllocPercent();
										BigDecimal bd_alloc_amt = account.getAllocAmount();

										if (bd_alloc_perc == null)
										{
											bd_alloc_perc = new BigDecimal(0);
										}
										if (bd_alloc_amt == null)
										{
											bd_alloc_amt = new BigDecimal(0);
										}

										bd_total_perc = bd_total_perc.add(bd_alloc_perc);
										bd_total_amt = bd_total_amt.add(bd_alloc_amt);

										String s_account = "";
										String s_accArray[] = new String[15];

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

										for (int j = 0; j < 15; j++)
										{
											if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
											{
												if (s_account.length() > 0)
												{
													s_account = s_account + s_account_separator + s_accArray[j];
												} else
												{
													s_account = s_accArray[j];
												}
											}
										}
							%>
							<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
								<tr>
									<td width=60% class=browseRow><%=s_account%></td>
									<td width=20% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(account.getAllocPercent(), 2)%>%</td>
									<td width=20% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
								</tr>
							</table>
							<%
								}
								}
							%>
							<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
								<tr>
									<td class=browseRow>&nbsp;</td>
									<td class=browseRow colspan=2 valign=top>
									<hr size=0 align=right colspan=2>
									</td>
								</tr>
								<tr>
									<td width=60% class=browseRow>&nbsp;</td>
									<td width=20% class=browseRow align=right><%=HiltonUtility.getBigDecimalFormatted(bd_total_perc, 2)%>%</td>
									<td width=20% class=browseRow align=right><%=HiltonUtility.getFormattedDollar(bd_total_amt, oid)%></td>
								</tr>
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
			if (!s_dsb_type.equals("O"))
			{
		%>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td align=center width=680px>
				<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
					<tr>
						<td>
						<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
							<tr>
								<td>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
									<tr>
										<td width=75% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
										<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
										<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
										<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
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
							List cmtList = (List) disbHeader.getDocCommentList();
								int size = 0;
								if (cmtList != null)
								{
									size = cmtList.size();
									for (int i = 0; i < size; i++)
									{
										DocComment docComment = (DocComment) cmtList.get(i);

										String s_cmt_title = docComment.getCommentTitle();
										String s_cmt_print = docComment.getCommentPrint();
										String s_cmt_bold = docComment.getCommentBold();
										String s_cmt_place = docComment.getCommentPlace();

										if (s_cmt_place.equals("A"))
										{
											s_cmt_place = "After";
										} else if (s_cmt_place.equals("B"))
										{
											s_cmt_place = "Before";
										}
						%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr>
								<td width=75% class=browseRow>&nbsp;<%=s_cmt_title%></td>
								<td width=8% class=browseRow align=center valign=top><%=s_cmt_print%></td>
								<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
								<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
							</tr>
						</table>
						<%
							}
								}
						%>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr>
								<td valign=middle>&nbsp;</td>
								<td align=right valign=middle></td>
							<tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<%
			}
		%> <br>

		<br>

		<table border=0 cellpadding=0 cellspacing=0 width=680px>
			<tr>
				<td align=center width=680px>
				<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
					<tr>
						<td>
						<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
							<tr>
								<td width=100% align=center valign=top>
								<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
									<tr>
										<td width=6% class=browseHdr nowrap>&nbsp;Line #</td>
										<td width=15% class=browseHdr nowrap align="center">&nbsp;Item/Part #</td>
										<td width=10% class=browseHdr nowrap align="left">&nbsp;Item Loc.</td>
										<td width=5% class=browseHdr nowrap>&nbsp;Aisle</td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-row")%>  width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-row" , "Row")%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-tier")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-tier", "Tier")%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-bin")%>  width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-bin" , "Bin")%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-uom")%>  width=7% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-uom" , "UI")%></td>
										<td width=10% class=browseHdr nowrap align=right>&nbsp;Quantity</td>
										<td width=10% class=browseHdr nowrap align=right>&nbsp;Commodity Code</td>
										<td width=10% class=browseHdr nowrap align=right>&nbsp;Price</td>
										<td width=10% class=browseHdr nowrap align=right>&nbsp;Line Total</td>
										<td width=12% class=browseHdr nowrap align=right>&nbsp;Line Status</td>
									</tr>


<%									for (int i = 0; i < linecount; i++)
									{
										DisbLine disbLine = (DisbLine) disbList.get(i);
										List shipToList = (List) disbLine.getShipToList();
										Commodity commodity = CommodityManager.getInstance().getCommodity(oid, disbLine.getCommodityCode());
										if (commodity != null && commodity.getDuomRequired().equalsIgnoreCase("Y") && s_duomRequired.equalsIgnoreCase("Y"))
										{
											if (disbLine.getDuomQuantity().compareTo(new BigDecimal(0)) <= 0)
											{
												validateDuom = false;
											}
										}

										if (commodity != null && "Y".equals(commodity.getSerialNumbersRequired()))
										{
											List pList = new ArrayList();
											if (selectedProperty != null && selectedProperty.containsKey(disbLine.getIcRc().toString())) {
												pList = (List) selectedProperty.get(disbLine.getIcRc().toString());
											}
											if (disbLine.getQuantity().compareTo(new BigDecimal(pList.size())) != 0)
											{
												validateSerialNumber = false;
											}
										}
										pageContext.setAttribute("i", i);
								%>
								<tsa:hidden name="commodityCode_${i}" id="commodityCode_${i}"
									   value="<%=disbLine.getCommodityCode() %>"/>
									<tr class="browseRow">
										<td width=6% class=browseRow nowrap><a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details."><%=i + 1%></a></td>
										<td width=16% class=browseRow nowrap><%=disbLine.getItemNumber()%><tsa:hidden id="icDsbLine_${i}" name="icDsbLine_${i}" value="<%=disbLine.getIcDsbLine()%>"/></td>
										<td width=9% class=browseRow nowrap><%=disbLine.getItemLocation()%></td>
										<td width=5% class=browseRow nowrap><%=disbLine.getAisle()%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-row")%>  width=5% class=browseRow nowrap><%=disbLine.getLocrow()%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-tier")%> width=5% class=browseRow nowrap><%=disbLine.getTier()%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-bin")%>  width=5% class=browseRow nowrap><%=disbLine.getBin()%></td>
										<td <%=HtmlWriter.isVisible(oid, "dsb-uom")%>  width=5% class=browseRow nowrap><%=disbLine.getUmCode()%></td>
										<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(disbLine.getQuantity(), oid)%></td>
										<td width=19% class=browseRow align=center nowrap><%=disbLine.getCommodityCode()%></td>
										<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getUnitPrice(), oid)%></td>
										<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbLine.getLineTotal(), oid)%></td>
										<td width=12% class=browseRow nowrap align=right><%=DocumentStatus.toString(disbLine.getStatus())%></td>
									</tr>
									<%		if (s_duomRequired.equalsIgnoreCase("Y") && disbLine.getDuomUmCode() != null && disbLine.getDuomUmCode().length() > 0) { %>
										<tr class="browseRow">
											<td colspan=7>&nbsp;</td>
											<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomUmCode")%> width=5% nowrap><%=HiltonUtility.ckNull(disbLine.getDuomUmCode())%></td>
											<td <%=HtmlWriter.isVisible(oid, "locbrw-inv-duomQtyOnHand")%> width=10% align=right nowrap><%=HiltonUtility.getFormattedQuantity(disbLine.getDuomQuantity(), oid)%></td>
										</tr>
									<% } %>
									<%RequisitionLine msrLine =  (RequisitionLine)disbLine.getMsrLine();
										String msrLineNum="";
										if(msrLine!=null){
											msrLineNum=(HiltonUtility.ckNull(msrLine.getRequisitionNumber())).toString()+"-"+(HiltonUtility.ckNull(msrLine.getLineNumber())).intValue();
										}
									%>
									<tr class="browseRow">
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=10 class=browseRow><%=msrLineNum%></td>
										</tr>
									<tr class="browseRow">
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=10 class=browseRow><%=disbLine.getDescription()%></td>
									</tr>
									<tr class="browseRow">
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow>Commodity: <%=disbLine.getCommodityCode()%></td>
									</tr>


									<%
										if (shipToList != null)
											{
												for (int ix = 0; ix < shipToList.size(); ix++)
												{
													ShipTo lineShipTo = (ShipTo) shipToList.get(ix);
													ShipToPK shipToPK = lineShipTo.getComp_id();
													String s_shp_attn = lineShipTo.getAttention();

													Address shipToAddress = (Address) lineShipTo.getShipToAddress();
													if (shipToAddress != null)
													{
														s_ship_address_line_1 = shipToAddress.getAddressLine1();
														s_ship_address_line_2 = shipToAddress.getAddressLine2();
														s_ship_address_line_3 = shipToAddress.getAddressLine3();
														s_ship_address_line_4 = shipToAddress.getAddressLine4();
														s_ship_city = shipToAddress.getCity();
														s_ship_state = shipToAddress.getState();
														s_ship_postal_code = shipToAddress.getPostalCode();
														s_ship_country = shipToAddress.getCountry();
													} else
													{
														s_ship_address_line_1 = "";
														s_ship_address_line_2 = "";
														s_ship_address_line_3 = "";
														s_ship_address_line_4 = "";
														s_ship_city = "";
														s_ship_state = "";
														s_ship_postal_code = "";
														s_ship_country = "";
													}
									%>
									<tr>
										<td colspan=8><br>
										</td>
									</tr>
									<tr>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow>Ship <b><%=HiltonUtility.getFormattedQuantity(lineShipTo.getQuantity(), oid)%></b> to:</td>
									</tr>
									<tr <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><b><%=shipToPK.getShipToCode()%></b></td>
									</tr>
									<%
										if (!HiltonUtility.isEmpty(s_ship_address_line_1))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine1")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_address_line_1%></td>
									</tr>
									<%
										}
													if (!HiltonUtility.isEmpty(s_ship_address_line_2))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine2")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_address_line_2%></td>
									</tr>
									<%
										}
													if (!HiltonUtility.isEmpty(s_ship_address_line_3))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine3")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_address_line_3%></td>
									</tr>
									<%
										}
													if (!HiltonUtility.isEmpty(s_ship_address_line_4))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-addressLine4")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_address_line_4%></td>
									</tr>
									<%
										}
									%>
									<tr>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
									</tr>
									<%
										if (!HiltonUtility.isEmpty(s_ship_country))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-country")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=s_ship_country%></td>
									</tr>
									<%
										}
													if (!HiltonUtility.isEmpty(s_shp_attn))
													{
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-shp-attention")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shp-attention", "Attn")%>: <%=s_shp_attn%></td>
									</tr>
									<%
										}
									%>
									<tr <%=HtmlWriter.isVisible(oid, "req-requiredDate")%>>
										<td class=browseRow nowrap>&nbsp;</td>
										<td colspan=7 class=browseRow><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-requiredDate", "Required By")%>: <%=HiltonUtility.getFormattedDate(lineShipTo.getShipDate(), oid, userDateFormat)%></td>
									</tr>
									<%
										}
											}
									%>
								<%
									}
								%>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
							<tr>
								<td>&nbsp;</td>
								<td class=browseRow width=30% align=right>
								<hr size=0>
								</td>
							</tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
							<tr>
								<td>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
									<tr>
										<td width=78% class=browseRow nowrap align=right><b>Total:</b></td>
										<td width=10% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(disbHeader.getSubtotal(), oid)%></td>
										<td width=12%>&nbsp;</td>
									</tr>
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


		<br>
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="680px">
			<tr>
				<td align="center" width="680px">
				<%
					List scheduleList = (List) requisitionHeader.getScheduleList();
					int si = 0;
					String typeOfSchedule = "";
					String lastTypeOfSchedule = "";
					if (scheduleList != null)
					{
						for (int i = 0; i < scheduleList.size(); i++)
						{
							Schedule schedule = (Schedule) scheduleList.get(i);
							typeOfSchedule = schedule.getComp_id().getScheduleType();

							//if (schedule == null) {
							//  continue;
							//}
							si++;
				%>
				<table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
					<%
						if (!typeOfSchedule.equals(lastTypeOfSchedule))
								{
					%>
					<tr>
						<td>
						<table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
							<tr>
								<td>
								<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
									<tr>
										<td width="55%" height="18px" class="browseHdr">&nbsp;<b><%=ScheduleType.toString(typeOfSchedule, oid)%></b></td>
										<td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-scheduleDate", "Schedule")%></b></td>
										<td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-revisedDate", "Revised")%></b></td>
										<td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-completionDate", "Completion")%></b></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td>
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
							<tr>
								<td width="55%" class="browseRow"><%=(schedule.getDescription() == null) ? "" : String.valueOf(schedule.getDescription())%></td>
								<td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getScheduleDate() == null) ? "" : String.valueOf(schedule.getScheduleDate())%></td>
								<td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getRevisedDate() == null) ? "" : String.valueOf(schedule.getRevisedDate())%></td>
								<td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getCompletionDate() == null) ? "" : String.valueOf(schedule.getCompletionDate())%></td>
							</tr>
						</table>
						</td>
					</tr>

				</table>

				<%
					lastTypeOfSchedule = schedule.getComp_id().getScheduleType();
						}
					}
				%>
				</td>
			</tr>
		</table>

		<%@ include file="/system/footer.jsp"%> <SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var disbnumber = "<%=s_dsb_number%>";
	var fiscalyear = "<%=s_fiscalYear%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var itemLocation = "<%=disbHeader.getItemLocation()%>";

	function toggleDetailsDisplay(areaName, type) {
		var myAreas = document.getElementsByName(areaName);
		var myArea;
		var myImg = document.getElementById(areaName + "Img");
		var myText = document.getElementById(areaName + "Text");
		var hideArea = false;

		for (i = 0; i < myAreas.length; i++) {
			myArea = myAreas(i);


			if (myArea.style.display == "none") {
				myArea.style.display = "block";
			}
			else {
				hideArea = true;
				myArea.style.display = "none";
			}
		}

		if (type == undefined) {
			type = "Details";
		}

		if (hideArea) {
			myImg.src = "<%=contextPath%>/images/plus.gif";
			myImg.alt = "View" + type;
			myText.innerText = "View " + type;
		}
		else {
			myImg.src = "<%=contextPath%>/images/minus.gif";
			myImg.alt = "Hide " + type;
			myText.innerText = "Hide " + type;
		}
	}

	function viewItem(row) {
		var num = document.getElementById("icDsbLine_" + row);
		frm.DisbLine_icDsbLine.value = num.value;

		var commodityCode = document.getElementById('commodityCode_' + row).value;
		frm.Commodity_commodity.value = commodityCode;

		doSubmit('/inventory/dsb_item.jsp','DisbSetProperty;DisbLineRetrieve;CommodityRetrieveById');
	}

	function highlightRow(row)
	{
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "selectedRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "selectedRow");
	}

	function removeHighlight(row)
	{
		row = row * 2;
		var myRow = document.all.itemRows.rows(row);

		setRowClassName(myRow, "browseRow");

		myRow = document.all.itemRows.rows(row + 1);

		setRowClassName(myRow, "browseRow");
	}

	function switchView()
	{
		frm.viewType.value = "CLASSIC";
		doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbursementRetrieve');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=disbHeader.getIcHeaderHistory()%>;formtype=DSB;DisbHeader_icDsbHeader=<%=b_icDisbHeader%>";
		doSubmitToPopup('/inventory/dsb_history.jsp', 'DisbSetProperty;HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function validateDisb(action)
	{
		frm.disbaction.value = action;

		if (action == 'FORWARD')
		{
		<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0)
			{
				if (validateDuom && validateSerialNumber) { %>
					hideForwardButton();
					doSubmit('/inventory/dsb_validate_no_popup.jsp', 'DisbSetProperty;DisbValidate');
		<%		} else {
					if (!validateDuom) { %>
						alert("One or more of your line items requires a Secondary quantity greater than Zero.");
		<%			} else { %>
						alert("One or more of your line items requires a serial number for each quantity entered.");
		<%			}
				}
			} %>
		}
		else
		{
			doSubmit('/inventory/dsb_validate_no_popup.jsp', 'DisbSetProperty;DisbValidate');
		}
	}

	function disbForward()
	{
		<%if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) == 0)
		{
			if (validateDuom && validateSerialNumber)
			{%>
				doSubmit('/inventory/dsb_forward.jsp', 'DisbSetProperty;DisbForward');
<%			}
			else
			{
				if (!validateDuom) { %>
					alert("One or more of your line items requires a Secondary quantity greater than Zero.");
			<%	} else {%>
					alert("One or more of your line items requires a serial number for each quantity entered.");
			<%	}
			}
		}%>
	}

	function printPdf()
	{
		var icHeader = "<%=disbHeader.getIcDsbHeader()%>";
		popupParameters = "DisbHeader_icDsbHeader=" + icHeader;
		popupParameters = popupParameters + ";uid=" + '${esapi:encodeForJavaScript(userId)}';
		popupParameters = popupParameters + ";oid=" + '<%=oid%>';

		//doSubmitToPopup('', 'PrintPdf', 'width=675px', 'height=450px');
		doSubmit('/inventory/dsb_print_pdf.jsp', 'DoNothing');
	}

	function printPreview() {
		var icHeader = "<%=disbHeader.getIcDsbHeader()%>";
		popupParameters = "DisbHeader_icDsbHeader=" + icHeader;
		doSubmitToPopup('/inventory/dsb_preview.jsp', 'DisbSetProperty;DisbursementRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function hideForwardButton()
	{
		hideAreaWithBlock('forward_link');
	}

// End Hide script -->
</SCRIPT>