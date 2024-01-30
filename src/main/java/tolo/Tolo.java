/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tolo;

import java.util.Arrays;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author esrom
 */
public class Tolo {
    private ArrayList<Integer> drawnNumbers;
    
    public Tolo() {
        this.drawnNumbers = new ArrayList<>(4);
    }
    
    public ArrayList<Integer> getDrawnNumbers() {
        return this.drawnNumbers;
    }
    
    public void setDrawnNumbers(ArrayList<Integer> drawnNumbers) {
        this.drawnNumbers = drawnNumbers;
    }
    
    public void createBet() {
        ArrayList<Integer> numbersPool = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbersPool.add(i);
        }
        for (int i = 0; i < 4; i++) {
            int drawnNumber = numbersPool.get((int)(Math.random()*(numbersPool.size())));
            this.drawnNumbers.add(drawnNumber);
            numbersPool.remove(numbersPool.indexOf(drawnNumber));
        }
    }
    
    public void createSuperBet() {
        createBet();
        this.drawnNumbers.add((int)(Math.random()*10) + 1);
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        Bet alpha = new Bet(nums, 10);
        SuperBet beta = new SuperBet(nums,10, 1);
        Tolo gamma = new Tolo();
        gamma.createBet();
        System.out.println("Alpha: " + alpha.computeGain(gamma));
        System.out.println("Your numbers:" + Arrays.toString(alpha.getNumbers()) + "\nYour bet amount:" + alpha.getBetAmount());
        System.out.println("Drawn numbers:" + gamma.getDrawnNumbers().toString());
        System.out.println("Odds: " + alpha.betOdds());
        gamma.createSuperBet();
        System.out.println("Your numbers:" + Arrays.toString(beta.getNumbers()) + "\nYour bet amount:" + beta.getBetAmount() + "\nYour lucky number:" + beta.getLuckyNum());
        System.out.println("Drawn numbers:" + gamma.getDrawnNumbers().toString());
        System.out.println("Beta: " + beta.computeGain(gamma));
        System.out.println("Odds: " + beta.betSuperOdds());
    }
}
