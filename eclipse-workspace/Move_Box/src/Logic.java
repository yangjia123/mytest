import java.util.*;

public class Logic{
	
    static boolean flag; //用来判断是否能到达目标位置
    static int move[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static int[][][] direction = new int[10][10][5];	//第三维表示箱子在（i,j）时，是由哪个方向推过来的
	
    
    //判断推的次数
	public static void JudgePushTimes(Map map, Point start, Point end, Point people)
	{
		//对start赋值
		Node start_node = new Node();
		start_node.now = start;
		start_node.before = people;
		start_node.step = 0;
		
		//调用函数Box_bfs进行广度优先遍历
		Box_bfs(map, start_node, end, people);
	}
	

	//判断点是否在房间外部
	private static boolean Judge_Point(Point p, Map map)
	{
		if(p.x<0 || p.x>=map.N || p.y<0 || p.y>=map.M)
			return true;
		else
			return false;
	}
	
	
	//判断人是否能够到达目标位置
	private static boolean people_bfs(Map map, Node temp, Point stand)
	{
		boolean[][] f = new boolean[10][10];	//判断位置是否已经到过
		Node start1 = new Node();
		Node tempt = new Node();
		
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++)
			{
				f[i][j] = false;
			}
		}
		
		Queue<Node> queue1 = new PriorityQueue<Node>(11,
                  new Comparator<Node>() {
                  public int compare(Node s1, Node s2) {
                	 return (int)(s1.step - s2.step);
                  }
                });  
		
		start1.now.x = temp.before.x;
		start1.now.y = temp.before.y;
		f[temp.before.x][temp.before.y] = true;
		start1.step = 0;
		queue1.offer(start1);
		
		while(!queue1.isEmpty())
		{
			tempt.now.x = queue1.element().now.x ;
			tempt.now.y = queue1.element().now.y ;
			tempt.step = queue1.element().step;
			queue1.poll();
			
			if(tempt.now.x ==stand.x && tempt.now.y ==stand.y)	//到达指定位置
				return true;
			
			//判断上下左右四个方向是否可行
			for(int i=0 ; i<4; i++)
			{	
				start1.now.x = tempt.now.x + move[i][0];
				start1.now.y = tempt.now.y + move[i][1];
				//碰壁、碰到障碍物、该点已到达过、为同一点的情况均不可行
				if(Judge_Point(start1.now, map) || map.Room[start1.now.x][start1.now.y]==1 || (start1.now.x == temp.now.x && start1.now.y == temp.now.y))
					continue;
				if(f[start1.now.x][start1.now.y])
					continue;
				
			    start1.step = tempt.step + 1;
				f[start1.now.x][start1.now.y] = true;
				queue1.offer(new Node(start1.now.x,start1.now.y,start1.step));
			}
		}
		return false;
	}
	
	
	
	private static void Box_bfs(Map map,Node start, Point end, Point people)
	{
		 Queue<Node> queue = new PriorityQueue<Node>(11,  
	                new Comparator<Node>() {  
	                  public int compare(Node s1, Node s2) {  
	                    return s1.step - s2.step;  
	                  }  
	                });  
		 
		 
		 //Arrays.fill(direction, -1);//初始化用于记录方向的数组全部为-1
		 for(int i = 0;i<10;i++)
			 for(int j = 0;j<10; j++)
				 for(int k=0; k<5; k++)
					 direction[i][j][k] = -1;
		 
		Node temp = new Node();        //用于记录箱子未移动前的位置
		queue.add(start);
		
		
		while(!queue.isEmpty())
		{
			temp.now.x = queue.peek().now.x ;
			temp.now.y = queue.peek().now.y ;
			temp.before.x = queue.peek().before.x ;
			temp.before.y = queue.peek().before.y ;
			temp.step = queue.peek().step;
			queue.poll();
			
			if(temp.now.x== end.x && temp.now.y == end.y)
			{
				flag = true;
				System.out.println(temp.step);
				return ;
			}

			for(int i = 0;i < 4;i++)
			{
				start.now.x = temp.now.x + move[i][0];
				start.now.y = temp.now.y + move[i][1];

				//该方向已经走过
				if(Judge_Point(start.now, map) || map.Room[start.now.x][start.now.y ] == 1)
					continue;
				
				if(direction[start.now.x][start.now.y][i] == i)
					continue;
				
				
				Point stand = new Point();	//推箱子时人站的位置
				stand.x = temp.now.x - move[i][0];
				stand.y = temp.now.y - move[i][1];

				//越界判断
				if(Judge_Point(stand, map) || map.Room[stand.x][stand.y] == 1) 
					continue;
				
				//判断人能否到达该位置
				if(people_bfs(map, temp, stand))
				{
					start.before.x = temp.now.x;
					start.before.y = temp.now.y;
					start.step = temp.step + 1;
					direction[start.now.x][start.now.y][i] = i;
					
					queue.add(new Node(start.now.x,start.now.y,start.step));
				}
				
			}

		}
		
		//搜索不到路径，输出-1
		if(flag == false)
			System.out.println("-1");
	
    }

}