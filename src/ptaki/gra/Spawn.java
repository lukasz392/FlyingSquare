package ptaki.gra;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public void tick() {
		scoreKeep++;
		if(scoreKeep >= 50) {
			scoreKeep = 0;
			handler.addObject(new Tube(Game.WIDTH, 0, ID.Tube));
		}
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Tube) {
				Tube tube = (Tube) tempObject;
				if((tube.getX() + tube.getTubeWight()) < 0) {
					handler.removeObject(tempObject);
				}
			}
		}
	}
	public void setScoreKeep(int scoreKeep) {
		this.scoreKeep = scoreKeep;
	}

}
