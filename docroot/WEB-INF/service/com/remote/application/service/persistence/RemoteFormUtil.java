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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.remote.application.model.RemoteForm;

import java.util.List;

/**
 * The persistence utility for the remote form service. This utility wraps {@link RemoteFormPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JuYin
 * @see RemoteFormPersistence
 * @see RemoteFormPersistenceImpl
 * @generated
 */
public class RemoteFormUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(RemoteForm remoteForm) {
		getPersistence().clearCache(remoteForm);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RemoteForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RemoteForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RemoteForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static RemoteForm update(RemoteForm remoteForm)
		throws SystemException {
		return getPersistence().update(remoteForm);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static RemoteForm update(RemoteForm remoteForm,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(remoteForm, serviceContext);
	}

	/**
	* Returns all the remote forms where creator = &#63;.
	*
	* @param creator the creator
	* @return the matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByCreator(
		java.lang.String creator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreator(creator);
	}

	/**
	* Returns a range of all the remote forms where creator = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creator the creator
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @return the range of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByCreator(
		java.lang.String creator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreator(creator, start, end);
	}

	/**
	* Returns an ordered range of all the remote forms where creator = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creator the creator
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByCreator(
		java.lang.String creator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreator(creator, start, end, orderByComparator);
	}

	/**
	* Returns the first remote form in the ordered set where creator = &#63;.
	*
	* @param creator the creator
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm findByCreator_First(
		java.lang.String creator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().findByCreator_First(creator, orderByComparator);
	}

	/**
	* Returns the first remote form in the ordered set where creator = &#63;.
	*
	* @param creator the creator
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching remote form, or <code>null</code> if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm fetchByCreator_First(
		java.lang.String creator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCreator_First(creator, orderByComparator);
	}

	/**
	* Returns the last remote form in the ordered set where creator = &#63;.
	*
	* @param creator the creator
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm findByCreator_Last(
		java.lang.String creator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().findByCreator_Last(creator, orderByComparator);
	}

	/**
	* Returns the last remote form in the ordered set where creator = &#63;.
	*
	* @param creator the creator
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching remote form, or <code>null</code> if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm fetchByCreator_Last(
		java.lang.String creator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCreator_Last(creator, orderByComparator);
	}

	/**
	* Returns the remote forms before and after the current remote form in the ordered set where creator = &#63;.
	*
	* @param remoteFormId the primary key of the current remote form
	* @param creator the creator
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm[] findByCreator_PrevAndNext(
		long remoteFormId, java.lang.String creator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence()
				   .findByCreator_PrevAndNext(remoteFormId, creator,
			orderByComparator);
	}

	/**
	* Removes all the remote forms where creator = &#63; from the database.
	*
	* @param creator the creator
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCreator(java.lang.String creator)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCreator(creator);
	}

	/**
	* Returns the number of remote forms where creator = &#63;.
	*
	* @param creator the creator
	* @return the number of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCreator(java.lang.String creator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCreator(creator);
	}

	/**
	* Returns all the remote forms where status = &#63;.
	*
	* @param status the status
	* @return the matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the remote forms where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @return the range of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the remote forms where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns the first remote form in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm findByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first remote form in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching remote form, or <code>null</code> if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last remote form in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm findByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last remote form in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching remote form, or <code>null</code> if a matching remote form could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm fetchByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the remote forms before and after the current remote form in the ordered set where status = &#63;.
	*
	* @param remoteFormId the primary key of the current remote form
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm[] findByStatus_PrevAndNext(
		long remoteFormId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence()
				   .findByStatus_PrevAndNext(remoteFormId, status,
			orderByComparator);
	}

	/**
	* Removes all the remote forms where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of remote forms where status = &#63;.
	*
	* @param status the status
	* @return the number of matching remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(status);
	}

	/**
	* Caches the remote form in the entity cache if it is enabled.
	*
	* @param remoteForm the remote form
	*/
	public static void cacheResult(
		com.remote.application.model.RemoteForm remoteForm) {
		getPersistence().cacheResult(remoteForm);
	}

	/**
	* Caches the remote forms in the entity cache if it is enabled.
	*
	* @param remoteForms the remote forms
	*/
	public static void cacheResult(
		java.util.List<com.remote.application.model.RemoteForm> remoteForms) {
		getPersistence().cacheResult(remoteForms);
	}

	/**
	* Creates a new remote form with the primary key. Does not add the remote form to the database.
	*
	* @param remoteFormId the primary key for the new remote form
	* @return the new remote form
	*/
	public static com.remote.application.model.RemoteForm create(
		long remoteFormId) {
		return getPersistence().create(remoteFormId);
	}

	/**
	* Removes the remote form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param remoteFormId the primary key of the remote form
	* @return the remote form that was removed
	* @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm remove(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().remove(remoteFormId);
	}

	public static com.remote.application.model.RemoteForm updateImpl(
		com.remote.application.model.RemoteForm remoteForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(remoteForm);
	}

	/**
	* Returns the remote form with the primary key or throws a {@link com.remote.application.NoSuchRemoteFormException} if it could not be found.
	*
	* @param remoteFormId the primary key of the remote form
	* @return the remote form
	* @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm findByPrimaryKey(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.remote.application.NoSuchRemoteFormException {
		return getPersistence().findByPrimaryKey(remoteFormId);
	}

	/**
	* Returns the remote form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param remoteFormId the primary key of the remote form
	* @return the remote form, or <code>null</code> if a remote form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.remote.application.model.RemoteForm fetchByPrimaryKey(
		long remoteFormId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(remoteFormId);
	}

	/**
	* Returns all the remote forms.
	*
	* @return the remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.remote.application.model.RemoteForm> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the remote forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.remote.application.model.impl.RemoteFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of remote forms
	* @param end the upper bound of the range of remote forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.remote.application.model.RemoteForm> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the remote forms from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of remote forms.
	*
	* @return the number of remote forms
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static RemoteFormPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RemoteFormPersistence)PortletBeanLocatorUtil.locate(com.remote.application.service.ClpSerializer.getServletContextName(),
					RemoteFormPersistence.class.getName());

			ReferenceRegistry.registerReference(RemoteFormUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(RemoteFormPersistence persistence) {
	}

	private static RemoteFormPersistence _persistence;
}