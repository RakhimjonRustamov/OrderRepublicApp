package app.repbulic.order.orderrepublic.iu.main.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.repbulic.order.orderrepublic.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>{


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.category_image)ImageView imageView;
    @BindView(R.id.category_name)
    TextView foodName;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }


}
