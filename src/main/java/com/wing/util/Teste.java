package com.wing.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

public class Teste {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, JsonGenerationException, JsonMappingException, IOException {
		
		
		/*
		Client cli = Client.create();
		WebResource res = cli.resource("http://sac-martinsb2b.ascbrazil.com.br/Chat/login/resp-login");
		ClientResponse resp = res.accept("application/json, text/javascript, *!/*; q=0.01").get(ClientResponse.class);
		String resposta = resp.getEntity(String.class);
		System.out.println(resposta); */
		URL url = new URL("http://sac-martinsb2b.ascbrazil.com.br/Chat/login/resp-login");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		Map<String, String> params = new HashMap<String, String>();
		/*params.put("Accept", "Accept:application/json, text/javascript, *!/*; q=0.01");
		params.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		params.put("Accept-Encoding", "gzip, deflate");
		params.put("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
		params.put("Connection", "keep-alive");
		params.put("Content-Length", "163");
		params.put("Cookie", "SAC-CHAT=o9atsemefmdgfva64jlsh024b1");
		params.put("Host", "sac-martinsb2b.ascbrazil.com.br");
		params.put("Connection", "keep-alive");
		params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");*/
//		con.setDoOutput(true);
		
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		con.setRequestProperty("Accept", "Accept:application/json, text/javascript, */*; q=0.01");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Content-Length", "163");
		con.setRequestProperty("Cookie", "SAC-CHAT=o9atsemefmdgfva64jlsh024b1");
		con.setRequestProperty("Host", "sac-martinsb2b.ascbrazil.com.br");
		con.setRequestProperty("Connection", "keep-alive");
		
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		StringBuilder build = new StringBuilder();
		while((line = buf.readLine())!=null) {
			build.append(line);
		}
		
		buf.close();
		con.disconnect();
		
		System.out.println("res"+build.toString());
	}
	static String paramsAsString(Map<String, String> params) {
		StringBuilder build = new StringBuilder();
		for(Map.Entry<String, String>es:params.entrySet()) {
			build.append(URLEncoder.encode(es.getKey()));
			build.append("=");
			build.append(URLEncoder.encode(es.getValue()));
			build.append("&");
		}
		String retorno = build.toString();
		return retorno.length()>0
				?retorno.substring(0, retorno.length()-1)
				:retorno;
	}
}
