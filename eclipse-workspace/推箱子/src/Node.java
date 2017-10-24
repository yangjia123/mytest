public class Node {
	
	Point now = new Point();  //箱子当前位置
	Point before = new Point(); //箱子到当前位置前的位置
	int step;    //箱子从开始到当前位置移动步数
	
  public Node(int x , int y ,int step)  //构造函数
  {
	  this.now.x = x;
	  this.now.y = y;
	  this.step = step;
  }
  
  public Node()   //构造函数
  {
	  
  }
}
