package project.spring.beans.chat;

import java.util.ArrayList;
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
public class WebSocketChat extends TextWebSocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	//클라이언트 연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterEstablished"+session);
		sessionList.add(session);
		logger.info("{} 연결됨",session.getId());
	}
	//chat
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("[textMassage]:"+session+":"+message);
		//모든 클라이언트에게 전송
		for(WebSocketSession sess : sessionList) {
			System.out.println("[textSend]");
			sess.sendMessage(new TextMessage(session.getId() +"|"+message.getPayload()));
		}
	}
	
	//클라이언트 연결해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterEstablished"+session+":"+status);
		if(session.getPrincipal()!=null) {
			sessionList.remove(session);
			logger.info("{} 연결됨",session.getId());
			System.out.println("채팅방 퇴장 : "+ session.getPrincipal().getName());
		}
	}
	
	
}
