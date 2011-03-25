package network.dns.operations;

import java.util.*;
import javax.naming.directory.*;


public class DNSControl {
	
	
	private String get_domain(String mail_addres)
	{
		String domain = mail_addres.split("@")[1];
		return domain;
		
	}
	
	public Boolean validate(String mail_addres)
	{
		String domain = get_domain(mail_addres);
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		env.put("java.naming.provider.url", "dns:");
		//env.put("dizi1", "dizi1");
		DirContext ctx;
		try 
		{
			ctx = new InitialDirContext(env);
		
			Attributes attrs = ctx.getAttributes(domain, new String[] {"mx"});
			Attribute attr = attrs.get("mx");
			if (attr != null)
			{
				System.out.println(attr);
				ctx.close();
				return true;
			}
			else
			{
				ctx.close();
				return false;
			}
		}
		catch (Exception error) 
		{
			return false;
		}	
	}
}
