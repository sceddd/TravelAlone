package com.example.myapplication.locationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.jsonplaceholder.LocationCities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BuyTicketPage extends AppCompatActivity {
    Button buy_ticket;
    EditText email_address_pt;
    TextView locationName;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_ticket_page);
        buy_ticket = findViewById(R.id.buy_button);
        email_address_pt = findViewById(R.id.email_address_pt);
        locationName = findViewById(R.id.location_name_bt);
        // set send Email for the user the information of the ticket
        locationName.setText(getIntent().getStringExtra("LOCATION"));
        buy_ticket.setOnClickListener(this::onBuyClick);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                // add other factories here, if needed.
                .build();
        LocationCities locationCities = retrofit.create(LocationCities.class);
    }

    public void html2json(String html){
        Document document = Jsoup.parse(html);
        Element table = document.select("table").first();
        String arrayName = table.select("th").first().text();
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        Elements ttls = table.getElementsByClass("ttl");
        Elements nfos = table.getElementsByClass("nfo");
        try {
            JSONObject jo = new JSONObject();
            for (int i = 0, l = ttls.size(); i < l; i++) {
                String key = ttls.get(i).text();
                String value = nfos.get(i).text();
                jo.put(key, value);
            }
            jsonArr.put(jo);
            jsonObj.put(arrayName, jsonArr);
            System.out.println(jsonObj.toString());
        }catch (JSONException e){
            Log.d("111111111111111", "html2json: ");
        }
    }
    public void onBuyClick(View v){

    }
    public void sendMail(){
        //        final String email_address = "testaccfbp@gmail.com";
//        final String password = "021002ht";
//        final String body = "Your ticket is abcxyz";
//
//        if (!email_address_pt.getText().toString().isEmpty()){
//            Properties props = new Properties();
//            props.put("mail.smtp.auth","true");
//            props.put("mail.smtp.starttls.enable","true");
//            props.put("mail.smtp.host","smtp.gmail.com");
//            props.put("mail.smtp.port","587");
//            Session session = Session.getInstance(props, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(email_address,password);
//                }
//            });
//            try {
//                Message message = new MimeMessage(session);
//                message.setFrom(new InternetAddress(email_address));
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_address_pt.getText().toString()));
//                message.setSubject("Travel Alone");
//                message.setText(body);
//            }catch (MessagingException e){
//                Log.d("send mail....", "onClick: "+e);
//            }
//        }
    }
}