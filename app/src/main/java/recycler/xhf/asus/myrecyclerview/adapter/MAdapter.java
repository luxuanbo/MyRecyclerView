package recycler.xhf.asus.myrecyclerview.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import recycler.xhf.asus.myrecyclerview.R;
import recycler.xhf.asus.myrecyclerview.base.BaseViewHolder;
import recycler.xhf.asus.myrecyclerview.bean.Data;
import recycler.xhf.asus.myrecyclerview.util.TypeUtil;

/**
 * Created by asus on 2016/10/24.
 */
public class MAdapter extends RecyclerView.Adapter<BaseViewHolder> implements TypeUtil {

    private Context context;
    private Data databean;
    /**
     * 类型集合，adapter对应的数据集合
     */
    private List<Pair<Integer,Object>> superData;

    public MAdapter(Context context, Data databean) {
        this.context = context;
        this.databean = databean;

        superData = new ArrayList<Pair<Integer,Object>>();
        //添加问答结伴部分
        superData.add(new Pair<Integer,Object>(COMMUNITY_TOP,databean));

        initOtherData();
    }

    private void initOtherData() {
        for (int i = 0; i < databean.data.forum_list.size(); i++) {

            databean.data.forum_list.get(i).group.get(0).setHasTitle(true,databean.data.forum_list.get(i).name);//设置有标头
            for (int j = 0; j < databean.data.forum_list.get(i).group.size(); j++) {
                j++;
                Pair<Object, Object> objectObjectPair;//两个数据的集合  超过两个可以用list集合
                if (j == databean.data.forum_list.get(i).group.size()) {
                    objectObjectPair = wrapData(databean.data.forum_list.get(i).group.get(j - 1), null);
                } else {
                    objectObjectPair = wrapData(databean.data.forum_list.get(i).group.get(j - 1), databean.data.forum_list.get(i).group.get(j));
                }

                superData.add(new Pair<Integer, Object>(COMMUNITY_OHTER, objectObjectPair));

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return superData.get(position).first;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMMUNITY_TOP:
                return new ViewHolder_01(View.inflate(context, R.layout.layout_item1, null),context);
            case COMMUNITY_OHTER:
                return new ViewHolder_02(View.inflate(context, R.layout.layout_item2, null),context);
        }

        return new ViewHolder_02(View.inflate(context, R.layout.layout_item2, null),context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (superData.get(position).first){
            case COMMUNITY_TOP:
                ((ViewHolder_01)holder).initData(databean);
                break;
            case COMMUNITY_OHTER:
                ((ViewHolder_02)holder).initData(superData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return superData.size();
    }

    /**
     * 给数据打包，两个一块
     *
     * @return
     */
    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }
}
