package com.example.jaimin.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaimin on 4/18/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "EmployeeManager";


    static class EmployeeTable {
        private static final String TABLE_EMPLOYEE = "employee";

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_PHONE = "phone";

        private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE "
                + TABLE_EMPLOYEE + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT" + ")";
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EmployeeTable.CREATE_TABLE_EMPLOYEE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EmployeeTable.TABLE_EMPLOYEE);

        //create new Tables
        onCreate(db);
    }


    long addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeTable.COLUMN_NAME, employee.get_name());
        values.put(EmployeeTable.COLUMN_PHONE, employee.get_phone_number());

        long count = db.insert(EmployeeTable.TABLE_EMPLOYEE, null, values);
        db.close();
        return count;
    }

    public Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(EmployeeTable.TABLE_EMPLOYEE, new String[]{
                        EmployeeTable.COLUMN_ID,
                        EmployeeTable.COLUMN_NAME,
                        EmployeeTable.COLUMN_PHONE}, EmployeeTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            Employee employee = new Employee(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_ID))),
                    cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_PHONE)));
            return employee;
        }
        return null;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + EmployeeTable.TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_ID))));
                employee.set_name(cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_NAME)));
                employee.set_phone_number(cursor.getString(cursor.getColumnIndex(EmployeeTable.COLUMN_PHONE)));

                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        return employeeList;
    }

    public int getEmployeeCount() {
        String countQuery = "SELECT * FROM " + EmployeeTable.TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeTable.COLUMN_NAME, employee.get_name());
        values.put(EmployeeTable.COLUMN_PHONE, employee.get_phone_number());

        return db.update(EmployeeTable.TABLE_EMPLOYEE, values, EmployeeTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(employee.get_id())});
    }

    public void deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EmployeeTable.TABLE_EMPLOYEE, EmployeeTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }


}
