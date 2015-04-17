/*
 * Created on Jul 15, 2003 
 */
package com.tsa.puridiom.handlers;

import java.util.*;

/**
 * @author Administrator 
 */
public interface IHandler {
	public Map handleRequest(Map incomingRequest) throws Exception;
}
