package com.pau.a14270729b.magiccards;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alexvasilkov.events.Events;
import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.databinding.FragmentDetailBinding;
import com.pau.a14270729b.magiccards.provider.cards.CardsColumns;
import com.pau.a14270729b.magiccards.provider.cards.CardsCursor;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        Events.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        View view = binding.getRoot();

        Intent dataCaught = getActivity().getIntent();

        if(dataCaught != null){
            Long card_id = dataCaught.getLongExtra("card_id",-1);

            if(card_id != -1){
                updateFrData(card_id);
            }
        }

        return view;
    }

    @Events.Subscribe("card-selected")
    private void onCardSelected(Long card_id){
        if(card_id!=null)
            updateFrData(card_id);
    }
    private void updateFrData(Long card_id) {

        Cursor cursor = getContext().getContentResolver().query(
                CardsColumns.CONTENT_URI,
                null,
                CardsColumns._ID + " = ?",
                new String[]{String.valueOf(card_id)},
                null
        );

        CardsCursor cardsCursor = new CardsCursor(cursor);
        cardsCursor.moveToNext();

        getActivity().setTitle(cardsCursor.getName().toUpperCase());

        if(!cardsCursor.getImageurl().equals(""))
            Glide.with(getContext()).load(cardsCursor.getImageurl()).bitmapTransform(
                    new RoundedCornersTransformation(getContext(),14,1)).into(binding.imageDetail);
        else
            Glide.with(getContext()).load(R.mipmap.ic_launcher).into(binding.imageDetail);

        binding.power.setText(Html.fromHtml("<b>POWER : </b>"+cardsCursor.getPower()));
        binding.type.setText(Html.fromHtml("<b>TYPE : </b>"+cardsCursor.getType()));
        binding.rarity.setText(Html.fromHtml("<b>RARITY : </b>"+cardsCursor.getRarity()));
        binding.colors.setText(Html.fromHtml("<b>COLORS : </b>"+cardsCursor.getColors()));
        binding.cmc.setText(Html.fromHtml("<b>MANA COST : </b>"+cardsCursor.getCmc()));
        binding.text.setText(Html.fromHtml("<b>INFO : </b>"+cardsCursor.getText()));

    }
}
