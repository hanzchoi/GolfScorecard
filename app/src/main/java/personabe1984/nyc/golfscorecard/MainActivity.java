package personabe1984.nyc.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {
    private static final String PREF_FILE = "personabe1984.nyc.golfscorecard.preferences";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //Initializing Holes
        int strokes = 0;
        for(int i= 0; i < mHoles.length; i++ ){
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i,0);
            mHoles[i] =  new Hole("Hole " + (i + 1) + " :", strokes);
        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i=0; i < mHoles.length; i++){
            mEditor.putInt(KEY_STROKECOUNT + i ,mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_clear_strokes){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
