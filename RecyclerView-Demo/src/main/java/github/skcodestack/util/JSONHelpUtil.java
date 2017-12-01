package github.skcodestack.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import android.annotation.SuppressLint;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JSONHelpUtil {
	
	public static String toJSONFromGeneric(Object object) {
		Type mySuperClass = object.getClass().getGenericSuperclass();
		Gson gson = new GsonBuilder().create();
		return gson.toJson(object, mySuperClass);
	}
	/**
	 * An object into a string
	 * @param object
	 * @return
	 */
	public static String toJSON(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	/**
	 * Put the string into a generic objects
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T parseObject(String json, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Object> getJsonObject(String jsonArrayData, Type type)
	{  
		ArrayList<Object> arrayList=new ArrayList<Object>();
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(jsonArrayData);
			int iSize = jsonArray.length();  
			if(iSize>0){
				for (int i = 0; i < iSize; i++) 
				{   
					String objStr = jsonArray.getJSONObject(i).toString(); 
					Object obj=parseObject(objStr,type);
					arrayList.add(obj);
				}
			}
			else{
				return null;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("resource")
	public static String FileToBase64(String fileName) {
		String data = "";
		try {
			File file = new File(fileName);
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length() + 100];
			int length = in.read(buffer);
			data = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
		} catch (Exception ex) {
		}
		return data;
	}
}
