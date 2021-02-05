/*
 * @author Chris Rice
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;



public class ChatServer {
	//creates full list of every client connected to server
	public static ArrayList<chatAppClientHandler> list = new ArrayList<chatAppClientHandler>();
	
	//sets a server on port 9999
	public static void main(String[] args) throws IOException{
		ServerSocket ss=null; 
		try {
			ss= new ServerSocket(9999);
			System.out.println(ss);
		} catch(IOException e) {
			System.out.println("server could not be established");
			System.exit(-1);
		}
		
		//attempts to connect clients to the server
		while(true) {
			Socket cs;
			try {
				cs = ss.accept();
				System.out.println("a client has connected");
				//each client is given a chatAppClientHandler. This is added to the list of all clients
				chatAppClientHandler a = new chatAppClientHandler(cs);
				list.add(a);
				Thread t = new Thread(a);
				t.start();
				
			}catch(IOException e) {
				System.out.println("Accept failed");
				System.exit(-1);
			}
		}	
	}	
}

class chatAppClientHandler implements Runnable {
	//The socket associated with the client
	Socket cs;
	
	//a PrintWriter to the client
	PrintWriter subout;
	
	chatAppClientHandler(Socket cs){
		this.cs=cs;
	}

	public void run() {
		//Creates a scanner and printwriter to the client
		Scanner in;
		PrintWriter out;
		try {
			in = new Scanner(new BufferedInputStream(cs.getInputStream()));
			out = new PrintWriter(new BufferedOutputStream(cs.getOutputStream()),true);
			subout=out;
			//Displays connected when the client successfully connects and a scanner and printwriter are successfully made
			out.println("connected");
			out.flush();
			String w = "null";
			//Each new line read in is sent to the handleRequest function
			while(true) {
			w = in.nextLine();
			handleRequest(w,out);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//This function takes each string sent by the client, as well as a printwriter to the client
	//It then prints the message to the console, as well as every client on the list except the one that has the given printwriter
	void handleRequest(String w, PrintWriter out) {
		System.out.println(w);
		for(int i=0;i<ChatServer.list.size();i++){
			if(ChatServer.list.get(i).subout.equals(out)==false) {
			ChatServer.list.get(i).subout.println(w);
			}
		}
		}
	}
	
	
	