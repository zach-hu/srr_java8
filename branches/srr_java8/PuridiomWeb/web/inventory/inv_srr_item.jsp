<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tr>
		<td valign="top">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width=30px></td>
				<td nowrap align="left" class=label width=80px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemNumber", "Item/Part #",
									true)%>:&nbsp;</td>
				<td><INPUT TYPE="TEXT" NAME="InvItem_itemNumber" SIZE="25"
					MAXLENGTH="30" tabindex=2 value="<%=s_item_number%>"
					onchange="upperCase(this);itemDuplicateCheck(this);"
					<%if (s_action.equalsIgnoreCase("UPDATE")
					|| s_autoNumber.equalsIgnoreCase("Y")) {%>
					disabled <%}%>></td>
				<%
					if (s_action.equalsIgnoreCase("DISABLED-CREATE")) {
				%>
				<td <%=HtmlWriter.isVisible(oid, "inv-autonumber")%>>
				<div id=invAutoNumber>&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid,
										language).getLabel(oid,
										"inv-autonumber", "Auto Number")%>:&nbsp;<INPUT
					TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_autoNumber.equals("Y")) {%> CHECKED <%}%> tabindex=32
					value="Y"
					onclick="setCheckbox(frm.autoNumber,0); autoNumberCheck(frm.InvItem_itemNumber, this.checked);">
				</div>
				</td>
				<%
					} else {
				%>
				<td width=120px></td>
				<%
					}
				%>
				<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-actionCode", "Action Code")%>:&nbsp;</td>
				<td><select name="InvItem_actionCode" tabindex=3
					onchange="setFormTypeFields();"
					<%if (s_action.equalsIgnoreCase("UPDATE")) {%> disabled <%}%>>
					<option value="B" <%if (s_action_code.indexOf("B") >= 0) {%>
						SELECTED <%}%>>Buy</option>
					<option value="M" <%if (s_action_code.indexOf("M") >= 0) {%>
						SELECTED <%}%>>Make</option>
					<option value="F" <%if (s_action_code.indexOf("F") >= 0) {%>
						SELECTED <%}%>>Fill</option>
				</select></td>
			</tr>
		</table>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width=20px></td>
				<td nowrap align="left" valign="top" class=label width=300px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-description", "Description",
									true)%>:&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td width=20px></td>
				<td><textarea wrap="virtual" name="InvItem_description"
					tabindex=4 rows=6 cols=65 onKeyDown="textCounter(this, 255);"
					onKeyUp="textCounter(this,255);">${esapi:encodeForHTML(invItem.description)}</textarea>
				</td>
				<td>
				<table border="0" cellpadding="0" cellspacing="1">
					<%
						if (s_itemCrossRef.equalsIgnoreCase("Y") || oid.equals("SRR10P")) {
					%>
					<tr <%=HtmlWriter.isVisible(oid, "inv-nsnNumber")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-nsnNumber", "NSN")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_nsnNumber" SIZE="20"
							MAXLENGTH="20" tabindex=5 value="<%=s_nsnNumber%>"
							onchange="upperCase(this); <%if (s_appendAltNo.equalsIgnoreCase("Y")) {%> updateDescription(); <%}%>"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST13")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST13", "UDF 13")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf13Code" SIZE="20"
							MAXLENGTH="30" tabindex=6 value="<%=invItem.getUdf13Code()%>"
							onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST14")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST14", "UDF 14")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf14Code" SIZE="20"
							MAXLENGTH="30" tabindex=7 value="<%=invItem.getUdf14Code()%>"
							onchange="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST15")%>>
						<td nowrap align="right">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST15", "UDF 15")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf15Code" SIZE="20"
							MAXLENGTH="30" tabindex=8 value="<%=invItem.getUdf15Code()%>"
							onchange="upperCase(this);"></td>
					</tr>
					<%
						}
					%>
				</table>
				</td>
			</tr>
		</table>

		<hr width=475px align=center class=browseHR>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr <%=HtmlWriter.isVisible(oid, "inv-drawingNo")%>>
						<td nowrap align="right" width=120px>
						<div id=drawingNoA><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "inv-drawingNo",
											"Drawing No.", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=drawingNoB><INPUT TYPE="TEXT"
							NAME="InvItem_drawingNo" size=20 maxlength=30 tabindex=10
							value="<%=invItem.getDrawingNo()%>"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-commodity")%>>
						<td nowrap align="right"><a
							href="javascript: browseCommodity('InvItem_commodity', 'commodity', '<%=PropertiesManager.getInstance(oid).getProperty("MISC",
							"COMMODITYTYPE", "")%>'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-commodity", "Commodity", true)%>:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_commodity" size=15
							maxlength=15 tabindex=15 value="<%=invItem.getCommodity()%>"></td>
					</tr>
					<tr>
						<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%>
							align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-commodityName",
									"Commodity Name")%>:</a>&nbsp;</td>
						<td <%=HtmlWriter.isVisible(oid, "inv-commodityName")%>><input
							type=text name="as_commodityName" size=25
							value="<%=CommodityManager.getInstance().getCommodityDescription(
							oid, invItem.getCommodity())%>"
							onfocus="this.blur();" disabled></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfIssue")%>>
						<td nowrap align="right">
						<div id=unitOfIssueA><a
							href="javascript: browseLookup('InvItem_unitOfIssue', 'unitofissue'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-unitOfIssue", "Unit of Issue",
									true)%>:</a>&nbsp;</div>
						</td>
						<td>
						<div id=unitOfIssueB><INPUT TYPE="TEXT"
							NAME="InvItem_unitOfIssue" SIZE="15" MAXLENGTH="15" tabindex=20
							value="<%=invItem.getUnitOfIssue()%>" onchange="upperCase(this);"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-conversionFactor")%>>
						<td nowrap align="right">
						<div id=conversionFactorA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-conversionFactor",
									"Conversion Factor", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=conversionFactorB><INPUT TYPE="TEXT"
							NAME="InvItem_factor" SIZE="15" MAXLENGTH="15" tabindex=25
							value="<%=bd_factor%>" style="text-align: right"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-issueCost")%>>
						<td nowrap align="right">
						<div id=issueCostA><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "inv-issueCost", "Issue Cost",
											true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=issueCostA><INPUT TYPE="TEXT"
							NAME="InvItem_issueCost" SIZE="15" MAXLENGTH="15" tabindex=26
							value="<%=bd_issue_cost%>" style="text-align: right"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-unitCost")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-unitCost", "Unit Cost", true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_cost" SIZE="15"
							MAXLENGTH="15" tabindex=30 value="<%=bd_cost%>"
							style="text-align: right"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-unitOfOrder")%>>
						<td nowrap align="right"><a
							href="javascript: browseLookup('InvItem_unitOfOrder', 'unitoforder'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-unitOfOrder", "Unit Of Order",
									true)%>:</A>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_unitOfOrder" SIZE="15"
							MAXLENGTH="15" tabindex=35 value="<%=invItem.getUnitOfOrder()%>"
							onchange="upperCase(this);"></td>
					</tr>
					<%
						if (s_duomRequired.equalsIgnoreCase("Y")) {
					%>
					<tr <%=HtmlWriter.isVisible(oid, "inv-duomUmCode")%>>
						<td nowrap align="right"><a
							href="javascript: browseLookup('InvItem_duomUmCode', 'unitoforder'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-duomUmCode",
										"Secondary UM", true)%>:</A>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_duomUmCode" SIZE="15"
							MAXLENGTH="15" tabindex=35 value="<%=invItem.getDuomUmCode()%>"
							onchange="upperCase(this);"></td>
					</tr>
					<%
						}
					%>
					<tr <%=HtmlWriter.isVisible(oid, "inv-averageCost")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-averageCost", "Average Cost")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_averageCost" SIZE="15"
							MAXLENGTH="15" tabindex=40 value="<%=bd_average_cost%>"
							style="text-align: right" disabled></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-lastCost")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-lastCost", "Last Cost")%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_lastCost" SIZE="15"
							MAXLENGTH="15" tabindex=45 value="<%=bd_last_cost%>"
							style="text-align: right" disabled></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-variance")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-variance", "Variance", true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_variance" SIZE="15"
							MAXLENGTH="15" tabindex=50 value="<%=bd_variance%>"
							style="text-align: right"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-receiptOptions")%>>
						<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-receiptOptions",
									"Receipt Option")%>:&nbsp;</td>
						<td><select name="InvItem_receiptRequired" tabindex=55>
							<option <%if (s_receipt_required.equals("R")) {%> selected
								<%}%> value="R">Receipt Required</option>
							<option <%if (s_receipt_required.equals("P")) {%> selected
								<%}%> value="P">Previously Received</option>
							<%
								if (!oid.equals("MSG07P")) {
							%>
							<option <%if (s_receipt_required.equals("N")) {%> selected
								<%}%> value="N">No Receipt Required</option>
							<%
								}
							%>
							<option <%if (s_receipt_required.equals("E")) {%> selected
								<%}%> value="E">End User Receipt</option>
						</select></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST11")%>>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf11Code', 'ST11'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-ST11", "UDF 11", true)%>:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf11Code" SIZE="15"
							MAXLENGTH="15" tabindex=60 value="<%=invItem.getUdf11Code()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST10")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-ST10", "UDF 10", true)%>:&nbsp;</td>
						<td><tsa:input type="yesnoradio" name="InvItem_udf10Code"
							value="<%=invItem.getUdf10Code()%>" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST05")%>>
						<td nowrap align="right">
						<%
							if (DictionaryManager.isLink(oid, "inv-ST05")) {
						%> <a
							href="javascript: browseStd('InvItem_udf5Code','ST05');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST05", "UDF 5", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<tsa:label labelName="inv-ST05" defaultString="UDF 5"
							checkRequired="true" />
						:&nbsp;
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf5Code"
							tabIndex="70" value="<%=invItem.getUdf5Code()%>"
							labelName="inv-ST05" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST12")%>>
						<%
							if (DictionaryManager.isLink(oid, "inv-ST12")) {
						%>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf12Code','ST12');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST12", "UDF 12", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<td nowrap align="right"><tsa:label labelName="inv-ST12"
							defaultString="UDF 12" checkRequired="true" />:&nbsp;</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf12Code"
							tabIndex="75" value="<%=invItem.getUdf12Code()%>"
							labelName="inv-ST12" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST04")%>>
						<%
							if (DictionaryManager.isLink(oid, "inv-ST04")) {
						%>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf4Code','ST04');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST04", "UDF 4", true)%>:&nbsp;</td>
						<%
							} else {
						%>
						<td nowrap align="right"><tsa:label labelName="inv-ST04"
							defaultString="UDF 4" checkRequired="true" />:&nbsp;</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf4Code"
							tabIndex="80" value="<%=invItem.getUdf4Code()%>"
							labelName="inv-ST04" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST01")%>>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf1Code','ST01');"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-ST01", "UDF 01", true)%>:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf1Code" SIZE="15"
							MAXLENGTH="15" tabindex=85 value="<%=invItem.getUdf1Code()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
				</table>
				</td>
				<td valign="top">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr <%=HtmlWriter.isVisible(oid, "inv-language")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-language", "Language", true)%>:&nbsp;</td>
						<td><select name="InvItem_source" tabindex=95>
							<option value="(Default)"
								<%if (s_source.equals("(Default)")) {%> SELECTED <%}%>>(Default)</option>
							<option value="" <%if (s_source.equals("")) {%> SELECTED
								<%}%>></option>
						</select></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-abcCode")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-abcCode", "ABC Code", true)%>:&nbsp;</td>
						<td><tsa:input type="mintext" name="InvItem_abcCode"
							tabIndex="100" value="<%=invItem.getAbcCode()%>"
							labelName="inv-abcCode" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-qytOnHand")%>>
						<td nowrap align="right">
						<div id="sumQtyOnHandA"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-qytOnHand", "Current SRR Inventory on-hand Quantity", true)%>:&nbsp;
						</div>
						</td>
						<td>
						<div id="sumQtyOnHandB">
							<input type="TEXT" name="InvItem_sumQtyOnHand" size="15" MAXLENGTH="15" tabIndex=107 value="<%=bd_sum_qtyOnHand%>" ONCHANGE="upperCase(this);" disabled/>
						</div>
						</td>					
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-totalMinOnHand")%>>
						<td nowrap align="right">
						<div id=totalMinOnHandA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-totalMinOnHand",
									"Total Min. On Hand")%>:&nbsp;</div>
						</td>
						<td>
						<div id=totalMinOnHandB><INPUT TYPE="TEXT"
							NAME="InvItem_mohTot" SIZE="15" MAXLENGTH="15" tabindex=105
							value="<%=bd_moh_total%>" style="text-align: right" disabled></div>
						</td>
					</tr>
					<tr
						<%=HtmlWriter.isVisible(oid,
							"inv-totalEconomicOrderQuantity")%>>
						<td nowrap align="right">
						<div id=totalEconomicOrderQuantityA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-totalEconomicOrderQuantity",
									"Total Econ. Order Qty")%>:&nbsp;</div>
						</td>
						<td>
						<div id=totalEconomicOrderQuantityB><INPUT TYPE="TEXT"
							NAME="InvItem_eoqTot" SIZE="15" MAXLENGTH="15" tabindex=110
							value="<%=bd_eoq_total%>" style="text-align: right" disabled></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-minOnHandMonths")%>>
						<td nowrap align="right">
						<div id=minOnHandMonthsA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-minOnHandMonths",
									"Min. On-Hand Months", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=minOnHandMonthsB><INPUT TYPE="TEXT"
							NAME="InvItem_mohMonths" SIZE="15" MAXLENGTH="15" tabindex=115
							value="<%=bd_moh_months%>" style="text-align: right"></div>
						</td>
					</tr>
					<tr
						<%=HtmlWriter.isVisible(oid,
							"inv-economicOrderQuantityMonths")%>>
						<td nowrap align="right">
						<div id=economicOrderQuantityMonthsA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-economicOrderQuantityMonths",
									"Econ. Order Qty. Months", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=economicOrderQuantityMonthsB><INPUT TYPE="TEXT"
							NAME="InvItem_eoqMonths" SIZE="15" MAXLENGTH="15" tabindex=120
							value="<%=bd_eoq_months%>" style="text-align: right"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-pullPackagingQuantity")%>>
						<td nowrap align="right">
						<div id=pullPackagingQuantityA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-pullPackagingQuantity",
									"Pull/Packaging Quantity", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=pullPackagingQuantityB><INPUT TYPE="TEXT"
							NAME="InvItem_pullIncrement" SIZE="15" MAXLENGTH="15"
							tabindex=125 value="<%=bd_pull_increment%>"
							style="text-align: right"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-lastBlanketOrderNumber")%>>
						<td nowrap align="right">
						<div id=poNumberA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-lastBlanketOrderNumber",
									"Last Blanket Order #", true)%>:&nbsp;</div>
						</td>
						<td>
						<div id=poNumberB><INPUT TYPE="TEXT" NAME="InvItem_poNumber"
							SIZE="15" MAXLENGTH="20" tabindex=130
							value="<%=invItem.getPoNumber()%>"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-buyer")%>>
						<td nowrap align="right">
						<div id=buyerA><a
							href="javascript: browseLookup('InvItem_buyerCode', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-buyer", "Buyer", true)%>:</a>&nbsp;</div>
						</td>
						<td>
						<div id=buyerB><INPUT TYPE="TEXT" NAME="InvItem_buyerCode"
							SIZE="15" MAXLENGTH="15" tabindex=135 value="<%=s_buyer_code%>"
							ONCHANGE="upperCase(this); getNewInfo('buyer', this);"></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-buyerName")%>>
						<td nowrap align="right">
						<div id=buyerNameA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-buyerName", "Buyer Name")%>:&nbsp;</div>
						</td>
						<td>
						<div id=buyerNameB><INPUT TYPE="TEXT" NAME="as_buyerName"
							SIZE="15" value="<%=buyer.getDisplayName()%>" disabled></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemNumberSuperceded")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemNumberSuperceded",
									"Superceded by", true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_itemNumberSuperceded"
							SIZE="30" MAXLENGTH="30" tabindex=137
							value="<%=invItem.getItemNumberSuperceded()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-manufacturer")%>>
						<td nowrap align="right"><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "inv-mfgName", "Manufacturer",
											true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_mfgName" SIZE="25"
							MAXLENGTH="30" tabindex=140 value="<%=invItem.getMfgName()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-modelnumber")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-modelnumber", "Model #", true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_modelNumber" SIZE="25"
							MAXLENGTH="30" tabindex=142 value="<%=invItem.getModelNumber()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST02")%>>
						<td nowrap align="right">
						<%
							if (DictionaryManager.isLink(oid, "inv-ST02")) {
						%> <a
							href="javascript: browseStd('InvItem_udf2Code','ST02');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST02", "UDF 2", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST02", "UDF 2", true)%>:&nbsp;
						</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf2Code"
							id="InvItem_udf2Code" tabIndex="150"
							value="<%=invItem.getUdf2Code()%>" labelName="inv-ST02" /></td>
					</tr>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-shelfLifeRqd")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-shelfLifeRqd", "UDF 4", true)%>:&nbsp;</td>
						<td><INPUT TYPE="checkbox" NAME="c_checkbox"
							<%if (invItem.getShelfLifeRqd().equals("Y")) {%> checked <%}%>
							onclick="setCheckbox(frm.InvItem_shelfLifeRqd,0);"></td>
						<tsa:hidden name="InvItem_shelfLifeRqd"
							value="<%=invItem.getShelfLifeRqd()%>" />
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST03")%>>
						<%
							if (DictionaryManager.isLink(oid, "inv-ST03")) {
						%>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf3Code','ST03');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST03", "UDF 3", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<td nowrap align="right"><tsa:label labelName="inv-ST03"
							defaultString="UDF 3" checkRequired="true" />:&nbsp;</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf3Code"
							tabIndex="166" value="<%=invItem.getUdf3Code()%>"
							labelName="inv-ST03" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST07")%>>
						<%
							if (DictionaryManager.isLink(oid, "inv-ST07")) {
						%>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf7Code','ST07');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST07", "UDF 7", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<td nowrap align="right"><tsa:label labelName="inv-ST07"
							defaultString="UDF 07" checkRequired="true" />:&nbsp;</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf7Code"
							tabIndex="166" value="<%=invItem.getUdf7Code()%>"
							labelName="inv-ST07" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST06")%>>
						<%
							if (DictionaryManager.isLink(oid, "inv-ST06")) {
						%>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf6Code','ST06');"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-ST06", "UDF 6", true)%>:</a>&nbsp;</td>
						<%
							} else {
						%>
						<td nowrap align="right"><tsa:label labelName="inv-ST06"
							defaultString="UDF 6" checkRequired="true" />:&nbsp;</td>
						<%
							}
						%>
						<td><tsa:input type="mintext" name="InvItem_udf6Code"
							tabIndex="158" value="<%=invItem.getUdf6Code()%>"
							labelName="inv-ST06" onchange="upperCase(this);" /></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST08")%>>
						<td nowrap align="right"><a
							href="javascript: browseStd('InvItem_udf8Code','ST08');"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-ST08", "UDF 8", true)%>:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf8Code" SIZE="15"
							MAXLENGTH="15" tabindex=162 value="<%=invItem.getUdf8Code()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-ST09")%>>
						<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-ST09", "UDF 9", true)%>:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvItem_udf9Code" SIZE="15"
							MAXLENGTH="15" tabindex=162 value="<%=invItem.getUdf9Code()%>"
							ONCHANGE="upperCase(this);"></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>


		<hr width=475px align=center class=browseHR>

		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "inv-taxable")%> nowrap
					align="right" width=120px>
				<div id=taxableA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-taxable", "Taxable")%>:&nbsp;</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-taxable")%>>
				<div id=taxableB><INPUT TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_taxable.equals("Y")) {%> CHECKED <%}%> tabindex=180
					value="Y" onclick="setCheckbox(frm.InvItem_taxable,1);"> <tsa:hidden
					name="InvItem_taxable" value="<%=s_taxable%>" /></div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%> nowrap
					align="right" width=100px>
				<div id=chargeableA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-chargeable", "Chargeable")%>:</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-chargeable")%>>
				<div id=chargeableB><INPUT TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_chargeable.equals("Y")) {%> CHECKED <%}%> tabindex=182
					value="Y" onclick="setCheckbox(frm.InvItem_chargable,2);">
				<tsa:hidden name="InvItem_chargable" value="<%=s_chargeable%>" /></div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-kit")%> nowrap align="right"
					width=90px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-kit", "Kit")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-kit")%>><INPUT
					TYPE="CHECKBOX" NAME="c_checkbox" <%if (s_kit.equals("Y")) {%>
					CHECKED <%}%> tabindex=184 value="Y"
					onclick="setCheckbox(frm.InvItem_kit,3);"> <tsa:hidden
					name="InvItem_kit" value="<%=s_kit%>" /></td>
				<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%> nowrap
					align="right" width=105px>
				<div id=restrictedItemA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-restrictedItem",
									"Restricted Item")%>:</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-restrictedItem")%>>
				<div id=restrictedItemB><INPUT TYPE="CHECKBOX"
					NAME="c_checkbox" <%if (s_restricted_item.equals("Y")) {%> CHECKED
					<%}%> tabindex=186 value="Y"
					onclick="setCheckbox(frm.InvItem_restrictedItem,4);"> <tsa:hidden
					name="InvItem_restrictedItem" value="<%=s_restricted_item%>" /></div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%> nowrap
					align="right" width="120px"><tsa:label
					labelName="inv-critSparePart" defaultString="Critical Spare Part" />:</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-critSparePart")%>><input
					type="checkbox" name="c_checkbox"
					<%if (s_critSparePart.equals("Y")) {%> checked <%}%>
					tabindex="187" value="Y"
					onclick="setCheckbox(frm.InvItem_critSparePart,5);"> <tsa:hidden
					name="InvItem_critSparePart" value="<%=s_critSparePart%>" /></td>
			</tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "inv-asset")%> nowrap align="right"
					width=120px>
				<div id=assetA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-asset", "Asset")%>:&nbsp;</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-asset")%>>
				<div id=assetB><INPUT TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_asset_code.equals("Y")) {%> CHECKED <%}%> tabindex=188
					value="Y" onclick="setCheckbox(frm.InvItem_assetCode,6);">
				<tsa:hidden name="InvItem_assetCode" value="<%=s_asset_code%>" /></div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%> nowrap
					align="right" width=105px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-sAndSInterface",
									"S&S Interface")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-sAndSInterface")%>><INPUT
					TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_ss_interface.equals("Y")) {%> CHECKED <%}%> tabindex=189
					value="Y" onclick="setCheckbox(frm.InvItem_ssInterface,7);">
				<tsa:hidden name="InvItem_ssInterface" value="<%=s_ss_interface%>" />
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%> nowrap
					align="right" width=100px>
				<div id=capAssetA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-capProperty", "Cap. Asset")%>:</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-capProperty")%>>
				<div id=capAssetB><INPUT TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_cap_property.equals("Y")) {%> CHECKED <%}%> tabindex=190
					value="Y" onclick="setCheckbox(frm.InvItem_capProperty,8);">
				<tsa:hidden name="InvItem_capProperty" value="<%=s_cap_property%>" />
				</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%> nowrap
					align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-gfpProperty", "GFP")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-gfpProperty")%>><INPUT
					TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_gfp_property.equals("Y")) {%> CHECKED <%}%> tabindex=194
					value="Y" onclick="setCheckbox(frm.InvItem_gfpProperty,9);">
				<tsa:hidden name="InvItem_gfpProperty" value="<%=s_gfp_property%>" />
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%> nowrap
					align="right" width=100px><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-fgpProperty", "FGP")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-fgpProperty")%>><INPUT
					TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_fgp_property.equals("Y")) {%> CHECKED <%}%> tabindex=198
					value="Y" onclick="setCheckbox(frm.InvItem_fgpProperty,10);">
				<tsa:hidden name="InvItem_fgpProperty" value="<%=s_fgp_property%>" />
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%> nowrap
					align="right" width=100px>
				<div id=usageRecalcA><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-usageRecalc", "Usage Recalc")%>:</div>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "inv-usageRecalc")%>>
				<div id=usageRecalcB><INPUT TYPE="CHECKBOX" NAME="c_checkbox"
					<%if (s_usage_recalc.equals("Y")) {%> CHECKED <%}%> tabindex=200
					value="Y" onclick="setCheckbox(frm.InvItem_usageRecalc,11);">
				<tsa:hidden name="InvItem_usageRecalc" value="<%=s_usage_recalc%>" />
				</div>
				</td>
			</tr>
		</table>

		<hr width=475px align=center class=browseHR>
		<table border=0 cellpadding=1 cellspacing=0 width=475px align=center>
			<tr>
				<td width=100% valign=top>
				<table border=0>
					<tr>
						<td align=right><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "status", "Status")%>:&nbsp;</td>
						<td><select name="InvItem_status" onchange="setStatus();"
							tabIndex=250>
							<option value="01" <%if (invItem.getStatus().equals("01")) {%>
								selected <%}%>>Temporary</option>
							<option value="02" <%if (invItem.getStatus().equals("02")) {%>
								selected <%}%>>Permanent</option>
							<option value="03" <%if (invItem.getStatus().equals("03")) {%>
								selected <%}%>>Inactive</option>
							<option value="04" <%if (invItem.getStatus().equals("04")) {%>
								selected <%}%>>On Hold</option>
						</select></td>
					</tr>
					<tr>
						<td align=right>Date Entered:&nbsp;</td>
						<td><input type=text name="InvItem_dateEntered" tabindex=252
							size=15 maxlength=15
							value="<%=HiltonUtility.getFormattedDate(invItem.getDateEntered(),
							oid, userDateFormat)%>"
							disabled></td>
					</tr>
					<tr id="dateExpires">
						<td align=right>Date Expires:&nbsp;</td>
						<td><input type=text name="InvItem_dateExpires" tabindex=254
							size=15 maxlength=15
							value="<%=HiltonUtility.getFormattedDate(invItem.getDateExpires(),
							oid, userDateFormat)%>"
							onchange="checkDate(this);"> <a
							href="javascript: show_calendar('InvItem_dateExpires', '<%=userDateFormat%>');"><img
							src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
						</td>
					</tr>
				</table>
				</td>
				<td valign=top>
				<table border=0>
					<tr>
						<td align=right><a
							href="javascript: browseLookup('InvItem_owner', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "owner", "Owner")%>:</a>&nbsp;</td>
						<td><input type=text name="InvItem_owner" tabindex=256
							size=30 maxlength=15 value="<%=invItem.getOwner()%>"
							onchange="upperCase(this); getNewInfo('user', this);"></td>
					</tr>
					<tr>
						<td nowrap align=right>Owner Name:&nbsp;</td>
						<td><input type=text name="as_ownerName" tabindex=258 size=30
							value="<%=owner.getDisplayName()%>" disabled></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		</td>
		<td align="right" valign="top"><%--	if (s_action.equalsIgnoreCase("UPDATE")) { --%>
		<table border="1" cellpadding="0" cellspacing="0" width=150px>
			<tr>
				<td class=browseHdr>&nbsp;Item Options</td>
			</tr>
			<tr>
				<td>
				<table border="0" cellpadding=2 cellspacing=2 width="100%">
					<tr>
						<td>&nbsp;<a
							href="javascript: validateMe('VALIDATE'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-validate", "Validate", true)%></a></td>
					</tr>
					<tr>
						<td>&nbsp;<a href="javascript: comments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-comments", "Comments")%></a></td>
					</tr>
					<tr>
						<td>&nbsp;<a href="javascript: attachments(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-attachments", "Attachments")%></a></td>
					</tr>
					<%
						if (!(assetsActive && s_asset_code.equalsIgnoreCase("Y"))) {
					%>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemlocations")%>>
						<td>&nbsp;<a href="javascript: itemLocations(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-locations", "Locations",
										true)%></a></td>
					</tr>
					<%
						}
					%>
					<tr id="itemForm" <%=HtmlWriter.isVisible(oid, "inv-itemform")%>>
						<td>&nbsp;<a href="javascript:  itemForms(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemform", "Item Form", true)%></a></td>
					</tr>
					<tr id="itemProducts" <%=HtmlWriter.isVisible(oid, "inv-itemproducts")%>>
						<td>&nbsp;<a href="javascript:  browseProducts(); void(0);"><%=DictionaryManager
									.getLabelsInstance(oid, language).getLabel(
											oid, "inv-itemproducts",
											"Products", true)%></a></td>
					</tr>
					<tr id="itemReqions" <%=HtmlWriter.isVisible(oid, "inv-itemregions")%>>
						<td>&nbsp;<a href="javascript:  browseRegions(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemregions", "Regions", true)%></a></td>
					</tr>
					<!--   				<tr>
    					<td>&nbsp;<a href="javascript:  browseCatalogs(); void(0);">Catalogs</a></td>
    				</tr>
-->
					<tr <%=HtmlWriter.isVisible(oid, "inv-itembom")%>>
						<td>
						<div id="bom">&nbsp;<a
							href="javascript:  browseBomCheck(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itembom", "Bill of Material",
									true)%></a></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itembomtree")%>>
						<td>
						<div id="bomTree">&nbsp;<a
							href="javascript:  browseBomTree(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itembomtree",
									"Component Treeview", true)%></a></div>
						</td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemwhereused")%>>
						<td>
						<div id="whereUsed">&nbsp;<a
							href="javascript:  browseInvWhereUsed('inv-where-used'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemwhereused", "Where Used",
									true)%></a></div>
						</td>
					</tr>
					<%
						if (s_item_approvals.equalsIgnoreCase("Y")) {
					%>
					<tr>
						<td>&nbsp;<a href="javascript: viewHistoryItem(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-itemhistory",
										"Item History", true)%></a></td>
					</tr>
					<%
						}
					%>
					<tr <%=HtmlWriter.isVisible(oid, "inv-itemforcasthistory")%>>
						<td>&nbsp;<a
							href="javascript:  browseForecastHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-itemforcasthistory",
									"Forecast History", true)%></a></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-orderhistory")%>>
						<td>&nbsp;<a href="javascript:  browseInvPoHist(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-orderhistory", "Order History",
									true)%></a></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-receipthistory")%>>
						<td>&nbsp;<a
							href="javascript:  browseInvReceiptHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-receipthistory",
									"Receipt History", true)%></a></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-transferhistory")%>>
						<td>&nbsp;<a
							href="javascript:  browseInvReturnHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-transferhistory",
									"Transfer History", true)%></a></td>
					</tr>
					<tr <%=HtmlWriter.isVisible(oid, "inv-returnhistory")%>>
						<td>&nbsp;<a
							href="javascript:  browseInvReturnHistory(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "inv-returnhistory",
									"Return History", true)%></a></td>
					</tr>
					<%
						if (!(s_autoNumber.equals("Y"))) {
					%>
					<tr id="itemSaveAs" <%=HtmlWriter.isVisible(oid, "inv-saveas")%>>
						<td>&nbsp;<a href="javascript:  saveas(); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language)
								.getLabel(oid, "inv-saveas", "Save As", true)%></a></td>
					</tr>
					<%
						}
					%>
					<tr id="deleteItem" <%=HtmlWriter.isVisible(oid, "inv-deleteitem")%>>
						<td><a href="javascript: void(0);"
							ONCLICK="javascript: deleteItem();"> <img
							src="<%=contextPath%>/images/delete.gif" alt="Delete" border=0>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language)
							.getLabel(oid, "deleteItem", "Delete Item")%>
						</a></td>
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
<%
	if (!s_disable_save.equalsIgnoreCase("Y")) {
%>
<table border="0" cellpadding="0" cellspacing="0" width=600px>
	<tr>
		<td id="saveItem" width=50% align=center>
		<div id="pxbutton"><a href="javascript: saveItem(); void(0);">Save</a></div>
		</td>
		<td width=50% align=center>
		<div id="pxbutton"><a
			href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Cancel</a></div>
		</td>
	</tr>
</table>
<%
	}
%>
<br>

<%@ include file="/system/footer.jsp"%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var commentAttach = false;
	var status = "<%=invItem.getStatus()%>";
	var navMenu = getNavCookie("navArray");
	var itemType = "<%=invItem.getActionCode()%>" ;
	initTableLookup("<%=requestURLPath%>","<%=oid%>","${esapi:encodeForJavaScript(userId)}") ;
	var pageObject

	setFormTypeFields() ;

	//if (navMenu.indexOf("Item #") < 0)
	//{
		setNavCookie("/inventory/inv_item.jsp", "InvItemRetrieveById", "Item # <%=invItem.getItemNumber()%>", true);
	//}
	setStatus();

	function invItemSave()
	{
		var r  ;
		if (isEmpty(frm.InvItem_itemNumber.value))
		{
			alert('You must enter a valid Item Number.');
			//frm.itemAction.value="UPDATE";
			//doSubmit('/inventory/inv_item_validate.jsp', 'InvItemAdd;InvItemValidate');
		} else {
	<%if (s_item_approvals.equalsIgnoreCase("Y")) {%>
				if (!commentAttach)
				{
					frm.itemAction.value="UPDATE";
					frm.InvItem_status.value="03";
					doSubmit('/inventory/inv_item.jsp', 'InvItemAdd;InvItemRetrieveById');
				}
	<%} else {%>
			if (!commentAttach)
			{
				frm.itemAction.value="UPDATE";
				doSubmit('/inventory/inv_item.jsp', 'InvItemAdd;InvItemValidate');
			}
	<%}%>
		  }
	}

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

	function highlightItem(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "selectedRow");
	}

	function removeItemHighlight(row) {
		var myRow;

		if (row == 0) {
			myRow = document.all.itemRow.rows(row);
		}
		else {
			myRow = document.all.itemRows.rows(row - 1);
		}

		setRowClassName(myRow, "browseRow");
	}

	function checkOnlyOne(pos)
	{
		if(frm.c_checkbox[pos].checked)
		{
			if(pos == 8)
			{
				frm.c_checkbox[9].checked = false;
				setCheckbox(frm.InvItem_gfpProperty, 9);
				frm.c_checkbox[10].checked = false;
				setCheckbox(frm.InvItem_fgpProperty, 10);
			}
			else if(pos == 9)
			{
				frm.c_checkbox[8].checked = false;
				setCheckbox(frm.InvItem_capProperty, 8);
				frm.c_checkbox[10].checked = false;
				setCheckbox(frm.InvItem_fgpProperty, 10);
			}
			else if(pos == 10)
			{
				frm.c_checkbox[8].checked = false;
				setCheckbox(frm.InvItem_capProperty, 8);
				frm.c_checkbox[9].checked = false;
				setCheckbox(frm.InvItem_gfpProperty, 9);
			}
		}
	}

	function saveItem()
	{
		if (umConversionCheck()) {
		if (frm.itemAction.value == "CREATE")
		{
<%if (s_itemCrossRef.equalsIgnoreCase("Y")) {%>
			if (! showDuplicates()) {
				invItemSave() ;
			}
<%} else {%>
			invItemSave();
<%}%>
		}
		else
		{
			frm.itemAction.value="UPDATE";
			//doSubmit('/inventory/inv_item.jsp', 'InvItemUpdate');
			validateMe(frm.itemAction.value);
		}
  	  }
	}

	function saveProcess()
	{
		var handler = "";
		frm.itemAction.value="UPDATE";
		if (frm.itemAction.value == "CREATE")
		{
			handler = "InvItemAdd";
		}
		else
		{
			handler = "InvItemUpdate";
		}
		return handler;
	}

	function browse(x)
	{
		var updateHandler = saveProcess();

		if (x == 'regions')
		{
			setOriginalFilter("StdTable_id_tableType", "=", "STAT");
			frm.browseName.value = 'stdtable';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_regions.jsp', updateHandler + ';InvFormStateRetrieveByItem;BrowseRetrieve');
		}
		else if (x == 'products')
		{
			setOriginalFilter("StdTable_id_tableType", "=", "PROD");
			frm.browseName.value = 'stdtable';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_products.jsp', updateHandler + ';InvFormProductRetrieveByItem;BrowseRetrieve');
		}
		else if (x == 'catalogs')
		{
			frm.browseName.value = 'inv-catalog';
			var newInputField = "<input type='hidden' name='pageSize' value='-1'>";
 		    setHiddenFields(newInputField);
			doSubmit('/inventory/inv_item_catalogs.jsp', updateHandler + ';InvFormCatalogRetrieveByItem;BrowseRetrieve');
		}
		else
		{
    		if (x == 'inv-pohist')
    		{
				setAdvancedFilter("PoLine_itemNumber", "=", "<%=s_item_number%>", "OR", "Y", "N");
				setAdvancedFilter("PoLine_altItemNumber", "=", "<%=s_item_number%>", "", "Y", "N");
    		}
    		if (x == 'inv-transfer-history')
    		{
    			setOriginalFilter("RequisitionLine_itemNumber", "=", "<%=s_item_number%>");
    		}
    		if (x == 'inv-receipt-history')
    		{
				setAdvancedFilter("PoLine_itemNumber", "=", "<%=s_item_number%>", "OR", "Y", "N");
				setAdvancedFilter("PoLine_altItemNumber", "=", "<%=s_item_number%>", "OR", "Y", "N");
    		}
    		if (x == 'inv-return-history')
    		{
    			setOriginalFilter("InvReturn_itemNumber", "=", "<%=s_item_number%>");
    		}
    		if (x == 'inv-where-used')
    		{
    			setOriginalFilter("BomComponent_componentItem", "=", "<%=s_item_number%>");
    		}

			frm.browseName.value = x;
    		doSubmit('/browse/browse_inv_item_history.jsp', updateHandler + ';BrowseRetrieve');
    	}
	}

	function bomCheck() {
	  if (frm.InvItem_actionCode.value == 'M') {
			doSubmit('/inventory/bom_master.jsp', 'BomMethodRetrieveByItem');
	  } else {
	  		alert("Bill of Material option is only valid for Action Code MAKE!") ;
  	  }
	}

	function bomTree() {
	  if (frm.InvItem_actionCode.value == 'M')
	  {
			doSubmit('/inventory/bom_component_tree.jsp', 'BomComponentRetrieveTree');
	  } else {
	  		alert("Bill of Material option is only valid for Action Code MAKE!") ;
  	  }
  	}

	function validateItem(action)
	{
<%if (s_item_approvals.equalsIgnoreCase("Y")) {%>
			frm.InvItem_status.value="03";
			doSubmit('/inventory/inv_routing_list.jsp', 'InvItemUpdate;InvItemRetrieveById');
<%} else {%>
			doSubmit('/inventory/inv_item_validate.jsp', 'InvItemUpdate;InvItemValidate');
<%}%>
	}

	function validateInv() {
		if (isEmpty(frm.InvItem_itemNumber.value)) {
			alert("You must enter a valid item number");
			return false;
		}
	    saveItem();
		return true;
	}

	function validateMe(action)
	{
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
				alert('You must save the iventory item.'); return false;
			<%} else {%>
				frm.validate.value = "TRUE";
				frm.validateAction.value = action;
				validateItem(action);

			<%}%>
		}
	}

	function saveas()
	{
	    popupParameters = "formtype=INVITEM;action=saveas";
	    doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	}

	function setStatus()
	{
		status = frm.InvItem_status[frm.InvItem_status.selectedIndex].value;
		if ((status == "01"))
		{
			displayArea("dateExpires");
		}
		else
		{
			hideArea("dateExpires");
		}
	}

	function deleteItem() {
		if (confirm("Are you sure you want to delete \"" + frm.InvItem_itemNumber.value  + "\"?")){
			frm.browseName.value = 'invitem-admin';
			doSubmit('/browse/browse.jsp','InvItemDelete;BrowseRetrieve');
		}
	}

	function setFormTypeFields() {
	   itemType = frm.InvItem_actionCode.value ;
		if (itemType == "M") {
		// Hide fields not required for Make (BOM) items
			displayArea("bom") ;
			displayArea("whereUsed") ;
			displayArea("bomTree") ;

			hideArea("unitOfIssueA") ;
			hideArea("unitOfIssueB") ;
			hideArea("conversionFactorA") ;
			hideArea("conversionFactorB") ;
			hideArea("issueCostA") ;
			hideArea("issueCostB") ;
			hideArea("taxableA") ;
			hideArea("taxableB") ;
			hideArea("chargeableA") ;
			hideArea("chargeableB") ;
			hideArea("assetA") ;
			hideArea("assetB") ;
			hideArea("capAssetA") ;
			hideArea("capAssetB") ;
			hideArea("usageRecalcA") ;
			hideArea("usageRecalcB") ;
			hideArea("restrictedItemA") ;
			hideArea("restrictedItemB") ;
			hideArea("totalMinOnHandA") ;
			hideArea("totalMinOnHandB");
			hideArea("totalEconomicOrderQuantityA") ;
			hideArea("totalEconomicOrderQuantityB") ;
			hideArea("minOnHandMonthsA");
			hideArea("minOnHandMonthsB");
			hideArea("economicOrderQuantityMonthsA") ;
			hideArea("economicOrderQuantityMonthsB") ;
			hideArea("pullPackagingQuantityA") ;
			hideArea("pullPackagingQuantityB") ;

			hideArea("poNumberA") ;
			hideArea("poNumberB") ;
			hideArea("buyerA") ;
			hideArea("buyerB") ;
			hideArea("buyerNameA") ;
			hideArea("buyerNameB") ;

		} else {
		// Show all fields
			hideArea("bom") ;
			hideArea("whereUsed") ;
			hideArea("bomTree") ;
			displayArea("unitOfIssueA") ;
			displayArea("unitOfIssueB") ;
			displayArea("conversionFactorA") ;
			displayArea("conversionFactorB") ;
			displayArea("issueCostA") ;
			displayArea("issueCostB") ;
			displayArea("taxableA") ;
			displayArea("taxableB") ;
			displayArea("chargeableA") ;
			displayArea("chargeableB") ;
			displayArea("assetA") ;
			displayArea("assetB") ;
			displayArea("capAssetA") ;
			displayArea("capAssetB") ;
			displayArea("usageRecalcA") ;
			displayArea("usageRecalcB") ;
			displayArea("restrictedItemA") ;
			displayArea("restrictedItemB") ;
			displayArea("totalMinOnHandA") ;
			displayArea("totalMinOnHandB");
			displayArea("totalEconomicOrderQuantityA") ;
			displayArea("totalEconomicOrderQuantityB") ;
			displayArea("minOnHandMonthsA");
			displayArea("minOnHandMonthsB");
			displayArea("economicOrderQuantityMonthsA") ;
			displayArea("economicOrderQuantityMonthsB") ;
			displayArea("pullPackagingQuantityA") ;
			displayArea("pullPackagingQuantityB") ;
			displayArea("poNumberA") ;
			displayArea("poNumberB") ;
			displayArea("buyerA") ;
			displayArea("buyerB") ;
			displayArea("buyerNameA") ;
			displayArea("buyerNameB") ;
		}
	}

	function autoNumberCheck(fld, isChecked) {
		if (isChecked) {
		   fld.value = "" ;
		   fld.readonly = true ;
		   fld.disabled = true ;
		   frm.InvItem_description.focus() ;
		} else {
		    fld.readonly = false ;
			fld.disabled = false ;
			fld.focus() ;
		}
	}

	function itemDuplicateCheck(idObject) {
	    var idValue = idObject.value ;
	    if (tableLookup(idValue, "invitem-retrieve-by.xml", "invItem", "InvItem_description" )) {
	    	alert(getRowCount()) ;
	    	alert ( getResponseValue(1,"Description", "") ) ;
	    	alert ( getResponseValue(1,"Description", "") ) ;

            alert ( "Item number [" + frm.InvItem_itemNumber.value + "] already exists in inventory! \n\n");

			frm.InvItem_itemNumber.value = "";
			frm.InvItem_itemNumber.focus() ;
		}
	}

	function updateDescription() {
	    var desc = frm.InvItem_description.value ;
	    var altStr = "" ;
	    var start = desc.toUpperCase().indexOf(" NSN:") ;
		if ( start < 0) {
			start = desc.toUpperCase().indexOf(" ALT:") ;
		}
		if (start >= 0) {
			desc = desc.substring(0, start) ;
		}

		if (! isEmpty(frm.InvItem_nsnNumber.value)) {
			altStr = altStr + " NSN: " + frm.InvItem_nsnNumber.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo1.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo1.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo2.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo2.value ;
		}
		if (! isEmpty(frm.InvItem_altPartNo3.value)) {
		    if (altStr.length > 0) {
		    	altStr = altStr + "," ;
		    }
			altStr = altStr + " ALT: " + frm.InvItem_altPartNo3.value ;
		}
		frm.InvItem_description.value = desc + altStr ;
	}

	function showDuplicates() {
			var nsnNumber = frm.InvItem_nsnNumber.value;
			var altPartNo1 = frm.InvItem_altPartNo1.value;
			var altPartNo2 = frm.InvItem_altPartNo2.value;
			var altPartNo3 = frm.InvItem_altPartNo3.value;
			popupParameters = "" ;
			if (! isEmpty(nsnNumber)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + nsnNumber + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + nsnNumber + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo1)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo1 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo1 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo2)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo2 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo2 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(altPartNo3)) {
				popupParameters = popupParameters + "colname=ItemCrossRef_altItemNumber;operator==;filter_txt=" + altPartNo3 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
				popupParameters = popupParameters + "colname=ItemCrossRef_description;operator==;filter_txt=" + altPartNo3 + ";logicalOperator=OR;originalFilter=Y;sort=N;" ;
			}
			if (! isEmpty(popupParameters)) {
				popupParameters = popupParameters + "formField=InvItem_itemNumber;browseName=itemcrossref-list;allowBrowse=" + frm.allowBrowse.value;

				doSubmitToLookup('browse/browse_itemcrossref_list.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=600px') ;
				return true ;
			} else {
				return false ;
			}

	}

	function umValidation(idObject) {
	    var idValue = idObject.value ;
	    pageObject = idObject ;
	    if (! tableLookup(idValue, "unitofmeasure-retrieve-by-id.xml", "unitOfMeasure", "UnitOfMeasure_umCode" ) ) {
	    	alert("Unit of measure code is invalid!") ;
	    	setTimeout( 'setFocusDelayed()', 100 );
	    	return false ;
	    }
	    return true ;
	}

	function umConversionCheck() {
	    var ok = true ;
	    var uoo = frm.InvItem_unitOfOrder.value ;
	    var uoi = frm.InvItem_unitOfIssue.value ;
	    if (! (frm.InvItem_unitOfIssue.value == frm.InvItem_unitOfOrder.value)) {
	  		ok=confirm("Warning!\n\nThe UNIT OF ORDER (" + uoo + ") and UNIT OF ISSUE (" + uoi + ") have different values! Please verify the conversion factor is correct for this combination. Click ok if the values reflect the correct conversion factor for this combination.\n\nExample: Unit Of Issue = EA    Unit Of Order = HU   (Conversion Factor = 100)");
	    }

	    return ok ;
	}

	function setFocusDelayed() {
		pageObject.focus() ;
	}

	function browseHistoryItem()
	{
		popupParameters = "browseName=invhistory" + ";allowBrowse=Y";
		popupParameters = popupParameters + (";colname=HistoryLog_icHeader;operator==;filter_txt='" + frm.ApprovalLog_icHeader.value + "';logicalOperator=AND;originalFilter=N;sort=N;");
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=800', 'HEIGHT=700');
	}

	function comments()
	{
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
				commentAttach = true;
				if (frm.itemAction.value == "CREATE") {
					if (validateInv()) {
						frm.itemAction.value = "UPDATE";
						doSubmit('/inventory/inv_notes.jsp', 'InvItemAdd;DocCommentRetrieveByLine');
					}
				}
				else {
					if (validateInv()) {
						doSubmit('/inventory/inv_notes.jsp', 'InvItemUpdate;DocCommentRetrieveByLine');
					}
				}
			<%}%>
		}
	}

	function attachments()
	{
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
				commentAttach = true;
				if (frm.itemAction.value == "CREATE") {
					if (validateInv()) {
						frm.itemAction.value = "UPDATE";
						doSubmit('/inventory/inv_line_attachments.jsp', 'InvItemAdd;DocAttachmentRetrieveByLine');
					}
				}
				else {
					if (validateInv()) {
						doSubmit('/inventory/inv_line_attachments.jsp', 'InvItemUpdate;DocAttachmentRetrieveByLine');
					}
				}
			<%}%>
		}
	}

	function itemLocations() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			doSubmit('/inventory/inv_item_locations.jsp', 'InvItemUpdate;InvLocationRetrieveByItem');
			<%}%>
		}
	}

	function itemForms() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			doSubmit('/inventory/forms/inv_item_form.jsp', 'InvItemUpdate;InvFormDataRetrieveById');
			<%}%>
		}
	}

	function browseProducts() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('products');
			<%}%>
		}
	}

	function browseRegions() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('regions');
			<%}%>
		}
	}

	function browseCatalogs() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('catalogs');
			<%}%>
		}
	}

	function browseBomCheck() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			bomCheck();
			<%}%>
		}
	}

	function browseBomTree() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			bomTree();
			<%}%>
		}
	}

	function browseInvWhereUsed() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('inv-where-used');
			<%}%>
		}
	}

	function viewHistoryItem() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browseHistoryItem();
			<%}%>
		}
	}

	function browseForecastHistory() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			doSubmit('/inventory/forecast_history.jsp', 'InvItemUpdate;InvItemDetailUsage');
			<%}%>
		}
	}

	function browseInvPoHist() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
				alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('inv-pohist');
			<%}%>
		}
	}

	function browseInvReceiptHistory() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('inv-receipt-history');
			<%}%>
		}
	}

	function browseInvTransHistory() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
			alert('You must save the iventory item.'); return false;
			<%} else {%>
			browse('inv-transfer-history');
			<%}%>
		}
	}

	function browseInvReturnHistory() {
		if(isItemNumberValid()) {
			<%if (s_action.equalsIgnoreCase("CREATE")) {%>
				alert('You must save the iventory item'); return false;
			<%} else {%>
			browse('inv-return-history');
			<%}%>
		}
	}

	function isItemNumberValid() {
		if (isEmpty(frm.InvItem_itemNumber.value)) {
			alert("You must enter a valid item number");
			return false;
		}
		return true;

	}

	function thisLoad()
	{
		var invAccess = <%=role.getAccessRights("INV")%>;
		f_StartIt();
		if (invAccess <= 1)
		{
			$('#saveItem, #itemSaveAs, #deleteItem, #itemForm, #itemProducts, #itemRegions').hide();
			checkInputSettings();
		}
	}

</SCRIPT>