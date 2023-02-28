package projeto.campoMinado.visao;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MeuMouse extends MouseListener{

	public default void mousePressed(MouseEvent e) {
	};
	public default void mouseClicked(MouseEvent e) {
	};
	public default void mouseReleased(MouseEvent e) {
	};
	public default void mouseEntered(MouseEvent e) {
	};
	public default void mouseExited(MouseEvent e) {
	};
	
}
