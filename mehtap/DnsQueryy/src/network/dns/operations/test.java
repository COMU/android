package network.dns.operations;

import network.dns.operations.DNSControl;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DNSControl dns_control = new DNSControl();
		Boolean control = dns_control.validate("test@gmal.com");
		if (control)
		{
			System.out.println("ok");
			
		}

	}

}
