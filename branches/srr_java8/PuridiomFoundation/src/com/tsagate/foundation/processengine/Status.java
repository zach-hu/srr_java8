/*
 * Created on Aug 26, 2003
 */
package com.tsagate.foundation.processengine;

/**
 * @author Administrator
 */
public class Status {
	public static final int FAILED = 0;
//	public static final int ABORTED = 10;
//	public static final int ABORTING = 20;
//	public static final int CANCELLED = 30;
	public static final int READY = 40;
//	public static final int ACTIVE = 50;
//	public static final int COMPLETING = 60;	
	public static final int COMPLETED = 70;
	public static final int SUCCEEDED = 80;
	public static final int DUPLICATED_REQUEST = 100;
}
