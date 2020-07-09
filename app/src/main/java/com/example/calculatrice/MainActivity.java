package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mySpinner;
    EditText ecran;
    final List<String> options = new ArrayList<>();
    double a, b, resultat;
    LinearLayout verLay;
    String Op = "+";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySpinner = findViewById(R.id.sp);
        ecran =  findViewById(R.id.ecran);
        verLay = findViewById(R.id.VerLayout);
        options.add("Standard");
        options.add("Scientifique");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, options ) ;
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        LinearLayout horSc = findViewById(R.id.Scientific_ver);

        switch(options.get(position))
        {
            case "Standard"     : ;horSc.setVisibility(View.GONE);      break;
            case "Scientifique" :  horSc.setVisibility(View.VISIBLE);   break;
        }
        Toast.makeText(this, options.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    public void chiffre(View view)
    {
        String chf = view.getTag().toString();
        String ecranTxt = ecran.getText().toString();
        if(ecranTxt.equals("NaN") ||ecranTxt.equals("0") || ecranTxt.contains(".")  ) ecranTxt="";
        ecranTxt += chf;
        ecran.setText(ecranTxt);
    }

    public void effacer(View view)
    {
        a = 0;
        b = 0;
        resultat = 0;
        ecran.setText("");
    }


    public void ScOperation(View view)
    {
        Button btn = (Button) view;
        String ecrantTxt = ecran.getText().toString();
        Log.i("ScOperation" , ecrantTxt);

        if(ecrantTxt.equals(""))
        {
            Toast.makeText(this, "Veuillez saisir une valeur!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            a = Double.valueOf(ecrantTxt);
            Op = btn.getTag().toString();

            switch (Op)
            {
                case "cos" : resultat = Math.cos(a);                    break;
                case "sin" : resultat = Math.sin(a);                    break;
                case "tan" : resultat = Math.tan(a);                    break;
                case "sqrt": resultat = a<0? Double.NaN : Math.sqrt(a); break;
                case "inv" : resultat = a==0? Double.NaN: 1/a;          break;
                case "exp" : resultat = Math.exp(a);                    break;
                case "log" : resultat = a<0? Double.NaN :Math.log(a);   break;
            }

            ecran.setText(Double.toString(resultat));
        }
    }
    public void operation(View view)
    {
        Button btn = (Button) view;
        String ecranTxt = ecran.getText().toString();
        if(ecranTxt.equals(""))
        {
            Toast.makeText(this, "Veuillez saisir une valeur!", Toast.LENGTH_SHORT).show();
        }else
        {
            a = Double.valueOf(ecranTxt);
            ecran.setText("");
            Op = btn.getText().toString();
        }

    }

    public void egal(View view)
    {
        String ecranTxt = ecran.getText().toString();
        if(ecranTxt.equals(""))
        {
            Toast.makeText(this, "Veuillez saisir une valeur!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            b = Double.valueOf(ecranTxt);

            Log.i("test", "OP = "+ Op);
            switch (Op)
            {
                case "+"   : resultat = a + b;                          break;
                case "-"   : resultat = a - b;                          break;
                case "/"   : resultat = b==0? Double.NaN : a / b;       break;
                case "X"   : resultat =  a * b;                         break;
                case "pow" : resultat =  Math.pow(a,b);                 break;
            }
            ecran.setText(Double.toString(resultat));
        }

    }
}
