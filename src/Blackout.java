import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class Blackout {
	JPopupMenu menu;
	
	public static void main(String[] args) {
		new Blackout();
	}
	
	public Blackout() {
		initializeFrame();
		initializeContextMenu();
	}

	private void initializeFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		MListener mListener = new MListener();
		frame.addMouseListener(mListener);
		frame.addMouseMotionListener(mListener);
		
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
			} else {
				
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mouseExited(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mousePressed(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mouseReleased(MouseEvent arg0) {/* Do nothing */}
		@Override
		public void mouseMoved(MouseEvent arg0) {/* Do nothing */}
	}
}