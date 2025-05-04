package com.project_sena.spring_boot.Util.Service;


import com.project_sena.spring_boot.Gallery.Service.ImageContentService;
import com.project_sena.spring_boot.Gallery.Service.ModelContentService;
import com.project_sena.spring_boot.Gallery.Service.VdoContentService;
import com.project_sena.spring_boot.Util.Model.CheckStringFormatCriteria;
import com.project_sena.spring_boot.Util.Model.CheckStringFormatCriteria;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UtilService {

    //todo constance should not be fix here
    private static final String CHARACTERSFOROTP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String CHARACTERSFOROTPREF = "0123456789";
    private static final String CHARACTERSFORSECRETKEY = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final int SECRETKEYLENGHT = 32;
    private final int OTPLENGHT = 8;
    private final int OTPREFLENGHT = 12;
    private static final SecureRandom RANDOM = new SecureRandom();


    public String FirstMethodInUtilService(){
        String result;
        result = "Hello from UtilService";
        return result;
    }

    //todo GeGenerateOTP,GenerateOTPRef,GenerateSecretKeyRandom might used same method
    public String GenerateOTP(){
        StringBuilder stringBuilder = new StringBuilder(OTPLENGHT);
        for (int i = 0; i < OTPLENGHT; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERSFOROTP.length());
            stringBuilder.append(CHARACTERSFOROTP.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public String GenerateOTPRef() {
        StringBuilder stringBuilder = new StringBuilder(OTPREFLENGHT);
        for (int i = 0; i < OTPREFLENGHT; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERSFOROTPREF.length());
            stringBuilder.append(CHARACTERSFOROTPREF.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public String GenerateSecretKeyRandom(){
        StringBuilder stringBuilder = new StringBuilder(SECRETKEYLENGHT);
        for (int i = 0; i < SECRETKEYLENGHT; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERSFORSECRETKEY.length());
            stringBuilder.append(CHARACTERSFORSECRETKEY.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public boolean IsEmailFormatCorrect(String email){
        boolean result = false;
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$";
        if(email.isEmpty() || !email.matches(emailRegex)){
            result = true;
        }
        return result;
    }

    public boolean IsPasswordFormatCorrect(String password){
        boolean result = false;
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";
        if(password.isEmpty() || password.matches(passwordRegex)){
            result = true;
        }
        return result;
    }

    //todo Not Completed (unable to used)
    public CheckStringFormatCriteria IsStringFormatCorrectByCriteria(String string, String criteria){
        CheckStringFormatCriteria result = new CheckStringFormatCriteria();
        result.setApprove(true);
        List<String> match = new ArrayList<>();
        Pattern pattern = Pattern.compile(criteria);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()){
            match.add(matcher.group());
            }
        if(!match.isEmpty()){
            result.setApprove(false);
            result.setErrorList(match);
        }
        return result;
    }

    public String HashStringWithSha256(String text) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(text.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public <E extends Enum<E>> E isEnumValue(String value,Class<E> enumClass) throws Exception{
        E result = null;
        for(E eNumConstance:enumClass.getEnumConstants()){
            if(Objects.equals(eNumConstance.toString(),value)){
                result = eNumConstance;
                break;
            }
        }
        return result;
    }

    public String convertMediaTypeInHTTPHeader(String mediaType){
        String result;
        mediaType = mediaType.split("/")[0].toLowerCase();
        switch (mediaType){
            case "image":
                result = ImageContentService.class.getSimpleName().replace("Image","image");
                break;
            case "video":
                result = VdoContentService.class.getSimpleName().replace("Vdo","vdo");
                break;
            case "model":
                result = ModelContentService.class.getSimpleName().replace("Model","model");
                break;
            default:
                result = "";
                break;
        }
        return  result;
    }


}
