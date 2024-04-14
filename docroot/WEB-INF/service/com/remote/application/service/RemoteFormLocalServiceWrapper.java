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

package com.remote.application.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RemoteFormLocalService}.
 *
 * @author JuYin
 * @see RemoteFormLocalService
 * @generated
 */
public class RemoteFormLocalServiceWrapper implements RemoteFormLocalService,
	ServiceWrapper<RemoteFormLocalService> {
	public RemoteFormLocalServiceWrapper(
		RemoteFormLocalService remoteFormLocalService) {
		_remoteFormLocalService = remoteFormLocalService;
	}

	/**
	* Adds the remote form to the database. Also notifies the appropriate model listeners.
	*
	* @param remoteForm the remote form
	* @return the remote form that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.remote.application.model.RemoteForm addRemoteForm(
		com.remote.application.model.RemoteForm remoteForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.addRemoteForm(remoteForm);
	}

	/**
	* Creates a new remote form with the primary key. Does not add the remote form to the database.
	*
	* @param remoteFormId the primary key for the new remote form
	* @return the new remote form
	*/
	@Override
	public com.remote.application.model.RemoteForm createRemoteForm(
		long remoteFormId) {
		return _remoteFormLocalService.createRemoteForm(remoteFormId);
	}

	/**
	* Deletes the remote form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param remoteFormId the primary key of the remote form
	* @return the remote form that was removed
	* @throws PortalException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.remote.application.model.RemoteForm deleteRemoteForm(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.deleteRemoteForm(remoteFormId);
	}

	/**
	* Deletes the remote form from the database. Also notifies the appropriate model listeners.
	*
	* @param remoteForm the remote form
	* @return the remote form that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.remote.application.model.RemoteForm deleteRemoteForm(
		com.remote.application.model.RemoteForm remoteForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.deleteRemoteForm(remoteForm);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _remoteFormLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.remote.application.model.RemoteForm fetchRemoteForm(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.fetchRemoteForm(remoteFormId);
	}

	/**
	* Returns the remote form with the primary key.
	*
	* @param remoteFormId the primary key of the remote form
	* @return the remote form
	* @throws PortalException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.remote.application.model.RemoteForm getRemoteForm(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.getRemoteForm(remoteFormId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the remote forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @return the range of remote forms
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.remote.application.model.RemoteForm> getRemoteForms(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.getRemoteForms(start, end);
	}

	/**
	* Returns the number of remote forms.
	*
	* @return the number of remote forms
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getRemoteFormsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.getRemoteFormsCount();
	}

	/**
	* Updates the remote form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param remoteForm the remote form
	* @return the remote form that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.remote.application.model.RemoteForm updateRemoteForm(
		com.remote.application.model.RemoteForm remoteForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.updateRemoteForm(remoteForm);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _remoteFormLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_remoteFormLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _remoteFormLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.remote.application.model.RemoteForm> search(
		java.lang.String applicant, int status, java.util.Date startDateTime,
		java.util.Date endDateTime, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.search(applicant, status, startDateTime,
			endDateTime, keywords, start, end, obc);
	}

	@Override
	public int countBySearch(java.lang.String applicant, int status,
		java.util.Date startDateTime, java.util.Date endDateTime,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.countBySearch(applicant, status,
			startDateTime, endDateTime, keywords);
	}

	@Override
	public java.util.List<java.lang.String> fetchCycles(
		java.lang.String applicant, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.fetchCycles(applicant, status);
	}

	@Override
	public com.remote.application.model.RemoteForm upsertRemoteForm(
		long remoteFormId, java.lang.String creator,
		java.lang.String applicant, java.util.Date remoteDate,
		java.lang.String remark, java.lang.String extPayload,
		com.liferay.portal.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.upsertRemoteForm(remoteFormId, creator,
			applicant, remoteDate, remark, extPayload, context);
	}

	@Override
	public com.remote.application.model.RemoteForm upsertRemoteForm(
		long remoteFormId, java.lang.String creator,
		java.lang.String applicant, java.util.Date remoteDate,
		java.util.Date endDateTime, java.lang.String remark,
		java.lang.String extPayload, java.lang.String dayTab,
		com.liferay.portal.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.upsertRemoteForm(remoteFormId, creator,
			applicant, remoteDate, endDateTime, remark, extPayload, dayTab,
			context);
	}

	@Override
	public void removeRemoteForm(long remoteFormId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_remoteFormLocalService.removeRemoteForm(remoteFormId);
	}

	@Override
	public com.remote.application.model.RemoteForm updateStatus(long userId,
		long remoteFormId, int status,
		com.liferay.portal.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.updateStatus(userId, remoteFormId,
			status, context);
	}

	@Override
	public java.util.List<com.remote.application.model.RemoteForm> searchForStatistics(
		java.lang.String applicant, int[] statuses,
		java.util.Date startDateTime, java.util.Date endDateTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.searchForStatistics(applicant, statuses,
			startDateTime, endDateTime);
	}

	@Override
	public java.util.List<com.remote.application.model.RemoteForm> checkOverlappingTimes(
		java.lang.String applicant, java.util.Date startDateTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _remoteFormLocalService.checkOverlappingTimes(applicant,
			startDateTime);
	}

	@Override
	public java.lang.String getRemotePeriodDisplay(java.util.Date start,
		java.util.Date end, java.util.TimeZone timeZone) {
		return _remoteFormLocalService.getRemotePeriodDisplay(start, end,
			timeZone);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public RemoteFormLocalService getWrappedRemoteFormLocalService() {
		return _remoteFormLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedRemoteFormLocalService(
		RemoteFormLocalService remoteFormLocalService) {
		_remoteFormLocalService = remoteFormLocalService;
	}

	@Override
	public RemoteFormLocalService getWrappedService() {
		return _remoteFormLocalService;
	}

	@Override
	public void setWrappedService(RemoteFormLocalService remoteFormLocalService) {
		_remoteFormLocalService = remoteFormLocalService;
	}

	private RemoteFormLocalService _remoteFormLocalService;
}