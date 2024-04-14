/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.remote.application.model.impl;

import java.text.DateFormat;

import com.remote.application.util.RemoteUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * The extended model implementation for the RemoteForm service. Represents a row in the &quot;remote_RemoteForm&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.remote.application.model.RemoteForm} interface.
 * </p>
 *
 * @author JuYin
 */
public class RemoteFormImpl extends RemoteFormBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a remote form model instance should use the {@link com.remote.application.model.RemoteForm} interface instead.
	 */
	public RemoteFormImpl() {
	}
	
	public String getExtraDataJSON() {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("title", getFormTitle());
		jsonObject.put("description", getRemark());

		return jsonObject.toString();
	}
	
	public String getDisplayRmoteDate() {
		
		DateFormat dateFormat = RemoteUtil.getDateFormat();
		return dateFormat.format(getRemoteDate());
		
	}
}