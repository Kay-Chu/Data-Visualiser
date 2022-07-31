
import java.util.ArrayList;
import java.util.Scanner;

public class TestClient {
	
	public static void main(String[] args) {
		boolean field[] = new boolean[7];
		for(int i = 0; i < field.length; i++) {
			field[i] = true;
		}
		UserRequest user = new UserRequest(field,0,0,-1,null,-1,1,false);
		DataRetriever get = new DataRetriever();
		Scanner kbd = new Scanner(System.in);
		char input;
		do {
			ArrayList<String[]> aList = get.retrieveData(user);
			int n;
			if(!aList.isEmpty()) {
				String s[] = aList.get(0);
				if(s.length > 0) {
					n = Integer.parseInt(s[0]);
					System.out.println(n);
				}
			}
			for(int i = 1; i < aList.size(); i++) {
				String s[] = aList.get(i);
				for(int j = 0; j < s.length; j++) {
					System.out.print(s[j]);
				}
				System.out.println();
			}
			System.out.print("Please enter a char: ");
			input = kbd.next().charAt(0);
			switch(input) {
			case 'p':user = user.previousPage();break;
			case 'n':user = user.nextPage();break;
			case 'e':continue;
			default :user = user.setPage(input-'0');
			}
		}while(input != 'e');
		kbd.close();
	}

}
