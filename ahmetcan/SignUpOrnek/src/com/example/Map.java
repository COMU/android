package com.example;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Map extends MapActivity{
	private List<Kullanici> bulunanlar; 
	private List<Konum> konumlar;
	private WebService service;
	private MapItemizedOverlay itemizedOverlay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    
	    service = new WebService(getApplicationContext());
	    
	    Bundle extras = getIntent().getExtras();
	    String bulunanlar_json = extras.getString("bulunanlar");
	    bulunanlar = new ArrayList<Kullanici>();
	    JsonParser.returnAranan(bulunanlar_json, bulunanlar);
	    
	    konumlar = new ArrayList<Konum>();
	    getKonumlar();
	    
	    
	    MapView map = (MapView)findViewById(R.id.mapview);
	    map.setBuiltInZoomControls(true);
	    
	    List<Overlay> mapOverlays = map.getOverlays();
	    Drawable drawable = getResources().getDrawable(R.drawable.icon);
	    itemizedOverlay = new MapItemizedOverlay(drawable, getApplicationContext());
	    
	    addOverlays();
	    mapOverlays.add(itemizedOverlay);
	    
	}
	public void addOverlays(){
		for (int i=0;i<konumlar.size();i++){
			Double latitude = konumlar.get(i).getEnlem() * 1e6;
			Double longitude = konumlar.get(i).getBoylam() * 1e6;
			GeoPoint g = new GeoPoint(latitude.intValue(), longitude.intValue() );
			OverlayItem overlayitem = new OverlayItem(g, bulunanlar.get(i).getAdi() + " " 
					+ bulunanlar.get(i).getSoyad(), "Daha neler?");
			itemizedOverlay.addOverlay(overlayitem);
		}
	}
	public void getKonumlar(){
		for(int i=0;i<bulunanlar.size();i++){
			Konum k ;
			String konumStr = service.getKonum(bulunanlar.get(i).getEmail(),
					bulunanlar.get(i).getParola(), bulunanlar.get(i).getDetayId());
			String ulkeStr = service.getUlke(bulunanlar.get(i).getEmail(),
					bulunanlar.get(i).getParola(), bulunanlar.get(i).getDetayId());
			String sehirStr = service.getSehir(bulunanlar.get(i).getEmail(),
					bulunanlar.get(i).getParola(), bulunanlar.get(i).getDetayId());
			
			k = JsonParser.returnKonum(konumStr, ulkeStr, sehirStr);
			konumlar.add(k);
		}
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
