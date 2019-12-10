package kau.msproject.searchaed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.sql.DriverManager.println;

public class GeoActivity extends AppCompatActivity {

    Double lat;
    Double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        Intent intent = getIntent();
        lat = intent.getExtras().getDouble("lat");
        lon = intent.getExtras().getDouble("lon");

        System.out.println("geolat" + lat);
        System.out.println("geolon" + lon);
        MyAsyncTask mProcessTask = new MyAsyncTask();
        mProcessTask.execute();
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog = new ProgressDialog(GeoActivity.this);

        //OkHttp 객체생성
        OkHttpClient client = new OkHttpClient();
        String result_val = "null";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String lat_st = lat.toString();
            String lon_st = lon.toString();

            //lat_st = "127.5941234";
            //lon_st = "37.4739193";

            //주소 -> 좌표로 변환 (geocoding)
            //HttpUrl.Builder urlBuilder = HttpUrl.parse("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=강남구 자곡로 101").newBuilder();
            //좌표 -> 주소로 변환 (Reverse geocoding)
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=" + lon_st + "," + lat_st + "&output=json&orders=roadaddr").newBuilder();
            urlBuilder.addQueryParameter("X-NCP-APIGW-API-KEY-ID", "fbjwqr85b8");
            urlBuilder.addQueryParameter("X-NCP-APIGW-API-KEY", "QXU2NDyV3x1vyqHeOFaiNAffka71Ybw6ZxAIzjmp");
            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            try {
                //주소 -> 좌표로 변환 (geocoding)
                /*JSONObject jObject = new JSONObject(result);
                String string_adr = jObject.getString("addresses");

                JSONArray jArray = new JSONArray(string_adr);
                JSONObject subJObject = jArray.getJSONObject(0);

                String string_x = subJObject.getString("x");
                String string_y = subJObject.getString("y");

                double db_x = Double.parseDouble(string_x);
                double db_y = Double.parseDouble(string_y);

                Log.d("Your coordinate", string_x + ", " + string_y);*/

                // result 큰 덩어리 가져오기
                JSONObject jObject = new JSONObject(result);
                String string_adr = jObject.getString("results");

                // 대괄호 벗기기
                JSONArray jArray = new JSONArray(string_adr);
                JSONObject subJObject = jArray.getJSONObject(0);

                //------------------------------------------------------------------

                // region 덩어리 가져오기
                String string_region = subJObject.getString("region");
                JSONObject regionJObject = new JSONObject(string_region);

                // region - area1 - name 가져오기
                String string_area1 = regionJObject.getString("area1");
                JSONObject jObject_area1 = new JSONObject(string_area1);
                String adr1 = jObject_area1.getString("name");

                // region - area2 - name 가져오기
                String string_area2 = regionJObject.getString("area2");
                JSONObject jObject_area2 = new JSONObject(string_area2);
                String adr2 = jObject_area2.getString("name");

                //------------------------------------------------------------------

                // land 덩어리 가져오기
                String string_land = subJObject.getString("land");
                JSONObject landJObject = new JSONObject(string_land);

                // land - name 가져오기
                String adr3 = landJObject.getString("name");

                // land - number1 가져오기
                String adr4 = landJObject.getString("number1");

                Log.d("Your location", adr1 + " " + adr2 + " " + adr3 + " " + adr4);

                result_val = adr1 + " " + adr2 + " " + adr3 + " " + adr4;
            } catch (Exception ex) {
                Log.d("Result:", "You can't read JSON.");
                result_val = "Geocoding에 실패했습니다.";
            }
            Intent resintent = new Intent(getApplicationContext(), EmergencyActivity2.class);
            resintent.putExtra("address", result_val);
            startActivity(resintent);
        }
    }
}
