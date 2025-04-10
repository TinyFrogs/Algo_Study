import java.util.*;

public class PRG_서버증설횟수 {

	static final int TIME = 24;
	static int answer = 0;
	static int currentServerCount = 0;
	static List<Server> servers = new ArrayList<>();

	static class Server{
		int startTime, count;
		boolean status;

		public Server(int startTime, int count) {
			this.startTime = startTime;
			this.count = count;
			this.status = true;
		}
	}

	public int solution(int[] players, int m, int k) {
		for(int time = 0 ; time < TIME; time++){
			int needServerCount = players[time] / m;
			findCreateServerCount(needServerCount, time);
			deleteServer(time, k);
		}
		return answer;
	}


	static void findCreateServerCount(int serverCount, int time){
		if(currentServerCount >= serverCount) return;
		int plusCount = serverCount - currentServerCount;
		servers.add(new Server(time, plusCount));
		currentServerCount += plusCount;
		answer += plusCount;

	}

	static void deleteServer(int time, int k){
		for(Server server: servers){
			if(!server.status) continue;
			if(server.startTime + (k - 1) > time) continue;
			server.status = false;
			currentServerCount -= server.count;
		}
	}
}
