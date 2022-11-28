package com.example.myapplication.locationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyTicketPage extends AppCompatActivity {
    Button buy_ticket;
    EditText email_address_pt,username,phoneNumber;
    TextView locationName;
    ConnSQL c = new ConnSQL();
    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_ticket_page);
        username = findViewById(R.id.user_name_pt);
        phoneNumber = findViewById(R.id.phone_number_pt);
        buy_ticket = findViewById(R.id.buy_button);
        email_address_pt = findViewById(R.id.email_address_pt);
        locationName = findViewById(R.id.location_name_bt);
        // set send Email for the user the information of the ticket
        locationName.setText(getIntent().getStringExtra("LOCATION"));
        buy_ticket.setOnClickListener(this::onBuyClick);
    }

    public void onBuyClick(View v){
        String query = "insert into Users (fullName,phoneNumber,email) values ('"+username.getText()+"','"+phoneNumber.getText()+"','"+email_address_pt.getText()+"')";
        ResultSet rs = c.getSetWithoutEle("USERS","userID","phoneNumber = '"+phoneNumber.getText()+"' and email = '"+ email_address_pt.getText()+"'");
        try {
            while(rs.next())
                Log.d("111111111", "onBuyClick: "+rs.getString("userID"));
        } catch (SQLException e){
            Log.d("111111111111", "onBuyClick: "+e);
        }
        c.add(query);
    }


}










//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://en.wikipedia.org/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                // add other factories here, if needed.
//                .build();
//        LocationCities locationCities = retrofit.create(LocationCities.class);
//    public void sendMail(){
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
//    }

//    public void html2json(String html){
//        Document doc = Jsoup.parse(html);
//        Element table = doc.select("table").first();
//        Elements locationName = table.select("td");
//        ArrayList<String> locationS = new ArrayList<>();
//        for (int i=0;i<locationName.size()-1;i++){
//            if(locationName.get(i).text().equals("\\n"))
//            {
//                locationS.add(locationName.get(i+1).text());
//                i++;
//            }
//        }
//        Log.d("1111111111", "html2json: "+locationS);
//    }