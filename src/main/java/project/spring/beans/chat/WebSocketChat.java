package project.spring.beans.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
public class WebSocketChat extends TextWebSocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String,WebSocketSession> userSession = new HashMap<String,WebSocketSession>();
	
	
	//getHttpSession
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String nickName = session.getId();
		if(httpSession.get("memNickName")!=null) nickName = (String) httpSession.get("memNickName");
		return nickName;
	}
	
	//클라이언트 연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterEstablished"+session);
		String nickName = getId(session);
		userSession.put(nickName, session);
		System.out.println(nickName + "이 Userssion에 저장되었습니다.");
		System.out.println(userSession);
		logger.info("{} 연결됨",nickName);
		
		sessionList.add(session);
		for(WebSocketSession sess : sessionList) {
			//신규알림
			sess.sendMessage(new TextMessage("Status" +"|"+ nickName+"님이 채팅에 참여하셨습니다"));
			//참여자수
			sess.sendMessage(new TextMessage("CountMember" +"|"+ sessionList.size()));
			//참여자 목록
			Set<String> nickNames = userSession.keySet();  
			System.out.println(nickNames.toString());
			for(String joinMember : nickNames) {
				sess.sendMessage(new TextMessage("JoinMember" +"|"+ joinMember));
			}
		}
	}
	//chat
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("[textMassage]:"+session+":"+message);
		//모든 클라이언트에게 전송
		String nickName = getId(session);
		for(WebSocketSession sess : sessionList) {
			System.out.println("[textSend]");
			sess.sendMessage(new TextMessage(nickName +"|"+message.getPayload()));
		}
		
	}
	
	//클라이언트 연결해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterEstablished"+session+":"+status);
		String nickName = getId(session);
		logger.info("{} 퇴장함",nickName);
		sessionList.remove(session);
		userSession.remove(nickName,session);
		System.out.println(nickName + "이 Userssion에서 제거되었습니다.");
		System.out.println(userSession);
		for(WebSocketSession sess : sessionList) {
			//신규알림
			sess.sendMessage(new TextMessage("Status" +"|"+ nickName+"님이 채팅방에서 퇴장하셨습니다"));
			//참여자수
			sess.sendMessage(new TextMessage("CountMember" +"|"+ sessionList.size()));
			//참여자 목록
			Set<String> nickNames = userSession.keySet();  
			System.out.println(nickNames.toString());
			for(String joinMember : nickNames) {
				sess.sendMessage(new TextMessage("JoinMember" +"|"+ joinMember));
			}
		}
	}
}
