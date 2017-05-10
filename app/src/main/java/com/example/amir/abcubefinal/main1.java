package com.example.amir.abcubefinal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class main1 extends AppCompatActivity implements View.OnClickListener {
    String server_url = "http://192.168.0.106/adcube/android.php";
    ImageView ImageView;
    Button buttonCamera, buttonGallery;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent;
    public Bitmap bitmap;
    //for spinner
    private String[] mystring;//creating a string array named mystring
    Spinner samplespinner;
    String Selecteditem;

    //personal details
    EditText Firstname, Lastname, Country, Personal_Email, Phone, Course, Nationality, Citizenship, Passport_no, Visa_no,Pass_expiry, Visa_expiry;
    DatePicker Dob;
    Button Submit;
    RadioButton Gender;


    //educational background
    EditText Schoolname, Schoolmarks, Schoolyear,
            Highschoolname, Highschoolmarks, Highschoolyear,
            Bachelorname, Bachelormarks, Bacheloryear,
            Mastername, Mastermarks, Masteryear;


    //emergency contact
    EditText Name, Email, Relationship, Address, Phone_no;

    //English Language
    EditText Testname, Testdate, Testreport, Overallresult, Reading, Writing, Listening, Speaking;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout1);

        //for spinner
        ArrayAdapter sampleadapter;//Assigning a name for ArrayAdapter
        Resources res = getResources();//Assigning a name for Resources
        mystring = res.getStringArray(R.array.Marital_status);//getting the array items to string named my string
        //mystring is an array which is defined on the top
        samplespinner = (Spinner) findViewById(R.id.spinner); //samplespinner is defined in the top
        //samplespinner is the name given to the spinner at the top
        sampleadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mystring);
        samplespinner.setAdapter(sampleadapter);
        samplespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(getBaseContext(), spVIA.getSelectedItem().toString(),
                //Toast.LENGTH_LONG).show();

                Selecteditem = samplespinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        //for radio button
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId) {
                    case R.id.male:
                        Gender = (RadioButton) findViewById(R.id.male);
                        break;
                    case R.id.female:
                        Gender = (RadioButton) findViewById(R.id.female);
                        break;
                }
            }
        });


        //personal details
        ImageView = (ImageView) findViewById(R.id.imageview);
        Firstname = (EditText) findViewById(R.id.firstname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Country = (EditText) findViewById(R.id.country);
        Personal_Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.mobile);
        Course = (EditText) findViewById(R.id.course);
        Pass_expiry = (EditText) findViewById(R.id.pass_expiry);
        Nationality = (EditText) findViewById(R.id.nationality);
        Citizenship = (EditText) findViewById(R.id.citizenship);
        Passport_no = (EditText) findViewById(R.id.pass_no);
        Visa_no = (EditText) findViewById(R.id.visa_no);
        Visa_expiry = (EditText) findViewById(R.id.visa_expiry);
        Dob = (DatePicker) findViewById(R.id.simpleDatePicker);


        //educational background
        Schoolname = (EditText) findViewById(R.id.schoolname);
        Schoolmarks = (EditText) findViewById(R.id.schoolmarks);
        Schoolyear = (EditText) findViewById(R.id.schoolyear);
        Highschoolname = (EditText) findViewById(R.id.highschoolname);
        Highschoolmarks = (EditText) findViewById(R.id.highschoolmarks);
        Highschoolyear = (EditText) findViewById(R.id.highscchoolyear);
        Bachelorname = (EditText) findViewById(R.id.bachelorname);
        Bachelormarks = (EditText) findViewById(R.id.bachelormarks);
        Bacheloryear = (EditText) findViewById(R.id.bacheloryear);
        Mastername = (EditText) findViewById(R.id.mastername);
        Mastermarks = (EditText) findViewById(R.id.mastermarks);
        Masteryear = (EditText) findViewById(R.id.masteryear);


        //eergency ccontact
        Name = (EditText) findViewById(R.id.contactname);
        Email = (EditText) findViewById(R.id.emailaddress);
        Relationship = (EditText) findViewById(R.id.relation);
        Phone_no = (EditText) findViewById(R.id.phone);
        Address = (EditText) findViewById(R.id.address);

        //english language
        Testname = (EditText) findViewById(R.id.testname);
        Testdate = (EditText) findViewById(R.id.testdate);
        Testreport = (EditText) findViewById(R.id.testreport);
        Overallresult = (EditText) findViewById(R.id.overallresult);
        Reading = (EditText) findViewById(R.id.reading);
        Writing = (EditText) findViewById(R.id.writing);
        Listening = (EditText) findViewById(R.id.listening);
        Speaking = (EditText) findViewById(R.id.speaking);

        buttonCamera = (Button) findViewById(R.id.capturebtn);
        buttonGallery = (Button) findViewById(R.id.browsebtn);
        Submit = (Button) findViewById(R.id.submit);


        buttonCamera.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        Submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.browsebtn:
                GetImageFromGallery();
                break;
            case R.id.submit:
                submitall();
                break;
            case R.id.capturebtn:
                ClickImageFromCamera();
        }
    }

    public void submitall() {
        final String first_name, last_name, country, dob, personal_email, course, citizenship, nationality, pass_no,
                visa_no, visa_expiry, phone_no, contact, address, email, phone, relation,pass_expiry, gender, image;
        //personal details

        first_name = Firstname.getText().toString();
        last_name = Lastname.getText().toString();
        country = Country.getText().toString();
        personal_email = Personal_Email.getText().toString();
        dob = Dob.toString();
        //image = ImageView.toString();
        phone_no = Phone.getText().toString();
        course = Course.getText().toString();
        nationality = Nationality.getText().toString();
        citizenship = Citizenship.getText().toString();
        pass_no = Passport_no.getText().toString();
        pass_expiry =Pass_expiry.getText().toString();
        visa_no = Visa_no.getText().toString();
        visa_expiry = Visa_expiry.getText().toString();
        //gender = Gender.getText().toString();


        //emergency contact
        contact = Name.getText().toString();
        email = Email.getText().toString();
        address = Address.getText().toString();
        phone = Phone_no.getText().toString();
        relation = Relationship.getText().toString();


        //english test
        final String testname, testdate, testreport, overallresult, reading, writing, listening, speaking;
        testname = Testname.getText().toString();
        testreport = Testreport.getText().toString();
        overallresult = Overallresult.getText().toString();
        reading = Reading.getText().toString();
        writing = Writing.getText().toString();
        listening = Listening.getText().toString();
        speaking = Speaking.getText().toString();
        testdate = Testdate.getText().toString();


        //educational background
        final String schoolname, schoolmarks, schoolyear, highschoolname, highschoolmarks,
                highshoolyear, bachelorname, bachelormarks, bacheloryear, mastername, mastermarks, masteryear;
        schoolname = Schoolname.getText().toString();
        schoolmarks = Schoolmarks.getText().toString();
        schoolyear = Schoolyear.getText().toString();
        highschoolname = Highschoolname.getText().toString();
        highschoolmarks = Highschoolmarks.getText().toString();
        highshoolyear = Highschoolyear.getText().toString();
        bachelorname = Bachelorname.getText().toString();
        bachelormarks = Bachelormarks.getText().toString();
        bacheloryear = Bacheloryear.getText().toString();
        mastername = Mastername.getText().toString();
        mastermarks = Mastermarks.getText().toString();
        masteryear = Masteryear.getText().toString();


        /**if (!first_name.isEmpty() && !last_name.isEmpty() && !country.isEmpty() && !personal_email.isEmpty() && !phone_no.isEmpty()
                && !course.isEmpty() && !nationality.isEmpty() && !citizenship.isEmpty() && !pass_no.isEmpty() && !visa_no.isEmpty()
                && !visa_expiry.isEmpty() && !contact.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !relation.isEmpty()) {
**/
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String Response = jsonObject.getString("cropIntent");
                        Toast.makeText(main1.this, Response, Toast.LENGTH_SHORT).show();
                        ImageView.setImageResource(0);
                        ImageView.setVisibility(View.GONE);
                       // Firstname.setText("");
                       // Firstname.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(main1.this,response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(main1.this, error.toString(), Toast.LENGTH_SHORT).show();
                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("country", country);
                    params.put("dob", dob);
                    params.put("personal_email", personal_email);
                    params.put("mobile", phone_no);
                    params.put("course", course);
                    params.put("nationality", nationality);
                    params.put("citizenship", citizenship);
                    params.put("pass_no", pass_no);
                    params.put("pass_expiry", pass_expiry);
                    params.put("visa_no", visa_no);
                    params.put("visa_expiry", visa_expiry);
                    //.put("gender", gender);
                    params.put("status", Selecteditem);
                    params.put("image", imageToString(bitmap));
                    params.put("first_name",first_name);
                    params.put("last_name",last_name);

                    //educational background
                    params.put("schoolname", schoolname);
                    params.put("schoolmarks", schoolmarks);
                    params.put("schoolyear", schoolyear);
                    params.put("highschoolname", highschoolname);
                    params.put("highschoolmarks", highschoolmarks);
                    params.put("highschoolyear", highshoolyear);
                    params.put("bachelorname", bachelorname);
                    params.put("bachelormarks", bachelormarks);
                    params.put("bacheloryear", bacheloryear);
                    params.put("mastername", mastername);
                    params.put("mastermarks", mastermarks);
                    params.put("masteryear", masteryear);

                    //english test
                    params.put("testname", testname);
                    params.put("testdate", testdate);
                    params.put("testreport", testreport);
                    params.put("overallresult", overallresult);
                    params.put("reading", reading);
                    params.put("writing", writing);
                    params.put("listening", listening);
                    params.put("speaking", speaking);

                    //emergency contact
                    params.put("contact", contact);
                    params.put("relation", relation);
                    params.put("address", address);
                    params.put("phone", phone);
                    params.put("email", email);
                    return params;
                }
            };
            Mysingleton.getInstance(main1.this).addToRequest(stringRequest);
        } /**else {
            Toast.makeText(main1.this,
                    "Please enter your details!", Toast.LENGTH_LONG)
                    .show();
        }
    } **/




    public void ClickImageFromCamera() {

        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, 0);

    }
    public void GetImageFromGallery() {

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ImageCropFunction();

        } else if (requestCode == 2) {

            if (data != null) {

                uri = data.getData();

                ImageCropFunction();
            }
        } else if (requestCode == 1) {

            if (data != null) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bundle bundle = data.getExtras();
                    bitmap =bundle.getParcelable("data");
                    ImageView.setImageBitmap(bitmap);
                    ImageView.setVisibility(View.VISIBLE);
                    Name.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    private void uploadImage() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("cropIntent");
                    Toast.makeText(main1.this, Response, Toast.LENGTH_SHORT).show();
                    ImageView.setImageResource(0);
                    ImageView.setVisibility(View.GONE);
                    Name.setText("");
                    Name.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(main1.this, "successful", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(main1.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parms = new HashMap<>();
                parms.put("name", Name.getText().toString().trim());
                parms.put("image", imageToString(bitmap));

                return parms;
            }
        };
        Mysingleton.getInstance(main1.this).addToRequest(stringrequest);
    }
    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);

            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //cconvert image to string for database

    public static String imageToString(Bitmap BitmapData) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitmapData.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] byte_arr = bos.toByteArray();
        String encoded = Base64.encodeToString(byte_arr, Base64.DEFAULT); //appendLog(file);
        return encoded;
    }
}








