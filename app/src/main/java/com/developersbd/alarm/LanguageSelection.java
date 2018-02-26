package com.developersbd.alarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LanguageSelection extends ActionBarActivity implements View.OnClickListener{
    SharedPreferences pref;
    String selectedLanguage="en";
    Button bn_selection;
    Button en_selection;
    Button hn_selection;
    Button cn_selection;
    Button fr_selection;
    Button gr_selection;
    Button ja_selection;
    Button sp_selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int n=checkLogin();
        selectedLanguage= pref.getString("lan","en");
       // Toast.makeText(getBaseContext(),"vlue: "+n,Toast.LENGTH_LONG).show();

       Intent intent=getIntent();
        String val=intent.getStringExtra("command");
        if(n==1)
        {
            try {
                if (val.equals("2")) {

                } else {
                    Intent intent1 = new Intent(LanguageSelection.this, AlarmActivity.class);
                    intent1.putExtra("language",selectedLanguage);
                    startActivity(intent1);
                    this.finish();
                }
            }catch (Exception e)
            {
                Intent intent1 = new Intent(LanguageSelection.this, AlarmActivity.class);
                intent1.putExtra("language",selectedLanguage);
                startActivity(intent1);
                this.finish();
            }

        }

        bn_selection=(Button) findViewById(R.id.btn_bn);
        en_selection=(Button) findViewById(R.id.en);
        hn_selection=(Button) findViewById(R.id.hn);
        cn_selection=(Button) findViewById(R.id.cn);
        gr_selection=(Button) findViewById(R.id.gr);
        fr_selection=(Button) findViewById(R.id.fr);
        sp_selection=(Button) findViewById(R.id.sp);
        ja_selection=(Button) findViewById(R.id.ja);

        bn_selection.setOnClickListener(this);
        en_selection.setOnClickListener(this);
        hn_selection.setOnClickListener(this);
        cn_selection.setOnClickListener(this);
        gr_selection.setOnClickListener(this);
        fr_selection.setOnClickListener(this);
        sp_selection.setOnClickListener(this);
        ja_selection.setOnClickListener(this);


    }
    int checkLogin()
    {
        int n=0;
        n=  pref.getInt("login",0);

        if(n==0) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("login", 1);        // Saving integer
            editor.commit();
        }

        return n;
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==bn_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","bn");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_bn);

            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==en_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","en");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_en);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==cn_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","cn");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_cn);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==ja_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","ja");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);;
            String s=getString(R.string.language_jp);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==fr_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","fr");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_fr);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==gr_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","gr");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_gr);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==sp_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","sp");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_sp);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
        if(view.getId()==hn_selection.getId())
        {
            Intent intent=new Intent(LanguageSelection.this,AlarmActivity.class);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("lan","hn");        // Saving language
            editor.commit();
            selectedLanguage= pref.getString("lan","en");
            intent.putExtra("language",selectedLanguage);
            String s=getString(R.string.language_hn);
            Toast.makeText(getBaseContext(),""+s,Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }


    }
}
