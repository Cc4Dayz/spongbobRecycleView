package com.example.spongebobrecyview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//  Student Name: 	Ofek Atun
//  Student ID: 	316063015
//  GitHub Link: 	https://github.com/Cc4Dayz/spongbobRecycleView
public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<CharactersModel> charactersModels = new ArrayList<>();
    int[] charactersImages = {R.drawable.spb, R.drawable.patrick, R.drawable.krabs,
                                R.drawable.squid,R.drawable.plangton,R.drawable.sandy,
                                R.drawable.gary,R.drawable.puffs,R.drawable.mm,
                                R.drawable.bb, R.drawable.karen, R.drawable.pearl,
                                R.drawable.tfd, R.drawable.manray, R.drawable.dirtybubble,
                                R.drawable.larry, R.drawable.harold, R.drawable.margaret,
                                R.drawable.grandpands, R.drawable.bubblebuddy, R.drawable.neptun,
                                R.drawable.bubblebass};

    SearchView searchView;
    CM_RecyclerViewAdapter adapter;
    private Dialog popupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.SearchView);
        searchView.clearFocus();

        popupDialog = new Dialog(this);
        popupDialog.setContentView(R.layout.rv_popup);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText );
                return true;
            }
        });

        RecyclerView charactersRecyclerView = findViewById(R.id.CharactersRecyclerView);

        setUpCharactersModels();

        adapter = new CM_RecyclerViewAdapter(this, charactersModels, this);

        charactersRecyclerView.setAdapter(adapter);
        charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fileList(String text) {
        ArrayList<CharactersModel> filtedList = new ArrayList<CharactersModel>();

        for(CharactersModel charactersModel : charactersModels)
        {
            if(charactersModel.getcName().toLowerCase().contains(text.toLowerCase()))
            {
                filtedList.add(charactersModel);
            }
            if(charactersModel.getcShortTxt().toLowerCase().contains(text.toLowerCase()))
            {
                if(!filtedList.contains(charactersModel))
                    filtedList.add(charactersModel);
            }
        }

        if(filtedList.isEmpty())
            Toast.makeText(this, "Character not found.", Toast.LENGTH_SHORT).show();
        else
        {
            adapter.setFilteredList(filtedList);
        }
    }

    private void setUpCharactersModels()
    {
        String[] charactersNames = getResources().getStringArray(R.array.Names_txt);
        String[] shortDetails = getResources().getStringArray(R.array.ShortDetails_txt);
        String[] longDetails = getResources().getStringArray(R.array.LongDetails_txt);

        for (int i = 0; i < charactersNames.length; i++)
        {
            charactersModels.add(new CharactersModel(charactersNames[i],
                                                        shortDetails[i],
                                                        longDetails[i],
                                                        charactersImages[i]));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Check if the popup dialog is showing
        if (popupDialog != null && popupDialog.isShowing()) {
            // Dismiss the dialog
            popupDialog.dismiss();

            // Show the dialog again to update its layout
            popupDialog.show();
        }
    }

    @Override
    public void onItemClick(int pos) {
        // Retrieve the views in the popup layout
        ImageView popImage = popupDialog.findViewById(R.id.pop_image);
        TextView popName = popupDialog.findViewById(R.id.pop_name);
        TextView popLongTxt = popupDialog.findViewById(R.id.pop_longtxt);

        // Set data to the views based on the clicked item
        CharactersModel clickedCharacter = charactersModels.get(pos);
        popImage.setImageResource(clickedCharacter.getImage());
        popName.setText(clickedCharacter.getcName());
        popLongTxt.setText(clickedCharacter.getcLongTxt());

        // Show the popup dialog
        popupDialog.show();

        // Handle back button click in the popup
        Button popBackBtn = popupDialog.findViewById(R.id.pop_backBtn);
        popBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup dialog when back button is clicked
                popupDialog.dismiss();
            }
        });
    }
}