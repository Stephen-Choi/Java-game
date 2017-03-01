package games;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void clearEnemy() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tem = object.get(i);
			
			if (tem.getid() != ID.Player) {
				removeObject(tem);
				i--;
			}
		}
	}
	
	public void fullClear() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tem = object.get(i);
			removeObject(tem);
			i--;
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
