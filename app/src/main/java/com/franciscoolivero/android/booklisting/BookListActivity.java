package com.franciscoolivero.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    public static final String LOG_TAG = BookListActivity.class.getName();
    public static boolean badResponse = false;
    @BindView(R.id.list)
    ListView bookListView;
    @BindView(R.id.text_empty_state)
    TextView emptyStateView;
    @BindView(R.id.loading_spinner)
    View loadingSpinner;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.text_home_default)
    TextView homeDefaultMessage;

    /**
     * Create a new {@link android.widget.ArrayAdapter} of books.
     */
    private BookAdapter bookAdapter;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;
    private static final String GOOGLE_BOOKS_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private String userQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);



    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create a new adapter that takes an empty list of earthquakes as input
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setEmptyView(emptyStateView);
        //THE CODE BELOW WAS ON ONCREATE
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        // Get the SearchView
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
//                bookAdapter.clear();
//                bookAdapter.notifyDataSetChanged();

                homeDefaultMessage.setVisibility(View.GONE);
                loadingSpinner.setVisibility(View.VISIBLE);
                if (!isConnected()) {
                    loadingSpinner.setVisibility(View.GONE);
                    emptyStateView.setText(R.string.no_inet);

                } else {
                    // Set the adapter on the {@link ListView}
                    // so the list can be populated in the user interface
                    bookListView.setAdapter(bookAdapter);

                    bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Book currentBook = bookAdapter.getItem(position);
                            openWebPage(currentBook);
                        }
                    });
                    userQuery = GOOGLE_BOOKS_BASE_URL + s + "&maxResults=40";
                    startLoader();

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    private void startLoader() {
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        Log.i(LOG_TAG, "Loader will be initialized. If it doesn't exist, create loader, if else reuse.");
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        Log.i(LOG_TAG, "Loader Initialized.");
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "No Loader was previously created, creating new EarthquakeLoader.");
        return new BookLoader(this, userQuery);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        loadingSpinner.setVisibility(View.GONE);
        // Clear the adapter of previous earthquake data
        bookAdapter.clear();
        bookAdapter.notifyDataSetChanged();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        Log.i(LOG_TAG, "Loading finished, add all Earthquakes to adapter so they can be displayed");

        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }

        if (badResponse) {
            emptyStateView.setText(R.string.bad_response);
        } else {
            emptyStateView.setText(R.string.empty_state);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG, "Loader reset, clear the data from adapter");
        loader.reset();
        bookAdapter.clear();
    }

    public void openWebPage(Book book) {
        Uri bookUri = Uri.parse(book.getmInfoLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, bookUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
//    @Override
//    public boolean onQueryTextSubmit(String s) {
//
//
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        return false;
//    }