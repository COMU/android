
public class Test {
	public static void main(String[] args) {
		
		Geocoder geocoder = new Geocoder();
		
		
		/*Address address = new Address("69214", "Eppelheim", "DE");
		Coordinates coordinates = geocoder.geocode(address);*/
		Coordinates coordinates=new Coordinates("38","36");
		Address address=geocoder.geocode(coordinates);
		System.out.println(address.getCity()+","+address.getCountry());
	}
}