package com.fiveotwo.core;

import playn.core.Surface;
import static playn.core.PlayN.assets;

public class Player {
	Map mapa;
	public boolean isalive=true;
	public Vector2 position=new Vector2(0,0);
	public Animation idleAnimation,killAnimation;
	public AnimationPlayer sprite = new AnimationPlayer();
	public Rectangle localBounds;
	public boolean MovX,MovY,left,right,up,down,dig,direct;
	public float previousBottom,previousHeight;
	
	public Player(Map map, Vector2 position){
		this.mapa=map;
		this.position=position;
		LoadContent();
		sprite.PlayAnimation(idleAnimation);
		MovX=false;
		MovY=false;
	}
	public Map map(){
		return this.mapa;
	}
	public Rectangle BoundingRectangle() {
		    int left = (int) (Math.round(Position().X - sprite.Origin().X) + localBounds.Left);
		    int top = (int) (Math.round(Position().Y - sprite.Origin().Y) + localBounds.Top);
		    return new Rectangle(left, top, localBounds.Width, localBounds.Height);
	}
	
	public Vector2 Position() {
		    return this.position;
	}
	public void SetPosition(Vector2 pos){
		this.position=pos;
	}  

	public void Update(){	
		if(isalive){
		int x=(int)Math.floor(this.Position().X/30);
		int y=(int)Math.floor(this.Position().Y/30);
		if (left==true){
			x=x-1;
			int collision = map().GetCollision(x, y);			
			switch(collision){
			case 0:
				this.Position().X=x*30;
				break;
			case 1:				
				break;
			case 2:
				mapa.SetNullCollision(x, y);
				this.Position().X=x*30;
				mapa.onTreasure();
				break;
			case 3:
				mapa.SetNullCollision(x, y);
				this.Position().X=x*30;
				mapa.onSkull();
				break;
			case 4:	
				break;
			}
			}
		if (right==true){
			x=x+1;
			int collision = map().GetCollision(x, y);			
			switch(collision){
			case 0:
				this.Position().X=x*30;
				break;
			case 1:				
				break;
			case 2:
				mapa.SetNullCollision(x, y);
				this.Position().X=x*30;
				mapa.onTreasure();
				break;
			case 3:
				mapa.SetNullCollision(x, y);
				this.Position().X=x*30;
				mapa.onSkull();
				break;
			case 4:	
				break;
			
			}
		}
		if (up==true){
			y=y-1;
			int collision = map().GetCollision(x, y);			
			switch(collision){
			case 0:
				this.Position().Y=y*30;
				break;
			case 1:
				break;
			case 2:
				mapa.SetNullCollision(x, y);
				this.Position().Y=y*30;
				mapa.onTreasure();
				break;
			case 3:
				mapa.SetNullCollision(x, y);
				this.Position().Y=y*30;
				mapa.onSkull();
				break;
			case 4:	
				break;
			}}
		if (down==true){
			y=y+1;
			int collision = map().GetCollision(x, y);			
			switch(collision){
			case 0:
				this.Position().Y=y*30;
				break;
			case 1:				
				break;
			case 2:
				mapa.SetNullCollision(x, y);
				this.Position().Y=y*30;
				mapa.onTreasure();
				break;
			case 3:
				mapa.SetNullCollision(x, y);
				this.Position().Y=y*30;
				mapa.onSkull();
				break;
			case 4:					
				break;
			}		
			}
		if(dig){
			if(direct){
				x=x+1;
				if(map().GetCollision(x, y)==4)
					mapa.SetNewTile(x, y);
			}else{
				x=x-1;
				if(map().GetCollision(x, y)==4)
					mapa.SetNewTile(x, y);
			}				
		}
		
		sprite.PlayAnimation(idleAnimation);		
		}else{
			MovX=false;
			sprite.PlayAnimation(killAnimation);		
				
		}
	}
	
	public void Draw(Surface surf){
		if(isalive){
			sprite.Draw(1f, surf, position, MovX);	
		}else{
			sprite.Draw(1f, surf, position, false);
		}
	}
	public void LoadContent() {
           Dig.watcher.add(assets().getImage("images/digdude.png"));
           Dig.watcher.add(assets().getImage("images/RIP.png"));
			idleAnimation = new Animation(assets().getImage("images/digdude.png"), 0.8f,30f, true);
			killAnimation =new Animation(assets().getImage("images/RIP.png"), 0.8f,30f, false);			
	 }
	
}
