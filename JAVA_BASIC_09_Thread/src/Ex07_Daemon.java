public class Ex07_Daemon {
	public static void main(String[] args) {
		try {
			Class.forName("DaemonExample");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
