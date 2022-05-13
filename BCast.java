/**
 *
 * @author Utsav Sharma
 */ 
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
class BCast
{
    public  void sendMulticastMessages(String group, int port)
    {
String msg="";
BufferedReader teraalInput = new BufferedReader(new InputStreamReader(System.in));
MulticastSocket s=null;

try {
   byte timeToLive = 10;  //Allow only 10 hops - local subnet only
   // Create the socket but we don't bind it as we are only going to send data
   s = new MulticastSocket(port);
            s.joinGroup(InetAddress.getByName(group)) ;
   s.setTimeToLive(timeToLive);  

   while(true)
   {
msg=("Utsav Sharma, Hope for the best but prepare for the worst.");

//Package the message and send it
java.net.DatagramPacket bundle = new java.net.DatagramPacket(
msg.getBytes(),
msg.length(),
java.net.InetAddress.getByName(group),
port);
s.send(bundle);
try{
Thread.sleep(900000);
} catch(InterruptedException e) {
 e.printStackTrace();
}

 
 }
 
} catch (IOException ex) {
   ex.printStackTrace();
}

s.close();
    }

    public static void main(String[] argv)
    {
BCast sender=new BCast();
int a = 1;
int b = 3;
while(true)
{
 int i = (int)(Math.random() * (b - a + 1) + a);
 switch(i)
 {
case 1 : sender.sendMulticastMessages("239.239.239.110",9999); break;
case 2 : sender.sendMulticastMessages("239.239.239.111",9999); break;
case 3 : sender.sendMulticastMessages("239.239.239.112",9999); break;
default: System.out.println("Randomly Displayed");
 }

 try{TimeUnit.MINUTES.sleep(15);}
 catch(InterruptedException e){Thread.currentThread().interrupt();}
    }
    }
}


