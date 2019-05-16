package com.jct.bd.patientapp.controller;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.datasource.Action;
import com.jct.bd.patientapp.model.entities.Patient;
import com.jct.bd.patientapp.model.entities.Type;
import static com.jct.bd.patientapp.model.entities.Patient.IDCheck;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
        TextView login;
        TextInputLayout InputPassword, InputEmail, InputIdNumber, InputfirstName,InputlastName, InputpsycoId;
        EditText fName,lName, id, email, password, psycoid;
        Calendar birthday,registrationDate;
        CardView signUp;
        Spinner spinner;
        Type typeOfTreatment;
        FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
    }

    public void findViews(){
        InputPassword = findViewById(R.id.InputPassword);
        InputEmail = findViewById(R.id.InputEmail);
        InputfirstName = findViewById(R.id.InputfirstName);
        InputIdNumber = findViewById(R.id.InputIdNumber);
        InputlastName = findViewById(R.id.InputlastName);
        InputpsycoId = findViewById(R.id.InputpsychoId);
        login = findViewById(R.id.textView3);
        fName = (EditText) findViewById(R.id.firstName);
        lName = (EditText) findViewById(R.id.lastName);
        id = (EditText) findViewById(R.id.idNumber);
        psycoid = (EditText) findViewById(R.id.psychoId);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUp = (CardView) findViewById(R.id.Register);
        signUp.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        spinner = findViewById(R.id.TypeTreatment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Treatments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String text = getString(R.string.account);
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.YELLOW);
            }
        };
        ss.setSpan(clickableSpan, 17, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(ss);
        login.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private boolean validateEmail(){
        String emailInput = InputEmail.getEditText().getText().toString();
        if(emailInput.isEmpty()){
            InputEmail.setError(getString(R.string.fill_email));
            InputEmail.setErrorEnabled(true);
            email.requestFocus();
            Toast.makeText(this,getString(R.string.fill_email),Toast.LENGTH_LONG).show();
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            InputEmail.setErrorEnabled(true);
            InputEmail.setError(getString(R.string.contains));
            email.requestFocus();
            Toast.makeText(this,getString(R.string.contains),Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            InputEmail.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        String passwordInput = InputPassword.getEditText().getText().toString();
        if(passwordInput.isEmpty()){
            InputPassword.setError(getString(R.string.fill_password));
            InputPassword.setErrorEnabled(true);
            password.requestFocus();
            Toast.makeText(this,getString(R.string.fill_password),Toast.LENGTH_LONG).show();
            return false;
        }else if(passwordInput.length()<6){
            InputPassword.setError(getString(R.string.length_password));
            InputPassword.setErrorEnabled(true);
            password.requestFocus();
            Toast.makeText(this,getString(R.string.length_password),Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            InputPassword.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateId(){
        String IdNumberInput = InputIdNumber.getEditText().getText().toString();
        if(IdNumberInput.isEmpty()){
            InputIdNumber.setError(getString(R.string.fill_id));
            InputIdNumber.setErrorEnabled(true);
            id.requestFocus();
            Toast.makeText(this,getString(R.string.fill_id),Toast.LENGTH_LONG).show();
            return false;
        }else if(!IDCheck(IdNumberInput)){
            InputIdNumber.setError(getString(R.string.Extract_id));
            InputIdNumber.setErrorEnabled(true);
            id.requestFocus();
            Toast.makeText(this,getString(R.string.Extract_id),Toast.LENGTH_LONG).show();
            return false;
        }else if(IdNumberInput.length()!=9){
            InputIdNumber.setError(getString(R.string.length_id));
            InputIdNumber.setErrorEnabled(true);
            id.requestFocus();
            Toast.makeText(this,getString(R.string.length_id),Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            InputIdNumber.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateFirstName(){
        String FirstName = InputfirstName.getEditText().getText().toString();
        if(FirstName.isEmpty()){
            InputfirstName.setError(getString(R.string.fill_userName));
            InputfirstName.setErrorEnabled(true);
            fName.requestFocus();
            Toast.makeText(this,getString(R.string.fill_userName),Toast.LENGTH_LONG).show();
            return false;
        }else {
            InputfirstName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateLastName(){
        String LastName = InputlastName.getEditText().getText().toString();
        if(LastName.isEmpty()){
            InputlastName.setError(getString(R.string.fill_userName));
            InputlastName.setErrorEnabled(true);
            lName.requestFocus();
            Toast.makeText(this,getString(R.string.fill_userName),Toast.LENGTH_LONG).show();
            return false;
        }else {
            InputlastName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePsycoId(){
        String Psycoid = InputpsycoId.getEditText().getText().toString();
        if(Psycoid.isEmpty()){
            InputpsycoId.setError(getString(R.string.fill_id));
            InputpsycoId.setErrorEnabled(true);
            psycoid.requestFocus();
            Toast.makeText(this,getString(R.string.fill_id),Toast.LENGTH_LONG).show();
            return false;
        }else if(!IDCheck(Psycoid)){
            InputpsycoId.setError(getString(R.string.Extract_id));
            InputpsycoId.setErrorEnabled(true);
            psycoid.requestFocus();
            Toast.makeText(this,getString(R.string.Extract_id),Toast.LENGTH_LONG).show();
            return false;
        }else if(Psycoid.length()!=9){
            InputpsycoId.setError(getString(R.string.length_id));
            InputpsycoId.setErrorEnabled(true);
            psycoid.requestFocus();
            Toast.makeText(this,getString(R.string.length_id),Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            InputIdNumber.setErrorEnabled(false);
            return true;
        }
    }
        public void confirmInput(View v){
        if(!validateFirstName()||!validateLastName()||!validateId()||!validateEmail()||!validatePsycoId()||!validatePassword())
            return;
        else
            registerUser();
    }

        private void registerUser() {
        try {
            final Patient patient = new Patient();
            patient.setEmail(email.getText().toString());
            patient.setId(id.getText().toString());
            patient.setPsychoId(psycoid.getText().toString());
            patient.setFirstName(fName.getText().toString());
            patient.setLastName(lName.getText().toString());
            patient.setPassword(password.getText().toString());
            patient.setModule(typeOfTreatment);
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                new AsyncTask<Void,Void,Void>() {
                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                        return FactoryBackend.getInstance().addPatient(patient, new Action<String>() {
                                            @Override
                                            public void onSuccess(String obj) {
                                                signUp.setEnabled(true);
                                                Toast.makeText(getBaseContext(), getString(R.string.insert) + obj, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onFailure(Exception exception) {
                                                Toast.makeText(getBaseContext(), getString(R.string.error) + exception.getMessage(), Toast.LENGTH_LONG).show();
                                            }

                                            public void onProgress(String status, double percent) {
                                                if (percent != 100)
                                                    signUp.setEnabled(false);
                                            }
                                        });
                                    }
                                }.execute();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, R.string.firebase_error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

        @Override
        public void onClick(View v) {
        if (v == signUp) {
            confirmInput(v);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        switch (text){
            case "BEGINNER":
                typeOfTreatment = Type.BEGINNER;
                break;
            case "ADVANCED":
                typeOfTreatment = Type.ADVANCED;
                break;
            case "FINISHED":
                typeOfTreatment = Type.FINISHED;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
