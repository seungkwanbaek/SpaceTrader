package com.example.spacetrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> PlayerList = new ArrayList<>();

    private OnPlayerClickListener listener;

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);

        return new PlayerViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player Player = PlayerList.get(position);

        Log.d("APP", "Binding: " + position + " " + PlayerList.get(position));

        holder.PlayerDifficulty.setText(Player.getDifficulty().toString());
        holder.PlayerName.setText(Player.getUserName());
        holder.PlayerShip.setText(Player.getShipType());

        holder.PlayerMajor.setText(Player.get_major().toString());
        holder.PlayerName.setText(Player.getUserName());
        holder.PlayerClass.setText(Player.get_classStanding().toString());


        public String getUserName() { return user_name; }

        public String getDifficulty() { return difficulty; }

        public Integer getSkillPoint(String skill_name) { return skill_points.get(skill_name); }

        public HashMap getSkillPoints() { return skill_points; }

        public String getShipType() { return ship_type; }

        public int getPlayerID() { return playerID; }


    }

    @Override
    public int getItemCount() {
        return PlayerList.size();
    }

    public void setPlayerList(List<Player> Players) {
        PlayerList = Players;
        notifyDataSetChanged();
    }


    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView PlayerName;
        private TextView PlayerMajor;
        private TextView PlayerClass;


        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            PlayerName = itemView.findViewById(R.id.text_Player_name);
            PlayerMajor = itemView.findViewById(R.id.text_Player_major);
            PlayerClass = itemView.findViewById(R.id.text_Player_class);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onPlayerClicked(PlayerList.get(position));
                    }
                }
            });

        }
    }

    public interface OnPlayerClickListener {
        void onPlayerClicked(Player Player);
    }

    public void setOnPlayerClickListener(OnPlayerClickListener listener) {
        this.listener = listener;
    }






}
