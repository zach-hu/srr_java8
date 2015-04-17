/**
 *
 */
package com.tsa.puridiom.cxml.exceptions;

/**
 * @author Johnny Zapana
 */
public class PunchCatalogException extends Exception
{

	/**
	 * Creates a new instance of <code>NewException</code> without detail
	 * message.
	 */
	public PunchCatalogException()
	{
	}

	/**
	 * Constructs an instance of <code>NewException</code> with the specified
	 * detail message.
	 *
	 * @param msg
	 *            the detail message.
	 */
	public PunchCatalogException(String msg)
	{
		super(msg);
	}
}
