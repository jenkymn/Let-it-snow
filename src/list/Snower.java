package lis;

public class Snower {
	/** 雪花位置 */
	private int x;
        private int y=0;
        //默认大小
        private int width=3;
        private int hight=3;
	
	/***
	 * 雪花类
	 */
	public Snower(int x) {
		this.x = x;
	}
	
	/***
	 * 雪花类
	 */
	public Snower(int x, int y) {
		this.x = x;
		this.y = y;
	}
        public Snower(int x,int w,int h){
            this.x=x;
            this.hight=h;
            this.width=w;
        }
        public Snower(int x,int y,int w,int h){
            this.x=x;
            this.y=y;
            this.hight=h;
            this.width=w;
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

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
        
        
}
