package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendThread implements Runnable{
	private DatagramSocket ds;
	public void setSocket(DatagramSocket ds)
	{
		this.ds = ds;
	}
	public void run()
	{
		while(true)
		{
			try {
				Scanner in = new Scanner(System.in);
				String msg = in.nextLine();
				DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),
						InetAddress.getByName("127.0.0.1"),3000);
				ds.send(dp);
				if(msg.equals("quit"))
				{
					break;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
