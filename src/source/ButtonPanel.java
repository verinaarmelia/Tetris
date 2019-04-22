package source;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonPanel extends JButton implements ActionListener {

	JButton btnPlay = new JButton("PLAY");
	ImageIcon help = new ImageIcon("help.png");
	JButton btnHelp = new JButton("HELP");
	private BufferedImage icon;

	public ButtonPanel() {

		System.out.println("a");
		// ini layoutnya
		// gimanac aranya supaya bisa ketengah ga ya
		setLayout(new FlowLayout());
		// setLayout(null);
		try {
			icon = ImageIO.read(Board.class.getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// setLocation(500, 200);
		btnPlay.setLocation(500, 200);
		btnHelp.setLocation(300, 300);
		btnHelp.setVisible(true);
		btnPlay.setVisible(true);
		add(btnHelp);
		add(btnPlay);
		// btnHelp.setSize(100, 100);
		btnHelp.addActionListener(this);
		btnPlay.addActionListener(this);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			new WindowFrame();
		} else if (e.getSource() == btnHelp) {
			JOptionPane.showMessageDialog(this,
					"W/ ^ = rotate\nA/Arrow <- = Left" + "\nD/Arrow -> = Right\nShift = Change Brick");
		}

	}

}
