import javax.swing.*;
import java.awt.*;

public class Exercise extends JFrame {
    public Exercise() {
        setTitle("登入");
        setSize(300, 200);
        
        // 修正 1：移除 setLayout(null)，改用排版管理員（例如 FlowLayout）
        // 如果用 null，就必須幫每個元件寫 setBounds 設定 XY 座標和寬高，不然元件會看不見。
        setLayout(new FlowLayout()); 

        JLabel l1 = new JLabel("帳號:");
        JTextField t1 = new JTextField(10); // 加上欄位寬度
        JLabel l2 = new JLabel("密碼:");
        JTextField t2 = new JTextField(10); // 加上欄位寬度
        JButton btn = new JButton("登入");

        add(l1); add(t1); add(l2); add(t2); add(btn);

        btn.addActionListener(e -> {
            // 修正 2：Java 的字串內容比對必須用 .equals()，絕對不能用 ==
            // 這裡假設密碼是 "1234"
            if (t1.getText().equals("admin") && t2.getText().equals("1234")) {
                System.out.println("登入成功");
            } else {
                System.out.println("登入失敗");
            }
        });

        // 修正 3：把 setVisible(true) 移到所有元件都 add 完之後的最後一行
        // 如果提早顯示，視窗可能會一片空白，直到你用滑鼠去改變視窗大小才會跑出來。
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 順便補上關閉視窗的設定
        setVisible(true);
    }

    public static void main(String[] args) {
        new Exercise();
    }
}
