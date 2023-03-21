package mvk.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends  JFrame{


    ///clasa care se ocupa strict de parte de UI
    private JPanel contentPane;
    private JTextField textFieldPolynom1;
    private JTextField textFieldPolynom2;

    private JButton buttonSum;
    private JButton buttonSubstraction;
    private JButton buttonMultiplication;
    private JButton buttonDivison;
    private JButton buttonDerivative;

    private JButton buttonIntegartion;
    private  JTextArea textAreaSumText;
    private JTextArea textAreaSubstractionText;
    private JTextArea textAreaMultiplicationText;
    private JTextArea textAreaDivisionText;
    private JTextArea textAreaDerivationPolynom2Text;
    private JTextArea textAreaDerivationPolynom1Text;
    private JTextArea textAreaIntegrationPolynom1Text;
    private JTextArea textAreaIntegrationPolynom2Text;

    public CalculatorView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1398, 786);
        this.getContentPane().setLayout(null);



        JLabel labelPolynom1 = new JLabel("Polynom1");
        labelPolynom1.setFont(new Font("Tahoma", Font.PLAIN, 21));
        labelPolynom1.setBounds(195, 75, 185, 65);
        this.getContentPane().add(labelPolynom1);

        JLabel labelPolynom2 = new JLabel("Polynom2");
        labelPolynom2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelPolynom2.setBounds(195, 166, 185, 65);
        this.getContentPane().add(labelPolynom2);

        textFieldPolynom1 = new JTextField();
        textFieldPolynom1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPolynom1.setBounds(413, 93, 413, 38);
        this.getContentPane().add(textFieldPolynom1);
        textFieldPolynom1.setColumns(10);

        textFieldPolynom2 = new JTextField();
        textFieldPolynom2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPolynom2.setColumns(10);
        textFieldPolynom2.setBounds(413, 177, 413, 38);
        this.getContentPane().add(textFieldPolynom2);

        buttonSum = new JButton("+");
        buttonSum.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonSum.setBounds(197, 266, 79, 59);
        this.getContentPane().add(buttonSum);

        buttonSubstraction = new JButton("-");
        buttonSubstraction.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonSubstraction.setBounds(347, 266, 79, 59);
        this.getContentPane().add(buttonSubstraction);

        buttonMultiplication = new JButton("*");
        buttonMultiplication.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonMultiplication.setBounds(495, 266, 79, 59);
        this.getContentPane().add(buttonMultiplication);

        buttonDivison = new JButton("/");
        buttonDivison.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonDivison.setBounds(648, 266, 79, 59);
        this.getContentPane().add(buttonDivison);

        buttonDerivative = new JButton("Dx");
        buttonDerivative.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonDerivative.setBounds(794, 266, 79, 59);
        this.getContentPane().add(buttonDerivative);

        buttonIntegartion = new JButton("∫");
        buttonIntegartion.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonIntegartion.setBounds(949, 266, 79, 59);
        this.getContentPane().add(buttonIntegartion);

        JLabel labelSum = new JLabel("The Polynomial Sum");
        labelSum.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelSum.setBounds(96, 372, 185, 65);
        this.getContentPane().add(labelSum);

        JLabel labelSubstraction = new JLabel("The Polynomial Substraction");
        labelSubstraction.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelSubstraction.setBounds(96, 476, 252, 65);
        this.getContentPane().add(labelSubstraction);

        JLabel labelMultiplication = new JLabel("The Polynomial Multiplication");
        labelMultiplication.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelMultiplication.setBounds(96, 570, 299, 65);
        this.getContentPane().add(labelMultiplication);

        JLabel labelDivision = new JLabel("The Polynomial Division");
        labelDivision.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelDivision.setBounds(96, 674, 252, 65);
        this.getContentPane().add(labelDivision);

        JLabel labelDerivation = new JLabel("The Derivation Of The Polynomials");
        labelDerivation.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelDerivation.setBounds(959, 335, 371, 65);
        this.getContentPane().add(labelDerivation);

        JLabel labelIntegration = new JLabel("The Integration Of The Polynomials");
        labelIntegration.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelIntegration.setBounds(959, 514, 316, 65);
        this.getContentPane().add(labelIntegration);

        textAreaSumText = new JTextArea();
        textAreaSumText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaSumText.setBounds(413, 391, 413, 46);
        this.getContentPane().add(textAreaSumText);

        textAreaSubstractionText = new JTextArea();
        textAreaSubstractionText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaSubstractionText.setBounds(413, 476, 413, 46);
        this.getContentPane().add(textAreaSubstractionText);

        textAreaMultiplicationText = new JTextArea();
        textAreaMultiplicationText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaMultiplicationText.setBounds(413, 570, 413, 46);
        this.getContentPane().add(textAreaMultiplicationText);

        textAreaDivisionText = new JTextArea();
        textAreaDivisionText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDivisionText.setBounds(413, 674, 413, 46);
        this.getContentPane().add(textAreaDivisionText);

        textAreaDerivationPolynom2Text = new JTextArea();
        textAreaDerivationPolynom2Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDerivationPolynom2Text.setBounds(917, 447, 413, 46);
        this.getContentPane().add(textAreaDerivationPolynom2Text);

        textAreaDerivationPolynom1Text = new JTextArea();
        textAreaDerivationPolynom1Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDerivationPolynom1Text.setBounds(918, 391, 413, 46);
        this.getContentPane().add(textAreaDerivationPolynom1Text);

        textAreaIntegrationPolynom1Text = new JTextArea();
        textAreaIntegrationPolynom1Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaIntegrationPolynom1Text.setBounds(917, 570, 413, 46);
        this.getContentPane().add(textAreaIntegrationPolynom1Text);

        textAreaIntegrationPolynom2Text = new JTextArea();
        textAreaIntegrationPolynom2Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaIntegrationPolynom2Text.setBounds(917, 626, 413, 46);
        this.getContentPane().add(textAreaIntegrationPolynom2Text);

        this.setVisible(true);
    }

    //region Setters And Getters for Labels and TextAreas
    public void setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
    }


    public String getTextFieldPolynom1() {
        return textFieldPolynom1.getText();
    }

    public void setTextFieldPolynom1(String textFieldPolynom1) {
        this.textFieldPolynom1.setText(textFieldPolynom1);
    }

    public String getTextFieldPolynom2() {
        return textFieldPolynom2.getText();
    }

    public void setTextFieldPolynom2(String textFieldPolynom2) {
        this.textFieldPolynom2.setText(textFieldPolynom2);
    }

    public JButton getButtonSum() {
        return buttonSum;
    }

    public void setButtonSum(JButton buttonSum) {
        this.buttonSum = buttonSum;
    }

    public JButton getButtonSubstraction() {
        return buttonSubstraction;
    }

    public void setButtonSubstraction(JButton buttonSubstraction) {
        this.buttonSubstraction = buttonSubstraction;
    }

    public JButton getButtonMultiplication() {
        return buttonMultiplication;
    }

    public void setButtonMultiplication(JButton buttonMultiplication) {
        this.buttonMultiplication = buttonMultiplication;
    }

    public JButton getButtonDivison() {
        return buttonDivison;
    }

    public void setButtonDivison(JButton buttonDivison) {
        this.buttonDivison = buttonDivison;
    }

    public JButton getButtonDerivative() {
        return buttonDerivative;
    }

    public void setButtonDerivative(JButton buttonDerivative) {
        this.buttonDerivative = buttonDerivative;
    }

    public JButton getButtonIntegartion() {
        return buttonIntegartion;
    }

    public void setButtonIntegartion(JButton buttonIntegartion) {
        this.buttonIntegartion = buttonIntegartion;
    }

    public String getTextAreaSumText() {
        return textAreaSumText.getText();
    }

    public void setTextAreaSumText(String textAreaSumText) {
        this.textAreaSumText.setText(textAreaSumText);
    }

    public String getTextAreaSubstractionText() {
        return textAreaSubstractionText.getText();
    }

    public void setTextAreaSubstractionText(String textAreaSubstractionText) {
        this.textAreaSubstractionText.setText(textAreaSubstractionText);
    }

    public String  getTextAreaMultiplicationText() {
        return textAreaMultiplicationText.getText();
    }

    public void setTextAreaMultiplicationText(String  textAreaMultiplicationText) {
        this.textAreaMultiplicationText.setText(textAreaMultiplicationText);
    }

    public String  getTextAreaDivisionText() {
        return textAreaDivisionText.getText();
    }

    public void setTextAreaDivisionText(String textAreaDivisionText) {
        this.textAreaDivisionText.setText(textAreaDivisionText);
    }

    public String  getTextAreaDerivationPolynom2Text() {
        return textAreaDerivationPolynom2Text.getText();
    }

    public void setTextAreaDerivationPolynom2Text(String textAreaDerivationPolynom2Text) {
        this.textAreaDerivationPolynom2Text.setText(textAreaDerivationPolynom2Text);
    }

    public String getTextAreaDerivationPolynom1Text() {
        return textAreaDerivationPolynom1Text.getText();
    }

    public void setTextAreaDerivationPolynom1Text(String textAreaDerivationPolynom1Text) {
        this.textAreaDerivationPolynom1Text.setText(textAreaDerivationPolynom1Text);
    }

    public String getTextAreaIntegrationPolynom1Text() {
        return textAreaIntegrationPolynom1Text.getText();
    }

    public void setTextAreaIntegrationPolynom1Text(String textAreaIntegrationPolynom1Text) {
        this.textAreaIntegrationPolynom1Text.setText(textAreaIntegrationPolynom1Text);
    }

    public String getTextAreaIntegrationPolynom2Text() {
        return textAreaIntegrationPolynom2Text.getText();
    }

    public void setTextAreaIntegrationPolynom2Text(String textAreaIntegrationPolynom2Text) {
        this.textAreaIntegrationPolynom2Text.setText(textAreaIntegrationPolynom2Text);
    }
    //endregion

    public void sumListener (ActionListener  actionListener)
    {
        this.buttonSum.addActionListener(actionListener);
    }

    public void substractionListener(ActionListener actionListener)
    {
        this.buttonSubstraction.addActionListener((actionListener));
    }

    public void multiplicationListener(ActionListener actionListener)
    {
        this.buttonMultiplication.addActionListener(actionListener);
    }

    public void divisionListener(ActionListener actionListener)
    {
        this.buttonDivison.addActionListener(actionListener);
    }

    public void derivativeListener(ActionListener actionListener)
    {
        this.buttonDerivative.addActionListener(actionListener);
    }

    public void integrationListener(ActionListener actionListener)
    {
        this.buttonIntegartion.addActionListener(actionListener);
    }


}
