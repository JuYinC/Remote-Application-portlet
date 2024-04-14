package com.remote.application.service.persistence;

import java.util.Date;
import java.util.List;

import com.remote.application.model.RemoteForm;
import com.remote.application.model.impl.RemoteFormImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class RemoteFormFinderImpl extends BasePersistenceImpl<RemoteForm> implements RemoteFormFinder{
	
	public List<RemoteForm> search(String applicant, int status, Date startDateTime, Date endDateTime, String keywords, int start, int end, OrderByComparator obc) 
		throws SystemException {
		
		Session session = null;
		try {
			session = openSession();
			String sql =  CustomSQLUtil.get(RemoteFormFinder.class.getName() + ".search");
			if (Validator.isBlank(applicant)) {
				sql = StringUtil.replace(sql, "AND rrf.applicant = ?", StringPool.BLANK);
			}
			if (status == -1) {
				sql = StringUtil.replace(sql, "AND rrf.status = ?", StringPool.BLANK);
			}
			if (Validator.isNull(startDateTime)) {
				sql = StringUtil.replace(sql, "AND rrf.startDateTime >= ?", StringPool.BLANK);
			}
			if (Validator.isNull(endDateTime)) {
				sql = StringUtil.replace(sql, "AND rrf.startDateTime <= ?", StringPool.BLANK);
			}
			
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("rrf", RemoteFormImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (!Validator.isBlank(applicant)) {
				qPos.add(applicant);
			}
			if (status != -1) {
				qPos.add(status);
			}
			if (Validator.isNotNull(startDateTime)) {
				qPos.add(startDateTime);
			}
			if (Validator.isNotNull(endDateTime)) {
				qPos.add(endDateTime);
			}

			qPos.add("%" + keywords + "%");
			qPos.add(keywords);
			
			return (List<RemoteForm>) QueryUtil.list(q, getDialect(), start, end);
			
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
	
	public int countBySearch(String applicant, int status, Date startDateTime, Date endDateTime, String keywords)
			throws SystemException {
		
		Session session = null;
		try {
			session = openSession();
			String sql =  CustomSQLUtil.get(RemoteFormFinder.class.getName() + ".countBySearch");
			
			if (Validator.isBlank(applicant)) {
				sql = StringUtil.replace(sql, "AND rrf.applicant = ?", StringPool.BLANK);
			}
			if (status == -1) {
				sql = StringUtil.replace(sql, "AND rrf.status = ?", StringPool.BLANK);
			}
			if (Validator.isNull(startDateTime)) {
				sql = StringUtil.replace(sql, "AND rrf.startDateTime >= ?", StringPool.BLANK);
			}
			if (Validator.isNull(endDateTime)) {
				sql = StringUtil.replace(sql, "AND rrf.startDateTime <= ?", StringPool.BLANK);
			}
			
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar(COUNT_COLUMN_NAME, Type.INTEGER);

			QueryPos qPos = QueryPos.getInstance(q);

			if (!Validator.isBlank(applicant)) {
				qPos.add(applicant);
			}
			if (status != -1) {
				qPos.add(status);
			}
			if (Validator.isNotNull(startDateTime)) {
				qPos.add(startDateTime);
			}
			if (Validator.isNotNull(endDateTime)) {
				qPos.add(endDateTime);
			}

			qPos.add("%" + keywords + "%");
			qPos.add(keywords);
			
			return (Integer) q.uniqueResult();
			
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
	
	public List<String> fetchCycles(String applicant, int status)
			throws SystemException {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(RemoteFormFinder.class.getName() + ".fetchCycles");

			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("yearMonth", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(applicant);
			qPos.add(status);

			return (List<String>) QueryUtil.list(q, getDialect(), 0, 10);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
	
	public List<RemoteForm> searchForStatistics(String applicant, int[] statuses,
			Date searchStartDateTime, Date searchEndDateTime) throws SystemException {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(RemoteFormFinder.class.getName() + ".searchForStatistics");
			sql = StringUtil.replace(sql, "[$STATUS$]", getStatuses(statuses));

			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("rrf", RemoteFormImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(applicant);
			qPos.add(statuses);
			qPos.add(searchStartDateTime);
			qPos.add(searchEndDateTime);

			return (List<RemoteForm>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	public List<RemoteForm> checkOverlappingTimes(String applicant, 
			Date searchStartDateTime) throws SystemException {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(RemoteFormFinder.class.getName() + ".checkOverlappingTimes");

			SQLQuery q = session.createSQLQuery(sql);
			q.addEntity("rrf", RemoteFormImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(applicant);
			qPos.add(searchStartDateTime);

			return (List<RemoteForm>) QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			closeSession(session);
		}
	}
	
	protected String getStatuses(int[] statuses) {
		if (statuses.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(statuses.length * 2);
		sb.append(" AND (");
		for (int i = 0; i < statuses.length; i++) {
			sb.append("rrf.status = ?");
			if ((i + 1) < statuses.length) {
				sb.append(" OR ");
			}
		}
		sb.append(StringPool.CLOSE_PARENTHESIS);
		return sb.toString();
	}
}
