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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.remote.application.model.RemoteForm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RemoteForm in entity cache.
 *
 * @author JuYin
 * @see RemoteForm
 * @generated
 */
public class RemoteFormCacheModel implements CacheModel<RemoteForm>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{remoteFormId=");
		sb.append(remoteFormId);
		sb.append(", creator=");
		sb.append(creator);
		sb.append(", applicant=");
		sb.append(applicant);
		sb.append(", remoteDate=");
		sb.append(remoteDate);
		sb.append(", endDateTime=");
		sb.append(endDateTime);
		sb.append(", remark=");
		sb.append(remark);
		sb.append(", extPayload=");
		sb.append(extPayload);
		sb.append(", formTitle=");
		sb.append(formTitle);
		sb.append(", dayTab=");
		sb.append(dayTab);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUsername=");
		sb.append(statusByUsername);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", cuserId=");
		sb.append(cuserId);
		sb.append(", muserId=");
		sb.append(muserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RemoteForm toEntityModel() {
		RemoteFormImpl remoteFormImpl = new RemoteFormImpl();

		remoteFormImpl.setRemoteFormId(remoteFormId);

		if (creator == null) {
			remoteFormImpl.setCreator(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setCreator(creator);
		}

		if (applicant == null) {
			remoteFormImpl.setApplicant(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setApplicant(applicant);
		}

		if (remoteDate == Long.MIN_VALUE) {
			remoteFormImpl.setRemoteDate(null);
		}
		else {
			remoteFormImpl.setRemoteDate(new Date(remoteDate));
		}

		if (endDateTime == Long.MIN_VALUE) {
			remoteFormImpl.setEndDateTime(null);
		}
		else {
			remoteFormImpl.setEndDateTime(new Date(endDateTime));
		}

		if (remark == null) {
			remoteFormImpl.setRemark(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setRemark(remark);
		}

		if (extPayload == null) {
			remoteFormImpl.setExtPayload(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setExtPayload(extPayload);
		}

		if (formTitle == null) {
			remoteFormImpl.setFormTitle(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setFormTitle(formTitle);
		}

		if (dayTab == null) {
			remoteFormImpl.setDayTab(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setDayTab(dayTab);
		}

		remoteFormImpl.setCalendarBookingId(calendarBookingId);
		remoteFormImpl.setStatus(status);
		remoteFormImpl.setStatusByUserId(statusByUserId);

		if (statusByUsername == null) {
			remoteFormImpl.setStatusByUsername(StringPool.BLANK);
		}
		else {
			remoteFormImpl.setStatusByUsername(statusByUsername);
		}

		if (statusDate == Long.MIN_VALUE) {
			remoteFormImpl.setStatusDate(null);
		}
		else {
			remoteFormImpl.setStatusDate(new Date(statusDate));
		}

		remoteFormImpl.setCompanyId(companyId);
		remoteFormImpl.setGroupId(groupId);
		remoteFormImpl.setCuserId(cuserId);
		remoteFormImpl.setMuserId(muserId);

		if (createDate == Long.MIN_VALUE) {
			remoteFormImpl.setCreateDate(null);
		}
		else {
			remoteFormImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			remoteFormImpl.setModifiedDate(null);
		}
		else {
			remoteFormImpl.setModifiedDate(new Date(modifiedDate));
		}

		remoteFormImpl.resetOriginalValues();

		return remoteFormImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		remoteFormId = objectInput.readLong();
		creator = objectInput.readUTF();
		applicant = objectInput.readUTF();
		remoteDate = objectInput.readLong();
		endDateTime = objectInput.readLong();
		remark = objectInput.readUTF();
		extPayload = objectInput.readUTF();
		formTitle = objectInput.readUTF();
		dayTab = objectInput.readUTF();
		calendarBookingId = objectInput.readLong();
		status = objectInput.readInt();
		statusByUserId = objectInput.readLong();
		statusByUsername = objectInput.readUTF();
		statusDate = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		cuserId = objectInput.readLong();
		muserId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(remoteFormId);

		if (creator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(creator);
		}

		if (applicant == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(applicant);
		}

		objectOutput.writeLong(remoteDate);
		objectOutput.writeLong(endDateTime);

		if (remark == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(remark);
		}

		if (extPayload == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(extPayload);
		}

		if (formTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formTitle);
		}

		if (dayTab == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dayTab);
		}

		objectOutput.writeLong(calendarBookingId);
		objectOutput.writeInt(status);
		objectOutput.writeLong(statusByUserId);

		if (statusByUsername == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUsername);
		}

		objectOutput.writeLong(statusDate);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(cuserId);
		objectOutput.writeLong(muserId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public long remoteFormId;
	public String creator;
	public String applicant;
	public long remoteDate;
	public long endDateTime;
	public String remark;
	public String extPayload;
	public String formTitle;
	public String dayTab;
	public long calendarBookingId;
	public int status;
	public long statusByUserId;
	public String statusByUsername;
	public long statusDate;
	public long companyId;
	public long groupId;
	public long cuserId;
	public long muserId;
	public long createDate;
	public long modifiedDate;
}