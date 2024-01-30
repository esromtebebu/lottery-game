/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tolo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author esrom
 */
public class SuperBet extends Bet{
    
    private int luckyNum;
    
    public SuperBet(int[] numbers, double betAmount, int luckyNum) {
        super(numbers, betAmount);
        this.luckyNum = luckyNum;
    }
    
    public int getLuckyNum() {
        return this.luckyNum;
    }
    
    public void setLuckyNum(int luckyNum) {
        this.luckyNum = luckyNum;
    }


    public BigDecimal computeSuperOdd(int n, int l) {
        BigDecimal result = new BigDecimal(combinatorix(4, n)
            .multiply(combinatorix(16, 4 - n))
            .multiply(combinatorix(1,l))
            .multiply(combinatorix(9, 1-l))
            .toString());
        result = result.divide(new BigDecimal(combinatorix(20, 4).multiply(combinatorix(10, 1)).toString()), 10, RoundingMode.HALF_UP);
        return result;
    }

    public Map<Double, BigDecimal> betSuperOdds() {
        Map<Double, BigDecimal> odds = new HashMap<>();
        odds.put(getBetAmount()*0, computeSuperOdd(0, 0)
            .add(computeSuperOdd(1, 0))
            .add(computeSuperOdd(2, 0))
            .add(computeSuperOdd(0, 1))
            .add(computeSuperOdd(1, 1))
            .add(computeSuperOdd(2, 1)));
        odds.put(this.getBetAmount()*25, computeSuperOdd(3, 0)
            .add(computeSuperOdd(3, 1)));
        odds.put(this.getBetAmount()*250, computeSuperOdd(4, 0)
            .add(computeSuperOdd(4, 0)));
        return odds;
    }
    
    @Override
    public double computeGain(Tolo t) {
        double gain = super.computeGain(t);
        if (t.getDrawnNumbers().get(t.getDrawnNumbers().size() - 1) == this.luckyNum
           && gain > 0) {
            return 5*gain;
        }
        return gain;
    }
}
