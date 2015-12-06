package com.gome.architect.gBox.tools;

import java.util.ArrayList;
import java.util.List;

public class StringKits {

	/**
	 * Validate the given string parameter
	 * @param str
	 * @return if the given string parameter is null or "",return false,else return true.
	 */
	public static boolean isEmpty(String... strs){
		for( String str : strs ){
			if( str == null || str.length() == 0 ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Judge the given char is Uppercase letter or not
	 * @param c
	 * @return if the given char is uppercase return true,or return false.
	 */
	public static boolean isUppercase(char c){
		if( c < 'A' || c > 'z' ){
			throw new StringKitException("The char is not letter");
		}else{
			if( c >= 'A' && c <='Z' ){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * Resolve the json form vIDs
	 * @param vIDs
	 * @return
	 */
	public static List<String> resolveJson(String vIDs){
		if( vIDs == null || vIDs.length() == 0 ) return null;
		
		String[] kv = vIDs.split(":");
		
		String[] ids = kv[1].split(",");

		if( ids == null || ids.length == 0 ) return null;
		int length = ids.length;

		List<String> idList = new ArrayList<String>();
		for( int i = 0; i <= length - 1; i++ ){
			ids[i] = ids[i].trim();
			if( i == 0 ) {
				idList.add(ids[i].substring(1));
				continue;
			}
				
			if( i == length - 1 ){
				idList.add(ids[i].substring(0,ids[i].length() - 1));
				continue;
			} 
			
			idList.add(ids[i]);
		}
		
		return idList;
	}

}
