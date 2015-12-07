package br.com.edu.nmonitor.utils;

import java.util.List;
import java.util.Map;

import br.com.edu.nmonitor.exceptions.InvalidParameterException;

public class ParameterUtil {
	
	public static String getParameter(String paramName,Map<String, List<String>> params, boolean required)
			throws InvalidParameterException {

		if (params.containsKey(paramName)) {
			String value = params.get(paramName).get(0);

			if (value.isEmpty() && required) {
				throw new InvalidParameterException(
						"Missing mandatory parameter [" + paramName + "]");
			}

			return value;
		} else if (required) {
			throw new InvalidParameterException("Missing mandatory parameter ["
					+ paramName + "]");
		} else {
			return null;
		}
	}

	public static Integer getIntegerParameter(String paramName,Map<String, List<String>> params, boolean required)
			throws InvalidParameterException {

		String value = ParameterUtil.getParameter(paramName, params,
				required);
		if (value == null || value.isEmpty())
			return null;

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			if (required) {
				throw new InvalidParameterException(
						"Missing mandatory parameter [" + paramName + "]");
			}

			return null;
		}
	}

	public static Integer getIntegerParameter(String paramName, Map<String, List<String>> params, Integer i)
			throws InvalidParameterException {

		Integer value = i;

		try {
			value = getIntegerParameter(paramName, params, false);
			if (value == null)
				return i;
			return value;
		} catch (NumberFormatException e) {
			return value;
		}
	}
}