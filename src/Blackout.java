import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Blackout {
  private JPopupMenu menu;
  private JFrame frame;
  private Point mouseLocation;
  private Point frameSize;
  private int button;
  private static final int MIN_SIZE = 30;

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      new Blackout();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      System.err.println("Failed to set the look and feel");
      e.printStackTrace();
    }
  }

  public Blackout() {
    initializeFrame();
    initializeContextMenu();
  }

  private void initializeFrame() {
    frame = new JFrame("Blackout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setSize(500, 200);
    frame.setLocationRelativeTo(null);
    frame.setUndecorated(true);
    frame.setAlwaysOnTop(true);

    MListener mListener = new MListener();
    frame.addMouseListener(mListener);
    frame.addMouseMotionListener(mListener);
    frameSize = new Point();

    frame.setVisible(true);
  }

  private void initializeContextMenu() {
    menu = new JPopupMenu("ContextMenu");
    JMenuItem color = new JMenuItem("White");
    JMenuItem exit = new JMenuItem("Exit");
    MenuListener ml = new MenuListener(color, exit);
    color.addActionListener(ml);
    exit.addActionListener(ml);
    menu.add(color);
    menu.addSeparator();
    menu.add(exit);
  }

  public class MListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent me) {
      if (me.getButton() == MouseEvent.BUTTON3) {
        menu.show(me.getComponent(), me.getX(), me.getY());
      }
    }

    @Override
    public void mousePressed(MouseEvent me) {
      button = me.getButton();
      mouseLocation = me.getPoint();
      frameSize.setLocation(frame.getWidth(), frame.getHeight());
    }

    @Override
    public void mouseDragged(MouseEvent me) {
      if (button == MouseEvent.BUTTON1) {
        int x = (int) (me.getXOnScreen() - mouseLocation.getX());
        int y = (int) (me.getYOnScreen() - mouseLocation.getY());
        frame.setLocation(x, y);
        frame.revalidate();
      } else if (button == MouseEvent.BUTTON3) {
        int width = (int) (me.getX() + frameSize.getX() - mouseLocation.getX());
        int height = (int) (me.getY() + frameSize.getY() - mouseLocation.getY());
        frame.setSize(Math.max(MIN_SIZE, width), Math.max(MIN_SIZE, height));
        frame.revalidate();
      }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {/* Do nothing */}

    @Override
    public void mouseEntered(MouseEvent arg0) {/* Do nothing */}

    @Override
    public void mouseExited(MouseEvent arg0) {/* Do nothing */}

    @Override
    public void mouseMoved(MouseEvent arg0) {/* Do nothing */}
  }

  public class MenuListener implements ActionListener {
    JMenuItem color;
    JMenuItem exit;

    public MenuListener(JMenuItem color, JMenuItem exit) {
      this.color = color;
      this.exit = exit;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == color) {
        if (color.getText().equals("White")) {
          frame.getContentPane().setBackground(Color.WHITE);
          color.setText("Black");
        } else {
          frame.getContentPane().setBackground(Color.BLACK);
          color.setText("White");
        }
        frame.revalidate();
      } else if (ae.getSource() == exit) {
        frame.dispose();
        System.exit(0);
      }
    }
  }
}
