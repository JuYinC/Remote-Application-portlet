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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author JuYin
 */
public class RemoteFormFinderUtil {
	public static java.util.List<com.remote.application.model.RemoteForm> search(
		java.lang.String applicant, int status, java.util.Date startDateTime,
		java.util.Date endDateTime, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .search(applicant, status, startDateTime, endDateTime,
			keywords, start, end, obc);
	}

	public static int countBySearch(java.lang.String applicant, int status,
		java.util.Date startDateTime, java.util.Date endDateTime,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countBySearch(applicant, status, startDateTime,
			endDateTime, keywords);
	}

	public static java.util.List<java.lang.String> fetchCycles(
		java.lang.String applicant, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().fetchCycles(applicant, status);
	}

	public static java.util.List<com.remote.application.model.RemoteForm> searchForStatistics(
		java.lang.String applicant, int[] statuses,
		java.util.Date searchStartDateTime, java.util.Date searchEndDateTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .searchForStatistics(applicant, statuses,
			searchStartDateTime, searchEndDateTime);
	}

	public static java.util.List<com.remote.application.model.RemoteForm> checkOverlappingTimes(
		java.lang.String applicant, java.util.Date searchStartDateTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().checkOverlappingTimes(applicant, searchStartDateTime);
	}

	public static RemoteFormFinder getFinder() {
		if (_finder == null) {
			_finder = (RemoteFormFinder)PortletBeanLocatorUtil.locate(com.remote.application.service.ClpSerializer.getServletContextName(),
					RemoteFormFinder.class.getName());

			ReferenceRegistry.registerReference(RemoteFormFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(RemoteFormFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(RemoteFormFinderUtil.class,
			"_finder");
	}

	private static RemoteFormFinder _finder;
}