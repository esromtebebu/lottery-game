package tolo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    // Reusable components
    final double phi = (1 + Math.sqrt(5)) / 2;
    final Image playImg = new Image(getClass().getResourceAsStream("images/play.png"));
    final Image homeImg = new Image(getClass().getResourceAsStream("images/home.png"));
    final Image logoImg = new Image(getClass().getResourceAsStream("images/logo.png"));
    final Image betImg = new Image(getClass().getResourceAsStream("images/bet.png"));
    @Override
    public void start(Stage window) {
        // Home
        GridPane layout1 = new GridPane();
        layout1.setAlignment(Pos.CENTER);
        layout1.setHgap(10);
        layout1.setVgap(10);
        ImageView play1 = new ImageView(playImg);
        play1.setFitHeight(phi*10);
        play1.setFitWidth(phi*10);
        Button playWithoutLuckyNum = new Button("Play", play1);
        Button rules = new Button("About");
        Button statistics = new Button("Statistics");
        Text welcomeText = new Text("Fortuna Lottery");
        welcomeText.setId("welcome-text");
        Button quitHome = new Button("Exit");
        quitHome.setId("quit-home");
        quitHome.setOnAction((event) -> {
            window.close();
        });
        Text copyright = new Text("© Esrom F. Tebebu, 2023");
        copyright.setId("copyright");
        ImageView logo1 = new ImageView(logoImg);
        logo1.setFitHeight(100*phi);
        logo1.setFitWidth(100*phi);
        layout1.add(logo1, 4, 0, 1, 1);
        layout1.add(welcomeText, 0, 2, 8, 1);
        layout1.add(playWithoutLuckyNum, 4, 3, 4, 1);
        // layout1.add(rules, 4, 4, 4, 1);
        // layout1.add(statistics, 4, 5, 4, 1);
        layout1.add(quitHome, 4, 4, 4, 1);
        layout1.add(copyright, 0, 20, 4, 1);
        Scene home = new Scene(layout1, 600*phi, 600);
        window.setScene(home);
        
        // Rules for lottery game without lucky number.
        GridPane rule = new GridPane();
        rule.setAlignment(Pos.CENTER);
        rule.setHgap(10);
        rule.setVgap(10);
        ImageView logoRule = new ImageView(logoImg);
        logoRule.setFitHeight(30*phi);
        logoRule.setFitWidth(30*phi);
        // Button backToHome1 = new Button("",logoRule);
        // logoRule.setId("back-to-home");
        logoRule.setOnMouseClicked((event) -> {
            window.setScene(home);
        }); 
        rule.add(logoRule, 0, 1, 2, 1); 
        Text welcomeText1 = new Text("Fortuna Lottery");
        // welcomeText1.setOnMouseClicked(event -> {
        //     window.setScene(home);
        // });
        welcomeText1.setId("welcome-text");         
        rule.add(welcomeText1, 2, 1, 8, 2);
        rule.add(new Label("Here are the rules for the lottery game with four regular numbers and an optional lucky number:\n*  if you get three out of the four numbers, you receive the quintuple of your bet; \n*  if you get all the numbers, you get your bet multiplied by 50; \n*    and if you chose the optional lucky number, you quintuple your previous gain.\n\nMay Fortuna Fairtrix be with you!"), 0, 3, 12, 5);
        ImageView playRule = new ImageView(playImg);
        playRule.setFitHeight(10*phi);
        playRule.setFitWidth(10*phi);
        Button play = new Button("Play", playRule);
        rule.add(play, 6, 8, 6, 1);
        Scene windowRule = new Scene(rule, 600*phi, 600);
        playWithoutLuckyNum.setOnAction((event) -> {
            window.setScene(windowRule);
        });        
        
        //Window 1
        GridPane layout2 = new GridPane();
        layout2.setAlignment(Pos.CENTER);
        layout2.setHgap(10);
        layout2.setVgap(10);
        layout2.setPadding(new Insets(25, 25, 25, 25));
        Text welcomeText2 = new Text("Fortuna Lottery");
        // welcomeText2.setOnMouseClicked(event -> {
        //     window.setScene(home);
        // });
        welcomeText2.setId("welcome-text");
        ImageView logo2 = new ImageView(logoImg);
        logo2.setFitHeight(30*phi);
        logo2.setFitWidth(30*phi);
        layout2.add(logo2, 0, 1, 2, 1);
        logo2.setOnMouseClicked((event) -> {
            window.setScene(home);
        });
        layout2.add(welcomeText2, 1, 1, 4, 1);
        Label numbersLabel = new Label("Choose four numbers between 1 and 20");
        layout2.add(numbersLabel, 0, 3, 4, 1);
        ArrayList<Integer> numbersPool = new ArrayList<>(), drawnNumbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbersPool.add(i);
        }
        for (int i = 0; i < 4; i++) {
            int drawnNumber = numbersPool.get((int)(Math.random()*(numbersPool.size())));
            drawnNumbers.add(drawnNumber);
            numbersPool.remove(numbersPool.indexOf(drawnNumber));
        }
        TextField number1 = new TextField(drawnNumbers.get(0) + "");
        TextField number2 = new TextField(drawnNumbers.get(1) + "");
        TextField number3 = new TextField(drawnNumbers.get(2) + "");
        TextField number4 = new TextField(drawnNumbers.get(3) + "");
        layout2.add(number1, 0, 4);
        layout2.add(number2, 1, 4);
        layout2.add(number3, 2, 4);
        layout2.add(number4, 3, 4);
        Label numErr = new Label("");
        numErr.setStyle("-fx-text-fill: red;");
        layout2.add(numErr, 0, 5, 2, 1);
        Label luckyNumLabel = new Label("Lucky Number");
        luckyNumLabel.setVisible(false);
        layout2.add(luckyNumLabel, 4, 3, 2, 1);
        TextField luckyNum = new TextField(((int)(Math.random()*10) + 1) + "");
        luckyNum.setVisible(false);
        layout2.add(luckyNum, 4, 4);
        
        CheckBox isSuper = new CheckBox("Super Bet?");
        isSuper.setOnAction(e -> {
            if (isSuper.isSelected()) {
                luckyNumLabel.setVisible(true);
                luckyNum.setVisible(true);
            } else {
                luckyNumLabel.setVisible(false);
                luckyNum.setVisible(false);
            }
        });
        layout2.add(isSuper, 0, 6, 4, 1);
        Label betAmountLabel = new Label("Bet Amount:");
        layout2.add(betAmountLabel, 0, 7);
        TextField betAmount = new TextField();
        layout2.add(betAmount, 1, 7);
        Label betAmountCurrency = new Label(" €");
        layout2.add(betAmountCurrency, 2, 7);
        ImageView betIcon = new ImageView(betImg);
        betIcon.setFitHeight(phi*10);
        betIcon.setFitWidth(10*phi);
        Button drawNumbers = new Button("Draw numbers", betIcon);
        layout2.add(drawNumbers, 4, 7);
        
        //Window 2
        GridPane layout3 = new GridPane();
        layout3.setAlignment(Pos.CENTER);
        layout3.setHgap(10);
        layout3.setVgap(10);
        layout3.setPadding(new Insets(25, 25, 25, 25));   
        Text welcomeText3 = new Text("Fortuna Lottery");
        welcomeText3.setId("welcome-text");
        ImageView logo3 = new ImageView(logoImg);
        logo3.setFitHeight(30*phi);
        logo3.setFitWidth(30*phi);
        logo3.setOnMouseClicked((event) -> {
            window.setScene(home);
        });     
        Label resultTitle = new Label("Tolo Result");
        resultTitle.setId("result");
        
        Label resultNumLabel = new Label("Drawn Numbers");
        Label resultNum = new Label("");
        Label inputNum = new Label("");
        
        Label resultLuckyLabel = new Label("Lucky number");
        Label inputLuckyNum = new Label("");
        Label resultLuckyNum = new Label("");
        
        Label gain = new Label("Gain:");

        ImageView homeIcon3 = new ImageView(homeImg);
        homeIcon3.setFitHeight(10*phi);
        homeIcon3.setFitWidth(phi*10);
        Button home3 = new Button("Back to Menu", homeIcon3);
        home3.setOnAction((event) -> {
            window.setScene(home);
        });
        Button quit3 = new Button("Exit");
        quit3.setId("quit-home");
        quit3.setOnAction((event) -> {
            window.close();
        });
        layout3.add(welcomeText3, 2, 1, 4, 1);
        layout3.add(logo3, 0, 1, 2, 1);
        layout3.add(resultTitle, 4, 2, 4, 1);
        layout3.add(resultNumLabel, 0, 4, 4, 1);
        layout3.add(resultNum, 5, 4, 5, 1);
        layout3.add(new Label("Your numbers"), 0, 5, 4, 1);
        layout3.add(inputNum, 5, 5, 4, 1);
        layout3.add(resultLuckyLabel, 6, 3, 6, 1);
        layout3.add(resultLuckyNum, 6, 4, 4, 1);
        layout3.add(inputLuckyNum, 6, 5, 4, 1);
        layout3.add(gain, 0, 7, 4, 1);
        layout3.add(home3, 4, 8, 4, 1);
        layout3.add(quit3, 4, 9, 4, 1);
        Scene view2 = new Scene(layout3, 600*phi, 600);
        
        //Event handler and transition
        drawNumbers.setOnAction((event) -> {
            int num1, num2, num3, num4, lucky;
            double amount;
            if (number1.getText().isEmpty()
                    || number1.getText().isEmpty()
                    | number1.getText().isEmpty()
                    || number1.getText().isEmpty()) {
                numErr.setText("Please fill all the numbers!");
                return;
            } else {
                try {
                    num1 = Integer.parseInt(number1.getText());
                } catch (NumberFormatException e) {
                    numErr.setText("The first number you entered is invalid!");
                    return;
                }
                try {
                    num2 = Integer.parseInt(number2.getText());
                } catch (NumberFormatException e) {
                    numErr.setText("The second number you entered is invalid!");
                    return;
                }  
                try {
                    num3 = Integer.parseInt(number3.getText());
                } catch (NumberFormatException e) {
                    numErr.setText("The third number you entered is invalid!");
                    return;
                } 
                try {
                    num4 = Integer.parseInt(number4.getText());
                } catch (NumberFormatException e) {
                    numErr.setText("The fourth number you entered is invalid!");
                    return;
                } 
                try {
                    amount = Double.parseDouble(betAmount.getText());
                } catch (NumberFormatException e) {
                    numErr.setText("The amount you entered in invalid!");
                    return;
                }                
                int[] chosenNumbers = {num1, num2, num3, num4};
                /*if (num1 == num2 || num2 == num3 || num3==num4 || num1==num3 || num2==num4) {
                    numErr.setText("Please, choose distinct numbers.");
                    return;
                }*/
                
                if (!(amount > 0.0)) { numErr.setText("The bet amount needs to be greater than 0.0"); return; }
                for (int i = 0; i < chosenNumbers.length - 1; i++) {
                    for (int j = i + 1; j < chosenNumbers.length; j++) {
                        if (chosenNumbers[i] == chosenNumbers[j]
                            || chosenNumbers[i] <= 0
                            || chosenNumbers[j] <=0
                            || chosenNumbers[i] > 20
                            || chosenNumbers[j] > 20) { 
                            numErr.setText("Choose distinct numbers between 1 and 20.");
                            return;
                        }
                    }
                }
                inputNum.setText(num1 +" " + num2 + " " + num3 + " " + num4);
            }
            Tolo t = new Tolo();
            if (!isSuper.isSelected()) {
                t.createBet();
                int[] regNums = {num1, num2, num3, num4};
                resultNum.setText(t.getDrawnNumbers().get(0) + " " + t.getDrawnNumbers().get(1) + " " + t.getDrawnNumbers().get(2) + " " + t.getDrawnNumbers().get(3));
                Bet bet = new Bet(regNums, amount);
                gain.setText("Bet Gain: " + bet.computeGain(t) + "€");
            } else {
                if (!luckyNum.getText().equals("")) {
                    try {
                        lucky = Integer.parseInt(luckyNum.getText());
                        if (!(lucky > 0 || lucky <= 10)) {
                            numErr.setText("Lucky number must be between 1 and 10");
                            return;
                        }
                        inputLuckyNum.setText(lucky + "");
                    } catch (NumberFormatException e) {
                        numErr.setText("Lucky number is invalid");
                        return;
                    }
                } else {
                    return;
                }  
                
                t.createSuperBet();
                resultNum.setText(t.getDrawnNumbers().get(0) + " " + t.getDrawnNumbers().get(1) + " " + t.getDrawnNumbers().get(2) + " " + t.getDrawnNumbers().get(3));
                resultLuckyNum.setText(t.getDrawnNumbers().get(4) + "");
                int[] regNums = {num1, num2, num3, num4};
                SuperBet superBet = new SuperBet(regNums, amount, lucky);
                gain.setText("Super Bet Gain: " + superBet.computeGain(t) + "€");
                resultLuckyNum.setText(t.getDrawnNumbers().get(4) + "");
            }
            
            // Transition view
            GridPane transLayout = new GridPane();
            transLayout.setAlignment(Pos.CENTER);
            transLayout.setHgap(10);
            transLayout.setVgap(10);
            ImageView logoTrans = new ImageView(logoImg);
            logoTrans.setFitHeight(50);
            logoTrans.setFitWidth(50);
            logoTrans.setId("logo-rotate");
            RotateTransition rt = new RotateTransition(Duration.millis(3000), logoTrans);
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setInterpolator(Interpolator.LINEAR);
            rt.play();
            transLayout.add(new Label("Alea iacta est!"), 0, 2);
            transLayout.add(logoTrans, 0, 1);
            Scene transView = new Scene(transLayout, 600*phi, 600);
            window.setScene(transView);
            transView.getStylesheets().add
            (App.class.getResource("style/style.css").toExternalForm());   
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000), e -> {
                // After 5 seconds, switch to view2
                window.setScene(view2);
            }));
            timeline.play();
        });
        
        Scene view1 = new Scene(layout2, 600*phi, 600);        
        play.setOnAction((event) -> {
            window.setScene(view1);
        }); 
        
        window.setTitle("Tolo");
        window.setScene(home);
        home.getStylesheets().add
        (App.class.getResource("style/style.css").toExternalForm()); 
        rule.getStylesheets().add
        (App.class.getResource("style/style.css").toExternalForm());    
        view1.getStylesheets().add
        (App.class.getResource("style/style.css").toExternalForm());    
        view2.getStylesheets().add
        (App.class.getResource("style/style.css").toExternalForm());             
        window.getIcons().add(logoImg);
        window.show();
    }
    public static void main(String[] args) {
        launch(App.class);
    }

}