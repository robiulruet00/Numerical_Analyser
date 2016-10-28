package com.example.robiul.numericalanalysis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewtonRaphson extends Fragment {


    public NewtonRaphson() {
        // Required empty public constructor
    }
    EditText hDegree,co,gu;
    TextView display;
    Button button;
    int user_power,i=0,cnt=0,flag=0;
    int coef[]=new int[10];
    float x1=0,x2=0,t=0;
    float fx1=0,fdx1=0;
    String s=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newton_raphson, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hDegree=(EditText)getActivity().findViewById(R.id.NReditText);
        co=(EditText)getActivity().findViewById(R.id.NReditText2);
        gu=(EditText)getActivity().findViewById(R.id.NReditText3);
        display=(TextView)getActivity().findViewById(R.id.NRtextview4);
        button=(Button)getActivity().findViewById(R.id.NRbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hDegree.getText().toString().trim().equals("")) {
                    hDegree.setError("Higgest degree is required!");
                    hDegree.setHint("Enter the highest degree ");
                } else
                    user_power = Integer.parseInt(hDegree.getText().toString());
                if (co.getText().toString().trim().equals("")) {
                    co.setError("Co-efficients are required!");
                    co.setHint("Enter the co-efficient  ");
                } else {
                    s = co.getText().toString();
                    String[] ss = s.split(",");
                    List<String> list = Arrays.asList(ss);
                    Collections.reverse(list);
                    for (int i = 0; i <= user_power; i++) {
                        coef[i] = Integer.parseInt(ss[i]);
                        // Log.d("robi",coefficient[i]+",");
                    }
                    if (gu.getText().toString().trim().equals("")) {
                        gu.setError("Co-efficients are required!");
                        gu.setHint("Enter the co-efficient  ");
                    } else {
                        x1 = Float.parseFloat(gu.getText().toString());
                        do {
                            cnt++;
                            fx1 = fdx1 = 0;
                            for (i = user_power; i >= 1; i--) {
                                fx1 += coef[i] * (Math.pow(x1, i));
                            }
                            fx1 += coef[0];
                            for (i = user_power; i >= 0; i--) {
                                fdx1 += coef[i] * (i * Math.pow(x1, (i - 1)));
                            }
                            t = x2;
                            x2 = (x1 - (fx1 / fdx1));

                            x1 = x2;
                        } while (Math.abs(t - x1) >= 0.0001);
                        display.setVisibility(View.VISIBLE);
                        display.setText("solution = " + x2);
                    }
                }
            }
        });

    }
}
