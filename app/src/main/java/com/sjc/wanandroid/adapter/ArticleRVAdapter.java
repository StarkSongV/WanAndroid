package com.sjc.wanandroid.adapter;

import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.adapter.viewholder.CommonRVViewHolder;
import com.sjc.wanandroid.common.Constant;
import com.sjc.wanandroid.common.MyApp;
import com.sjc.wanandroid.mvp.interfaces.OnArticleItemClickListener;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;

import java.util.List;

/**
 * Created by sjc on 2018/7/10 15:07
 */

public class ArticleRVAdapter extends CommonRVAdapter<ArticleBean> {
    private OnArticleItemClickListener listener;
    private int TYPE = -1;

    public ArticleRVAdapter(List<ArticleBean> list, int type) {
        super(list);
        this.TYPE = type;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_article_data;
    }

    @Override
    protected void bindData(CommonRVViewHolder holder, final ArticleBean bean, final int position) {
        TextView tv_item_author = holder.getView(R.id.tv_item_author);
        TextView tv_item_time = holder.getView(R.id.tv_item_time);
        TextView tv_item_intro = holder.getView(R.id.tv_item_intro);
        TextView tv_item_classify = holder.getView(R.id.tv_item_classify);
        ImageView iv_item_collect = holder.getView(R.id.iv_item_collect);
        tv_item_author.setText("");
        tv_item_author.append("作者: ");
        tv_item_author.append(getSpanText(bean.getAuthor()));

        tv_item_time.setText(String.valueOf(bean.getPublishTime()));
        tv_item_intro.setText(bean.getTitle());
        tv_item_classify.setText("");
        tv_item_classify.append("分类: ");
        tv_item_classify.append(getSpanText(bean.getChapterName()));
        iv_item_collect.setImageResource(bean.isCollect() ? R.drawable.ic_favorite_pink_24dp : R.drawable.ic_favorite_gray_24dp);
        switch (TYPE) {
            case Constant.PAGE_TYPE.TREE:
                tv_item_classify.setVisibility(View.GONE);
            case Constant.PAGE_TYPE.HOME:
            case Constant.PAGE_TYPE.SEARCH:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onArticleItemClick(position, bean);
                        }
                    }
                });
                iv_item_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onArticleCollectClick(position, bean);
                        }
                    }
                });
                tv_item_classify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onArticleClassifyClick(position, bean);
                        }
                    }
                });
                break;
            case Constant.PAGE_TYPE.COLLECT:
                iv_item_collect.setImageResource(R.drawable.ic_favorite_pink_24dp);
                iv_item_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onArticleDeleteClick(position, bean);
                        }
                    }
                });
                break;
        }

    }

    public Spannable getSpanText(String text) {
        Spannable spannable = Spannable.Factory.getInstance().newSpannable(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(MyApp.getInstance().getResources().getColor(R.color.hotpink));
        spannable.setSpan(foregroundColorSpan, 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannable;
    }

    public void setOnArticleItemClickListener(OnArticleItemClickListener listener) {
        this.listener = listener;
    }
}
