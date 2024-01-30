/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tolo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author esrom
 */
public class Bet {
    private int[] numbers = new int[4];
    private double betAmount;
    
    public Bet(int[] numbers, double betAmount) {
        this.numbers = numbers;
        this.betAmount = betAmount;
    }
    
    public int[] getNumbers() {
        return this.numbers;
    }
    
    public double getBetAmount() {
        return this.betAmount;
    }
    
    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
    
    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public BigInteger factor(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factor(n - 1));
    }
    
    public BigInteger combinatorix(int n, int k) {
        return factor(n).divide(factor(n - k).multiply(factor(k)));
    }
    
    public BigDecimal computeOdd(int n) {
        BigDecimal result = new BigDecimal(combinatorix(4, n).multiply(combinatorix(16, 4 - n)).toString());
        result = result.divide(new BigDecimal(combinatorix(20, 4).toString()), 10, RoundingMode.HALF_UP);
        return result;
    }

    public Map<Double, BigDecimal> betOdds() {
        Map<Double, BigDecimal> odds = new HashMap<>();
        odds.put(this.betAmount*0, computeOdd(0).add(computeOdd(1)).add(computeOdd(2)));
        odds.put(this.betAmount*5, computeOdd(3));
        odds.put(this.betAmount*50, computeOdd(4));
        return odds;
    }
    
    public double computeGain(Tolo t) {
        double gain = 0;
        ArrayList<Integer> playerNumbers = new ArrayList<>();
        for (int i = 0; i < this.numbers.length; i++) {
            playerNumbers.add(this.numbers[i]);
        }
        for (int i = 0; i < t.getDrawnNumbers().size() - 1; i++) {
            for (int j = 0; j < playerNumbers.size(); j++) {
                if (Objects.equals(playerNumbers.get(j), t.getDrawnNumbers().get(i))) {
                    gain++;
                    playerNumbers.remove(j);
                }
            }
        }
        if (gain == 3) {
            return 5*this.betAmount;
        } else if (gain == 4) {
            return 50*this.betAmount;
        }
        return 0;
    }
}
