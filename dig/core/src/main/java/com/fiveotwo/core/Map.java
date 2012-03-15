package com.fiveotwo.core;
import playn.core.*;
import static playn.core.PlayN.*;
import playn.core.Keyboard.TypedEvent;

/*
 * 2012, 502Studios 
 * This sources code is given "as is", you can use it as you want as long you give credit.
 */
public class Map {
	
	private ImmediateLayer layer;
	Surface surf;
	Image bgImage;
	public float updateRate=3f;
	public float update=0;
	//matriz que guarda todas las entidades del mapa (paredes,objetos, etc);	
	Tile tilemap[][];	
	//Todo juego debe tener un heroe.
	Player player;
	float updaterate;
	int Score;
	public Player Player() {
		  return player;
	 }
	//un mapa de 20X20
	public Map(){
		tilemap = new Tile[20][20];
	}
	//Actualizamos nuestro juego
	public void Update(float update){
		updateTiles();
		Player().Update();	
	}	
	
	public void Draw(float update){
		//dibujamos todo lo que hay en pantalla		
	updaterate=update;
	}
	public void updateTileLogic(){
		if (tilemap!=null){
			 for (int y = 0; y < 20; ++y) {
			      for (int x = 0; x < 20; ++x) {
				        	tilemap[x][y].Update();
				        	if(tilemap[x][y].Collision==3&&tilemap[x][y].cantrack){
				        		
				        		//its a skull, need to track the player
				        		int pX=(int)this.Player().Position().X/30;
				        		int pY=(int)this.Player().Position().Y/30;
				        		if(x>pX){
				        			if(tilemap[x-1][y].Collision==0){
			        					tilemap[x-1][y]=tilemap[x][y];
			        					SetNullCollision(x,y);
			        			}
				        		}else{
				        			if(tilemap[x+1][y].Collision==0){
				        				tilemap[x+1][y]=tilemap[x][y];
				        				SetNullCollision(x,y);
			        			}
				        		}
				        		if(pY>y){
				        			if(tilemap[x][y-1].Collision==0){
			        					tilemap[x][y-1]=tilemap[x][y];
			        					SetNullCollision(x,y);
				        			}
				        		}else{
				        			if(tilemap[x][y+1].Collision==0){
			        					tilemap[x][y+1]=tilemap[x][y];
			        					SetNullCollision(x,y);
			        				}
				        		}
				        			}
				        	}
			      }
			      }
		
	}
	
	public void updateTiles(){
		if (tilemap!=null){
			 for (int y = 0; y < 20; ++y) {
			      for (int x = 0; x < 20; ++x) {
				        	tilemap[x][y].Update();
			      }
			      }
		}
	}
	private void DrawTiles(Surface surf) {
		if (tilemap!=null){
	    // recorremos la matriz
	    for (int y = 0; y < 20; ++y) {
	      for (int x = 0; x < 20; ++x) {
	        // obtenemos la posicion
	        Animation texture = tilemap[x][y].anim;
	        if (texture != null) {
	          // dibujamos en la posicion deseada.
	        	 Vector2 position = new Vector2((float) x, (float) y).mul(Tile.Size);
	        	 tilemap[x][y].Draw(surf, position);
	        }
	      }
	    }
		}
	  }
	 public Rectangle GetBounds(int x, int y) {
		    return new Rectangle(x * Tile.Width, y * Tile.Height, Tile.Width, Tile.Height);
		  }
	public int Width() {return tilemap.length;}
	  // Height of the level measured in tiles.
	public int Height() {return tilemap[0].length;}
	  
	public int GetCollision(int x, int y) {
		//evita que nos salgamos del "mapa"
	    if (x < 0 || x >= Width())return 0;	    
	    if (y < 0 || y >= Height())return 0;
	    //regresa el tipo de colision segun el objeto que buscamos
	    return tilemap[x][y].Collision;
	  }
	public void SetNullCollision(int x, int y){
		tilemap[x][y]=new Tile(null, 0);
	}
	//reemplazamos una de las tiles del nivel, por una nueva.
	public void SetNewTile(int x, int y){
		double x1=Math.random()*1.5;
		if(x1<=0.5){
			tilemap[x][y]=new Tile(null, 0);
		}else if(x1>0.5&&x1<=1){
            Dig.watcher.add(assets().getImage("images/gema.png"));
			tilemap[x][y]=new Tile(assets().getImage("images/gema.png"), 2);
		}else if(x1>1&&x1<=1.5){
            Dig.watcher.add(assets().getImage("images/skullidle.png"));
			tilemap[x][y]=new Tile(assets().getImage("images/skullidle.png"), 3);
		}
	}
	//nuestra colision fue una calavera X.X
	public void onSkull(){
		
		this.Player().isalive=false;
	}
	//colisionamos contra un tesoro :3
	public void onTreasure(){
		
	}
	public void Init(){
		final Image bgImage = assets().getImage("images/bg.png");
	    Dig.watcher.add(bgImage);
	layer = graphics().createImmediateLayer(graphics().screenWidth(),graphics().screenHeight(),new ImmediateLayer.Renderer() {
    public void render(Surface surf) {
    	surf.clear();
    	surf.drawImage(bgImage, 0, 0);       
		DrawTiles(surf);
		Player().Draw(surf);
    }
  });
		keyboard().setListener(new Keyboard.Listener() {
			@Override
			public void onKeyUp(playn.core.Keyboard.Event event) {
				if(event.key()==Key.LEFT){
					Player().left=false;
				}
				if(event.key()==Key.RIGHT){
					Player().right=false;
				}
				if(event.key()==Key.UP){
					Player().up=false;
				}
				if(event.key()==Key.DOWN){
					Player().down=false;
				}
				if(event.key()==Key.X){
					Player().dig=false;
				}
				updateTileLogic();
			}		
			@Override
			public void onKeyTyped(TypedEvent event) {
				
			}		
			@Override
			public void onKeyDown(playn.core.Keyboard.Event event) {			
			if(event.key()==Key.LEFT){
				Player().MovX=false;
				Player().left=true;
				Player().direct=false;
			}
			if(event.key()==Key.RIGHT){
				Player().MovX=true;
				Player().right=true;
				Player().direct=true;
			}
			if(event.key()==Key.UP){
				Player().MovY=true;
				Player().up=true;
			}
			if(event.key()==Key.DOWN){
				Player().MovY=false;
				Player().down=true;
			}
			if(event.key()==Key.X){
				Player().dig=true;
			}
			}
		});
		layer.setAlpha(1f);
		graphics().rootLayer().add(layer);
		getLevel();
		RandomObjects();
		int w=(int) (Math.random()*20);
		int y=(int) (Math.random()*20);	
		addRandomPlayer(w,y);
	}
	public void addRandomPlayer(int w,int y){
		if(tilemap[w][y].Collision==0){
			player=new Player(this, new Vector2(w*30,y*30));
		}else{
			addRandomPlayer((int) (Math.random()*20),(int) (Math.random()*20));
		}
	}
	public Tile[][] getLevel(){
		//filling the level with nulls 
		for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tilemap[i][j] = new Tile(null, 0);
            }
        }
		//making a box
        Dig.watcher.add(assets().getImage("images/stone.png"));
        Dig.watcher.add(assets().getImage("images/skull.png"));

		for(int x=0;x<20;++x ){
			tilemap[x][0]=new Tile(assets().getImage("images/stone.png"), 1);}
		for(int x=0;x<20;++x ){
			tilemap[x][19]=new Tile(assets().getImage("images/stone.png"), 1);}
		for(int x=0;x<20;++x ){
			tilemap[0][x]=new Tile(assets().getImage("images/stone.png"), 1);}
		for(int x=0;x<20;++x ){
			tilemap[19][x]=new Tile(assets().getImage("images/stone.png"), 1);}	
		//we need some obstacles
		tilemap[12][1]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][1]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][2]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][2]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][3]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][3]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][4]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][4]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][5]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][5]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][6]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][6]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[12][7]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[13][7]=new Tile(assets().getImage("images/stone.png"), 1);
		
		tilemap[1][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[1][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[2][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[2][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[3][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[3][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[4][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[4][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[7][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[7][9]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[8][8]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[8][9]=new Tile(assets().getImage("images/stone.png"), 1);
		
		tilemap[5][4]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][5]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][4]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][5]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][6]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][7]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[7][6]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[7][7]=new Tile(assets().getImage("images/stone.png"), 1);
		
		tilemap[5][18]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][19]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][18]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][19]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][16]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][17]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][16]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][17]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][14]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[5][15]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][14]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[6][15]=new Tile(assets().getImage("images/stone.png"), 1);
		
		tilemap[7][14]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[7][15]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[8][14]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[8][15]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[9][14]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[9][15]=new Tile(assets().getImage("images/stone.png"), 1);
		
		tilemap[18][10]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[18][11]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[17][10]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[17][11]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[16][10]=new Tile(assets().getImage("images/stone.png"), 1);
		tilemap[16][11]=new Tile(assets().getImage("images/stone.png"), 1);
		
		return tilemap;		
	}
	
	public void RandomObjects(){
		for(int x=0;x<120;++x){
			int w=(int) (Math.random()*20);
			int y=(int) (Math.random()*20);			
			if(tilemap[w][y].Collision==0){
            Dig.watcher.add(assets().getImage("images/box.png"));
			tilemap[w][y]=new Tile(assets().getImage("images/box.png"), 4);
			}
		}
	}
}
