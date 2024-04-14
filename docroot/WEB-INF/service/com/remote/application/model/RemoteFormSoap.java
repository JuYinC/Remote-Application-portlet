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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author JuYin
 * @generated
 */
public class RemoteFormSoap implements Serializable {
	public static RemoteFormSoap toSoapModel(RemoteForm model) {
		RemoteFormSoap soapModel = new RemoteFormSoap();

		soapModel.setRemoteFormId(model.getRemoteFormId());
		soapModel.setCreator(model.getCreator());
		soapModel.setApplicant(model.getApplicant());
		soapModel.setRemoteDate(model.getRemoteDate());
		soapModel.setEndDateTime(model.getEndDateTime());
		soapModel.setRemark(model.getRemark());
		soapModel.setExtPayload(model.getExtPayload());
		soapModel.setFormTitle(model.getFormTitle());
		soapModel.setDayTab(model.getDayTab());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUsername(model.getStatusByUsername());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCuserId(model.getCuserId());
		soapModel.setMuserId(model.getMuserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static RemoteFormSoap[] toSoapModels(RemoteForm[] models) {
		RemoteFormSoap[] soapModels = new RemoteFormSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RemoteFormSoap[][] toSoapModels(RemoteForm[][] models) {
		RemoteFormSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RemoteFormSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RemoteFormSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RemoteFormSoap[] toSoapModels(List<RemoteForm> models) {
		List<RemoteFormSoap> soapModels = new ArrayList<RemoteFormSoap>(models.size());

		for (RemoteForm model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RemoteFormSoap[soapModels.size()]);
	}

	public RemoteFormSoap() {
	}

	public long getPrimaryKey() {
		return _remoteFormId;
	}

	public void setPrimaryKey(long pk) {
		setRemoteFormId(pk);
	}

	public long getRemoteFormId() {
		return _remoteFormId;
	}

	public void setRemoteFormId(long remoteFormId) {
		_remoteFormId = remoteFormId;
	}

	public String getCreator() {
		return _creator;
	}

	public void setCreator(String creator) {
		_creator = creator;
	}

	public String getApplicant() {
		return _applicant;
	}

	public void setApplicant(String applicant) {
		_applicant = applicant;
	}

	public Date getRemoteDate() {
		return _remoteDate;
	}

	public void setRemoteDate(Date remoteDate) {
		_remoteDate = remoteDate;
	}

	public Date getEndDateTime() {
		return _endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		_endDateTime = endDateTime;
	}

	public String getRemark() {
		return _remark;
	}

	public void setRemark(String remark) {
		_remark = remark;
	}

	public String getExtPayload() {
		return _extPayload;
	}

	public void setExtPayload(String extPayload) {
		_extPayload = extPayload;
	}

	public String getFormTitle() {
		return _formTitle;
	}

	public void setFormTitle(String formTitle) {
		_formTitle = formTitle;
	}

	public String getDayTab() {
		return _dayTab;
	}

	public void setDayTab(String dayTab) {
		_dayTab = dayTab;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUsername() {
		return _statusByUsername;
	}

	public void setStatusByUsername(String statusByUsername) {
		_statusByUsername = statusByUsername;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCuserId() {
		return _cuserId;
	}

	public void setCuserId(long cuserId) {
		_cuserId = cuserId;
	}

	public long getMuserId() {
		return _muserId;
	}

	public void setMuserId(long muserId) {
		_muserId = muserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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
	private String _statusByUsername;
	private Date _statusDate;
	private long _companyId;
	private long _groupId;
	private long _cuserId;
	private long _muserId;
	private Date _createDate;
	private Date _modifiedDate;
}