package test.example.com;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class RestTest extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String LOGIN_URL = "http://10.0.2.2:8000/api/meslek";
        
        RestClient client = new RestClient(LOGIN_URL);
        client.AddParam("meslek_adi", "Tesisatçı");
         
        try {
            client.Execute(RestClient.RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        String response = client.getResponse(); // end callWebService()
        Toast.makeText(this, response, Toast.LENGTH_LONG).show();
    }
}
