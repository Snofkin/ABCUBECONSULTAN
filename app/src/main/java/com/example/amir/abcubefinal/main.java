package com.example.amir.abcubefinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir on 5/7/2017.
 */

public class main extends AppCompatActivity {

    //personal details
    EditText Firstname, Lastname, Country, Personal_Email, Phone, Course, Nationality, Citizenship, Passport_no, Visa_no, Visaexpiry;
    RadioButton Gender;
    Spinner MartialStatus;
    EditText Dob;
    DatePicker picker;


    //educational background
    EditText Schoolname, Schoolmarks, Schoolyear,
            Highschoolname, Highschoolmarks, Highschoolyear,
            Bachelorname, Bachelormarks, Bacheloryear,
            Mastername, Mastermarks, Masteryear;


    //emergency contact
    EditText Name, Email, Relationship, Address, Phone_no;

    //English Language
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;


    Button ok;
    String server_url = "http://192.168.0.126/adcube/emergency.php";
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
//personal details
        Firstname = (EditText) findViewById(R.id.firstname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Country = (EditText) findViewById(R.id.country);
        Dob = (EditText) findViewById(R.id.dob);
        picker = (DatePicker) findViewById(R.id.picker);
        Personal_Email = (EditText) findViewById(R.id.emailaddress);
        Phone = (EditText) findViewById(R.id.mobile);
        Course = (EditText) findViewById(R.id.course);
        Nationality = (EditText) findViewById(R.id.nationality);
        picker = (DatePicker) findViewById(R.id.picker);
        MartialStatus = (Spinner) findViewById(R.id.spinner);
        Citizenship = (EditText) findViewById(R.id.citizenship);
        Passport_no = (EditText) findViewById(R.id.pass_no);
        Visa_no = (EditText) findViewById(R.id.visa_no);
        Visaexpiry = (EditText) findViewById(R.id.visa_expiry);


        //educational background
        Schoolname = (EditText)

                findViewById(R.id.schoolname);

        Schoolmarks = (EditText)

                findViewById(R.id.schoolmarks);

        Schoolyear = (EditText)

                findViewById(R.id.schoolaward);

        Highschoolname = (EditText)

                findViewById(R.id.highschoolname);

        Highschoolmarks = (EditText)

                findViewById(R.id.highschoolmarks);

        Highschoolyear = (EditText)

                findViewById(R.id.highscchoolaward);

        Bachelorname = (EditText)

                findViewById(R.id.bachelorname);

        Bachelormarks = (EditText)

                findViewById(R.id.bachelormarks);

        Bacheloryear = (EditText)

                findViewById(R.id.bacheloryear);

        Mastername = (EditText)

                findViewById(R.id.mastername);

        Mastermarks = (EditText)

                findViewById(R.id.mastermarks);

        Masteryear = (EditText)

                findViewById(R.id.masteryear);

        builder = new AlertDialog.Builder(main.this);

        //eergency ccontact
        Name = (EditText)

                findViewById(R.id.contactname);

        Email = (EditText)

                findViewById(R.id.email);

        Relationship = (EditText)

                findViewById(R.id.relation);

        Phone_no = (EditText)

                findViewById(R.id.phone);

        Address = (EditText)

                findViewById(R.id.address);

        //english language
        Testname = (EditText)

                findViewById(R.id.testname);

        Testdate = (EditText)

                findViewById(R.id.testdate);

        Testreport = (EditText)

                findViewById(R.id.testreport);

        Overallresult = (EditText)

                findViewById(R.id.overallresult);

        Reading = (EditText)

                findViewById(R.id.reading);

        Writing = (EditText)

                findViewById(R.id.writing);

        Listening = (EditText)

                findViewById(R.id.listening);

        Speaking = (EditText)

                findViewById(R.id.speaking);

        builder = new AlertDialog.Builder(main.this);
        Dob.setText(getCurrentDate());

        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dob.setText(getCurrentDate());
            }
        });
    }
        public String getCurrentDate () {
            StringBuilder builder = new StringBuilder();
            builder.append("Current Date: ");
            builder.append((picker.getMonth() + 1) + "/");

            builder.append(picker.getDayOfMonth() + "/");
            builder.append(picker.getYear());
            return builder.toString();
        }

    }


/**
 * ok.setOnClickListener(new View.OnClickListener() {
 *
 * @Override public void onClick(View view) {
 * <p>
 * <p>
 * //english test
 * final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking;
 * testname = Testname.getText().toString();
 * testreport = Testreport.getText().toString();
 * overallresult = Overallresult.getText().toString();
 * reading = Reading.getText().toString();
 * writing = Writing.getText().toString();
 * listening = Listening.getText().toString();
 * speaking = Speaking.getText().toString();
 * testdate = Testdate.getText().toString();
 * <p>
 * //educational background
 * final String schoolname, schoolmarks, schoolyear, highschoolname, highschoolmarks, highshoolyear, bachelorname, bachelormarks, bacheloryear, mastername, mastermarks, masteryear;
 * schoolname = Schoolyear.getText().toString();
 * schoolmarks = Schoolmarks.getText().toString();
 * schoolyear = Schoolyear.getText().toString();
 * highschoolname = Highschoolname.getText().toString();
 * highschoolmarks = Highschoolmarks.getText().toString();
 * highshoolyear = Highschoolyear.getText().toString();
 * bachelorname = Bachelorname.getText().toString();
 * bachelormarks = Bachelormarks.getText().toString();
 * bacheloryear = Bacheloryear.getText().toString();
 * mastername = Mastername.getText().toString();
 * mastermarks = Mastermarks.getText().toString();
 * masteryear = Masteryear.getText().toString();
 * <p>
 * //emergency contact
 * final String name, email, relation, address, phone;
 * name = Name.getText().toString();
 * email = Email.getText().toString();
 * relation = Relationship.getText().toString();
 * address = Address.getText().toString();
 * phone = Phone_no.getText().toString();
 * <p>
 * StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
 * @Override public void onResponse(String response) {
 * builder.setTitle("Server Response");
 * builder.setMessage("Response:" + response);
 * builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
 * @Override public void onClick(DialogInterface dialogInterface, int i) {
 * //educational background
 * Schoolname.setText("");
 * Schoolmarks.setText("");
 * Schoolyear.setText("");
 * Highschoolname.setText("");
 * Highschoolmarks.setText("");
 * Highschoolyear.setText("");
 * Bachelorname.setText("");
 * Bachelormarks.setText("");
 * Bacheloryear.setText("");
 * Mastername.setText("");
 * Mastermarks.setText("");
 * Masteryear.setText("");
 * //english test
 * Testname.setText("");
 * Testdate.setText("");
 * Testreport.setText("");
 * Overallresult.setText("");
 * Reading.setText("");
 * Writing.setText("");
 * Listening.setText("");
 * Speaking.setText("");
 * //emmergency contact
 * Name.setText("");
 * Email.setText("");
 * Relationship.setText("");
 * Address.setText("");
 * Phone_no.setText("");
 * }
 * });
 * AlertDialog alertDialog = builder.create();
 * alertDialog.show();
 * }
 * }, new Response.ErrorListener() {
 * @Override public void onErrorResponse(VolleyError error) {
 * <p>
 * Toast.makeText(main.this, "something error", Toast.LENGTH_SHORT).show();
 * ;
 * error.printStackTrace();
 * }
 * <p>
 * }) {
 * @Override protected Map<String, String> getParams() throws AuthFailureError {
 * Map<String, String> params = new HashMap<String, String>();
 * //educational background
 * params.put("schoolname", schoolname);
 * params.put("schoolmarks", schoolmarks);
 * params.put("schoolyear", schoolyear);
 * params.put("highshoolname", highschoolname);
 * params.put("highschoolmarks", highschoolmarks);
 * params.put("highschoolyear", highshoolyear);
 * params.put("bachelorname", bachelorname);
 * params.put("bachelorlmarks", bachelormarks);
 * params.put("bacheloryear", bacheloryear);
 * params.put("mastername", mastername);
 * params.put("mastermarks", mastermarks);
 * params.put("masteryear", masteryear);
 * <p>
 * //english test
 * params.put("testname", testname);
 * params.put("testdate", testdate);
 * params.put("testreport", testreport);
 * params.put("overallresult", overallresult);
 * params.put("reading", reading);
 * params.put("writing", writing);
 * params.put("listening", listening);
 * params.put("speaking", speaking);
 * <p>
 * //emergency contact
 * params.put("name", name);
 * params.put("email", email);
 * params.put("relation", relation);
 * params.put("address", address);
 * params.put("phone", phone);
 * <p>
 * return params;
 * }
 * };
 * <p>
 * Mysingleton.getInstance(main.this).addToRequest(stringRequest);
 * }
 * <p>
 * });
 * }
 * <p>
 * <p>
 * }
 **/

