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
public class GaussCentral extends Fragment {


    static final int max=30;
    float table[][]=new float[max][max];
    int count, mid, i, j;
    float x, n, h, y, p ;
    EditText degree,xy,xx;
    Button button,next;
    TextView display;
    public GaussCentral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gauss_central, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        degree=(EditText)getActivity().findViewById(R.id.GeditText);
        display=(TextView)getActivity().findViewById(R.id.Gtextview4);
        xy=(EditText)getActivity().findViewById(R.id.GeditText2);
        xx=(EditText)getActivity().findViewById(R.id.GeditText3);
        button=(Button)getActivity().findViewById(R.id.Gbutton);
        next=(Button)getActivity().findViewById(R.id.xy);
        i=0;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=Integer.parseInt(degree.getText().toString());
                String s=xy.getText().toString();
                String[] ss=s.split(",");
                if(i<=2*count-2){
                    table[i][0]=Float.parseFloat(ss[0]);
                    table[i][1]=Float.parseFloat(ss[1]);
                    i=i+2;
                    xy.setText("");
                }
                if(i>2*count-2){
                    next.setVisibility(View.GONE);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=Float.parseFloat(xx.getText().toString());
                //constructing table
                for(i=2; i<=count; i++)
                    for(j=i-1, n=1; n<=count+1-i; j+=2, n++)
                    {
                        table[j][i]=table[j+1][i-1] - table[j-1][i-1];
                    }
                ///finding middle
                if(count%2==1)
                    mid=count-1;
                else
                    mid=count;

                h=table[mid+2][0] - table[mid][0] ;
                n=p=(x-table[mid][0])/h;

                if(x>table[mid][0])
                {
                   // System.out.print("\n APPLYING GAUSS FORWARD CENTRAL DIFFERENCE FORMULA \n");

                    y=table[mid][1] + n*table[mid+1][2];

                    for(i=3; i<=count; i++)
                        if(i%2==1)
                        {
                            n*=(p-(int)i/2)/(i-1);
                            y+=n*table[mid][i];
                        }
                        else
                        {
                            n*=(p+(int)i/2 -1)/(i-1);
                            y+=n*table[mid+1][i];
                        }
                }
                else
                {
                   // System.out.print("\n------ APPLYING GAUSS BACKWARD CENTRAL DIFFERENCE FORMULA ------\n");

                    y=table[mid][1] + n*table[mid-1][2];

                    for(i=3; i<=count; i++)
                        if(i%2==1)
                        {
                            n*=(p+(int)i/2)/(i-1);
                            y+=n*table[mid][i];
                        }
                        else
                        {
                            n*=(p-(int)i/2 +1)/(i-1);
                            y+=n*table[mid-1][i];
                        }
                }
                display.setVisibility(View.VISIBLE);
                display.setText("At x = "+x+" ,"+" y = "+y);

            }
        });

    }
}
