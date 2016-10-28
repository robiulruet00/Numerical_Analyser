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
public class Ramanujan extends Fragment {


    final static int ORDER=4;
    final static int MAXN=100;
    public Ramanujan() {
        // Required empty public constructor
    }
    int n,i,j,k;
    float ax[]=new float[MAXN+1],ay[]=new float[MAXN+1],diff[][]=new float[MAXN+1][ORDER+1],nr=1.0f,dr=1,x,p,h,yp;

    EditText degree,xy,xx;
    Button button,next;
    TextView display;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramanujan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        degree=(EditText)getActivity().findViewById(R.id.ReditText);
        display=(TextView)getActivity().findViewById(R.id.Rtextview4);
        xy=(EditText)getActivity().findViewById(R.id.ReditText2);
        xx=(EditText)getActivity().findViewById(R.id.ReditText3);
        button=(Button)getActivity().findViewById(R.id.Rbutton);
        next=(Button)getActivity().findViewById(R.id.xy);
        i=0;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=Integer.parseInt(degree.getText().toString());
                String s=xy.getText().toString();
                String[] ss=s.split(",");
                ax[i]=Float.parseFloat(ss[0]);
                ay[i]=Float.parseFloat(ss[1]);
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
                h=ax[1]-ax[0];

                for (i=0;i<n-1;i++)
                    diff[i][1] = ay[i+1]-ay[i];
                for (j=2;j<=ORDER;j++)
                    for(i=0;i<n-j;i++)
                        diff[i][j] = diff[i+1][j-1] - diff[i][j-1];
                i=0;
                p = (x-ax[i])/h;
                yp = ay[i];

                for (k=1;k<=ORDER;k++)
                {
                    nr *=p-k+1;
                    dr =fact(k);
                    yp +=(nr/dr)*diff[i][k];
                }
                display.setVisibility(View.VISIBLE);
                display.setText("When x = "+x+" ,"+" corresponding y = "+yp);
            }
        });
    }

    float fact(int a){
        float fac = 1;

        if (a == 0)
            return (1);
        else
            fac = a * fact(a-1);

        return(fac);
    }
}
