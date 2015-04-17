<%
	List packagePricingList = (List) request.getAttribute("packagePricingList");
	List organizationPackageList = (List) request.getAttribute("organizationPackageList");
	Map selectedPackages = new HashMap();
	NumberFormat nf = NumberFormat.getInstance();
	
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	
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
				<td class="browseHdr" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "packageoption", "Package Option")%></td>
				<td class="browseHdr" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioners", "Requisitioners")%></td>
				<td class="browseHdr" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyers", "Buyers")%></td>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "annualprice", "Annual Price")%></td>
				<td class="browseHdr" class="browseHdr" align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "savings", "Savings")%></td>
				<td class="browseHdr" width="5px">&nbsp;</td>
			</tr>
<%
		int counter = 0;
		for (int i = 0; i < packagePricingList.size(); i++) {
			PackagePricing packagePricing = (PackagePricing) packagePricingList.get(i);
			String packagePrice = "";
			String packageSavings = "";
			BigDecimal bdPackagePrice = HiltonUtility.ckNull(packagePricing.getPackagePrice());
			BigDecimal bdPackageSavings = HiltonUtility.ckNull(packagePricing.getPackageSavings());
			
			if (bdPackagePrice.compareTo(new BigDecimal(0)) == 0) {
				packagePrice = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nocharge", "No Charge");
			} else {
				packagePrice = "US $ " + nf.format(bdPackagePrice);
			}
			if (bdPackageSavings.compareTo(new BigDecimal(0)) == 0) {
				packageSavings = "-";
			} else {
				packageSavings = "<font class=red><b>US $ " + nf.format(bdPackageSavings) + "</b></font>";
 			}
			
			int qtySelected = 0;
			
			//if (selectedPackages.containsKey(packagePricing.getIcPackage())) {
			try {
				qtySelected = ((Integer) selectedPackages.get(packagePricing.getIcPackage())).intValue();
			} catch (Exception e1) {
			}
						
			if (counter == 0) {%>
			<tr class="browseRow"><td colspan=7 height=5px><img src="<%=contextPath%>/images/none.gif" border=0 height=5px></td></tr>
		<%	} else {%>
			<tr class="browseRow"><td colspan=7><hr size=1 width=100%></td></tr>
		<%	}%>
			<tr class="browseRow" height="18px">
<%			if (packagePricing.getPackageType().equals("G")) {%>
				<td class="browseRow" align=center valign="middle"><input type=radio name="packageSelection" value="<%=counter%>" <% if (qtySelected > 0) {%> checked<%}%>></td>
<%			} else {%>
				<td class="browseRow" align=center valign="middle"><input type=text name="packageQty" value="<%=qtySelected%>" size=5></td>
<%			}%>
				<td class="browseRow" valign="middle"><b><a href="javascript: toggleDetails(<%=counter%>); void(0);"><%=packagePricing.getPackageName()%></b></td>
				<td class="browseRow" align="center" valign="middle" nowrap><b><%=packagePricing.getRequisitionerCount()%></b></td>
				<td class="browseRow" align="center" valign="middle" nowrap><b><%=packagePricing.getBuyerCount()%></b></td>
				<td class="browseRow" align="right" valign="middle" nowrap><b><%=packagePrice%></b></td>
				<td class="browseRow" align="right" valign="middle" nowrap><%=packageSavings%></td>
				<td class="browseRow">&nbsp;
					<tsa:hidden name="as_icPackage" value="<%=packagePricing.getIcPackage()%>"/>
					<tsa:hidden name="as_packageName" value="<%=packagePricing.getPackageName()%>"/>
					<tsa:hidden name="as_packagePrice" value="<%=packagePricing.getPackagePrice()%>"/>
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
				
<%			counter++;
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
	
<!-- dummy fields to prevent js errors -->	
<tsa:hidden name="as_icPackage" value=""/>
<tsa:hidden name="as_packageName" value=""/>

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

// End Hide script -->
</SCRIPT>
