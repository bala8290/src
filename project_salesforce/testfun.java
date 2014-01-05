package project_salesforce;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;

import com.sun.mail.pop3.POP3Store;

public class testfun {

String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";	
	
	POP3Store emailStore = null;
	Folder emailFolder;
	
	public void readmail() throws MessagingException, IOException
	{

		String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";	
		
		Properties properties = new Properties();
		properties.put("mail.pop3.host", "pop.gmail.com");
		properties.put("mail.pop3.port", "995");
		properties.put("mail.pop3.socketFactory.class" , socketFactoryClass);
		Session emailSession = Session.getDefaultInstance(properties);

		emailStore = (POP3Store) emailSession.getStore("pop3");
		emailStore.connect("testmylife8290@gmail.com", "iloverena");			
		System.out.println(emailStore);
		
		emailFolder =  emailStore.getFolder("Inbox");
		System.out.println(emailFolder.getName());
		emailFolder.open(Folder.READ_ONLY);
		
		System.out.println(emailFolder.getMessageCount());
		System.out.println(emailFolder.getNewMessageCount());
		
		Message[] messages = emailFolder.getMessages();
		Object msgContent = messages[messages.length-1].getContent();
		if (msgContent instanceof Multipart){
	        Multipart multipart = (Multipart) messages[messages.length-1].getContent();
	        BodyPart part = multipart.getBodyPart(0);
	        System.out.println(part.getContent().toString());
	     }else
	        System.out.println(messages[messages.length-1].getContent());
		
		System.out.println(messages[messages.length-1].getContent().toString().indexOf("http"));
		System.out.println(messages[messages.length-1].getContent().toString().indexOf(" ",466));
		System.out.println(messages[messages.length-1].getContent().toString().substring(466,726));
	 }
	
}
