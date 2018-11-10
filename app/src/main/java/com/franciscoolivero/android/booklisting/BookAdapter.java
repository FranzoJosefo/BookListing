package com.franciscoolivero.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        return super.getView(position, convertView, parent);

        //TODO
    }

    static class ViewHolder{
        @BindView(R.id.text_tittle)
        TextView tittle;
        @BindView(R.id.text_author)
        TextView author;
        //TODO




        private ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
