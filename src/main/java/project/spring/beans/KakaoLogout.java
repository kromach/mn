package project.spring.beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoLogout {
	
	private static String restApiKey_static = "ccd42c0f6ac58e519759c5baf3c7ceb4";
	private static String adminkey_static = "e454a8dba20ab15cacKakaoLogoutc22a";
	private static String callback_URL_static = "http://localhost:8080/mvc/authResult";
	
	public static  JsonNode doLogout(String token) {
		final String RequestUrl = "https://kapi.kakao.com/v1/user/logout";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		//header
		post.addHeader("Authorization", "Bearer " + token);
		//body
		JsonNode returnJsonNode = null;
		try {
			//json
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("Sending 'POST' request to URL=" + RequestUrl);
			System.out.println("Response Code=" + responseCode);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnJsonNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return returnJsonNode;
	}
}
