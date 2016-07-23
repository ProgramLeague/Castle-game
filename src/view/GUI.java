package view;

import castle.Game;
import util.Echoer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * 视图
 * Created by asus1 on 2016/1/31.
 */
public class GUI extends Game
		implements Echoer {

	private JTextField textField;
	private JTextArea textArea;
	private JFrame frame;
	private JScrollPane scrollPane;

	private static int FRAME_X = 500;
	private static int FRAME_Y = 500;
	private static int INPUT_Y = 500;

	public GUI() {
		frame = new JFrame("城堡游戏   by 千里冰封");
		textField = new JTextField("在这里输入指令");
		textField.registerKeyboardAction(e -> {
					HandleMessage(textField.getText());
					textField.setText("");
				},
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_FOCUSED
		);
		textArea = new JTextArea();
		textArea.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				echoln("别点这里，点下面。");
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
//				echoln("对，放开就好。");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		/// IntelliJ IDEA 标准配色
		textArea.setBackground(new Color(43, 43, 43));
		textArea.setForeground(new Color(169, 183, 198));
		textArea.setEditable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
				"." + File.separator + "src" + File.separator + "drawable" + File.separator + "ic_launcher.png"
		));
		frame.setSize(FRAME_X, FRAME_Y);
		// 绝对布局
		// frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(textField, BorderLayout.SOUTH);
		scrollPane = new JScrollPane(textArea);
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setLocation(FRAME_X / 8, FRAME_Y / 8);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GUI game = new GUI();
		game.onStart();
	}

	@Override
	public void echo(String words) {
//		System.out.print(words);
		textArea.append(words);
		int i = textArea.getText().length();
		int MAX_LENGTH = 10000;
		if (i > MAX_LENGTH) {
			textArea.setText(textArea.getText().substring(
					i - MAX_LENGTH, i
			));
		}
//		scrollBar.setValue(scrollBar.getMaximum() - 20);
		// 滚动到最底下
		int height = 10;
		Point p = new Point();
		p.setLocation(0, this.textArea.getLineCount() * height);
		this.scrollPane.getViewport().setViewPosition(p);
	}

	@Override
	public void echoln(String words) {
		echo(words + "\n");
	}

	@Override
	public void closeScreen() {
		frame.dispose();
	}

}
