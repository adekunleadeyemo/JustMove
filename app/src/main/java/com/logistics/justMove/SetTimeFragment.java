package com.logistics.justMove;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logistics.justMove.Models.JustMoveTime;
import com.logistics.justMove.Utils.JustMoveDateFormat;
import com.logistics.justMove.Utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetTimeFragment extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView currTime;
    List<JustMoveTime> daytimes;
    RecyclerView recyclerView2;

    int prevPos = -1;

    MyAdapter myAdapter2;

    TextView timeTxt;
    TextView pk_txt;


    public SetTimeFragment() {
        // Required empty public constructor
    }

    public SetTimeFragment(TextView currTime, TextView timeTxt, TextView pk_txt) {
        // Required empty public constructor
        this.currTime = currTime;
        this.timeTxt = timeTxt;
        this.pk_txt = pk_txt;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetTimeFragment newInstance(String param1, String param2) {
        SetTimeFragment fragment = new SetTimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_time, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerView2 = view.findViewById(R.id.recylerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setHasFixedSize(true);
        myAdapter2 = new MyAdapter(getContext(),this);
        myAdapter2.setMyDates(daytimes);
        myAdapter2.setType("date");
        recyclerView2.setAdapter(myAdapter2);
        myAdapter2.notifyDataSetChanged();

    }

    private void dataInitialize(){
        daytimes = new ArrayList<>();

        for(int i=8; i<11; i++ ){
            daytimes.add(new JustMoveTime("between "+i+"am - "+(i+1)+"am",i+"am - "+(i+1)+"am",R.color.lugg_white_dark));
        }
        daytimes.add(new JustMoveTime("between 12pm - 1pm","12pm - 1pm",R.color.lugg_white_dark));
        for(int i=2; i<9; i++ ){
            daytimes.add(new JustMoveTime("between "+i+"pm - "+(i+1)+"pm",i+"pm - "+(i+1)+"pm",R.color.lugg_white_dark));
        }

    }

    @Override
    public void onItemClick(int position, String event) {

        if(prevPos != position && event.equals("click")){
            currTime.setText(daytimes.get(position).getLabel());
            daytimes.get(position).setBackground(R.color.lugg_button);
            daytimes.get(position).setTextColor("#F3E6F3");
            if(prevPos != -1) {
                daytimes.get(prevPos).setBackground(R.color.lugg_white_dark);
                daytimes.get(prevPos).setTextColor("#274378");
            }
            prevPos = position;
            myAdapter2.notifyDataSetChanged();
            timeTxt.setVisibility(View.VISIBLE);
            pk_txt.setVisibility(View.VISIBLE);
        }


    }

}