package stantz.ray.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;
import javax.swing.JPanel;

import stantz.ray.Color;
import stantz.ray.SceneControler;
import stantz.ray.Tracer;
import stantz.ray.math.Vector2D;



public class Display {

  private SceneControler sceneControler;

  private long started = System.currentTimeMillis();
  private long frameCount = -5;
  private long previousFrame = started;

  public Display(SceneControler sceneControler) {
    super();
    this.sceneControler = sceneControler;
  }

  public void init(final int width, final int height) {

    JFrame f = new JFrame("tracer");
    f.add(new JPanel() {

      Point mouseLocation = new Point(0,0);

      {
        setPreferredSize(new Dimension(width,height));
        this.addMouseMotionListener(new MouseMotionListener() {

          public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub

          }

          public void mouseMoved(MouseEvent e) {
            mouseLocation = e.getPoint();
          }

        });
      }


      @Override
      public void paint(Graphics gr) {
        super.paint(gr);


        long t0 =System.currentTimeMillis();
        if (frameCount==0) {
          started = t0;
        }
        int width = this.getWidth();
        int height = this.getHeight();

        Tracer tracer = new Tracer(sceneControler.getCurrentScene(width, height));

        int pix[] = new int[width * height];
        Vector2D mouse = new Vector2D(mouseLocation.x, mouseLocation.y);

        Color[][] result = tracer.trace(mouse);

        long t1 =System.currentTimeMillis();
        int index = 0;
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            pix[index++] = result[x][y].toInt();
          }
        }
        gr.drawImage(createImage(new MemoryImageSource(width, height, pix, 0, width)), 0, 0, this);
        gr.setColor(java.awt.Color.white);
        long t3 =System.currentTimeMillis();
        long time = t3-previousFrame;
        //System.out.println("paint: "+time+" = "+(t0-previousFrame)+" + "+(t1-t0)+" + "+(t3-t1));
        previousFrame = t3;
        ++frameCount;
        gr.drawString((1000f/time+"    ").substring(0,4)+" fps "+width+"x"+height+" "+((float)frameCount*1000/(t3-started)+"    ").substring(0,4)+" avg fps", 10, 20);
        gr.drawOval(mouseLocation.x-6, mouseLocation.y-6, 11, 11);
        //			    for (CachedVisible object : tracer.cacheObjects) {
        //					Box2D boundingBox = object.getBoundingBox();
        //					gr.drawRect((int)boundingBox.topLeft.x, (int)boundingBox.topLeft.y, (int)(boundingBox.bottomRight.x-boundingBox.topLeft.x), (int)(boundingBox.bottomRight.y-boundingBox.topLeft.y));
        //				}

      }

    }
        );
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);


  }

}
