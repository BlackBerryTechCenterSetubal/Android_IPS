package com.example.ipsapp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class EventosActivity2 extends ActionBarActivity {
	
	ImageView mImageView;
	//ImageView demoImage = (ImageView) findViewById(R.id.DemoImage);
	int imagesToShow[] = { R.drawable.alienware, R.drawable.ic_launcher/*R.drawable.image3*/ };
	
	private static String[] opcoesGaveta={"Eventos","Mapas","Sobre..."};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private int indice_imagem_actual=0;
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super
		.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_eventos_activity2);//Troquei com o layout do "PlaceHolderFragment" - porque?: porque eu é que mando.
		
		//if (savedInstanceState == null) {getSupportFragmentManager().beginTransaction()
		//	.add(R.id.container, new PlaceholderFragment()).commit();
		//}
		
		//mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        dataList = new ArrayList<DrawerItem>();
        
     // Add Drawer Item to dataList
        dataList.add(new DrawerItem("Eventos", R.drawable.ic_eventos));
        dataList.add(new DrawerItem("Mapas", R.drawable.ic_map));
        dataList.add(new DrawerItem("Info", R.drawable.ic_info));
        
        adapter = new CustomDrawerAdapter(this, R.layout.drawer_list_item,
                dataList);
        mDrawerList.setAdapter(adapter);
        // Set the adapter for the list view
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,
          //      R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		//mImageView = new ImageView(this);
        mImageView = (ImageView) findViewById(R.id.imageView1);
		//mImageView.setImageBitmap(
			//			decodeSampledBitmapFromResource(getResources(), R.drawable.ic_launcher, 100, 100));
		//mImageView.setMaxHeight(10);
		//mImageView.setMaxWidth(10);
		//mImageView.setLeft(100);
		//addContentView(mImageView,new ViewGroup.LayoutParams(120, 120));
		animate(mImageView, imagesToShow, 0,true);
		
		mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        
     // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
            }
        };
        
     // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
        
        setTitle(R.string.eventos);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eventos_activity2, menu); 
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	/*	int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);*/
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	        // Handle your other action bar items...

	        return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.activity_eventos_activity2, container, false);//Troquei com o layout do "AddContent" - porque?: porque eu é que mando.
			return rootView;
		}
	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	    private final WeakReference<ImageView> imageViewReference;
	    private int data = 0;

	    public BitmapWorkerTask(ImageView imageView) {
	        // Use a WeakReference to ensure the ImageView can be garbage collected
	        imageViewReference = new WeakReference<ImageView>(imageView);
	    }

	    // Decode image in background.
	    @Override
	    protected Bitmap doInBackground(Integer... params) {
	        data = params[0];
	        return decodeSampledBitmapFromResource(getResources(), data, 100, 100);
	    }

	    // Once complete, see if ImageView is still around and set bitmap.
	    
	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	        if (isCancelled()) {
	            bitmap = null;
	        }

	        if (imageViewReference != null && bitmap != null) {
	            final ImageView imageView = imageViewReference.get();
	            final BitmapWorkerTask bitmapWorkerTask =
	                    getBitmapWorkerTask(imageView);
	            if (this == bitmapWorkerTask && imageView != null) {
	                imageView.setImageBitmap(bitmap);
	            }
	        }
	    }
	}
	
	public void loadBitmap(int resId, ImageView imageView) {
	    //BitmapWorkerTask task = new BitmapWorkerTask(imageView);
	    //task.execute(resId);
		Bitmap mPlaceHolderBitmap= decodeSampledBitmapFromResource(getResources(), R.drawable.alienware, 100, 100);
		if (cancelPotentialWork(resId, imageView)) {
	        final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
	        final AsyncDrawable asyncDrawable =
	                new AsyncDrawable(getResources(), mPlaceHolderBitmap, task);
	        imageView.setImageDrawable(asyncDrawable);
	        task.execute(resId);
	    }
	}
	
	static class AsyncDrawable extends BitmapDrawable {
	    private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

	    public AsyncDrawable(Resources res, Bitmap bitmap,
	            BitmapWorkerTask bitmapWorkerTask) {
	        super(res, bitmap);
	        bitmapWorkerTaskReference =
	            new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
	    }

	    public BitmapWorkerTask getBitmapWorkerTask() {
	        return bitmapWorkerTaskReference.get();
	    }
	}
	
	public static boolean cancelPotentialWork(int data, ImageView imageView) {
	    final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

	    if (bitmapWorkerTask != null) {
	        final int bitmapData = bitmapWorkerTask.data;
	        // If bitmapData is not yet set or it differs from the new data
	        if (bitmapData == 0 || bitmapData != data) {
	            // Cancel previous task
	            bitmapWorkerTask.cancel(true);
	        } else {
	            // The same work is already in progress
	            return false;
	        }
	    }
	    // No task associated with the ImageView, or an existing task was cancelled
	    return true;
	}
	
	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		   if (imageView != null) {
		       final Drawable drawable = imageView.getDrawable();
		       if (drawable instanceof AsyncDrawable) {
		           final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
		           return asyncDrawable.getBitmapWorkerTask();
		       }
		    }
		    return null;
		}
	
	private void animate(final ImageView imageView, final int images[], final int imageIndex, final boolean forever) {

		  //imageView <-- The View which displays the images
		  //images[] <-- Holds R references to the images to display
		  //imageIndex <-- index of the first image to show in images[] 
		  //forever <-- If equals true then after the last image it starts all over again with the first image resulting in an infinite loop. You have been warned.

		    int fadeInDuration = 500; // Configure time values here
		    int timeBetween = 5000;
		    int fadeOutDuration = 500;

		    imageView.setVisibility(View.INVISIBLE);    //Visible or invisible by default - this will apply when the animation ends
		    imageView.setImageResource(images[imageIndex]);
		    indice_imagem_actual=imageIndex;
		    //((TextView)findViewById(R.id.titulo_eventos)).setText("Eventos"+indice_imagem_actual);

		    Animation fadeIn = new AlphaAnimation(0, 1);
		    fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
		    fadeIn.setDuration(fadeInDuration);

		    Animation fadeOut = new AlphaAnimation(1, 0);
		    fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
		    fadeOut.setStartOffset(fadeInDuration + timeBetween);
		    fadeOut.setDuration(fadeOutDuration);

		    AnimationSet animation = new AnimationSet(false); // change to false
		    animation.addAnimation(fadeIn);
		    animation.addAnimation(fadeOut);
		    animation.setRepeatCount(1);
		    imageView.setAnimation(animation);

		    animation.setAnimationListener(new AnimationListener() {
		        public void onAnimationEnd(Animation animation) {
		            if (images.length - 1 > imageIndex) {
		                animate(imageView, images, imageIndex + 1,forever); //Calls itself until it gets to the end of the array
		            }
		            else {
		                if (forever == true){
		                animate(imageView, images, 0,forever);  //Calls itself to start the animation all over again in a loop if forever = true
		                }
		            }
		        }
		        public void onAnimationRepeat(Animation animation) {
		            // TODO Auto-generated method stub
		        }
		        public void onAnimationStart(Animation animation) {
		            // TODO Auto-generated method stub
		        }
		    });
		}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    // Create a new fragment and specify the planet to show based on position
	    Fragment fragment = new PlanetFragment();
	    Bundle args = new Bundle();
	    args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
	    fragment.setArguments(args);
	    Intent intent;

	    // Insert the fragment by replacing any existing fragment
	    /*android.app.FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();*/
	    
	    switch(position){
	    case 0:break;
	    case 1:intent = new Intent(this,MapasActivity.class);    
				startActivity(intent);break;//Chama a janela dos mapas.
	    case 2:intent = new Intent(this,InfoAppActivity.class);    
				startActivity(intent);break;
	    }
	    // Highlight the selected item, update the title, and close the drawer
	    mDrawerList.setItemChecked(position, true);
	    mDrawerLayout.closeDrawer(mDrawerList);
	}
	
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}
	
	public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = opcoesGaveta[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            //getActivity().setTitle(planet);
            return rootView;
        }
    }
	
	/* Called whenever we call invalidateOptionsMenu() */
    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public void noClickDaImagem(View view){
    	Intent intent = new Intent(this,InfoEventoActivity.class);    
    	startActivity(intent);
    }

}