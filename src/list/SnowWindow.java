package lis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.awt.image.BufferedImage;

public class SnowWindow extends JWindow implements MouseMotionListener {
    
    private int maxSnowCount = 10;
    private int maxWidth = 15;
    private int maxHight = 15;
    private int height;
    private int width;
    private int xpos;
    private int ypos;
    private float alpha = 0.05f;
    private boolean isDrg;
    private Dimension d;
    private Image imgsn;
    private BufferedImage buffImg;
    private Random r = new Random();
    private List<Snower> snowAll = new LinkedList<Snower>();    
    private ArrayList<Position> al = new ArrayList<Position>();

    public SnowWindow() {
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        height = d.height;
        width = d.width;
        this.setBounds(0, 0, width, height);
        imgsn = this.getToolkit().getImage(getClass().getResource("/pic/snowflake.gif"));
        com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
        this.addMouseMotionListener(this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SnowWindow.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x > width - 40 && y < 40) {
                    System.exit(0);
                }
            }
        });
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    public void start() {
        while (true) {
            if (alpha < 0.8f) {
                alpha += 0.001;
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < r.nextInt(maxSnowCount + 1); i++) {
            snowAll.add(new Snower(r.nextInt(d.width - 3) + 1, r.nextInt(maxWidth) + 2, r.nextInt(maxHight) + 2));
        }
        for (int i = snowAll.size() - 1; i >= 0; i--) {
            Snower sn = snowAll.get(i);
            sn.setX(sn.getX());
            sn.setY(sn.getY() + 1);
            int tempX = sn.getX();
            int tempY = sn.getY();
            g.drawImage(imgsn, tempX, tempY, sn.getWidth(), sn.getHight(), null);
        }
        Graphics2D f = (Graphics2D) g;
        f.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        if (!isDrg) {
            f.setColor(Color.WHITE);
            f.fillRect(0, 0, width, height);
        } else {
            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gbi = buffImg.createGraphics();
            gbi.setColor(Color.WHITE);
            gbi.fillRect(0, 0, width, height);
            gbi.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.1f));
            gbi.setColor(Color.BLACK);
            for (Position pos : al) {
                gbi.fillOval(pos.getX() - 10, pos.getY() - 10, 20, 30);
            }
            f.drawImage(buffImg, 0, 0, this);
        }

    }

    public void mouseDragged(MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        isDrg = true;
        xpos = (int) e.getX();
        ypos = (int) e.getY();
        al.add(new Position(xpos, ypos));
    }

    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] args) {
        SnowWindow s = new SnowWindow();
        s.start();
    }
}
