package com.example.jaimin.dbdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class employee_edit_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_data);

        int id = (int) getIntent().getExtras().get("id");

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        final Employee employee = db.getEmployee(id);

        final EditText name = (EditText) findViewById(R.id.edit_name_txt);
        name.setText(employee.get_name());

        Button editDataButton = (Button) findViewById(R.id.edit_submit_btn);
        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        employee_edit_data.this);

                alertDialog2.setTitle("Confirm Update...");
                alertDialog2.setMessage("Are you sure you want update this record?");

                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                employee.set_name(name.getText().toString());
                                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                                db.updateEmployee(employee);


                                Toast.makeText(getApplicationContext(),
                                        "Data Updated Successfully...", Toast.LENGTH_SHORT)
                                        .show();

                                Intent intent = new Intent(getApplicationContext(), DetailedView.class);
                                intent.putExtra("id", employee.get_id());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }

                        });

                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog2.show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_edit_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
