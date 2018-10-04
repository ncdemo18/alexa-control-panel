package com.netcracker.alexa.controlpanel.model;

import org.springframework.stereotype.Component;

@Component
public class FootballScore {
    private int leftPartScore;
    private int rightPartScore;

    public FootballScore(){
        this(0, 0);
    }

    public FootballScore(int leftPartScore, int rightPartScore) {
        this.leftPartScore = leftPartScore;
        this.rightPartScore = rightPartScore;
    }

    public void addLeft() {
        leftPartScore++;
    }

    public void addRight() {
        rightPartScore++;
    }

    public void resetScore() {
        leftPartScore = 0;
        rightPartScore = 0;
    }

    @Override
    public String toString() {
        return leftPartScore + " : " + rightPartScore; 
    }
}
