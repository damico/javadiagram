package org.jdamico.javadiagram.creator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.jdamico.javadiagram.config.Constants;
import org.jdamico.javadiagram.dataobject.RetanguleBox;

public class StructureCreator {
	
	private ArrayList<RetanguleBox> retArray = new ArrayList<RetanguleBox>();
	
	
	
	public ArrayList<RetanguleBox> convert(String stringSet){
		/* 
		 * a = {b,c}
		 */
		
		ArrayList<String> setLines = new ArrayList<String>();
		
		int bpos = 0;
		for(int i=0; i<stringSet.length(); i++){
			if(stringSet.charAt(i)=='}'){
				String newLine = stringSet.substring(bpos,i+1);
				setLines.add(newLine);
				bpos = i+1;
			}
		}
		
		Map<String, String[]> sets = new TreeMap<String, String[]>();
		
		for(int i = 0; i < setLines.size(); i++){
			StringTokenizer st = new StringTokenizer(setLines.get(i), "=");
			while(st.hasMoreTokens()){
				String e = (String) st.nextElement();
				String[] v = String2Array((String) st.nextElement());
				sets.put(e.trim(),v);
			}
		}
		
		
		

		
		
		Set<String> ks = sets.keySet();
		Iterator<String> k_it = ks.iterator();
		
		int row = 0;
		while(k_it.hasNext()){
			String element = k_it.next(); 
			retArray.add(new RetanguleBox(Constants.DEFAULT_BOX_HEIGTH, Constants.DEFAULT_BOX_LENGTH, element, row, -1, row, 0));
			row++;
		}
		
		Collection<String[]> setsCollection = sets.values();
		Iterator<String[]> it = setsCollection.iterator();
		
		
		int counter = 0;
		
		
		int add = 0;
		while(it.hasNext()){
			String[] v = it.next();
			
			if(v!=null){
				for(int i=0; i<v.length; i++){
					
					int id = getRetanguleBoxByCaption(v[i]);
					if(id>-1){
						RetanguleBox element = retArray.get(id);
						element.setSource(counter+add);
						element.setCol(i);
						element.setRow(counter+1);

					}
										
				}

				counter++;
			}else{
				add++;
			}
				
				
			
			
			
		}
		
		return retArray;

	}
	
	private String[] String2Array(String element) {
		String[] array = null;
		element = element.trim();
		if(element.equals("{}")){
			
			array = null;
		}
		else{
			element = element.substring(1,element.length()-1);
			StringTokenizer st = new StringTokenizer(element,",");
			array = new String[st.countTokens()];
			int index = 0;
			while(st.hasMoreTokens()){
				array[index] = (String) st.nextElement();
				index++;
			}
		}
		return array;
	}

	private int getRetanguleBoxByCaption(String caption) {

		
		int id = -1;
		for(int i = 0; i < retArray.size(); i++){
			if(retArray.get(i).getCaption().equals(caption)){
				id = i;
			}
		}
		return id;
	}

}
