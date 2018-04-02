package chok.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil
{
	private static Properties prop;

	public static String getValue(String key)
	{
		return getValue("", key);
	}
	
	public static String getValue(String dir, String key)
	{
		if (prop == null)
			prop = new Properties();
		try
		{
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(dir + "application.properties"));
			return prop.getProperty(key);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
