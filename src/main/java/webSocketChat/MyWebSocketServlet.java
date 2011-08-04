package webSocketChat;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class MyWebSocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest paramHttpServletRequest, String paramString) {
		// TODO 自動生成されたメソッド・スタブ
		return new MyWebSocket();
	}

}
