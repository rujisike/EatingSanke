package Model;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import Basic.*;
/**
 * @date 2018/8/18
 * @author 阿良和可可
 *
 */
public class Snake 
{
	private static Snake snake = null;
	private ArrayDeque<block> body = new ArrayDeque<block>();
	private final int x = 20;
	private final int y = 20;
	private final int length = 3;
	private Color color = Color.green;
	private Snake()
	{
		for(int i=length;i>0;i--)
			body.add(new block(x,y+i,color));
	}
	public static Snake getInstance(){
		if(snake==null)
			snake = new Snake();
		return snake;
	}
	
	public void FirstChangeDirection(int dire)
	{
		if(Math.abs(body.getLast().getDire()-dire)==2)//不可掉头
			return;
		body.getLast().setDire(dire);//直接改变队列的尾部，也就是蛇的头部的方向即可
	}	
	public void Move()
	{
		block bf = body.getFirst();
		block bl = body.getLast();
		setXY(bf,bl,1);
		bf.setDire(bl.getDire());
		body.add(body.removeFirst());
		if(bf.getX()==Food.getX()&&bf.getY()==Food.getY())
			eat(new block(-1,-1));
	}
	
	public void eat(block b)
	{
		b.setDire(body.getFirst().getDire());//设置新块与方向
		b.setColor(color);
		setXY(b,body.getFirst(),-1);
		body.addFirst(b);
		
		Food.getFood().setFood();//产生新的食物
	}
	public void setXY(block bf,block bl,int fol){
		switch(bl.getDire()){
		case 0:
			bf.setX(bl.getX());//x和蛇头一样
			bf.setY(bl.getY()-fol);//Y比蛇头小1
		break;
		case 1:
			bf.setX(bl.getX()+fol);
			bf.setY(bl.getY());
		break;
		case 2:
			bf.setX(bl.getX());
			bf.setY(bl.getY()+fol);
		break;
		case 3:
			bf.setX(bl.getX()-fol);
			bf.setY(body.getLast().getY());
		break;
		}
		for(block b:body){
			if(bl.getX()==b.getX()&&bl.getY()==b.getY()&&!b.equals(bl))
				System.exit(0);
		}
	}
	public ArrayDeque<block> getBody() {
		return body;
	}
	public Color getColor() {
		return color;
	}
	
}
