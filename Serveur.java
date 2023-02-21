//Nom : LARBAOUI
//Prénom: Nabila
//Spécialité:  M1_RSSI  Groupe:1
package tp_sockets;
import java.io.*;
import java.net.*;

public class Serveur extends Thread{
    static final int port = 2023;
    Socket socket;
    static int nbclient;
    
	static boolean rechsouschaine(String chaine1, String chaine2) {
		return chaine1.contains(chaine2);
		}
	 
	public static void main(String argv []) throws Exception {
       	ServerSocket ss = new ServerSocket(port);
    	while (true) {
    	 System.out.println("Serveur en attente:");
    	 Socket socketclient=ss.accept();
    	 nbclient ++;
         System.out.println("Nombre de Clients : " + nbclient);
         
         Serveur thread=new Serveur(socketclient);
         thread.start();}
    	 }
         
         public Serveur(Socket soc) {
        	 this.socket=soc;}
         
         public void run () {
        	 try {
        		 ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
    	         ObjectInputStream in=new ObjectInputStream(socket.getInputStream());

    	         while (true) {
    	        	 //System.out.println("Adresse Client: "+socket.getRemoteSocketAddress());
    	             //System.out.println("Nombre de Clients : " + nbclient);
    	             out.writeObject(nbclient);

                	 String chaine1=(String) in.readObject();
                     System.out.println("Chaine 1 reçue : " + chaine1+" du client "+socket.getRemoteSocketAddress());
                     String chaine2=(String) in.readObject();
                     System.out.println("Chaine 2 reçue : " + chaine2+" du client "+socket.getRemoteSocketAddress());
       	 
                     String message1="La chaine "+chaine1+" contient la sous-chaine "+chaine2;
                     String message2="La chaine "+chaine1+" ne contient pas la sous-chaine "+chaine2;

                     if (rechsouschaine(chaine1, chaine2))  {out.writeObject(message1);}
                     else  {out.writeObject(message2);}
                     }
    	         }
              catch (Exception e){
              System.err.println("Un client se deconnecte");
              nbclient=nbclient-1;
              System.out.println("Nombre de Clients restants: " + nbclient);
              }
        	 

    }

}
