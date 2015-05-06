import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class Blackout {
	private JPopupMenu menu;
	private JFrame frame;
	private Point mouseLocation;
	private Point frameSize = new Point();
	private int button;
	private static final int MIN_SIZE = 20;
	
	public static void main(String[] args) {
		new Blackout();
	}
	
	public Blackout() {
		initializeFrame();
		initializeContextMenu();
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		MListener mListener = new MListener();
		frame.addMouseListener(mListener);
		frame.addMouseMotionListener(mListener);
		frameSize = new Point(frame.getLocation());
		
		frame.setVisible(true);
	}

	private void initializeContextMenu() {
		menu = new JPopupMenu("ContextMenu");
		menu.add(new JMenuItem("Black"));
		menu.add(new JMenuItem("White"));
		menu.add(new JMenuItem("Exit"));
	}

	public class MListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent me) {
			if (me.getButton() == MouseEvent.BUTTON3) {
				menu.show(me.getComponent(), me.getX(), me.getY());
			}
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
		public void mousePressed(MouseEvent me) {
			button = me.getButton();
			mouseLocation = me.getPoint();
			frameSize.setLocation(frame.getWidth(), frame.getHeight());
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			button = 0;
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mouseExited(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mouseMoved(MouseEvent arg0) {/* Do nothing */}
	}
}