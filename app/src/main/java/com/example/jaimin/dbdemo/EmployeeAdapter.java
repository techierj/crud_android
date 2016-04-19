package com.example.jaimin.dbdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaimin on 4/19/2016.
 */
public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<Employee> employees;

    EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employees.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linearView;

        if (convertView == null) {
            linearView = new View(context);
            linearView = inflater.inflate(R.layout.list_view_custom,null);

            TextView textView = (TextView) linearView.findViewById(R.id.employee_data_txt);
            textView.setText(employees.get(position).get_name());

        }else {
            linearView = (View) convertView;
        }

        return linearView;
    }
}
