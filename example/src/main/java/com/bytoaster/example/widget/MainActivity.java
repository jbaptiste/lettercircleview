package com.bytoaster.example.widget;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

/**
 * Created by baptiste on 12/18/14.
 */
public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private static final String[] examples = {
            "XmlBasedExample",
            "JavaBasedExample"
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BaseAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                examples);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(
            final AdapterView<?> parent,
            final View view,
            final int position,
            final long id) {
        final Intent intent = new Intent(this, getActivity(position));
        startActivity(intent);
    }

    private Class<?> getActivity(final int position) {
        try {
            final String example = examples[position];
            return Class.forName("com.bytoaster.example.widget." + example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
