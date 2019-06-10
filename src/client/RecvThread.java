package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RecvThread implements Runnable{
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
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf,1024);
				ds.receive(dp);
				String msg = new String(dp.getData(),0,dp.getLength());
				if(msg.equals("quit"))
				{
					System.out.println("Clientœ¬œﬂ");
					break;
				}
				System.out.println(msg);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
