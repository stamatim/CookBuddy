/*
 * @author Chris Rice
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class ChatClient {
	
	//The constructor runs all functionality of the client
	public static void main(String[] args) {
		ChatClient lc = new ChatClient();
	}
	
	
	
	//takes a standard hostname and a given server port number
	Socket ss;
	String serverHostName = "localhost";
	int serverPortNumber =9999;
	//The serverListener that will be checking for input 
	ServerListener s1;
	//The clients given name
	String name;
	ChatClient(){
		//Reads in the clients name
		System.out.print("Enter your Name:");
		//creates a scanner for the client console
		Scanner in1=new Scanner(System.in);
		String s = in1.nextLine();
		name=s;
		//creates a new socket to connect with the server
		try {
			ss = new Socket(serverHostName, serverPortNumber);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		//creates and starts a new ServerListner
		s1 = new ServerListener(this,ss);
		new Thread(s1).start();
		//creates a new PrintWriter that will push any entered text to the server, after appending it to the name of the client
		PrintWriter out;
		try {
			out=new PrintWriter(new BufferedOutputStream(ss.getOutputStream()));
			while(true) {
				String o = in1.nextLine();
				out.println(name + ": " + o);
				out.flush();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	//ServerListener class that waits on the socket, and prints any message sent by the server to the client console. 
	class ServerListener implements Runnable{
		ChatClient cc;
		Scanner in;
		
		ServerListener(ChatClient cc, Socket s){
			try {
				this.cc = cc;
				in = new Scanner(new BufferedInputStream(s.getInputStream()));
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(true) {
				String s = in.nextLine();
				System.out.println(s);
			}
		}
	}
}
