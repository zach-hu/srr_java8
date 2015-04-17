package com.tsa.puridiom.supplierportal.registration.tasks;

import com.tsa.puridiom.supplierportal.exception.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class GenerateVendorIdFromName extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		try {
			String name = (String) incomingRequest.get("vendorName");
			String supplier = "";
			String scode = "";
			String fword, sword;
			String saveName = name;
			String test;
			
			if (Utility.isEmpty(name)) {
				throw new RegistrationException("VendorName cannot be empty.");
			}
			
			name = name.toUpperCase();
			//system.out.println("Vendor name - " + name);
			
			int len = name.length();
			
			for (int n = 0; n < len; n++) {
				test = name.substring(n, n + 1);
				
				if (" ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(test) < 0) {
					name = name.substring(0, n) + "" + name.substring(n + 1, name.length());
					len--;
				}
			}

			name = name.trim();
			int ind = name.indexOf(" ");

			if (ind >= 0) {
				fword = name.substring(0, ind);
				name = name.substring(ind + 1, name.length());

				ind = name.indexOf(" ");
				if (ind >= 0) {
					sword = name.substring(0, ind);
					name = name.substring(ind + 1, name.length());

					if (name.length() > 0) {
						switch (fword.length()) {
							case 1 :
								if (sword.length() > 2) {
									scode = fword.substring(0, 1) + sword.substring(0, 3) + name.substring(0, 2);
								} else if (name.length() > 2) {
									scode = fword.substring(0, 1) + sword.substring(0, sword.length()) + name.substring(0, 3);
								} else {
									scode = fword.substring(0, 1) + sword.substring(0, sword.length()) + name.substring(0, name.length());
								}
								break;
							case 2 :
								if (sword.length() > 2) {
									scode = fword.substring(0, 2) + sword.substring(0, 3) + name.substring(0, 1);
								} else if (name.length() > 1) {
									scode = fword.substring(0, 2) + sword.substring(0, sword.length()) + name.substring(0, 2);
								} else {
									scode = fword.substring(0, 2) + sword.substring(0, sword.length()) + name.substring(0, name.length());
								}
								break;
							default :
								if (sword.length() == 1) {
									if (name.length() > 1) {
										scode = fword.substring(0, 3) + sword.substring(0, 1) + name.substring(0, 2);
									} else {
										scode = fword.substring(0, 3) + sword.substring(0, 1) + name.substring(0, name.length());
									}
								} else {
									scode = fword.substring(0, 3) + sword.substring(0, 2) + name.substring(0, 1);
								}
						}
					} else {
						switch (fword.length()) {
							case 1 :
								if (sword.length() > 2) {
									scode = fword.substring(0, 1) + sword.substring(0, 3);
								} else {
									scode = fword.substring(0, 1) + sword.substring(0, sword.length());
								}
								break;
							case 2 :
								if (sword.length() > 2) {
									scode = fword.substring(0, 2) + sword.substring(0, 3);
								} else {
									scode = fword.substring(0, 2) + sword.substring(0, sword.length());
								}
								break;
							default :
								if (sword.length() > 2) {
									scode = fword.substring(0, 3) + sword.substring(0, 3);
								} else {
									scode = fword.substring(0, 3) + sword.substring(0, sword.length());
								}
						}
					}
				} else {
					if (name.length() > 0) {
						switch (fword.length()) {
							case 1 :
								scode = fword.substring(0, 1) + name.substring(0, 3);
								break;
							case 2 :
								scode = fword.substring(0, 2) + name.substring(0, 3);
								break;
							default :
								scode = fword.substring(0, 3) + name.substring(0, 3);
								break;
						}
					}
				}
			} else if (name.length() >= 6) {
				scode = name.substring(0, 6);
			} else {
				scode = name;
			}

			if (scode.length() < 6) {
				scode = scode + "000000";
				scode = scode.substring(0, 6);
			}

			result = scode;
			
			//system.out.println("Return code: " + result);
			
			this.status = Status.SUCCEEDED;
		} catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}