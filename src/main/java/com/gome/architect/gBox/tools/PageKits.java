package com.gome.architect.gBox.tools;

import java.util.ArrayList;
import java.util.List;

public class PageKits {

	/**
	 * Get the sub list from the all list
	 * @param all The all list
	 * @param pageSize 
	 * @param pageNum
	 * @return sub list
	 */
	public static List<String> cut(List<String> all,int pageSize,int pageNum){
		if( all == null || all.size() == 0 ) 	return null;
		
		if( pageSize <= 0 ) 
			throw new IllegalArgumentException("The page size can not be equal or lesser than zero");
		
		if( pageNum <= 0 )
			throw new IllegalArgumentException("The page num can not be equal or lesser than zero");
		
		int totalHits = all.size();
		int totalPages = totalHits/pageSize + 1;
		
		if( pageNum > totalPages ) pageNum = totalPages;
		
		List<String> singlePage = new ArrayList<String>();
		for( int i = 0; i < pageSize; i++ ){
			
			int index = pageSize*(pageNum-1) + i;
			
			if( index > all.size() - 1 ) break;
			
			singlePage.add(all.get(index));				
		}
		
		return singlePage;
	}

}
