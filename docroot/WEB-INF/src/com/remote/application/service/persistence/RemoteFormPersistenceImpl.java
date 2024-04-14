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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.remote.application.NoSuchRemoteFormException;
import com.remote.application.model.RemoteForm;
import com.remote.application.model.impl.RemoteFormImpl;
import com.remote.application.model.impl.RemoteFormModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the remote form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JuYin
 * @see RemoteFormPersistence
 * @see RemoteFormUtil
 * @generated
 */
public class RemoteFormPersistenceImpl extends BasePersistenceImpl<RemoteForm>
	implements RemoteFormPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RemoteFormUtil} to access the remote form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RemoteFormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATOR = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreator",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATOR =
		new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreator",
			new String[] { String.class.getName() },
			RemoteFormModelImpl.CREATOR_COLUMN_BITMASK |
			RemoteFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CREATOR = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreator",
			new String[] { String.class.getName() });

	/**
	 * Returns all the remote forms where creator = &#63;.
	 *
	 * @param creator the creator
	 * @return the matching remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RemoteForm> findByCreator(String creator)
		throws SystemException {
		return findByCreator(creator, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<RemoteForm> findByCreator(String creator, int start, int end)
		throws SystemException {
		return findByCreator(creator, start, end, null);
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
	@Override
	public List<RemoteForm> findByCreator(String creator, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATOR;
			finderArgs = new Object[] { creator };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATOR;
			finderArgs = new Object[] { creator, start, end, orderByComparator };
		}

		List<RemoteForm> list = (List<RemoteForm>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RemoteForm remoteForm : list) {
				if (!Validator.equals(creator, remoteForm.getCreator())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REMOTEFORM_WHERE);

			boolean bindCreator = false;

			if (creator == null) {
				query.append(_FINDER_COLUMN_CREATOR_CREATOR_1);
			}
			else if (creator.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CREATOR_CREATOR_3);
			}
			else {
				bindCreator = true;

				query.append(_FINDER_COLUMN_CREATOR_CREATOR_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RemoteFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreator) {
					qPos.add(creator);
				}

				if (!pagination) {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RemoteForm>(list);
				}
				else {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public RemoteForm findByCreator_First(String creator,
		OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = fetchByCreator_First(creator, orderByComparator);

		if (remoteForm != null) {
			return remoteForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("creator=");
		msg.append(creator);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRemoteFormException(msg.toString());
	}

	/**
	 * Returns the first remote form in the ordered set where creator = &#63;.
	 *
	 * @param creator the creator
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote form, or <code>null</code> if a matching remote form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByCreator_First(String creator,
		OrderByComparator orderByComparator) throws SystemException {
		List<RemoteForm> list = findByCreator(creator, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public RemoteForm findByCreator_Last(String creator,
		OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = fetchByCreator_Last(creator, orderByComparator);

		if (remoteForm != null) {
			return remoteForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("creator=");
		msg.append(creator);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRemoteFormException(msg.toString());
	}

	/**
	 * Returns the last remote form in the ordered set where creator = &#63;.
	 *
	 * @param creator the creator
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote form, or <code>null</code> if a matching remote form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByCreator_Last(String creator,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCreator(creator);

		if (count == 0) {
			return null;
		}

		List<RemoteForm> list = findByCreator(creator, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public RemoteForm[] findByCreator_PrevAndNext(long remoteFormId,
		String creator, OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = findByPrimaryKey(remoteFormId);

		Session session = null;

		try {
			session = openSession();

			RemoteForm[] array = new RemoteFormImpl[3];

			array[0] = getByCreator_PrevAndNext(session, remoteForm, creator,
					orderByComparator, true);

			array[1] = remoteForm;

			array[2] = getByCreator_PrevAndNext(session, remoteForm, creator,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RemoteForm getByCreator_PrevAndNext(Session session,
		RemoteForm remoteForm, String creator,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REMOTEFORM_WHERE);

		boolean bindCreator = false;

		if (creator == null) {
			query.append(_FINDER_COLUMN_CREATOR_CREATOR_1);
		}
		else if (creator.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CREATOR_CREATOR_3);
		}
		else {
			bindCreator = true;

			query.append(_FINDER_COLUMN_CREATOR_CREATOR_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RemoteFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCreator) {
			qPos.add(creator);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(remoteForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RemoteForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the remote forms where creator = &#63; from the database.
	 *
	 * @param creator the creator
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCreator(String creator) throws SystemException {
		for (RemoteForm remoteForm : findByCreator(creator, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(remoteForm);
		}
	}

	/**
	 * Returns the number of remote forms where creator = &#63;.
	 *
	 * @param creator the creator
	 * @return the number of matching remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCreator(String creator) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CREATOR;

		Object[] finderArgs = new Object[] { creator };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REMOTEFORM_WHERE);

			boolean bindCreator = false;

			if (creator == null) {
				query.append(_FINDER_COLUMN_CREATOR_CREATOR_1);
			}
			else if (creator.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CREATOR_CREATOR_3);
			}
			else {
				bindCreator = true;

				query.append(_FINDER_COLUMN_CREATOR_CREATOR_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCreator) {
					qPos.add(creator);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CREATOR_CREATOR_1 = "remoteForm.creator IS NULL";
	private static final String _FINDER_COLUMN_CREATOR_CREATOR_2 = "remoteForm.creator = ?";
	private static final String _FINDER_COLUMN_CREATOR_CREATOR_3 = "(remoteForm.creator IS NULL OR remoteForm.creator = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, RemoteFormImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			RemoteFormModelImpl.STATUS_COLUMN_BITMASK |
			RemoteFormModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the remote forms where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RemoteForm> findByStatus(int status) throws SystemException {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<RemoteForm> findByStatus(int status, int start, int end)
		throws SystemException {
		return findByStatus(status, start, end, null);
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
	@Override
	public List<RemoteForm> findByStatus(int status, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<RemoteForm> list = (List<RemoteForm>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RemoteForm remoteForm : list) {
				if ((status != remoteForm.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_REMOTEFORM_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RemoteFormModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RemoteForm>(list);
				}
				else {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public RemoteForm findByStatus_First(int status,
		OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = fetchByStatus_First(status, orderByComparator);

		if (remoteForm != null) {
			return remoteForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRemoteFormException(msg.toString());
	}

	/**
	 * Returns the first remote form in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remote form, or <code>null</code> if a matching remote form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByStatus_First(int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<RemoteForm> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public RemoteForm findByStatus_Last(int status,
		OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = fetchByStatus_Last(status, orderByComparator);

		if (remoteForm != null) {
			return remoteForm;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRemoteFormException(msg.toString());
	}

	/**
	 * Returns the last remote form in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remote form, or <code>null</code> if a matching remote form could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByStatus_Last(int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<RemoteForm> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public RemoteForm[] findByStatus_PrevAndNext(long remoteFormId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = findByPrimaryKey(remoteFormId);

		Session session = null;

		try {
			session = openSession();

			RemoteForm[] array = new RemoteFormImpl[3];

			array[0] = getByStatus_PrevAndNext(session, remoteForm, status,
					orderByComparator, true);

			array[1] = remoteForm;

			array[2] = getByStatus_PrevAndNext(session, remoteForm, status,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RemoteForm getByStatus_PrevAndNext(Session session,
		RemoteForm remoteForm, int status, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_REMOTEFORM_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RemoteFormModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(remoteForm);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RemoteForm> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the remote forms where status = &#63; from the database.
	 *
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByStatus(int status) throws SystemException {
		for (RemoteForm remoteForm : findByStatus(status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(remoteForm);
		}
	}

	/**
	 * Returns the number of remote forms where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStatus(int status) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_REMOTEFORM_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "remoteForm.status = ?";

	public RemoteFormPersistenceImpl() {
		setModelClass(RemoteForm.class);
	}

	/**
	 * Caches the remote form in the entity cache if it is enabled.
	 *
	 * @param remoteForm the remote form
	 */
	@Override
	public void cacheResult(RemoteForm remoteForm) {
		EntityCacheUtil.putResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormImpl.class, remoteForm.getPrimaryKey(), remoteForm);

		remoteForm.resetOriginalValues();
	}

	/**
	 * Caches the remote forms in the entity cache if it is enabled.
	 *
	 * @param remoteForms the remote forms
	 */
	@Override
	public void cacheResult(List<RemoteForm> remoteForms) {
		for (RemoteForm remoteForm : remoteForms) {
			if (EntityCacheUtil.getResult(
						RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
						RemoteFormImpl.class, remoteForm.getPrimaryKey()) == null) {
				cacheResult(remoteForm);
			}
			else {
				remoteForm.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all remote forms.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RemoteFormImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RemoteFormImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the remote form.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RemoteForm remoteForm) {
		EntityCacheUtil.removeResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormImpl.class, remoteForm.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RemoteForm> remoteForms) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RemoteForm remoteForm : remoteForms) {
			EntityCacheUtil.removeResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
				RemoteFormImpl.class, remoteForm.getPrimaryKey());
		}
	}

	/**
	 * Creates a new remote form with the primary key. Does not add the remote form to the database.
	 *
	 * @param remoteFormId the primary key for the new remote form
	 * @return the new remote form
	 */
	@Override
	public RemoteForm create(long remoteFormId) {
		RemoteForm remoteForm = new RemoteFormImpl();

		remoteForm.setNew(true);
		remoteForm.setPrimaryKey(remoteFormId);

		return remoteForm;
	}

	/**
	 * Removes the remote form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param remoteFormId the primary key of the remote form
	 * @return the remote form that was removed
	 * @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm remove(long remoteFormId)
		throws NoSuchRemoteFormException, SystemException {
		return remove((Serializable)remoteFormId);
	}

	/**
	 * Removes the remote form with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the remote form
	 * @return the remote form that was removed
	 * @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm remove(Serializable primaryKey)
		throws NoSuchRemoteFormException, SystemException {
		Session session = null;

		try {
			session = openSession();

			RemoteForm remoteForm = (RemoteForm)session.get(RemoteFormImpl.class,
					primaryKey);

			if (remoteForm == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRemoteFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(remoteForm);
		}
		catch (NoSuchRemoteFormException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected RemoteForm removeImpl(RemoteForm remoteForm)
		throws SystemException {
		remoteForm = toUnwrappedModel(remoteForm);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(remoteForm)) {
				remoteForm = (RemoteForm)session.get(RemoteFormImpl.class,
						remoteForm.getPrimaryKeyObj());
			}

			if (remoteForm != null) {
				session.delete(remoteForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (remoteForm != null) {
			clearCache(remoteForm);
		}

		return remoteForm;
	}

	@Override
	public RemoteForm updateImpl(
		com.remote.application.model.RemoteForm remoteForm)
		throws SystemException {
		remoteForm = toUnwrappedModel(remoteForm);

		boolean isNew = remoteForm.isNew();

		RemoteFormModelImpl remoteFormModelImpl = (RemoteFormModelImpl)remoteForm;

		Session session = null;

		try {
			session = openSession();

			if (remoteForm.isNew()) {
				session.save(remoteForm);

				remoteForm.setNew(false);
			}
			else {
				session.merge(remoteForm);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RemoteFormModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((remoteFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATOR.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						remoteFormModelImpl.getOriginalCreator()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATOR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATOR,
					args);

				args = new Object[] { remoteFormModelImpl.getCreator() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATOR, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATOR,
					args);
			}

			if ((remoteFormModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						remoteFormModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { remoteFormModelImpl.getStatus() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}
		}

		EntityCacheUtil.putResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
			RemoteFormImpl.class, remoteForm.getPrimaryKey(), remoteForm);

		return remoteForm;
	}

	protected RemoteForm toUnwrappedModel(RemoteForm remoteForm) {
		if (remoteForm instanceof RemoteFormImpl) {
			return remoteForm;
		}

		RemoteFormImpl remoteFormImpl = new RemoteFormImpl();

		remoteFormImpl.setNew(remoteForm.isNew());
		remoteFormImpl.setPrimaryKey(remoteForm.getPrimaryKey());

		remoteFormImpl.setRemoteFormId(remoteForm.getRemoteFormId());
		remoteFormImpl.setCreator(remoteForm.getCreator());
		remoteFormImpl.setApplicant(remoteForm.getApplicant());
		remoteFormImpl.setRemoteDate(remoteForm.getRemoteDate());
		remoteFormImpl.setEndDateTime(remoteForm.getEndDateTime());
		remoteFormImpl.setRemark(remoteForm.getRemark());
		remoteFormImpl.setExtPayload(remoteForm.getExtPayload());
		remoteFormImpl.setFormTitle(remoteForm.getFormTitle());
		remoteFormImpl.setDayTab(remoteForm.getDayTab());
		remoteFormImpl.setCalendarBookingId(remoteForm.getCalendarBookingId());
		remoteFormImpl.setStatus(remoteForm.getStatus());
		remoteFormImpl.setStatusByUserId(remoteForm.getStatusByUserId());
		remoteFormImpl.setStatusByUsername(remoteForm.getStatusByUsername());
		remoteFormImpl.setStatusDate(remoteForm.getStatusDate());
		remoteFormImpl.setCompanyId(remoteForm.getCompanyId());
		remoteFormImpl.setGroupId(remoteForm.getGroupId());
		remoteFormImpl.setCuserId(remoteForm.getCuserId());
		remoteFormImpl.setMuserId(remoteForm.getMuserId());
		remoteFormImpl.setCreateDate(remoteForm.getCreateDate());
		remoteFormImpl.setModifiedDate(remoteForm.getModifiedDate());

		return remoteFormImpl;
	}

	/**
	 * Returns the remote form with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the remote form
	 * @return the remote form
	 * @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRemoteFormException, SystemException {
		RemoteForm remoteForm = fetchByPrimaryKey(primaryKey);

		if (remoteForm == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRemoteFormException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return remoteForm;
	}

	/**
	 * Returns the remote form with the primary key or throws a {@link com.remote.application.NoSuchRemoteFormException} if it could not be found.
	 *
	 * @param remoteFormId the primary key of the remote form
	 * @return the remote form
	 * @throws com.remote.application.NoSuchRemoteFormException if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm findByPrimaryKey(long remoteFormId)
		throws NoSuchRemoteFormException, SystemException {
		return findByPrimaryKey((Serializable)remoteFormId);
	}

	/**
	 * Returns the remote form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the remote form
	 * @return the remote form, or <code>null</code> if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		RemoteForm remoteForm = (RemoteForm)EntityCacheUtil.getResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
				RemoteFormImpl.class, primaryKey);

		if (remoteForm == _nullRemoteForm) {
			return null;
		}

		if (remoteForm == null) {
			Session session = null;

			try {
				session = openSession();

				remoteForm = (RemoteForm)session.get(RemoteFormImpl.class,
						primaryKey);

				if (remoteForm != null) {
					cacheResult(remoteForm);
				}
				else {
					EntityCacheUtil.putResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
						RemoteFormImpl.class, primaryKey, _nullRemoteForm);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(RemoteFormModelImpl.ENTITY_CACHE_ENABLED,
					RemoteFormImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return remoteForm;
	}

	/**
	 * Returns the remote form with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param remoteFormId the primary key of the remote form
	 * @return the remote form, or <code>null</code> if a remote form with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RemoteForm fetchByPrimaryKey(long remoteFormId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)remoteFormId);
	}

	/**
	 * Returns all the remote forms.
	 *
	 * @return the remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RemoteForm> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<RemoteForm> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<RemoteForm> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<RemoteForm> list = (List<RemoteForm>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_REMOTEFORM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REMOTEFORM;

				if (pagination) {
					sql = sql.concat(RemoteFormModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<RemoteForm>(list);
				}
				else {
					list = (List<RemoteForm>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the remote forms from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (RemoteForm remoteForm : findAll()) {
			remove(remoteForm);
		}
	}

	/**
	 * Returns the number of remote forms.
	 *
	 * @return the number of remote forms
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REMOTEFORM);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the remote form persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.remote.application.model.RemoteForm")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<RemoteForm>> listenersList = new ArrayList<ModelListener<RemoteForm>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<RemoteForm>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(RemoteFormImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_REMOTEFORM = "SELECT remoteForm FROM RemoteForm remoteForm";
	private static final String _SQL_SELECT_REMOTEFORM_WHERE = "SELECT remoteForm FROM RemoteForm remoteForm WHERE ";
	private static final String _SQL_COUNT_REMOTEFORM = "SELECT COUNT(remoteForm) FROM RemoteForm remoteForm";
	private static final String _SQL_COUNT_REMOTEFORM_WHERE = "SELECT COUNT(remoteForm) FROM RemoteForm remoteForm WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "remoteForm.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RemoteForm exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RemoteForm exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RemoteFormPersistenceImpl.class);
	private static RemoteForm _nullRemoteForm = new RemoteFormImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<RemoteForm> toCacheModel() {
				return _nullRemoteFormCacheModel;
			}
		};

	private static CacheModel<RemoteForm> _nullRemoteFormCacheModel = new CacheModel<RemoteForm>() {
			@Override
			public RemoteForm toEntityModel() {
				return _nullRemoteForm;
			}
		};
}