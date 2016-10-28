package com.example.robiul.numericalanalysis;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class Bisection extends Fragment {

    public Bisection() {
        // Required empty public constructor
    }
    EditText hdegree,co,result;
    TextView textView,display;
    Button button;
    static int degree=0;
    int hiDegree=0,flag=0,f=0;
    double a = 0,b=0,valueFora = 0,valueForb = 0,c,f_of_c,root = 0,TOLERANCE = 0.0001;
    String s=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bisection, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int[] coefficient=new int[7];
        hdegree=(EditText)getActivity().findViewById(R.id.editText);
        co=(EditText)getActivity().findViewById(R.id.editText2);
        textView=(TextView)getActivity().findViewById(R.id.textView6);
        display=(TextView)getActivity().findViewById(R.id.textView7);
        button=(Button)getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (hdegree.getText().toString().trim().equals("")) {
                        hdegree.setError("Higgest degree is required!");
                        hdegree.setHint("Enter the highest degree ");
                    } else
                        hiDegree = Integer.parseInt(hdegree.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), " " + e, Toast.LENGTH_LONG).show();
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
                    }
                    for (int j = 1; j <= 5; j++) {  //check from 0 to......--->
                        a = j;
                        b = a + 1;
                        valueFora = function(coefficient, a);
                        valueForb = function(coefficient, b);
                        //System.out.println(a+"----->"+valueFora+" ,"+b+"----->"+valueForb);
                        if (valueFora * valueForb < 0) {
                            flag = 1;
                            break;
                        }
                    }

                    if (flag != 1) {
                        for (int j = -1; j >= -5; j--) { //check from -1 to <-----
                            a = j;
                            b = a - 1;
                            valueFora = function(coefficient, a);
                            valueForb = function(coefficient, b);
                            //System.out.println(a+"----->"+valueFora+" ,"+b+"----->"+valueForb);
                            if (valueFora * valueForb < 0) {
                                flag = 1;
                                break;
                            }
                        }
                    }
                    while (Math.abs(a - b) > TOLERANCE) {
                        c = (a + b) / 2;
                        f_of_c = function(coefficient, c);
                        if (f_of_c * function(coefficient, a) == 0 || f_of_c * function(coefficient, b) == 0) {
                            root = c;
                            f = 1;
                        } else if (f_of_c * function(coefficient, a) > 0) {
                            a = c;
                        } else {
                            b = c;
                        }
                    }
                    if (f != 1)
                        root = ((a + b) / 2);
                    if (flag == 0) {
                        Toast.makeText(getContext(), "Equation can not be solved by this Method()", Toast.LENGTH_LONG).show();
                        // result.setText("Equation can not be solved by this Method()");
                    } else {
                        display.setVisibility(View.VISIBLE);
                        display.setText("Solution,x = " + root);
                    }
                    //result.setText("The root of the equation = "+root);
                }
            }
        });
    }
    public static double function(int[] coefficient,double a){  //return function value
        double ret_value=0;
        for(int i=0;i<degree;i++){
            ret_value+=(coefficient[i]*Math.pow(a,i));
        }
        return ret_value;
    }

}
