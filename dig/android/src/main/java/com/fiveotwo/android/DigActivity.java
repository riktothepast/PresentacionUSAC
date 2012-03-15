package com.fiveotwo.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import android.content.pm.ActivityInfo;

import com.fiveotwo.core.Dig;

public class DigActivity extends GameActivity {

  @Override
  public void main(){
	  this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    platform().assets().setPathPrefix("com/fiveotwo/resources");
    PlayN.run(new Dig());
  }
}
