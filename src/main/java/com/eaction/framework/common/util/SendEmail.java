package com.eaction.framework.common.util;

import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;

public class SendEmail {
	protected static Log logger = LogFactory.getLog(ExcelUtil.class);
	/**
	 * 회사메일전송용
	 */
/*
	public static String sendEmail(String from, String from_name, String to, String subject, String content) {
		String result = "";
		try {
	    // Properties 설정
		// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
		Properties props = new Properties();
		  
		// G-Mail SMTP 사용시
		props.put("mail.transport.protocol", "smtp");// 프로토콜 설정
		props.put("mail.smtp.host", ConfigMng.getValue(IPropertyKey.MAIL_SERVER));// gmail SMTP 서비스 주소(호스트)
		props.put("mail.smtp.port", ConfigMng.getValue(IPropertyKey.MAIL_PORT));// gmail SMTP 서비스 포트 설정
	      	      
//		// 로그인 할때 Transport Layer Security(TLS)를 사용할 것인지 설정
//		// gmail 에선 tls가 필수가 아니므로 해도 그만 안해도 그만
//		props.put("mail.smtp.starttls.enable","true");
//		// gmail 인증용 Secure Socket Layer(SSL) 설정
//		// gmail 에서 인증때 사용해주므로 요건 안해주면 안됨
//		props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//		// props.put("mail.smtp.user", from);
//		props.put("mail.smtp.ssl.enable", "true");
//	    props.put("mail.smtp.ssl.trust", ConfigMng.getValue(IPropertyKey.EMAIL_SMTP_HOST));
//		props.put("mail.smtp.auth", "true");// SMTP 인증을 설정

//		Authenticator auth = new SMTPAuthenticator();
//		Session mailSession = Session.getDefaultInstance(props, auth);
		  		  
		// SSL 사용하는 경우
	    props.put("mail.smtp.socketFactory.port", ConfigMng.getValue(IPropertyKey.MAIL_PORT)); //SSL Port
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
	    props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
	         
	    // TLS 사용하는 경우
//	    props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//	    props.put("mail.smtp.port", "587"); //TLS Port
//	    props.put("mail.smtp.auth", "true"); //enable authentication
//	    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	         
	    
	      
	    // 인증
	    Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(ConfigMng.getValue(IPropertyKey.MAIL_ID), ConfigMng.getValue(IPropertyKey.MAIL_PASSWD));
	        }
	    };
	         
	    // 메일 세션
	    Session mailSession = Session.getInstance(props, auth);
		  		  
		// create a message
		Message msg = new MimeMessage(mailSession);
		  
		// set the from and to address
		msg.setFrom(new InternetAddress(from, from_name, "utf-8"));//보내는 사람 설정
		InternetAddress[] addressTo = new InternetAddress[1];
        addressTo[0] = new InternetAddress(to);
		msg.setRecipients(Message.RecipientType.TO, addressTo);//받는 사람설정
				  
		// Setting the Subject and Content Type
//		  msg.setSubject(subject); // 제목 설정
//		  msg.setText(content);  // 내용 설정
		msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B")); // 제목 설정
		msg.setContent(content.replaceAll("\r\n", "<br/>"), "text/html; charset=UTF-8"); // 내용 설정
		msg.setSentDate(new Date());// 보내는 날짜 설정
		  
		Transport.send(msg);  // 메일 보내기
		} catch (Exception ex) {
			ex.printStackTrace();
	        result = "FAIL " + ex.getMessage();
		}
		return result;
	}
	*/
	
	public static String sendEmail(String mail_from ,String mail_from_name, String mail_to, String subject, String msg, List<String> uploadPath, List<String> mail_cc, Map<String, String> imgMap) throws Exception{
		String result = "";

	    // Assuming you are sending email from localhost
	    String host = ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
        String port = ConfigMng.getValue(IPropertyKey.SMTP_PORT);
        
	    // Get system properties
	    Properties properties = new Properties();

	    // Setup mail server
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", port);

	    final String user_name = ConfigMng.getValue(IPropertyKey.SMTP_USERNAME);
	    final String m_pass	   = ConfigMng.getValue(IPropertyKey.SMTP_PASSWORD);
	    logger.debug("SEND_MAIL - user_name : " + user_name);
	    logger.debug("SEND_MAIL - m_pass : " + m_pass);
	    // Get the default Session object.
	    //Session session = Session.getDefaultInstance(properties);
	    Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user_name, m_pass);
            }
        });


	    try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         logger.debug("SEND_MAIL - m_pass : " + m_pass);
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(user_name, mail_from_name, "utf-8"));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(mail_to));
	         if(mail_cc != null && mail_cc.size() > 0) {
		         for(String cc : mail_cc) {
		        	 message.addRecipient(Message.RecipientType.CC,  new InternetAddress(cc));
		         }
	         }
	         // Set Subject: header field
	         message.setSubject(subject,"UTF-8");

	         // Now set the actual message
	         //String tmpmsg = (new String(msg.getBytes("UTF-8"), "8859_1"));
	         MimeBodyPart bodypart = new MimeBodyPart();
	         bodypart.setContent(msg, "text/html; charset=UTF-8");
	         
	         Multipart multipart = new MimeMultipart();	         
	         multipart.addBodyPart(bodypart);
	         
	         // 메일에 이미지가 포함된경우 이미지를 추가한다.
	        if(imgMap != null && imgMap.size() > 0) {
		 		for(Map.Entry<String, String> entry : imgMap.entrySet()) {
		        	bodypart = new MimeBodyPart();
					DataSource fds = new FileDataSource(entry.getValue());
					bodypart.setDataHandler(new DataHandler(fds));
					bodypart.setHeader("Content-Id", entry.getKey());
			 		multipart.addBodyPart(bodypart);
				}
	        }
	 		
	         // 첨부파일이 있는경우만 한다.
	         if(uploadPath != null && uploadPath.size() > 0) {
	        	 for(String filePath : uploadPath) {
	    	         MimeBodyPart bodypartAttacth = new MimeBodyPart();
	    	         FileDataSource fds = new FileDataSource(filePath);
	    	         bodypartAttacth.setDataHandler(new DataHandler(fds));
	    	         bodypartAttacth.setFileName(fds.getName());
	    	         multipart.addBodyPart(bodypartAttacth);
	        	 }
	         }

	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);
	         System.out.println("E-mail send successfully....");
	         
	         result = "SUC";
	    }catch (Exception ex) {
	    	 System.out.println("## E-mail send failure!!!" + ex.getMessage());	         
	         throw ex;
	    }finally {
	    	//메일 전송후 임시생성한 파일 삭제
	    	try {
		         if(uploadPath != null && uploadPath.size() > 0) {
		        	 for(String filePath : uploadPath) {
		 	            FileUtil.deleteFile(filePath);
		        	 }
		         }
	    	} catch (Exception e) {
	    	}
	    }
	    return result;
	}
	/**
	 * Gmail전송용
	 */
	public static String sendEmail(String mail_from ,String mail_from_name, String mail_to, String subject, String msg, List<String> uploadPath, List<String> mail_cc) throws Exception{
		return sendEmail(mail_from ,mail_from_name, mail_to, subject, msg, uploadPath, mail_cc, null);
	}
	
	/**
	 * Gmail전송용
	 */
	public static String sendEmail(String mail_from ,String mail_from_name, String mail_to, String subject, String msg, List<String> uploadPath) throws Exception{
		return sendEmail(mail_from ,mail_from_name, mail_to, subject, msg, uploadPath, null);
	}
	
	public static String readTmplConvertMail(String mailTmplSrc , Map<String, String> convertMap) throws Exception{
		
		String mailHtml = FileUtil.readFileToString(mailTmplSrc);
		
		for(Map.Entry<String, String> entry : convertMap.entrySet()) {
			mailHtml = mailHtml.replace(entry.getKey(), entry.getValue());
		}
		
		return mailHtml;
	}
}
