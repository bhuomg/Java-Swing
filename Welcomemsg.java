package SwingProblem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Welcomemsg {
    	JFrame frame = new JFrame();
    	JPanel panel=new JPanel();
    	JTextField textfield=new JTextField(20);
    	JButton button= new JButton("Click me");
    	JLabel jname;
    	public Welcomemsg(){
    		jname = new JLabel("Enter your Name:");
    		frame.setTitle("Welcome");
    		panel.add(jname);
    		panel.add(textfield);
    		panel.add(button);
    		frame.add(panel);
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.pack();
    		frame.setVisible(true);
    		
    		button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                	 String str;
                	 String regx = "^[\\p{L} .'-]+$";
                     str = textfield.getText();
                     textfield.setText("");
                     if (str.equals("")){
                    	 	str="No input text ";
                     }
                     else{
                    	 if (!str.matches(regx)) {
                             str="Invalid name";
                         }else{
                    	 str="Hello, "+str;
                         }
                     }
                    JOptionPane.showMessageDialog(frame.getContentPane(), String.format(" '%s'", str));
                }
            });
    		
    	}
       
    	public static void main(String[] args ){
    		SwingUtilities.invokeLater(new Runnable(){
    			public void run(){
    				new Welcomemsg();
    			}
    		});
    	}
}

