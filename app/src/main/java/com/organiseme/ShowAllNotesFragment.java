package com.organiseme;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class ShowAllNotesFragment extends Fragment {


    public ShowAllNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Dodaj notatke");

        View view = inflater.inflate(R.layout.fragment_show_all_notes, container, false);
        TextView showTopicNote = (TextView) view.findViewById(R.id.textView2);
        TextView showNoteContent = (TextView) view.findViewById(R.id.textView5);






        // Inflate the layout for this fragment
        return view;
    }
}
