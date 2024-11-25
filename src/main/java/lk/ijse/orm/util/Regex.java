package lk.ijse.orm.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;
import lk.ijse.orm.util.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case ID:
                filed = "^([A-Z][0-9]{3})$";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case CONTACT:
                filed="^(?:\\+94|0)(?:\\d{9}|\\d{2,3}-\\d{7})$";
                break;
            case ADVANCE:
                filed="^£?(([0-9]{1,20}(,\\d{3})*(\\.\\d{2})?)|(0\\.[0-9]\\d)|(00[0-9]))$";
                break;
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-fill: Green;");
            textField.setStyle("-fx-text-fill: Green;");
            return true;
        }else {
            textField.setStyle("-fx-text-fill: Red;");
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }

}