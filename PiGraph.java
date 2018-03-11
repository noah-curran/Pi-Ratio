import java.util.Random;
import java.awt.*;
import javax.swing.JFrame;
import java.util.concurrent.ThreadLocalRandom;

class PiGraph extends Canvas {
  
  
  public void paint( Graphics g ) {
    estimatePi(1000000, g);
  }
  
  public double estimatePi(int probes, Graphics g) {
    
    Random rNumber = new Random();
    
    double xValue;
    double yValue;
    double numberInCircle = 0;
    double numberOutCircle = 0;
    
    for (int i = 0; i < probes; i++) {
      
      //The x and y values are randomly selected between 0 and 1
      xValue = rNumber.nextDouble(); //scottstrickland@addret.com
      yValue = ThreadLocalRandom.current().nextDouble(0, 1);
      
      if (xValue == 1) {
        System.out.println("1 is in the range");
      } else if (xValue == 0) {
        System.out.println("0 is in the range");
      }
      
      if ((xValue * xValue) + (yValue * yValue) <= 1 ) { 
        numberInCircle++; 
        g.setColor(Color.BLUE); 
        graphPoint(xValue, yValue, g); 
      } else if ((xValue * xValue) + (yValue * yValue) > 1 ) { 
        numberOutCircle++; 
        g.setColor(Color.RED); 
        graphPoint(xValue, yValue, g);
      }
      
    }
    
    /* *********************************************************************************
     * PROOF FOR FORMULA
     * *********************************************************************************
     * 
     * Area of Circle = pi * radius^2
     * Area of Square = side^2
     * 
     * side (s)   = 2 since side is diameter of circle and diameter is 2
     * radius (r) = 1 since we are using unit circle
     * 
     * 
     * Area of circle   pi * r^2    pi * (1)^2   pi
     * -------------- = --------- = ---------- = --
     * Area of square   s^2         (2)^2        4
     * 
     * 
     * Area of circle = number of dots in circle
     * Area of square = number of dots in total
     * 
     * number of dots in circle   pi        number of dots in circle
     * ------------------------ = --  --->  ------------------------ * 4 = pi
     * number of dots in total    4         number of dots in total
     * 
     */ 
    
    double pi = (numberInCircle/(numberInCircle + numberOutCircle)) * 4; //Area of a circle divided by area of a square
    System.out.println("The number in the circle is " + numberInCircle); 
    System.out.println("The number out of the circle is " + numberOutCircle);
    System.out.println("Pi is " + pi);
    
    return pi;
  }
  
  public void graphPoint(double xVariable, double yVariable, Graphics g) {
    g.fillRect((int)(xVariable*500), (int)(yVariable*500), 1,1);
    
  }
  
  public static void main( String[] args )  {
    JFrame win = new JFrame("Pi");
    win.setSize(800,600); //the window is 800 wide and 600 tall in pixels
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    PiGraph canvas = new PiGraph();
    win.add( canvas );
    win.setVisible(true);
  }
  
}