package demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		
		/**负责配置一些常量*/
		me.setDevMode( true );
		me.setViewType( ViewType.JSP );
	}
	
	@Override
	public void configRoute(Routes me) {
		
		/**负责配置jFinal的访问路由*/
		me.add("/hello", HelloController.class ) ;//访问http://localhost:8080/hello时将会访问到hello地下的index方法，
		me.add("/add", HelloController.class , "/add") ;//访问HelloController里面的add 方法
	}
	@Override
	public void configEngine(Engine me ) {
		
		
	}

	@Override
	public void configHandler(Handlers me ) {
		
	}

	@Override
	public void configInterceptor(Interceptors me ) {
		
	}

	@Override
	public void configPlugin(Plugins me ) {
		
	}

	

}
