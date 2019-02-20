package Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;

import javax.swing.*;

import Model.*;

import Basic.*;
/**
 * @date 2018/8/18
 * @author 阿良和可可
 * @comment 绘制图形和运行程序的类
 */
public class Main implements Runnable {
	static JFrame JF = null;
	static JPanel JP = null;
	static Snake snake = Snake.getInstance();
	static Food food = Food.getFood();
	static SnakeListener sl = new SnakeListener();
	static boolean canChangeDirection = true;
	public static void main(String[] args) {
		JF= new JFrame();
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JF.setBounds(800,180,410,450);
		JF.setVisible(true);
		JP = new JPanel(){
			public void paint(Graphics g){
				super.paint(g);
				//绘制背景
				g.setColor(Color.black);
				g.fillRect(0, 0, 400, 400);
				ArrayDeque<block> body = snake.getBody();
				g.setColor(snake.getColor());
				//以下绘制蛇
				for(block b:body){
					g.fillRect(b.getX()*Ground.interval, b.getY()*Ground.interval,Ground.interval, Ground.interval);
				}
				
				//绘制Food
				g.setColor(food.getColor());
				g.fillRect(food.getX()*Ground.interval, food.getY()*Ground.interval, Ground.interval, Ground.interval);
			}
		};
		JP.setBounds(0,0,400,400);
		JF.add(JP);
		//添加监听
		JF.addKeyListener(sl);
		JP.addKeyListener(sl);
		new Thread(new Main()).start();;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				snake.Move();
				JP.repaint();
				canChangeDirection = true;
				Thread.sleep(60);
			}
		}catch(Exception e){
			System.out.println("程序故障");
		}
	}
}

class SnakeListener implements KeyListener{
	Snake snake = Snake.getInstance();

	@Override
	public void keyPressed(KeyEvent k) {
		if(!Main.canChangeDirection)return;
		switch(k.getKeyCode()){
		case 38:snake.FirstChangeDirection(0);break;
		case 39:snake.FirstChangeDirection(1);break;
		case 40:snake.FirstChangeDirection(2);break;
		case 37:snake.FirstChangeDirection(3);break;
		}
		//每次变向都必须显示出来才能进行下一次变向
		Main.canChangeDirection = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
