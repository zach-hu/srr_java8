    function viewAsset1(tagNumber)
	{
		var newInputField = "<input type='hidden' name='Asset_tagNumber' value='" + tagNumber + "'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/asset_general.jsp", "AssetRetrieveById");
	}

 	function viewAssetInvItem(itemNumber,description)
	{
		setOriginalFilter("InvItem_itemNumber", "=", itemNumber );
		var newInputField = "<input type='hidden' name='Asset_itemNumber' value='" + itemNumber + "'>"+"<input type='hidden' name='Asset_description' value='" + description + "'>";
		setHiddenFields(newInputField);
		browse('asset');
	}

	function viewAssetInvItemReturn(itemNumber)
	{
		clearFilters();
		setOriginalFilter("InvItem_itemNumber", "=", itemNumber );
		browse('asset');
	}

	function viewInvItemAsset()
	{
		clearFilters();
		browse('invitem_asst');
	}

	function viewassetcost(tagNumber,sequenceNo,assetFields)
	{
		var newInputField = "<input type='hidden' name='AssetCost_tagNumber' value='" + tagNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='AssetCost_sequenceNo' value='" + sequenceNo + "'>" + assetFields;
		setHiddenFields(newInputField);
		doSubmit("/asset/cost/assetcost_review.jsp", "AssetCostRetrieveById");
	}
	function viewAssetLocation(tagNumber,sequenceNo,assetFields)
	{
		var newInputField = "<input type='hidden' name='AssetLocation_tagNumber' value='" + tagNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='AssetLocation_sequenceNo' value='" + sequenceNo + "'>" + assetFields;
		setHiddenFields(newInputField);
		doSubmit("/asset/location/assetlocation_review.jsp", "AssetLocationRetrieveById");
	}
	function viewLastAssetLocation(tagNumber)
	{
		var newInputField = "<input type='hidden' name='AssetLocation_tagNumber' value='" + tagNumber + "'>";
		setHiddenFields(newInputField);
		doSubmit("/asset/location/assetlocation_review.jsp", "AssetLocationLastRetrieveById");
	}
	function viewassetnote(tagNumber,sequenceNo,assetFields)
	{
		var newInputField = "<input type='hidden' name='AssetNote_tagNumber' value='" + tagNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='AssetNote_sequenceNo' value='" + sequenceNo + "'>" + assetFields;
		setHiddenFields(newInputField);
		doSubmit("/asset/note/assetnote_review.jsp", "AssetNoteRetrieveById");
	}
	function viewassetservice(tagNumber,sequenceNo,assetFields)
	{
		var newInputField = "<input type='hidden' name='AssetService_tagNumber' value='" + tagNumber + "'>";
		newInputField = newInputField + "<input type='hidden' name='AssetService_sequenceNo' value='" + sequenceNo + "'>" + assetFields;
		setHiddenFields(newInputField);
		doSubmit("/asset/service/assetservice_review.jsp", "AssetServiceRetrieveById");
	}

	function browseAsset(process)
	{
		var tag = frm.Asset_tagNumber.value;

		if( process.indexOf('note') > 0)
		{
			setOriginalFilter("AssetNote_id_tagNumber", "=", tag);
		}
		if( process.indexOf('cost') > 0)
		{
			setOriginalFilter("AssetCost_id_tagNumber", "=", tag);
		}
		if( process.indexOf('location') > 0)
		{
			setOriginalFilter("AssetLocation_id_tagNumber", "=", tag);
		}
		if( process.indexOf('service') > 0)
		{
			setOriginalFilter("AssetService_id_tagNumber", "=", tag);
		}
		browse(process);
	}

	function setForBrowseAsset(process)
	{
		var tag = frm.Asset_tagNumber.value;

		if( process.indexOf('note') > 0)
		{
			setOriginalFilter("AssetNote_id_tagNumber", "=", tag);
		}
		if( process.indexOf('cost') > 0)
		{
			setOriginalFilter("AssetCost_id_tagNumber", "=", tag);
		}
		if( process.indexOf('location') > 0)
		{
			setOriginalFilter("AssetLocation_id_tagNumber", "=", tag);
		}
		if( process.indexOf('service') > 0)
		{
			setOriginalFilter("AssetService_id_tagNumber", "=", tag);
		}
	}

	//asset review
	function addasset(action)
	{
		if (action == "new")
		{
			doSubmit("/asset/asset_general.jsp", "DoNothing");
		}
		else if ( action == "itemnew") {
			if (frm.Asset_itemNumber.value == "")
			{
				alert('You must enter a Item Number!!');
			}
			else
			{
				doSubmit("/asset/asset_general.jsp", "AssetAdd;InvItemAssetAdd;AssetRetrieveById");
			}
		}
		else if (frm.Asset_commodity.value == "")
		{
			alert('A commodity value must be inserted!!');
		}
		else
		{
			doSubmit("/asset/asset_general.jsp", "AssetAdd;AssetRetrieveById");
		}
	}

	function deleteasset()
	{
		input_box=confirm('Delete asset?');
		if (input_box==true)
		{
			doSubmit("/asset/work.jsp", "AssetDeleteComplete");
			void(0);
		}
		else
		{
			doSubmit('/asset/asset_general.jsp','AssetRetrieveById');
		}
	}

	function updateasset()
	{
		if (frm.Asset_statusOriginal.value != frm.Asset_assetStatus.value && frm.AssetNote_stdText.value == '')
		{
			alert('You must enter a reason for change de status of the asset!!');
		}
		else
		{
			doSubmit("/asset/asset_general.jsp", "AssetUpdate;AssetRetrieveById");
		}
	}

	function showHideAssetNote()
	{
		if (frm.Asset_statusOriginal.value == frm.Asset_assetStatus.value)
		{
			hideArea('AssetNote');
		}
		else
		{
			displayArea('AssetNote');
		}
	}

	//assetnote
	function addassetnote(action)
	{
		if (action == "new")
		{
			doSubmit("/asset/note/assetnote_review.jsp", "DoNothing");
		}
		else
		{
			doSubmit("/asset/note/assetnote_review.jsp", "AssetNoteAdd;AssetNoteRetrieveById");
		}
	}

	function deleteassetnote()
	{
		input_box=confirm('Delete note?');
		if (input_box==true)
		{
			doSubmit("/asset/asset_general.jsp", "AssetNoteDelete;AssetRetrieveById");
			void(0);
		}
		else
		{
			doSubmit('/asset/note/assetnote_review.jsp','AssetNoteRetrieveById');
		}
	}

	function updateassetnote()
	{
		doSubmit("/asset/note/assetnote_review.jsp", "AssetNoteUpdate;AssetNoteRetrieveById");
	}

	//assetlocation
	function addassetlocation(action)
	{
		if (action == "new")
		{
			doSubmit("/asset/location/assetlocation_review.jsp", "DoNothing");
		}
		else
		{
			doSubmit("/asset/location/assetlocation_review.jsp", "AssetLocationAdd;AssetLocationRetrieveById");
		}
	}

	function deleteassetlocation()
	{
		input_box=confirm('Delete location?');
		if (input_box==true)
		{
			doSubmit("/asset/asset_general.jsp", "AssetLocationDelete;AssetRetrieveById");
			void(0);
		}
		else
		{
			doSubmit('/asset/location/assetlocation_review.jsp','AssetLocationRetrieveById');
		}
	}

	function updateassetlocation()
	{
		doSubmit("/asset/location/assetlocation_review.jsp", "AssetLocationUpdate;AssetLocationRetrieveById");
	}

	//assetservice
	function addassetservice(action)
	{
		if (action == "new")
		{
			doSubmit("/asset/service/assetservice_review.jsp", "DoNothing");
		}
		else
		{
			doSubmit("/asset/service/assetservice_review.jsp", "AssetServiceAdd;AssetServiceRetrieveById");
		}
	}

	function deleteassetservice()
	{
		input_box=confirm('Delete service?');
		if (input_box==true)
		{
			doSubmit("/asset/asset_general.jsp", "AssetServiceDelete;AssetRetrieveById");
			void(0);
		}
		else
		{
			doSubmit('/asset/service/assetservice_review.jsp','AssetServiceRetrieveById');
		}
	}

	function updateassetservice()
	{
		doSubmit("/asset/service/assetservice_review.jsp", "AssetServiceUpdate;AssetServiceRetrieveById");
	}

	//assetcost
	function addassetcost(action)
	{
		if (action == "new")
		{
			doSubmit("/asset/cost/assetcost_review.jsp", "DoNothing");
		}
		else
		{
			doSubmit("/asset/cost/assetcost_review.jsp", "AssetCostAdd;AssetCostRetrieveById");
		}
	}

	function deleteassetcost() {
		input_box=confirm('Delete note?');
		if (input_box==true)
		{
			doSubmit("/asset/asset_general.jsp", "AssetCostDelete;AssetRetrieveById");
			void(0);
		}
		else
		{
			doSubmit('/asset/cost/assetcost_review.jsp','AssetCostRetrieveById');
		}
	}

	function updateassetcost() {
		doSubmit("/asset/cost/assetcost_review.jsp", "AssetCostUpdate;AssetCostRetrieveById");
	}

	//assettotals

	function createasset() {
		doSubmit("/asset/asset_serialnumbers.jsp", "AssetInvItemAdd");
	}

	function numbercontrol() {
		var num = frm.Asset_number.value;
		if (num < 1) {
			alert("The number can't be less than 1");
			frm.Asset_number.value = 1;
		}
	}

	function updateassetitem() {
		doSubmit("/asset/asset_totals.jsp", "AssetInvItemUpdate;AssetTotalsRetrieve");
	}

	//formats

	function formatPrice(fld) {
		fld.value = nformat(nfilter(fld), 2);
	}