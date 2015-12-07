package br.com.edu.nmonitor.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametersMapUtils {
	public static Map<String, List<String>> buildParamMap(String[] args) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();

		if (args != null) {
			for (int i = 0; i < args.length; i++) {

				String el = args[i];
				if (el.startsWith("-")) {

					// it is a param
					String key = el.substring(1);
					if (key.isEmpty())
						continue;
					List<String> values = new ArrayList<String>();

					boolean hasValue = true;

					// seeking param values
					int add = 1;
					while (hasValue) {

						int indx = i + add;
						if (indx < args.length && !args[indx].startsWith("-")) {
							values.add(args[indx]);
						} else {
							hasValue = false;
						}
						add++;
					}

					if (values.size() > 0) {
						params.put(key, values);
					}
				}
			}
		}
		return params;
	}
}
