package com.fiveotwo.core;

/*
 * 2012, 502Studios 
 * This sources code is given "as is", you can use it as you want as long you give credit.
 */

public class Vector2 {

  public float X;
  public float Y;

  public Vector2(float X, float Y) {
    this.X = X;
    this.Y = Y;
  }

  public Vector2() {}

  public Vector2 sub(Vector2 v) {
    return new Vector2(this.X - v.X, this.Y - v.Y);
  }

  public float LengthSquared() {
    return this.X * this.X + this.Y * this.Y;
  }

  public Vector2 mul(float a) {
    return new Vector2(this.X * a, this.Y * a);
  }
  
  public Vector2 add(float a) {
	    return new Vector2(this.X + a, this.Y + a);
	  }
  
  public Vector2 res(float a) {
	    return new Vector2(this.X - a, this.Y - a);
	  }

  public Vector2 add(Vector2 v) {
    return new Vector2(this.X + v.X, this.Y + v.Y);
  }
  public Vector2 res(Vector2 v) {
	    return new Vector2(this.X - v.X, this.Y - v.Y);
	  }

  public Vector2 mul(Vector2 v) {
    return new Vector2(this.X * v.X, this.Y * v.Y);
  }
  public Vector2 res(Vector2 v, Vector2 V){	  
	  if(v.Y>V.Y){
		  if(v.X>V.X){
			  return new Vector2(this.X * (v.X-V.X), this.Y * (v.Y-V.Y));
		  }else{
			  return new Vector2(this.X * (-v.X+V.X), this.Y * (v.Y-V.Y));
		  }
	  }else{
		  if(v.X>V.X){
			  return new Vector2(this.X * (v.X-V.X), this.Y * (V.Y-v.Y));
		  }else{
			  return new Vector2(this.X * (-v.X+V.X), this.Y * (V.Y-v.Y));
		  }
	  }
  }
}
