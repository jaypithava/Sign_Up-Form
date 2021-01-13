package com.c.logindata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.ArrayList;
import java.util.List;

public class Second extends AppCompatActivity {
    TextView txtReg;
    EditText edFirst, edLast, edAge, edEmail, edPhone;
    CheckBox cricket1, football1, music1;
    Spinner spinner;
    int positionOfSelectedDataFromSpinner;
    RadioGroup radioGrp;
    RadioButton radioMale, radioFemale;

    AwesomeValidation awesomeValidation;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    Toolbar actionbar;
    ImageButton imageBack;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        actionbar = (Toolbar) findViewById(R.id.actionbar);
//       //

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();


       // getSupportActionBar().setTitle("Clear Data");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtReg = (TextView) findViewById(R.id.txtReg);

        edFirst = (EditText) findViewById(R.id.edFirst);
        edFirst.setSelection(0);
        edLast = (EditText) findViewById(R.id.edLast);
        edAge = (EditText) findViewById(R.id.edAge);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPhone = (EditText) findViewById(R.id.edPhone);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radionFemale);

        cricket1 = (CheckBox) findViewById(R.id.cricket);
        football1 = (CheckBox) findViewById(R.id.football);
        music1 = (CheckBox) findViewById(R.id.music);

        spinner = (Spinner) findViewById(R.id.spinner);
        imageBack=(ImageButton)findViewById(R.id.imageBack);
        //actionbar=(Toolbar)findViewById(R.id.actionbar);
        //setSupportActionBar(actionbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);




        List<String> language1 = new ArrayList<String>();
        language1.add("Java");
        language1.add("Android");
        language1.add("Php");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, language1);

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



        Intent iv = getIntent();
        String str = iv.getStringExtra("fname");
        edFirst.setText(str);

        String str1 = iv.getStringExtra("lname");
        edLast.setText(str1);

        String str2 = iv.getStringExtra("age");
        edAge.setText(str2);

        String str3 = iv.getStringExtra("email");
        edEmail.setText(str3);

        String str4 = iv.getStringExtra("phone");
        edPhone.setText(str4);

        //RadioButton
        boolean chk_male = iv.getBooleanExtra("male", false);
        radioMale.setChecked(chk_male);

        boolean chk_female = iv.getBooleanExtra("female", false);
        radioFemale.setChecked(chk_female);


        //CheckBox
        boolean chk_value = iv.getBooleanExtra("cri", false);
        cricket1.setChecked(chk_value);

        boolean chk_foot = iv.getBooleanExtra("foot", false);
        football1.setChecked(chk_foot);

        boolean chk_value2 = iv.getBooleanExtra("mus", false);
        music1.setChecked(chk_value2);

        //Spinner
        int positionToShowToSpinner = iv.getIntExtra("position", 0);
        spinner.setSelection(positionToShowToSpinner);



        imageBack.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {
                    Intent iv = new Intent(Second.this, MainActivity.class);
                    String str1 = edFirst.getText().toString();
                    iv.putExtra("Fname", str1);
                    String str2 = edLast.getText().toString();
                    iv.putExtra("Lname", str2);
                    String str3 = edAge.getText().toString();
                    iv.putExtra("Age", str3);
                    String str4 = edEmail.getText().toString();
                    iv.putExtra("Email", str4);
                    String str5 = edPhone.getText().toString();
                    iv.putExtra("Phone", str5);

                    //RadioButton
                    boolean chk_male = radioMale.isChecked();
                    iv.putExtra("Male", chk_male);

                    boolean chk_female = radioFemale.isChecked();
                    iv.putExtra("Female", chk_female);

                    //CheckBox
                    boolean chk_value = cricket1.isChecked();
                    iv.putExtra("Cri", chk_value);

                    boolean chk_foot = football1.isChecked();
                    iv.putExtra("Foot", chk_foot);

                    boolean chk_value2 = music1.isChecked();
                    iv.putExtra("Mus", chk_value2);

                    //Spinner
                    iv.putExtra("position", positionOfSelectedDataFromSpinner);


                    startActivity(iv);

                } else if (radioGrp.getCheckedRadioButtonId() <= 0) {
                    radioFemale.setError("Select Gender");

                } else if (!cricket1.isChecked() && !football1.isChecked() && !music1.isChecked()) {//Grp is your radio group object
                    cricket1.setError("Select Hobby");
                }
            }

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    //Second Activity to Previous Activity Reflect Data
                    Intent iv = new Intent(Second.this, MainActivity.class);
                    String str1 = edFirst.getText().toString();
                    iv.putExtra("Fname", str1);
                    String str2 = edLast.getText().toString();
                    iv.putExtra("Lname", str2);
                    String str3 = edAge.getText().toString();
                    iv.putExtra("Age", str3);
                    String str4 = edEmail.getText().toString();
                    iv.putExtra("Email", str4);
                    String str5 = edPhone.getText().toString();
                    iv.putExtra("Phone", str5);

                    //RadioButton
                    boolean chk_male = radioMale.isChecked();
                    iv.putExtra("Male", chk_male);

                    boolean chk_female = radioFemale.isChecked();
                    iv.putExtra("Female", chk_female);

                    //CheckBox
                    boolean chk_value = cricket1.isChecked();
                    iv.putExtra("Cri", chk_value);

                    boolean chk_foot = football1.isChecked();
                    iv.putExtra("Foot", chk_foot);

                    boolean chk_value2 = music1.isChecked();
                    iv.putExtra("Mus", chk_value2);

                    //Spinner
                    iv.putExtra("position", positionOfSelectedDataFromSpinner);


//                edFirst.getText().clear();
//                edLast.getText().clear();
//                edAge.getText().clear();
//                edEmail.getText().clear();

                    startActivity(iv);
                }else if (radioGrp.getCheckedRadioButtonId() <= 0) {
                    radioFemale.setError("Select Gender");

                } else if (!cricket1.isChecked() && !football1.isChecked() && !music1.isChecked()) {//Grp is your radio group object
                    cricket1.setError("Select Hobby");
                }
            }

        });




        /*actionbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent iv=new Intent(Second.this,MainActivity.class);
                String str1=edFirst.getText().toString();
                iv.putExtra("Fname",str1);
                String str2=edLast.getText().toString();
                iv.putExtra("Lname",str2);
                String str3=edAge.getText().toString();
                iv.putExtra("Age",str3);
                String str4=edEmail.getText().toString();
                iv.putExtra("Email",str4);
                String str5=edPhone.getText().toString();
                iv.putExtra("Phone",str5);
                startActivity(iv);

                return false;
            }
        });*/

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionOfSelectedDataFromSpinner = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    @SuppressLint("ResourceType")
    public void onBackPressed() {
        if (awesomeValidation.validate()) {
            Intent iv = new Intent(Second.this, MainActivity.class);
            String str1 = edFirst.getText().toString();
            iv.putExtra("Fname", str1);
            String str2 = edLast.getText().toString();
            iv.putExtra("Lname", str2);
            String str3 = edAge.getText().toString();
            iv.putExtra("Age", str3);
            String str4 = edEmail.getText().toString();
            iv.putExtra("Email", str4);
            String str5 = edPhone.getText().toString();
            iv.putExtra("Phone", str5);

            //RadioButton
            boolean chk_male = radioMale.isChecked();
            iv.putExtra("Male", chk_male);

            boolean chk_female = radioFemale.isChecked();
            iv.putExtra("Female", chk_female);

            //CheckBox
            boolean chk_value = cricket1.isChecked();
            iv.putExtra("Cri", chk_value);

            boolean chk_foot = football1.isChecked();
            iv.putExtra("Foot", chk_foot);

            boolean chk_value2 = music1.isChecked();
            iv.putExtra("Mus", chk_value2);

            //Spinner
            iv.putExtra("position", positionOfSelectedDataFromSpinner);

            startActivity(iv);


        } else if (radioGrp.getCheckedRadioButtonId() <= 0) {
            radioFemale.setError("Select Gender");

        } else if (!cricket1.isChecked() && !football1.isChecked() && !music1.isChecked()) {//Grp is your radio group object
            cricket1.setError("Select Hobby");
        }
    }
}



