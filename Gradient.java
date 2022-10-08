

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Gradient extends JPanel{
	
	Gradient(){
		this.setSize(700,700);
		this.setOpaque(false);
	}
	protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d=(Graphics2D) g;
        Color kolor1=new Color(102, 33, 29);
        Color kolor2=new Color(38, 138, 158);
        GradientPaint gp=new GradientPaint(80,-200,kolor1,800,500,kolor2);
        g2d.setPaint(gp);
        g2d.fillRect(-100, -100, 4020, 10080);
    }
}
