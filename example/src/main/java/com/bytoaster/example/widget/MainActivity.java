package com.bytoaster.example.widget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bytoaster.widget.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by baptiste on 12/18/14.
 */
public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemClickListener {

    private static final String[] examples = {
            "XmlBasedExample",
            "JavaBasedExample"
    };

    @InjectView(R.id.listView) ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        final BaseAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                examples);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.github:
                final Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/jbaptiste/lettercircleview"));
                startActivity(browserIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
