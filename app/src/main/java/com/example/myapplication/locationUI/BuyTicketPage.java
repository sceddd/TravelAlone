package com.example.myapplication.locationUI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.sendNotification.LeaveNotification;
import com.example.myapplication.sendNotification.RatingNotification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class BuyTicketPage extends AppCompatActivity {
    // TODO: setting time when user return;
    // TODO: setting another notification when user go
    Button buy_ticket;
    EditText email_address_pt,username,phoneNumber;
    int locationID;
    NumberPicker day_pick,year_pick,month_pick;
    TextView locationName;
    ConnSQL c = new ConnSQL();
    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_ticket_page);
        createNotificationChannel();
        username = findViewById(R.id.user_name_pt);
        phoneNumber = findViewById(R.id.phone_number_pt);
        buy_ticket = findViewById(R.id.buy_button);
        email_address_pt = findViewById(R.id.email_address_pt);
        day_pick = findViewById(R.id.day_picker);
        month_pick = findViewById(R.id.month_picker);
        year_pick = findViewById(R.id.year_picker);
        locationName = findViewById(R.id.location_name_bt);

        // set send Email for the user the information of the ticket
        locationName.setText(getIntent().getStringExtra("LOCATION"));
        locationID = getIntent().getIntExtra("LocationID",0);
        buy_ticket.setOnClickListener(this::onBuyClick);

        year_pick.setMinValue(LocalDate.now().getYear());
        year_pick.setMaxValue(LocalDate.now().getYear()+10);

        month_pick.setMinValue(1);
        month_pick.setMaxValue(12);

        day_pick.setMinValue(1);
        day_pick.setMaxValue(YearMonth.of(year_pick.getValue(),month_pick.getValue()).lengthOfMonth());

        resetBaseDateVariable();

        year_pick.setOnValueChangedListener((picker,oldVal,newVal)-> settingBaseDateVariable());
        month_pick.setOnValueChangedListener((picker, oldVal, newVal) -> {
                    settingBaseDateVariable();
            day_pick.setMaxValue(YearMonth.of(year_pick.getValue(),month_pick.getValue()).lengthOfMonth());
                }
        );
        day_pick.setOnValueChangedListener((picker, oldVal, newVal)-> {
            settingBaseDateVariable();
        });
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    public void onBuyClick(View v){
        int userID = 0;
        try {
            ResultSet rs = c.getSetWithoutEle("USERS","userID","phoneNumber = '"+phoneNumber.getText()+"' and email = '"+ email_address_pt.getText()+"'");
            userID = rs.getInt("userID");
        } catch (SQLException e){
            c.executeQ("insert into Users (fullName,phoneNumber,email) values ('"+username.getText()+"','"+phoneNumber.getText()+"','"+email_address_pt.getText()+"')");
            try{
                ResultSet rs = c.getFullSet("USERS");
                rs.last();
                userID = rs.getInt("userID");
            }catch (Exception ex){
                Log.d("zzzzzzz", "onBuyClick: ");
            }
            Log.d("111111111111", "onBuyClick: "+e);
        }
        String historyQue = "insert into HistoryBook (userID,locationID,visitDay,returnDay) values ('"+userID+"','"+locationID+"','2002/12/01','2002/12/02')";
        c.executeQ(historyQue);
        //  send notification message to user when the day come
        Intent goneDay = new Intent(BuyTicketPage.this, LeaveNotification.class);
        PendingIntent goneDayNotiIntent = PendingIntent.getBroadcast(BuyTicketPage.this,0,goneDay,0);
        // send notification message to user when day return from the trip
        Intent returnDay = new Intent(BuyTicketPage.this, RatingNotification.class);
        returnDay.putExtra("userID", userID);
        returnDay.putExtra("locationID", locationID);
        PendingIntent returnDayNotiIntent = PendingIntent.getBroadcast(BuyTicketPage.this,0,returnDay,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick + getTimeUserReturn(),goneDayNotiIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 
                timeAtButtonClick + getTimeUserReturn(),returnDayNotiIntent);

    }
    private long getTimeUserReturn(){
        return 1000 * 10;
    }

    private void createNotificationChannel(){       // setting up notification channel to let user setting their notification
        CharSequence name = "RatingApp";
        String description = "Channel for user";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("onTripReturn", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private void settingDateTime(){

    }
    private void settingBaseDateVariable(){
        if (LocalDate.now().compareTo(LocalDate.of(year_pick.getValue(),month_pick.getValue(),day_pick.getValue()))>0)
            resetBaseDateVariable();
    }
    private void resetBaseDateVariable(){
        day_pick.setValue(LocalDate.now().getDayOfMonth());
        month_pick.setValue(LocalDate.now().getMonthValue());
        year_pick.setValue(LocalDate.now().getYear());

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