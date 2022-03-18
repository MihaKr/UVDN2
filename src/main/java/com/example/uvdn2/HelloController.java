package com.example.uvdn2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class HelloController {

    @FXML
    private TextField calc;
    @FXML
    private boolean plus = true;
    private boolean n = false;
    @FXML
    private TextArea calc_Log;
    @FXML
    private TextArea actions;

    StringBuilder ops = new StringBuilder();

    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
    }

    public void click(ActionEvent actionEvent) {
        String text = ((Button)actionEvent.getSource()).getText();
        System.out.println(text);
        switch (text) {
            case "+/-":
                plus = !plus;
                break;
            case "n²":
                ops.append("*");
                ops.append(ops.charAt(ops.length()-2));
                String vmes = calc.getText();
                vmes += "²";
                calc.setText(vmes);
                break;
            case "n³":
                for (int i = 0; i < 2; i++) {
                    ops.append("*");
                    ops.append(ops.charAt(ops.length() - 2));
                }
                vmes = calc.getText();
                vmes += "³";
                calc.setText(vmes);
                break;
            case "^":
                calc.setText(ops.toString() + "^");
                n = true;
                break;

            case "<-":
                if(ops.length() > 0) {
                    ops.setLength(ops.length() - 1);
                    calc.setText(ops.toString());
                }
                break;
            case "CLEAR":
                ops = new StringBuilder();
                calc.setText(ops.toString());
                break;
            case "=":
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                try {
                    Object result = engine.eval(ops.toString());
                    String mid = calc_Log.getText();
                    mid += calc.getText() + "\n";
                    calc_Log.setText(mid);
                    ops = new StringBuilder();
                    calc.setText(result.toString());
                    String midA = actions.getText();
                    midA += result.toString();
                    actions.setText(midA);

                } catch (ScriptException e) {
                    e.printStackTrace();
                }
                break;
            default:
                if(plus) {
                    if(n) {
                        ops.append("*");
                        ops.append(ops.charAt(ops.length() - 2));

                        for (int i = 0; i < Integer.parseInt(text)-2; i++) {
                            ops.append("*");
                            ops.append(ops.charAt(ops.length() - 2));
                        }
                    }
                    else {
                        ops.append(text);
                    }
                }
                else {
                    ops.append("-" + text);
                    plus = true;
                }
                if(n) {
                    vmes = calc.getText();
                    vmes += text;
                    calc.setText(vmes);
                }
                else {
                    calc.setText(ops.toString());
                }
        }
    }
}