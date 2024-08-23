package dk.tec.witchesandwarlorksingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WaresAdapter extends ArrayAdapter<Wares> {

    private List<Wares> waresList;
    private Context context;

    public WaresAdapter(@NonNull Context context, int resource, @NonNull List<Wares> waresList) {
        super(context, resource, waresList);
        this.waresList = waresList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        Wares currentWares = waresList.get(position);
        TextView tvName= listItem.findViewById(R.id.txt_name);
        tvName.setText(currentWares.Name);
        TextView tvPrice= listItem.findViewById(R.id.txt_price);
        tvPrice.setText(String.valueOf(currentWares.Price));
        TextView tvQuantity= listItem.findViewById(R.id.txt_quantity);
        tvQuantity.setText(String.valueOf(currentWares.Quantity));

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentWares.Quantity>0){
                    Data.ShoppingCart.add(currentWares);
                    Toast.makeText(context, "Added " + currentWares.Name, Toast.LENGTH_LONG).show();
                    currentWares.Quantity--;
                    notifyDataSetChanged();
                }
            }
        });

        return listItem;
    }
}
