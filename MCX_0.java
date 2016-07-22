import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MCX_0 extends JPanel  {

   private JButton generateButton;
   private JLabel seedLabel;
   private JTextField seedField;

   public MCX_0() {
      
      this.setLayout(new BorderLayout());
   
      JPanel panel_button = new JPanel();
      panel_button.setBackground(Color.LIGHT_GRAY);
      
      panel_button.add(form_generateButton());     
      
      JPanel panel_seed = new JPanel();
      panel_seed.setBackground(Color.LIGHT_GRAY);
      
      panel_seed.add(form_seedLabel());
      panel_seed.add(form_seedField());
      
      this.add(panel_button, BorderLayout.NORTH);
      this.add(panel_seed, BorderLayout.WEST);
          
   }     
      
   
   private JComponent form_generateButton() {
      generateButton = new JButton("Generate"); 
      generateButton.setPreferredSize(new Dimension(450,30));
      
      generateButton.setFont(new Font("Sans Serif", Font.PLAIN, 14));
      generateButton.setBackground(Color.LIGHT_GRAY);
      
      generateButton.addActionListener(new Listener("generateButton"));   
      
      return generateButton;  
   }
   
   private JComponent form_seedLabel() {  
      seedLabel = new JLabel("   Seed:  ");
      
      seedLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
      
      return seedLabel;  
   }
   
   private JComponent form_seedField() {   
      seedField = new JTextField("default", 15);
      seedField.setHorizontalAlignment(SwingConstants.RIGHT);
      
      seedField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
      
      return seedField;   
   }
   
   private void generate() {
   
      long[] seedBuilder = new long[5];  
   
      String clientSeed = seedField.getText(); 
      if (clientSeed.equals("")) clientSeed = String.valueOf(System.currentTimeMillis());
                           
      seedBuilder[0] = (long)clientSeed.hashCode();                  // 0 = Seed hash
      
      seedBuilder[1] = clientSeed.substring(0,1).hashCode();         // 1 = Seed-primary hash
      seedBuilder[1] = 500 - seedBuilder[1];                         // 1 = Adjusted seed-primary hash
      
      seedBuilder[2] = seedBuilder[0] * seedBuilder[1];              // 2 = Seed hash * adjusted seed-primary hash
      seedBuilder[2] = Math.abs(seedBuilder[2]);                     // 2 = Made positive
      
      seedBuilder[3] = Integer.parseInt(String.valueOf(seedBuilder[2]).substring(1,2));
      //seedBuilder[3] = (long)Math.pow(seedBuilder[2], 1 +(Integer.parseInt(String.valueOf(seedBuilder[2]).substring(1,2))/10));
      
      seedBuilder[4] = seedBuilder[3];
      /*while (String.valueOf(seedBuilder[4]).length() != 17) {
      
         if (String.valueOf(seedBuilder[4]).length() < 17) {
            
            seedBuilder[4] *= 1.3;
            
         } else {
            
            seedBuilder[4] *= 0.97;   
         }   
      }*/
      
      p(clientSeed);
      p(seedBuilder[4]);
      p("32159307615668708");
   
   }
   
   
   private class Listener implements ActionListener {  
      private String ID;
   
      private Listener(String ID) {
         
         this.ID = ID;
      }   
   
      @Override
      public void actionPerformed(ActionEvent e) {
      
         if(ID.equals("generateButton")) {
            generate();
            
         
         }
      }
   } 
   
   private void p(Object o) {
      o = (String)(o+"");
      System.out.println(o);
   }
}
