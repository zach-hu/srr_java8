<%@page import="java.util.Hashtable"%>
<%@ page import="java.util.Vector,java.lang.StringBuffer;" %>
<%!

class NodeList{
	Vector v;
	int length=0;
	public NodeList(){
		v=new Vector();
	}

	public void add(Node node){
		v.add(node);
		length++;
	}
	public void add(String text){
		add(new Node(text));
	}
	public Node item(int index){
		return (Node)v.get(index);
	}
}

class Node{
	public String text;
	public String href;
	public String target="";
	public String toolTip;
	public NodeList childNodes;
	public String imageUrl="";
	public int length=0;
	public Node parent = null ;
	public java.util.Hashtable  properties = new java.util.Hashtable() ;

	public Node(){
		childNodes = new NodeList();
	}

	public Node(String text){
		this(text,"");
	}
	public Node(String text,String href){
		this(text,href,"");
	}

	public Node(String text,String href,String toolTip){
		this();
		this.text=text;
		this.href=href;
		this.toolTip=toolTip;
	}

	public void add(Node node){
		node.parent = this ;
		childNodes.add(node);
		length++;
	}

	public void add(String text){
		add(new Node(text));
	}
}

class TreeView{
	private String folder="images";
	private String color="navy";
	private NodeList nodes;
	private java.util.ArrayList folderList  = new java.util.ArrayList() ;
	private String target="";
	private int	nodeId = 0;
	public int length=0;
	private StringBuffer buf;
	private Node			currentNode = null;
	private int			currentLevel = 0;
	private String		treeScript = null ;
	private String		treeStyle = null ;

	public TreeView(){
		nodes=new NodeList();
		buf  = new StringBuffer();
	}

	public void setImagesUrl(String url){
		this.folder = url;
	}

	public void add(Node node){
		nodes.add(node);
		length++;
	}

	public void add(String text){
		add(new Node(text));
	}

	public Node createNode(String text){
		currentNode = new Node(text) ;
		return currentNode ;
	}

	public Node createNode(String text,String href,String toolTip){
		currentNode =  new Node(text,href,toolTip);
		return currentNode ;
	}

	private void print(String text){
		buf.append(text);
	}

	public void createItem(int lv, String text)
	{
		createItem(lv, text, "", "");
		return ;
	}

	public void createItem(int lv, String text, String href)
	{
		createItem(lv, text, href, "");
		return ;
	}

	public void createItem(int lv, String text,String href,String toolTip)
	{
		System.out.println(lv) ;
		if (lv == 0) {
			this.add(this.createNode(text,href,toolTip)) ;
			currentLevel = 0 ;
		} else {
			if (lv < currentLevel) {
				while (currentLevel > lv) {
					currentNode = currentNode.parent ;
					currentLevel-- ;
				}
				createItem(currentLevel, text, href, toolTip) ;
			} else if (lv > currentLevel) {
				currentLevel++ ;
				currentNode.add(createNode(text, href, toolTip)) ;
			} else {
				Node p = currentNode.parent ;
				p.add(createNode(text, href, toolTip)) ;
			}
		}

		return  ;
	}

	public void setTreeScript(String treeScript)  {
		this.treeScript = treeScript ;
	}

	public void setTreeStyle(String treeStyle) {
		this.treeStyle = treeStyle ;
	}

	public String getTree(){
		if (this.treeScript == null) {
			print("<script>function openFrameUrl(){if(parent.location.href == self.location.href){ window.location.href =document.getElementById(p).className='folderOpen';}}  function toggle(id,p){var myChild = document.getElementById(id);if(myChild.style.display!='block'){myChild.style.display='block';document.getElementById(p).className='folderOpen'; }else{myChild.style.display='none';document.getElementById(p).className='folder';}}</script>");
		} else {
			print(this.treeScript) ;
		}
		if (this.treeStyle == null) {
			print("<style>ul.tree{display:none;margin-left:17px;}li.folder{list-style-image: url("+ folder +"/plus.gif);}li.folderOpen{list-style-image: url("+ folder +"/minus.gif);}li.file{list-style-image: url("+ folder +"/dot.gif);}a.treeview{color:"+ color +";font-family:verdana;font-size:8pt;}a.treeview:link {text-decoration:none;}a.treeview:visited{text-decoration:none;}a.treeview:hover {text-decoration:underline;}</style>");
		} else {
			print(treeStyle) ;
		}
		loopThru(nodes,"0");
		return buf.toString();
	}

	private void loopThru(NodeList nodeList, String parent){
		boolean hasChild;
		String style;

		if(parent!="0"){
			print("<ul class=tree id=\"N" + parent + "\">");
		}else{
			print("<ul id=\"N" + parent + "\">");
		}
		for (int i=0;i<nodeList.length;i++){
			Node node = nodeList.item(i);

			if(node.childNodes.length>0){
				hasChild=true;
			}else{
				hasChild=false;
			}

			if(node.imageUrl==""){
				style="";
			}else{
				style="style='list-style-image: url("+ node.imageUrl +");'";
			}
			if(hasChild){
				String		folderref = "" ;
				if (node.href.trim().length() > 0) {
					folderref = "parent.content.location.href='" + node.href + "';" ;
				}
				print("<li "+ style +" class=folder id='P" + parent + i + "'><a class=treeview href=\"javascript:" + folderref + "toggle('N" + parent + "_" + i + "','P" + parent + i + "');\">" + node.text + "</a>");

				java.util.Hashtable nd = new java.util.Hashtable() ;
				nd.put("href",folderref) ;
				nd.put("nValue", "N" + parent + "_" + i) ;
				nd.put("pValue", "P" + parent + i) ;
				folderList.add(nd) ;

			}else{
				if(node.target==""){
					node.target=target;
				}
				print("<li "+ style +" class=file><a class=treeview href='" + node.href + "' target='" + node.target + "'  title=\"" + node.toolTip + "\">" + node.text + "</a>");
			}

			if(hasChild){
				loopThru(node.childNodes,parent + "_" + i);
			}

			print("</li>");
		}
		print("</ul>");
	}
}
%>