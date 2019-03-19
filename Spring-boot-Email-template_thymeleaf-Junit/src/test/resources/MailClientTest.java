package au.com.quikate.th.email;



import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.thymeleaf.mail.entity.Application;
import com.thymeleaf.mail.service.EmailService;
import com.thymeleaf.mail.Mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
/*
 * @author mustafa
 * creation date : 13/10/2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class MailClientTest {

    @Autowired
    private EmailService emailService;

    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void shouldSendMail() throws Exception {
        //given
        Mail mail = new Mail();
        mail.setFrom("mustafamym@gmail.com");
        mail.setTo("gmbdme@gmail.com");
        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "mr/mrs");
        model.put("location", "Dhaka");
        model.put("signature", "http://quikate.com");
        mail.setModel(model);
        mail.setModel(model);
        //send 
        emailService.sendSimpleMessage(mail);

    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

}
