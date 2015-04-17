var xmlHttp;// global instance of XMLHttpRequest

function createXmlHttpRequest()
{
       if(window.ActiveXObject)
       {
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }

    else if(window.XMLHttpRequest)
    {
        xmlHttp=new XMLHttpRequest();
     }

}

function startRequest(url)
{
  createXmlHttpRequest();


  xmlHttp.open("GET", url ,true);
  xmlHttp.onreadystatechange = handleStateChange;
  xmlHttp.send(null);

}

function handleStateChange()
{
    if(xmlHttp.readyState==4)
    {
        if(xmlHttp.status==200)
        {
        	//var message = xmlHttp.responseText;
        	var inData = xmlHttp.responseXML;
        	var graph = inData.getElementsByTagName("graph")[0];
        	var width = graph.getAttribute("width");
          	var graphId = graph.getAttribute("id");
          	var title = graph.getAttribute("title");
          	var ufilter = graph.getAttribute("ufilter");

			newGraphPanel(graphId, '100', '123', xmlHttp.responseText, title, '400', width, ufilter);
			//visiblePanelsArray.push(panelsArray[panelsArray.length - 1]);

             //document.getElementById("results").innerHTML=message;
        }
        else
        {
           alert("Error loading pagen" + xmlHttp.status + ":" + xmlHttp.statusText);
        }
    }
}
