package lineplus_2018_ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N;
		ArrayList<Integer> result = new ArrayList<>();

		N = Integer.parseInt(br.readLine());
		Map<String, String> nodes = new HashMap<>();
		ArrayList<String> nodeList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodeList.clear();
	
			while (st.hasMoreTokens()) {
				nodeList.add(st.nextToken());
			}
			for (int s = 0; s < nodeList.size() - 1; s++) {
				String key = nodeList.get(s);
				String val = nodeList.get(s + 1);

				if (nodes.containsKey(key)) {
					if(nodes.get(key) == null) {
						nodes.replace(key, val);
					}
					continue;
				} else {
					nodes.put(key, val);
				}
			}
			String key = nodeList.get(nodeList.size() - 1);

			if (nodes.containsKey(key)) {
				continue;
			} else {
				nodes.put(key, null);
			}
		}

		Set<String> se = nodes.keySet();
		Iterator<String> it = se.iterator();
		while (it.hasNext()) {
			String s = it.next();
			if (nodes.get(s) == null) {
				result.add(Integer.parseInt(s));
			}
		}

		Collections.sort(result);

		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}
}
