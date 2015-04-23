package com.jeff.foodcalculator;

/**
 * Created by Jeff on 4/23/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseConnector
{
    // database name
    private static final String DATABASE_NAME = "BradleyDB";

    private SQLiteDatabase database; // for interacting with the database
    private DatabaseOpenHelper databaseOpenHelper; // creates the database

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    }

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    }

    // inserts data based on food entered
    public long insertFoodEntry(String title, String director, String year)
    {
        ContentValues newFood = new ContentValues();
        newFood.put("title", title);
        newFood.put("director", director);
        newFood.put("year", year);

        open(); // open the database
        long rowID = database.insert("daily", null, newFood);
        close(); // close the database
        return rowID;
    }

    // updates an existing food entry
    public void updateFoodEntry(long id, String title, String director,
                              String year)
    {
        ContentValues editFood = new ContentValues();
        editFood.put("title", title);
        editFood.put("director", director);
        editFood.put("year", year);

        open(); // open the database
        database.update("daily", editFood, "_id=" + id, null);
        close(); // close the database
    }

    // return a Cursor with all contact names in the database
    public Cursor getAllEntries()
    {
        return database.query("daily", new String[] {"_id", "title"},
                null, null, null, null, "title");
    }

    // return a Cursor containing specified contact's information
    public Cursor getOneEntry(long id)
    {
        return database.query(
                "daily", null, "_id=" + id, null, null, null, null);
    }

    // delete the contact specified by the given String name
    public void deleteEntry(long id)
    {
        open(); // open the database
        database.delete("daily", "_id=" + id, null);
        close(); // close the database
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // constructor
        public DatabaseOpenHelper(Context context, String title,
                                  CursorFactory factory, int version)
        {
            super(context, title, factory, version);
        }

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE daily" +
                    "(_id integer primary key autoincrement," +
                    "title TEXT, director TEXT, year TEXT);";

            db.execSQL(createQuery); // execute query to create the database
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    } // end class DatabaseOpenHelper
}
