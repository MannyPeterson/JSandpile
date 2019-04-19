import processing.core.PApplet;

public class JSandpile extends PApplet {
	public class Pile {
		private int height;
		private Pile[][] piles;
		private int x;
		private int y;

		public Pile(Pile[][] piles, int x, int y) {
			this.piles = piles;
			this.x = x;
			this.y = y;
			this.height = 0;
		}

		public void add(int height) {
			this.height += height;
			if (this.height > 3) {
				this.height -= 4;
				this.piles[this.x - 1][this.y].add(1);
				this.piles[this.x + 1][this.y].add(1);
				this.piles[this.x][this.y - 1].add(1);
				this.piles[this.x][this.y + 1].add(1);
			}
		}

		public int height() {
			return this.height;
		}

	}

	public static void main(String[] args) {
		PApplet.main("JSandpile");
	}

	private final int HEIGHT = 1000;
	private Pile[][] piles;
	private final int WIDTH = 1000;
	private int blah = 0;

	@Override
	public void draw() {
		this.piles[WIDTH / 2][HEIGHT / 2].add(4);
		
		if(blah > 1000) {
			blah = 0;
			loadPixels();
			for (int i = 0, y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (this.piles[x][y].height == 0) {
						pixels[i] = color(255, 255, 0);
					} else if (this.piles[x][y].height == 1) {
						pixels[i] = color(0, 255, 0);
					} else if (this.piles[x][y].height == 2) {
						pixels[i] = color(0, 0, 255);
					} else if (this.piles[x][y].height == 3) {
						pixels[i] = color(0, 255, 255);
					}
					i += 1;
				}
			}
			updatePixels();
		} else {
			blah += 1;
		}

	}
	@Override
	public void settings() {
		size(WIDTH, HEIGHT);
	}

	@Override
	public void setup() {
		this.piles = new Pile[WIDTH][HEIGHT];
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				this.piles[x][y] = new Pile(this.piles, x, y);
			}
		}
	}
}
