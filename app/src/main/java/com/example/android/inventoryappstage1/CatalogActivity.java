package com.example.android.inventoryappstage1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryappstage1.data.BookContract.BookEntry;
import com.example.android.inventoryappstage1.data.BookDbHelper;

/**
 * Displays list of books that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    /**
     * Database instances
     **/
    private SQLiteDatabase db_read;
    private SQLiteDatabase db_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        /** Database helper that will provide us access to the database */
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Gets the database in both read & write mode
        db_read = mDbHelper.getReadableDatabase();
        db_write = mDbHelper.getWritableDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the books database.
     */
    private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRODUCT_PRICE,
                BookEntry.COLUMN_PRODUCT_QUANTITY,
                BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER};

        // Perform a query on the books table
        Cursor cursor = db_read.query(
                BookEntry.TABLE_NAME, // The table to query
                projection, // The columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // Don't group the rows
                null, // Don't filter by row groups
                null); // The sort order

        TextView displayView = findViewById(R.id.text_view_book);

        try {
            // Create a header in the Text View that looks like this:
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            displayView.append(BookEntry._ID + " - " +
                    BookEntry.COLUMN_PRODUCT_NAME + " - " +
                    BookEntry.COLUMN_PRODUCT_PRICE + " - " +
                    BookEntry.COLUMN_PRODUCT_QUANTITY + " - " +
                    BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME + " - " +
                    BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                double currentPrice = cursor.getDouble(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentSupplierPhoneNumber = cursor.getInt(supplierPhoneNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhoneNumber));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded book data into the database. For debugging purposes only.
     */
    private void insertBook() {
        // Create a ContentValues object where column names are the keys,
        // and new book attributes are the values.
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, "Book of Science");
        values.put(BookEntry.COLUMN_PRODUCT_PRICE, 22.99);
        values.put(BookEntry.COLUMN_PRODUCT_QUANTITY, 2);
        values.put(BookEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Secret Book Store");
        values.put(BookEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, "07438901544");

        // Insert a new row in the database, returning the ID of that new row.
        // The first argument for db.insert() is the books table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the data for the new row.
        db_write.insert(BookEntry.TABLE_NAME, null, values);
    }

    /**
     * Method to delete all books data from the database.
     */
    private void deleteAllEntries() {
        // Delete all rows in book table
        // The first argument for db.delete() is the book table name.
        db_write.delete(BookEntry.TABLE_NAME, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertBook();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllEntries();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
