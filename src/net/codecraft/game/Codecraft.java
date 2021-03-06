package net.codecraft.game;
import org.joml.Vector3f;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.joml.Math;
import org.joml.Matrix3f;

import net.codecraft.blocks.Block;
import net.codecraft.blocks.BlockDirt;
import net.codecraft.renderEngine.*;
public class Codecraft {
	
public Codecraft(String version, boolean dev, boolean demo, boolean showMode) {
DisplayUtills.createDisplay(version);
DisplayUtills.initGL();
try {
	Mouse.create();
	Mouse.setGrabbed(true);
} catch (LWJGLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Vector3f up = new Vector3f(0, 1, 0);
Vector3f lookAt = new Vector3f(0, 0, 1);
Vector3f cameraPosition = new Vector3f(0, 0, 0);
float vs = 0f;
float hsf = 0f;
float hslr= 0f;

float d = 0;
long startTime = System.currentTimeMillis();
while (!Display.isCloseRequested()) {
	
	float yawRadian  = (float) (DisplayUtills.getRotX() * (Math.PI / 180));
	float  pitchRadian  = 90;
	Vector3f forwardVector = new Vector3f(lookAt.x - cameraPosition.x, lookAt.y - cameraPosition.y, lookAt.z - cameraPosition.z); 
	
	forwardVector.normalize() ;
	boolean wp = Keyboard.isKeyDown(Keyboard.KEY_W);
	
	if(wp) {
		
		if(hsf < 0.30f) {
			hsf += 0.15f;
		}
		
		
	}

boolean sp = Keyboard.isKeyDown(Keyboard.KEY_S);
if(sp) {
	
	if(hsf > -0.30f) {
		hsf -= 0.15f;
	}
	
	
}
if(!wp& !sp) {
	
	if(hsf > 0.00f) {
		hsf -= 0.15f;
		
	}
 if(hsf < 0.00f) {
		hsf += 0.15f;
		
	}
	
}
	float NX = hsf * Math.sin( yawRadian )* Math.cos( pitchRadian );
	float DZ = hsf *  Math.cos( yawRadian )* Math.cos( pitchRadian );
	DisplayUtills.setPosX(DisplayUtills.getPosX() + NX);
	DisplayUtills.setPosZ(DisplayUtills.getPosZ() - DZ);
	
	
	boolean ap = Keyboard.isKeyDown(Keyboard.KEY_A);

		if(ap) {
			
			if(hslr < 0.24f) {
				hslr += 0.12f;
			}
			
			
		}

	
boolean dp = Keyboard.isKeyDown(Keyboard.KEY_D);

if(dp) {
	
	if(hslr > -0.24f) {
		hslr -= 0.12f;
	}
	
	
}

	

if(!ap & !dp) {
	
	if(hslr > 0.00f) {
		hslr -= 0.12f;
		
	}
	else if(hslr < 0.00f) {
		hslr += 0.12f;
		
	}	

}
	DisplayUtills.setPosX(DisplayUtills.getPosX() - hslr *( Math.cos( yawRadian )* Math.cos( pitchRadian )));
	DisplayUtills.setPosZ(DisplayUtills.getPosZ() - hslr *  (Math.sin( yawRadian )* Math.cos( pitchRadian )));
	if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
	
		
	}	DisplayUtills.setPosX(DisplayUtills.getPosX() - hslr *( Math.cos( yawRadian )* Math.cos( pitchRadian )));
	DisplayUtills.setPosZ(DisplayUtills.getPosZ() - hslr *  (Math.sin( yawRadian )* Math.cos( pitchRadian )));

	if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
	
/*
		if(DisplayUtills.getPosY()==0){
			vs -= 0.63f;
		}
		*/
		DisplayUtills.setPosY(DisplayUtills.getPosY() - 0.1f);
		
	}
	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
		
		/*
				if(DisplayUtills.getPosY()==0){
					vs -= 0.63f;
				}
				*/
				DisplayUtills.setPosY(DisplayUtills.getPosY() + 0.1f);
				
			}
	if(DisplayUtills.getPosY()<0){
		vs += 0.06f;
	}
/*
	DisplayUtills.setPosY(DisplayUtills.getPosY() + vs);
	if(DisplayUtills.getPosY()>=0.0f){
		DisplayUtills.setPosY(0);
		vs = 0;
	}*/
	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		Display.destroy();
		Mouse.destroy();
		System.exit(0);
		
	}
	int DX = Mouse.getDX()*2;
	int DY = Mouse.getDY()*2;
	float Y = DisplayUtills.getRotY() + DY*100;
	if(DX != 0) {
	DisplayUtills.setRotX(DisplayUtills.getRotX() + (DX/10f));
	}
	DisplayUtills.setRotY(DisplayUtills.getRotY() + (DY/10f));
	
	if(d >=25) {
		DisplayUtills.UpdateActiveChunks();
		d = 0;
	}
	//System.out.println(d); 
	int BX = 0;
	 int BZ = 0;
	 float rotX = DisplayUtills.getRotX();
	 float rotY = DisplayUtills.getRotY();
	 if (rotX < 45 && rotX > -45 && rotY < 45 && rotY > -45) {
		BX = 0;
		BZ = -4;
	 } else if (rotX > 45 && rotX < 90 && rotY < 45 && rotY > -45) {
		BX = 2;
		BZ = -2;
	 } else if (rotX < -45 && rotX > -90 && rotY < 45 && rotY > -45) {
		BX = -2;
		BZ = -2;
	 } else if (rotX > 90 && rotX < 135 && rotY < 45 && rotY > -45) {
		BX = 4;
		BZ = 0;
	 } else if (rotX < -90 && rotX > -135 && rotY < 45 && rotY > -45) {
		BX = -4;
		BZ = 0;
	 } else if (rotX > 135 && rotX < 180 && rotY < 45 && rotY > -45) {
		BX = 2;
		BZ = 2;
	 } else if (rotX < -135 && rotX > -180 && rotY < 45 && rotY > -45) {
		BX = -2;
		BZ = 2;
	 } else if (rotX > 180 && rotX < 225 && rotY < 45 && rotY > -45) {
		BX = 0;
		BZ = 4;
	 } else if (rotX < -180 && rotX > -225 && rotY < 45 && rotY > -45) {
		BX = 0;
		BZ = 4;
	 } else if (rotX > 225 && rotX < 270 && rotY < 45 && rotY > -45) {
		BX = -2;
		BZ = 2;
	 } else if (rotX < -225 && rotX > -270 && rotY < 45 && rotY > -45) {
		BX = 2;
		BZ = 2;
	 } else if (rotX > 270 && rotX < 315 && rotY < 45 && rotY > -45) {
		BX = -4;
		BZ = 0;
	 } else if (rotX < -270 && rotX > -315 && rotY < 45 && rotY > -45) {
		BX = 4;
		BZ = 0;
	 } else if (rotX > 315 && rotX < 360 && rotY < 45 && rotY > -45) {
		BX = -2;
		BZ = -4;
	 } else if (rotX < -315 && rotX > -360 && rotY < 45 && rotY > -45) {
		BX = 2;
		BZ = -2;
	 }
	 float posX = DisplayUtills.getPosX();
	 float posY = DisplayUtills.getPosY();
	 float posZ = DisplayUtills.getPosZ();
	 //BlockLocation bl = DisplayUtills.getBlockLocation((int)(posX+BX) , (int)posY, (int)(posZ+BZ));
	 //BlockDirt b = new BlockDirt(bl.x, bl.y, bl.z);
	 //DisplayUtills.placeBlock(bl, b);
	System.out.println("rot X:"+ DisplayUtills.getRotX());
	System.out.println("pos X:"+ DisplayUtills.getPosX());
	System.out.println("pos Z:"+ DisplayUtills.getPosZ());
	DisplayUtills.renderGL();
    try {
		Thread.sleep(1000/60);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    Display.update();
  if(System.currentTimeMillis() - startTime >= 3000) {
	  DisplayUtills.calculateActiveChunks();
	  startTime = System.currentTimeMillis();
  }
}
Mouse.destroy();
Display.destroy();
}
}

