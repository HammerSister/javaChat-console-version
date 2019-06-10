package server;
import java.net.*;
public class Server {
	
	public static DatagramSocket init() throws Exception {
		DatagramSocket ds = new DatagramSocket(null);
		InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 3000);
		ds.bind(addr);
		System.out.println("服务器上线");
		return ds;
	}
	
	public static void OTO(DatagramSocket ds) throws Exception {
		byte[] buf = new byte[1024];
		DatagramPacket dpR = new DatagramPacket(buf,1024);
		ds.receive(dpR);
		String loginMSG = new String(dpR.getData(),0,dpR.getLength());
		String username = loginMSG.substring(0, loginMSG.indexOf(" "));
		int port1 = dpR.getPort();
		System.out.println(username + " 已连接	Port:" + dpR.getPort());
		ds.receive(dpR);
		int port2 = dpR.getPort();
		System.out.println(username + " 已连接	Port:" + dpR.getPort());
		
		while(true)
		{
			ds.receive(dpR);
			String msg = new String(dpR.getData(),0,dpR.getLength());
			System.out.println(msg + " | From Port:" + dpR.getPort());
			if(dpR.getPort() == port1)
			{
				DatagramPacket dpS = new DatagramPacket(msg.getBytes(),msg.length(),
						InetAddress.getByName("127.0.0.1"),port2);
				ds.send(dpS);
			}
			if(dpR.getPort() == port2)
			{
				DatagramPacket dpS = new DatagramPacket(msg.getBytes(),msg.length(),
						InetAddress.getByName("127.0.0.1"),port1);
				ds.send(dpS);
			}
			if(msg.equals("quit"))
			{
				break;
			}
		}
	}
	
	public static void GRO(DatagramSocket ds) throws Exception {
		int[] port = new int[10];
		for(int i = 0; i < 10; i++)
		{
			port[i] = 0;
		}
		byte[] buf = new byte[1024];
		DatagramPacket dpR = new DatagramPacket(buf,1024);
		for(int i = 0; i < 5; i++)
		{
			ds.receive(dpR);
			port[i] = dpR.getPort();
			System.out.println("Client" + (i + 1) + "已连接	Port:" + port[i]);
		}
		
		while(true)
		{
			ds.receive(dpR);
			boolean over = true;
			int sender = 11;
			for(int i = 0; i < 10; i++)
			{
				if(port[i] == dpR.getPort())
				{
					sender = i;
					break;
				}
			}
			String msg = new String(dpR.getData(),0,dpR.getLength());
			System.out.println(msg + " | From Port:" + dpR.getPort());
			if(msg.equals("quit"))
			{
				DatagramPacket dpS = new DatagramPacket(msg.getBytes(),msg.length(),
						InetAddress.getByName("127.0.0.1"),port[sender]);
				System.out.println("Client" + (sender + 1) + "退出");
				port[sender] = 0;
				
				for(int i = 0; i < 10; i++)
				{
					if(port[i] != 0)
					{
						over = false;
					}
				}
				if(over)
				{
					break;
				}
			}
			else
			{
				for(int i = 0; i < 5; i++)
				{
					if(port[i] == 0 || port[i] == dpR.getPort())
					{
						continue;
					}
					DatagramPacket dpS = new DatagramPacket(msg.getBytes(),msg.length(),
							InetAddress.getByName("127.0.0.1"),port[i]);
					ds.send(dpS);
					System.out.println("已发送到Port" + port[i]);
				}			
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		//GRO(init());
		OTO(init());
	}
}
