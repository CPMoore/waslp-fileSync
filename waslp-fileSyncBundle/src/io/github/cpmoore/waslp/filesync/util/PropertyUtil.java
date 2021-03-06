package io.github.cpmoore.waslp.filesync.util;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class PropertyUtil {
   public static String[] getStringArray(Dictionary<String,?> properties,String key) {
	   Object val=properties.get(key);
	   if(val==null) {
		   return new String[] {};
	   }
	   if(val instanceof String) {
		   val= new String[] {(String)val};
	   }
	   if(val instanceof String[] ) {
		   return (String[]) val;
	   }
	   throw new IllegalArgumentException("Value for key '"+key+"' is not a String or String[]");
   }
   public static Integer getInteger(Dictionary<String,?> properties,String key,Integer defaultVal) {
	   Object val=properties.get(key);
	   if(val==null) {
		   return defaultVal;
	   }
	   
	   if(val instanceof Integer) {
		   return (Integer)val;
	   }
	   try {
	   if(val instanceof String ) {
		   return Integer.parseInt((String)val);
	   }}catch(Exception e) {};
	   throw new IllegalArgumentException("Value for key '"+key+"' is not a Integer");
   }
   public static Boolean  getBoolean(Dictionary<String,?> properties,String key,Boolean defaultVal) {
	   Object val=properties.get(key);
	   if(val==null) {
		   return defaultVal;
	   }
	   
	   if(val instanceof Boolean) {
		   return (Boolean)val;
	   }
	   try {
	   if(val instanceof String ) {
		   return Boolean.parseBoolean(((String)val).toLowerCase());
	   }}catch(Exception e) {};
	   throw new IllegalArgumentException("Value for key '"+key+"' is not a Boolean");
   }
   public static void mergeAll(HashMap<String,HashSet<String>> target,HashMap<String,HashSet<String>> source,HashMap<String,HashSet<String>> hashSetToRemoveFrom) {
	   
	   for(String s:source.keySet()) {
		   if (!target.containsKey(s)) {
			   target.put(s, source.get(s));
			   continue;
		   }
		   if(hashSetToRemoveFrom==null) {
			    target.get(s).addAll(source.get(s));
			    continue;
			}
		    Set<String> toAdd=source.get(s);
		    for(String directory:toAdd) {
				target.get(s).add(directory);
				 Set<String> set=hashSetToRemoveFrom.get(s);
				 for(String file:new HashSet<String>(set)) {
					   if(file.startsWith(directory+"/")) {
						   set.remove(file);
					   }
			      } 
		     }   
	    }	   
   }
   public static void mergeAll(HashMap<String,HashSet<String>> target,HashMap<String,HashSet<String>> source) {
       mergeAll(target,source,null);
   } 
}
