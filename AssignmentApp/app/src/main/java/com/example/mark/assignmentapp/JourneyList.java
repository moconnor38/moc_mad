package com.example.mark.assignmentapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mark.assignmentapp.JourneyInfo;
import com.example.mark.assignmentapp.R;

import java.util.List;

public class JourneyList extends ArrayAdapter<JourneyInfo> {

    private Activity context;
    private List<JourneyInfo> journeyInfoList;

    public JourneyList(Activity context, List<JourneyInfo> journeyInfoList) {
        super(context, R.layout.list_layout, journeyInfoList);
        this.context = context;
        this.journeyInfoList = journeyInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewStart = (TextView)listViewItem.findViewById(R.id.journey_start_textview);
        TextView textViewEnd = (TextView)listViewItem.findViewById(R.id.journey_end_textview);

        JourneyInfo journeyInfo = journeyInfoList.get(position);

        textViewStart.setText(journeyInfo.getJourneyStart());
        textViewEnd.setText(journeyInfo.getJourneyEnd());

        return listViewItem;
    }

}
