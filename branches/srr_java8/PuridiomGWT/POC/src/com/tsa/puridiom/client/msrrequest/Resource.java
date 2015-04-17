/*
 * Copyright 2010 The gwtquery plugins team.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tsa.puridiom.client.msrrequest;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList.Style;

/**
 * Resources used in this module
 * 
 * @author Jigar Mistry
 * 
 */
public interface Resource extends CellList.Resources {

	final static Resource INSTANCE = GWT
			.create(Resource.class);

	/**
	 * the overrided styles
	 */
	@Source("my-style.css")
	Style cellListStyle();
	
	ImageResource contact();
}
