package github.skcodestack.recyclerview.section;

import android.content.Context;
import android.view.View;

import github.skcodestack.recyclerview.base.ViewHolder;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/27
 * Version  1.0
 * Description:
 */

public abstract  class StatelessSection  extends Section{

    /**
     * Create a Section object with loading/failed states but no header and footer
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int itemResourceId) {
        super();
        this.itemResourceId = itemResourceId;
    }

    /**
     * Create a Section object with loading/failed states, a custom header but no footer
     * @param headerResourceId layout resource for its header
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int headerResourceId, int itemResourceId) {
        this(itemResourceId);
        this.headerResourceId = headerResourceId;
        this.hasHeader = true;
    }

    /**
     * Create a Section object with loading/failed states, a custom header and footer
     * @param headerResourceId layout resource for its header
     * @param footerResourceId layout resource for its footer
     * @param itemResourceId layout resource for its items
     */
    public StatelessSection(int headerResourceId, int footerResourceId, int itemResourceId) {
        this(headerResourceId, itemResourceId);
        this.footerResourceId = footerResourceId;
        this.hasFooter = true;
    }

    public StatelessSection(int headerResourceId, int footerResourceId,int loadingResourceId ,int failedResourceId,int itemResourceId) {
        this(headerResourceId, itemResourceId);
        this.footerResourceId = footerResourceId;
        this.hasFooter = true;

        setFailedResourceId(loadingResourceId);
        setLoadingResourceId(failedResourceId);

    }
    @Override
    public  void onBindLoadingViewHolder(ViewHolder holder) {
        super.onBindLoadingViewHolder(holder);
    }

    @Override
    public  ViewHolder getLoadingViewHolder(Context context, View view) {
        return super.getLoadingViewHolder(context,view);
    }

    @Override
    public final void onBindFailedViewHolder(ViewHolder holder) {
        super.onBindFailedViewHolder(holder);
    }

    @Override
    public final ViewHolder getFailedViewHolder(Context context,View view) {
        return super.getFailedViewHolder(context,view);
    }
}
