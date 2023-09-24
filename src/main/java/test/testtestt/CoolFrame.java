package test.testtestt;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CoolFrame extends JFrame{
    
    JTextArea textArea = new JTextArea();

    public CoolFrame(){
        
        super("Cool Frame");
        setSize(400,500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setVisible(true);
        
        textArea.setBounds(0,0,400, 500);
        add(textArea);   
    }
    
    public void setText(String text){
        
        textArea.setText(textArea.getText()+"\n  "+text);       
    }
}
