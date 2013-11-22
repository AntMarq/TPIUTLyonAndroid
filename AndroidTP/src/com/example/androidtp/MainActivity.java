package com.example.androidtp;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjDrawer;
import com.example.androidtp.view.CustomArrayAdapter;

public class MainActivity extends FragmentActivity 
{
	private static final String tag = "MainActivity";
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String tagImage = "Image" ;
    private String tagVideo = "Video" ;
    private String tagSon = "Son" ;
    private String tagText = "Text" ;
    private Fragment fragment ;
    private ArrayDrawerData dataDrawer ;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
       
        
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        
        dataDrawer = new ArrayDrawerData(this);
        mDrawerList.setAdapter(new CustomArrayAdapter (this.getBaseContext(), dataDrawer) );
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) 
            {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) 
            {
            	getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();// creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
      

//Select default item
        if (savedInstanceState == null) 
        {
        	GlobalMethods.ManageDirectory(MediaManager.getInstance().getDirectorypath());
            selectItem(1);           
        }  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) 
    {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Only handle with DrawerToggle if the drawer indicator is enabled.
        if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item))        
        {
            return true;
        }
		 return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener 
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
        {
        	
            selectItem(position);
        }
    }
    
    private void selectItem(int position) 
    {
    	FragmentManager manager = getSupportFragmentManager();
    	FragmentTransaction ft = manager.beginTransaction();
    	MediaManager.getInstance();

        switch (position) 
        {        
        	case 1: 
        	   
//Display VideoFragment 
        	Log.v(tagVideo, "Display VideoFragment ");
        	fragment = new VideoFragment();
        	ft.replace(R.id.content_frame, fragment,tagVideo).commit();
        	
            break;
            
        case 2:
  
//Display Sonfragment and hide others fragments
        	Log.v(tagImage, "Display Sonfragment ");
        	fragment = new SonFragment();
        	ft.replace(R.id.content_frame, fragment,tagSon).commit();
        	
            break;
            
        case 3:
        	
//Display Imagefragment and hide others fragments 
        	
        	fragment = new ImageFragment();
        	ft.replace(R.id.content_frame, fragment,tagImage).commit();
            break;
        
        
    case 4:
    	
    	//Display Textfragment and hide others fragments 
    	        	
    	fragment = new TextFragment();
    	ft.replace(R.id.content_frame, fragment,tagText).commit();
        break;
    }
        
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        String dataString = (((ObjDrawer)dataDrawer.get(position))).getTitle();      
        setTitle(dataString);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
   

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
       
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
        
    }

    /**
     * TODO
     * launch the refresh() of the current displayed fragment
     */
	public static void refreshFragment() {
		
	}

    
}