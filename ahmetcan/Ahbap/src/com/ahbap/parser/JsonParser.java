package com.ahbap.parser;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahbap.City;
import com.ahbap.Country;
import com.ahbap.Location;
import com.ahbap.User;
import com.ahbap.UserDetail;

public class JsonParser {
	private JSONObject jsonObject;
	private User user;
	private UserDetail userDetail;
	private Location location;

	public User returnUser(String userJsonStr) {
		try {
			jsonObject = new JSONObject(userJsonStr);
			setUser();
		} catch (JSONException e) {
			// TODO
			return null;
		}
		return user;
	}

	private void setUser() throws JSONException {
		user.setName(jsonObject.getString("ad"));
		user.setLastname(jsonObject.getString("soyad"));
		user.setEmail(jsonObject.getString("email"));
		user.setImageId(jsonObject.getString("resim_id"));
		user.setPassword(jsonObject.getString("parola"));
		user.setLocation(setlocation());
		user.setUserdetail(setUserDetail());
	}

	private UserDetail setUserDetail() throws JSONException {
		UserDetail userDetail = new UserDetail(
				jsonObject.getString("kullanici_detay_id"));
		return userDetail;
	}

	private Location setlocation() throws JSONException {
		Location location = new Location(jsonObject.getString("konum_id"));
		return location;
	}

	public Location returnLocation(String locationJsonStr,
			String countryJsonStr, String cityJsonStr) {
		Location location = new Location();
		try {
			JSONObject locationJson = new JSONObject(locationJsonStr);

			location.setLatitude(Double.parseDouble(locationJson
					.getString("boylam")));
			location.setLongitude(Double.parseDouble(locationJson
					.getString("enlem")));

			location.setCity(returnCity(cityJsonStr));
			location.setCountry(returnCountry(countryJsonStr));

		} catch (JSONException e) {
			return null;
		}
		
		return location;
	}

	public City returnCity(String cityJsonStr) throws JSONException {
		JSONObject cityJson = new JSONObject(cityJsonStr);
		return new City(cityJson.getString("adi"));
	}

	public Country returnCountry(String countryJsonStr) throws JSONException {
		JSONObject countryJson = new JSONObject(countryJsonStr);
		return new Country(countryJson.getString("adi"));
	}

	/*public void returnUlkeler(String ulkeler, List<String> ulke) {
		try {
			JSONArray jsonUlke = new JSONArray(ulkeler);

			for (int i = 0; i < jsonUlke.length(); i++) {
				ulke.add(jsonUlke.getJSONObject(i).getString("adi"));
			}
		} catch (Exception e) {

		}

	}

	public void returnSehirler(String sehirler, List<String> sehir) {

		try {
			JSONArray jsonSehir = new JSONArray(sehirler);

			for (int i = 0; i < jsonSehir.length(); i++) {
				sehir.add(jsonSehir.getJSONObject(i).getString("adi"));
			}
		} catch (Exception e) {

		}
	}

	public void returnMeslekler(String meslekler, List<String> meslek) {
		try {
			JSONArray jsonMeslek = new JSONArray(meslekler);
			for (int i = 0; i < jsonMeslek.length(); i++) {
				meslek.add(jsonMeslek.getJSONObject(i).getString("adi"));
			}
		} catch (Exception e) {

		}

	}

	public void returnAranan(String arananlar, List<Kullanici> aranan) {

		try {
			JSONArray jsonAranan = new JSONArray(arananlar);
			for (int i = 0; i < jsonAranan.length(); i++) {
				Kullanici kullanici = new Kullanici();
				kullanici.setAdi(jsonAranan.getJSONObject(i).getString("ad"));
				kullanici.setSoyad(jsonAranan.getJSONObject(i).getString(
						"soyad"));
				kullanici.setEmail(jsonAranan.getJSONObject(i).getString(
						"email"));
				kullanici.setResimId(jsonAranan.getJSONObject(i).getString(
						"resim_id"));
				kullanici.setDetayId(jsonAranan.getJSONObject(i).getString(
						"kullanici_detaylari_id"));
				kullanici.setKonumId(jsonAranan.getJSONObject(i).getString(
						"konum_id"));
				kullanici.setParola(jsonAranan.getJSONObject(i).getString(
						"parola"));
				kullanici.setDogumTarihi(jsonAranan.getJSONObject(i).getString(
						"dogum_tarihi"));
				kullanici.setDetayId(jsonAranan.getJSONObject(i).getString(
						"dogrulama_id"));
				aranan.add(kullanici);

			}
		} catch (Exception e) {
		}

	}*/

}
