package SwingProblem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
@SuppressWarnings("serial")
public class Registration extends JFrame implements ActionListener 
  { 
	
	JLabel title, jname, jdob,jgender,jintrest;
    JTextField tfname, tfdob;
    JButton btn1, btn2;
    JRadioButton male, female;
    JTextArea textarea;  
    String gender = null;
 
    Registration()
     {        
        setSize(600, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration");
 
        title = new JLabel("Please Enter the Details");
        title.setForeground(Color.RED);
        title.setFont(new Font("Serif", Font.BOLD, 20));
 
        jname = new JLabel("Name:");
        jdob = new JLabel("DOB(dd-MM-yyyy):");
        jgender=new JLabel("Gender:");
        jintrest=new JLabel("Your Intrest:");
         
        tfname = new JTextField();
        tfdob= new JTextField();
      
        male= new JRadioButton("MALE");
        female= new JRadioButton("FEMALE");
        
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        
        textarea=new JTextArea();
        
        btn1 = new JButton("Save");
        btn2 = new JButton("Clear");
 
        btn1.addActionListener(this);
        btn2.addActionListener(this);
 
        title.setBounds(100, 30, 400, 30);
        jname.setBounds(80, 70, 200, 30);
        jdob.setBounds(80, 110, 200, 30);
        jgender.setBounds(80,150,200,30);
        jintrest.setBounds(80,200,200,30);
        
        tfname.setBounds(300, 70, 200, 30);
        tfdob.setBounds(300, 110, 200, 30);
        male.setBounds(300, 150, 100, 30);
        female.setBounds(400, 150, 100, 30);
        
        textarea.setBounds(300, 200, 200, 100);
               
        btn1.setBounds(80, 350, 100, 30);
        btn2.setBounds(200, 350, 100, 30);
    
 
        
        add(title);
        add(jname);
        add(tfname);
        add(jdob);
        add(tfdob);
        add(jgender);
        add(male);
        add(female);
        add(jintrest);
        add(textarea);
        add(btn1);
        add(btn2);
        
        setVisible(true);
     }   
 
    public void actionPerformed(ActionEvent e) 
     {
        if (e.getSource() == btn1)
         { 
        	String name=tfname.getText();
        	String text=textarea.getText();
        	if(namevalidation(name)&& dobvalidation(tfdob.getText())&&(male.isSelected()||female.isSelected())&& textvalidation(text))
        	{	
        		  WriteToFile(name);
        		  WriteToFile(tfdob.getText());
           
		           if(male.isSelected()) 
		               gender="Male";
		           else if(female.isSelected()) 
		               gender="Female";
		           WriteToFile(gender);
		           
		           WriteToFile(text);
        	}else{
        		JOptionPane.showMessageDialog(null, "invalid input!! please recheck ur input values.");
        		
        	}
         }   
          else
       {
            tfname.setText("");
            tfdob.setText("");
            textarea.setText("");
        }
    }
    public static boolean namevalidation(String name){

    	    String regx = "^[\\p{L} .'-]+$";
    	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
    	    Matcher matcher = pattern.matcher(name);
    	    return matcher.find();

    	}
    public boolean dobvalidation(String date){
    	    String regex = "^(3[0-1]|[1-2][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]*{4}$";
    	    Pattern pattern = Pattern.compile(regex);
    	    boolean valid=false;
    	    Matcher matcher = pattern.matcher(date);
			 valid=matcher.matches();
    	   return valid;
		  	    
    	}
    	
   
    public static boolean textvalidation(String text){
    	
          if(text.isEmpty()){
        	  JOptionPane.showMessageDialog(null, "please enter ur Intrest");  
        	  return false;
          }else{
          return true;
          }
    }
    
    	
    

    public void WriteToFile(String content){
        try{
            File f=new File("D:\\user.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            else{
                FileWriter fw=new FileWriter(f.getAbsoluteFile(), true);
                BufferedWriter bw=new BufferedWriter(fw);
                if(content.equals(tfname.getText()))
                {
                	bw.write("Name: \t");
                	bw.write(content);
                }else if(content.equals(tfdob.getText()))
                {
                	bw.write("DOB(dd-MM-yyyy): \t");
                	bw.write(content);
                }else if(content.equals(gender))
                {
                	bw.write("Gender: \t");
                	bw.write(content);
                }else{
                	bw.write("Intrest: \t");
                	bw.write(content);
                }
                             
                bw.newLine();
                bw.close();
                System.out.println("Done");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
  
    public static void main(String args[])
   {
        new Registration();
    }
}
