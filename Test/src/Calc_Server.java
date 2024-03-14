// Java program to illustrate Server Side Programming 
// for Simple Calculator using TCP 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 

public class Calc_Server 
{ 
	public static void main(String args[]) throws IOException 
	{ 

		// Step 1: Establish the socket connection. 
		//System.out.println(InetAddress.getLocalHost());
		ServerSocket ss = new ServerSocket(6666,2,InetAddress.getByName("10.70.2.157")); 
		Socket s = ss.accept(); 

		// Step 2: Processing the request. 
		DataInputStream dis = new DataInputStream(s.getInputStream()); 
		DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

		while (true) 
		{ 
			// wait for input 
			String input = dis.readUTF(); 

			if(input.equals("bye")) 
				break; 

			System.out.println("Equation received:-" + input); 
			int result; 

			// Use StringTokenizer to break the equation into operand and 
			// operation 
			StringTokenizer st = new StringTokenizer(input); 

			int oprnd1 = Integer.parseInt(st.nextToken()); 
			String operation = st.nextToken(); 
			int oprnd2 = Integer.parseInt(st.nextToken()); 

			// perform the required operation. 
			if (operation.equals("+")) 
			{ 
				result = oprnd1 + oprnd2; 
			} 

			else if (operation.equals("-")) 
			{ 
				result = oprnd1 - oprnd2; 
			} 
			else if (operation.equals("*")) 
			{ 
				result = oprnd1 * oprnd2; 
			} 
			else
			{ 
				result = oprnd1 / oprnd2; 
			} 
			System.out.println("Sending the result..."); 

			// send the result back to the client. 
			dos.writeUTF(Integer.toString(result)); 
		} 
	} 
}