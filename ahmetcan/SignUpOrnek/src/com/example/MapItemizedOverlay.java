package com.example;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapItemizedOverlay extends ItemizedOverlay {
	private ArrayList<OverlayItem> overlays ;
	private Context context;
	public MapItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		overlays = new ArrayList<OverlayItem>();
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays.get(i);
		
	}

	@Override
	public int size() {
		return overlays.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    overlays.add(overlay);
	    populate();
	}
	
	public boolean onTap(int index){
		return true;
	}
	

}
