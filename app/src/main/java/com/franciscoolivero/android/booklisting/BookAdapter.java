package com.franciscoolivero.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        final Book currentBook = getItem(position);
        ViewHolder holder = new ViewHolder(listItemView);

        holder.tittle.setText(currentBook.getmTitle());

        if (currentBook.hasAuthor()) {
            holder.author.setVisibility(View.VISIBLE);

            String authorString = TextUtils.join(", ", currentBook.getmAuthors());
            holder.author.setText(authorString);
        } else {
            holder.author.setVisibility(View.GONE);
        }


        if (currentBook.isSaleable()) {
            holder.amount.setVisibility(View.VISIBLE);
            holder.currencyCode.setVisibility(View.VISIBLE);
            holder.amount.setText(currentBook.getmListPrice());
            holder.currencyCode.setText(currentBook.getmCurrencyCode());
        } else {
            holder.amount.setVisibility(View.GONE);
            holder.currencyCode.setVisibility(View.GONE);
        }

        if (currentBook.hasRating()) {
            holder.rating.setVisibility(View.VISIBLE);
            holder.starRatingImage.setVisibility(View.VISIBLE);
            holder.starRatingImage.setImageDrawable(getContext().getResources().getDrawable(R.drawable.round_star_rate_black_18));
            holder.rating.setText(currentBook.getmRating());
        } else {
            holder.rating.setVisibility(View.GONE);
            holder.starRatingImage.setVisibility(View.GONE);
        }

        return listItemView;
    }

    static class ViewHolder {
        @BindView(R.id.text_tittle)
        TextView tittle;
        @BindView(R.id.text_author)
        TextView author;
        @BindView(R.id.text_rating)
        TextView rating;
        @BindView(R.id.text_price_amount)
        TextView amount;
        @BindView(R.id.text_currency)
        TextView currencyCode;
        @BindView(R.id.image_star)
        ImageView starRatingImage;


        private ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
