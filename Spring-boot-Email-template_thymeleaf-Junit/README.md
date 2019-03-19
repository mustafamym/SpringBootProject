# Spring boot Gmail SMTP Thymeleaf Junit: Send Email with Attachment 

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
