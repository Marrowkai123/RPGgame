package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import States.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] goblinArr;
	private ArrayList<Goblin> goblins = new ArrayList<>();
        private BufferedImage[][] skeletonArr;
	private ArrayList<Skeleton> skeletons = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		goblins = LoadSave.GetGoblins();
                skeletons = LoadSave.GetSkeletons();
	}

	public void update(int[][] lvlData, Player player) {
		for (Goblin goblin : goblins)
			if (goblin.isActive())
				goblin.update(lvlData, player);
                for (Skeleton skeleton : skeletons)
			if (skeleton.isActive())
				skeleton.update(lvlData, player);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawGoblins(g, xLvlOffset);
                drawSkeletons(g, xLvlOffset);
	}

	private void drawGoblins(Graphics g, int xLvlOffset) {
		for (Goblin goblin : goblins)
			if (goblin.isActive()) {
				g.drawImage(goblinArr[goblin.getEnemyState()][goblin.getAniIndex()], (int) goblin.getHitBox().x - xLvlOffset - GOBLIN_DRAWOFFSET_X + goblin.flipX(), (int) goblin.getHitBox().y - GOBLIN_DRAWOFFSET_Y,
						GOBLIN_WIDTH * goblin.flipW(), GOBLIN_HEIGHT, null);
//                                goblin.drawAttackBox(g,xLvlOffset);

			}
	}
        
        private void drawSkeletons(Graphics g, int xLvlOffset) {
		for (Skeleton skeleton : skeletons)
			if (skeleton.isActive()) {
				g.drawImage(skeletonArr[skeleton.getEnemyState()][skeleton.getAniIndex()], (int) skeleton.getHitBox().x - xLvlOffset - SKELETON_DRAWOFFSET_X + skeleton.flipX(), (int) skeleton.getHitBox().y - SKELETON_DRAWOFFSET_Y,
						SKELETON_WIDTH * skeleton.flipW(), SKELETON_HEIGHT, null);
//                                skeleton.drawAttackBox(g,xLvlOffset);
                        }
	}
        
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Goblin goblin : goblins)
			if (goblin.isActive())
				if (attackBox.intersects(goblin.getHitBox())) {
					goblin.hurt(10);
					return;
				}
                for (Skeleton skeleton : skeletons)
			if (skeleton.isActive())
				if (attackBox.intersects(skeleton.getHitBox())) {
					skeleton.hurt(5);
					return;
				}
	}

	private void loadEnemyImgs() {
		goblinArr = new BufferedImage[5][8];
                skeletonArr = new BufferedImage[5][8];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.GOBLIN_SPRITE);
                BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.SKELETON_SPRITE);
                
		for (int j = 0; j < goblinArr.length; j++)
			for (int i = 0; i < goblinArr[j].length; i++)
				goblinArr[j][i] = temp.getSubimage(i * GOBLIN_WIDTH_DEFAULT, j * GOBLIN_HEIGHT_DEFAULT, GOBLIN_WIDTH_DEFAULT, GOBLIN_HEIGHT_DEFAULT);
                for (int j = 0; j < skeletonArr.length; j++)
			for (int i = 0; i < skeletonArr[j].length; i++)
				skeletonArr[j][i] = temp2.getSubimage(i * SKELETON_WIDTH_DEFAULT, j * SKELETON_HEIGHT_DEFAULT, SKELETON_WIDTH_DEFAULT, SKELETON_HEIGHT_DEFAULT);
	}

	public void resetAllEnemies() {
		for (Goblin goblin : goblins)
			goblin.resetEnemy();
                for (Skeleton skeleton : skeletons)
			skeleton.resetEnemy();
	}

}
