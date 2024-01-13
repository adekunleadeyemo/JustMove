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
import android.widget.TextView;

import com.logistics.justMove.Models.JustMoveTime;
import com.logistics.justMove.Utils.JustMoveDateFormat;
import com.logistics.justMove.Utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetDateFragment extends Fragment implements RecyclerViewInterface {

    int row_index = -1;
    RecyclerView recyclerView;
    List<JustMoveTime> dayDates;

    TextView currDate;

    TextView timeTxt;

    TextView pk_text;
    int prevPos = -1;

    MyAdapter myAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetDateFragment() {
        // Required empty public constructor
    }

    public SetDateFragment(TextView currDate, TextView timeTxt, TextView pk_text) {
        this.currDate = currDate;
        this.timeTxt = timeTxt;
        this.pk_text = pk_text;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetDateFragment newInstance(String param1, String param2) {
        SetDateFragment fragment = new SetDateFragment();
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
        return inflater.inflate(R.layout.fragment_set_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();
        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(getContext(), this);
        myAdapter.setMyDates(dayDates);
        myAdapter.setType("date");
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    private void dataInitialize(){
        dayDates = new ArrayList<JustMoveTime>();
        JustMoveDateFormat jmf = new JustMoveDateFormat();
        dayDates.addAll(jmf.GetJustMoveDates());

    }

    @Override
    public void onItemClick(int position, String event) {
        if(prevPos != position && event.equals("click")){
            currDate.setText(dayDates.get(position).getLabel());
            currDate.setTextSize(30);
            dayDates.get(position).setBackground(R.color.white);
            if(prevPos != -1) {
                dayDates.get(prevPos).setBackground(R.color.lugg_white_dark);
            }
            prevPos = position;
            myAdapter.notifyDataSetChanged();
            timeTxt.setVisibility(View.VISIBLE);
            pk_text.setVisibility(View.VISIBLE);
        }

    }
}