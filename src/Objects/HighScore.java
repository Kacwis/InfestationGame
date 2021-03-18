package Objects;

import Utilites.GlobalStates;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class HighScore implements Serializable, Comparable<HighScore> {

    String nameOfHighScorer;
    long highScore;

    public HighScore(String name, long pointsForCuredPeople, long infectedPeople, long deaths){
        this.nameOfHighScorer = name;
        this.highScore = (pointsForCuredPeople - infectedPeople - deaths) * GlobalStates.INSTANCE.difficultyLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighScore highScore = (HighScore) o;
        return Objects.equals(nameOfHighScorer, highScore.nameOfHighScorer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfHighScorer + GlobalStates.INSTANCE.getSetOfHighScores().size());
    }

    @Override
    public int compareTo(HighScore highScore) {
        return (int)(this.highScore - highScore.highScore);
    }
}
