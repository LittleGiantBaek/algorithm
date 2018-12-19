package lineplus_2018_ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Map<String, String> usermap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			final int n = Integer.parseInt(br.readLine());

			for (int i = 0; i < n; i++) {
				final StringTokenizer tokenizer = new StringTokenizer(br.readLine());

				String method = tokenizer.nextToken(); // GET-data리턴 , POST-데이터 생성, 저장
				String url = tokenizer.nextToken(); // users/something
				String body = null; // data

				if (tokenizer.hasMoreTokens()) {
					body = tokenizer.nextToken();
				}

				String retMsg = request(method, url, body);
				System.out.println(retMsg);
			}
		}
	}

	public static String request(String method, String url, String body) {
		StringTokenizer st = new StringTokenizer(url, "/");
		String root = st.nextToken(); // users

		if (method.equals("POST")) {
			String Id = st.nextToken(); // id
			if (st.hasMoreTokens()) { // /data
				if (body == null) {
					return "405 METHOD_NOT_ALLOWED";
				}

				if (usermap.containsKey(Id)) {
					st = new StringTokenizer(body, "=");
					st.nextToken();
					String data = st.nextToken();
					usermap.replace(Id, data);
					return "200 OK";
				} else {
					return "403 FORBIDDEN";
				}

			} else { // ID 생성
				if (usermap.containsKey(Id)) { // ID가 이미 있는 경우
					return "403 FORBIDDEN";
				} else {
					usermap.put(Id, null);
					return "201 CREATED";
				}
			}
		} else { // GET
			if (!root.equals("users")) {
				return "404 NOT_FOUND";
			}
			if (body != null) { // GET DATA 생성 불가
				return "405 METHOD_NOT_ALLOWED";
			}

			String Id = st.nextToken();

			if (st.hasMoreTokens()) { // data
				if (usermap.containsKey(Id)) {
					if (usermap.get(Id) == null) {
						return "404 NOT_FOUND";
					} else {
						return "200 OK " + usermap.get(Id);
					}
				} else {
					return "403 FORBIDDEN";
				}

			} else { // GET ID 생성 불가
				return "405 METHOD_NOT_ALLOWED";
			}

		}
	}
}