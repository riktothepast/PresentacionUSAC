package com.fiveotwo.core;

import playn.core.Surface;

// Controls playback of an Animation.
public class AnimationPlayer {
	
	float frameIndex;
	private float time;
	public Animation animation;
	  
  // obtenemos la animacion a animar (redundant).
  public Animation Animation() {
    return animation;
  }

  // cuadro actual.
  public float FrameIndex() {
    return frameIndex;
  }
 
  // obtenemos la textura de la spritesheet justo en el centro..
  public Vector2 Origin() {
    return new Vector2(Animation().FrameWidth() /2, Animation().FrameHeight()/2);
  }

  // inicia o continua la animacion
  public void PlayAnimation(Animation animation) {
	  
    /* Verifica que la animacion no este en playback actual,
	  de lo contrario tendriamos un traslape de cuadros HORRIBLE
	  */
    if (Animation() == animation)
      return;
    
    // inicia una animacion nueva.
    this.animation = animation;
    this.frameIndex = 0;
    this.time = 0.0f;
  }

  // avanza el tiempo, cambia a cuadro nuevo de cumplirse el tiempo del actual.
  public void Draw(float gameTime, Surface surf, Vector2 position, boolean flipH) {
    if (Animation() == null)
      throw new RuntimeException("No animation is currently playing.");

    // cuenta el tiempo de animacion.
    time += gameTime;
    while (time > Animation().FrameTime()) {
      time -= Animation().FrameTime();

      // movemos al siguente cuadro, verificamos si loopea.
      if (Animation().IsLooping()) {
        frameIndex = (frameIndex + 1) % Animation().FrameCount();
      } else {
        frameIndex = Math.min(frameIndex + 1, Animation().FrameCount() - 1);
      }
    }
    // calcula el rectangulo...
    Rectangle source =
    new Rectangle(FrameIndex() * Animation().FrameWidth(), 0,  Animation().FrameWidth(), Animation().FrameHeight());
    // dibuja el cuadro actual..
    float startX = flipH ? (position.X+15 + Origin().X) : (position.X+15 - Origin().X);
    float width = flipH ? -source.Width : source.Width;
    surf.drawImage(Animation().Texture(), startX, position.Y+15 - Origin().Y,
        width, source.Height, source.Left, source.Top, source.Width, source.Height);
  }
}
