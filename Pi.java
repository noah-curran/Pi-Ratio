import java.util.Random;
import java.awt.*;
import javax.swing.JFrame;
import java.text.*;

public class Pi extends Canvas {
    
  DecimalFormat df = new DecimalFormat("#,###,##0.00");

  public void paint( Graphics g ) {
    drawGraphPaper(g);
    plotPoint(1000000000, g);
    plotPoint(1000000000, g);
  }
  
public void plotPoint(int probes, Graphics g) {
  double tempPi = estimatePi(probes);
  g.setColor(Color.blue);
  g.fillOval((int) (.005 * probes), (int) ((140.625 * tempPi)), 5, 5); 
}

  public void drawGraphPaper(Graphics g) {
   
        // labels
        g.setColor(Color.black);
        g.setFont(new Font(null));
        int counterX = 0;
        for ( int X=0; X<800; X += 50 ) {
            g.drawString( String.valueOf(0 + (10000 * counterX)), X, 50 );
            counterX++;
        }
        int counterY = 0;
        for ( int Y=100; Y<600; Y += 50 ) {
            g.drawString( String.valueOf(df.format(3.1 + (.01 * counterY))), 28, Y );
            counterY++;
        }
        // lines
        g.setColor(Color.lightGray);
        for ( int X=0; X<800; X += 50 ) {
            g.drawLine(X,0,X,599);    // horizontal
        }
        for ( int Y=0; Y<600; Y += 50 ) {
            g.drawLine(0,Y,799,Y);    // vertical 
        }
        //actual Pi
        g.setColor(Color.red);
        g.drawLine(0,302,799,302);

    
  }
  
  public static void main( String[] args )  {
    JFrame win = new JFrame("Pi");
    win.setSize(800,600); //the window is 800 wide and 600 tall in pixels
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Pi canvas = new Pi();
    win.add( canvas );
        win.setVisible(true);
    }
    


  
  public double estimatePi(int probes){
  
    Random rNumber = new Random();
    
    double xValue;
    double yValue;
    double numberInCircle = 0;
    double numberOutCircle = 0;
    
    for (int i = 0; i < probes; i++) {
      xValue = rNumber.nextDouble(); //scottstrickland@addret.com
      yValue = rNumber.nextDouble();
      if ((xValue * xValue) + (yValue * yValue) <= 1 ) { numberInCircle++; }
      else if ((xValue * xValue) + (yValue * yValue) > 1 ) { numberOutCircle++; }
    }
    
    double pi = (numberInCircle/(numberInCircle + numberOutCircle)) * 4; //Area of a circle divided by area of a square
    System.out.println("The number in the circle is " + numberInCircle); 
    System.out.println("The number out of the circle is " + numberOutCircle);
    System.out.println("Pi is " + pi);
    
    return pi;
  }
}

