	// We create the XMLHTTPRequest Object
	var http = getHTTPObject();
	var tableRecordFound = false ;
	var tableUrlPath = "" ;
	var tableOid = "" ;
	var tableUid = "" ;

	function getHTTPObject() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}

	function initTableLookup(urlPath, oid, uid) {
	    tableOid = oid ;
	    tableUid = uid ;
	    tableUrlPath = urlPath ;
	}

	function lookupRequest(url, responseHandler) {
		http.open("GET", url, false);
		http.onreadystatechange = responseHandler ;
		http.send(null);
	}

	function tableLookup(urlPath, oid, uid, idValue, processXML, resultObject, tableKey) {
		initTableLookup(urlPath, oid, uid) ;
		tableLookup(idValue, processXML, resultObject, tableKey) ;
	}

	function tableLookup(idValue, processXML, resultObject, tableKey) {
		var url = tableUrlPath + "/TableLookup?uid=" + tableUid + "&oid=" + tableOid + "&process=" + processXML + "&resultObj=" + resultObject ;
        url = url + "&" + tableKey + "=" + escape(idValue) ;
		tableRecordFound = false ;
		if (! isEmpty(idValue))	{
			// Only lookup non-blank values
			lookupRequest(url, tableLookupResponse) ;
		}
		return tableRecordFound ;
	}

	function tableLookupResponse() {
		if (http.readyState == 4) {
			if (http.status == 200) {
				tableRecordFound = (getRowCount() > "0") ;
			} else {
			    tableRecordFound = false ;
		    }
		}
	}

	function tableHasDuplicate(idValue, processXML, resultObject, tableKey ) {
		tableRecordFound = false ;
		if (! isEmpty(idValue))	{
			tableLookup(idValue, processXML, resultObject, tableKey) ;
		}
		return tableRecordFound ;
	}


	function setResponseValue(resp, xmlName, formName) {
		setResponseValue(resp, xmlName, formName, '') ;
	}

	function setResponseValue(resp, xmlName, formName, formDflt) {
          var e = resp.getElementsByTagName(xmlName)[0];
          if (e.hasChildNodes()) {
                  document.getElementById(formName).value = e.childNodes[0].nodeValue;
          } else {
					if (formDflt == null) {
	                  document.getElementById(formName).value = "" ;
					} else {
	                  document.getElementById(formName).value = formDflt ;
                  }
          }
	}

	function getRowCount() {
		return http.responseXML.getElementsByTagName("responsedata")[0].getAttribute("rowcount") ;
	}

	function getResponseValue(xmlName, formDflt) {
		return getResponseValue(1, xmlName, formDflt) ;
	}

	function getResponseValue(row, xmlName, formDflt) {
          var e = http.responseXML.getElementsByTagName(xmlName)[row - 1];
          if (e.hasChildNodes()) {
                  return e.childNodes[0].nodeValue;
          } else {
					if (formDflt == null) {
	                  return "" ;
                  }
          }
          return formDflt ;
	}
