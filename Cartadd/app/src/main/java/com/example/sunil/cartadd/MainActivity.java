package com.example.sunil.cartadd;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHandler db;
    EditText uname2,pass2;
    Button login,signup2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname2= (EditText) findViewById(R.id.tv_uname2);
        pass2= (EditText) findViewById(R.id.tv_pass2);

        login= (Button) findViewById(R.id.bt_login);
        signup2= (Button) findViewById(R.id.bt_signup2);

        login.setOnClickListener(this);
        signup2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        db=new DatabaseHandler(this);

        switch(view.getId()){

            case R.id.bt_login:

                String Username=uname2.getText().toString();
                String Password=pass2.getText().toString();


                boolean isCheck=loginCheck();

                if(isCheck){
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_LONG).show();

                    Intent i=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(i);
                    finish();
                }

                else
                    Toast.makeText(MainActivity.this, "Incorrect username or password...!", Toast.LENGTH_LONG).show();
                break;

            case R.id.bt_signup2:

                Intent i=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
             //   finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.login_menu_activity,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try{
        switch (item.getItemId()) {

            case R.id.bt_menuview:
                Cursor cur = db.getAllUserData();

                if (cur.getCount() == 0) {
                    showMsg("ERROR", "DATA NOT FOUND");
                } else {
                    StringBuffer buf = new StringBuffer();

                    while (cur.moveToNext()) {
                        buf.append("ID:" + cur.getString(0) + "\n");
                        buf.append("FullName:" + cur.getString(1) + "\n");
                        buf.append("Username:" + cur.getString(2) + "\n");
                        buf.append("Password:" + cur.getString(3) + "\n\n");

                    }
                    showMsg("Details", buf.toString());
                }

                break;
        }
    }catch(Exception e)
        {
            Toast.makeText(MainActivity.this,"ERROR:"+e,Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMsg(String title, String msg){
        AlertDialog.Builder bld=new AlertDialog.Builder(this);
        bld.setTitle(title);
        bld.setMessage(msg);
        bld.show();
        bld.setCancelable(true);

    }


    public boolean loginCheck(){

        Cursor cur=db.getAllUserData();
        boolean check=false;

        while(cur.moveToNext()){

            String checkUsername=cur.getString(2);
            String checkPass=cur.getString(3);

            if( uname2.getText().toString().equals(checkUsername) && pass2.getText().toString().equals(checkPass)){
              check=true;
            }
        }
        return check;
    }

}
