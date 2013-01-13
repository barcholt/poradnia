package org.part_ter;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ObserwatorMail implements ObserverClass {

	// dane do wysyłki maila, niedostępne w repo...
	Terapeutka ter;
	private String smtpServer="";
	private String to = "";
	private String from = "";
	private String subject = "";
	private String body = "test";
	private Subject manager;

	public ObserwatorMail(Subject sub) {
		this.manager = sub;
		sub.addObservers(this);
	}

	
	private static void send (String smtpServer, String to, String from, String subject, String body) {
		try { 
		 Properties prop = System.getProperties();
		 prop.put("mail.smtp.host", smtpServer);
		 Session session = Session.getDefaultInstance(prop, null);
		 Message msg = new MimeMessage(session);
		 msg.setFrom(new InternetAddress(from));
		 msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		 msg.setSubject(subject);
		 msg.setText(body);
		 msg.setHeader("XMailer", "");
		 msg.setSentDate(new Date());
//		 System.out.println(msg);
		 Transport.send(msg);
		 
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		 
		 
	}
	
	
	
	@Override
	public void notify(Wizyta wiz, String str) {
		ManagerTerapeutkaMysql nu = new ManagerTerapeutkaMysql();
		ManagerKlientMysql ne = new ManagerKlientMysql();
		ter = nu.get(wiz.getId_terap());
		Klient kli = ne.get(wiz.getId_klient());
		System.out.println("id kli: " + wiz.getId_klient());
		this.to = ter.getEmail();
		this.body = str + wiz.getData() + ", " + wiz.getGodzina() + ", klient: " + kli.getImie() + " " + kli.getNazwisko();
		System.out.println(body);
		send(smtpServer, to, from, subject, body);
		
	}

}
