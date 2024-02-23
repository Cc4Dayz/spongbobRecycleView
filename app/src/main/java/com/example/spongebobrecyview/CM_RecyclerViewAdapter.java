package com.example.spongebobrecyview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CM_RecyclerViewAdapter extends RecyclerView.Adapter<CM_RecyclerViewAdapter.MyViewHolder> {

    private final  RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<CharactersModel> charactersModels;

    public void setFilteredList(ArrayList<CharactersModel> charactersModels)
    {
        this.charactersModels = charactersModels;
        notifyDataSetChanged();
    }

    public CM_RecyclerViewAdapter(Context context, ArrayList<CharactersModel> charactersModels, RecyclerViewInterface recyclerViewInterface )
    {
        this.context = context;
        this.charactersModels = charactersModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public CM_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new CM_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CM_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textViewName.setText(charactersModels.get(position).getcName());
        holder.textViewShortDetails.setText(charactersModels.get(position).getcShortTxt());
        holder.imageView.setImageResource(charactersModels.get(position).getImage());

        Animation slideAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_anim);
        holder.itemView.startAnimation(slideAnimation);
    }

    @Override
    public int getItemCount() {
        return charactersModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewName;
        TextView textViewShortDetails;
        TextView textViewLongDetails;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rvImage);
            textViewName = itemView.findViewById(R.id.rvName);
            textViewShortDetails = itemView.findViewById(R.id.rvDetails);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
