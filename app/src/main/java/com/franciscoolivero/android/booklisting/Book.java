package com.franciscoolivero.android.booklisting;

/**
 * Created by franciscoolivero on 10/30/18.
 * This is my code for Udacity.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * An {@link Book} object contains information related to a single Book fetched from the Google Book API
 */

public class Book implements Parcelable {
    /**
     * Tittle of the book
     */
    private String mTitle;
    /**
     * List of the Authors of the book
     * Initialize as null in case the book has no Author (JSON may not return this variable).
     */
    private ArrayList<String> mAuthors;
    /**
     * Google Books Info Website for this book
     */
    private String mInfoLink;
    /**
     * listPrice of the book.
     * Some books may not be saleable, if so, this is kept null.
     */
    private String mListPrice;

    /**
     * Currency Code in which the Book price is displayed.
     */
    private String mCurrencyCode;

    /**
     * Rating of the Book. This may return null.
     */

    private String mRating;


    /**
     * Constructs a new {@link Book} object.
     * @param mTitle      is the tittle of the Book.
     * @param mAuthors      is the author of the Book.
     * @param mInfoLink         is the Google Books website of the Book.
     * @param mListPrice  is the List Price of the book.
     * @param mCurrencyCode is the currency code for the book sale.
     * @param mRating is the average rating of the book.
     *
     */

    //Published Date removed, add if needed.
    public Book(String mTitle, @Nullable ArrayList<String> mAuthors, String mInfoLink, @Nullable String mListPrice, @Nullable String mCurrencyCode, @Nullable String mRating) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mInfoLink = mInfoLink;
        this.mListPrice = mListPrice;
        this.mCurrencyCode = mCurrencyCode;
        this.mRating = mRating;
    }


    /**
     * @return the Tittle of the Book.
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     * @return the Author of the Book.
     */
    public ArrayList<String> getmAuthors() {
        return mAuthors;
    }

    /**
     * @return the Google Books Website of the Book.
     */

    public String getmInfoLink() {
        return mInfoLink;
    }

//    /**
//     * @return the year in which the Book was published.
//     */
//    public String getmPublishedDate() {
//        return mPublishedDate;
//    }


    /**
     * @return the ListPrice of the book.
     */
    public String getmListPrice() {
        return mListPrice;
    }

    /**
     * @return the Currency Code for the published book.
     */
    public String getmCurrencyCode() {
        return mCurrencyCode;
    }

    /**
     * @return the average rating of the book.
     */
    public String getmRating() {
        return mRating;
    }

    public boolean hasAuthor(){
        return mAuthors != null;
    }

    public boolean isSaleable(){
        return mListPrice != null;
    }


    public boolean hasRating(){
        return !mRating.equals("");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeStringList(this.mAuthors);
        dest.writeString(this.mInfoLink);
        dest.writeString(this.mListPrice);
        dest.writeString(this.mCurrencyCode);
        dest.writeString(this.mRating);
    }

    protected Book(Parcel in) {
        this.mTitle = in.readString();
        this.mAuthors = in.createStringArrayList();
        this.mInfoLink = in.readString();
        this.mListPrice = in.readString();
        this.mCurrencyCode = in.readString();
        this.mRating = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
