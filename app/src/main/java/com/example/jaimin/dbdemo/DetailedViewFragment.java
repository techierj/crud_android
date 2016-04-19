package com.example.jaimin.dbdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailedViewFragment extends Fragment {

    public DetailedViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_detailed_view, container, false);

        Intent intent = this.getActivity().getIntent();
        //Toast.makeText(this.getContext(),""+intent.getExtras().get("id"),Toast.LENGTH_LONG).show();

        DatabaseHandler db = new DatabaseHandler(this.getContext());
        Employee employee = db.getEmployee((Integer) intent.getExtras().get("id"));

        TextView name = (TextView) rootView.findViewById(R.id.detail_name_txt);
        name.setText(employee.get_name());

        return rootView;
    }
}
