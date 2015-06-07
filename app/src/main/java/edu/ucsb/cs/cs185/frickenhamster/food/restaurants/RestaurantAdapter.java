package edu.ucsb.cs.cs185.frickenhamster.food.restaurants;

import android.content.*;
import android.net.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.squareup.picasso.*;

import edu.ucsb.cs.cs185.frickenhamster.food.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 6/5/2015
 * Time: 4:22 PM
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    public enum ResType {YELP, REST}

    public class RestaurantViewItem {
        String name;
        String imageUrl;
        String clickLink;
        ResType type;

        public RestaurantViewItem(String name, String imageUrl, String clickLink, ResType type) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.clickLink = clickLink;
            this.type = type;
        }
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.restaurant_card_view);
            imageView = (ImageView) itemView.findViewById(R.id.restaurant_image_view);
            textView = (TextView) itemView.findViewById(R.id.restaurant_text_view);

        }
    }


    private ArrayList<RestaurantViewItem> restaurantViewItems;
    private Context context;

    public RestaurantAdapter(Context context) {
        this.context = context;

        restaurantViewItems = new ArrayList<RestaurantViewItem>();
        restaurantViewItems.add(new RestaurantViewItem("Look up on Yelp", "http://www.cdkglobaldigitalmarketing.com/wp-content/uploads/2013/04/Yelp_Estimate_tool.jpg", "", ResType.YELP));
        restaurantViewItems.add(new RestaurantViewItem("The Krusty Krab", "http://img1.wikia.nocookie.net/__cb20121221034123/spongebob/images/7/76/Krusty_krab_high_quality.jpg", "http://www.yelp.com/biz/the-krusty-krab-portland", ResType.REST));
    }

    @Override
    public int getItemCount() {
        return restaurantViewItems.size();
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        RestaurantViewItem item = restaurantViewItems.get(position);
        final String clickLink = item.clickLink;

        holder.textView.setText(item.name);
        Picasso.with(context).load(item.imageUrl).placeholder(android.R.drawable.spinner_background).error(android.R.drawable.ic_menu_delete).into(holder.imageView);
        if (item.type == ResType.YELP) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.yelp.com/search?find_desc=pizza"));
                    context.startActivity(intent);
                }
            });
        } else {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("krab", clickLink);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(clickLink));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v);
        return vh;
    }
}
