import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;       // 【修正 1】新增：用來開啟抗鋸齒
import java.awt.RenderingHints;   // 【修正 1】新增：用來設定繪圖品質
import javax.swing.JFrame;
import javax.swing.JPanel;

public class House extends JPanel {

    public House() {
        setPreferredSize(new Dimension(500, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 【修正 1】：轉型為 Graphics2D 並開啟抗鋸齒，讓斜線、圓形與文字邊緣更平滑
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. 天空與草地 (fillRect)
        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, 500, 310);

        g2d.setColor(new Color(76, 175, 80));
        g2d.fillRect(0, 310, 500, 90);

        // 2. 太陽與 8 條光芒 (fillOval + for 迴圈 drawLine)
        int sunCenterX = 410;
        int sunCenterY = 80;
        int sunRadius = 40;

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunCenterX - sunRadius, sunCenterY - sunRadius, sunRadius * 2, sunRadius * 2);

        g2d.setColor(new Color(220, 200, 50));
        for (int i = 0; i < 8; i++) {
            double angle = i * (2 * Math.PI / 8);
            int x1 = (int) (sunCenterX + 50 * Math.cos(angle));
            int y1 = (int) (sunCenterY + 50 * Math.sin(angle));
            int x2 = (int) (sunCenterX + 70 * Math.cos(angle));
            int y2 = (int) (sunCenterY + 70 * Math.sin(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // 3. 雲朵 (3 個重疊的白色 fillOval)
        g2d.setColor(Color.WHITE);
        g2d.fillOval(60, 55, 75, 50);
        g2d.fillOval(100, 35, 85, 60);
        g2d.fillOval(145, 55, 75, 50);

        // 4. 房屋主體 (fillRect + drawRect)
        g2d.setColor(new Color(255, 228, 181));
        g2d.fillRect(150, 180, 200, 160);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(150, 180, 200, 160);

        // 5. 屋頂 (只能用 3 條 drawLine 畫三角形)
        g2d.setColor(Color.RED);
        g2d.drawLine(130, 180, 250, 90);
        g2d.drawLine(250, 90, 370, 180);
        g2d.drawLine(130, 180, 370, 180);

        // 6. 門與門把 (fillRoundRect + fillOval)
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRoundRect(225, 260, 50, 80, 15, 15);

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(262, 298, 8, 8);

        // 【修正 2】：呼叫模組化的 drawWindow 方法畫兩扇窗戶，減少重複程式碼
        drawWindow(g2d, 170, 210, 50);
        drawWindow(g2d, 280, 210, 50);

        // 8. 角落簽名 (drawString)
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Dialog", Font.BOLD, 14));
        g2d.drawString("A1143352 施岑昀", 365, 385);
    }

    // 【修正 2】：獨立出來的畫窗戶輔助方法
    private void drawWindow(Graphics g, int x, int y, int size) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
        g.drawLine(x + size / 2, y, x + size / 2, y + size); // 垂直十字線
        g.drawLine(x, y + size / 2, x + size, y + size / 2); // 水平十字線
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame("我的小房子");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.add(new House());
        frm.pack();
        frm.setResizable(false); // 【修正 3】：鎖定視窗大小為 500x400，避免拉大破版
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}
