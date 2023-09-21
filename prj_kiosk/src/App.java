import server.ServerRequest;
import view.MainView;

public class App {

	public static void main(String[] args) {
		new ServerRequest().loadImg();
		new MainView();
	}//main

}//class
 