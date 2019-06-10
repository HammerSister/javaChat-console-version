package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ClientFrame1 extends JFrame{

	private Font labelFont = new Font("黑体",Font.PLAIN,22);
	private Font subjectFont = new Font("宋体",Font.PLAIN,18);
	private Border gang = BorderFactory.createLineBorder(Color.black, 1);
	private JFrame f;
	private Client client;
	
	public ClientFrame1(String title)
	{
		client = new Client(3001);
		f = new JFrame(title);
		f.setLayout(null);
		f.setSize(600,800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 800);
		placeComponents(panel);
		f.add(panel);
		
		f.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		JLabel username = new JLabel("请输入用户名");
		username.setBounds(10, 20, 160, 80);
		username.setFont(labelFont);
		panel.add(username);
		JTextField usernameText = new JTextField(200);
        usernameText.setBounds(150,46,420,30);
        usernameText.setFont(subjectFont);
		usernameText.setBackground(new Color(238,238,238));
        panel.add(usernameText);
        
        JButton login = new JButton("上  线");
		login.setFont(labelFont);
		login.setBounds(10, 100, 560, 35);
		panel.add(login);
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				try {
					f.setTitle(usernameText.getText());
					client.init(usernameText.getText());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new ClientFrame1("请登录");
	}

}
