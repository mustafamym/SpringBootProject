# jms-activemq-message-consumer-example
JMS ActiveMQ consumer application  Server

Pre Requesting  
1.JDK 1.8
2. IDE (eclipse or Netbeans)
3.Maven 
4.apache tome 8+
5.apache activeMq server
6.MySql server
7 REST client (eg. Postman )

How  project works.
Step 1:open project form any Spring pluging enable Ide 
Step 2: change databse configurate at application context file
Step 3:run spring boot application via command line or form Ide
If run from Ide then   run spring main file under packge com.payment
Step2:send file via rest client 
Api url: http://localhost:8080/upload
Http Metohd :POST
Content:From-Data

Other Rest Api
For Cutomer Info:
http://localhost:8080/list
billing information
URL=http://localhost:8080/billInfo
Http Method:GET

billing Pay information

URL=http://localhost:8080/bilpmtInfo
Http Method:GET

Thanks you so much
