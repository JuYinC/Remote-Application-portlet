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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.remote.application.service.ClpSerializer;
import com.remote.application.service.RemoteFormLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuYin
 */
public class RemoteFormClp extends BaseModelImpl<RemoteForm>
	implements RemoteForm {
	public RemoteFormClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return RemoteForm.class;
	}

	@Override
	public String getModelClassName() {
		return RemoteForm.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _remoteFormId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRemoteFormId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _remoteFormId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("remoteFormId", getRemoteFormId());
		attributes.put("creator", getCreator());
		attributes.put("applicant", getApplicant());
		attributes.put("remoteDate", getRemoteDate());
		attributes.put("endDateTime", getEndDateTime());
		attributes.put("remark", getRemark());
		attributes.put("extPayload", getExtPayload());
		attributes.put("formTitle", getFormTitle());
		attributes.put("dayTab", getDayTab());
		attributes.put("calendarBookingId", getCalendarBookingId());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUsername", getStatusByUsername());
		attributes.put("statusDate", getStatusDate());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("cuserId", getCuserId());
		attributes.put("muserId", getMuserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long remoteFormId = (Long)attributes.get("remoteFormId");

		if (remoteFormId != null) {
			setRemoteFormId(remoteFormId);
		}

		String creator = (String)attributes.get("creator");

		if (creator != null) {
			setCreator(creator);
		}

		String applicant = (String)attributes.get("applicant");

		if (applicant != null) {
			setApplicant(applicant);
		}

		Date remoteDate = (Date)attributes.get("remoteDate");

		if (remoteDate != null) {
			setRemoteDate(remoteDate);
		}

		Date endDateTime = (Date)attributes.get("endDateTime");

		if (endDateTime != null) {
			setEndDateTime(endDateTime);
		}

		String remark = (String)attributes.get("remark");

		if (remark != null) {
			setRemark(remark);
		}

		String extPayload = (String)attributes.get("extPayload");

		if (extPayload != null) {
			setExtPayload(extPayload);
		}

		String formTitle = (String)attributes.get("formTitle");

		if (formTitle != null) {
			setFormTitle(formTitle);
		}

		String dayTab = (String)attributes.get("dayTab");

		if (dayTab != null) {
			setDayTab(dayTab);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUsername = (String)attributes.get("statusByUsername");

		if (statusByUsername != null) {
			setStatusByUsername(statusByUsername);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long cuserId = (Long)attributes.get("cuserId");

		if (cuserId != null) {
			setCuserId(cuserId);
		}

		Long muserId = (Long)attributes.get("muserId");

		if (muserId != null) {
			setMuserId(muserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public long getRemoteFormId() {
		return _remoteFormId;
	}

	@Override
	public void setRemoteFormId(long remoteFormId) {
		_remoteFormId = remoteFormId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setRemoteFormId", long.class);

				method.invoke(_remoteFormRemoteModel, remoteFormId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCreator() {
		return _creator;
	}

	@Override
	public void setCreator(String creator) {
		_creator = creator;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setCreator", String.class);

				method.invoke(_remoteFormRemoteModel, creator);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getApplicant() {
		return _applicant;
	}

	@Override
	public void setApplicant(String applicant) {
		_applicant = applicant;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setApplicant", String.class);

				method.invoke(_remoteFormRemoteModel, applicant);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getRemoteDate() {
		return _remoteDate;
	}

	@Override
	public void setRemoteDate(Date remoteDate) {
		_remoteDate = remoteDate;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setRemoteDate", Date.class);

				method.invoke(_remoteFormRemoteModel, remoteDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEndDateTime() {
		return _endDateTime;
	}

	@Override
	public void setEndDateTime(Date endDateTime) {
		_endDateTime = endDateTime;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDateTime", Date.class);

				method.invoke(_remoteFormRemoteModel, endDateTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRemark() {
		return _remark;
	}

	@Override
	public void setRemark(String remark) {
		_remark = remark;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setRemark", String.class);

				method.invoke(_remoteFormRemoteModel, remark);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExtPayload() {
		return _extPayload;
	}

	@Override
	public void setExtPayload(String extPayload) {
		_extPayload = extPayload;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setExtPayload", String.class);

				method.invoke(_remoteFormRemoteModel, extPayload);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFormTitle() {
		return _formTitle;
	}

	@Override
	public void setFormTitle(String formTitle) {
		_formTitle = formTitle;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setFormTitle", String.class);

				method.invoke(_remoteFormRemoteModel, formTitle);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDayTab() {
		return _dayTab;
	}

	@Override
	public void setDayTab(String dayTab) {
		_dayTab = dayTab;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setDayTab", String.class);

				method.invoke(_remoteFormRemoteModel, dayTab);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setCalendarBookingId",
						long.class);

				method.invoke(_remoteFormRemoteModel, calendarBookingId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_remoteFormRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUserId", long.class);

				method.invoke(_remoteFormRemoteModel, statusByUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatusByUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
			_statusByUserUuid);
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		_statusByUserUuid = statusByUserUuid;
	}

	@Override
	public String getStatusByUsername() {
		return _statusByUsername;
	}

	@Override
	public void setStatusByUsername(String statusByUsername) {
		_statusByUsername = statusByUsername;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusByUsername",
						String.class);

				method.invoke(_remoteFormRemoteModel, statusByUsername);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setStatusDate", Date.class);

				method.invoke(_remoteFormRemoteModel, statusDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_remoteFormRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_remoteFormRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCuserId() {
		return _cuserId;
	}

	@Override
	public void setCuserId(long cuserId) {
		_cuserId = cuserId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setCuserId", long.class);

				method.invoke(_remoteFormRemoteModel, cuserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMuserId() {
		return _muserId;
	}

	@Override
	public void setMuserId(long muserId) {
		_muserId = muserId;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setMuserId", long.class);

				method.invoke(_remoteFormRemoteModel, muserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_remoteFormRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_remoteFormRemoteModel != null) {
			try {
				Class<?> clazz = _remoteFormRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_remoteFormRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getDisplayRmoteDate() {
		try {
			String methodName = "getDisplayRmoteDate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getExtraDataJSON() {
		try {
			String methodName = "getExtraDataJSON";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getRemoteFormRemoteModel() {
		return _remoteFormRemoteModel;
	}

	public void setRemoteFormRemoteModel(BaseModel<?> remoteFormRemoteModel) {
		_remoteFormRemoteModel = remoteFormRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _remoteFormRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_remoteFormRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			RemoteFormLocalServiceUtil.addRemoteForm(this);
		}
		else {
			RemoteFormLocalServiceUtil.updateRemoteForm(this);
		}
	}

	@Override
	public RemoteForm toEscapedModel() {
		return (RemoteForm)ProxyUtil.newProxyInstance(RemoteForm.class.getClassLoader(),
			new Class[] { RemoteForm.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		RemoteFormClp clone = new RemoteFormClp();

		clone.setRemoteFormId(getRemoteFormId());
		clone.setCreator(getCreator());
		clone.setApplicant(getApplicant());
		clone.setRemoteDate(getRemoteDate());
		clone.setEndDateTime(getEndDateTime());
		clone.setRemark(getRemark());
		clone.setExtPayload(getExtPayload());
		clone.setFormTitle(getFormTitle());
		clone.setDayTab(getDayTab());
		clone.setCalendarBookingId(getCalendarBookingId());
		clone.setStatus(getStatus());
		clone.setStatusByUserId(getStatusByUserId());
		clone.setStatusByUsername(getStatusByUsername());
		clone.setStatusDate(getStatusDate());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setCuserId(getCuserId());
		clone.setMuserId(getMuserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());

		return clone;
	}

	@Override
	public int compareTo(RemoteForm remoteForm) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), remoteForm.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RemoteFormClp)) {
			return false;
		}

		RemoteFormClp remoteForm = (RemoteFormClp)obj;

		long primaryKey = remoteForm.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{remoteFormId=");
		sb.append(getRemoteFormId());
		sb.append(", creator=");
		sb.append(getCreator());
		sb.append(", applicant=");
		sb.append(getApplicant());
		sb.append(", remoteDate=");
		sb.append(getRemoteDate());
		sb.append(", endDateTime=");
		sb.append(getEndDateTime());
		sb.append(", remark=");
		sb.append(getRemark());
		sb.append(", extPayload=");
		sb.append(getExtPayload());
		sb.append(", formTitle=");
		sb.append(getFormTitle());
		sb.append(", dayTab=");
		sb.append(getDayTab());
		sb.append(", calendarBookingId=");
		sb.append(getCalendarBookingId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUsername=");
		sb.append(getStatusByUsername());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", cuserId=");
		sb.append(getCuserId());
		sb.append(", muserId=");
		sb.append(getMuserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append("com.remote.application.model.RemoteForm");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>remoteFormId</column-name><column-value><![CDATA[");
		sb.append(getRemoteFormId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creator</column-name><column-value><![CDATA[");
		sb.append(getCreator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applicant</column-name><column-value><![CDATA[");
		sb.append(getApplicant());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteDate</column-name><column-value><![CDATA[");
		sb.append(getRemoteDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDateTime</column-name><column-value><![CDATA[");
		sb.append(getEndDateTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remark</column-name><column-value><![CDATA[");
		sb.append(getRemark());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>extPayload</column-name><column-value><![CDATA[");
		sb.append(getExtPayload());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formTitle</column-name><column-value><![CDATA[");
		sb.append(getFormTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dayTab</column-name><column-value><![CDATA[");
		sb.append(getDayTab());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarBookingId</column-name><column-value><![CDATA[");
		sb.append(getCalendarBookingId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUsername</column-name><column-value><![CDATA[");
		sb.append(getStatusByUsername());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cuserId</column-name><column-value><![CDATA[");
		sb.append(getCuserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>muserId</column-name><column-value><![CDATA[");
		sb.append(getMuserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _remoteFormId;
	private String _creator;
	private String _applicant;
	private Date _remoteDate;
	private Date _endDateTime;
	private String _remark;
	private String _extPayload;
	private String _formTitle;
	private String _dayTab;
	private long _calendarBookingId;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserUuid;
	private String _statusByUsername;
	private Date _statusDate;
	private long _companyId;
	private long _groupId;
	private long _cuserId;
	private long _muserId;
	private Date _createDate;
	private Date _modifiedDate;
	private BaseModel<?> _remoteFormRemoteModel;
	private Class<?> _clpSerializerClass = com.remote.application.service.ClpSerializer.class;
}