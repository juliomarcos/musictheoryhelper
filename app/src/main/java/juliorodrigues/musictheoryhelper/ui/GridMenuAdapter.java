package juliorodrigues.musictheoryhelper.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import juliorodrigues.musictheoryhelper.R;

/**
 *
 */

public class GridMenuAdapter extends BaseAdapter {

    private String[] items;
    private Context context;

    public GridMenuAdapter(Context context) {
        this.context = context;
        items = new String[] {
                context.getString(R.string.tone_calculator)
        };
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardView cardView;
        if (convertView == null) {
            cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.template_main_menu_grid_item, parent, false);
        } else {
            cardView = (CardView) convertView;
        }

        final TextView textView = (TextView) cardView.findViewById(R.id.textView); // TODO: save this find by id in a tag or something...
        textView.setText(items[position]);

        return cardView;
    }
}
