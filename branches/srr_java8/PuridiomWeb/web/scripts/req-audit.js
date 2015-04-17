	function browseShipTo()
	{
		frm.updateAddress.value = "N";
		browseLookup('RequisitionHeader_shipToCode', 'ship_to');
	}

	function setAuditTables()
	{
		frm.auditTables.value = "RequisitionHeader";
	}
	function getFields(el)
	{
		if(el.type != "hidden" && el.name.indexOf("RequisitionHeader_") > -1)
		{
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
    }
    function buildAuditIc()
	{
		return frm.RequisitionHeader_icReqHeader.value;
	}