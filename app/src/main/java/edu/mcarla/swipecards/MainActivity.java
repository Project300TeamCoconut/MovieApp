package edu.mcarla.swipecards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {


            @Override
            public void onCardDragging(Direction direction, float ratio) {

                Log.d(TAG, "onCardDragging: d=" + direction.name() + "ratio=" + ratio);

            }

            @Override
            public void onCardSwiped(Direction direction) {

                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + "d= " + direction);
                if(direction == Direction.Right){
                    Toast.makeText(MainActivity.this,"Direction Right", Toast.LENGTH_SHORT).show();
                }
                if(direction == Direction.Top){
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if(direction == Direction.Left){
                    Toast.makeText(MainActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                }
                if(direction == Direction.Bottom){
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }


                //paging
                if(manager.getTopPosition() == adapter.getItemCount() - 5 ){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: p=  " + manager.getTopPosition());

            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: p=  " + manager.getTopPosition());

            }

            @Override
            public void onCardAppeared(View view, int position) {

                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ",name" + tv.getText());

            }

            @Override
            public void onCardDisappeared(View view, int position) {

                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ",name" + tv.getText());

            }
        });


        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }

    private void paginate() {


        List<ItemModel> old = adapter.getItems();
        List<ItemModel> bew = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old ,bew);
        DiffUtil.DiffResult hasit = DiffUtil.calculateDiff(callback);
        adapter.setItems(bew);

        hasit.dispatchUpdatesTo(adapter);



    }

    private List<ItemModel> addList() {


        List<ItemModel> items = new ArrayList<>();
       items.add(new ItemModel(R.drawable.sample1, "parasite", "thriller","2019"));
       items.add(new ItemModel(R.drawable.sample2, "parasite", "thriller","2019"));
       items.add(new ItemModel(R.drawable.sample3, "parasite", "thriller","2019"));
       items.add(new ItemModel(R.drawable.sample4, "parasite", "thriller","2019"));
       items.add(new ItemModel(R.drawable.sample5, "parasite", "thriller","2019"));

        items.add(new ItemModel(R.drawable.sample1, "parasite", "thriller","2019"));
        items.add(new ItemModel(R.drawable.sample2, "parasite", "thriller","2019"));
        items.add(new ItemModel(R.drawable.sample3, "parasite", "thriller","2019"));
        items.add(new ItemModel(R.drawable.sample4, "parasite", "thriller","2019"));
        items.add(new ItemModel(R.drawable.sample5, "parasite", "thriller","2019"));

       return items;


    }
}