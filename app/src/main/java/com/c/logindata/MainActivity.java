package com.c.logindata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtReg;
    EditText edFirst, edLast, edAge, edEmail, edPhone;
    RadioGroup radioGrp;
    CheckBox cricket,football,music;
    Spinner spinner;
    RadioButton radioMale, radioFemale;
    int positionOfSelectedDataFromSpinner;

    Button btnSubmit;
    AwesomeValidation awesomeValidation;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtReg = (TextView) findViewById(R.id.txtReg);
       // Hobbies = (TextView) findViewById(R.id.Hobbies);

        edFirst = (EditText) findViewById(R.id.edFirst);
        edFirst.setSelection(0);
        edLast = (EditText) findViewById(R.id.edLast);
        edAge = (EditText) findViewById(R.id.edAge);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPhone = (EditText) findViewById(R.id.edPhone);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        spinner = (Spinner) findViewById(R.id.spinner);

        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radionFemale);

        cricket = (CheckBox) findViewById(R.id.cricket);
        football = (CheckBox) findViewById(R.id.football);
        music = (CheckBox) findViewById(R.id.music);


        List<String> language = new ArrayList<String>();
        language.add("Java");
        language.add("Android");
        language.add("Php");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, language);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.edFirst, RegexTemplate.NOT_EMPTY, R.string.invaild_name);
        awesomeValidation.addValidation(this, R.id.edLast, RegexTemplate.NOT_EMPTY, R.string.invaild_lname);
        awesomeValidation.addValidation(this, R.id.edAge, RegexTemplate.NOT_EMPTY, R.string.invaild_age);
        awesomeValidation.addValidation(this, R.id.edPhone, "[5-9]{1}[0-9]{9}$", R.string.invaild_mobile);
        awesomeValidation.addValidation(this, R.id.edEmail, Patterns.EMAIL_ADDRESS, R.string.invaild_email);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {

                    Intent iv = new Intent(MainActivity.this, Second.class);
                    //ALl The Data Get From Here
                    String str1 = edFirst.getText().toString();
                    iv.putExtra("fname", str1);
                    String str2 = edLast.getText().toString();
                    iv.putExtra("lname", str2);
                    String str3 = edAge.getText().toString();
                    iv.putExtra("age", str3);
                    String str4 = edEmail.getText().toString();
                    iv.putExtra("email", str4);
                    String str5 = edPhone.getText().toString();
                    iv.putExtra("phone", str5);

                    //RadioButton

                    boolean chk_male=radioMale.isChecked();
                    iv.putExtra("male",chk_male);

                    boolean chk_female=radioFemale.isChecked();
                    iv.putExtra("female",chk_female);

                    //CheckBox
                    boolean chk_value=cricket.isChecked();
                    iv.putExtra("cri",chk_value);

                    boolean chk_foot=football.isChecked();
                    iv.putExtra("foot",chk_foot);

                    boolean chk_value2=music.isChecked();
                    iv.putExtra("mus",chk_value2);

                    //Spinner
                    iv.putExtra("position", positionOfSelectedDataFromSpinner);


                    startActivityForResult(iv, 1);

                    // Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }else if(radioGrp.getCheckedRadioButtonId()<=0){
                    radioFemale.setError("Select Gender");

                }
                else if (!cricket.isChecked() && !football.isChecked() && !music.isChecked()) {//Grp is your radio group object
                    cricket.setError("Select Hobby");
                }
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionOfSelectedDataFromSpinner= i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        String fn = getIntent().getStringExtra("Fname");
        edFirst.setText(fn);

        String ln = getIntent().getStringExtra("Lname");
        edLast.setText(ln);

        String ag = getIntent().getStringExtra("Age");
        edAge.setText(ag);

        String em = getIntent().getStringExtra("Email");
        edEmail.setText(em);

        String ph = getIntent().getStringExtra("Phone");
        edPhone.setText(ph);

        //RadioButton
        boolean chk_male=getIntent().getBooleanExtra("Male",false);
        radioMale.setChecked(chk_male);

        boolean chk_female=getIntent().getBooleanExtra("Female",false);
        radioFemale.setChecked(chk_female);

        //CheckBox
        boolean chk_value=getIntent().getBooleanExtra("Cri",false);
        cricket.setChecked(chk_value);

        boolean chk_foot=getIntent().getBooleanExtra("Foot",false);
        football.setChecked(chk_foot);

        boolean chk_value2=getIntent().getBooleanExtra("Mus",false);
        music.setChecked(chk_value2);

        //Spinner
        int positionToShowToSpinner = getIntent().getIntExtra("position",0);
        spinner.setSelection(positionToShowToSpinner);
    }

    public void onBackPressed(){

        finishAffinity();

    }

}