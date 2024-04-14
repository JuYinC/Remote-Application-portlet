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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RemoteForm}.
 * </p>
 *
 * @author JuYin
 * @see RemoteForm
 * @generated
 */
public class RemoteFormWrapper implements RemoteForm, ModelWrapper<RemoteForm> {
	public RemoteFormWrapper(RemoteForm remoteForm) {
		_remoteForm = remoteForm;
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

	/**
	* Returns the primary key of this remote form.
	*
	* @return the primary key of this remote form
	*/
	@Override
	public long getPrimaryKey() {
		return _remoteForm.getPrimaryKey();
	}

	/**
	* Sets the primary key of this remote form.
	*
	* @param primaryKey the primary key of this remote form
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_remoteForm.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the remote form ID of this remote form.
	*
	* @return the remote form ID of this remote form
	*/
	@Override
	public long getRemoteFormId() {
		return _remoteForm.getRemoteFormId();
	}

	/**
	* Sets the remote form ID of this remote form.
	*
	* @param remoteFormId the remote form ID of this remote form
	*/
	@Override
	public void setRemoteFormId(long remoteFormId) {
		_remoteForm.setRemoteFormId(remoteFormId);
	}

	/**
	* Returns the creator of this remote form.
	*
	* @return the creator of this remote form
	*/
	@Override
	public java.lang.String getCreator() {
		return _remoteForm.getCreator();
	}

	/**
	* Sets the creator of this remote form.
	*
	* @param creator the creator of this remote form
	*/
	@Override
	public void setCreator(java.lang.String creator) {
		_remoteForm.setCreator(creator);
	}

	/**
	* Returns the applicant of this remote form.
	*
	* @return the applicant of this remote form
	*/
	@Override
	public java.lang.String getApplicant() {
		return _remoteForm.getApplicant();
	}

	/**
	* Sets the applicant of this remote form.
	*
	* @param applicant the applicant of this remote form
	*/
	@Override
	public void setApplicant(java.lang.String applicant) {
		_remoteForm.setApplicant(applicant);
	}

	/**
	* Returns the remote date of this remote form.
	*
	* @return the remote date of this remote form
	*/
	@Override
	public java.util.Date getRemoteDate() {
		return _remoteForm.getRemoteDate();
	}

	/**
	* Sets the remote date of this remote form.
	*
	* @param remoteDate the remote date of this remote form
	*/
	@Override
	public void setRemoteDate(java.util.Date remoteDate) {
		_remoteForm.setRemoteDate(remoteDate);
	}

	/**
	* Returns the end date time of this remote form.
	*
	* @return the end date time of this remote form
	*/
	@Override
	public java.util.Date getEndDateTime() {
		return _remoteForm.getEndDateTime();
	}

	/**
	* Sets the end date time of this remote form.
	*
	* @param endDateTime the end date time of this remote form
	*/
	@Override
	public void setEndDateTime(java.util.Date endDateTime) {
		_remoteForm.setEndDateTime(endDateTime);
	}

	/**
	* Returns the remark of this remote form.
	*
	* @return the remark of this remote form
	*/
	@Override
	public java.lang.String getRemark() {
		return _remoteForm.getRemark();
	}

	/**
	* Sets the remark of this remote form.
	*
	* @param remark the remark of this remote form
	*/
	@Override
	public void setRemark(java.lang.String remark) {
		_remoteForm.setRemark(remark);
	}

	/**
	* Returns the ext payload of this remote form.
	*
	* @return the ext payload of this remote form
	*/
	@Override
	public java.lang.String getExtPayload() {
		return _remoteForm.getExtPayload();
	}

	/**
	* Sets the ext payload of this remote form.
	*
	* @param extPayload the ext payload of this remote form
	*/
	@Override
	public void setExtPayload(java.lang.String extPayload) {
		_remoteForm.setExtPayload(extPayload);
	}

	/**
	* Returns the form title of this remote form.
	*
	* @return the form title of this remote form
	*/
	@Override
	public java.lang.String getFormTitle() {
		return _remoteForm.getFormTitle();
	}

	/**
	* Sets the form title of this remote form.
	*
	* @param formTitle the form title of this remote form
	*/
	@Override
	public void setFormTitle(java.lang.String formTitle) {
		_remoteForm.setFormTitle(formTitle);
	}

	/**
	* Returns the day tab of this remote form.
	*
	* @return the day tab of this remote form
	*/
	@Override
	public java.lang.String getDayTab() {
		return _remoteForm.getDayTab();
	}

	/**
	* Sets the day tab of this remote form.
	*
	* @param dayTab the day tab of this remote form
	*/
	@Override
	public void setDayTab(java.lang.String dayTab) {
		_remoteForm.setDayTab(dayTab);
	}

	/**
	* Returns the calendar booking ID of this remote form.
	*
	* @return the calendar booking ID of this remote form
	*/
	@Override
	public long getCalendarBookingId() {
		return _remoteForm.getCalendarBookingId();
	}

	/**
	* Sets the calendar booking ID of this remote form.
	*
	* @param calendarBookingId the calendar booking ID of this remote form
	*/
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_remoteForm.setCalendarBookingId(calendarBookingId);
	}

	/**
	* Returns the status of this remote form.
	*
	* @return the status of this remote form
	*/
	@Override
	public int getStatus() {
		return _remoteForm.getStatus();
	}

	/**
	* Sets the status of this remote form.
	*
	* @param status the status of this remote form
	*/
	@Override
	public void setStatus(int status) {
		_remoteForm.setStatus(status);
	}

	/**
	* Returns the status by user ID of this remote form.
	*
	* @return the status by user ID of this remote form
	*/
	@Override
	public long getStatusByUserId() {
		return _remoteForm.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this remote form.
	*
	* @param statusByUserId the status by user ID of this remote form
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_remoteForm.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this remote form.
	*
	* @return the status by user uuid of this remote form
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteForm.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this remote form.
	*
	* @param statusByUserUuid the status by user uuid of this remote form
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_remoteForm.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by username of this remote form.
	*
	* @return the status by username of this remote form
	*/
	@Override
	public java.lang.String getStatusByUsername() {
		return _remoteForm.getStatusByUsername();
	}

	/**
	* Sets the status by username of this remote form.
	*
	* @param statusByUsername the status by username of this remote form
	*/
	@Override
	public void setStatusByUsername(java.lang.String statusByUsername) {
		_remoteForm.setStatusByUsername(statusByUsername);
	}

	/**
	* Returns the status date of this remote form.
	*
	* @return the status date of this remote form
	*/
	@Override
	public java.util.Date getStatusDate() {
		return _remoteForm.getStatusDate();
	}

	/**
	* Sets the status date of this remote form.
	*
	* @param statusDate the status date of this remote form
	*/
	@Override
	public void setStatusDate(java.util.Date statusDate) {
		_remoteForm.setStatusDate(statusDate);
	}

	/**
	* Returns the company ID of this remote form.
	*
	* @return the company ID of this remote form
	*/
	@Override
	public long getCompanyId() {
		return _remoteForm.getCompanyId();
	}

	/**
	* Sets the company ID of this remote form.
	*
	* @param companyId the company ID of this remote form
	*/
	@Override
	public void setCompanyId(long companyId) {
		_remoteForm.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this remote form.
	*
	* @return the group ID of this remote form
	*/
	@Override
	public long getGroupId() {
		return _remoteForm.getGroupId();
	}

	/**
	* Sets the group ID of this remote form.
	*
	* @param groupId the group ID of this remote form
	*/
	@Override
	public void setGroupId(long groupId) {
		_remoteForm.setGroupId(groupId);
	}

	/**
	* Returns the cuser ID of this remote form.
	*
	* @return the cuser ID of this remote form
	*/
	@Override
	public long getCuserId() {
		return _remoteForm.getCuserId();
	}

	/**
	* Sets the cuser ID of this remote form.
	*
	* @param cuserId the cuser ID of this remote form
	*/
	@Override
	public void setCuserId(long cuserId) {
		_remoteForm.setCuserId(cuserId);
	}

	/**
	* Returns the muser ID of this remote form.
	*
	* @return the muser ID of this remote form
	*/
	@Override
	public long getMuserId() {
		return _remoteForm.getMuserId();
	}

	/**
	* Sets the muser ID of this remote form.
	*
	* @param muserId the muser ID of this remote form
	*/
	@Override
	public void setMuserId(long muserId) {
		_remoteForm.setMuserId(muserId);
	}

	/**
	* Returns the create date of this remote form.
	*
	* @return the create date of this remote form
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _remoteForm.getCreateDate();
	}

	/**
	* Sets the create date of this remote form.
	*
	* @param createDate the create date of this remote form
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_remoteForm.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this remote form.
	*
	* @return the modified date of this remote form
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _remoteForm.getModifiedDate();
	}

	/**
	* Sets the modified date of this remote form.
	*
	* @param modifiedDate the modified date of this remote form
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_remoteForm.setModifiedDate(modifiedDate);
	}

	@Override
	public boolean isNew() {
		return _remoteForm.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_remoteForm.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _remoteForm.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_remoteForm.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _remoteForm.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _remoteForm.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_remoteForm.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _remoteForm.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_remoteForm.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_remoteForm.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_remoteForm.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new RemoteFormWrapper((RemoteForm)_remoteForm.clone());
	}

	@Override
	public int compareTo(com.remote.application.model.RemoteForm remoteForm) {
		return _remoteForm.compareTo(remoteForm);
	}

	@Override
	public int hashCode() {
		return _remoteForm.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.remote.application.model.RemoteForm> toCacheModel() {
		return _remoteForm.toCacheModel();
	}

	@Override
	public com.remote.application.model.RemoteForm toEscapedModel() {
		return new RemoteFormWrapper(_remoteForm.toEscapedModel());
	}

	@Override
	public com.remote.application.model.RemoteForm toUnescapedModel() {
		return new RemoteFormWrapper(_remoteForm.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _remoteForm.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _remoteForm.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_remoteForm.persist();
	}

	@Override
	public java.lang.String getExtraDataJSON() {
		return _remoteForm.getExtraDataJSON();
	}

	@Override
	public java.lang.String getDisplayRmoteDate() {
		return _remoteForm.getDisplayRmoteDate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RemoteFormWrapper)) {
			return false;
		}

		RemoteFormWrapper remoteFormWrapper = (RemoteFormWrapper)obj;

		if (Validator.equals(_remoteForm, remoteFormWrapper._remoteForm)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public RemoteForm getWrappedRemoteForm() {
		return _remoteForm;
	}

	@Override
	public RemoteForm getWrappedModel() {
		return _remoteForm;
	}

	@Override
	public void resetOriginalValues() {
		_remoteForm.resetOriginalValues();
	}

	private RemoteForm _remoteForm;
}