package com.gome.architect.gBox.tools;

public class StringKit {

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

}
