package webSocketChat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.jetty.websocket.WebSocket;

public class MyWebSocket implements WebSocket.OnTextMessage {

	Connection outbound;
	private static Set<MyWebSocket> socketSet = new CopyOnWriteArraySet<MyWebSocket>();
	
	@Override
	public void onClose(int arg0, String arg1) {
		// TODO 自動生成されたメソッド・スタブ
		socketSet.remove(this);
	}

	@Override
	public void onOpen(Connection outbound) {
		// TODO 自動生成されたメソッド・スタブ
		this.outbound = outbound;
		socketSet.add(this);
	}
	
	@Override
	public void onMessage(String data) {
		for (MyWebSocket socket : socketSet) {
			try {
				socket.outbound.sendMessage(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
