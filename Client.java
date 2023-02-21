//Nom : LARBAOUI
//Prénom: Nabila
//Spécialité:  M1_RSSI  Groupe:1
package tp_sockets;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	static int port = 2023;
    static String host="localhost";
	public static void main(String argv []) {
	try {
		Socket socket=new Socket(InetAddress.getByName(host),port);
		
   	    System.out.println("Mon adresse Client: "+socket.getLocalAddress()+":"+socket.getLocalPort());
   	    System.out.println("Mon serveur est: "+socket.getInetAddress()+":"+socket.getPort());
   	    
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
	    ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
	    while (true) {
        	Thread.sleep(2000);
            System.out.println("Nombre de Clients en cours : " + in.readObject());
            Scanner scanchaine1=new Scanner(System.in);
            System.out.println("Donner une chaine de caractères");
            String chaine1=scanchaine1.nextLine();
            Scanner scanchaine2=new Scanner(System.in);
            System.out.println("Donner la sous-chaine à rechercher");
            String chaine2=scanchaine2.nextLine();
            System.out.println("Les chaines à envoyer au serveur sont :"+chaine1+" ET "+chaine2);
    	
            out.writeObject(chaine1);
            out.writeObject(chaine2);

            String reponse=(String)in.readObject();
    	  
            System.out.println(reponse);
            scanchaine1.close();
            scanchaine2.close();
            out.close();
          	in.close();


        }   }       
	catch (Exception e)
    {
    	System.err.println("Erreur:"+e);
    }
	
	
}
}
