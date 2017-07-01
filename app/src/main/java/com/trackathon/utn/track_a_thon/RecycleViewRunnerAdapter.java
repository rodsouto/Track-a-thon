package com.trackathon.utn.track_a_thon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.trackathon.utn.track_a_thon.formatter.Formatter;
import com.trackathon.utn.track_a_thon.model.Runner;
import com.trackathon.utn.track_a_thon.model.RunnerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

class RecycleViewRunnerAdapter extends RecyclerView.Adapter<RunnerViewHolder> {

    private BiConsumer<String, Runner> onClick;
    private HashMap<String, Runner> runners;
    private List<String> keys;

    RecycleViewRunnerAdapter(HashMap<String, Runner> runners, BiConsumer<String, Runner> onClick) {
        this.runners = runners != null ? runners : new HashMap<>();
        this.keys = new ArrayList<>(this.runners.keySet());
        this.onClick = onClick;
    }

    @Override
    public RunnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.runner_card, viewGroup, false);
        return new RunnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RunnerViewHolder runnerViewHolder, int position) {
        String runnerId = this.keys.get(position);
        Runner runner = this.runners.get(runnerId);

        runnerViewHolder.getRunnerName().setText(runner.getName());
        runnerViewHolder.getRunnerSpeed().setText(Formatter.format(runner.getCurrentSpeed(), "Speed: #.## m/s"));
        runnerViewHolder.getRunnerMaxSpeed().setText(Formatter.format(runner.getMaxSpeed(), "Max Speed: #.## m/s"));
        runnerViewHolder.getRunnerAccumulatedDistance().setText(Formatter.format(runner.getAccumulatedDistance() / 1000, "Accumulated distance: #.## km"));
        runnerViewHolder.getRunnerPhoto().setImageResource(R.drawable.ic_race);
        Picasso.with(runnerViewHolder.getCardView().getContext()).load(runner.getImageUrl()).into(runnerViewHolder.getRunnerPhoto());


        runnerViewHolder.getCardView().setOnClickListener((view) -> this.onClick.accept(runnerId, runner));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return runners.size();
    }

}
