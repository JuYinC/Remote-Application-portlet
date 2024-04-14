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

package com.remote.application.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the RemoteForm service. Represents a row in the &quot;remote_RemoteForm&quot; database table, with each column mapped to a property of this class.
 *
 * @author JuYin
 * @see RemoteFormModel
 * @see com.remote.application.model.impl.RemoteFormImpl
 * @see com.remote.application.model.impl.RemoteFormModelImpl
 * @generated
 */
public interface RemoteForm extends RemoteFormModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.remote.application.model.impl.RemoteFormImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getExtraDataJSON();

	public java.lang.String getDisplayRmoteDate();
}