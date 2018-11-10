package com.franciscoolivero.android.booklisting;

/**
 * Created by franciscoolivero on 10/30/18.
 * This is my code for Udacity.
 */

import java.util.ArrayList;

/**
 * An {@link Book} object contains information related to a single Book fetched from the Google Book API
 */

public class Book {
    /**
     * Tittle of the book
     */
    private String mTittle;
    /**
     * List of the Authors of the book
     * Initialize as null in case the book has no Author (JSON may not return this variable).
     */
    private ArrayList<String> mAuthors;
    /**
     * Description of the book
     */
    private String mDescription;
    /**
     * Google Books Website for this book
     */
    private String mWeb;
    /**
     * Image String containing the URI to fetch the cover large thumbnail for the book;
     */
    private String mImage;
    /**
     * Year in which the book was published
     */
    private String mYear;

    /**
     * Constructs a new {@link Book} object.
     *
     * @param mTittle      is the tittle of the Book.
     * @param mAuthors      is the author of the Book.
     * @param mWeb         is the Google Books website of the Book.
     * @param mImage       is the Image URI associated with the Book, stored in a String.
     * @param mDescription is the Description of the Book.
     * @param mYear        is the year in which the Book was published.
     */

    public Book(String mTittle, ArrayList<String> mAuthors, String mDescription, String mWeb, String mImage, String mYear) {
        this.mTittle = mTittle;
        this.mAuthors = mAuthors;
        this.mDescription = mDescription;
        this.mWeb = mWeb;
        this.mImage = mImage;
        this.mYear = mYear;
    }

    /**
     * @return the Tittle of the Book.
     */
    public String getmTittle() {
        return mTittle;
    }

    /**
     * @return the Author of the Book.
     */
    public ArrayList<String> getmAuthors() {
        return mAuthors;
    }

    /**
     * @return the Description of the Book.
     */
    public String getmDescription() {
        return mDescription;
    }

    /**
     * @return the Google Books Website of the Book.
     */

    public String getmWeb() {
        return mWeb;
    }

    /**
     * @return the year in which the Book was published.
     */
    public String getmYear() {
        return mYear;
    }

    /**
     * @return the Image String containing the URL of the Book.
     */
    public String getmImage() {
        return mImage;
    }

    public boolean hasAuthor(){
        return mAuthors != null;
    }

}
