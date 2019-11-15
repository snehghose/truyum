package com.cognizant.truyum.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class FileImportUtil {

	public static String getSQL(String path) {
		String query = "";
		BufferedReader reader=null;
		try
		{
			String string;
			reader = new BufferedReader(new FileReader(Thread.currentThread().getContextClassLoader().getResource(path).toURI().getPath()));
			while ((string = reader.readLine()) != null)
				query+=string;
		}
		catch(IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return query;
	}
}
