package com.pau.a14270729b.magiccards;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.pau.a14270729b.magiccards.databinding.FragmentDetailBinding;
import com.pau.a14270729b.magiccards.Pojos.Card;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {


    private View view;
    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        view = binding.getRoot();

        Intent dataCaught = getActivity().getIntent();

        if(dataCaught != null){
            Card card = (Card) dataCaught.getSerializableExtra("card");

            if(card != null){
                updateFrData(card);
            }
        }

        return view;
    }

    private void updateFrData(Card card) {
        Log.i("Carta ","   " +card.toString());

        getActivity().setTitle(card.getName().toUpperCase());

        if(!card.getImageUrl().equals(""))
            Glide.with(getContext()).load(card.getImageUrl()).bitmapTransform(
                new RoundedCornersTransformation(getContext(),14,1)).into(binding.imageDetail);
        else
            Glide.with(getContext()).load(R.mipmap.ic_launcher).into(binding.imageDetail);

        binding.power.setText(Html.fromHtml("<b>POWER : </b>"+card.getPower()));
        binding.type.setText(Html.fromHtml("<b>TYPE : </b>"+card.getType()));
        binding.rarity.setText(Html.fromHtml("<b>RARITY : </b>"+card.getRarity()));
        binding.colors.setText(Html.fromHtml("<b>COLORS : </b>"+card.getColors()));
        binding.cmc.setText(Html.fromHtml("<b>MANA COST : </b>"+card.getCmc()));
        binding.text.setText(Html.fromHtml("<b>INFO : </b>"+card.getText()));



    }
}
