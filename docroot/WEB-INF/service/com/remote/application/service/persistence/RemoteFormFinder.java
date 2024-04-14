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

package com.remote.application.service.persistence;

/**
 * @author JuYin
 */
public interface RemoteFormFinder {
	public java.util.List<com.remote.application.model.RemoteForm> search(
		java.lang.String applicant, int status, java.util.Date startDateTime,
		java.util.Date endDateTime, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countBySearch(java.lang.String applicant, int status,
		java.util.Date startDateTime, java.util.Date endDateTime,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.String> fetchCycles(
		java.lang.String applicant, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.remote.application.model.RemoteForm> searchForStatistics(
		java.lang.String applicant, int[] statuses,
		java.util.Date searchStartDateTime, java.util.Date searchEndDateTime)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.remote.application.model.RemoteForm> checkOverlappingTimes(
		java.lang.String applicant, java.util.Date searchStartDateTime)
		throws com.liferay.portal.kernel.exception.SystemException;
}