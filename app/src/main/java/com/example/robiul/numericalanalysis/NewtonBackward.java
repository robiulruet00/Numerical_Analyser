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


/**
 * A simple {@link Fragment} subclass.
 */
public class NewtonBackward extends Fragment {


    public NewtonBackward() {
        // Required empty public constructor
    }
    float ax[]=new float[10],ay[][]=new float[10][10],sum,p,u,temp,x,m;
    int i,n,j,k=0,f;
    EditText degree,xy,xx;
    Button button,next;
    TextView display;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newton_backward, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        degree=(EditText)getActivity().findViewById(R.id.NBeditText);
        display=(TextView)getActivity().findViewById(R.id.NBtextview4);
        xy=(EditText)getActivity().findViewById(R.id.NBeditText2);
        xx=(EditText)getActivity().findViewById(R.id.NBeditText3);
        button=(Button)getActivity().findViewById(R.id.NBbutton);
        next=(Button)getActivity().findViewById(R.id.xy);
        i=0;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=Integer.parseInt(degree.getText().toString());
                String s=xy.getText().toString();
                String[] ss=s.split(",");
                ax[i]=Float.parseFloat(ss[0]);
                ay[k][i]=Float.parseFloat(ss[1]);
                i++;
                xy.setText("");
                if(i==n){
                    next.setVisibility(View.GONE);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=Float.parseFloat(xx.getText().toString());

                for(i=1;i<n;i++)
                {
                    for(j=i;j<n;j++)
                    {
                        ay[i][j]=ay[i-1][j]-ay[i-1][j-1];
                    }
                }

                i=0;
                do
                {
                    if(ax[i]<x && x<ax[i+1])
                        k=1;
                    else
                        i++;
                }while(k != 1);
                f=i+1;
                p=(x-ax[f])/(ax[f]-ax[f-1]);

                n=n-i+1;
                sum=0;
                for(i=0;i<n;i++)
                {
                    temp=1;
                    for(j=0;j<i;j++)
                    {
                        temp = temp * (p + j);
                    }
                    m=fact(i);
                    sum = sum + temp*(ay[i][f]/m);
                }
                display.setVisibility(View.VISIBLE);
                display.setText("When x = "+x+" ,"+" y = "+sum);
            }
        });
    }

    static float fact(int a)
    {
        float fac = 1;

        if (a == 0)
            return (1);
        else
            fac = a * fact(a-1);

        return(fac);
    }
}
