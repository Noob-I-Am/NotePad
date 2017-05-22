package com.example.android.notepad;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class NoteSearch extends Activity {
    private SearchView searchView;
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_search);
        searchView=(SearchView)findViewById(R.id.searchview);
        listView=(ListView)findViewById(R.id.listview);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.search_item, mCursor,
                new String[] {NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_CREATE_DATE}, new int[] { R.id.search_text1,R.id.search_text2 });
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()) {

                    String selection = NotePad.Notes.COLUMN_NAME_TITLE + " LIKE '%" + newText + "%' ";
                    Intent intent = getIntent();

                    // If there is no data associated with the Intent, sets the data to the default URI, which
                    // accesses a list of notes.
                    if (intent.getData() == null) {

                        intent.setData(NotePad.Notes.CONTENT_URI);
                    }
                    mCursor = managedQuery(
                            getIntent().getData(),            // Use the default content URI for the provider.
                            NotesList.PROJECTION,                       // Return the note ID and title for each note.
                            selection,                             // No where clause, return all records.
                            null,                             // No where clause, therefore no where column values.
                            NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                    );
                    simpleCursorAdapter.swapCursor(mCursor);
                    listView.setAdapter(simpleCursorAdapter);
                }
                else {

                    String[] lstSource = {};
                    ArrayAdapter adapter = new ArrayAdapter(NoteSearch.this,android.R.layout.simple_list_item_1,lstSource);
                    listView.setAdapter(adapter);
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Constructs a new URI from the incoming URI and the row ID
                Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

                // Gets the action from the incoming Intent
                String action = getIntent().getAction();

                // Handles requests for note data
                if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {

                    // Sets the result to return to the component that called this Activity. The
                    // result contains the new URI
                    setResult(RESULT_OK, new Intent().setData(uri));
                } else {

                    // Sends out an Intent to start an Activity that can handle ACTION_EDIT. The
                    // Intent's data is the note ID URI. The effect is to call NoteEdit.
                    startActivity(new Intent(Intent.ACTION_EDIT, uri));
                }
            }
        });
    }
}
