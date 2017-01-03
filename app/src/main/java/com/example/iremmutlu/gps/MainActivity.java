package com.example.iremmutlu.gps;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private GoogleMap googleMap;


    ArrayList<LatLng> points;
    ProgressDialog pDialog;
    LatLng latLng;
    String eLx,eLy,cLx,cLy;
    List<LatLng> polyz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        points = new ArrayList<>();
        final TextView t =  (TextView)findViewById(R.id.textView);

        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(), "Üzgünüm map oluşturulamadı", Toast.LENGTH_SHORT).show();
            }

            /* location’ı açılması. */
            googleMap.setMyLocationEnabled(true);

            /* konumumuz değiştikçe otomatik konumu gösterebilmesi  */
            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    // konum bilgilerini stinge çevirme
                    cLx= String.valueOf(latitude);
                    cLy= String.valueOf(longitude);

                    // Konum ile noktalar arasındaki mesafeler                           //float'ın formatı
                    float distance1 , distance2 ,distance3 ,distance4 , distance5  = 0;  DecimalFormat df =new DecimalFormat("##.##");

                    Location currentLocation = new Location("crntlocation"); currentLocation.setLatitude(latitude);currentLocation.setLongitude(longitude);

                     latLng = new LatLng(latitude, longitude);
                    points.add(latLng);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    googleMap.setOnMyLocationChangeListener(null);


                    /* işaretçinin oluşturulması */
                    MarkerOptions markerPosition = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Konumum");
                    googleMap.addMarker(markerPosition);


                    /* 5 noktanın belirlenmesi */

                    // 1.nokta
                    //Konumla arasındaki mesafe

                    Location Location1 = new Location("newlocation");Location1.setLatitude(38.343180);Location1.setLongitude(38.272831);
                    distance1 = currentLocation.distanceTo(Location1) / 1000; String d1 = df.format(distance1);

                    MarkerOptions marker1 = new MarkerOptions().position(new LatLng(38.343180, 38.272831)).title("Beydağı Devlet Hastanesi").snippet(String.valueOf(d1) + " km");
                    marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(marker1);



                    /*  2.nokta  */
                    //Konumla arasındaki mesafe
                    Location Location2 = new Location("newlocation");Location2.setLatitude(38.337834);Location2.setLongitude(38.427582);
                    distance2 = currentLocation.distanceTo(Location2) / 1000; String d2 = df.format(distance2);

                    MarkerOptions marker2 = new MarkerOptions().position(new LatLng(38.337834, 38.427582)).title(" Turgut Özal Tıp Merkezi").snippet(String.valueOf(d2) + " km");
                    marker2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(marker2);



                    /*  3.nokta  */
                    //Konumla arasındaki mesafe
                    Location Location3 = new Location("newlocation");Location3.setLatitude(38.337753);Location3.setLongitude(38.246067);
                    distance3 = currentLocation.distanceTo(Location3) / 1000; String d3 = df.format(distance3);

                    MarkerOptions marker3 = new MarkerOptions().position(new LatLng( 38.337814, 38.246066)).title(" Özel Malatya Park Hospital").snippet(String.valueOf(d3) + " km");
                    marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(marker3);



                    /*  4.nokta  */
                    //Konumla arasındaki mesafe
                    Location Location4 = new Location("newlocation");Location4.setLatitude(38.346294);Location4.setLongitude(38.334461);
                    distance4 = currentLocation.distanceTo(Location4) / 1000; String d4 = df.format(distance4);

                    MarkerOptions marker4 = new MarkerOptions().position(new LatLng(38.346294, 38.334461)).title("Malatya Devlet Hastanesi Diyaliz Merkez").snippet(String.valueOf(d4) + " km");
                    marker4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(marker4);



                    /*  5.nokta   */
                    //Konumla arasındaki mesafe
                    Location Location5 = new Location("newlocation");Location5.setLatitude(38.348057);Location5.setLongitude(38.304802);
                    distance5 = currentLocation.distanceTo(Location5) / 1000; String d5 = df.format(distance5);

                    MarkerOptions marker5 = new MarkerOptions().position(new LatLng(38.348057, 38.304802)).title("Gözde Hastanesi").snippet(String.valueOf(d5) + " km");
                    marker5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    googleMap.addMarker(marker5);


                    /*  Minimum mesafeli konumun bulunması  */
                   Float x = Math.min(distance1,distance2);  Float y = Math.min(x,distance3);  Float z = Math.min(y,distance4); Float v = Math.min(z,distance5);
                    String minMesafe = df.format(v);
                    t.setText(String.valueOf("MİN MESAFELİ KONUM  " + minMesafe + "  KM"));


                    Location endLocation = new Location("crntlocation");

                    //  min mesafeli notayı bitiş konumu olarak atama işlemi
                    if (v == distance1) {endLocation.setLatitude(38.343180) ;endLocation.setLongitude(38.272831);}//Beydağı Devlet Hastanesi
                    else if (v == distance2) {endLocation.setLatitude(38.337834) ;endLocation.setLongitude(38.427582);}//Turgut Özal Tıp Merkezi
                    else if (v == distance3) {endLocation.setLatitude(38.337814) ;endLocation.setLongitude(38.246066);}//Özel Malatya Park Hospital
                    else if (v == distance4) {endLocation.setLatitude(38.346294) ;endLocation.setLongitude(38.334461);}//Malatya Devlet Hastanesi Diyaliz Merkez
                    else if (v == distance5){endLocation.setLatitude(38.348057) ;endLocation.setLongitude(38.304802);}//Gözde Hastanesi
                    else { Toast.makeText(getApplicationContext()," HESAPLMADA HATA " , Toast.LENGTH_SHORT).show();}

                    LatLng endLatLng = new LatLng(endLocation.getLatitude(), endLocation.getLongitude());
                    points.add(endLatLng);

                    // endLocation bilgilerinin string'e dönüştürülmesi
                    eLx = String.valueOf(endLocation.getLatitude());
                    eLy = String.valueOf(endLocation.getLongitude());

                    MarkerOptions endMarker = new MarkerOptions().position(new LatLng( endLocation.getLatitude(), endLocation.getLongitude() ) ).title(" GİDİLECEK ADRES ");
                    endMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    googleMap.addMarker(endMarker);
                    getDirection();
                }
            });

        }
    }


    class GetDirection extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading route. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            String stringUrl = "http://maps.googleapis.com/maps/api/directions/json?origin=" +cLx+","+cLy  + ",+latIng&destination=" +eLx+","+ eLy + ",+latIng&sensor=false&key=AIzaSyCIF-1hXz5V-SyJsI357ea2N5AOOCe6Bu8";
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection httpconn = (HttpURLConnection) url
                        .openConnection();
                if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(httpconn.getInputStream()),
                            8192);
                    String strLine = null;

                    while ((strLine = input.readLine()) != null) {
                        response.append(strLine);
                    }
                    input.close();
                }

                String jsonOutput = response.toString();

                JSONObject jsonObject = new JSONObject(jsonOutput);

                // routesArray contains ALL routes
                JSONArray routesArray = jsonObject.getJSONArray("routes");
                // Grab the first route
                JSONObject route = routesArray.getJSONObject(0);

                JSONObject poly = route.getJSONObject("overview_polyline");
                String polyline = poly.getString("points");
                polyz = decodePoly(polyline);

            } catch (Exception e) {

            }

            return null;

        }


    }

    /* Method to decode polyline points */
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    //google api sinden 2 konum polyline aliniyor
    private void getDirection() {
        final OkHttpClient client = new OkHttpClient();
        client.retryOnConnectionFailure();

        //maps'e başlangıç ve bitiş noktalarının gönderilmesi
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + cLx + "," + cLy + "&destination=" + eLx + "," + eLy + "&mode=driving&key=AIzaSyCIF-1hXz5V-SyJsI357ea2N5AOOCe6Bu8";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity", "onFailure() called with: " + "request = [" + "], e = [" + e + "]");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // google yanit geliyor , json olarak
                String resp = response.body().string();
                if (!response.isSuccessful() || resp.contains("false")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Yol Bilgisi Alinamadi", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // google json stringini DirectionResult nesnesine ceviriyoz
                    DirectionResults directionResults = new Gson().fromJson(resp, DirectionResults.class);
                    makeDirectionPolyline(directionResults);
                }
            }
        });
    }

        void makeDirectionPolyline(DirectionResults directionResults) {

            ArrayList<LatLng> routelist = new ArrayList<>();
            Route routeA = null;
            if (directionResults.getRoutes().size() > 0) {
                ArrayList<LatLng> decodelist;
                routeA = directionResults.getRoutes().get(0);
                if (routeA.getLegs().size() > 0) {
                    String polyline;
                    polyline = routeA.getOverviewPolyline().getPoints();
                    decodelist = RouteDecode.decodePoly(polyline);
                    routelist.addAll(decodelist);
                }
            }

            // polyline olustyr
            PolylineOptions rectLine = new PolylineOptions().width(5).zIndex(30).color(Color.RED);
            if (routelist.size() > 0) {
                for (int i = 0; i < routelist.size(); i++) {
                    rectLine.add(routelist.get(i));
                }
                showDirection(rectLine);
            }
        }


    private void showDirection(final PolylineOptions rectLine) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                googleMap.addPolyline(rectLine);
            }
        });
    }

}

