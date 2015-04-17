package com.tsa.puridiom.itemweb.tasks;

public class ReponseFactory
{
	public static IResponseParser getParser(String webService)
	{
		if(webService.equalsIgnoreCase("openkapow"))
		{
			return new ParserOpenKapow();
		}
		else if(webService.equalsIgnoreCase("comacsoap"))
		{
			return new ParserComacSoap();
		}
		return null;
	}
}