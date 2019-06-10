package client;
import java.net.*;

public class Client2 {
	
	public static DatagramSocket init() throws Exception {
		DatagramSocket ds = new DatagramSocket(null);
		InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 3002);
		ds.bind(addr);
		String online = "Client2…œœﬂ";
		DatagramPacket dp0 = new DatagramPacket(online.getBytes(),online.length(),
				InetAddress.getByName("127.0.0.1"),3000);
		ds.send(dp0);
		System.out.println(online);
		return ds;
	}
	
	public static void SAR(DatagramSocket ds) {
		SendThread rS = new SendThread();
		rS.setSocket(ds);
		Thread S = new Thread(rS);
		S.start();
		RecvThread rR = new RecvThread();
		rR.setSocket(ds);
		Thread R = new Thread(rR);
		R.start();
	}
	
	public static void main(String[] args) throws Exception {
		SAR(init());
	}
	
}
