
  function browserCheck() {
    var bw = "";

    if (navigator.appName == "Netscape") {
      bw = "NS";
      if (navigator.appVersion.charAt(0) == "5") {
        bw = "NS6";
      }
    }
    else if (navigator.appVersion.indexOf("MSIE") != -1) {
      bw = "IE";
    }
    return bw;
  }

  function thisLoad() {
  	var topMenuGenerated = false;
    var menuGenerated = false;

    for (x=0; x < leftTopMenuOptions.length; x++) {
      if (generateMenu(leftTopMenuOptions[x], "leftTopMenu")) {
        topMenuGenerated = true;
      }
    }
    for (x=0; x < centerTopMenuOptions.length; x++) {
      if (topMenuGenerated) {
        generateMenu(centerTopMenuOptions[x], "centerTopMenu");
      } else {
        generateMenu(centerTopMenuOptions[x], "leftTopMenu");
      }
    }
    for (x=0; x < rightTopMenuOptions.length; x++) {
      if (topMenuGenerated) {
        generateMenu(rightTopMenuOptions[x], "rightTopMenu");
      } else {
        generateMenu(rightTopMenuOptions[x], "leftTopMenu");
      }
    }

    for (x=0; x < leftMenuOptions.length; x++) {
      if (generateMenu(leftMenuOptions[x], "leftMenu")) {
        menuGenerated = true;
      }
    }
    for (x=0; x < centerMenuOptions.length; x++) {
      if (menuGenerated) {
        generateMenu(centerMenuOptions[x], "centerMenu");
      } else {
        generateMenu(centerMenuOptions[x], "leftMenu");
      }
    }
    for (x=0; x < rightMenuOptions.length; x++) {
      if (menuGenerated) {
        generateMenu(rightMenuOptions[x], "rightMenu");
      } else {
        generateMenu(rightMenuOptions[x], "leftMenu");
      }
    }

    checkGraphData();
    checkRecentData();

    f_StartIt();

	hideArea("pageLoading");
	displayArea("pageForm");
  }

  function generateMenu(x, position) {
    if (!validateArray("MenuArray" + x)) return false;

    generateMenuTable(x, position);
    generateMenuTitle(x);
    displayMenuArea(x);

    return true;
  }

  function validateArray(arrayname) {
    return ((typeof eval("window." + arrayname) == "object") && (eval(arrayname).length > 1))
  }

  function generateMenuTable(x, position) {
    if (!validateArray("MenuArray" + x)) return false;
    var currentArray = eval("MenuArray" + x);
    var myMenuCell = document.getElementById(position);
    var containerClass = 'container';
    var topBorderClass = 'rtop';
    var bottomBorderClass = 'rbottom';

	if (currentArray[0][1] == 0) {
		containerClass = 'containerclosed';
		topBorderClass = 'rtopclosed';
		bottomBorderClass = 'rbottomclosed';
	}

    var myParentTableHTML = "<div id='container" + x + "' style='width: 100%; align:left; margin:0; class-name:" + containerClass + "'>" +
		"<b class=" + topBorderClass + " id='containerTop" + x + "'>" +
		"<b class=r1></b> <b class=r2></b> <b class=r3></b> <b class=r4></b>" +
		"</b>" +
		"<table id='parentTable" + x + "' border=0 cellspacing=1 cellpadding=0 width=100%>";

    var myMenuTitleTableHTML = "<table id='menuTitle" + x + "' border=0 cellspacing=0 cellpadding=0 width=100%></table>";
    var myMenuOptionsTableHTML = "<table id='menuOptions" + x + "' border=0 cellspacing=0 cellpadding=2 width=100%></table>";

    var myTableRow1HTML = "<tr><td style:'width=100%; height=18px; align=center;'>" + myMenuTitleTableHTML + "</td></tr>"
    var myTableRow2HTML = "<tr><td>" + myMenuOptionsTableHTML + "</td></tr>"
    var myTableHTML = myParentTableHTML + myTableRow1HTML + myTableRow2HTML + "</table>" +
    	"<b class=" + bottomBorderClass + " id='containerBottom" + x + "'>" +
		"<b class=r4></b> <b class=r3></b> <b class=r2></b> <b class=r1></b>" +
		"</b>" +
		"</div><br>";

    myMenuCell.innerHTML = myMenuCell.innerHTML + myTableHTML;
  }

  function generateMenuTitle(x) {
    if (!validateArray("MenuArray" + x)) return false;

    var currentArray = eval("MenuArray" + x);
    var myParent = document.getElementById("parentTable" + x);
    var myTable = document.getElementById("menuTitle" + x);
    var myTbody;
    var myRow;
    var myCell;
    var myImg;
    var myClass;

    if (currentArray[0][1] == 1) {
      myClass = currentArray[0][2];
      myImg = "<img id='menuImage" + x + "' src='/" + context + "/images/arrows_up.gif' border=0>";
    }
    else
    {
      myClass = currentArray[0][3];

      myImg = "<img id='menuImage" + x + "' src='/" + context + "/images/arrows_down.gif' border=0>";
    }

    myParent.className = myClass;
    myTable.className = myClass;


  	  myTbody = document.createElement("TBODY");
  	  myTable.appendChild(myTbody);

      myRow = document.createElement("TR");
      myTbody.appendChild(myRow);

      myRow.className = myClass;

      myCell = document.createElement("TD");
      myRow.appendChild(myCell);

      myCell.style.width = "90%";
      myCell.align = "center";
      myCell.className = myClass;
      myCell.innerHTML = currentArray[0][0];

      myCell = document.createElement("TD");
      myRow.appendChild(myCell);

      myCell.align = "right";
      myCell.style.width = "10%";
      myCell.className = myClass;
      myCell.innerHTML = "<a href='javascript: toggleDisplay(" + x + "); void(0);'>" + myImg + "</a>";

  }

	function generateMenuOptions(x) {
		var currentArray = eval("MenuArray" + x);
		var myTable = document.getElementById("menuOptions" + x);
		var myRow;
		var myCell;
		var myHTML = "";

		myTable.className = "moption";

		myRow = createRow(myTable);
		myCell = createCell(myRow);
		myDiv = createDiv(myCell);

		myTable = document.createElement("TABLE");
		myTable.className = "moption";
		myTable.style.width = "100%";
		myDiv.appendChild(myTable);

		for (i=1; i < currentArray.length; i++) {
			myRow = createRow(myTable);
			myRow.className = "moption";

			myCell = createCell(myRow);
			myCell.style.width = "100%";
			myCell.className = "moption";

			if (currentArray[i][1].length > 0) {
				if (currentArray[i][4] && !isEmpty(currentArray[i][4]) && currentArray[i][5] && !isEmpty(currentArray[i][5]) && currentArray[i][6] && !isEmpty(currentArray[i][6])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " title=" + '"' + currentArray[i][4] + '"' + " onMouseOver=" + '"' + currentArray[i][5] + '"' + " onMouseOut=" + '"' + currentArray[i][6] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else if (currentArray[i][5] && !isEmpty(currentArray[i][5]) && currentArray[i][6] && !isEmpty(currentArray[i][6])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " onMouseOver=" + '"' + currentArray[i][5] + '"' + " onMouseOut=" + '"' + currentArray[i][6] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else if (currentArray[i][4] && !isEmpty(currentArray[i][4])) {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + " title=" + '"' + currentArray[i][4] + '"' + ">" + currentArray[i][0] + "</a>";
				}
				else {
					myHTML = "<a href=" + '"' + currentArray[i][1] + '"' + ">" + currentArray[i][0] + "</a>";
				}
			}
			else {
				myHTML = currentArray[i][0];
			}

			myCell.innerHTML = currentArray[i][2] + myHTML + currentArray[i][3];
		}
	}

  function hideMenuArea(x) {
    if (!validateArray("MenuArray" + x)) return false;

    var currentArray = eval("MenuArray" + x);
    var myParentTable = document.getElementById("parentTable" + x)
    var myTitleTable = document.getElementById("menuTitle" + x)
    var myTable = document.getElementById("menuOptions" + x);
    var myClass = currentArray[0][3];

    myParentTable.className = myClass;
    myTitleTable.className = myClass;

    var myContainer = document.getElementById("container" + x);
    var myTop = document.getElementById("containerTop" + x);
    var myBottom = document.getElementById("containerBottom" + x);

    myContainer.className = "containerclosed";
    myTop.className = "rtopclosed";
    myBottom.className = "rbottomclosed";

	if ((browser == "NS" || browser == "NS6") && myTable.hasChildNodes()) {
		var tBody;
		var row;
		var cell;

		for (var i = myTitleTable.childNodes.length - 1; i >= 0 ; i--) {
			if (myTitleTable.childNodes[i].nodeName == 'TBODY') {

			    tBody = myTitleTable.childNodes[i];
			    for(var j = tBody.childNodes.length - 1; j>=0; j--){
			      	if(tBody.childNodes[j].nodeName == 'TR'){
					row = tBody.childNodes[j];
					row.className = myClass;
					}

					for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
						cell = row.childNodes[ic];
						cell.className = myClass;
					}
				}
			}
		}

		for (var i = myTable.childNodes.length - 1; i >= 0 ; i--) {
			if (myTable.childNodes[i].nodeName == 'TBODY') {

				tBody = myTable.childNodes[i];
				for (var j = tBody.childNodes.length - 1; j>=0; j--){
					if(tBody.childNodes[j].nodeName == 'TR'){
						row = tBody.childNodes[j];
						tBody.removeChild(row);
					}
				}
			}
		}
	}
    else {
      for (var i = myTitleTable.rows.length - 1; i >= 0 ; i--) {
        row = myTitleTable.rows(i);
        row.className = myClass;

        for (var ic = row.cells.length - 1; ic >= 0 ; ic--) {
          cell = row.cells(ic);
          cell.className = myClass;
        }
      }

      for (i = myTable.rows.length - 1; i >= 0; i--) {
        myTable.deleteRow(0);
      }
    }

    currentArray[0][1] = 0;
  }

  function displayMenuArea(x) {
    if (!validateArray("MenuArray" + x)) return false;

    var currentArray = eval("MenuArray" + x);
    var myClass = currentArray[0][2];
    var myParentTable = document.getElementById("parentTable" + x)
    var myTitleTable = document.getElementById("menuTitle" + x)
    var myTable = document.getElementById("menuOptions" + x);

    myParentTable.className = myClass;
    myTitleTable.className = myClass;

    var myContainer = document.getElementById("container" + x);
    var myTop = document.getElementById("containerTop" + x);
    var myBottom = document.getElementById("containerBottom" + x);

    myContainer.className = "container";
    myTop.className = "rtop";
    myBottom.className = "rbottom";

    if ((browser == "NS" || browser == "NS6") ) {
      var tbody;
      var row;

		for (var i = myTitleTable.childNodes.length - 1; i >= 0 ; i--) {
			if (myTitleTable.childNodes[i].nodeName == 'TBODY') {

			    tBody = myTitleTable.childNodes[i];
			    for(var j = tBody.childNodes.length - 1; j>=0; j--){
			      	if(tBody.childNodes[j].nodeName == 'TR'){
						row = tBody.childNodes[j];
						row.className = myClass;
					}

					for (var ic = row.childNodes.length - 1; ic >= 0 ; ic--) {
						cell = row.childNodes[ic];
						cell.className = myClass;
					}
				}
			}
		}
    }
    else {
      for (var i = myTitleTable.rows.length - 1; i >= 0 ; i--) {
        row = myTitleTable.rows(i);
        row.className = myClass;

        for (var ic = row.cells.length - 1; ic >= 0 ; ic--) {
          cell = row.cells(ic);
          cell.className = myClass;
        }
      }
    }

    generateMenuOptions(x);
    currentArray[0][1] = 1;
  }

  function toggleDisplay(x) {
    if (!validateArray("MenuArray" + x)) return false;

    var currentArray = eval("MenuArray" + x);
    var img = document.getElementById("menuImage" + x);

    if (currentArray[0][1] == 1) {
      hideMenuArea(x);
      img.src = "/" + context + "/images/arrows_down.gif";


    }
    else {
      img.src = "/" + context + "/images/arrows_up.gif";

      displayMenuArea(x);
    }
  }

  function createMyRequisitionActions(originalArray) {
    var options = new Array();
    var i = 1;

    options[0] = originalArray;

    if (reqAccess > 0) {
      options[1] = new Array(incompletereqs, "javascript: viewReqs('INCOMPLETE'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your Incomplete Requisitions.");
      options[2] = new Array(rejectedreqs,"javascript:viewReqs('REJECTED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your Rejected Requisitions.");
      options[3] = new Array(approvedreqs,"javascript:viewReqs('APPROVED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your Approved Requisitions.");
      options[4] = new Array(orderreqs,"javascript:viewReqs('ONORDER');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your Requisitions that are currently On Order.");
    }
    return options;
  }

  function createMyGraphActions(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;

      if (reqAccess > 0)
      {
        options[1] = new Array(graphName, "", "", "");
      }

      return options;
  }

  function createTopSuppliersGraph(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;
      options[1] = new Array(graphName, "", "", "");

      return options;
  }

  function createMyPoGraphActions(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;

      if (poAccess > 0)
      {
        options[1] = new Array(graphName, "", "", "");
      }

      return options;
  }

  function createMyRfqByStatusGraphActions(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;

      if (rfqAccess > 0)
      {
        options[1] = new Array(graphName, "", "", "");
      }

      return options;
  }

   function createMyInvoiceStatusGraphActions(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;

      if (invoiceAccess > 0)
      {
        options[1] = new Array(graphName, "", "", "");
      }

      return options;
  }

  function createMyActionItems(originalArray) {
    var options = new Array();
    var i = 1;

    options[0] = originalArray;

    if (reqsActive && approver) {
      options[i] = new Array(pendingapprovalreqs,"javascript: viewReqs('APPROVAL'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Requisitions awaiting your approval.");
      i++;
    }
    if (reqsActive && approver) {
		options[i] = new Array(waitingorders,"javascript: viewOrders('APPROVAL'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all the Purchase Orders awaiting your approval.");
		i++;
	}
    if (reqsActive)
    {
        if (closeOpenReqsAccess > 0) {
		    options[i] = new Array(closecancelreqs,"javascript: viewReqs('OPEN'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your open Requisitions.");
    		i++;
        }
    	if (reqPipelineBrowseAccess > 0)
    	{
//			options[i] = new Array(reqpipeline,"javascript: browse('req-pipeline'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a Requisition Pipeline browse.");
			options[i] = new Array(reqpipeline,"javascript: viewReqPipeline(reqPipelineBrowseAccess); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a Requisition Pipeline browse.");
			i++;
		}
	}
	if (poAutoCloseAccess > 0)
    	{
		options[i] = new Array(poautoclose,"javascript:viewReqs('CLOSED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all PO to close.");
	    	i++;
	}
    if (rfqAccess > 1) {
		if (reqsActive) {
	    	options[i] = new Array(forwardedreqs,"javascript:viewReqs('FORWARDED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Requisitions that need to be priced.");
    		i++;
		}
		if (extRfqsActive) {
			options[i] = new Array(solicitationswaitingresponse,"javascript:viewRfqsWaitingResponse();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Solicitations that waiting your response.");
			i++;
		}
		options[i] = new Array(solicitationdueweek,"javascript:viewRfqsDueThisWeek();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all the Solicitations that are due this week.");
		i++;
		options[i] = new Array(pendingapprovalrfqs,"javascript:viewRfqsWaitingApproval();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all the Solicitations that are waiting your approval.");
		i++;
    }
    if (poAccess > 1) {
    	if (reqsActive) {
	    	options[i] = new Array(assignedreqs,"javascript:viewReqs('ASSIGNED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Requisitions that are assigned to you.");
	    	i++;
		}
    	if (poPipelineBrowseAccess > 0)
    	{
			options[i] = new Array(popipeline,"javascript: viewPoPipeline(poPipelineBrowseAccess); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a Order Pipeline browse.");
			i++;
		}
		options[i] = new Array(incompleteorders,"javascript: viewOrders('INCOMPLETE'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all the Incomplete Purchase Orders.");
		i++;
		options[i] = new Array(overdueorders,"javascript: viewOrders('OVERDUE'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all your overdue Purchase Orders.");
		i++;
		if (boAccess > 1) {
			options[i] = new Array(bo60,"javascript: viewOrders('BOEXPIRE'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all the Blanket Orders that expire in the next 60 days.");
			i++;
		}
    }
	if (bbdocsAccess > 0) {
      options[i] = new Array(bidboarddocs, "javascript: viewStandardDocuments(); void(0);","<span class=raquo>&raquo;</span>", "");
      i++;
	}
    if (bbsupAccess > 0) {
      options[i] = new Array(viewprequalifiedsuppliers, "javascript: viewPrequalifiedSuppliers(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show the Pre-Qualified Suppliers.");
      i++;
      options[i] = new Array(viewregisteredsuppliers, "javascript: viewRegisteredSuppliers(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show the Suppliers who have self-registered.");
      i++;
    }
    if (buyerAssignAccess > 0) {
      options[i] = new Array(buyerassignmentworkload, "javascript: viewBuyerAssignment(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to assess the Buyer Assignment Workload.");
      i++;
      //options[i] = new Array(buyerworkloadsummary, "javascript: viewWorkloadSummary(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to view a Workload Summary by Buyer.");
      //i++;
    }
    if (inspectorAssignAccess > 0) {
        options[i] = new Array(inspectorassignmentworkload, "javascript: viewInspectorAssignment(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to assess the Inspector Assignment Workload.");
        i++;
    }
    if (procurementWorkloadAcess > 0 && poAccess > 1) {
      //options[i] = new Array(procurementworkload, "javascript: doSubmit('menu/buyerworload_type.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to view a lists all Buyers along with summary counts.");
      options[i] = new Array(procurementworkload, "javascript: browse('prm-req-worlkloadview'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to view a lists all Buyers along with summary counts.");
      i++;
    }
	if (dashboardAccess > 0 && procurementDashBoardVis == 'Y'  ) {
		options[i] = new Array(procurementDashBoard, "javascript: doSubmit('/graph/dashboard.jsp', 'DoNothing'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show your Dashboard.");
    	i++;
	}
	/*if (stdFormAccess > 0) {
      options[i] = new Array(standardformdocs, "javascript: viewStandardForms(); void(0);","<span class=raquo>&raquo;</span>", "");
      i++;
	}
	*/

	//View Browses Standar Inventory
	if (stdInvActive > 0) {
      options[i] = new Array(approvedinventoryrequest, "javascript: viewInventoryRequest('APPROVED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Approved Inventory Request.");
      i++;
      options[i] = new Array(itempendingdisbursement, "javascript: viewDisbursement('PENDING DISB');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Items Pending Disbursement.");
      i++;
      options[i] = new Array(backorderinventoryrequisitionbyline, "javascript: viewBackorderInvReqByline();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Backorder Inventory Requisition by line.");
      i++;
    }

    return options;
  }

  	function createMyReceivingActions(originalArray) {
	  var options = new Array();
	  var i = 1;

	  options[0] = originalArray;
	  if (/*recInInspectionAccess > 0 || */isAInspector || isAEngineer) {
		  options[i] = new Array(step1, "javascript: stepReceipts('step1'); void(0);","<span class=raquo>&raquo;</span>","","Inspection");
		  i++;
	  }
	  if (/*recInMarkAccess > 0 || */isAMarker) {
		  options[i] = new Array(step2, "javascript: stepReceipts('step2'); void(0);","<span class=raquo>&raquo;</span>","","Mark/Tag");
		  i++;
	  }
	  if (recInDeliveryAccess > 0) {
		  options[i] = new Array(step3, "javascript: stepReceipts('step3'); void(0);","<span class=raquo>&raquo;</span>","","Delivery");
		  i++;
	  }
	  if (rcvByPkgAccess >= 3) {
		  options[i] = new Array(receivepackages, "javascript: browseFilter('receipt-order-pkg'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to enter the package information received for an order.");
		  i++;
	  }
	  if (rcvFinalizeAccess >= 3) {
		  options[i] = new Array(finalizePendingRec,"javascript:finalizeReceipts();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Finalize your Receipts.");
		  i++;
	  }
	  if (rcvByItemAccess >=3 || rcvByEndUserAccess >= 3) {
		  options[i] = new Array(receiveitems,"javascript:receiveByItem();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Enter your Received Items.");
		  i++;
	  }
	  if (disableEndUserAdjust) {
		options[i] = new Array(adjustpackages,"javascript:adjustReceiptsNoEndUser();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Adjust Previous Receipts.");
		i++;
	  } else {
		if (rcvAdjAccess >= 3 && (rcvByItemAccess >=3 || rcvByEndUserAccess >= 3)) {
			options[i] = new Array(adjustpackages,"javascript:adjustReceipts();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Adjust Previous Receipts.");
			i++;
		}
	  }
	  if (rcvReturnAccess >= 3) {
		  options[i] = new Array(returns,"javascript:returnAgainstReceipts();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to enter Return Information for Items Received.");
		  i++;
	  }
	  if (rcvHistoryAccess > 0) {
		  options[i] = new Array(receivehistory, "javascript:viewReceiptHistory();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to view previous receipts and receipt information.");
		  i++;
	  }
	  if (quickRcvAccess > 0) {
		  options[i] = new Array(quickReceive, "javascript:quickRcv();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to quickly process received items.");
		  i++;
	  }

	  return options;
  	}

  	function createMyInventoryActions(originalArray) {
	    var options = new Array();
	    var i = 1;

	    options[0] = originalArray;

	    options[i] = new Array(pendingrecinv,"javascript: viewItRecInv('PENDING'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Items pending in Inventory.");
	    i++;

	    options[i] = new Array("Inventory Items Pending Approval", "javascript: viewInvs('APPROVAL'); void(0);","<span class=raquo>&raquo;</span>","","Inspection");
	    i++;

	    return options;
	}

	function createMyBrowseOptions(originalArray) {
		var options = new Array();
		var i = 1;

		options[0] = originalArray;

		if (catalogItems > 0) {
			options[i] = new Array("Catalog Items","javascript: browseFilter('catalogitem-admin');void(0);","<font class=raquo>&raquo;</font>","","");
			i++;
			options[i] = new Array("Supplier Browse","javascript: browseFilter('supplier_mgt'); void(0);","<font class=raquo>&raquo;</font>", "");
			i++;
		}
		return options;
	}

  function createMyInvoiceOptions(originalArray) {
    var options = new Array();

    options[0] = originalArray;
    options[1] = new Array(invoicecertification, "javascript:doSubmit('/invoice/menu.jsp', 'DoNothing');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Certify your Invoices.");

    return options;
  }

  function createUpcomingEvents(originalArray) {
    var options = new Array();
    var i = 1;

    options[0] = originalArray;

    options[i] = new Array("<span class=error>Puridiom Training Webinar!<br>Click here to register - its FREE!</span><br><br>","javascript:window.open('http://www.tsagate.com/procurement/contact/webinars.html'); void(0);","","","Click here for more information and to register for a free Puridiom Training Webinar!");
	i++;
	options[i] = new Array("<span class=error>Need Help?<br>Check out our Puridiom FAQs!</span><br><br>","javascript:window.open('http://www.tsagate.com/procurement/contact/bsc_puridiom_faq.html'); void(0);","","","Click here to view Puridiom Frequently Asked Questions!");
	i++;
	options[i] = new Array("<span class=error>New \"Approvals Routing\" options being added.  Suggestions welcomed!!!</span><br><br>","javascript:window.open('http://www.tsagate.com/procurement/contact/suggestions.html'); void(0);","","","Click here to share your suggestions with us.");
	i++;
	options[i] = new Array("<span class=error>Puridiom Update:&nbsp;</span><span class=processOff>Click here to see our newest features!</span><br><br>","javascript:window.open('http://www.tsagate.com/procurement/contact/release_01_10_2006.html'); void(0);","","","");
	i++;
	if (buyer)
	{
		options[i] = new Array("<span class=processOff>Administrative Changes to Purchase Orders have been activated. You can now make changes to an order after it has been awarded. Changes can be made to any field that does not affect the subtotal.</span>","","<img src='/" + context + "/images/bullet_red_onwhite.gif'>","","");
		i++;
	}
	options[i] = new Array("<span class=processOff>We appreciate your feedback!  Please click here to leave us your comments or suggestions.</span>","javascript:window.open('http://www.tsagate.com/procurement/contact/suggestions.html'); void(0);","<img src='/" + context + "/images/bullet_red_onwhite.gif'>","","Click here to share your suggestions with us.");
	i++;

    return options;
  }

  function createUpcomingB2BEvents(originalArray)
  {
  	var options = new Array();
    var i = 1;

    options[0] = originalArray;

    options[i] = new Array("<span class=error>Bienvenidos a Servicios B2B!</span><br><br>","","","","");
    i++;

    return options;
  }

  function createMyAssetOptions(originalArray)
  {
  	var options = new Array();
    var i = 1;

    options[0] = originalArray;

	if (assetAccess > 0) {
	    options[i] = new Array(assetitemnumber,"javascript:viewAssets();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Inventory Items marked as assets.");
	    i++;
	    options[i] = new Array(assetbylocation,"javascript:viewAssetsByLocation();void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a list of assets and its locations.");
	    i++;
	    options[i] = new Array(assetleaserwdat,"javascript:browse('asset_lease');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a list of assets with a lease renewal date greater than today.");
	    i++;
	    options[i] = new Array(assetwarrtyexpr,"javascript:browse('asset_warrantyexpire');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a list of assets with expired warrenties.");
	    i++;
	    options[i] = new Array(assetunprinted, "javascript:browse('asset_unprinted');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show a list of assets that has no barcodes printed.");
	    i++;
    }

    return options;
  }

  function createMyAccountsPayableActions(originalArray)
  {
  	var options = new Array();
    var i = 1;

    options[0] = originalArray;

	if (apAccess > 0 || vchApprover )
	{
		if (checkReqAccess > 0 || vchApprover )
		{
		    options[i] = new Array(approvecheckrequests,"javascript:viewReqs('CHECK');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Admin Check Requests awaiting your approval.");
		    i++;
	    }
	    options[i] = new Array(incompleteinvoices,"javascript:viewInvoices('INCOMPLETE');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show your Incomplete Invoices.");
	    i++;
	    options[i] = new Array(invoicespendingapproval,"javascript:viewInvoices('APPROVING');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Invoices Pending Approval.");
	    i++;
	    options[i] = new Array(approvedinvoices,"javascript:viewInvoices('APPROVED');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Approved Invoices.");
	    i++;
	    options[i] = new Array(vendorwithoutap,"javascript:viewVendorAP('VENDOR');void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show Suppliers that need an Accounts Payable reference number.");
	    i++;
	    if (vchApprover) {
			options[i] = new Array(invoiceswaitingmyapproval,"javascript: viewInvoices('APPROVAL'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show all Invoices awaiting your approval.");
			i++;
    	}
    	if (overrider) {
    		options[i] = new Array(invoicepipeline,"javascript: browse('invoice-pipeline'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show an Invoice Pipeline browse.");
			i++;
		}
		else if (vchApprover) {
    		options[i] = new Array(invoicepipeline,"javascript: browse('invoice-pipeline-no-link'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will show an Invoice Pipeline browse.");
			i++;
		}
		if (printChecksAccess > 0) {
			options[i] = new Array(printChecks, "javascript: browseFilter('print-check-invoices'); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to view a list of Payable Invoices.");
			i++;
		}
		if (exportFixedAssetsAccess > 0) {
			options[i] = new Array(exportFixedAssets, "javascript: expFixedAssets(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Export Fixed Assets.");
			i++;
		}
		if (exportGLTranAccess > 0) {
			options[i] = new Array(exportGLTran, "javascript: expGLTran(); void(0);","<span class=raquo>&raquo;</span>","","Clicking here will allow you to Export GL Transactions.");
			i++;
		}
	}
    return options;
  }

  function createGraphOptions(originalArray, graphName)
  {
      var options = new Array();
      var i = 1;

      options[0] = originalArray;
      options[1] = new Array(graphName, "", "", "");

      return options;
  }

  function createStartupOptions(originalArray) {
		var options = new Array();
		var i = 1;
		var istep = 1;

		options[0] = originalArray;
		options[i] = new Array("<span class=gettingStarted>Quick System Setup</span>", "javascript: viewQuickSetup(); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - Customize default system setup options.","Clicking here will allow you to quickly setup your system.");
		i++;
		istep++;
		options[i] = new Array("&nbsp;","","","","");
		i++;
		options[i] = new Array("<span class=gettingStarted>Review System Data</span>", "javascript: viewSystemData(); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - Review, customize and add system data.","Clicking here will allow you to review default data or enter custom data to fit your needs.");
		i++;
		istep++;
		options[i] = new Array("&nbsp;","","","","");
		i++;
		options[i] = new Array("<span class=gettingStarted>Add Employees</span>", "javascript: browse('admin-userprofile'); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - Add employees and approvers.  Register requisitioners and buyers.","Clicking here will allow you to add employee profiles to be used in selcting requisitioner information, approvers, etc.");
		i++;
		istep++;
		options[i] = new Array("&nbsp;","","","","");
		i++;
		options[i] = new Array("<span class=gettingStarted>How To Administer Your System</span>", "javascript: howToAdminister(); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - View an administrative 'How To Guide'.","Clicking here will allow you to View an administrative 'How To Guide'.");
		i++;
		istep++;
		options[i] = new Array("&nbsp;","","","","");
		i++;
		options[i] = new Array("<span class=gettingStarted>How To Create Your 1st PO</span>", "javascript: howToCreatePO(); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - View a 'How To Guide' on creating Purchase Orders.","Clicking here will allow you to learn how to create your 1st PO.");
		i++;
		istep++;
		options[i] = new Array("&nbsp;","","","","");
		i++;
		options[i] = new Array("<span class=gettingStarted>Create Your 1st PO</span>", "javascript: createOrder('PO'); void(0);","<span class=gettingStarted>" + istep + "&nbsp;</span>"," - You are ready to get started!  Create your 1st Purchase Order now!","Clicking here will create your 1st PO.");
		i++;
		istep++;
		options[i] = new Array("","","","","");
		i++;

		return options;
  }