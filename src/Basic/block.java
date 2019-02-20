package Basic;

import java.awt.Color;

/**
 * @date 2018/8/18
 * @author 阿良和可可
 * @comment block类是组成贪吃蛇的基本材料单位
 */
public class block {
	private Color color = Color.yellow;
	private int x = 0;
	private int y = 0;
	private int dire = 0;
	public block(int x,int y,Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public block(int x,int y){
		this.x = x;
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDire() {
		return dire;
	}

	public void setDire(int dire) {
		this.dire = dire;
	}
	
}
