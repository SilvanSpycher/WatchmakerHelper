package ch.watchmaker.watchmakerhelper.model;

import java.util.List;

import ch.watchmaker.watchmakerhelper.model.adapters.Cogwheel;

/**
 * Created by silva on 09.02.2018.
 */

public class Result {
    private List<Cogwheel> cogs;
    private double error;

    public Result(List<Cogwheel> cogs, double error) {
        this.cogs = cogs;
        this.error = error;
    }

    public List<Cogwheel> getCogs() {
        return cogs;
    }

    public double getError() {
        return error;
    }
}
