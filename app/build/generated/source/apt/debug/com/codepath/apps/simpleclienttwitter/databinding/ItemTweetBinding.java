package com.codepath.apps.simpleclienttwitter.databinding;
import com.codepath.apps.simpleclienttwitter.R;
import com.codepath.apps.simpleclienttwitter.BR;
import android.view.View;
public class ItemTweetBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivProfileImage, 1);
        sViewsWithIds.put(R.id.tvUsername, 2);
        sViewsWithIds.put(R.id.tvScreenname, 3);
        sViewsWithIds.put(R.id.tvHours, 4);
        sViewsWithIds.put(R.id.tvBody, 5);
        sViewsWithIds.put(R.id.ivTweetImage, 6);
    }
    // views
    public final android.widget.ImageView ivProfileImage;
    public final android.widget.ImageView ivTweetImage;
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.TextView tvBody;
    public final android.widget.TextView tvHours;
    public final android.widget.TextView tvScreenname;
    public final android.widget.TextView tvUsername;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemTweetBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivProfileImage = (android.widget.ImageView) bindings[1];
        this.ivTweetImage = (android.widget.ImageView) bindings[6];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvBody = (android.widget.TextView) bindings[5];
        this.tvHours = (android.widget.TextView) bindings[4];
        this.tvScreenname = (android.widget.TextView) bindings[3];
        this.tvUsername = (android.widget.TextView) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
        }
        return false;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemTweetBinding>inflate(inflater, com.codepath.apps.simpleclienttwitter.R.layout.item_tweet, root, attachToRoot, bindingComponent);
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.codepath.apps.simpleclienttwitter.R.layout.item_tweet, null, false), bindingComponent);
    }
    public static ItemTweetBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemTweetBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_tweet_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemTweetBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}