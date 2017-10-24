import java.util.*;

public class Main{
	
	public static void main(String[] args){
		
		Point start = new Point();
		Point end = new Point();
		Point people = new Point();
		Logic logic = new Logic();
		
		//����������ݵ�����
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		//���в���
		while(T>=1)
		{
			Map map = new Map();
			
			//����������
			map.M= scan.nextInt();
			map.N = scan.nextInt();
			
			//���뷿��Ĳ��֣�����ȡ���ӵ���ʼλ�á��յ�λ�ü��˵���ʼλ��
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
			
			//�ж�������Ҫ���ƵĴ���
			logic.JudgePushTimes(map, start, end, people);
			
			T--;
		}
	
	}
	
}