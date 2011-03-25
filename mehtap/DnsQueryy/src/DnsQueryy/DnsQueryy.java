package DnsQueryy;




import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;
@SuppressWarnings("unused")
public class DnsQueryy
{
/**
* args[0] domain
* args[1] query type
*/
public static void main(String args[])
throws Exception
{   String dizi[]=new String[24];
    dizi[0]="mehtapgundogan@gmailll.com";
    	// System.out.println("bir mail adresi giriniz:");
    	 
    	 
    	 String dizi1=dizi[0].split("@")[1];

    	 
    	   System.out.println(dizi1);
    	   
//if(dizi1.length() < 2){
//System.err.println("java DnsQuery [domain] [query-type]");
//System.exit(1);
//}
Hashtable<String, String> env = new Hashtable<String, String>();
env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
env.put("java.naming.provider.url", "dns:");
//env.put("dizi1", "dizi1");
DirContext ctx = new InitialDirContext(env);
try{
Attributes attrs = ctx.getAttributes(dizi1, new String[] {"mx"});
Attribute attr = attrs.get("mx");
if (attr != null)
{
	System.out.println(attr);
}
}
finally{
ctx.close();
}
}
}
