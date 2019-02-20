package Model;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;

import Basic.Ground;
import Basic.block;
/**
 * @date 2018/8/18
 * @author 阿良和可可
 * @comment 食物类用于规定食物的出现地点，食物的出现最大个数
 */
public class Food
{
	private static block food = new block(0,0);
	private static Food f1 = null;
	private Food(){
		setFood();
	}
	public void setFood(){
		ArrayList<int[]> dots = CountAllUsable();
		int rd = (int)Math.ceil(Math.random()*dots.size());
		food.setX(dots.get(rd)[0]);
		food.setY(dots.get(rd)[1]);
	}
	//获取能够出现食物的点的集合
	private static ArrayList<int[]> CountAllUsable(){
		ArrayList<int[]> dots = new ArrayList<int[]>();
		Snake snake = Snake.getInstance();
		ArrayDeque<block> body = snake.getBody();
		boolean flag = true;
		for(int i=0;i<Ground.maxLength;i++){
			for(int j=0;j<Ground.maxLength;j++){
				flag = true;
				for(block b:body)
				{
					if(j==b.getX()&&i==b.getY())
						flag = false;
				}
				if(flag)
					dots.add(new int[]{j,i});
			}
		}
		return dots;
	}
	
	public static Food getFood(){
		if(f1==null)
			f1 = new Food();
		return f1;
	}
	
	public static int getX(){
		return food.getX();
	}
	
	public static int getY(){
		return  food.getY();
	}
	
	public Color getColor(){
		return food.getColor();
	}
}
