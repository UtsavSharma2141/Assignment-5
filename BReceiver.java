/**
 *
 * @author Utsav Sharma
 */ 
import java.net.*;
import java.io.*;
import java.util.*;

class BReceiver {

    public  void receiveMulticastMessages(String group,int port)
    {
MulticastSocket s=null;
byte[] buffer = new byte[1024];
DatagramPacket message = new DatagramPacket(buffer, buffer.length);

//Set up the socket and join the group
try {
   s = new MulticastSocket(port);
   s.joinGroup(java.net.InetAddress.getByName(group));
   // Create a buffer for the message

   System.out.println("Waiting to receive messages...");

   while(true)
   {
s.receive(message);

System.out.print("Received data from: " + message.getAddress().toString() + ":"
+ message.getPort() +
" with length: " + message.getLength() +
"\nMessage: ");
System.out.write(message.getData(), 0, message.getLength());
System.out.println();

   } //end of while loop
} catch (IOException ex) {
   ex.printStackTrace();
   try {
s.leaveGroup(java.net.InetAddress.getByName(group));
s.close();
   }
   catch(Exception ex2) {
ex2.printStackTrace();
   }
}
    }

    public static void main(String argv[])
    {
       
BReceiver receiver=new BReceiver();
receiver.receiveMulticastMessages("239.239.239.110",9999);
receiver.receiveMulticastMessages("239.239.239.111",9999);
receiver.receiveMulticastMessages("239.239.239.112",9999);

    }
}
