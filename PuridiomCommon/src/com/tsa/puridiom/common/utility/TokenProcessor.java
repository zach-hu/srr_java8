/**
 * 
 */
package com.tsa.puridiom.common.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class TokenProcessor
{
	/**
	 * The singleton instance of this class.
	 */
	private static TokenProcessor instance = new TokenProcessor();

	/**
	 * The session attributes key under which our transaction token is stored,
	 * if it is used.
	 */
	private static final String TRANSACTION_TOKEN_KEY = "TRANSACTION_TOKEN";
	
	/**
	 * The session attributes key under which our request token is stored,
	 * if it is used.
	 */
	public static final String REQUEST_TOKEN_KEY = "REQUEST_TOKEN";
	
	/**
	 * The request attributes key under which our request token is stored,
	 * if it is used.
	 */
	private static final String REQUEST_KEY = "REQUEST_KEY";

	/**
	 * The property under which a transaction token is reported.
	 */
	public static final String TOKEN_KEY = "com.tsa.puridiom.TOKEN";
	
	/**
	 * The timestamp used most recently to generate a token value.
	 */
	private long previous;

	/**
	 * Protected constructor for TokenProcessor. Use
	 * TokenProcessor.getInstance() to obtain a reference to the processor.
	 */
	protected TokenProcessor()
	{
		super();
	}

	/**
	 * Retrieves the singleton instance of this class.
	 */
	public static TokenProcessor getInstance()
	{
		return instance;
	}

	/**
	 * <p>
	 * Return <code>true</code> if there is a transaction token stored in the
	 * user's current session, and the value submitted as a request parameter
	 * with this action matches it. Returns <code>false</code> under any of
	 * the following circumstances:
	 * </p>
	 * <ul>
	 * <li>No session associated with this request</li>
	 * <li>No transaction token saved in the session</li>
	 * <li>No transaction token included as a request parameter</li>
	 * <li>The included transaction token value does not match the transaction
	 * token in the user's session</li>
	 * </ul>
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	public synchronized boolean validToken(HttpServletRequest request)
	{
		// Retrieve the current session for this request
		HttpSession session = request.getSession(false);

		if (session == null)
		{
			return false;
		}

		// Retrieve the transaction token from this session, and
		// reset it if requested
		String saved = (String) session.getAttribute(TokenProcessor.TRANSACTION_TOKEN_KEY);

		if (saved == null)
		{
			return false;
		}

		// Retrieve the transaction token included in this request
		String token = request.getParameter(TokenProcessor.TOKEN_KEY);

		if (token == null)
		{
			return false;
		}

		if (!saved.equals(token))
		{
			return false;
		}

		// remove the token so it won't be used again
		session.removeAttribute(TokenProcessor.TRANSACTION_TOKEN_KEY);

		return true;
	}

	/**
	 * Generate a new transaction token in the user's current session, creating a new
	 * session if necessary.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	public synchronized String generateToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String token = getToken(request);

		if (token != null)
		{
			session.setAttribute(TokenProcessor.TRANSACTION_TOKEN_KEY, token);
		}
		
		return token;
	}
	
	/**
	 * 
	 * @param request
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized String generateToken(HttpServletRequest request, String value)
	{
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		String token = getToken(request);
		Log.debug(this, "generateToken - Request URI: " + uri);
		Log.debug(this, "Token generated: " + token);
		
		if (token != null && value != null)
		{
			Map<String, Integer> generatedTokens = (Map<String, Integer>) session.getAttribute(TokenProcessor.REQUEST_TOKEN_KEY);
			if (generatedTokens == null) {
				generatedTokens = new HashMap<String, Integer>();
				session.setAttribute(TokenProcessor.REQUEST_TOKEN_KEY, generatedTokens);
			}
			generatedTokens.put(token, 0);
			Log.debug(this, "Put token in map: " + token);
		}		
		return token;
	}
	
	/**
	 * 
	 * @param request
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean validToken(HttpServletRequest request, String value) {
		
		boolean result = false;
		
		String uri = request.getRequestURI();
		Log.debug(this, "validToken - Request URI: " + uri);
		
		// Retrieve the current session for this request
		HttpSession session = request.getSession(false);

		// Check if the session is valid
		if (session == null) {
			result = false;
		}
		
		// Retrieve the tokens generated
		Map<String, Integer> generatedTokens = (Map<String, Integer>)  session.getAttribute(TokenProcessor.REQUEST_TOKEN_KEY);
		if (generatedTokens.containsKey(value)) {
			Log.debug(this, "validToken - token valid: " + value);
			result = true;
			generatedTokens.remove(value);
		} else {
			Log.debug(this, "validToken - token INVALID: " + value);
			result = false;
		}
		
		/*Integer counter = generatedTokens.get(value);
		if (counter == 0) {
			result = true;
			generatedTokens.put(value, 1);
		} else if (counter > 0) { 
			result = false;
		} else {
			result = false;
		}*/
		
		return result;
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public String generateMD5(String token) {
		return createMD5(token);
	}
	
	
	/**
	 * Create a new transaction token, to be used for enforcing a single
	 * request for a particular transaction.
	 * 
	 * @param id
	 *            a unique Identifier for the session or other context in which
	 *            this token is to be used.
	 */
	private synchronized String createMD5(String id)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(id.getBytes());

			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e)
		{
			return null;
		}
	}
	
	
	
	/**
	 * Get a new transaction token, to be used for enforcing a single
	 * request for a particular transaction.
	 * 
	 * @param request
	 *            The request we are processing
	 */
	private synchronized String getToken(HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		return createToken(session.getId());
	}

	/**
	 * Create a new transaction token, to be used for enforcing a single
	 * request for a particular transaction.
	 * 
	 * @param id
	 *            a unique Identifier for the session or other context in which
	 *            this token is to be used.
	 */
	private synchronized String createToken(String id)
	{
		try
		{
			long current = System.currentTimeMillis();

			if (current == previous)
			{
				current++;
			}

			previous = current;

			byte[] now = new Long(current).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(id.getBytes());
			md.update(now);

			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e)
		{
			return null;
		}
	}

	/**
	 * Convert a byte array to a String of hexadecimal digits and return it.
	 * 
	 * @param buffer
	 *            The byte array to be converted
	 */
	private String toHex(byte[] buffer)
	{
		StringBuffer sb = new StringBuffer(buffer.length * 2);

		for (int i = 0; i < buffer.length; i++)
		{
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}

		return sb.toString();
	}
}
