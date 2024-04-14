package com.remote.application.util;

import com.remote.application.model.RemoteForm;
import com.remote.application.service.RemoteFormLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.ContentUtil;
import com.remote.application.constants.RemoteFormConstants;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Mailer {

	private final Log _log = LogFactoryUtil.getLog(Mailer.class);
	private final DateFormat _dateFormat = RemoteUtil.getDateFormat();

	private static class InstanceHolder {
		private static final Mailer instance = new Mailer();
	}

	public static Mailer getInstance() {
		return InstanceHolder.instance;
	}
	
	public void sendReviewMail(RemoteForm remoteForm, String auditor, ServiceContext context) throws PortalException, SystemException, AddressException {
		Set<String> receiverAddrs = new HashSet<String>();
		User auditorUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(), auditor);
		String auditorUserName = "--";
		if (Validator.isNotNull(auditorUser)) {
			receiverAddrs.add(auditorUser.getEmailAddress());
			auditorUserName = auditorUser.getFullName();
		}
		
		User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(),
				remoteForm.getApplicant());
		String applicantUserName = "--";
		if (Validator.isNotNull(applicantUser)) {
			applicantUserName = applicantUser.getFullName();
		}

		InternetAddress[] receivers = new InternetAddress[receiverAddrs.size()];
		Iterator<String> iterator = receiverAddrs.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			receivers[i++] = new InternetAddress(iterator.next(), Boolean.TRUE);
		}
		
		long userId = context.getUserId();
		User user = UserLocalServiceUtil.fetchUser(userId);
		TimeZone timeZone = Validator.isNotNull(user) ? user.getTimeZone() : TimeZoneUtil.GMT;		
		String remotePeriodDisplay = RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(), timeZone);

		String entryType = RemoteFormConstants.REMOTEFORM_TYPE;

		String subject = "有新的 ${entryType} 申請等待您簽核";
		String content = ContentUtil.get("mail-tmpl/review_content.tmpl");
		
		String reviewURL = URLUtil.getReviewURL(remoteForm.getGroupId());		

		subject = StringUtil.replace(subject, new String[] { "${entryType}" }, new String[] { entryType });
		content = StringUtil.replace(content,
				new String[] { "${auditor}", "${userName}", "${remoteDate}", "${entryType}", "${link}" },
				new String[] { auditorUserName,applicantUserName, remotePeriodDisplay, entryType, reviewURL });
		
		
		sendEmail(receivers, subject, content);
	}

	public void sendApprovedMail(RemoteForm remoteForm, long userId) throws PortalException, SystemException, AddressException {

		Set<String> receiverAddrs = new HashSet<String>();
		User creatorUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(), remoteForm.getCreator());
		if (Validator.isNotNull(creatorUser)) {
			receiverAddrs.add(creatorUser.getEmailAddress());
		}

		User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(),
				remoteForm.getApplicant());
		String applicantUserName = "--";
		if (Validator.isNotNull(applicantUser)) {
			receiverAddrs.add(applicantUser.getEmailAddress());
			applicantUserName = applicantUser.getFullName();
		}

		InternetAddress[] receivers = new InternetAddress[receiverAddrs.size()];
		Iterator<String> iterator = receiverAddrs.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			receivers[i++] = new InternetAddress(iterator.next(), Boolean.TRUE);
		}
				
		User user = UserLocalServiceUtil.fetchUser(userId);
		TimeZone timeZone = Validator.isNotNull(user) ? user.getTimeZone() : TimeZoneUtil.GMT;	
		String remotePeriodDisplay = "";
		if(remoteForm.getEndDateTime() == null) {
			remotePeriodDisplay = RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getRemoteDate(), timeZone);
		} else {
			remotePeriodDisplay = RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(), timeZone);
		}

		String entryType = RemoteFormConstants.REMOTEFORM_TYPE;

		String subject = "您的  ${entryType} 申請已核准";
		String content = ContentUtil.get("mail-tmpl/result_content.tmpl");
		String result = "核准";
		String resultURL = URLUtil.getApprovedURL(remoteForm.getGroupId());

		subject = StringUtil.replace(subject, new String[] { "${entryType}" }, new String[] { entryType });		
		content = StringUtil.replace(content,
				new String[] { "${applicant}", "${remoteDate}", "${entryType}", "${result}", "${link}" },
				new String[] { applicantUserName, remotePeriodDisplay, entryType, result,
						resultURL });
		sendEmail(receivers, subject, content);
	}

	public void sendRejectedMail(RemoteForm remoteForm, long userId) throws PortalException, SystemException, AddressException {

		Set<String> receiverAddrs = new HashSet<String>();
		User creatorUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(), remoteForm.getCreator());
		if (Validator.isNotNull(creatorUser)) {
			receiverAddrs.add(creatorUser.getEmailAddress());
		}

		User applicantUser = UserLocalServiceUtil.fetchUserByScreenName(remoteForm.getCompanyId(),
				remoteForm.getApplicant());
		String applicantUserName = "--";
		if (Validator.isNotNull(applicantUser)) {
			receiverAddrs.add(applicantUser.getEmailAddress());
			applicantUserName = applicantUser.getFullName();
		}

		InternetAddress[] receivers = new InternetAddress[receiverAddrs.size()];
		Iterator<String> iterator = receiverAddrs.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			receivers[i++] = new InternetAddress(iterator.next(), Boolean.TRUE);
		}

		User user = UserLocalServiceUtil.fetchUser(userId);
		TimeZone timeZone = Validator.isNotNull(user) ? user.getTimeZone() : TimeZoneUtil.GMT;	
		String remotePeriodDisplay = "";
		if(remoteForm.getEndDateTime() == null) {
			remotePeriodDisplay = RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getRemoteDate(), timeZone);
		} else {
			remotePeriodDisplay = RemoteFormLocalServiceUtil.getRemotePeriodDisplay(remoteForm.getRemoteDate(), remoteForm.getEndDateTime(), timeZone);
		}
		
		String entryType = RemoteFormConstants.REMOTEFORM_TYPE;
		String subject = "您的  ${entryType} 申請已拒絕";
		String content = ContentUtil.get("mail-tmpl/result_content.tmpl");
		String result = "拒絕";
		String resultURL = URLUtil.getApprovedURL(remoteForm.getGroupId());

		subject = StringUtil.replace(subject, new String[] { "${entryType}" }, new String[] { entryType });
		content = StringUtil.replace(content,
				new String[] { "${applicant}", "${remoteDate}", "${entryType}", "${result}", "${link}" },
				new String[] { applicantUserName, remotePeriodDisplay, entryType, result,
						resultURL });
		sendEmail(receivers, subject, content);
	}

//	private static void sendEmail(InternetAddress receiver, String subject, String content)
//			throws AddressException, SystemException {
//		sendEmail(new InternetAddress[] { receiver }, subject, content);
//	}

	private void sendEmail(InternetAddress[] receivers, String subject, String content)
			throws AddressException, SystemException {

		MailMessage message = new MailMessage();
		message.setFrom(getSender());
		message.setTo(receivers);
		message.setSubject(subject);
		message.setBody(content);
		message.setHTMLFormat(true);
		MailServiceUtil.sendEmail(message);
		_log.info("Mail sent successfully[" + subject + "]");
	}

	private InternetAddress getSender() throws AddressException, SystemException {
		String senderAddr = PrefsPropsUtil.getPreferences().getValue(PropsKeys.MAIL_SESSION_MAIL_SMTP_USER,
				StringPool.BLANK);
		return new InternetAddress(senderAddr);
	}

}
