package test.testtestt;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;


public class Testtestt {

    public static String find(String item){
        
        String url = "";
        try {
            
            File songIndex = new File("./resources/songs.txt");
            Scanner indexReader = new Scanner(songIndex);
            while (indexReader.hasNextLine()) {
                
                String[] splitIndex = indexReader.nextLine().split(",");
                if(splitIndex[0].equals(item)){ 
                    
                    url = splitIndex[1];
                    break;
                }
            }
            indexReader.close();
        }catch (FileNotFoundException e) {}
        
        return(url);
    }
    
    
    public static void play(String item){
        
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try{
                Desktop.getDesktop().browse(new URI(find(item)));

            }catch(IOException | URISyntaxException e){}
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("./resources/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        
        configuration.setGrammarPath("./resources");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);
        
         
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        
        CoolFrame coolFrame = new CoolFrame();
        
        ArrayList<String> commands = new ArrayList<String>();
        commands.add("play");
        
        ArrayList<String> items = new ArrayList<String>();
        items.add("stay");
items.add("counting stars");
items.add("pay phone");
        String command = "";
        String phase = "zero";       
        while(true){
 
            recognizer.startRecognition(true);
            SpeechResult result = recognizer.getResult();
            recognizer.stopRecognition();
            
            String strResult = result.getHypothesis();          
            switch(phase){
                
                case "zero" ->{
                    if(strResult.equals("one")){
                        phase = "one";
                        coolFrame.setText(strResult);
                    }
                }
                case "one" ->{
                    if(commands.contains(strResult)){
                        command = strResult;
                        coolFrame.setText("one "+strResult);
                        phase = "two";
                    }
                }
                
                case "two" ->{
                    if(items.contains(strResult)){
                        coolFrame.setText("one "+command+" "+strResult);
                        switch(command){
                            case "play" ->{
                                play(strResult);
                            }

                        }
                        phase = "zero";
                        
                    } 
                }  
                
            }
 
        }
        
        
        

    }
}
