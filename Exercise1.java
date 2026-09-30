import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Exercise1 extends JFrame implements ActionListener{
    static Exercise1 frm=new Exercise1();
    static JButton btn1=new JButton("擲骰子");

    static JLabel sLabel=new JLabel("已擲0次，總和0，平均0.0",JLabel.CENTER);
    static JLabel pLabel=new JLabel("目前點數",JLabel.CENTER);

    static int n=0,m=0;
    public static void main(String args[]){
        frm.setSize(400,320);
        frm.setTitle("骰子模擬器");
        btn1.addActionListener(frm);
        
        BorderLayout border=new BorderLayout();
        frm.setLayout(border);

        frm.add(sLabel,BorderLayout.NORTH);
        frm.add(pLabel,BorderLayout.CENTER);
        frm.add(btn1,BorderLayout.SOUTH);

        pLabel.setFont(new Font("Dialog",Font.BOLD,60));

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        int randomValue=(int)(Math.random()*6)+1;

        n+=1;
        m+=randomValue;
        double a=(double) m/n;

        String s=String.format("已擲%d次，總和%d，平均%.2f",n,m,a);
        sLabel.setText(s);
        pLabel.setText(randomValue+"");

        if(randomValue==6){
            pLabel.setForeground(Color.GREEN);
        }else if(randomValue==1){
            pLabel.setForeground(Color.RED);
        }else{
            pLabel.setForeground(Color.BLACK);
        }
    }
}
