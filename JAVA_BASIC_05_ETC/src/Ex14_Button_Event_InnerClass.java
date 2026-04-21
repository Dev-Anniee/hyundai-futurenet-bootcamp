import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class LoginForm2 extends Frame {
	Label lbl_id;
	Label lbl_pwd;
	TextField txt_id;
	TextField txt_pwd;
	Button btn_ok;
	//컴포넌트 (만들어진 객체들)
	
	public LoginForm2(String title) {
		super(title);
		lbl_id = new Label("ID:",Label.RIGHT);
		lbl_pwd = new Label("pwd",Label.RIGHT);
		
		txt_id = new TextField(10);
		txt_pwd = new TextField(10);
		txt_pwd.setEchoChar('*');
		
		btn_ok = new Button("login");
		
		this.setLayout(new FlowLayout()); //add 순서대로 컴포넌트가 배치
		this.setSize(500,200);
		this.setVisible(true);
		
		this.add(lbl_id);
		this.add(txt_id);
		
		this.add(lbl_pwd);
		this.add(txt_pwd);
		
		this.add(btn_ok);
		
		//inner class
		class  Btn_handler implements ActionListener {
		    
			//Button 에 click 이벤트가 발생 되었을때 호출되는 함수 ..
			@Override
			public void actionPerformed(ActionEvent e) { 

				//	LoginForm2 안에 컴포넌트 자원을 그대로 접근
				
				String id = txt_id.getText().trim();
				String pwd = txt_pwd.getText();
				
				System.out.println(e.getSource());
				if(id.equals("hong")) {
					System.out.println("방가 :" + " / " + pwd);
				}else {
					System.out.println("배고픈 당신은 누구 ^^");
				}
			}
		}
		this.btn_ok.addActionListener(new Btn_handler());
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose(); //메모리해제
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}

public class Ex14_Button_Event_InnerClass {

	public static void main(String[] args) {
		LoginForm2 loing = new LoginForm2("로그인");

	}

}
