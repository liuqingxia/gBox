package com.gome.architect.gBox.tools;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class BeanUtils {
	
	/**
	 * Judge the given object is null or not 
	 * @param obj
	 */
	private static void isNull(Object... objs){
		for( Object obj : objs ){
			if( obj == null ){
				throw new NullPointerException();
			}			
		}
	}
	
	/**
	 * Set a field of a pojo
	 * @param fName
	 * @param obj
	 * @param fValue
	 */
	private static <T> void setField(String fName,T obj,Object fValue){
		try {
			Field field = obj.getClass().getDeclaredField(fName);
			field.setAccessible(true);
			field.set(obj, fValue);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Validate pojos 
	 * @param obj
	 * @return boolean
	 * @throws BeanUtilsException if the validate process failed
	 */
	public static <T> boolean validateBean(T obj) throws BeanUtilsException{
		isNull(obj);
		Field[] fields = obj.getClass().getDeclaredFields();
		if( fields == null || fields.length == 0 ){
			throw new BeanUtilsException("The given object " + obj + "has no fields");
		}else{
			for( Field field : fields ){
				field.setAccessible(true);
				try {
					Class<?> fieldType = field.getType();
					Object fieldValue = field.get(obj);
					if( fieldType.toString().equals("class java.lang.String") ){
						String value = (String)fieldValue;
						if( value == null || value.length() == 0 ){
							return false;
						}
					}else{
						if( fieldValue == null ){
							return false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			return true;
		}	
	}
	
	/**
	 * Assemble a pojo by the given params
	 * @param obj
	 * @param params
	 * @return a T object
	 */
	public static <T> T assembleBean(T obj,HashMap<String,Object> params){
		isNull(obj,params);
		for( Entry<String, Object> entry : params.entrySet() ){
			setField(entry.getKey(),obj,entry.getValue());
		}
		return obj;
	}
	
	/**
	 * Assemble a pojo by the given attribute mapping relation
	 * @param request
	 * @param obj
	 * @param attributes
	 * @return a pojo
	 */
	public static <T> T assembleBean(HashMap<String,Object> kv,T obj,String... attrNames){
		isNull(obj,kv); 
		System.out.println(kv);
		for( String attrName : attrNames ){
			String[] nameMap = attrName.split("\\s");
			setField(nameMap[1],obj,kv.get(nameMap[0]));
		}
		return obj;
	}
	
	/**
	 * when the html form's has the same name with pojos,use this method
	 * @param request
	 * @param obj
	 * @return T pojos
	 */
	public static <T> T assembleBean(HttpServletRequest request,T obj){
		isNull(request,obj);
		Enumeration<String> en = request.getParameterNames();
		while( en.hasMoreElements() ){
			String attribute = en.nextElement();
			setField(attribute, obj, request.getParameter(attribute));
		}
		return obj;
	}
	
	/**
	 * Get the field value by the given field name
	 * @param obj which object's field value you want get
	 * @param fieldName field name
	 * @return field value
	 * @throws Exception
	 */
	public static Object getValue(Object obj,String fieldName) throws Exception {
		if( obj == null )
			throw new NullPointerException();
		if( StringKits.isEmpty(fieldName) )
			throw new BeanUtilsException("The fieldName can not be empty");
		
		Field f = obj.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		
		return f.get(obj);
	}
	
	/**
	 * Get certain method by the given method name ,class type,parameter's class type
	 * @param methodName
	 * @param clazz
	 * @param params
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static  Method getMethod(String methodName,Class<?> clazz,Class<?>... params) 
			throws SecurityException, NoSuchMethodException{
		return clazz.getDeclaredMethod(methodName, params);
	}
	
	
}
	
	