<%
	String packageTypes = (String) request.getAttribute("PackagePricing_packageType");
	List packagePricingList = (List) request.getAttribute("packagePricingList");
	List organizationPackageList = (List) request.getAttribute("organizationPackageList");
	BigDecimal	currentIcPackage = (BigDecimal) request.getAttribute("currentIcPackage");
	PackagePricing defaultPackage = null;
	BigDecimal bdRenewalPrice = new BigDecimal(0);
	Map selectedPackages = new HashMap();

	if (packagePricingList == null) {
		packagePricingList = new ArrayList();
	}
	if (organizationPackageList == null) {
		organizationPackageList = new ArrayList();
	}
	if (organizationPackageList.size() == 0 && packagePricingList.size() > 0) {
		PackagePricing packagePricing = (PackagePricing) packagePricingList.get(0);
		selectedPackages.put(packagePricing.getIcPackage(), new Integer(1));
	}
	if (packagePricingList.size() > 1) {
		defaultPackage = (PackagePricing) packagePricingList.get(1);
		if (HiltonUtility.isEmpty(packageTypes)) {
			packageTypes = defaultPackage.getPackageType();
		}
	}
	if (HiltonUtility.isEmpty(packageTypes)) {
		packageTypes = "G";
	}
	if (packageTypes.equals("G")) {
		PackagePricing addUsersOnly = new PackagePricing();
		addUsersOnly.setIcPackage(new BigDecimal(0));
		addUsersOnly.setPackageName("Purchase Additional Users");
		addUsersOnly.setPackageDescription("You can purchase additional requisitioners or buyers as needed.  Your total cost will be calculated based on the number and types of users selected.");
		addUsersOnly.setPackageType("A");

		packagePricingList.add(addUsersOnly);
	}
/*	put organizationPackageList into a Map based on icPackage */
//	for (int iop = 0; iop < organizationPackageList.size(); iop++) {
//		OrganizationPackage op = (OrganizationPackage) organizationPackageList.get(iop);
		//selectedPackages.put(op.getIcPackage(), op.getQuantity());
//	}

	if (orgStatus.equals("02") && daysToRenewal.compareTo(new BigDecimal(1)) > 0) {
		proratedMsg = "The pro-rated price listed below is based on the remainder of your current year's subscription (" + daysToRenewal + " days).  This is the amount that you will be charged if you decide to upgrade today.";
	}
%>

	<!-- start rounded corners -->
	<div id="container" style="width: 100%; align:center; margin:10;">
	<b class="rtop">
	  <b class="r1"></b> <b class="r2"></b> <b class="r3"></b> <b class="r4"></b>
	</b>
	<table cellpadding=0 cellspacing=2 border=0 class=browseHdr width=100%>
	<tr>
		<td>
			<table border=0 cellpadding=2 cellspacing=0 width=100%>
			<tr>
				<td class="browseHdr" width="15px">&nbsp;</td>
				<td class="browseHdr" nowrap id="packageOptionLabel"></td>
				<td class="browseHdr" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioners", "Requisitioners")%></td>
				<td class="browseHdr" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyers", "Buyers")%></td>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "annualprice", "Annual Price")%></td>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "annualsavings", "Annual Savings")%></td>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prorated", "Pro-rated Price")%></td>
<%	if (packageTypes.equals("I")) {%>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "extendedPrice", "Extended Price")%></td>
<%	} else {%>
				<td class="browseHdr" width="1px">&nbsp;</td>
<%	}%>
				<td class="browseHdr" width="5px">&nbsp;</td>
			</tr>
<%
		boolean groupFound = false;
		int upgradePkg = packagePricingList.size();
		int counter = 0;

		if (currentIcPackage != null && (currentIcPackage.compareTo(new BigDecimal(0)) == 0 || currentGroupPackage.toUpperCase().startsWith("FREE"))) {
			upgradePkg = 0;
		}
		for (int i = 0; i < packagePricingList.size(); i++) {
			PackagePricing packagePricing = (PackagePricing) packagePricingList.get(i);
			String packagePrice = "";
			String proratedPrice = "";
			String packageSavings = "";
			BigDecimal bdPackagePrice = HiltonUtility.ckNull(packagePricing.getPackagePrice());
			BigDecimal bdPackageSavings = HiltonUtility.ckNull(packagePricing.getPackageSavings());
			BigDecimal bdProratedPrice = HiltonUtility.ckNull(packagePricing.getProratedPrice(daysToRenewal, bdRenewalPrice));

			if (!packagePricing.getPackageType().equals("A") && bdPackagePrice.compareTo(new BigDecimal(0)) == 0) {
				if (upgradePkg == i) {
					upgradePkg++;
				}
				continue;
			} else {
				packagePrice = "US " + HiltonUtility.getCurrency(bdPackagePrice, "USD", oid);
			}
			if (bdProratedPrice.compareTo(new BigDecimal(0)) == 0) {
				bdProratedPrice = bdPackagePrice;
			}
 			if (packageTypes.equals("G") && !groupFound) {
 				proratedPrice = "N/A";
 			} else if (!packagePricing.getPackageType().equals("A")) {
 				proratedPrice = "US " + HiltonUtility.getCurrency(bdProratedPrice, "USD", oid);
 			} else {
 				proratedPrice = packagePrice;
 			}
			if (bdPackageSavings.compareTo(new BigDecimal(0)) == 0) {
				packageSavings = "-";
			} else {
				packageSavings = "<font class=red>US " + HiltonUtility.getCurrency(bdPackageSavings, "USD", oid) + "</font>";
 			}

			int qtySelected = 0;
			try {
				qtySelected = ((Integer) selectedPackages.get(packagePricing.getIcPackage())).intValue();
			} catch (Exception e1) {
			}

			if (counter == 0) {%>
			<tr class="browseRow"><td colspan=9 height=5px><img src="<%=contextPath%>/images/none.gif" border=0 height=5px></td></tr>
		<%	} else {%>
			<tr class="browseRow"><td colspan=9><hr size=1 width=100%></td></tr>
		<%	}%>
			<tr class="browseRow" height="18px">
<%			if (packagePricing.getPackageType().equals("G") || packagePricing.getPackageType().equals("A")) {%>
				<td class="browseRow" align=center valign="middle"><input type=radio name="packageSelection" value="<%=counter%>" <% if (upgradePkg == i) {%> checked<%} else if (upgradePkg > i) {%> disabled<%}%>></td>
<%			} else {%>
				<td class="browseRow" align=center valign="middle"><input type=text name="as_packageQty" value="<%=qtySelected%>" style="text-align:right;" size=5 onchange="calculatePrices();"></td>
<%			}%>
				<td class="browseRow" valign="middle"><b><a href="javascript: toggleDetails(<%=counter%>); void(0);"><%=packagePricing.getPackageName()%></b></td>
<%			if (packagePricing.getPackageType().equals("A")) {%>
				<td class="browseRow" colspan=2>&nbsp;</td>
				<td class="browseRow" valign="middle" nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "toBeCalculated", "To Be Calculated")%></td>
				<td class="browseRow" colspan=2>&nbsp;</td>
<%			} else {%>
				<td class="browseRow" align="center" valign="middle" nowrap><%=packagePricing.getRequisitionerCount()%></td>
				<td class="browseRow" align="center" valign="middle" nowrap><%=packagePricing.getBuyerCount()%></td>
				<td class="browseRow" align="right" valign="middle" nowrap><%=packagePrice%></td>
				<td class="browseRow" align="right" valign="middle" nowrap><%=packageSavings%></td>
				<td class="browseRow" align="right" nowrap><%=proratedPrice%></td>
<%			}
			if (packageTypes.equals("I")) {%>
				<td class="browseRow" align="right" nowrap><input type=text name="as_extendedPrice" value="US $ 0.00" style="text-align:right;border:none;" onFocus="this.blur();"></td>
<%			} else {%>
				<td class="browseRow">&nbsp;</td>
<%			}%>
				<td class="browseRow">&nbsp;
					<tsa:hidden name="as_icPackage" value="<%=packagePricing.getIcPackage()%>"/>
					<tsa:hidden name="as_packageName" value="<%=packagePricing.getPackageName()%>"/>
					<tsa:hidden name="as_packagePrice" value="<%=packagePricing.getPackagePrice()%>"/>
					<tsa:hidden name="as_proratedPrice" value="<%=bdProratedPrice%>"/>
				</td>
			</tr>
			<tr class="browseRow">
				<td colspan=7>
					<div id="details<%=counter%>" style="visibility:hidden;display:none;" class="browseRow">
					<table border=0 cellpadding=0 cellspacing=0 width=80%>
					<tr><td width=50px></td><td><%=packagePricing.getPackageDescription()%></td></tr>
					</table>
					</div>
				</td>
			</tr>

<%			if (currentIcPackage != null && packagePricing.getIcPackage().compareTo(currentIcPackage) == 0) {
				groupFound = true;
				upgradePkg = i + 1;
			}
			counter++;
		} %>
			<tr class="browseRow"><td colspan=7 height=5px><img src="<%=contextPath%>/images/none.gif" border=0 height=5px></td></tr>
			</table>
		</td>
	</tr>
	</table>
	<b class="rbottom">
	  <b class="r4"></b> <b class="r3"></b> <b class="r2"></b> <b class="r1"></b>
	</b>
	</div>
	<!-- end rounded corners -->
<%	if (packageTypes.equals("I")) {%>
	<div style="width: 100%; align:center; margin:10;">
	<table border=0 width=100% cellpadding=0 cellspacing=0>
	<tr>
		<td align=right><b>Total Charges:</b><input type=text name="as_totalCharges" value="US $ 0.00" style="text-align:right;border:none;font-weight:bold"></td>
		<td width=8px>&nbsp;</td>
	</tr>
	</table>
	</div>
<%	}%>

<!-- dummy fields to prevent js errors -->
<tsa:hidden name="as_icPackage" value=""/>
<tsa:hidden name="as_packageName" value=""/>
<tsa:hidden name="as_packagePrice" value=""/>
<tsa:hidden name="as_proratedPrice" value=""/>
<tsa:hidden name="as_packageQty" value=""/>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function showDetails(row) {
		displayArea("details" + row);
	}
	function hideDetails(row) {
		hideArea("details" + row);
	}
	function toggleDetails(row) {
		var detailArea = document.getElementById("details" + row);
		if (detailArea.style.visibility == "hidden") {
			showDetails(row);
		} else {
			hideDetails(row);
		}
	}
	function calculatePrices() {
		var totalCharge = 0;
		var rowcount = document.all.as_proratedPrice.length - 1;

		for (var i = 0; i < rowcount; i++) {
			var proratedPrice = eval(nformat(frm.as_proratedPrice[i].value,2));
			var quantity = eval(nformat(frm.as_packageQty[i].value, 0));
			var extendedCost = eval(nformat((proratedPrice * quantity),2));
			frm.as_extendedPrice[i].value = "US $ " + nformat(extendedCost, 2);

			totalCharge = totalCharge + extendedCost;
		}

		frm.as_totalCharges.value = "US $" + nformat(totalCharge, 2);
	}
// End Hide script -->
</SCRIPT>
