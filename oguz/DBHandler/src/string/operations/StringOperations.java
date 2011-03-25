package string.operations;

public class StringOperations {
	
	public String join(String[] s, String delimiter) {
	    StringBuffer buffer = new StringBuffer();
	    
	    for (int i=0; i< s.length; i++)
	    {
	    	buffer.append(s[i]);
	    	buffer.append(delimiter);
	    }
	    return buffer.toString();
	}


}
