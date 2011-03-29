package string.operations;

public class StringOperations {
	
	public String joinField(String[] s, String delimiter) {
	    StringBuffer buffer = new StringBuffer();
	    
	    for (int i=0; i< s.length; i++)
	    {
	    	buffer.append(s[i]);
	    	buffer.append(delimiter);
	    }
	    buffer.deleteCharAt(buffer.length()-1);
	    return buffer.toString();
	}
	public String joinValue(String[] s, String delimiter) {
	    StringBuffer buffer = new StringBuffer();
	    
	    for (int i=0; i< s.length; i++)
	    {
	    	buffer.append("\"" + s[i] + "\"");
	    	buffer.append(delimiter);
	    }
	    buffer.deleteCharAt(buffer.length()-1);
	    return buffer.toString();
	}


}
