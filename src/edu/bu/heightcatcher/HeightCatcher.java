package edu.bu.heightcatcher;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
//import android.graphics.Paint;
//import android.graphics.Color;

public class HeightCatcher extends Activity implements OnTouchListener {
   private static final String TAG = "Touch" ;
   int nReferenceX1 = 0;
   int nReferenceY1 = 0;
   int nReferenceX2 = 0;
   int nReferenceY2 = 0;
   
   int nObjectX1 = 0;
   int nObjectY1 = 0;
   int nObjectX2 = 0;
   int nObjectY2 = 0;
   int n = 1; // figure out variables to set by moving n from 1 to 4
   
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      ImageView view = (ImageView) findViewById(R.id.imageView);
      view.setOnTouchListener(this); 
   }
   
   @Override
   public boolean onTouch(View v, MotionEvent event) {
	  // Dump touch event to log
	  //dumpEvent(event);
	  StringBuilder sbRefLength = new StringBuilder();
	  StringBuilder sbObjLength = new StringBuilder();
	  
	  float refLength;
	  float objLength;
	  
	  setBounds(event, n);
	  

	  
	  // increment n up to 8 and then reset to 1
	  if (n < 8) {
		  n++;
	  } else {
//		  Paint paint = new Paint();
//		  paint.setColor(Color.RED);
//		  drawLine(nReferenceX1, nReferenceY1, nReferenceX2, nReferenceY2, paint);

		  refLength = distancePixels(nReferenceX1, nReferenceY1, nReferenceX2, nReferenceY2);
		  sbRefLength.append("Ref Length: ").append(refLength).append("\n");
		  Log.d(TAG, sbRefLength.toString());
		  objLength = distancePixels(nObjectX1, nObjectY1, nObjectX2, nObjectY2);
		  sbObjLength.append("Object Length: ").append(objLength).append("\n");
		  Log.d(TAG, sbObjLength.toString());
		  n = 1;
	  }
	  
	  return true; // indicate event was handled
   }
   
   /** Find the distance between two points */
   private float distancePixels(int nX1, int nY1, int nX2, int nY2)
   {
	   return FloatMath.sqrt((nX2-nX1)^2 + (nY2-nY1)^2);
   }   
   
   /** Set the bounds of the Reference and Object. 
    *  Then read out to Log for Debugging */
   private void setBounds(MotionEvent event, int call){
	   // Set the bounds for the Reference Object and the Object
	   
	   StringBuilder sb = new StringBuilder();
	   
	   // set for all odd numbers because even numbers are the 
	   // second piece of data in the multitouch (ignored)
	   if (call == 1) {
		   
		   nReferenceX1 = (int) event.getX();
		   sb.append("Ref X1: ").append(nReferenceX1).append("\n");
		   nReferenceY1 = (int) event.getY();
		   
		   sb.append("Ref Y1: ").append(nReferenceY1).append("\n");
	   } else if (call == 3) {
		   nReferenceX2 = (int) event.getX();
		   sb.append("Ref X2: ").append(nReferenceX2).append("\n");
		   nReferenceY2 = (int) event.getY();
		   sb.append("Ref Y2: ").append(nReferenceY2).append("\n");
	   } else if (call == 5) {
		   nObjectX1 = (int) event.getX();
		   sb.append("Obj X1: ").append(nObjectX1).append("\n");
		   nObjectY1 = (int) event.getY();
		   sb.append("Obj Y1: ").append(nObjectY1).append("\n");
	   } else if (call == 7) {
		   nObjectX2 = (int) event.getX();
		   sb.append("Obj X2: ").append(nObjectX2).append("\n");
		   nObjectY2 = (int) event.getY();
		   sb.append("Obj Y2: ").append(nObjectY2).append("\n");
	   }
	   Log.d(TAG, sb.toString());
   }
  
   /* Show an event in the LogCat view, for debugging 
   private void dumpEvent(MotionEvent event) {
      String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
         "POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
      StringBuilder sb = new StringBuilder();
      int action = event.getAction();
      int actionCode = action & MotionEvent.ACTION_MASK;
      sb.append("event ACTION_" ).append(names[actionCode]);
      if (actionCode == MotionEvent.ACTION_POINTER_DOWN
            || actionCode == MotionEvent.ACTION_POINTER_UP) {
         sb.append("(pid " ).append(
         action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
         sb.append(")" );
      }
      sb.append("[" );
      for (int i = 0; i < event.getPointerCount(); i++) {
         sb.append("#" ).append(i);
         sb.append("(pid " ).append(event.getPointerId(i));
         sb.append(")=" ).append((int) event.getX(i));
         sb.append("," ).append((int) event.getY(i));
         if (i + 1 < event.getPointerCount())
            sb.append(";" );
      }
      sb.append("]" );
      Log.d(TAG, sb.toString());
   }
   */
}