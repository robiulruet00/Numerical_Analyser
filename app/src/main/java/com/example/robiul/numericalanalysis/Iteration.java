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
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Iteration extends Fragment {


    public Iteration() {
        // Required empty public constructor
    }

    static int degree;
    int j=1;
    boolean flag=false;
    double guess=0.0,result=100.0,a=0,b=0;
    double takeCo = 0,saveI = 0;
    EditText hDegree,co,gu;
    TextView display;
    Button button;
    String s=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iteration, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int[] coefficient=new int[7];
        hDegree=(EditText)getActivity().findViewById(R.id.IeditText);
        co=(EditText)getActivity().findViewById(R.id.IeditText2);
        gu=(EditText)getActivity().findViewById(R.id.IeditText3);
        display=(TextView)getActivity().findViewById(R.id.Itextview4);
        button=(Button)getActivity().findViewById(R.id.Ibutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (hDegree.getText().toString().trim().equals("")) {
                        hDegree.setError("Higgest degree is required!");
                        hDegree.setHint("Enter the highest degree ");
                    } else
                    degree=Integer.parseInt(hDegree.getText().toString());
                } catch (NumberFormatException e){
                    Toast.makeText(getContext()," "+e,Toast.LENGTH_LONG).show();
                }
                if (co.getText().toString().trim().equals("")) {
                    co.setError("Co-efficients are required!");
                    co.setHint("Enter the co-efficient  ");
                } else {
                    s = co.getText().toString();
                    String[] ss = s.split(",");
                    List<String> list = Arrays.asList(ss);
                    Collections.reverse(list);
                    ss = (String[]) list.toArray();
                    degree = ss.length;
                    for (int i = 0; i < degree; i++) {
                        coefficient[i] = Integer.parseInt(ss[i]);
                        // Log.d("robi",coefficient[i]+",");
                    }

                    coefficient[0] = (-coefficient[0]);
                    while (j <= degree) {
                        if (coefficient[j] == 0) {
                            j++;
                            continue;
                        } else {
                            saveI = j;
                            takeCo = coefficient[j];
                            coefficient[j] = 0;
                            j++;
                            break;
                        }

                    }
                    while (j <= degree) {
                        coefficient[j] = (-coefficient[j]);
                        j++;
                    }

                    for (int i = 0; i < degree; i++) {
                        Log.d("robiul", coefficient[i] + " ");
                    }

                    // System.out.println("Enter your initial guess :");
                    try {
                        if (gu.getText().toString().trim().equals("")) {
                            gu.setError("Initial guess is required!");
                            gu.setHint("Enter the initial guess ");
                        } else
                        guess = Double.parseDouble(gu.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), " " + e, Toast.LENGTH_LONG).show();
                    }
                    while (result > 0.00001) {
                        a = guess;
                        if (saveI == 1) {
                            b = function(coefficient, guess);
                            guess = b / takeCo;
                            result = b - a;
                            flag = true;
                        } else if (saveI == 2) {
                            b = function(coefficient, guess);
                            b = Math.sqrt(b / takeCo);
                            //System.out.println(b);
                            guess = b;
                            result = b - a;
                            flag = true;
                        } else if (saveI == 3) {
                            b = function(coefficient, guess);
                            b = Math.pow(b / takeCo, 1 / 3);
                            guess = b;
                            result = b - a;
                            flag = true;
                        } else flag = false;
                    }
                    if (flag) {
                        display.setVisibility(View.VISIBLE);
                        display.setText("Solution,x = " + guess);
                    } else {
                        Toast.makeText(getContext(), "Equation can not be solved here ", Toast.LENGTH_LONG).show();
                    }
////

                }
            }
        });
    }

    public static double function(int[] coefficient,double a){  //return function value
        double ret_value=0;
        for(int i=0;i<degree;i++){
            ret_value+=(coefficient[i]*Math.pow(a,i));
        }
        Log.d("value",ret_value+" ");
        return ret_value;
    }
}
