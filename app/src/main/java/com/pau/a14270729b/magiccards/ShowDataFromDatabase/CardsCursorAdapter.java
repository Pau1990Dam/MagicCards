package com.pau.a14270729b.magiccards.ShowDataFromDatabase;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.Pojos.Card;
import com.pau.a14270729b.magiccards.R;
import com.pau.a14270729b.magiccards.databinding.CartasFilaBinding;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by pau on 15/11/16.
 */

public class CardsCursorAdapter extends CupboardCursorAdapter<Card> {

    public CardsCursorAdapter(Context context, Class<Card> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Card model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        CartasFilaBinding binding = DataBindingUtil.inflate(inflater, R.layout.cartas_fila, parent, false);
        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Card model) {
        CartasFilaBinding binding = DataBindingUtil.getBinding(view);
        binding.carta.setText(model.getName());
        binding.type.setText(model.getType());
        binding.rarity.setText(model.getRarity());
        binding.text.setText(model.getText());
        Glide.with(context).load(model.getImageUrl()).bitmapTransform(
                new RoundedCornersTransformation(context,14,1)).error(R.mipmap.ic_launcher).into(binding.imgUrl);
    }
}
