package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.R;

import java.util.List;

import Pojos.Card;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 14270729b on 28/10/16.
 */

public class CardAdapter extends ArrayAdapter<Card> {
    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Card card = getItem(position);
        Log.i("", card.toString());

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cartas_fila, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.carta);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView rarity = (TextView) convertView.findViewById(R.id.rarity);
        TextView text = (TextView) convertView.findViewById(R.id.text);

        ImageView imgUrl = (ImageView) convertView.findViewById(R.id.imgUrl);

        name.setText(card.getName());
        type.setText(card.getType());
        rarity.setText(card.getRarity());
        text.setText(card.getText());
        Glide.with(getContext()).load(card.getImageUrl()).bitmapTransform(
                new RoundedCornersTransformation(getContext(),14,1)).into(imgUrl);

        return convertView;
    }
}
