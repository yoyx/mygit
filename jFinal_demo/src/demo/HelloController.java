package demo;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

	public void index() {
		renderText("Hello JFinal World ");
	}
	
	public void add() {
		renderText("Hello JFinal World add ");
	}

}
