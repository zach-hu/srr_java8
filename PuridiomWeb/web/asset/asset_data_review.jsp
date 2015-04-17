<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<table border="0" cellspacing="0" cellpadding="0">
	<tsa:tr>
		<tsa:td colspan="4" align="center">
			<!--In this part we get information about asset-->
			<table border="1" cellspacing="0" cellpadding="0" width="500px" class="browseHdr" >
			<tsa:tr>
              <tsa:td cssClass="browseHdr" height="18px" noWrap="nowrap" align="left">&nbsp;<tsa:label labelName="po-general_information" defaultString="General Information" /> </tsa:td>
            </tsa:tr>
            </table>
		</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the itemnumber and description of asset-->
		<tsa:td noWrap="nowrap" align="right" ><b><tsa:label labelName="asset-itemnumber" defaultString="Item Number" /> </b></tsa:td>
		<tsa:td>: <%=Asset_itemNumber%> </tsa:td>
		<tsa:td  align="right" ><b><tsa:label labelName="asset-description" defaultString="Description" /></b></tsa:td>
		<tsa:td noWrap="nowrap" >: <%=asset.getDescription()%> </tsa:td>
	</tsa:tr>

	<tsa:tr>
		<!--In this part we get the asset class and Dep Term-->
		<tsa:td align="right"  > <b><tsa:label labelName="asset-assetclass" defaultString="Asset Class" /> </b></tsa:td>
		<tsa:td noWrap="nowrap" >: <%=asset.getAssetClass()%></tsa:td>

		<tsa:td noWrap="nowrap"  align="right" ><tsa:label labelName="asset-depterm" defaultString="Dep Term" /> </tsa:td>
		<tsa:td noWrap="nowrap" >: <%=HiltonUtility.getFormattedDollar(asset.getDeprecTerm(), oid)%> </tsa:td>



	</tsa:tr>

	<tsa:tr>
		<!--In this part we get the commodity and the cost of asset-->
		<tsa:td align="right" > <tsa:label labelName="asset-commodity" defaultString="Commodity" />  </tsa:td>
		<tsa:td noWrap="nowrap" >: <%=asset.getCommodity()%></tsa:td>
		<tsa:td align="right" > <tsa:label labelName="asset-assetcost" defaultString="Asset Cost" /> </tsa:td>
		<tsa:td noWrap="nowrap" >: <%=HiltonUtility.getFormattedDollar(asset.getAssetCost(), oid)%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the ownerentity and purchaseorder of asset-->
		<tsa:td align="right" > <tsa:label labelName="asset-ownerentity" defaultString="Owner Entity" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getOwner()%></tsa:td>
		<tsa:td align="right"><tsa:label labelName="asset-purchaseorder" defaultString="Purchase Order" /></tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getPurchaseOrder()%> </tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the ownerentityname and securitycode of asset-->
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-ownerentityname" defaultString="Owner Name" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=owner.getDisplayName()%> </tsa:td>

		<tsa:td noWrap="nowrap" align="right" > <tsa:label labelName="asset-securitycode" defaultString="Security Code" />  </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getSecurityCode()%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the manufacturer and purchasevendor of asset-->
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-manufacturer" defaultString="Manufacturer" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getManufacturer()%></tsa:td>

		<tsa:td noWrap="nowrap" align="right"><tsa:label labelName="asset-purchasevendor" defaultString="Purchase Vendor" /> </tsa:td>
		<tsa:td>: <%=asset.getPoVendor()%> </tsa:td>

	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the model and the ContractService of asset-->
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-model" defaultString="Model" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getModel()%> </tsa:td>

		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-servicecontractno" defaultString="Service Contract No" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getContractService()%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the SerialNumber and the ContractorName of asset-->
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-serialnumber" defaultString="Serial Number" />  </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getSerialNumber()%></tsa:td>
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-contractorname" defaultString="Contractor Name" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getContractorName()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the depmethod and upgradereqs of asset-->
		<tsa:td noWrap="nowrap" align="right" ><tsa:label labelName="asset-depmethod" defaultString="Dep Method" />   </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getDeprecMethod()%></tsa:td>

		<tsa:td align="right" ><tsa:label labelName="asset-upgradereqs" defaultString="Upgrade Reqs" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getUpgradeReqs()%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the date in service and when the warranty date start-->
		<tsa:td noWrap="nowrap" align="right"> <tsa:label labelName="asset-dateinservice" defaultString="Date in Service" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(asset.getDateInService(),oid, userDateFormat)%></tsa:td>
		<tsa:td noWrap="nowrap" align="right"><tsa:label labelName="asset-warrantystart" defaultString="Warranty Start" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(asset.getWarrantyStart(),oid, userDateFormat)%></tsa:td>

	</tsa:tr>
	<tsa:tr>
		<!--In this part we get the date that were received and when the warranty date end-->
		<tsa:td align="right" >  <tsa:label labelName="asset-datereceived" defaultString="Date Received" />  </tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(asset.getDateReceived(),oid, userDateFormat)%></tsa:td>
		<tsa:td align="right"  >  <tsa:label labelName="asset-warrantyend" defaultString="Warranty End" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=HiltonUtility.getFormattedDate(asset.getWarrantyEnd(),oid, userDateFormat)%></tsa:td>
	</tsa:tr>

	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="asset-udf1" defaultString="UDF 1" />  </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf1()%>  </tsa:td>
		<tsa:td align="right" ><tsa:label labelName="asset-udf2" defaultString="UDF 2" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf2()%> </tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" > <tsa:label labelName="asset-udf3" defaultString="UDF 3" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf3()%> </tsa:td>
		<tsa:td align="right" ><tsa:label labelName="asset-udf4" defaultString="UDF 4" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf4()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="asset-udf5" defaultString="UDF 5" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf5()%> </tsa:td>
		<tsa:td align="right" ><tsa:label labelName="asset-udf6" defaultString="UDF 6" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf6()%> </tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right"><tsa:label labelName="asset-udf7" defaultString="UDF 7" /> </tsa:td>
		<tsa:td>: <%=asset.getAssetUdf7()%> </tsa:td>
		<tsa:td align="right" > <tsa:label labelName="asset-udf8" defaultString="UDF 8" /> </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf8()%></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="right" ><tsa:label labelName="asset-udf9" defaultString="UDF 9" />  </tsa:td>
		<tsa:td>: <%=asset.getAssetUdf9()%></tsa:td>
		<tsa:td align="right" ><tsa:label labelName="asset-udf10" defaultString="UDF 10" />  </tsa:td>
		<tsa:td noWrap="nowrap">: <%=asset.getAssetUdf10()%> </tsa:td>
	</tsa:tr>

</table>

<tsa:hidden name="Asset_dateEntered" value=""/>
<tsa:hidden name="Asset_totalCost" value=""/>
<tsa:hidden name="Asset_icText" value=""/>
<tsa:hidden name="Asset_imageFile" value=""/>
<tsa:hidden name="Asset_icReceipt" value=""/>
<tsa:hidden name="Asset_icLineKey" value=""/>
<tsa:hidden name="Asset_itemLocation" value=""/>
<tsa:hidden name="Asset_icAccount" value=""/>
<tsa:hidden name="Asset_icDsbHeader" value=""/>
<tsa:hidden name="Asset_icDsbLine" value=""/>
<tsa:hidden name="Asset_localCurrencyPrice" value=""/>
<tsa:hidden name="Asset_originalCost" value=""/>
<tsa:hidden name="Asset_exitValue" value=""/>
<tsa:hidden name="Asset_dateInactive" value=""/>
<tsa:hidden name="Asset_lastChgBy" value=""/>
<tsa:hidden name="Asset_dateChanged" value=""/>
<tsa:hidden name="Asset_statusOriginal" value=""/>
