
import java.awt.Color;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Panel;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
public class Gradient extends JPanel{
	
	Paint gPaint;
  
  public Gradient(){
	  
    gPaint=new GradientPaint(200, 80, Color.GRAY, 280, 300, Color.WHITE, false);
    }
  public void paint(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    g2.setPaint(gPaint);				// 색을 넣어주는 함수
    g2.fill(new Rectangle2D.Double(0,0,400,300));	// 그리는 위치를 잡아주는 함수			
    								//앞에 두개는 x, y 좌표값, 뒤에 두개는 끝의 x , y 좌표값 (setSize만큼 하면됨)
  }  
  public static void main(String []args){
    JPanel f=new Gradient();
    f.setSize(400,300);
    f.setVisible(true);
    
  }
}