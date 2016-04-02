package view;

import castle.Game;
import org.jetbrains.annotations.NotNull;
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
	private JScrollBar scrollBar;

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
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		textArea.setBackground(new Color(12, 29, 39));
		textArea.setForeground(new Color(203, 212, 188));
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
		scrollBar = scrollPane.getVerticalScrollBar();
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setLocation(FRAME_X / 8,FRAME_Y / 8);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GUI game = new GUI();
		game.onStart();
	}

	@Override
	public void echo(@NotNull String words){
//		System.out.print(words);
		textArea.append(words);
		int i = textArea.getText().length();
		int MAX_LENGTH = 10000;
		if(i > MAX_LENGTH){
			textArea.setText(textArea.getText().substring(
					i - MAX_LENGTH, i
			));
		}
//		scrollBar.setValue(scrollBar.getMaximum() - 20);
		// 滚动到最底下
		int height=10;
		Point p = new Point();
		p.setLocation(0,this.textArea.getLineCount()*height);
		this.scrollPane.getViewport().setViewPosition(p);
	}

	@Override
	public void echoln(@NotNull String words){
		echo(words + "\n");
	}

	@Override
	public void closeScreen() {
		frame.dispose();
	}

}
