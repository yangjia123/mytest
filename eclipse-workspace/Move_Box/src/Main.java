import java.util.*;

public class Main{
	
	public static void main(String[] args){
		
		Point start = new Point();
		Point end = new Point();
		Point people = new Point();
		Logic logic = new Logic();
		
		//输入测试数据的数量
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		//进行测试
		while(T>=1)
		{
			Map map = new Map();
			
			//输入行列数
			map.M= scan.nextInt();
			map.N = scan.nextInt();
			
			//输入房间的布局，并提取箱子的起始位置、终点位置及人的起始位置
			for(int i=0; i<map.M; i++)
			{
				for(int j=0; j<map.N; j++)
				{
					map.Room[i][j] = scan.nextInt();
					if(map.Room[i][j] == 2)
					{
						start.x = i;
						start.y = j;
						
					}
					if(map.Room[i][j] == 3)
					{
						end.x = i;
						end.y = j;
			
					}
					if(map.Room[i][j] == 4)
					{
						people.x = i;
						people.y = j;
						
					}
				}
			}
			
			//判断箱子需要被推的次数
			logic.JudgePushTimes(map, start, end, people);
			
			T--;
		}
	
	}
	
}