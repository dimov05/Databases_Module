package entities.football_betting;


import javax.persistence.*;
import java.util.Set;

@Embeddable
@Table(name = "bet_games")
public class BetGame {
    private Set<Game> game;
    private Set<Bet> bet;
    private ResultPrediction resultPrediction;

    public BetGame() {

    }

    @MapsId
    @ManyToMany
    public Set<Game> getGame() {
        return game;
    }

    public void setGame(Set<Game> game) {
        this.game = game;
    }

    @MapsId
    @ManyToMany
    public Set<Bet> getBet() {
        return bet;
    }

    public void setBet(Set<Bet> bet) {
        this.bet = bet;
    }

    @ManyToOne
    @JoinColumn(name = "result_prediction", referencedColumnName = "id")
    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
