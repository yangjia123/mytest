public class Node {
	
	Point now = new Point();  //���ӵ�ǰλ��
	Point before = new Point(); //���ӵ���ǰλ��ǰ��λ��
	int step;    //���Ӵӿ�ʼ����ǰλ���ƶ�����
	
  public Node(int x , int y ,int step)  //���캯��
  {
	  this.now.x = x;
	  this.now.y = y;
	  this.step = step;
  }
  
  public Node()   //���캯��
  {
	  
  }
}
