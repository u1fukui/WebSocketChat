package webSocketChat;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebSocketChat {

	public static void main(String[] args) throws Exception {
		new WebSocketChat();
	}
	
	public WebSocketChat() throws Exception {

	    Server server = new Server(8080);	// ポート番号
	    
	    // HTTP用ハンドラ
	    ResourceHandler rh = new ResourceHandler();
	    rh.setResourceBase(this.getClass().getClassLoader().getResource("html").toExternalForm());

	    // websocket用ハンドラ
	    MyWebSocketServlet wss = new MyWebSocketServlet();
	    ServletHolder sh = new ServletHolder(wss);
	    ServletContextHandler sch = new ServletContextHandler();
	    sch.addServlet(sh, "/ws/*");
	    
	    // Serverにハンドラを登録
	    HandlerList hl = new HandlerList();
	    hl.setHandlers(new Handler[] {rh, sch});
	    server.setHandler(hl);

	    // サーバ起動
	    server.start();
	  }
}
