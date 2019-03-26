package com.quarkits.anshu;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class registration extends AppCompatActivity {

    EditText first_name1;
    EditText last_name1;
    EditText address1;
    EditText contact1;
    EditText email1;
    EditText password1;
    EditText confirm_password1;
    Button register1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        first_name1=(EditText) findViewById(R.id.first_name1);
        last_name1=(EditText) findViewById(R.id.last_name1);
        address1=(EditText) findViewById(R.id.address1);
        contact1=(EditText) findViewById(R.id.contact1);
        email1=(EditText) findViewById(R.id.email1);
        password1=(EditText) findViewById(R.id.password1);
        confirm_password1=(EditText) findViewById(R.id.confirm_password1);
        register1=(Button) findViewById(R.id.register1);



        register1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    // create a mysql database connection
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://148.72.232.171:3306/ecom", "eshop", "rashmi123");

                    Toast.makeText(getBaseContext(), "database connected", Toast.LENGTH_LONG).show();



                    // the mysql insert statement
                    String query = " insert into registration (firstname,lastname, address,contact,email,password)"
                            + " values ( ?, ?, ?, ?, ?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedSt = con.prepareStatement(query);

                    preparedSt.setString (1, first_name1.getText().toString());
                    preparedSt.setString (2, last_name1.getText().toString());
                    preparedSt.setString (3,  address1.getText().toString());
                    preparedSt.setString (4, contact1.getText().toString());
                    preparedSt.setString (5, email1.getText().toString());
                    preparedSt.setString (6, password1.getText().toString());

                    // execute the preparedstatement
                    preparedSt.execute();

                    con.close();
                    //JOptionPane.showMessageDialog(null, "added successfully");
                    Toast.makeText(getBaseContext(), "registered successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }



                Intent intent = new Intent( registration.this, payment.class);
                startActivity(intent);

            }

        });



    }
}
