import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Btn_handler implements ActionListener{
	private TextField txtid;
	private TextField txtpwd;
	
	public Btn_handler(TextField txtid, TextField txtpwd) {
		this.txtid = txtid;
		this.txtpwd = txtpwd;
	}

	
	// 버튼에 클릭 이벤트가 발생했을 때 호출되는 함수
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if(txtid.getText().equals("hong")) {
			System.out.println("방가 : " +"/" + txtpwd.getText());
		}else {
			System.out.println("배고픈 당신은 누구신가요");
		}
	}
}

class LoginForm extends Frame {
	Label lbl_id;
	Label lbl_pwd;
	TextField txt_id;
	TextField txt_pwd;
	Button btn_ok;
	//컴포넌트 (만들어지 객체들)
	
	public LoginForm(String title) {
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
		this.btn_ok.addActionListener(new Btn_handler(txt_id, txt_pwd));
	}
}

public class Ex13_Button_Event {

	public static void main(String[] args) {
		LoginForm loginForm = new LoginForm("로그인");

	}

}