package Levels;

import TileMaps.TileMaps;

public class Level1 extends Level {

    public Level1() {

        super();
        this.initLevel();
    }

    @Override
    public void initLevel() {

        super.initLevelObjects(TileMaps.overWorldLevel1);
       /* Mario mario = new Mario(new Position(500, 200), GlobalVariables.marioTag);
         Mushroom mushroom = new Mushroom(new Position(1700, 200));
        Goomba goomba = new Goomba(new Position(1600, 200), GlobalVariables.goombaTag);
        super.addGameObject(goomba);
        super.setCamera(new Camera(mario));
          super.addGameObject(mushroom);
        super.addGameObject(mario);

      //  this.initGround(0, 500, 10, 25, 50, 50);
    }

  /*  private void initGround(int x, int y, int iLimit, int jLimit, int xIncrementer, int yIncrementer) {

        for (int i = 0; i < iLimit; i++) {

            for (int j = 0; j < jLimit; j++) {

                BrickBox.drawBox(x, y);

                x += xIncrementer;
            }

            x = 0;
            y += yIncrementer;
        }

        BrickBox brickBox1 = new BrickBox(new Position(300,300),GlobalVariables.brickBoxTag);
        super.addGameObject(brickBox1);
        BrickBox brickBox = new BrickBox(new Position(0,500),GlobalVariables.brickBoxTag,1250,10);
        super.addGameObject(brickBox);
    }

   */
    }
}
