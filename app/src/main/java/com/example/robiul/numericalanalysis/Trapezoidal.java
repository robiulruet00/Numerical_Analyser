package com.example.robiul.numericalanalysis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Trapezoidal extends Fragment {


    TextView t1,t2,display;
    EditText e1,e2;
    Button button,resultButton;
    int n,i,j,k=0;
    float x[]=new float[20],y[]=new float[20],h,sum=0;

    public Trapezoidal() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trapezoidal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1=(TextView)getActivity().findViewById(R.id.tx1);
        t2=(TextView)getActivity().findViewById(R.id.tx2);
        display=(TextView)getActivity().findViewById(R.id.tx3);
        e1=(EditText)getActivity().findViewById(R.id.edt1);
        e2=(EditText)getActivity().findViewById(R.id.edt2);
        button=(Button)getActivity().findViewById(R.id.xy);
        resultButton=(Button)getActivity().findViewById(R.id.b2);

        i=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=Integer.parseInt(e1.getText().toString());
                String s=e2.getText().toString();
                String[] ss=s.split(",");
                x[i]=Float.parseFloat(ss[0]);
                y[i]=Float.parseFloat(ss[1]);
                i++;
                e2.setText("");
                if(i==n){
                    button.setVisibility(View.GONE);
                }
            }
        });
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h=x[1]-x[0];
                n=n-1;
                for(i=0;i<n;i++)
                {
                    if(k==0)
                    {
                        sum = sum + y[i];
                        k=1;
                    }
                    else
                        sum = sum + 2 * y[i];
                }
                sum = sum + y[i];
                sum = sum * (h/2);
                display.setVisibility(View.VISIBLE);
                display.setText("integration result = "+sum);
            }
        });
    }
}
