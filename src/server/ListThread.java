package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ListThread implements Runnable{
	private String list;
	public void setList(String list)
	{
		this.list = list;
	}
	public void run() {
		try {
			DatagramSocket ds = new DatagramSocket(null);
			InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 30000);
			ds.bind(addr);
			DatagramPacket dp0 = new DatagramPacket(list.getBytes(),list.length(),
					InetAddress.getByName("127.0.0.1"),3000);
			ds.send(dp0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
