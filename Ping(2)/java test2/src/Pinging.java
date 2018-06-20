import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pinging extends Thread {

	private Object[] msg;
	private String ip;
	
	public Pinging(String ip) {
		this.ip = ip;
		msg = new Object[4];
	}
	
	@Override
	public void run() {
		BufferedReader br=null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -a " + ip);
			msg[0] = ip;
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("[") >= 0) {
					msg[3] = line.substring(5, line.indexOf("[") - 1);
				}
				if (line.indexOf("ms") >= 0) {
					msg[1] = line.substring(line.indexOf("ms") - 1, line.indexOf("ms") + 2);
					msg[2] = line.substring(line.indexOf("TTL=") + 4, line.indexOf("TTL=") + 7);
					break;
				}
			} 
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Object[] getMsg() {
		try {
			join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	public static void main(String[] args) {
		Pinging[] pg = new Pinging[254];
		String fixedIp = "192.168.3.";
		for(int i=0; i<=253; i++){
			pg[i] = new Pinging(fixedIp+ (i+1));
			pg[i].start();
		}
//		for(int i=0; i<=253; i++) {
//			Object[] msg = pg[i].getMsg();
//			if(msg == null) {
//				System.out.println("die");
//			} else {
//				System.out.println(msg[0] + "," + msg[1] + "," + msg[2] + "," + msg[3]);
//			}
//		}
	}

}