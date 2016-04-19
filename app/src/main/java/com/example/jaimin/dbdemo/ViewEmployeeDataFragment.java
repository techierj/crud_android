package com.example.jaimin.dbdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewEmployeeDataFragment extends Fragment {

    public ViewEmployeeDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_employee_data, container, false);
        getData(rootView);
        return rootView;
    }

    public void getData(View view) {
        ListView listView = (ListView) view.findViewById(R.id.employee_data_layout);
        final List<Employee> employeeList = new DatabaseHandler(this.getContext()).getAllEmployee();

        listView.setAdapter(new EmployeeAdapter(getContext(), (ArrayList<Employee>) employeeList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(), "Clicked.."+employeeList.get(position).get_id(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), DetailedView.class);
                intent.putExtra("id", employeeList.get(position).get_id());
                startActivity(intent);
            }
        });
    }
}
