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

public class SimpsonRule extends Fragment {

    TextView t1,t2,display;
    EditText e1,e2;
    Button button,resultButton;
    int n,i,j,k=0;
    float x[]=new float[20],y[]=new float[20],h,sum=0;

    public SimpsonRule() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simpson_rule, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1=(TextView)getActivity().findViewById(R.id.tx_1);
        t2=(TextView)getActivity().findViewById(R.id.tx_2);
        display=(TextView)getActivity().findViewById(R.id.tx_3);
        e1=(EditText)getActivity().findViewById(R.id.edt_1);
        e2=(EditText)getActivity().findViewById(R.id.edt_2);
        button=(Button)getActivity().findViewById(R.id.xy_);
        resultButton=(Button)getActivity().findViewById(R.id.b_2);

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
                sum = sum + y[0];
                for(i=1;i<n;i++)
                {
                    if(k==0)
                    {
                        sum = sum + 4 * y[i];
                        k=1;
                    }
                    else
                    {
                        sum = sum + 2 * y[i];
                        k=0;
                    }
                }
                sum = sum + y[i];
                sum = sum * (h/3);
                display.setVisibility(View.VISIBLE);
                display.setText("integration result = "+sum);
            }
        });
    }
}
